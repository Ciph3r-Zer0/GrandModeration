package ir.ciph3r.grandmoderation.utilities;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

public class Utils {

    private static Component colorize(String msg) {
        return Component.text(msg.replace('&', '§'));
    }

    public static void sendMessage(CommandSource source, String msg) {
        source.sendMessage(colorize(msg));
    }

    public static void sendMessage(Player source, String msg) {
        source.sendMessage(colorize(msg));
    }
}
