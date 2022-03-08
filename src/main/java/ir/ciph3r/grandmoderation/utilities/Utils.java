package ir.ciph3r.grandmoderation.utilities;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import ir.ciph3r.grandmoderation.GrandModeration;
import ir.ciph3r.grandmoderation.modules.manager.Manager;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public static List<Player> getPlayersByUUID(List<UUID> uuids) {
        List<Player> players = new ArrayList<>();
        for (UUID uuid : uuids) {
            Optional<Player> player = GrandModeration.getInst().getProxyServer().getPlayer(uuid);
            if (player.isPresent()) {
                players.add(player.get());
            } else {
                Manager.staffList.remove(uuid);
            }
        }
        return players;
    }
}
