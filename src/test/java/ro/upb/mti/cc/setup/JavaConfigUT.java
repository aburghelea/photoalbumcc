package ro.upb.mti.cc.setup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ro.upb.mti.cc.config.BaseConfig;

/**
 * @author Alexandru Burghelea (alexandru.george.burghelea@gmail.com)
 * @since 12/2/13 - 10:27 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfig.class)
public class JavaConfigUT {

    @Autowired(required = false)
    InternalResourceViewResolver internalResourceViewResolver;

    @Test
    public void testInjectInternalResourceViewResolver() throws Exception {
        Assert.notNull(internalResourceViewResolver);
    }
}
