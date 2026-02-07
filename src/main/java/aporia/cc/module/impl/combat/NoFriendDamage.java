package aporia.cc.module.impl.combat;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import aporia.cc.api.repository.friend.FriendUtils;
import aporia.cc.module.api.Module;
import aporia.cc.module.api.ModuleCategory;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.event.impl.player.AttackEvent;

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

