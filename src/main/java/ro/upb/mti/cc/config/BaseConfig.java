package ro.upb.mti.cc.config;

import com.googlecode.objectify.spring.ObjectifyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alexandru Burghelea (alexandru.george.burghelea@gmail.com)
 * @since 12/2/13 - 8:13 PM
 */
@Configuration
@ComponentScan("ro.upb.mti.cc.controller")
public class BaseConfig {

    @Bean
    public ObjectifyFactoryBean objectifyFactory() throws Exception {
        ObjectifyFactoryBean objectifyFactoryBean = new ObjectifyFactoryBean();
        objectifyFactoryBean.setBasePackage("ro.upb.mti.cc.domain");

        return objectifyFactoryBean;
    }
}
