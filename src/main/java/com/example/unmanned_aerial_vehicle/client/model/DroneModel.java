package com.example.unmanned_aerial_vehicle.client.model;

import com.example.unmanned_aerial_vehicle.entity.DroneEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DroneModel extends AnimatedGeoModel<DroneEntity> {
    @Override
    public ResourceLocation getModelResource(DroneEntity object) {
        return new ResourceLocation("unmanned_aerial_vehicle", "geo/entity/drone.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DroneEntity object) {
        return new ResourceLocation("unmanned_aerial_vehicle", "textures/entity/drone.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DroneEntity animatable) {
        return new ResourceLocation("unmanned_aerial_vehicle", "animations/entity/drone.animation.json");
    }
}