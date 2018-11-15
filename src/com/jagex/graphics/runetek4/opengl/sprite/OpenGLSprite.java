package com.jagex.graphics.runetek4.opengl.sprite;

import static com.jagex.graphics.runetek4.opengl.GLState.bind_texture;
import static com.jagex.graphics.runetek4.opengl.GLState.clip_left;
import static com.jagex.graphics.runetek4.opengl.GLState.clip_top;
import static com.jagex.graphics.runetek4.opengl.GLState.setup_2d_opaque_sprites_state;
import static com.jagex.graphics.runetek4.opengl.GLState.setup_2d_translucent_sprites_state;
import static com.jagex.graphics.runetek4.opengl.GLState.viewport_height;
import static jaggl.GLConstants.GL_COMBINE_RGB;
import static jaggl.GLConstants.GL_LINEAR;
import static jaggl.GLConstants.GL_MODULATE;
import static jaggl.GLConstants.GL_NEAREST;
import static jaggl.GLConstants.GL_PREVIOUS;
import static jaggl.GLConstants.GL_REPLACE;
import static jaggl.GLConstants.GL_SOURCE0_RGB;
import static jaggl.GLConstants.GL_TEXTURE;
import static jaggl.GLConstants.GL_TEXTURE0;
import static jaggl.GLConstants.GL_TEXTURE1;
import static jaggl.GLConstants.GL_TEXTURE_2D;
import static jaggl.GLConstants.GL_TEXTURE_ENV;
import static jaggl.GLConstants.GL_TEXTURE_MAG_FILTER;
import static jaggl.GLConstants.GL_TEXTURE_MIN_FILTER;
import static jaggl.GLConstants.GL_TRIANGLE_FAN;
import static jaggl.OpenGL.*;

