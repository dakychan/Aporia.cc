package aporia.cc.api.command.api.datatypes;

import aporia.cc.api.command.api.exception.CommandException;

public interface IDatatypeFor<T> extends IDatatype  {
    T get(IDatatypeContext datatypeContext) throws CommandException;
}
