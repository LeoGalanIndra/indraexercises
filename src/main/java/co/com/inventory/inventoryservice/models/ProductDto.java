package co.com.inventory.inventoryservice.models;

import lombok.Data;

@Data
public class ProductDto {

    private String id ;

    private String name ;

    private String description ;

    private String units ;

    private Double quantity ;


}
