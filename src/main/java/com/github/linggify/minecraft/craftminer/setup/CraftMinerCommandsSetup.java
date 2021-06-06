package com.github.linggify.minecraft.craftminer.setup;

import com.github.linggify.minecraft.craftminer.CraftMiner;
import com.github.linggify.minecraft.craftminer.command.CraftMinerCommands;
import com.github.linggify.minecraft.craftminer.command.arguments.Args;
import com.github.linggify.minecraft.craftminer.command.arguments.FileArgumentType;
import com.github.linggify.minecraft.craftminer.javascript.JavaScriptExecutor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.CallbackI;

import javax.script.ScriptException;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

/**
 * Used to wrap the setup of commands
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CraftMinerCommandsSetup {

    private static Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void setupCommands(FMLServerStartingEvent event) {
        // register commands
        CraftMinerCommands.registerCommand(context -> {
            try {
                JsonObject result = JavaScriptExecutor.execute(context.getArgument("script", File.class), context.getSource());

                Writer writer = new OutputStreamWriter(new FileOutputStream(new File(CraftMiner.DUMPS_ROOT, "dump.json")), StandardCharsets.UTF_8);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(result, writer);
                writer.flush();
            } catch (IOException e) {
                context.getSource().sendFailure(new StringTextComponent("Failed to write output:"));
                context.getSource().sendFailure(new StringTextComponent(e.getMessage()));
                LOGGER.error("Failed to write to output", e);
            } catch (ScriptException e) {
                context.getSource().sendFailure(new StringTextComponent("Failed to execute script:"));
                context.getSource().sendFailure(new StringTextComponent(e.getMessage()));
                LOGGER.error("Failed to execute script", e);
            } catch (Exception e) {
                context.getSource().sendFailure(new StringTextComponent("An unexpected exception occured:"));
                context.getSource().sendFailure(new StringTextComponent(e.getMessage()));
                LOGGER.error("Unexpected exception:", e);
            }

            return Command.SINGLE_SUCCESS;
        }, "craftminer exec", Args.fileInput("script",
                FileArgumentType.suggestFrom(CraftMiner.SCRIPTS_ROOT, FileArgumentType.filterWithToolTip(".*\\.js", "javascript")),
                FileArgumentType.addRoot(CraftMiner.SCRIPTS_ROOT)));

        CraftMinerCommands.registerQueuedCommands(event.getServer().getCommands().getDispatcher());
    }
}
