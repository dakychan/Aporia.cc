package aporia.cc.implement.events.container;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.minecraft.client.gui.screen.Screen;
import aporia.cc.api.event.events.Event;
import aporia.cc.api.event.events.callables.EventCancellable;


@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetScreenEvent implements Event {
    public Screen screen;
}
