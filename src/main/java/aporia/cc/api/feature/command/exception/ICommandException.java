package aporia.cc.api.feature.command.exception;

import net.minecraft.util.Formatting;
import aporia.cc.api.feature.command.ICommand;
import aporia.cc.api.feature.command.argument.ICommandArgument;
import aporia.cc.common.QuickLogger;

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
