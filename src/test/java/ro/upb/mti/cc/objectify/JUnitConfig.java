package ro.upb.mti.cc.objectify;

import com.googlecode.objectify.spring.ObjectifyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alexandru Burghelea (alexandru.george.burghelea@gmail.com)
 * @since 12/8/13 - 7:01 PM
 */
@Configuration
public class JUnitConfig {

    @Bean
    public BaseDao baseDao() {
        return new BaseDao();
    }

    @Bean
    public ObjectifyFactoryBean objectifyFactory() throws Exception {
        ObjectifyFactoryBean objectifyFactoryBean = new ObjectifyFactoryBean();
        objectifyFactoryBean.setBasePackage("ro.upb.mti.cc.objectify");

        return objectifyFactoryBean;
    }
}
