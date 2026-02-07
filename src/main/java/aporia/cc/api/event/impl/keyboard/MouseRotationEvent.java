package aporia.cc.api.event.impl.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import aporia.cc.api.event.events.callables.EventCancellable;

@Getter
@Setter
@AllArgsConstructor
public class MouseRotationEvent extends EventCancellable {
    float cursorDeltaX, cursorDeltaY;
}
