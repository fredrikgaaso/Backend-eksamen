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
    void shouldFetchOneOrdersPage(){

        var ordersPage1 = orderService.getOneOrdersPage(1);
        var ordersPage2 = orderService.getOneOrdersPage(2);

        assert ordersPage1.count() <= 10 && ordersPage2.count() <= 10;
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
    void createEmptyOrder(){
        var emptyOrder = orderService.createOnlyOrder();

        assert emptyOrder.getMachine().isEmpty();
        assert emptyOrder.getCustomer() == null;
    }

    @Test
    @Transactional
    void createOrderWthCustomerAndMachine(){
        Machine machine = machineRepo.save(new Machine("TestMachine"));
        Customer customer = customerRepo.save(new Customer("TestCustomer", "TestCustomer@test.com"));

        var createdOrder = orderService.createOrder(customer,machine);

        assert createdOrder.getMachine().size() == 1;
        assert createdOrder.getMachine().get(0).getMachineName() == "TestMachine";

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
