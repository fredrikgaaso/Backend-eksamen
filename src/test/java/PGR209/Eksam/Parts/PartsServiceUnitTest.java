package PGR209.Eksam.Parts;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Repo.PartsRepo;
import PGR209.Eksam.Service.PartsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
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
    void createOnlyPart(){
        String partName = "TestPart";
        Parts expectedPart = new Parts();
        expectedPart.setPartsName(partName);
        when(partsRepo.save(Mockito.any(Parts.class))).thenReturn(expectedPart);

        Parts createdPart = partsService.createParts(partName);
        assertEquals(expectedPart, createdPart);
    }

    @Test
    void shouldFetchOneOrdersPage(){

        List<Parts> partsList = List.of(new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts(), new Parts());
        when(partsRepo.findAll()).thenReturn(partsList);

        var partsPage1 = partsService.getOnePartsPage(1);
        var partsPage2 = partsService.getOnePartsPage(2);

        assert partsPage1.count() == 10;
        assert partsPage2.count() == 2;
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
        Parts deletedPart = partsService.getPartsById(partsId);
        assert deletedPart == null;
    }
    @Test
    void createPart(){
        String partName = "TestPart";
        Parts expectedParts = new Parts();
        expectedParts.setPartsId(1L);
        expectedParts.setPartsName(partName);

        when(partsRepo.save(Mockito.any(Parts.class))).thenReturn(expectedParts);

        Parts result = partsService.createParts(partName);

        assertEquals(expectedParts, result);
    }
    @Test
    void updatePart() {
        long partId = 1L;
        String updatedPartName = "TestPart";

        Parts oldParts = new Parts();
        when(partsRepo.findById(partId)).thenReturn(Optional.of(oldParts));

        Parts savedPart = new Parts();
        savedPart.setPartsId(partId);
        savedPart.setPartsName("OldName");
        when(partsRepo.save(Mockito.any(Parts.class))).thenReturn(savedPart);

        partsService.updateParts(updatedPartName, partId);

        Parts updatedPart = partsService.getPartsById(partId);

        assert Objects.equals(updatedPart.getPartsName(), updatedPartName);
    }
}
