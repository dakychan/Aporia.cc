package aporia.cc.api.system.font.entry;

import aporia.cc.api.system.font.glyph.Glyph;

public record DrawEntry(float atX, float atY, int color, Glyph toDraw) {
}
