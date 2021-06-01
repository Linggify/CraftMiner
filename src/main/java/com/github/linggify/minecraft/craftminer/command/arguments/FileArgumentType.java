package com.github.linggify.minecraft.craftminer.command.arguments;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FileArgumentType implements ArgumentType<File> {

    public static interface INamedFileFilter extends FileFilter {
        String getToolTip();
    }

    public static INamedFileFilter filterWithToolTip(String regex, String tooltip) {
        return new INamedFileFilter() {
            @Override
            public String getToolTip() {
                return tooltip;
            }

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().matches(regex);
            }
        };
    }

    public static BiFunction<CommandContext<?>, SuggestionsBuilder, CompletableFuture<Suggestions>> noSuggestions() {
        return (commandContext, suggestionsBuilder) -> suggestionsBuilder.buildFuture();
    }

    public static Function<String, File> addRoot(File root) {
        return file -> new File(root, file);
    }

    public static Function<String, File> asJson(File root) {
        return file -> new File(root, file.endsWith(".json") ? file : file + ".json");
    }

    public static BiFunction<CommandContext<?>, SuggestionsBuilder, CompletableFuture<Suggestions>> suggestFrom(File root, FileFilter... filters) {
        return (commandContext, suggestionsBuilder) -> {
            SuggestionsBuilder builder = suggestionsBuilder;
            for (FileFilter filter : filters) {
                String tooltip = filter instanceof INamedFileFilter ? ((INamedFileFilter) filter).getToolTip() : null;

                for (File file : Objects.requireNonNull(root.listFiles(filter))) {
                    builder = tooltip != null ? builder.suggest(file.getName(), () -> tooltip) : builder.suggest(file.getName());
                }
            }

            return builder.buildFuture();
        };
    }

    private final BiFunction<CommandContext<?>, SuggestionsBuilder, CompletableFuture<Suggestions>> m_suggester;
    private final Function<String, File> m_finalizer;

    public <S> FileArgumentType(BiFunction<CommandContext<?>, SuggestionsBuilder, CompletableFuture<Suggestions>> suggester, Function<String, File> finalizer) {
        m_suggester = suggester;
        m_finalizer = finalizer;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        return m_suggester.apply(context, builder);
    }

    @Override
    public File parse(StringReader reader) throws CommandSyntaxException {
        // parse string
        String file_name = reader.readString();

        //convert to usable file
        return m_finalizer.apply(file_name);
    }
}
