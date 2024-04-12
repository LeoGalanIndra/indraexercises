package co.com.inventory.inventoryservice.usecases.impl;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryGetUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryGetUseCase implements IInventoryGetUseCase {


    @Override
    public List<ProductDto> read(Map<String, String> pathVariables) {
        return null;
    }
}
