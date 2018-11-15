package com.jagex.graphics.runetek4.opengl.font;

import static com.jagex.graphics.runetek4.opengl.GLState.bind_texture;
import static com.jagex.graphics.runetek4.opengl.GLState.clip_mask;
import static com.jagex.graphics.runetek4.opengl.GLState.setup_2d_font_state;
import static jaggl.GLConstants.GL_TEXTURE0;
import static jaggl.GLConstants.GL_TEXTURE1;
import static jaggl.GLConstants.GL_TEXTURE_2D;
import static jaggl.OpenGL.glActiveTexture;
import static jaggl.OpenGL.glBegin;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glCallList;
import static jaggl.OpenGL.glColor3ub;
import static jaggl.OpenGL.glColor4ub;
import static jaggl.OpenGL.glDisable;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glEnd;
import static jaggl.OpenGL.glEndList;
import static jaggl.OpenGL.glGenLists;
import static jaggl.OpenGL.glLoadIdentity;
import static jaggl.OpenGL.glMultiTexCoord2f;
import static jaggl.OpenGL.glNewList;
import static jaggl.OpenGL.glTexCoord2f;
import static jaggl.OpenGL.glTexEnvi;
import static jaggl.OpenGL.glTranslatef;
import static jaggl.OpenGL.glVertex2f;

import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture2D;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLFont extends Font {

	private OpenGLTexture2D texture;
	public int texture_size;
	public int[] draw_lists;

	public OpenGLFont(byte[] var1, int[] var2, int[] var3, int[] var4, int[] var5, byte[][] var6) {
		super(var1, var2, var3, var4, var5);
		initialise(var6);
		compile_drawlist();
	}

	public void initialise(byte[][] glyphs_data) {
		for (int glyph = 0; glyph < 256; ++glyph) {
			if (glyphs_height[glyph] > texture_size) {
				texture_size = glyphs_height[glyph];
			}

			if (glyphs_width[glyph] > texture_size) {
				texture_size = glyphs_width[glyph];
			}
		}
		texture_size *= 16;
		texture_size = MathTools.get_greater_pow2(texture_size);
		int plot_size = texture_size / 16;
		byte[] pixels = new byte[texture_size * texture_size * 2];
		for (int glyph_index = 0; glyph_index < 256; glyph_index++) {
			int plot_x = glyph_index % 16 * plot_size;
			int plot_y = glyph_index / 16 * plot_size;
			int plot_offset = (plot_y * texture_size + plot_x) * 2;
			int data_offset = 0;
			int height = glyphs_height[glyph_index];
			int width = glyphs_width[glyph_index];
			byte[] glyph_data = glyphs_data[glyph_index];
			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width; ++x) {
					if (glyph_data[data_offset++] != 0) {
						pixels[plot_offset++] = -1;
						pixels[plot_offset++] = -1;
					} else {
						plot_offset += 2;
					}
				}
				plot_offset += (texture_size - width) * 2;
			}
		}
		texture = new OpenGLTexture2D(GL_TEXTURE_2D, PixelFormat.LUMINANCE_ALPHA, DataType.INT8, texture_size, texture_size, false, pixels, PixelFormat.LUMINANCE_ALPHA, false);
		texture.set_linear_filter(false);
	}

	public void compile_drawlist() {
		if (draw_lists == null) {
			draw_lists = new int[256];
			for (int var2 = 0; var2 < 256; ++var2) {
				float var3 = var2 % 16 / 16.0F;
				float var4 = var2 / 16 / 16.0F;
				float var5 = var3 + (float) glyphs_width[var2] / (float) texture_size;
				float var6 = var4 + (float) glyphs_height[var2] / (float) texture_size;
				draw_lists[var2] = glGenLists(1);
				glNewList(draw_lists[var2], 4864);
				{
					glBegin(6);
					{
						glTexCoord2f(var5, var4);
						glVertex2f(glyphs_width[var2], 0.0F);
						glTexCoord2f(var3, var4);
						glVertex2f(0.0F, 0.0F);
						glTexCoord2f(var3, var6);
						glVertex2f(0.0F, -glyphs_height[var2]);
						glTexCoord2f(var5, var6);
						glVertex2f(glyphs_width[var2], -glyphs_height[var2]);
					}
					glEnd();
				}
				glEndList();
			}
		}
	}

	@Override
	public void draw_glyph(int glyph, int x, int y, int var4, int var5, int colour, boolean var7) {
		if (clip_mask != null) {
			GLState.setup_2d_font_state();
			glColor3ub((byte) (colour >> 16), (byte) (colour >> 8), (byte) colour);
			glTranslatef(x, GLState.viewport_height - y, 0.0F);
			float var9 = glyph % 16 / 16.0F;
			float var10 = glyph / 16 / 16.0F;
			float var11 = var9 + (float) glyphs_width[glyph] / (float) texture_size;
			float var12 = var10 + (float) glyphs_height[glyph] / (float) texture_size;
			bind_texture(texture.handle);
			OpenGLSprite var13 = GLState.clip_mask;
			glActiveTexture(GL_TEXTURE1);
			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, var13.texture.handle);
			glTexEnvi(8960, '\u8571', 7681);
			glTexEnvi(8960, '\u8580', '\u8578');
			float var14 = (float) (x - GLState.clip_left) / (float) var13.texture_width;
			float var15 = (float) (y - GLState.clip_top) / (float) var13.texture_height;
			float var16 = (float) (x + var4 - GLState.clip_left) / (float) var13.texture_width;
			float var17 = (float) (y + var5 - GLState.clip_top) / (float) var13.texture_height;
			glBegin(6);
			glMultiTexCoord2f('\u84c1', var16, var15);
			glTexCoord2f(var11, var10);
			glVertex2f(glyphs_width[glyph], 0.0F);
			glMultiTexCoord2f('\u84c1', var14, var15);
			glTexCoord2f(var9, var10);
			glVertex2f(0.0F, 0.0F);
			glMultiTexCoord2f('\u84c1', var14, var17);
			glTexCoord2f(var9, var12);
			glVertex2f(0.0F, -glyphs_height[glyph]);
			glMultiTexCoord2f('\u84c1', var16, var17);
			glTexCoord2f(var11, var12);
			glVertex2f(glyphs_width[glyph], -glyphs_height[glyph]);
			glEnd();
			glTexEnvi(8960, '\u8571', 8448);
			glTexEnvi(8960, '\u8580', 5890);
			glDisable(GL_TEXTURE_2D);
			glActiveTexture(GL_TEXTURE0);
			glLoadIdentity();
		} else {
			setup_2d_font_state();
			bind_texture(texture.handle);
			glColor3ub((byte) (colour >> 16), (byte) (colour >> 8), (byte) colour);
			glTranslatef(x, GLState.viewport_height - y, 0.0F);
			glCallList(draw_lists[glyph]);
			glLoadIdentity();
		}
	}

	@Override
	public void draw_glyph(int glyph, int x, int y, int width, int hheight, int colour, int alpha, boolean var8) {
		setup_2d_font_state();
		bind_texture(texture.handle);
		glColor4ub((byte) (colour >> 16), (byte) (colour >> 8), (byte) colour, alpha > 255 ? -1 : (byte) alpha);
		glTranslatef(x, GLState.viewport_height - y, 0.0F);
		glCallList(draw_lists[glyph]);
		glLoadIdentity();
	}

	@Override
	public void finalize() throws Throwable {
		if (draw_lists != null) {
			for (int element : draw_lists) {
				GLManager.delete_list(element);
			}
			draw_lists = null;
		}
	}

}
