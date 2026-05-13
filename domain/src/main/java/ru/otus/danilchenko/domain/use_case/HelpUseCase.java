package ru.otus.danilchenko.domain.use_case;

import ru.otus.danilchenko.domain.command.Interaction;

public class HelpUseCase {
    public void execute(Interaction interaction) {
        interaction.write("RLE Compressor — CLI application for Run-Length Encoding compression/decompression.");
        interaction.write("");
        interaction.write("Available commands:");
        interaction.write("  --compress <from> <to>   Compress a file using RLE algorithm");
        interaction.write("  --decompress <from> <to> Decompress a file using RLE algorithm");
        interaction.write("  --greeting               Show greeting message");
        interaction.write("  --help                   Show this help message");
        interaction.write("  --exit                   Exit the application");
    }
}
