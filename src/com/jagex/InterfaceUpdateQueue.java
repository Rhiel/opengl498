package com.jagex;

/**
 * Created by Chris on 4/27/2017.
 */
public class InterfaceUpdateQueue extends Queuable {
    int t;
    public static int u;
    public static int r;
    int z;
    public static int y;
    public static int v;
    RSString x;
    public static int w;
    int anInt3596;
    public static Queue queue = new Queue();
    public static Queue f = new Queue();

    public final long d(int i) {
        if (i != -1759803272) f(107);
        y++;
        return 0x7fffffffffffffffL & queue_id;
    }

    public final void b(int i) {
        if (i != -139247624)
            d(-35);
        r++;
        queue_id |= ~0x7fffffffffffffffL;
        if (d(-1759803272) == 0L)
            f.add_last(this);
    }

    public final int f(int i) {
        u++;
        if (i != 21553)
            a();
        return (int) (0xffL & ((Linkable) this).uid >>> 56);
    }

    public final long a() {
        return uid & 0xffffffffffffffL;
    }

    public final void e(int i) {

        queue_id = 500L + TimeTools.getMillis() | queue_id & ~0x7fffffffffffffffL;
        queue.add_last(this);
    }

    public InterfaceUpdateQueue(int i, long l) {
        uid = (long) i << 56 | l;
    }

    public static final InterfaceUpdateQueue a(int i) {
        InterfaceUpdateQueue var_iu = (InterfaceUpdateQueue) f.get_first();
        if (var_iu != null) {
            var_iu.unlink();
            var_iu.unlink_queue();
            return var_iu;
        }
        do {
            var_iu = (InterfaceUpdateQueue) queue.get_first();
            if (var_iu == null)
                return null;
            if ((var_iu.d(-1759803272) ^ 0xffffffffffffffffL) < (TimeTools.getMillis() ^ 0xffffffffffffffffL))
                return null;
            var_iu.unlink();
            var_iu.unlink_queue();
        } while ((~0x7fffffffffffffffL & ((Queuable) var_iu).queue_id
                ^ 0xffffffffffffffffL)
                == -1L);
        return var_iu;
    }

    public static final void insertInterfaceHash(int i, int i_7_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 21);
        var_iu.e(-139247624);
    }

    public static final void insertHash1(boolean bool, int i) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 8);
        var_iu.e(-139247624);
    }

    public static final void insertHash2(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 3);
        var_iu.e(-139247624);
    }

    public static final void insertHash3(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i_0_, 5);
        var_iu.e(-139247624);
    }

    public static final void insertModelHash(int i, boolean bool) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 10);
        var_iu.e(-139247624);
    }

    public static final void insertSpriteHash(int i, int i_5_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 14);
        var_iu.e(-139247624);
    }

    public static final void insertColorHash(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 6);
        var_iu.e(-139247624);
    }

    public static final void insertScollHash(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i_0_, i);
        var_iu.e(-139247624);
    }

    public static final void insertFontHash(int i, int i_27_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 16);
        var_iu.e(-139247624);
    }

    public static final void insertPositionHash(int i, boolean bool) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 11);
        var_iu.e(-139247624);
    }

    public static final void insertHiddenHash(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i_0_, i);
        var_iu.e(i ^ ~0x84cc000);
    }

    public static final void insertMediaHash(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 9);
        var_iu.e(-139247624);
    }

    public static final void insertGlobalHash(int i, int i_0_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 1);
        var_iu.e(-139247624);
    }

    public static final void insertGlobalStringHash(int i, int i_2_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i_2_, 2);
        if (i == 19416)
            var_iu.e(-139247624);
    }

    public static final void insertNewConfig(int i, byte i_6_, int i_7_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i_7_, 1);
        var_iu.b(i_6_ ^ ~0x84cc048);
        var_iu.z = i;
    }

    public static void insertGlobalStringPacketHash(RSString string, int i, int i_16_) {
        InterfaceUpdateQueue var_iu = RSInterfaceManager.method2861(i, 2);
        var_iu.b(i_16_ + -139247535);
        if (i_16_ == -89) var_iu.x = string;
    }

}
