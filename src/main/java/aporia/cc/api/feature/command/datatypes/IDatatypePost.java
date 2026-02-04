package aporia.cc.api.feature.command.datatypes;

import aporia.cc.api.feature.command.exception.CommandException;

public interface IDatatypePost<T, O> extends IDatatype {
    T apply(IDatatypeContext datatypeContext, O original) throws CommandException;
}
