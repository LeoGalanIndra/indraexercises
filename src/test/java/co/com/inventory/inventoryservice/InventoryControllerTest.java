package co.com.inventory.inventoryservice;

import co.com.inventory.inventoryservice.controllers.InventoryController;
import co.com.inventory.inventoryservice.models.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@SpringBootTest
class InventoryControllerTest {

	@Autowired
	InventoryController controller ;



	@Test
	void contextLoads() {
	}

	@Disabled
	@Test
	void getTest(){



		Assertions.assertEquals(controller.get(new HashMap<>()).getStatusCode(), HttpStatus.OK);

	}

	@Disabled
	@Test
	void postTest(){

		Assertions.assertEquals(controller.post(ProductDto.builder().build()).getStatusCode(), HttpStatus.ACCEPTED);

	}

	@Disabled
	@Test
	void putTest(){

		Assertions.assertEquals(controller.put(ProductDto.builder().build()).getStatusCode(), HttpStatus.ACCEPTED);

	}

	@Disabled
	@Test
	void patchTest(){

		Assertions.assertEquals(controller.patch("", ProductDto.builder().build()) .getStatusCode(), HttpStatus.ACCEPTED);

	}




}
