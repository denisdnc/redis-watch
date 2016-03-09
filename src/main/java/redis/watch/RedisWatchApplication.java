package redis.watch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class RedisWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisWatchApplication.class, args);
	}

//	@Bean
//	public Jackson2JsonRedisSerializer<Model> jsonRedisSerializer() {
//		return new Jackson2JsonRedisSerializer<>(Model.class);
//	}

//	@Bean
//	RedisTemplate<String, Model> redisTemplate(RedisConnectionFactory connectionFactory) {
//		RedisTemplate<String, Model> template = new RedisTemplate<>();
//		template.setConnectionFactory(connectionFactory);
//		template.setDefaultSerializer(jsonRedisSerializer());
//		return template;
//	}

	@Bean
	StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(connectionFactory);
		
		// explicitly enable transaction support
		template.setEnableTransactionSupport(true);
		return template;
	}

}
