package com.example.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class DroneDockBlock extends Block implements MenuProvider {
    private static final int CHARGE_RATE = 100;
    private static final int MAX_ENERGY = 10000;
    private int energyStored = 0;
    private final ContainerData data = new SimpleContainerData(2);
    
    public DroneDockBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL)
            .strength(3.0f, 6.0f)
            .requiresCorrectToolForDrops());
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            MenuProvider menuProvider = this;
            NetworkHooks.openScreen((ServerPlayer) player, menuProvider, pos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new DroneDockMenu(id, inv, this, data);
    }
    
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.unmanned_aerial_vehicle.return_station");
    }
    
    public void chargeDrone(DroneEntity drone) {
        if (energyStored > 0) {
            int energyToTransfer = Math.min(CHARGE_RATE, energyStored);
            drone.receiveEnergy(energyToTransfer);
            energyStored -= energyToTransfer;
        }
    }
    
    public void transferItems(DroneEntity drone) {
        // 物品装卸逻辑
    }
}