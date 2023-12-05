package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import PGR209.Eksam.Repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Address getAddressById(Long id) {
        return addressRepo.findById(id).orElse(null);
    }
    public Address createAddress(Address address){
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



