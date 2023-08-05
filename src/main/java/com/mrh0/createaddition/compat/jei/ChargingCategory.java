package com.mrh0.createaddition.compat.jei;

import com.mrh0.createaddition.recipe.charging.ChargingRecipe;
import com.mrh0.createaddition.util.Util;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class ChargingCategory extends CARecipeCategory<ChargingRecipe> {

	private final AnimatedTeslaCoil tesla_coil = new AnimatedTeslaCoil();

	public ChargingCategory(Info<ChargingRecipe> info) {
		super(info);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ChargingRecipe recipe, @NotNull IFocusGroup focuses) {
		builder
				.addSlot(RecipeIngredientRole.INPUT, 15, 9)
				.setBackground(getRenderedSlot(), -1, -1)
				.addIngredients(recipe.ingredient);

		builder
				.addSlot(RecipeIngredientRole.OUTPUT, 140, 28)
				.setBackground(getRenderedSlot(), -1, -1)
				.addItemStack(recipe.getResultItem(null));
	}

	@Override
	public void draw(ChargingRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
		AllGuiTextures.JEI_ARROW.render(guiGraphics, 85, 32);
		AllGuiTextures.JEI_DOWN_ARROW.render(guiGraphics, 43, 4);
		tesla_coil.draw(guiGraphics, 48, 27);
		Font font = Minecraft.getInstance().font;
		guiGraphics.drawString(font,Util.format(recipe.energy) + "fe", 86, 9, 4210752);
	}
}