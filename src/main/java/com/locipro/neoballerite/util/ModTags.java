package com.locipro.neoballerite.util;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.locipro.neoballerite.NeoBallerite.MODID;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> BALLERITE_BLOCKS = createTag("ballerite_blocks");


        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> BALLERITE_ITEMS = createTag("ballerite_items");
        public static final TagKey<Item> WITHERED_LOGS = createTag("withered_logs");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MODID, name));
        }
    }

}
