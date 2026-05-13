package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.lib.api.hash.IHashTable;
import ru.otus.danilchenko.lib.v1.hash.OpenAddressHashTable;
import ru.otus.danilchenko.lib.v1.hash.StringHasher;

public class GateWayCommand implements ICommand {
    private final IHashTable<String, ICommand> commands;
    private ECommand commandName;


    public GateWayCommand(String name) {
        try {
            commandName = ECommand.valueOf(name);
        } catch (Exception e) {
            commandName = null;
        }

        commands = new OpenAddressHashTable<>(new StringHasher(), 1, 1);
        commands.insert(ECommand.EXIT.name(), new ExitCommand(null));
        commands.insert(ECommand.GREETING.name(), new GreetingCommand(commands.find(ECommand.EXIT.name())));
        commands.insert(ECommand.HELP.name(), new HelpCommand(commands.find(ECommand.EXIT.name())));
    }

    @Override
    public ICommand execute(Interaction interaction) {
        ICommand command;
        if (commandName == null) {
            command = commands.find(ECommand.GREETING.name());
        } else {
            command = commands.find(commandName.name());
        }
        return command;
    }
}
