package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.use_case.GreetingUseCase;

public class GreetingCommand extends ACommand {
    private final GreetingUseCase useCase;
    public GreetingCommand(ICommand command) {
        super(command);
        useCase = new GreetingUseCase();
    }

    @Override
    public ICommand execute(Interaction interaction) {
        useCase.execute(interaction);
        return nextCommand;
    }
}
