package aporia.cc.implement.features.modules.movement;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.util.math.MathHelper;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.api.feature.module.setting.implement.SelectSetting;
import aporia.cc.implement.events.player.SwimmingEvent;
import aporia.cc.implement.events.player.TickEvent;
import aporia.cc.implement.features.modules.combat.killaura.rotation.RotationController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WaterSpeed extends Module {

    SelectSetting modeSetting = new SelectSetting("Mode", "Select bypass mode").value("FunTime");

    public WaterSpeed() {
        super("WaterSpeed", "Water Speed", ModuleCategory.MOVEMENT);
        setup(modeSetting);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (modeSetting.isSelected("FunTime") && mc.player.isSwimming() && mc.player.isOnGround()) {
            mc.player.jump();
            mc.player.velocity.y = 0.1;
        }
    }

    @Compile
    @EventHandler
    public void onSwimming(SwimmingEvent e) {
        if (modeSetting.isSelected("FunTime")) {
            if (mc.options.jumpKey.isPressed()) {
                float pitch = RotationController.INSTANCE.getRotation().getPitch();
                float boost = pitch >= 0 ? MathHelper.clamp(pitch / 45, 1, 2) : 1;
                e.getVector().y = 1 * boost;
            } else if (mc.options.sneakKey.isPressed()) {
                e.getVector().y = -0.8;
            }
        }
    }
}
