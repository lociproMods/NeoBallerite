package net.locipro.balleritemod.util;

import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

public abstract class ModPotionUtil {
    public static boolean isBadPotion(Potion potion) {
        return potion == Potions.HARMING || potion == Potions.LONG_POISON || potion == Potions.LONG_SLOWNESS
                || potion == Potions.LONG_WEAKNESS || potion == Potions.POISON || potion == Potions.SLOWNESS || potion == Potions.STRONG_HARMING
                || potion == Potions.STRONG_POISON || potion == Potions.STRONG_SLOWNESS || potion == Potions.WEAKNESS;
    }
    public static boolean isBadPotion(ItemStack stack) {
        Potion potion = PotionUtils.getPotion(stack);
        return potion == Potions.HARMING || potion == Potions.LONG_POISON || potion == Potions.LONG_SLOWNESS
                || potion == Potions.LONG_WEAKNESS || potion == Potions.POISON || potion == Potions.SLOWNESS || potion == Potions.STRONG_HARMING
                || potion == Potions.STRONG_POISON || potion == Potions.STRONG_SLOWNESS || potion == Potions.WEAKNESS;
    }

    public static boolean isBadEffect(MobEffect effect) {
        return !effect.isBeneficial();
    }
    public static boolean isBadEffect(List<MobEffect> list) {
        for (MobEffect effect : list) {
            if (!effect.isBeneficial()) {
                return true;
            }
        }
        return false;
    }
    public static boolean isBadEffectInstance(List<MobEffectInstance> list) {
        for (MobEffectInstance effectInstance : list) {
            MobEffect effect = effectInstance.getEffect();
            if (!effect.isBeneficial()) {
                return true;
            }
        }
        return false;
    }
}
