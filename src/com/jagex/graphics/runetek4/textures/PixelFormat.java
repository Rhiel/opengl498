package com.jagex.graphics.runetek4.textures;

import jaggl.OpenGL;

public enum PixelFormat {

	RGB(9, 3),
	RGBA(3, 4),
	ALPHA(2, 1),
	LUMINANCE(4, 1),
	LUMINANCE_ALPHA(6, 2),
	DEPTH_COMPONENT(7, 1),
	aClass72_615(5, 3),
	aClass72_616(0, 4),
	COMPRESSED_RGBA_S3TC_DXT1(8, 1),
	COMPRESSED_RGBA_S3TC_DXT5(1, 1);

	public int id;
	public int size;

	private PixelFormat(int id, int size) {
		this.id = id;
		this.size = size;
	}

	public static int getFormat(PixelFormat pixelFormat) {
		switch (pixelFormat) {
			case RGB:
				return OpenGL.GL_RGB;
			case RGBA:
				return OpenGL.GL_RGBA;
			case ALPHA:
				return OpenGL.GL_ALPHA;
			case LUMINANCE:
				return OpenGL.GL_LUMINANCE;
			case LUMINANCE_ALPHA:
				return OpenGL.GL_LUMINANCE_ALPHA;
			case DEPTH_COMPONENT:
				return OpenGL.GL_DEPTH_COMPONENT;
			default:
				throw new IllegalStateException("Unrecognized pixel format: " + pixelFormat);
		}
	}

	public static int getInternalFormat(PixelFormat pixel, DataType class86) {
		if (class86 == DataType.INT8) {
			switch (pixel) {
				case RGB:
					return OpenGL.GL_RGB;
				case RGBA:
					return OpenGL.GL_RGBA;
				case ALPHA:
					return OpenGL.GL_ALPHA;
				case LUMINANCE:
					return OpenGL.GL_LUMINANCE;
				case LUMINANCE_ALPHA:
					return OpenGL.GL_LUMINANCE_ALPHA;
				default:
					throw new IllegalArgumentException();
			}
		}
		if (class86 == DataType.INT16) {
			switch (pixel.id) {
				case 2:
					return OpenGL.GL_ALPHA16;
				case 3:
					return OpenGL.GL_RGBA16;
				case 4:
					return OpenGL.GL_LUMINANCE16;
				case 6:
					return OpenGL.GL_LUMINANCE_ALPHA16UI_EXT;
				case 7:
					return OpenGL.GL_DEPTH_COMPONENT16;
				case 9:
					return OpenGL.GL_RGB16;
				default:
					throw new IllegalArgumentException();
			}
		}
		if (class86 == DataType.INT24) {
			switch (pixel.id) {
				default:
					throw new IllegalArgumentException();
				case 7:
					return OpenGL.GL_DEPTH_COMPONENT24;
			}
		}
		if (class86 == DataType.FLOATING_POINT16) {
			switch (pixel.id) {
				case 2:
					return OpenGL.GL_ALPHA16F_ARB;
				case 3:
					return OpenGL.GL_RGBA16F_ARB;
				case 4:
					return OpenGL.GL_LUMINANCE16F_ARB;
				case 6:
					return OpenGL.GL_LUMINANCE_ALPHA16F_ARB;
				case 9:
					return OpenGL.GL_RGB16F;
				default:
					throw new IllegalArgumentException();
			}
		}
		if (class86 == DataType.FLOATING_POINT32) {
			switch (pixel.id) {
				case 2:
					return OpenGL.GL_ALPHA32F_ARB;
				case 3:
					return OpenGL.GL_RGBA32F;
				case 4:
					return OpenGL.GL_LUMINANCE32F_ARB;
				case 6:
					return OpenGL.GL_LUMINANCE_ALPHA32F_ARB;
				case 9:
					return OpenGL.GL_RGB32F;
				default:
					throw new IllegalArgumentException();
			}
		}
		throw new IllegalArgumentException();
	}
}
