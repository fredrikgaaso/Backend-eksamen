package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public List<Address> getAllAddresses(){
        return addressRepo.findAll();
    }

    public Stream<Address> getOneAddressPage(int pageNumber) { return addressRepo.findAll().stream().skip((pageNumber * 10) - 10).limit(10); }

    public Address getAddressById(Long id) {
        return addressRepo.findById(id).orElse(null);
    }
    public Address createOnlyAddress(String addressName){
        var address = new Address(addressName);
       Address newAddress =  addressRepo.save(address);
       return newAddress;
    }
    public Address createAddress(String addressName, Customer customer){
        var address = new Address();
        address.setAddressName(addressName);
        address.getCustomers().add(customer);
        return addressRepo.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepo.deleteById(id);
    }

    public Address updateAddress(String addressName, Customer customer, Long id){
        var addressToUpdate = getAddressById(id);
        addressToUpdate.setAddressName(addressName);
        addressToUpdate.getCustomers().add(customer);
        Address savedAddress = addressRepo.save(addressToUpdate);
        return savedAddress;
    }
}



