package com.example.drones;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;

public abstract class DroneEntity extends AbstractMinecart {
    protected DroneEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public Type getMinecartType() {
        return Type.RIDEABLE;
    }

    public abstract DroneTypes getDroneType();
}