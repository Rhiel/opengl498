package com.jagex.graphics.runetek4.opengl.buffer;

import com.jagex.graphics.runetek4.opengl.GLManager;

import jaclib.memory.Buffer;
import jaggl.OpenGL;

public class OpenGLVertexBufferARB extends OpenGLBufferARB implements VertexBuffer {
	public int stride;

	public OpenGLVertexBufferARB(int stride, Buffer buffer, int size, boolean bool) {
		super(OpenGL.GL_ARRAY_BUFFER, buffer, size, bool);
		this.stride = stride;
	}

	public OpenGLVertexBufferARB(int stride, byte[] data, int size, boolean stream) {
		super(OpenGL.GL_ARRAY_BUFFER, data, size, stream);
		this.stride = stride;
	}

	@Override
	public void upload(int stride, byte[] data, int length) {
		put(data, length);
		this.stride = stride;
	}

	@Override
	public long getAddress() {
		return 0L;
	}

	@Override
	public int getStride() {
		return stride;
	}

	@Override
	public int getHandle() {
		return handle;
	}

	@Override
	public void bind() {
		GLManager.bind_vertex_buffer(this);
	}
}
