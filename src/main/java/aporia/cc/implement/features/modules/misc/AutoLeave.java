package aporia.cc.implement.features.modules.misc;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.text.Text;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.api.feature.module.setting.implement.MultiSelectSetting;
import aporia.cc.api.feature.module.setting.implement.SelectSetting;
import aporia.cc.api.feature.module.setting.implement.ValueSetting;
import aporia.cc.api.repository.friend.FriendUtils;
import aporia.cc.common.util.world.ServerUtil;
import aporia.cc.implement.events.player.TickEvent;
import aporia.cc.implement.features.draggables.Notifications;
import aporia.cc.implement.features.draggables.StaffList;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AutoLeave extends Module {
    SelectSetting leaveType = new SelectSetting("Leave Type", "Allows you to select the leave type")
            .value("Hub", "Main Menu");

    MultiSelectSetting triggerSetting = new MultiSelectSetting("Triggers", "Select in which case you will exit")
            .value("Players", "Staff");

    ValueSetting distanceSetting = new ValueSetting("Max Distance", "Maximum distance for triggering auto leave")
            .setValue(10).range(5, 40).visible(() -> triggerSetting.isSelected("Players"));

    public AutoLeave() {
        super("AutoLeave", "Auto Leave", ModuleCategory.MISC);
        setup(leaveType, triggerSetting, distanceSetting);
    }

    
    @EventHandler
    public void onTick(TickEvent e) {
        if (ServerUtil.isPvp()) return;

        if (triggerSetting.isSelected("Players"))
            mc.world.getPlayers().stream().filter(p -> mc.player.distanceTo(p) < distanceSetting.getValue() && mc.player != p && !FriendUtils.isFriend(p))
                    .findFirst().ifPresent(p -> leave(p.getName().copy().append(" - Появился рядом " + mc.player.distanceTo(p) + "м")));
        if (triggerSetting.isSelected("Staff") && !StaffList.getInstance().list.isEmpty())
            leave(Text.of("Стафф на сервере"));
    }

    
    public void leave(Text text) {
        switch (leaveType.getSelected()) {
            case "Hub" -> {
                Notifications.getInstance().addList(Text.of("[AutoLeave] ").copy().append(text), 10000);
                mc.getNetworkHandler().sendChatCommand("hub");
            }
            case "Main Menu" ->
                    mc.getNetworkHandler().getConnection().disconnect(Text.of("[Auto Leave] \n").copy().append(text));
        }
        setState(false);
    }

}
