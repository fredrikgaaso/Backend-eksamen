package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }
    public Customer createCustomer(Customer newCustomer){
        return customerRepo.save(newCustomer);
    }
    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

}
