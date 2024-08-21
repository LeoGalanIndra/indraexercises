package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

	@InjectMocks
	InventoryController controller ;



	@Disabled
	@Test
	void contextLoads() {
	}

	@Disabled
	@DisplayName("validar status code exitoso para el metodo http GET ")
	@Test
	void getTest(){
		Assertions.assertEquals(controller.get(new HashMap<>()).getStatusCode(), HttpStatus.OK);



		

	}

	@Disabled
	@DisplayName("validar status code exitoso para el metodo http POST ")
	@Test
	void postTest(){
		Assertions.assertEquals(controller.post(ProductDto.builder().build()).getStatusCode(), HttpStatus.ACCEPTED);

		

	}

	@Disabled
	@DisplayName("validar status code exitoso para el metodo http PUT ")
	@Test
	void putTest(){
		Assertions.assertEquals(controller.put(ProductDto.builder().build()).getStatusCode(), HttpStatus.ACCEPTED);

		

	}

	@Disabled
	@DisplayName("validar status code exitoso para el metodo http PATCH ")
	@Test
	void patchTest(){

		

	}




}
