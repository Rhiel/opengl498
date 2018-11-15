package com.jagex;

import com.jagex.launcher.GameLaunch;

public class HashTable {
	static RSString aClass16_1245 = RSString.createString("leuchten2:");
	public Linkable get_current_node;
	public static RSString aClass16_1249 = RSString.createString("Welcome to " + GameLaunch.getSetting().getName());
	static RSString aClass16_1250;
	static RSString aClass16_1253 = RSString.createString("(U0a )2 in: ");
	static RSString aClass16_1254;
	public Linkable[] nodes;
	public long get_current_hash;
	static RSString aClass16_1258;
	public int size;
	public static RSString aClass16_1260;
	public static int[] anIntArray1262;
	public Linkable iterator_current;
	public static int anInt1267;
	public int iterator_position = 0;

	public void clear() {
		int index = 0;
		while (index < size) {
			Linkable bucket = nodes[index];
			while (true) {
				Linkable next = bucket.next;
				if (next == bucket) {
					index++;
					break;
				}

				next.unlink();
			}
		}
		get_current_node = null;
		iterator_current = null;
	}

	public int toArray(Linkable[] output) {
		int var3 = 0;
		for (int var4 = 0; var4 < size; ++var4) {
			Linkable var5 = nodes[var4];
			for (Linkable var6 = var5.next; var6 != var5; var6 = var6.next) {
				output[var3++] = var6;
			}
		}

		return var3;
	}

	public final Linkable get_next() {
		if (iterator_position > 0 && nodes[iterator_position - 1] != iterator_current) {
			Linkable node = iterator_current;
			iterator_current = node.next;
			return node;
		}
		while (iterator_position < size) {
			Linkable node = nodes[iterator_position++].next;
			if (node != nodes[iterator_position - 1]) {
				iterator_current = node.next;
				return node;
			}
		}
		return null;
	}

	public final Linkable get_first() {
		iterator_position = 0;
		return get_next();
	}

	public final void put(long key, Linkable value) {
		if (value.prev != null) {
			value.unlink();
		}
		Linkable previous = nodes[(int) (-1 + size & key)];
		value.uid = key;
		value.prev = previous.prev;
		value.next = previous;
		value.prev.next = value;
		value.next.prev = value;
	}

	public final Linkable getLastFetchedNode() {
		if (get_current_node == null) {
			return null;
		}
		Linkable node = nodes[(int) (size + -1 & get_current_hash)];
		for (/**/; get_current_node != node; get_current_node = get_current_node.next) {
			if (get_current_hash == get_current_node.uid) {
				Linkable class23_15_ = get_current_node;
				get_current_node = get_current_node.next;
				return class23_15_;
			}
		}
		get_current_node = null;
		return null;
	}

	static final InterfaceNode get(int walkable, int interfaceId, int interfaceHash, boolean notWindowPane) {
		InterfaceNode inter = new InterfaceNode();
		inter.interfaceId = interfaceId;
		inter.walkable = walkable;
		Class36.anOa565.put(interfaceHash, inter);
		Stereo.method76(15532, interfaceId);
		RSInterface rsInterface = RSInterface.getInterface(interfaceHash);
		if (rsInterface != null) {
			RSInterfaceList.setDirty(rsInterface);
		}
		if (Varbit.aClass64_4007 != null) {
			RSInterfaceList.setDirty(Varbit.aClass64_4007);
			Varbit.aClass64_4007 = null;
		}
		ContextMenu.menuOpen = false;
		ContextMenu.menuActionRow = 0;
		RuntimeException_Sub1.method1589(ContextMenu.menuHeight, ContextMenu.menuWidth, ContextMenu.menuOffsetX, ContextMenu.menuOffsetY, true);
		if (rsInterface != null) {
			RSInterfaceLayout.calc_layout(rsInterface, !notWindowPane);
		}
		if (!notWindowPane) {
			StaticMethods.method313(interfaceId, true);
		}
		if (!notWindowPane && GameClient.interface_top_id != -1) {
			StaticMethods2.method757(GameClient.interface_top_id, 1, false);
		}
		return inter;
	}

	public final Linkable get(long l) {
		get_current_hash = l;
		Linkable parser = nodes[(int) (l & -1 + size)];
		get_current_node = parser.next;
		for (/**/; get_current_node != parser; get_current_node = get_current_node.next) {
			if (l == get_current_node.uid) {
				Linkable class23_19_ = get_current_node;
				get_current_node = get_current_node.next;
				return class23_19_;
			}
		}
		get_current_node = null;
		return null;
	}

	public HashTable(int size) {
		nodes = new Linkable[size];
		this.size = size;
		for (int i = 0; i < size; i++) {
			Linkable node = nodes[i] = new Linkable();
			node.next = node;
			node.prev = node;
		}
	}

	final int get_hash_resolution() {
		return size;
	}

	public int getSize() {
		int var3 = 0;
		for (int var4 = 0; var4 < size; ++var4) {
			Linkable var5 = nodes[var4];

			for (Linkable var6 = var5.next; var6 != var5; ++var3) {
				var6 = var6.next;
			}
		}
		return var3;
	}

	static {
		aClass16_1250 = aClass16_1249;
		aClass16_1260 = RSString.createString("Please unlink ");
		aClass16_1254 = aClass16_1260;
		aClass16_1258 = aClass16_1260;
		anIntArray1262 = new int[2100];
		anInt1267 = 0;
	}
}
