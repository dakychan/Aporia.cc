package aporia.cc.api.command.api;

import aporia.cc.api.command.api.argparser.IArgParserManager;

public interface ICommandSystem {
    IArgParserManager getParserManager();
}
