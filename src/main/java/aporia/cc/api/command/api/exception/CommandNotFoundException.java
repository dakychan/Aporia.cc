package aporia.cc.api.command.api.exception;

import aporia.cc.api.command.api.ICommand;
import aporia.cc.api.command.api.argument.ICommandArgument;
import aporia.cc.api.base.common.QuickLogger;

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
