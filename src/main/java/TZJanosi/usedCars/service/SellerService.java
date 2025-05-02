package TZJanosi.usedCars.service;

import TZJanosi.usedCars.dto.CreateCarCommand;
import TZJanosi.usedCars.dto.CreateSellerCommand;
import TZJanosi.usedCars.dto.SellerDto;
import TZJanosi.usedCars.model.Car;
import TZJanosi.usedCars.model.Seller;
import TZJanosi.usedCars.repository.CarRepository;
import TZJanosi.usedCars.repository.KilometerStateRepository;
import TZJanosi.usedCars.repository.SellerRepository;
import jakarta.persistence.CascadeType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private ModelMapper modelMapper;
    private CarRepository carRepository;
    private SellerRepository sellerRepository;
    private KilometerStateRepository kmStateRepository;

    public SellerService(ModelMapper modelMapper, CarRepository carRepository, SellerRepository sellerRepository, KilometerStateRepository kmStateRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.kmStateRepository = kmStateRepository;
    }

    public List<SellerDto> findAll(){
        List<Seller> sellers=sellerRepository.findAll();

        return sellers.stream().map(s->modelMapper.map(s, SellerDto.class)).toList();
    }


    public List<SellerDto> findByName(Optional<String> prefix) {
        if(prefix.isEmpty()){
            return findAll();
        }
        else{
            return findWithName(prefix.get());
        }
    }

    private List<SellerDto> findWithName(String prefix) {
        List<Seller> sellers=sellerRepository.findWithName(prefix);
        return sellers.stream().map(s->modelMapper.map(s, SellerDto.class)).toList();
    }

    @Transactional
    public SellerDto addSeller(@Valid CreateSellerCommand command) {
        Seller seller=modelMapper.map(command, Seller.class);
        List<Car> carsToSave=new ArrayList<>();
        if(command.getCars()!=null){
            for(CreateCarCommand carCommand : command.getCars()){
                Car car=modelMapper.map(carCommand, Car.class);
                car.setSeller(seller);
                carsToSave.add(car);
            }
            if(carsToSave.size()>0){
//            cascade = {CascadeType.PERSIST,CascadeType.REMOVE} is used in Seller.java so the next line is not needed anymore
//            carRepository.saveAll(carsToSave);
                seller.setCars(carsToSave);
            }
        }

        sellerRepository.save(seller);

        return modelMapper.map(seller, SellerDto.class);
    }

    @Transactional
    public void deleteAll() {
        sellerRepository.deleteAll();
        sellerRepository.resetIdGenerator();
    }
}
