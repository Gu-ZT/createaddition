package com.mrh0.createaddition.index;

import com.mrh0.createaddition.CreateAddition;
import com.mrh0.createaddition.blocks.accumulator.AccumulatorRenderer;
import com.mrh0.createaddition.blocks.accumulator.AccumulatorTileEntity;
import com.mrh0.createaddition.blocks.alternator.AlternatorRenderer;
import com.mrh0.createaddition.blocks.alternator.AlternatorTileEntity;
import com.mrh0.createaddition.blocks.connector.ConnectorRenderer;
import com.mrh0.createaddition.blocks.connector.ConnectorTileEntity;
import com.mrh0.createaddition.blocks.creative_energy.CreativeEnergyTileEntity;
import com.mrh0.createaddition.blocks.crude_burner.CrudeBurnerTileEntity;
import com.mrh0.createaddition.blocks.electric_motor.ElectricMotorRenderer;
import com.mrh0.createaddition.blocks.electric_motor.ElectricMotorTileEntity;
import com.mrh0.createaddition.blocks.furnace_burner.FurnaceBurnerTileEntity;
import com.mrh0.createaddition.blocks.heater.HeaterTileEntity;
import com.mrh0.createaddition.blocks.redstone_relay.RedstoneRelayRenderer;
import com.mrh0.createaddition.blocks.redstone_relay.RedstoneRelayTileEntity;
import com.mrh0.createaddition.blocks.rolling_mill.RollingMillInstance;
import com.mrh0.createaddition.blocks.rolling_mill.RollingMillRenderer;
import com.mrh0.createaddition.blocks.rolling_mill.RollingMillTileEntity;
import com.mrh0.createaddition.blocks.tesla_coil.TeslaCoilTileEntity;
import com.mrh0.createaddition.transfer.EnergyTransferable;
import com.simibubi.create.content.contraptions.base.HalfShaftInstance;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import io.github.fabricators_of_create.porting_lib.transfer.fluid.FluidTransferable;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemTransferable;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CATileEntities {
	public static final BlockEntityEntry<ElectricMotorTileEntity> ELECTRIC_MOTOR = CreateAddition.registrate()
			.tileEntity("electric_motor", ElectricMotorTileEntity::new)
			.instance(() -> HalfShaftInstance::new)
			.validBlocks(CABlocks.ELECTRIC_MOTOR)
			.renderer(() -> ElectricMotorRenderer::new)
			.register();
	
	public static final BlockEntityEntry<AlternatorTileEntity> ALTERNATOR = CreateAddition.registrate()
			.tileEntity("alternator", AlternatorTileEntity::new)
			.instance(() -> HalfShaftInstance::new)
			.validBlocks(CABlocks.ALTERNATOR)
			.renderer(() -> AlternatorRenderer::new)
			.register();
	
	public static final BlockEntityEntry<RollingMillTileEntity> ROLLING_MILL = CreateAddition.registrate()
			.tileEntity("rolling_mill", RollingMillTileEntity::new)
			.instance(() -> RollingMillInstance::new)
			.validBlocks(CABlocks.ROLLING_MILL)
			.renderer(() -> RollingMillRenderer::new)
			.register();
	
	public static final BlockEntityEntry<CreativeEnergyTileEntity> CREATIVE_ENERGY = CreateAddition.registrate()
			.tileEntity("creative_energy", CreativeEnergyTileEntity::new)
			.validBlocks(CABlocks.CREATIVE_ENERGY)
			.register();
	
	public static final BlockEntityEntry<ConnectorTileEntity> CONNECTOR = CreateAddition.registrate()
			.tileEntity("connector", ConnectorTileEntity::new)
			.validBlocks(CABlocks.CONNECTOR_COPPER)
			.renderer(() -> ConnectorRenderer::new)
			.register();
	
	public static final BlockEntityEntry<HeaterTileEntity> HEATER = CreateAddition.registrate()
			.tileEntity("heater", HeaterTileEntity::new)
			.validBlocks(CABlocks.HEATER)
			.register();
	
	public static final BlockEntityEntry<AccumulatorTileEntity> ACCUMULATOR = CreateAddition.registrate()
			.tileEntity("accumulator", AccumulatorTileEntity::new)
			.validBlocks(CABlocks.ACCUMULATOR)
			.renderer(() -> AccumulatorRenderer::new)
			.register();
	
	public static final BlockEntityEntry<RedstoneRelayTileEntity> REDSTONE_RELAY = CreateAddition.registrate()
			.tileEntity("redstone_relay", RedstoneRelayTileEntity::new)
			.validBlocks(CABlocks.REDSTONE_RELAY)
			.renderer(() -> RedstoneRelayRenderer::new)
			.register();
	
	public static final BlockEntityEntry<FurnaceBurnerTileEntity> FURNACE_BURNER = CreateAddition.registrate()
			.tileEntity("furnace_burner", FurnaceBurnerTileEntity::new)
			.validBlocks(CABlocks.FURNACE_BURNER)
			.register();
	
	public static final BlockEntityEntry<CrudeBurnerTileEntity> CRUDE_BURNER = CreateAddition.registrate()
			.tileEntity("crude_burner", CrudeBurnerTileEntity::new)
			.validBlocks(CABlocks.CRUDE_BURNER)
			.register();
	
	/*public static final TileEntityEntry<ChargerTileEntity> CHARGER = CreateAddition.registrate()
			.tileEntity("charger", ChargerTileEntity::new)
			.validBlocks(CABlocks.CHARGER)
			.renderer(() -> ChargerRenderer::new)
			.register();*/
	
	public static final BlockEntityEntry<TeslaCoilTileEntity> TESLA_COIL = CreateAddition.registrate()
			.tileEntity("tesla_coil", TeslaCoilTileEntity::new)
			.validBlocks(CABlocks.TESLA_COIL)
			//.renderer(() -> ChargerRenderer::new)
			.register();
	
	public static void register() {
		EnergyStorage.SIDED.registerFallback((world, pos, state, blockEntity, context) -> {
			if(blockEntity instanceof EnergyTransferable transferable)
				return transferable.getEnergyStorage(context);
			return null;
		});
	}
}
