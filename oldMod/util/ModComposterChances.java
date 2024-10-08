package net.locipro.balleritemod.util;

import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

public abstract class ModComposterChances {
    public static void registerModComposterChances() {
        BalleriteMod.LOGGER.info("Registering composter additions for " + BalleriteMod.MOD_ID);

        ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO_SEEDS, 0.35f);
        ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO, 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.EGGPLANT_SEEDS, 0.35f);
        ComposterBlock.COMPOSTABLES.put(ModItems.EGGPLANT, 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.STRAWBERRY_SEEDS, 0.35f);
        ComposterBlock.COMPOSTABLES.put(ModItems.STRAWBERRY, 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BLUE_BERRIES, 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BLACK_BERRIES, 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.UNRIPE_STRAWBERRY, 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SWEET_POTATO, 0.65f);
    }
}
