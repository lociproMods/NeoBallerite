package net.locipro.balleritemod.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public abstract class ModCommands {
    public static void registerModCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> HydrationCommands.registerHydrationCommands(dispatcher));
    }
}
