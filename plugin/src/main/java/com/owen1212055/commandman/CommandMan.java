package com.owen1212055.commandman;

import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.*;
import com.owen1212055.commandman.node.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;

public class CommandMan extends JavaPlugin implements CommandRegister {

    @Override
    public void onEnable() {
        Nodes.setAdapter(new NmsNodeAdapter());
        Arguments.setAdapter(new NmsArgumentAdapter());
    }

    @Override
    public void register(Plugin plugin, Command<?> command) {
        CommandAPI.register(plugin, command);
    }
}
