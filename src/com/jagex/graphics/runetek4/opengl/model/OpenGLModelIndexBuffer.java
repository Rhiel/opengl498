package com.jagex.graphics.runetek4.opengl.model;

import com.jagex.graphics.runetek4.opengl.buffer.IndexBuffer;

public class OpenGLModelIndexBuffer {

	public IndexBuffer buffer;
	public boolean is_clean;

	public void destroy_buffer() {
		if (buffer != null) {
			buffer.destroy();
			buffer = null;
		}
	}
}
