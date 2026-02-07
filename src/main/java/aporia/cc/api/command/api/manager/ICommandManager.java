package aporia.cc.api.command.api.manager;

import net.minecraft.util.Pair;
import aporia.cc.api.command.api.ICommand;
import aporia.cc.api.command.api.argument.ICommandArgument;
import aporia.cc.api.command.api.registry.Registry;

import java.util.List;
import java.util.stream.Stream;

public interface ICommandManager {
    Registry<ICommand> getRegistry();

    ICommand getCommand(String name);

    boolean execute(String string);

    boolean execute(Pair<String, List<ICommandArgument>> expanded);

    Stream<String> tabComplete(Pair<String, List<ICommandArgument>> expanded);

    Stream<String> tabComplete(String prefix);
}
