package org.inchain;

import org.inchain.rpcserver.RpcService;
import org.inchain.rpcserver.services.RpcServiceImpl;
import org.junit.Test;

/**
 * Created by Niels on 2017/9/25.
 * inchain.org
 */
public class TestHttp {

    @Test
    public void test(){
        RpcService service = new RpcServiceImpl();
        service.init();
        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
