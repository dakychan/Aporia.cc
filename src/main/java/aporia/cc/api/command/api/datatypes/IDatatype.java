package aporia.cc.api.command.api.datatypes;

import aporia.cc.api.command.api.exception.CommandException;
import aporia.cc.api.base.common.QuickImports;

import java.util.stream.Stream;

public interface IDatatype extends QuickImports {
    Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException;
}
