package site.coderan.usefulbookshelf.block.modblocks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.coderan.usefulbookshelf.ModMain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ModBlocks {
    public static Map<DeferredHolder<Block, UsefulBookshelfBlock>, String> holders = new HashMap<>();
    public static Map<DeferredHolder<Block, UsefulBookshelfHalfBlock>, String> holdersHalf = new HashMap<>();
    // 模组方块注册器
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(
            BuiltInRegistries.BLOCK,
            ModMain.MOD_ID
    );

    static {
        Stream<Block> blockStream = BuiltInRegistries.BLOCK.stream().filter(block -> {
            return block.getDescriptionId().contains("planks");
        });
        List<Block> list = blockStream.toList();
        for (Block block : list) {
            String descriptionId = block.getDescriptionId();
            String[] split = descriptionId.split("\\.");
            String material = split[split.length - 1];
            holders.put(BLOCK_REGISTER.register(
                            "useful_bookshelf_" + material,
                            () -> new UsefulBookshelfBlock(BlockBehaviour.Properties.of()
                                    .noOcclusion()
                                    .sound(SoundType.WOOD)
                                    .strength(2.0F,3.0F)
                                    .ignitedByLava()
                                    .pushReaction(PushReaction.NORMAL)
                            )
                        ), descriptionId);
            holdersHalf.put(BLOCK_REGISTER.register(
                            "useful_bookshelf_half_" + material,
                            () -> new UsefulBookshelfHalfBlock(BlockBehaviour.Properties.of()
                                    .noOcclusion()
                                    .sound(SoundType.WOOD)
                                    .strength(2.0F,3.0F)
                                    .ignitedByLava()
                                    .pushReaction(PushReaction.NORMAL)
                            )
                        ), descriptionId);
        }
    }
}
