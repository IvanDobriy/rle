package ru.otus.danilchenko.domain.command;

import ru.otus.danilchenko.lib.api.array.IArray;

public interface Interaction {
    IArray<String> arguments();

    void write(String data);
}
