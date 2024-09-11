package co.com.inventory.inventoryservice.controllers;



import co.com.inventory.inventoryservice.models.ProductDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class InventoryControllerTestContainerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    TestRestTemplate restTemplate;

    ProductDto productDtoTomato ;

    @BeforeEach
    void setUp() {
        productDtoTomato = ProductDto.builder()
                .name("RED TOMATOE")
                .units("pounds")
                .description("Red tomatoe")
                .quantity(10d)
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void connectionEstablished() {
        Assertions.assertThat(postgres.isCreated()).isTrue();
        Assertions.assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void get() {
    }

    @DisplayName("Validar la creacion de un objeto")
    @Test
    void post() {

        ResponseEntity<String> response = restTemplate.postForEntity("/inventory", productDtoTomato, String.class );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseEntity<ProductDto[]> getResponse = restTemplate.getForEntity("/inventory?id=" + response.getBody(), ProductDto[].class);

        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertEquals(productDtoTomato.getDescription(), getResponse.getBody()[0].getDescription());
        assertEquals(productDtoTomato.getName(), getResponse.getBody()[0].getName());
        assertEquals(productDtoTomato.getUnits(), getResponse.getBody()[0].getUnits());
        assertEquals(productDtoTomato.getQuantity(), getResponse.getBody()[0].getQuantity());




    }

    @Test
    void put() {
    }

    @Test
    void patch() {
    }
}