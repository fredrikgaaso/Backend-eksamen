package PGR209.Eksam.Machine;

import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Repo.MachineRepo;
import PGR209.Eksam.Repo.SubassemblyRepo;
import PGR209.Eksam.Service.MachineService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MachineServiceIntegrationTest {
    @Autowired
    MachineService machineService;
    @Autowired
    MachineRepo machineRepo;
    @Autowired
    SubassemblyRepo subassemblyRepo;

    @Test
    @Transactional
    void getAllMachines(){
        var machines = machineService.getAllMachines();

        assert machines.size()==12;
        assert machines.get(0).getMachineName().equals("Mac");
    }

    @Test
    void shouldFetchOneMachinePage(){

        var machinePage1 = machineService.getOneMachinePage(1);
        var machinePage2 = machineService.getOneMachinePage(2);

        assert machinePage1.count() <= 10 && machinePage2.count() <= 10;
    }

    @Test
    @Transactional
    void shouldFetchMachineById(){
        var machine = machineService.getMachineById(1L);
        assert machine.getMachineId()==1L;
    }
    @Test
    @Transactional
    void shouldDeleteMachine(){
        machineService.deleteMachine(1L);

        assert machineService.getMachineById(1L) == null;
    }
    @Test
    @Transactional
    void updateMachine(){
        var machineId = 1L;
        var oldMachineName = machineService.getMachineById(machineId).getMachineName();
        var oldSubassembly = machineService.getMachineById(machineId).getSubassemblies().size();
        String newMachine = "TestMachine";

        Subassembly newSubassembly = subassemblyRepo.save(new Subassembly("TestSubassembly"));
        machineService.updateMachine(newMachine, newSubassembly, machineId);

        var updatedMachine = machineService.getMachineById(1L);

        assert oldMachineName != updatedMachine.getMachineName();
        assert oldSubassembly != updatedMachine.getSubassemblies().size();
    }
}
