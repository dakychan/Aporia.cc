package aporia.cc.implement.events.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import aporia.cc.api.event.events.callables.EventCancellable;

@Getter
@Setter
@AllArgsConstructor
public class UsingItemEvent extends EventCancellable {
    byte type;
}
