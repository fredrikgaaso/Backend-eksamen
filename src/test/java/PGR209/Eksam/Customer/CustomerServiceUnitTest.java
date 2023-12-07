package PGR209.Eksam.Customer;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.CustomerRepo;
import PGR209.Eksam.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceUnitTest {

    @MockBean
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerService customerService;
    @Test
    void shouldFetchAllCustomers(){
        List<Customer> customerList = List.of(new Customer(), new Customer(), new Customer());
        when(customerRepo.findAll()).thenReturn(customerList);

        var customers = customerService.getAllCustomer();
        assert customers.size() == 3;
    }

    @Test
    void shouldFetchOneCustomerPage(){

        List<Customer> customersList = List.of(new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer(), new Customer());
        when(customerRepo.findAll()).thenReturn(customersList);

        var customerPage1 = customerService.getOneCustomerPage(1);
        var customerPage2 = customerService.getOneCustomerPage(2);

        assert customerPage1.count() == 10;
        assert customerPage2.count() == 2;
    }

    @Test
    void shouldFetchCustomerById(){
        long customerId = 1L;
        Customer customer = new Customer();
        when(customerRepo.findById(customerId)).thenReturn(Optional.of(customer));

        var fetchedCustomers = customerService.getCustomerById(customerId);
        assert customer == fetchedCustomers;
    }
    @Test
    void shouldDeleteCustomer(){
        long customerId = 1L;
        customerService.deleteCustomer(customerId);

        verify(customerRepo).deleteById(customerId);
        Customer deletedCustomer = customerService.getCustomerById(customerId);
        assert deletedCustomer == null;
    }
    @Test
    void createOnlyCustomer(){
        String customerName = "TestCustomer";
        String customerEmail = "TestMail@mail.com";
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerName(customerName);
        expectedCustomer.setCustomerEmail(customerEmail);
        when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(expectedCustomer);

        Customer createdCustomer = customerService.createOnlyCustomer(customerName,customerEmail);
        assertEquals(expectedCustomer, createdCustomer);
    }
    @Test
    void createCustomerWithAddress(){
        Customer expectedCustomer = new Customer("TestCustomer", "TestMail@mail.com");
        Address expectedAddress = new Address("TestAddress 22");
        String customerName = "TestCustomer";
        String customerEmail = "TestMail@mail.com";
        expectedCustomer.getAddresses().add(expectedAddress);

        when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(expectedCustomer);

        Customer result = customerService.createCustomer(customerName, customerEmail, expectedAddress);

        assertEquals(expectedCustomer, result);
    }
    @Test
    void updateCustomer(){
        long customerId = 1L;
        String updatedName = "TestCustomer";
        String updatedEmail = "TestMail@mail.com";
        Address newAddress = new Address("TestAddress 22");

        Customer oldCustomer = new Customer();
        when(customerRepo.findById(customerId)).thenReturn(Optional.of(oldCustomer));

        Customer savedCustomer = new Customer();
        savedCustomer.setCustomerId(customerId);
        savedCustomer.setCustomerName("OldCustomerName");
        savedCustomer.setCustomerEmail("OldCustomerMail");
        savedCustomer.getAddresses().add(new Address("OldCustomerAddress"));
        when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(savedCustomer);

        customerService.updateCustomer(updatedName, updatedEmail, newAddress, customerId);

        Customer updatedCustomer = customerService.getCustomerById(customerId);

        assert updatedCustomer.getCustomerName() == updatedName;
        assert updatedCustomer.getCustomerEmail() == updatedEmail;
        assert updatedCustomer.getAddresses().contains(newAddress);
    }
}
