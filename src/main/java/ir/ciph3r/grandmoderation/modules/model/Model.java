package ir.ciph3r.grandmoderation.modules.model;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.ciph3r.grandmoderation.GrandModeration;
import lombok.Getter;

public abstract class Model implements SimpleCommand {
    @Getter
    private ProxyServer proxyServer;
    @Getter
    private boolean Enabled;
    @Getter
    private String moduleName;
    @Getter
    private String commandName;
    @Getter
    private String[] aliases;

    public Model(ProxyServer proxyServer, boolean Enabled, String moduleName, String commandName, String... aliases) {
        this.moduleName = moduleName;
        this.commandName = commandName;
        this.Enabled = Enabled;
        this.proxyServer = proxyServer;
        this.aliases = aliases;
    }

    public void register() {
        if (isEnabled()) {
            proxyServer.getEventManager().register(GrandModeration.getInst(), this);
            CommandManager manager = proxyServer.getCommandManager();
            if (aliases == null) {
                CommandMeta meta = manager.metaBuilder(commandName)
                        .build();
                manager.register(meta, this);
            } else {
                CommandMeta meta = manager.metaBuilder(commandName)
                        .aliases(aliases)
                        .build();
                manager.register(meta, this);
            }
        }
    }
}
