package co.com.inventory.inventoryservice.repositories;

import co.com.inventory.inventoryservice.entities.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat ;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ICatalogRepository repository ;

    Product chessProduct ;

    Product milkProduct ;

    Product riceProduct ;

    String cheeseId = UUID.randomUUID().toString();

    String milkId = UUID.randomUUID().toString();

    String riceId = UUID.randomUUID().toString();

    @BeforeEach
    void init() {


        chessProduct = Product.builder()
                .id(cheeseId)
                .units("gr")
                .name("cheese")
                .quantity(100d)
                .description("premium cheese.")
                .build() ;



        milkProduct = Product.builder()
                .id(milkId)
                .units("lt")
                .name("milk")
                .quantity(50d)
                .description("milk bottles")
                .build() ;



        riceProduct = Product.builder()
                .id(riceId)
                .units("pkg")
                .name("rice by pound")
                .quantity(200d)
                .description("")
                .build() ;


    }

    @DisplayName("ValidaCreacionProducto")
    @Test
    void testIsProductoCreated(){
        // given - dado a condicion
        Product product = chessProduct ;

        // when - cuando
        Product realProduct = repository.save(product);

        // then - entonces
        assertThat(realProduct).isNotNull();

    }

    @DisplayName("ValidaSiElIdEsCorrecto")
    @Test
    void testIsIdValueCorrectProductoCreated(){
        // given - dado a condicion
        String id = riceProduct.getId();

        Product product = riceProduct ;

        // when - cuando
        Product realProduct = repository.save(product);

        // then - entonces
        Assertions.assertEquals(realProduct.getId(), id);

    }

    @Disabled
    @DisplayName("ValidaSiElNameNoEsNull")
    @Test
    void testIsNameNotNullProductoCreated(){
        // given - dado a condicion
        String id = UUID.randomUUID().toString();

        Product product = Product.builder()
                .id(id)
                .units("gr")
                .quantity(100d)
                .description("")
                .build() ;

        // when - cuando
        Exception exc = new Exception();
        try{
            repository.save(product);
        }catch (DataIntegrityViolationException e){
            exc = e ;
        }

        // then - entonces
        Assertions.assertEquals(DataIntegrityViolationException.class, exc.getClass());
    }

    @DisplayName("ValidaEltotalderegistros")
    @Test
    void testIsAllItemsComplet(){
        // given - dado a condicion
        repository.save(milkProduct);
        repository.save(riceProduct);
        repository.save(chessProduct);

        // when - cuando
        List<Product> items = repository.findAll();


        // then - entonces
        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(3);


    }

    @DisplayName("ValidaElregistroActualizado")
    @Test
    void testIsUpdateItemsComplet(){
        // given - dado a condicion
        repository.save(milkProduct);

        Optional<Product> product = repository.findById(milkId);

        assertThat(product.isPresent()).isTrue();

        Product product1 = product.get();

        product1.setName("Premium milk");
        product1.setQuantity(200d);

        repository.save(product1);

        // when - cuando
        product = repository.findById(milkId);


        // then - entonces
        assertThat(product.isPresent()).isTrue();
        assertThat(product.get().getName()).isEqualTo("Premium milk");
        assertThat(product.get().getQuantity()).isEqualTo(200d);

        System.out.println("product: " + product.get());

    }








}
