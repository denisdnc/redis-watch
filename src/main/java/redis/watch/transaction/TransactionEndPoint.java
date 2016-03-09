package redis.watch.transaction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/transaction")
public class TransactionEndPoint {

    @Autowired
    private TransactionDAO dao;

    @GET
    @Path("/{sku}/{qtdy}")
    public String serviceSync(@PathParam("sku") String sku, @PathParam("qtdy") String size,
            @QueryParam("sleep") boolean sleep) {
        dao.updateSKU(sku, size, sleep);
        return sku;
    }

}
