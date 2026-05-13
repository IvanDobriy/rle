package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.data.rle.RleDataService;
import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.data.IRle;
import ru.otus.danilchenko.domain.use_case.CompressUseCase;
import ru.otus.danilchenko.lib.v1.rle.RleCompressorV1;

public class CompressCommand extends ACommand {
    private final CompressUseCase useCase;

    public CompressCommand(ICommand command) {
        super(command);
        RleCompressorV1 compressor = new RleCompressorV1();
        IRle rle = new RleDataService(compressor, compressor);
        useCase = new CompressUseCase(rle);
    }

    @Override
    public ICommand execute(Interaction interaction) {
        useCase.execute(interaction);
        return nextCommand;
    }
}
