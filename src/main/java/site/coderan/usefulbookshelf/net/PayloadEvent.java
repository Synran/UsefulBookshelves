package site.coderan.usefulbookshelf.net;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import site.coderan.usefulbookshelf.ModMain;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PayloadEvent {
    @SubscribeEvent
    public static void registerPayloadHandler(final RegisterPayloadHandlersEvent event){

        final PayloadRegistrar registrar = event.registrar("1");
        registrar.executesOn(HandlerThread.NETWORK);
        registrar.playBidirectional(
                ConfigChangeData.TYPE,
                ConfigChangeData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleDataOnNetwork,
                        ServerPayloadHandler::handleDataOnNetwork
                )
        );
    }
}
