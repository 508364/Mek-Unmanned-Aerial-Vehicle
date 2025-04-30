package com.example;

import com.example.blocks.ControlStationBlock;
import com.example.blocks.DroneDockBlock;
import com.example.items.RemoteControllerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "unmanned_aerial_vehicle");
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "unmanned_aerial_vehicle");
    
    // 方块注册
    public static final RegistryObject<Block> DRONE_DOCK = BLOCKS.register("drone_dock", DroneDockBlock::new);
    public static final RegistryObject<Block> CONTROL_STATION = BLOCKS.register("control_station", ControlStationBlock::new);
    
    // 方块物品注册
    public static final RegistryObject<Item> DRONE_DOCK_ITEM = ITEMS.register("drone_dock", 
        () -> new BlockItem(DRONE_DOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONTROL_STATION_ITEM = ITEMS.register("control_station", 
        () -> new BlockItem(CONTROL_STATION.get(), new Item.Properties()));
    
    // 物品注册
    public static final RegistryObject<Item> REMOTE_CONTROLLER = ITEMS.register("remote_controller", 
        () -> new RemoteControllerItem(new Item.Properties()));
}