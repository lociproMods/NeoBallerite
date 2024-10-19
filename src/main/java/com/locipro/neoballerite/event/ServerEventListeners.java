package com.locipro.neoballerite.event;


import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoEnchantmentComponents;
import com.locipro.neoballerite.component.enchantment.MobEffectEnchantmentType;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ServerEventListeners {

    @SubscribeEvent
    public static void attackEntityEvent(AttackEntityEvent attackEntityEvent) {

        Player attacker = attackEntityEvent.getEntity();
        Entity target = attackEntityEvent.getTarget();
        if (attacker instanceof ServerPlayer player
        && target instanceof LivingEntity entity) {
            ItemStack item = player.getWeaponItem();

            EnchantmentHelper.runIterationOnItem(item, (enchantmentHolder, enchantLevel) -> {
                MobEffectEnchantmentType enchantment = enchantmentHolder.value().effects().get(NeoEnchantmentComponents.MOB_EFFECT_ENCHANTMENT.get());
                if (enchantment != null) {
                    entity.addEffect(new MobEffectInstance(
                            Holder.direct(enchantment.mobEffect()),
                            enchantment.duration() * enchantLevel,
                            (enchantment.amplifier() - 1) * enchantLevel,
                            enchantment.ambient(),
                            enchantment.particles_visible()));
                }
            });
        }
    }
}
