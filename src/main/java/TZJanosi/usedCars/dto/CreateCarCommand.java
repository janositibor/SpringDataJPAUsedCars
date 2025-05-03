package TZJanosi.usedCars.dto;

import TZJanosi.usedCars.model.CarCondition;
import TZJanosi.usedCars.model.Seller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarCommand {
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @PositiveOrZero
    private int ageInYears;
    @NotNull
    private CarCondition condition;
    @NotNull
    private List<KilometerStateDto> kilometerStates=new ArrayList<>();
    private Seller seller;

    public CreateCarCommand(String brand, String model, int ageInYears, CarCondition condition,int actualKm) {
        this.brand = brand;
        this.model = model;
        this.ageInYears = ageInYears;
        this.condition = condition;
        kilometerStates.add(new KilometerStateDto(actualKm, LocalDate.now()));
    }
}
