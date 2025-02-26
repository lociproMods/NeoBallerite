package com.locipro.neoballerite.item.tool;

import com.locipro.neoballerite.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

public class BalleriteSwordItem extends SwordItem {
    public BalleriteSwordItem(ToolMaterial tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();
        if (level instanceof ServerLevel serverLevel && Config.ballerite_tools_emit_particles) {
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
