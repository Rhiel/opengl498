package com.jagex.game.runetek4.clientoptions;/* ce - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class IntegerPreferenceField {

    public static boolean b = true;
    public static int e;
    int value;
    public static int f_i;
    GamePreferences preference_store;

    public static long a(long l, long l_0_) {
        return l & l_0_;
    }

    public abstract int a(int i, byte i_1_);

    public abstract void b(int i, byte i_2_);

    public abstract int get_default_value(byte i);

    public final void set_value(int i, boolean bool) {
        e++;
        if (a(i, (byte) 41) != 3)
            b(i, (byte) -110);
    }

    public abstract void validate(int i);

    IntegerPreferenceField(GamePreferences var_gamePreferences) {
        this.preference_store = var_gamePreferences;
        this.value = get_default_value((byte) 110);
    }

    IntegerPreferenceField(int i, GamePreferences var_gamePreferences) {
        this.value = i;
        this.preference_store = var_gamePreferences;
    }
}
