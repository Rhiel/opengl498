package com.jagex.graphics.runetek4.opengl.buffer;

import com.jagex.graphics.runetek4.opengl.GLManager;

import jaclib.memory.Buffer;

/**
 * @author Walied K. Yassen
 */
public class OpenGLBuffer {

	public Buffer buffer;

	public OpenGLBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	public OpenGLBuffer(byte[] data, int length) {
		buffer = GLManager.heap.allocate_buffer(length, false);
		if (data != null) {
			buffer.put(data, 0, 0, length);
		}
	}

	public void upload(byte[] data, int length) {
		if (buffer == null || buffer.getSize() < length) {
			buffer = GLManager.heap.allocate_buffer(length, false);
		}
		buffer.put(data, 0, 0, length);
	}

}
