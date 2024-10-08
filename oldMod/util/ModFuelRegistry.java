package net.locipro.balleritemod.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.data.ModItemTagGenerator;
import net.locipro.balleritemod.item.ModItems;

public abstract class ModFuelRegistry {
    public static void registerModFuels() {
        BalleriteMod.LOGGER.info("Registering fuel items for " + BalleriteMod.MOD_ID);

        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(ModItems.CHARRED_BALLERITE, 1600);
        registry.add(ModBlocks.CHARRED_BALLERITE_BLOCK, 16000);

        registry.add(ModItemTagGenerator.WITHERED_LOGS_ITEM, 300);
        registry.add(ModItemTagGenerator.STAR_LOGS_ITEM, 300*2);

        registry.add(ModBlocks.WITHERED_PLANKS, 300);
        registry.add(ModBlocks.STAR_PLANKS, 300*2);

        registry.add(ModBlocks.WITHERED_SLAB, 150);
        registry.add(ModBlocks.STAR_SLAB, 150*2);

        registry.add(ModBlocks.WITHERED_STAIRS, 300);
        registry.add(ModBlocks.STAR_STAIRS, 300*2);

        registry.add(ModBlocks.WITHERED_BUTTON, 100);
        registry.add(ModBlocks.STAR_BUTTON, 100*2);

        registry.add(ModBlocks.WITHERED_TRAPDOOR, 300);
        registry.add(ModBlocks.STAR_TRAPDOOR, 300*2);

        registry.add(ModBlocks.WITHERED_FENCE, 300);
        registry.add(ModBlocks.STAR_FENCE, 300*2);

        registry.add(ModBlocks.WITHERED_FENCE_GATE, 300);
        registry.add(ModBlocks.STAR_FENCE_GATE, 300*2);
    }
}
