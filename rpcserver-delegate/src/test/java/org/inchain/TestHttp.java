package org.inchain;

import org.inchain.rpcserver.RpcServerService;
import org.inchain.util.log.Log;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

/**
 * Created by Niels on 2017/9/25.
 * inchain.org
 */
public class TestHttp {

    public static ClassPathXmlApplicationContext applicationContext;

    /**
     * @param args
     */
    public static void main(String[] args) {
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
        main(null);
        RpcServerService service = applicationContext.getBean(RpcServerService.class);

        service.init();

        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
