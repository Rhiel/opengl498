package com.rs2.client.scene.light;

public final class Class288 implements Comparable {
	Object anObject2951;
	Object anObject2952;
	long aLong2953;
	long aLong2954;

	Class288(Object object, Object object_0_) {
		anObject2951 = object;
		anObject2952 = object_0_;
	}

	int method2884(Class288 class288_1_) {
		if (8505647925819568395L * aLong2954 < class288_1_.aLong2954 * 8505647925819568395L) {
			return -1;
		}
		if (aLong2954 * 8505647925819568395L > 8505647925819568395L * class288_1_.aLong2954) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Class288) {
			return anObject2952.equals(((Class288) object).anObject2952);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int compareTo(Object object) {
		return method2884((Class288) object);
	}

}
