package aporia.cc.api.feature.command;

import aporia.cc.api.feature.command.argparser.IArgParserManager;

public interface ICommandSystem {
    IArgParserManager getParserManager();
}
