package com.owen1212055.commandman.api;

import org.bukkit.plugin.*;

public interface CommandRegister {

    void register(Plugin plugin, Command<?> command);
}
