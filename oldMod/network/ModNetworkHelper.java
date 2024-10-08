package net.locipro.balleritemod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;

public class ModNetworkHelper {
    public static boolean aroundBlock(ServerPlayer player, ServerLevel world, Block block, int expandRadius) {
        return BlockPos.betweenClosedStream(player.getBoundingBox().inflate(expandRadius))
                .map(world::getBlockState).filter(blockState -> blockState.is(block)).toArray().length > 0;
    }

    // TODO IMPLEMENT THIS YOU LAZY FUCK
    public static boolean aroundBlockBelow(ServerPlayer player, ServerLevel world, Block block, int expandRadius) {
        return BlockPos.betweenClosedStream(player.getBoundingBox().inflate(expandRadius))
                .map(world::getBlockState).filter(blockState -> blockState.is(block)).toArray().length > 0;
    }
}
