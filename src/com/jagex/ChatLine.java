package com.jagex;

/**
 * Created by Chris on 5/1/2017.
 */
public class ChatLine {

    public        RSString message;
    public        int    j;
    public        int    flags;
    public static int    update_callcount;
    public        int    type;
    public        RSString name_simple;
    public        RSString name;
    public        int    time;
    public        RSString name_unfiltered;
    public        RSString clan;
    public        int    k;
    public static int xMapSize;

    public final void update(int type, int flags, RSString name, RSString name_unfiltered, RSString name_simple, RSString clan, int i_5_, RSString message) {
        update_callcount++;
        this.k = ChatBuffer.get_new_message_uid();
        this.j = i_5_;
        this.name = name;
        this.clan = clan;
        this.time = GameClient.timer;
        this.message = message;
        this.type = type;
        this.name_unfiltered = name_unfiltered;
        this.name_simple = name_simple;
        this.flags = flags;
    }

    public ChatLine(int type, int flags, RSString name, RSString name_unfiltered, RSString name_simple, RSString clan, int i_14_, RSString message) {
        k = ChatBuffer.get_new_message_uid();
        this.name = name;
        this.type = type;
        this.clan = clan;
        this.message = message;
        this.name_simple = name_simple;
        this.j = i_14_;
        this.flags = flags;
        this.name_unfiltered = name_unfiltered;
        this.time = GameClient.timer;
    }

}
