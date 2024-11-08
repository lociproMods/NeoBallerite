package com.locipro.neoballerite;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LEAD_ARMOR_SET_EFFECTS = BUILDER
            .comment("Whether wearing a full set of lead armor gives bonus status effects or not")
            .define("leadArmorSetEffects", true);
    private static final ModConfigSpec.BooleanValue BALLERITE_ARMOR_SET_EFFECTS = BUILDER
            .comment("Whether wearing a full set of ballerite armor gives bonus status effects or not")
            .define("balleriteArmorSetEffects", true);

    private static final ModConfigSpec.BooleanValue LEAD_SWORD_DOES_POISON = BUILDER
            .define("leadSwordDoesPoison", true);

    private static final ModConfigSpec.BooleanValue BALLERITE_TOOL_PARTICLES = BUILDER
            .comment("Whether using ballerite tools emits particles or not")
            .define("balleriteToolsEmitParticles", true);


    /*private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);
*/
    static final ModConfigSpec SPEC = BUILDER.build();

    /*public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;*/

    public static boolean lead_armor_set_effects;
    public static boolean ballerite_armor_set_effects;
    public static boolean lead_sword_does_poison;
    public static boolean ballerite_tools_emit_particles;

    /*private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }*/

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        /*logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();


        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());*/
        lead_armor_set_effects = LEAD_ARMOR_SET_EFFECTS.get();
        ballerite_armor_set_effects = BALLERITE_ARMOR_SET_EFFECTS.get();
        lead_sword_does_poison = LEAD_SWORD_DOES_POISON.get();
        ballerite_tools_emit_particles = BALLERITE_TOOL_PARTICLES.get();
    }
}
