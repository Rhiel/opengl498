package com.jagex;

public class FloTypeList {

	public static final MemoryCache typeCache = new MemoryCache(64);
	public static Js5 configsJs5;

	public static FloType list(int id) {
		FloType type = (FloType) FloTypeList.typeCache.get(id);
		if (type != null) {
			return type;
		}
		byte[] data = FloTypeList.configsJs5.get_file(4, id);
		type = new FloType();
		type.id = id;
		if (data != null) {
			type.decode(new Packet(data));
		} else {
			DebugMissing.notify_overlay(id);
		}
		FloTypeList.typeCache.put(id, type);
		return type;
	}

}
