package net.locipro.balleritemod.util;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;

public abstract class ModStrippables {
    public static void registerModStrippables() {
        BalleriteMod.LOGGER.info("Registering strippable blocks for " + BalleriteMod.MOD_ID);


        StrippableBlockRegistry.register(ModBlocks.WITHERED_LOG, ModBlocks.STRIPPED_WITHERED_LOG);
        StrippableBlockRegistry.register(ModBlocks.WITHERED_WOOD, ModBlocks.STRIPPED_WITHERED_WOOD);

        StrippableBlockRegistry.register(ModBlocks.STAR_LOG, ModBlocks.STRIPPED_STAR_LOG);
        StrippableBlockRegistry.register(ModBlocks.STAR_WOOD, ModBlocks.STRIPPED_STAR_WOOD);
    }
}
