package co.com.inventory.inventoryservice.repositories;

import co.com.inventory.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatalogRepository extends JpaRepository<Product, String> {




}
