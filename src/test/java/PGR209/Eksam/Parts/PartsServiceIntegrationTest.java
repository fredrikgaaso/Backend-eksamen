package PGR209.Eksam.Parts;

import PGR209.Eksam.Repo.PartsRepo;
import PGR209.Eksam.Service.PartsService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PartsServiceIntegrationTest {
    @Autowired
    PartsService partsService;

    @Autowired
    PartsRepo partsRepo;

    @Test
    void getAllParts(){
        var parts = partsService.getAllParts();

        assert parts.size()==12;
        assert parts.get(0).getPartsName().equals("Chip");
    }

    @Test
    void shouldFetchOnePartsPage(){

        var partsPage1 = partsService.getOnePartsPage(1);
        var partsPage2 = partsService.getOnePartsPage(2);

        assert partsPage1.count() <= 10 && partsPage2.count() <= 10;
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
    @Transactional
    void createPart(){
        String partName = "TestPart";

        var createdPart = partsService.createParts(partName);

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
