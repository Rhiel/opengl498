package com.jagex;

/**
 * Created by Chris on 4/15/2017.
 */
public class RSInterfaceList {

	public static boolean[] is_dirty = new boolean[100];

	static final void setDirty(RSInterface inter) {
		if (StaticMethods.anInt3120 == inter.anInt1144) {
			is_dirty[inter.anInt1078] = true;
		}
	}

	static final void setAllDirty(int i) {
		for (int i_1_ = 0; i_1_ < 100; i_1_++) {
			is_dirty[i_1_] = true;
		}
	}

	static final RSInterface get_dynamic_component(int i, int i_42_, byte b) {
		RSInterface class64 = RSInterface.getInterface(i);
		if (i_42_ == -1) {
			return class64;
		}
		if (class64 == null || class64.dynamic_components == null || (i_42_ ^ 0xffffffff) <= (class64.dynamic_components.length ^ 0xffffffff)) {
			return null;
		}
		if (b > -26) {
			StaticMethods2.keysPressed = null;
		}
		return class64.dynamic_components[i_42_];
	}

	public static final void uncacheInterface(byte b, int i) {
		if (i != -1 && Class54.aBooleanArray859[i]) {
			Class71_Sub3.aClass105_2745.is_file_cached(i, 1);
			if (StaticMethods.cached_interfaces[i] != null) {
				boolean bool = true;
				if (b == 9) {
					for (int i_32_ = 0; (i_32_ ^ 0xffffffff) > (StaticMethods.cached_interfaces[i].length ^ 0xffffffff); i_32_++) {
						if (StaticMethods.cached_interfaces[i][i_32_] != null) {
							if (StaticMethods.cached_interfaces[i][i_32_].type != 2) {
								StaticMethods.cached_interfaces[i][i_32_] = null;
							} else {
								bool = false;
							}
						}
					}
					if (bool) {
						StaticMethods.cached_interfaces[i] = null;
					}
					Class54.aBooleanArray859[i] = false;
				}
			}
		}
	}

	public static RSInterface get_parent(RSInterface inter) {
		if (inter.parentId != -1) {
			return RSInterface.getInterface(inter.parentId);
		}
		int main_id = inter.uid >>> 16;
		HashTableIterator it = new HashTableIterator(Class36.anOa565);
		for (InterfaceNode var_dba = (InterfaceNode) it.start(); var_dba != null; var_dba = (InterfaceNode) it.next()) {
			if (var_dba.interfaceId == main_id) {
				return RSInterface.getInterface((int) var_dba.uid);
			}
		}
		return null;
	}

	static final void method677(Js5 class105, Js5 class105_0_, boolean bool, Js5 class105_1_, Js5 class105_2_) {
		Class71_Sub3.aClass105_2745 = class105_2_;
		Class61.aClass105_958 = class105_0_;
		if (bool != false) {
			get_dynamic_component(-100, 46, (byte) -18);
		}
		AbstractTimer.aClass105_313 = class105;
		RSString.sprites_js5 = class105_1_;
		StaticMethods.cached_interfaces = new RSInterface[Class71_Sub3.aClass105_2745.get_group_count()][];
		Class54.aBooleanArray859 = new boolean[Class71_Sub3.aClass105_2745.get_group_count()];
	}
}
