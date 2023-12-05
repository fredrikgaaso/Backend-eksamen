package PGR209.Eksam.Orders;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Repo.CustomerRepo;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        Iterable orders = orderService.getAllOrders();

        assert orders != null;

    }

    @Test
    @Transactional
    void createOrder(){
        Machine machine = machineRepo.save(new Machine("TestMachine"));
        Customer customer = customerRepo.save(new Customer("TestCustomer", "TestCustomer@test.com"));

        orderService.createOrder(customer,machine);

        var createdOrder = orderService.getOrderById(2L);

        assert createdOrder.getMachine().size() == 1;
        assert createdOrder.getMachine().get(0).getMachineName() == "TestMachine";

    }
    @Test
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
        var orderId = 1L;
        var oldOrderMachines = orderService.getOrderById(orderId).getMachine().size();
        var oldOrderCustomer = orderService.getOrderById(orderId).getCustomer().getCustomerName();

        Machine newMachine = machineRepo.save(new Machine("TestMachine"));

        Customer newCustomer = customerRepo.save(new Customer("TestCustomer", "TestCustomer@mail.com"));
        orderService.updateOrders(newCustomer, newMachine, orderId);
        var updatedOrder = orderService.getOrderById(1L);
        assert oldOrderMachines != updatedOrder.getMachine().size();
        assert oldOrderCustomer != updatedOrder.getCustomer().getCustomerName();
    }
}
