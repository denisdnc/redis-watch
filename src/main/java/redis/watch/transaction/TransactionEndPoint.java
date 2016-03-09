package redis.watch.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.watch.RedisDAO;

@RestController
public class TransactionEndPoint {

  @Autowired
  private RedisDAO dao;

  @RequestMapping(path = "/api/transaction/{sku}/{size}", method = RequestMethod.GET)
  public String update(@PathVariable String sku, @PathVariable Integer size) {
//    System.out.println(String.format("SKU: %s | Size: %d", sku, size));
    dao.updateSKU(sku, size);
    return sku;
  }

}
