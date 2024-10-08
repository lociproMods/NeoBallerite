package net.locipro.balleritemod.util.data;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public abstract class ModTrades {
    public static void registerCustomTrades() {
        BalleriteMod.LOGGER.info("Registering villager trades for " + BalleriteMod.MOD_ID);

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.TOMATO, 24),
                            new ItemStack(Items.EMERALD, 1),
                            8,2,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.BLUE_BERRIES, 8),
                            new ItemStack(Items.EMERALD, 1),
                            7,2,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.SWEET_POTATO, 24),
                            new ItemStack(Items.EMERALD, 1),
                            10, 2, 0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.TOMATO_SEEDS, 5),
                            5,2,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.SWEET_POTATO, 5),
                            5,2,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.STRAWBERRY, 6),
                            new ItemStack(Items.EMERALD, 1),
                            6,3,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(ModItems.STRAWBERRY_SEEDS, 4),
                            5,2,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.TOMATO_JAM, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16,3,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.SWEET_BERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16,3,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(ModItems.EGGPLANT_SEEDS, 5),
                            5,2,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(ModItems.BLUE_BERRIES, 2),
                            5,2,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.BLUEBERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16,4,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.BLACK_BERRIES, 8),
                            new ItemStack(Items.EMERALD, 1),
                            6,3,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.STRAWBERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 2),
                            16,4,0.02f));
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(ModItems.BLACK_BERRIES, 2),
                            5,3,0.02f));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.BLACKBERRY_JAM, 1),
                            new ItemStack(Items.EMERALD, 2),
                            16,5,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 24),
                            new ItemStack(ModItems.PURPLE_CARROT, 1),
                            1,6,0.02f));
                });




        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 1,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.COMPRESSED_BALLERITE, 4),
                            new ItemStack(Items.EMERALD, 1),
                            10, 3, 0.02f));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 2,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 14),
                            new ItemStack(ModItems.IRON_CLAYMORE, 1),
                            3, 5, 0.02f));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 4,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.LEAD_INGOT, 12),
                            new ItemStack(Items.EMERALD, 2),
                            3, 5, 0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 4,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 32),
                            new ItemStack(ModItems.DIAMOND_CLAYMORE),
                            2, 20, 0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 64),
                            new ItemStack(ModItems.NETHERITE_CLAYMORE),
                            1, 10, 0.02f));
                });
    }
}
