package com.mrh0.createaddition.recipe.charging;

import com.mrh0.createaddition.index.CARecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChargingRecipe implements Recipe<RecipeWrapper> {
    public final ResourceLocation id;
    public Ingredient ingredient;
    public ItemStack output;
    public int energy;

    public static RecipeType<ChargingRecipe> TYPE = new ChargingRecipeType();
    public static RecipeSerializer<?> SERIALIZER = CARecipes.CHARGING.get();

    public ChargingRecipe(ResourceLocation id, Ingredient ingredient, ItemStack output, int energy) {
        this.id = id;
        this.ingredient = ingredient;
        this.output = output;
        this.energy = energy;
    }

    @Override
    public boolean matches(@NotNull RecipeWrapper wrapper, @NotNull Level world) {
        if (ingredient == null)
            return false;
        if (wrapper.getItem(0) == null)
            return false;
        return ingredient.test(wrapper.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeWrapper pContainer, @NotNull RegistryAccess pRegistryAccess) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@Nullable RegistryAccess pRegistryAccess) {
        return output;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return TYPE;
    }

    public int getEnergy() {
        return energy;
    }
}