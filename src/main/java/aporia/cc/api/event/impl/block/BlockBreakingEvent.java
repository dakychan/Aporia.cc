package aporia.cc.api.event.impl.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import aporia.cc.api.event.events.Event;

public record BlockBreakingEvent(BlockPos blockPos, Direction direction) implements Event {}
