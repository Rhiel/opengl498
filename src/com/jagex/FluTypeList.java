package com.jagex;

public class FluTypeList {

	public static final MemoryCache typeCache = new MemoryCache(64);;
	public static Js5 configsJs5;

	public static FluType list(int id) {
		FluType type = (FluType) FluTypeList.typeCache.get(id);
		if (type != null) {
			return type;
		}
		byte[] data = FluTypeList.configsJs5.get_file(1, id);
		type = new FluType();
		if (data != null) {
			type.decode(new Packet(data));
		} else {
			DebugMissing.notify_underlay(id);
		}
		FluTypeList.typeCache.put(id, type);
		return type;
	}

	static final void method562(byte b, Js5 class105) {
		configsJs5 = class105;
	}

	static final void method93() {
		typeCache.clear();
	}

}
