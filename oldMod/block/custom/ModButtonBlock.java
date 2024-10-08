package net.locipro.balleritemod.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModButtonBlock extends ButtonBlock {
    public ModButtonBlock(Properties settings, BlockSetType blockSetType, int pressTicks) {
        super(blockSetType, pressTicks, settings);
    }
    public static ModButtonBlock createWoodenButtonBlock(BlockSetType blockSetType) {
        return new ModButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).noCollission().strength(0.5f), blockSetType, 30);
    }

    public static ModButtonBlock createStoneButtonBlock() {
        return new ModButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noCollission().strength(0.5f), BlockSetType.STONE, 20);
    }
    public static ModButtonBlock createMetalButtonBlock(Properties properties) {
        return new ModButtonBlock(properties, BlockSetType.IRON, 10);
    }
}
