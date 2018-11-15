package com.jagex.graphics.runetek4.opengl.buffer;

import static jaggl.GLConstants.GL_STATIC_DRAW;
import static jaggl.GLConstants.GL_STREAM_DRAW;
import static jaggl.OpenGL.glBufferDataARBa;
import static jaggl.OpenGL.glBufferDataARBub;
import static jaggl.OpenGL.glBufferSubDataARBub;
import static jaggl.OpenGL.glGenBuffersARB;

import com.jagex.graphics.runetek4.opengl.GLManager;

import jaclib.memory.Buffer;

public abstract class OpenGLBufferARB {

	public static int[] handleBuffer = new int[1];

	public int target;
	public int size;
	public int handle;
	public boolean stream;

	public OpenGLBufferARB(int target, byte[] data, int size, boolean stream) {
		this.target = target;
		this.size = size;
		this.stream = stream;
		glGenBuffersARB(1, handleBuffer, 0);
		handle = handleBuffer[0];
		bind();
		glBufferDataARBub(target, size, data, 0, stream ? GL_STREAM_DRAW : GL_STATIC_DRAW);
		GLManager.allocated_vertexbuffers_size += size;
	}

	public OpenGLBufferARB(int target, Buffer buffer, int size, boolean stream) {
		this.target = target;
		this.size = size;
		this.stream = stream;
		glGenBuffersARB(1, handleBuffer, 0);
		handle = handleBuffer[0];
		bind();
		glBufferDataARBa(target, size, buffer.getAddress(), stream ? GL_STREAM_DRAW : GL_STATIC_DRAW);
		GLManager.allocated_vertexbuffers_size += size;
	}

	public abstract void bind();

	public void put(byte[] data, int length) {
		bind();
		if (length > size) {
			glBufferDataARBub(target, length, data, 0, stream ? GL_STREAM_DRAW : GL_STATIC_DRAW);
			GLManager.allocated_vertexbuffers_size += length - size;
			size = length;
		} else {
			glBufferSubDataARBub(target, 0, length, data, 0);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		destroy();
	}

	public void destroy() {
		if (handle != -1) {
			GLManager.delete_vertexbuffer(handle, size);
			handle = -1;
			size = 0;
		}
	}

	@Override
	public String toString() {
		return "OpennGLBuffer [handle=" + handle + ", size=" + size + "]";
	}
}
