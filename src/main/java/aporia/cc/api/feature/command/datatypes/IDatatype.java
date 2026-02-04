package aporia.cc.api.feature.command.datatypes;

import aporia.cc.api.feature.command.exception.CommandException;
import aporia.cc.common.QuickImports;

import java.util.stream.Stream;

public interface IDatatype extends QuickImports {
    Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException;
}
