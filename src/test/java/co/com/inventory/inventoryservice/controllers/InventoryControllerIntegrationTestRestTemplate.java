package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryControllerIntegrationTestRestTemplate {

    @Autowired
    private TestRestTemplate client ;

    ObjectMapper mapper = new ObjectMapper();

    @LocalServerPort
    int port;

    ProductDto productDtoTomato ;

    ProductDto productDtoCheesse ;

    ProductDto productDtoMilk ;


    @BeforeEach
    void setUp() {

        productDtoTomato = ProductDto.builder()
                .name("RED TOMATOE")
                .units("pounds")
                .description("Red tomatoe")
                .quantity(10d)
                .build();

        productDtoCheesse = ProductDto.builder()
                .name("PREMIUM CHEESE")
                .units("pounds")
                .description("White premium chesse")
                .quantity(30d)
                .build();

        productDtoMilk = ProductDto.builder()
                .id(UUID.randomUUID().toString())
                .name("PREMIUM MILK")
                .units("liters")
                .description("Premium milk")
                .quantity(100d)
                .build();

    }

    @DisplayName("Validamos la existencia del producto despues de crearlo")
    @Test
    @Order(1)
    void validarExistenciaProducto(){
        ResponseEntity<String> response = client
                .postForEntity(getEntityUrl(),
                            productDtoTomato,
                            String.class) ;

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        ResponseEntity<ProductDto[]> getResponse = client.
                getForEntity( getEntityUrl () + "?id=" + response.getBody(), ProductDto[].class);

        assertEquals(1, getResponse.getBody().length);

        assertEquals(productDtoTomato.getDescription(), getResponse.getBody()[0].getDescription());
        assertEquals(productDtoTomato.getName(), getResponse.getBody()[0].getName());
        assertEquals(productDtoTomato.getQuantity(), getResponse.getBody()[0].getQuantity());
        assertEquals(productDtoTomato.getUnits(), getResponse.getBody()[0].getUnits());



    }

    @DisplayName("Validar todos los datos actualizados correctamente.")
    @Test
    @Order(2)
    void validarActualizacionCompletaRegistros(){

    }

    @DisplayName("Validar la actualizacion parcial ")
    @Test
    @Order(3)
    void validarActualizacionParcialRegistros(){


    }

    private String getEntityUrl(){
        return "http://localhost:" + port + "/api/v1/inventory" ;
    }





}
