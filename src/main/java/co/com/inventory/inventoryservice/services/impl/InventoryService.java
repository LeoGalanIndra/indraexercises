package co.com.inventory.inventoryservice.services.impl;

import co.com.inventory.inventoryservice.entities.Product;
import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.repositories.ICatalogRepository;
import co.com.inventory.inventoryservice.services.IInventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.TypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.List;


@Service
public class InventoryService implements IInventoryService {

    @Autowired
    ICatalogRepository repository ;



    @Override
    public String create(ProductDto productDto) throws IllegalStateException {




        // Product product = mapper.
        return null;
    }

    @Override
    public void update(ProductDto productDto) throws IllegalStateException {

    }

    @Override
    public List<ProductDto> getById(String id) {
        return null;
    }

    @Override
    public List<ProductDto> getByName(String name) {
        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        return null;
    }


}
