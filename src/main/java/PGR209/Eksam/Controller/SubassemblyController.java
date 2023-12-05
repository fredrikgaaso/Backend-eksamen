package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Subassembly")
public class SubassemblyController {

    private final SubassemblyService subassemblyService;

    @Autowired
    public SubassemblyController(SubassemblyService subassemblyService) {
        this.subassemblyService = subassemblyService;
    }

    @PostMapping
    public Subassembly createSubassembly(@RequestBody Subassembly subassembly) {
         return subassemblyService.createSubassembly(subassembly);
    }

    @GetMapping
    public List<Subassembly> getSubassemblys(){
        return subassemblyService.getAllSubassemblies();
    }

    @GetMapping("/{id}")
    public Subassembly getSubassemblyById(@PathVariable Long id) {
        return subassemblyService.getSubassemblyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubassemblyById(@PathVariable Long id) {
        subassemblyService.deleteSubassembly(id);
    }
}
