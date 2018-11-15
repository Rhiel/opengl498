package com.rs2.client.components.worldmap;

import com.jagex.*;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.jagex.launcher.GamePlayConfiguration;
import com.rs2.client.scene.Scene;

/**
 * @author Walied K. Yassen
 */
public class WorldMap {

	public static Js5 data_js5;
	public static NodeDeque areas = new NodeDeque();
	public static SoftwareSprite[] mapfunction_raw;
	public static boolean[] functions_displayed;
	public static WorldMapArea current_area;
	public static WorldMapAreaLabels current_labels;
	public static int loading_percentage = 0;
	public static int[][][] anIntArrayArrayArray1903;
	public static int anInt455;
	public static int anInt1460;
	public static int anInt3256;
	public static int anInt65;
	public static int anInt2010 = -16 + (int) (33.0D * Math.random());
	public static int anInt4034 = -8 + (int) (17.0D * Math.random());
	public static int[] anIntArray1161;
	public static int anInt3536;
	public static int anInt3362 = -1;
	public static int anInt1150 = -1;
	public static int anInt2251;
	public static byte[][][] aByteArrayArrayArray2339;
	public static byte[][][] aByteArrayArrayArray640;
	public static int[][][] anIntArrayArrayArray720;
	public static byte[][][] aByteArrayArrayArray383;
	public static byte[][][] aByteArrayArrayArray3390;
	public static byte[][][] aByteArrayArrayArray2452;
	public static int[][][] anIntArrayArrayArray558;
	public static int amountFloors;
	public static float aFloat727;
	public static float aFloat3979;
	public static WorldMapFont font_size_11;
	public static WorldMapFont font_size_12;
	public static WorldMapFont font_size_14;
	public static WorldMapFont font_size_17;
	public static WorldMapFont font_size_19;
	public static WorldMapFont font_size_22;
	public static WorldMapFont font_size_26;
	public static WorldMapFont font_size_30;
	public static RSString[] labels_paragraph_buffer = new RSString[5];
	public static RSString last_area_filename;
	public static RSInterface worldmap_component;
	public static NodeDeque aClass61_1162 = new NodeDeque();
	public static int anInt3611;
	public static Sprite aClass3_Sub28_Sub16_637;
	public static SoftwareSprite worldmap_sprite;
	public static NodeDeque aClass61_1424 = new NodeDeque();
	public static int anInt410;
	public static int anInt930;
	public static int anInt934;
	public static int anInt817;
	public static int anInt4073;
	public static int anInt660;
	public static WorldMapAreaLabels current_player_labels;
	public static WorldMapArea current_player_area;

	public static void initialise(Js5 data_js5, SoftwareSprite[] mapfunctions) {
		WorldMap.data_js5 = data_js5;
		mapfunction_raw = mapfunctions;
		functions_displayed = new boolean[mapfunction_raw.length];
		areas.clear();
		int details_groupid = data_js5.get_groupid("details");
		int[] areas_fileids = data_js5.get_file_entry_file_id(details_groupid);
		for (int areaid = 0; ~areas_fileids.length < ~areaid; ++areaid) {
			areas.add_last(WorldMapArea.deserialise(new Packet(data_js5.get_file(details_groupid, areas_fileids[areaid]))));
		}
	}

