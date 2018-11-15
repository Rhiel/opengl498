package com.jagex;

/**
 * Created by Chris on 5/26/2017.
 */
public class PlayerRelations {


    public static long[] ignoreList = new long[100];
    public static RSString[] ignoreListNames = new RSString[100];
    static int ignoreListSize = 0;

    public static final void removeFriend(long lname, byte b) {
        if (lname != 0L) {
            for (int i = 0; i < Class45.friends_count; i++) {
                if ((lname ^ 0xffffffffffffffffL) == (NameHashTable.friends_uid[i] ^ 0xffffffffffffffffL)) {
                    Class45.friends_count--;
                    for (int i_70_ = i; (i_70_ ^ 0xffffffff) > (Class45.friends_count ^ 0xffffffff); i_70_++) {
                        StaticMethods.friends_name[i_70_] = StaticMethods.friends_name[i_70_ + 1];
                        Class23_Sub10_Sub3.friends_worldid[i_70_] = Class23_Sub10_Sub3.friends_worldid[i_70_ + 1];
                        Class87_Sub3.friends_worldname[i_70_] = Class87_Sub3.friends_worldname[i_70_ + 1];
                        NameHashTable.friends_uid[i_70_] = NameHashTable.friends_uid[1 + i_70_];
                        Mouse.friends_rank[i_70_] = Mouse.friends_rank[1 + i_70_];
                        StaticMethods2.aBooleanArray1741[i_70_] = StaticMethods2.aBooleanArray1741[1 + i_70_];
                    }
                    Class75.anInt1372 = SomeSoundClass.anInt3589;
                    GameClient.outBuffer.putOpcode(47);
                    GameClient.outBuffer.putLong(lname, (byte) -108);
                    break;
                }
            }
        }
    }

    public static final void kickUser(RSString class16, boolean bool) {
        if (NameHashTable.currentClanChatUsers != null) {
            long l = class16.toUsernameLong();
            int i = 0;
            if ((l ^ 0xffffffffffffffffL) != -1L) {
                for (/**/; i < NameHashTable.currentClanChatUsers.length; i++) {
                    if (NameHashTable.currentClanChatUsers[i].uid == l) {
                        break;
                    }
                }
                if ((NameHashTable.currentClanChatUsers.length ^ 0xffffffff) < (i ^ 0xffffffff) && NameHashTable.currentClanChatUsers[i] != null) {
                    GameClient.outBuffer.putOpcode(87);
                    if (bool == false) {
                        GameClient.outBuffer.putLong(NameHashTable.currentClanChatUsers[i].uid, (byte) -115);
                    }
                }
            }
        }
    }

    static final void removeFromIgnore(int i, long playerName) {
        if (playerName != 0L) {
            for (int i_73_ = 0; i_73_ < ignoreListSize; i_73_++) {
                if (ignoreList[i_73_] == playerName) {
                    ignoreListSize--;
                    for (int i_74_ = i_73_; ignoreListSize > i_74_; i_74_++) {
                        ignoreList[i_74_] = ignoreList[1 + i_74_];
                        ignoreListNames[i_74_] = ignoreListNames[1 + i_74_];
                    }
                    Class75.anInt1372 = SomeSoundClass.anInt3589;
                    GameClient.outBuffer.putOpcode(13);
                    GameClient.outBuffer.putLong(playerName, (byte) -110);
                    break;
                }
            }
        }
    }
}
