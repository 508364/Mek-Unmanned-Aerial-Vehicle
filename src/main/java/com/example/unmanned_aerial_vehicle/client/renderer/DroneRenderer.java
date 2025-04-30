package com.example.unmanned_aerial_vehicle.client.renderer;

import com.example.unmanned_aerial_vehicle.UnmannedAerialVehicleMod;
import com.example.unmanned_aerial_vehicle.entity.DroneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DroneRenderer extends GeoEntityRenderer<DroneEntity> {
    public DroneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DroneModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(DroneEntity entity) {
        return new ResourceLocation(UnmannedAerialVehicleMod.MODID, "textures/entity/drone.png");
    }

    @Override
    protected int getSkyLight(DroneEntity entity) {
        return 15;
    }

    @Override
    protected int getBlockLight(DroneEntity entity) {
        return entity.isOnFire() ? 15 : 0;
    }
}