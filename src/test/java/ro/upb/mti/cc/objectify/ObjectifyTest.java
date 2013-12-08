package ro.upb.mti.cc.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Alexandru Burghelea (alexandru.george.burghelea@gmail.com)
 * @since 12/2/13 - 10:27 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JUnitConfig.class})
public class ObjectifyTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    @Autowired(required = false)
    InternalResourceViewResolver internalResourceViewResolver;

    @Autowired
    BaseDao baseDao;

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testObjectifySave() throws Exception {
        final int COLOR = 2;
        final String TEXT = "tage";

        Base base = new Base();
        base.setColor(COLOR);
        base.setText(TEXT);
        baseDao.put(base);

        Assert.assertNotNull(base.getId());

        base = baseDao.get(base.getId());

        Assert.assertEquals(TEXT, base.getText());
        Assert.assertEquals(COLOR, base.getColor());

    }
}
