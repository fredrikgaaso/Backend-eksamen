package PGR209.Eksam.Customer;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Repo.AddressRepo;
import PGR209.Eksam.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceIntegrationTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    AddressRepo addressRepo;

    @Test
    @Transactional
    void getAllCustomers(){
        var customers = customerService.getAllCustomer();

        assert customers.size()==1;
        assert customers.get(0).getCustomerName().equals("Hank");
    }

    @Test
    @Transactional
    void shouldFetchCustomerById(){
        var customer = customerService.getCustomerById(1L);
        assert customer.getCustomerId()==1L;
    }
    @Test
    @Transactional
    void shouldDeleteCustomer(){
        customerService.deleteCustomer(1L);

        assert customerService.getCustomerById(1L) == null;
    }
    @Test
    @Transactional
    void updateCustomer(){
        var customerId = 1L;
        var oldCustomerName = customerService.getCustomerById(customerId).getCustomerName();
        var oldCustomerEmail = customerService.getCustomerById(customerId).getCustomerEmail();
        var oldCustomerAddresses = customerService.getCustomerById(customerId).getAddresses().size();
        String newCustomerName = "TestCustomer";
        String newCustomerEmail = "TestCustomer@mail.com";
        Address newAddress = addressRepo.save(new Address("TestAddress 22"));
        customerService.updateCustomer(newCustomerName, newCustomerEmail,newAddress , customerId);
        var updatedCustomer = customerService.getCustomerById(1L);
        assert oldCustomerName != updatedCustomer.getCustomerName();
        assert oldCustomerEmail != updatedCustomer.getCustomerEmail();
        assert oldCustomerAddresses != updatedCustomer.getAddresses().size();
    }
}
