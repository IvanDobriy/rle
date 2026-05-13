package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;

public class GreetingCommand extends ACommand {

    public GreetingCommand(ICommand command) {
        super(command);
    }

    @Override
    public ICommand execute(Interaction interaction) {
        return nextCommand;
    }
}
