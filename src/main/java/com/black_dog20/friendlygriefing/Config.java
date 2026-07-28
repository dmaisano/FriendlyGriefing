package com.black_dog20.friendlygriefing;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Arrays;
import java.util.List;

@EventBusSubscriber(modid = FriendlyGriefing.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {

    public static final String CATEGORY_GENERAL = "general";

    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec SERVER_CONFIG;

    public static ModConfigSpec.ConfigValue<List<? extends String>> FRIENDLY_GRIEFING_MOBS;

    static {

        SERVER_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        FRIENDLY_GRIEFING_MOBS = SERVER_BUILDER.comment("Mob ids to allow griefing", "Ids have the pattern \"minecraft/mod:entity\"", "Default is \"minecraft:villager\"")
                .defineList("friendlyGriefing", Arrays.asList("minecraft:villager"), String.class::isInstance);
        SERVER_BUILDER.pop();
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) {
    }
}
