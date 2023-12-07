package PGR209.Eksam.Machine;

import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Service.MachineService;
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
public class MachineServiceUnitTest {

    @MockBean
    private MachineRepo machineRepo;
    @Autowired
    private MachineService machineService;
    @Test
    void shouldFetchAllMachine(){
        List<Machine> machineList = List.of(new Machine(), new Machine(), new Machine());
        when(machineRepo.findAll()).thenReturn(machineList);

        var machines = machineService.getAllMachines();
        assert machines.size() == 3;
    }

    @Test
    void shouldFetchOneMachinePage(){

        List<Machine> machineList = List.of(new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine(), new Machine());
        when(machineRepo.findAll()).thenReturn(machineList);

        var machinePage1 = machineService.getOneMachinePage(1);
        var machinePage2 = machineService.getOneMachinePage(2);

        assert machinePage1.count() == 10;
        assert machinePage2.count() == 2;
    }

    @Test
    void shouldFetchMachineById(){
        long machineId = 1L;
        Machine Machine = new Machine();
        when(machineRepo.findById(machineId)).thenReturn(Optional.of(Machine));

        var fetchedMachine = machineService.getMachineById(machineId);
        assert Machine == fetchedMachine;
    }
    @Test
    void shouldDeleteMachine(){
        long machineId = 1L;
        machineService.deleteMachine(machineId);

        verify(machineRepo).deleteById(machineId);
    }
    @Test
    void createMachine(){
        String machineName = "TestMachine";
        Machine expectedMachine = new Machine();
        expectedMachine.setMachineId(1L);
        expectedMachine.setMachineName(machineName);

        when(machineRepo.save(Mockito.any(Machine.class))).thenReturn(expectedMachine);

        Machine result = machineService.createMachine(expectedMachine);
        assertEquals(expectedMachine, result);
    }
    @Test
    void updatePart(){}
}
