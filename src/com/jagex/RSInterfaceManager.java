package com.jagex;

/**
 * Created by Chris on 4/27/2017.
 */
public class RSInterfaceManager {

    public static int f;
    public static HashTable s = new HashTable(16);

    public static final void a(int i, int id_dword) {
        f++;
        InterfaceUpdateQueue var_iu = method2861(id_dword, i);
        var_iu.e(-139247624);
    }

    public static final InterfaceUpdateQueue method2861(long id, int i) {
        InterfaceUpdateQueue var_iu = (InterfaceUpdateQueue) s.get((long) i << 56 | id);
        if (var_iu == null) {
            var_iu = new InterfaceUpdateQueue(i, id);
            s.put(((Linkable) var_iu).uid, var_iu);
        }
        return var_iu;
    }

}
