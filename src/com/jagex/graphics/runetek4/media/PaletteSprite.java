package com.jagex.graphics.runetek4.media;

import com.jagex.Js5;
import com.jagex.SpriteLoader;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLPaletteSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public abstract class PaletteSprite {

	public int offset_x;
	public int offset_y;
	public int width;
	public int height;
	public int trim_height;
	public int trim_width;

	public PaletteSprite() {
		// NOOP
	}

	public abstract void draw(int x, int y);

	public abstract void draw(int x, int y, int alpha);

	public static PaletteSprite load(Js5 sprites_js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(sprites_js5, groupid, fileid)) {
			return null;
		}
		return create();
	}

	public static final PaletteSprite[] load_all(Js5 js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(js5, groupid, fileid)) {
			return null;
		}
		return create_all();
	}

	public static PaletteSprite[] create_all() {
		PaletteSprite[] sprites = new PaletteSprite[SpriteLoader.num_sprites];
		if (GLManager.opengl_mode) {
			for (int index = 0; index < SpriteLoader.num_sprites; index++) {
				sprites[index] = new OpenGLPaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[index], SpriteLoader.sprites_offsety[index], SpriteLoader.sprites_width[index], SpriteLoader.sprites_height[index], SpriteLoader.sprites_pixels[index], SpriteLoader.palette);
			}
		} else {
			for (int index = 0; index < SpriteLoader.num_sprites; index++) {
				sprites[index] = new SoftwarePaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[index], SpriteLoader.sprites_offsety[index], SpriteLoader.sprites_width[index], SpriteLoader.sprites_height[index], SpriteLoader.sprites_pixels[index], SpriteLoader.palette);
			}
		}
		SpriteLoader.reset();
		return sprites;
	}

	public static PaletteSprite load(Js5 worker, int archiveID) {
		if (!SpriteLoader.cache_sprite(worker, archiveID)) {
			return null;
		}
		return PaletteSprite.create();
	}

	public static PaletteSprite create() {
		PaletteSprite sprite;
		if (GLManager.opengl_mode) {
			sprite = new OpenGLPaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], SpriteLoader.sprites_pixels[0], SpriteLoader.palette);
		} else {
			sprite = new SoftwarePaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], SpriteLoader.sprites_pixels[0], SpriteLoader.palette);
		}
		SpriteLoader.reset();
		return sprite;
	}

	public static SoftwarePaletteSprite load_software(Js5 sprites_js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(sprites_js5, groupid, fileid)) {
			return null;
		}
		return create_software();
	}

	public static SoftwarePaletteSprite create_software() {
		SoftwarePaletteSprite var1 = new SoftwarePaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[0], SpriteLoader.sprites_offsety[0], SpriteLoader.sprites_width[0], SpriteLoader.sprites_height[0], SpriteLoader.sprites_pixels[0], SpriteLoader.palette);
		SpriteLoader.reset();
		return var1;
	}

	public static SoftwarePaletteSprite[] load_software_all(Js5 sprites_js5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(sprites_js5, groupid, fileid)) {
			return null;
		}
		return create_software_all();
	}

	public static SoftwarePaletteSprite[] create_software_all() {
		SoftwarePaletteSprite[] var1 = new SoftwarePaletteSprite[SpriteLoader.num_sprites];
		for (int var2 = 0; ~var2 > ~SpriteLoader.num_sprites; ++var2) {
			var1[var2] = new SoftwarePaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[var2], SpriteLoader.sprites_offsety[var2], SpriteLoader.sprites_width[var2], SpriteLoader.sprites_height[var2], SpriteLoader.sprites_pixels[var2], SpriteLoader.palette);
		}
		SpriteLoader.reset();
		return var1;
	}

}
