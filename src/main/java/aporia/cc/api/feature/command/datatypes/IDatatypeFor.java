package aporia.cc.api.feature.command.datatypes;

import aporia.cc.api.feature.command.exception.CommandException;

public interface IDatatypeFor<T> extends IDatatype  {
    T get(IDatatypeContext datatypeContext) throws CommandException;
}
