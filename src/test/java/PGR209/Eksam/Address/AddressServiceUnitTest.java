package PGR209.Eksam.Address;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Repo.AddressRepo;
import PGR209.Eksam.Service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceUnitTest {

    @MockBean
    private AddressRepo addressRepo;

    @Autowired
    private AddressService addressService;

    @Test
    void shouldFetchAllAddresses(){
        List<Address> addressList = List.of(new Address(), new Address());
        when(addressRepo.findAll()).thenReturn(addressList);

        var addresses = addressService.getAllAddresses();
        assert addresses.size() == 2;
    }

    @Test
    void shouldFetchAddressById(){
        long addressId = 1L;
        Address address = new Address();
        when(addressRepo.findById(addressId)).thenReturn(Optional.of(address));

        var fetchedAddress = addressService.getAddressById(addressId);
        assert address == fetchedAddress;
    }
    @Test
    void shouldDeleteAddress(){
        long addressId = 1L;
        addressService.deleteAddress(addressId);

        verify(addressRepo).deleteById(addressId);
    }
    @Test
    void updateAddress(){}
}
