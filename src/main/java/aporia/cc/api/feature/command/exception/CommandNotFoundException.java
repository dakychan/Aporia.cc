package aporia.cc.api.feature.command.exception;

import aporia.cc.api.feature.command.ICommand;
import aporia.cc.api.feature.command.argument.ICommandArgument;
import aporia.cc.common.QuickLogger;

import java.util.List;

public class CommandNotFoundException extends CommandException implements QuickLogger {

    public final String command;

    public CommandNotFoundException(String command) {
        super(String.format("Команда не найдена: %s", command));
        this.command = command;
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
       logDirect(getMessage());
    }
}
