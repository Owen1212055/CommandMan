package com.owen1212055.commandman.api.permission.impl;

import com.owen1212055.commandman.api.permission.*;
import org.bukkit.entity.*;

public record StaticPermissionModule(AccessResult result) implements PermissionModule {

    public static final StaticPermissionModule ALLOWED = new StaticPermissionModule(AccessResult.ACCEPTED);

    @Override
    public AccessResult hasPermission(Player player) {
        return result;
    }
}
