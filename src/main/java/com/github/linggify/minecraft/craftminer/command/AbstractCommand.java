package com.github.linggify.minecraft.craftminer.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractCommand implements ICommandBase{

    private final List<String> m_path;
    private final List<CommandArgumentContainer> m_arguments;

    public AbstractCommand(String path, CommandArgumentContainer... arguments) {
        m_path = new ArrayList<>();
        m_path.addAll(Arrays.asList(path.split("\\s+")));

        m_arguments = new ArrayList<>();
        m_arguments.addAll(Arrays.asList(arguments));
    }

    @Override
    public List<String> getPath() {
        return m_path;
    }

    @Override
    public List<CommandArgumentContainer> getArguments() {
        return m_arguments;
    }
}
