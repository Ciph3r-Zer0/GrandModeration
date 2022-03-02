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

    public static String STAFF_CHAT_USAGE;
    public static String STAFF_CHAT_TOGGLE_USAGE;

    @Override
    public void init() {
        Toml global = getConfiguration().getTable("Global");
        PREFIX = global.getString("Prefix");
        NOT_PERMISSION = global.getString("NoPermission");

        Toml staffChat = getConfiguration().getTable("StaffChat");
        STAFF_CHAT_USAGE = staffChat.getString("Usage").replace("{prefix}", PREFIX);

        Toml staffChatToggle = getConfiguration().getTable("StaffChat");
        STAFF_CHAT_TOGGLE_USAGE = staffChatToggle.getString("Usage").replace("{prefix}", PREFIX);
    }
}
