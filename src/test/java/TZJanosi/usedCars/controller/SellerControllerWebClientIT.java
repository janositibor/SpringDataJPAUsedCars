package TZJanosi.usedCars.controller;

import TZJanosi.usedCars.dto.*;
import TZJanosi.usedCars.model.CarCondition;
import TZJanosi.usedCars.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SellerControllerWebClientIT {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    SellerService sellerService;

    @BeforeEach
    void init(){
        sellerService.deleteAll();
        webTestClient
                .post()
                .uri("/api/sellers")
                .bodyValue(new CreateSellerCommand("Seftes"))
                .exchange();

    }

    @Test
    void addSellerTest() {
        webTestClient
                .get()
                .uri("api/sellers/all")
                .exchange()
                .expectBodyList(SellerDto.class)
                .hasSize(1)
                .contains(new SellerDto(1L,"Seftes"));


    }
}