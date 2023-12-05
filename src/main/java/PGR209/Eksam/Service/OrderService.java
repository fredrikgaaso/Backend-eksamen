package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Orders;
import PGR209.Eksam.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Orders getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }
    public Orders createOrder(Customer customer, Machine machine){
        Orders newOrder = new Orders();
        newOrder.setCustomer(customer);
        newOrder.getMachine().add(machine);

        return orderRepo.save(newOrder);
    }
    public void deleteOrder(Long id) {
     orderRepo.deleteById(id);
    }

}
