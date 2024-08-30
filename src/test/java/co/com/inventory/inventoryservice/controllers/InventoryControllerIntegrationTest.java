package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryControllerIntegrationTest {


     private  WebTestClient client ;

     ObjectMapper mapper = new ObjectMapper();

    @LocalServerPort
    int port;



    ProductDto productDtoTomato ;

    ProductDto productDtoCheesse ;

    ProductDto productDtoMilk ;

    @BeforeEach
    void setUp() {


        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();


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

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Validamos la existencia del producto despues de crearlo")
    @Test
    void validarExistenciaProducto(){

         // given
         String id = client
                .post()
                .uri("/api/v1/inventory")
                .bodyValue(productDtoTomato)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                 .returnResult().getResponseBody()
                  ;

         log.info("id: " + id);

         client
                 .get()
                 .uri("/api/v1/inventory?id=" + id)
                 .exchange()
                 .expectStatus()
                 .is2xxSuccessful()
                 .expectBody(List.class)
                 .consumeWith(result -> {

                     assertNotNull(result);
                     assertNotNull(result.getResponseBody());

                     List results = result.getResponseBody();

                     assertEquals(results.size(), 1);

                     ProductDto tempResult = mapper.convertValue(results.get(0), ProductDto.class);


                     assertEquals(tempResult.getName(), productDtoTomato.getName());
                     assertEquals(tempResult.getUnits(), productDtoTomato.getUnits());
                     assertEquals(tempResult.getQuantity(), productDtoTomato.getQuantity());
                     assertEquals(tempResult.getDescription(), productDtoTomato.getDescription());


                 });



    }

    @DisplayName("Validar todos los datos actualizados correctamente.")
    @Test
    void validarActualizacionCompletaRegistros(){

        // given
        String id = client
                .post()
                .uri("/api/v1/inventory")
                .bodyValue(productDtoCheesse)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody()
                ;

        log.info("id: " + id);

        productDtoCheesse.setId(id);
        productDtoCheesse.setQuantity(10d);


        client
                .put()
                .uri("/api/v1/inventory")
                .bodyValue(productDtoCheesse)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                ;

        client
                .get()
                .uri("/api/v1/inventory?id=" + id)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(List.class)
                .consumeWith(result -> {

                    assertNotNull(result);
                    assertNotNull(result.getResponseBody());

                    List results = result.getResponseBody();

                    assertEquals(results.size(), 1);

                    ProductDto tempResult = mapper.convertValue(results.get(0), ProductDto.class);


                    assertEquals(tempResult.getName(), productDtoCheesse.getName());
                    assertEquals(tempResult.getUnits(), productDtoCheesse.getUnits());
                    assertEquals(tempResult.getQuantity(), productDtoCheesse.getQuantity());
                    assertEquals(tempResult.getDescription(), productDtoCheesse.getDescription());


                });


    }

    @DisplayName("Validar la actualizacion parcial ")
    @Test
    void validarActualizacionParcialRegistros(){

        // given
        String id = client
                .post()
                .uri("/api/v1/inventory")
                .bodyValue(productDtoMilk)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody()
                ;

        log.info("id: " + id);

        MultiValueMap<String, String> myBody = new LinkedMultiValueMap<>();
        myBody.add("quantity", "50");

        productDtoMilk.setQuantity(60d);

        client
                .patch()
                .uri("/api/v1/inventory/" + id)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(Collections.singletonMap("quantity", 60)))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
        ;

        client
                .get()
                .uri("/api/v1/inventory?id=" + id)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(List.class)
                .consumeWith(result -> {

                    assertNotNull(result);
                    assertNotNull(result.getResponseBody());

                    List results = result.getResponseBody();

                    assertEquals(results.size(), 1);

                    ProductDto tempResult = mapper.convertValue(results.get(0), ProductDto.class);


                    assertEquals(tempResult.getName(), productDtoMilk.getName());
                    assertEquals(tempResult.getUnits(), productDtoMilk.getUnits());
                    assertEquals(tempResult.getQuantity(), productDtoMilk.getQuantity());
                    assertEquals(tempResult.getDescription(), productDtoMilk.getDescription());


                });


    }
}