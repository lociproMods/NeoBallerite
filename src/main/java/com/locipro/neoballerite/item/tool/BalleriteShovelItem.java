package com.locipro.neoballerite.item.tool;

import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BalleriteShovelItem extends ShovelItem {
    public BalleriteShovelItem(Tier p_43114_, Properties p_43117_) {
        super(p_43114_, p_43117_);
    }
    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {

        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel) level;
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.COMPRESSED_BALLERITE_BLOCK.get().defaultBlockState()),
                    pos.getX() + 0.5,
                    pos.getY() + 0.8,
                    pos.getZ() + 0.5,
                    32,
                    0,
                    0,
                    0,
                    4f
            );
            serverLevel.sendParticles(
                    ParticleTypes.GLOW_SQUID_INK,
                    pos.getX() + 0.5,
                    pos.getY() + 0.4,
                    pos.getZ() + 0.5,
                    3,
                    0,
                    0,
                    0,
                    0.1f
            );
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }
}
