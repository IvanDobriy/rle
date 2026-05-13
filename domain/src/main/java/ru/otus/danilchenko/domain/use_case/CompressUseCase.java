package ru.otus.danilchenko.domain.use_case;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.data.IRle;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class CompressUseCase {
    private final IRle rle;

    public CompressUseCase(IRle rle) {
        this.rle = Objects.requireNonNull(rle);
    }

    public void execute(Interaction interaction) {
        var args = interaction.arguments();
        if (args.size() < 2) {
            interaction.write("Usage: --compress <from> <to>");
            return;
        }
        Path from = Paths.get(args.get(0));
        Path to = Paths.get(args.get(1));
        rle.compress(from, to);
        interaction.write("Compressed " + from + " -> " + to);
    }
}
