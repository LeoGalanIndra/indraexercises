package co.com.inventory.inventoryservice.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
public class InventoryController {


    @GetMapping
    public ResponseEntity<String> get(){
        return  new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public  ResponseEntity<String> post(@RequestBody String body){
        return  new ResponseEntity<>("", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping
    public  ResponseEntity<String> put(@RequestBody String id){
        return  new ResponseEntity<>("", HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<String> patch(@PathVariable String id, @RequestBody String body){
        return  new ResponseEntity<>("", HttpStatus.NOT_ACCEPTABLE);
    }



}
