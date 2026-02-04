package aporia.cc.implement.features.modules.player;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.api.feature.module.setting.implement.SelectSetting;
import aporia.cc.api.event.EventHandler;
import aporia.cc.common.util.world.ServerUtil;
import aporia.cc.implement.events.packet.PacketEvent;
import aporia.cc.implement.events.player.DeathScreenEvent;

@SuppressWarnings("all")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AutoRespawn extends Module {

    SelectSetting modeSetting = new SelectSetting("Mode", "Choose what will be used").value("FunTime Back", "Default");

    public AutoRespawn() {
        super("AutoRespawn", "Auto Respawn", ModuleCategory.PLAYER);
        setup(modeSetting);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        switch (e.getPacket()) {
            case DeathMessageS2CPacket message when ServerUtil.getWorldType().equals("lobby") && modeSetting.isSelected("FunTime Back") -> {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(1448, 1337, 228, false, false));
                mc.player.requestRespawn();
                mc.player.closeScreen();
            }
            default -> {
            }
        }
    }

    @Compile
    @EventHandler
    public void onDeathScreen(DeathScreenEvent e) {
        if (modeSetting.isSelected("Default")) {
            mc.player.requestRespawn();
            mc.setScreen(null);
        }
    }
}
