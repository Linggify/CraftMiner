package com.github.linggify.minecraft.craftminer;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CraftMiner.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CraftMiner
{
    public static final String MOD_ID = "craftminer";

    private static final Logger LOGGER = LogManager.getLogger();

    public static final File SCRIPTS_ROOT = new File("craftminer_scripts");
    public static final File DUMPS_ROOT = new File("craftminer_dumps");

    public CraftMiner() {
    }

    @SubscribeEvent
    public static void onSetup(FMLCommonSetupEvent event) {
        //ensure scripts and dumps folder does actually exist
        if (!SCRIPTS_ROOT.exists()) {
            SCRIPTS_ROOT.mkdirs();
        }

        if (!DUMPS_ROOT.exists()) {
            DUMPS_ROOT.mkdirs();
        }
    }
}
