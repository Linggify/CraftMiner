package com.github.linggify.minecraft.craftminer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CraftMinerCommands {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final List<ICommandBase> QUEUED_COMMANDS = new ArrayList<>();

    private static List<LiteralArgumentBuilder<CommandSource>> recursiveRegisterCommands(List<ICommandBase> commands, int iteration) {
        //for a command to be passed to this method, the path has to at least contain the iteration'th element in the path
        Map<String, List<ICommandBase>> commandsByPathFragment = new HashMap<>();
        for (ICommandBase command : commands) {
            String fragment = command.getPath().get(iteration);
            if (!commandsByPathFragment.containsKey(fragment)) {
                commandsByPathFragment.put(fragment, new ArrayList<>());
            }

            commandsByPathFragment.get(fragment).add(command);
        }

        List<LiteralArgumentBuilder<CommandSource>> literals = new ArrayList<>();
        for (String fragment : commandsByPathFragment.keySet()) {
            LiteralArgumentBuilder<CommandSource> literal = Commands.literal(fragment);
            List<ICommandBase> deeperCommands = new ArrayList<>();

            for (ICommandBase command : commandsByPathFragment.get(fragment)) {
                //if path still contains elements, dispatch it later
                if (command.getPath().size() - 1 > iteration) {
                    deeperCommands.add(command);
                } else {
                    //if not, add the command here
                    //if command has arguments, add them
                    if (!command.getArguments().isEmpty()) {
                        List<RequiredArgumentBuilder<CommandSource, ?>> arguments = new ArrayList<>();
                        for (CommandArgumentContainer argument : command.getArguments()) {
                            arguments.add(Commands.argument(argument.getName(), argument.getType()));
                        }

                        //last argument executes the method
                        RequiredArgumentBuilder<CommandSource, ?> carg = arguments.remove(arguments.size() - 1).executes(command::execute);

                        //chain arguments
                        while (!arguments.isEmpty()) {
                            carg = arguments.remove(arguments.size() - 1).executes(command::execute);
                        }
                        literal = literal.then(carg);
                    } else {
                        literal = literal.executes(command::execute);
                    }
                }
            }

            //get deeper literals
            for (LiteralArgumentBuilder<CommandSource> deeperLiteral : recursiveRegisterCommands(deeperCommands, iteration + 1)) {
                literal = literal.then(deeperLiteral);
            }

            //add resulting literal to result list
            literals.add(literal);
        }

        return literals;
    }

    public static void registerCommand(Function<CommandContext<CommandSource>, Integer> executable, String path, CommandArgumentContainer... arguments) {
        registerCommand(new AbstractCommand(path, arguments) {
            @Override
            public int execute(CommandContext<CommandSource> context) {
                return executable.apply(context);
            }
        });
    }

    public static void registerCommand(ICommandBase command) {
        QUEUED_COMMANDS.add(command);
    }

    public static void registerQueuedCommands(CommandDispatcher<CommandSource> dispatcher) {
        for (LiteralArgumentBuilder<CommandSource> literal : recursiveRegisterCommands(QUEUED_COMMANDS, 0)) {
            dispatcher.register(literal);
        }

        //clear queued commands
        QUEUED_COMMANDS.clear();
    }
}
