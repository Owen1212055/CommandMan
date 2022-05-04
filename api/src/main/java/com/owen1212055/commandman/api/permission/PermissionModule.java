package com.owen1212055.commandman.api.permission;

import org.bukkit.entity.*;

public interface PermissionModule {

    AccessResult hasPermission(Player player);
}
