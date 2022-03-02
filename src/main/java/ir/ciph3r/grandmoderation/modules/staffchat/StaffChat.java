package ir.ciph3r.grandmoderation.modules.staffchat;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.ciph3r.grandmoderation.modules.model.Model;
import ir.ciph3r.grandmoderation.storage.permissions.Perms;
import ir.ciph3r.grandmoderation.storage.toml.Config;
import ir.ciph3r.grandmoderation.storage.toml.Messages;
import ir.ciph3r.grandmoderation.utilities.Utils;

public class StaffChat extends Model {
    public StaffChat(ProxyServer proxyServer) {
        super(proxyServer, Config.STAFF_CHAT_ENABLED, "StaffChat", "StaffChat", "SC");
    }

    @Override
    public void execute(Invocation invocation) {
        if (!(invocation.source() instanceof Player)) return;
        if (!(invocation.source().hasPermission(Perms.STAFF_CHAT))) {
            Utils.sendMessage(invocation.source(), Messages.NOT_PERMISSION);
            return;
        }
        Player player = (Player) invocation.source();
        String[] args = invocation.arguments();

        if (args.length == 0) {
            Utils.sendMessage(player, Messages.STAFF_CHAT_USAGE);
        } else {
            StringBuilder builder = new StringBuilder();
            for (String arg : args) {
                builder.append(arg).append(" ");
            }
            String msgModel = Config.STAFF_CHAT_FORMAT
                    .replace("{message}", builder.toString())
                    .replace("{player}", player.getUsername())
                    .replace("{server}", player.getCurrentServer().get().getServerInfo().getName());

            for (Player p : getProxyServer().getAllPlayers()) {
                if (!(p.hasPermission(Perms.STAFF_CHAT))) continue;
                if (StaffChatToggle.toggleMute.contains(p.getUniqueId())) continue;

                Utils.sendMessage(p, msgModel);
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

        if (StaffChatToggle.toggleChat.contains(player.getUniqueId())) {
            event.setResult(PlayerChatEvent.ChatResult.denied());

            for (Player p : getProxyServer().getAllPlayers()) {
                if (!(p.hasPermission(Perms.STAFF_CHAT))) continue;
                if (!(StaffChatToggle.toggleMute.contains(p.getUniqueId()))) continue;

                Utils.sendMessage(p, msgModel);
            }
        }
    }
}
