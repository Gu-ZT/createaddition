package com.mrh0.createaddition.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrh0.createaddition.index.CABlocks;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class AnimatedRollingMill extends AnimatedKinetics {

    private boolean shadow = true;

    public AnimatedRollingMill(boolean shadow) {
        this.shadow = shadow;
    }

    public AnimatedRollingMill() {
        shadow = true;
    }

    @Override
    public void draw(@NotNull GuiGraphics guiGraphics, int xOffset, int yOffset) {
        PoseStack matrixStack = guiGraphics.pose();
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 0);
        if (shadow)
            AllGuiTextures.JEI_SHADOW.render(guiGraphics, -16, 13);
        matrixStack.translate(-2, 18, 0);
        int scale = 22;

		/*GuiGameElement.of(AllBlockPartials.MILLSTONE_COG)
			.rotateBlock(22.5, getCurrentAngle() * 2, 0)
			.scale(scale)
			.render(matrixStack);*/

        GuiGameElement.of(CABlocks.ROLLING_MILL.getDefaultState())
                .rotateBlock(22.5, 22.5, 0)
                .scale(scale)
                .render(guiGraphics);

        matrixStack.popPose();
    }
}