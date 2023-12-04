package PGR209.Eksam.Parts;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Repo.PartsRepo;
import PGR209.Eksam.Service.PartsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PartsServiceUnitTest {

    @MockBean
    private PartsRepo partsRepo;
    @Autowired
    private PartsService partsService;
    @Test
    void shouldFetchAllParts(){
        List<Parts> partsList = List.of(new Parts(), new Parts(), new Parts());
        when(partsRepo.findAll()).thenReturn(partsList);

        var parts = partsService.getAllParts();
        assert parts.size() == 3;
    }
    @Test
    void shouldFetchPartById(){
        long partsId = 1L;
        Parts parts = new Parts();
        when(partsRepo.findById(partsId)).thenReturn(Optional.of(parts));

        var fetchedParts = partsService.getPartsById(partsId);
        assert parts == fetchedParts;
    }
    @Test
    void shouldDeletePart(){
        long partsId = 1L;
        partsService.deleteParts(partsId);

        verify(partsRepo).deleteById(partsId);
    }
    @Test
    void createPart(){
        Parts newPart = new Parts("TestPart");
         partsService.createParts(newPart);
        Parts createdPart = partsService.getPartsById(1L);
        partsRepo.save(createdPart);

        assertNotNull(createdPart);

        assert partsService.getPartsById(1L).getPartsName() == "TestPart";
    }
    @Test
    void updatePart(){}
}
