package aporia.cc.api.draggable.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import aporia.cc.api.draggable.api.AbstractDraggable;
import aporia.cc.api.system.font.FontRenderer;
import aporia.cc.api.system.font.Fonts;
import aporia.cc.api.system.shape.ShapeProperties;
import aporia.cc.api.base.common.util.color.ColorUtil;
import aporia.cc.api.base.common.util.math.MathUtil;
import aporia.cc.api.base.common.util.other.StringUtil;
import aporia.cc.api.base.core.Main;

public class Watermark extends AbstractDraggable {
    private int fpsCount = 0;

    public Watermark() {
        super("Watermark", 10, 10, 92, 16,true);
    }

    @Override
    public void tick() {
        fpsCount = (int) MathUtil.interpolate(fpsCount, mc.getCurrentFps());
    }

    @Override
    public void drawDraggable(DrawContext e) {
        MatrixStack matrix = e.getMatrices();
        FontRenderer font = Fonts.getSize(15, Fonts.Type.DEFAULT);

        String offset = "      ";
        String name = Main.getInstance().getClientInfoProvider().clientName() + offset;
        String version = StringUtil.getUserRole() + offset;
        String fps = fpsCount + " FPS";

        blur.render(ShapeProperties.create(matrix, getX(), getY(), getWidth(), getHeight())
                .round(3).softness(1).thickness(2).outlineColor(ColorUtil.getOutline()).color(ColorUtil.getRect(0.7F)).build());
        font.drawGradientString(matrix, name, getX() + 5, getY() + 6.5F, ColorUtil.fade(0), ColorUtil.fade(100));
        font.drawString(matrix, version + fps, getX() + font.getStringWidth(name) + 5, getY() + 6.5F, ColorUtil.getText());
        rectangle.render(ShapeProperties.create(matrix, getX() + font.getStringWidth(name),getY() + 4,0.5F,getHeight() - 8).color(ColorUtil.getOutline(0.75F,0.5f)).build());
        rectangle.render(ShapeProperties.create(matrix, getX() + font.getStringWidth(name + version),getY() + 4,0.5F,getHeight() - 8).color(ColorUtil.getOutline(0.75F,0.5f)).build());
        setWidth((int) (font.getStringWidth(name + version + fps) + 9));
    }
}
