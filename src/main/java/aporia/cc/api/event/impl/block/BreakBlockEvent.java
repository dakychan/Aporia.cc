package aporia.cc.api.event.impl.block;

import net.minecraft.util.math.BlockPos;
import aporia.cc.api.event.events.Event;

public record BreakBlockEvent(BlockPos blockPos) implements Event {}
