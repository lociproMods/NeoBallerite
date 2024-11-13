package com.locipro.neoballerite.event;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BlockEntityTypeAddBlocksHandler {
    @SubscribeEvent
    public static void blockEntityTypeAdd(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                ModBlocks.WITHERED_SIGN.get(),
                ModBlocks.WITHERED_WALL_SIGN.get(),
                ModBlocks.STAR_SIGN.get(),
                ModBlocks.STAR_WALL_SIGN.get());

        event.modify(BlockEntityType.HANGING_SIGN,
                ModBlocks.WITHERED_HANGING_SIGN.get(),
                ModBlocks.WITHERED_WALL_HANGING_SIGN.get(),
                ModBlocks.STAR_HANGING_SIGN.get(),
                ModBlocks.STAR_WALL_HANGING_SIGN.get());
    }
}
/*    // Neo: This field will be modified by BlockEntityTypeAddBlocksEvent event. Please use the event to add to this field for vanilla or other mod's BlockEntityTypes.
    private final Set<Block> validBlocks;*/