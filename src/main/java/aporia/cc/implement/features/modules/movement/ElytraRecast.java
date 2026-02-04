package aporia.cc.implement.features.modules.movement;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.api.feature.module.setting.implement.BooleanSetting;
import aporia.cc.common.util.entity.MovingUtil;
import aporia.cc.common.util.entity.PlayerIntersectionUtil;
import aporia.cc.common.util.task.TaskPriority;
import aporia.cc.implement.events.player.InputEvent;
import aporia.cc.implement.features.modules.combat.killaura.rotation.AngleUtil;
import aporia.cc.implement.features.modules.combat.killaura.rotation.RotationConfig;
import aporia.cc.implement.features.modules.combat.killaura.rotation.RotationController;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ElytraRecast extends Module {

    public ElytraRecast() {super("ElytraRecast", "Elytra Recast", ModuleCategory.MOVEMENT);}

    @Compile
    @EventHandler
    public void onInput(InputEvent e) {
        if (mc.player.getEquippedStack(EquipmentSlot.CHEST).getItem().equals(Items.ELYTRA) && MovingUtil.hasPlayerMovement()) {
            if (mc.player.isOnGround()) e.setJumping(true);
            else if (!mc.player.isGliding()) PlayerIntersectionUtil.startFallFlying();
        }
    }
}
