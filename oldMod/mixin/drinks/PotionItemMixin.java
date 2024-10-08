package net.locipro.balleritemod.mixin.drinks;

import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.locipro.balleritemod.util.ModPotionUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin {
    // When the potion item is used, if the player using it is a ServerPlayerEntity, then add or remove hydration based on potion.
    @Inject(method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "HEAD"))
    public void finishUsingMixin(ItemStack stack, Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (user instanceof ServerPlayer player && !world.isClientSide()) {
            HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
            if (ModPotionUtil.isBadPotion(stack)) {
                hydrationManager.removeHydration(2);
            }
            else{
                hydrationManager.addHydration(2);
            }
            //HydrationHelper.syncHydration(player);
        }
    }
}
