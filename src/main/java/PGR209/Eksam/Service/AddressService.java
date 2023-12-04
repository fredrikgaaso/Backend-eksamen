package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Address;
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
    public Address deleteAddress(Long id) {
        Address addressToDelete = addressRepo.findById(id).orElse(null);
        addressRepo.deleteById(id);
        return addressToDelete;
    }
}



