package redis.watch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDAO {

	// @Autowired
	// @Qualifier("redisTemplate")
	// private RedisTemplate<String, Model> template;

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringTemplate;

	public void updateSKU(String sku, Integer qty) {

		System.out.println("And now my watch begin!");

		// execute a transaction
		stringTemplate.execute(new SessionCallback<List<Object>>() {
			public List<Object> execute(RedisOperations operations) throws DataAccessException {
				operations.watch("key");
				operations.multi();

				Map<String, String> map = new HashMap<>();
				map.put("name", "name2");
				map.put("price", "2");

				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				stringTemplate.opsForHash().putAll("key", map);

				// This will contain the results of all ops in the transaction
				return operations.exec();
			}
		});

		System.out.println("And now his watch is over!");

	}
}
