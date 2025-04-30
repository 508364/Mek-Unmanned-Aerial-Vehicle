package com.example.blockentity;

import com.example.unmanned_aerial_vehicle.entity.DroneEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DroneDockBlockEntity extends BlockEntity implements WorldlyContainer {
    private final SimpleContainer inventory = new SimpleContainer(9);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    private int energyStored = 0;
    private static final int MAX_ENERGY = 10000;
    private static final int CHARGE_RATE = 100;
    
    public DroneDockBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }
    
    public void chargeDrone(DroneEntity drone) {
        if (energyStored > 0) {
            int energyToTransfer = Math.min(CHARGE_RATE, energyStored);
            drone.receiveEnergy(energyToTransfer);
            energyStored -= energyToTransfer;
            setChanged();
        }
    }
    
    public void transferItems(DroneEntity drone) {
        // 实现物品装卸逻辑
    }
    
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        energyStored = tag.getInt("Energy");
        // 加载物品栏
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Energy", energyStored);
        // 保存物品栏
    }
    
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER && side != null) {
            return handlers[side.ordinal()].cast();
        }
        return super.getCapability(cap, side);
    }
    
    // 实现WorldlyContainer接口方法
    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[0];
    }
    
    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return false;
    }
    
    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return false;
    }
    
    @Override
    public int getContainerSize() {
        return inventory.getContainerSize();
    }
    
    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }
    
    @Override
    public ItemStack getItem(int index) {
        return inventory.getItem(index);
    }
    
    @Override
    public ItemStack removeItem(int index, int count) {
        return inventory.removeItem(index, count);
    }
    
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return inventory.removeItemNoUpdate(index);
    }
    
    @Override
    public void setItem(int index, ItemStack stack) {
        inventory.setItem(index, stack);
    }
    
    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }
    
    @Override
    public void clearContent() {
        inventory.clearContent();
    }
}