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

			// execute a transaction
			result = stringTemplate.execute(new SessionCallback<List<Object>>() {
				@Override
				public List<Object> execute(RedisOperations operations) throws DataAccessException {

					// Key to WATCH
					operations.watch("sku:" + sku);

					// Start Transaction
					operations.multi();

					if (sleep) {
						// Sleep to test WATCH
						try {
							System.out.println("And now my watch begin!");
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("And now his watch is over!");
					}

					// Add SKU hash to Redis
					stringTemplate.opsForHash().put("sku:" + sku, "qty", qty);

					// This will contain the results of all ops in the
					// transaction
					return operations.exec();
				}
			});
		}

		return sku;
	}
}
