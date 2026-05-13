package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.use_case.CompressUseCase;

public class CompressCommand extends ACommand {
    private final CompressUseCase useCase;

    public CompressCommand(ICommand command, CompressUseCase useCase) {
        super(command);
        this.useCase = useCase;
    }

    @Override
    public ICommand execute(Interaction interaction) {
        useCase.execute(interaction);
        return nextCommand;
    }
}
