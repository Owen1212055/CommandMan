package com.owen1212055.commandman.api.node;

import com.owen1212055.commandman.api.permission.*;

import java.util.*;

public interface Node {

    <T extends Node> T permission(PermissionModule permissionModule);

    PermissionModule getPermission();

    <T extends Node> T addChild(Node child);

    List<Node> getChildren();

    <T extends Node> T optional(boolean optional);

    boolean isOptional();

}
