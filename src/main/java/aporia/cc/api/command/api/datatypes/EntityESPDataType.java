package aporia.cc.api.command.api.datatypes;

import net.minecraft.entity.EntityType;
import aporia.cc.api.command.api.exception.CommandException;
import aporia.cc.api.command.api.helpers.TabCompleteHelper;
import aporia.cc.api.base.core.Main;

import java.util.Optional;
import java.util.stream.Stream;

public enum EntityESPDataType implements IDatatypeFor<EntityType<?>> {
    INSTANCE;

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        Stream<String> ways = getEntities().map(s -> s.getName().getString().replace(" ", "_"));
        String context = ctx.getConsumer().getString();
        return new TabCompleteHelper().append(ways).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public EntityType<?> get(IDatatypeContext datatypeContext) throws CommandException {
        return findEntity(datatypeContext.getConsumer().getString()).orElse(null);
    }

    public Optional<EntityType<?>> findEntity(String text) {
        return getEntities().filter(s -> s.getName().getString().replace(" ", "_").equalsIgnoreCase(text)).findFirst();
    }

    private Stream<EntityType<?>> getEntities() {
        return Main.getInstance().getBoxESPRepository().entities.keySet().stream();
    }
}