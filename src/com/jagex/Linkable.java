package com.jagex;

public class Linkable {
	public long uid;
	public Linkable prev;
	public Linkable next;

	public boolean method227(int i) {
		if (prev == null) {
			return false;
		}
		return true;
	}

	public void unlink() {
		if (prev != null) {
			prev.next = next;
			next.prev = prev;
			prev = null;
			next = null;
		}
	}

}
