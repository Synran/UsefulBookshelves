package site.coderan.usefulbookshelf.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import site.coderan.usefulbookshelf.block.modblocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ModBlocks.holders.forEach((holder, descriptionId) -> {
            // get slab
            Item slab = BuiltInRegistries.ITEM.stream().filter(item -> {
                return item.getDescriptionId().equals(descriptionId.replace("planks", "slab"));
            }).findFirst().get();

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, holder.get())
                    .pattern("XXX")
                    .pattern("#X#")
                    .pattern("XXX")
                    .define('X', slab)
                    .define('#', Items.STICK)
                    .unlockedBy("has_spruce", has(slab))
                    .save(output);
        });
        ModBlocks.holdersHalf.forEach((holder, descriptionId) -> {
            // get slab
            Item slab = BuiltInRegistries.ITEM.stream().filter(item -> {
                return item.getDescriptionId().equals(descriptionId.replace("planks", "slab"));
            }).findFirst().get();

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, holder.get())
                    .pattern(" XX")
                    .pattern(" X#")
                    .pattern(" XX")
                    .define('X', slab)
                    .define('#', Items.STICK)
                    .unlockedBy("has_spruce", has(slab))
                    .save(output);
        });
    }
}
