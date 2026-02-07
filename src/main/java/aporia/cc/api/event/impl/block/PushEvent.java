package aporia.cc.api.event.impl.block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import aporia.cc.api.event.events.callables.EventCancellable;

@Getter
@AllArgsConstructor
public class PushEvent extends EventCancellable {
    private Type type;

    public enum Type {
        COLLISION, BLOCK, WATER
    }
}
