package ru.otus.danilchenko.cli.command;

public enum ECommand {
    EXIT("exit"),
    HELP("help"),
    GREETING("greeting"),
    COMPRESS("compress"),
    DECOMPRESS("decompress");

    private String name;
    private ECommand(String name){
        this.name = name;
    }
    public static ECommand find(String name){
        switch (name){
            case "exit": return EXIT;
            case "help": return HELP;
            case "greeting": return GREETING;
            case "compress": return COMPRESS;
            case "decompress": return DECOMPRESS;
            default: return null;
        }
    }
}
