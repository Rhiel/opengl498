package com.jagex.graphics.runetek4.media;

import com.jagex.Js5;
import com.jagex.MathUtils;
import com.jagex.Queuable;
import com.jagex.SpriteLoader;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLAlphaSprite;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareAlphaSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;

public abstract class Sprite extends Queuable {
	public int offsetx;
	public int offsety;
	public int width;
	public int height;
	public int trimmed_width;
	public int trimmed_height;

	public final void draw_rotated(int x, int y, int angle, int scalar) {
		int i_16_ = trimmed_width << 3;
		x = (x << 4) + (i_16_ & 0xf);
		int i_17_ = trimmed_height << 3;
		y = (y << 4) - -(0xf & i_17_);
		draw_rotated(i_16_, i_17_, x, y, angle, scalar);
	}

	public abstract void draw(int x, int y);

	public abstract void draw(int x, int y, int alpha);

	public abstract void draw(int x, int y, int width, int height);

	public abstract void draw(int x, int y, int width, int height, int alpha);

	// public abstract void draw_coloured(int x, int y, int alpha, int colour);

	public abstract void draw_rotated(int i, int i_27_, int i_28_, int i_29_, int angle, int scalar);

	public abstract void draw_clipped_right_anchor(int x, int y);

	public abstract void draw_clipped_left_anchor(int x, int y);

