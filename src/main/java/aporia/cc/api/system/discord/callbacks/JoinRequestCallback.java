package aporia.cc.api.system.discord.callbacks;

import com.sun.jna.Callback;
import aporia.cc.api.system.discord.utils.DiscordUser;

public interface JoinRequestCallback extends Callback {
    void apply(DiscordUser var1);
}