package org.inchain;

import org.inchain.mq.intf.QueueService;
import org.inchain.rpcserver.intf.RpcServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InchainContext {

    @Autowired
    private RpcServerService rpcServerService;
    @Autowired
    private QueueService queueService;

    public RpcServerService getRpcServerService() {
        return rpcServerService;
    }

    public QueueService getQueueService() {
        return queueService;
    }
}
