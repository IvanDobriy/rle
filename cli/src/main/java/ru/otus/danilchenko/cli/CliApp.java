package ru.otus.danilchenko.cli;

import ru.otus.danilchenko.cli.command.GateWayCommand;
import ru.otus.danilchenko.cli.command.ICommand;
import ru.otus.danilchenko.cli.command.Parser;
import ru.otus.danilchenko.cli.interaction.CliInteraction;
import ru.otus.danilchenko.domain.command.Interaction;

import java.util.Arrays;
import java.util.logging.Logger;

public class CliApp {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private void run(String[] args) {
        try {
            Interaction interaction = new CliInteraction(args);
            Parser parser = new Parser(interaction);
            parser.handle();
            String commandName = parser.commandName();
            interaction = parser.interaction();
            ICommand command = new GateWayCommand(commandName);
            do {
                command = command.execute(interaction);
            } while (command != null);
        } catch (Exception e) {
            logger.warning(e.getMessage() + Arrays.asList(e.getStackTrace()));
        }
    }

    public static void main(String[] args) {
        final var app = new CliApp();
        app.run(args);
    }
}
