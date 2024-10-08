package net.locipro.balleritemod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LightningStrikeEnchantment extends Enchantment {
    public LightningStrikeEnchantment(Rarity weight, EnchantmentCategory target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }
    @Override
    public int getMinCost(int level) {
        return level * 15;
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 50;
    }
    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (!user.level().isClientSide()) {
            ServerLevel world = (ServerLevel) user.level();
            Player player = (Player) user;
            BlockPos position = target.blockPosition();

            switch (level) {
                case 1 -> {
                    EntityType.LIGHTNING_BOLT.spawn(
                            world, (ItemStack) null, null, position, MobSpawnType.TRIGGERED, true, true);
                    player.hurt(world.damageSources().magic(), 1);
                }
                case 2 -> {
                    EntityType.LIGHTNING_BOLT.spawn(
                            world, (ItemStack) null, null, position, MobSpawnType.TRIGGERED, true, true);
                    EntityType.LIGHTNING_BOLT.spawn(
                            world, (ItemStack) null, null, position, MobSpawnType.TRIGGERED, true, true);
                    player.hurt(world.damageSources().magic(), 1);
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                }
            }
            super.doPostAttack(user, target, level);
        }
    }
}
