package site.coderan.usefulbookshelf.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;

public class ModItems {
    // 模组物品注册器
    public static final DeferredRegister.Items ITEM_REGISTER = DeferredRegister.createItems(
            ModMain.MOD_ID
    );

    static {
        ModBlocks.holders.forEach((holder, descriptionId) -> {
            ITEM_REGISTER.registerSimpleBlockItem(
                    holder.getRegisteredName().split(":")[1],
                    holder,
                    new Item.Properties()
            );
        });

        ModBlocks.holdersHalf.forEach((holder, descriptionId) -> {
            ITEM_REGISTER.registerSimpleBlockItem(
                    holder.getRegisteredName().split(":")[1],
                    holder,
                    new Item.Properties()
            );
        });
    }
}
