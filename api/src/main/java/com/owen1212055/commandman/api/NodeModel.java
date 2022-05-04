package com.owen1212055.commandman.api;

import com.owen1212055.commandman.api.node.*;

import java.util.*;

public class NodeModel {

    private final List<Node> arguments = new ArrayList<>();

    public NodeModel arg(Node arg) {
        this.arguments.add(arg);
        return this;
    }

    public NodeModel chain(Node... args) {
        if (args.length == 0) {
            return this;
        }

        Node previousArg = args[0];
        this.arguments.add(previousArg);

        for (int i = 1; i < args.length; i++) {
            Node argument = args[i];
            previousArg.addChild(argument);

            previousArg = argument;
        }


        return this;
    }


    public List<Node> getArguments() {
        return arguments;
    }

}
