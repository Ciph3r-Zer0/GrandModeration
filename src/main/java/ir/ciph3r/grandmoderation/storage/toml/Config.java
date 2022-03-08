package ir.ciph3r.grandmoderation.storage.toml;

import com.moandjiezana.toml.Toml;
import ir.ciph3r.grandmoderation.storage.toml.model.Model;

import java.nio.file.Path;

public class Config extends Model {
    public Config(Path path, Class<?> clazz) {
        super(path, clazz, "config.toml");
    }

    public static boolean STAFF_CHAT_ENABLED;
    public static String STAFF_CHAT_FORMAT;

    public static boolean STAFF_LIST_ENABLED;
    public static String STAFF_LIST_FIRST_LINE_FORMAT;
    public static String STAFF_LIST_LIST_FORMAT;

    @Override
    public void init() {
        Toml staffChat = getConfiguration().getTable("StaffChat");
        STAFF_CHAT_ENABLED = staffChat.getBoolean("Enabled");
        STAFF_CHAT_FORMAT = staffChat.getString("Format");

        Toml staffList = getConfiguration().getTable("StaffList");
        STAFF_LIST_ENABLED = staffList.getBoolean("Enabled");
        STAFF_LIST_FIRST_LINE_FORMAT = staffList.getString("FirstLineFormat");
        STAFF_LIST_LIST_FORMAT = staffList.getString("ListFormat");

    }
}
