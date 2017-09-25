package org.inchain.rpcserver;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Niels on 2017/9/25.
 * inchain.org
 */
@Service("rpc")
public class RpcServiceImpl implements RpcService {


    @Override
    public void init() {
        String ip = "127.0.0.1";
        int port = 8001;
        String packages = "org.inchain.rpc.resources";



        URI serverURI = UriBuilder.fromUri("http://" + ip + "/").port(port).build();
        final Map<String, Object> initParams = new HashMap<>();
        initParams.put("jersey.config.server.provider.packages", packages);
        ResourceConfig rc = new ResourceConfig();
        rc.addProperties(initParams);
        GrizzlyHttpServerFactory.createHttpServer(serverURI, rc);
    }
}
