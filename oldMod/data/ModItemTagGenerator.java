package net.locipro.balleritemod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.locipro.balleritemod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import java.util.concurrent.CompletableFuture;
import static net.locipro.balleritemod.BalleriteMod.MOD_ID;
import static net.locipro.balleritemod.data.ModBlockTagGenerator.*;
import static net.locipro.balleritemod.item.ModItems.*;


/**
 * <i>Register your implementation using FabricDataGenerator.Pack.addProvider in a net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint.</i> <br>
 * Declare your tags, then in configure() use <i>getOrCreateTagBuilder(yourDeclaredTagHere).add(reverseLookup(itemThatGetsAddedToTag)</i><br>
 * For copying block tags into item tags, declare your item tag and do <i>copy(BLOCK_TAG, ITEM_TAG)</i><br>
 * To add to vanilla tags, just use <i>ItemTags.ITEM_TAG</i> as the argument in getOrCreateTagBuilder
 */
public class ModItemTagGenerator extends FabricTagProvider.ItemTagProvider {


    public ModItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture,BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    public static final TagKey<Item> BALLERITE_BLOCKS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "ballerite_blocks"));
    public static final TagKey<Item> WITHERED_LOGS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "withered_logs"));
    public static final TagKey<Item> STAR_LOGS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "star_logs"));
    public static final TagKey<Item> STAR_BLOCKS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "star_blocks"));
    public static final TagKey<Item> WITHERED_BLOCKS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "withered_blocks"));
    public static final TagKey<Item> WOODEN_STAIRS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_stairs"));
    public static final TagKey<Item> WOODEN_SLABS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_slabs"));
    public static final TagKey<Item> WOODEN_FENCES_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_fences"));
    public static final TagKey<Item> WOODEN_BUTTONS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_buttons"));
    public static final TagKey<Item> WOODEN_DOORS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_doors"));
    public static final TagKey<Item> WOODEN_TRAPDOORS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_trapdoors"));
    public static final TagKey<Item> WOODEN_PRESSURE_PLATES_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_pressure_plates"));




    public static final TagKey<Item> SWORDS = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "swords"));
    public static final TagKey<Item> PICKAXES = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "pickaxes"));
    public static final TagKey<Item> AXES = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "axes"));
    public static final TagKey<Item> SHOVELS = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "shovels"));
    public static final TagKey<Item> HOES = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "hoes"));
    public static final TagKey<Item> CLAYMORES = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "claymores"));
    public static final TagKey<Item> HORSE_ARMOR = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "horse_armor"));



    @Override
    protected void addTags(HolderLookup.Provider arg) {
        //region TAG COPYING
        copy(BALLERITE_BLOCKS, BALLERITE_BLOCKS_ITEM);

        copy(WITHERED_LOGS, WITHERED_LOGS_ITEM);
        copy(STAR_LOGS, STAR_LOGS_ITEM);

        copy(WITHERED_BLOCKS, WITHERED_BLOCKS_ITEM);
        copy(STAR_BLOCKS, STAR_BLOCKS_ITEM);

        copy(WOODEN_BUTTONS, WOODEN_BUTTONS_ITEM);
        copy(WOODEN_PRESSURE_PLATES, WOODEN_PRESSURE_PLATES_ITEM);
        copy(WOODEN_DOORS, WOODEN_DOORS_ITEM);
        copy(WOODEN_FENCES, WOODEN_FENCES_ITEM);
        copy(WOODEN_SLABS, WOODEN_SLABS_ITEM);
        copy(WOODEN_TRAPDOORS, WOODEN_TRAPDOORS_ITEM);
        copy(WOODEN_STAIRS, WOODEN_STAIRS_ITEM);
        //endregion
        //region MOD-TAGS

        tag(SWORDS)
                .add(reverseLookup(BALLERITE_SWORD))
                .add(reverseLookup(LEAD_SWORD));
        tag(PICKAXES)
                .add(reverseLookup(BALLERITE_PICKAXE))
                .add(reverseLookup(LEAD_PICKAXE));
        tag(AXES)
                .add(reverseLookup(BALLERITE_AXE))
                .add(reverseLookup(LEAD_AXE));
        tag(SHOVELS)
                .add(reverseLookup(BALLERITE_SHOVEL))
                .add(reverseLookup(LEAD_SHOVEL));
        tag(HOES)
                .add(reverseLookup(BALLERITE_HOE))
                .add(reverseLookup(LEAD_HOE));
        tag(CLAYMORES)
                .add(reverseLookup(WOODEN_CLAYMORE))
                .add(reverseLookup(STONE_CLAYMORE))
                .add(reverseLookup(IRON_CLAYMORE))
                .add(reverseLookup(LEAD_CLAYMORE))
                .add(reverseLookup(GOLD_CLAYMORE))
                .add(reverseLookup(DIAMOND_CLAYMORE))
                .add(reverseLookup(NETHERITE_CLAYMORE))
                .add(reverseLookup(BALLERITE_CLAYMORE));

        tag(HORSE_ARMOR)
                .add(reverseLookup(BALLERITE_HORSE_ARMOR));
        //endregion
        //region VANILLA-TAGS
        tag(ItemTags.AXES)
                .addTag(AXES);
        tag(ItemTags.PICKAXES)
                .addTag(PICKAXES);
        tag(ItemTags.SHOVELS)
                .addTag(SHOVELS);
        tag(ItemTags.SWORDS)
                .addTag(SWORDS);
        tag(ItemTags.HOES)
                .addTag(HOES);

        tag(ItemTags.PLANKS)
                .add(reverseLookup(ModBlocks.WITHERED_PLANKS.asItem()))
                .add(reverseLookup(ModBlocks.STAR_PLANKS.asItem()));

        tag(ItemTags.LOGS)
                .addTag(STAR_LOGS_ITEM)
                        .addTag(WITHERED_LOGS_ITEM);

        tag(ItemTags.WOODEN_BUTTONS)
                .addTag(WOODEN_BUTTONS_ITEM);
        tag(ItemTags.WOODEN_DOORS)
                .addTag(WOODEN_DOORS_ITEM);
        tag(ItemTags.WOODEN_FENCES)
                .addTag(WOODEN_FENCES_ITEM);
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .addTag(WOODEN_PRESSURE_PLATES_ITEM);
        tag(ItemTags.WOODEN_SLABS)
                .addTag(WOODEN_SLABS_ITEM);
        tag(ItemTags.WOODEN_STAIRS)
                .addTag(WOODEN_STAIRS_ITEM);
        tag(ItemTags.WOODEN_TRAPDOORS)
                .addTag(WOODEN_TRAPDOORS_ITEM);


        //endregion


    }
}
