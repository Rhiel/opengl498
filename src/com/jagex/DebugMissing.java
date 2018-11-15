package com.jagex;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Walied K. Yassen
 */
public final class DebugMissing {

	public static final boolean ENABLED = true;

	public static final Set<Integer> missing_sprites = newSet();
	public static final Set<Integer> missing_scripts = newSet();
	public static final Set<Integer> missing_fonts = newSet();
	public static final Set<Integer> missing_structs = newSet();
	public static final Set<Integer> missing_params = newSet();
	public static final Set<Integer> missing_enums = newSet();
	public static final Set<Integer> missing_materials = newSet();
	public static final Set<Integer> missing_seqs = newSet();
	public static final Set<Integer> missing_underlays = newSet();
	public static final Set<Integer> missing_overlays = newSet();

	public static void notify_sprite(int spriteid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_sprites.contains(spriteid)) {
			warn("sprite", spriteid);
			missing_sprites.add(spriteid);
		}
	}

	public static void notify_script(int scriptid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_scripts.contains(scriptid)) {
			warn("script", scriptid);
			missing_scripts.add(scriptid);
		}
	}

	public static void notify_font(int fontid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_fonts.contains(fontid)) {
			warn("font", fontid);
			missing_fonts.add(fontid);
		}
	}

	public static void notify_struct(int structid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_structs.contains(structid)) {
			warn("struct", structid);
			missing_structs.add(structid);
		}
	}

	public static void notify_param(int paramid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_params.contains(paramid)) {
			warn("param", paramid);
			missing_params.add(paramid);
		}
	}

	public static void notify_enum(int enumid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_enums.contains(enumid)) {
			warn("enum", enumid);
			missing_enums.add(enumid);
		}
	}

	public static void notify_material(int materialid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_materials.contains(materialid)) {
			warn("material", materialid);
			missing_materials.add(materialid);
		}
	}

	public static void notify_sequence(int seqid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_seqs.contains(seqid)) {
			warn("sequence", seqid);
			missing_seqs.add(seqid);
		}
	}

	public static void notify_underlay(int fluid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_overlays.contains(fluid)) {
			warn("underlay", fluid);
			missing_overlays.add(fluid);
		}
	}

	public static void notify_overlay(int floid) {
		if (!ENABLED) {
			return;
		}
		if (!missing_overlays.contains(floid)) {
			warn("overlay", floid);
			missing_overlays.add(floid);
		}
	}

	private static void warn(String kind, int id) {
		System.out.println("WARNING: Missing " + kind + " id: " + id);
	}

	private static HashSet<Integer> newSet() {
		return ENABLED ? new HashSet<Integer>() : null;
	}

}
