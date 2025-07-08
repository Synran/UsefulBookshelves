package site.coderan.usefulbookshelf.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;
import site.coderan.usefulbookshelf.block.modblocks.UsefulBookshelfBlock;
import site.coderan.usefulbookshelf.block.modblocks.UsefulBookshelfHalfBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModItems {
    // 模组物品注册器
    public static final DeferredRegister.Items ITEM_REGISTER = DeferredRegister.createItems(
            ModMain.MOD_ID
    );

//    // 注册useful_bookshelf
//    public static final DeferredHolder<Item, BlockItem> USEFUL_BOOKSHELF = ITEM_REGISTER.registerSimpleBlockItem(
//            "useful_bookshelf",
//            ModBlocks.USEFUL_BOOKSHELF,
//            new Item.Properties()
//    );
//    // 注册useful_bookshelf_half
//    public static final DeferredHolder<Item, BlockItem> USEFUL_BOOKSHELF_HALF = ITEM_REGISTER.registerSimpleBlockItem(
//            "useful_bookshelf_half",
//            ModBlocks.USEFUL_BOOKSHELF_HALF,
//            new Item.Properties()
//    );

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
