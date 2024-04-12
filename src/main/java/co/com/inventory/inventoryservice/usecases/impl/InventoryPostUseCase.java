package co.com.inventory.inventoryservice.usecases.impl;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryPostUseCase;

public class InventoryPostUseCase implements IInventoryPostUseCase {


    @Override
    public void create(ProductDto productDto) throws IllegalStateException, IllegalArgumentException {

        throw new IllegalArgumentException("Not implemented") ;

    }
}
