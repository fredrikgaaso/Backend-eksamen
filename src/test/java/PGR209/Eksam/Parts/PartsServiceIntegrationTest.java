package PGR209.Eksam.Parts;

import PGR209.Eksam.Service.PartsService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PartsServiceIntegrationTest {
    @Autowired
    PartsService partsService;

    @Test
    @Transactional
    void getAllParts(){
        var parts = partsService.getAllParts();

        assert parts.size()==1;
        assert parts.get(0).getPartsName().equals("Chip");
    }

    @Test
    void shouldFetchPartsById(){
        var parts = partsService.getPartsById(1L);
        assert parts.getPartsId()==1L;
    }
    @Test
    @Transactional
    void shouldDeleteParts(){
        partsService.deleteParts(1L);

        assert partsService.getPartsById(1L) == null;
    }

    @Test
    void createPart(){
        String partName = "TestPart";

        partsService.createParts(partName);

        var createdPart = partsService.getPartsById(3L);

        assert createdPart.getPartsName() == partName;
    }
    @Test
    @Transactional
    void updateParts(){
        var partId = 1L;
        var oldPartsName = partsService.getPartsById(partId).getPartsName();
        String partsName = "TestPart";

        partsService.updateParts(partsName, partId);

        var updatedPart = partsService.getPartsById(1L);

        assert oldPartsName != updatedPart.getPartsName();
    }
}
