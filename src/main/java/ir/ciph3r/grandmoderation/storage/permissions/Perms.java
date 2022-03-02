package ir.ciph3r.grandmoderation.storage.permissions;

public class Perms {
    public static String STAFF_CHAT;
    public static String STAFF_CHAT_TOGGLE_MUTE;
    public static String STAFF_CHAT_TOGGLE_CHAT;

    public void init() {
        STAFF_CHAT = "grandmoderation.commands.staffchat";
        STAFF_CHAT_TOGGLE_MUTE = "grandmoderation.commands.staffchattoggle.mute";
        STAFF_CHAT_TOGGLE_CHAT = "grandmoderation.commands.staffchattoggle.chat";
    }
}
