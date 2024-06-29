package com.black_dog20.friendlygriefing;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = FriendlyGriefing.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketHandler {

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlerEvent event) {
		final IPayloadRegistrar registrar = event.registrar(FriendlyGriefing.MOD_ID);
	}
}