import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTextureRectangle;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLSprite extends Sprite {

	public OpenGLTextureRectangle texture;
	public int texture_width;
	public int texture_height;
	public int drawlist_id = -1;
	public int filter_mode = 0;

	public OpenGLSprite(int var1, int var2, int var3, int var4, int var5, int var6, int[] var7) {
		trimmed_width = var1;
		trimmed_height = var2;
		offsetx = var3;
		offsety = var4;
		width = var5;
		height = var6;
		upload_data(var7);
		compile_drawlist();
	}

	public OpenGLSprite(SoftwareSprite source_sprite) {
		trimmed_width = source_sprite.trimmed_width;
		trimmed_height = source_sprite.trimmed_height;
		offsetx = source_sprite.offsetx;
		offsety = source_sprite.offsety;
		width = source_sprite.width;
		height = source_sprite.height;
		upload_data(source_sprite.pixels);
		compile_drawlist();
	}

	public void upload_data(int[] src_pixels) {
		texture_width = MathTools.get_greater_pow2(width);
		texture_height = MathTools.get_greater_pow2(height);
		int[] pixels = new int[src_pixels.length];
		for (int index = 0; index < src_pixels.length; index++) {
			int argb = src_pixels[index];
			if (argb != 0) {
				pixels[index] = 0xff000000 | argb & 0xffffff;
			} else {
				pixels[index] = 0;
			}
		}
		texture = OpenGLTextureRectangle.method525(PixelFormat.RGBA, DataType.INT8, texture_width, texture_height);
		texture.set_pixels(0, 0, texture_width, texture_height, pixels, 0, width, false);
	}

	public void compile_drawlist() {
		float u = (float) width / texture_width;
		float v = (float) height / texture_height;
		if (drawlist_id == -1) {
			drawlist_id = glGenLists(1);
		}
		glNewList(drawlist_id, 4864);
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

	@Override
	public void draw(int x, int y) {
		setup_2d_opaque_sprites_state();
		x += offsetx;
		y += offsety;
		bind_texture(texture.handle);
		update_filtermode(1);
		glTranslatef(x, viewport_height - y, 0.0F);
		glCallList(drawlist_id);
		glLoadIdentity();
	}

	@Override
	public void draw(int x, int y, int alpha) {
		setup_2d_translucent_sprites_state();
		x += offsetx;
		y += offsety;
		bind_texture(texture.handle);
		update_filtermode(1);
		glColor4f(1.0F, 1.0F, 1.0F, alpha / 256.0F);
		glTranslatef(x, viewport_height - y, 0.0F);
		glCallList(drawlist_id);
		glLoadIdentity();
	}

	@Override
	public void draw(int x, int y, int width, int height) {
		if (width > 0 && height > 0) {
			setup_2d_opaque_sprites_state();
			int var5 = this.width;
			int var6 = this.height;
			int var7 = 0;
			int var8 = 0;
			int var9 = trimmed_width;
			int var10 = trimmed_height;
			int var11 = (var9 << 16) / width;
			int var12 = (var10 << 16) / height;
			int var13;
			if (offsetx > 0) {
				var13 = ((offsetx << 16) + var11 - 1) / var11;
				x += var13;
				var7 += var13 * var11 - (offsetx << 16);
			}
			if (offsety > 0) {
				var13 = ((offsety << 16) + var12 - 1) / var12;
				y += var13;
				var8 += var13 * var12 - (offsety << 16);
			}
			if (var5 < var9) {
				width = ((var5 << 16) - var7 + var11 - 1) / var11;
			}

			if (var6 < var10) {
				height = ((var6 << 16) - var8 + var12 - 1) / var12;
			}
			bind_texture(texture.handle);
			update_filtermode(2);
			float start_x = x;
			float end_x = start_x + width;
			float start_y = viewport_height - y;
			float end_y = start_y - height;
			float u = (float) this.width / texture_width;
			float v = (float) this.height / texture_height;
			glBegin(GL_TRIANGLE_FAN);
			{
				glTexCoord2f(u, 0.0F);
				glVertex2f(end_x, start_y);
				glTexCoord2f(0.0F, 0.0F);
				glVertex2f(start_x, start_y);
				glTexCoord2f(0.0F, v);
				glVertex2f(start_x, end_y);
				glTexCoord2f(u, v);
				glVertex2f(end_x, end_y);
			}
			glEnd();
		}
	}

	@Override
	public void draw(int x, int y, int width, int height, int alpha) {
		if (width > 0 && height > 0) {
			setup_2d_translucent_sprites_state();
			int var6 = this.width;
			int var7 = this.height;
			int var8 = 0;
			int var9 = 0;
			int var10 = trimmed_width;
			int var11 = trimmed_height;
			int var12 = (var10 << 16) / width;
			int var13 = (var11 << 16) / height;
			int var14;
			if (offsetx > 0) {
				var14 = ((offsetx << 16) + var12 - 1) / var12;
				x += var14;
				var8 += var14 * var12 - (offsetx << 16);
			}
			if (offsety > 0) {
				var14 = ((offsety << 16) + var13 - 1) / var13;
				y += var14;
				var9 += var14 * var13 - (offsety << 16);
			}
			if (var6 < var10) {
				width = ((var6 << 16) - var8 + var12 - 1) / var12;
			}
			if (var7 < var11) {
				height = ((var7 << 16) - var9 + var13 - 1) / var13;
			}
			bind_texture(texture.handle);
			update_filtermode(1);
			float var15 = x;
			float var16 = var15 + width;
			float var17 = viewport_height - y;
			float var18 = var17 - height;
			float var19 = (float) this.width / texture_width;
			float var20 = (float) this.height / texture_height;
			float alpha_f = alpha / 256.0F;
			glBegin(GL_TRIANGLE_FAN);
			{
				glColor4f(1.0F, 1.0F, 1.0F, alpha_f);
				glTexCoord2f(var19, 0.0F);
				glVertex2f(var16, var17);
				glTexCoord2f(0.0F, 0.0F);
				glVertex2f(var15, var17);
				glTexCoord2f(0.0F, var20);
				glVertex2f(var15, var18);
				glTexCoord2f(var19, var20);
				glVertex2f(var16, var18);
			}
			glEnd();
		}
	}

	@Override
	public void draw_rotated(int x, int y, int xoff, int yoff, int angle, int scalar) {
		setup_2d_opaque_sprites_state();
		bind_texture(texture.handle);
		update_filtermode(1);
		x -= offsetx << 4;
		y -= offsety << 4;
		glTranslatef(xoff / 16.0F, viewport_height - yoff / 16.0F, 0.0F);
		glRotatef(angle * 0.005493164F, 0.0F, 0.0F, 1.0F);
		if (scalar != 4096) {
			glScalef(scalar / 4096.0F, scalar / 4096.0F, 0.0F);
		}
		glTranslatef(-x / 16.0F, y / 16.0F, 0.0F);
		glCallList(drawlist_id);
		glLoadIdentity();
	}

	@Override
	public void draw_clipped_left_anchor(int x, int y) {
		setup_2d_opaque_sprites_state();
		x += offsetx;
		y += offsety;
		bind_texture(texture.handle);
		update_filtermode(1);
		glTranslatef(x, viewport_height - y, 0.0F);
		glCallList(drawlist_id);
		glLoadIdentity();
	}

	@Override
	public void draw_clipped_right_anchor(int x, int y) {
		setup_2d_opaque_sprites_state();
		x += offsetx;
		y += offsety;
		bind_texture(texture.handle);
		update_filtermode(1);
		glTranslatef(x, viewport_height - y, 0.0F);
		float var4 = (float) width / texture_width;
		float var5 = (float) height / texture_height;
		glBegin(GL_TRIANGLE_FAN);
		{
			glTexCoord2f(0.0F, 0.0F);
			glVertex2f(width, 0.0F);
			glTexCoord2f(var4, 0.0F);
			glVertex2f(0.0F, 0.0F);
			glTexCoord2f(var4, var5);
			glVertex2f(0.0F, -height);
			glTexCoord2f(0.0F, var5);
			glVertex2f(width, -height);
			glEnd();
		}
		glLoadIdentity();
	}

	public void method645(int var1, int var2, OpenGLSprite var3) {
		if (var3 != null) {
			setup_2d_opaque_sprites_state();
			bind_texture(var3.texture.handle);
			var3.update_filtermode(1);
			bind_texture(texture.handle);
			update_filtermode(1);
			glActiveTexture(GL_TEXTURE1);
			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, var3.texture.handle);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PREVIOUS);
			float var5 = (float) (var1 - clip_left) / var3.texture_width;
			float var6 = (float) (var2 - clip_top) / var3.texture_height;
			float var7 = (float) (var1 + width - clip_left) / var3.texture_width;
			float var8 = (float) (var2 + height - clip_top) / var3.texture_height;
			var1 += offsetx;
			var2 += offsety;
			glBegin(6);
			{
				glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				float var9 = (float) width / texture_width;
				float var10 = (float) height / texture_height;
				glMultiTexCoord2f(GL_TEXTURE1, var7, var6);
				glTexCoord2f(var9, 0.0F);
				glVertex2f(var1 + width, viewport_height - var2);
				glMultiTexCoord2f(GL_TEXTURE1, var5, var6);
				glTexCoord2f(0.0F, 0.0F);
				glVertex2f(var1, viewport_height - var2);
				glMultiTexCoord2f(GL_TEXTURE1, var5, var8);
				glTexCoord2f(0.0F, var10);
				glVertex2f(var1, viewport_height - (var2 + height));
				glMultiTexCoord2f(GL_TEXTURE1, var7, var8);
				glTexCoord2f(var9, var10);
				glVertex2f(var1 + width, viewport_height - (var2 + height));
			}
			glEnd();
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
			glDisable(GL_TEXTURE_2D);
			glActiveTexture(GL_TEXTURE0);

		}
	}

	public void draw(int var1, int var2, int var3, int var4, int var5, int var6, int angle, int var8, OpenGLSprite sprite) {
		if (sprite != null) {
			setup_2d_opaque_sprites_state();
			bind_texture(sprite.texture.handle);
			sprite.update_filtermode(1);
			bind_texture(texture.handle);
			update_filtermode(1);
			glActiveTexture(GL_TEXTURE1);
			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, sprite.texture.handle);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PREVIOUS);
			int var11 = -var3 / 2;
			int var12 = -var4 / 2;
			int var13 = -var11;
			int var14 = -var12;
			int var15 = (int) (Math.sin(angle / 326.11D) * 65536.0D);
			int var16 = (int) (Math.cos(angle / 326.11D) * 65536.0D);
			var15 = var15 * var8 >> 8;
			var16 = var16 * var8 >> 8;
			int var17 = (var5 << 16) + var12 * var15 + var11 * var16;
			int var18 = (var6 << 16) + var12 * var16 - var11 * var15;
			int var19 = (var5 << 16) + var12 * var15 + var13 * var16;
			int var20 = (var6 << 16) + var12 * var16 - var13 * var15;
			int var21 = (var5 << 16) + var14 * var15 + var11 * var16;
			int var22 = (var6 << 16) + var14 * var16 - var11 * var15;
			int var23 = (var5 << 16) + var14 * var15 + var13 * var16;
			int var24 = (var6 << 16) + var14 * var16 - var13 * var15;
			float var25 = (float) sprite.width / (float) sprite.texture_width;
			float var26 = (float) sprite.height / (float) sprite.texture_height;
			glBegin(6);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			float var27 = 65536.0F * texture_width;
			float var28 = 65536 * texture_height;
			glMultiTexCoord2f(GL_TEXTURE1, var25, 0.0F);
			glTexCoord2f(var19 / var27, var20 / var28);
			glVertex2f(var1 + var3, GLState.viewport_height - var2);
			glMultiTexCoord2f(GL_TEXTURE1, 0.0F, 0.0F);
			glTexCoord2f(var17 / var27, var18 / var28);
			glVertex2f(var1, GLState.viewport_height - var2);
			glMultiTexCoord2f(GL_TEXTURE1, 0.0F, var26);
			glTexCoord2f(var21 / var27, var22 / var28);
			glVertex2f(var1, GLState.viewport_height - (var2 + var4));
			glMultiTexCoord2f(GL_TEXTURE1, var25, var26);
			glTexCoord2f(var23 / var27, var24 / var28);
			glVertex2f(var1 + var3, GLState.viewport_height - (var2 + var4));
			glEnd();
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
			glDisable(GL_TEXTURE_2D);
			glActiveTexture(GL_TEXTURE0);
		}
	}

	public void draw_scaled(int x, int y, int alpha, int scalex, int scaley) {
		setup_2d_translucent_sprites_state();
		bind_texture(texture.handle);
		update_filtermode(1);
		float var7 = (float) width / texture_width;
		float var8 = (float) height / texture_height;
		var7 *= scalex;
		var8 *= scaley;
		int var9 = x + offsetx;
		int var10 = var9 + width * scalex;
		int var11 = viewport_height - y - offsety;
		int var12 = var11 - height * scaley;
		float var13 = alpha / 256.0F;
		glBegin(GL_TRIANGLE_FAN);
		{
			glColor4f(1.0F, 1.0F, 1.0F, var13);
			glTexCoord2f(var7, 0.0F);
			glVertex2f(var10, var11);
			glTexCoord2f(0.0F, 0.0F);
			glVertex2f(var9, var11);
			glTexCoord2f(0.0F, var8);
			glVertex2f(var9, var12);
			glTexCoord2f(var7, var8);
			glVertex2f(var10, var12);
		}
		glEnd();
	}

	public void draw_scaled(int x, int y, int scalex, int scaley) {
		setup_2d_opaque_sprites_state();
		bind_texture(texture.handle);
		update_filtermode(1);
		float var6 = (float) width / texture_width;
		float var7 = (float) height / texture_height;
		var6 *= scalex;
		var7 *= scaley;
		int var8 = x + offsetx;
		int var9 = var8 + width * scalex;
		int var10 = viewport_height - y - offsety;
		int var11 = var10 - height * scaley;
		glBegin(GL_TRIANGLE_FAN);
		{
			glTexCoord2f(var6, 0.0F);
			glVertex2f(var9, var10);
			glTexCoord2f(0.0F, 0.0F);
			glVertex2f(var8, var10);
			glTexCoord2f(0.0F, var7);
			glVertex2f(var8, var11);
			glTexCoord2f(var6, var7);
			glVertex2f(var9, var11);
		}
		glEnd();
	}

	public void method648(int x, int y, int xan, int yan, int zan, int scalar) {
		setup_2d_opaque_sprites_state();
		bind_texture(texture.handle);
		update_filtermode(2);
		x -= offsetx << 4;
		y -= offsety << 4;
		glTranslatef(xan / 16.0F, viewport_height - yan / 16.0F, 0.0F);
		glRotatef(-zan * 0.005493164F, 0.0F, 0.0F, 1.0F);
		if (scalar != 4096) {
			glScalef(scalar / 4096.0F, scalar / 4096.0F, 0.0F);
		}
		glTranslatef(-x / 16.0F, y / 16.0F, 0.0F);
		glCallList(drawlist_id);
		glLoadIdentity();
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
		if (drawlist_id != -1) {
			GLManager.delete_list(drawlist_id);
			drawlist_id = -1;
		}
	}

}
