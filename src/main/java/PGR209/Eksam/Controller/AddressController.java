package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address createAddress(@RequestBody String addressName, Customer customer) {
         return addressService.createAddress(addressName, customer);
    }

    @GetMapping
    public List<Address> getAddress(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/page={pageNumber}")
    public Stream<Address> getAddressPage(@PathVariable int pageNumber){
        return addressService.getOneAddressPage(pageNumber);
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
