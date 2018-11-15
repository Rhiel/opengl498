package com.jagex;

import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.font.OpenGLFont;
import com.jagex.graphics.runetek4.software.font.SoftwareFont;

/**
 * @author Walied K. Yassen
 */
public final class FontCache {

	public static MemoryCache metrics_cache = new MemoryCache(4);
	public static SoftwareFont objnum_font;
	public static Font p11_full;
	public static Font p12_full;
	public static Font b12_full;

	public static void initialise(Js5 fontsJs5, Js5 spritesJs5) {
		p11_full = load_font(fontsJs5, spritesJs5, StaticMedia.p11_full_groupid, 0);
		p12_full = load_font(fontsJs5, spritesJs5, StaticMedia.p12_full_groupid, 0);
		b12_full = load_font(fontsJs5, spritesJs5, StaticMedia.b12_full_groupid, 0);
		if (!GLManager.opengl_mode) {
			objnum_font = (SoftwareFont) p11_full;
		} else {
			objnum_font = load_software_font(spritesJs5, fontsJs5, StaticMedia.p11_full_groupid, 0);
		}
	}

	public static Font load_font(Js5 fontsJs5, Js5 spritesJs5, String groupName) {
		return load_font(fontsJs5, spritesJs5, spritesJs5.get_groupid(groupName), 0);
	}

	public static Font load_font(Js5 fontsJs5, Js5 spritesJs5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(spritesJs5, groupid, fileid)) {
			return null;
		}
		return load_font(fontsJs5.get_file(groupid, fileid));
	}

	public static Font load_font(byte[] data) {
		if (data == null) {
			return null;
		}
		Font font;
		if (GLManager.opengl_mode) {
			font = new OpenGLFont(data, SpriteLoader.sprites_offsetx, SpriteLoader.sprites_offsety, SpriteLoader.sprites_width, SpriteLoader.sprites_height, SpriteLoader.sprites_pixels);
		} else {
			font = new SoftwareFont(data, SpriteLoader.sprites_offsetx, SpriteLoader.sprites_offsety, SpriteLoader.sprites_width, SpriteLoader.sprites_height, SpriteLoader.sprites_pixels);
		}
		SpriteLoader.reset();
		return font;
	}

	public static SoftwareFont load_software_font(Js5 spritesJs5, Js5 fontsJs5, String groupName) {
		return load_software_font(fontsJs5, spritesJs5, spritesJs5.get_groupid(groupName), 0);
	}

	public static SoftwareFont load_software_font(Js5 spritesJs5, Js5 fontsJs5, int groupid, int fileid) {
		if (!SpriteLoader.cache_sprites(spritesJs5, groupid, fileid)) {
			return null;
		}
		return load_software_font(fontsJs5.get_file(groupid, fileid));
	}

	public static SoftwareFont load_software_font(byte[] data) {
		if (data == null) {
			return null;
		}
		SoftwareFont font = new SoftwareFont(data, SpriteLoader.sprites_offsetx, SpriteLoader.sprites_offsety, SpriteLoader.sprites_width, SpriteLoader.sprites_height, SpriteLoader.sprites_pixels);
		SpriteLoader.reset();
		return font;
	}

	public static SoftwareFont load_metrics(int font_id) {
		SoftwareFont font = (SoftwareFont) metrics_cache.get(font_id);
		if (font != null) {
			return font;
		} else {
			byte[] data = CacheConstants.fonts_js5.get_file(font_id, 0);
			if (data == null) {
				DebugMissing.notify_font(font_id);
				return load_metrics(StaticMedia.p11_full_groupid);
			}
			font = new SoftwareFont(data);
			font.set_glyphs(StaticMedia.mod_icons, null);
			FontCache.metrics_cache.put(font_id, font);
			return font;
		}
	}

	public FontCache() {
		// NOOP
	}

}
