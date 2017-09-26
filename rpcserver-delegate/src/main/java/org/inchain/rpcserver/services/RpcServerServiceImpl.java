package org.inchain.rpcserver.services;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.inchain.rpcserver.RpcServerService;
import org.inchain.util.log.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Niels on 2017/9/25.
 * inchain.org
 */
@Service
public class RpcServerServiceImpl implements RpcServerService {
    @Value("${server.ip}")
    private String serverIp;
    @Value("${server.port}")
    private String serverPort;
    @Value("${rest.packages}")
    private String packages;
    @Override
    public void init() {
        if(null==serverIp) {
            //todo 临时测试使用的代码，需要删除
            serverIp = "127.0.0.1";
            serverPort = "8001";
            packages = "org.inchain.rpc.resources";
        }
        URI serverURI = UriBuilder.fromUri("http://" + serverIp + "/").port(Integer.parseInt(serverPort)).build();
        final Map<String, Object> initParams = new HashMap<>();
        initParams.put("jersey.config.server.provider.packages", packages);
        ResourceConfig rc = new ResourceConfig();
        rc.addProperties(initParams);
        GrizzlyHttpServerFactory.createHttpServer(serverURI, rc);
        Log.info("http restFul server is started!");
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }
}
