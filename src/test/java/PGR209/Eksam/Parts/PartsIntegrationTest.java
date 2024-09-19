package PGR209.Eksam.Parts;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Service.PartsService;
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
public class PartsIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PartsService partsService;

    @Test
    void shouldGetParts() throws Exception {
        mockMvc.perform(get("/api/parts"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }
    @Test
    void shouldGetPartByID() throws Exception {
        long partId = 1L;
        Parts parts = new Parts();
        parts.setPartsId(partId);
        when(partsService.getPartsById(partId)).thenReturn(parts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/parts/{id}", partId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.partsId").value(partId));
    }


}
