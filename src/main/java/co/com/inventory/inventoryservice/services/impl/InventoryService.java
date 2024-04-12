package co.com.inventory.inventoryservice.services.impl;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.services.IInventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements IInventoryService {


    @Override
    public String create(ProductDto productDto) throws IllegalStateException {
        return null;
    }

    @Override
    public String update(ProductDto productDto) throws IllegalStateException {
        return null;
    }

    @Override
    public String get() {
        return null;
    }
}
