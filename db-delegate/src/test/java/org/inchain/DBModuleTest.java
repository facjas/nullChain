package org.inchain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.inchain.util.log.Log;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

/**
 * Unit test for simple DBModule.
 */
public class DBModuleTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    public static ClassPathXmlApplicationContext applicationContext;

    /**
     * start spring
     */
    @Before
    public static void init() {
        //加载spring环境
        if (null != applicationContext) {
            return;
        }
        Log.info("get application context");
        try {
            applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                Log.error("There is not applicationContext.xml in classpath!", e);
            } else {
                Log.error("", e);
            }
            return;
        }
        Log.info("System is started!");
    }
}
