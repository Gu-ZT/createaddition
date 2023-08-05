package com.mrh0.createaddition.recipe.rolling;

import com.mrh0.createaddition.compat.jei.RollingMillAssemblySubCategory;
import com.mrh0.createaddition.index.CABlocks;
import com.mrh0.createaddition.index.CARecipes;
import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.sequenced.IAssemblyRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class RollingRecipe extends ProcessingRecipe<RecipeWrapper> implements IAssemblyRecipe {

    public static RecipeType<RollingRecipe> TYPE = new RollingRecipeType();
    public static RecipeSerializer<?> SERIALIZER = CARecipes.ROLLING.get();
    protected final ItemStack output;
    protected final ResourceLocation id;
    protected final Ingredient ingredient;

    protected RollingRecipe(Ingredient ingredient, ItemStack output, ResourceLocation id) {
        super(new RollingRecipeInfo(id, (SequencedAssemblyRollingRecipeSerializer) SERIALIZER, TYPE), new RollingMillRecipeParams(id, ingredient, new ProcessingOutput(output, 1f)));
        this.output = output;
        this.id = id;
        this.ingredient = ingredient;
    }

    public static void register() {
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    @Override
    public boolean matches(RecipeWrapper inv, @NotNull Level worldIn) {
        if (inv.isEmpty())
            return false;
        return ingredient.test(inv.getItem(0));
    }

    @Override
    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    protected int getMaxOutputCount() {
        return 1;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeWrapper inv, @Nullable RegistryAccess registryAccess) {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height > 0;
    }

    @Override
    public @NotNull ItemStack getResultItem(@Nullable RegistryAccess registryAccess) {
        return this.output;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return TYPE;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return this.output;
    }

	@Override
    public Component getDescriptionForAssembly() {
        return Component.translatable("createaddition.recipe.rolling.sequence").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    public void addRequiredMachines(Set<ItemLike> set) {
        set.add(CABlocks.ROLLING_MILL.get());
    }

    @Override
    public void addAssemblyIngredients(List<Ingredient> list) {

    }

    @Override
    public Supplier<Supplier<SequencedAssemblySubCategory>> getJEISubCategory() {
        return () -> RollingMillAssemblySubCategory::new;
    }
}
