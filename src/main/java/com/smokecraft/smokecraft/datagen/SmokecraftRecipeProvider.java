package com.smokecraft.smokecraft.datagen;

import com.smokecraft.smokecraft.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class SmokecraftRecipeProvider extends RecipeProvider {

    public SmokecraftRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Cigarette recipe: paper + sugar (placeholder for tobacco)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.CIGARETTE.get())
                .pattern(" S ")
                .pattern(" P ")
                .pattern("   ")
                .define('S', Items.SUGAR) // Placeholder for tobacco
                .define('P', Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(consumer);

        // Alternative cigarette recipe with dried kelp (another tobacco substitute)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.CIGARETTE.get())
                .pattern(" K ")
                .pattern(" P ")
                .pattern("   ")
                .define('K', Items.DRIED_KELP)
                .define('P', Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_dried_kelp", has(Items.DRIED_KELP))
                .save(consumer, "smokecraft:cigarette_kelp");

        // Lighter recipe: flint + iron ingot + iron nugget
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemInit.LIGHTER.get())
                .pattern(" F ")
                .pattern(" I ")
                .pattern(" N ")
                .define('F', Items.FLINT)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy("has_flint", has(Items.FLINT))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer);
    }
}