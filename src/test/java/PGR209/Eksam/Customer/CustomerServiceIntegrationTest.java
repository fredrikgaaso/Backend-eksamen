package PGR209.Eksam.Customer;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Repo.AddressRepo;
import PGR209.Eksam.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class CustomerServiceIntegrationTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    AddressRepo addressRepo;

    @Test
    void getAllCustomers(){
        var customers = customerService.getAllCustomer();

        assert customers.size()==15;
        assert customers.get(0).getCustomerName().equals("Hank");
    }

    @Test
    void shouldFetchOneCustomerPage(){

        var customerPage1 = customerService.getOneCustomerPage(1);
        var customerPage2 = customerService.getOneCustomerPage(2);

        assert customerPage1.count() <= 10 && customerPage2.count() <= 10;
    }

    @Test
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
    void createOnlyCustomer(){
        String customerName = "TestCustomer";
        String customerEmail = "TestMail@mail.com";

        var createdCustomer = customerService.createOnlyCustomer(customerName, customerEmail);

        assert Objects.equals(createdCustomer.getCustomerName(), customerName);
        assert Objects.equals(createdCustomer.getCustomerEmail(), customerEmail);
        assert createdCustomer.getAddresses().isEmpty();

    }

    @Test
    @Transactional
    void createCustomerWithAddress(){
        String customerName = "TestCustomer";
        String customerEmail = "TestMail@mail.com";
        Address address = new Address("TestAddress 22");

        var createdCustomer = customerService.createCustomer(customerName,customerEmail,address);

        assert Objects.equals(createdCustomer.getCustomerName(), customerName);
        assert Objects.equals(createdCustomer.getCustomerEmail(), customerEmail);
        assert createdCustomer.getAddresses().contains(address);
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
        assert !Objects.equals(oldCustomerName, updatedCustomer.getCustomerName());
        assert !Objects.equals(oldCustomerEmail, updatedCustomer.getCustomerEmail());
        assert oldCustomerAddresses != updatedCustomer.getAddresses().size();
    }
}
