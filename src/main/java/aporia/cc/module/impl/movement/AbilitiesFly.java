package aporia.cc.module.impl.movement;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.util.math.Vec3d;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.event.EventHandler;
import aporia.cc.module.api.Module;
import aporia.cc.module.api.ModuleCategory;
import aporia.cc.module.api.setting.implement.ValueSetting;
import aporia.cc.api.base.common.util.entity.MovingUtil;
import aporia.cc.api.event.impl.player.MoveEvent;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AbilitiesFly extends Module {
    ValueSetting speedSetting = new ValueSetting("Speed", "Select fly speed").setValue(2.0F).range(0.5F, 4.0F);

    public AbilitiesFly() {
        super("AbilitiesFly", "Abilities Fly", ModuleCategory.MOVEMENT);
        setup(speedSetting);
    }
    @Compile
    @EventHandler
    public void onMove(MoveEvent e) {
        if (mc.player != null && mc.player.getAbilities().flying) {
            float speed = speedSetting.getValue();
            float y = mc.player.isSneaking() ? -speed : mc.player.jumping ? speed : 0;
            double[] motion = MovingUtil.calculateDirection(speed);
            e.setMovement(new Vec3d(motion[0], y, motion[1]));
        }
    }
}
