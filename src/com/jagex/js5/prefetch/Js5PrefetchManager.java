package com.jagex.js5.prefetch;

import com.jagex.GameClient;

import jaclib.ping.Ping;

/**
 * @author Walied K. Yassen
 */
public final class Js5PrefetchManager {

	private static final Js5PrefetchEntry[] ENTRIES = Js5PrefetchEntry.values();
	private static int prefetch_stage = 0;

	public static int process() {
		if (prefetch_stage == 0) {
			Js5PrefetchEntry.JACLIB.setPrefetch(new Js5PrefetchNative("jaclib"));
			if (Js5PrefetchEntry.JACLIB.getPrefetch().prefetch() != 100) {
				return 1;
			}
			GameClient.loadNativeLibrary("jaclib");
			try {
				Ping.init();
			} catch (Throwable throwable) {
				/* empty */
			}
			prefetch_stage = 1;
		}
		if (prefetch_stage == 1) {
			Js5PrefetchEntry.JAGGL.setPrefetch(new Js5PrefetchNative("jaggl"));
			for (Js5PrefetchEntry entry : ENTRIES) {
				if (entry.getPrefetch() == null) {
					throw new RuntimeException("The prefetch was not initialised: " + entry);
				}
			}
			for (Js5PrefetchEntry entry : ENTRIES) {
				entry.getPrefetch().prefetch();
			}
			prefetch_stage = 2;
		}
		int num_total = 0;
		int num_percent = 0;
		boolean completed = true;
		for (Js5PrefetchEntry entry : ENTRIES) {
			int percent = entry.getPrefetch().prefetch();
			if (percent < 100) {
				completed = false;
			}
			num_percent += percent;
			num_total++;
		}
		int total_percent = num_total > 0 ? num_percent / num_total : 100;
		if (!completed && total_percent > 99) {
			total_percent = 99;
		}
		return total_percent;
	}

	private Js5PrefetchManager() {
		// NOOP
	}
}
