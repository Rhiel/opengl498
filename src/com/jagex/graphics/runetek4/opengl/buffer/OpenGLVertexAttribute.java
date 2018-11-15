package com.jagex.graphics.runetek4.opengl.buffer;

public class OpenGLVertexAttribute {

	public VertexBuffer buffer;
	public short type;
	public byte stride;
	public byte offset;
	public boolean is_clean;

	public OpenGLVertexAttribute(VertexBuffer buffer, int type, int elementSize, int offset) {
		this.buffer = buffer;
		this.type = (short) type;
		stride = (byte) elementSize;
		this.offset = (byte) offset;
	}
}
