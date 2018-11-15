package com.jagex;

import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.rs2.client.scene.shadow.SceneShadowMapper;

/**
 * @author Walied K. Yassen
 */
public final class StaticMedia {

	public static int p11_full_groupid;
	public static int p12_full_groupid;
	public static int b12_full_groupid;
	public static int mapfunction_groupid;
	public static int hitmarks_groupid;
	public static int hitbar_default_groupid;
	public static int headicons_pk_groupid;
	public static int haedicons_prayer_groupid;
	public static int hint_headicons_groupid;
	public static int hint_mapmarkers_groupid;
	public static int mapflag_groupid;
	public static int mapscene_groupid;
	public static int cross_groupid;
	public static int mapdots_groupid;
	public static int scrollbar_groupid;
	public static int mod_icons_groupid;
	public static int floorshadows_groupid;
	public static int compass_groupid;
	public static int hint_mapedge_groupid;
	public static SoftwareSprite[] mapfunction_raw;
	public static Sprite[] hitmarks;
	public static Sprite[] hitbar_default;
	public static Sprite[] headicons_pk;
	public static Sprite[] hint_headicons;
	public static Sprite[] headicons_prayer;
	public static Sprite[] hint_mapmarkers;
	public static Sprite mapflag;
	public static PaletteSprite[] mapscene;
	public static Sprite[] cross;
	public static Sprite[] mapdots;
	public static PaletteSprite[] scrollbar;
	public static PaletteSprite[] mod_icons;
	public static Sprite compass;
	public static Sprite[] hint_mapedge;
	public static Sprite[] mapfunction;

	public static void fetch_ids(Js5 sprites_js5) {
		p11_full_groupid = sprites_js5.get_groupid("p11_full");
		p12_full_groupid = sprites_js5.get_groupid("p12_full");
		b12_full_groupid = sprites_js5.get_groupid("b12_full");
		mapfunction_groupid = sprites_js5.get_groupid("mapfunction");
		hitmarks_groupid = sprites_js5.get_groupid("hitmarks");
		hitbar_default_groupid = sprites_js5.get_groupid("hitbar_default");
		headicons_pk_groupid = sprites_js5.get_groupid("headicons_pk");
		haedicons_prayer_groupid = sprites_js5.get_groupid("headicons_prayer");
		hint_headicons_groupid = sprites_js5.get_groupid("hint_headicons");
		hint_mapmarkers_groupid = sprites_js5.get_groupid("hint_mapmarkers");
		mapflag_groupid = sprites_js5.get_groupid("mapflag");
		mapscene_groupid = sprites_js5.get_groupid("mapscene");
		cross_groupid = sprites_js5.get_groupid("cross");
		mapdots_groupid = sprites_js5.get_groupid("mapdots");
		scrollbar_groupid = sprites_js5.get_groupid("scrollbar");
		mod_icons_groupid = 1455;// sprites_js5.get_groupid("mod_icons");
		floorshadows_groupid = sprites_js5.get_groupid("floorshadows");
		compass_groupid = sprites_js5.get_groupid("compass");
		hint_mapedge_groupid = sprites_js5.get_groupid("hint_mapedge");
	}

