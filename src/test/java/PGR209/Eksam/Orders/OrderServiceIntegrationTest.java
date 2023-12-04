package PGR209.Eksam.Orders;

import PGR209.Eksam.Service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceIntegrationTest {
    @Autowired
    OrderService orderService;

    @Test
    @Transactional
    void getAllOrders(){
        var orders = orderService.getAllOrders();

        assert orders.size()==1;
        assert orders.get(0).getCustomer().getCustomerName().equals("Hank");
    }

    @Test
    @Transactional
    void shouldFetchOrderById(){
        var order = orderService.getOrderById(1L);
        assert order.getOrderId()==1L;
    }
    @Test
    @Transactional
    void shouldDeleteOrder(){
        orderService.deleteOrder(1L);

        assert orderService.getOrderById(1L) == null;
    }
    @Test
    @Transactional
    void updateOrder(){
    }
}
