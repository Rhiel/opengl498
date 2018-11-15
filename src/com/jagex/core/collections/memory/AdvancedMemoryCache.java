package com.jagex.core.collections.memory;

import com.jagex.HashTable;
import com.jagex.Queuable;
import com.jagex.core.collections.queue.Queue;

public class AdvancedMemoryCache {

	private Queue remove_order = new Queue();
	private HashTable table;
	private int space_left;
	private int space_size;

	public AdvancedMemoryCache(int size) {
		space_size = size;
		space_left = size;
		int resolution = 1;
		while (resolution + resolution < size) {
			resolution += resolution;
		}
		table = new HashTable(resolution);
	}

	public void process_age_cycle(int maxAge) {
		for (MemoryNode memory = (MemoryNode) remove_order.get_first(); null != memory; memory = (MemoryNode) remove_order.get_next()) {
			if (!memory.is_soft()) {
				if (memory.queue_id++ > maxAge) {
					MemoryNode new_node = new SoftMemoryNode(memory.get_content());
					table.put(memory.uid, new_node);
					replace_node(memory, new_node);
					memory.unlink();
					memory.unlink_queue();
				}
			} else if (memory.get_content() == null) {
				memory.unlink();
				memory.unlink_queue();
				space_left++;
			}
		}
	}

	public void put(long key, Object value) {
		remove(key);
		if (space_left == 0) {
			MemoryNode node = (MemoryNode) remove_order.remove_first();
			node.unlink();
			node.unlink_queue();
		} else {
			space_left--;
		}
		StrongMemoryNode memory = new StrongMemoryNode(value);
		table.put(key, memory);
		remove_order.add_last(memory);
		memory.queue_id = 0L;
	}

	public Object get(long key) {
		MemoryNode previous_memory = (MemoryNode) table.get(key);
		if (null == previous_memory) {
			return null;
		} else {
			Object content = previous_memory.get_content();
			if (content != null) {
				if (previous_memory.is_soft()) {
					StrongMemoryNode new_memory = new StrongMemoryNode(content);
					table.put(previous_memory.uid, new_memory);
					remove_order.add_last(new_memory);
					new_memory.queue_id = 0L;
					previous_memory.unlink();
					previous_memory.unlink_queue();
				} else {
					remove_order.add_last(previous_memory);
					previous_memory.queue_id = 0L;
				}

				return content;
			} else {
				previous_memory.unlink();
				previous_memory.unlink_queue();
				space_left++;
				return null;
			}
		}
	}

	public void remove(long key) {
		MemoryNode node = (MemoryNode) table.get(key);
		if (null != node) {
			node.unlink();
			node.unlink_queue();
			space_left++;
		}
	}

	public int size() {
		int size = 0;
		for (MemoryNode memory = (MemoryNode) remove_order.get_first(); memory != null; memory = (MemoryNode) remove_order.get_next()) {
			if (!memory.is_soft()) {
				size++;
			}
		}
		return size;
	}

	public void clear() {
		remove_order.clear();
		table.clear();
		space_left = space_size;
	}

	public void remove_soft_references() {
		for (MemoryNode memory = (MemoryNode) remove_order.get_first(); memory != null; memory = (MemoryNode) remove_order.get_next()) {
			if (memory.is_soft()) {
				memory.unlink();
				memory.unlink_queue();
				space_left++;
			}
		}
	}

	public static void replace_node(Queuable after, Queuable node) {
		if (node.queue_prev != null) {
			node.unlink_queue();
		}

		node.queue_prev = after;
		node.queue_next = after.queue_next;
		node.queue_prev.queue_next = node;
		node.queue_next.queue_prev = node;
	}

}
