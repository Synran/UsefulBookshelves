package site.coderan.usefulbookshelf;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ACCEPT_STORED_ITEMS = BUILDER
            .comment("Defines which books could be stored in bookshelves\n定义哪些书可以储存到书架中")
            .defineListAllowEmpty("accept_stored_items", new ArrayList<String>(), ModConfigs::validateItemName);

    public static List<? extends String> accept_stored_items;

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(Object obj){
        return obj instanceof String;
    }

    @SubscribeEvent
    static void onChange(final ModConfigEvent event) {
        accept_stored_items = ACCEPT_STORED_ITEMS.get();
    }
}
