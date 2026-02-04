package aporia.cc.implement.features.modules.combat;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import aporia.cc.api.repository.friend.FriendUtils;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.api.event.EventHandler;
import aporia.cc.implement.events.player.AttackEvent;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoFriendDamage extends Module {
    public NoFriendDamage() {
        super("NoFriendDamage", "No Friend Damage", ModuleCategory.COMBAT);
    }

    @EventHandler
    public void onAttack(AttackEvent e) {
        e.setCancelled(FriendUtils.isFriend(e.getEntity()));
    }
}

