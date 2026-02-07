package aporia.cc.api.command.impl;

import aporia.cc.api.command.api.ICommandSystem;
import aporia.cc.api.command.api.argparser.IArgParserManager;
import aporia.cc.api.command.impl.argparser.ArgParserManager;

public enum CommandSystem implements ICommandSystem {
    INSTANCE;

    @Override
    public IArgParserManager getParserManager() {
        return ArgParserManager.INSTANCE;
    }
}
