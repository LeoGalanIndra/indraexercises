package co.com.inventory.inventoryservice.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {

    private String id ;

    private String name ;

    private String description ;

    private String units ;

    private Double quantity ;


}
