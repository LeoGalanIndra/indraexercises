package co.com.inventory.inventoryservice.repositories;

import co.com.inventory.inventoryservice.entities.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
class ICatalogRepositoryTestContainerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    ICatalogRepository repository;


    Product chessProduct ;

    Product milkProduct ;

    Product riceProduct ;

    String cheeseId = UUID.randomUUID().toString();

    String milkId = UUID.randomUUID().toString();

    String riceId = UUID.randomUUID().toString();

    @BeforeEach
    void setUp(){

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

    @Test
    void connectionEstablished() {
        Assertions.assertThat(postgres.isCreated()).isTrue();
        Assertions.assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void validarDatosCreados(){

        repository.save(milkProduct);


        Optional<Product> product = repository.findById(milkId);

        if (product.isEmpty()){
            fail("No se encontraron registros!");
        }

        assertEquals(milkProduct.getQuantity(), product.get().getQuantity());

    }

}