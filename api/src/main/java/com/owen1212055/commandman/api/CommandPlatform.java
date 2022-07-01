package com.owen1212055.commandman.api;

import com.owen1212055.commandman.api.node.ArgumentAdapter;
import com.owen1212055.commandman.api.node.NodeAdapter;

public interface CommandPlatform extends CommandRegister {

    NodeAdapter getNodeAdapter();

    ArgumentAdapter getArgumentAdapter();
}
