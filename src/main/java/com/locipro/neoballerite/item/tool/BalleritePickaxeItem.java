package com.locipro.neoballerite.item.tool;

import com.locipro.neoballerite.Config;
import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BalleritePickaxeItem extends PickaxeItem {


    public BalleritePickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {

        if (level instanceof ServerLevel serverLevel && Config.ballerite_tools_emit_particles) {
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
                    0.1f
            );
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }
}
