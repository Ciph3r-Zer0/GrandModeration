package ir.ciph3r.grandmoderation;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.ciph3r.grandmoderation.modules.staffchat.StaffChat;
import ir.ciph3r.grandmoderation.modules.staffchat.StaffChatToggle;
import ir.ciph3r.grandmoderation.storage.permissions.Perms;
import ir.ciph3r.grandmoderation.storage.toml.Config;
import ir.ciph3r.grandmoderation.storage.toml.Messages;
import lombok.Getter;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "grandmoderation",
        name = "GrandModeration",
        version = "1.0.0-Alpha",
        description = "A moderation plugin made for velocity based minecraft servers.",
        authors = {"Ciph3r"}
)
public class GrandModeration {

    private static GrandModeration grandModeration;
    @Getter
    private final ProxyServer proxyServer;
    @Getter
    private final Logger logger;
    @Getter
    private final Path dataDirectory;

    @Inject
    public GrandModeration(ProxyServer proxyServer, Logger logger, @DataDirectory Path dataDirectory) {
        this.proxyServer = proxyServer;
        this.logger = logger;
        this.dataDirectory = dataDirectory;

        grandModeration = this;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        new Config(getDataDirectory(), getClass()).load();
        new Messages(getDataDirectory(), getClass()).load();
        new Perms().init();

        new StaffChat(getProxyServer()).register();
        new StaffChatToggle(getProxyServer()).register();

    }
    public static GrandModeration getInst() {
        return grandModeration;
    }
}
