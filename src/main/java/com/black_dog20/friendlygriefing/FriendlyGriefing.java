package com.black_dog20.friendlygriefing;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;

@Mod(FriendlyGriefing.MOD_ID)
public class FriendlyGriefing {

    public static final String MOD_ID = "friendlygriefing";

    public FriendlyGriefing(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onMobGriefing(EntityMobGriefingEvent event) {
        Entity entity = event.getEntity();

        if (entity == null || event.isMobGriefingEnabled()) {
            return;
        }

        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());

        event.setCanGrief(Config.FRIENDLY_GRIEFING_MOBS.get().contains(entityId.toString()));
    }
}
