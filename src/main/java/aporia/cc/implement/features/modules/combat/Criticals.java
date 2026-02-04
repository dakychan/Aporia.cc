package aporia.cc.implement.features.modules.combat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.api.feature.module.setting.implement.SelectSetting;
import aporia.cc.common.util.math.MathUtil;
import aporia.cc.common.util.entity.PlayerIntersectionUtil;
import aporia.cc.common.util.other.Instance;
import aporia.cc.implement.events.player.AttackEvent;
import aporia.cc.implement.features.modules.combat.killaura.rotation.RotationController;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Criticals extends Module {
    public static Criticals getInstance() {
        return Instance.get(Criticals.class);
    }

    SelectSetting mode = new SelectSetting("Mode", "Select bypass mode").value("Grim");

    public Criticals() {
        super("Criticals", ModuleCategory.COMBAT);
        setup(mode);
    }

    @Compile
    @EventHandler
    public void onAttack(AttackEvent e) {
        if (mc.player.isTouchingWater()) return;
        if (mode.isSelected("Grim")) {
            if (!mc.player.isOnGround() && mc.player.fallDistance == 0) {
                PlayerIntersectionUtil.grimSuperBypass$$$(-(mc.player.fallDistance = MathUtil.getRandom(1e-5F, 1e-4F)), RotationController.INSTANCE.getRotation().random(1e-3F));
            }
        }
    }
}