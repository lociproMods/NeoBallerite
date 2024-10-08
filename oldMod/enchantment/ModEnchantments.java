package net.locipro.balleritemod.enchantment;

import net.locipro.balleritemod.BalleriteMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantments {
    public static void registerModEnchantments(){
        BalleriteMod.LOGGER.info("Registering enchantments for " + BalleriteMod.MOD_ID);
    }
    public static Enchantment THORS_CURSE = register("thors_curse",
            new LightningStrikeEnchantment(Enchantment.Rarity.RARE,
                    EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static Enchantment POISONED_TIP = register("poisoned_tip",
            new PoisonedTipEnchantment(Enchantment.Rarity.UNCOMMON,
                    EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT, new ResourceLocation(BalleriteMod.MOD_ID, name), enchantment);
    }
}
