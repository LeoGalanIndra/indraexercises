package co.com.inventory.inventoryservice.services.impl;

import co.com.inventory.inventoryservice.entities.Product;
import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.repositories.ICatalogRepository;
import co.com.inventory.inventoryservice.services.IInventoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat ;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given ;
import static org.mockito.Mockito.never;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    ICatalogRepository repository ;

    @InjectMocks
    InventoryService service ;

    ProductDto productDtoTomato ;

    ProductDto productDtoCheesse ;


    ProductDto productDtoMilk ;

    List<ProductDto> products ;

    List<Product> entityProducts ;

    ModelMapper modelMapper = new ModelMapper();

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(InventoryServiceTest.class);
    }

    @BeforeEach
    void setUp(){
        productDtoTomato = ProductDto.builder()
                .id(UUID.randomUUID().toString())
                .name("RED TOMATOE")
                .units("pounds")
                .description("Red tomatoe")
                .quantity(10d)
                .build();

        productDtoCheesse = ProductDto.builder()
                .id(UUID.randomUUID().toString())
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

        products = List.of(productDtoCheesse, productDtoTomato, productDtoMilk);

        entityProducts = products
                .stream()
                .map(product -> modelMapper.map(product, Product.class))
                .collect(Collectors.toList());

    }

    @DisplayName("creando Inventory")
    @Test
    public  void createInventoryTest(){

        // given
        Product product = modelMapper.map(productDtoTomato, Product.class);

        given(this.repository.save(Mockito.any(Product.class)))
                .willReturn(product);

        // when
        String id = service.create(productDtoTomato);

        // then
        assertThat(id).isNotNull();
    }

    @DisplayName("controlando la excepcion al crear")
    @Test
    public  void handleErrorOnCreateInventoryTest(){

        // given
        given(this.repository.save(Mockito.any(Product.class)))
                .willThrow(IllegalStateException.class);

        // when
        Assertions.assertThrows(IllegalStateException.class ,
                () -> {
                service.create(productDtoTomato);
        });

    }

    @DisplayName("Inventory List")
    @Test
    public  void inventoryListTest(){

        // given

        given(this.repository.findAll())
                .willReturn(entityProducts);

        // when
        List<ProductDto> productDtoList = service.getAll();

        // then
        assertThat(productDtoList).isNotNull();
        assertThat(productDtoList.size()).isEqualTo(3);

    }

}
