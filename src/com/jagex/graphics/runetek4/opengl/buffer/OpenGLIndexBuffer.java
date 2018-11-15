package com.jagex.graphics.runetek4.opengl.buffer;

public class OpenGLIndexBuffer extends OpenGLBuffer implements IndexBuffer {

	public OpenGLIndexBuffer(byte[] is, int i_4_) {
		super(is, i_4_);
	}

	@Override
	public void upload(int format, byte[] data, int length) {
		upload(data, length);
	}

	@Override
	public long getAddress() {
		return buffer.getAddress();
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
