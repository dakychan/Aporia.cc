package aporia.cc.api.event.impl.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import aporia.cc.api.event.events.Event;

public record BlockCollisionEvent(BlockPos blockPos, BlockState state) implements Event {}

