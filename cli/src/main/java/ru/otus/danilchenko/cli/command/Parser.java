package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.cli.interaction.CliInteraction;
import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.lib.api.array.IArray;
import ru.otus.danilchenko.lib.v1.array.SingleArray;

import java.util.Objects;

public class Parser {
    private final Interaction interaction;
    private String commandName = null;
    private IArray<String> commandArgs;

    public Parser(Interaction interaction) {
        Objects.requireNonNull(interaction);
        this.interaction = interaction;
        commandArgs = new SingleArray<>(0);
    }

    Parser handle() {
        int argsSize = interaction.arguments().size();
        for (int i = 0; i < argsSize; i++) {
            String argument = interaction.arguments().get(i).trim().toLowerCase();
            if (argument.startsWith("--")) {
                if (commandName != null) {
                    break;
                }
                commandName = argument.substring(2, argument.length());
                continue;
            }
            commandArgs.add(commandArgs.size(), argument);
        }
        return this;
    }

    String commandName() {
        return commandName;
    }

    Interaction interaction() {
        String[] args = new String[commandArgs.size()];
        for (int i = 0; i < args.length; i++) {
            args[i] = commandArgs.get(i);
        }
        return new CliInteraction(args);
    }
}
