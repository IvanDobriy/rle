package ru.otus.danilchenko.domain.command;

public interface Interaction {
    String read();
    void write(String data);
}
