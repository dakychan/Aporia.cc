package aporia.cc.implement.features.commands;

import aporia.cc.api.feature.command.ICommandSystem;
import aporia.cc.api.feature.command.argparser.IArgParserManager;
import aporia.cc.implement.features.commands.argparser.ArgParserManager;

public enum CommandSystem implements ICommandSystem {
    INSTANCE;

    @Override
    public IArgParserManager getParserManager() {
        return ArgParserManager.INSTANCE;
    }
}