	public static Sprite[] load_alpha_all(Js5 sprites_js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(sprites_js5, groupid, fileid)) {
			return null;
		}
		return create_alpha_all();
	}

	public static Sprite[] create_alpha_all() {
		Sprite[] sprites = new Sprite[SpriteLoader.num_sprites];
		for (int index = 0; SpriteLoader.num_sprites > index; index++) {
			int i_21_ = SpriteLoader.sprites_height[index] * SpriteLoader.sprites_width[index];
			byte[] bs = SpriteLoader.sprites_pixels[index];
			if (SpriteLoader.sprites_translucent[index]) {
				byte[] var4 = SpriteLoader.sprites_alphas[index];
				int[] var5 = new int[i_21_];

				for (int var6 = 0; var6 < i_21_; ++var6) {
					var5[var6] = MathUtils.doBitwiseOr(MathUtils.bitAnd(var4[var6] << 24, -16777216), SpriteLoader.palette[MathUtils.bitAnd(bs[var6], 255)]);
				}
				if (GLManager.opengl_mode) {
					sprites[index] = new OpenGLAlphaSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[index], SpriteLoader.sprites_offsety[index], SpriteLoader.sprites_width[index], SpriteLoader.sprites_height[index], var5);
				} else {
					sprites[index] = new SoftwareAlphaSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[index], SpriteLoader.sprites_offsety[index], SpriteLoader.sprites_width[index], SpriteLoader.sprites_height[index], var5);
				}
			} else {
				int[] var8 = new int[i_21_];

				for (int var9 = 0; var9 < i_21_; ++var9) {
					var8[var9] = SpriteLoader.palette[MathUtils.bitAnd(bs[var9], 255)];
				}
				if (GLManager.opengl_mode) {
					sprites[index] = new OpenGLSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[index], SpriteLoader.sprites_offsety[index], SpriteLoader.sprites_width[index], SpriteLoader.sprites_height[index], var8);
				} else {
					sprites[index] = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[index], SpriteLoader.sprites_offsety[index], SpriteLoader.sprites_width[index], SpriteLoader.sprites_height[index], var8);
				}
			}
		}
		SpriteLoader.reset();
		return sprites;
	}

	public static SoftwareSprite load_software_alpha(Js5 js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(js5, groupid, fileid)) {
			return null;
		}
		return create_software_alpha();
	}

	public static SoftwareSprite create_software_alpha() {
		int i_1_ = SpriteLoader.sprites_height[0] * SpriteLoader.sprites_width[0];
		byte[] bs = SpriteLoader.sprites_pixels[0];
		SoftwareSprite var3;
		if (SpriteLoader.sprites_translucent[0]) {
			byte[] var4 = SpriteLoader.sprites_alphas[0];
			int[] var5 = new int[i_1_];
			for (int var6 = 0; var6 < i_1_; ++var6) {
				var5[var6] = MathUtils.doBitwiseOr(MathUtils.bitAnd(var4[var6] << 24, -16777216), SpriteLoader.palette[MathUtils.bitAnd(bs[var6], 255)]);
			}

			var3 = new SoftwareAlphaSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], var5);
		} else {
			int[] var8 = new int[i_1_];

			for (int var9 = 0; var9 < i_1_; ++var9) {
				var8[var9] = SpriteLoader.palette[MathUtils.bitAnd(bs[var9], 255)];
			}

			var3 = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], var8);
		}
		SpriteLoader.reset();
		return var3;
	}

	public static Sprite[] load_software_alpha_all(Js5 js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(js5, groupid, fileid)) {
			return null;
		}
		return create_software_alpha_all();
	}

	public static Sprite[] create_software_alpha_all() {
		Sprite[] class23_sub13_sub10s = new Sprite[SpriteLoader.num_sprites];
		for (int i = 0; i < SpriteLoader.num_sprites; i++) {
			int i_2_ = SpriteLoader.sprites_height[i] * SpriteLoader.sprites_width[i];
			byte[] bs = SpriteLoader.sprites_pixels[i];
			if (SpriteLoader.sprites_translucent[i]) {
				byte[] var4 = SpriteLoader.sprites_alphas[i];
				int[] var5 = new int[i_2_];

				for (int var6 = 0; var6 < i_2_; ++var6) {
					var5[var6] = MathUtils.doBitwiseOr(MathUtils.bitAnd(var4[var6] << 24, -16777216), SpriteLoader.palette[MathUtils.bitAnd(bs[var6], 255)]);
				}

				class23_sub13_sub10s[i] = new SoftwareAlphaSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[i], SpriteLoader.sprites_offsety[i], SpriteLoader.sprites_width[i], SpriteLoader.sprites_height[i], var5);
			} else {
				int[] var8 = new int[i_2_];

				for (int var9 = 0; var9 < i_2_; ++var9) {
					var8[var9] = SpriteLoader.palette[MathUtils.bitAnd(bs[var9], 255)];
				}

				class23_sub13_sub10s[i] = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[i], SpriteLoader.sprites_offsety[i], SpriteLoader.sprites_width[i], SpriteLoader.sprites_height[i], var8);
			}
		}
		SpriteLoader.reset();
		return class23_sub13_sub10s;
	}

	public static Sprite load_alpha(Js5 js5, String groupName) {
		return load_alpha(js5, js5.get_groupid(groupName), 0);
	}

	public static Sprite load_alpha(Js5 js5, int groupId, int fileId) {
		if (!SpriteLoader.cache_sprites(js5, groupId, fileId)) {
			return null;
		}
		return create_alpha();
	}

	public static Sprite create_alpha() {
		int num_pixels = SpriteLoader.sprites_width[0] * SpriteLoader.sprites_height[0];
		byte[] bs = SpriteLoader.sprites_pixels[0];
		Sprite sprite;
		if (SpriteLoader.sprites_translucent[0]) {
			byte[] alphas = SpriteLoader.sprites_alphas[0];
			int[] pixels = new int[num_pixels];
			for (int pixel = 0; pixel < num_pixels; ++pixel) {
				pixels[pixel] = MathUtils.doBitwiseOr(MathUtils.bitAnd(alphas[pixel] << 24, -16777216), SpriteLoader.palette[MathUtils.bitAnd(bs[pixel], 255)]);
			}
			if (GLManager.opengl_mode) {
				sprite = new OpenGLAlphaSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], pixels);
			} else {
				sprite = new SoftwareAlphaSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], pixels);
			}
		} else {
			int[] pixel = new int[num_pixels];
			for (int var9 = 0; var9 < num_pixels; ++var9) {
				pixel[var9] = SpriteLoader.palette[MathUtils.bitAnd(bs[var9], 255)];
			}
			if (GLManager.opengl_mode) {
				sprite = new OpenGLSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], pixel);
			} else {
				sprite = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], pixel);
			}
		}
		SpriteLoader.reset();
		return sprite;
	}

	public static Sprite load(Js5 js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(js5, groupid, fileid)) {
			return null;
		}
		return create();
	}

	public static Sprite create() {
		int var1 = SpriteLoader.sprites_width[0] * SpriteLoader.sprites_height[0];
		byte[] var2 = SpriteLoader.sprites_pixels[0];
		int[] var3 = new int[var1];
		for (int var4 = 0; var1 > var4; ++var4) {
			var3[var4] = SpriteLoader.palette[MathUtils.bitAnd(var2[var4], 255)];
		}
		Sprite sprite;
		if (GLManager.opengl_mode) {
			sprite = new OpenGLSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], var3);
		} else {
			sprite = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], var3);
		}
		SpriteLoader.reset();
		return sprite;
	}

	public static SoftwareSprite load_software(Js5 js5, String groupName) {
		return load_software(js5, js5.get_groupid(groupName), 0);
	}

	public static SoftwareSprite load_software(Js5 js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(js5, groupid, fileid)) {
			return null;
		}
		return create_software();
	}

	public static SoftwareSprite create_software() {
		int var1 = SpriteLoader.sprites_width[0] * SpriteLoader.sprites_height[0];
		byte[] var2 = SpriteLoader.sprites_pixels[0];
		int[] var3 = new int[var1];
		for (int var4 = 0; var1 > var4; ++var4) {
			var3[var4] = SpriteLoader.palette[MathUtils.bitAnd(var2[var4], 255)];
		}
		SoftwareSprite var6 = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], var3);
		SpriteLoader.reset();
		return var6;
	}

	public static SoftwareSprite[] load_software_all(Js5 sprites_js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(sprites_js5, groupid, fileid)) {
			return null;
		}
		return create_software_all();
	}

	public static SoftwareSprite[] create_software_all() {
		SoftwareSprite[] var1 = new SoftwareSprite[SpriteLoader.num_sprites];
		for (int var2 = 0; var2 < SpriteLoader.num_sprites; ++var2) {
			int var3 = SpriteLoader.sprites_height[var2] * SpriteLoader.sprites_width[var2];
			byte[] var4 = SpriteLoader.sprites_pixels[var2];
			int[] var5 = new int[var3];
			for (int var6 = 0; ~var6 > ~var3; ++var6) {
				var5[var6] = SpriteLoader.palette[var4[var6] & 0xff];
			}
			var1[var2] = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[var2], SpriteLoader.sprites_offsety[var2], SpriteLoader.sprites_width[var2], SpriteLoader.sprites_height[var2], var5);
		}
		SpriteLoader.reset();
		return var1;
	}

	public static Sprite[] load_all(Js5 sprites_js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(sprites_js5, groupid, fileid)) {
			return null;
		}
		return load_all();
	}

	public static final Sprite[] load_all() {
		Sprite[] var1 = new Sprite[SpriteLoader.num_sprites];
		for (int var2 = 0; ~SpriteLoader.num_sprites < ~var2; ++var2) {
			int var3 = SpriteLoader.sprites_width[var2] * SpriteLoader.sprites_height[var2];
			byte[] var4 = SpriteLoader.sprites_pixels[var2];
			int[] var5 = new int[var3];

			for (int var6 = 0; var6 < var3; ++var6) {
				var5[var6] = SpriteLoader.palette[var4[var6] & 0xff];
			}
			if (!GLManager.opengl_mode) {
				var1[var2] = new SoftwareSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[var2], SpriteLoader.sprites_offsety[var2], SpriteLoader.sprites_width[var2], SpriteLoader.sprites_height[var2], var5);
			} else {
				var1[var2] = new OpenGLSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[var2], SpriteLoader.sprites_offsety[var2], SpriteLoader.sprites_width[var2], SpriteLoader.sprites_height[var2], var5);
			}
		}
		SpriteLoader.reset();
		return var1;
	}
}
