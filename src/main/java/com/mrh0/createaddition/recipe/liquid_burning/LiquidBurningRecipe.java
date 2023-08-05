package com.mrh0.createaddition.recipe.liquid_burning;

import com.mrh0.createaddition.index.CARecipes;
import com.mrh0.createaddition.recipe.FluidRecipeWrapper;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LiquidBurningRecipe implements Recipe<FluidRecipeWrapper> {

	protected final ResourceLocation id;
	protected FluidIngredient fluidIngredients;
	protected int burnTime;
	protected boolean superheated;
	
	public static RecipeType<LiquidBurningRecipe> TYPE = new LiquidBurningRecipeType();
	public static RecipeSerializer<?> SERIALIZER = CARecipes.LIQUID_BURNING.get();
	public LiquidBurningRecipe(ResourceLocation id, FluidIngredient fluid, int burnTime, boolean superheated) {
		this.id = id;
		this.fluidIngredients = fluid;
		this.burnTime = burnTime;
		this.superheated = superheated;
	}

	@Override
	public boolean matches(@NotNull FluidRecipeWrapper wrapper, @NotNull Level world) {
		if(fluidIngredients == null)
			return false;
		if(wrapper.fluid == null)
			return false;
		return fluidIngredients.test(wrapper.fluid);
	}

	@Override
	public @NotNull ItemStack assemble(@NotNull FluidRecipeWrapper pContainer, @NotNull RegistryAccess pRegistryAccess) {
		return new ItemStack(Items.AIR);
	}

	@Override
	public boolean canCraftInDimensions(int w, int h) {
		return true;
	}

	@Override
	public @NotNull ItemStack getResultItem(@Nullable RegistryAccess pRegistryAccess) {
		return new ItemStack(Items.AIR);
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

	public FluidIngredient getFluidIngredient() {
		return fluidIngredients;
	}
	
	public int getBurnTime() {
		return this.burnTime;
	}
	
	public boolean isSuperheated() {
		return this.superheated;
	}
}