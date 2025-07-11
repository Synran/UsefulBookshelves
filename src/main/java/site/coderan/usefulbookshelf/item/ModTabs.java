package site.coderan.usefulbookshelf.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;

import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB_REGISTER = DeferredRegister.create(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ModMain.MOD_ID
    );
    public static final Supplier<CreativeModeTab> MOD_TAB_BOOKSHELF = CREATIVE_MOD_TAB_REGISTER.register("example", () -> CreativeModeTab.builder()
            //Set the title of the tab. Don't forget to add a translation!
            .title(Component.translatable("itemGroup." + ModMain.MOD_ID + ".modTabBookshelf"))
            //Set the icon of the tab.
            .icon(() -> new ItemStack(ModBlocks.holders.entrySet().stream().findFirst().get().getKey().get()))
            //Add your items to the tab.
            .displayItems((params, output) -> {
                ModBlocks.holders.forEach((holder, descriptionId) -> {
                    output.accept(holder.get());
                });
                ModBlocks.holdersHalf.forEach((holder, descriptionId) -> {
                    output.accept(holder.get());
                });
            })
            .build()
    );
}
