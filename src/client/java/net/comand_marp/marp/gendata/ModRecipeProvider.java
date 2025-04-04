package net.comand_marp.marp.gendata;

import net.comand_marp.marp.block.ModBlocks;
import net.comand_marp.marp.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider  {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                List<ItemConvertible> ORE_SMELTABLES = List.of(ModItems.COPPER_ORE, ModBlocks.COPPER_ORE_BLOCK);

                offerSmelting(ORE_SMELTABLES, RecipeCategory.MISC, ModItems.COPPER_INGOT, 0.25f, 200, "pink_garnet");
                offerBlasting(ORE_SMELTABLES, RecipeCategory.MISC, ModItems.COPPER_INGOT, 0.25f, 100, "pink_garnet");

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.HAY, RecipeCategory.DECORATIONS, ModBlocks.HAY_BLOCK);

                createShaped(RecipeCategory.MISC, ModBlocks.GRASS_BLOCK)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .input('R', ModItems.GRASS)
                        .criterion(hasItem(ModItems.GRASS), conditionsFromItem(ModItems.GRASS))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.GRASS, 9)
                        .input(ModBlocks.GRASS_BLOCK)
                        .criterion(hasItem(ModBlocks.GRASS_BLOCK), conditionsFromItem(ModBlocks.GRASS_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.CHISEL, 1)
                        .pattern(" M ")
                        .pattern(" R ")
                        .pattern("   ")
                        .input('M', ModItems.COPPER_INGOT)
                        .input('R', Items.STICK)
                        .criterion(hasItem(ModItems.CHISEL), conditionsFromItem(ModItems.CHISEL))
                        .offerTo(exporter);

            }
        };
    }

    @Override
    public String getName() {
        return "Mod Recipe Provider";
    }
}
