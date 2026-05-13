package ru.otus.danilchenko.domain.use_case;

import ru.otus.danilchenko.domain.command.Interaction;

public class HelpUseCase {
    void execute(Interaction interaction) {
        interaction.write("help");
    }
}
