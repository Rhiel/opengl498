package com.jagex;

/**
 * Created by Chris on 4/24/2017.
 */
public class ServerActiveProperties extends Linkable {

    int anInt2444;
    int anInt2452;

    ServerActiveProperties(int i, int i_1_) {
        this.anInt2452 = i;
        this.anInt2444 = i_1_;
    }

    final boolean method93() {
        return 0 != (572878952 & this.anInt2452) >> 29;
    }

    final boolean method100() {
        return (this.anInt2452 & 455226656) >> 28 != 0;
    }

    final boolean method97() {
        return 0 != (1 & this.anInt2452 >> 22);
    }

    final boolean method95() {
        return (0x1 & this.anInt2452) != 0;
    }

    final boolean method99() {
       return -1 != ~((1738913629 & this.anInt2452) >> 30);
    }

    final boolean method96() {
        return (this.anInt2452 >> 31 & 1) != 0;
    }

    final boolean method98() {
        return 0 != (this.anInt2452 >> 21 & 1);
    }

    final int method101() {
        return method630((byte)-34, this.anInt2452);
    }

    static final int method630(byte var0, int var1) {
        return (var1 & 0x3f966) / 2048;
    }

    final int method2876(boolean bool) {
        return method2085(this.anInt2452, false);
    }

    static final int method2085(int i, boolean bool) {
        return (i & 0x3f9af) >> 11;
    }

    public final boolean a(int i) {
        if ((0x1 & this.anInt2452 >> 1 + i) == 0)
            return false;
        return true;
    }

}
