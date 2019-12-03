import com.pyp.cast.store.domain.PO.Order;
import org.junit.Test;

import java.util.UUID;

public class test {
    @Test
    public void test(){
        String oid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        System.out.println(oid);
        Order order = new Order();
        order.setOid(oid);
        System.out.println(order);
    }
}
