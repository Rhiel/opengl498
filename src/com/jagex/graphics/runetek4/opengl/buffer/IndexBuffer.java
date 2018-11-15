package com.jagex.graphics.runetek4.opengl.buffer;

public interface IndexBuffer {

	public void upload(int format, byte[] data, int length);

	public long getAddress();

	public int getHandle();

	public void destroy();
}
