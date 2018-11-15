package com.jagex.js5.prefetch;

/**
 * @author Walied K. Yassen
 */
public enum Js5PrefetchEntry {

	JACLIB(Js5PrefetchType.NATIVE),
	JAGGL(Js5PrefetchType.NATIVE);

	public Js5PrefetchType type;
	public Js5Prefetch prefetch;
	public int max;

	private Js5PrefetchEntry(Js5PrefetchType type) {
		this.type = type;
		max = -1158503105;
	}

	public Js5Prefetch getPrefetch() {
		return prefetch;
	}

	public void setPrefetch(Js5Prefetch prefetch) {
		if (prefetch.type() != type) {
			throw new IllegalArgumentException("Invalid prefetch.type - got:" + prefetch.type() + " expected:" + type);
		}
		this.prefetch = prefetch;
	}
}
