package com.owen1212055.commandman.node.impl;

import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.command.*;
import com.owen1212055.commandman.api.permission.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class CommandNodeImpl implements CommandNode {

    private boolean optional;
    private final Command<?> command;

    public CommandNodeImpl(Command<?> command) {
        this.command = command;
    }

    @Override
    public Command<?> getCommand() {
        return command;
    }

    @Override
    public List<Node> getChildren() {
        return command.getNodeModel().getArguments();
    }

    @Override
    public <T extends Node> T optional(boolean optional) {
        this.optional = optional;
        return (T) this;
    }

    @Override
    public boolean isOptional() {
        return this.optional;
    }

    @Override
    public PermissionModule getPermission() {
        return command.getPermissionModule();
    }


}
