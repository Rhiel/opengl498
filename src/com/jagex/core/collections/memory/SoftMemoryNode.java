package com.jagex.core.collections.memory;
import java.lang.ref.SoftReference;

public class SoftMemoryNode extends MemoryNode {

	private SoftReference content;

	public SoftMemoryNode(Object content) {
		this.content = new SoftReference(content);
	}

	@Override
	public Object get_content() {
		return content.get();
	}

	@Override
	public boolean is_soft() {
		return true;
	}
}
