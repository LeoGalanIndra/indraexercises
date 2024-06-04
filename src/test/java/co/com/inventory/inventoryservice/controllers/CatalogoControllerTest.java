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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CatalogoControllerTest {

    @InjectMocks
    CatalogoController controller ;

    @Mock
    IInventoryGetUseCase getUseCaseService ;

    @BeforeAll
    static void beforeAll(){
        MockitoAnnotations.openMocks(CatalogoControllerTest.class);

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("valida el status code del metodo get cuando la peticion es exitosa")
    @Test
    void validarStatusCodeOK() {
        Map<String, String> params = new HashMap<>();

        params.put("id", "Hello World");

        ResponseEntity<List<ProductDto>> response = controller.get(params);
        HttpStatusCode statusCode = response.getStatusCode();

        assertEquals(HttpStatus.OK, statusCode);
    }

    @DisplayName("valida el status code del metodo get cuando los parametros estan vacios")
    @Test
    void validarStatusNotFound() {
        Map<String, String> params = new HashMap<>();

        ResponseEntity<List<ProductDto>> response = controller.get(params);
        HttpStatusCode statusCode = response.getStatusCode();

        assertEquals(HttpStatus.NOT_FOUND, statusCode);
    }

    @DisplayName("valida el contenido del metodo get cuando los parametros son por id")
    @Test
    void validarContenidoCuandoParamIsId() {
        Map<String, String> params = new HashMap<>();

        params.put("id", "Hello World");

        ResponseEntity<List<ProductDto>> response = controller.get(params);
        HttpStatusCode statusCode = response.getStatusCode();

        List<ProductDto> body = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK, statusCode),
                () -> assertNotNull(body)  ,
                () -> assertEquals(0, body.size())
        );


    }

    @DisplayName("valida el contenido del metodo get cuando los parametros son por id usando mock")
    @Test
    void validarContenidoCuandoParamIsIdUsandoMock() {

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

        params.put("id", "Hello World");

        ResponseEntity<List<ProductDto>> response = controller.get(params);
        HttpStatusCode statusCode = response.getStatusCode();

        List<ProductDto> body = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK, statusCode),
                () -> assertNotNull(body)  ,
                () -> assertEquals(1, body.size()),
                () -> assertEquals(id, body.get(0).getId())
        );


    }
}