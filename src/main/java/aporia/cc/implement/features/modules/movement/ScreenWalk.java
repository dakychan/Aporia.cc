package aporia.cc.implement.features.modules.movement;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import aporia.cc.api.event.EventHandler;
import aporia.cc.common.util.entity.MovingUtil;
import aporia.cc.common.util.entity.PlayerIntersectionUtil;
import aporia.cc.common.util.entity.PlayerInventoryComponent;

import aporia.cc.common.util.entity.PlayerInventoryUtil;
import aporia.cc.implement.events.container.CloseScreenEvent;
import aporia.cc.implement.events.item.ClickSlotEvent;
import aporia.cc.implement.events.packet.PacketEvent;
import aporia.cc.implement.events.player.TickEvent;
import aporia.cc.api.feature.module.Module;
import aporia.cc.api.feature.module.ModuleCategory;

import java.util.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScreenWalk extends Module {
    private final List<Packet<?>> packets = new ArrayList<>();

    public ScreenWalk() {
        super("ScreenWalk", "Screen Walk", ModuleCategory.MOVEMENT);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        switch (e.getPacket()) {
            case ClickSlotC2SPacket slot when (!packets.isEmpty() || MovingUtil.hasPlayerMovement()) && PlayerInventoryComponent.shouldSkipExecution() -> {
                packets.add(slot);
                e.cancel();
            }
            case CloseScreenS2CPacket screen when screen.getSyncId() == 0 -> e.cancel();
            default -> {
            }
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (!PlayerInventoryUtil.isServerScreen() && PlayerInventoryComponent.shouldSkipExecution() && (!packets.isEmpty() || mc.player.currentScreenHandler.getCursorStack().isEmpty())) {
            PlayerInventoryComponent.updateMoveKeys();
        }
    }

    @EventHandler
    public void onClickSlot(ClickSlotEvent e) {
        SlotActionType actionType = e.getActionType();
        if ((!packets.isEmpty() || MovingUtil.hasPlayerMovement()) && ((e.getButton() == 1 && !actionType.equals(SlotActionType.SWAP) && !actionType.equals(SlotActionType.THROW)) || actionType.equals(SlotActionType.PICKUP_ALL))) {
            e.cancel();
        }
    }

    @EventHandler
    public void onCloseScreen(CloseScreenEvent e) {
        if (!packets.isEmpty()) PlayerInventoryComponent.addTask(() -> {
            packets.forEach(PlayerIntersectionUtil::sendPacketWithOutEvent);
            packets.clear();
            PlayerInventoryUtil.updateSlots();
        });
    }
}
