package aporia.cc.module.impl.combat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.util.math.*;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.event.types.EventType;
import aporia.cc.module.api.Module;
import aporia.cc.module.api.ModuleCategory;
import aporia.cc.module.api.setting.implement.*;
import aporia.cc.api.system.animation.Animation;
import aporia.cc.api.system.animation.Direction;
import aporia.cc.api.system.animation.implement.DecelerateAnimation;
import aporia.cc.api.base.common.util.other.Instance;
import aporia.cc.api.base.common.util.render.Render3DUtil;
import aporia.cc.api.base.common.util.task.TaskPriority;
import aporia.cc.api.base.core.Main;
import aporia.cc.api.event.impl.packet.PacketEvent;
import aporia.cc.api.event.impl.player.RotationUpdateEvent;
import aporia.cc.api.event.impl.render.WorldRenderEvent;
import aporia.cc.api.draggable.impl.Notifications;
import aporia.cc.module.impl.combat.killaura.attack.AttackHandler;
import aporia.cc.module.impl.combat.killaura.attack.AttackPerpetrator;
import aporia.cc.module.impl.combat.killaura.rotation.*;
import aporia.cc.module.impl.combat.killaura.rotation.angle.*;
import aporia.cc.module.impl.combat.killaura.target.TargetSelector;
import aporia.cc.module.impl.render.Hud;

import java.util.Objects;


@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Aura extends Module {
    public static Aura getInstance() {
        return Instance.get(Aura.class);
    }

    Animation esp_anim = new DecelerateAnimation().setMs(400).setValue(1);
    TargetSelector targetSelector = new TargetSelector();
    PointFinder pointFinder = new PointFinder();
    @NonFinal
    LivingEntity target, lastTarget;
    Float maxDistance = 3.3f;

    MultiSelectSetting targetType = new MultiSelectSetting("Target Type", "Filters the entire list of targets by type")
            .value("Players", "Mobs", "Animals", "Friends");

    MultiSelectSetting attackSetting = new MultiSelectSetting("Attack Setting", "Allows you to customize the attack")
            .value("Only Critical", "Dynamic Cooldown", "Break Shield", "UnPress Shield", "No Attack When Eat", "Ignore The Walls");

    SelectSetting correctionType = new SelectSetting("Correction Type", "Selects the type of correction")
            .value("Free", "Focused").selected("Free");

    GroupSetting correctionGroup = new GroupSetting("Move correction", "Prevents detection by movement sensitive anti-cheats")
            .settings(correctionType).setValue(true);

    SelectSetting aimMode = new SelectSetting("Rotation Type", "Allows you to select the rotation type")
            .value("FunTime", "Snap", "Matrix").selected("Snap");

    SelectSetting targetEspType = new SelectSetting("Target Esp Type", "Selects the type of target esp")
            .value("Cube", "Circle", "Ghosts").selected("Circle");

    ValueSetting ghostSpeed = new ValueSetting("Ghost Speed", "Speed of ghost flying around the target")
            .setValue(1).range(1F, 2F).visible(()-> targetEspType.isSelected("Ghosts"));

    GroupSetting targetEspGroup = new GroupSetting("Target Esp", "Displays the player in the world")
            .settings(targetEspType, ghostSpeed).setValue(true);

    public Aura() {
        super("Aura", ModuleCategory.COMBAT);
        setup(targetType, attackSetting, correctionGroup, aimMode, targetEspGroup);
    }

    @Override
    public void deactivate() {
        targetSelector.releaseTarget();
        target = null;
        super.deactivate();
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        esp_anim.setDirection(target != null ? Direction.FORWARDS : Direction.BACKWARDS);
        float anim = esp_anim.getOutput().floatValue();
        if (targetEspGroup.isValue() && lastTarget != null && !esp_anim.isFinished(Direction.BACKWARDS)) {
            float red = MathHelper.clamp((lastTarget.hurtTime - tickCounter.getTickDelta(false)) / 10, 0, 1);
            switch (targetEspType.getSelected()) {
                case "Cube" -> Render3DUtil.drawCube(lastTarget, anim, red);
                case "Circle" -> Render3DUtil.drawCircle(e.getStack(), lastTarget, anim, red);
                case "Ghosts" -> Render3DUtil.drawGhosts(lastTarget, anim, red, ghostSpeed.getValue());
            }
        }
    }

    
    @EventHandler
    public void onPacket(PacketEvent e) {
        if (e.getPacket() instanceof EntityStatusS2CPacket status && status.getStatus() == 30) {
            Entity entity = status.getEntity(mc.world);
            if (entity != null && entity.equals(target) && Hud.getInstance().notificationSettings.isSelected("Break Shield")) {
                Notifications.getInstance().addList(Text.literal("Сломали щит игроку - ").append(entity.getDisplayName()), 3000);
            }
        }
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        switch (e.getType()) {
            case EventType.PRE -> {
                target = updateTarget();
                if (target != null) {
                    rotateToTarget(getConfig());
                    lastTarget = target;
                }
            }
            case EventType.POST -> {
                Render3DUtil.updateTargetEsp();
                if (target != null) Main.getInstance().getAttackPerpetrator().performAttack(getConfig());
            }
        }
    }

    private LivingEntity updateTarget() {
        TargetSelector.EntityFilter filter = new TargetSelector.EntityFilter(targetType.getSelected());
        targetSelector.searchTargets(mc.world.getEntities(), maxDistance, 360, attackSetting.isSelected("Ignore The Walls"));
        targetSelector.validateTarget(filter::isValid);
        return targetSelector.getCurrentTarget();
    }

    
    private void rotateToTarget(AttackPerpetrator.AttackPerpetratorConfigurable config) {
        AttackHandler attackHandler = Main.getInstance().getAttackPerpetrator().getAttackHandler();
        RotationController controller = RotationController.INSTANCE;
        Angle.VecRotation rotation = new Angle.VecRotation(config.getAngle(), config.getAngle().toVector());
        RotationConfig rotationConfig = getRotationConfig();
        switch (aimMode.getSelected()) {
            case "Snap" -> {
                if (attackHandler.canAttack(config, 1) || !attackHandler.getAttackTimer().finished(100)) {
                    controller.rotateTo(rotation, target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
                }
            }
            case "FunTime" -> {
                if (attackHandler.canAttack(config, 3)) {
                    controller.clear();
                    controller.rotateTo(rotation, target, 40, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
                }
            }
            case "Matrix" -> controller.rotateTo(rotation, target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
        }
    }

    public AttackPerpetrator.AttackPerpetratorConfigurable getConfig() {
        Pair<Vec3d, Box> point = pointFinder.computeVector(target, maxDistance, RotationController.INSTANCE.getRotation(), getSmoothMode().randomValue(), attackSetting.isSelected("Ignore The Walls"));
        Angle angle = AngleUtil.fromVec3d(point.getLeft().subtract(Objects.requireNonNull(mc.player).getEyePos()));
        Box box = point.getRight();
        return new AttackPerpetrator.AttackPerpetratorConfigurable(target, angle, maxDistance, attackSetting.getSelected(), aimMode, box);
    }

    public RotationConfig getRotationConfig() {
        return new RotationConfig(getSmoothMode(), correctionGroup.isValue(), correctionType.isSelected("Free"));
    }

    
    public AngleSmoothMode getSmoothMode() {
        return switch (aimMode.getSelected()) {
            case "FunTime" -> new FunTimeSmoothMode();
            case "Matrix" -> new MatrixSmoothMode();
            case "Snap" -> new SnapSmoothMode();
            default -> new LinearSmoothMode();
        };
    }
}