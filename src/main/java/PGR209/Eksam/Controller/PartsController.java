package PGR209.Eksam.Controller;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Parts;
import PGR209.Eksam.Service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/Parts")
public class PartsController {

    private final PartsService partsService;

    @Autowired
    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @PostMapping
    public Parts createParts(@RequestBody String partName) {
         return partsService.createParts(partName);
    }

    @GetMapping
    public List<Parts> getParts(){
        return partsService.getAllParts();
    }

    @GetMapping("/page={pageNumber}")
    public Stream<Parts> getPartsPage(@PathVariable int pageNumber){
        return partsService.getOnePartsPage(pageNumber);
    }

    @GetMapping("/{id}")
    public Parts getPartsById(@PathVariable Long id) {
        return partsService.getPartsById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePartsById(@PathVariable Long id) {
        partsService.deleteParts(id);
    }
}
