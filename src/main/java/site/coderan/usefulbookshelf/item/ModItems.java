package site.coderan.usefulbookshelf.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;

public class ModItems {
    // 模组物品注册器
    public static final DeferredRegister.Items ITEM_REGISTER = DeferredRegister.createItems(
            ModMain.MOD_ID
    );

    // 注册useful_bookshelf
    public static final DeferredHolder<Item, BlockItem> USEFUL_BOOKSHELF = ITEM_REGISTER.registerSimpleBlockItem(
            "useful_bookshelf",
            ModBlocks.USEFUL_BOOKSHELF,
            new Item.Properties()
    );
    // 注册useful_bookshelf_half
    public static final DeferredHolder<Item, BlockItem> USEFUL_BOOKSHELF_HALF = ITEM_REGISTER.registerSimpleBlockItem(
            "useful_bookshelf_half",
            ModBlocks.USEFUL_BOOKSHELF_HALF,
            new Item.Properties()
    );
    // 测试provider的Item
    public static final DeferredHolder<Item, BlockItem> TEST_BLOCK = ITEM_REGISTER.registerSimpleBlockItem(
            "test_block",
            ModBlocks.TEST_BLOCK,
            new Item.Properties()
    );
}
