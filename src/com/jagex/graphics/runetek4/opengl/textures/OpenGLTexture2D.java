package com.jagex.graphics.runetek4.opengl.textures;

import static jaggl.GLConstants.GL_BGRA;
import static jaggl.GLConstants.GL_CLAMP_TO_EDGE;
import static jaggl.GLConstants.GL_FLOAT;
import static jaggl.GLConstants.GL_REPEAT;
import static jaggl.GLConstants.GL_TEXTURE_2D;
import static jaggl.GLConstants.GL_TEXTURE_RECTANGLE;
import static jaggl.GLConstants.GL_TEXTURE_WRAP_S;
import static jaggl.GLConstants.GL_TEXTURE_WRAP_T;
import static jaggl.GLConstants.GL_UNPACK_ALIGNMENT;
import static jaggl.GLConstants.GL_UNPACK_ROW_LENGTH;
import static jaggl.GLConstants.GL_UNSIGNED_BYTE;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glPixelStorei;
import static jaggl.OpenGL.glTexImage2Df;
import static jaggl.OpenGL.glTexImage2Di;
import static jaggl.OpenGL.glTexImage2Dub;
import static jaggl.OpenGL.glTexParameteri;
import static jaggl.OpenGL.glTexSubImage2Di;
import static jaggl.OpenGL.glTexSubImage2Dub;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

import jaggl.OpenGL;

public class OpenGLTexture2D extends OpenGLTexture {

	public int width;
	public int height;

