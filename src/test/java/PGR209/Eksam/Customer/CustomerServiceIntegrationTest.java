package PGR209.Eksam.Customer;

import PGR209.Eksam.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceIntegrationTest {
    @Autowired
    CustomerService customerService;

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
    }
}