	public static void fetch_sprites(Js5 sprites_js5) {
		mapfunction_raw = Sprite.load_software_all(sprites_js5, mapfunction_groupid, 0);
		hitmarks = Sprite.load_alpha_all(sprites_js5, hitmarks_groupid, 0);
		hitbar_default = Sprite.load_software_alpha_all(sprites_js5, hitbar_default_groupid, 0);
		headicons_pk = Sprite.load_software_alpha_all(sprites_js5, headicons_pk_groupid, 0);
		headicons_prayer = Sprite.load_software_alpha_all(sprites_js5, haedicons_prayer_groupid, 0);
		hint_headicons = Sprite.load_software_alpha_all(sprites_js5, hint_headicons_groupid, 0);
		hint_mapmarkers = Sprite.load_software_alpha_all(sprites_js5, hint_mapmarkers_groupid, 0);
		mapflag = Sprite.load(sprites_js5, mapflag_groupid, 0);
		mapscene = PaletteSprite.load_all(sprites_js5, mapscene_groupid, 0);
		cross = Sprite.load_all(sprites_js5, cross_groupid, 0);
		mapdots = Sprite.load_all(sprites_js5, mapdots_groupid, 0);
		scrollbar = PaletteSprite.load_all(sprites_js5, scrollbar_groupid, 0);
		mod_icons = PaletteSprite.load_all(sprites_js5, mod_icons_groupid, 0);
		FontCache.p11_full.set_glyphs(mod_icons, null);
		FontCache.p12_full.set_glyphs(mod_icons, null);
		FontCache.b12_full.set_glyphs(mod_icons, null);
		if (GLManager.opengl_mode) {
			SceneShadowMapper.floorshadows = PaletteSprite.load_software_all(sprites_js5, floorshadows_groupid, 0);
			for (int var2 = 0; ~SceneShadowMapper.floorshadows.length < ~var2; ++var2) {
				SceneShadowMapper.floorshadows[var2].trim_size();
			}
		}
		SoftwareSprite compass_sprite_raw = Sprite.load_software(sprites_js5, compass_groupid, 0);
		compass_sprite_raw.apply_padding();
		if (GLManager.opengl_mode) {
			compass = new OpenGLSprite(compass_sprite_raw);
		} else {
			compass = compass_sprite_raw;
		}
		SoftwareSprite[] software_hint_mapedge = Sprite.load_software_all(sprites_js5, hint_mapedge_groupid, 0);
		for (int var4 = 0; ~var4 > ~software_hint_mapedge.length; ++var4) {
			software_hint_mapedge[var4].apply_padding();
		}
		if (!GLManager.opengl_mode) {
			hint_mapedge = software_hint_mapedge;
		} else {
			hint_mapedge = new Sprite[software_hint_mapedge.length];
			for (int var4 = 0; var4 < software_hint_mapedge.length; ++var4) {
				hint_mapedge[var4] = new OpenGLSprite(software_hint_mapedge[var4]);
			}
		}
		int var5 = (int) (21 * Math.random()) - 10;
		int var4 = (int) (21.0D * Math.random()) - 10;
		int var6 = -10 + (int) (21.0D * Math.random());
		int var7 = -20 + (int) (Math.random() * 41.0D);
		for (SoftwareSprite element : mapfunction_raw) {
			element.tint(var4 + var7, var7 + var5, var7 + var6);
		}
		if (!GLManager.opengl_mode) {
			mapfunction = mapfunction_raw;
		} else {
			mapfunction = new Sprite[mapfunction_raw.length];
			for (int var8 = 0; var8 < mapfunction_raw.length; ++var8) {
				mapfunction[var8] = new OpenGLSprite(mapfunction_raw[var8]);
			}
		}
	}

	public static int get_completed_sprites(Js5 sprites_js5) {
		int num_completed = 0;
		if (sprites_js5.is_group_cached(mapfunction_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(hitmarks_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(hitbar_default_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(headicons_pk_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(haedicons_prayer_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(hint_headicons_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(hint_mapmarkers_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(mapflag_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(mapscene_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(cross_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(mapdots_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(scrollbar_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(mod_icons_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(floorshadows_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(compass_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(hint_mapedge_groupid)) {
			num_completed++;
		}
		return num_completed;
	}

	public static int get_total_sprites() {
		return 16;
	}

	public static int get_completed_fonts(Js5 sprites_js5, Js5 fonts_js5) {
		int num_completed = 0;
		if (sprites_js5.is_group_cached(p11_full_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(p12_full_groupid)) {
			num_completed++;
		}
		if (sprites_js5.is_group_cached(b12_full_groupid)) {
			num_completed++;
		}
		if (fonts_js5.is_group_cached(p11_full_groupid)) {
			num_completed++;
		}
		if (fonts_js5.is_group_cached(p12_full_groupid)) {
			num_completed++;
		}
		if (fonts_js5.is_group_cached(b12_full_groupid)) {
			num_completed++;
		}
		return num_completed;
	}

	public static int get_total_fonts() {
		return 6;
	}

	public StaticMedia() {
		// NOOP
	}
}
