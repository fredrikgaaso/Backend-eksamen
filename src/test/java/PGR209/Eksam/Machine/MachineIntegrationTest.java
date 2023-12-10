package PGR209.Eksam.Machine;

import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Service.MachineService;
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
public class MachineIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MachineService machineService;

    @Test
    void shouldGetMachines() throws Exception {
        mockMvc.perform(get("/api/machines"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }
    @Test
    void shouldGetMachineByID() throws Exception {
        long machineId = 1L;
        Machine machine = new Machine();
        machine.setMachineId(machineId);
        when(machineService.getMachineById(machineId)).thenReturn(machine);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/machines/{id}", machineId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.machineId").value(machineId));
    }


}
