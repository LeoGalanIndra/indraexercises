package co.com.inventory.inventoryservice.repositories;

import co.com.inventory.inventoryservice.entities.Factura;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@DataJpaTest
public class IFacturaRepositoryTest {


    @Autowired
    IFacturaRepository repository ;

    Factura factura ;

    Factura factura2 ;

    Factura factura3 ;

    Factura factura4 ;

    Factura factura5 ;

    List<Factura> bills ;

    @BeforeEach
    public void initVariables(){

        factura = Factura
                .builder()
                .id("12345")
                .requiereFactura(true)
                .cambio(BigDecimal.ZERO)
                .emailCliente("")
                .fechaRecibo(LocalDate.now())
                .idCliente(-1L)
                .items(new ArrayList<>())
                .nombreCliente("")
                .total(new BigDecimal("1000"))
                .nroRecibo("0000101")
                .subTotal(new BigDecimal("810"))
                .totalImpuestos(new BigDecimal("190"))
                .totalPagado(new BigDecimal("1000"))
                .total(new BigDecimal("1000"))

                .build();

        factura2 = Factura
                .builder()
                .id("98765")
                .requiereFactura(false)
                .cambio(BigDecimal.ZERO)
                .emailCliente("")
                .fechaRecibo(LocalDate.now())
                .idCliente(-1L)
                .items(new ArrayList<>())
                .nombreCliente("")
                .total(new BigDecimal("1000"))
                .nroRecibo("0000102")
                .subTotal(new BigDecimal("810"))
                .totalImpuestos(new BigDecimal("190"))
                .totalPagado(new BigDecimal("1000"))
                .total(new BigDecimal("1000"))

                .build();

        factura3 = Factura
                .builder()
                .id("98762")
                .requiereFactura(true)
                .cambio(BigDecimal.ZERO)
                .emailCliente("")
                .fechaRecibo(LocalDate.now())
                .idCliente(-1L)
                .items(new ArrayList<>())
                .nombreCliente("")
                .total(new BigDecimal("1000"))
                .nroRecibo("0000103")
                .subTotal(new BigDecimal("810"))
                .totalImpuestos(new BigDecimal("190"))
                .totalPagado(new BigDecimal("1000"))
                .total(new BigDecimal("1000"))

                .build();

        factura4 = Factura
                .builder()
                .id("98763")
                .requiereFactura(false)
                .cambio(BigDecimal.ZERO)
                .emailCliente("")
                .fechaRecibo(LocalDate.now())
                .idCliente(2L)
                .items(new ArrayList<>())
                .nombreCliente("")
                .total(new BigDecimal("1000"))
                .nroRecibo("0000104")
                .subTotal(new BigDecimal("810"))
                .totalImpuestos(new BigDecimal("190"))
                .totalPagado(new BigDecimal("1000"))
                .total(new BigDecimal("1000"))

                .build();

        factura5 = Factura
                .builder()
                .id("98764")
                .requiereFactura(true)
                .cambio(BigDecimal.ZERO)
                .emailCliente("")
                .fechaRecibo(LocalDate.now())
                .idCliente(2L)
                .items(new ArrayList<>())
                .nombreCliente("")
                .total(new BigDecimal("1000"))
                .nroRecibo("0000105")
                .subTotal(new BigDecimal("810"))
                .totalImpuestos(new BigDecimal("190"))
                .totalPagado(new BigDecimal("1000"))
                .total(new BigDecimal("1000"))

                .build();

        bills = List.of(factura, factura2, factura3, factura4, factura5);

    }


    // @Disabled
    @DisplayName("metodo para chequear el guardado en la base de datos")
    @Test

    public void validarFacturaGuardada(){

        // given - dado
        // dado una factura de 0 elementos


        // when - cuando
        // cuando yo la guarde, debe quedar registrada
        Factura facturaEjecutada = repository.save(factura);

        // then - luego
        // al consultarla debe estar

        Assertions.assertEquals(factura.getId(), facturaEjecutada.getId());

    }


    // @Disabled
    @DisplayName("metodo para validar la integridad de la informacion al momento de guardar la factura en la base de datos ")
    @Test

    public void validarIntegridadDeLaFactura(){

        // given - dado
        // dado una factura de 0 elementos


        // when - cuando
        // cuando yo la guarde, debe quedar registrada
        Factura facturaEjecutada = repository.save(factura);

        // then - luego
        // al consultarla debe estar

        Assertions.assertEquals(factura.getId(), facturaEjecutada.getId());

    }

    // @Disabled
    @DisplayName("validar todas las consultar registradas")
    @Test
    public void consultarTodasLasFacturas(){

        // given
        repository.saveAll(bills);

        // when
        List<Factura> anotherBills = repository.findAll();

        // then
        Assertions.assertEquals(bills.size(), anotherBills.size());

    }

    // @Disabled
    @DisplayName("validar la consulta de una factura ")
    @Test
    public void consultarUnaFacturaDeLaBaseDatos(){

        // given
        repository.saveAll(bills);

        // when
        Optional<Factura> bill = repository.findById("98762");

        // then
        Assertions.assertEquals(factura3.getNroRecibo(), bill.get().getNroRecibo());



    }

    // @Disabled
    @DisplayName("validar la consulta de una factura por numero de recibo ")
    @Test
    public void consultarUnaFacturaDeLaBaseDatosPorRecibo(){

        // given
        repository.saveAll(bills);

        // when
        List<Factura> bill = repository.findByNroRecibo("0000105");

        log.info("consulta por recibo " + bill);

        // then
        Assertions.assertEquals(factura5.getNroRecibo(), bill.get(0).getNroRecibo());



    }

    // @Disabled
    @DisplayName("validar la consulta de una factura por id cliente y requiere envio de factura")
    @Test
    public void consultarUnaFacturaDeLaBaseDatosPorClienteYRequiereFactura(){

        // given
        repository.saveAll(bills);

        // when
        List<Factura> bill = repository.findByIdClienteAndRequiereFactura(-1L, true);

        log.info("consulta por id cliente " + bill);

        // then
        Assertions.assertEquals(2, bill.size());



    }
}
