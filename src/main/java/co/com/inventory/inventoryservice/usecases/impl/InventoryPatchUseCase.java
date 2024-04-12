package co.com.inventory.inventoryservice.usecases.impl;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryPatchUseCase;
import org.springframework.stereotype.Service;

@Service
public class InventoryPatchUseCase implements IInventoryPatchUseCase {

    @Override
    public void update(String id, ProductDto productDto) throws IllegalStateException, IllegalArgumentException {

    }
}
