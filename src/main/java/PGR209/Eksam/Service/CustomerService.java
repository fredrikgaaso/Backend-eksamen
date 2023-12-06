package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }

    public Stream<Customer> getOneCustomerPage(int pageNumber) { return customerRepo.findAll().stream().skip((pageNumber * 10) - 10).limit(10); }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer newCustomer){
        return customerRepo.save(newCustomer);
    }

    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

    public Customer updateCustomer(String customerName, String customerEmail, Address address, Long id){
        var customerToUpdate = getCustomerById(id);
        customerToUpdate.setCustomerName(customerName);
        customerToUpdate.setCustomerEmail(customerEmail);
        customerToUpdate.getAddresses().add(address);
        Customer savedCustomer = customerRepo.save(customerToUpdate);
        return savedCustomer;
    }

}
