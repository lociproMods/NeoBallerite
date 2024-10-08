package net.locipro.balleritemod.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import java.util.Objects;

public class PoisonedTipEnchantment extends Enchantment {
    protected PoisonedTipEnchantment(Rarity weight, EnchantmentCategory target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinCost(int level) {
        return level * 10;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (!user.level().isClientSide()) {
            if (!((LivingEntity)target).hasEffect(MobEffects.POISON) || Objects.requireNonNull(((LivingEntity) target).getEffect(MobEffects.POISON)).getAmplifier() <= level) {
                switch (level) {
                    case 1 -> ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.POISON, 50, 1));
                    case 2 -> ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
                    case 3 -> ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.POISON, 200, 2));
                }
            }
            super.doPostAttack(user, target, level);
        }
    }
}
