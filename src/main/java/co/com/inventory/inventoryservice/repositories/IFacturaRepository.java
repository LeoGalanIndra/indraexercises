package co.com.inventory.inventoryservice.repositories;

import co.com.inventory.inventoryservice.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, String> {

}
