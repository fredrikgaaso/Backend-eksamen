package PGR209.Eksam.Subassembly;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Repo.PartsRepo;
import PGR209.Eksam.Service.SubassemblyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class SubassemblyServiceIntegrationTest {
    @Autowired
    SubassemblyService subassemblyService;

    @Autowired
    PartsRepo partsRepo;

    @Test
    void getAllSubassemblies(){
        var subassemblies = subassemblyService.getAllSubassemblies();

        assert subassemblies.size()==12;
        assert subassemblies.get(0).getSubassemblyName().equals("Motherboard");
    }

    @Test
    void shouldFetchOneSubassemblyPage(){

        var subassemblyPage1 = subassemblyService.getOneSubassemblyPage(1);
        var subassemblyPage2 = subassemblyService.getOneSubassemblyPage(2);

        assert subassemblyPage1.count() <= 10 && subassemblyPage2.count() <= 10;
    }

    @Test
    void shouldFetchSubassemblyById(){
        var subassembly = subassemblyService.getSubassemblyById(1L);
        assert subassembly.getSubassemblyId()==1L;
    }
    @Test
    @Transactional
    void shouldDeleteSubassembly(){
        subassemblyService.deleteSubassembly(1L);

        assert subassemblyService.getSubassemblyById(1L) == null;
    }

    @Test
    @Transactional
    void createOnlySubassembly(){
        String subassemblyName = "TestSubassembly";
        var createdSubassembly = subassemblyService.createOnlySubassembly(subassemblyName);

        assert Objects.equals(createdSubassembly.getSubassemblyName(), subassemblyName);
        assert createdSubassembly.getParts().isEmpty();
    }

    @Test
    @Transactional
    void createSubassemblyWithPart(){
        String subassemblyName = "TestSubassembly";
        Parts parts = new Parts("TestPart");

        var createdSubassembly = subassemblyService.createSubassembly(subassemblyName, parts);

        assert Objects.equals(createdSubassembly.getSubassemblyName(), subassemblyName);
        assert createdSubassembly.getParts().contains(parts);
    }
    @Test
    @Transactional
    void updateSubassembly(){
        Long subassemblyId = 1L;
        var oldSubassemblyName = subassemblyService.getSubassemblyById(subassemblyId).getSubassemblyName();
        var oldSubassemblyParts = subassemblyService.getSubassemblyById(subassemblyId).getParts().size();
        String newSubassemblyName = "TestSubassembly";
        Parts parts = partsRepo.save(new Parts("TestParts"));
        subassemblyService.updateSubassembly(newSubassemblyName, parts, subassemblyId);
        var updatedSubassembly = subassemblyService.getSubassemblyById(1L);

        assert !Objects.equals(oldSubassemblyName, updatedSubassembly.getSubassemblyName());
        assert oldSubassemblyParts != updatedSubassembly.getParts().size();
    }
}
