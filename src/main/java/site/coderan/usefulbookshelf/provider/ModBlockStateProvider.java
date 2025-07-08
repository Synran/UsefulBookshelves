package site.coderan.usefulbookshelf.provider;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;
import site.coderan.usefulbookshelf.block.modblocks.UsefulBookshelfBlock;
import site.coderan.usefulbookshelf.block.modblocks.UsefulBookshelfHalfBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModMain.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.holders.forEach((holder, descriptionId) -> {
            // plank对应的modId
            String[] split = descriptionId.split("\\.");
            String modId = split[1];
            // 初始化builder
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            VariantBlockStateBuilder variantBuilder = getVariantBuilder(holder.get());
            // 生成blockState
            variantBuilder.forAllStates(blockState -> {
                String bookshelfRegisteredName = holder.getRegisteredName();
                String bookshelfName = bookshelfRegisteredName.split(":")[1];
                String texturePart = bookshelfName.replace("useful_bookshelf_", "");
                if (blockState.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.NORTH) {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .build();
                } else if (blockState.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.SOUTH) {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .rotationY(180)
                            .build();
                } else if (blockState.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.WEST) {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .rotationY(270)
                            .build();
                } else {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .rotationY(90)
                            .build();
                }
            });

            // 生成itemModel
            simpleBlockItem(
                    holder.get(),
                    builder.buildLast().model
            );
        });
        ModBlocks.holdersHalf.forEach((holder, descriptionId) -> {
            // plank对应的modId
            String[] split = descriptionId.split("\\.");
            String modId = split[1];
            // 初始化builder
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            VariantBlockStateBuilder variantBuilder = getVariantBuilder(holder.get());
            // 生成blockState
            variantBuilder.forAllStates(blockState -> {
                String bookshelfRegisteredName = holder.getRegisteredName();
                String bookshelfName = bookshelfRegisteredName.split(":")[1];
                String texturePart = bookshelfName.replace("useful_bookshelf_half_", "");
                if (blockState.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.NORTH) {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_half_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .build();
                } else if (blockState.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.SOUTH) {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_half_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .rotationY(180)
                            .build();
                } else if (blockState.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.WEST) {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_half_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .rotationY(270)
                            .build();
                } else {
                    return  builder.modelFile(models().withExistingParent(holder.getRegisteredName().split(":")[1] + "_model", modLoc("block/bookshelf_shelf_half_part"))
                                    .texture("particle", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("end", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                                    .texture("2", ResourceLocation.fromNamespaceAndPath(modId, "block/" + texturePart))
                            )
                            .rotationY(90)
                            .build();
                }
            });

            // 生成itemModel
            simpleBlockItem(
                    holder.get(),
                    builder.buildLast().model
            );
        });
    }

}
