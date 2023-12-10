package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Machine;
import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/machines")
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    public Machine createMachine(@RequestBody String machine, Subassembly subassembly) {
         return machineService.createMachine( machine, subassembly);
    }

    @GetMapping
    public List<Machine> getMachines(){
        return machineService.getAllMachines();
    }

    @GetMapping("/page={pageNumber}")
    public Stream<Machine> getMachinePage(@PathVariable int pageNumber){
        return machineService.getOneMachinePage(pageNumber);
    }

    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineService.getMachineById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMachineById(@PathVariable Long id) {
        machineService.deleteMachine(id);
    }
}
