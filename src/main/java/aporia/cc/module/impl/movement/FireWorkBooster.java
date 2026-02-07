package aporia.cc.module.impl.movement;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.util.math.Vec3d;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.event.EventHandler;
import aporia.cc.module.api.Module;
import aporia.cc.module.api.ModuleCategory;
import aporia.cc.module.api.setting.implement.SelectSetting;
import aporia.cc.api.event.impl.player.FireworkEvent;
import aporia.cc.module.impl.combat.killaura.rotation.RotationController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FireWorkBooster extends Module {
    SelectSetting modeSetting = new SelectSetting("Mode", "Selects the type of mode")
            .value("Grim");

    public FireWorkBooster() {
        super("FireWorkBooster", "FireWork Booster", ModuleCategory.MOVEMENT);
        setup(modeSetting);
    }

    @Compile
    @EventHandler
    public void onFirework(FireworkEvent e) {
        if (modeSetting.isSelected("Grim")) {
            int ff = RotationController.INSTANCE.getRotation().getYaw() > 0F ? 45 : -45;
            double acceleration = Math.abs((RotationController.INSTANCE.getRotation().getYaw() + ff) % 90 - ff) / 45, boost = 1 + (0.3 * acceleration * acceleration);
            boolean yAcceleration = Math.abs(RotationController.INSTANCE.getMoveRotation().getPitch()) > 60;
            Vec3d vec3d = e.getVector();
            e.setVector(new Vec3d(vec3d.x * boost, yAcceleration ? vec3d.y * boost : vec3d.y, vec3d.z * boost));
        }
    }
}
