package co.com.inventory.inventoryservice.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEnviromentService implements EnviromentService{

    @Override
    public String getEnviroment() {
        return "prod";
    }

}