	public static void process_loading(int var0) {
		if (null != current_area) {
			if (loading_percentage < 10) {
				if (!data_js5.is_group_cached(current_area.filename.toString())) {
					loading_percentage = CacheConstants.worldMapCacheIdx.get_group_progress(current_area.filename.toString()) / 10;
					return;
				}
				method169();
				loading_percentage = 10;
			}
			if (loading_percentage == 10) {
				anInt3256 = current_area.minx >> 6 << 6;
				anInt65 = current_area.miny >> 6 << 6;
				anInt1460 = (current_area.maxy >> 6 << 6) - anInt65 + 64;
				anInt455 = 64 + (current_area.maxx >> 6 << 6) + -anInt3256;
				if (current_area.defaultzoom == 37) {
					aFloat727 = 3.0F;
					aFloat3979 = 3.0F;
				} else if (current_area.defaultzoom == 50) {
					aFloat727 = 4.0F;
					aFloat3979 = 4.0F;
				} else if (current_area.defaultzoom == 85) {
					aFloat727 = 6.0F;
					aFloat3979 = 6.0F;
				} else if (current_area.defaultzoom == 100) {
					aFloat727 = 8.0F;
					aFloat3979 = 8.0F;
				} else if (current_area.defaultzoom == 200) {
					aFloat727 = 16.0F;
					aFloat3979 = 16.0F;
				} else {
					aFloat727 = 8.0F;
					aFloat3979 = 8.0F;
				}
			}
			int var1 = -anInt3256 + (GameClient.currentPlayer.bound_extents_x >> 7) + MapLoader.region_aboslute_x;
			var1 += -5 + (int) (Math.random() * 10.0D);
			int var2 = -MapLoader.region_aboslute_z + -(GameClient.currentPlayer.bound_extents_z >> 7) + anInt65 + -1 + anInt1460;
			var2 += -5 + (int) (Math.random() * 10.0D);
			if (-1 >= ~var1 && ~anInt455 < ~var1 && 0 <= var2 && ~var2 > ~anInt1460) {
				anInt3536 = var1;
				anInt2251 = var2;
			} else {
				anInt2251 = anInt65 - current_area.origin_y * 64 + anInt1460 + -1;
				anInt3536 = current_area.origin_x * 64 + -anInt3256;
			}
			method117();
			anIntArray1161 = new int[1 + amountFloors];
			int var4 = anInt1460 >> 6;
			int var3 = anInt455 >> 6;
			int var5 = MapLoader.lightness_2 >> 2 << 10;
			int var6 = MapLoader.lightness_1 >> 1;
			aByteArrayArrayArray2339 = new byte[var3][var4][];
			aByteArrayArrayArray640 = new byte[var3][var4][];
			anIntArrayArrayArray720 = new int[var3][var4][];
			aByteArrayArrayArray383 = new byte[var3][var4][];
			anIntArrayArrayArray1903 = new int[var3][var4][];
			aByteArrayArrayArray3390 = new byte[var3][var4][];
			aByteArrayArrayArray2452 = new byte[var3][var4][];
			anIntArrayArrayArray558 = new int[var3][var4][];
			cache_underlays_data(var6, var5);
			loading_percentage = 20;
		} else if (loading_percentage == 20) {
			load_underlays(new Packet(data_js5.get_file(current_area.filename.toString(), "underlay")));
			loading_percentage = 30;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 30) {
			load_overlays(new Packet(data_js5.get_file(current_area.filename.toString(), "overlay")));
			loading_percentage = 40;
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 40) {
			load_overlays2(new Packet(data_js5.get_file(current_area.filename.toString(), "overlay2")));
			loading_percentage = 50;
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 50) {
			load_locs(new Packet(data_js5.get_file(current_area.filename.toString(), "loc")));
			loading_percentage = 60;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (-61 == ~loading_percentage) {
			if (!data_js5.is_valid_group(current_area.filename + "_labels")) {
				current_labels = new WorldMapAreaLabels(0);
			} else {
				if (!data_js5.is_group_cached(current_area.filename + "_labels")) {
					return;
				}
				current_labels = WorldMapAreaLabels.from_area_js5(data_js5, current_area.filename + "_labels");
			}
			loading_percentage = 70;
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 70) {
			font_size_11 = new WorldMapFont(GameShell.canvas, 11, true);
			loading_percentage = 73;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 73) {
			font_size_12 = new WorldMapFont(GameShell.canvas, 12, true);
			loading_percentage = 76;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 76) {
			font_size_14 = new WorldMapFont(GameShell.canvas, 14, true);
			loading_percentage = 79;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 79) {
			font_size_17 = new WorldMapFont(GameShell.canvas, 17, true);
			loading_percentage = 82;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 82) {
			font_size_19 = new WorldMapFont(GameShell.canvas, 19, true);
			loading_percentage = 85;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 85) {
			font_size_22 = new WorldMapFont(GameShell.canvas, 22, true);
			loading_percentage = 88;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else if (loading_percentage == 8) {
			font_size_26 = new WorldMapFont(GameShell.canvas, 26, true);
			loading_percentage = 91;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
		} else {
			font_size_30 = new WorldMapFont(GameShell.canvas, 30, true);
			loading_percentage = 100;
			MapLoader.process_keepalive(true);
			StaticMethods.reset_frametimer();
			System.gc();
		}
	}

	public static void cache_underlays_data(int var1, int var2) {
		for (int var4 = 0; var4 < amountFloors; ++var4) {
			FloType var5 = FloTypeList.list(var4);
			if (null != var5) {
				int var6 = var5.texture_id;
				if (0 <= var6 && !GraphicTools.get_materials().get_material(var6).terrains_only) {
					var6 = -1;
				}

				int var7;
				int var8;
				int var9;
				int var10;
				if (-1 < ~var5.minimap_colour) {
					if (var6 >= 0) {
						var7 = ColourUtil.hslToRgbTable[MapLoader.method729(GraphicTools.get_materials().get_material(var6).get_colour(), 96)];
					} else if (-1 == var5.tile_colour) {
						var7 = -1;
					} else {
						var8 = var5.tile_colour;
						var9 = var1 + (var8 & 127);
						if (var9 < 0) {
							var9 = 0;
						} else if (var9 > 127) {
							var9 = 127;
						}

						var10 = var9 + (896 & var8) + ('\ufc00' & var8 + var2);
						var7 = ColourUtil.hslToRgbTable[MapLoader.method729(var10, 96)];
					}
				} else {
					var8 = var5.minimap_colour;
					var9 = (127 & var8) + var1;
					if (~var9 > -1) {
						var9 = 0;
					} else if (-128 > ~var9) {
						var9 = 127;
					}

					var10 = (896 & var8) + ('\ufc00' & var2 + var8) + var9;
					var7 = ColourUtil.hslToRgbTable[MapLoader.method729(var10, 96)];
				}

				anIntArray1161[1 + var4] = var7;
			}
		}
	}

	public static void load_underlays(Packet packet) {
		int var3 = anInt2010 >> 1;
		int var2 = anInt4034 >> 2 << 10;
		byte[][] var4 = new byte[anInt455][anInt1460];

		int var6;
		int var12;
		int var14;
		while (packet.index < packet.byteBuffer.length) {
			int var7 = 0;
			var6 = 0;
			boolean var5 = false;
			if (~packet.g1() == -2) {
				var6 = packet.g1();
				var7 = packet.g1();
				var5 = true;
			}

			int var8 = packet.g1();
			int var9 = packet.g1();
			int var10 = -anInt3256 + var8 * 64;
			int var11 = -1 + anInt1460 - var9 * 64 + anInt65;
			if (~var10 <= -1 && 0 <= -63 + var11 && anInt455 > var10 - -63 && ~anInt1460 < ~var11) {
				for (var12 = 0; var12 < 64; ++var12) {
					byte[] var13 = var4[var10 - -var12];

					for (var14 = 0; 64 > var14; ++var14) {
						if (!var5 || var12 >= 8 * var6 && 8 + 8 * var6 > var12 && var14 >= var7 * 8 && var14 < 8 + 8 * var7) {
							var13[var11 - var14] = packet.g1s();
						}
					}
				}
			} else if (!var5) {
				packet.index += 4096;
			} else {
				packet.index += 64;
			}
		}
		int var27 = anInt455;
		var6 = anInt1460;
		int[] var29 = new int[var6];
		int[] var28 = new int[var6];
		int[] var30 = new int[var6];
		int[] var32 = new int[var6];
		int[] var31 = new int[var6];
		for (var12 = -5; ~var12 > ~var27; ++var12) {
			int var15;
			int var35;
			for (int var34 = 0; ~var34 > ~var6; ++var34) {
				var14 = var12 + 5;
				if (var27 > var14) {
					var15 = 255 & var4[var14][var34];
					if (~var15 < -1) {
						FluType var16 = FluTypeList.list(var15 - 1);
						var28[var34] += var16.chroma;
						var29[var34] += var16.hue;
						var30[var34] += var16.saturation;
						var32[var34] += var16.luminance;
						++var31[var34];
					}
				}
				var15 = var12 + -5;
				if (-1 >= ~var15) {
					var35 = var4[var15][var34] & 255;
					if (0 < var35) {
						FluType var17 = FluTypeList.list(-1 + var35);
						var28[var34] -= var17.chroma;
						var29[var34] -= var17.hue;
						var30[var34] -= var17.saturation;
						var32[var34] -= var17.luminance;
						--var31[var34];
					}
				}
			}

			if (~var12 <= -1) {
				int[][] var33 = anIntArrayArrayArray1903[var12 >> 6];
				var14 = 0;
				var15 = 0;
				int var36 = 0;
				int var18 = 0;
				var35 = 0;

				for (int var19 = -5; ~var19 > ~var6; ++var19) {
					int var20 = var19 - -5;
					if (var6 > var20) {
						var18 += var31[var20];
						var15 += var29[var20];
						var35 += var30[var20];
						var14 += var28[var20];
						var36 += var32[var20];
					}

					int var21 = -5 + var19;
					if (~var21 <= -1) {
						var35 -= var30[var21];
						var36 -= var32[var21];
						var14 -= var28[var21];
						var18 -= var31[var21];
						var15 -= var29[var21];
					}

					if (var19 >= 0 && 0 < var18) {
						int[] var22 = var33[var19 >> 6];
						int var23 = var36 != 0 ? MapLoader.pack_hsl(var15 / var18, var14 * 256 / var36, var35 / var18) : 0;
						if (var4[var12][var19] == 0) {
							if (var22 != null) {
								var22[MathUtils.bitAnd(4032, var19 << 6) - -MathUtils.bitAnd(var12, 63)] = 0;
							}
						} else {
							if (var22 == null) {
								var22 = var33[var19 >> 6] = new int[4096];
							}
							int var24 = var3 + (var23 & 127);
							if (var24 < 0) {
								var24 = 0;
							} else if (var24 > 127) {
								var24 = 127;
							}
							int var25 = var24 + (896 & var23) + (var23 + var2 & '\ufc00');
							var22[MathUtils.bitAnd(4032, var19 << 6) + MathUtils.bitAnd(63, var12)] = ColourUtil.hslToRgbTable[MapLoader.repack_hsl_lightness(var25, 96)];
						}
					}
				}
			}
		}
	}

	public static void method117() {
		if (0 > anInt3536) {
			anInt3362 = -1;
			anInt3536 = 0;
			anInt1150 = -1;
		}
		if (~anInt3536 < ~anInt455) {
			anInt3362 = -1;
			anInt3536 = anInt455;
			anInt1150 = -1;
		}
		if (anInt2251 < 0) {
			anInt1150 = -1;
			anInt3362 = -1;
			anInt2251 = 0;
		}
		if (anInt1460 < anInt2251) {
			anInt2251 = anInt1460;
			anInt3362 = -1;
			anInt1150 = -1;
		}
	}

	public static void method169() {
		Scene.reset_scene();
		for (int var1 = 0; 4 > var1; ++var1) {
			MapLoader.collision_maps[var1].method1298(113);
		}
		System.gc();
	}

	public static void load_overlays2(Packet var1) {
		while (var1.index < var1.byteBuffer.length) {
			int var4 = 0;
			boolean var3 = false;
			int var5 = 0;
			if (var1.g1() == 1) {
				var3 = true;
				var4 = var1.g1();
				var5 = var1.g1();
			}

			int var6 = var1.g1();
			int var7 = var1.g1();
			int var8 = -anInt3256 + var6 * 64;
			int var9 = anInt1460 + -1 - -anInt65 - 64 * var7;
			byte var2;
			int var10;
			if (-1 >= ~var8 && ~(-63 + var9) <= -1 && anInt455 > var8 + 63 && ~var9 > ~anInt1460) {
				var10 = var8 >> 6;
				int var11 = var9 >> 6;

				for (int var12 = 0; 64 > var12; ++var12) {
					for (int var13 = 0; ~var13 > -65; ++var13) {
						if (!var3 || ~(var4 * 8) >= ~var12 && 8 + 8 * var4 > var12 && var13 >= var5 * 8 && 8 + var5 * 8 > var13) {
							var2 = var1.g1s();
							if (var2 != 0) {
								if (null == aByteArrayArrayArray3390[var10][var11]) {
									aByteArrayArrayArray3390[var10][var11] = new byte[4096];
								}

								aByteArrayArrayArray3390[var10][var11][(63 + -var13 << 6) + var12] = var2;
								byte var14 = var1.g1s();
								if (null == aByteArrayArrayArray2452[var10][var11]) {
									aByteArrayArrayArray2452[var10][var11] = new byte[4096];
								}

								aByteArrayArrayArray2452[var10][var11][var12 + (-var13 + 63 << 6)] = var14;
							}
						}
					}
				}
			} else {
				for (var10 = 0; ~var10 > ~(!var3 ? 4096 : 64); ++var10) {
					var2 = var1.g1s();
					if (-1 != ~var2) {
						++var1.index;
					}
				}
			}
		}
	}

	public static void load_locs(Packet var1) {
		label134: while (true) {
			if (~var1.byteBuffer.length < ~var1.index) {
				boolean var18 = false;
				int var5 = 0;
				int var6 = 0;
				if (-2 == ~var1.g1()) {
					var5 = var1.g1();
					var18 = true;
					var6 = var1.g1();
				}

				int var7 = var1.g1();
				int var8 = var1.g1();
				int var9 = -anInt3256 + 64 * var7;
				int var10 = -(var8 * 64) - (-anInt65 - -1) + anInt1460;
				int var11;
				int var12;
				if (-1 >= ~var9 && -63 + var10 >= 0 && anInt455 > var9 + 63 && var10 < anInt1460) {
					var11 = var9 >> 6;
					var12 = var10 >> 6;
					int var13 = 0;

					while (true) {
						if (var13 >= 64) {
							continue label134;
						}

						for (int var14 = 0; ~var14 > -65; ++var14) {
							if (!var18 || ~(8 * var5) >= ~var13 && ~var13 > ~(8 * var5 - -8) && ~var14 <= ~(8 * var6) && var14 < var6 * 8 - -8) {
								int var15 = var1.g1();
								if (0 != var15) {
									int var2;
									if (1 == (1 & var15)) {
										var2 = var1.g1();
										if (aByteArrayArrayArray640[var11][var12] == null) {
											aByteArrayArrayArray640[var11][var12] = new byte[4096];
										}

										aByteArrayArrayArray640[var11][var12][var13 + (-var14 + 63 << 6)] = (byte) var2;
									}

									if (2 == (var15 & 2)) {
										var2 = var1.g3();
										if (null == anIntArrayArrayArray558[var11][var12]) {
											anIntArrayArrayArray558[var11][var12] = new int[4096];
										}

										anIntArrayArrayArray558[var11][var12][(-var14 + 63 << 6) + var13] = var2;
									}

									if (4 == (var15 & 4)) {
										var2 = var1.g3();
										if (null == anIntArrayArrayArray720[var11][var12]) {
											anIntArrayArrayArray720[var11][var12] = new int[4096];
										}

										--var2;
										LocType var3 = LocTypeList.list(var2);
										if (null != var3.morphisms) {
											var3 = var3.morph();
											if (var3 == null || 0 == ~var3.map_sprite_id) {
												continue;
											}
										}

										anIntArrayArrayArray720[var11][var12][(-var14 + 63 << 6) + var13] = 1 + var3.id;
										WorldMapElement var16 = new WorldMapElement();
										var16.graphic = var3.map_sprite_id;
										var16.x = var9;
										var16.y = var10;
										WorldMap.aClass61_1162.add_last(var16);
									}
								}
							}
						}

						++var13;
					}
				}

				var11 = 0;

				while (true) {
					if (var11 >= (var18 ? 64 : 4096)) {
						continue label134;
					}

					var12 = var1.g1();
					if (var12 != 0) {
						if (~(var12 & 1) == -2) {
							++var1.index;
						}

						if (2 == (var12 & 2)) {
							var1.index += 2;
						}

						if (4 == (var12 & 4)) {
							var1.index += 3;
						}
					}

					++var11;
				}
			}
			return;
		}
	}

	public static void load_overlays(Packet var0) {
		while (~var0.index > ~var0.byteBuffer.length) {
			int var4 = 0;
			boolean var3 = false;
			int var5 = 0;
			if (var0.g1() == 1) {
				var3 = true;
				var4 = var0.g1();
				var5 = var0.g1();
			}

			int var6 = var0.g1();
			int var7 = var0.g1();
			int var9 = -(var7 * 64) - (-anInt65 - anInt1460 + 1);
			int var8 = var6 * 64 + -anInt3256;
			byte var2;
			int var10;
			if (var8 >= 0 && var9 + -63 >= 0 && ~(var8 - -63) > ~anInt455 && var9 < anInt1460) {
				var10 = var8 >> 6;
				int var11 = var9 >> 6;

				for (int var12 = 0; ~var12 > -65; ++var12) {
					for (int var13 = 0; var13 < 64; ++var13) {
						if (!var3 || ~var12 <= ~(8 * var4) && var12 < 8 + var4 * 8 && var13 >= var5 * 8 && ~var13 > ~(8 + var5 * 8)) {
							var2 = var0.g1s();
							if (-1 != ~var2) {
								if (aByteArrayArrayArray383[var10][var11] == null) {
									aByteArrayArrayArray383[var10][var11] = new byte[4096];
								}

								aByteArrayArrayArray383[var10][var11][var12 + (-var13 + 63 << 6)] = var2;
								byte var14 = var0.g1s();
								if (aByteArrayArrayArray2339[var10][var11] == null) {
									aByteArrayArrayArray2339[var10][var11] = new byte[4096];
								}

								aByteArrayArrayArray2339[var10][var11][var12 + (63 - var13 << 6)] = var14;
							}
						}
					}
				}
			} else {
				for (var10 = 0; ~(var3 ? 64 : 4096) < ~var10; ++var10) {
					var2 = var0.g1s();
					if (0 != var2) {
						++var0.index;
					}
				}
			}
		}
	}

	public static int anInt2475 = 0;
	public static RSString aClass94_3268 = RSString.createString(")1");
	public static RSString aClass94_3192 = RSString.createString(" ");
	public static RSString aClass94_4066 = RSString.createString("<br>");
	public static RSString aClass94_4049 = RSString.createString("");
	public static int anInt1780;
	public static int anInt101;
	public static int anInt3704;

	private WorldMap() {
		// NOOP
	}

	public static void draw_labels(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		for (int var9 = 0; ~current_labels.num_labels < ~var9; ++var9) {
			if (current_labels.method1787(var9, (byte) -124)) {
				int var10 = -anInt3256 + current_labels.label_x[var9];
				int var11 = anInt65 - (current_labels.label_y[var9] - -1 - anInt1460);
				int var12 = var1 + (-var1 + var4) * (var10 - var3) / (-var3 + var7);
				int var14 = current_labels.method1791(var9);
				int var13 = (var8 + -var2) * (var11 - var6) / (var5 + -var6) + var2;
				int var15 = 16777215;
				WorldMapFont var16 = null;
				if (var14 == 0) {
					if (aFloat727 == 3.0D) {
						var16 = font_size_11;
					}

					if (4.0D == aFloat727) {
						var16 = font_size_12;
					}

					if (aFloat727 == 6.0D) {
						var16 = font_size_14;
					}

					if (aFloat727 >= 8.0D) {
						var16 = font_size_17;
					}
				}

				if (~var14 == -2) {
					if (3.0D == aFloat727) {
						var16 = font_size_14;
					}

					if (aFloat727 == 4.0D) {
						var16 = font_size_17;
					}

					if (aFloat727 == 6.0D) {
						var16 = font_size_19;
					}

					if (8.0D <= aFloat727) {
						var16 = font_size_22;
					}
				}

				if (var14 == 2) {
					if (aFloat727 == 3.0D) {
						var16 = font_size_19;
					}

					var15 = 16755200;
					if (aFloat727 == 4.0D) {
						var16 = font_size_22;
					}

					if (6.0D == aFloat727) {
						var16 = font_size_26;
					}

					if (aFloat727 >= 8.0D) {
						var16 = font_size_30;
					}
				}

				if (current_labels.label_colour[var9] != -1) {
					var15 = current_labels.label_colour[var9];
				}

				if (null != var16) {
					int var17 = FontCache.p11_full.perform_word_warp(current_labels.label_text[var9], null, labels_paragraph_buffer);
					var13 -= var16.method998() * (var17 + -1) / 2;
					var13 += var16.method1006() / 2;

					for (int var18 = 0; ~var17 < ~var18; ++var18) {
						RSString var19 = labels_paragraph_buffer[var18];
						if (-1 + var17 > var18) {
							var19.method1553(-4 + var19.length(), false);
						}

						var16.method1003(var19, var12, var13, var15, true);
						var13 += var16.method998();
					}
				}
			}
		}
	}

	public static void reset(int var0, boolean var1) {
		aByteArrayArrayArray2339 = null;
		anIntArrayArrayArray720 = null;
		worldmap_component = null;
		aByteArrayArrayArray383 = null;
		anIntArray1161 = null;
		aByteArrayArrayArray2452 = null;
		if (var1 && null != current_area) {
			last_area_filename = current_area.filename;
		} else {
			last_area_filename = null;
		}
		aByteArrayArrayArray640 = null;
		aByteArrayArrayArray3390 = null;
		anIntArrayArrayArray558 = null;
		anIntArrayArrayArray1903 = null;
		loading_percentage = 0;
		current_area = null;
		aClass61_1162.clear();
		current_labels = null;
		anInt3362 = -1;
		font_size_22 = null;
		font_size_30 = null;
		font_size_12 = null;
		font_size_26 = null;
		font_size_11 = null;
		font_size_14 = null;
		font_size_17 = null;
		font_size_19 = null;
		aClass3_Sub28_Sub16_637 = null;
		anInt1150 = -1;
		worldmap_sprite = null;
	}

	public static void draw_surface(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
		int var11 = var4 - var6;
		int var12 = -var3 + var2;
		if (anInt455 > var4) {
			++var11;
		}

		if (anInt1460 > var2) {
			++var12;
		}
		int var13;
		int var14;
		int var15;
		int var17;
		int var16;
		int var19;
		int var21;
		int var20;
		int var22;
		int var25;
		int var24;
		int var26;
		int var28;
		int var31;
		int var30;
		int var32;
		int var33;
		int[][] var41;
		int var10000;
		for (var13 = 0; ~var11 < ~var13; ++var13) {
			var14 = var13 * var8 + var1 >> 16;
			var15 = (var13 + 1) * var8 + var1 >> 16;
			var16 = -var14 + var15;
			if (var16 > 0) {
				var17 = var13 - -var6 >> 6;
				if (0 <= var17 && ~(anIntArrayArrayArray1903.length + -1) <= ~var17) {
					var14 += var5;
					var41 = anIntArrayArrayArray1903[var17];
					byte[][] var45 = aByteArrayArrayArray383[var17];
					byte[][] var42 = aByteArrayArrayArray2339[var17];
					byte[][] var23 = aByteArrayArrayArray640[var17];
					byte[][] var43 = aByteArrayArrayArray2452[var17];
					var15 += var5;
					byte[][] var46 = aByteArrayArrayArray3390[var17];

					for (var24 = 0; var12 > var24; ++var24) {
						var25 = var7 * var24 + var9 >> 16;
						var26 = var9 - -((1 + var24) * var7) >> 16;
						int var27 = -var25 + var26;
						if (~var27 < -1) {
							var26 += var0;
							var28 = var3 + var24 >> 6;
							int var29 = 63 & var3 + var24;
							var25 += var0;
							var30 = var13 + var6 & 63;
							var31 = (var29 << 6) + var30;
							if (0 <= var28 && var41.length + -1 >= var28 && null != var41[var28]) {
								var32 = var41[var28][var31];
							} else {
								if (~current_area.size == 0) {
									if (~(4 & var24 + var3) != ~(var6 + var13 & 4)) {
										var32 = 4936552;
									} else {
										var32 = anIntArray1161[1 + AbstractTimer.anInt305];
									}
								} else {
									var32 = current_area.size;
								}

								if (~var28 > -1 || var28 > var41.length + -1) {
									if (var32 == 0) {
										var32 = 1;
									}

									Rasterizer2D.fill_rectangle(var14, var25, var16, var27, var32);
									continue;
								}
							}

							var33 = var45[var28] == null ? 0 : anIntArray1161[var45[var28][var31] & 255];
							if (-1 == ~var32) {
								var32 = 1;
							}

							int var34 = var46[var28] != null ? anIntArray1161[255 & var46[var28][var31]] : 0;
							int var36;
							if (var33 == 0 && -1 == ~var34) {
								Rasterizer2D.fill_rectangle(var14, var25, var16, var27, var32);
							} else {
								byte var35;
								if (0 != var33) {
									if (~var33 == 0) {
										var33 = 1;
									}

									var35 = var42[var28] != null ? var42[var28][var31] : 0;
									var36 = var35 & 252;
									if (-1 != ~var36 && ~var16 < -2 && var27 > 1) {
										method2272(Rasterizer2D.colour_buffer, var33, var14, var35 & 3, var32, var36 >> 2, var27, var16, var25, true, (byte) 21);
									} else {
										Rasterizer2D.fill_rectangle(var14, var25, var16, var27, var33);
									}
								}

								if (~var34 != -1) {
									if (var34 == -1) {
										var34 = var32;
									}

									var35 = var43[var28][var31];
									var36 = 252 & var35;
									if (~var36 == -1 || -2 <= ~var16 || 1 >= var27) {
										Rasterizer2D.fill_rectangle(var14, var25, var16, var27, var34);
									}

									method2272(Rasterizer2D.colour_buffer, var34, var14, var35 & 3, 0, var36 >> 2, var27, var16, var25, var33 == 0, (byte) 21);
								}
							}

							if (var23[var28] != null) {
								int var49 = var23[var28][var31] & 255;
								if (-1 != ~var49) {
									if (1 != var16) {
										var36 = var15 - 1;
									} else {
										var36 = var14;
									}

									int var37;
									if (1 != var27) {
										var37 = -1 + var26;
									} else {
										var37 = var25;
									}

									int var38 = 13421772;
									if (5 <= var49 && 8 >= var49 || var49 >= 13 && 16 >= var49 || 21 <= var49 && ~var49 >= -25 || -28 == ~var49 || 28 == var49) {
										var38 = 13369344;
										var49 -= 4;
									}

									if (1 == var49) {
										Rasterizer2D.draw_vertical_line(var14, var25, var27, var38);
									} else if (2 == var49) {
										Rasterizer2D.draw_horizontal_line(var14, var25, var16, var38);
									} else if (3 != var49) {
										if (-5 != ~var49) {
											if (var49 != 9) {
												if (~var49 != -11) {
													if (-12 == ~var49) {
														Rasterizer2D.draw_vertical_line(var36, var25, var27, 16777215);
														Rasterizer2D.draw_horizontal_line(var14, var37, var16, var38);
													} else if (var49 != 12) {
														if (var49 == 17) {
															Rasterizer2D.draw_horizontal_line(var14, var25, 1, var38);
														} else if (var49 != 18) {
															if (~var49 == -20) {
																Rasterizer2D.draw_horizontal_line(var36, var37, 1, var38);
															} else if (~var49 == -21) {
																Rasterizer2D.draw_horizontal_line(var14, var37, 1, var38);
															} else {
																int var39;
																if (25 == var49) {
																	for (var39 = 0; ~var39 > ~var27; ++var39) {
																		Rasterizer2D.draw_horizontal_line(var39 + var14, -var39 + var37, 1, var38);
																	}
																} else if (26 == var49) {
																	for (var39 = 0; var39 < var27; ++var39) {
																		Rasterizer2D.draw_horizontal_line(var39 + var14, var25 + var39, 1, var38);
																	}
																}
															}
														} else {
															Rasterizer2D.draw_horizontal_line(var36, var25, 1, var38);
														}
													} else {
														Rasterizer2D.draw_vertical_line(var14, var25, var27, 16777215);
														Rasterizer2D.draw_horizontal_line(var14, var37, var16, var38);
													}
												} else {
													Rasterizer2D.draw_vertical_line(var36, var25, var27, 16777215);
													Rasterizer2D.draw_horizontal_line(var14, var25, var16, var38);
												}
											} else {
												Rasterizer2D.draw_vertical_line(var14, var25, var27, 16777215);
												Rasterizer2D.draw_horizontal_line(var14, var25, var16, var38);
											}
										} else {
											Rasterizer2D.draw_horizontal_line(var14, var37, var16, var38);
										}
									} else {
										Rasterizer2D.draw_vertical_line(var36, var25, var27, var38);
									}
								}
							}
						}
					}
				} else {
					var14 += var5;

					for (int var18 = 0; ~var12 < ~var18; ++var18) {
						if (-1 != current_area.size) {
							var19 = current_area.size;
						} else if ((var13 - -var6 & 4) == (4 & var18 + var3)) {
							var19 = anIntArray1161[1 + AbstractTimer.anInt305];
						} else {
							var19 = 4936552;
						}

						if (~var19 == -1) {
							var19 = 1;
						}

						var20 = (var7 * var18 + var9 >> 16) + var0;
						var21 = var0 + ((var18 + 1) * var7 + var9 >> 16);
						var22 = var21 + -var20;
						Rasterizer2D.fill_rectangle(var14, var20, var16, var22, var19);
					}

					var10000 = var15 + var5;
				}
			}
		}

		for (var13 = -2; 2 + var11 > var13; ++var13) {
			var14 = var1 - -(var13 * var8) >> 16;
			var15 = var8 * (var13 + 1) + var1 >> 16;
			var16 = -var14 + var15;
			if (~var16 < -1) {
				var14 += var5;
				var17 = var6 + var13 >> 6;
				var10000 = var15 + var5;
				if (-1 >= ~var17 && anIntArrayArrayArray558.length + -1 >= var17) {
					var41 = anIntArrayArrayArray558[var17];

					for (var19 = -2; ~var19 > ~(var12 - -2); ++var19) {
						var20 = var9 - -(var19 * var7) >> 16;
						var21 = var9 - -((var19 + 1) * var7) >> 16;
						var22 = var21 + -var20;
						if (~var22 < -1) {
							var20 += var0;
							int var44 = var19 - -var3 >> 6;
							var10000 = var21 + var0;
							if (~var44 <= -1 && var44 <= -1 + var41.length) {
								var24 = ((63 & var3 + var19) << 6) - -(var13 - -var6 & 63);
								if (null != var41[var44]) {
									var25 = var41[var44][var24];
									var26 = 16383 & var25;
									if (-1 != ~var26) {
										var28 = ('\ud228' & var25) >> 14;
										MSIType var47 = MSITypeList.list(-1 + var26);
										SoftwarePaletteSprite var48 = var47.get_sprite(var28);
										if (var48 != null) {
											var31 = var22 * var48.height / 4;
											var30 = var16 * var48.width / 4;
											if (var47.enlarge) {
												var32 = var25 >> 16 & 15;
												var33 = (16103184 & var25) >> 20;
												if (-2 == ~(1 & var28)) {
													var28 = var32;
													var32 = var33;
													var33 = var28;
												}

												var30 = var16 * var32;
												var31 = var22 * var33;
											}

											if (-1 != ~var30 && -1 != ~var31) {
												if (~var47.colour == -1) {
													var48.draw(var14, -var31 + var20 - -var22, var30, var31);
												} else {
													var48.draw(var14, var20 - (var31 - var22), var30, var31, var47.colour);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	public static void method2272(int[] var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, byte var10) {
		int var11 = var2;
		if (~Rasterizer2D.clip_right < ~var2) {
			if (~var2 > ~Rasterizer2D.clip_left) {
				var11 = Rasterizer2D.clip_left;
			}

			int var12 = var7 + var2;
			if (Rasterizer2D.clip_left < var12) {
				if (Rasterizer2D.clip_right < var12) {
					var12 = Rasterizer2D.clip_right;
				}

				int var13 = var8;
				if (~Rasterizer2D.clip_bottom < ~var8) {
					int var14 = var8 + var6;
					if (~var8 > ~Rasterizer2D.clip_top) {
						var13 = Rasterizer2D.clip_top;
					}

					if (var14 > Rasterizer2D.clip_top) {
						if (var10 == 21) {
							int var15 = var11 + Rasterizer2D.width * var13;
							if (-10 == ~var5) {
								var3 = 3 & var3 - -1;
								var5 = 1;
							}

							int var16 = -var12 + var11 + Rasterizer2D.width;
							var13 -= var8;
							int var20 = var6 + -var13;
							if (Rasterizer2D.clip_bottom < var14) {
								var14 = Rasterizer2D.clip_bottom;
							}

							if (~var5 == -11) {
								var3 = var3 - -3 & 3;
								var5 = 1;
							}

							var11 -= var2;
							int var18 = -var11 + var7;
							if (11 == var5) {
								var3 = 3 & var3 + 3;
								var5 = 8;
							}

							var12 -= var2;
							int var17 = var7 + -var12;
							var14 -= var8;
							int var19 = var6 - var14;
							int var21;
							int var22;
							if (-2 != ~var5) {
								if (2 == var5) {
									if (-1 != ~var3) {
										if (1 == var3) {
											for (var21 = var13; ~var21 > ~var14; ++var21) {
												for (var22 = var11; ~var12 < ~var22; ++var22) {
													if (0 <= var15 && ~var15 > ~var0.length) {
														if (~(var21 << 1) >= ~var22) {
															var0[var15] = var1;
														} else if (var9) {
															var0[var15] = var4;
														}

														++var15;
													} else {
														++var15;
													}
												}

												var15 += var16;
											}

										} else if (var3 != 2) {
											if (3 == var3) {
												for (var21 = var20 + -1; var19 <= var21; --var21) {
													for (var22 = -1 + var18; var17 <= var22; --var22) {
														if (var21 << 1 > var22) {
															if (var9) {
																var0[var15] = var4;
															}
														} else {
															var0[var15] = var1;
														}

														++var15;
													}

													var15 += var16;
												}

											}
										} else {
											for (var21 = var13; ~var14 < ~var21; ++var21) {
												for (var22 = var18 + -1; var17 <= var22; --var22) {
													if (var21 >> 1 >= var22) {
														var0[var15] = var1;
													} else if (var9) {
														var0[var15] = var4;
													}

													++var15;
												}

												var15 += var16;
											}

										}
									} else {
										for (var21 = var20 + -1; var19 <= var21; --var21) {
											for (var22 = var11; ~var22 > ~var12; ++var22) {
												if (~(var21 >> 1) <= ~var22) {
													var0[var15] = var1;
												} else if (var9) {
													var0[var15] = var4;
												}

												++var15;
											}

											var15 += var16;
										}

									}
								} else if (~var5 != -4) {
									if (~var5 != -5) {
										if (var5 != 5) {
											if (~var5 == -7) {
												if (~var3 == -1) {
													for (var21 = var13; var14 > var21; ++var21) {
														for (var22 = var11; var12 > var22; ++var22) {
															if (var22 > var7 / 2) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (~var3 == -2) {
													for (var21 = var13; ~var21 > ~var14; ++var21) {
														for (var22 = var11; var12 > var22; ++var22) {
															if (var21 > var6 / 2) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (2 == var3) {
													for (var21 = var13; ~var21 > ~var14; ++var21) {
														for (var22 = var11; ~var12 < ~var22; ++var22) {
															if (var22 >= var7 / 2) {
																var0[var15] = var1;
															} else if (var9) {
																var0[var15] = var4;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (~var3 == -4) {
													for (var21 = var13; ~var21 > ~var14; ++var21) {
														for (var22 = var11; ~var22 > ~var12; ++var22) {
															if (~var21 > ~(var6 / 2)) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}
											}

											if (7 == var5) {
												if (0 == var3) {
													for (var21 = var13; ~var14 < ~var21; ++var21) {
														for (var22 = var11; ~var12 < ~var22; ++var22) {
															if (var22 <= var21 + -(var6 / 2)) {
																var0[var15] = var1;
															} else if (var9) {
																var0[var15] = var4;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (var3 == 1) {
													for (var21 = var20 + -1; var21 >= var19; --var21) {
														for (var22 = var11; var12 > var22; ++var22) {
															if (~(-(var6 / 2) + var21) > ~var22) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (~var3 == -3) {
													for (var21 = var20 + -1; ~var19 >= ~var21; --var21) {
														for (var22 = -1 + var18; ~var17 >= ~var22; --var22) {
															if (var22 > var21 + -(var6 / 2)) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (3 == var3) {
													for (var21 = var13; ~var21 > ~var14; ++var21) {
														for (var22 = -1 + var18; ~var22 <= ~var17; --var22) {
															if (var21 + -(var6 / 2) >= var22) {
																var0[var15] = var1;
															} else if (var9) {
																var0[var15] = var4;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}
											}

											if (~var5 == -9) {
												if (0 == var3) {
													for (var21 = var13; var14 > var21; ++var21) {
														for (var22 = var11; ~var12 < ~var22; ++var22) {
															if (-(var6 / 2) + var21 <= var22) {
																var0[var15] = var1;
															} else if (var9) {
																var0[var15] = var4;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (var3 == 1) {
													for (var21 = -1 + var20; var21 >= var19; --var21) {
														for (var22 = var11; ~var12 < ~var22; ++var22) {
															if (-(var6 / 2) + var21 > var22) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (var3 == 2) {
													for (var21 = var20 - 1; ~var21 <= ~var19; --var21) {
														for (var22 = -1 + var18; ~var17 >= ~var22; --var22) {
															if (~var22 > ~(var21 - var6 / 2)) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}

												if (~var3 == -4) {
													for (var21 = var13; ~var14 < ~var21; ++var21) {
														for (var22 = -1 + var18; ~var22 <= ~var17; --var22) {
															if (var21 + -(var6 / 2) > var22) {
																if (var9) {
																	var0[var15] = var4;
																}
															} else {
																var0[var15] = var1;
															}

															++var15;
														}

														var15 += var16;
													}

													return;
												}
											}

										} else if (0 == var3) {
											for (var21 = var20 + -1; var21 >= var19; --var21) {
												for (var22 = -1 + var18; ~var17 >= ~var22; --var22) {
													if (var21 >> 1 <= var22) {
														var0[var15] = var1;
													} else if (var9) {
														var0[var15] = var4;
													}

													++var15;
												}

												var15 += var16;
											}

										} else if (1 == var3) {
											for (var21 = -1 + var20; ~var19 >= ~var21; --var21) {
												for (var22 = var11; var22 < var12; ++var22) {
													if (var22 > var21 << 1) {
														if (var9) {
															var0[var15] = var4;
														}
													} else {
														var0[var15] = var1;
													}

													++var15;
												}

												var15 += var16;
											}

										} else if (~var3 != -3) {
											if (~var3 == -4) {
												for (var21 = var13; ~var14 < ~var21; ++var21) {
													for (var22 = var18 + -1; var17 <= var22; --var22) {
														if (var21 << 1 >= var22) {
															var0[var15] = var1;
														} else if (var9) {
															var0[var15] = var4;
														}

														++var15;
													}

													var15 += var16;
												}

											}
										} else {
											for (var21 = var13; var14 > var21; ++var21) {
												for (var22 = var11; ~var12 < ~var22; ++var22) {
													if (~var22 > ~(var21 >> 1)) {
														if (var9) {
															var0[var15] = var4;
														}
													} else {
														var0[var15] = var1;
													}

													++var15;
												}

												var15 += var16;
											}

										}
									} else if (~var3 != -1) {
										if (1 == var3) {
											for (var21 = var13; var21 < var14; ++var21) {
												for (var22 = var11; var22 < var12; ++var22) {
													if (~(var21 << 1) <= ~var22) {
														var0[var15] = var1;
													} else if (var9) {
														var0[var15] = var4;
													}

													++var15;
												}

												var15 += var16;
											}

										} else if (2 == var3) {
											for (var21 = var13; var21 < var14; ++var21) {
												for (var22 = var18 + -1; ~var17 >= ~var22; --var22) {
													if (~var22 <= ~(var21 >> 1)) {
														var0[var15] = var1;
													} else if (var9) {
														var0[var15] = var4;
													}

													++var15;
												}

												var15 += var16;
											}

										} else if (var3 == 3) {
											for (var21 = -1 + var20; ~var19 >= ~var21; --var21) {
												for (var22 = -1 + var18; ~var17 >= ~var22; --var22) {
													if (var22 <= var21 << 1) {
														var0[var15] = var1;
													} else if (var9) {
														var0[var15] = var4;
													}

													++var15;
												}

												var15 += var16;
											}

										}
									} else {
										for (var21 = var20 + -1; ~var19 >= ~var21; --var21) {
											for (var22 = var11; ~var22 > ~var12; ++var22) {
												if (~(var21 >> 1) < ~var22) {
													if (var9) {
														var0[var15] = var4;
													}
												} else {
													var0[var15] = var1;
												}

												++var15;
											}

											var15 += var16;
										}

									}
								} else if (-1 == ~var3) {
									for (var21 = var20 - 1; ~var19 >= ~var21; --var21) {
										for (var22 = -1 + var18; var17 <= var22; --var22) {
											if (~(var21 >> 1) <= ~var22) {
												var0[var15] = var1;
											} else if (var9) {
												var0[var15] = var4;
											}

											++var15;
										}

										var15 += var16;
									}

								} else if (~var3 == -2) {
									for (var21 = -1 + var20; ~var21 <= ~var19; --var21) {
										for (var22 = var11; ~var22 > ~var12; ++var22) {
											if (var22 >= var21 << 1) {
												var0[var15] = var1;
											} else if (var9) {
												var0[var15] = var4;
											}

											++var15;
										}

										var15 += var16;
									}

								} else if (2 == var3) {
									for (var21 = var13; ~var14 < ~var21; ++var21) {
										for (var22 = var11; ~var22 > ~var12; ++var22) {
											if (~var22 >= ~(var21 >> 1)) {
												var0[var15] = var1;
											} else if (var9) {
												var0[var15] = var4;
											}

											++var15;
										}

										var15 += var16;
									}

								} else if (3 == var3) {
									for (var21 = var13; ~var14 < ~var21; ++var21) {
										for (var22 = var18 - 1; ~var22 <= ~var17; --var22) {
											if (~(var21 << 1) < ~var22) {
												if (var9) {
													var0[var15] = var4;
												}
											} else {
												var0[var15] = var1;
											}

											++var15;
										}

										var15 += var16;
									}

								}
							} else if (var3 == 0) {
								for (var21 = var13; ~var14 < ~var21; ++var21) {
									for (var22 = var11; ~var12 < ~var22; ++var22) {
										if (~var22 >= ~var21) {
											var0[var15] = var1;
										} else if (var9) {
											var0[var15] = var4;
										}

										++var15;
									}

									var15 += var16;
								}

							} else if (1 != var3) {
								if (2 != var3) {
									if (-4 == ~var3) {
										for (var21 = var20 + -1; var19 <= var21; --var21) {
											for (var22 = var11; ~var22 > ~var12; ++var22) {
												if (var22 < var21) {
													if (var9) {
														var0[var15] = var4;
													}
												} else {
													var0[var15] = var1;
												}

												++var15;
											}

											var15 += var16;
										}

									}
								} else {
									for (var21 = var13; var21 < var14; ++var21) {
										for (var22 = var11; var12 > var22; ++var22) {
											if (var22 >= var21) {
												var0[var15] = var1;
											} else if (var9) {
												var0[var15] = var4;
											}

											++var15;
										}

										var15 += var16;
									}

								}
							} else {
								for (var21 = var20 + -1; var21 >= var19; --var21) {
									for (var22 = var11; ~var12 < ~var22; ++var22) {
										if (var21 >= var22) {
											var0[var15] = var1;
										} else if (var9) {
											var0[var15] = var4;
										}

										++var15;
									}

									var15 += var16;
								}

							}
						}
					}
				}
			}
		}
	}

	public static void set_area_by_filename(RSString var0) {
		for (WorldMapArea var2 = (WorldMapArea) areas.get_first(); var2 != null; var2 = (WorldMapArea) areas.get_next()) {
			if (var2.filename.equals(var0)) {
				current_area = var2;
				return;
			}
		}
	}

	public static Queue get_selectable_areas(int x, int y) {
		Queue var3 = new Queue();
		for (WorldMapArea var4 = (WorldMapArea) areas.get_first(); var4 != null; var4 = (WorldMapArea) areas.get_next()) {
			if (var4.selectable && var4.is_within(x, y)) {
				var3.add_last(var4);
			}
		}
		return var3;
	}

	public static WorldMapArea get_area_by_filename(RSString var1) {
		for (WorldMapArea var2 = (WorldMapArea) areas.get_first(); var2 != null; var2 = (WorldMapArea) areas.get_next()) {
			if (var2.filename.equals(var1)) {
				return var2;
			}
		}
		return null;
	}

	public static WorldMapArea get_selectable_area(int var0, int var2) {
		for (WorldMapArea var3 = (WorldMapArea) areas.get_first(); var3 != null; var3 = (WorldMapArea) areas.get_next()) {
			if (var3.selectable && var3.is_within(var0, var2)) {
				return var3;
			}
		}
		return null;
	}

	public static void draw_mapfunctions(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
		int var11 = var2 - var4;
		int var13 = -1;
		if (~WorldMap.anInt3704 < -1) {
			if (anInt3611 <= 10) {
				var13 = 5 * anInt3611;
			} else {
				var13 = -((-10 + anInt3611) * 5) + 50;
			}
		}

		int var12 = -var9 + var1;
		int var15 = 983040 / var8;
		int var16 = 983040 / var3;

		for (int var17 = -var15; var17 < var11 - -var15; ++var17) {
			int var18 = var5 - -(var17 * var8) >> 16;
			int var19 = var8 * (var17 + 1) + var5 >> 16;
			int var20 = -var18 + var19;
			if (-1 > ~var20) {
				int var21 = var4 + var17 >> 6;
				var18 += var0;
				int var10000 = var19 + var0;
				if (~var21 <= -1 && var21 <= -1 + anIntArrayArrayArray720.length) {
					int[][] var22 = anIntArrayArrayArray720[var21];

					for (int var23 = -var16; var23 < var12 - -var16; ++var23) {
						int var25 = var6 - -(var3 * (var23 - -1)) >> 16;
						int var24 = var23 * var3 + var6 >> 16;
						int var26 = var25 + -var24;
						if (0 < var26) {
							var24 += var10;
							int var27 = var9 + var23 >> 6;
							var10000 = var25 + var10;
							if (~var27 <= -1 && ~var27 >= ~(-1 + var22.length) && null != var22[var27]) {
								int var28 = (63 & var17 + var4) + (4032 & var9 + var23 << 6);
								int var29 = var22[var27][var28];
								if (-1 != ~var29) {
									LocType var14 = LocTypeList.list(-1 + var29);
									if (!functions_displayed[var14.map_sprite_id]) {
										if (~var13 != 0 && ~var14.map_sprite_id == ~WorldMap.anInt101) {
											WorldMapElement var30 = new WorldMapElement();
											var30.x = var18;
											var30.y = var24;
											var30.graphic = var14.map_sprite_id;
											aClass61_1424.add_last(var30);
										} else {
											mapfunction_raw[var14.map_sprite_id].draw_clipped_left_anchor(var18 + -7, -7 + var24);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (var7 >= 124) {
			for (WorldMapElement var32 = (WorldMapElement) aClass61_1424.get_first(); null != var32; var32 = (WorldMapElement) aClass61_1424.get_next()) {
				Rasterizer2D.method201(var32.x, var32.y, 15, 16776960, var13);
				Rasterizer2D.method201(var32.x, var32.y, 13, 16776960, var13);
				Rasterizer2D.method201(var32.x, var32.y, 11, 16776960, var13);
				Rasterizer2D.method201(var32.x, var32.y, 9, 16776960, var13);
				mapfunction_raw[var32.graphic].draw_clipped_left_anchor(-7 + var32.x, -7 + var32.y);
			}

			aClass61_1424.clear();
		}
	}

	public static boolean is_mapfunction_displayable(int var0, int var1) {
		return ~var0 <= -1 && functions_displayed.length > var0 ? functions_displayed[var0] : false;
	}

	public static void switch_mapfunction_displayable(int var0, int var1) {
		if (0 <= var0 && functions_displayed.length > var0) {
			functions_displayed[var0] = !functions_displayed[var0];
		}
	}

	public static WorldMapArea get_current_area() {
		return current_area;
	}

	public static int get_label_index(RSString var0) {
		if (current_labels != null && var0.length() != 0) {
			for (int var2 = 0; var2 < current_labels.num_labels; ++var2) {
				if (current_labels.label_text[var2].replace(WorldMap.aClass94_3192, WorldMap.aClass94_4066).equals(var0)) {
					return var2;
				}
			}
			return -1;
		} else {
			return -1;
		}
	}

	public static void method1175(int var0, int var1) {
		anInt3362 = -1;
		anInt1150 = -1;
		anInt3536 = var0;
		method117();
	}

	public static void method354(int var0, int var1) {
		anInt3362 = -1;
		anInt3362 = -1;
		anInt2251 = var1;
		method117();
	}

	public static int method1602(int var0, RSString var1) {
		if (current_labels != null && ~var1.length() != -1) {
			for (int var2 = var0; ~current_labels.num_labels < ~var2; ++var2) {
				if (current_labels.label_text[var2].replace(aClass94_3192, aClass94_4066).method150(var1)) {
					return var2;
				}
			}

			return -1;
		} else {
			return -1;
		}
	}

	public static void method565(int var1, int var2) {
		anInt1150 = -anInt3256 + var1;
		int var3 = -((int) (worldmap_component.layout_width / aFloat727)) + anInt1150;
		int var4 = anInt1150 + (int) (worldmap_component.layout_width / aFloat727);
		if (var3 < 0) {
			anInt1150 = (int) (worldmap_component.layout_width / aFloat727);
		}
		anInt3362 = anInt1460 + -1 + anInt65 + -var2;
		int var6 = (int) (worldmap_component.layout_height / aFloat727) + anInt3362;
		int var5 = anInt3362 - (int) (worldmap_component.layout_height / aFloat727);
		if (~var4 < ~anInt455) {
			anInt1150 = anInt455 + -((int) (worldmap_component.layout_width / aFloat727));
		}

		if (-1 < ~var5) {
			anInt3362 = (int) (worldmap_component.layout_height / aFloat727);
		}

		if (~anInt1460 > ~var6) {
			anInt3362 = -((int) (worldmap_component.layout_height / aFloat727)) + anInt1460;
		}
	}

	public static void method84(RSString var0, int var1) {
		int var2 = method1602(0, var0);
		if (~var2 != 0) {
			method565(current_labels.label_x[var2], current_labels.label_y[var2]);
		}
	}

	public static void method915(RSString var0, int var1) {
		int var2 = get_label_index(var0);
		if (var1 != var2) {
			method565(current_labels.label_x[var2], current_labels.label_y[var2]);
		}
	}

	public static RSString method27(RSString var0, boolean var1) {
		int var2 = method1602(0, var0);
		return var2 != -1 ? current_labels.label_text[var2].replace(aClass94_3192, aClass94_4066) : aClass94_4049;
	}

	public static int method251(int var0) {
		if (var0 != -1) {
			return 18;
		} else if (current_labels == null) {
			return -1;
		} else {
			while (~WorldMap.anInt1780 > ~current_labels.num_labels) {
				if (current_labels.method1794(WorldMap.anInt1780, -20138)) {
					return WorldMap.anInt1780++;
				}

				++WorldMap.anInt1780;
			}

			return -1;
		}
	}

	public static void method72(int var0, int var1, int var2, int var3, int var4) {
		if (GLManager.opengl_mode) {
			GLState.set_clipping(var0, var4, var2 + var0, var1 + var4);
			GLShapes.fill_rectangle(var0, var4, var2, var1, 0);
		} else {
			Rasterizer2D.set_clipping(var0, var4, var2 + var0, var4 + var1);
			Rasterizer2D.fill_rectangle(var0, var4, var2, var1, 0);
		}
		if (~loading_percentage <= -101) {
			if (null == aClass3_Sub28_Sub16_637 || var2 != aClass3_Sub28_Sub16_637.width || aClass3_Sub28_Sub16_637.height != var1) {
				SoftwareSprite sprite = new SoftwareSprite(var2, var1);
				Rasterizer2D.initialise(sprite.pixels, var2, var1);
				method523(var2, 0, 0, anInt455, 0, 0, anInt1460, var1, 0);
				if (GLManager.opengl_mode) {
					aClass3_Sub28_Sub16_637 = new OpenGLSprite(sprite);
				} else {
					aClass3_Sub28_Sub16_637 = sprite;
				}
				if (GLManager.opengl_mode) {
					Rasterizer2D.colour_buffer = null;
				} else {
					GameClient.software_frame_buffer.bind();
				}
			}

			aClass3_Sub28_Sub16_637.draw(var0, var4);
			int var6 = var1 * anInt934 / anInt1460 + var4;
			int var8 = anInt410 * var1 / anInt1460;
			int var15 = var0 + var2 * anInt930 / anInt455;
			int var7 = var2 * anInt817 / anInt455;
			int var9 = 16711680;
			if (GLManager.opengl_mode) {
				GLShapes.fill_rectangle(var15, var6, var7, var8, var9, 128);
				GLShapes.draw_rectangle(var15, var6, var7, var8, var9);
			} else {
				Rasterizer2D.fill_rectangle(var15, var6, var7, var8, var9, 128);
				Rasterizer2D.draw_rectangle(var15, var6, var7, var8, var9);
			}

			if (~WorldMap.anInt3704 < -1) {
				int var10;
				if (-11 > ~anInt3611) {
					var10 = (-anInt3611 + 20) * 25;
				} else {
					var10 = 25 * anInt3611;
				}

				for (WorldMapElement var11 = (WorldMapElement) aClass61_1162.get_first(); var11 != null; var11 = (WorldMapElement) aClass61_1162.get_next()) {
					if (~var11.graphic == ~WorldMap.anInt101) {
						int var13 = var4 - -(var11.y * var1 / anInt1460);
						int var12 = var2 * var11.x / anInt455 + var0;
						if (GLManager.opengl_mode) {
							GLShapes.fill_rectangle(-2 + var12, -2 + var13, 4, 4, 16776960, var10);
						} else {
							Rasterizer2D.fill_rectangle(var12 + -2, -2 + var13, 4, 4, 16776960, var10);
						}
					}
				}
			}

		}
	}

	public static void method799(int x, int y, int width, int height) {
		if (~loading_percentage > -101) {
			process_loading(64);
		}

		if (GLManager.opengl_mode) {
			GLState.set_clipping(x, y, x + width, height + y);
		} else {
			Rasterizer2D.set_clipping(x, y, x + width, height + y);
		}

		int var6;
		int var7;
		if (~loading_percentage <= -101) {
			anInt410 = (int) (height * 2 / aFloat727);
			anInt930 = anInt3536 + -((int) (width / aFloat727));
			int var15 = -((int) (width / aFloat727)) + anInt3536;
			var6 = anInt2251 - (int) (height / aFloat727);
			anInt934 = anInt2251 + -((int) (height / aFloat727));
			int var8 = anInt2251 + (int) (height / aFloat727);
			var7 = (int) (width / aFloat727) + anInt3536;
			anInt817 = (int) (width * 2 / aFloat727);
			if (GLManager.opengl_mode) {
				if (worldmap_sprite == null || ~worldmap_sprite.width != ~width || ~worldmap_sprite.height != ~height) {
					worldmap_sprite = null;
					worldmap_sprite = new SoftwareSprite(width, height);
				}
				Rasterizer2D.initialise(worldmap_sprite.pixels, width, height);
				method523(width, 0, 0, var7, var6, 0, var8, height, var15);
				method938(width, 0, var7, var8, height, 0, 1, var15, var6);
				draw_labels(0, 0, var15, width, var8, var6, var7, height);
				GLManager.draw_pixels(worldmap_sprite.pixels, x, y, width, height);
				Rasterizer2D.colour_buffer = null;
			} else {
				method523(width + x, y, 0, var7, var6, x, var8, y - -height, var15);
				method938(x + width, x, var7, var8, height + y, y, 1, var15, var6);
				draw_labels(x, y, var15, x - -width, var8, var6, var7, height + y);
			}
			if (0 < WorldMap.anInt3704) {
				--anInt3611;
				if (-1 == ~anInt3611) {
					anInt3611 = 20;
					--WorldMap.anInt3704;
				}
			}
			if (GamePlayConfiguration.isFPSEnabled) {
				int var10 = -8 + y - -height;
				int var9 = -5 + x - -width;
				FontCache.b12_full.draw_text_right_anchor(RSString.joinRsStrings(new RSString[] { Class97.aClass16_1641, RSString.valueOf(ReflectionAntiCheat.anInt79) }), var9, var10, 16776960, -1);
				Runtime var11 = Runtime.getRuntime();
				int var12 = (int) ((var11.totalMemory() - var11.freeMemory()) / 1024L);
				int var13 = 16776960;
				var10 -= 15;
				if (~var12 < -65537) {
					var13 = 16711680;
				}
				FontCache.b12_full.draw_text_right_anchor(RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3294, RSString.valueOf(var12), Class55.aClass16_868 }), var9, var10, var13, -1);
				var10 -= 15;
			}

		} else {
			byte var5 = 20;
			var6 = x - -(width / 2);
			var7 = height / 2 + y - 18 + -var5;
			if (GLManager.opengl_mode) {
				GLShapes.fill_rectangle(x, y, width, height, 0);
				GLShapes.draw_rectangle(var6 - 152, var7, 304, 34, 9179409);
				GLShapes.draw_rectangle(var6 + -151, var7 + 1, 302, 32, 0);
				GLShapes.fill_rectangle(-150 + var6, var7 + 2, 3 * loading_percentage, 30, 9179409);
				GLShapes.fill_rectangle(-150 + var6 + loading_percentage * 3, var7 - -2, 300 + -(3 * loading_percentage), 30, 0);
			} else {
				Rasterizer2D.fill_rectangle(x, y, width, height, 0);
				Rasterizer2D.draw_rectangle(var6 + -152, var7, 304, 34, 9179409);
				Rasterizer2D.draw_rectangle(var6 + -151, 1 + var7, 302, 32, 0);
				Rasterizer2D.fill_rectangle(var6 + -150, var7 + 2, loading_percentage * 3, 30, 9179409);
				Rasterizer2D.fill_rectangle(3 * loading_percentage + -150 + var6, var7 - -2, -(loading_percentage * 3) + 300, 30, 0);
			}

			FontCache.b12_full.draw_text_centered(UpdateServerNode.aClass16_2329, var6, var5 + var7, 16777215, -1);
		}
	}

	public static void method253(int var0, int var1, int var2, int var3, int var4) {
		anInt3536 = anInt455 * var3 / var1;
		anInt2251 = anInt1460 * var2 / var4;
		anInt1150 = -1;
		anInt3362 = -1;
		method117();
	}

	public static void method503(byte var0, int var1) {
		anInt101 = var1;
		anInt3611 = 20;
		anInt3704 = 3;
	}

	public static void method523(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		int var9 = var3 - var8;
		int var11 = (-var5 + var0 << 16) / var9;
		int var10 = -var4 + var6;
		int var12 = (var7 + -var1 << 16) / var10;
		draw_surface(var1, 0, var6, var4, var3, var5, var8, var12, var11, var2, -12541);
	}

	public static void method938(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		int var9 = var2 - var7;
		int var10 = var3 - var8;
		int var11 = (-var1 + var0 << 16) / var9;
		int var12 = (-var5 + var4 << 16) / var10;
		draw_mapfunctions(var1, var3, var2, var12, var7, 0, 0, 127, var11, var8, var5);
	}

	public static void method848(int var0) {
		if (aFloat727 < aFloat3979) {
			aFloat727 = (float) (aFloat727 + aFloat727 / 30.0D);
			if (aFloat3979 < aFloat727) {
				aFloat727 = aFloat3979;
			}

			method117();
		} else if (aFloat3979 < aFloat727) {
			aFloat727 = (float) (aFloat727 - aFloat727 / 30.0D);
			if (aFloat3979 > aFloat727) {
				aFloat727 = aFloat3979;
			}

			method117();
		}

		if (~anInt1150 != 0 && -1 != anInt3362) {
			int var1 = -anInt3536 + anInt1150;
			if (2 > var1 || -3 > ~var1) {
				var1 >>= 4;
			}

			int var2 = -anInt2251 + anInt3362;
			if (var2 < 2 || var2 > 2) {
				var2 >>= 4;
			}

			anInt2251 -= -var2;
			anInt3536 += var1;
			if (0 == var1 && 0 == var2) {
				anInt1150 = -1;
				anInt3362 = -1;
			}

			method117();
		}
	}

	public static void method1479(int var0, byte var1) {
		anInt3362 = -1;
		if (-38 == ~var0) {
			aFloat3979 = 3.0F;
		} else if (50 != var0) {
			if (var0 == 75) {
				aFloat3979 = 6.0F;
			} else if (var0 != 100) {
				if (var0 == 200) {
					aFloat3979 = 16.0F;
				}
			} else {
				aFloat3979 = 8.0F;
			}
		} else {
			aFloat3979 = 4.0F;
		}
		anInt3362 = -1;
	}

	public static int method571(int var0) {
		if (aFloat3979 == 3.0D) {
			return 37;
		}
		if (aFloat3979 == 4.0D) {
			return 50;
		}
		if (6.0D != aFloat3979) {
			return 75;
		}
		if (aFloat3979 == 8.0D) {
			return 100;
		}
		return 200;
	}

	public static void method138(RSString var0, int var1) {
		reset(var1 ^ 93, false);
		set_area_by_filename(var0);
	}

	public static void method388(byte var0) {
		if (last_area_filename != null) {
			method138(last_area_filename, 0);
			last_area_filename = null;
		}
	}

	public static int method1258(byte var0) {
		anInt1780 = 0;
		return method251(-1);
	}

}
