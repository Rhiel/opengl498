package com.jagex;

import com.jagex.graphics.runetek4.media.Sprite;

/**
 * @author Walied K. Yassen
 */
public final class SpriteLoader {

	public static int num_sprites;
	public static int trimmed_width;
	public static int trimmed_height;
	public static int[] palette;
	public static int[] sprites_offsetx;
	public static int[] sprites_offsety;
	public static int[] sprites_width;
	public static int[] sprites_height;
	public static byte[][] sprites_pixels;
	public static byte[][] sprites_alphas;
	public static boolean[] sprites_translucent;

	public static final void load_sprites(byte[] file) {
		Packet buffer = new Packet(file);
		buffer.index = file.length - 2;
		num_sprites = buffer.g2();
		sprites_offsetx = new int[num_sprites];
		sprites_offsety = new int[num_sprites];
		sprites_height = new int[num_sprites];
		sprites_pixels = new byte[num_sprites][];
		sprites_alphas = new byte[num_sprites][];
		sprites_translucent = new boolean[num_sprites];
		sprites_width = new int[num_sprites];
		buffer.index = file.length - 7 - num_sprites * 8;
		trimmed_width = buffer.g2();
		trimmed_height = buffer.g2();
		int palleteSize = (buffer.g1() & 0xff) + 1;
		for (int i_4_ = 0; i_4_ < num_sprites; i_4_++) {
			sprites_offsetx[i_4_] = buffer.g2();
		}
		for (int i_5_ = 0; i_5_ < num_sprites; i_5_++) {
			sprites_offsety[i_5_] = buffer.g2();
		}
		for (int i_6_ = 0; i_6_ < num_sprites; i_6_++) {
			sprites_width[i_6_] = buffer.g2();
		}
		for (int i_7_ = 0; num_sprites > i_7_; i_7_++) {
			sprites_height[i_7_] = buffer.g2();
		}
		buffer.index = -(num_sprites * 8) + file.length + -7 - (palleteSize - 1) * 3;
		palette = new int[palleteSize];
		for (int palleteIndex = 1; palleteIndex < palleteSize; palleteIndex++) {
			palette[palleteIndex] = buffer.g3();
			if (palette[palleteIndex] == 0) {
				palette[palleteIndex] = 1;
			}
		}
		buffer.index = 0;
		for (int spriteIndex = 0; spriteIndex < num_sprites; spriteIndex++) {
			int i_10_ = sprites_width[spriteIndex];
			int i_11_ = sprites_height[spriteIndex];
			int i_56_ = i_11_ * i_10_;
			byte[] pixelInd = new byte[i_56_];
			sprites_pixels[spriteIndex] = pixelInd;
			int maskData = buffer.g1();
			if ((maskData & 0x2) == 0) {
				if ((maskData & 0x1) == 1) {
					for (int i_15_ = 0; i_15_ < i_10_; i_15_++) {
						for (int i_16_ = 0; i_16_ < i_11_; i_16_++) {
							pixelInd[i_15_ + i_10_ * i_16_] = buffer.g1s();
						}
					}
				} else {
					for (int i_17_ = 0; i_17_ < i_56_; i_17_++) {
						pixelInd[i_17_] = buffer.g1s();
					}
				}
			} else {
				boolean have_alpha = false;
				byte[] alphas = new byte[i_56_];
				sprites_alphas[spriteIndex] = alphas;
				if ((maskData & 0x1) == 0) {
					for (int _pxl_ptr = 0; _pxl_ptr < i_56_; _pxl_ptr++) {
						pixelInd[_pxl_ptr] = buffer.g1s();
					}
					for (int _pxl_ptr = 0; _pxl_ptr < i_56_; _pxl_ptr++) {
						byte alpha = alphas[_pxl_ptr] = buffer.g1s();
						have_alpha = have_alpha | alpha != -1;
					}
				} else {
					for (int _x = 0; _x < i_10_; _x++) {
						for (int _y = 0; _y < i_11_; _y++) {
							pixelInd[_x + _y * i_10_] = buffer.g1s();
						}
					}
					for (int _x = 0; _x < i_10_; _x++) {
						for (int _y = 0; _y < i_11_; _y++) {
							byte alpha = alphas[_x + _y * i_10_] = buffer.g1s();
							have_alpha |= alpha != -1;
						}
					}
				}
				sprites_translucent[spriteIndex] = have_alpha;
			}
		}

	}

	public static final void reset() {
		palette = null;
		sprites_offsetx = null;
		sprites_offsety = null;
		sprites_width = null;
		sprites_height = null;
		sprites_pixels = null;
	}

	public SpriteLoader() {
		// NOOP
	}

	public static boolean cache_sprites(Js5 js5, int archiveId, int fileId) {
		byte[] data = js5.get_file(archiveId, fileId);
		if (data == null) {
			return false;
		}
		load_sprites(data);
		return true;
	}

	public static boolean cache_sprite(Js5 js5, int groupId) {
		byte[] data = js5.get_file(groupId);
		if (data == null) {
			return false;
		}
		load_sprites(data);
		return true;
	}

	public static final Sprite create(Js5 js5, int groupId) {
		if (!cache_sprite(js5, groupId)) {
			return null;
		}
		return Sprite.create_alpha();
	}

}
