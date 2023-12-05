package PGR209.Eksam.Orders;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Repo.CustomerRepo;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
public class OrderServiceIntegrationTest {
    @Autowired
    OrderService orderService;

    @Autowired
    MachineRepo machineRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @DirtiesContext
    void getAllOrders(){
        Iterable orders = orderService.getAllOrders();

        assert orders != null;

    }

    @Test
    void createOrder(){
        Machine machine = new Machine("TestMachine");
        Customer customer = new Customer("TestCustomer", "TestCustomer@test.com");
        orderService.createOrder(customer,machine);

        var createdOrder = orderService.getOrderById(1L);
        assert createdOrder.getMachine().contains("TestMachine");
        assert createdOrder.getCustomer().getCustomerName()=="TestCustomer";
        assert createdOrder.getCustomer().getCustomerEmail()=="TestCustomer@test.com";

    }
    @Test
    void shouldFetchOrderById(){
        var order = orderService.getOrderById(1L);
        assert order.getOrderId()==1L;
    }
    @Test
    void shouldDeleteOrder(){
        orderService.deleteOrder(1L);

        assert orderService.getOrderById(1L) == null;
    }
    @Test
    void updateOrder(){
    }
}
