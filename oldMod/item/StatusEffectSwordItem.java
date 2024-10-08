package net.locipro.balleritemod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

/**
 *  Very simple class for swords that give entities a debuff on-hit.
 *  postHit() adds the specified status effect to the "LivingEntity target" for the specified amount of ticks.
 */
public abstract class StatusEffectSwordItem extends SwordItem {
    private final MobEffect statusEffect;
    private final int statusDuration;
    public StatusEffectSwordItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings, MobEffect statusEffect, int statusDuration) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.statusEffect = statusEffect;
        this.statusDuration = statusDuration;
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(statusEffect, statusDuration));
        return super.hurtEnemy(stack, target, attacker);
    }
}
