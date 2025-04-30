package com.example.unmanned_aerial_vehicle.entity;

import com.example.unmanned_aerial_vehicle.UnmannedAerialVehicleMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UnmannedAerialVehicleMod.MODID);

    public static final RegistryObject<EntityType<DroneEntity>> DRONE =
            ENTITIES.register("drone",
                    () -> EntityType.Builder.of(DroneEntity::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build("drone"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}