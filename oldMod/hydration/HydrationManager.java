package net.locipro.balleritemod.hydration;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biomes;

// This class will be used on player entities, both server side *and* client side.
// When something changes, we should send a sync packet which syncs the server side HydrationManager with the client side.
public class HydrationManager {
    //How many ticks for the entire 10 stacks of hydration.

    //private final int thirstTickPeriod = 24000; default
    // 24000 ticks is 20 minutes. each second is 20 ticks.
    //private final int thirstTickPeriod = 24000/10; for mod testing
    private final int thirstTickPeriod = 24000;
    private final int maxHydration = 10;
    private boolean hasThirst = true;
    private boolean debugHudBool = false;


    private final Player player;

    public HydrationManager(Player player) {
        this.player = player;
    }


    private int hydration = 10;
    // Simple timer that gets incremented every tick.
    private int thirstTickTimer;
    // How many ticks has the player had 0 hydration? Gets incremented by 1 for each tick.
    private int dehydrationTimer;

    // Default is 1, this determines how many thirst ticks get counted per tick.
    private int thirstMultiplier = 1;

    public void addHydration(int amount) {
        hydration = Math.min(hydration + amount, maxHydration);
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncAllHydration(serverPlayerEntity);
        }
    }
    public void removeHydration(int amount) {
        hydration = Math.max(hydration - amount, 0);
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncAllHydration(serverPlayerEntity);
        }
    }
    public void setHydration(int amount) {
        if (amount <= maxHydration && amount >= 0) {
            hydration = amount;
        }
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncAllHydration(serverPlayerEntity);
        }
    }


/*public static final int thirstTickTimer = 24000/10;

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            IHydratable player = ((IHydratable) playerEntity);
            int time = (player.getThirstTickTimer());
            if (time >= thirstTickTimer/10) {
                time = 0; // I think this line is useless.
                player.setThirstTickTimer(0);
                player.removeHydration(1);
                HydrationHelper.syncThirstTimer(0, playerEntity);
            }else {
                player.setThirstTickTimer(++time);
            }
        }
    }*/

    // Our main update method which runs every tick.
    public void update() {
        if (hasThirst) {
            Difficulty difficulty = player.level().getDifficulty();
            // Because our max hydration is 10, meaning we have 10 "pieces" of hydration, we divide the max tick period by 10.
            // if (thirstTickTimer >= thirstTickPeriod/10)
            /// simple to figure out bozo
            if (thirstTickTimer >= Math.round(((float) thirstTickPeriod /maxHydration))) {
                thirstTickTimer = 0;
                removeHydration(1);
                if (player instanceof ServerPlayer sPlayer) {
                    HydrationHelper.syncHydrationThirstTickTimer(sPlayer);
                }
            }else {
                if (player.level().getBiome(player.blockPosition()).is(Biomes.DESERT)) {
                    thirstTickTimer += thirstMultiplier*2;
                }else {
                    thirstTickTimer += thirstMultiplier;
                }
            }

            if (this.hydration <= 0) {
                // If our hydration is too low, we start damaging the player every 120 ticks. Which is every 6 seconds
                ++this.dehydrationTimer;
                if (this.dehydrationTimer >= 120) {
                    // Only damage the player if he's not at 1 HP, unless the difficulty is hard.
                    if (player.getHealth() > 10.0F || difficulty == Difficulty.HARD || (player.getHealth() > 1.0F && difficulty == Difficulty.NORMAL)) {
                        player.hurt(player.level().damageSources().starve(), 1);
                    }
                    this.dehydrationTimer = 0;
                }
            }else {
                this.dehydrationTimer = 0;
            }

            if (!player.isCreative() && !player.isSpectator()) {
                if (hydration == 8 && !player.hasEffect(MobEffects.DIG_SPEED)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 400, 0, false, false, false));
                }
                if (hydration == 0 && player.getFoodData().getFoodLevel() == 0 && !player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 2, false, false, false));
                }
            }

            if (player instanceof ServerPlayer sPlayer) {
                HydrationHelper.syncAllHydration(sPlayer);
            }
        }
        if (!hasThirst && player instanceof ServerPlayer sPlayer) {
            HydrationHelper.syncAllHydration(sPlayer);
        }
    }

    public void readNbt(CompoundTag tag) {
        if (tag.contains("balleritemod.hydration_data", 10)) {
            CompoundTag holder = tag.getCompound("balleritemod.hydration_data");
            this.hydration = holder.getInt("hydrationLevel");
            this.thirstTickTimer = holder.getInt("thirstTickTimer");
            this.dehydrationTimer = holder.getInt("dehydrationTimer");
            this.thirstMultiplier = holder.getInt("thirstTickMultiplier");
            this.hasThirst = holder.getBoolean("thirstBool");
            this.debugHudBool = holder.getBoolean("debugHudBool");
        }
    }

    public void writeNbt(CompoundTag tag) {
        CompoundTag myData = new CompoundTag();
        myData.putInt("hydrationLevel", this.hydration);
        myData.putInt("thirstTickTimer", this.thirstTickTimer);
        myData.putFloat("dehydrationTimer", this.dehydrationTimer);
        myData.putInt("thirstTickMultiplier", this.thirstMultiplier);
        myData.putBoolean("thirstBool", this.hasThirst);
        myData.putBoolean("debugHudBool", this.debugHudBool);
        tag.put("balleritemod.hydration_data", myData);
    }

    public int getMaxHydration() {
        return maxHydration;
    }
    public int getHydration() {
        return hydration;
    }
    public int getThirstTickTimer() {
        return thirstTickTimer;
    }
    public int getThirstTickMultiplier() {return thirstMultiplier;}

    public boolean isNotFull() {
        return hydration < maxHydration;
    }
    public boolean getDebugHudBool() {return debugHudBool;}

    public boolean hasThirst() {
        return hasThirst;
    }
    public void setThirstBool(boolean value) {
        hasThirst = value;
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncHydrationBoolean(serverPlayerEntity);
        }
    }

    public void setThirstTickTimer(int value) {
        thirstTickTimer = value;
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncHydrationThirstTickTimer(serverPlayerEntity);
        }
    }

    public void setThirstTickMultiplier(int value) {
        thirstMultiplier = value;
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncHydrationThirstTickMultiplier(serverPlayerEntity);
        }
    }

    public void reset() {
        hydration = maxHydration;
        hasThirst = true;
        thirstTickTimer = 0;
        dehydrationTimer = 0;
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncAllHydration(serverPlayerEntity);
        }
    }

    public void toggleDebug() {
        debugHudBool = !debugHudBool;
        if (player instanceof ServerPlayer serverPlayerEntity) {
            HydrationHelper.syncAllHydration(serverPlayerEntity);
        }
    }

    /**
     *  Client side method , used in the S2C sync packet.
     *  */
    public void setDebugHudBool(boolean b) {
        debugHudBool = b;
    }


    //TODO figure this shit out
    /*public static final DamageSource THIRST = new DamageSource("thirst") {
        @Override
        public float getExhaustion() {
            return 0F;
        }

        @Override
        public boolean isScaledWithDifficulty() {
            return true;
        }
    };*/
}
