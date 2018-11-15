package com.jagex.core.collections.queue;

import com.jagex.Queuable;

public class Queue {

	private Queuable head = new Queuable();
	private Queuable current;

	public Queue() {
		head.queue_next = head;
		head.queue_prev = head;
	}

	public void add_last(Queuable node) {
		if (node.queue_prev != null) {
			node.unlink_queue();
		}
		node.queue_prev = head.queue_prev;
		node.queue_next = head;
		node.queue_prev.queue_next = node;
		node.queue_next.queue_prev = node;
	}

	public Queuable get_first() {
		Queuable node = head.queue_next;
		if (head == node) {
			current = null;
			return null;
		}
		current = node.queue_next;
		return node;
	}

	public Queuable get_next() {
		Queuable node = current;
		if (head == node) {
			current = null;
			return null;
		} else {
			current = node.queue_next;
			return node;
		}
	}

	public Queuable remove_first() {
		Queuable node = head.queue_next;
		if (head == node) {
			return null;
		}
		node.unlink_queue();
		return node;
	}

	public void clear() {
		while (true) {
			Queuable node = head.queue_next;
			if (head == node) {
				current = null;
				return;
			}
			node.unlink_queue();
		}
	}

	public int size() {
		int size = 0;
		for (Queuable node = head.queue_next; node != head; size++) {
			node = node.queue_next;
		}
		return size;
	}

}
