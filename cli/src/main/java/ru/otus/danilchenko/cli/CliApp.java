package ru.otus.danilchenko.cli;

import ru.otus.danilchenko.cli.command.GateWayCommand;
import ru.otus.danilchenko.cli.command.ICommand;
import ru.otus.danilchenko.cli.interaction.CliInteraction;
import ru.otus.danilchenko.domain.command.Interaction;

public class CliApp {
    private void run(String[] args) {
        final Interaction interaction = new CliInteraction(args);
        ICommand command = new GateWayCommand();
        do {
            command = command.execute(interaction);
        } while (command != null);
    }

    public static void main(String[] args) {
        final var app = new CliApp();
        app.run(args);
    }
}
