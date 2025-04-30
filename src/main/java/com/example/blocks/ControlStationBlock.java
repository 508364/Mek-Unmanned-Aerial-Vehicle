package com.example.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ControlStationBlock extends Block {
    public ControlStationBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL)
            .strength(4.0f, 8.0f)
            .requiresCorrectToolForDrops());
    }
}