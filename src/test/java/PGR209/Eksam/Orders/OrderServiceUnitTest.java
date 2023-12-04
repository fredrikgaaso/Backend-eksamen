package PGR209.Eksam.Orders;

import PGR209.Eksam.Model.Orders;
import PGR209.Eksam.Repo.OrderRepo;
import PGR209.Eksam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceUnitTest {

    @MockBean
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;

    @Test
    void shouldFetchAllOrders(){
        List<Orders> ordersList = List.of(new Orders(), new Orders(), new Orders());
        when(orderRepo.findAll()).thenReturn(ordersList);

        var orders = orderService.getAllOrders();
        assert orders.size() == 3;
    }
    @Test
    void shouldFetchOrderById(){
        long orderId = 1L;
        Orders orders = new Orders();
        when(orderRepo.findById(orderId)).thenReturn(Optional.of(orders));

        var fetchedOrders = orderService.getOrderById(orderId);
        assert orders == fetchedOrders;
    }
    @Test
    void shouldDeleteOrder(){
        long ordersId = 1L;
        orderService.deleteOrder(ordersId);

        verify(orderRepo).deleteById(ordersId);
    }
    @Test
    void updateOrder(){}
}
