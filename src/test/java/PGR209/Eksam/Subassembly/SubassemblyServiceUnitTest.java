package PGR209.Eksam.Subassembly;

import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Repo.SubassemblyRepo;
import PGR209.Eksam.Service.SubassemblyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    }
    @Test
    void createSubassembly(){
        String subassemblyName = "TestSubassembly";
        Subassembly expectedSubassembly = new Subassembly();
        expectedSubassembly.setSubassemblyId(1L);
        expectedSubassembly.setSubassemblyName(subassemblyName);

        when(subassemblyRepo.save(Mockito.any(Subassembly.class))).thenReturn(expectedSubassembly);

        Subassembly result = subassemblyService.createSubassembly(subassemblyName);
        assertEquals(expectedSubassembly, result);
    }
    @Test
    void updatePart(){}
}
