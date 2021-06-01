package com.github.linggify.minecraft.craftminer.command;

import com.mojang.brigadier.arguments.ArgumentType;

public class CommandArgumentContainer {

    private final String m_name;
    private final ArgumentType<?> m_argumentType;

    public CommandArgumentContainer(String name, ArgumentType<?> argumentType) {
        m_name = name;
        m_argumentType = argumentType;
    }

    public String getName() {
        return m_name;
    }

    public ArgumentType<?> getType() {
        return m_argumentType;
    }
}
