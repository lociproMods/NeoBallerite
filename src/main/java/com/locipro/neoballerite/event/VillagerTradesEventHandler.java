package com.locipro.neoballerite.event;

import com.locipro.neoballerite.NeoBallerite;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.Arrays;
import java.util.List;

import static com.locipro.neoballerite.item.ModItems.*;

@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.GAME)
public class VillagerTradesEventHandler {
    @SubscribeEvent
    public static void villagerTradesEvent(VillagerTradesEvent event) {
        VillagerProfession profession = event.getType();
        /* Pretty sure this is very unoptimized, but it looks pretty and only runs on reload. So don't care.
         * Looks ARE more important !
         * Be vain! */

        if (profession == VillagerProfession.ARMORER) {
            // Multiple calls? This could be further optimized.
            // We can have one function that adds trades to multiple levels,
            // therefore reducing the memory complexity of this code,
            // because the function allocates memory for the event trades list.
            addTrade(event, 1,
                    new BasicItemListing(
                            7,
                            new ItemStack(LEAD_HELMET.get(), 1),
                            1,
                            20
                    ),
                    itemForEmeralds(
                            new ItemStack(LEAVES_BOOTS.get(), 1),
                            4,
                            1,
                            20
                    ));
            addTrade(event, 2,
                    new BasicItemListing(
                            9,
                            new ItemStack(BALLERITE_CHESTPLATE.get(), 1),
                            1,
                            25
                    ));
        }

        if (profession == VillagerProfession.TOOLSMITH) {
            addTrade(event, 1,
                    itemForEmeralds(
                            new ItemStack(COMPRESSED_BALLERITE_INGOT.get(), 7), 5,
                            4,
                            12
                    ));
            addTrade(event,  2,
                    new BasicItemListing(
                            7,
                            new ItemStack(LEAD_PICKAXE.get(), 1),
                            1,
                            14
                    ),
                    new BasicItemListing(
                            5,
                            new ItemStack(BALLERITE_HOE.get(), 1),
                            1,
                            15
                    ));
            addTrade(event,  3,
                    new BasicItemListing(
                            11,
                            new ItemStack(BALLERITE_PICKAXE.get(), 1),
                            1,
                            24
                    ),
                    new BasicItemListing(
                            8,
                            new ItemStack(BALLERITE_AXE.get(), 1),
                            1,
                            23
                    ));
        }
        if (profession == VillagerProfession.FARMER) {
            addTrade(event, 1,
                    emeraldsForItem(
                            new ItemStack(TOMATO.get(), 24),
                            1,
                            16,
                            2
                    ),
                    emeraldsForItem(
                            new ItemStack(BLUEBERRIES.get(), 9),
                            1,
                            8,
                            2
                    ),
                    emeraldsForItem(
                            new ItemStack(SWEET_POTATO.get(), 24),
                            1,
                            16,
                            2
                    ),
                    itemForEmeralds(
                            new ItemStack(SWEET_POTATO.get(), 5), 1,
                            6,
                            3
                    ),
                    itemForEmeralds(
                            new ItemStack(UNRIPE_STRAWBERRY.get(), 32),
                            8,
                            4,
                            28
                    ));
            
            addTrade(event, 2,
                    emeraldsForItem(
                            new ItemStack(BLUEBERRIES.get(), 9),
                            1,
                            16,
                            3
                    ),
                    emeraldsForItem(
                            new ItemStack(CORN_COB.get(), 7),
                            2,
                            18,
                            3
                    ),
                    emeraldsForItem(
                            new ItemStack(EGGPLANT.get(), 12),
                            1,
                            16,
                            2
                    ),
                    itemForEmeralds(
                            new ItemStack(CORN_KERNELS.get(), 8),
                            4,
                            8,
                            3
                    ));
            addTrade(event, 3,
                    emeraldsForItem(
                            new ItemStack(STRAWBERRY.get(), 6),
                            2,
                            20,
                            3
                    ),
                    itemForEmeralds(
                            new ItemStack(STRAWBERRY_SEEDS.get(), 9),
                            9,
                            4,
                            3
                    ));

            addTrade(event, 5,
                    itemForEmeralds(
                            new ItemStack(ENCHANTED_DIAMOND_CARROT.get(), 1),
                            42,
                            2,
                            50
                    ));
        }

        /*
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
                factories -> {

                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(TOMATO_JAM, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16,3,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(SWEET_BERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16,3,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(BLUEBERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16,4,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(STRAWBERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 2),
                            16,4,0.02f));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(BLACKBERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 2),
                            16,5,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 24),
                            new ItemStack(PURPLE_CARROT, 1),
                            1,6,0.02f));
                });*/

        if (profession == VillagerProfession.WEAPONSMITH) {
            addTrade(event, 1,
                    itemForEmeralds(
                            new ItemStack(STONE_CLAYMORE.get(), 1),
                            12,
                            1,
                            10
                    ));
            addTrade(event, 2,
                    itemForEmeralds(
                            new ItemStack(IRON_CLAYMORE.get(), 1),
                            14,
                            1,
                            15
                    ));
            addTrade(event, 3,
                    itemForEmeralds(
                            new ItemStack(LEAD_CLAYMORE.get(), 1),
                            15,
                            1,
                            20
                    ));
            addTrade(event, 4,
                    itemForEmeralds(
                            new ItemStack(DIAMOND_CLAYMORE.get(), 1),
                            20,
                            1,
                            15
                    ));
        }

        if (profession == VillagerProfession.CLERIC) {
            addTrade(event, 1,
                    itemForEmeralds(
                            new ItemStack(MILK_CHEESE.get(), 8),
                            12,
                            9,
                            4
                    ));
            addTrade(event, 2,
                    itemForEmeralds(
                            new ItemStack(CHARRED_BALLERITE.get(), 12),
                            12,
                            10,
                            4
                    ));
        }
    }

    private static void addTrade(VillagerTradesEvent event, int level, BasicItemListing ... listings) {
        if (level > 5 || level < 1) {
            throw new IllegalArgumentException(String.format("Villager trade level out of bounds (1 <= level <= 5), can't be %d", level));
        }

        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        Arrays.stream(listings)
                .iterator()
                .forEachRemaining(listing ->
                        trades.get(level).add(listing));

    }
    private static BasicItemListing itemForEmeralds(ItemStack forSale, int baseCost, int maxTrades, int xp) {
        return new BasicItemListing(
                new ItemStack(Items.EMERALD, baseCost),
                forSale,
                maxTrades,
                xp,
                1f);
    }
    private static BasicItemListing emeraldsForItem(ItemStack sold, int emeraldCount, int maxTrades, int xp) {
        return new BasicItemListing(
                sold,
                new ItemStack(Items.EMERALD, emeraldCount),
                maxTrades,
                xp,
                1f);
    }
}
