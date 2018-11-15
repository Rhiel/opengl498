package com.jagex;

/* Deque - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class NodeDeque {
	public static Packet aClass23_Sub5_1514;
	static RSString aClass16_1515 = RSString.createString(" zuerst von Ihrer Freunde)2Liste(Q");
	public Linkable head = new Linkable();
	public static int anInt1524 = 0;
	public Linkable current;

	public final Linkable get_next() {
		Linkable parser = current;
		if (parser == head) {
			current = null;
			return null;
		}
		current = parser.next;
		return parser;
	}

	public static void method1432(int i) {
		aClass16_1515 = null;
		aClass23_Sub5_1514 = null;
	}

	final void add_first(Linkable parser, int i) {
		if (parser.prev != null) {
			parser.unlink();
		}
		parser.prev = head;
		parser.next = head.next;
		parser.prev.next = parser;
		parser.next.prev = parser;
	}



	public void clear() {
		for (;;) {
			Linkable parser = head.next;
			if (head == parser) {
				break;
			}
			parser.unlink();
		}
	}

	public Linkable remove_first() {
		Linkable node = head.next;
		if (node == head) {
			return null;
		}
		node.unlink();
		return node;
	}

	public Linkable get_first() {
		Linkable parser = head.next;
		if (parser == head) {
			current = null;
			return null;
		}
		current = parser.next;
		return parser;
	}

	public Linkable get_prev(byte b) {
		Linkable parser = current;
		if (head == parser) {
			current = null;
			return null;
		}
		current = parser.prev;
		return parser;
	}

	public void add_last(Linkable node) {
		if (node.prev != null) {
			node.unlink();
		}
		node.next = head;
		node.prev = head.prev;
		node.next.prev = node;
		node.prev.next = node;
	}
	public Linkable get_last(int i) {
		Linkable parser = head.prev;
		if (parser == head) {
			current = null;
			return null;
		}
		current = parser.prev;
		return parser;
	}

	public boolean is_empty() {
		return head.next == head;
	}

	public void method1441(Linkable parser, int i, Linkable class23_8_) {
		if (class23_8_.prev != null) {
			class23_8_.unlink();
		}
		class23_8_.prev = parser.prev;
		class23_8_.next = parser;
		class23_8_.prev.next = class23_8_;
		class23_8_.next.prev = class23_8_;
	}

	public NodeDeque() {
		head.next = head;
		head.prev = head;
	}

	static {
		GameClient.timer = 0;
	}
}
