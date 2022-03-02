package ir.ciph3r.grandmoderation.storage.toml;

import com.moandjiezana.toml.Toml;
import ir.ciph3r.grandmoderation.storage.toml.model.Model;

import java.nio.file.Path;

public class Messages extends Model {
    public Messages(Path path, Class<?> clazz) {
        super(path, clazz, "messages.toml");
    }

    public static String PREFIX;
    public static String NOT_PERMISSION;
    public static String NO_CONSOLE;

    public static String STAFF_CHAT_USAGE;
    public static String STAFF_CHAT_TOGGLE_USAGE;
    public static String STAFF_CHAT_TOGGLE_CHAT_ENABLE;
    public static String STAFF_CHAT_TOGGLE_CHAT_DISABLE;
    public static String STAFF_CHAT_TOGGLE_MUTE_ENABLE;
    public static String STAFF_CHAT_TOGGLE_MUTE_DISABLE;

    @Override
    public void init() {
        Toml global = getConfiguration().getTable("Global");
        PREFIX = global.getString("Prefix");
        NOT_PERMISSION = global.getString("NoPermission");
        NO_CONSOLE = global.getString("NoConsole");

        Toml staffChat = getConfiguration().getTable("StaffChat");
        Toml staffChatToggle = getConfiguration().getTable("StaffChat.Toggle");
        Toml staffChatToggleChat = getConfiguration().getTable("StaffChat.Toggle.Chat");
        Toml staffChatToggleMute = getConfiguration().getTable("StaffChat.Toggle.Mute");
        STAFF_CHAT_USAGE = staffChat.getString("Usage").replace("{prefix}", PREFIX);
        STAFF_CHAT_TOGGLE_USAGE = staffChatToggle.getString("Usage").replace("{prefix}", PREFIX);
        STAFF_CHAT_TOGGLE_CHAT_ENABLE = staffChatToggleChat.getString("EnableMessage").replace("{prefix}", PREFIX);
        STAFF_CHAT_TOGGLE_CHAT_DISABLE = staffChatToggleChat.getString("DisableMessage").replace("{prefix}", PREFIX);
        STAFF_CHAT_TOGGLE_MUTE_ENABLE = staffChatToggleMute.getString("EnableMessage").replace("{prefix}", PREFIX);
        STAFF_CHAT_TOGGLE_MUTE_DISABLE = staffChatToggleMute.getString("DisableMessage").replace("{prefix}", PREFIX);

    }
}
