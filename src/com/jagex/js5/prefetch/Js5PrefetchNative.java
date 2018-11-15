package com.jagex.js5.prefetch;

import com.jagex.GameClient;

/**
 * @author Walied K. Yassen
 */
public class Js5PrefetchNative implements Js5Prefetch {

	public String name;

	public Js5PrefetchNative(String string) {
		name = string;
	}

	@Override
	public int prefetch() {
		int percent = GameClient.nativeManager.getProgress(name);
		if (percent >= 0 && percent <= 100) {
			return percent;
		}
		return 100;
	}

	@Override
	public Js5PrefetchType type() {
		return Js5PrefetchType.NATIVE;
	}
}
