package com.example.unmannedaerialvehicle;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import net.minecraft.client.resources.language.I18n;
import org.apache.logging.log4j.Logger;

@Mod(UnmannedAerialVehicleMod.MOD_ID)
public class UnmannedAerialVehicleMod {
    public static final String MOD_ID = "unmanned_aerial_vehicle";
    private static final Logger LOGGER = LogManager.getLogger();

    public UnmannedAerialVehicleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info(I18n.get("message.unmanned_aerial_vehicle.setup"));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Unmanned Aerial Vehicle Mod client setup");
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
        LOGGER.info("Unmanned Aerial Vehicle Mod server setup");
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        LOGGER.info("Unmanned Aerial Vehicle Mod enqueue IMC");
    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Unmanned Aerial Vehicle Mod process IMC");
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        LOGGER.info("Unmanned Aerial Vehicle Mod load complete");
    }
}