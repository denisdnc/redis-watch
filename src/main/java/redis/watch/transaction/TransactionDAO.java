package redis.watch.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAO {

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringTemplate;

	public String updateSKU(String sku, String qty, boolean sleep) {

		List<Object> result = null;

		int attempts = 10;

		while (result == null && attempts > 0) {

			attempts--;

			final String newValue = some(sku, qty);

			// execute a transaction
			result = stringTemplate.execute(new SessionCallback<List<Object>>() {
				@Override
				public List<Object> execute(RedisOperations operations) throws DataAccessException {

					// Key to WATCH
					operations.watch(getKey(sku));

					// Start Transaction
					operations.multi();

					sleep(sleep);

					// Add SKU hash to Redis
					stringTemplate.opsForHash().put(getKey(sku), "qty", newValue);

					// This will contain the results of all ops in the
					// transaction
					return operations.exec();
				}

			});

			if (result == null) {
				System.out.println("Concurrency occurred!");
			}

		}

		System.out.println("Finished!");
		return sku;
	}

	private String some(String sku, String qty) {

		String current = getCurrent(sku);

		if (current != null) {
			Integer currentQty = Integer.valueOf(current);

			Integer incomingQty = Integer.valueOf(qty);

			Integer finalValue = Integer.sum(currentQty, incomingQty);

			return finalValue.toString();
		} else {
			return qty;
		}
	}

	private String getKey(String sku) {
		return "sku:" + sku;
	}

	private String getCurrent(String sku) {
		return (String) stringTemplate.opsForHash().get(getKey(sku), "qty");
	}

	private void sleep(boolean sleep) {
		if (sleep) {
			// Sleep to test WATCH
			try {
				System.out.println("And now my watch begins!");
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("And now his watch is over!");
		}
	}
}
