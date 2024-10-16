package com.locipro.neoballerite.util;


import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.item.custom.CheeseItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> BALLERITE_BLOCKS = createTag("ballerite_blocks");
        public static final TagKey<Block> INCORRECT_FOR_BALLERITE_TOOL = createTag("incorrect_for_ballerite_tool");
        public static final TagKey<Block> INCORRECT_FOR_LEAD_TOOL = createTag("incorrect_for_lead_tool");
        
        public static final TagKey<Block> WITHERED_BLOCKS = createTag("withered_blocks");
        public static final TagKey<Block> STAR_BLOCKS = createTag("star_blocks");
        public static final TagKey<Block> WITHERED_LOGS = createTag("withered_logs");
        public static final TagKey<Block> STAR_LOGS = createTag("star_logs");

        public static final TagKey<Block> FUNGALS = createTag("fungals");

        public static final TagKey<Block> OVERWORLD_FUNGALS = createTag("overworld_fungals");
        public static final TagKey<Block> NETHER_FUNGALS = createTag("warped_fungals");
        public static final TagKey<Block> END_FUNGALS = createTag("end_fungals");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> BALLERITE_BLOCKS = createTag("ballerite_items");
        public static final TagKey<Item> WITHERED_LOGS = createTag("withered_logs");
        public static final TagKey<Item> STAR_LOGS = createTag("star_logs");

        public static final TagKey<Item> WITHERED_BLOCKS = createTag("withered_blocks");
        public static final TagKey<Item> STAR_BLOCKS = createTag("star_blocks");
        public static final TagKey<Item> CLAYMORES = createTag("claymores");

        public static final TagKey<Item> FUNGALS = createTag("fungals");

        public static final TagKey<Item> OVERWORLD_FUNGALS = createTag("overworld_fungals");
        public static final TagKey<Item> NETHER_FUNGALS = createTag("warped_fungals");
        public static final TagKey<Item> END_FUNGALS = createTag("end_fungals");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MODID, name));
        }
    }
    public static class Common {
        public static final TagKey<Item> INGOTS = createCommonItemTagKey("ingots");
        public static final TagKey<Item> NUGGETS = createCommonItemTagKey("nuggets");
        public static final TagKey<Item> BERRIES = createCommonItemTagKey("fruits/berries");
        public static final TagKey<Item> STRAWBERRIES = createCommonItemTagKey("strawberries");

        public static final TagKey<Block> BERRY_BUSHES = createCommonBlockTagKey("berry_bushes");
    }
    public static TagKey<Item> getFungalTagForCheese(@NotNull CheeseItem cheeseItem) {
        return switch (cheeseItem.getCheeseType()) {
            case OVERWORLD -> Items.OVERWORLD_FUNGALS;
            case NETHER -> Items.NETHER_FUNGALS;
            case END -> Items.END_FUNGALS;
            case null -> {
                NeoBallerite.LOGGER.error("Tried to get an associated fungal tag for a cheese item with an unspecified type. Returning ALL fungals.");
                yield Items.FUNGALS;
            }
        };
    }
    private static TagKey<Block> createCommonBlockTagKey(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
    private static TagKey<Item> createCommonItemTagKey(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

}
