package aporia.cc.implement.features.draggables;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import aporia.cc.api.system.font.FontRenderer;
import aporia.cc.common.util.color.ColorUtil;
import aporia.cc.common.util.world.ServerUtil;
import aporia.cc.common.util.math.MathUtil;
import aporia.cc.common.util.entity.MovingUtil;
import aporia.cc.api.feature.draggable.AbstractDraggable;
import aporia.cc.api.system.font.Fonts;
import aporia.cc.common.util.entity.PlayerIntersectionUtil;

import java.util.Objects;

public class PlayerInfo extends AbstractDraggable {

    public PlayerInfo() {
        super("Player Info", 0, 0, 60, 0,false);
    }

    @Override
    public void drawDraggable(DrawContext context) {
        int offset = PlayerIntersectionUtil.isChat(mc.currentScreen) ? -13 : 0;
        BlockPos blockPos = Objects.requireNonNull(mc.player).getBlockPos();
        FontRenderer font = Fonts.getSize(15);

        setY(window.getScaledHeight() + offset);

        String bps = "bps: " + Formatting.RED + MathUtil.round(MovingUtil.getSpeedSqrt(mc.player) * 20.0F, 0.1F) + Formatting.RESET + "\n";
        String tps = "tps: " + Formatting.GOLD + MathUtil.round(ServerUtil.TPS,0.1) + Formatting.RESET + "\n";
        String xyz = "xyz: " + Formatting.GREEN + blockPos.getX() + Formatting.RESET + ", " + Formatting.GREEN + blockPos.getY() + Formatting.RESET + ", " + Formatting.GREEN + blockPos.getZ() + Formatting.RESET;
        font.drawString(context.getMatrices(), bps + tps + xyz,3,getY() - 24, ColorUtil.getText());
    }
}
