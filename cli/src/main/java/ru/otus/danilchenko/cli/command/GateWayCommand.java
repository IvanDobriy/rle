package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.lib.api.hash.IHashTable;
import ru.otus.danilchenko.lib.v1.hash.OpenAddressHashTable;

public class GateWayCommand implements ICommand {
    private IHashTable<String, ICommand> commands;
    public GateWayCommand() {
        commands = new OpenAddressHashTable<>()
        commands.insert("help", new HelpCommand());
    }
    @Override
    public ICommand execute(Interaction interaction) {
        return null;
    }
}
