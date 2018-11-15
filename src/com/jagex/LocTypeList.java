package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;

public class LocTypeList {

	public static LocType list(int objectId) {
		LocType def = (LocType) LocTypeList.objectMap.get(objectId);
		if (def != null) {
			return def;
		}
		byte[] bs = LocTypeList.objectContainer.get_file(LocTypeList.getArchiveID(objectId), LocTypeList.getFileID(objectId));
		def = new LocType();
		def.id = objectId;
		if (bs != null) {
			def.decode(new Packet(bs));
		}
		def.setObjectClipping();
		if (def.isSolidObject) {
			def.clipping_type = 0;
			def.projectile_blocked = false;
		}
		if (!LocTypeList.isMember && def.membersOnly) {
			def.actions = null;
		}
		LocTypeList.objectMap.put(objectId, def);
		return def;
	}

	public static int getFileID(int objectId) {
		return 0xff & objectId;
	}

	public static int getArchiveID(int objectId) {
		return objectId >>> 8;
	}

	public static AdvancedMemoryCache cache;
	public static LocResult result = new LocResult();
	public static AdvancedMemoryCache opengl_cache = new AdvancedMemoryCache(500);
	public static Js5 models_js5;
	public static boolean isMember;
	public static Js5 objectContainer;
	public static MemoryCache objectMap = new MemoryCache(64);
	public static boolean aBoolean3792 = false;
	static AdvancedMemoryCache somecache = new AdvancedMemoryCache(50);

	static final void method1211(int i) {
		objectMap.clear();
		opengl_cache.clear();
		cache.clear();
		somecache.clear();
	}

}
