package PGR209.Eksam.Address;

import PGR209.Eksam.Service.AddressService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceIntegrationTest {
    @Autowired
    AddressService addressService;

    @Test
    @Transactional
    void getAllAddresses(){
        var addresses = addressService.getAllAddresses();

        assert addresses.size()==1;
        assert addresses.get(0).getAddressName().equals("hanks gate 22");
    }

    @Test
    @Transactional
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
    }
}
