package aporia.cc.api.draggable.api;

import net.minecraft.client.gui.DrawContext;
import aporia.cc.api.event.impl.container.SetScreenEvent;
import aporia.cc.api.event.impl.packet.PacketEvent;

public interface Draggable {
    boolean visible();

    void tick();

    void render(DrawContext context, int mouseX, int mouseY, float delta);

    void packet(PacketEvent e);

    void setScreen(SetScreenEvent screen);

    boolean mouseClicked(double mouseX, double mouseY, int button);

    boolean mouseReleased(double mouseX, double mouseY, int button);
}
