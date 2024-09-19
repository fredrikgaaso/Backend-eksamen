package PGR209.Eksam.Subassembly;

import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Service.SubassemblyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class SubassemblyIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SubassemblyService subassemblyService;

    @Test
    void shouldGetSubassemblies() throws Exception {
        mockMvc.perform(get("/api/subassembly"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }
    @Test
    void shouldGetSubassemblyByID() throws Exception {
        long subassemblyId = 1L;
        Subassembly subassembly = new Subassembly();
        subassembly.setSubassemblyId(subassemblyId);
        when(subassemblyService.getSubassemblyById(subassemblyId)).thenReturn(subassembly);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subassembly/{id}", subassemblyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.subassemblyId").value(subassemblyId));
    }

}
