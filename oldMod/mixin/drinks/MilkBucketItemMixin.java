package net.locipro.balleritemod.mixin.drinks;

import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin extends Item {
    public MilkBucketItemMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"))
    void finishDrinkingMixin(ItemStack stack, Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClientSide()) {
            HydrationManager manager = ((IHydratable)user).getHydrationManager();
            manager.addHydration(6);
            //HydrationHelper.syncHydration((ServerPlayerEntity) user);
        }
    }
}