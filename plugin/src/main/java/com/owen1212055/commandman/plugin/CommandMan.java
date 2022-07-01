package com.owen1212055.commandman.plugin;

import com.owen1212055.commandman.api.CommandPlatforms;
import com.owen1212055.commandman.plugin.platform.CommandPlatformTypes;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandMan extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandPlatforms.setPlatform(CommandPlatformTypes.NMS);

        //   CommandPlatforms.getPlatform().register(this, new TestCommand());
    }

}
