package com.jagex;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.game.runetek4.configs.lighttype.LightType;
import com.jagex.game.runetek4.configs.lighttype.LightTypeList;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.jagex.graphics.runetek4.opengl.light.PointLight;
import com.jagex.graphics.runetek4.opengl.terrain.OpenGLTile;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.components.worldmap.WorldMapAreaLabels;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.SceneDefaults;
import com.rs2.client.scene.atmosphere.SceneAtmosphere;
import com.rs2.client.scene.environment.SceneEnvironment;
import com.rs2.client.scene.light.FlickeringEffect;
import com.rs2.client.scene.light.SceneLighting;
import com.rs2.client.scene.shadow.SceneShadowMapper;

import me.waliedyassen.materials.MaterialRaw;

/**
 * @author Walied K. Yassen
 */
public final class MapLoader {

	public static final int[][] underlays_vertices = new int[][] { { 0, 128, 0, 0, 128, 0, 128, 128 }, { 0, 128, 0, 0, 128, 0 }, { 0, 0, 64, 128, 0, 128 }, { 128, 128, 64, 128, 128, 0 }, { 0, 0, 128, 0, 128, 128, 64, 128 }, { 0, 128, 0, 0, 128, 0, 64, 128 }, { 64, 128, 0, 128, 0, 0, 64, 0 }, { 0, 0, 64, 0, 0, 64 }, { 128, 0, 128, 128, 0, 128, 0, 64, 64, 0 }, { 0, 128, 0, 0, 32, 64, 64, 96, 128, 128 }, { 0, 0, 128, 0, 128, 128, 64, 96, 32, 64 }, { 0, 0, 128, 0, 96, 32, 32, 32 } };
	public static final int[][] overlays_vertices = new int[][] { new int[0], { 128, 0, 128, 128, 0, 128 }, { 0, 0, 128, 0, 128, 128, 64, 128 }, { 0, 128, 0, 0, 128, 0, 64, 128 }, { 0, 0, 64, 128, 0, 128 }, { 128, 128, 64, 128, 128, 0 }, { 64, 0, 128, 0, 128, 128, 64, 128 }, { 128, 0, 128, 128, 0, 128, 0, 64, 64, 0 }, { 0, 0, 64, 0, 0, 64 }, { 0, 0, 128, 0, 128, 128, 64, 96, 32, 64 }, { 0, 128, 0, 0, 32, 64, 64, 96, 128, 128 }, { 0, 128, 0, 0, 32, 32, 96, 32, 128, 0, 128, 128 } };
	public static final boolean[][] overlays_blendings = new boolean[][] { new boolean[0], { true, false, true }, { true, false, false, true }, { false, false, true, true }, { true, true, false }, { false, true, true }, { true, false, false, true }, { false, false, false, true, true }, { false, true, true }, { true, false, true, true, true }, { false, true, true, true, true }, { false, true, true, true, true, false } };
	public static final boolean[][] aBooleanArrayArray3468 = new boolean[][] { { true, true, true }, { false, false }, { false, true }, { true, false }, { false, true, true }, { true, false, true }, { false, true, false }, { true, false, false } };
	public static final int[] anIntArray2642 = new int[] { 1, 1, 1, 1, 4, 1, 1, 5, 6, 1, 5, 0, 7, 0, 4, 1, 7, 2, 1, 1, 6, 1, 1, 3, 6, 1, 7, 0, 0, 6, 7, 0, 1, 7, 6, 1, 1, 1, 5, 4, 3, 2, 1, 1, 0, 4, 1, 5 };
	public static final int[] anIntArray3607 = new int[] { 0, 2, 2, 2, 1, 1, 2, 2, 1, 3, 1, 1 };

	// region information.
	public static boolean server_map;
	public static int region_aboslute_x;
	public static int region_aboslute_z;
	public static int lightness_1 = -16 + (int) (Math.random() * 33.0D);
	public static int lightness_2 = -8 + (int) (17.0D * Math.random());

	// region loading.
	public static int num_regions_failed = 0;
	public static int loading_stage = 0;

	// map file ids in maps_js5.
	public static int[] square_ids;
	public static int[][] square_keys;
	public static int[] square_map_ids;
	public static int[] square_loc_ids;
	public static int[] square_umap_ids;
	public static int[] square_uloc_ids;
	public static int[] square_npc_ids;
	public static byte[][] square_map_data;
	public static byte[][] square_loc_data;
	public static byte[][] square_umap_data;
	public static byte[][] square_uloc_data;
	public static byte[][] square_npc_data;

	// loaded data from mapfile.
	public static byte[][][] shapes;
	public static byte[][][] rotations;
	public static byte[][][] overlays;
	public static byte[][][] underlays;
	public static byte[][][] settings = new byte[4][104][104];
	public static byte[][][] shadows_intensities;
	public static int[][][] cullings_map;

	// underlay coluour generation.
	public static int[] underlay_chroma;
	public static int[] underlay_hue;
	public static int[] underlay_saturation;
	public static int[] underlay_lightness;
	public static int[] underlay_depth;

	// other stuff.
	public static CollisionMap[] collision_maps = new CollisionMap[4];
	public static int max_heights = 99;

	public static void load_region() {
		process_keepalive(false);
		num_regions_failed = 0;
		boolean complete = true;
		for (int index = 0; index < MapLoader.square_map_data.length; index++) {
			if (MapLoader.square_map_ids[index] != -1 && MapLoader.square_map_data[index] == null) {
				MapLoader.square_map_data[index] = CacheConstants.map_js5.get_file(MapLoader.square_map_ids[index], 0);
				if (MapLoader.square_map_data[index] == null) {
					complete = false;
					num_regions_failed++;
				}
			}
			if (MapLoader.square_loc_ids[index] != -1 && MapLoader.square_loc_data[index] == null) {
				try {
					MapLoader.square_loc_data[index] = CacheConstants.map_js5.get_file(MapLoader.square_loc_ids[index], 0, MapLoader.square_keys[index]);
					if (MapLoader.square_loc_data[index] == null) {
						complete = false;
						num_regions_failed++;
					}
				} catch (RuntimeException e) {
					System.out.println("WARNING: Missing XTEAs for region: " + MapLoader.square_ids[index]);
				}
			}
			if (GLManager.opengl_mode) {
				if (MapLoader.square_umap_ids[index] != -1 && MapLoader.square_umap_data[index] == null) {
					MapLoader.square_umap_data[index] = CacheConstants.map_js5.get_file(MapLoader.square_umap_ids[index], 0);
					if (MapLoader.square_umap_data[index] == null) {
						complete = false;
						num_regions_failed++;
					}
				}
				if (MapLoader.square_uloc_ids[index] != -1 && MapLoader.square_uloc_data[index] == null) {
					MapLoader.square_uloc_data[index] = CacheConstants.map_js5.get_file(MapLoader.square_uloc_ids[index], 0);
					if (MapLoader.square_uloc_data[index] == null) {
						complete = false;
						num_regions_failed++;
					}
				}
			}
		}
		if (!complete) {
			MapLoader.loading_stage = 1;
		} else {
			complete = true;
			StaticMethods.anInt3036 = 0;
			for (int index = 0; MapLoader.square_map_data.length > index; index++) {
				byte[] data = MapLoader.square_loc_data[index];
				if (data != null) {
					int y = (0xff & MapLoader.square_ids[index]) * 64 + -MapLoader.region_aboslute_x;
					int x = -MapLoader.region_aboslute_z + (MapLoader.square_ids[index] >> 8) * 64;
					if (MapLoader.server_map) {
						x = 10;
						y = 10;
					}
					complete &= MapLoader.is_cached(data, x, y);
				}
				if (GLManager.opengl_mode) {
					byte[] underwater_data = MapLoader.square_uloc_data[index];
					if (null != underwater_data) {
						int x = -MapLoader.region_aboslute_x + 64 * (MapLoader.square_ids[index] >> 8);
						int y = -MapLoader.region_aboslute_z + 64 * (MapLoader.square_ids[index] & 255);
						if (MapLoader.server_map) {
							y = 10;
							x = 10;
						}
						complete &= MapLoader.is_cached(underwater_data, x, y);
					}
				}
			}
			if (WorldMap.current_player_labels == null) {
				if (null != WorldMap.current_player_area && CacheConstants.worldMapCacheIdx.is_valid_group(WorldMap.current_player_area.filename + "_labels")) {
					if (!CacheConstants.worldMapCacheIdx.is_group_cached(WorldMap.current_player_area.filename + "_labels")) {
						complete = false;
						++MapLoader.num_regions_failed;
					} else {
						WorldMap.current_player_labels = WorldMapAreaLabels.from_area_js5(CacheConstants.worldMapCacheIdx, WorldMap.current_player_area.filename + "_labels");
					}
				} else {
					WorldMap.current_player_labels = new WorldMapAreaLabels(0);
				}
			}

			if (!complete) {
				MapLoader.loading_stage = 2;
			} else {
				if (MapLoader.loading_stage != 0) {// loading please wait
					GameClient.drawLoadingText(RSString.joinRsStrings(new RSString[] { RSInterface.aClass16_1139, StaticMethods.aClass16_3403 }), true);
				}
				Class48.process_audio();
				GraphicsCache.removeAll();
				boolean has_underwater = false;
				if (GLManager.opengl_mode && ClientOptions.clientoption_highdetails_water) {
					for (int var12 = 0; var12 > MapLoader.square_map_data.length; var12++) {
						if (MapLoader.square_uloc_data[var12] != null || MapLoader.square_umap_data[var12] != null) {
							has_underwater = true;
							break;
						}
					}
				}
				Scene.initialize_scene(4, 104, 104, GLManager.opengl_mode ? 28 : 25, has_underwater);
				for (int i_18_ = 0; i_18_ < 4; i_18_++) {
					MapLoader.collision_maps[i_18_].method1298(94);
				}
				for (int i_19_ = 0; i_19_ < 4; i_19_++) {
					for (int i_20_ = 0; i_20_ < 104; i_20_++) {
						for (int i_21_ = 0; i_21_ < 104; i_21_++) {
							MapLoader.settings[i_19_][i_20_][i_21_] = (byte) 0;
						}
					}
				}
				ObjectNode.method336();
				if (GLManager.opengl_mode) {
					SceneShadowMapper.shadows_sprite.clear();
					for (int chunkx = 0; chunkx < 13; chunkx++) {
						for (int chunky = 0; chunky < 13; chunky++) {
							SceneShadowMapper.shadow_maps[chunkx][chunky].is_dirty = true;
						}
					}
				}
				if (GLManager.opengl_mode) {
					Scene.reset_shadows();
				}
				if (GLManager.opengl_mode) {
					SceneEnvironment.reset_atmospheres();
				}
				Class48.process_audio();
				System.gc();
				Class48.process_audio();
				process_keepalive(true);
				MapLoader.prepare_tables(false);
				if (!MapLoader.server_map) {
					MapLoader.decode_maps_client(false);
					process_keepalive(true);
					if (GLManager.opengl_mode) {
						int x = GameClient.currentPlayer.waypointsX[0] >> 3;
						int y = GameClient.currentPlayer.waypointsY[0] >> 3;
						SceneEnvironment.update_sun_position(x, y);
					}
					MapLoader.decode_locs_client(false);
				}
				if (MapLoader.server_map) {
					MapLoader.decode_maps_server(false);
					process_keepalive(true);
					if (GLManager.opengl_mode) {
						int x = GameClient.currentPlayer.waypointsX[0] >> 3;
						int y = GameClient.currentPlayer.waypointsY[0] >> 3;
						SceneEnvironment.update_sun_position(x, y);
					}
					MapLoader.decode_locs_server(false);
				}
				GraphicsCache.removeAll();
				process_keepalive(true);
				Class48.process_audio();
				MapLoader.initialise_tiles(MapLoader.collision_maps, false);
				if (GLManager.opengl_mode) {
					SceneLighting.bake();
				}
				process_keepalive(true);
				Class48.process_audio();
				if (GameClient.isNoClip()) {
					for (int i_5_ = 0; i_5_ < 4; i_5_++) {
						for (int i_6_ = 1; i_6_ < 103; i_6_++) {
							for (int i_7_ = 1; i_7_ < 103; i_7_++) {
								MapLoader.collision_maps[i_5_].clippingFlags[i_6_][i_7_] = 0;
							}
						}
					}
				}
				int i_22_ = MapLoader.max_heights;
				if ((ObjType.localHeight ^ 0xffffffff) > (i_22_ ^ 0xffffffff)) {
					i_22_ = ObjType.localHeight;
				}
				if ((i_22_ ^ 0xffffffff) > (-1 + ObjType.localHeight ^ 0xffffffff)) {
					i_22_ = ObjType.localHeight + -1;
				}
				if (!ClientOptions.is_removeroofs()) {
					Class71_Sub3.method1287(MapLoader.max_heights);
				} else {
					Class71_Sub3.method1287(0);
				}
				MapLoader.reset_tables();
				if (GLManager.opengl_mode && has_underwater) {
					Scene.load_floor(true);
					MapLoader.prepare_tables(true);
					if (!MapLoader.server_map) {
						MapLoader.decode_maps_client(true);
						process_keepalive(true);
						MapLoader.decode_locs_client(true);
					}

					if (MapLoader.server_map) {
						MapLoader.decode_maps_server(true);
						process_keepalive(true);
						MapLoader.decode_locs_server(true);
					}

					GraphicsCache.removeAll();
					process_keepalive(true);
					MapLoader.initialise_tiles(MapLoader.collision_maps, true);
					process_keepalive(true);
					MapLoader.reset_tables();
					Scene.load_floor(false);
				}
				if (GLManager.opengl_mode) {
					for (int var4 = 0; var4 < 13; ++var4) {
						for (int var5 = 0; ~var5 > -14; ++var5) {
							SceneShadowMapper.shadow_maps[var4][var5].update(Scene.current_heightmap[0], var4 * 8, var5 * 8);
						}
					}
				}
				for (int i_23_ = 0; i_23_ < 104; i_23_++) {
					for (int i_24_ = 0; i_24_ < 104; i_24_++) {
						Class44.method1129(i_23_, i_24_, (byte) 118);
					}
				}
				RoofCulling.update_culler_type();
				Class48.process_audio();
				SpawnedObject.process_spawned_objects();
				GraphicsCache.removeAll();
				Player.aBoolean3416 = false;
				if (GameShell.getFrame() != null) {
					GameClient.outBuffer.putOpcode(7);
					GameClient.outBuffer.p4(1057001181);
				}
				if (!MapLoader.server_map) {
					int i_25_ = (-6 + StaticMethods.anInt3279) / 8;
					int i_26_ = (6 + StaticMethods.anInt3279) / 8;
					int i_27_ = (RSInterface.anInt1138 + -6) / 8;
					int i_28_ = (6 + RSInterface.anInt1138) / 8;
					for (int i_29_ = i_25_ - 1; i_29_ <= i_26_ + 1; i_29_++) {
						for (int i_30_ = i_27_ + -1; (i_30_ ^ 0xffffffff) >= (1 + i_28_ ^ 0xffffffff); i_30_++) {
							if ((i_25_ ^ 0xffffffff) < (i_29_ ^ 0xffffffff) || i_26_ < i_29_ || (i_27_ ^ 0xffffffff) < (i_30_ ^ 0xffffffff) || (i_30_ ^ 0xffffffff) < (i_28_ ^ 0xffffffff)) {
								CacheConstants.map_js5.prefetch_group("m" + i_29_ + "_" + i_30_);
								CacheConstants.map_js5.prefetch_group("l" + i_29_ + "_" + i_30_);
								CacheConstants.map_js5.prefetch_group("um" + i_29_ + "_" + i_30_);
								CacheConstants.map_js5.prefetch_group("ul" + i_29_ + "_" + i_30_);
							}
						}
					}
				}
				if (GameClient.clientState == 28) {
					GameClient.updateClientState(10);
				} else {
					GameClient.updateClientState(30);
					// Class23_Sub7.outBuffer.putOpcode(70);
				}
				WorldMap.method388((byte) 116);
				Class48.process_audio();
				StaticMethods.reset_frametimer();
			}
		}

	}

