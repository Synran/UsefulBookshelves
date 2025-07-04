package site.coderan.usefulbookshelf;

import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import site.coderan.usefulbookshelf.block.modblockentities.ModBlockEntities;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;
import site.coderan.usefulbookshelf.gui.modmenus.ModMenus;
import site.coderan.usefulbookshelf.item.ModItems;
import site.coderan.usefulbookshelf.sounds.ModSounds;

@Mod(ModMain.MOD_ID)
public class ModMain {
    public static final String MOD_ID = "usefulbookshelf";

    public ModMain(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEM_REGISTER.register(modEventBus);
        ModBlocks.BLOCK_REGISTER.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_REGISTER.register(modEventBus);
        ModMenus.MENU_REGISTER.register(modEventBus);
        ModSounds.SOUND_REGISTER.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, ModConfigs.SPEC);
    }
}
