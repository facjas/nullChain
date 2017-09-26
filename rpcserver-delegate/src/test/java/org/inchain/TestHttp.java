package org.inchain;

import junit.framework.TestCase;
import org.inchain.rpcserver.RpcServerService;
import org.inchain.util.log.Log;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

/**
 * Created by Niels on 2017/9/25.
 * inchain.org
 */
public class TestHttp extends TestCase{

    public static ClassPathXmlApplicationContext applicationContext;

    /**
     * start spring
     */
    public static void start() {
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

    @Test
    public void test() {
        start();
        RpcServerService service = applicationContext.getBean(RpcServerService.class);

        service.init();

        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
