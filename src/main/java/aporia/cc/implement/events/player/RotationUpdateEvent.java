package aporia.cc.implement.events.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import aporia.cc.api.event.events.Event;

@Getter
@AllArgsConstructor
public class RotationUpdateEvent implements Event {
    byte type;
}
