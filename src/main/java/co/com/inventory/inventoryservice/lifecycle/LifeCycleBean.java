package co.com.inventory.inventoryservice.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleBean implements BeanNameAware {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

    @Override
    public void setBeanName(String name) {
        log.info("Bean name aware {}", name);
    }

    @PostConstruct
    public void init(){
        log.info("Bean post construct! ");
    }

    @PreDestroy
    public void destroy(){
        log.info("Bean pre destroy! ");
    }
}
