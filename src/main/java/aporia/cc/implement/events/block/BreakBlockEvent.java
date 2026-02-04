package aporia.cc.implement.events.block;

import net.minecraft.util.math.BlockPos;
import aporia.cc.api.event.events.Event;

public record BreakBlockEvent(BlockPos blockPos) implements Event {}
