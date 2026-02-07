package aporia.cc.api.command.api.exception;

import aporia.cc.api.command.api.ICommand;
import aporia.cc.api.command.api.argument.ICommandArgument;
import aporia.cc.api.base.common.QuickLogger;

import java.util.List;

public class CommandUnhandledException extends RuntimeException implements ICommandException, QuickLogger {

    public CommandUnhandledException(String message) {
        super(message);
    }

    public CommandUnhandledException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
    }
}
