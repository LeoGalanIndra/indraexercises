package co.com.inventory.inventoryservice;

import co.com.inventory.inventoryservice.controllers.InventoryController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class InventoryControllerTest {

	@Autowired
	InventoryController controller ;

	@Test
	void contextLoads() {
	}

	@Test
	void getTest(){

		Assertions.assertEquals(controller.get().getStatusCode(), HttpStatus.OK);

	}

	@Test
	void postTest(){

		Assertions.assertEquals(controller.post("").getStatusCode(), HttpStatus.ACCEPTED);

	}

	@Test
	void putTest(){

		Assertions.assertEquals(controller.put("").getStatusCode(), HttpStatus.ACCEPTED);

	}

	@Test
	void patchTest(){

		Assertions.assertEquals(controller.patch("", "") .getStatusCode(), HttpStatus.ACCEPTED);

	}




}
