package co.com.inventory.inventoryservice.usecases.impl;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryPutUseCase;
import org.springframework.stereotype.Service;

@Service
public class InventoryPutUseCase implements IInventoryPutUseCase {
    @Override
    public void update(ProductDto productDto) throws IllegalStateException, IllegalArgumentException {

        throw new IllegalArgumentException("Not implemented") ;

    }


}
