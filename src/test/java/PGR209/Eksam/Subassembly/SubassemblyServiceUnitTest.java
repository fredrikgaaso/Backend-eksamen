package PGR209.Eksam.Subassembly;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Repo.SubassemblyRepo;
import PGR209.Eksam.Service.SubassemblyService;
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
public class SubassemblyServiceUnitTest {

    @MockBean
    private SubassemblyRepo subassemblyRepo;
    @Autowired
    private SubassemblyService subassemblyService;

    @Test
    void shouldFetchAllSubassembly(){
        List<Subassembly> subassemblyList = List.of(new Subassembly(), new Subassembly(), new Subassembly());
        when(subassemblyRepo.findAll()).thenReturn(subassemblyList);

        var Subassembly = subassemblyService.getAllSubassemblies();
        assert Subassembly.size() == 3;
    }

    @Test
    void shouldFetchOneSubassemblyPage(){

        List<Subassembly> subassemblyList = List.of(new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly(), new Subassembly());
        when(subassemblyRepo.findAll()).thenReturn(subassemblyList);

        var subassemblyPage1 = subassemblyService.getOneSubassemblyPage(1);
        var subassemblyPage2 = subassemblyService.getOneSubassemblyPage(2);

        assert subassemblyPage1.count() == 10;
        assert subassemblyPage2.count() == 2;
    }

    @Test
    void shouldFetchSubassemblyById(){
        long subassemblyId = 1L;
        Subassembly subassembly = new Subassembly();
        when(subassemblyRepo.findById(subassemblyId)).thenReturn(Optional.of(subassembly));

        var fetchedSubassembly = subassemblyService.getSubassemblyById(subassemblyId);
        assert subassembly == fetchedSubassembly;
    }
    @Test
    void shouldDeleteSubassembly(){
        long subassemblyId = 1L;
        subassemblyService.deleteSubassembly(subassemblyId);

        verify(subassemblyRepo).deleteById(subassemblyId);

        Subassembly deletedSubassembly = subassemblyService.getSubassemblyById(subassemblyId);
        assert deletedSubassembly == null;
    }
    @Test
    void createOnlySubassembly(){
        String subassemblyName = "TestSubassembly";
        Subassembly expectedSubassembly = new Subassembly();
        expectedSubassembly.setSubassemblyName(subassemblyName);
        when(subassemblyRepo.save(Mockito.any(Subassembly.class))).thenReturn(expectedSubassembly);

        Subassembly createdSubassembly = subassemblyService.createOnlySubassembly(subassemblyName);
        assertEquals(expectedSubassembly, createdSubassembly);
    }
    @Test
    void createSubassemblyWithPart(){
        String subassemblyName = "TestSubassembly";
        Subassembly expectedSubassembly = new Subassembly();
        Parts newPart = new Parts("TestPart");
        expectedSubassembly.setSubassemblyId(1L);
        expectedSubassembly.setSubassemblyName(subassemblyName);

        when(subassemblyRepo.save(Mockito.any(Subassembly.class))).thenReturn(expectedSubassembly);

        Subassembly result = subassemblyService.createSubassembly(subassemblyName, newPart);

        assertEquals(expectedSubassembly, result);
    }
    @Test
    void updateSubassembly(){
        long subassemblyId = 1L;
        String updatedName = "TestSubassembly";
        Parts newPart = new Parts("TestPart");

        Subassembly oldSubassembly = new Subassembly();
        when(subassemblyRepo.findById(subassemblyId)).thenReturn(Optional.of(oldSubassembly));

        Subassembly savedSubassembly = new Subassembly();
        savedSubassembly.setSubassemblyId(subassemblyId);
        savedSubassembly.setSubassemblyName("OldName");
        savedSubassembly.getParts().add(new Parts("OldPart"));
        when(subassemblyRepo.save(Mockito.any(Subassembly.class))).thenReturn(savedSubassembly);

        subassemblyService.updateSubassembly(updatedName, newPart, subassemblyId);

        Subassembly updatedSubassembly = subassemblyService.getSubassemblyById(subassemblyId);

        assert updatedSubassembly.getSubassemblyName() == updatedName;
        assert updatedSubassembly.getParts().contains(newPart);
    }
}
