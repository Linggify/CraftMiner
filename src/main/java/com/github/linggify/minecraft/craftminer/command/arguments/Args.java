package com.github.linggify.minecraft.craftminer.command.arguments;

import com.github.linggify.minecraft.craftminer.command.CommandArgumentContainer;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Args {

    public static CommandArgumentContainer integer(String name) {
        return integer(name, Integer.MIN_VALUE);
    }

    public static CommandArgumentContainer integer(String name, int min) {
        return integer(name, min, Integer.MAX_VALUE);
    }

    public static CommandArgumentContainer integer(String name, int min, int max) {
        return new CommandArgumentContainer(name, IntegerArgumentType.integer(min, max));
    }

    public static CommandArgumentContainer decimal(String name) {
        return decimal(name, Double.MIN_VALUE);
    }

    public static CommandArgumentContainer decimal(String name, double min) {
        return decimal(name, min, Double.MAX_VALUE);
    }

    public static CommandArgumentContainer decimal(String name, double min, double max) {
        return new CommandArgumentContainer(name, DoubleArgumentType.doubleArg(min, max));
    }

    public static CommandArgumentContainer string(String name) {
        return new CommandArgumentContainer(name, StringArgumentType.string());
    }

    public static CommandArgumentContainer word(String name) {
        return new CommandArgumentContainer(name, StringArgumentType.word());
    }

    public static CommandArgumentContainer fileInput(String name, BiFunction<CommandContext<?>, SuggestionsBuilder, CompletableFuture<Suggestions>> suggester, Function<String, File> finalizer) {
        return new CommandArgumentContainer(name, new FileArgumentType(suggester, finalizer));
    }

    public static CommandArgumentContainer fileOutput(String name, Function<String, File> finalizer) {
        return new CommandArgumentContainer(name, new FileArgumentType(FileArgumentType.noSuggestions(), finalizer));
    }
}
