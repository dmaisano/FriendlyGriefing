package com.black_dog20.friendlygriefing;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import java.util.Optional;

@Mod(FriendlyGriefing.MOD_ID)
public class FriendlyGriefing {

    public static final String MOD_ID = "friendlygriefing";

    public FriendlyGriefing(IEventBus event) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
        Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-server.toml"));
        event.addListener(this::registerNetwork);

        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onMobGriefing(EntityMobGriefingEvent event){
        Entity entity = event.getEntity();

        if (entity == null || entity.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            return;
        }

        Optional<String> entityId = Optional.of(entity)
                .map(Entity::getType)
                .map(BuiltInRegistries.ENTITY_TYPE::getKey)
                .map(ResourceLocation::toString);

        if (Config.FRIENDLY_GRIEFING_MOBS.get().contains(entityId.get())) {
            event.setResult(Event.Result.ALLOW);
        } else {
            event.setResult(Event.Result.DENY);
        }
    }

    public void registerNetwork(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(FriendlyGriefing.MOD_ID);
    }
}
