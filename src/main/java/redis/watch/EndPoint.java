package redis.watch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPoint {

	@Autowired
	private RedisDAO dao;

	@RequestMapping(value = "/model", method = RequestMethod.GET)
	public void insert() {
		dao.insert();
	}

	@RequestMapping(value = "/watch", method = RequestMethod.GET)
	public void watch() {
		dao.watch();
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void update() {
		dao.update();
	}

}
