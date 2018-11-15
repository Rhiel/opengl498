package com.jagex.graphics.runetek4.opengl.sprite;

import static com.jagex.graphics.runetek4.opengl.GLState.bind_texture;
import static com.jagex.graphics.runetek4.opengl.GLState.setup_2d_opaque_sprites_state;
import static com.jagex.graphics.runetek4.opengl.GLState.setup_2d_translucent_sprites_state;
import static jaggl.GLConstants.GL_COMPILE;
import static jaggl.GLConstants.GL_LINEAR;
import static jaggl.GLConstants.GL_NEAREST;
import static jaggl.GLConstants.GL_TEXTURE_2D;
import static jaggl.GLConstants.GL_TEXTURE_MAG_FILTER;
import static jaggl.GLConstants.GL_TEXTURE_MIN_FILTER;
import static jaggl.GLConstants.GL_TRIANGLE_FAN;
import static jaggl.OpenGL.glBegin;
import static jaggl.OpenGL.glCallList;
import static jaggl.OpenGL.glColor4f;
import static jaggl.OpenGL.glEnd;
import static jaggl.OpenGL.glEndList;
import static jaggl.OpenGL.glGenLists;
import static jaggl.OpenGL.glLoadIdentity;
import static jaggl.OpenGL.glNewList;
import static jaggl.OpenGL.glTexCoord2f;
import static jaggl.OpenGL.glTexParameteri;
import static jaggl.OpenGL.glTranslatef;
import static jaggl.OpenGL.glVertex2f;

import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTextureRectangle;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLPaletteSprite extends PaletteSprite {

	private OpenGLTextureRectangle texture;
	public int listId = -1;
	public int filter_mode = 0;
	public int cycleid;
	public int texture_height;
	public int texture_width;

	public OpenGLPaletteSprite(int var1, int var2, int var3, int var4, int width, int height, byte[] index, int[] palette) {
		trim_width = var1;
		trim_height = var2;
		offset_x = var3;
		offset_y = var4;
		this.width = width;
		this.height = height;
		upload_data(index, palette);
		compile_drawlist();
	}

	public void upload_data(byte[] src_pixels, int[] src_palette) {
		texture_width = MathTools.get_greater_pow2(width);
		texture_height = MathTools.get_greater_pow2(height);
		int[] pixels = new int[src_pixels.length];
		for (int index = 0; index < src_pixels.length; index++) {
			if (src_pixels[index] != 0) {
				int argb = src_palette[src_pixels[index] & 0xff];
				pixels[index] = 0xff << 24 | argb & 0xffffff;
			} else {
				pixels[index] = 0;
			}
		}
		texture = OpenGLTextureRectangle.method525(PixelFormat.RGBA, DataType.INT8, texture_width, texture_height);
		texture.set_pixels(0, 0, texture_width, texture_height, pixels, 0, width, false);
	}

	@Override
	public void draw(int x, int y) {
		setup_2d_opaque_sprites_state();
		x += offset_x;
		y += offset_y;
		bind_texture(texture.handle);
		update_filtermode(1);
		glTranslatef(x, GLState.viewport_height - y, 0.0F);
		glCallList(listId);
		glLoadIdentity();
	}

	@Override
	public void draw(int x, int y, int alpha) {
		setup_2d_translucent_sprites_state();
		x += offset_x;
		y += offset_y;
		bind_texture(texture.handle);
		update_filtermode(1);
		glColor4f(1.0F, 1.0F, 1.0F, alpha / 256.0F);
		glTranslatef(x, GLState.viewport_height - y, 0.0F);
		glCallList(listId);
		glLoadIdentity();
	}

	public void compile_drawlist() {
		float u = (float) width / (float) texture_width;
		float v = (float) height / (float) texture_height;
		if (listId == -1) {
			listId = glGenLists(1);
			cycleid = GLManager.memorycycle;
		}
		glNewList(listId, GL_COMPILE);
		{
			glBegin(GL_TRIANGLE_FAN);
			{
				glTexCoord2f(u, 0.0F);
				glVertex2f(width, 0.0F);
				glTexCoord2f(0.0F, 0.0F);
				glVertex2f(0.0F, 0.0F);
				glTexCoord2f(0.0F, v);
				glVertex2f(0.0F, -height);
				glTexCoord2f(u, v);
				glVertex2f(width, -height);
			}
			glEnd();
		}
		glEndList();
	}

	public void update_filtermode(int mode) {
		if (filter_mode != mode) {
			filter_mode = mode;
			if (mode == 2) {
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			} else {
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			}

		}
	}

	@Override
	public void finalize() throws Throwable {
		if (listId != -1) {
			GLManager.delete_list(listId);
			listId = -1;
		}
	}

}
