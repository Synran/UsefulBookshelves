package site.coderan.usefulbookshelf.provider;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.neoforge.common.data.LanguageProvider;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, ModMain.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ModBlocks.holders.forEach((holder, namespace) -> {
            String registeredName = holder.getRegisteredName();
            MutableComponent name = holder.get().getName().;
            int a = 1;
        });
        String s = I18n.get("block.minecraft.acacia_planks");
        //Component.translatable("");
        int a = 1;
    }
}
