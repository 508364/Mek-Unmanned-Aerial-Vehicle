package com.example.menu;

import com.example.blocks.DroneDockBlock;
import com.example.unmanned_aerial_vehicle.entity.DroneEntity;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DroneDockMenu extends AbstractContainerMenu {
    private final Container container;
    private final DroneDockBlock dockBlock;
    private final DroneEntity drone;
    
    public DroneDockMenu(int id, Inventory playerInventory, DroneDockBlock dockBlock, ContainerData data) {
        super(null, id);
        this.dockBlock = dockBlock;
        this.drone = null; // 实际应用中需要从数据获取无人机实例
        this.container = new SimpleContainer(9);
        
        // 玩家物品栏
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        
        // 玩家快捷栏
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        
        // 无人机物品栏
        if(drone != null) {
            for(int l = 0; l < 3; ++l) {
                for(int m = 0; m < 3; ++m) {
                    this.addSlot(new Slot(drone.getInventory(), m + l * 3, 62 + m * 18, 17 + l * 18));
                }
            }
        }
        
        // 返航台物品栏
        for(int n = 0; n < 3; ++n) {
            for(int o = 0; o < 3; ++o) {
                this.addSlot(new Slot(container, o + n * 3, 116 + o * 18, 17 + n * 18));
            }
        }
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            
            // 物品转移逻辑
            if (index < 36) {
                if (!this.moveItemStackTo(itemstack1, 36, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
            
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }
    
    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}