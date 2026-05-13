package ru.otus.danilchenko.cli.command;

public enum ECommand {
    EXIT("exit"),
    HELP("help"),
    GREETING("greeting");

    private String name;
    private ECommand(String name){
        this.name = name;
    }
}
