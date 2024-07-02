package co.com.inventory.inventoryservice;

import co.com.inventory.inventoryservice.aop.TargetObject;
import co.com.inventory.inventoryservice.lifecycle.LifeCycleBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {


		// SpringApplication.run(InventoryServiceApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(InventoryServiceApplication.class, args);

		TargetObject targetObject = context.getBean(TargetObject.class);
		targetObject.sayHello("leo Galan");

		// Arrays.binarySearch();
		StringBuffer stringBuffer =  new StringBuffer( ) ;
		String str  = stringBuffer.reverse().toString() ;

	}

}
