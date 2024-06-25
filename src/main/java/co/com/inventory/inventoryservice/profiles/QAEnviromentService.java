package co.com.inventory.inventoryservice.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("qa")
public class QAEnviromentService implements EnviromentService{

    @Override
    public String getEnviroment() {
        return "qa";
    }

}
