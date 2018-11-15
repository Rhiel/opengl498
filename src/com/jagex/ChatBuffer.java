package com.jagex;

/**
 * Created with IntelliJ IDEA.
 * User: peterbjornx
 * Date: 7-10-14
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class ChatBuffer {

    public static ChatLine[] buffer = new ChatLine[100];
    public static int chat_message_count;
    public static int chat_message_tracking_counter = 0;
    public static int timestamp_chatbox_update = 0;

    public static void add_message(int type, int flags, RSString name, RSString name_unfiltered, RSString name_simple, RSString clan, int i_4_, RSString message) {
        ChatLine chatline = buffer[99];
        System.arraycopy(buffer, 0, buffer, 1, 99);
        if ( chatline == null )
            chatline = new ChatLine(type, flags, name, name_unfiltered, name_simple, clan, i_4_, message);
        else
            chatline.update(type, flags, name, name_unfiltered, name_simple, clan, i_4_, message);
        chat_message_count++;
        buffer[0] = chatline;
        timestamp_chatbox_update = SomeSoundClass.anInt3589;
    }

    public static void add_message(int type, int flags, RSString name, RSString name_unfiltered, RSString name_simple, RSString message) {
        add_message(type, flags, name, name_unfiltered, name_simple, null, -1, message);
    }

    public static void add_message(int type, RSString message) {
        add_message(type, 0, StaticMethods.empty_string, StaticMethods.empty_string, StaticMethods.empty_string, message);
    }

    public static void add_message(RSString string) {
        add_message(0, 0, StaticMethods.empty_string, StaticMethods.empty_string, StaticMethods.empty_string, string);
    }

    public static int get_new_message_uid() {
        return chat_message_tracking_counter++;
    }

    public static void clear(int i) {
        for ( int i_0_ = i; i_0_ < 100; i_0_++ )
            buffer[i_0_] = null;
        chat_message_count = 0;
    }

    public static int get_chat_message_count() {
        return chat_message_count;
    }

    public static ChatLine get_message(int ptr) {
        if ( ptr < 0 || ptr >= 100 )
            return null;
        return buffer[ptr];
    }
}

