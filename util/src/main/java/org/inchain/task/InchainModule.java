package org.inchain.task;

import java.util.List;
import java.util.Map;

/**
 * Created by Niels on 2017/9/26.
 * inchain.org
 */
public interface InchainModule {

    String getModuleName();

    void start();

    void reboot();

    void shutdown();

    String getInfo();

    ModuleStatus getStatus();
}
