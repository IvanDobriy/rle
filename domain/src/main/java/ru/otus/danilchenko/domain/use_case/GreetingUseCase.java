package ru.otus.danilchenko.domain.use_case;

import ru.otus.danilchenko.domain.command.Interaction;

public class GreetingUseCase {
    public void execute(Interaction interaction) {
        interaction.write("Welcome to RLE Compressor!");
        interaction.write("A CLI tool for compressing and decompressing files using the Run-Length Encoding algorithm.");
        interaction.write("Use --help to see available commands.");
    }
}
