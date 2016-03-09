package redis.watch.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionEndPoint {

	@Autowired
	private TransactionDAO dao;

	@RequestMapping(path = "/api/transaction/{sku}/{size}", method = RequestMethod.GET)
	public String update(@PathVariable String sku, @PathVariable String size,
			@RequestParam(value = "sleep", required = false) boolean sleep) {
		dao.updateSKU(sku, size, sleep);
		return sku;
	}

}
