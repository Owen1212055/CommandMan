package com.owen1212055.commandman.api.node.command;

import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.permission.*;

public interface CommandNode extends Node {

    Command<?> getCommand();

    @Override
    default <T extends Node> T permission(PermissionModule permissionModule) {
        throw new UnsupportedOperationException("You simply cannot.");
    }

    @Override
    default <T extends Node> T addChild(Node child) {
        throw new UnsupportedOperationException("You simply cannot.");
    }
}
