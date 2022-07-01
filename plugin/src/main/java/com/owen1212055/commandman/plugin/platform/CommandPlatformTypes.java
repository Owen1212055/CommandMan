package com.owen1212055.commandman.plugin.platform;

import com.owen1212055.commandman.api.Command;
import com.owen1212055.commandman.api.CommandPlatform;
import com.owen1212055.commandman.api.node.ArgumentAdapter;
import com.owen1212055.commandman.api.node.NodeAdapter;
import com.owen1212055.commandman.nms.CommandAPI;
import com.owen1212055.commandman.nms.node.NmsArgumentAdapter;
import com.owen1212055.commandman.nms.node.NmsNodeAdapter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class CommandPlatformTypes {

    // TODO:
    public static final CommandPlatform BUKKIT = new CommandPlatform() {
        //private final NodeAdapter

        @Override
        public NodeAdapter getNodeAdapter() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ArgumentAdapter getArgumentAdapter() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void register(Plugin plugin, Command<?> command) {
            if (true) {
                throw new UnsupportedOperationException();
            }
            plugin.getServer().getCommandMap().register(plugin.getName(), new org.bukkit.command.Command(command.getName()) {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    return false;
                }
            });
        }
    };

    public static final CommandPlatform NMS = new CommandPlatform() {

        private final NodeAdapter nodeAdapter = new NmsNodeAdapter();
        private final ArgumentAdapter argumentAdapter = new NmsArgumentAdapter();

        @Override
        public NodeAdapter getNodeAdapter() {
            return nodeAdapter;
        }

        @Override
        public ArgumentAdapter getArgumentAdapter() {
            return argumentAdapter;
        }

        @Override
        public void register(Plugin plugin, Command<?> command) {
            CommandAPI.register(plugin, command);
        }
    };

}
