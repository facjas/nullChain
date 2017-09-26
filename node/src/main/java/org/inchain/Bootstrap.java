package org.inchain;

import org.inchain.constant.CfgConstant;
import org.inchain.rpcserver.intf.RpcServerService;
import org.inchain.util.cfg.ConfigLoader;
import org.inchain.util.log.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * System start class
 */
public class Bootstrap {
    public static ClassPathXmlApplicationContext applicationContext;

    public static void main(String[] args) {
        do {
            //load cfg.properties
            Properties prop;
            try {
                prop = ConfigLoader.loadProperties("cfg.properties");
            } catch (IOException e) {
                Log.error("Client start faild", e);
                break;
            }
            String profile = prop.getProperty(CfgConstant.SpringProfile);
            String dbType = prop.getProperty(CfgConstant.MybatisDbType);
            //load spring context
            boolean result = loadSpringContext(profile, dbType);
            if(!result){
                break;
            }
            //init modules
            //init rpc server
            Log.info("");
        } while (false);
    }

    /**
     * start spring
     */
    private static boolean loadSpringContext(String profile, String dbType) {
        //加载spring环境
        Log.info("get application context");
        boolean result = false;
        try {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
            ctx.getEnvironment().setActiveProfiles(profile);
            String filePath[] = new String[]{"classpath:/applicationContext.xml"};

            ctx.setConfigLocations(filePath);
            ctx.refresh();
            applicationContext = ctx;
            Log.info("System is started!");
            result = true;
        } catch (Exception e) {
            Log.error("", e);
        }
        //        RpcServerService service = applicationContext.getBean(RpcServerService.class);
        return result;
    }

}
