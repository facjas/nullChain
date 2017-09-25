package org.inchain;


import org.inchain.Message.InchainMessage;

public interface IBroadcaster {
    void Boradcast(InchainMessage msg);
}
