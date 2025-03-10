package com.locipro.neoballerite.event;

import com.google.common.collect.ImmutableSet;
import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.GAME)
public class MobSpawnEventHandler {

    private static final ImmutableSet<Class<? extends Mob>> KNIFE_WIELDING = ImmutableSet.of(
            Zombie.class, AbstractPiglin.class, Pillager.class);

    // Not even gonna bother
    /*// I might be actually fucking mentally disabled.
    private static HashMap<Integer, DeferredItem<?>> KNIVES = new HashMap<>();
    static {
        KNIVES.put(1, ModItems.DIAMOND_KNIFE);
        KNIVES.put(2, ModItems.KNIFE);
        KNIVES.put(3, ModItems.KNIFE);
    }*/


    @SubscribeEvent
    public static void onFinalizeSpawn(FinalizeSpawnEvent event) {
        Mob mob = event.getEntity();

        if (mob.getMainHandItem().is(ModTags.Items.KNIVES)) {
            return;
        }
        if (KNIFE_WIELDING.stream().anyMatch(clazz -> clazz.isInstance(mob))
                && event.getSpawnType() != EntitySpawnReason.CONVERSION) {
            float chanceToWield = 0f;
            if (mob.getMainHandItem().isEmpty()) {
                chanceToWield = event.getLevel().getDifficulty() == Difficulty.HARD ? 0.1f : 0.05f;
            }else {
                chanceToWield = event.getLevel().getDifficulty() == Difficulty.HARD ? 0.05f : 0.0025f;
            }


            if (event.getLevel().getRandom().nextFloat() <= chanceToWield) {
                mob.setItemSlot(
                        EquipmentSlot.MAINHAND,
                        ModItems.KNIFE.toStack()
                );
            }
        }
    }
}
