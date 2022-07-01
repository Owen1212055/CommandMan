package com.owen1212055.commandman.plugin.platform.bukkit;

import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import org.bukkit.command.*;
import org.bukkit.command.Command;
import org.jetbrains.annotations.*;

import java.util.*;

public class CommandBukkitCommand extends Command implements TabCompleter {

    private final com.owen1212055.commandman.api.Command<?> apiCommand;
    private final NodeModel nodeModel;


    public CommandBukkitCommand(com.owen1212055.commandman.api.Command<?> apiCommand) {
        super(apiCommand.getName());
        this.apiCommand = apiCommand;
        this.nodeModel = apiCommand.getNodeModel();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        for (Node node : nodeModel.getArguments()) {

        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }

    private List<Node> getActiveNodes(String[] args) {
        return List.of();
    }
}
