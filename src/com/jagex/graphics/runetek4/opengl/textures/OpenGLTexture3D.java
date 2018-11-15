package com.jagex.graphics.runetek4.opengl.textures;

import static jaggl.GLConstants.GL_TEXTURE_3D;
import static jaggl.GLConstants.GL_UNPACK_ALIGNMENT;
import static jaggl.GLConstants.GL_UNSIGNED_BYTE;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glCopyTexSubImage3D;
import static jaggl.OpenGL.glFlush;
import static jaggl.OpenGL.glPixelStorei;
import static jaggl.OpenGL.glTexImage3Dub;

import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLTexture3D extends OpenGLTexture {

	public int width;
	public int height;
	public int depth;

	public OpenGLTexture3D(PixelFormat pixelFormat, DataType depthFormat, int width, int height, int depth, byte[] is, PixelFormat class72_8_) {
		super(GL_TEXTURE_3D, pixelFormat, depthFormat, width * height * depth, false);
		this.width = width;
		this.height = height;
		this.depth = depth;
		glBindTexture(GL_TEXTURE_3D, handle);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage3Dub(target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), this.width, this.height, this.depth, 0, PixelFormat.getFormat(class72_8_), GL_UNSIGNED_BYTE, is, 0);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
		set_linear_filter(true);
	}

	public OpenGLTexture3D(PixelFormat pixelFormat, DataType class86, int width, int height, int depth) {
		super(GL_TEXTURE_3D, pixelFormat, class86, width * height * depth, false);
		this.width = width;
		this.height = height;
		this.depth = depth;
		glBindTexture(GL_TEXTURE_3D, handle);
		glTexImage3Dub(target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), this.width, this.height, this.depth, 0, PixelFormat.getFormat(pixel_format), GL_UNSIGNED_BYTE, null, 0);
		set_linear_filter(true);
	}

	public void set_pixels(int xOffset, int yOffset, int zOffset, int width, int height, int x, int y) {
		glBindTexture(GL_TEXTURE_3D, handle);
		glCopyTexSubImage3D(target, 0, xOffset, yOffset, zOffset, x, y, width, height);
		glFlush();
	}

}
