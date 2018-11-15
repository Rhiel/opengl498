package com.jagex.game.runetek4.configs.lighttype;

import com.jagex.CacheConstants;
import com.jagex.MemoryCache;
import com.jagex.Packet;

/**
 * @author Walied K. Yassen
 */
public final class LightTypeList {

	public static MemoryCache type_cache = new MemoryCache(64);

	public static LightType list(int id) {
		LightType type = (LightType) LightTypeList.type_cache.get(id);
		if (type != null) {
			return type;
		}
		byte[] data = CacheConstants.configs_js5.get_file(31, id);
		type = new LightType();
		if (data != null) {
			type.decode(new Packet(data));
		}
		LightTypeList.type_cache.put(id, type);
		return type;
	}

	public LightTypeList() {
		// NOOP
	}
}
