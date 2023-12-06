package PGR209.Eksam.Address;

import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.CustomerRepo;
import PGR209.Eksam.Service.AddressService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceIntegrationTest {
    @Autowired
    AddressService addressService;

    @Autowired
    CustomerRepo customerRepo;
    @Test
    void getAllAddresses(){
        var addresses = addressService.getAllAddresses();

        assert addresses.size()==15;
        assert addresses.get(0).getAddressName().equals("hanks gate 22");
    }

    @Test
    void shouldFetchOneAddressPage(){

        var addressPage1 = addressService.getOneAddressPage(1);
        var addressPage2 = addressService.getOneAddressPage(2);

        assert addressPage1.count() <= 10 && addressPage2.count() <= 10;
    }

    @Test
    void shouldFetchAddressById(){
        var address = addressService.getAddressById(1L);
        assert address.getAddressId()==1L;
    }
    @Test
    @Transactional
    void shouldDeleteAddress(){
        addressService.deleteAddress(1L);

        assert addressService.getAddressById(1L) == null;
    }
    @Test
    @Transactional
    void updateAddress(){
        var addressId = 1L;
        var oldAddressName = addressService.getAddressById(addressId).getAddressName();
        var oldAddressCustomers = addressService.getAddressById(addressId).getCustomers().size();

        String newAddressName = "TestAddress 22";

        Customer newCustomer = customerRepo.save(new Customer("TestCustomer", "TestCustomer@mail.com"));

        addressService.updateAddress(newAddressName, newCustomer, addressId);
        var updatedAddress = addressService.getAddressById(1L);
        assert oldAddressName != updatedAddress.getAddressName();
        assert oldAddressCustomers != updatedAddress.getCustomers().size();
    }
}
