package com.jagex;
/* Class28 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class28 {
    static RSString aClass16_423 = RSString.createString("<col=ffffff> )4 ");
    public static int[] anIntArray425;
    static Class33 aClass33_428;
    public static RSString aClass16_430 = RSString.createString(" has logged in)3");
    static RSString aClass16_431 = aClass16_430;
    static int anInt432;

    public static void method938(int i) {
        aClass16_423 = null;
        aClass16_431 = null;
        aClass33_428 = null;
        aClass16_430 = null;
        anIntArray425 = null;
        if (i < 53) {
            aClass16_431 = null;
        }
    }

    static final void method940(int i, int i_5_, int i_6_, int i_7_, byte b, int i_8_, int i_9_, int i_10_) {
        if ((i_7_ ^ 0xffffffff) == (i_5_ ^ 0xffffffff)) {
            StaticMethods2.method1528(i_10_, i_9_, i_6_, i_8_, i, true, i_7_);
        } else if (b == 120) {
            if (-i_7_ + i >= VarpDefinition.anInt3728 && i_7_ + i <= Class35.anInt554 && (i_9_ + -i_5_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff) && StaticMethods.anInt3435 >= i_5_ + i_9_) {
                Class56.method1186(i_6_, true, i, i_10_, i_7_, i_9_, i_8_, i_5_);
            } else {
                Class95.method1467(i_9_, i_5_, i_8_, i_7_, i_6_, -13805, i, i_10_);
            }
        }
    }


}
