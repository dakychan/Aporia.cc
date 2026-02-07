package aporia.cc.api.event.impl.block;

import net.minecraft.block.entity.BlockEntity;
import aporia.cc.api.event.events.Event;

public record BlockEntityProgressEvent(BlockEntity blockEntity, Type type) implements Event {
    public enum Type {
        ADD, REMOVE
    }
}
