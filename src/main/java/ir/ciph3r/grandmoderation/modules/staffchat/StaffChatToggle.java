package ir.ciph3r.grandmoderation.modules.staffchat;

import com.google.common.eventbus.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.ciph3r.grandmoderation.modules.model.Model;
import ir.ciph3r.grandmoderation.storage.permissions.Perms;
import ir.ciph3r.grandmoderation.storage.toml.Config;
import ir.ciph3r.grandmoderation.storage.toml.Messages;
import ir.ciph3r.grandmoderation.utilities.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffChatToggle extends Model {
    public StaffChatToggle(ProxyServer proxyServer) {
        super(proxyServer, Config.STAFF_CHAT_ENABLED, "StaffChatToggle", "StaffChatToggle", "SCT");
    }
    public static List<UUID> toggleMute = new ArrayList<>();
    public static List<UUID> toggleChat = new ArrayList<>();

    @Override
    public void execute(Invocation invocation) {
        if (!(invocation.source() instanceof Player)) return;

        Player player = (Player) invocation.source();
        String[] args = invocation.arguments();

        if (args.length == 0) {
            Utils.sendMessage(player, Messages.STAFF_CHAT_TOGGLE_USAGE);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("Chat")) {
                if (!(player.hasPermission(Perms.STAFF_CHAT_TOGGLE_CHAT))) {
                    Utils.sendMessage(player, Messages.NOT_PERMISSION);
                    return;
                }

                if (toggleChat.contains(player.getUniqueId())) {
                    toggleChat.remove(player.getUniqueId());
                } else {
                    toggleChat.add(player.getUniqueId());
                }
            } else if (args[0].equalsIgnoreCase("Mute")) {
                if (!(player.hasPermission(Perms.STAFF_CHAT_TOGGLE_MUTE))) {
                    Utils.sendMessage(player, Messages.NOT_PERMISSION);
                    return;
                }

                if (toggleMute.contains(player.getUniqueId())) {
                    toggleMute.remove(player.getUniqueId());
                } else {
                    toggleMute.add(player.getUniqueId());
                }
            } else {
                Utils.sendMessage(player, Messages.STAFF_CHAT_TOGGLE_USAGE);
            }
        }
    }

    @Subscribe
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String msgModel = Config.STAFF_CHAT_FORMAT
                .replace("{message}", event.getMessage())
                .replace("{player}", player.getUsername())
                .replace("{server}", player.getCurrentServer().get().getServerInfo().getName());

        if (toggleChat.contains(player.getUniqueId())) {

            for (Player p : getProxyServer().getAllPlayers()) {
                if (!(p.hasPermission(Perms.STAFF_CHAT))) continue;
                if (!(toggleMute.contains(p.getUniqueId()))) continue;

                Utils.sendMessage(p, msgModel);
            }
        }
    }
}
