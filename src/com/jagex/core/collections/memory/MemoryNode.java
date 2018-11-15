package com.jagex.core.collections.memory;

import com.jagex.Queuable;

public abstract class MemoryNode extends Queuable {

	public abstract Object get_content();

	public abstract boolean is_soft();

}
