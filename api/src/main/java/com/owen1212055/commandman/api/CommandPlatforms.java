package com.owen1212055.commandman.api;

public class CommandPlatforms {

    private static CommandPlatform instance = null;

    public static void setPlatform(CommandPlatform platform) {
        if (instance != null) {
            throw new IllegalStateException("Can't reset singleton!");
        }

        instance = platform;
    }

    public static CommandPlatform getPlatform() {
        return instance;
    }
}
