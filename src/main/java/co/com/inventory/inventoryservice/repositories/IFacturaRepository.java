package co.com.inventory.inventoryservice.repositories;

import co.com.inventory.inventoryservice.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, String> {

    List<Factura> findByNroRecibo(String nroRecibo);


    List<Factura> findByIdClienteAndRequiereFactura(Long idCliente, Boolean requiereFactura);


    // @Query(value = "SELECT * FROM FACTURA WHERE ... :A ", nativeQuery = true)
    // List<Factura> findByNroRecibo1(String nroRecibo);

}
