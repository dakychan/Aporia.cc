package aporia.cc.api.feature.command.exception;

import aporia.cc.api.feature.command.ICommand;
import aporia.cc.api.feature.command.argument.ICommandArgument;
import aporia.cc.common.QuickLogger;

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
