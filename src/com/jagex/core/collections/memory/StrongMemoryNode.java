package com.jagex.core.collections.memory;

public class StrongMemoryNode extends MemoryNode {

	private Object content;

	public StrongMemoryNode(Object content) {
		this.content = content;
	}

	@Override
	public final Object get_content() {
		return content;
	}

	@Override
	public final boolean is_soft() {
		return false;
	}
}
