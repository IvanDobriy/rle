package ru.otus.danilchenko.cli.interaction;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.lib.api.array.IArray;
import ru.otus.danilchenko.lib.v1.array.SingleArray;

import java.util.Objects;

public class CliInteraction implements Interaction {
    private IArray<String> arguments;

    public CliInteraction(String[] args) {
        Objects.requireNonNull(args);
        arguments = new SingleArray<>(args);
    }

    @Override
    public IArray<String> arguments() {
        return arguments;
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
