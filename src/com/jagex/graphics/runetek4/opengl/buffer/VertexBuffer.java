package com.jagex.graphics.runetek4.opengl.buffer;

public interface VertexBuffer {

	public void upload(int stride, byte[] data, int length);

	public long getAddress();

	public int getStride();

	public int getHandle();

	public void destroy();

}
