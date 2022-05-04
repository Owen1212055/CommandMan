package com.owen1212055.commandman.api;

import java.util.*;

public class ArgumentSet {

    private final Map<String, Object> arguments = new HashMap<>();

    public boolean has(String id) {
        return arguments.containsKey(id);
    }

    public void set(String id, Object obj) {
        arguments.put(id, obj);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String id) {
        return (T) arguments.get(id);
    }
}
