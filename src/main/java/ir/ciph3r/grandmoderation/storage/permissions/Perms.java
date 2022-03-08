package ir.ciph3r.grandmoderation.storage.permissions;

public class Perms {
    public static String STAFF;
    public static String STAFF_EXEMPT;

    public static String STAFF_CHAT;
    public static String STAFF_CHAT_TOGGLE_MUTE;
    public static String STAFF_CHAT_TOGGLE_CHAT;

    public static String STAFF_LIST;

    public void init() {
        STAFF = "grandmoderation.events.staff";
        STAFF_EXEMPT = "grandmoderation.events.staff.exempt";

        STAFF_CHAT = "grandmoderation.commands.staffchat";
        STAFF_CHAT_TOGGLE_MUTE = "grandmoderation.commands.staffchattoggle.mute";
        STAFF_CHAT_TOGGLE_CHAT = "grandmoderation.commands.staffchattoggle.chat";

        STAFF_LIST = "grandmoderation.commands.stafflist";
    }
}
