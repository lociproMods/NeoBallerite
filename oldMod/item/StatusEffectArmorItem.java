package net.locipro.balleritemod.item;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 *  <pre>How this works : You map an armor material to a status effect.
 *  Then, on inventoryTick() the game checks if the player is wearing a full suit of armor, and if the armor
 *  is of the material specified. If all conditions are met, the player gets the status effect that's mapped
 *  to the armor material added to them.
 *  <b><i>IMPORTANT NOTICE:</i></b> <b>There should be only 1 Item of type "StatusEffectArmorItem" in your armor set.
 *  This is so that it doesn't do </b><i>inventoryTick()</i> <b>for every armor item.</b>
 *  <b>So you should have 1 Item of type <i>StatusEffectArmorItem</i>, and the 3 others of type <i>ArmorItem</i></b>.</pre>
 */
public class StatusEffectArmorItem extends ArmorItem {
    private final Map<ArmorMaterial, MobEffect> MATERIAL_TO_EFFECT_MAP;
    public StatusEffectArmorItem(ArmorMaterial material, Type type, Properties settings, MobEffect statusEffect) {
        super(material, type, settings);
        MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial, MobEffect>())
                .put(material, statusEffect).build();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if(!world.isClientSide()) {
            if(entity instanceof Player player) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffect> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffect mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffect mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect);

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect, 200, 0, false, false));
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return  !helmet.isEmpty() &&
                !breastplate.isEmpty() &&
                !leggings.isEmpty() &&
                !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (int i = 0; i < 4; i ++) {
            if (player.getInventory().getArmor(i).getItem() instanceof ArmorItem) {
                continue;
            }else {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return  helmet.getMaterial() == material &&
                breastplate.getMaterial() == material &&
                leggings.getMaterial() == material &&
                boots.getMaterial() == material;
    }
}
