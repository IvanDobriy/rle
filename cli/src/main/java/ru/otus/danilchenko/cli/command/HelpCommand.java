package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.use_case.HelpUseCase;

public class HelpCommand extends ACommand {
    private final HelpUseCase useCase;

    public HelpCommand(ICommand command) {
        super(command);
        useCase = new HelpUseCase();
    }

    @Override
    public ICommand execute(Interaction interaction) {
        useCase.execute(interaction);
        return nextCommand;
    }
}
