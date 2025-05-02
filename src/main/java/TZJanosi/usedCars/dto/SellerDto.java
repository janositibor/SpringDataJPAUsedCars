package TZJanosi.usedCars.dto;

import TZJanosi.usedCars.model.Car;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SellerDto {
    private Long id;
    private String name;
    private List<CarDto> cars=new ArrayList<>();

    public SellerDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellerDto(Long id, String name, List<CarDto> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }
}
