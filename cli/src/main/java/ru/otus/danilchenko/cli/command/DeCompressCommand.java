package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.data.rle.RleDataService;
import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.data.IRle;
import ru.otus.danilchenko.domain.use_case.DecompressUseCase;
import ru.otus.danilchenko.lib.v1.rle.RleCompressorV1;

public class DeCompressCommand extends ACommand {
    private final DecompressUseCase useCase;

    public DeCompressCommand(ICommand command) {
        super(command);
        RleCompressorV1 compressor = new RleCompressorV1();
        IRle rle = new RleDataService(compressor, compressor);
        useCase = new DecompressUseCase(rle);
    }

    @Override
    public ICommand execute(Interaction interaction) {
        useCase.execute(interaction);
        return nextCommand;
    }
}
