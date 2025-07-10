package site.coderan.usefulbookshelf.block.modblockrenders;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblockentities.ModBlockEntities;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModBlockRenders {
    @SubscribeEvent
    // 注册render并将其绑定到对应的entity
    public static void registerBlockEntityRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.USEFUL_BOOKSHELF_ENTITY.get(),
                UsefulBookshelfBlockEntityRender::new
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.USEFUL_BOOKSHELF_HALF_ENTITY.get(),
                UsefulBookshelfHalfBlockEntityRender::new
        );
    }
}
