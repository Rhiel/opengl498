package com.rs2.client.scene.light;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public final class Class273 {
	int anInt2758;
	PriorityQueue anAbstractQueue_Sub1_2759;
	PriorityQueue anAbstractQueue_Sub1_2760;
	long aLong2761;
	Class271 aClass271_2762;
	Map aMap2763;

	public Object method2674(Object object) {
		synchronized (this) {
			if (-1L != aLong2761 * -5428598357119944393L) {
				method2679();
			}
			Class288 class288 = (Class288) aMap2763.remove(object);
			if (class288 != null) {
				anAbstractQueue_Sub1_2759.remove(class288);
				if (method2675()) {
					anAbstractQueue_Sub1_2760.remove(class288);
				}
				return class288.anObject2951;
			}
			return null;
		}
	}

	boolean method2675() {
		return anInt2758 * -1286678403 != -1;
	}

	public Object method2676(Object object, Object object_2_) {
		synchronized (this) {
			if (-1L != aLong2761 * -5428598357119944393L) {
				method2679();
			}
			Class288 class288 = (Class288) aMap2763.get(object);
			if (class288 != null) {
				Object object_3_ = class288.anObject2951;
				class288.anObject2951 = object_2_;
				method2677(class288, false);
				return object_3_;
			}
			if (method2675() && aMap2763.size() == anInt2758 * -1286678403) {
				Class288 class288_5_ = (Class288) anAbstractQueue_Sub1_2760.remove();
				aMap2763.remove(class288_5_.anObject2952);
				anAbstractQueue_Sub1_2759.remove(class288_5_);
			}
			Class288 class288_6_ = new Class288(object_2_, object);
			aMap2763.put(object, class288_6_);
			method2677(class288_6_, true);
			return null;
		}
	}

	void method2677(Class288 class288, boolean bool) {
		if (!bool) {
			anAbstractQueue_Sub1_2759.remove(class288);
			if (method2675() && !anAbstractQueue_Sub1_2760.remove(class288)) {
				throw new IllegalStateException("");
			}
		}
		class288.aLong2953 = System.currentTimeMillis() * -6491415923264622733L;
		if (method2675()) {
			switch (aClass271_2762.level) {
				case 1:
					class288.aLong2954 = class288.aLong2953 * 9102800332700574737L;
					break;
				case 0:
					class288.aLong2954 += 5253132899669824163L;
					break;
			}
			anAbstractQueue_Sub1_2760.add(class288);
		}
		anAbstractQueue_Sub1_2759.add(class288);
	}

	public void method2678() {
		synchronized (this) {
			aMap2763.clear();
			anAbstractQueue_Sub1_2759.clear();
			if (method2675()) {
				anAbstractQueue_Sub1_2760.clear();
			}
		}
	}

	void method2679() {
		if (-5428598357119944393L * aLong2761 == -1L) {
			throw new IllegalStateException("");
		}
		long l = System.currentTimeMillis() - aLong2761 * -5428598357119944393L;
		while_77_: do {
			for (;;) {
				if (anAbstractQueue_Sub1_2759.isEmpty()) {
					break while_77_;
				}
				Class288 class288 = (Class288) anAbstractQueue_Sub1_2759.peek();
				if (class288.aLong2953 * 870820241587588539L >= l) {
					break;
				}
				aMap2763.remove(class288.anObject2952);
				anAbstractQueue_Sub1_2759.remove(class288);
				if (method2675()) {
					anAbstractQueue_Sub1_2760.remove(class288);
				}
			}
			break;
		} while (false);
	}

	public Class273(int i, Class271 class271) {
		this(-1L, i, class271);
	}

	public Object method2680(Object object) {
		synchronized (this) {
			if (-5428598357119944393L * aLong2761 != -1L) {
				method2679();
			}
			Class288 class288 = (Class288) aMap2763.get(object);
			if (class288 == null) {
				return null;
			}
			method2677(class288, false);
			return class288.anObject2951;
		}
	}

	Class273(long l, int i, Class271 class271) {
		aLong2761 = l * -6662316179537376121L;
		anInt2758 = 1673748693 * i;
		aClass271_2762 = class271;
		if (anInt2758 * -1286678403 == -1) {
			aMap2763 = new HashMap(64);
			anAbstractQueue_Sub1_2759 = new PriorityQueue(64);
			anAbstractQueue_Sub1_2760 = null;
		} else {
			if (aClass271_2762 == null) {
				throw new IllegalArgumentException("");
			}
			aMap2763 = new HashMap(anInt2758 * -1286678403);
			anAbstractQueue_Sub1_2759 = new PriorityQueue(anInt2758 * -1286678403);
			anAbstractQueue_Sub1_2760 = new PriorityQueue(anInt2758 * -1286678403);
		}
	}

}
