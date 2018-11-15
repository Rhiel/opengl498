package com.jagex.graphics.runetek4.opengl.buffer;

import com.jagex.graphics.runetek4.opengl.GLManager;

import jaggl.OpenGL;

public class OpenGLIndexBufferARB extends OpenGLBufferARB implements IndexBuffer {

	public OpenGLIndexBufferARB(byte[] data, int length, boolean stream) {
		super(OpenGL.GL_ELEMENT_ARRAY_BUFFER, data, length, stream);
	}

	@Override
	public void upload(int format, byte[] data, int length) {
		put(data, length);
	}

	@Override
	public long getAddress() {
		return 0L;
	}

	@Override
	public void bind() {
		GLManager.bind_index_buffer(this);
	}

	@Override
	public int getHandle() {
		return handle;
	}
}
