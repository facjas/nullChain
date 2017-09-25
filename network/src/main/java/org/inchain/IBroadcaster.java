package org.inchain;


import org.inchain.message.InchainMessage;

public interface IBroadcaster {
    void Boradcast(InchainMessage msg);
}
