package aporia.cc.api.command.api.exception;

import net.minecraft.util.Formatting;
import aporia.cc.api.command.api.ICommand;
import aporia.cc.api.command.api.argument.ICommandArgument;
import aporia.cc.api.base.common.QuickLogger;

import java.util.List;

public interface ICommandException extends QuickLogger {

    String getMessage();

    default void handle(ICommand command, List<ICommandArgument> args) {
        logDirect(
                this.getMessage(),
                Formatting.RED
        );
    }
}
