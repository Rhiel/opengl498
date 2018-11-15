package com.jagex;
/* AbstractTimer - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class AbstractTimer {
	static int anInt302;
	public static int anInt304;
	public static int anInt305;
	static RSString aClass16_306;
	static RSString aClass16_307;
	public static int[] anIntArray308 = new int[128];
	static Js5 aClass105_313;
	public static RSString aClass16_317;

	abstract int wait_for_next_frame(int i, byte b, int i_0_);

	public AbstractTimer() {
		/* empty */
	}

	abstract void method188(int i);

	public static final boolean hasActiveInterface(int dummy, int interfaceId) {
		// if(interfaceId != 549 && interfaceId != 378 && interfaceId != 16)
		// System.out.println(interfaceId);
		if (Class54.aBooleanArray859[interfaceId]) {
			return true;
		}
		if (!Class71_Sub3.aClass105_2745.is_group_cached(interfaceId)) {
			return false;
		}
		int i_2_ = Class71_Sub3.aClass105_2745.get_file_count(interfaceId);
		if (i_2_ == 0) {
			Class54.aBooleanArray859[interfaceId] = true;
			return true;
		}
		if (StaticMethods.cached_interfaces[interfaceId] == null) {
			StaticMethods.cached_interfaces[interfaceId] = new RSInterface[i_2_];
		}
		boolean version20 = isVersion20(interfaceId);
		for (int childId = 0; (i_2_ ^ 0xffffffff) < (childId ^ 0xffffffff); childId++) {
			if (StaticMethods.cached_interfaces[interfaceId][childId] == null) {
				byte[] bs = Class71_Sub3.aClass105_2745.get_file(interfaceId, childId);
				if (bs != null) {
					StaticMethods.cached_interfaces[interfaceId][childId] = new RSInterface();
					StaticMethods.cached_interfaces[interfaceId][childId].uid = (interfaceId << 16) + childId;
					if (version20) {
						StaticMethods.cached_interfaces[interfaceId][childId].parseVersion20(new Packet(bs));
					} else if (bs[0] == -1) {
						StaticMethods.cached_interfaces[interfaceId][childId].parseVersion15(new Packet(bs));
					} else {
						StaticMethods.cached_interfaces[interfaceId][childId].parseVersion10(new Packet(bs));
					}
				}
			}
		}
		Class54.aBooleanArray859[interfaceId] = true;
		return true;
	}

	public static boolean isVersion20(int id) {
		return id == 20 || id == 21 || id == 163 || id == 164 || id == 742;
	}


	static {
		anInt305 = 0;
		aClass16_317 = RSString.createString("Take");
		aClass16_306 = RSString.createString("settings=");
		aClass16_307 = aClass16_317;
	}
}
