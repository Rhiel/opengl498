package com.jagex;
/* JList - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class MemoryCache
{
	public static RSString aClass16_102 = RSString.createString("Type");
	public Queuable aClass23_Sub13_103 = new Queuable();
	static RSString aClass16_108;
	static RSString aClass16_113;
	static RSString aClass16_114 = aClass16_102;
	public Queue aClass27_121 = new Queue();
	public int anInt122;
	public HashTable list;
	public int anInt125;
	
	public final Queuable get(long id) {
		Queuable n = (Queuable) list.get(id);
		if (n != null) {
			aClass27_121.add_last(n);
		}
		return n;
	}
	
	public static void method64(int i) {
		aClass16_114 = null;
		aClass16_113 = null;
		aClass16_102 = null;
		aClass16_108 = null;
		CacheConstants.globalQuickChatIdx = null;
	}
	
	final Linkable method65(int i) {
		if (i != -1) {
			anInt122 = -120;
		}
		return list.get_next();
	}

    public final void put(long l, Queuable node) {
		if (anInt125 == 0) {
			Queuable n = aClass27_121.remove();
			n.unlink();
			n.unlink_queue();
			if (aClass23_Sub13_103 == n) {
				n = aClass27_121.remove();
				n.unlink();
				n.unlink_queue();
			}
		} else {
			anInt125--;
		}
		list.put(l, node);
		aClass27_121.add_last(node);
	}
	
	public final void clear() {
		for (;;) {
			Queuable class23_sub13 = aClass27_121.remove();
			if (class23_sub13 == null) {
				break;
			}
			class23_sub13.unlink();
			class23_sub13.unlink_queue();
		}
		anInt125 = anInt122;
	}
	
	final Linkable method69(int i) {
		if (i < 97) {
			aClass16_113 = null;
		}
		return list.get_first();
	}
	
	final void method70(long l, int i) {
		if (i != 2047) {
			method65(-106);
		}
		Queuable class23_sub13 = (Queuable) list.get(l);
		if (class23_sub13 != null) {
			class23_sub13.unlink();
			class23_sub13.unlink_queue();
			anInt125++;
		}
	}
	
	public MemoryCache(int i) {
		anInt125 = i;
		anInt122 = i;
		int i_23_;
		for (i_23_ = 1; (i_23_ + i_23_ ^ 0xffffffff) > (i ^ 0xffffffff); i_23_ += i_23_) {
			/* empty */
		}
		list = new HashTable(i_23_);
	}
	
	static {
		aClass16_113 = RSString.createString("(R");
		aClass16_108 = RSString.createString("Welt");
	}
}
