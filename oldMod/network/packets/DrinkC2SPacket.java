package net.locipro.balleritemod.network.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.locipro.balleritemod.hydration.HydrationHelper;
import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

import static net.locipro.balleritemod.network.ModNetworkHelper.aroundBlock;

public class DrinkC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler,
                               FriendlyByteBuf buf, PacketSender response) {
        //EVERYTHING HERE HAPPENS ONLY ON THE SERVER!

        ServerLevel world = (ServerLevel) player.level();
        Holder<Biome> biome = world.getBiome(player.blockPosition());

        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();


        if (aroundBlock(player, world, Blocks.WATER, 1)) {
            world.playSound(null, player.blockPosition(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                    0.5f, world.random.nextFloat() * 0.5f + 0.9f);

            if (player.blockPosition().getY() < 35) {
                RandomSource random =  RandomSource.create();
                if (random.nextBoolean()) {
                    hydrationManager.addHydration(2);
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1, true, true));
                    player.displayClientMessage(Component.literal("This cave water full of minerals makes you feel strong!")
                            .withStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW))
                            .append(Component.literal(" +2 Hydration, +Haste for 1 minute")
                                    .withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))), true);
                }else {
                    hydrationManager.removeHydration(1);
                    player.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1, true, true));
                    player.displayClientMessage(Component.literal("This cave water made you sick...")
                            .withStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW))
                            .append(Component.literal(" -1 Hydration, +Poison for 10 seconds")
                                    .withStyle(Style.EMPTY.withColor(ChatFormatting.RED))), true);
                }
            }


            else if (biome.is(Biomes.SWAMP)) {
                hydrationManager.removeHydration(1);
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 160, 0, true, true));
                player.displayClientMessage(Component.literal("Drank from nearby swamp water. Ew.")
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.RED))
                        .append(Component.literal(" +1 Hydration, +Poison for 8 seconds")
                                .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED))), true);
            }

            else if (biome.is(Biomes.DESERT)) {

                hydrationManager.addHydration(1);
                player.displayClientMessage(Component.literal("You drink from the desert water, nourishing your dehydration.")
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW))
                        .append(Component.literal(" +2 Hydration")
                                .withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))), true);

            }

            else if (biome.is(Biomes.OCEAN) || biome.is(Biomes.COLD_OCEAN) || biome.is(Biomes.DEEP_COLD_OCEAN)||
                    biome.is(Biomes.DEEP_OCEAN) || biome.is(Biomes.DEEP_FROZEN_OCEAN) || biome.is(Biomes.DEEP_LUKEWARM_OCEAN)||
                    biome.is(Biomes.FROZEN_OCEAN) || biome.is(Biomes.LUKEWARM_OCEAN) || biome.is(Biomes.WARM_OCEAN) || biome.is(Biomes.BEACH))
            {
                hydrationManager.addHydration(1);
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0, true, true));
                player.displayClientMessage(Component.literal("It's salty... You feel thirsty for a short period of time...")
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW))
                        .append(Component.literal(" +1 Hydration, +Fatigue for 5 seconds")
                                .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA))), true);
            }

            else if (biome.is(Biomes.RIVER) || biome.is(Biomes.FROZEN_RIVER)) {
                hydrationManager.addHydration(3);
                player.displayClientMessage(Component.literal("Delicious river water!")
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA))
                        .append(Component.literal(" +3 Hydration")
                                .withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))), true);
            }
            else {
                hydrationManager.addHydration(2);
                player.displayClientMessage(Component.literal("Drank!")
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA))
                        .append(Component.literal(" +2 Hydration")
                                .withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))), true);
            }
        }


        else {
            player.displayClientMessage(Component.literal("No water around, couldn't drink.")
                    .withStyle(Style.EMPTY.withColor(ChatFormatting.RED)), true);
        }

        HydrationHelper.syncHydration(player);

    }


}
