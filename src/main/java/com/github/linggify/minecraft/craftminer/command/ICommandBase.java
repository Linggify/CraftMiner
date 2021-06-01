package com.github.linggify.minecraft.craftminer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;

import java.util.List;

public interface ICommandBase {

    List<String> getPath();
    List<CommandArgumentContainer> getArguments();

    int execute(CommandContext<CommandSource> context);
}
