package PGR209.Eksam.Orders;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Repo.CustomerRepo;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceIntegrationTest {
    @Autowired
    OrderService orderService;

    @Autowired
    MachineRepo machineRepo;

    @Autowired
    CustomerRepo customerRepo;


    @Test
    void getAllOrders(){
        var orders = orderService.getAllOrders();

        assert orders.size()==1;
        assert orders.get(0).getCustomer().getCustomerName().equals("Hank");
    }

    @Test
    void createOrder(){
        Machine machine = new Machine("TestMachine");
        machineRepo.save(machine);
        Customer customer = new Customer("TestCustomer", "TestCustomer@test.com");
        customerRepo.save(customer);
        orderService.createOrder(customer,machine);

        var createdOrder = orderService.getOrderById(2L);
        assert createdOrder.getMachine().getMachineName()=="TestMachine";
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
