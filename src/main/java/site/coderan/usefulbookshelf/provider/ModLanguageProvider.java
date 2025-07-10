package site.coderan.usefulbookshelf.provider;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, ModMain.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ModBlocks.holders.forEach((holder, descriptionId) -> {
            addBlock(holder, I18n.get(descriptionId).replace(" Planks", "") + " Bookshelf");
        });
        ModBlocks.holdersHalf.forEach((holder, descriptionId) -> {
            addBlock(holder, I18n.get(descriptionId).replace(" Planks", "") + " Half Bookshelf");
        });
        add("usefulbookshelf.menu.useful_bookshelf_inventory", "Bookshelf inventory");
        add("subtitles.usefulbookshelf.bookshelf_sound", "sound of bookshelf");
    }
}
