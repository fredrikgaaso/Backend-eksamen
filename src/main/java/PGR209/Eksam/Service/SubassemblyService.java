package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Repo.SubassemblyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubassemblyService {
    private final SubassemblyRepo subassemblyRepo;

    public SubassemblyService(SubassemblyRepo subassemblyRepo) {
        this.subassemblyRepo = subassemblyRepo;
    }
    public List<Subassembly> getAllSubassemblies(){
        return subassemblyRepo.findAll();
    }
    public Subassembly getSubassemblyById(Long id) {
        return subassemblyRepo.findById(id).orElse(null);
    }
    public Subassembly createSubassembly(Subassembly newSubassembly){
        return subassemblyRepo.save(newSubassembly);
    }
    public void deleteSubassembly(Long id){
        subassemblyRepo.deleteById(id);
    }
    public Subassembly updateSubassembly(String assemblyName, Parts parts, Long id){
        var subassemblyToUpdate = getSubassemblyById(id);
        subassemblyToUpdate.setSubassemblyName(assemblyName);
        subassemblyToUpdate.getParts().add(parts);
        Subassembly savedSubassembly = subassemblyRepo.save(subassemblyToUpdate);
        return savedSubassembly;
    }
}
