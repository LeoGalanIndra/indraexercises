package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryGetUseCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LogisticControllerTest {

    @InjectMocks
    LogisticController controller ;

    @Mock
    IInventoryGetUseCase getUseCaseService ;


    @BeforeAll
    static void beforeAll(){
        MockitoAnnotations.openMocks(LogisticControllerTest.class);

    }

    @BeforeEach
    void setUp() {
        System.out.println("alistamiento de los datos");

    }

    @AfterEach
    void tearDown() {
        System.out.println("fin de la prueba");

    }

    @DisplayName("validar la respuesta del met get con estatus code OK")
    @Test
    void checkGetResponseWithSucessfullCode() {

        Map<String, String> params = new HashMap<>();

        params.put("id", "cualquiera");

        ResponseEntity<List<ProductDto>> responseEntity =  controller.get(params);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @DisplayName("validar la respuesta del met get con estatus code Fallo cuando los params estan vacios ")
    @Test
    void checkGetResponseWithFailedCode() {

        Map<String, String> params = new HashMap<>();



        ResponseEntity<List<ProductDto>> responseEntity =  controller.get(params);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @DisplayName("validar la respuesta del met get con estatus code OK y contenido con lista de productos")
    @Test
    void checkGetResponseWithSucessfullCodeAndNonEmptyBody() {

        String id = UUID.randomUUID().toString() ;
        String id2 = UUID.randomUUID().toString() ;

        ProductDto productDto = ProductDto.builder()
                .id(id)
                .build();

        List<ProductDto> productDtoList = List.of(productDto);

        Mockito
                .when(getUseCaseService.read(Mockito.any()))
                .thenReturn(productDtoList);

        Map<String, String> params = new HashMap<>();

        params.put("id", "cualquiera");

        ResponseEntity<List<ProductDto>> responseEntity =  controller.get(params);
        List<ProductDto> results = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, results.size());
        assertEquals(id, results.get(0).getId());

    }

}