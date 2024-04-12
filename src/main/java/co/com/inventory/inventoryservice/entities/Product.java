package co.com.inventory.inventoryservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private String id ;

    @Column
    private String name ;

    @Column
    private String description ;

    @Column
    private String units ;

    @Column
    private Double quantity ;

}
