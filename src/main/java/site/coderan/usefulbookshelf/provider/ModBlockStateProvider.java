package site.coderan.usefulbookshelf.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
        UsefulBookshelfBlock block = ModBlocks.TEST_BLOCK.get();
        BlockModelBuilder testBlockModel = models().withExistingParent("test_block_model", modLoc("block/bookshelf_shelf_part"));
        testBlockModel.texture("particle", mcLoc("block/acacia_planks"));
        testBlockModel.texture("end", mcLoc("block/acacia_planks"));
        testBlockModel.texture("2", mcLoc("block/acacia_planks"));
        simpleBlock(block, testBlockModel);
        simpleBlockItem(
                block,
                testBlockModel
        );
    }
}
