package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.lib.api.hash.IHashTable;
import ru.otus.danilchenko.lib.v1.hash.OpenAddressHashTable;
import ru.otus.danilchenko.lib.v1.hash.StringHasher;

public class GateWayCommand implements ICommand {
    private IHashTable<String, ICommand> commands;
    private static final String EXIT = "exit";
    private static final String HELP = "help";
    private static final String GREETING = "greeting";

    public GateWayCommand() {
        commands = new OpenAddressHashTable<>(new StringHasher(), 0, 1);
        commands.insert(EXIT, new ExitCommand(null));
        commands.insert(GREETING, new GreetingCommand(commands.find(EXIT)));
        commands.insert(HELP, new HelpCommand(commands.find(EXIT)));
    }

    @Override
    public ICommand execute(Interaction interaction) {
        return null;
    }
}
