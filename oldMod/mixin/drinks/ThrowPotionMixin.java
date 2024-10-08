package net.locipro.balleritemod.mixin.drinks;


import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.locipro.balleritemod.util.ModPotionUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Debug(export = true)
@Mixin(ThrownPotion.class)
public abstract class ThrowPotionMixin {

    /*@Inject(method = "applySplashPotion(Ljava/util/List;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    injecting into wrong method im dumb
    private void applySplashHydration(List<StatusEffectInstance> statusEffects, @Nullable Entity entity, CallbackInfo ci) {
        PotionEntity instancePotionEntity = (PotionEntity) (Object) this;
        ItemStack instanceStack = instancePotionEntity.getStack();


        Box hydrationBox = instancePotionEntity.getBoundingBox().expand(4.0, 2.0, 4.0);
        // List of living entities that are in the bounding box specified.
        List<LivingEntity> entitiesInBox = instancePotionEntity.world.getNonSpectatingEntities(LivingEntity.class, hydrationBox);

        BalleriteMod.LOGGER.info("A splash potion has been thrown");
        BalleriteMod.LOGGER.info(statusEffects.toString());
        BalleriteMod.LOGGER.info(String.valueOf(statusEffects.isEmpty()));

        if (!entitiesInBox.isEmpty()) {
            for (LivingEntity livingEntity : entitiesInBox) {

                double d = instancePotionEntity.squaredDistanceTo(livingEntity);
                if (!livingEntity.isAffectedBySplashPotions() || !(d < 16.0)) continue;


                if (livingEntity.isPlayer()) {
                    if (statusEffects.isEmpty()) {
                        HydrationData.addHydration((IEntityDataSaver) livingEntity, 2);
                        continue;
                    }
                    if (ModPotionUtil.isBadPotion(instanceStack)) {
                        HydrationData.removeHydration((IEntityDataSaver) livingEntity, 2);
                        continue;
                    }
                    HydrationData.addHydration((IEntityDataSaver) livingEntity, 2);
                }
            }
        }
    }*/
    // When the splash potion hits something, if the splash potion is in on the server side, do the logic.
    @Inject(method = "onHit(Lnet/minecraft/world/phys/HitResult;)V", at = @At("HEAD"))
    protected void onCollision0(HitResult hitResult, CallbackInfo ci) {

        ThrownPotion instancePotionEntity = (ThrownPotion) (Object) this;
        Potion potion = PotionUtils.getPotion(instancePotionEntity.getItem());

        if (!instancePotionEntity.level().isClientSide()) {
            AABB hydrationBox = instancePotionEntity.getBoundingBox().inflate(4.0, 2.0, 4.0);
            // List of living entities that are in the bounding box specified.
            List<LivingEntity> entitiesInBox = instancePotionEntity.level().getEntitiesOfClass(LivingEntity.class, hydrationBox);

            BalleriteMod.LOGGER.info(instancePotionEntity.toString());
            BalleriteMod.LOGGER.info(potion.toString());
            BalleriteMod.LOGGER.info("A splash potion has collided with an obstacle.");


            if (!entitiesInBox.isEmpty()) {
                for (LivingEntity livingEntity : entitiesInBox) {
                    if (livingEntity.isAlwaysTicking() && livingEntity instanceof ServerPlayer player) {
                        double d = instancePotionEntity.distanceToSqr(livingEntity);
                        if (!player.isAffectedByPotions() || !(d < 16.0)) continue;

                        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();

                        if (ModPotionUtil.isBadPotion(potion)) {
                            hydrationManager.removeHydration(2);
                        }else {
                            hydrationManager.addHydration(2);
                        }
                        //HydrationHelper.syncHydration(player);
                    }
                }
            }
        }
    }
}
/*//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minecraft.entity.projectile.thrown;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PotionEntity extends ThrownItemEntity implements FlyingItemEntity {
    public static final double field_30667 = 4.0;
    private static final double field_30668 = 16.0;
    public static final Predicate<LivingEntity> AFFECTED_BY_WATER = (entity) -> {
        return entity.hurtByWater() || entity.isOnFire();
    };

    public PotionEntity(EntityType<? extends PotionEntity> entityType, World world) {
        super(entityType, world);
    }

    public PotionEntity(World world, LivingEntity owner) {
        super(EntityType.POTION, owner, world);
    }

    public PotionEntity(World world, double x, double y, double z) {
        super(EntityType.POTION, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SPLASH_POTION;
    }

    protected float getGravity() {
        return 0.05F;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient) {
            ItemStack itemStack = this.getStack();
            Potion potion = PotionUtil.getPotion(itemStack);
            List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemStack);
            boolean bl = potion == Potions.WATER && list.isEmpty();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockPos blockPos2 = blockPos.offset(direction);
            if (bl) {
                this.extinguishFire(blockPos2);
                this.extinguishFire(blockPos2.offset(direction.getOpposite()));
                Iterator var9 = Type.HORIZONTAL.iterator();

                while(var9.hasNext()) {
                    Direction direction2 = (Direction)var9.next();
                    this.extinguishFire(blockPos2.offset(direction2));
                }
            }

        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            ItemStack itemStack = this.getStack();
            Potion potion = PotionUtil.getPotion(itemStack);
            List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemStack);
            boolean bl = potion == Potions.WATER && list.isEmpty();
            if (bl) {
                this.applyWater();
            } else if (!list.isEmpty()) {
                if (this.isLingering()) {
                    this.applyLingeringPotion(itemStack, potion);
                } else {
                    this.applySplashPotion(list, hitResult.getType() == net.minecraft.util.hit.HitResult.Type.ENTITY ? ((EntityHitResult)hitResult).getEntity() : null);
                }
            }

            int i = potion.hasInstantEffect() ? 2007 : 2002;
            this.getWorld().syncWorldEvent(i, this.getBlockPos(), PotionUtil.getColor(itemStack));
            this.discard();
        }
    }

    private void applyWater() {
        Box box = this.getBoundingBox().expand(4.0, 2.0, 4.0);
        List<LivingEntity> list = this.getWorld().getEntitiesByClass(LivingEntity.class, box, AFFECTED_BY_WATER);
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            LivingEntity livingEntity = (LivingEntity)var3.next();
            double d = this.squaredDistanceTo(livingEntity);
            if (d < 16.0) {
                if (livingEntity.hurtByWater()) {
                    livingEntity.damage(this.getDamageSources().indirectMagic(this, this.getOwner()), 1.0F);
                }

                if (livingEntity.isOnFire() && livingEntity.isAlive()) {
                    livingEntity.extinguishWithSound();
                }
            }
        }

        List<AxolotlEntity> list2 = this.getWorld().getNonSpectatingEntities(AxolotlEntity.class, box);
        Iterator var8 = list2.iterator();

        while(var8.hasNext()) {
            AxolotlEntity axolotlEntity = (AxolotlEntity)var8.next();
            axolotlEntity.hydrateFromPotion();
        }

    }

    private void applySplashPotion(List<StatusEffectInstance> statusEffects, @Nullable Entity entity) {
        Box box = this.getBoundingBox().expand(4.0, 2.0, 4.0);
        List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
        if (!list.isEmpty()) {
            Entity entity2 = this.getEffectCause();
            Iterator var6 = list.iterator();

            while(true) {
                LivingEntity livingEntity;
                double d;
                do {
                    do {
                        if (!var6.hasNext()) {
                            return;
                        }

                        livingEntity = (LivingEntity)var6.next();
                    } while(!livingEntity.isAffectedBySplashPotions());

                    d = this.squaredDistanceTo(livingEntity);
                } while(!(d < 16.0));

                double e;
                if (livingEntity == entity) {
                    e = 1.0;
                } else {
                    e = 1.0 - Math.sqrt(d) / 4.0;
                }

                Iterator var12 = statusEffects.iterator();

                while(var12.hasNext()) {
                    StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var12.next();
                    StatusEffect statusEffect = statusEffectInstance.getEffectType();
                    if (statusEffect.isInstant()) {
                        statusEffect.applyInstantEffect(this, this.getOwner(), livingEntity, statusEffectInstance.getAmplifier(), e);
                    } else {
                        int i = statusEffectInstance.mapDuration((ix) -> {
                            return (int)(e * (double)ix + 0.5);
                        });
                        StatusEffectInstance statusEffectInstance2 = new StatusEffectInstance(statusEffect, i, statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.shouldShowParticles());
                        if (!statusEffectInstance2.isDurationBelow(20)) {
                            livingEntity.addStatusEffect(statusEffectInstance2, entity2);
                        }
                    }
                }
            }
        }
    }

    private void applyLingeringPotion(ItemStack stack, Potion potion) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity) {
            areaEffectCloudEntity.setOwner((LivingEntity)entity);
        }

        areaEffectCloudEntity.setRadius(3.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotion(potion);
        Iterator var5 = PotionUtil.getCustomPotionEffects(stack).iterator();

        while(var5.hasNext()) {
            StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var5.next();
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
        }

        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null && nbtCompound.contains("CustomPotionColor", 99)) {
            areaEffectCloudEntity.setColor(nbtCompound.getInt("CustomPotionColor"));
        }

        this.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    private boolean isLingering() {
        return this.getStack().isOf(Items.LINGERING_POTION);
    }

    private void extinguishFire(BlockPos pos) {
        BlockState blockState = this.getWorld().getBlockState(pos);
        if (blockState.isIn(BlockTags.FIRE)) {
            this.getWorld().breakBlock(pos, false, this);
        } else if (AbstractCandleBlock.isLitCandle(blockState)) {
            AbstractCandleBlock.extinguish((PlayerEntity)null, blockState, this.getWorld(), pos);
        } else if (CampfireBlock.isLitCampfire(blockState)) {
            this.getWorld().syncWorldEvent((PlayerEntity)null, 1009, pos, 0);
            CampfireBlock.extinguish(this.getOwner(), this.getWorld(), pos, blockState);
            this.getWorld().setBlockState(pos, (BlockState)blockState.with(CampfireBlock.LIT, false));
        }

    }
}
*/