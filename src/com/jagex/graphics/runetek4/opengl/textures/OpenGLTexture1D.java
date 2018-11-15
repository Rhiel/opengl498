package com.jagex.graphics.runetek4.opengl.textures;

import static jaggl.GLConstants.GL_TEXTURE_1D;
import static jaggl.GLConstants.GL_UNPACK_ALIGNMENT;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glPixelStorei;
import static jaggl.OpenGL.glTexImage1Dub;
import static jaggl.OpenGL.glTexParameteri;

import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

import jaggl.OpenGL;

public class OpenGLTexture1D extends OpenGLTexture {

	public int width;

	public OpenGLTexture1D(PixelFormat pformat, DataType dformat, int width, byte[] data, PixelFormat symbolFormat) {
		super(GL_TEXTURE_1D, pformat, dformat, width, false);
		this.width = width;
		glBindTexture(GL_TEXTURE_1D, handle);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage1Dub(target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), this.width, 0, PixelFormat.getFormat(symbolFormat), 5121, data, 0);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
		set_linear_filter(true);
	}

	public void set_repeat(boolean repeat) {
		glBindTexture(GL_TEXTURE_1D, handle);
		glTexParameteri(target, OpenGL.GL_TEXTURE_WRAP_S, repeat ? OpenGL.GL_REPEAT : OpenGL.GL_CLAMP_TO_EDGE);
	}
}
