package redis.watch;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import redis.watch.transaction.TransactionEndPoint;


@Configuration
@ApplicationPath("/api")
public class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        register(TransactionEndPoint.class);
    }

}
