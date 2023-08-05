package com.mrh0.createaddition.compat.jei;

import com.mrh0.createaddition.index.CAItems;
import com.mrh0.createaddition.recipe.liquid_burning.LiquidBurningRecipe;
import com.mrh0.createaddition.util.ClientMinecraftWrapper;
import com.simibubi.create.compat.jei.category.animations.AnimatedBlazeBurner;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.utility.Lang;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class LiquidBurningCategory extends CARecipeCategory<LiquidBurningRecipe> {

    private final AnimatedBlazeBurner heater = new AnimatedBlazeBurner();

    public LiquidBurningCategory(Info<LiquidBurningRecipe> info) {
        super(info);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, LiquidBurningRecipe recipe, @NotNull IFocusGroup focuses) {
        List<ItemStack> buckets = recipe.getFluidIngredient().getMatchingFluidStacks().stream()
                .filter(Objects::nonNull)
                .map((e) -> new ItemStack(e.getFluid().getBucket()))
                .toList();
        builder
                .addSlot(RecipeIngredientRole.INPUT, getBackground().getWidth() / 2 - 56, 3)
                .setBackground(getRenderedSlot(), -1, -1)
                .addItemStack(new ItemStack(CAItems.STRAW.get()));
        builder
                .addSlot(RecipeIngredientRole.INPUT, getBackground().getWidth() / 2 - 36, 3)
                .setBackground(getRenderedSlot(), -1, -1)
                .addItemStacks(buckets);
        builder
                .addSlot(RecipeIngredientRole.INPUT, getBackground().getWidth() / 2 - 16, 3)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(ForgeTypes.FLUID_STACK, withImprovedVisibility(recipe.getFluidIngredient().getMatchingFluidStacks()))
                .addTooltipCallback(addFluidTooltip(recipe.getFluidIngredient().getRequiredAmount()));
    }

    @Override
    public void draw(LiquidBurningRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Font font = ClientMinecraftWrapper.getFont();
        guiGraphics.drawString(font, formatTime(recipe.getBurnTime()), getBackground().getWidth() / 2 + 48, 86 - 50, 4210752);
        HeatCondition requiredHeat = recipe.isSuperheated() ? HeatCondition.SUPERHEATED : HeatCondition.HEATED;

        AllGuiTextures.JEI_LIGHT.render(guiGraphics, 81, 58 + 30 - 50);

        AllGuiTextures.JEI_HEAT_BAR.render(guiGraphics, 4, 80 - 50);
        guiGraphics.drawString(font, Lang.translateDirect(requiredHeat.getTranslationKey()), 9,
                86 - 50, requiredHeat.getColor());

        heater.withHeat(requiredHeat.visualizeAsBlazeBurner())
                .draw(guiGraphics, getBackground().getWidth() / 2 + 3, 55 - 50);

        AllGuiTextures.JEI_DOWN_ARROW.render(guiGraphics, getBackground().getWidth() / 2 + 3, 8);
    }

    public static String formatTime(int ticks) {
        if (ticks > 20 * 60) return (ticks / (20 * 60)) + " min";
        if (ticks > 20) return (ticks / 20) + " sec";
        return (ticks) + " ticks";
    }
}
