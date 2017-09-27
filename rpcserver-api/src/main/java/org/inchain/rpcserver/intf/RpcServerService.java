package org.inchain.rpcserver.intf;

/**
 * Created by Niels on 2017/9/25.
 * inchain.org
 */
public interface RpcServerService {
    /**
     * start http server，restFul
     */
    void init();

    void shutdown();

    boolean isStarted();

}
