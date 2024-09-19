package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Model.Subassembly;
import PGR209.Eksam.Service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/subassembly")
public class SubassemblyController {

    private final SubassemblyService subassemblyService;

    @Autowired
    public SubassemblyController(SubassemblyService subassemblyService) {
        this.subassemblyService = subassemblyService;
    }

    @PostMapping
    public Subassembly createSubassembly(@RequestBody String subassembly, Parts parts) {
         return subassemblyService.createSubassembly(subassembly, parts);
    }

    @GetMapping
    public List<Subassembly> getSubassemblys(){
        return subassemblyService.getAllSubassemblies();
    }

    @GetMapping("/page={pageNumber}")
    public Stream<Subassembly> getSubassemblyPage(@PathVariable int pageNumber){
        return subassemblyService.getOneSubassemblyPage(pageNumber);
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
