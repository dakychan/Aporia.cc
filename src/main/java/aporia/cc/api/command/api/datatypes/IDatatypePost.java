package aporia.cc.api.command.api.datatypes;

import aporia.cc.api.command.api.exception.CommandException;

public interface IDatatypePost<T, O> extends IDatatype {
    T apply(IDatatypeContext datatypeContext, O original) throws CommandException;
}
