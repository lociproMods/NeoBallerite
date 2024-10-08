package net.locipro.balleritemod.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;

public abstract class ModFlammableBlockRegistry {
    public static void registerFlammableBlocks() {
        BalleriteMod.LOGGER.info("Registering flammable blocks for " + BalleriteMod.MOD_ID);

        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        registry.add(ModBlocks.WITHERED_LOG, 5, 5);
        registry.add(ModBlocks.WITHERED_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_WITHERED_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_WITHERED_WOOD, 5, 5);

        registry.add(ModBlocks.WITHERED_PLANKS, 5, 20);
        registry.add(ModBlocks.WITHERED_LEAVES, 20, 60);



    }
}