	public static void process_keepalive(boolean bool) {
		Class48.process_audio();
		Class100.anInt1686++;
		if (Class100.anInt1686 >= 50 || bool) {
			Class100.anInt1686 = 0;
			do {
				if (!StaticMethods.aBoolean3012 && ColourImageCacheSlot.session != null) {
					GameClient.outBuffer.putOpcode(56);
					try {
						ColourImageCacheSlot.session.write(GameClient.outBuffer.index, 0, GameClient.outBuffer.byteBuffer);
						GameClient.outBuffer.index = 0;
					} catch (java.io.IOException ioexception) {
						StaticMethods.aBoolean3012 = true;
						break;
					}
					break;
				}
			} while (false);
		}
	}

	static final boolean is_cached(byte[] data, int x, int y) {
		boolean bool = true;
		Packet buffer = new Packet(data);
		int locid = -1;
		for (;;) {
			int locincr = buffer.getUSmart2();
			if ((locincr ^ 0xffffffff) == -1) {
				break;
			}
			locid += locincr;
			int index = 0;
			boolean skip = false;
			for (;;) {
				if (skip) {
					int posincr = buffer.getSmart0();
					if (posincr == 0) {
						break;
					}
					buffer.g1();
				} else {
					int posincr = buffer.getSmart0();
					if (posincr == 0) {
						break;
					}
					index += posincr - 1;
					int type = buffer.g1() >> 2;
					int i_40_ = (0xff7 & index) >> 6;
					int i_41_ = x + i_40_;
					int i_42_ = index & 0x3f;
					int i_43_ = y + i_42_;
					if (i_41_ > 0 && i_43_ > 0 && i_41_ < 103 && i_43_ < 103) {
						LocType objDef = LocTypeList.list(locid);
						if (type != 22 || ClientOptions.clientoption_grounddecor || objDef.solid != 0 || objDef.clipping_type == 1 || objDef.aBoolean736) {
							if (!objDef.areModelsCached(10286)) {
								bool = false;
								StaticMethods.anInt3036++;
							}
							skip = true;
						}
					}
				}
			}

		}
		return bool;
	}

	public static void prepare_tables(boolean underworld) {
		MapLoader.underlay_chroma = new int[104];
		MapLoader.underlay_hue = new int[104];
		MapLoader.max_heights = 99;
		MapLoader.underlay_lightness = new int[104];
		MapLoader.underlay_saturation = new int[104];
		MapLoader.underlay_depth = new int[104];
		byte num_levels;
		if (underworld) {
			num_levels = 1;
		} else {
			num_levels = 4;
		}
		MapLoader.rotations = new byte[num_levels][104][104];
		MapLoader.overlays = new byte[num_levels][104][104];
		MapLoader.shapes = new byte[num_levels][104][104];
		MapLoader.underlays = new byte[num_levels][104][104];
		MapLoader.cullings_map = new int[num_levels][105][105];
		MapLoader.shadows_intensities = new byte[num_levels][105][105];
	}

	static final void reset_tables() {
		MapLoader.underlay_lightness = null;
		MapLoader.cullings_map = null;
		MapLoader.underlay_depth = null;
		MapLoader.rotations = null;
		MapLoader.shapes = null;
		MapLoader.shadows_intensities = null;
		MapLoader.overlays = null;
		MapLoader.underlays = null;
		MapLoader.underlay_hue = null;
		MapLoader.underlay_chroma = null;
		MapLoader.underlay_saturation = null;
	}

