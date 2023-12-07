package PGR209.Eksam.Address;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.AddressRepo;
import PGR209.Eksam.Service.AddressService;
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
    void shouldFetchOneAddressPage(){

        List<Address> addressList = List.of(new Address(), new Address(), new Address(), new Address(), new Address(), new Address(), new Address(), new Address(), new Address(), new Address(), new Address(), new Address());
        when(addressRepo.findAll()).thenReturn(addressList);

        var addressPage1 = addressService.getOneAddressPage(1);
        var addressPage2 = addressService.getOneAddressPage(2);

        assert addressPage1.count() == 10;
        assert addressPage2.count() == 2;
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
    void createOnlyAddress(){
        String addressName = "TestAddress 22";
        Address expectedAddress = new Address();
        expectedAddress.setAddressName(addressName);
        when(addressRepo.save(Mockito.any(Address.class))).thenReturn(expectedAddress);

        Address createdAddress = addressService.createOnlyAddress(addressName);
        assertEquals(expectedAddress, createdAddress);
    }
    @Test
    void createAddressWithCustomer(){
        Address expectedAddress = new Address("TestAddress 22");
        Customer expectedCustomer = new Customer("TestCustomer", "Testmail@mail.com");
        expectedAddress.getCustomers().add(expectedCustomer);
        String newAddress = "TestAddress 22";

        when(addressRepo.save(Mockito.any(Address.class))).thenReturn(expectedAddress);

        Address createdAddress = addressService.createAddress(newAddress, expectedCustomer);
        assertEquals(expectedAddress, createdAddress);
    }
    @Test
    void updateAddress(){
        long addressId = 1L;
        String updatedName = "TestAddress 22";
        Customer newCustomer = new Customer("TestCustomer", "TestMail@mail.com");

        Address oldAddress = new Address();
        when(addressRepo.findById(addressId)).thenReturn(Optional.of(oldAddress));

        Address savedAddress = new Address();
        savedAddress.setAddressId(addressId);
        savedAddress.setAddressName("OldAddressName");
        savedAddress.getCustomers().add(new Customer("OldCustomerName", "OldCustomerMail"));
        when(addressRepo.save(Mockito.any(Address.class))).thenReturn(savedAddress);

        addressService.updateAddress(updatedName, newCustomer, addressId);

        Address updatedAddress = addressService.getAddressById(addressId);

        assert updatedAddress.getAddressName() == updatedName;
        assert updatedAddress.getCustomers().contains(newCustomer);
    }
}
