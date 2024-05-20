package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryGetUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("catalogo")
public class CatalogoController {

    @Autowired
    IInventoryGetUseCase getUseCaseService ;

    @GetMapping
    public ResponseEntity<List<ProductDto>> get(@RequestParam Map<String, String> params){

        if(params == null || params.isEmpty())
            return  ResponseEntity.notFound().build();

        else
            return ResponseEntity.ok(getUseCaseService.read(params));


    }
}
