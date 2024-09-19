package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody String customerName, String customerEmail, Address address) {
         return customerService.createCustomer(customerName, customerEmail, address);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/page={pageNumber}")
    public Stream<Customer> getOneCustomerPage(@PathVariable int pageNumber){
        return customerService.getOneCustomerPage(pageNumber);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
