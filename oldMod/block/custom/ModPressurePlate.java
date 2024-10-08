package net.locipro.balleritemod.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModPressurePlate extends PressurePlateBlock {
    public ModPressurePlate(Properties settings, BlockSetType blockSetType) {
        super(blockSetType, settings);
    }
    public static ModPressurePlate createWoodenPressurePlate() {
        return new ModPressurePlate(FabricBlockSettings.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).strength(0.5f).noCollission(), BlockSetType.OAK);
    }
    public static ModPressurePlate createMetalPressurePlate() {
        return new ModPressurePlate(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(1f).requiresCorrectToolForDrops().noCollission(), BlockSetType.IRON);
    }
}
