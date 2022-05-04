package com.owen1212055.commandman.api;

import com.owen1212055.commandman.api.permission.*;
import org.bukkit.command.*;

public interface Command<T extends CommandSender> {

    Class<T> getTarget();

    String getName();

    default String[] getAliases() {
        return new String[0];
    }

    PermissionModule getPermissionModule();

    NodeModel getNodeModel();

    void execute(T sender, ArgumentSet args);
}
