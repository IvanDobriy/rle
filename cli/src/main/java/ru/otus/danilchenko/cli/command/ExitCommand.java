package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;

public class ExitCommand extends ACommand {

    public ExitCommand(ICommand command) {
        super(command);
    }

    @Override
    public ICommand execute(Interaction interaction) {
        return null;
    }
}
