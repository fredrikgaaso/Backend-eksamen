package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Orders;
import PGR209.Eksam.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Orders> getAllOrders(){
        return orderRepo.findAll();
    }

    public Stream<Orders> getOneOrdersPage(int pageNumber) { return orderRepo.findAll().stream().skip((pageNumber * 10) - 10).limit(10); }

    public Orders getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Orders createOrder(Customer customer, Machine machine){
        Orders newOrder = new Orders();
        newOrder.setCustomer(customer);
        newOrder.getMachine().add(machine);

       Orders savedOrder = orderRepo.save(newOrder);

        return savedOrder;
    }

    public void deleteOrder(Long id) {
     orderRepo.deleteById(id);
    }

    public Orders updateOrders(Customer customer, Machine machine, Long id){
        var orderToUpdate = getOrderById(id);
        orderToUpdate.setCustomer(customer);
        orderToUpdate.getMachine().add(machine);
       Orders savedOrder = orderRepo.save(orderToUpdate);
        return savedOrder;
    }

}
