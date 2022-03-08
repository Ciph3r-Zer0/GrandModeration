package ir.ciph3r.grandmoderation.modules.manager;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.ciph3r.grandmoderation.modules.model.Model;
import ir.ciph3r.grandmoderation.storage.permissions.Perms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Manager extends Model {
    public Manager(ProxyServer proxyServer) {
        super(proxyServer, true, "Manager", null, null);
    }

    public static List<UUID> staffList = new ArrayList<>();

    @Subscribe
    public void onJoin(PostLoginEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission(Perms.STAFF)) {
            staffList.add(player.getUniqueId());
        }
    }

    @Subscribe
    public void onJoin(DisconnectEvent event) {
        Player player = event.getPlayer();

        staffList.remove(player.getUniqueId());
    }

    @Override
    public void execute(Invocation invocation) {}
}
