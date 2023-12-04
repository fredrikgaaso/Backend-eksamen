package PGR209.Eksam.Subassembly;

import PGR209.Eksam.Service.SubassemblyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubassemblyServiceIntegrationTest {
    @Autowired
    SubassemblyService subassemblyService;

    @Test
    @Transactional
    void getAllSubassemblies(){
        var subassemblies = subassemblyService.getAllSubassemblies();

        assert subassemblies.size()==1;
        assert subassemblies.get(0).getSubassemblyName().equals("Motherboard");
    }

    @Test
    @Transactional
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
    void updateSubassembly(){
    }
}
