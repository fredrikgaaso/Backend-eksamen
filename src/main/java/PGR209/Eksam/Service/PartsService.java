package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Repo.PartsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PartsService {
    private final PartsRepo partsRepo;

    public PartsService(PartsRepo partsRepo) {
        this.partsRepo = partsRepo;
    }

    public List<Parts> getAllParts(){
        return partsRepo.findAll();
    }

    public Stream<Parts> getOnePartsPage(int pageNumber) { return partsRepo.findAll().stream().skip((pageNumber * 10) - 10).limit(10); }

    public Parts getPartsById(Long id) {
        return partsRepo.findById(id).orElse(null);
    }

    public Parts createParts(String partName){
        Parts newPart = new Parts();
       newPart.setPartsName(partName);

       Parts savedPart = partsRepo.save(newPart);

        return savedPart;
    }

    public void deleteParts(Long id){
        partsRepo.deleteById(id);
    }

    public Parts updateParts(String partName, Long id){
        var partsToUpdate = getPartsById(id);
        partsToUpdate.setPartsName(partName);
        Parts savedPart = partsRepo.save(partsToUpdate);
        return savedPart;

    }
}
