package net.locipro.balleritemod.mixin;

import net.locipro.balleritemod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pig.class)
public abstract class PigEntityMixin extends Animal {

    //private static final Ingredient CUSTOM_BREEDING_INGREDIENT = Ingredient.ofItems(ModItems.SWEET_POTATO, ModItems.TOMATO);


    protected PigEntityMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }


    @Inject(method = "registerGoals()V", at = @At(value = "TAIL"))
    void initGoalsMixin(CallbackInfo ci) {
        super.goalSelector.addGoal(4, new TemptGoal(this, 1.2, Ingredient.of(ModItems.SWEET_POTATO, ModItems.TOMATO), false));
    }
    @Inject(method = "isFood(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
    void isCustomBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(ModItems.SWEET_POTATO) || stack.is(ModItems.TOMATO)) {
            cir.setReturnValue(true);
        }
    }
}
