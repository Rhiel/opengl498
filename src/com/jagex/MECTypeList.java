package com.jagex;

import com.jagex.core.collections.memory.AdvancedMemoryCache;

/**
 * @author Walied K. Yassen
 */
public class MECTypeList {

	protected static final AdvancedMemoryCache types_cache = new AdvancedMemoryCache(128);
	protected static final AdvancedMemoryCache sprites_cache = new AdvancedMemoryCache(64);

	public static MECType list(int category) {
		MECType type;
		synchronized (types_cache) {
			type = (MECType) types_cache.get(category);
		}
		if (null != type) {
			return type;
		}
		byte[] data;
		synchronized (CacheConstants.configs_js5) {
			data = CacheConstants.configs_js5.get_file(36, category);
		}
		type = new MECType();
		if (data != null) {
			type.decode(new Packet(data));
		}
		synchronized (types_cache) {
			types_cache.put(category, type);
		}
		return type;
	}

	public static void cacheReset() {
		synchronized (types_cache) {
			types_cache.clear();
		}
		synchronized (sprites_cache) {
			sprites_cache.clear();
		}
	}

}
