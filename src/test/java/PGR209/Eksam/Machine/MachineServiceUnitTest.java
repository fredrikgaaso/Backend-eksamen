package PGR209.Eksam.Machine;

import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Service.MachineService;
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
        Machine deletedMachine = machineService.getMachineById(machineId);
        assert deletedMachine == null;
    }
    @Test
    void createOnlyMachine(){
        String machineName = "TestMachine";
        Machine expectedMachine = new Machine();
        expectedMachine.setMachineName(machineName);
        when(machineRepo.save(Mockito.any(Machine.class))).thenReturn(expectedMachine);

        Machine createdMachine = machineService.createOnlyMachine(machineName);
        assertEquals(expectedMachine, createdMachine);
    }
    @Test
    void createMachineWithSubassembly(){
        Machine expectedMachine = new Machine("TestMachine");
        Subassembly expectedSubassembly = new Subassembly("TestSubassembly");
        expectedMachine.setMachineId(1L);
        String newMachine = "TestMachine";

        when(machineRepo.save(Mockito.any(Machine.class))).thenReturn(expectedMachine);

        Machine result = machineService.createMachine(newMachine, expectedSubassembly);
        assertEquals(expectedMachine, result);
    }
    @Test
    void updateMachine(){
        long machineId = 1L;
        String updatedName = "TestMachine";
        Subassembly newSubassembly = new Subassembly("TestSubassembly");

        Machine oldMachine = new Machine();
        when(machineRepo.findById(machineId)).thenReturn(Optional.of(oldMachine));

        Machine savedMachine = new Machine();
        savedMachine.setMachineId(machineId);
        savedMachine.setMachineName("OldMachineName");
        savedMachine.getSubassemblies().add(new Subassembly("OldSubassembly"));
        when(machineRepo.save(Mockito.any(Machine.class))).thenReturn(savedMachine);

        machineService.updateMachine(updatedName, newSubassembly, machineId);

        Machine updatedMachine = machineService.getMachineById(machineId);

        assert Objects.equals(updatedMachine.getMachineName(), updatedName);
        assert updatedMachine.getSubassemblies().contains(newSubassembly);
    }
}
