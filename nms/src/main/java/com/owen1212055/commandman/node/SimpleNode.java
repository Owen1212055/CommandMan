package com.owen1212055.commandman.node;

import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.permission.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class SimpleNode implements Node {

    private final List<Node> children = new ArrayList<>();
    private PermissionModule module;
    private boolean optional;

    @Override
    public <T extends Node> T permission(PermissionModule permissionModule) {
        this.module = permissionModule;
        return (T) this;
    }

    @Override
    public PermissionModule getPermission() {
        return this.module;
    }

    @Override
    public <T extends Node> T addChild(Node child) {
        this.children.add(child);
        return (T) this;
    }

    @Override
    public List<Node> getChildren() {
        return this.children;
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
}
