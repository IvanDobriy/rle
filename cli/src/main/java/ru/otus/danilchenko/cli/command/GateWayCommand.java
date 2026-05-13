package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.lib.api.hash.IHashTable;
import ru.otus.danilchenko.lib.v1.hash.OpenAddressHashTable;
import ru.otus.danilchenko.lib.v1.hash.StringHasher;

public class GateWayCommand implements ICommand {
    private IHashTable<String, ICommand> commands;


    public GateWayCommand() {
        commands = new OpenAddressHashTable<>(new StringHasher(), 0, 1);
        commands.insert(ECommand.EXIT.name(), new ExitCommand(null));
        commands.insert(ECommand.GREETING.name(), new GreetingCommand(commands.find(ECommand.GREETING.name())));
        commands.insert(ECommand.HELP.name(), new HelpCommand(commands.find(ECommand.HELP.name())));
    }

    @Override
    public ICommand execute(Interaction interaction) {
        int argsSize = interaction.arguments().size();
        if (argsSize > 0) {
            for (int i = 0; i < argsSize; i++) {

            }
        }
        return null;
    }
}
