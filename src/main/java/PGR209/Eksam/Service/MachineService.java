package PGR209.Eksam.Service;

import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Repo.MachineRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MachineService {
    private final MachineRepo machineRepo;

    public MachineService(MachineRepo machineRepo) {
        this.machineRepo = machineRepo;
    }

    public List<Machine> getAllMachines(){
        return machineRepo.findAll();
    }

    public Stream<Machine> getOneMachinePage(int pageNumber) { return machineRepo.findAll().stream().skip((pageNumber * 10) - 10).limit(10); }

    public Machine getMachineById(Long id) {
        return machineRepo.findById(id).orElse(null);
    }

    public Machine createOnlyMachine(String machineName){
        var machine = new Machine();
        machine.setMachineName(machineName);
        return machineRepo.save(machine);
    }
    public Machine createMachine(String machineName, Subassembly subassembly ){
        var machine = new Machine();
        machine.setMachineName(machineName);
        machine.getSubassemblies().add(subassembly);
        return machineRepo.save(machine);
    }

    public void deleteMachine(Long id){
        machineRepo.deleteById(id);
    }


    public Machine updateMachine(String machineName, Subassembly subassembly, Long id){

        var machineToUpdate = getMachineById(id);
        machineToUpdate.setMachineName(machineName);
        machineToUpdate.getSubassemblies().add(subassembly);

        Machine savedMachine = machineRepo.save(machineToUpdate);
        return savedMachine;
    }
}
