package ir.ciph3r.grandmoderation.modules.stafflist;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.ciph3r.grandmoderation.modules.manager.Manager;
import ir.ciph3r.grandmoderation.modules.model.Model;
import ir.ciph3r.grandmoderation.storage.permissions.Perms;
import ir.ciph3r.grandmoderation.storage.toml.Config;
import ir.ciph3r.grandmoderation.storage.toml.Messages;
import ir.ciph3r.grandmoderation.utilities.Utils;

import java.util.List;

public class StaffList extends Model {
    public StaffList(ProxyServer proxyServer) {
        super(proxyServer, Config.STAFF_LIST_ENABLED, "StaffList", "StaffList", "SList");
    }

    @Override
    public void execute(Invocation invocation) {
        if (!(invocation.source().hasPermission(Perms.STAFF_LIST))) {
            Utils.sendMessage(invocation.source(), Messages.NOT_PERMISSION);
            return;
        }
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (args.length == 0) {
            List<Player> staffs = Utils.getPlayersByUUID(Manager.staffList);
            Utils.sendMessage(source, Config.STAFF_LIST_FIRST_LINE_FORMAT);
            for (Player p : staffs) {
                if (p.hasPermission(Perms.STAFF_EXEMPT)) continue;
                Utils.sendMessage(source, Config.STAFF_LIST_LIST_FORMAT
                        .replace("{server}", p.getCurrentServer().get().getServerInfo().getName())
                        .replace("{player}", p.getUsername()));
            }
        }
    }
}
