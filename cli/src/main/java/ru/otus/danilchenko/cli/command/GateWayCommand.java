package ru.otus.danilchenko.cli.command;

import ru.otus.danilchenko.data.rle.RleDataService;
import ru.otus.danilchenko.domain.command.Interaction;
import ru.otus.danilchenko.domain.data.IRle;
import ru.otus.danilchenko.domain.use_case.CompressUseCase;
import ru.otus.danilchenko.lib.api.hash.IHashTable;
import ru.otus.danilchenko.lib.v1.hash.OpenAddressHashTable;
import ru.otus.danilchenko.lib.v1.hash.StringHasher;
import ru.otus.danilchenko.lib.v1.rle.RleCompressorV1;

public class GateWayCommand implements ICommand {
    private final IHashTable<String, ICommand> commands;
    private ECommand commandName;

    public GateWayCommand(String name) {
        commandName = ECommand.find(name);
        commands = new OpenAddressHashTable<>(new StringHasher(), 1, 1);
        commands.insert(ECommand.EXIT.name(), new ExitCommand(null));
        commands.insert(ECommand.GREETING.name(), new GreetingCommand(commands.find(ECommand.EXIT.name())));
        commands.insert(ECommand.HELP.name(), new HelpCommand(commands.find(ECommand.EXIT.name())));

        IRle rle = new RleDataService(new RleCompressorV1(), new RleCompressorV1());
        commands.insert(ECommand.COMPRESS.name(), new CompressCommand(commands.find(ECommand.EXIT.name()), new CompressUseCase(rle)));
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
