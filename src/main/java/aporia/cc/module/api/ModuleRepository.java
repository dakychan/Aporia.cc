package aporia.cc.module.api;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import aporia.cc.module.impl.combat.*;
import aporia.cc.module.impl.misc.*;
import aporia.cc.module.impl.movement.*;
import aporia.cc.module.impl.player.*;
import aporia.cc.module.impl.render.*;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ModuleRepository {
    List<Module> modules = new ArrayList<>();

    public void setup() {
        register(
                new ServerHelper(),
                new WaterSpeed(),
                new ClickAction(),
                new ItemTweaks(),
                new Hud(),
                new AuctionHelper(),
                new ProjectilePrediction(),
                new XRay(),
                new Aura(),
                new AutoSwap(),
                new NoFriendDamage(),
                new HitBoxModule(),
                new AntiBot(),
                new AutoCrystal(),
                new AutoSprint(),
                new NoPush(),
                new ElytraHelper(),
                new ClanUpgrade(),
                new NoDelay(),
                new AutoRespawn(),
                new NoSlow(),
                new ScreenWalk(),
                new ElytraFly(),
                new Blink(),
                new ElytraRecast(),
                new AutoTool(),
                new Nuker(),
                new FastBreak(),
                new CameraTweaks(),
                new HandTweaks(),
                new BlockHighLight(),
                new EntityESP(),
                new AutoTotem(),
                new EnderChestPlus(),
                new DebugCamera(),
                new TriggerBot(),
                new ContainerStealer(),
                new AutoTpAccept(),
                new Arrows(),
                new AutoLeave(),
                new WorldTweaks(),
                new NoRender(),
                new Criticals(),
                new TargetPearl(),
                new NameProtect(),
                new AbilitiesFly(),
                new SeeInvisible(),
                new AutoArmor(),
                new AutoUse(),
                new AirJump(),
                new NoInteract(),
                new CrossHair(),
                new FireWorkBooster(),
                new Spider(),
                new ServerRPSpoofer()
        );
    }

    @Compile
    public void register(Module... module) {
        modules.addAll(List.of(module));
    }

    public List<Module> modules() {
        return modules;
    }
}