	public OpenGLTexture2D(int target, PixelFormat pixel_format, DataType data_type, int width, int height, boolean mipmap, float[] fs, PixelFormat class72_21_) {
		super(target, pixel_format, data_type, width * height, mipmap);
		this.width = width;
		this.height = height;
		glBindTexture(target, handle);
		if (mipmap && this.target != GL_TEXTURE_RECTANGLE) {
			generate_software_mips(target, PixelFormat.getInternalFormat(pixel_format, data_type), width, height, class72_21_, fs);
			set_mipmap_filter(true);
		} else {
			glTexImage2Df(this.target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), this.width, this.height, 0, PixelFormat.getFormat(class72_21_), GL_FLOAT, fs, 0);
			set_mipmap_filter(false);
		}
		set_linear_filter(true);
	}

	public OpenGLTexture2D(int target, int width, int height, boolean mimap, int[] pixels, int scanline, int scanoffset, boolean bool_4_) {
		super(target, PixelFormat.RGBA, DataType.INT8, width * height, mimap);
		this.width = width;
		this.height = height;
		if (bool_4_) {
			int[] is_5_ = new int[pixels.length];
			for (int i_6_ = 0; i_6_ < height; i_6_++) {
				int i_7_ = i_6_ * width;
				int i_8_ = (height - i_6_ - 1) * width;
				for (int i_9_ = 0; i_9_ < width; i_9_++) {
					is_5_[i_7_++] = pixels[i_8_++];
				}
			}
			pixels = is_5_;
		}
		glBindTexture(target, handle);
		if (this.target == GL_TEXTURE_RECTANGLE || !mimap || scanline != 0 || scanoffset != 0) {
			glPixelStorei(GL_UNPACK_ROW_LENGTH, scanline);
			glTexImage2Di(this.target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), this.width, this.height, 0, GL_BGRA, GLManager.byte_type, pixels, scanoffset * 4);
			glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);
			set_mipmap_filter(false);
		} else {
			generate_software_mips(this.target, PixelFormat.getInternalFormat(pixel_format, data_type), this.width, this.height, GL_BGRA, GLManager.byte_type, pixels);
			set_mipmap_filter(true);
		}
		set_linear_filter(true);
	}

	public OpenGLTexture2D(int i, PixelFormat internal_format, DataType data_type, int i_10_, int i_11_, boolean mipmap, byte[] pixels, PixelFormat data_format, boolean flipped) {
		super(i, internal_format, data_type, i_10_ * i_11_, mipmap);
		width = i_10_;
		height = i_11_;
		if (flipped) {
			byte[] is_14_ = new byte[pixels.length];
			for (int i_15_ = 0; i_15_ < i_11_; i_15_++) {
				int i_16_ = i_15_ * i_10_;
				int i_17_ = (i_11_ - i_15_ - 1) * i_10_;
				for (int i_18_ = 0; i_18_ < i_10_; i_18_++) {
					is_14_[i_16_++] = pixels[i_17_++];
				}
			}
			pixels = is_14_;
		}
		GLState.bind_texture(handle);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		if (mipmap && target != GL_TEXTURE_RECTANGLE) {
			generate_software_mips(i, PixelFormat.getInternalFormat(pixel_format, data_type), i_10_, i_11_, data_format, pixels);
			set_mipmap_filter(true);
		} else {
			glTexImage2Dub(target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), width, height, 0, PixelFormat.getFormat(data_format), GL_UNSIGNED_BYTE, pixels, 0);
			set_mipmap_filter(false);
		}
		glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
		set_linear_filter(true);
	}

	public OpenGLTexture2D(int target, PixelFormat format, DataType class86, int width, int height) {
		super(target, format, class86, width * height, false);
		this.width = width;
		this.height = height;
		glBindTexture(target, handle);
		glTexImage2Dub(this.target, 0, PixelFormat.getInternalFormat(pixel_format, data_type), width, height, 0, PixelFormat.getFormat(pixel_format), GL_UNSIGNED_BYTE, null, 0);
		set_linear_filter(true);
	}

	public void set_pixels(int xOffset, int yOffset, int width, int height, int[] pixels, int pixelOffset, int scanLength, boolean flipY) {
		if (scanLength == 0) {
			scanLength = width;
		}
		if (flipY) {
			int[] flipedPixels = new int[width * height];
			for (int y = 0; y < height; y++) {
				int dstOffset = y * width;
				int srcOffset = (height - y - 1) * scanLength + pixelOffset;
				for (int x = 0; x < width; x++) {
					flipedPixels[dstOffset++] = pixels[srcOffset++];
				}
			}
			pixels = flipedPixels;
		}
		glBindTexture(target, handle);
		if (width != scanLength) {
			glPixelStorei(OpenGL.GL_UNPACK_ROW_LENGTH, scanLength);
		}
		glTexSubImage2Di(target, 0, xOffset, this.height - yOffset - height, width, height, 32993, GLManager.byte_type, pixels, pixelOffset);
		if (width != scanLength) {
			glPixelStorei(OpenGL.GL_UNPACK_ROW_LENGTH, 0);
		}
	}

	public void set_pixels(int xoffset, int yoffset, int _width, int _height, byte[] data, PixelFormat pixel_format, int i_26_, int scanwidth, boolean bool) {
		if (scanwidth == 0) {
			scanwidth = _width;
		}
		if (bool) {
			int pixel_size = pixel_format.size;
			int dst_line_size = pixel_size * _width;
			int src_line_size = pixel_size * scanwidth;
			byte[] is_31_ = new byte[dst_line_size * _height];
			for (int copy_y = 0; copy_y < _height; copy_y++) {
				int copy_dst_offset = copy_y * dst_line_size;
				int copy_src_offset = (_height - copy_y - 1) * src_line_size + i_26_;
				for (int copy_x = 0; copy_x < dst_line_size; copy_x++) {
					is_31_[copy_dst_offset++] = data[copy_src_offset++];
				}
			}
			data = is_31_;
		}
		glBindTexture(target, handle);
		glPixelStorei(3317, 1);
		if (scanwidth != _width) {
			glPixelStorei(3314, scanwidth);
		}
		glTexSubImage2Dub(target, 0, xoffset, height - yoffset - _height, _width, _height, PixelFormat.getFormat(pixel_format), GL_UNSIGNED_BYTE, data, i_26_);
		if (scanwidth != _width) {
			glPixelStorei(3314, 0);
		}
		glPixelStorei(3317, 4);
	}

	public void set_repeat(boolean repeat_s, boolean repeat_t) {
		if (target == GL_TEXTURE_2D) {
			GLState.bind_texture(handle);
			glTexParameteri(target, GL_TEXTURE_WRAP_S, repeat_s ? GL_REPEAT : GL_CLAMP_TO_EDGE);
			glTexParameteri(target, GL_TEXTURE_WRAP_T, repeat_t ? GL_REPEAT : GL_CLAMP_TO_EDGE);
		}
	}

}
