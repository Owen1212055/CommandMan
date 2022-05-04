package com.owen1212055.commandman.api.node.argument.type.custom;

import net.kyori.adventure.text.*;

public class ArgumentParsingException extends RuntimeException {

    private final Component component;

    public ArgumentParsingException() {
        super();
        this.component = null;
    }

    public ArgumentParsingException(Component component) {
        super();
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }
}
