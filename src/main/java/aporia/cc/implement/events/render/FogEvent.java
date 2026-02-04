package aporia.cc.implement.events.render;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import aporia.cc.api.event.events.callables.EventCancellable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FogEvent extends EventCancellable {
    float distance;
    int color;
}
