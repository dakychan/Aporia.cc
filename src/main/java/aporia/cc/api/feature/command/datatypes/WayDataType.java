package aporia.cc.api.feature.command.datatypes;

import aporia.cc.api.feature.command.exception.CommandException;
import aporia.cc.api.feature.command.helpers.TabCompleteHelper;
import aporia.cc.api.repository.way.Way;
import aporia.cc.core.Main;

import java.util.List;
import java.util.stream.Stream;

public enum WayDataType implements IDatatypeFor<Way> {
    INSTANCE;

    @Override
    public Stream<String> tabComplete(IDatatypeContext datatypeContext) throws CommandException {
        Stream<String> ways = getWay().stream().map(Way::name);
        String context = datatypeContext.getConsumer().getString();
        return new TabCompleteHelper().append(ways).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public Way get(IDatatypeContext datatypeContext) throws CommandException {
        String text = datatypeContext.getConsumer().getString();
        return getWay().stream().filter(s -> s.name().equalsIgnoreCase(text)).findFirst().orElse(null);
    }

    private List<? extends Way> getWay() {
        return Main.getInstance().getWayRepository().wayList;
    }
}