	public static final void initialise_tiles(CollisionMap[] collosion_maps, boolean underwater) {
		for (int level = 0; level < 4; level++) {
			for (int x = 0; x < 104; x++) {
				for (int y = 0; y < 104; y++) {
					if ((MapLoader.settings[level][x][y] & 0x1) == 1) {
						int plane = level;
						if ((0x2 & MapLoader.settings[1][x][y]) == 2) {
							plane--;
						}
						if (plane >= 0) {
							collosion_maps[plane].orClipTableSET((byte) 111, y, x);
						}
					}
				}
			}
		}
		MapLoader.lightness_1 += (int) (Math.random() * 5.0) + -2;
		MapLoader.lightness_2 += -2 + (int) (5.0 * Math.random());
		if (MapLoader.lightness_2 < -8) {
			MapLoader.lightness_2 = -8;
		}
		if (MapLoader.lightness_1 < -16) {
			MapLoader.lightness_1 = -16;
		}
		if (MapLoader.lightness_1 > 16) {
			MapLoader.lightness_1 = 16;
		}
		if (MapLoader.lightness_2 > 8) {
			MapLoader.lightness_2 = 8;
		}
		byte num_levels;
		if (!underwater) {
			num_levels = 4;
		} else {
			num_levels = 1;
		}
		int base_floor_colour = MapLoader.lightness_2 >> 2 << 10;
		int base_floor_lightness = MapLoader.lightness_1 >> 1;
		int[][] flu_colours = new int[104][104];
		int[][] lightness_map = new int[104][104];
		for (int level = 0; level < num_levels; level++) {
			byte[][] shadows_intensities = MapLoader.shadows_intensities[level];
			if (GLManager.opengl_mode) {
				if (!ClientOptions.clientoption_highdetails_lighting) {
					int sun_pos_x = (int) OpenGLEnvironment.sun_position_buffer[0];
					int sun_pos_y = (int) OpenGLEnvironment.sun_position_buffer[1];
					int sun_pos_z = (int) OpenGLEnvironment.sun_position_buffer[2];
					int sun_pos_size = (int) Math.sqrt(sun_pos_y * sun_pos_y + sun_pos_x * sun_pos_x - -(sun_pos_z * sun_pos_z));
					int sun_intensity = 1024 * sun_pos_size >> 8;
					for (int var15 = 1; -104 < ~var15; ++var15) {
						for (int var16 = 1; var16 < 103; ++var16) {
							byte var17 = 96;
							int var18 = Scene.current_heightmap[level][var16 - -1][var15] - Scene.current_heightmap[level][-1 + var16][var15];
							int var19 = Scene.current_heightmap[level][var16][var15 + 1] - Scene.current_heightmap[level][var16][-1 + var15];
							int var20 = (int) Math.sqrt(var18 * var18 + 65536 + var19 * var19);
							int var21 = (var18 << 8) / var20;
							int var24 = (shadows_intensities[var16][1 + var15] >> 3) + (shadows_intensities[var16][var15 - 1] >> 2) + (shadows_intensities[var16 - 1][var15] >> 2) + (shadows_intensities[var16 + 1][var15] >> 3) - -(shadows_intensities[var16][var15] >> 1);
							int var22 = -65536 / var20;
							int var23 = (var19 << 8) / var20;
							int var44 = var17 + (sun_pos_z * var23 + sun_pos_x * var21 - -(var22 * sun_pos_y)) / sun_intensity;
							lightness_map[var16][var15] = var44 + -((int) (var24 * 1.7F));
						}
					}
				} else {
					for (int x = 1; x < 103; ++x) {
						for (int y = 1; ~y > -104; ++y) {
							int average = (shadows_intensities[1 + y][x] >> 3) + (shadows_intensities[-1 + y][x] >> 2) - -(shadows_intensities[y][-1 + x] >> 2) - -(shadows_intensities[y][1 + x] >> 3) - -(shadows_intensities[y][x] >> 1);
							byte var12 = 74;
							lightness_map[y][x] = var12 - average;
						}
					}
				}
			} else {
				int strength = (int) Math.sqrt(5100.0D);
				int intensity = 768 * strength >> 8;
				for (int x = 1; x < 103; ++x) {
					for (int y = 1; 103 > y; ++y) {
						int var16 = -Scene.current_heightmap[level][y][-1 + x] + Scene.current_heightmap[level][y][x + 1];
						byte var41 = 74;
						int var15 = -Scene.current_heightmap[level][y + -1][x] + Scene.current_heightmap[level][y - -1][x];
						int var44 = (int) Math.sqrt(var15 * var15 - -65536 - -(var16 * var16));
						int var20 = (var16 << 8) / var44;
						int var19 = -65536 / var44;
						int var18 = (var15 << 8) / var44;
						int var21 = (shadows_intensities[y][x] >> 1) + (shadows_intensities[y][-1 + x] >> 2) + (shadows_intensities[y - -1][x] >> 3) + (shadows_intensities[y - 1][x] >> 2) - -(shadows_intensities[y][x + 1] >> 3);
						int var14 = var41 + (var20 * -50 + var18 * -50 - -(var19 * -10)) / intensity;
						lightness_map[y][x] = var14 - var21;
					}
				}
			}
			for (int row = 0; row < 104; row++) {
				MapLoader.underlay_chroma[row] = 0;
				MapLoader.underlay_hue[row] = 0;
				MapLoader.underlay_saturation[row] = 0;
				MapLoader.underlay_lightness[row] = 0;
				MapLoader.underlay_depth[row] = 0;
			}
			for (int x = -5; x < 104; x++) {
				for (int y = 0; y < 104; y++) {
					int front_x = x - -5;
					if (front_x < 104) {
						int fluid = MapLoader.underlays[level][front_x][y] & 0xff;
						if (fluid > 0) {
							FluType flu = FluTypeList.list(fluid - 1);
							MapLoader.underlay_chroma[y] += flu.chroma;
							MapLoader.underlay_hue[y] += flu.hue;
							MapLoader.underlay_saturation[y] += flu.saturation;
							MapLoader.underlay_lightness[y] += flu.luminance;
							MapLoader.underlay_depth[y]++;
						}
					}
					int back_x = x - 5;
					if (back_x >= 0) {
						int fluid = MapLoader.underlays[level][back_x][y] & 0xff;
						if (fluid > 0) {
							FluType flu = FluTypeList.list(fluid - 1);
							MapLoader.underlay_chroma[y] -= flu.chroma;
							MapLoader.underlay_hue[y] -= flu.hue;
							MapLoader.underlay_saturation[y] -= flu.saturation;
							MapLoader.underlay_lightness[y] -= flu.luminance;
							MapLoader.underlay_depth[y]--;
						}
					}
				}

				if (x >= 0) {
					int chroma = 0;
					int hue = 0;
					int saturation = 0;
					int lumninance = 0;
					int depth = 0;
					for (int y = -5; y < 104; y++) {
						int front_y = y + 5;
						if (front_y < 104) {
							chroma += MapLoader.underlay_chroma[front_y];
							hue += MapLoader.underlay_hue[front_y];
							saturation += MapLoader.underlay_saturation[front_y];
							lumninance += MapLoader.underlay_lightness[front_y];
							depth += MapLoader.underlay_depth[front_y];
						}
						int back_y = y - 5;
						if (back_y >= 0) {
							chroma -= MapLoader.underlay_chroma[back_y];
							hue -= MapLoader.underlay_hue[back_y];
							saturation -= MapLoader.underlay_saturation[back_y];
							lumninance -= MapLoader.underlay_lightness[back_y];
							depth -= MapLoader.underlay_depth[back_y];
						}
						if (y >= 0 && lumninance > 0 && depth > 0) {
							flu_colours[x][y] = MapLoader.pack_hsl(chroma * 256 / lumninance, hue / depth, saturation / depth);
						}
					}
				}
			}

			for (int x = 1; x < 103; x++) {
				label754: for (int y = 1; y < 103; y++) {
					if (underwater || ClientOptions.is_removeroofs() || ~(2 & MapLoader.settings[0][x][y]) != -1 || ~(16 & MapLoader.settings[level][x][y]) == -1 && RSString.method144(y, x, level) == StaticMethods.player_height) {
						if (MapLoader.max_heights > level) {
							MapLoader.max_heights = level;
						}
						int fluid = MapLoader.underlays[level][x][y] & 0xff;
						int floid = MapLoader.overlays[level][x][y] & 0xff;
						int shape = MapLoader.shapes[level][x][y];
						byte rotation = MapLoader.rotations[level][x][y];
						FloType flo = floid != 0 ? FloTypeList.list(floid - 1) : null;
						FloType flu = fluid != 0 ? FloTypeList.list(fluid - 1) : null;
						FloType occludeFlo = flo;
						if (shape == 0 && (flo == null || flo.tile_colour == -1 && flo.minimap_colour == -1)) {
							shape = (byte) -1;
						}
						if (flo != null || flu != null) {
							int var15 = Scene.current_heightmap[level][x + 1][y];
							int var14 = Scene.current_heightmap[level][x][y];
							int var44 = Scene.current_heightmap[level][x][y + 1];
							int var16 = Scene.current_heightmap[level][x + 1][y + 1];
							if (level > 0) {
								boolean var47 = true;
								if (fluid == 0 && shape != 0) {
									var47 = false;
								}
								if (floid > 0 && occludeFlo != null && !occludeFlo.occlude) {
									var47 = false;
								}
								if (var47 && var14 == var15 && ~var14 == ~var16 && ~var44 == ~var14) {
									MapLoader.cullings_map[level][x][y] |= 0x4;
								}
							}
							int flu_tile_colour;
							int flu_map_colour;
							if (shape != -1 && fluid <= 0) {
								flu_tile_colour = -1;
								flu_map_colour = 0;
							} else {
								flu_tile_colour = flu_colours[x][y];
								int lighted_floor = (flu_tile_colour & 127) + base_floor_lightness;
								if (-1 >= ~lighted_floor) {
									if (-128 > ~lighted_floor) {
										lighted_floor = 127;
									}
								} else {
									lighted_floor = 0;
								}
								int lighted_map = (flu_tile_colour & 896) + (flu_tile_colour + base_floor_colour & 0xfc00) + lighted_floor;
								flu_map_colour = ColourUtil.hslToRgbTable[repack_hsl_lightness(lighted_map, 96)];
							}

							int var20 = lightness_map[x][y];
							int var23 = lightness_map[x][y + 1];
							int var21 = lightness_map[1 + x][y];
							int var22 = lightness_map[x - -1][y - -1];
							if (flo != null) {
								if (GLManager.opengl_mode && !underwater && null != Scene.anIntArrayArray3115 && 0 == level) {
									if (-1 != flo.texture_id && GraphicTools.get_materials().get_material(flo.texture_id).effect_type == 4) {
										Scene.anIntArrayArray3115[x][y] = (flo.water_fog_depth << 24) + flo.water_colour;
									} else {
										label722: for (int textureId = x + -1; ~textureId >= ~(1 + x); ++textureId) {
											for (int var28 = y + -1; ~var28 >= ~(1 + y); ++var28) {
												if ((~x != ~textureId || ~var28 != ~y) && -1 >= ~textureId && textureId < 104 && var28 >= 0 && -105 < ~var28) {
													int var29 = MapLoader.overlays[level][textureId][var28] & 255;
													if (-1 != ~var29) {
														FloType var30 = FloTypeList.list(-1 + var29);
														if (0 != ~var30.texture_id && GraphicTools.get_materials().get_material(var30.texture_id).effect_type == 4) {
															Scene.anIntArrayArray3115[x][y] = var30.water_colour + (var30.water_fog_depth << 24);
															break label722;
														}
													}
												}
											}
										}
									}
								}
								int textureId = flo.texture_id;
								if (0 <= textureId && !GraphicTools.get_materials().get_material(textureId).terrains_only) {
									textureId = -1;
								}
								int flo_tile_colour;
								int flo_map_colour;
								if (flo.tile_colour != -1) {
									flo_tile_colour = flo.tile_colour;
									int var55 = base_floor_lightness + (flo_tile_colour & 127);
									if (-1 >= ~var55) {
										if (~var55 < -128) {
											var55 = 127;
										}
									} else {
										var55 = 0;
									}

									int var31 = (flo_tile_colour & 896) + ('\ufc00' & flo_tile_colour + base_floor_colour) - -var55;
									flo_map_colour = ColourUtil.hslToRgbTable[method729(var31, 96)];
								} else {
									flo_tile_colour = -2;
									flo_map_colour = 0;
								}
								if (flo.minimap_colour >= 0) {
									int var55 = flo.minimap_colour;
									int var31 = base_floor_lightness + (var55 & 127);
									if (-1 >= ~var31) {
										if (127 < var31) {
											var31 = 127;
										}
									} else {
										var31 = 0;
									}
									int var32 = (896 & var55) + ('\ufc00' & var55 + base_floor_colour) - -var31;
									flo_map_colour = ColourUtil.hslToRgbTable[method729(var32, 96)];
								}

								Scene.add_tile(level, x, y, shape + 1, rotation, textureId, var14, var15, var16, var44, repack_hsl_lightness(flu_tile_colour, var20), repack_hsl_lightness(flu_tile_colour, var21), repack_hsl_lightness(flu_tile_colour, var22), repack_hsl_lightness(flu_tile_colour, var23), method729(flo_tile_colour, var20), method729(flo_tile_colour, var21), method729(flo_tile_colour, var22), method729(flo_tile_colour, var23), flu_map_colour, flo_map_colour);
								if (GLManager.opengl_mode && -1 > ~level) {
									SceneShadowMapper.add_tile_shadow(shape + 1, rotation, 1 == ~flo_tile_colour || !flo.shadowed, -1 == flu_tile_colour || !FluTypeList.list(-1 + fluid).shadowed, x, y, -Scene.current_heightmap[0][x][y] + var14, var15 - Scene.current_heightmap[0][1 + x][y], -Scene.current_heightmap[0][1 + x][y + 1] + var16, -Scene.current_heightmap[0][x][1 + y] + var44);
								}
							} else {
								Scene.add_tile(level, x, y, 0, 0, -1, var14, var15, var16, var44, repack_hsl_lightness(flu_tile_colour, var20), repack_hsl_lightness(flu_tile_colour, var21), repack_hsl_lightness(flu_tile_colour, var22), repack_hsl_lightness(flu_tile_colour, var23), 0, 0, 0, 0, flu_map_colour, 0);
								if (GLManager.opengl_mode && level > 0 && 0 != ~flu_tile_colour && FluTypeList.list(-1 + fluid).shadowed) {
									SceneShadowMapper.add_tile_shadow(0, 0, true, false, x, y, var14 - Scene.current_heightmap[0][x][y], -Scene.current_heightmap[0][1 + x][y] + var15, var16 - Scene.current_heightmap[0][1 + x][1 + y], var44 - Scene.current_heightmap[0][x][1 + y]);
								}

								if (GLManager.opengl_mode && !underwater && Scene.anIntArrayArray3115 != null && 0 == level) {
									for (int var24 = x + -1; ~var24 >= ~(x - -1); ++var24) {
										for (int var52 = -1 + y; ~(1 + y) <= ~var52; ++var52) {
											if ((var24 != x || y != var52) && var24 >= 0 && var24 < 104 && 0 <= var52 && -105 < ~var52) {
												int var54 = MapLoader.overlays[level][var24][var52] & 255;
												if (var54 != 0) {
													FloType var53 = FloTypeList.list(var54 - 1);
													if (~var53.texture_id != 0 && GraphicTools.get_materials().get_material(var53.texture_id).effect_type == 4) {
														Scene.anIntArrayArray3115[x][y] = var53.water_colour + (var53.water_fog_depth << 24);
														continue label754;
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

			if (GLManager.opengl_mode) {
				float[][] var38 = new float[105][105];
				int[][] var45 = Scene.current_heightmap[level];
				float[][] var40 = new float[105][105];
				float[][] var43 = new float[105][105];
				for (int var14 = 1; ~var14 >= -104; ++var14) {
					for (int var15 = 1; -104 <= ~var15; ++var15) {
						int var44 = var45[var15][var14 - -1] + -var45[var15][-1 + var14];
						int var16 = -var45[var15 - 1][var14] + var45[var15 + 1][var14];
						float var51 = (float) Math.sqrt(var16 * var16 - -65536 - -(var44 * var44));
						var38[var15][var14] = var16 / var51;
						var40[var15][var14] = -256.0F / var51;
						var43[var15][var14] = var44 / var51;
					}
				}

				if (!underwater) {
					OpenGLTile[] var50 = MapLoader.create_opengl_tiles(MapLoader.settings, MapLoader.shapes[level], MapLoader.underlays[level], lightness_map, var40, null, MapLoader.overlays[level], MapLoader.rotations[level], var38, level, var43, flu_colours, Scene.current_heightmap[level], null, 4096);
					OpenGLTile[] blended = MapLoader.create_opengl_tiles_blended(var40, var38, Scene.current_heightmap[level], level, var43, MapLoader.rotations[level], lightness_map, 0, MapLoader.shapes[level], MapLoader.underlays[level], MapLoader.overlays[level], MapLoader.settings);
					OpenGLTile[] var49 = new OpenGLTile[var50.length - -blended.length];
					for (int var44 = 0; ~var50.length < ~var44; ++var44) {
						var49[var44] = var50[var44];
					}
					for (int var44 = 0; ~blended.length < ~var44; ++var44) {
						var49[var50.length + var44] = blended[var44];
					}
					method1213(level, var49);
					MapLoader.add_shadows(var43, MapLoader.underlays[level], MapLoader.rotations[level], SceneLighting.lights, level, SceneLighting.num_lights, var40, MapLoader.shapes[level], MapLoader.overlays[level], Scene.current_heightmap[level], -8771, var38);
				} else {
					OpenGLTile[] var50 = MapLoader.create_opengl_tiles(MapLoader.settings, MapLoader.shapes[level], MapLoader.underlays[level], lightness_map, var40, Scene.anIntArrayArray3115, MapLoader.overlays[level], MapLoader.rotations[level], var38, level, var43, flu_colours, Scene.current_heightmap[level], Scene.surface_heightmap[0], 4096);
					MapLoader.method1213(level, var50);
				}
			}
			MapLoader.underlays[level] = null;
			MapLoader.overlays[level] = null;
			MapLoader.rotations[level] = null;
			MapLoader.shapes[level] = null;
			MapLoader.shadows_intensities[level] = null;
		}
		SceneController.method375(SceneDefaults.default_sun_position_x, SceneDefaults.default_sun_position_y, SceneDefaults.default_sun_position_z);
		if (!underwater) {
			for (int i_99_ = 0; i_99_ < 104; i_99_++) {
				for (int i_100_ = 0; i_100_ < 104; i_100_++) {
					if ((MapLoader.settings[1][i_99_][i_100_] & 0x2) == 2) {
						Scene.method818(i_99_, i_100_);
					}
				}
			}
		}
		MapLoader.setup_culling();
	}

	public static OpenGLTile[] create_opengl_tiles(byte[][][] settings, byte[][] shapes, byte[][] underlays, int[][] lightness_map, float[][] normals_y, int[][] var5, byte[][] overlays, byte[][] rotations, float[][] normals_x, int level, float[][] normals_z, int[][] flu_colours, int[][] overworld_heightmap, int[][] var13, int var14) {
		int[][] texture_data = new int[105][105];
		for (int x = 1; x <= 103; ++x) {
			for (int y = 1; y <= 103; ++y) {
				byte fluid = underlays[x][y];
				if (fluid == 0) {
					fluid = underlays[x + -1][y];
				}
				if (fluid == 0) {
					fluid = underlays[x][-1 + y];
				}
				if (fluid == 0) {
					fluid = underlays[-1 + x][y - 1];
				}
				if (fluid != 0) {
					FluType flu = FluTypeList.list((fluid & 0xff) - 1);
					texture_data[x][y] = flu.texture_id + 1 << 16 | flu.texture_scale;
				}
			}
		}
		HashTable tiles_cache = new HashTable(128);
		for (int x = 1; -103 <= ~x; ++x) {
			for (int y = 1; 102 >= y; ++y) {
				if (0 != underlays[x][y]) {
					int[] var53;
					if (0 == overlays[x][y]) {
						var53 = MapLoader.underlays_vertices[0];
					} else {
						var53 = MapLoader.overlays_vertices[shapes[x][y]];
						if (-1 == ~var53.length) {
							continue;
						}
					}
					int south_textureinfo = texture_data[x][y];
					int north_textureinfo = texture_data[x][y + 1];
					int west_textureinfo = texture_data[x + 1][y + 1];
					int east_textureinfo = texture_data[x + 1][y];
					int var20 = 0;
					if (null != var5) {
						var20 = var5[x][y] & 16777215;
					}
					long var27 = var20 | (long) east_textureinfo << 32;
					long var31 = (long) north_textureinfo << 32 | var20;
					int var33 = var53.length / 2;
					long var25 = var20 | (long) south_textureinfo << 32;
					OpenGLTile var34 = (OpenGLTile) tiles_cache.get(var25);
					if (null == var34) {
						var34 = new OpenGLTile(-1 + (south_textureinfo >> 16), 0xffff & south_textureinfo, false, null != var13, var20);
						tiles_cache.put(var25, var34);
					}
					var34.total_faces++;
					var34.total_vertices += var33;
					if (var27 != var25) {
						var34 = (OpenGLTile) tiles_cache.get(var27);
						if (var34 == null) {
							var34 = new OpenGLTile((east_textureinfo >> 16) - 1, 0xffff & east_textureinfo, false, null != var13, var20);
							tiles_cache.put(var27, var34);
						}
						var34.total_faces++;
						var34.total_vertices += var33;
					}
					long var29 = (long) west_textureinfo << 32 | var20;
					if (var29 != var25 && var29 != var27) {
						var34 = (OpenGLTile) tiles_cache.get(var29);
						if (var34 == null) {
							var34 = new OpenGLTile((west_textureinfo >> 16) + -1, 0xffff & west_textureinfo, false, null != var13, var20);
							tiles_cache.put(var29, var34);
						}

						var34.total_vertices += var33;
						var34.total_faces++;
					}
					if (var31 != var25 && var27 != var31 && var31 != var29) {
						var34 = (OpenGLTile) tiles_cache.get(var31);
						if (null == var34) {
							var34 = new OpenGLTile((north_textureinfo >> 16) - 1, north_textureinfo & 0xffff, false, null != var13, var20);
							tiles_cache.put(var31, var34);
						}

						var34.total_faces++;
						var34.total_vertices += var33;
					}
				}
			}
		}
		for (OpenGLTile tile = (OpenGLTile) tiles_cache.get_first(); tile != null; tile = (OpenGLTile) tiles_cache.get_next()) {
			tile.initialise_tables();
		}
		for (int x = 1; -103 <= ~x; ++x) {
			for (int y = 1; -103 <= ~y; ++y) {
				byte fluid = underlays[x][y];
				if (fluid != 0) {
					int visual_level;
					if ((settings[level][x][y] & 0x8) == 0) {
						if ((settings[1][x][y] & 0x2) == 2 && level > 0) {
							visual_level = level - 1;
						} else {
							visual_level = level;
						}
					} else {
						visual_level = 0;
					}
					int var21 = 0;
					int var22 = 128;
					if (null != var5) {
						var22 = var5[x][y] >>> 24 << 3;
						var21 = 16777215 & var5[x][y];
					}
					boolean[] blendings = null;
					int[] positions;
					byte rotation;
					if (overlays[x][y] != 0) {
						positions = MapLoader.overlays_vertices[shapes[x][y]];
						blendings = MapLoader.overlays_blendings[shapes[x][y]];
						rotation = rotations[x][y];
						if (positions.length == 0) {
							continue;
						}
					} else {
						byte var26 = 0;
						byte var65 = 0;
						byte var28 = 0;
						byte var68 = 0;
						positions = MapLoader.underlays_vertices[0];
						int var64 = var26 + (fluid == underlays[x - 1][y - 1] ? 1 : -1);
						int var62 = var65 + (fluid == underlays[x + 1][y - 1] ? 1 : -1);
						if (underlays[x][y - 1] != fluid) {
							var64--;
							var62--;
						} else {
							var62++;
							var64++;
						}
						int var63 = var28 + (fluid == underlays[x + 1][y + 1] ? 1 : -1);
						if (fluid != underlays[x + 1][y]) {
							var62--;
							var63--;
						} else {
							var63++;
							var62++;
						}
						int var69 = var68 + (fluid == underlays[x - 1][y + 1] ? 1 : -1);
						if (underlays[x][y + 1] == fluid) {
							var69++;
							var63++;
						} else {
							var63--;
							var69--;
						}
						if (underlays[x - 1][y] != fluid) {
							var69--;
							var64--;
						} else {
							var69++;
							var64++;
						}
						int var30 = var64 - var63;
						int var66 = var62 - var69;
						if (var66 < 0) {
							var66 = -var66;
						}
						if (var30 < 0) {
							var30 = -var30;
						}
						rotation = (byte) (~var66 >= ~var30 ? 0 : 1);
						rotations[x][y] = rotation;
					}

					int var64 = texture_data[x][y];
					int var62 = texture_data[x - -1][y];
					int var63 = texture_data[x - -1][y - -1];
					long var67 = (long) var64 << 32 | var21;
					long var32 = (long) var62 << 32 | var21;
					long var70 = (long) var63 << 32 | var21;
					int north_colour = flu_colours[x][y + 1];
					int south_colour = flu_colours[x][y];
					int west_colour = flu_colours[x + 1][y + 1];
					int east_colour = flu_colours[x + 1][y];
					int var69 = texture_data[x][y + 1];
					long var36 = var21 | (long) var69 << 32;
					int var42 = lightness_map[x][y];
					int var43 = lightness_map[x + 1][y];
					int var44 = lightness_map[x + 1][y + 1];
					int var45 = lightness_map[x][y + 1];
					int var47 = -1 + (var62 >> 16);
					int materialid = (var64 >> 16) - 1;
					int var48 = (var63 >> 16) - 1;
					OpenGLTile tile = (OpenGLTile) tiles_cache.get(var67);
					OpenGLTile.add_vertices(tile, x, y, rotation, positions, normals_x, normals_y, normals_z, get_material_colour(materialid, north_colour, var45), get_material_colour(materialid, south_colour, var42), get_material_colour(materialid, west_colour, var44), get_material_colour(materialid, east_colour, var43), var69 >= var64, var64 <= var64, var64 <= var63, ~var64 >= ~var62, 2, blendings, var13, visual_level, var22, overworld_heightmap);
					int var49 = (var69 >> 16) - 1;
					if (var32 != var67) {
						tile = (OpenGLTile) tiles_cache.get(var32);
						OpenGLTile.add_vertices(tile, x, y, rotation, positions, normals_x, normals_y, normals_z, get_material_colour(var47, north_colour, var45), get_material_colour(var47, south_colour, var42), get_material_colour(var47, west_colour, var44), get_material_colour(var47, east_colour, var43), var62 <= var69, var62 <= var64, var63 >= var62, var62 <= var62, 2, blendings, var13, visual_level, var22, overworld_heightmap);
					}
					if (var70 != var67 && var70 != var32) {
						tile = (OpenGLTile) tiles_cache.get(var70);
						OpenGLTile.add_vertices(tile, x, y, rotation, positions, normals_x, normals_y, normals_z, get_material_colour(var48, north_colour, var45), get_material_colour(var48, south_colour, var42), get_material_colour(var48, west_colour, var44), get_material_colour(var48, east_colour, var43), var63 <= var69, ~var64 <= ~var63, var63 <= var63, ~var62 <= ~var63, var14 ^ 4098, blendings, var13, visual_level, var22, overworld_heightmap);
					}

					if (var36 != var67 && var36 != var32 && var36 != var70) {
						tile = (OpenGLTile) tiles_cache.get(var36);
						OpenGLTile.add_vertices(tile, x, y, rotation, positions, normals_x, normals_y, normals_z, get_material_colour(var49, north_colour, var45), get_material_colour(var49, south_colour, var42), get_material_colour(var49, west_colour, var44), get_material_colour(var49, east_colour, var43), ~var69 <= ~var69, ~var69 >= ~var64, var69 <= var63, ~var62 <= ~var69, var14 ^ 4098, blendings, var13, visual_level, var22, overworld_heightmap);
					}
				}
			}
		}
		for (OpenGLTile var54 = (OpenGLTile) tiles_cache.get_first(); var54 != null; var54 = (OpenGLTile) tiles_cache.get_next()) {
			if (var54.num_vertices != 0) {
				var54.bake();
			} else {
				var54.unlink();
			}
		}
		int num_tiles = tiles_cache.getSize();
		OpenGLTile[] render_tiles = new OpenGLTile[num_tiles];
		tiles_cache.toArray(render_tiles);
		long[] priorities = new long[num_tiles];
		for (int var20 = 0; num_tiles > var20; ++var20) {
			priorities[var20] = render_tiles[var20].uid;
		}
		ArrayUtils.quicksort(render_tiles, priorities);
		return render_tiles;
	}

	public static OpenGLTile[] create_opengl_tiles_blended(float[][] normals_y, float[][] normals_x, int[][] heights, int var3, float[][] normals_z, byte[][] var5, int[][] tile_lightness, int var7, byte[][] var8, byte[][] var9, byte[][] var10, byte[][][] var11) {
		HashTable var12 = new HashTable(128);
		for (int var13 = 1; var13 <= 102; ++var13) {
			for (int var14 = 1; ~var14 >= -103; ++var14) {
				int var15 = var9[var13][var14] & 255;
				int var16 = 255 & var10[var13][var14];
				if (~var16 != -1) {
					FloType var17 = FloTypeList.list(-1 + var16);
					if (var17.tile_colour == -1) {
						continue;
					}

					OpenGLTile var18 = method2052(var12, false, var17);
					byte var19 = var8[var13][var14];
					int[] var20 = underlays_vertices[var19];
					var18.total_vertices += var20.length / 2;
					++var18.total_faces;
					if (var17.blended && var15 != 0) {
						var18.total_vertices += anIntArray3607[var19];
					}
				}

				if (-1 != ~(var9[var13][var14] & 255) || ~var16 != -1 && ~var8[var13][var14] == -1) {
					int var58 = 0;
					int var60 = 0;
					int var21 = 0;
					int var65 = 0;
					int var23 = 255 & var10[var13][1 + var14];
					int var25 = var10[var13][var14 - 1] & 255;
					int var24 = var10[-1 + var13][var14] & 255;
					int[] var63 = new int[8];
					int var22 = 0;
					int var27 = 255 & var10[var13 + -1][1 + var14];
					int var26 = var10[var13 + 1][var14] & 255;
					int var29 = 255 & var10[var13 + 1][-1 + var14];
					int var28 = var10[var13 - 1][-1 + var14] & 255;
					int var30 = var10[1 + var13][var14 - -1] & 255;
					boolean var70;
					if (0 != var27 && ~var16 != ~var27) {
						FloType var31 = FloTypeList.list(-1 + var27);
						if (var31.blended && ~var31.tile_colour != 0) {
							int var32 = var5[var13 + -1][var14 - -1];
							int var33 = var8[-1 + var13][var14 + 1];
							int var34 = anIntArray2642[4 * var33 - -(2 + var32 & 3)];
							int var35 = anIntArray2642[(3 + var32 & 3) + 4 * var33];
							if (aBooleanArrayArray3468[var35][1] && aBooleanArrayArray3468[var34][0]) {
								var70 = false;
							} else {
								for (int var36 = 0; 8 > var36; ++var36) {
									if (~var36 == ~var58) {
										var63[var58++] = var27;
										break;
									}

									if (~var27 == ~var63[var36]) {
										break;
									}
								}
							}
						} else {
							var70 = false;
						}
					} else {
						var70 = false;
					}
					boolean var69;
					if (-1 != ~var28 && var28 != var16) {
						FloType var31 = FloTypeList.list(-1 + var28);
						if (var31.blended && 0 != ~var31.tile_colour) {
							int var32 = var5[-1 + var13][var14 + -1];
							int var33 = var8[-1 + var13][-1 + var14];
							int var34 = anIntArray2642[var33 * 4 - -(var32 & 3)];
							int var35 = anIntArray2642[(var32 - -3 & 3) + var33 * 4];
							if (aBooleanArrayArray3468[var34][1] && aBooleanArrayArray3468[var35][0]) {
								var69 = false;
							} else {
								for (int var36 = 0; ~var36 > -9; ++var36) {
									if (~var58 == ~var36) {
										var63[var58++] = var28;
										break;
									}

									if (~var63[var36] == ~var28) {
										break;
									}
								}
							}
						} else {
							var69 = false;
						}
					} else {
						var69 = false;
					}

					boolean var72;
					if (-1 != ~var29 && ~var16 != ~var29) {
						FloType var31 = FloTypeList.list(var29 - 1);
						if (var31.blended && ~var31.tile_colour != 0) {
							int var32 = var5[1 + var13][var14 - 1];
							int var33 = var8[var13 - -1][var14 + -1];
							int var35 = anIntArray2642[4 * var33 + (3 & 1 + var32)];
							int var34 = anIntArray2642[var33 * 4 - -(var32 & 3)];
							if (aBooleanArrayArray3468[var35][1] && aBooleanArrayArray3468[var34][0]) {
								var72 = false;
							} else {
								for (int var36 = 0; ~var36 > -9; ++var36) {
									if (~var58 == ~var36) {
										var63[var58++] = var29;
										break;
									}

									if (~var29 == ~var63[var36]) {
										break;
									}
								}
							}
						} else {
							var72 = false;
						}
					} else {
						var72 = false;
					}

					boolean var71;
					if (var30 != 0 && ~var30 != ~var16) {
						FloType var31 = FloTypeList.list(var30 + -1);
						if (var31.blended && 0 != ~var31.tile_colour) {
							int var32 = var5[var13 - -1][1 + var14];
							int var33 = var8[var13 + 1][1 + var14];
							int var35 = anIntArray2642[4 * var33 + (var32 - -1 & 3)];
							int var34 = anIntArray2642[var33 * 4 + (var32 - -2 & 3)];
							if (aBooleanArrayArray3468[var34][1] && aBooleanArrayArray3468[var35][0]) {
								var71 = false;
							} else {
								for (int var36 = 0; ~var36 > -9; ++var36) {
									if (var58 == var36) {
										var63[var58++] = var30;
										break;
									}

									if (~var30 == ~var63[var36]) {
										break;
									}
								}
							}
						} else {
							var71 = false;
						}
					} else {
						var71 = false;
					}

					if (-1 != ~var23 && ~var16 != ~var23) {
						FloType var31 = FloTypeList.list(-1 + var23);
						if (var31.blended && var31.tile_colour != -1) {
							var60 = anIntArray2642[4 * var8[var13][var14 - -1] + (var5[var13][var14 - -1] - -2 & 3)];
							for (int var74 = 0; ~var74 > -9; ++var74) {
								if (~var58 == ~var74) {
									var63[var58++] = var23;
									break;
								}
								if (var63[var74] == var23) {
									break;
								}
							}
						}
					}

					if (0 != var24 && ~var16 != ~var24) {
						FloType var31 = FloTypeList.list(var24 + -1);
						if (var31.blended && var31.tile_colour != -1) {
							var65 = anIntArray2642[(3 & 3 + var5[var13 + -1][var14]) + var8[var13 + -1][var14] * 4];
							for (int var74 = 0; -9 < ~var74; ++var74) {
								if (var58 == var74) {
									var63[var58++] = var24;
									break;
								}
								if (~var24 == ~var63[var74]) {
									break;
								}
							}
						}
					}

					if (-1 != ~var25 && var16 != var25) {
						FloType var31 = FloTypeList.list(-1 + var25);
						if (var31.blended && ~var31.tile_colour != 0) {
							var21 = anIntArray2642[(3 & var5[var13][var14 + -1]) + var8[var13][var14 + -1] * 4];
							for (int var74 = 0; ~var74 > -9; ++var74) {
								if (~var74 == ~var58) {
									var63[var58++] = var25;
									break;
								}
								if (var25 == var63[var74]) {
									break;
								}
							}
						}
					}

					if (0 != var26 && var16 != var26) {
						FloType var31 = FloTypeList.list(var26 - 1);
						if (var31.blended && ~var31.tile_colour != 0) {
							var22 = anIntArray2642[(3 & var5[var13 + 1][var14] + 1) + 4 * var8[1 + var13][var14]];
							for (int var74 = 0; ~var74 > -9; ++var74) {
								if (~var58 == ~var74) {
									var63[var58++] = var26;
									break;
								}
								if (var63[var74] == var26) {
									break;
								}
							}
						}
					}
					for (int var73 = 0; var58 > var73; ++var73) {
						int var74 = var63[var73];
						boolean[] var79 = aBooleanArrayArray3468[var24 != ~var74 ? 0 : var65];
						boolean[] var75 = aBooleanArrayArray3468[var74 != var25 ? 0 : var21];
						boolean[] var80 = aBooleanArrayArray3468[var23 == var74 ? var60 : 0];
						boolean[] var81 = aBooleanArrayArray3468[var26 == var74 ? var22 : 0];
						FloType var37 = FloTypeList.list(-1 + var74);
						OpenGLTile var38 = method2052(var12, false, var37);
						var38.total_vertices += 5;
						var38.total_vertices += -2 + var80.length;
						var38.total_vertices += -2 + var79.length;
						var38.total_vertices += var75.length - 2;
						var38.total_vertices += -2 + var81.length;
						++var38.total_faces;
					}
				}
			}
		}

		for (OpenGLTile tile = (OpenGLTile) var12.get_first(); null != tile; tile = (OpenGLTile) var12.get_next()) {
			tile.initialise_tables();
		}
		for (int grid_x = 1; 102 >= grid_x; ++grid_x) {
			for (int grid_y = 1; ~grid_y >= -103; ++grid_y) {
				int var16 = 255 & var9[grid_x][grid_y];
				int var58 = 255 & var10[grid_x][grid_y];
				int var15;
				if ((8 & var11[var3][grid_x][grid_y]) != 0) {
					var15 = 0;
				} else if (2 == (var11[1][grid_x][grid_y] & 2) && ~var3 < -1) {
					var15 = var3 + -1;
				} else {
					var15 = var3;
				}
				if (0 != var58) {
					FloType var62 = FloTypeList.list(-1 + var58);
					if (var62.tile_colour == -1) {
						continue;
					}

					OpenGLTile var66 = method2052(var12, false, var62);
					byte var67 = var8[grid_x][grid_y];
					byte var68 = var5[grid_x][grid_y];
					int var22 = get_material_colour(var62.texture_id, var62.tile_colour, tile_lightness[grid_x][grid_y]);
					int var23 = get_material_colour(var62.texture_id, var62.tile_colour, tile_lightness[grid_x + 1][grid_y]);
					int var24 = get_material_colour(var62.texture_id, var62.tile_colour, tile_lightness[1 + grid_x][grid_y + 1]);
					int var25 = get_material_colour(var62.texture_id, var62.tile_colour, tile_lightness[grid_x][grid_y - -1]);
					method971(var22, heights, normals_x, grid_x, normals_y, var23, var68, var15, var24, ~var16 != -1 && var62.blended, var67, grid_y, normals_z, var25, var66);
				}
				if (~(var9[grid_x][grid_y] & 255) != -1 || ~var58 != -1 && 0 == var8[grid_x][grid_y]) {
					int[] var64 = new int[8];
					int var65 = 0;
					int var61 = 0;
					int var21 = 0;
					int var22 = 0;
					int var24 = var10[grid_x][grid_y - -1] & 255;
					int var23 = 0;
					int var25 = var10[-1 + grid_x][grid_y] & 255;
					int var27 = var10[1 + grid_x][grid_y] & 255;
					int var26 = var10[grid_x][-1 + grid_y] & 255;
					int var28 = 255 & var10[-1 + grid_x][grid_y + 1];
					int var29 = 255 & var10[grid_x - 1][grid_y - 1];
					int var30 = 255 & var10[1 + grid_x][grid_y + -1];
					int var73 = var10[1 + grid_x][grid_y + 1] & 255;
					if (0 != var28 && var28 != var58) {
						FloType var76 = FloTypeList.list(-1 + var28);
						if (var76.blended && var76.tile_colour != -1) {
							int var33 = var5[grid_x + -1][1 + grid_y];
							int var77 = var8[-1 + grid_x][1 + grid_y];
							int var35 = anIntArray2642[4 * var77 - -(2 + var33 & 3)];
							int var36 = anIntArray2642[var77 * 4 - -(3 + var33 & 3)];
							if (aBooleanArrayArray3468[var36][1] && aBooleanArrayArray3468[var35][0]) {
								var28 = 0;
							} else {
								for (int var83 = 0; -9 < ~var83; ++var83) {
									if (var61 == var83) {
										var64[var61++] = var28;
										break;
									}

									if (~var64[var83] == ~var28) {
										break;
									}
								}
							}
						} else {
							var28 = 0;
						}
					} else {
						var28 = 0;
					}
					if (0 != var29 && ~var58 != ~var29) {
						FloType var76 = FloTypeList.list(-1 + var29);
						if (var76.blended && -1 != var76.tile_colour) {
							int var33 = var5[grid_x + -1][-1 + grid_y];
							int var77 = var8[-1 + grid_x][grid_y + -1];
							int var35 = anIntArray2642[(3 & var33) + var77 * 4];
							int var36 = anIntArray2642[(var33 - -3 & 3) + 4 * var77];

							if (aBooleanArrayArray3468[var35][1] && aBooleanArrayArray3468[var36][0]) {
								var29 = 0;
							} else {
								for (int var83 = 0; 8 > var83; ++var83) {
									if (var83 == var61) {
										var64[var61++] = var29;
										break;
									}

									if (var29 == var64[var83]) {
										break;
									}
								}
							}
						} else {
							var29 = 0;
						}
					} else {
						var29 = 0;
					}

					if (var30 != 0 && ~var30 != ~var58) {
						FloType var76 = FloTypeList.list(-1 + var30);
						if (var76.blended && -1 != var76.tile_colour) {
							int var33 = var5[1 + grid_x][grid_y - 1];
							int var77 = var8[1 + grid_x][grid_y + -1];
							int var36 = anIntArray2642[(1 + var33 & 3) + 4 * var77];
							int var35 = anIntArray2642[var77 * 4 + (var33 & 3)];
							if (aBooleanArrayArray3468[var36][1] && aBooleanArrayArray3468[var35][0]) {
								var30 = 0;
							} else {
								for (int var83 = 0; 8 > var83; ++var83) {
									if (~var83 == ~var61) {
										var64[var61++] = var30;
										break;
									}

									if (var64[var83] == var30) {
										break;
									}
								}
							}
						} else {
							var30 = 0;
						}
					} else {
						var30 = 0;
					}

					if (~var73 != -1 && ~var58 != ~var73) {
						FloType var76 = FloTypeList.list(-1 + var73);
						if (var76.blended && var76.tile_colour != -1) {
							int var77 = var8[1 + grid_x][1 + grid_y];
							int var33 = var5[1 + grid_x][grid_y + 1];
							int var35 = anIntArray2642[(3 & var33 - -2) + 4 * var77];
							int var36 = anIntArray2642[(var33 + 1 & 3) + 4 * var77];
							if (aBooleanArrayArray3468[var35][1] && aBooleanArrayArray3468[var36][0]) {
								var73 = 0;
							} else {
								for (int var83 = 0; ~var83 > -9; ++var83) {
									if (~var83 == ~var61) {
										var64[var61++] = var73;
										break;
									}

									if (var64[var83] == var73) {
										break;
									}
								}
							}
						} else {
							var73 = 0;
						}
					} else {
						var73 = 0;
					}

					int var78;
					if (-1 != ~var24 && ~var24 != ~var58) {
						FloType var76 = FloTypeList.list(var24 - 1);
						if (var76.blended && -1 != var76.tile_colour) {
							var65 = anIntArray2642[var8[grid_x][grid_y - -1] * 4 + (2 + var5[grid_x][grid_y - -1] & 3)];
							for (var78 = 0; 8 > var78; ++var78) {
								if (~var78 == ~var61) {
									var64[var61++] = var24;
									break;
								}
								if (~var64[var78] == ~var24) {
									break;
								}
							}
						}
					}

					if (var25 != 0 && var58 != var25) {
						FloType var76 = FloTypeList.list(var25 + -1);
						if (var76.blended && 0 != ~var76.tile_colour) {
							var21 = anIntArray2642[(3 & var5[grid_x - 1][grid_y] - -3) + 4 * var8[grid_x + -1][grid_y]];
							for (var78 = 0; var78 < 8; ++var78) {
								if (~var61 == ~var78) {
									var64[var61++] = var25;
									break;
								}
								if (var25 == var64[var78]) {
									break;
								}
							}
						}
					}

					if (var26 != 0 && ~var26 != ~var58) {
						FloType var76 = FloTypeList.list(var26 - 1);
						if (var76.blended && -1 != var76.tile_colour) {
							var22 = anIntArray2642[(var5[grid_x][grid_y + -1] & 3) + 4 * var8[grid_x][-1 + grid_y]];
							for (var78 = 0; ~var78 > -9; ++var78) {
								if (~var61 == ~var78) {
									var64[var61++] = var26;
									break;
								}

								if (~var26 == ~var64[var78]) {
									break;
								}
							}
						}
					}

					if (var27 != 0 && var27 != var58) {
						FloType var76 = FloTypeList.list(var27 + -1);
						if (var76.blended && var76.tile_colour != -1) {
							var23 = anIntArray2642[4 * var8[1 + grid_x][grid_y] - -(3 & var5[grid_x + 1][grid_y] - -1)];

							for (var78 = 0; ~var78 > -9; ++var78) {
								if (~var78 == ~var61) {
									var64[var61++] = var27;
									break;
								}

								if (~var64[var78] == ~var27) {
									break;
								}
							}
						}
					}

					for (int var74 = 0; ~var74 > ~var61; ++var74) {
						var78 = var64[var74];
						boolean[] var79 = aBooleanArrayArray3468[var78 == var24 ? var65 : 0];
						boolean[] var75 = aBooleanArrayArray3468[var25 == var78 ? var21 : 0];
						boolean[] var81 = aBooleanArrayArray3468[~var78 == ~var26 ? var22 : 0];
						boolean[] var84 = aBooleanArrayArray3468[var78 != var27 ? 0 : var23];
						FloType var82 = FloTypeList.list(-1 + var78);
						OpenGLTile tile = method2052(var12, false, var82);
						int north_colour = 255 | get_material_colour(var82.texture_id, var82.tile_colour, tile_lightness[grid_x][grid_y + 1]) << 8;
						int south_colour = get_material_colour(var82.texture_id, var82.tile_colour, tile_lightness[grid_x][grid_y]) << 8 | 255;
						int west_colour = get_material_colour(var82.texture_id, var82.tile_colour, tile_lightness[grid_x + 1][grid_y + 1]) << 8 | 255;
						int east_colour = 255 | get_material_colour(var82.texture_id, var82.tile_colour, tile_lightness[1 + grid_x][grid_y]) << 8;
						byte var44 = 6;
						boolean var48 = var78 != var29 && var81[0] && var75[1];
						boolean var47 = var78 != var73 && var79[0] && var84[1];
						boolean var46 = var28 != var78 && var75[0] && var79[1];
						boolean var49 = var78 != var30 && var84[0] && var81[1];
						int var85 = var44 + -2 + var79.length;
						var85 += -2 + var75.length;
						var85 += var81.length - 2;
						var85 += -2 + var84.length;
						int[] var45 = new int[var85];
						var44 = 0;
						int var50 = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 64, 64, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, true);
						int var51 = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 0, 128, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var46);
						int var52 = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 128, 128, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var47);
						int var53 = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 0, 0, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var48);
						int var54 = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 128, 0, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var49);
						var85 = var44 + 1;
						var45[var44] = var50;
						var45[var85++] = var52;
						if (-3 > ~var79.length) {
							var45[var85++] = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 64, 128, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var79[2]);
						}

						var45[var85++] = var51;
						if (var75.length > 2) {
							var45[var85++] = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 0, 64, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var75[2]);
						}

						var45[var85++] = var53;
						if (var81.length > 2) {
							var45[var85++] = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 64, 0, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var81[2]);
						}

						var45[var85++] = var54;
						if (-3 > ~var84.length) {
							var45[var85++] = OpenGLTile.add_vertex(tile, grid_x, grid_y, 0, 128, 64, normals_x, normals_y, normals_z, heights, null, north_colour, south_colour, west_colour, east_colour, 0.0F, var84[2]);
						}

						var45[var85++] = var52;
						tile.add_face(var15, grid_x, grid_y, var45, null, true);
					}
				}
			}
		}

		for (OpenGLTile var56 = (OpenGLTile) var12.get_first(); null != var56; var56 = (OpenGLTile) var12.get_next()) {
			if (-1 != ~var56.num_vertices) {
				var56.bake();
			} else {
				var56.unlink();
			}
		}
		int var13 = var12.getSize();
		OpenGLTile[] var57 = new OpenGLTile[var13];
		long[] var59 = new long[var13];
		var12.toArray(var57);
		for (int var16 = var7; ~var13 < ~var16; ++var16) {
			var59[var16] = var57[var16].uid;
		}
		ArrayUtils.quicksort(var57, var59);
		return var57;
	}

	public static OpenGLTile method2052(HashTable var0, boolean var1, FloType var2) {
		long var3 = (var2.texture_id - -1 << 16) + var2.texture_scale + (((long) var2.slot << 56) - -((long) var2.water_colour << 32));
		OpenGLTile var5 = (OpenGLTile) var0.get(var3);
		if (null == var5) {
			var5 = new OpenGLTile(var2.texture_id, var2.texture_scale, true, false, var2.water_colour);
			var0.put(var3, var5);
		}

		return var5;
	}

	public static void method971(int var0, int[][] var1, float[][] var2, int var3, float[][] var4, int var5, byte var6, int var7, int var8, boolean var10, byte var11, int var12, float[][] var13, int var14, OpenGLTile var15) {
		int var16 = 255 + (var0 << 8);
		int var17 = (var5 << 8) - -255;
		int var18 = (var8 << 8) - -255;
		int var19 = (var14 << 8) - -255;
		int[] var20 = underlays_vertices[var11];
		int[] var22 = new int[var20.length >> 1];
		int var23;
		for (var23 = 0; var23 < var22.length; ++var23) {
			var22[var23] = OpenGLTile.add_vertex(var15, var3, var12, var6, var20[var23 + var23], var20[var23 + var23 + 1], var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, false);
		}

		int[] var21 = null;
		if (var10) {
			int var24;
			if (1 == var11) {
				var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 64, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
				var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 64, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
				var21 = new int[] { var24, var23, var22[2], var23, var22[0], var22[2] };
			} else if (2 == var11) {
				var21 = new int[6];
				var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
				var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 64, 0, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
				var21[2] = var23;
				var21[0] = var22[0];
				var21[5] = var22[0];
				var21[3] = var23;
				var21[1] = var24;
				var21[4] = var22[1];
			} else if (~var11 != -4) {
				if (~var11 == -5) {
					var21 = new int[3];
					var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 0, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
					var21[0] = var22[3];
					var21[2] = var22[0];
					var21[1] = var23;
				} else if (~var11 != -6) {
					if (~var11 != -7) {
						if (~var11 == -8) {
							var21 = new int[6];
							var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 0, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 0, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							var21[3] = var23;
							var21[2] = var23;
							var21[0] = var22[1];
							var21[4] = var22[2];
							var21[1] = var24;
							var21[5] = var22[1];
						} else if (var11 == 8) {
							var21 = new int[3];
							var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 0, 0, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							var21[2] = var22[4];
							var21[0] = var22[3];
							var21[1] = var23;
						} else if (var11 == 9) {
							var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 64, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 96, 32, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							int var25 = OpenGLTile.add_vertex(var15, var3, var12, var6, 64, 0, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							var21 = new int[] { var24, var23, var22[4], var24, var22[4], var22[3], var24, var22[3], var22[2], var24, var22[2], var22[1], var24, var22[1], var25 };
						} else if (10 != var11) {
							if (-12 == ~var11) {
								var21 = new int[12];
								var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 0, 64, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
								var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 64, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
								var21[5] = var23;
								var21[1] = var23;
								var21[8] = var23;
								var21[0] = var22[3];
								var21[2] = var22[0];
								var21[11] = var24;
								var21[6] = var22[2];
								var21[7] = var24;
								var21[10] = var22[1];
								var21[3] = var22[3];
								var21[4] = var22[2];
								var21[9] = var22[2];
							}
						} else {
							var21 = new int[9];
							var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 0, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
							var21[0] = var22[2];
							var21[8] = var22[0];
							var21[1] = var23;
							var21[4] = var23;
							var21[2] = var22[3];
							var21[7] = var23;
							var21[3] = var22[3];
							var21[5] = var22[4];
							var21[6] = var22[4];
						}
					} else {
						var21 = new int[6];
						var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 0, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
						var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
						var21[1] = var23;
						var21[0] = var22[3];
						var21[2] = var24;
						var21[4] = var22[0];
						var21[3] = var24;
						var21[5] = var22[3];
					}
				} else {
					var21 = new int[3];
					var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 128, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
					var21[1] = var23;
					var21[0] = var22[2];
					var21[2] = var22[3];
				}
			} else {
				var21 = new int[6];
				var23 = OpenGLTile.add_vertex(var15, var3, var12, var6, 0, 128, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
				var24 = OpenGLTile.add_vertex(var15, var3, var12, var6, 64, 0, var2, var4, var13, var1, null, var19, var16, var18, var17, 0.0F, true);
				var21[4] = var24;
				var21[1] = var22[1];
				var21[0] = var22[2];
				var21[3] = var23;
				var21[2] = var23;
				var21[5] = var22[2];
			}
		}

		var15.add_face(var7, var3, var12, var22, var21, false);
	}

	public static int get_material_colour(int materialid, int hsl, int lightness) {
		int var5 = ColourUtil.hslToRgbTable[repackHSL(hsl, lightness)];
		if (-1 > ~materialid) {
			MaterialRaw material = GraphicTools.get_materials().get_material(materialid & 0xffff);
			int intensity = material.intensity & 0xff;
			if (intensity != 0) {
				int var7;
				if (-1 >= ~lightness) {
					if (-128 > ~lightness) {
						var7 = 16777215;
					} else {
						var7 = 131586 * lightness;
					}
				} else {
					var7 = 0;
				}

				if (intensity == 256) {
					var5 = var7;
				} else {
					int var9 = 256 - intensity;
					var5 = (16711680 & (var7 & 0xff00) * intensity + var9 * (var5 & 0xff00)) + (intensity * (var7 & 16711935) - -((16711935 & var5) * var9) & -16711936) >> 8;
				}
			}
			int var7 = material.brightness & 0xff;
			if (var7 != 0) {
				var7 += 256;
				int var8 = ((16711680 & var5) >> 16) * var7;
				if (0xffff < var8) {
					var8 = 0xffff;
				}
				int var9 = ((var5 & 0xff00) >> 8) * var7;
				if (var9 > 0xffff) {
					var9 = 0xffff;
				}

				int var10 = var7 * (var5 & 255);
				if (var10 > 0xffff) {
					var10 = 0xffff;
				}
				var5 = (var10 >> 8) + (0xff00 & var9) + (16711711 & var8 << 8);
			}
		}
		return var5;
	}

	public static final int pack_hsl(int saturation, int hue, int depth) {
		if (depth > 243) {
			hue >>= 4;
		} else if (depth > 217) {
			hue >>= 3;
		} else if (depth > 192) {
			hue >>= 2;
		} else if (depth > 179) {
			hue >>= 1;
		}
		return (depth >> 1) + (saturation >> 2 << 10) + (hue >> 5 << 7);

	}

	public static void decode_server_request(boolean server_map) {
		MapLoader.server_map = server_map;
		if (!server_map) {
			int index = (StaticMethods.currentLength - StaticMethods2.packet.index) / 16;
			square_keys = new int[index][4];
			for (int squareidx = 0; squareidx < index; squareidx++) {
				for (int keyidx = 0; keyidx < 4; keyidx++) {
					square_keys[squareidx][keyidx] = StaticMethods2.packet.g4();
				}
			}
			int chunk_y = StaticMethods2.packet.g2();
			int chunk_x = StaticMethods2.packet.getLEShortA0(125);
			int i_6_ = StaticMethods2.packet.getLEShortA0(124);
			int plane = StaticMethods2.packet.getByteC(-115);
			int i_8_ = StaticMethods2.packet.getShortA();
			square_ids = new int[index];
			square_map_ids = new int[index];
			square_loc_ids = new int[index];
			square_umap_ids = new int[index];
			square_uloc_ids = new int[index];
			square_map_data = new byte[index][];
			square_loc_data = new byte[index][];
			square_umap_data = new byte[index][];
			square_uloc_data = new byte[index][];
			index = 0;
			for (int region_x = (chunk_x - 6) / 8; (chunk_x - -6) / 8 >= region_x; region_x++) {
				for (int region_y = (chunk_y + -6) / 8; ((6 + chunk_y) / 8 ^ 0xffffffff) <= (region_y ^ 0xffffffff); region_y++) {
					int regionId = region_x << 8 | region_y;
					square_ids[index] = regionId;
					square_map_ids[index] = CacheConstants.map_js5.get_groupid("m" + region_x + "_" + region_y);
					square_loc_ids[index] = CacheConstants.map_js5.get_groupid("l" + region_x + "_" + region_y);
					square_umap_ids[index] = CacheConstants.map_js5.get_groupid("um" + region_x + "_" + region_y);
					square_uloc_ids[index] = CacheConstants.map_js5.get_groupid("ul" + region_x + "_" + region_y);
					index++;
				}
			}
			EntityUpdating.method342(plane, i_6_, 31362, i_8_, chunk_y, chunk_x, true);
		} else

		{
			StaticMethods2.packet.start_bitwise_access(0);
			for (int z = 0; z < 4; z++) {
				for (int x = 0; x < 13; x++) {
					for (int y = 0; y < 13; y++) {
						int opcode = StaticMethods2.packet.getBits(1);
						if (opcode != 1) {
							MapRegion.localRegions[z][x][y] = -1;
						} else {
							MapRegion.localRegions[z][x][y] = StaticMethods2.packet.getBits(26);
						}
					}
				}
			}
			StaticMethods2.packet.end_bitwise_access(-113);
			int index = (-StaticMethods2.packet.index + StaticMethods.currentLength) / 16;
			square_keys = new int[index][4];
			for (int squareidx = 0; squareidx < index; squareidx++) {
				for (int keyidx = 0; keyidx < 4; keyidx++) {
					square_keys[squareidx][keyidx] = StaticMethods2.packet.g4();
				}
			}
			int sceneY = StaticMethods2.packet.getLEShort();
			int sceneX = StaticMethods2.packet.getShortA();
			int chunkY = StaticMethods2.packet.g2();
			int chunkX = StaticMethods2.packet.getLEShort();
			int plane = StaticMethods2.packet.g1();
			square_ids = new int[index];
			square_map_ids = new int[index];
			square_loc_ids = new int[index];
			square_umap_ids = new int[index];
			square_uloc_ids = new int[index];
			square_map_data = new byte[index][];
			square_loc_data = new byte[index][];
			square_umap_data = new byte[index][];
			square_uloc_data = new byte[index][];
			index = 0;
			for (int i_24_ = 0; i_24_ < 4; i_24_++) {
				for (int i_25_ = 0; i_25_ < 13; i_25_++) {
					for (int i_26_ = 0; i_26_ < 13; i_26_++) {
						int i_27_ = MapRegion.localRegions[i_24_][i_25_][i_26_];
						if ((i_27_ ^ 0xffffffff) != 0) {
							int i_28_ = i_27_ >> 14 & 0x3ff;
							int i_29_ = (0x3ffd & i_27_) >> 3;
							int region = (i_28_ / 8 << 8) + i_29_ / 8;
							for (int i_31_ = 0; i_31_ < index; i_31_++) {
								if (region == square_ids[i_31_]) {
									region = -1;
									break;
								}
							}
							if (region != -1) {
								int region_x = 0xff & region >> 8;
								int region_y = region & 0xff;
								square_ids[index] = region;
								square_map_ids[index] = CacheConstants.map_js5.get_groupid("m" + region_x + "_" + region_y);
								square_loc_ids[index] = CacheConstants.map_js5.get_groupid("l" + region_x + "_" + region_y);
								square_umap_ids[index] = CacheConstants.map_js5.get_groupid("um" + region_x + "_" + region_y);
								square_uloc_ids[index] = CacheConstants.map_js5.get_groupid("ul" + region_x + "_" + region_y);
								index++;
							}
						}
					}
				}
			}
			EntityUpdating.method342(plane, sceneX, 31362, sceneY, chunkY, chunkX, true);
		}
	}

	public static void decode_client_side(CollisionMap[] collision_maps, byte[] bs, int i, int base_y, int i_8_, boolean underwater, int i_9_, int base_x) {
		int num_levels;
		if (underwater) {
			num_levels = 1;
		} else {
			num_levels = 4;
		}
		if (!underwater) {
			for (int i_12_ = 0; i_12_ < 4; i_12_++) {
				for (int i_13_ = 0; i_13_ < 64; i_13_++) {
					for (int i_14_ = 0; i_14_ < 64; i_14_++) {
						if (i_13_ + base_x > 0 && base_x - -i_13_ < 103 && (base_y - -i_14_ ^ 0xffffffff) < -1 && base_y - -i_14_ < 103) {
							collision_maps[i_12_].clippingFlags[i_13_ + base_x][i_14_ + base_y] = MathUtils.bitAnd(collision_maps[i_12_].clippingFlags[i_13_ + base_x][i_14_ + base_y], -16777217);
						}
					}
				}
			}
		}
		Packet map_buffer = new Packet(bs);
		for (int level = 0; (num_levels ^ 0xffffffff) < (level ^ 0xffffffff); level++) {
			for (int x = 0; x < 64; x++) {
				for (int y = 0; y < 64; y++) {
					MapLoader.decode_tile(y - -base_y, map_buffer, level, x - -base_x, underwater, i_9_, (byte) -121, 0, i);
				}
			}
		}
		boolean has_shadows_map = false;
		while (map_buffer.index < map_buffer.byteBuffer.length) {
			int opcode = map_buffer.g1();
			if (opcode != 129) {
				map_buffer.index--;
				break;
			}
			for (int level = 0; level < 4; ++level) {
				byte var13 = map_buffer.g1s();
				if (0 != var13) {
					if (1 != var13) {
						if (var13 == 2 && -1 > ~level) {
							int var15 = base_x + 64;
							int grid_y = base_y;
							int var17 = base_y + 64;
							if (~var15 > -1) {
								var15 = 0;
							} else if (104 <= var15) {
								var15 = 104;
							}

							if (~base_y <= -1) {
								if (-105 >= ~base_y) {
									grid_y = 104;
								}
							} else {
								grid_y = 0;
							}

							if (-1 >= ~var17) {
								if (~var17 <= -105) {
									var17 = 104;
								}
							} else {
								var17 = 0;
							}

							int grid_x = base_x;
							if (base_x >= 0) {
								if (104 <= base_x) {
									grid_x = 104;
								}
							} else {
								grid_x = 0;
							}
							while (var15 > grid_x) {
								while (~grid_y > ~var17) {
									Scene.lighting[level][grid_x][grid_y] = Scene.lighting[level + -1][grid_x][grid_y];
									++grid_y;
								}
								grid_x++;
							}
						}
					} else {
						for (int var14 = 0; ~var14 > -65; var14 += 4) {
							for (int var15 = 0; var15 < 64; var15 += 4) {
								byte var16 = map_buffer.g1s();

								for (int var17 = var14 + base_x; 4 + base_x + var14 > var17; ++var17) {
									for (int var18 = base_y + var15; ~var18 > ~(4 + base_y + var15); ++var18) {
										if (var17 >= 0 && ~var17 > -105 && 0 <= var18 && -105 < ~var18) {
											Scene.lighting[level][var17][var18] = var16;
										}
									}
								}
							}
						}
					}
				} else {
					int var14 = base_x;
					if (base_x >= 0) {
						if (~base_x <= -105) {
							var14 = 104;
						}
					} else {
						var14 = 0;
					}
					int var24 = base_y;
					if (-1 < ~base_y) {
						var24 = 0;
					} else if (~base_y <= -105) {
						var24 = 104;
					}

					int var15 = 64 + base_x;
					int var17 = base_y + 64;
					if (-1 >= ~var17) {
						if (var17 >= 104) {
							var17 = 104;
						}
					} else {
						var17 = 0;
					}

					if (-1 < ~var15) {
						var15 = 0;
					} else if (var15 >= 104) {
						var15 = 104;
					}

					while (~var14 > ~var15) {
						while (var24 < var17) {
							Scene.lighting[level][var14][var24] = 0;
							++var24;
						}

						++var14;
					}
				}
			}
			has_shadows_map = true;
		}
		if (GLManager.opengl_mode && !underwater) {
			SceneAtmosphere atmosphere = null;
			while (map_buffer.index < map_buffer.byteBuffer.length) {
				int opcode = map_buffer.g1();
				if (opcode == 0) {
					atmosphere = new SceneAtmosphere(map_buffer);
				} else if (opcode == 1) {
					int var23 = map_buffer.g1();
					if (0 < var23) {
						for (int var14 = 0; var23 > var14; ++var14) {
							FlickeringEffect light = new FlickeringEffect(map_buffer);
							if (light.type == 31) {
								LightType type = LightTypeList.list(map_buffer.g2());
								light.set_type(type.effect, type.anInt908, type.anInt899, type.anInt907);
							}
							light.y += base_y << 7;
							light.x += base_x << 7;
							int var17 = light.y >> 7;
							int var24 = light.x >> 7;
							if (~var24 <= -1 && 0 <= var17 && ~var24 > -105 && ~var17 > -105) {
								light.has_bridge = 0 != (MapLoader.settings[1][var24][var17] & 2);
								light.z = Scene.current_heightmap[light.level][var24][var17] + -light.z;
								SceneLighting.add_light(light);
							}
						}
					}
				} else if (opcode == 2) {
					if (atmosphere == null) {
						atmosphere = new SceneAtmosphere();
					}
					atmosphere.decode_hdr(map_buffer);
				} else if (opcode == 128) {
					if (atmosphere == null) {
						atmosphere = new SceneAtmosphere();
					}
					atmosphere.decode_skybox(map_buffer);
				}
			}
			if (atmosphere == null) {
				atmosphere = new SceneAtmosphere();
			}
			for (int var12 = 0; -9 < ~var12; ++var12) {
				for (int var23 = 0; -9 < ~var23; ++var23) {
					int chunkx = var12 + (base_x >> 3);
					int chunky = (base_y >> 3) + var23;
					if (0 <= chunkx && chunkx < 13 && -1 >= ~chunky && ~chunky > -14) {
						SceneEnvironment.atmospheres[chunkx][chunky] = atmosphere;
					}
				}
			}
		}

		if (!has_shadows_map) {
			for (int var11 = 0; var11 < 4; ++var11) {
				for (int var12 = 0; 16 > var12; ++var12) {
					for (int var23 = 0; var23 < 16; ++var23) {
						int var14 = (base_x >> 2) - -var12;
						int var15 = var23 + (base_y >> 2);
						if (0 <= var14 && 26 > var14 && 0 <= var15 && var15 < 26) {
							Scene.lighting[var11][var14][var15] = 0;
						}
					}
				}
			}
		}
	}

	public static void decode_server_side(int i, boolean bool, int i_5_, byte b, int i_6_, byte[] bs, CollisionMap[] tileSettings, int i_7_, int i_8_, int i_9_, int i_10_) {
		if (!bool) {
			for (int i_11_ = 0; i_11_ < 8; i_11_++) {
				for (int i_12_ = 0; i_12_ < 8; i_12_++) {
					if ((i_7_ + i_11_ ^ 0xffffffff) < -1 && i_7_ - -i_11_ < 103 && i_8_ - -i_12_ > 0 && i_8_ + i_12_ < 103) {
						tileSettings[i_5_].clippingFlags[i_7_ - -i_11_][i_12_ + i_8_] = MathUtils.bitAnd(tileSettings[i_5_].clippingFlags[i_7_ - -i_11_][i_12_ + i_8_], -16777217);
					}
				}
			}
		}
		int i_13_;
		if (bool) {
			i_13_ = 1;
		} else {
			i_13_ = 4;
		}
		Packet buffer = new Packet(bs);
		for (int i_14_ = 0; i_14_ < i_13_; i_14_++) {
			for (int i_15_ = 0; i_15_ < 64; i_15_++) {
				for (int i_16_ = 0; i_16_ < 64; i_16_++) {
					if ((i_14_ ^ 0xffffffff) != (i_9_ ^ 0xffffffff) || (i_15_ ^ 0xffffffff) > (i ^ 0xffffffff) || 8 + i <= i_15_ || (i_16_ ^ 0xffffffff) > (i_10_ ^ 0xffffffff) || i_16_ >= 8 + i_10_) {
						MapLoader.decode_tile(-1, buffer, 0, -1, bool, 0, (byte) -122, 0, 0);
					} else {
						MapLoader.decode_tile(StaticMethods.method413(i_6_, (byte) 83, i_16_ & 0x7, 0x7 & i_15_) + i_8_, buffer, i_5_, i_7_ + Class55.method1181((byte) 96, 0x7 & i_15_, 0x7 & i_16_, i_6_), bool, 0, (byte) -122, i_6_, 0);
					}
				}
			}
		}
	}

	public static void decode_tile(int y, Packet buffer, int plane, int x, boolean bool, int i_48_, byte b, int i_49_, int i_50_) {
		if ((x ^ 0xffffffff) <= -1 && x < 104 && (y ^ 0xffffffff) <= -1 && y < 104) {
			if (!bool) {
				MapLoader.settings[plane][x][y] = (byte) 0;
			}
			for (;;) {
				int opcode = buffer.g1();
				if (opcode == 0) {
					if (bool) {
						Scene.current_heightmap[0][x][y] = Scene.surface_heightmap[0][x][y];
					} else {
						if (plane == 0) {
							Scene.current_heightmap[0][x][y] = 8 * -StaticMethods.method354(-118, y + 556238 + i_50_, i_48_ + x + 932731);
						} else {
							Scene.current_heightmap[plane][x][y] = Scene.current_heightmap[-1 + plane][x][y] + -240;
						}
					}
					break;
				}
				if (opcode == 1) {
					int height = buffer.g1();
					if (bool) {
						Scene.current_heightmap[0][x][y] = Scene.surface_heightmap[0][x][y] + height * 8;
					} else {
						if (height == 1) {
							height = 0;
						}
						if (plane == 0) {
							Scene.current_heightmap[0][x][y] = 8 * -height;
						} else {
							Scene.current_heightmap[plane][x][y] = Scene.current_heightmap[plane - 1][x][y] + -(height * 8);
						}
					}
					break;
				}
				if (opcode <= 49) {
					MapLoader.overlays[plane][x][y] = buffer.g1s();
					MapLoader.shapes[plane][x][y] = (byte) ((opcode - 2) / 4);
					MapLoader.rotations[plane][x][y] = (byte) (opcode - 2 + i_49_ & 0x3);
				} else if (opcode <= 81) {
					if (!bool) {
						MapLoader.settings[plane][x][y] = (byte) (opcode - 49);
					}
				} else {
					MapLoader.underlays[plane][x][y] = (byte) (opcode - 81);
				}
			}
		} else {
			for (;;) {
				int i_53_ = buffer.g1();
				if (i_53_ == 0) {
					break;
				}
				if (i_53_ == 1) {
					buffer.g1();
					break;
				}
				if (i_53_ <= 49) {
					buffer.g1();
				}
			}
		}
	}

	public static final void decode_maps_client(boolean has_underwater) {
		byte num_levels;
		byte[][] datas;
		if (GLManager.opengl_mode && has_underwater) {
			num_levels = 1;
			datas = square_umap_data;
		} else {
			num_levels = 4;
			datas = square_map_data;
		}
		int i_0_ = datas.length;
		for (int i_1_ = 0; (i_0_ ^ 0xffffffff) < (i_1_ ^ 0xffffffff); i_1_++) {
			int base_y = 64 * (square_ids[i_1_] >> 8 & 0xff) - region_aboslute_z;
			int base_x = (square_ids[i_1_] & 0xff) * 64 - region_aboslute_x;
			byte[] bs_4_ = datas[i_1_];
			if (bs_4_ != null) {
				Class48.process_audio();
				decode_client_side(MapLoader.collision_maps, bs_4_, 8 * (-6 + RSInterface.anInt1138), base_x, -125, has_underwater, (StaticMethods.anInt3279 - 6) * 8, base_y);
			}
		}
		for (int i_5_ = 0; i_0_ > i_5_; i_5_++) {
			int i_6_ = (square_ids[i_5_] >> 8) * 64 - region_aboslute_z;
			int i_7_ = (0xff & square_ids[i_5_]) * 64 - region_aboslute_x;
			byte[] bs_8_ = datas[i_5_];
			if (bs_8_ == null && RSInterface.anInt1138 < 800) {
				Class48.process_audio();
				for (int i_9_ = 0; i_9_ < num_levels; i_9_++) {
					Class35.method980(-1, 64, i_9_, 64, i_6_, i_7_);
				}
			}
		}
	}

	public static final void decode_maps_server(boolean has_underwater) {
		byte num_levels;
		byte[][] datas;
		if (GLManager.opengl_mode && has_underwater) {
			num_levels = 1;
			datas = square_umap_data;
		} else {
			datas = square_map_data;
			num_levels = 4;
		}
		for (int i_1_ = 0; i_1_ < num_levels; i_1_++) {
			Class48.process_audio();
			for (int i_2_ = 0; i_2_ < 13; i_2_++) {
				for (int i_3_ = 0; i_3_ < 13; i_3_++) {
					boolean bool_4_ = false;
					int i_5_ = MapRegion.localRegions[i_1_][i_2_][i_3_];
					if ((i_5_ ^ 0xffffffff) != 0) {
						int i_6_ = 0x3 & i_5_ >> 24;
						if (!has_underwater || (i_6_ ^ 0xffffffff) == -1) {
							int i_7_ = i_5_ >> 1 & 0x3; // Z
							int i_8_ = i_5_ >> 14 & 0x3ff;
							int i_9_ = (i_5_ & 0x3fff) >> 3;
							int i_10_ = (i_8_ / 8 << 8) + i_9_ / 8;
							for (int i_11_ = 0; (i_11_ ^ 0xffffffff) > (square_ids.length ^ 0xffffffff); i_11_++) {
								if (i_10_ == square_ids[i_11_] && datas[i_11_] != null) {
									decode_server_side(8 * (0x7 & i_8_), has_underwater, i_1_, (byte) 11, i_7_, datas[i_11_], MapLoader.collision_maps, i_2_ * 8, 8 * i_3_, i_6_, (0x7 & i_9_) * 8);
									bool_4_ = true;
									break;
								}
							}
						}
					}
					if (!bool_4_) {
						Class35.method980(-1, 8, i_1_, 8, 8 * i_2_, 8 * i_3_);
					}
				}
			}
		}
	}

	public static void decode_locs_client(boolean underwater) {
		int num_squares = square_map_data.length;
		byte[][] datas;
		if (GLManager.opengl_mode && underwater) {
			datas = square_uloc_data;
		} else {
			datas = square_loc_data;
		}
		for (int index = 0; index < num_squares; index++) {
			byte[] data = datas[index];
			if (data != null) {
				int absolute_x = -region_aboslute_x + (square_ids[index] & 0xff) * 64;
				int absolute_z = -region_aboslute_z + (square_ids[index] >> 8) * 64;
				Class48.process_audio();
				MapRegion.decode_locs(data, absolute_x, absolute_z, MapLoader.collision_maps, underwater);
			}
		}
	}

	public static final void decode_locs_server(boolean has_underwater) {
		byte num_levels;
		byte[][] datas;
		if (GLManager.opengl_mode && has_underwater) {
			datas = square_uloc_data;
			num_levels = 1;
		} else {
			num_levels = 4;
			datas = square_loc_data;
		}
		for (int i_3_ = 0; (i_3_ ^ 0xffffffff) > (num_levels ^ 0xffffffff); i_3_++) {
			Class48.process_audio();
			for (int i_4_ = 0; i_4_ < 13; i_4_++) {
				for (int i_5_ = 0; i_5_ < 13; i_5_++) {
					int i_6_ = MapRegion.localRegions[i_3_][i_4_][i_5_];
					if ((i_6_ ^ 0xffffffff) != 0) {
						int i_7_ = (0x3cb6356 & i_6_) >> 24;
						if (!has_underwater || (i_7_ ^ 0xffffffff) == -1) {
							int i_8_ = (i_6_ & 0xffdacd) >> 14;
							int i_9_ = (i_6_ & 0x3ffa) >> 3;
							int i_10_ = (i_8_ / 8 << 8) - -(i_9_ / 8);
							int i_11_ = (i_6_ & 0x7) >> 1;
							for (int i_12_ = 0; (square_ids.length ^ 0xffffffff) < (i_12_ ^ 0xffffffff); i_12_++) {
								if (square_ids[i_12_] == i_10_ && datas[i_12_] != null) {
									MapRegion.parseLandscape(i_3_, i_11_, 8 * (0x7 & i_9_), 8 * (0x7 & i_8_), 8 * i_4_, has_underwater, i_7_, MapLoader.collision_maps, 8 * i_5_, -1137131194, datas[i_12_]);
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	public static void setup_culling() {
		int i_101_ = 1;
		int i_102_ = 2;
		int i_103_ = 4;
		for (int i_104_ = 0; i_104_ < 4; i_104_++) {
			if ((i_104_ ^ 0xffffffff) < -1) {
				i_102_ <<= 3;
				i_101_ <<= 3;
				i_103_ <<= 3;
			}
			for (int i_105_ = 0; (i_104_ ^ 0xffffffff) <= (i_105_ ^ 0xffffffff); i_105_++) {
				for (int i_106_ = 0; i_106_ <= 104; i_106_++) {
					for (int i_107_ = 0; i_107_ <= 104; i_107_++) {
						if ((i_101_ & cullings_map[i_105_][i_107_][i_106_] ^ 0xffffffff) != -1) {
							int i_108_;
							for (i_108_ = i_106_; i_108_ < 104 && (i_101_ & cullings_map[i_105_][i_107_][i_108_ + 1] ^ 0xffffffff) != -1; i_108_++) {
								/* empty */
							}
							int i_109_;
							for (i_109_ = i_106_; (i_109_ ^ 0xffffffff) < -1 && (cullings_map[i_105_][i_107_][-1 + i_109_] & i_101_) != 0; i_109_--) {
								/* empty */
							}
							int i_110_;
							while_108_: for (i_110_ = i_105_; (i_110_ ^ 0xffffffff) < -1; i_110_--) {
								for (int i_111_ = i_109_; (i_108_ ^ 0xffffffff) <= (i_111_ ^ 0xffffffff); i_111_++) {
									if ((i_101_ & cullings_map[-1 + i_110_][i_107_][i_111_] ^ 0xffffffff) == -1) {
										break while_108_;
									}
								}
							}
							int i_112_;
							while_109_: for (i_112_ = i_105_; (i_104_ ^ 0xffffffff) < (i_112_ ^ 0xffffffff); i_112_++) {
								for (int i_113_ = i_109_; (i_108_ ^ 0xffffffff) <= (i_113_ ^ 0xffffffff); i_113_++) {
									if ((cullings_map[i_112_ - -1][i_107_][i_113_] & i_101_ ^ 0xffffffff) == -1) {
										break while_109_;
									}
								}
							}
							int i_114_ = (-i_110_ + i_112_ - -1) * (-i_109_ + i_108_ - -1);
							if (i_114_ >= 8) {
								int i_115_ = 240;
								int i_116_ = Scene.current_heightmap[i_110_][i_107_][i_109_];
								int i_117_ = Scene.current_heightmap[i_112_][i_107_][i_109_] + -i_115_;
								Scene.create_culling_cluster(i_104_, 1, TileConstants.SIZE_1BY1 * i_107_, i_107_ * TileConstants.SIZE_1BY1, i_109_ * TileConstants.SIZE_1BY1, TileConstants.SIZE_1BY1 * i_108_ + TileConstants.SIZE_1BY1, i_117_, i_116_);
								for (int i_118_ = i_110_; i_112_ >= i_118_; i_118_++) {
									for (int i_119_ = i_109_; (i_108_ ^ 0xffffffff) <= (i_119_ ^ 0xffffffff); i_119_++) {
										cullings_map[i_118_][i_107_][i_119_] = MathUtils.bitAnd(cullings_map[i_118_][i_107_][i_119_], i_101_ ^ 0xffffffff);
									}
								}
							}
						}
						if ((i_102_ & cullings_map[i_105_][i_107_][i_106_] ^ 0xffffffff) != -1) {
							int i_120_;
							for (i_120_ = i_107_; (i_120_ ^ 0xffffffff) < -1 && (i_102_ & cullings_map[i_105_][i_120_ - 1][i_106_] ^ 0xffffffff) != -1; i_120_--) {
								/* empty */
							}
							int i_121_ = i_105_;
							int i_122_;
							for (i_122_ = i_107_; i_122_ < 104 && (cullings_map[i_105_][1 + i_122_][i_106_] & i_102_ ^ 0xffffffff) != -1; i_122_++) {
								/* empty */
							}
							int i_123_ = i_105_;
							while_110_: for (/**/; i_121_ > 0; i_121_--) {
								for (int i_124_ = i_120_; i_124_ <= i_122_; i_124_++) {
									if ((cullings_map[-1 + i_121_][i_124_][i_106_] & i_102_ ^ 0xffffffff) == -1) {
										break while_110_;
									}
								}
							}
							while_111_: for (/**/; i_104_ > i_123_; i_123_++) {
								for (int i_125_ = i_120_; i_122_ >= i_125_; i_125_++) {
									if ((i_102_ & cullings_map[1 + i_123_][i_125_][i_106_] ^ 0xffffffff) == -1) {
										break while_111_;
									}
								}
							}
							int i_126_ = (-i_121_ + 1 + i_123_) * (1 + i_122_ - i_120_);
							if (i_126_ >= 8) {
								int i_127_ = 240;
								int i_128_ = -i_127_ + Scene.current_heightmap[i_123_][i_120_][i_106_];
								int i_129_ = Scene.current_heightmap[i_121_][i_120_][i_106_];
								Scene.create_culling_cluster(i_104_, 2, i_120_ * 128, 128 + i_122_ * 128, 128 * i_106_, 128 * i_106_, i_128_, i_129_);
								for (int i_130_ = i_121_; i_123_ >= i_130_; i_130_++) {
									for (int i_131_ = i_120_; i_131_ <= i_122_; i_131_++) {
										cullings_map[i_130_][i_131_][i_106_] = MathUtils.bitAnd(cullings_map[i_130_][i_131_][i_106_], i_102_ ^ 0xffffffff);
									}
								}
							}
						}
						if ((i_103_ & cullings_map[i_105_][i_107_][i_106_]) != 0) {
							int i_132_ = i_107_;
							int i_133_ = i_107_;
							int i_134_ = i_106_;
							int i_135_;
							for (i_135_ = i_106_; i_135_ < 104; i_135_++) {
								if ((i_103_ & cullings_map[i_105_][i_107_][1 + i_135_] ^ 0xffffffff) == -1) {
									break;
								}
							}
							for (/**/; i_134_ > 0; i_134_--) {
								if ((cullings_map[i_105_][i_107_][i_134_ - 1] & i_103_ ^ 0xffffffff) == -1) {
									break;
								}
							}
							while_112_: for (/**/; (i_132_ ^ 0xffffffff) < -1; i_132_--) {
								for (int i_136_ = i_134_; (i_135_ ^ 0xffffffff) <= (i_136_ ^ 0xffffffff); i_136_++) {
									if ((cullings_map[i_105_][i_132_ - 1][i_136_] & i_103_) == 0) {
										break while_112_;
									}
								}
							}
							while_113_: for (/**/; i_133_ < 104; i_133_++) {
								for (int i_137_ = i_134_; i_137_ <= i_135_; i_137_++) {
									if ((cullings_map[i_105_][1 + i_133_][i_137_] & i_103_) == 0) {
										break while_113_;
									}
								}
							}
							if ((i_135_ + -i_134_ + 1) * (1 + -i_132_ + i_133_) >= 4) {
								int i_138_ = Scene.current_heightmap[i_105_][i_132_][i_134_];
								Scene.create_culling_cluster(i_104_, 4, i_132_ * 128, i_133_ * 128 - -128, i_134_ * 128, 128 * i_135_ + 128, i_138_, i_138_);
								for (int i_139_ = i_132_; (i_133_ ^ 0xffffffff) <= (i_139_ ^ 0xffffffff); i_139_++) {
									for (int i_140_ = i_134_; i_140_ <= i_135_; i_140_++) {
										cullings_map[i_105_][i_139_][i_140_] = MathUtils.bitAnd(cullings_map[i_105_][i_139_][i_140_], i_103_ ^ 0xffffffff);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static int method729(int var1, int var2) {
		if (var1 == -2) {
			return 12345678;
		} else if (~var1 == 0) {
			if (2 > var2) {
				var2 = 2;
			} else if (~var2 < -127) {
				var2 = 126;
			}

			return var2;
		} else {
			var2 = (127 & var1) * var2 >> 7;
			if (var2 < 2) {
				var2 = 2;
			} else if (var2 > 126) {
				var2 = 126;
			}
			return (var1 & 0xff80) - -var2;
		}
	}

	public static int repack_hsl_lightness(int hsl, int lightness) {
		if (0 == ~hsl) {
			return 12345678;
		} else {
			lightness = lightness * (127 & hsl) >> 7;
			if (2 <= lightness) {
				if (126 < lightness) {
					lightness = 126;
				}
			} else {
				lightness = 2;
			}
			return lightness + ('\uff80' & hsl);
		}
	}

	public static int get_average_height(int[][] heights, int x, int y, int vertex_x, int vertex_y) {
		int corner1 = vertex_x * heights[1 + x][y] + (128 - vertex_x) * heights[x][y] >> 7;
		int corner2 = heights[x][1 + y] * (-vertex_x + 128) + heights[x - -1][y - -1] * vertex_x >> 7;
		return corner1 * (128 + -vertex_y) - -(vertex_y * corner2) >> 7;
	}

	public static int repackHSL(int hsl, int lightness) {
		lightness = lightness * (hsl & 127) >> 7;
		if (lightness < 2) {
			lightness = 2;
		} else if (lightness > 126) {
			lightness = 126;
		}

		return (hsl & '\uff80') + lightness;
	}

	public static int repackHSL2(int lightness, int hsl) {
		lightness = lightness * (hsl & 127) >> 7;
		if (lightness < 2) {
			lightness = 2;
		} else if (lightness > 126) {
			lightness = 126;
		}

		return (hsl & '\uff80') + lightness;
	}

	public static void method1213(int var0, OpenGLTile[] var1) {
		Scene.current_opengl_tiles[var0] = var1;
	}

	public static void add_shadows(float[][] var0, byte[][] var1, byte[][] var2, FlickeringEffect[] var3, int var4, int var5, float[][] var6, byte[][] var7, byte[][] var8, int[][] var9, int var10, float[][] var11) {
		for (int var12 = 0; ~var12 > ~var5; ++var12) {
			FlickeringEffect var13 = var3[var12];
			if (var13.level == var4) {
				int var15 = 0;
				PointLight var14 = new PointLight(var13);
				int var16 = -var13.radius + (var13.x >> 7);
				int var17 = -var13.radius + (var13.y >> 7);
				if (-1 < ~var17) {
					var15 -= var17;
					var17 = 0;
				}
				int var18 = var13.radius + (var13.y >> 7);
				if (var18 > 103) {
					var18 = 103;
				}
				for (int var19 = var17; ~var18 <= ~var19; ++var19) {
					int var20 = var13.ranges[var15];
					int var21 = var16 + (var20 >> 8);
					int var22 = -1 + var21 - -(255 & var20);
					if (103 < var22) {
						var22 = 103;
					}
					if (-1 < ~var21) {
						var21 = 0;
					}
					for (int var23 = var21; var23 <= var22; ++var23) {
						int var24 = 255 & var1[var23][var19];
						int var25 = 255 & var8[var23][var19];
						boolean var26 = false;
						FloType var27;
						int[] var29;
						int[] var28;
						if (0 == var24) {
							if (-1 == ~var25) {
								continue;
							}
							var27 = FloTypeList.list(var25 + -1);
							if (0 == ~var27.tile_colour) {
								continue;
							}
							if (~var7[var23][var19] != -1) {
								var28 = underlays_vertices[var7[var23][var19]];
								var14.total_indices += 3 * (-2 + (var28.length >> 1));
								var14.total_vertices += var28.length >> 1;
								continue;
							}
						} else if (-1 != ~var25) {
							var27 = FloTypeList.list(var25 - 1);
							byte var42;
							if (var27.tile_colour == -1) {
								var42 = var7[var23][var19];
								if (-1 != ~var42) {
									var29 = overlays_vertices[var42];
									var14.total_indices += 3 * (-2 + (var29.length >> 1));
									var14.total_vertices += var29.length >> 1;
								}
								continue;
							}
							var42 = var7[var23][var19];
							if (0 != var42) {
								var26 = true;
							}
						}
						InteractiveEntity var40 = Scene.getInteractiveEntity(var4, var23, var19);
						if (null != var40) {
							int var41 = (int) (var40.uid >> 14) & 63;
							if (~var41 == -10) {
								var29 = null;
								int var30 = 3 & (int) (var40.uid >> 20);
								if ((1 & var30) != 0) {
									boolean var31 = var21 <= -1 + var23;
									boolean var32 = ~(var23 + 1) >= ~var22;
									if (!var31 && -1 + var19 >= var17) {
										int var33 = var13.ranges[-1 + var15];
										int var34 = (var33 >> 8) + var16;
										int var35 = var34 + (255 & var33);
										var31 = ~var34 > ~var23 && ~var23 > ~var35;
									}
									if (!var32 && ~(1 + var19) >= ~var18) {
										int var33 = var13.ranges[var15 + 1];
										int var34 = (var33 >> 8) + var16;
										int var35 = var34 - -(255 & var33);
										var32 = ~var34 > ~var23 && ~var35 < ~var23;
									}
									if (var31 && var32) {
										var29 = underlays_vertices[0];
									} else if (!var31) {
										if (var32) {
											var29 = underlays_vertices[1];
										}
									} else {
										var29 = underlays_vertices[1];
									}
								} else {
									boolean var32 = var22 >= 1 + var23;
									boolean var31 = var23 + -1 >= var21;
									if (!var31 && ~(var19 - -1) >= ~var18) {
										int var33 = var13.ranges[1 + var15];
										int var34 = var16 + (var33 >> 8);
										int var35 = var34 + (255 & var33);
										var31 = var34 < var23 && var23 < var35;
									}
									if (!var32 && -1 + var19 >= var17) {
										int var33 = var13.ranges[var15 + -1];
										int var34 = var16 + (var33 >> 8);
										int var35 = var34 - -(var33 & 255);
										var32 = var23 > var34 && ~var23 > ~var35;
									}
									if (var31 && var32) {
										var29 = underlays_vertices[0];
									} else if (var31) {
										var29 = underlays_vertices[1];
									} else if (var32) {
										var29 = underlays_vertices[1];
									}
								}
								if (null != var29) {
									var14.total_indices += 3 * (var29.length >> 1) - 6;
									var14.total_vertices += var29.length >> 1;
								}
								continue;
							}
						}
						if (var26) {
							var29 = overlays_vertices[var7[var23][var19]];
							var28 = underlays_vertices[var7[var23][var19]];
							var14.total_indices += (-2 + (var28.length >> 1)) * 3;
							var14.total_indices += ((var29.length >> 1) - 2) * 3;
							var14.total_vertices += var28.length >> 1;
							var14.total_vertices += var29.length >> 1;
						} else {
							var28 = underlays_vertices[0];
							var14.total_indices += (-2 + (var28.length >> 1)) * 3;
							var14.total_vertices += var28.length >> 1;
						}
					}
					++var15;
				}
				var15 = 0;
				var14.initialise();
				if (-1 < ~(-var13.radius + (var13.y >> 7))) {
					var15 -= -var13.radius + (var13.y >> 7);
				}
				for (int var19 = var17; var19 <= var18; ++var19) {
					int var20 = var13.ranges[var15];
					int var21 = (var20 >> 8) + var16;
					int var22 = -1 + (255 & var20) + var21;
					if (~var22 < -104) {
						var22 = 103;
					}
					if (0 > var21) {
						var21 = 0;
					}
					for (int var23 = var21; ~var23 >= ~var22; ++var23) {
						int var43 = 255 & var8[var23][var19];
						int var25 = 255 & var1[var23][var19];
						byte var38 = var2[var23][var19];
						boolean var39 = false;
						FloType var46;
						if (var25 != 0) {
							if (~var43 != -1) {
								var46 = FloTypeList.list(-1 + var43);
								if (-1 == var46.tile_colour) {
									method284(var0, var9, var23, var6, var19, overlays_vertices[var7[var23][var19]], var14, (byte) 116, var13, var11, var2[var23][var19]);
									continue;
								}

								byte var48 = var7[var23][var19];
								if (var48 != 0) {
									var39 = true;
								}
							}
						} else {
							if (0 == var43) {
								continue;
							}
							var46 = FloTypeList.list(var43 - 1);
							if (-1 == var46.tile_colour) {
								continue;
							}
							if (var7[var23][var19] != 0) {
								method284(var0, var9, var23, var6, var19, underlays_vertices[var7[var23][var19]], var14, (byte) -88, var13, var11, var2[var23][var19]);
								continue;
							}
						}
						InteractiveEntity var44 = Scene.getInteractiveEntity(var4, var23, var19);
						if (null != var44) {
							int var49 = (int) (var44.uid >> 14) & 63;
							if (9 == var49) {
								int[] var45 = null;
								int var47 = 3 & (int) (var44.uid >> 20);
								if ((1 & var47) != 0) {
									boolean var32 = var23 - 1 >= var21;
									boolean var51 = var22 >= 1 + var23;
									if (!var32 && var17 <= var19 - 1) {
										int var50 = var13.ranges[var15 - 1];
										int var35 = var16 + (var50 >> 8);
										int var36 = (255 & var50) + var35;
										var32 = var23 > var35 && var36 > var23;
									}
									if (!var51 && ~var18 <= ~(var19 + 1)) {
										int var50 = var13.ranges[var15 - -1];
										int var35 = var16 + (var50 >> 8);
										int var36 = (255 & var50) + var35;
										var51 = var23 > var35 && ~var36 < ~var23;
									}
									if (var32 && var51) {
										var45 = underlays_vertices[0];
									} else if (!var32) {
										if (var51) {
											var45 = underlays_vertices[1];
											var38 = 2;
										}
									} else {
										var38 = 0;
										var45 = underlays_vertices[1];
									}
								} else {
									boolean var32 = ~(-1 + var23) <= ~var21;
									boolean var51 = ~var22 <= ~(var23 + 1);
									if (!var32 && var18 >= var19 - -1) {
										int var50 = var13.ranges[1 + var15];
										int var35 = (var50 >> 8) + var16;
										int var36 = (var50 & 255) + var35;
										var32 = var23 > var35 && ~var36 < ~var23;
									}
									if (!var51 && var19 - 1 >= var17) {
										int var50 = var13.ranges[-1 + var15];
										int var35 = var16 + (var50 >> 8);
										int var36 = (255 & var50) + var35;
										var51 = var35 < var23 && ~var36 < ~var23;
									}
									if (var32 && var51) {
										var45 = underlays_vertices[0];
									} else if (var32) {
										var45 = underlays_vertices[1];
										var38 = 1;
									} else if (var51) {
										var45 = underlays_vertices[1];
										var38 = 3;
									}
								}
								if (null != var45) {
									method284(var0, var9, var23, var6, var19, var45, var14, (byte) 98, var13, var11, var38);
								}
								continue;
							}
						}
						if (var39) {
							method284(var0, var9, var23, var6, var19, overlays_vertices[var7[var23][var19]], var14, (byte) 96, var13, var11, var2[var23][var19]);
							method284(var0, var9, var23, var6, var19, underlays_vertices[var7[var23][var19]], var14, (byte) -117, var13, var11, var2[var23][var19]);
						} else {
							method284(var0, var9, var23, var6, var19, underlays_vertices[0], var14, (byte) 61, var13, var11, var38);
						}
					}
					++var15;
				}
				if (~var14.num_vertices < -1 && var14.num_indices > 0) {
					var14.bake();
					var13.point_light = var14;
				}
			}
		}
	}

	public static void method284(float[][] var0, int[][] var1, int var2, float[][] var3, int var4, int[] var5, PointLight var6, byte var7, FlickeringEffect var8, float[][] var9, int var10) {
		int[] var11 = new int[var5.length / 2];
		int var12;
		for (var12 = 0; ~var12 > ~var11.length; ++var12) {
			int var13 = var5[var12 + var12];
			int var14 = var5[var12 + var12 + 1];
			int var15;
			if (-2 != ~var10) {
				if (-3 != ~var10) {
					if (~var10 == -4) {
						var15 = var13;
						var13 = 128 - var14;
						var14 = var15;
					}
				} else {
					var14 = -var14 + 128;
					var13 = -var13 + 128;
				}
			} else {
				var15 = var13;
				var13 = var14;
				var14 = -var15 + 128;
			}

			float var17;
			float var16;
			float var24;
			if (-1 == ~var13 && var14 == 0) {
				var16 = var3[var2][var4];
				var24 = var9[var2][var4];
				var17 = var0[var2][var4];
			} else if (~var13 == -129 && -1 == ~var14) {
				var17 = var0[var2 - -1][var4];
				var24 = var9[1 + var2][var4];
				var16 = var3[var2 - -1][var4];
			} else if (128 == var13 && ~var14 == -129) {
				var16 = var3[var2 + 1][var4 + 1];
				var24 = var9[var2 + 1][var4 + 1];
				var17 = var0[var2 - -1][var4 + 1];
			} else if (var13 == 0 && 128 == var14) {
				var17 = var0[var2][1 + var4];
				var16 = var3[var2][var4 + 1];
				var24 = var9[var2][1 + var4];
			} else {
				var24 = var9[var2][var4];
				var17 = var0[var2][var4];
				float var18 = var13 / 128.0F;
				var16 = var3[var2][var4];
				var16 += (-var16 + var3[1 + var2][var4]) * var18;
				var17 += var18 * (var0[1 + var2][var4] - var17);
				var24 += (-var24 + var9[var2 - -1][var4]) * var18;
				float var20 = var9[var2][1 + var4];
				var20 += (var9[var2 - -1][var4 - -1] - var20) * var18;
				float var21 = var3[var2][1 + var4];
				float var19 = var14 / 128.0F;
				var24 += (-var24 + var20) * var19;
				float var22 = var0[var2][1 + var4];
				var22 += (var0[1 + var2][var4 + 1] - var22) * var18;
				var21 += (-var21 + var3[var2 + 1][1 + var4]) * var18;
				var16 += (-var16 + var21) * var19;
				var17 += (var22 - var17) * var19;
			}

			int var26 = (var2 << 7) + var13;
			int var25 = (var4 << 7) - -var14;
			int var27 = get_average_height(var1, var2, var4, var13, var14);
			var11[var12] = var6.add_vertex(var26, var27, var25, var24, var16, var17);
		}

		var6.add_indices(var11);
		var12 = 105 % ((-20 - var7) / 54);
	}
}
