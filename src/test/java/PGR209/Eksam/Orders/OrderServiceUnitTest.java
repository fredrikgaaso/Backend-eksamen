package PGR209.Eksam.Orders;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Orders;
import PGR209.Eksam.Repo.CustomerRepo;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Repo.OrderRepo;
import PGR209.Eksam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceUnitTest {

    @MockBean
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;
    @MockBean
    MachineRepo machineRepo;
    @MockBean
    CustomerRepo customerRepo;



    @Test
    void shouldFetchAllOrders(){
        List<Orders> ordersList = List.of(new Orders(), new Orders(), new Orders());
        when(orderRepo.findAll()).thenReturn(ordersList);

        var orders = orderService.getAllOrders();
        assert orders.size() == 3;
    }

    @Test
    void shouldFetchOneOrdersPage(){

        List<Orders> ordersList = List.of(new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders(), new Orders());
        when(orderRepo.findAll()).thenReturn(ordersList);

        var ordersPage1 = orderService.getOneOrdersPage(1);
        var ordersPage2 = orderService.getOneOrdersPage(2);

        assert ordersPage1.count() == 10;
        assert ordersPage2.count() == 2;
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
        Orders deletedOrder = orderService.getOrderById(ordersId);
        assert deletedOrder == null;
    }
    @Test
    void createOnlyOrder(){
        var createdOrder = orderService.createOnlyOrder();
        assert createdOrder != null;
    }
    @Test
    void createOrderWithCustomerAndMachine(){
        Machine machine = new Machine();
        Customer customer = new Customer();
        Orders expectedOrder = new Orders();
        machine.setMachineName("TestMachine");
        machine.setMachineId(1L);
        customer.setCustomerEmail("TestMail@mail.com");
        customer.setCustomerName("TestCustomer");
        customer.setCustomerId(1L);
        expectedOrder.setCustomer(customer);
        expectedOrder.getMachine().add(machine);
        expectedOrder.setOrderId(1L);

        when(orderRepo.save(Mockito.any(Orders.class))).thenReturn(expectedOrder);

        Orders result = orderService.createOrder(customer, machine);

        assertEquals(expectedOrder, result);
    }
    @Test
    void updateOrder(){
        long orderId = 1L;
        Customer updatedCustomer = new Customer("TestCustomer", "Testmail@mail.com");
        Machine updatedMachine = new Machine("TestMachine");


        Orders oldOrders = new Orders();
        when(orderRepo.findById(orderId)).thenReturn(Optional.of(oldOrders));

        Orders savedOrders = new Orders();
        savedOrders.setOrderId(1L);
        savedOrders.setCustomer(new Customer("OldCostumerName", "OldCustomerMail"));
        savedOrders.getMachine().add(new Machine("OldMachine"));
        when(orderRepo.save(Mockito.any(Orders.class))).thenReturn(savedOrders);

        orderService.updateOrders(updatedCustomer, updatedMachine, orderId);

        Orders updatedOrders = orderService.getOrderById(orderId);

        assert updatedOrders.getCustomer() == updatedCustomer;
        assert updatedOrders.getMachine().contains(updatedMachine);
    }
}
