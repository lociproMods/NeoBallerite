package com.locipro.neoballerite.item.tool;

import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BalleriteAxeItem extends AxeItem {
    public BalleriteAxeItem(Tier p_40521_, Properties p_40524_) {
        super(p_40521_, p_40524_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {

        if (level instanceof ServerLevel serverLevel) {
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
                    ParticleTypes.GLOW,
                    pos.getX() + 0.5,
                    pos.getY() + 0.4,
                    pos.getZ() + 0.5,
                    3,
                    0,
                    0,
                    0,
                    0.15f
            );
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }
}
