package aporia.cc.api.command.api;

import aporia.cc.api.command.api.argument.IArgConsumer;
import aporia.cc.api.command.api.exception.CommandException;
import aporia.cc.api.base.common.QuickLogger;

import java.util.List;
import java.util.stream.Stream;

public interface ICommand extends QuickLogger {
    void execute(String label, IArgConsumer args) throws CommandException;

    Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException;

    String getShortDesc();

    List<String> getLongDesc();

    List<String> getNames();

    default boolean hiddenFromHelp() {
        return false;
    }
}
