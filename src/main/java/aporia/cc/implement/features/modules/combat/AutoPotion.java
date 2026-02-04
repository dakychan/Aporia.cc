package aporia.cc.implement.features.modules.combat;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.implement.events.player.TickEvent;

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
