package PGR209.Eksam.Machine;

import PGR209.Eksam.Service.MachineService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MachineServiceIntegrationTest {
    @Autowired
    MachineService machineService;

    @Test
    @Transactional
    void getAllMachines(){
        var machines = machineService.getAllMachines();

        assert machines.size()==1;
        assert machines.get(0).getMachineName().equals("Mac");
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
    }
}
