package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.item.NeoJams;
import com.locipro.neoballerite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import static com.locipro.neoballerite.NeoBallerite.MODID;
import static com.locipro.neoballerite.item.ModItems.*;
import static com.locipro.neoballerite.block.ModBlocks.*;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.BALLERITE_BLOCKS)
                .add(RAW_BALLERITE.get())
                .add(COOKED_BALLERITE.get())
                .add(CHARRED_BALLERITE.get())
                .add(COMPRESSED_BALLERITE_INGOT.get());

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(EGGPLANT_SEEDS.get())
                .add(TOMATO_SEEDS.get())
                .add(CORN_KERNELS.get());

        
        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(WOODEN_CLAYMORE.get())
                .add(STONE_CLAYMORE.get())
                .add(IRON_CLAYMORE.get())
                .add(LEAD_CLAYMORE.get())
                .add(GOLD_CLAYMORE.get())
                .add(DIAMOND_CLAYMORE.get())
                .add(BALLERITE_CLAYMORE.get())
                .add(NETHERITE_CLAYMORE.get());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(WOODEN_CLAYMORE.get())
                .add(STONE_CLAYMORE.get())
                .add(IRON_CLAYMORE.get())
                .add(LEAD_CLAYMORE.get())
                .add(GOLD_CLAYMORE.get())
                .add(DIAMOND_CLAYMORE.get())
                .add(BALLERITE_CLAYMORE.get())
                .add(NETHERITE_CLAYMORE.get());

        tag(ItemTags.SWORDS)
                .add(BALLERITE_SWORD.get())
                .add(LEAD_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(BALLERITE_PICKAXE.get())
                .add(LEAD_PICKAXE.get());
        
        tag(ItemTags.AXES)
                .add(BALLERITE_AXE.get())
                .add(LEAD_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(BALLERITE_SHOVEL.get())
                .add(LEAD_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(BALLERITE_HOE.get())
                .add(LEAD_HOE.get());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(BALLERITE_HELMET.get())
                .add(BALLERITE_CHESTPLATE.get())
                .add(BALLERITE_LEGGINGS.get())
                .add(BALLERITE_BOOTS.get())
                .add(LEAD_HELMET.get())
                .add(LEAD_CHESTPLATE.get())
                .add(LEAD_LEGGINGS.get())
                .add(LEAD_BOOTS.get());
        tag(ItemTags.HEAD_ARMOR)
                .add(BALLERITE_HELMET.get())
                .add(LEAD_HELMET.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(BALLERITE_CHESTPLATE.get())
                .add(LEAD_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR)
                .add(BALLERITE_LEGGINGS.get())
                .add(LEAD_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(BALLERITE_BOOTS.get())
                .add(LEAD_BOOTS.get())
                .add(LEAVES_BOOTS.get());



        
        tag(ItemTags.PLANKS)
                .add(WITHERED_PLANKS.asItem())
                .add(STAR_PLANKS.asItem());


        copy(ModTags.Blocks.WITHERED_LOGS, ModTags.Items.WITHERED_LOGS);
        copy(ModTags.Blocks.STAR_LOGS, ModTags.Items.STAR_LOGS);

        tag(ItemTags.LOGS)
                .addTags(ModTags.Items.WITHERED_LOGS, ModTags.Items.STAR_LOGS);

        tag(ItemTags.LEAVES)
                .add(WITHERED_LEAVES.get().asItem())
                .add(STAR_LEAVES.get().asItem());

        tag(ItemTags.SAPLINGS)
                .add(WITHERED_SAPLING.get().asItem())
                .add(STAR_SAPLING.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .add(WITHERED_LOG.get().asItem())
                .add(STRIPPED_WITHERED_LOG.get().asItem())
                .add(WITHERED_WOOD.get().asItem())
                .add(STRIPPED_WITHERED_WOOD.get().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(WITHERED_FENCE.get().asItem())
                .add(STAR_FENCE.get().asItem());
        tag(ItemTags.FENCE_GATES)
                .add(WITHERED_FENCE_GATE.get().asItem())
                .add(STAR_FENCE_GATE.get().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(WITHERED_DOOR.get().asItem())
                .add(STAR_DOOR.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(WITHERED_TRAPDOOR.get().asItem())
                .add(STAR_TRAPDOOR.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(WITHERED_PRESSURE_PLATE.get().asItem())
                .add(STAR_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(WITHERED_BUTTON.get().asItem())
                .add(STAR_DOOR.get().asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(WITHERED_SLAB.get().asItem())
                .add(STAR_SLAB.get().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(WITHERED_STAIRS.get().asItem())
                .add(STAR_STAIRS.get().asItem());




        tag(ItemTags.HORSE_FOOD)
                .add(SWEET_POTATO.get(),CORN_COB.get());
        tag(ItemTags.HORSE_TEMPT_ITEMS)
                .add(SWEET_POTATO.get());
        tag(ItemTags.FOX_FOOD)
                .add(BLUEBERRIES.get(), BLACKBERRIES.get(), STRAWBERRY.get());
        tag(ItemTags.CHICKEN_FOOD)
                .add(EGGPLANT_SEEDS.get(),
                        TOMATO_SEEDS.get(),
                        STRAWBERRY_SEEDS.get(),
                        CORN_KERNELS.get());
        tag(ItemTags.PIG_FOOD)
                .add(TOMATO.get(), EGGPLANT.get(), SWEET_POTATO.get());





        copy(ModTags.Blocks.WITHERED_BLOCKS, ModTags.Items.WITHERED_BLOCKS);
        copy(ModTags.Blocks.STAR_BLOCKS, ModTags.Items.STAR_BLOCKS);

        tag(ModTags.Items.CLAYMORES)
                .add(WOODEN_CLAYMORE.get())
                .add(STONE_CLAYMORE.get())
                .add(IRON_CLAYMORE.get())
                .add(LEAD_CLAYMORE.get())
                .add(GOLD_CLAYMORE.get())
                .add(DIAMOND_CLAYMORE.get())
                .add(BALLERITE_CLAYMORE.get());

        copy(ModTags.Blocks.OVERWORLD_FUNGALS, ModTags.Items.OVERWORLD_FUNGALS);
        copy(ModTags.Blocks.NETHER_FUNGALS, ModTags.Items.NETHER_FUNGALS);
        copy(ModTags.Blocks.END_FUNGALS, ModTags.Items.END_FUNGALS);

        tag(ModTags.Items.OVERWORLD_FUNGALS)
                .add(Items.ROTTEN_FLESH);

        tag(ModTags.Items.FUNGALS)
                .addTags(ModTags.Items.OVERWORLD_FUNGALS, ModTags.Items.NETHER_FUNGALS, ModTags.Items.END_FUNGALS);

        tag(ModTags.Items.KNIVES)
                .add(KNIFE.get())
                .add(DIAMOND_KNIFE.get());

        tag(ModTags.Items.POTATOES)
                .add(SWEET_POTATO.get())
                .add(Items.POTATO)
                .add(Items.BAKED_POTATO)
                .add(BAKED_SWEET_POTATO.get());


        NeoJams.JAMS.iterator().forEachRemaining((item) ->
                tag(ModTags.Items.JAMS)
                        .add(item.get()));




        tag(Tags.Items.INGOTS)
                .add(LEAD_INGOT.get())
                .add(COMPRESSED_BALLERITE_INGOT.get());
        tag(Tags.Items.NUGGETS)
                .add(LEAD_NUGGET.get());

        tag(ModTags.Common.STRAWBERRIES)
                .add(STRAWBERRY.get())
                .add(UNRIPE_STRAWBERRY.get());

        tag(Tags.Items.FOODS_BERRY)
                .add(STRAWBERRY.get())
                .add(UNRIPE_STRAWBERRY.get())
                .add(BLUEBERRIES.get())
                .add(BLACKBERRIES.get());

        tag(ModTags.Common.CARROTS)
                .add(IRON_CARROT.get())
                .add(DIAMOND_CARROT.get())
                .add(ENCHANTED_DIAMOND_CARROT.get());

        tag(Tags.Items.RAW_MATERIALS)
                .add(RAW_LEAD.get());


    }
}
