package com.locipro.neoballerite;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = NeoBallerite.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LEAD_ARMOR_SET_EFFECTS = SERVER_BUILDER
            .comment("Whether wearing a full set of lead armor gives bonus status effects or not")
            .define("leadArmorSetEffects", true);
    private static final ModConfigSpec.BooleanValue BALLERITE_ARMOR_SET_EFFECTS = SERVER_BUILDER
            .comment("Whether wearing a full set of ballerite armor gives bonus status effects or not")
            .define("balleriteArmorSetEffects", true);

    private static final ModConfigSpec.BooleanValue LEAD_SWORD_DOES_POISON = SERVER_BUILDER
            .define("leadSwordDoesPoison", true);

    private static final ModConfigSpec.BooleanValue BALLERITE_TOOL_PARTICLES = CLIENT_BUILDER
            .comment("Whether using ballerite tools emits particles or not")
            .define("balleriteToolsEmitParticles", true);


    static final ModConfigSpec SERVER_SPEC = SERVER_BUILDER.build();


    static final ModConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();



    public static boolean lead_armor_set_effects;
    public static boolean ballerite_armor_set_effects;
    public static boolean lead_sword_does_poison;
    public static boolean ballerite_tools_emit_particles;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        if (event.getConfig().getSpec() == SERVER_SPEC) {
            lead_armor_set_effects = LEAD_ARMOR_SET_EFFECTS.get();
            ballerite_armor_set_effects = BALLERITE_ARMOR_SET_EFFECTS.get();
            lead_sword_does_poison = LEAD_SWORD_DOES_POISON.get();
        }
        if (event.getConfig().getSpec() == CLIENT_SPEC) {
            ballerite_tools_emit_particles = BALLERITE_TOOL_PARTICLES.get();
        }

    }
}
