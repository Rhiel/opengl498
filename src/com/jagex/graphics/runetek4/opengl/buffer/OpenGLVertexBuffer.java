
package com.jagex.graphics.runetek4.opengl.buffer;

import jaclib.memory.Buffer;

public class OpenGLVertexBuffer extends OpenGLBuffer implements VertexBuffer {

	public int stride;

	public OpenGLVertexBuffer(int stride, Buffer buffer) {
		super(buffer);
		this.stride = stride;
	}

	public OpenGLVertexBuffer(int stride, byte[] data, int length) {
		super(data, length);
		this.stride = stride;
	}

	@Override
	public void upload(int stride, byte[] data, int length) {
		upload(data, length);
		this.stride = stride;
	}

	@Override
	public long getAddress() {
		return buffer.getAddress();
	}

	@Override
	public int getStride() {
		return stride;
	}

	@Override
	public int getHandle() {
		return 0;
	}

	@Override
	public void destroy() {
		// NOOP
	}
}
