package aporia.cc.module.impl.combat;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import aporia.cc.api.event.EventHandler;
import aporia.cc.module.api.Module;
import aporia.cc.module.api.ModuleCategory;
import aporia.cc.api.event.impl.player.TickEvent;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AutoPotion extends Module {
    public AutoPotion() {
        super("AutoPotion", "Auto Potion", ModuleCategory.COMBAT);
        setup();
    }

    @EventHandler
    public void onTick(TickEvent e) {

    }
}
