package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class InventoryControllerWebClientTest {

    @Autowired
    private WebTestClient client ;

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

                .name("PREMIUM MILK")
                .units("liters")
                .description("Premium milk")
                .quantity(100d)
                .build();
    }

    @Disabled
    @DisplayName("Validar que existan los registros despues de creados.")
    @Test
    void get() {

        // dado (given)
        // se crea un objeto en el sistema

        String id= client
                .post()
                .uri("/api/v1/inventory")
                .bodyValue(productDtoTomato)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .returnResult().getResponseBody() ;

        //         when
        // cuando consulto el registro, debo leer todos sus atributos


        client
                .get()
                .uri("/api/v1/inventory?id="+id)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(List.class)

                // then
                // comparo la informacion creada con la informacion consultada.

                .consumeWith((result) -> {

                    assertNotNull(result);
                    assertNotNull(result.getResponseBody());

                    assertEquals(1, result.getResponseBody().size());

                    ProductDto tempResult = mapper.convertValue(result.getResponseBody().get(0), ProductDto.class);

                    assertEquals(productDtoTomato.getUnits(), tempResult.getUnits());
                    assertEquals(productDtoTomato.getName(), tempResult.getName());
                    assertEquals(productDtoTomato.getDescription(), tempResult.getDescription());
                    assertEquals(productDtoTomato.getQuantity(), tempResult.getQuantity());


                }) ;





    }

}