package redis.watch;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDAO {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, Model> template;
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringTemplate;
	
//	@Autowired
//	RedisOperations<String, String> redisOperations;

	public void insert() {

		Map<String, String> map = new HashMap<>();
		map.put("name", "name");
		map.put("price", "10");

		stringTemplate.opsForHash().putAll("hash", map);
	}
	
	public void watch(){
		
		stringTemplate.watch("hash");
		
		stringTemplate.multi();
		
		stringTemplate.exec();
	}
	
	public void update() {

		Map<String, String> map = new HashMap<>();
		map.put("name", "name");
		map.put("price", "10");
		
//		Integer i = Integer.valueOf(1);
		
//		while(true){
//			i++;
//			stringTemplate.opsForHash().put("hash", "price", i.toString());
//		}
		
		for (Integer i = 0; i < 10; i++) {
			stringTemplate.opsForHash().put("hash", "price", i.toString());
		}
	}
}
