package com.jagex;

import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class ComponentMinimap {
	public static final int[][] anIntArrayArray3215 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };
	public static final int[][] anIntArrayArray2039 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };
	private static final boolean HD_MINIMAP = true;

	public static SoftwareSprite cached_sprite;
	public static Sprite full_sprite;
	public static int flag_x;
	public static int flag_y;
	public static int last_rendered_level = -1;
	public static int[] elements_ids = new int[1000];
	public static int[] elements_x = new int[1000];
	public static int[] elements_y = new int[1000];
	public static int elements_count;

	public static void draw_minimap_tile_ground_old(int[] var0, int offset, int size, int level, int x, int y) {
		Ground var6 = Scene.current_grounds[level][x][y];
		if (var6 != null) {
			PlainTile var7 = var6.plain_tile;
			int var9;
			if (var7 != null) {
				int var17 = var7.anInt1673;
				if (var17 != 0) {
					for (var9 = 0; var9 < 4; ++var9) {
						var0[offset] = var17;
						var0[offset + 1] = var17;
						var0[offset + 2] = var17;
						var0[offset + 3] = var17;
						offset += size;
					}

				}
			} else {
				ShapedTile var8 = var6.shaped_tile;
				if (var8 != null) {
					var9 = var8.anInt611;
					int var10 = var8.anInt612;
					int var11 = var8.anInt626;
					int var12 = var8.anInt621;
					int[] var13 = anIntArrayArray3215[var9];
					int[] var14 = anIntArrayArray2039[var10];
					int var15 = 0;
					int var16;
					if (var11 != 0) {
						for (var16 = 0; var16 < 4; ++var16) {
							var0[offset] = var13[var14[var15++]] == 0 ? var11 : var12;
							var0[offset + 1] = var13[var14[var15++]] == 0 ? var11 : var12;
							var0[offset + 2] = var13[var14[var15++]] == 0 ? var11 : var12;
							var0[offset + 3] = var13[var14[var15++]] == 0 ? var11 : var12;
							offset += size;
						}
					} else {
						for (var16 = 0; var16 < 4; ++var16) {
							if (var13[var14[var15++]] != 0) {
								var0[offset] = var12;
							}

							if (var13[var14[var15++]] != 0) {
								var0[offset + 1] = var12;
							}

							if (var13[var14[var15++]] != 0) {
								var0[offset + 2] = var12;
							}

							if (var13[var14[var15++]] != 0) {
								var0[offset + 3] = var12;
							}

							offset += size;
						}
					}

				}
			}
		}
	}

	public static void draw_minimap_tile_ground(int[] ai, int offset, int size, int level, int x, int y) {
		Ground ground = Scene.current_grounds[level][x][y];
		if (ground == null) {
			return;
		}
		PlainTile class43 = ground.plain_tile;
		if (class43 != null) {
			if (HD_MINIMAP && class43.color1 != 12345678) {
				if (class43.anInt1673 != 0) {
					int hs = class43.color1 & ~0x7f;
					int l1 = class43.color4 & 0x7f;
					int l2 = class43.color3 & 0x7f;
					int l3 = (class43.color1 & 0x7f) - l1;
					int l4 = (class43.color2 & 0x7f) - l2;
					l1 <<= 2;
					l2 <<= 2;
					for (int k1 = 0; k1 < 4; k1++) {
						if (class43.textureid < 0) {
							ai[offset] = ColourUtil.hslToRgbTable[hs | l1 >> 2];
							ai[offset + 1] = ColourUtil.hslToRgbTable[hs | l1 * 3 + l2 >> 4];
							ai[offset + 2] = ColourUtil.hslToRgbTable[hs | l1 + l2 >> 3];
							ai[offset + 3] = ColourUtil.hslToRgbTable[hs | l1 + l2 * 3 >> 4];
						} else {
							int j1 = class43.anInt1673;
							int lig = 0xff - ((l1 >> 1) * (l1 >> 1) >> 8);
							ai[offset] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							lig = 0xff - ((l1 * 3 + l2 >> 3) * (l1 * 3 + l2 >> 3) >> 8);
							ai[offset + 1] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							lig = 0xff - ((l1 + l2 >> 2) * (l1 + l2 >> 2) >> 8);
							ai[offset + 2] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							lig = 0xff - ((l1 + l2 * 3 >> 3) * (l1 + l2 * 3 >> 3) >> 8);
							ai[offset + 3] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
						}
						l1 += l3;
						l2 += l4;
						offset += size;
					}
				}
			} else {
				int j1 = class43.anInt1673;
				if (j1 != 0) {
					for (int k1 = 0; k1 < 4; k1++) {
						ai[offset] = j1;
						ai[offset + 1] = j1;
						ai[offset + 2] = j1;
						ai[offset + 3] = j1;
						offset += size;
					}
				}
			}
		} else {
			ShapedTile class40 = ground.shaped_tile;
			if (class40 != null) {
				int l1 = class40.anInt611;
				int i2 = class40.anInt612;
				int j2 = class40.anInt626;
				int k2 = class40.anInt621;
				int ai1[] = anIntArrayArray3215[l1];
				int ai2[] = anIntArrayArray2039[i2];
				int l2 = 0;
				if (HD_MINIMAP && class40.color62 != 12345678) {
					int hs1 = class40.color62 & ~0x7f;
					int l11 = class40.color92 & 0x7f;
					int l21 = class40.color82 & 0x7f;
					int l31 = (class40.color62 & 0x7f) - l11;
					int l41 = (class40.color72 & 0x7f) - l21;
					l11 <<= 2;
					l21 <<= 2;
					for (int k1 = 0; k1 < 4; k1++) {
						if (class40.anIntArray616 == null) {
							if (ai1[ai2[l2++]] != 0) {
								ai[offset] = ColourUtil.hslToRgbTable[hs1 | l11 >> 2];
							}
							if (ai1[ai2[l2++]] != 0) {
								ai[offset + 1] = ColourUtil.hslToRgbTable[hs1 | l11 * 3 + l21 >> 4];
							}
							if (ai1[ai2[l2++]] != 0) {
								ai[offset + 2] = ColourUtil.hslToRgbTable[hs1 | l11 + l21 >> 3];
							}
							if (ai1[ai2[l2++]] != 0) {
								ai[offset + 3] = ColourUtil.hslToRgbTable[hs1 | l11 + l21 * 3 >> 4];
							}
						} else {
							int j1 = k2;
							if (ai1[ai2[l2++]] != 0) {
								int lig = 0xff - ((l11 >> 1) * (l11 >> 1) >> 8);
								ai[offset] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							}
							if (ai1[ai2[l2++]] != 0) {
								int lig = 0xff - ((l11 * 3 + l21 >> 3) * (l11 * 3 + l21 >> 3) >> 8);
								ai[offset + 1] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							}
							if (ai1[ai2[l2++]] != 0) {
								int lig = 0xff - ((l11 + l21 >> 2) * (l11 + l21 >> 2) >> 8);
								ai[offset + 2] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							}
							if (ai1[ai2[l2++]] != 0) {
								int lig = 0xff - ((l11 + l21 * 3 >> 3) * (l11 + l21 * 3 >> 3) >> 8);
								ai[offset + 3] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							}
						}
						l11 += l31;
						l21 += l41;
						offset += size;
					}
					if (j2 != 0 && class40.color61 != 12345678) {
						offset -= size << 2;
						l2 -= 16;
						hs1 = class40.color61 & ~0x7f;
						l11 = class40.color91 & 0x7f;
						l21 = class40.color81 & 0x7f;
						l31 = (class40.color61 & 0x7f) - l11;
						l41 = (class40.color71 & 0x7f) - l21;
						l11 <<= 2;
						l21 <<= 2;
						for (int k1 = 0; k1 < 4; k1++) {
							if (ai1[ai2[l2++]] == 0) {
								ai[offset] = ColourUtil.hslToRgbTable[hs1 | l11 >> 2];
							}
							if (ai1[ai2[l2++]] == 0) {
								ai[offset + 1] = ColourUtil.hslToRgbTable[hs1 | l11 * 3 + l21 >> 4];
							}
							if (ai1[ai2[l2++]] == 0) {
								ai[offset + 2] = ColourUtil.hslToRgbTable[hs1 | l11 + l21 >> 3];
							}
							if (ai1[ai2[l2++]] == 0) {
								ai[offset + 3] = ColourUtil.hslToRgbTable[hs1 | l11 + l21 * 3 >> 4];
							}
							l11 += l31;
							l21 += l41;
							offset += size;
						}
					}
				} else {
					if (j2 != 0) {
						for (int i3 = 0; i3 < 4; i3++) {
							ai[offset] = ai1[ai2[l2++]] != 0 ? k2 : j2;
							ai[offset + 1] = ai1[ai2[l2++]] != 0 ? k2 : j2;
							ai[offset + 2] = ai1[ai2[l2++]] != 0 ? k2 : j2;
							ai[offset + 3] = ai1[ai2[l2++]] != 0 ? k2 : j2;
							offset += size;
						}
					} else {
						for (int j3 = 0; j3 < 4; j3++) {
							if (ai1[ai2[l2++]] != 0) {
								ai[offset] = k2;
							}
							if (ai1[ai2[l2++]] != 0) {
								ai[offset + 1] = k2;
							}
							if (ai1[ai2[l2++]] != 0) {
								ai[offset + 2] = k2;
							}
							if (ai1[ai2[l2++]] != 0) {
								ai[offset + 3] = k2;
							}
							offset += size;
						}
					}
				}
			}
		}
	}

	public static boolean renderMapScene(int level, byte b) {
		if (cached_sprite == null) {
			if (ComponentMinimap.full_sprite != null && !GLManager.opengl_mode) {
				cached_sprite = (SoftwareSprite) ComponentMinimap.full_sprite;
			} else {
				cached_sprite = new SoftwareSprite(512, 512);
			}
			int[] is = cached_sprite.pixels;
			int i_5_ = is.length;
			for (int i_6_ = 0; i_6_ < i_5_; i_6_++) {
				is[i_6_] = 1;
			}
			for (int i_7_ = 1; i_7_ < 103; i_7_++) {
				int i_8_ = 4 * (-(i_7_ * 512) + 52736) + 24628;
				for (int i_9_ = 1; i_9_ < 103; i_9_++) {
					if ((MapLoader.settings[level][i_9_][i_7_] & 0x18) == 0) {
						ComponentMinimap.draw_minimap_tile_ground(is, i_8_, 512, level, i_9_, i_7_);
					}
					if (level < 3 && (0x8 & MapLoader.settings[1 + level][i_9_][i_7_] ^ 0xffffffff) != -1) {
						ComponentMinimap.draw_minimap_tile_ground(is, i_8_, 512, level + 1, i_9_, i_7_);
					}
					i_8_ += 4;
				}
			}
			ComponentMinimap.elements_count = 0;
			for (int i_14_ = 0; i_14_ < 104; i_14_++) {
				for (int i_15_ = 0; i_15_ < 104; i_15_++) {
					long l = GroundDecoration.getGroundDecorationUid(ObjType.localHeight, i_14_, i_15_);
					if (l != 0L) {
						LocType type = LocTypeList.list(0x7fffffff & (int) (l >>> 32));
						int spriteid = type.map_categoryid;
						if (null != type.morphisms) {
							for (int var12 = 0; ~type.morphisms.length < ~var12; ++var12) {
								if (-1 != type.morphisms[var12]) {
									LocType var13 = LocTypeList.list(type.morphisms[var12]);
									if (0 <= var13.map_categoryid) {
										spriteid = var13.map_categoryid;
										break;
									}
								}
							}
						}
						if ((spriteid ^ 0xffffffff) <= -1) {
							int i_17_ = i_15_;
							int i_18_ = i_14_;
							if (spriteid != 22 && spriteid != 29 && spriteid != 34 && spriteid != 36 && spriteid != 46 && spriteid != 47 && spriteid != 48) {
								int[][] is_19_ = MapLoader.collision_maps[ObjType.localHeight].clippingFlags;
								for (int i_20_ = 0; i_20_ < 10; i_20_++) {
									int i_21_ = (int) (4.0 * Math.random());
									if (i_21_ == 0 && (i_18_ ^ 0xffffffff) < -1 && (-3 + i_14_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff) && (is_19_[-1 + i_18_][i_17_] & 0x12c0108 ^ 0xffffffff) == -1) {
										i_18_--;
									}
									if (i_21_ == 1 && i_18_ < 103 && i_14_ + 3 > i_18_ && (is_19_[i_18_ + 1][i_17_] & 0x12c0180) == 0) {
										i_18_++;
									}
									if (i_21_ == 2 && (i_17_ ^ 0xffffffff) < -1 && (-3 + i_15_ ^ 0xffffffff) > (i_17_ ^ 0xffffffff) && (0x12c0102 & is_19_[i_18_][-1 + i_17_]) == 0) {
										i_17_--;
									}
									if (i_21_ == 3 && i_17_ < 103 && i_15_ + 3 > i_17_ && (0x12c0120 & is_19_[i_18_][1 + i_17_] ^ 0xffffffff) == -1) {
										i_17_++;
									}
								}
							}
							ComponentMinimap.elements_ids[ComponentMinimap.elements_count] = type.id;
							ComponentMinimap.elements_x[ComponentMinimap.elements_count] = i_18_;
							ComponentMinimap.elements_y[ComponentMinimap.elements_count] = i_17_;
							ComponentMinimap.elements_count++;
						}
					}
				}
			}
		}
		cached_sprite.bind();
		int i_10_ = (238 - -(int) (Math.random() * 20.0) - 10 << 16) - -(238 - (-(int) (Math.random() * 20.0) + 10) << 8) - (10 + -238 - (int) (20.0 * Math.random()));
		int i_11_ = -10 + 238 - -(int) (Math.random() * 20.0) << 16;
		for (int i_12_ = 1; i_12_ < 103; i_12_++) {
			for (int i_13_ = 1; i_13_ < 103; i_13_++) {
				if ((0x18 & MapLoader.settings[level][i_13_][i_12_]) == 0) {
					if (!ComponentMinimap.drawMapScenes(i_13_, i_11_, i_10_, i_12_, level)) {
						if (GLManager.opengl_mode) {
							Rasterizer2D.colour_buffer = null;
						} else {
							GameClient.software_frame_buffer.bind();
						}
						return false;
					}
				}
				if (level < 3 && (MapLoader.settings[1 + level][i_13_][i_12_] & 0x8 ^ 0xffffffff) != -1) {
					if (!ComponentMinimap.drawMapScenes(i_13_, i_11_, i_10_, i_12_, 1 + level)) {
						if (GLManager.opengl_mode) {
							Rasterizer2D.colour_buffer = null;
						} else {
							GameClient.software_frame_buffer.bind();
						}
						return false;
					}

				}
			}
		}
		if (GLManager.opengl_mode) {
			int[] var19 = cached_sprite.pixels;
			int var7 = var19.length;
			for (int var8 = 0; var7 > var8; ++var8) {
				if (var19[var8] == 0) {
					var19[var8] = 1;
				}
			}
			full_sprite = new OpenGLSprite(cached_sprite);
		} else {
			full_sprite = cached_sprite;
		}
		if (!GLManager.opengl_mode) {
			GameClient.software_frame_buffer.bind();
		} else {
			Rasterizer2D.colour_buffer = null;
		}
		cached_sprite = null;
		return true;
	}

	static boolean drawMapScenes(int x, int i_0_, int i_1_, int y, int var6) {
		long var8 = WallObject.getWallObjectUid(var6, x, y);
		if (var8 != 0L) {
			int var10 = ((int) var8 & 0x32a695) >> 20;
			int var11 = 0x1f & (int) var8 >> 14;
			int i_7_ = i_1_;
			if (var8 > 0L) {
				i_7_ = i_0_;
			}
			int[] is = Rasterizer2D.colour_buffer;
			int var12 = 0x7fffffff & (int) (var8 >>> 32);
			int i_9_ = 4 * (-y + 103) * 512 + 24624 - -(4 * x);
			LocType var13 = LocTypeList.list(var12);
			if (var13.map_icon_id != -1) {
				if (!ComponentMinimap.draw_loc_icon(var13, x, y, var10)) {
					return false;
				}
			} else {
				if ((var11 ^ 0xffffffff) == -1 || var11 == 2) {
					if (var10 != 0) {
						if (var10 != 1) {
							if (var10 == 2) {
								is[3 + i_9_] = i_7_;
								is[512 + 3 + i_9_] = i_7_;
								is[1024 + 3 + i_9_] = i_7_;
								is[1536 + 3 + i_9_] = i_7_;
							} else if (var10 == 3) {
								is[i_9_ + 1536] = i_7_;
								is[1 + i_9_ + 1536] = i_7_;
								is[2 + 1536 + i_9_] = i_7_;
								is[i_9_ - -1536 - -3] = i_7_;
							}
						} else {
							is[i_9_] = i_7_;
							is[i_9_ - -1] = i_7_;
							is[2 + i_9_] = i_7_;
							is[3 + i_9_] = i_7_;
						}
					} else {
						is[i_9_] = i_7_;
						is[512 + i_9_] = i_7_;
						is[1024 + i_9_] = i_7_;
						is[1536 + i_9_] = i_7_;
					}
				}
				if (var11 == 3) {
					if (var10 != 0) {
						if (var10 != 1) {
							if (var10 != 2) {
								if (var10 == 3) {
									is[i_9_ - -1536] = i_7_;
								}
							} else {
								is[i_9_ - -1539] = i_7_;
							}
						} else {
							is[i_9_ - -3] = i_7_;
						}
					} else {
						is[i_9_] = i_7_;
					}
				}
				if (var11 == 2) {
					if (var10 != 3) {
						if (var10 == 0) {
							is[i_9_] = i_7_;
							is[i_9_ + 1] = i_7_;
							is[2 + i_9_] = i_7_;
							is[3 + i_9_] = i_7_;
						} else if (var10 == 1) {
							is[i_9_ - -3] = i_7_;
							is[512 + i_9_ - -3] = i_7_;
							is[1024 + 3 + i_9_] = i_7_;
							is[1536 + 3 + i_9_] = i_7_;
						} else if (var10 == 2) {
							is[i_9_ - -1536] = i_7_;
							is[1 + 1536 + i_9_] = i_7_;
							is[2 + 1536 + i_9_] = i_7_;
							is[3 + 1536 + i_9_] = i_7_;
						}
					} else {
						is[i_9_] = i_7_;
						is[512 + i_9_] = i_7_;
						is[i_9_ - -1024] = i_7_;
						is[1536 + i_9_] = i_7_;
					}
				}
			}
		}
		var8 = Scene.getInteractiveUid(var6, x, y);
		if (var8 != 0L) {
			int var10 = (int) var8 >> 20 & 0x3;
			int i_13_ = (int) (var8 >>> 32) & 0x7fffffff;
			LocType var13 = LocTypeList.list(i_13_);
			int i_14_ = (int) var8 >> 14 & 0x1f;
			if (var13.map_icon_id != -1) {
				if (!ComponentMinimap.draw_loc_icon(var13, x, y, var10)) {
					return false;
				}
			} else if (i_14_ == 9) {
				int i_17_ = 15658734;
				int[] is = Rasterizer2D.colour_buffer;
				int i_18_ = 24624 + 4 * x - -(4 * (52736 - y * 512));
				if ((var8 ^ 0xffffffffffffffffL) < -1L) {
					i_17_ = 15597568;
				}
				if (var10 == 0 || var10 == 2) {
					is[1536 + i_18_] = i_17_;
					is[1025 + i_18_] = i_17_;
					is[512 + i_18_ - -2] = i_17_;
					is[3 + i_18_] = i_17_;
				} else {
					is[i_18_] = i_17_;
					is[512 + i_18_ - -1] = i_17_;
					is[2 + i_18_ + 1024] = i_17_;
					is[i_18_ - -1539] = i_17_;
				}
			}
		}
		var8 = GroundDecoration.getGroundDecorationUid(var6, x, y);
		if (var8 != 0L) {
			int var11 = 0x7fffffff & (int) (var8 >>> 32);
			int var10 = (int) var8 >> 20 & 3;
			LocType var18 = LocTypeList.list(var11);
			if (var18.map_icon_id != -1) {
				if (!ComponentMinimap.draw_loc_icon(var18, x, y, var10)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean draw_loc_icon(LocType loc, int draw_x, int draw_y, int rotation) {
		MSIType msi = MSITypeList.list(loc.map_icon_id);
		if (msi.graphic == -1) {
			return true;
		} else {
			if (loc.map_icon_rotates) {
				rotation += loc.map_icon_rotation;
				rotation &= 3;
			} else {
				rotation = 0;
			}
			SoftwarePaletteSprite sprite = msi.get_sprite(rotation);
			if (sprite == null) {
				return false;
			} else {
				int size_x = loc.size2d;
				int size_y = loc.size3d;
				if ((rotation & 0x1) == 1) {
					size_x = loc.size3d;
					size_y = loc.size2d;
				}
				int width = sprite.trim_width;
				int height = sprite.trim_height;
				if (msi.enlarge) {
					width = size_x * 4;
					height = size_y * 4;
				}
				if (~msi.colour == -1) {
					sprite.draw(48 + draw_x * 4, 48 + (-size_y + -draw_y + 104) * 4, width, height);
				} else {
					sprite.draw(48 + draw_x * 4, 48 + (-size_y + -draw_y + 104) * 4, width, height, ~0xffffff | msi.colour);
				}
				return true;
			}
		}
	}

	static final void method956(RSInterface class64, byte b, int i, int i_0_, int i_1_, int i_2_, int i_3_) {
		int i_4_ = i_0_ * i_0_ + i_1_ * i_1_;
		if (i_4_ <= 360000) {
			int i_5_ = Math.min(class64.layout_width / 2, class64.layout_height / 2);
			if (b == -100) {
				if (i_5_ * i_5_ >= i_4_) {
					ComponentMinimap.draw_minimap_icon(class64, StaticMethods2.hint_mapmarkers[i_3_], i_2_, i, i_1_, i_0_);
				} else {
					i_5_ -= 10;
					int i_6_ = 0x7ff & StaticMethods.anInt3162 + Class35.cameraDirection;
					int i_7_ = Rasterizer.SINE[i_6_];
					i_7_ = i_7_ * 256 / (256 + Player.anInt4410);
					int i_8_ = Rasterizer.COSINE[i_6_];
					i_8_ = 256 * i_8_ / (Player.anInt4410 - -256);
					int i_9_ = -(i_7_ * i_1_) + i_0_ * i_8_ >> 16;
					int i_10_ = i_0_ * i_7_ + i_1_ * i_8_ >> 16;
					double d = Math.atan2(i_10_, i_9_);
					int i_11_ = (int) (i_5_ * Math.sin(d));
					int i_12_ = (int) (i_5_ * Math.cos(d));
					((SoftwareSprite) StaticMedia.hint_mapedge[i_3_]).draw_rotated(class64.layout_width / 2 + i_2_ - (-i_11_ - -10), -i_12_ + i - -(class64.layout_height / 2) - 10, 20, 20, 15, 15, d, 256);
				}
			}
		}
	}

	public static void draw_minimap_icon(RSInterface widget, Sprite icon, int draw_x, int draw_y, int offset_x, int offset_y) {
		if (icon != null) {
			int i_5_ = 0x7ff & Class35.cameraDirection + StaticMethods.anInt3162;
			int i_6_ = 10 + Math.max(widget.layout_width / 2, widget.layout_height / 2);
			int i_7_ = offset_x * offset_x + offset_y * offset_y;
			if (i_7_ <= i_6_ * i_6_) {
				int sin = Rasterizer.SINE[i_5_];
				int cos = Rasterizer.COSINE[i_5_];
				sin = sin * 256 / (256 + Player.anInt4410);
				cos = 256 * cos / (Player.anInt4410 - -256);
				int var12 = offset_x * cos + sin * offset_y >> 16;
				int var13 = cos * offset_y - sin * offset_x >> 16;
				if (GLManager.opengl_mode) {
					((OpenGLSprite) icon).method645(widget.layout_width / 2 + draw_x + var12 - icon.trimmed_width / 2, widget.layout_height / 2 + draw_y - (var13 + icon.trimmed_height / 2), (OpenGLSprite) widget.get_sprite(false));
				} else {
					((SoftwareSprite) icon).draw_offseted(draw_x + widget.layout_width / 2 + var12 + -(icon.trimmed_width / 2), -(icon.trimmed_height / 2) + draw_y - -(widget.layout_height / 2) + -var13, widget.anIntArray1064, widget.anIntArray1109);
				}
			}
		}
	}

	public static void method3139(RSInterface component, int i, int i_7_, int i_8_, int i_9_, int i_10_, RSString text, Font renderFont, Font rSFontMetrics, int i_11_) {
		int i_13_ = 0x7ff & Class35.cameraDirection + StaticMethods.anInt3162;
		int i_14_ = Math.max(component.layout_width / 2, component.layout_height / 2) + 10;
		int i_15_ = i_8_ * i_8_ + i_9_ * i_9_;
		if (i_15_ <= i_14_ * i_14_) {
			int i_16_ = Rasterizer.SINE[i_13_];
			int i_17_ = Rasterizer.COSINE[i_13_];
			i_16_ = 256 * i_16_ / (256 + Player.anInt4410);
			i_17_ = i_17_ * 256 / (Player.anInt4410 + 256);
			int i_18_ = i_17_ * i_8_ + i_16_ * i_9_ >> 16;
			int i_19_ = i_17_ * i_9_ - i_8_ * i_16_ >> 16;
			int i_20_ = rSFontMetrics.get_paragraph_width(text, 100);
			int i_21_ = rSFontMetrics.get_paragraph_height(text, 100, 0);
			i_18_ -= i_20_ / 2;
			if (i_18_ >= -component.layout_width && i_18_ <= component.layout_width && i_19_ >= -component.layout_height && i_19_ <= component.layout_height) {
				renderFont.draw_text(text, component.layout_width / 2 + i + i_18_, i_7_ + component.layout_height / 2 - i_19_ - i_10_ - i_21_, i_20_, 50, i_11_, 0, 1, 0, 0);
			}

		}
	}

	static final void drawMinimapIcons(RSInterface widget, int draw_x, int draw_y, int i_0_) {
		Class48.process_audio();
		if (GLManager.opengl_mode) {
			GLState.set_clipping(draw_x, draw_y, widget.layout_width + draw_x, widget.layout_height + draw_y);
		} else {
			Rasterizer2D.set_clipping(draw_x, draw_y, widget.layout_width + draw_x, widget.layout_height + draw_y);
		}
		if (Huffman.anInt1819 != 2 && Huffman.anInt1819 != 5 && ComponentMinimap.full_sprite != null) {
			int var19 = Class35.cameraDirection + StaticMethods.anInt3162 & 0x7ff;
			int var7 = -(GameClient.currentPlayer.bound_extents_z / 32) + 464;
			int var6 = 48 + GameClient.currentPlayer.bound_extents_x / 32;
			if (GLManager.opengl_mode) {
				((OpenGLSprite) ComponentMinimap.full_sprite).draw(draw_x, draw_y, widget.layout_width, widget.layout_height, var6, var7, var19, Player.anInt4410 + 256, (OpenGLSprite) widget.get_sprite(false));
			} else {
				((SoftwareSprite) ComponentMinimap.full_sprite).draw_offseted(draw_x, draw_y, widget.layout_width, widget.layout_height, var6, var7, var19, 256 - -Player.anInt4410, widget.anIntArray1064, widget.anIntArray1109);
			}
			if (null != WorldMap.current_player_labels) {
				for (int var8 = 0; var8 < WorldMap.current_player_labels.num_labels; ++var8) {
					if (WorldMap.current_player_labels.method1789(var8)) {
						Font var15 = FontCache.p11_full;
						int var11 = Rasterizer.SINE[var19];
						int var12 = Rasterizer.COSINE[var19];
						int var9 = 2 + 4 * (WorldMap.current_player_labels.label_x[var8] + -MapLoader.region_aboslute_x) + -(GameClient.currentPlayer.bound_extents_x / 32);
						int var10 = 2 + 4 * (-MapLoader.region_aboslute_z + WorldMap.current_player_labels.label_y[var8]) - GameClient.currentPlayer.bound_extents_z / 32;
						var11 = var11 * 256 / (256 + Player.anInt4410);
						var12 = var12 * 256 / (256 + Player.anInt4410);
						int var14 = -(var9 * var11) + var10 * var12 >> 16;
						if (WorldMap.current_player_labels.method1791(var8) == 1) {
							var15 = FontCache.p12_full;
						}

						if (2 == WorldMap.current_player_labels.method1791(var8)) {
							var15 = FontCache.b12_full;
						}

						int var13 = var11 * var10 - -(var12 * var9) >> 16;
						int var16 = var15.get_paragraph_width(WorldMap.current_player_labels.label_text[var8], 100);
						var13 -= var16 / 2;
						if (~var13 <= ~-widget.layout_width && var13 <= widget.layout_width && var14 >= -widget.layout_height && var14 <= widget.layout_height) {
							int var17 = 16777215;
							if (~WorldMap.current_player_labels.label_colour[var8] != 0) {
								var17 = WorldMap.current_player_labels.label_colour[var8];
							}
							if (!GLManager.opengl_mode) {
								Rasterizer2D.set_clippingmask(widget.anIntArray1064, widget.anIntArray1109);
							} else {
								GLState.set_clippingmask((OpenGLSprite) widget.get_sprite(false));
							}
							var15.draw_text(WorldMap.current_player_labels.label_text[var8], draw_x + var13 + widget.layout_width / 2, draw_y + widget.layout_height / 2 + -var14, var16, 50, var17, 0, 256, 1, 0, 0);
							if (GLManager.opengl_mode) {
								GLState.reset_clipmask();
							} else {
								Rasterizer2D.reset_clippingmask();
							}
						}
					}
				}
			}
			for (int i_5_ = 0; (ComponentMinimap.elements_count ^ 0xffffffff) < (i_5_ ^ 0xffffffff); i_5_++) {
				int offset_x = -(GameClient.currentPlayer.bound_extents_x / 32) + ComponentMinimap.elements_x[i_5_] * 4 + 2;
				int offset_y = -(GameClient.currentPlayer.bound_extents_z / 32) + 2 + ComponentMinimap.elements_y[i_5_] * 4;
				LocType loc = LocTypeList.list(ComponentMinimap.elements_ids[i_5_]);
				if (loc.morphisms != null) {
					loc = loc.morph();
				}
				if (loc == null || loc.map_categoryid == -1) {
					continue;
				}
				draw_category(widget, draw_x, draw_y, offset_x, offset_y, loc.map_categoryid);
			}
			for (int i_8_ = 0; i_8_ < 104; i_8_++) {
				for (int i_9_ = 0; i_9_ < 104; i_9_++) {
					NodeDeque class89 = StaticMethods2.groundItems[ObjType.localHeight][i_8_][i_9_];
					if (class89 != null) {
						int offsetX = -(GameClient.currentPlayer.bound_extents_z / 32) + 2 + i_9_ * 4;
						int offsetY = -(GameClient.currentPlayer.bound_extents_x / 32) + 2 + i_8_ * 4;
						draw_minimap_icon(widget, StaticMedia.mapdots[0], draw_x, draw_y, offsetY, offsetX);
					}
				}
			}
			for (int i_12_ = 0; (EntityUpdating.localNPCCount ^ 0xffffffff) < (i_12_ ^ 0xffffffff); i_12_++) {
				NPC npc = GameClient.activeNPCs[EntityUpdating.localNPCIndexes[i_12_]];
				if (npc != null && npc.is_ready()) {
					NPCType def = npc.config;
					if (def != null && def.morphisms != null) {
						def = def.getChildNPC();
					}
					if (def != null && def.drawMinimapDot && def.clickable) {
						int offsetX = -(GameClient.currentPlayer.bound_extents_z / 32) + npc.bound_extents_z / 32;
						int offsetY = npc.bound_extents_x / 32 - GameClient.currentPlayer.bound_extents_x / 32;
						draw_minimap_icon(widget, StaticMedia.mapdots[1], draw_x, draw_y, offsetY, offsetX);
					}
				}
			}
			for (int i_15_ = 0; (StaticMethods.anInt3067 ^ 0xffffffff) < (i_15_ ^ 0xffffffff); i_15_++) {
				Player player = GameClient.localPlayers[GameClient.localPlayerPointers[i_15_]];
				if (player != null && player.is_ready()) {
					int offsetY = -(GameClient.currentPlayer.bound_extents_x / 32) + player.bound_extents_x / 32;
					boolean isFriend = false;
					int offsetX = -(GameClient.currentPlayer.bound_extents_z / 32) + player.bound_extents_z / 32;
					long l = player.username.toUsernameLong();
					for (int i_19_ = 0; i_19_ < Class45.friends_count; i_19_++) {
						if (l == NameHashTable.friends_uid[i_19_] && (Class23_Sub10_Sub3.friends_worldid[i_19_] ^ 0xffffffff) != -1) {
							isFriend = true;
							break;
						}
					}
					boolean sameTeam = false;
					if (GameClient.currentPlayer.currentTeamId != 0 && player.currentTeamId != 0 && (player.currentTeamId ^ 0xffffffff) == (GameClient.currentPlayer.currentTeamId ^ 0xffffffff)) {
						sameTeam = true;
					}
					if (isFriend) {
						draw_minimap_icon(widget, StaticMedia.mapdots[3], draw_x, draw_y, offsetY, offsetX);
					} else if (!sameTeam) {
						draw_minimap_icon(widget, StaticMedia.mapdots[2], draw_x, draw_y, offsetY, offsetX);
					} else {
						draw_minimap_icon(widget, StaticMedia.mapdots[4], draw_x, draw_y, offsetY, offsetX);
					}
				}
			}
			HintIcon[] icon = ReflectionRequest.currentHintIcons;
			for (int i_21_ = 0; (icon.length ^ 0xffffffff) < (i_21_ ^ 0xffffffff); i_21_++) {
				HintIcon class10 = icon[i_21_];
				if (class10 != null && (class10.targetType ^ 0xffffffff) != -1 && GameClient.timer % 20 < 10) {
					if (class10.targetType == 1 && (class10.entityIndex ^ 0xffffffff) <= -1 && (GameClient.activeNPCs.length ^ 0xffffffff) < (class10.entityIndex ^ 0xffffffff)) {
						NPC target = GameClient.activeNPCs[class10.entityIndex];
						if (target != null) {
							int i_22_ = -(GameClient.currentPlayer.bound_extents_z / 32) + target.bound_extents_z / 32;
							int i_23_ = -(GameClient.currentPlayer.bound_extents_x / 32) + target.bound_extents_x / 32;
							method956(widget, (byte) -100, draw_y, i_22_, i_23_, draw_x, class10.arrowId);
						}
					}
					if (class10.targetType == 2) {
						int i_24_ = (-MapLoader.region_aboslute_z + class10.targetX) * 4 + 2 + -(GameClient.currentPlayer.bound_extents_x / 32);
						int i_25_ = -(GameClient.currentPlayer.bound_extents_z / 32) + 2 + 4 * (-MapLoader.region_aboslute_x + class10.targetY);
						method956(widget, (byte) -100, draw_y, i_25_, i_24_, draw_x, class10.arrowId);
					}
					if (class10.targetType == 10 && (class10.entityIndex ^ 0xffffffff) <= -1 && (GameClient.localPlayers.length ^ 0xffffffff) < (class10.entityIndex ^ 0xffffffff)) {
						Player target = GameClient.localPlayers[class10.entityIndex];
						if (target != null) {
							int i_26_ = -(GameClient.currentPlayer.bound_extents_z / 32) + target.bound_extents_z / 32;
							int i_27_ = target.bound_extents_x / 32 + -(GameClient.currentPlayer.bound_extents_x / 32);
							method956(widget, (byte) -100, draw_y, i_26_, i_27_, draw_x, class10.arrowId);
						}
					}
				}
			}
			if (ComponentMinimap.flag_x != 0) {
				int offsetY = 2 + 4 * ComponentMinimap.flag_x - GameClient.currentPlayer.bound_extents_x / 32;
				int offsetX = -(GameClient.currentPlayer.bound_extents_z / 32) + 2 + ComponentMinimap.flag_y * 4;
				draw_minimap_icon(widget, StaticMedia.mapflag, draw_x, draw_y, offsetY, offsetX);
			}
			if (GLManager.opengl_mode) {
				GLShapes.fill_rectangle(widget.layout_width / 2 + draw_x + -1, draw_y + widget.layout_height / 2 - 1, 3, 3, 16777215);
			} else {
				Rasterizer2D.fill_rectangle(widget.layout_width / 2 + draw_x + -1, draw_y + widget.layout_height / 2 - 1, 3, 3, 16777215);
			}
		} else {
			if (GLManager.opengl_loaded) {
				Sprite sprite = widget.get_sprite(false);
				if (sprite != null) {
					sprite.draw_clipped_left_anchor(draw_x, draw_y);
				}
			} else {
				Rasterizer2D.method216(draw_x, draw_y, 0, widget.anIntArray1064, widget.anIntArray1109);
			}
		}
		Class36.needs_clipping[i_0_] = true;
	}

	public static void draw_category(RSInterface component, int i, int i_179_, int i_180_, int i_181_, int category) {
		MECType type = MECTypeList.list(category);
		Sprite sprite = null;
		if (type.graphicId != -1) {
			sprite = type.method3800(false);
			if (sprite != null) {
				draw_minimap_icon(component, sprite, i, i_179_, i_180_, i_181_);
			}
		}
		if (type.text != null) {
			int i_204_ = 0;
			if (sprite != null) {
				i_204_ = sprite.height;
			}
			Font font = FontCache.p11_full;
			Font metrics = FontCache.load_metrics(StaticMedia.p11_full_groupid);
			if (1 == type.textSize) {
				font = FontCache.p12_full;
				metrics = FontCache.load_metrics(StaticMedia.p12_full_groupid);
			}
			if (type.textSize == 2) {
				font = FontCache.b12_full;
				metrics = FontCache.load_metrics(StaticMedia.b12_full_groupid);
			}
			method3139(component, i, i_179_, i_180_, i_181_, i_204_, type.text, font, metrics, type.textColor);
		}
	}

}
