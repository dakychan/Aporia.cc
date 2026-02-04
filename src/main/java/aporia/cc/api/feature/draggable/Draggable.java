package aporia.cc.api.feature.draggable;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import aporia.cc.implement.events.container.SetScreenEvent;
import aporia.cc.implement.events.packet.PacketEvent;
import aporia.cc.implement.features.modules.render.Hud;

public interface Draggable {
    boolean visible();

    void tick();

    void render(DrawContext context, int mouseX, int mouseY, float delta);

    void packet(PacketEvent e);

    void setScreen(SetScreenEvent screen);

    boolean mouseClicked(double mouseX, double mouseY, int button);

    boolean mouseReleased(double mouseX, double mouseY, int button);
}
