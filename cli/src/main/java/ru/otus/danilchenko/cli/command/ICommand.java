package ru.otus.danilchenko.cli.command;


import ru.otus.danilchenko.domain.command.Interaction;

public interface ICommand {
    ICommand execute(Interaction interaction);
}
