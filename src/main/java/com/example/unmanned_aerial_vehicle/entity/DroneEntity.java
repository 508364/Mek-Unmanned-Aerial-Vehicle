package com.example.unmanned_aerial_vehicle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DroneEntity extends MobEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private int energy = 0;
    private static final int MAX_ENERGY = 5000;
    private BlockPos homePos = null;
    private boolean isReturning = false;

    public DroneEntity(EntityType<? extends MobEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public void setHomePos(BlockPos pos) {
        this.homePos = pos;
    }

    public void startReturning() {
        if (homePos != null) {
            this.isReturning = true;
            this.getNavigation().moveTo(homePos.getX(), homePos.getY(), homePos.getZ(), 1.0);
        }
    }

    public void receiveEnergy(int amount) {
        this.energy = Math.min(energy + amount, MAX_ENERGY);
    }

    @Override
    public void tick() {
        super.tick();
        if (isReturning && homePos != null && this.distanceToSqr(homePos.getX(), homePos.getY(), homePos.getZ()) < 4.0) {
            isReturning = false;
            
            // 检查返航台方块
            BlockEntity blockEntity = level.getBlockEntity(homePos);
            if (blockEntity instanceof DroneDockBlockEntity) {
                DroneDockBlockEntity dock = (DroneDockBlockEntity) blockEntity;
                
                // 充电
                dock.chargeDrone(this);
                
                // 物品装卸
                dock.transferItems(this);
            }
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}