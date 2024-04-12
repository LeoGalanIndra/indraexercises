package co.com.inventory.inventoryservice.services;


import co.com.inventory.inventoryservice.models.ProductDto;

public interface IInventoryService {

    public String create(ProductDto productDto) throws IllegalStateException;

    public String update(ProductDto productDto) throws IllegalStateException ;


    public String get() ;




}
