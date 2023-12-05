package PGR209.Eksam.Orders;

import PGR209.Eksam.Model.Orders;
import PGR209.Eksam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldGetOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }
    @Test
    void shouldGetOrderByID() throws Exception {
        long orderId = 1L;
        Orders order = new Orders();
        order.setOrderId(orderId);
        when(orderService.getOrderById(orderId)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/{id}", orderId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(orderId));
    }

    @Test
    void shouldCreateNewOrder() throws Exception{
    }

}
