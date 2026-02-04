package aporia.cc.implement.features.modules.render;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import aporia.cc.api.event.EventHandler;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;
import aporia.cc.common.util.color.ColorUtil;
import aporia.cc.common.util.other.Instance;
import aporia.cc.common.util.render.Render3DUtil;
import aporia.cc.implement.events.render.WorldRenderEvent;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockHighLight extends Module {
    public static BlockHighLight getInstance() {
        return Instance.get(BlockHighLight.class);
    }

    public BlockHighLight() {
        super("BlockHighLight", "Block HighLight", ModuleCategory.RENDER);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (mc.crosshairTarget instanceof BlockHitResult result && result.getType().equals(HitResult.Type.BLOCK)) {
            BlockPos pos = result.getBlockPos();
            Render3DUtil.drawShapeAlternative(pos, mc.world.getBlockState(pos).getOutlineShape(mc.world, pos), ColorUtil.getClientColor(), 2, true, true);
        }
    }
}
