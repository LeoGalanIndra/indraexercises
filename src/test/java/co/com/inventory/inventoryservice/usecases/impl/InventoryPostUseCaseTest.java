package co.com.inventory.inventoryservice.usecases.impl;


import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.services.IInventoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InventoryPostUseCaseTest {

    @InjectMocks
    InventoryPostUseCase postUseCaseService ;

    @Mock
    IInventoryService service ;

    @BeforeAll
    static void beforeAll(){
        MockitoAnnotations.openMocks(InventoryPostUseCaseTest.class);

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Valida la creacion del producto CUANDO el id del producto existe")
    @Test
    void create(){

        // given

        String id = UUID.randomUUID().toString() ;


        ProductDto productDto = ProductDto.builder()
                .id(id)
                .name("un producto X")
                .build();

        List<ProductDto> productDtoList =  List.of(productDto) ; // new ArrayList<>()

        given(service.getById(Mockito.any())).willReturn(productDtoList);

        // when

        Assertions.assertThrows(IllegalStateException.class, () -> postUseCaseService.create(productDto));

        // then


    }

    @DisplayName("Valida la creacion del producto CUANDO el nombre del producto existe")
    @Test
    void createProductWithSameName(){

        // given

        String id = UUID.randomUUID().toString() ;


        ProductDto productDto = ProductDto.builder()
                .id(id)
                .name("un producto X")
                .build();

        List<ProductDto> productDtoList = new ArrayList<>()  ; //  List.of(productDto)
        List<ProductDto> productDtoListByName = List.of(productDto) ;

        given(service.getById(Mockito.any())).willReturn(productDtoList);
        given(service.getByName(Mockito.any())).willReturn(productDtoListByName);


        // when

        Assertions.assertThrows(IllegalStateException.class, () -> postUseCaseService.create(productDto));

        // then

        verify(service).getById(Mockito.any());


    }


    @DisplayName("Valida la creacion del producto CUANDO los atributos del producto estan OK")
    @Test
    void createProductWithProductOK(){

        // given

        String id = UUID.randomUUID().toString() ;
        String id2 = UUID.randomUUID().toString() ;


        ProductDto productDto = ProductDto.builder()
                .id(id)
                .name("un producto X")
                .build();

        List<ProductDto> productDtoList = new ArrayList<>()  ; //  List.of(productDto)
        List<ProductDto> productDtoListByName = List.of(productDto) ;

        given(service.getById(Mockito.any())).willReturn(productDtoList);
        given(service.getByName(Mockito.any())).willReturn(productDtoList);
        given(service.create(Mockito.any())).willReturn(id);


        // when

        String idReal = postUseCaseService.create(productDto) ;
        Assertions.assertEquals(id, idReal);

        // then

        verify(service).getById(Mockito.any());
        verify(service).getByName(Mockito.any());


    }
}