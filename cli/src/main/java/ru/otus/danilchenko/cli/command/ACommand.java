package ru.otus.danilchenko.cli.command;

public abstract class ACommand implements ICommand {
    protected ICommand nextCommand;
    public ACommand(ICommand command){
        nextCommand = command;
    }
}
