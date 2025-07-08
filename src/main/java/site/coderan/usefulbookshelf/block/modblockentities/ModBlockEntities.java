package site.coderan.usefulbookshelf.block.modblockentities;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;
import site.coderan.usefulbookshelf.block.modblocks.UsefulBookshelfBlock;
import site.coderan.usefulbookshelf.block.modblocks.UsefulBookshelfHalfBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ModBlockEntities {
    // 方块实体注册器
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            ModMain.MOD_ID
    );
    // 注册useful_bookshelf的方块实体
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UsefulBookshelfBlockEntity>> USEFUL_BOOKSHELF_ENTITY = BLOCK_ENTITY_REGISTER.register(
            "useful_bookshelf_entity",
            () -> {
                List<UsefulBookshelfBlock> usefulBookshelfBlocks = new ArrayList<>();
                ModBlocks.holders.forEach((holder, descriptionId) -> {
                    usefulBookshelfBlocks.add(holder.get());
                });
                return new BlockEntityType<>(
                        UsefulBookshelfBlockEntity::new,
                        ImmutableSet.copyOf(usefulBookshelfBlocks.iterator()),
                        null
                );
            }
    );
    // 注册useful_bookshelf_half的方块实体
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UsefulBookshelfHalfBlockEntity>> USEFUL_BOOKSHELF_HALF_ENTITY = BLOCK_ENTITY_REGISTER.register(
            "useful_bookshelf_half_entity",
            () -> {
                List<UsefulBookshelfHalfBlock> usefulBookshelfHalfBlocks = new ArrayList<>();
                ModBlocks.holdersHalf.forEach((holder, descriptionId) -> {
                    usefulBookshelfHalfBlocks.add(holder.get());
                });
                return new BlockEntityType<>(
                        UsefulBookshelfHalfBlockEntity::new,
                        ImmutableSet.copyOf(usefulBookshelfHalfBlocks.iterator()),
                        null
                );
            }
    );

}
