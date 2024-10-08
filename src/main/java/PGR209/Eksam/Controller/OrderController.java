package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Orders;
import PGR209.Eksam.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders createOrder(@RequestBody Customer customer, Machine machine) {
         return orderService.createOrder(customer, machine);
    }

    @GetMapping
    public List<Orders> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/page={pageNumber}")
    public Stream<Orders> getOrdersPage(@PathVariable int pageNumber){
        return orderService.getOneOrdersPage(pageNumber);
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
