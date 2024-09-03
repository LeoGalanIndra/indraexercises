package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryControllerTestRestTemplateTest {

    @Autowired
    private TestRestTemplate client ;

    @LocalServerPort
    int port;

    ProductDto productDtoCheesse ;

    @BeforeEach
    void setUp() {

        productDtoCheesse = ProductDto.builder()

                .name("PREMIUM CHEESE")
                .units("pounds")
                .description("White premium chesse")
                .quantity(30d)
                .build();

    }

    @Disabled
    @DisplayName("Validar que existan los registros modificados se encuentren consistentes. ")
    @Test
    void createProduct(){

        // dado (given)

        //  se crea un objeto en el sistema.

        ResponseEntity<String> postResult =  client
                .postForEntity(getEntityUrl(), productDtoCheesse, String.class);

        assertEquals(HttpStatus.CREATED, postResult.getStatusCode());

        assertNotNull(postResult.getBody());

        String id = postResult.getBody();

        //         modifico el objeto

        productDtoCheesse.setId(id);
        productDtoCheesse.setQuantity(93d);
        productDtoCheesse.setDescription("valor del queso modificado");

        client.put(getEntityUrl(), productDtoCheesse);

        // when
        // cuando consulto el registro, debo leer todos sus atributos

        ResponseEntity<ProductDto[]> getResult = client.getForEntity(getEntityUrl() + "?id=" + id, ProductDto[].class);

        // then
        // comparo la informacion editada con la informacion consultada.

        assertEquals(HttpStatus.OK , getResult.getStatusCode());
        assertEquals(1, getResult.getBody().length);
         ProductDto temp = getResult.getBody()[0];

         assertEquals(productDtoCheesse.getQuantity(), temp.getQuantity());
        assertEquals(productDtoCheesse.getDescription(), temp.getDescription());


    }

    private String getEntityUrl(){
        return "http://localhost:" + port + "/api/v1/inventory" ;
    }
}