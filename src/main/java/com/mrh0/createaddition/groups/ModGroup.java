package com.mrh0.createaddition.groups;

import com.mrh0.createaddition.CreateAddition;
import com.mrh0.createaddition.index.CABlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModGroup {
    public static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, CreateAddition.MODID);
    public static final RegistryObject<CreativeModeTab> MAIN = TAB_REGISTER.register("main", () -> CreativeModeTab.builder()
            .title(Component.literal(CreateAddition.MODID + ":main"))
            .icon(() -> new ItemStack(CABlocks.ELECTRIC_MOTOR.get()))
            .build());
}
