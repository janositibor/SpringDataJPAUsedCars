package TZJanosi.usedCars.controller;

import TZJanosi.usedCars.dto.CarDto;
import TZJanosi.usedCars.dto.CreateSellerCommand;
import TZJanosi.usedCars.dto.SellerDto;
import TZJanosi.usedCars.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    private SellerService service;

    public SellerController(SellerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<SellerDto> findAll(){
        return service.findAll();
    }

    @GetMapping
    public List<SellerDto> findByName(@RequestParam("prefix") Optional<String> prefix){
        return service.findByName(prefix);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SellerDto addSeller(@Valid @RequestBody CreateSellerCommand command){
        return service.addSeller(command);
    }
}
