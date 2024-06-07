package co.com.inventory.inventoryservice.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TargetObject {

    private static final Logger log = LoggerFactory.getLogger(TargetObject.class);

    public void sayHello(String name){
        log.info("Hello World {}! ", name);

        
    }
}
