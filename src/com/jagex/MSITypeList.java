package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;

/**
 * @author Walied K. Yassen
 */
public class MSITypeList {

	public static final AdvancedMemoryCache types_cache = new AdvancedMemoryCache(64);
	public static final AdvancedMemoryCache sprites_cache = new AdvancedMemoryCache(64);

	public static Js5 configs_js5;
	public static Js5 sprites_js5;

	public static void initialise(Js5 configs_js5, Js5 sprites_js5) {
		MSITypeList.configs_js5 = configs_js5;
		MSITypeList.sprites_js5 = sprites_js5;
	}

	public static MSIType list(int id) {
		MSIType type;
		synchronized (types_cache) {
			type = (MSIType) types_cache.get(id);
		}
		if (null != type) {
			return type;
		}
		byte[] data;
		synchronized (configs_js5) {
			data = configs_js5.get_file(34, id);
		}
		type = new MSIType();
		if (data != null) {
			type.decode(new Packet(data));
		}
		synchronized (types_cache) {
			types_cache.put(id, type);
		}
		return type;
	}

	public static void cacheReset() {
		types_cache.clear();
		sprites_cache.clear();
	}
}
