package com.jagex.graphics.runetek4.opengl.textures;

import static jaggl.GLConstants.GL_BGRA;
import static jaggl.GLConstants.GL_FLOAT;
import static jaggl.GLConstants.GL_LINEAR;
import static jaggl.GLConstants.GL_LINEAR_MIPMAP_LINEAR;
import static jaggl.GLConstants.GL_NEAREST;
import static jaggl.GLConstants.GL_NEAREST_MIPMAP_NEAREST;
import static jaggl.GLConstants.GL_TEXTURE_MAG_FILTER;
import static jaggl.GLConstants.GL_TEXTURE_MIN_FILTER;
import static jaggl.GLConstants.GL_UNSIGNED_BYTE;
import static jaggl.OpenGL.glDeleteTextures;
import static jaggl.OpenGL.glGenTextures;
import static jaggl.OpenGL.glGenerateMipmapEXT;
import static jaggl.OpenGL.glTexImage2Df;
import static jaggl.OpenGL.glTexImage2Di;
import static jaggl.OpenGL.glTexImage2Dub;
import static jaggl.OpenGL.glTexParameteri;

import com.jagex.Queuable;
import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public abstract class OpenGLTexture extends Queuable {

	private static final int[] TEXTURE_ID_BUFFER = new int[1];

	public int handle;
	public int target;
	public PixelFormat pixel_format;
	public DataType data_type;
	public boolean mipmap;
	public boolean linear;
	public int pixels_count;

	public OpenGLTexture(int target, PixelFormat pixel_format, DataType data_type, int pixels_count, boolean mipmap) {
		this.target = target;
		this.pixel_format = pixel_format;
		this.data_type = data_type;
		this.mipmap = mipmap;
		this.pixels_count = pixels_count;
		glGenTextures(1, TEXTURE_ID_BUFFER, 0);
		handle = TEXTURE_ID_BUFFER[0];
		update_memoryspace(0);
	}

	public static void generate_software_mips(int target, int internalFormat, int width, int height, int format, int type, int[] is) {
		if (width > 0 && !MathTools.is_power_of_two(width)) {
			throw new IllegalArgumentException("Error generating mips - width is not fixed");
		}
		if (height > 0 && !MathTools.is_power_of_two(height)) {
			throw new IllegalArgumentException("Error generating mips - height is not fixed");
		}
		if (format != GL_BGRA) {
			throw new IllegalArgumentException("Error generating mips - invalid texture format");
		}
		int level = 0;
		int smallest = width < height ? width : height;
		int levelWidth = width >> 1;
		int levelHeight = height >> 1;
		int[] levelPixels = is;
		int[] nextLevelPixels = new int[levelWidth * levelHeight];
		for (;;) {
			glTexImage2Di(target, level, internalFormat, width, height, 0, format, type, levelPixels, 0);
			if (smallest <= 1) {
				break;
			}
			int targetOffset = 0;
			int row1Offset = 0;
			int row2Offset = row1Offset + width;
			for (int x = 0; x < levelHeight; x++) {
				for (int y = 0; y < levelWidth; y++) {
					int pixA = levelPixels[row1Offset++];
					int pixB = levelPixels[row1Offset++];
					int pixC = levelPixels[row2Offset++];
					int pixD = levelPixels[row2Offset++];
					int mpixA = pixA >> 24 & 0xff;
					int mpixR = pixA >> 16 & 0xff;
					int mpixG = pixA >> 8 & 0xff;
					int mpixB = pixA & 0xff;
					mpixA += pixB >> 24 & 0xff;
					mpixR += pixB >> 16 & 0xff;
					mpixG += pixB >> 8 & 0xff;
					mpixB += pixB & 0xff;
					mpixA += pixC >> 24 & 0xff;
					mpixR += pixC >> 16 & 0xff;
					mpixG += pixC >> 8 & 0xff;
					mpixB += pixC & 0xff;
					mpixA += pixD >> 24 & 0xff;
					mpixR += pixD >> 16 & 0xff;
					mpixG += pixD >> 8 & 0xff;
					mpixB += pixD & 0xff;
					nextLevelPixels[targetOffset++] = (mpixA & 0x3fc) << 22 | (mpixR & 0x3fc) << 14 | (mpixG & 0x3fc) << 6 | (mpixB & 0x3fc) >> 2;
				}
				row1Offset += width;
				row2Offset += width;
			}
			int[] copyPixels = nextLevelPixels;
			nextLevelPixels = levelPixels;
			levelPixels = copyPixels;
			width = levelWidth;
			height = levelHeight;
			levelWidth >>= 1;
			levelHeight >>= 1;
			smallest >>= 1;
			level++;
		}
	}

	public static void generate_software_mips(int target, int internalFormat, int width, int height, PixelFormat pformat, byte[] pixels) {
		if (width > 0 && !MathTools.is_power_of_two(width)) {
			throw new IllegalArgumentException("Texture width is not fixed");
		}
		if (height > 0 && !MathTools.is_power_of_two(height)) {
			throw new IllegalArgumentException("Texture height is not fixed");
		}
		int pixelSize = pformat.size;
		int level = 0;
		int smallest = width < height ? width : height;
		int levelWidth = width >> 1;
		int levelHeight = height >> 1;
		byte[] levelPixels = pixels;
		byte[] nextLevelPixels = new byte[levelWidth * levelHeight * pixelSize];
		for (;;) {
			glTexImage2Dub(target, level, internalFormat, width, height, 0, PixelFormat.getFormat(pformat), GL_UNSIGNED_BYTE, levelPixels, 0);
			if (smallest <= 1) {
				break;
			}
			int scanize = width * pixelSize;
			for (int i_37_ = 0; i_37_ < pixelSize; i_37_++) {
				int targetOffset = i_37_;
				int row1Offset = i_37_;
				int row2Offset = row1Offset + scanize;
				for (int i_41_ = 0; i_41_ < levelHeight; i_41_++) {
					for (int i_42_ = 0; i_42_ < levelWidth; i_42_++) {
						int val = levelPixels[row1Offset];
						row1Offset += pixelSize;
						val += levelPixels[row1Offset];
						row1Offset += pixelSize;
						val += levelPixels[row2Offset];
						row2Offset += pixelSize;
						val += levelPixels[row2Offset];
						row2Offset += pixelSize;
						nextLevelPixels[targetOffset] = (byte) (val >> 2);
						targetOffset += pixelSize;
					}
					row1Offset += scanize;
					row2Offset += scanize;
				}
			}
			byte[] copy = nextLevelPixels;
			nextLevelPixels = levelPixels;
			levelPixels = copy;
			width = levelWidth;
			height = levelHeight;
			levelWidth >>= 1;
			levelHeight >>= 1;
			smallest >>= 1;
			level++;
		}
	}

	public static void generate_software_mips(int target, int internalFormat, int width, int height, PixelFormat pformat, float[] pixels) {
		if (width > 0 && !MathTools.is_power_of_two(width)) {
			throw new IllegalArgumentException("Texture width is not fixed");
		}
		if (height > 0 && !MathTools.is_power_of_two(height)) {
			throw new IllegalArgumentException("Texture height is not fixed");
		}
		int size = pformat.size;
		int level = 0;
		int snakkest = width < height ? width : height;
		int levelWidth = width >> 1;
		int levelHeight = height >> 1;
		float[] levelPixels = pixels;
		float[] nextLevelPixels = new float[levelWidth * levelHeight * size];
		for (;;) {
			glTexImage2Df(target, level, internalFormat, width, height, 0, PixelFormat.getFormat(pformat), GL_FLOAT, levelPixels, 0);
			if (snakkest <= 1) {
				break;
			}
			int scanize = width * size;
			for (int i = 0; i < size; i++) {
				int targetOffset = i;
				int row1Offset = i;
				int row2Offset = row1Offset + scanize;
				for (int y = 0; y < levelHeight; y++) {
					for (int x = 0; x < levelWidth; x++) {
						float val = levelPixels[row1Offset];
						row1Offset += size;
						val += levelPixels[row1Offset];
						row1Offset += size;
						val += levelPixels[row2Offset];
						row2Offset += size;
						val += levelPixels[row2Offset];
						row2Offset += size;
						nextLevelPixels[targetOffset] = val * 0.25F;
						targetOffset += size;
					}
					row1Offset += scanize;
					row2Offset += scanize;
				}
			}
			float[] copy = nextLevelPixels;
			nextLevelPixels = levelPixels;
			levelPixels = copy;
			width = levelWidth;
			height = levelHeight;
			levelWidth >>= 1;
			levelHeight >>= 1;
			snakkest >>= 1;
			level++;
		}
	}

	public void filter() {
		GLState.bind_texture(handle);
		if (linear) {
			glTexParameteri(target, GL_TEXTURE_MIN_FILTER, mipmap ? GL_LINEAR_MIPMAP_LINEAR : GL_LINEAR);
			glTexParameteri(target, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		} else {
			glTexParameteri(target, GL_TEXTURE_MIN_FILTER, mipmap ? GL_NEAREST_MIPMAP_NEAREST : GL_NEAREST);
			glTexParameteri(target, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		}
	}

	public boolean force_mipmapping() {
		if (GLManager.framebuffer_objects_supported) {
			int old = get_memoryspace();
			GLState.bind_texture(handle);
			glGenerateMipmapEXT(target);
			mipmap = true;
			filter();
			update_memoryspace(old);
			return true;
		}
		return false;
	}

	public void set_mipmap_filter(boolean mipmap) {
		if (this.mipmap != mipmap) {
			int old = get_memoryspace();
			this.mipmap = true;
			filter();
			update_memoryspace(old);
		}
	}

	public void set_linear_filter(boolean linear) {
		if (this.linear != linear) {
			this.linear = linear;
			filter();
		}
	}

	public void destroy() {
		if (handle > 0) {
			GLManager.allocated_textures_size -= get_memoryspace();
			int[] temp = new int[1];
			temp[0] = handle;
			glDeleteTextures(1, temp, 0);
			handle = 0;
		}
	}

	public void request_destroy() {
		if (handle > 0) {
			GLManager.delete_texture(handle, get_memoryspace());
			handle = 0;
		}
	}

	public void update_memoryspace(int old) {
		GLManager.allocated_textures_size -= old;
		GLManager.allocated_textures_size += get_memoryspace();
	}

	public int get_memoryspace() {
		int size = pixel_format.size * data_type.size * pixels_count;
		return mipmap ? size * 4 / 3 : size;
	}

	@Override
	protected void finalize() throws Throwable {
		request_destroy();
	}
}
