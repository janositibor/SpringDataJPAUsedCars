package TZJanosi.usedCars.controller;

import TZJanosi.usedCars.dto.CreateCarCommand;
import TZJanosi.usedCars.model.CarCondition;
import TZJanosi.usedCars.service.CarService;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InitExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(context);
        CarService carService = applicationContext.getBean(CarService.class);
        WebTestClient webTestClient = applicationContext.getBean(WebTestClient.class);
        carService.deleteAllCar();
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Corolla",25, CarCondition.POOR,295000))
                .exchange();
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Corolla",15,CarCondition.EXCELLENT,95000))
                .exchange();
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("BMW","X3",19,CarCondition.NORMAL,350000))
                .exchange();
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Auris",5,CarCondition.EXCELLENT,55892))
                .exchange();
    }
}
