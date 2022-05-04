package com.owen1212055.commandman.api.permission;

// Better name appreciated, but this lets us use this in other places by providing detailed user friendly
// permission reason.
public record AccessResult(boolean hasPermission, String reason) {

    public static final AccessResult ACCEPTED = new AccessResult(true, "Has Permission");

}
