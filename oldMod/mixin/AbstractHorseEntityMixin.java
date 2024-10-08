package net.locipro.balleritemod.mixin;

import net.locipro.balleritemod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorse.class)
public abstract class AbstractHorseEntityMixin extends Animal implements ContainerListener,
        HasCustomInventoryScreen,
        OwnableEntity,
        PlayerRideableJumping,
        Saddleable {

    protected AbstractHorseEntityMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean isTamed();
    @Shadow public abstract int modifyTemper(int difference);
    @Shadow public abstract int getTemper();
    @Shadow public abstract int getMaxTemper();
    @Shadow protected abstract void eating();

    @Inject(method = "registerGoals()V", at = @At(value = "TAIL"))
    private void initCustomGoalsMixin(CallbackInfo ci) {
        super.goalSelector.addGoal(4, new TemptGoal(this, 1.15, Ingredient.of(ModItems.TOMATO), false));
    }


    @Inject(method = "handleEating(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void receiveCustomFoodMixin(Player player, ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        boolean bl = false;
        float f = 0f;
        int i = 0;
        int j = 0;

        if (item.is(ModItems.COCAINE)) {
            f = 0f;
            i = 300;
            j = 12;
            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                bl = true;
                this.setInLove(player);
                if (!(this.hasEffect(MobEffects.MOVEMENT_SPEED))) {
                    this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1, true, true));
                    player.displayClientMessage(Component.literal("You just gave your horse cocaine... Well now he's speedy! I'm calling PETA though...")
                            .withStyle(ChatFormatting.UNDERLINE, ChatFormatting.RED), true);
                }
                else {
                    this.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1, true, true));
                    player.displayClientMessage(Component.literal("Again. You gave him coke again. HE'S HIGH. HE'S GONNA OVERDOSE.")
                            .withStyle(ChatFormatting.UNDERLINE, ChatFormatting.RED), true);
                }
            }
        }
        else if (item.is(ModItems.TOMATO)) {
            f = 3f;
            i = 60;
            j = 3;
            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                bl = true;
                this.setInLove(player);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0f) {
            this.heal(f);
            bl = true;
        }
        if (this.isBaby() && i > 0) {
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            if (!this.level().isClientSide) {
                this.ageUp(i);
            }
            bl = true;
        }
        if (j > 0 && (bl || !this.isTamed()) && this.getTemper() < this.getMaxTemper()) {
            bl = true;
            if (!this.level().isClientSide) {
                this.modifyTemper(j);
            }
        }
        if (bl) {
            this.eating();
            this.gameEvent(GameEvent.EAT);
            cir.setReturnValue(true);
        }
    }


    @Inject(method = "isFood(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
    void isCustomBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(ModItems.TOMATO) || stack.is(ModItems.COCAINE) || stack.is(ModItems.SWEET_POTATO)) {
            cir.setReturnValue(true);
        }
    }
}