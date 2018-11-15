package com.jagex.graphics.runetek4.opengl.shadow;

import static jaggl.GLConstants.GL_FLOAT;
import static jaggl.GLConstants.GL_TRIANGLES;
import static jaggl.GLConstants.GL_UNSIGNED_SHORT;

import com.jagex.Packet;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.buffer.IndexBuffer;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexAttribute;
import com.jagex.graphics.runetek4.opengl.buffer.VertexBuffer;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture2D;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class ShadowMap {

	private static final byte[] pixels_buffer = new byte[16384];
	private static final Packet packet = new Packet(1620);

	private OpenGLTexture2D texture;
	private OpenGLVertexAttribute positions_attribute;
	private OpenGLVertexAttribute texcoords_attribute;
	private VertexBuffer vertices_buffer;
	private IndexBuffer indices_buffer;
	public int last_num_pixels = -1;
	public boolean is_dirty = true;

	public ShadowMap() {
		texcoords_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 2, 0);
		positions_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 8);
	}

	public void draw() {
		GLState.bind_texture(texture.handle);
		GLManager.load_vertices_array(positions_attribute, null, null, texcoords_attribute);
		GLManager.draw_index_buffer(indices_buffer, GL_TRIANGLES, 0, 384);
	}

	public final void update(int[][] heights, int x, int y) {
		packet.index = 0;
		for (int offset_y = 0; offset_y <= 8; offset_y++) {
			for (int offset_x = 0; offset_x <= 8; offset_x++) {
				if (GLManager.big_endianess) {
					packet.pf(offset_x / 8.0F);
					packet.pf(offset_y / 8.0F);

					packet.pf(offset_x * 128);
					packet.pf(heights[offset_x + x][offset_y + y]);
					packet.pf(offset_y * 128);

				} else {
					packet.ipf(offset_x / 8.0F);
					packet.ipf(offset_y / 8.0F);

					packet.ipf(offset_x * 128);
					packet.ipf(heights[offset_x + x][offset_y + y]);
					packet.ipf(offset_y * 128);
				}
			}
		}
		if (vertices_buffer == null) {
			vertices_buffer = GLManager.create_vertex_buffer(20, packet.byteBuffer, packet.index, false);
			positions_attribute.buffer = vertices_buffer;
			texcoords_attribute.buffer = vertices_buffer;
		} else {
			vertices_buffer.upload(20, packet.byteBuffer, packet.index);
		}
		packet.index = 0;
		for (int offset_y = 0; offset_y < 8; ++offset_y) {
			for (int offset_x = 0; offset_x < 8; ++offset_x) {
				if (GLManager.big_endianess) {
					packet.putShort(offset_x + (offset_y + 1) * 9);
					packet.putShort(offset_x + offset_y * 9);
					packet.putShort(offset_x + 1 + offset_y * 9);
					packet.putShort(offset_x + (offset_y + 1) * 9);
					packet.putShort(offset_x + 1 + offset_y * 9);
					packet.putShort(offset_x + 1 + (offset_y + 1) * 9);
				} else {
					packet.putLEShort(offset_x + (offset_y + 1) * 9);
					packet.putLEShort(offset_x + offset_y * 9);
					packet.putLEShort(offset_x + 1 + offset_y * 9);
					packet.putLEShort(offset_x + (offset_y + 1) * 9);
					packet.putLEShort(offset_x + 1 + offset_y * 9);
					packet.putLEShort(offset_x + 1 + (offset_y + 1) * 9);
				}
			}
		}
		if (indices_buffer == null) {
			indices_buffer = GLManager.create_index_buffer(GL_UNSIGNED_SHORT, packet.byteBuffer, packet.index, false);
		} else {
			indices_buffer.upload(GL_UNSIGNED_SHORT, packet.byteBuffer, packet.index);
		}
	}

	public boolean update(SoftwarePaletteSprite var1, int var2, int var3) {
		byte[] num_pixels = var1.pixels;
		int width = var1.width;
		int offset = var2 * 128 + 1 + (var3 * 128 + 1) * width;
		int var7 = 0;
		for (int var8 = -128; var8 < 0; var8++) {
			var7 = (var7 << 8) - var7;
			for (int var9 = -128; var9 < 0; var9++) {
				if (num_pixels[offset++] != 0) {
					++var7;
				}
			}

			offset += width - 128;
		}

		if (var7 == last_num_pixels) {
			return false;
		} else {
			last_num_pixels = var7;
			offset = var2 * 128 + 1 + (var3 * 128 + 1) * width;
			int var8 = 0;
			for (int var9 = -128; var9 < 0; ++var9) {
				for (int var10 = -128; var10 < 0; ++var10) {
					if (num_pixels[offset] != 0) {
						pixels_buffer[var8++] = 68;
					} else {
						int var11 = 0;
						if (num_pixels[offset - 1] != 0) {
							++var11;
						}

						if (num_pixels[offset + 1] != 0) {
							++var11;
						}

						if (num_pixels[offset - width] != 0) {
							++var11;
						}

						if (num_pixels[offset + width] != 0) {
							++var11;
						}
						pixels_buffer[var8++] = (byte) (17 * var11);
					}
					offset++;
				}
				offset += width - 128;
			}
			if (texture == null) {
				texture = new OpenGLTexture2D(3553, PixelFormat.ALPHA, DataType.INT8, 128, 128, false, pixels_buffer, PixelFormat.ALPHA, false);
				texture.set_repeat(false, false);
				texture.set_linear_filter(true);
			} else {
				texture.set_pixels(0, 0, 128, 128, pixels_buffer, PixelFormat.ALPHA, 0, 0, false);
			}
			return true;
		}
	}
}
