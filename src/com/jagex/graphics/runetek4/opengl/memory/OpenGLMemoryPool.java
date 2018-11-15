package com.jagex.graphics.runetek4.opengl.memory;

import com.jagex.Linkable;

import jaclib.memory.heap.NativeHeap;

public class OpenGLMemoryPool extends Linkable {

	public NativeHeap heap;

	public OpenGLMemoryPool(int size) {
		heap = new NativeHeap(size);
	}

	public void deallocate() {
		heap.deallocate();
	}
}
