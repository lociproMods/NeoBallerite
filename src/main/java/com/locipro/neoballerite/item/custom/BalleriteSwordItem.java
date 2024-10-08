package com.locipro.neoballerite.item.custom;

import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BalleriteSwordItem extends SwordItem {
    public BalleriteSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel) level;
            serverLevel.sendParticles(
                    ParticleTypes.GLOW,
                    target.getX(),
                    target.getY() + 0.3,
                    target.getZ(),
                    9,
                    0,
                    0,
                    0,
                    0.15f
            );
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
