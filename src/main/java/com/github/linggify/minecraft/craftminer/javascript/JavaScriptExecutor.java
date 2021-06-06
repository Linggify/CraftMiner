package com.github.linggify.minecraft.craftminer.javascript;

import com.google.gson.JsonObject;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;

import javax.script.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Used to execute craftminer scripts at runtime
 */
public class JavaScriptExecutor {

    public static JsonObject execute(File file, CommandSource caller) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        //retrieve scriptengine
        //ScriptEngineManager manager = new ScriptEngineManager();
        //ScriptEngine jsEngine = manager.getEngineByName("JavaScript");
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        ScriptEngine jsEngine = factory.getScriptEngine("--language=es6");

        //add context as global variable
        CraftMinerExecutionContext context = new CraftMinerExecutionContext(caller);
        jsEngine.put("context", context);

        //execute the script
        jsEngine.eval(new BufferedReader(new FileReader(file)));

        return context.getDumps();
    }
}
