package com.rs2.client.scene;

import static jaggl.GLConstants.GL_COLOR_ARRAY;
import static jaggl.GLConstants.GL_DEPTH_TEST;
import static jaggl.GLConstants.GL_FOG_START;
import static jaggl.OpenGL.glColor4f;
import static jaggl.OpenGL.glDisable;
import static jaggl.OpenGL.glDisableClientState;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glEnableClientState;
import static jaggl.OpenGL.glFogf;
import static jaggl.OpenGL.glPopAttrib;
import static jaggl.OpenGL.glPopMatrix;
import static jaggl.OpenGL.glPushAttrib;
import static jaggl.OpenGL.glPushMatrix;
import static jaggl.OpenGL.glTranslatef;

import com.jagex.*;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.effects.OpenGLEffects;
import com.jagex.graphics.runetek4.opengl.effects.WaterDepthEffect;
import com.jagex.graphics.runetek4.opengl.effects.WaterEffect;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.jagex.graphics.runetek4.opengl.terrain.OpenGLTile;
import com.rs2.client.scene.environment.SceneEnvironment;
import com.rs2.client.scene.light.SceneLighting;
import com.rs2.client.scene.shadow.SceneShadowMapper;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

/**
 * @author Walied K. Yassen
 */
public class Scene {

	// scene container
	public static NodeDeque projectiles = new NodeDeque();
	// component bounding
	public static RSInterface scene_component;
	public static int scene_draw_x = -1;
	public static int scene_draw_y = -1;
	public static int render_cycle;
	// effective dimensions
	public static int effective_x = 0;
	public static int effective_y = 0;
	public static int effective_width = 0;
	public static int effective_height = 0;
	// zoom
	public static short zoomx = 256;
	public static short zoomy = 320;
	// field of view
	public static int fov = 0;
	public static short fovx = 256;
	public static short fovy = 205;
	public static short fov_minx = 1;
	public static short fov_maxx = 32767;
	public static short fov_miny = 1;
	public static short fov_maxy = 32767;
	// scene data
	public static int num_processed_cullings = 0;
	public static int visible_tiles;
	public static int visible_to_xnegative;
	public static int visible_to_ynegative;
	public static int visible_to_xpositive;
	public static int visible_to_ypositive;
	public static int visible_level_start;
	public static int visible_level_end;
	public static boolean[][] tileOnScreen;
	public static boolean[][] adjacentTileOnScreen;
	public static int[] anIntArray548;
	public static int[] anIntArray1433;
	public static int[] anIntArray3285;
	public static int[] anIntArray3427;
	public static int[] anIntArray1455;
	public static int width;
	public static int height;
	public static boolean object_selected;
	public static int num_rendered;;
	public static Ground[][][] current_grounds;
	public static CullingCluster[] processed_cullings;
	public static CullingCluster[] active_cullings;
	public static int num_active_cullings;
	public static int num_offscreen_entities;
	public static byte[][][] lighting;
	public static InteractiveEntity[] onscreen_entities;
	public static InteractiveEntity[] offscreen_entities;
	public static int[][] anIntArrayArray3115;
	public static Ground[][][] underwater_grounds;
	public static Ground[][][] surface_grounds;
	public static int[][][] current_heightmap;

	// underwater

	// surface

	// current
	public static int[][][] underwater_heightmap;
	public static int[][][] surface_heightmap;
	public static OpenGLTile[][] current_opengl_tiles;
	public static OpenGLTile[][] surface_opengl_tiles;
	public static OpenGLTile[][] underwater_opengl_tiles;
	public static int bound_water_fog_colour = -1;
	public static int bound_water_depth_scale = -1;

	public static void initialize_scene(int planes, int width, int height, int visible_tiles, boolean underwater) {
		Scene.width = width;
		Scene.height = height;
		Scene.visible_tiles = visible_tiles;
		Scene.surface_grounds = new Ground[planes][Scene.width][Scene.height];
		Scene.surface_heightmap = new int[planes][Scene.width + 1][Scene.height + 1];
		if (GLManager.opengl_mode) {
			Scene.surface_opengl_tiles = new OpenGLTile[4][];
		}
		if (underwater) {
			Scene.underwater_grounds = new Ground[1][Scene.width][Scene.height];
			Scene.anIntArrayArray3115 = new int[Scene.width][Scene.height];
			Scene.underwater_heightmap = new int[1][Scene.width + 1][Scene.height + 1];
			if (GLManager.opengl_mode) {
				Scene.underwater_opengl_tiles = new OpenGLTile[1][];
			}
		} else {
			Scene.underwater_grounds = null;
			Scene.anIntArrayArray3115 = null;
			Scene.underwater_heightmap = null;
			Scene.underwater_opengl_tiles = null;
		}
		Scene.load_floor(false);
		Scene.active_cullings = new CullingCluster[500];
		Scene.num_active_cullings = 0;
		Scene.processed_cullings = new CullingCluster[500];
		Scene.num_processed_cullings = 0;
		Scene.anIntArrayArrayArray1142 = new int[planes][Scene.width + 1][Scene.height + 1];
		Scene.offscreen_entities = new InteractiveEntity[5000];
		Scene.num_offscreen_entities = 0;
		Scene.onscreen_entities = new InteractiveEntity[100];
		Scene.adjacentTileOnScreen = new boolean[Scene.visible_tiles + Scene.visible_tiles + 1][Scene.visible_tiles + Scene.visible_tiles + 1];
		Scene.tileOnScreen = new boolean[Scene.visible_tiles + Scene.visible_tiles + 2][Scene.visible_tiles + Scene.visible_tiles + 2];
		Scene.lighting = new byte[planes][Scene.width][Scene.height];
	}

	public static void draw_component(int layout_x, int layout_y, int layout_width, int layout_height, boolean resizable) {
		render_cycle++;
		if (!resizable) {
			process_players(true);
			process_npcs(true);
			process_players(false);
		}
		process_npcs(false);
		if (!resizable) {
			Scene.process_projectiles();
		}
		Scene.process_spots();
		if (GLManager.opengl_mode) {
			Scene.calculate_effective_size(layout_x, layout_y, layout_width, layout_height, true);
			layout_x = effective_x;
			layout_y = effective_y;
			layout_width = effective_width;
			layout_height = effective_height;
		}
		if (!Camera.cameraViewChanged) {
			int i_9_ = Camera.cameraRotationZ;
			int i_10_ = 0x7ff & Class35.cameraDirection + StaticMethods.anInt2923;
			if (i_9_ < WallDecoration.anInt365 / 256) {
				i_9_ = WallDecoration.anInt365 / 256;
			}
			if (ISAACPacket.aBooleanArray3531[4] && 128 + Class79.anIntArray1890[4] > i_9_) {
				i_9_ = Class79.anIntArray1890[4] + 128;
			}
			Camera.set_camera_pos(Camera.currentCameraDisplayY, i_9_, -50 + Scene.get_average_height(ObjType.localHeight, GameClient.currentPlayer.bound_extents_x, GameClient.currentPlayer.bound_extents_z), i_10_, 3 * i_9_ + Camera.ZOOM, Camera.currentCameraDisplayX, layout_height);
		}
		int plane;
		if (Camera.cameraViewChanged) {
			plane = UpdateServerThread.method92(false);
		} else {
			plane = FaceNormal.method1107(-175122297);
		}
		int x_camera_pos = Camera.xCameraPos;
		int y_camera_pos = Camera.yCameraPos;
		int z_camera_pos = Camera.zCameraPos;
		int x_camera_curve = Camera.xCameraCurve;
		int y_camera_curve = Camera.yCameraCurve;
		for (int i_17_ = 0; i_17_ < 5; i_17_++) {
			if (ISAACPacket.aBooleanArray3531[i_17_]) {
				int i_18_ = (int) ((1 + StaticMethods.anIntArray2597[i_17_] * 2) * Math.random() - StaticMethods.anIntArray2597[i_17_] + Math.sin(StaticMethods.anIntArray3128[i_17_] * (StaticMethods2.anIntArray2768[i_17_] / 100.0)) * Class79.anIntArray1890[i_17_]);
				if (i_17_ == 0) {
					Camera.xCameraPos += i_18_;
				}
				if (i_17_ == 1) {
					Camera.zCameraPos += i_18_;
				}
				if (i_17_ == 2) {
					Camera.yCameraPos += i_18_;
				}
				if (i_17_ == 3) {
					Camera.xCameraCurve = i_18_ + Camera.xCameraCurve & 0x7ff;
				}
				if (i_17_ == 4) {
					Camera.yCameraCurve += i_18_;
					if (Camera.yCameraCurve < 128) {
						Camera.yCameraCurve = 128;
					}
					if (Camera.yCameraCurve > 383) {
						Camera.yCameraCurve = 383;
					}
				}
			}
		}
		RoofCulling.method725(-118);
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		if (GLManager.opengl_mode) {
			GLState.set_clipping(layout_x, layout_y, layout_x + layout_width, layout_y - -layout_height);
			float x_curve = Camera.yCameraCurve * 0.17578125F;
			float y_curve = Camera.xCameraCurve * 0.17578125F;
			GLState.setup_perspective_viewport(layout_x, layout_y, layout_width, layout_height, layout_width / 2 + layout_x, layout_y + layout_height / 2, x_curve, y_curve, Scene.fov, Scene.fov);
		} else {
			Rasterizer2D.set_clipping(layout_x, layout_y, layout_x - -layout_width, layout_y - -layout_height);
			rasterizer.clip_reset();
		}
		int i_19_ = Mouse.mouseY;
		int i_20_ = Mouse.mouseX;
		if (layout_x > i_20_ || (i_20_ ^ 0xffffffff) <= (layout_x - -layout_width ^ 0xffffffff) || i_19_ < layout_y || (i_19_ ^ 0xffffffff) <= (layout_height + layout_y ^ 0xffffffff)) {
			object_selected = false;
			num_rendered = 0;
		} else {
			object_selected = true;
			num_rendered = 0;
			int left = Viewport.screen_left;
			int right = Viewport.screen_right;
			int top = Viewport.screen_top;
			int bottom = Viewport.screen_bottom;
			Viewport.anInt3878 = left + (right - left) * (Mouse.mouseX - layout_x) / layout_width;
			Viewport.anInt1973 = top + (bottom - top) * (Mouse.mouseY - layout_y) / layout_height;
		}
		Class48.process_audio();
		byte var19 = -3 != ~RoofCulling.get_culling_type((byte) 70) ? 1 : (byte) Scene.render_cycle;

		if (GLManager.opengl_mode) {
			GLState.load_lighting_state();
			GLState.set_depthtest_enabled(true);
			GLState.set_fog_enabled(true);
			int sky_colour;
			if (GameClient.clientState == 10) {
				sky_colour = SceneEnvironment.process_atmosphere(Camera.xCameraPos >> 10, Camera.yCameraPos >> 10, GameClient.timer, ClientOptions.clientoption_brightness);
			} else {
				sky_colour = SceneEnvironment.process_atmosphere(GameClient.currentPlayer.waypointsX[0] >> 3, GameClient.currentPlayer.waypointsY[0] >> 3, GameClient.timer, ClientOptions.clientoption_brightness);
			}
			SceneLighting.method1269(GameClient.timer, !ClientOptions.clientoption_flickering_on);
			GLState.clear_screen(sky_colour);
			WaterEffect.updater_watereffect(Camera.xCameraPos, Camera.zCameraPos, Camera.yCameraPos, Camera.yCameraCurve, Camera.xCameraCurve);
			OpenGLEffects.animation_offset = GameClient.timer;
			Scene.draw_scene(Camera.xCameraPos, Camera.zCameraPos, Camera.yCameraPos, Camera.yCameraCurve, Camera.xCameraCurve, RoofCulling.tile_settings, RoofCulling.anIntArray686, RoofCulling.anIntArray2696, RoofCulling.anIntArray2021, RoofCulling.anIntArray3959, RoofCulling.anIntArray1871, ObjType.localHeight + 1, var19, GameClient.currentPlayer.bound_extents_x >> 7, GameClient.currentPlayer.bound_extents_z >> 7);
			opengl_scene_dirty = true;
			SceneLighting.disable_all();
			WaterEffect.updater_watereffect(0, 0, 0, 0, 0);
			Class48.process_audio();
			StaticMethods.method414();
			StaticMethods2.method1526(layout_x, layout_y, layout_width, layout_height, Scene.fov, Scene.fov);
			Queue.updateHintIcons(layout_x, layout_y, layout_width, layout_height, Scene.fov, Scene.fov);
		} else {
			Rasterizer2D.fill_rectangle(layout_x, layout_y, layout_width, layout_height, 0);
			Scene.draw_scene(Camera.xCameraPos, Camera.zCameraPos, Camera.yCameraPos, Camera.yCameraCurve, Camera.xCameraCurve, RoofCulling.tile_settings, RoofCulling.anIntArray686, RoofCulling.anIntArray2696, RoofCulling.anIntArray2021, RoofCulling.anIntArray3959, RoofCulling.anIntArray1871, ObjType.localHeight + 1, var19, GameClient.currentPlayer.bound_extents_x >> 7, GameClient.currentPlayer.bound_extents_z >> 7);
			Class48.process_audio();
			StaticMethods.method414();
			StaticMethods2.method1526(layout_x, layout_y, layout_width, layout_height, 256, 256);
			Queue.updateHintIcons(layout_x, layout_y, layout_width, layout_height, 256, 256);
		}
		// TODO: texture cache
		GameClient.draw_cross(layout_width, layout_y, layout_height, -2, layout_x);
		Camera.xCameraPos = x_camera_pos;
		Camera.yCameraCurve = y_camera_curve;
		Camera.xCameraCurve = x_camera_curve;
		Camera.zCameraPos = z_camera_pos;
		Camera.yCameraPos = y_camera_pos;
		if (StaticMethods.aBoolean3413 && GameClient.js5_client.get_priority_request_count() == 0) {
			StaticMethods.aBoolean3413 = false;
		}
		if (StaticMethods.aBoolean3413) {
			if (GLManager.opengl_mode) {
				GLShapes.fill_rectangle(layout_x, layout_y, layout_width, layout_height, 0);
			} else {
				Rasterizer2D.fill_rectangle(layout_x, layout_y, layout_width, layout_height, 0);
			}
			GameClient.drawLoadingText(RSInterface.aClass16_1139, false);
		}
		if (!StaticMethods.aBoolean3413 && !ContextMenu.menuOpen && (i_20_ ^ 0xffffffff) <= (layout_x ^ 0xffffffff) && (i_20_ ^ 0xffffffff) > (layout_x + layout_width ^ 0xffffffff) && (i_19_ ^ 0xffffffff) <= (layout_y ^ 0xffffffff) && i_19_ < layout_y - -layout_height) {
			EntityUpdating.setEntityActions(layout_y, layout_height, i_20_, layout_x, (byte) 37, layout_width, i_19_);
		}
	}

	public static void draw_scene(int xCampos, int zCampos, int yCampos, int pitch, int yaw, byte[][][] renderable_tiles, int[] var6, int[] var7, int[] var8, int[] var9, int[] var10, int roof_plane, byte roof_target, int local_x, int local_y) {
		if (xCampos < 0) {
			xCampos = 0;
		} else if (xCampos >= Scene.width * TileConstants.SIZE_1BY1) {
			xCampos = Scene.width * TileConstants.SIZE_1BY1 - 1;
		}
		if (yCampos < 0) {
			yCampos = 0;
		} else if (yCampos >= Scene.height * TileConstants.SIZE_1BY1) {
			yCampos = Scene.height * TileConstants.SIZE_1BY1 - 1;
		}
		Camera.yCurveSine = Rasterizer.SINE[pitch];
		Camera.yCurveCosine = Rasterizer.COSINE[pitch];
		Camera.xCurveSine = Rasterizer.SINE[yaw];
		Camera.xCurveCosine = Rasterizer.COSINE[yaw];
		Camera.xCameraPosition = xCampos;
		Camera.zCameraPosition = zCampos;
		Camera.yCameraPosition = yCampos;
		Camera.xCameraGridPosition = xCampos / TileConstants.SIZE_1BY1;
		Camera.yCameraGridPosition = yCampos / TileConstants.SIZE_1BY1;
		Scene.visible_to_xnegative = Camera.xCameraGridPosition - Scene.visible_tiles;
		if (Scene.visible_to_xnegative < 0) {
			Scene.visible_to_xnegative = 0;
		}
		Scene.visible_to_ynegative = Camera.yCameraGridPosition - Scene.visible_tiles;
		if (Scene.visible_to_ynegative < 0) {
			Scene.visible_to_ynegative = 0;
		}
		Scene.visible_to_xpositive = Camera.xCameraGridPosition + Scene.visible_tiles;
		if (Scene.visible_to_xpositive > Scene.width) {
			Scene.visible_to_xpositive = Scene.width;
		}
		Scene.visible_to_ypositive = Camera.yCameraGridPosition + Scene.visible_tiles;
		if (Scene.visible_to_ypositive > Scene.height) {
			Scene.visible_to_ypositive = Scene.height;
		}
		int far = GLManager.opengl_mode ? 3584 : 3500;
		for (int x = 0; x < Scene.visible_tiles + Scene.visible_tiles + 2; x++) {
			for (int y = 0; y < Scene.visible_tiles + Scene.visible_tiles + 2; y++) {
				int sceneX = (x - Scene.visible_tiles << 7) - (Camera.xCameraPosition & 0x7f);
				int sceneZ = (y - Scene.visible_tiles << 7) - (Camera.yCameraPosition & 0x7f);
				int xLoc = Camera.xCameraGridPosition - visible_tiles + x;
				int yLoc = Camera.yCameraGridPosition - visible_tiles + y;
				if (xLoc >= 0 && yLoc >= 0 && xLoc < Scene.width && yLoc < Scene.height) {
					int lowerZ;
					if (Scene.underwater_heightmap != null) {
						lowerZ = Scene.underwater_heightmap[0][xLoc][yLoc] - Camera.zCameraPosition + TileConstants.SIZE_1BY1;
					} else {
						lowerZ = Scene.surface_heightmap[0][xLoc][yLoc] - Camera.zCameraPosition + TileConstants.SIZE_1BY1;
					}
					int upperZ = Scene.surface_heightmap[3][xLoc][yLoc] - Camera.zCameraPosition - 1000;
					Scene.tileOnScreen[x][y] = SceneController.isOnScreen(sceneX, upperZ, lowerZ, sceneZ, far);
				} else {
					Scene.tileOnScreen[x][y] = false;
				}
			}
		}
		for (int i_19_ = 0; i_19_ < visible_tiles + visible_tiles + 1; i_19_++) {
			for (int i_20_ = 0; i_20_ < visible_tiles + visible_tiles + 1; i_20_++) {
				Scene.adjacentTileOnScreen[i_19_][i_20_] = Scene.tileOnScreen[i_19_][i_20_] || Scene.tileOnScreen[i_19_ + 1][i_20_] || Scene.tileOnScreen[i_19_][i_20_ + 1] || Scene.tileOnScreen[i_19_ + 1][i_20_ + 1];
			}
		}
		Scene.anIntArray548 = var6;
		Scene.anIntArray1433 = var7;
		Scene.anIntArray3285 = var8;
		Scene.anIntArray3427 = var9;
		Scene.anIntArray1455 = var10;
		Scene.process_culling();
		if (underwater_grounds != null) {
			load_floor(true);
			Scene.draw_tiles(xCampos, zCampos, yCampos, null, 0, (byte) 0, local_x, local_y);
			if (GLManager.opengl_mode) {
				OpenGLEffects.disable_effects = false;
				OpenGLEffects.setup_effect(0, 0);
				OpenGLEnvironment.upload_fog_colour(null);
				SceneLighting.disable_all();
			}
			load_floor(false);
		}
		Scene.draw_tiles(xCampos, zCampos, yCampos, renderable_tiles, roof_plane, roof_target, local_x, local_y);
	}

	public static void draw_tiles(int xcamera, int ycamera, int zcamera, byte[][][] var3, int var4, byte roof_target, int local_x, int local_y) {
		Class73.anInt1321++;
		Mouse.anInt221 = 0;
		int min_x = local_x - 16;
		int max_x = local_x + 16;
		int min_y = local_y - 16;
		int max_y = local_y + 16;
		for (int level = visible_level_start; level < visible_level_end; level++) {
			Ground[][] grounds = current_grounds[level];
			for (int x = visible_to_xnegative; x < visible_to_xpositive; x++) {
				for (int y = visible_to_ynegative; y < visible_to_ypositive; y++) {
					Ground ground = grounds[x][y];
					if (ground != null) {
						if (adjacentTileOnScreen[x - Camera.xCameraGridPosition + visible_tiles][y - Camera.yCameraGridPosition + visible_tiles] && (var3 == null || level < var4 || var3[level][x][y] != roof_target)) {
							ground.aBoolean2021 = true;
							ground.aBoolean2036 = true;
							if (ground.num_interactives > 0) {
								ground.aBoolean2033 = true;
							} else {
								ground.aBoolean2033 = false;
							}
							Mouse.anInt221++;
						} else {
							ground.aBoolean2021 = false;
							ground.aBoolean2036 = false;
							ground.anInt2015 = 0;
							if (x >= min_x && x <= max_x && y >= min_y && y <= max_y) {
								if (ground.wall_object != null) {
									WallObject object = ground.wall_object;
									object.first_node.update_shadows(0, level, object.anInt1469, object.position_x, object.position_y);
									if (object.second_node != null) {
										object.second_node.update_shadows(0, level, object.anInt1469, object.position_x, object.position_y);
									}
								}
								if (ground.wall_decoration != null) {
									WallDecoration decoration = ground.wall_decoration;
									decoration.first_node.update_shadows(decoration.anInt374, level, decoration.anInt370, decoration.position_x, decoration.position_y);
									if (decoration.second_node != null) {
										decoration.second_node.update_shadows(decoration.anInt374, level, decoration.anInt370, decoration.position_x, decoration.position_y);
									}
								}
								if (ground.decoration != null) {
									GroundDecoration decoration = ground.decoration;
									decoration.node.update_shadows(0, level, decoration.position_y, decoration.position_x, decoration.position_y);
								}
								if (ground.interactives != null) {
									for (int index = 0; index < ground.num_interactives; ++index) {
										InteractiveEntity entity = ground.interactives[index];
										entity.node.update_shadows(entity.anInt612, level, entity.anInt608, entity.anInt602, entity.anInt610);
									}
								}
							}
						}
					}
				}
			}
		}
		boolean underwater = current_heightmap == underwater_heightmap;
		if (GLManager.opengl_mode) {
			glPushMatrix();
			glTranslatef(-xcamera, -ycamera, -zcamera);
			if (underwater) {
				render_underwater_tiles();
				OpenGLEffects.setup_effect(3, -1);
				OpenGLEffects.disable_effects = true;
				WaterDepthEffect.bind();
				Scene.bound_water_fog_colour = -1;
				Scene.bound_water_depth_scale = -1;
				for (int var14 = 0; var14 < current_opengl_tiles[0].length; ++var14) {
					OpenGLTile var28 = current_opengl_tiles[0][var14];
					float var26 = 251.5F - (var28.has_underwater ? 1.0F : 0.5F);
					if (var28.underwater_colour != Scene.bound_water_fog_colour) {
						Scene.bound_water_fog_colour = var28.underwater_colour;
						WaterDepthEffect.set_water_fog_colour(var28.underwater_colour);
						OpenGLEnvironment.upload_fog_colour(WaterDepthEffect.calculate_water_environment_colour());
					}

					var28.render(current_grounds, var26, false);
				}
				WaterDepthEffect.unbind();
			} else {
				for (int var14 = Scene.visible_level_start; var14 < Scene.visible_level_end; ++var14) {
					for (int var15 = 0; var15 < current_opengl_tiles[var14].length; ++var15) {
						OpenGLTile var25 = current_opengl_tiles[var14][var15];
						float var33 = 201.5F - 50.0F * var14 - (var25.has_underwater ? 1.0F : 0.5F);
						if (var25.textureid != -1 && GraphicTools.get_materials().get_material(var25.textureid).effect_type == 4 && ClientOptions.clientoption_highdetails_water) {
							WaterDepthEffect.set_water_fog_colour(var25.underwater_colour);
						}
						var25.render(current_grounds, var33, false);
					}
					if (var14 == 0 && ClientOptions.clientoption_hardshadows > 0) {
						GLState.update_depth_range(101.5F);
						SceneShadowMapper.drawShadows(Camera.xCameraGridPosition, Camera.yCameraGridPosition, visible_tiles, ycamera, adjacentTileOnScreen, current_heightmap[0]);
					}
				}
				SceneLighting.draw_point_lights(Camera.xCameraGridPosition, Camera.yCameraGridPosition, current_grounds);
			}
			glPopMatrix();
		}

		for (int i_27_ = Scene.visible_level_start; i_27_ < Scene.visible_level_end; i_27_++) {
			Ground[][] grounds = Scene.current_grounds[i_27_];
			for (int i_28_ = -visible_tiles; i_28_ <= 0; i_28_++) {
				int xpositive = Camera.xCameraGridPosition + i_28_;
				int xnegative = Camera.xCameraGridPosition - i_28_;
				if (xpositive >= visible_to_xnegative || xnegative < visible_to_xpositive) {
					for (int i_31_ = -visible_tiles; i_31_ <= 0; i_31_++) {
						int ypositive = Camera.yCameraGridPosition + i_31_;
						int ynegative = Camera.yCameraGridPosition - i_31_;
						if (xpositive >= visible_to_xnegative) {
							if (ypositive >= visible_to_ynegative) {
								Ground ground = grounds[xpositive][ypositive];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, true);
								}
							}
							if (ynegative < visible_to_ypositive) {
								Ground ground = grounds[xpositive][ynegative];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, true);
								}
							}
						}
						if (xnegative < visible_to_xpositive) {
							if (ypositive >= visible_to_ynegative) {
								Ground ground = grounds[xnegative][ypositive];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, true);
								}
							}
							if (ynegative < visible_to_ypositive) {
								Ground ground = grounds[xnegative][ynegative];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, true);
								}
							}
						}
						if (Mouse.anInt221 == 0) {
							if (!underwater) {
								Scene.pick_mouseover_tile = false;
							}
							return;
						}
					}
				}
			}
		}
		for (int i_34_ = Scene.visible_level_start; i_34_ < Scene.visible_level_end; i_34_++) {
			Ground[][] grounds = Scene.current_grounds[i_34_];
			for (int i_35_ = -visible_tiles; i_35_ <= 0; i_35_++) {
				int xpositive = Camera.xCameraGridPosition + i_35_;
				int xnegative = Camera.xCameraGridPosition - i_35_;
				if (xpositive >= visible_to_xnegative || xnegative < visible_to_xpositive) {
					for (int i_38_ = -visible_tiles; i_38_ <= 0; i_38_++) {
						int ypositive = Camera.yCameraGridPosition + i_38_;
						int ynegative = Camera.yCameraGridPosition - i_38_;
						if (xpositive >= visible_to_xnegative) {
							if (ypositive >= visible_to_ynegative) {
								Ground ground = grounds[xpositive][ypositive];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, false);
								}
							}
							if (ynegative < visible_to_ypositive) {
								Ground ground = grounds[xpositive][ynegative];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, false);
								}
							}
						}
						if (xnegative < visible_to_xpositive) {
							if (ypositive >= visible_to_ynegative) {
								Ground ground = grounds[xnegative][ypositive];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, false);
								}
							}
							if (ynegative < visible_to_ypositive) {
								Ground ground = grounds[xnegative][ynegative];
								if (ground != null && ground.aBoolean2021) {
									Scene.draw_tile(ground, false);
								}
							}
						}
						if (Mouse.anInt221 == 0) {
							if (!underwater) {
								Scene.pick_mouseover_tile = false;
							}
							return;
						}
					}
				}
			}
		}
		Scene.pick_mouseover_tile = false;
	}

	public static void render_underwater_tiles() {
		glDisableClientState(GL_COLOR_ARRAY);
		GLState.set_lights_enabled(false);
		glDisable(GL_DEPTH_TEST);
		glPushAttrib(128);
		glFogf(GL_FOG_START, 3072.0F);
		GLState.disable_depthmask();
		for (int var1 = 0; var1 < surface_opengl_tiles[0].length; ++var1) {
			OpenGLTile var2 = surface_opengl_tiles[0][var1];
			if (var2.textureid >= 0 && GraphicTools.get_materials().get_material(var2.textureid).effect_type == 4) {
				float[] colors = WaterDepthEffect.update_environment_colour_underwater(var2.underwater_colour);
				glColor4f(colors[0], colors[1], colors[2], colors[3]);
				float var3 = 201.5F - (var2.has_underwater ? 1.0F : 0.5F);
				var2.render(current_grounds, var3, true);
			}
		}
		glEnableClientState(GL_COLOR_ARRAY);
		GLState.load_lighting_state();
		glEnable(GL_DEPTH_TEST);
		glPopAttrib();
		GLState.enable_depthmask();
	}

	public static void draw_tile(Ground ground, boolean bool) {

		Class73.aClass89_1316.add_last(ground);
		for (;;) {
			Ground var2 = (Ground) Class73.aClass89_1316.remove_first();
			if (var2 == null) {
				break;
			}
			if (var2.aBoolean2036) {
				int grid_x = var2.grid_x;
				int grid_y = var2.grid_y;
				int grid_level = var2.grid_level;
				int someshit = var2.anInt2017;
				float var8 = 0.0F;
				Ground[][] class23_sub1s = Scene.current_grounds[grid_level];
				if (GLManager.opengl_mode) {
					if (underwater_heightmap == current_heightmap) {
						int var9 = anIntArrayArray3115[grid_x][grid_y];
						int var10 = var9 & 16777215;
						if (var10 != Scene.bound_water_fog_colour) {
							Scene.bound_water_fog_colour = var10;
							WaterDepthEffect.set_water_fog_colour(var10);
							OpenGLEnvironment.upload_fog_colour(WaterDepthEffect.calculate_water_environment_colour());
						}
						int var11 = var9 >>> 24 << 3;
						if (var11 != Scene.bound_water_depth_scale) {
							Scene.bound_water_depth_scale = var11;
							WaterDepthEffect.set_water_depthscale(var11);
						}
						int var12 = surface_heightmap[0][grid_x][grid_y] + surface_heightmap[0][grid_x + 1][grid_y] + surface_heightmap[0][grid_x][grid_y + 1] + surface_heightmap[0][grid_x + 1][grid_y + 1] >> 2;
						OpenGLEffects.setup_effect(3, -var12);
						var8 = 201.5F;
						GLState.update_depth_range(var8);
					} else {
						var8 = 201.5F - 50.0F * (someshit + 1);
						GLState.update_depth_range(var8);
					}
				}
				if (var2.aBoolean2021) {
					if (bool) {
						if (grid_level > 0) {
							Ground class23_sub1_4_ = Scene.current_grounds[grid_level - 1][grid_x][grid_y];
							if (class23_sub1_4_ != null && class23_sub1_4_.aBoolean2036) {
								continue;
							}
						}
						if (grid_x <= Camera.xCameraGridPosition && grid_x > visible_to_xnegative) {
							Ground class23_sub1_5_ = class23_sub1s[grid_x - 1][grid_y];
							if (class23_sub1_5_ != null && class23_sub1_5_.aBoolean2036 && (class23_sub1_5_.aBoolean2021 || (var2.flags & 0x1) == 0)) {
								continue;
							}
						}
						if (grid_x >= Camera.xCameraGridPosition && grid_x < visible_to_xpositive - 1) {
							Ground class23_sub1_6_ = class23_sub1s[grid_x + 1][grid_y];
							if (class23_sub1_6_ != null && class23_sub1_6_.aBoolean2036 && (class23_sub1_6_.aBoolean2021 || (var2.flags & 0x4) == 0)) {
								continue;
							}
						}
						if (grid_y <= Camera.yCameraGridPosition && grid_y > visible_to_ynegative) {
							Ground class23_sub1_7_ = class23_sub1s[grid_x][grid_y - 1];
							if (class23_sub1_7_ != null && class23_sub1_7_.aBoolean2036 && (class23_sub1_7_.aBoolean2021 || (var2.flags & 0x8) == 0)) {
								continue;
							}
						}
						if (grid_y >= Camera.yCameraGridPosition && grid_y < visible_to_ypositive - 1) {
							Ground class23_sub1_8_ = class23_sub1s[grid_x][grid_y + 1];
							if (class23_sub1_8_ != null && class23_sub1_8_.aBoolean2036 && (class23_sub1_8_.aBoolean2021 || (var2.flags & 0x2) == 0)) {
								continue;
							}
						}
					} else {
						bool = true;
					}
					var2.aBoolean2021 = false;
					if (var2.aClass23_Sub1_2022 != null) {
						Ground var21 = var2.aClass23_Sub1_2022;
						if (GLManager.opengl_mode) {
							GLState.update_depth_range(201.5F - 50.0F * (var21.anInt2017 + 1));
						}
						if (var21.plain_tile != null) {
							if (!method846(0, grid_x, grid_y)) {
								render_plain_tile(var21.plain_tile, 0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, false);
							} else {
								render_plain_tile(var21.plain_tile, 0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, true);
							}
						} else if (var21.shaped_tile != null) {
							if (!method846(0, grid_x, grid_y)) {
								render_shaped_tile(var21.shaped_tile, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, false);
							} else {
								render_shaped_tile(var21.shaped_tile, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, true);
							}
						}
						WallObject wall_object = var21.wall_object;
						if (wall_object != null) {
							if (GLManager.opengl_mode) {
								if ((wall_object.anInt1055 & var2.anInt2241) != 0) {
									SceneLighting.update_visible_lights(wall_object.anInt1055, Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, someshit, grid_x, grid_y);
								} else {
									SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
								}
							}
							wall_object.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, wall_object.position_x - Camera.xCameraPosition, wall_object.anInt1469 - Camera.zCameraPosition, wall_object.position_y - Camera.yCameraPosition, wall_object.uid, 0);
						}
						for (int i_10_ = 0; i_10_ < var21.num_interactives; i_10_++) {
							InteractiveEntity interactiveObject = var21.interactives[i_10_];
							if (interactiveObject != null) {
								if (GLManager.opengl_mode) {
									SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
								}
								int id = 0x7fffffff & (int) (interactiveObject.uid >>> 32);
								interactiveObject.node.draw2(interactiveObject.anInt612, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, interactiveObject.anInt602 - Camera.xCameraPosition, interactiveObject.anInt608 - Camera.zCameraPosition, interactiveObject.anInt610 - Camera.yCameraPosition, interactiveObject.uid, id > 65000 ? 0 : 8);
							}
						}
						if (GLManager.opengl_mode) {
							GLState.update_depth_range(var8);
						}
					}
					boolean var22 = false;
					if (var2.plain_tile != null) {
						if (!Scene.method846(someshit, grid_x, grid_y)) {
							var22 = true;
							if (var2.plain_tile.color3 != 12345678 || Scene.pick_mouseover_tile && grid_level <= Class71_Sub3.anInt2742) {
								render_plain_tile(var2.plain_tile, someshit, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, false);
							}
						} else {
							render_plain_tile(var2.plain_tile, someshit, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, true);
						}
					} else if (var2.shaped_tile != null) {
						if (!Scene.method846(someshit, grid_x, grid_y)) {
							var22 = true;
							render_shaped_tile(var2.shaped_tile, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, false);
						} else {
							render_shaped_tile(var2.shaped_tile, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, grid_x, grid_y, true);
						}
					}
					if (var22) {
						GroundDecoration decoration = var2.decoration;
						if (decoration != null && (decoration.uid & 2147483648L) != 0L) {
							if (GLManager.opengl_mode && decoration.aBoolean329) {
								GLState.update_depth_range(var8 + 50.0F - 1.5F);
							}
							if (GLManager.opengl_mode) {
								SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
							}
							decoration.node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, decoration.position_x - Camera.xCameraPosition, decoration.anInt1210 - Camera.zCameraPosition, decoration.position_y - Camera.yCameraPosition, decoration.uid, 0);
							if (GLManager.opengl_mode && decoration.aBoolean329) {
								GLState.update_depth_range(var8);
							}
						}
						if (GLManager.opengl_mode) {
							GLState.update_depth_range(var8);
						}
					}
					int i_12_ = 0;
					int i_13_ = 0;
					WallObject var26 = var2.wall_object;
					WallDecoration wall_decoration = var2.wall_decoration;
					if (var26 != null || wall_decoration != null) {
						if (Camera.xCameraGridPosition == grid_x) {
							i_12_++;
						} else if (Camera.xCameraGridPosition < grid_x) {
							i_12_ += 2;
						}
						if (Camera.yCameraGridPosition == grid_y) {
							i_12_ += 3;
						} else if (Camera.yCameraGridPosition > grid_y) {
							i_12_ += 6;
						}
						i_13_ = StaticMethods2.anIntArray2039[i_12_];
						var2.anInt2241 = Class25.anIntArray382[i_12_];
					}
					if (var26 != null) {
						if ((var26.anInt1055 & Mouse.anIntArray224[i_12_]) != 0) {
							if (var26.anInt1055 == 16) {
								var2.anInt2015 = 3;
								var2.anInt2027 = Class54.anIntArray858[i_12_];
								var2.anInt2014 = 3 - var2.anInt2027;
							} else if (var26.anInt1055 == 32) {
								var2.anInt2015 = 6;
								var2.anInt2027 = Class71.anIntArray1271[i_12_];
								var2.anInt2014 = 6 - var2.anInt2027;
							} else if (var26.anInt1055 == 64) {
								var2.anInt2015 = 12;
								var2.anInt2027 = CS2CallFrame.anIntArray774[i_12_];
								var2.anInt2014 = 12 - var2.anInt2027;
							} else {
								var2.anInt2015 = 9;
								var2.anInt2027 = ForceMovement.anIntArray398[i_12_];
								var2.anInt2014 = 9 - var2.anInt2027;
							}
						} else {
							var2.anInt2015 = 0;
						}
						if ((var26.anInt1055 & i_13_) != 0 && !SceneController.method226(someshit, grid_x, grid_y, var26.anInt1055)) {
							if (GLManager.opengl_mode) {
								SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
							}
							var26.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, var26.position_x - Camera.xCameraPosition, var26.anInt1469 - Camera.zCameraPosition, var26.position_y - Camera.yCameraPosition, var26.uid, 0);
						}
						if ((var26.anInt1059 & i_13_) != 0 && !SceneController.method226(someshit, grid_x, grid_y, var26.anInt1059)) {
							if (GLManager.opengl_mode) {
								SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
							}
							var26.second_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, var26.position_x - Camera.xCameraPosition, var26.anInt1469 - Camera.zCameraPosition, var26.position_y - Camera.yCameraPosition, var26.uid, 0);
						}
					}
					if (wall_decoration != null && !StaticMethods.method811(someshit, grid_x, grid_y, wall_decoration.first_node.get_miny())) {
						if (GLManager.opengl_mode) {
							GLState.update_depth_range(var8 - 0.5F);
						}
						if ((wall_decoration.anInt378 & i_13_) != 0) {
							if (GLManager.opengl_mode) {
								SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
							}
							wall_decoration.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, wall_decoration.position_x - Camera.xCameraPosition + wall_decoration.anInt375, wall_decoration.anInt370 - Camera.zCameraPosition, wall_decoration.position_y - Camera.yCameraPosition + wall_decoration.anInt371, wall_decoration.uid, 15);
						} else if (wall_decoration.anInt378 == 256) {
							int i_14_ = wall_decoration.position_x - Camera.xCameraPosition;
							int i_15_ = wall_decoration.anInt370 - Camera.zCameraPosition;
							int i_16_ = wall_decoration.position_y - Camera.yCameraPosition;
							int i_17_ = wall_decoration.anInt374;
							int i_18_;
							if (i_17_ == 1 || i_17_ == 2) {
								i_18_ = -i_14_;
							} else {
								i_18_ = i_14_;
							}
							int i_19_;
							if (i_17_ == 2 || i_17_ == 3) {
								i_19_ = -i_16_;
							} else {
								i_19_ = i_16_;
							}
							if (i_19_ < i_18_) {
								if (GLManager.opengl_mode) {
									SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
								}
								wall_decoration.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, i_14_ + wall_decoration.anInt375, i_15_, i_16_ + wall_decoration.anInt371, wall_decoration.uid, 15);
							} else if (wall_decoration.second_node != null) {
								if (GLManager.opengl_mode) {
									SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
								}
								wall_decoration.second_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, i_14_, i_15_, i_16_, wall_decoration.uid, 15);
							}
						}
						if (GLManager.opengl_mode) {
							GLState.update_depth_range(var8);
						}
					}
					if (var22) {
						GroundDecoration decoration = var2.decoration;
						if (decoration != null && (decoration.uid & 2147483648L) == 0L) {
							if (GLManager.opengl_mode && decoration.aBoolean329) {
								GLState.update_depth_range(var8 + 50.0F - 1.5F);
							}

							if (GLManager.opengl_mode) {
								SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
							}
							decoration.node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, decoration.position_x - Camera.xCameraPosition, decoration.anInt1210 - Camera.zCameraPosition, decoration.position_y - Camera.yCameraPosition, decoration.uid, 15);
							if (GLManager.opengl_mode && decoration.aBoolean329) {
								GLState.update_depth_range(var8);
							}
						}
						GroundObjEntity obj_entity = var2.obj_entity;
						if (obj_entity != null && obj_entity.anInt709 == 0) {
							if (GLManager.opengl_mode) {
								SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
							}
							if (obj_entity.second_node != null) {
								obj_entity.second_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, obj_entity.position_x - Camera.xCameraPosition, obj_entity.anInt703 - Camera.zCameraPosition, obj_entity.position_y - Camera.yCameraPosition, obj_entity.aLong711, 5);
							}
							if (obj_entity.third_node != null) {
								obj_entity.third_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, obj_entity.position_x - Camera.xCameraPosition, obj_entity.anInt703 - Camera.zCameraPosition, obj_entity.position_y - Camera.yCameraPosition, obj_entity.aLong711, 5);
							}
							if (obj_entity.first_node != null) {
								obj_entity.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, obj_entity.position_x - Camera.xCameraPosition, obj_entity.anInt703 - Camera.zCameraPosition, obj_entity.position_y - Camera.yCameraPosition, obj_entity.aLong711, 5);
							}
						}
					}
					int i_20_ = var2.flags;
					if (i_20_ != 0) {
						if (grid_x < Camera.xCameraGridPosition && (i_20_ & 0x4) != 0) {
							Ground class23_sub1_21_ = class23_sub1s[grid_x + 1][grid_y];
							if (class23_sub1_21_ != null && class23_sub1_21_.aBoolean2036) {
								Class73.aClass89_1316.add_last(class23_sub1_21_);
							}
						}
						if (grid_y < Camera.yCameraGridPosition && (i_20_ & 0x2) != 0) {
							Ground class23_sub1_22_ = class23_sub1s[grid_x][grid_y + 1];
							if (class23_sub1_22_ != null && class23_sub1_22_.aBoolean2036) {
								Class73.aClass89_1316.add_last(class23_sub1_22_);
							}
						}
						if (grid_x > Camera.xCameraGridPosition && (i_20_ & 0x1) != 0) {
							Ground class23_sub1_23_ = class23_sub1s[grid_x - 1][grid_y];
							if (class23_sub1_23_ != null && class23_sub1_23_.aBoolean2036) {
								Class73.aClass89_1316.add_last(class23_sub1_23_);
							}
						}
						if (grid_y > Camera.yCameraGridPosition && (i_20_ & 0x8) != 0) {
							Ground class23_sub1_24_ = class23_sub1s[grid_x][grid_y - 1];
							if (class23_sub1_24_ != null && class23_sub1_24_.aBoolean2036) {
								Class73.aClass89_1316.add_last(class23_sub1_24_);
							}
						}
					}
				}
				if (var2.anInt2015 != 0) {
					boolean bool_25_ = true;
					for (int i_26_ = 0; i_26_ < var2.num_interactives; i_26_++) {
						if (var2.interactives[i_26_].anInt614 != Class73.anInt1321 && (var2.anIntArray2024[i_26_] & var2.anInt2015) == var2.anInt2027) {
							bool_25_ = false;
							break;
						}
					}
					if (bool_25_) {
						WallObject wall_object = var2.wall_object;
						if (!SceneController.method226(someshit, grid_x, grid_y, wall_object.anInt1055)) {
							if (GLManager.opengl_mode) {
								label736: {
									if ((wall_object.uid & 1032192L) == 16384L) {
										int var11 = wall_object.position_x - Camera.xCameraPosition;
										int var12 = wall_object.position_y - Camera.yCameraPosition;
										int var27 = (int) (wall_object.uid >> 20 & 3L);
										if (var27 == 0) {
											var11 -= 64;
											var12 += 64;
											if (var12 < var11 && grid_x > 0 && grid_y < height - 1) {
												SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x - 1, grid_y + 1);
												break label736;
											}
										} else if (var27 == 1) {
											var11 += 64;
											var12 += 64;
											if (var12 < -var11 && grid_x < width - 1 && grid_y < height - 1) {
												SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x + 1, grid_y + 1);
												break label736;
											}
										} else if (var27 == 2) {
											var11 += 64;
											var12 -= 64;
											if (var12 > var11 && grid_x < width - 1 && grid_y > 0) {
												SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x + 1, grid_y - 1);
												break label736;
											}
										} else if (var27 == 3) {
											var11 -= 64;
											var12 -= 64;
											if (var12 > -var11 && grid_x > 0 && grid_y > 0) {
												SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x - 1, grid_y - 1);
												break label736;
											}
										}
									}
									SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
								}
							}
							wall_object.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, wall_object.position_x - Camera.xCameraPosition, wall_object.anInt1469 - Camera.zCameraPosition, wall_object.position_y - Camera.yCameraPosition, wall_object.uid, 0);
						}
						var2.anInt2015 = 0;
					}
				}
				if (var2.aBoolean2033) {
					try {
						int i_27_ = var2.num_interactives;
						var2.aBoolean2033 = false;
						int i_28_ = 0;
						while_10_: for (int i_29_ = 0; i_29_ < i_27_; i_29_++) {
							InteractiveEntity interactiveObject = var2.interactives[i_29_];
							if (interactiveObject.anInt614 != Class73.anInt1321) {
								for (int i_30_ = interactiveObject.anInt601; i_30_ <= interactiveObject.anInt613; i_30_++) {
									for (int i_31_ = interactiveObject.anInt607; i_31_ <= interactiveObject.anInt599; i_31_++) {
										Ground class23_sub1_32_ = class23_sub1s[i_30_][i_31_];
										if (class23_sub1_32_.aBoolean2021) {
											var2.aBoolean2033 = true;
											continue while_10_;
										}
										if (class23_sub1_32_.anInt2015 != 0) {
											int i_33_ = 0;
											if (i_30_ > interactiveObject.anInt601) {
												i_33_++;
											}
											if (i_30_ < interactiveObject.anInt613) {
												i_33_ += 4;
											}
											if (i_31_ > interactiveObject.anInt607) {
												i_33_ += 8;
											}
											if (i_31_ < interactiveObject.anInt599) {
												i_33_ += 2;
											}
											if ((i_33_ & class23_sub1_32_.anInt2015) == var2.anInt2014) {
												var2.aBoolean2033 = true;
												continue while_10_;
											}
										}
									}
								}
								Scene.onscreen_entities[i_28_++] = interactiveObject;
								int i_34_ = Camera.xCameraGridPosition - interactiveObject.anInt601;
								int i_35_ = interactiveObject.anInt613 - Camera.xCameraGridPosition;
								if (i_35_ > i_34_) {
									i_34_ = i_35_;
								}
								int i_36_ = Camera.yCameraGridPosition - interactiveObject.anInt607;
								int i_37_ = interactiveObject.anInt599 - Camera.yCameraGridPosition;
								if (i_37_ > i_36_) {
									interactiveObject.anInt611 = i_34_ + i_37_;
								} else {
									interactiveObject.anInt611 = i_34_ + i_36_;
								}
							}
						}
						while (i_28_ > 0) {
							int i_38_ = -50;
							int selectted_entity_index = -1;
							for (int i_40_ = 0; i_40_ < i_28_; i_40_++) {
								InteractiveEntity entity = Scene.onscreen_entities[i_40_];
								if (entity.anInt614 != Class73.anInt1321) {
									if (entity.anInt611 > i_38_) {
										i_38_ = entity.anInt611;
										selectted_entity_index = i_40_;
									} else if (entity.anInt611 == i_38_) {
										int i_41_ = entity.anInt602 - Camera.xCameraPosition;
										int i_42_ = entity.anInt610 - Camera.yCameraPosition;
										int i_43_ = Scene.onscreen_entities[selectted_entity_index].anInt602 - Camera.xCameraPosition;
										int i_44_ = Scene.onscreen_entities[selectted_entity_index].anInt610 - Camera.yCameraPosition;
										if (i_41_ * i_41_ + i_42_ * i_42_ > i_43_ * i_43_ + i_44_ * i_44_) {
											selectted_entity_index = i_40_;
										}
									}
								}
							}
							if (selectted_entity_index == -1) {
								break;
							}
							InteractiveEntity selected_entity = Scene.onscreen_entities[selectted_entity_index];
							selected_entity.anInt614 = Class73.anInt1321;
							if (!StaticMethods.method327(someshit, selected_entity.anInt601, selected_entity.anInt613, selected_entity.anInt607, selected_entity.anInt599, selected_entity.node.get_miny())) {
								if (GLManager.opengl_mode) {
									if ((selected_entity.uid & 1032192L) == 147456L) {
										SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
										int var14 = selected_entity.anInt602 - Camera.xCameraPosition;
										int var15 = selected_entity.anInt610 - Camera.yCameraPosition;
										int var16 = (int) (selected_entity.uid >> 20 & 3L);
										if (var16 != 1 && var16 != 3) {
											if (var15 > var14) {
												SceneLighting.method1272(grid_level, grid_x, grid_y - 1, grid_x + 1, grid_y);
											} else {
												SceneLighting.method1272(grid_level, grid_x, grid_y + 1, grid_x - 1, grid_y);
											}
										} else if (var15 > -var14) {
											SceneLighting.method1272(grid_level, grid_x, grid_y - 1, grid_x - 1, grid_y);
										} else {
											SceneLighting.method1272(grid_level, grid_x, grid_y + 1, grid_x + 1, grid_y);
										}
									} else {
										SceneLighting.method1266(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, selected_entity.anInt601, selected_entity.anInt607, selected_entity.anInt613, selected_entity.anInt599);
									}
								}
								int id = 0x7fffffff & (int) (selected_entity.uid >>> 32);
								selected_entity.node.draw2(selected_entity.anInt612, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, selected_entity.anInt602 - Camera.xCameraPosition, selected_entity.anInt608 - Camera.zCameraPosition, selected_entity.anInt610 - Camera.yCameraPosition, selected_entity.uid, id > 65000 ? 0 : 8);
							}
							for (int i_45_ = selected_entity.anInt601; i_45_ <= selected_entity.anInt613; i_45_++) {
								for (int i_46_ = selected_entity.anInt607; i_46_ <= selected_entity.anInt599; i_46_++) {
									Ground class23_sub1_47_ = class23_sub1s[i_45_][i_46_];
									if (class23_sub1_47_.anInt2015 != 0) {
										Class73.aClass89_1316.add_last(class23_sub1_47_);
									} else if ((i_45_ != grid_x || i_46_ != grid_y) && class23_sub1_47_.aBoolean2036) {
										Class73.aClass89_1316.add_last(class23_sub1_47_);
									}
								}
							}
						}
						if (var2.aBoolean2033) {
							continue;
						}
					} catch (Exception exception) {
						var2.aBoolean2033 = false;
					}
				}
				if (var2.aBoolean2036 && var2.anInt2015 == 0) {
					if (grid_x <= Camera.xCameraGridPosition && grid_x > visible_to_xnegative) {
						Ground class23_sub1_48_ = class23_sub1s[grid_x - 1][grid_y];
						if (class23_sub1_48_ != null && class23_sub1_48_.aBoolean2036) {
							continue;
						}
					}
					if (grid_x >= Camera.xCameraGridPosition && grid_x < visible_to_xpositive - 1) {
						Ground class23_sub1_49_ = class23_sub1s[grid_x + 1][grid_y];
						if (class23_sub1_49_ != null && class23_sub1_49_.aBoolean2036) {
							continue;
						}
					}
					if (grid_y <= Camera.yCameraGridPosition && grid_y > visible_to_ynegative) {
						Ground class23_sub1_50_ = class23_sub1s[grid_x][grid_y - 1];
						if (class23_sub1_50_ != null && class23_sub1_50_.aBoolean2036) {
							continue;
						}
					}
					if (grid_y >= Camera.yCameraGridPosition && grid_y < visible_to_ypositive - 1) {
						Ground class23_sub1_51_ = class23_sub1s[grid_x][grid_y + 1];
						if (class23_sub1_51_ != null && class23_sub1_51_.aBoolean2036) {
							continue;
						}
					}
					var2.aBoolean2036 = false;
					Mouse.anInt221--;
					GroundObjEntity obj_entity = var2.obj_entity;
					if (obj_entity != null && obj_entity.anInt709 != 0) {
						if (GLManager.opengl_mode) {
							SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
						}
						if (obj_entity.second_node != null) {
							obj_entity.second_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, obj_entity.position_x - Camera.xCameraPosition, obj_entity.anInt703 - Camera.zCameraPosition - obj_entity.anInt709, obj_entity.position_y - Camera.yCameraPosition, obj_entity.aLong711, 5);
						}
						if (obj_entity.third_node != null) {
							obj_entity.third_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, obj_entity.position_x - Camera.xCameraPosition, obj_entity.anInt703 - Camera.zCameraPosition - obj_entity.anInt709, obj_entity.position_y - Camera.yCameraPosition, obj_entity.aLong711, 5);
						}
						if (obj_entity.first_node != null) {
							obj_entity.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, obj_entity.position_x - Camera.xCameraPosition, obj_entity.anInt703 - Camera.zCameraPosition - obj_entity.anInt709, obj_entity.position_y - Camera.yCameraPosition, obj_entity.aLong711, 5);
						}
					}
					if (var2.anInt2241 != 0) {
						WallDecoration class24 = var2.wall_decoration;
						if (class24 != null && !StaticMethods.method811(someshit, grid_x, grid_y, class24.first_node.get_miny())) {
							if ((class24.anInt378 & var2.anInt2241) != 0) {
								if (GLManager.opengl_mode) {
									SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
								}
								class24.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, class24.position_x - Camera.xCameraPosition + class24.anInt375, class24.anInt370 - Camera.zCameraPosition, class24.position_y - Camera.yCameraPosition + class24.anInt371, class24.uid, 15);
							} else if (class24.anInt378 == 256) {
								int i_52_ = class24.position_x - Camera.xCameraPosition;
								int i_53_ = class24.anInt370 - Camera.zCameraPosition;
								int i_54_ = class24.position_y - Camera.yCameraPosition;
								int i_55_ = class24.anInt374;
								int i_56_;
								if (i_55_ == 1 || i_55_ == 2) {
									i_56_ = -i_52_;
								} else {
									i_56_ = i_52_;
								}
								int i_57_;
								if (i_55_ == 2 || i_55_ == 3) {
									i_57_ = -i_54_;
								} else {
									i_57_ = i_54_;
								}
								if (i_57_ >= i_56_) {
									if (GLManager.opengl_mode) {
										SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
									}
									class24.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, i_52_ + class24.anInt375, i_53_, i_54_ + class24.anInt371, class24.uid, 15);
								} else if (class24.second_node != null) {
									if (GLManager.opengl_mode) {
										SceneLighting.update_visible_lights(Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, grid_level, grid_x, grid_y);
									}
									class24.second_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, i_52_, i_53_, i_54_, class24.uid, 15);
								}
							}
						}
						WallObject wall_object = var2.wall_object;
						if (wall_object != null) {
							if ((wall_object.anInt1059 & var2.anInt2241) != 0 && !SceneController.method226(someshit, grid_x, grid_y, wall_object.anInt1059)) {
								if (GLManager.opengl_mode) {
									SceneLighting.update_visible_lights(wall_object.anInt1059, Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, someshit, grid_x, grid_y);
								}
								wall_object.second_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, wall_object.position_x - Camera.xCameraPosition, wall_object.anInt1469 - Camera.zCameraPosition, wall_object.position_y - Camera.yCameraPosition, wall_object.uid, 0);
							}
							if ((wall_object.anInt1055 & var2.anInt2241) != 0 && !SceneController.method226(someshit, grid_x, grid_y, wall_object.anInt1055)) {
								if (GLManager.opengl_mode) {
									SceneLighting.update_visible_lights(wall_object.anInt1055, Camera.xCameraPosition, Camera.zCameraPosition, Camera.yCameraPosition, someshit, grid_x, grid_y);
								}
								wall_object.first_node.draw2(0, Camera.yCurveSine, Camera.yCurveCosine, Camera.xCurveSine, Camera.xCurveCosine, wall_object.position_x - Camera.xCameraPosition, wall_object.anInt1469 - Camera.zCameraPosition, wall_object.position_y - Camera.yCameraPosition, wall_object.uid, 0);
							}
						}
					}
					if (grid_level < visible_level_end - 1) {
						Ground class23_sub1_58_ = Scene.current_grounds[grid_level + 1][grid_x][grid_y];
						if (class23_sub1_58_ != null && class23_sub1_58_.aBoolean2036) {
							Class73.aClass89_1316.add_last(class23_sub1_58_);
						}
					}
					if (grid_x < Camera.xCameraGridPosition) {
						Ground class23_sub1_59_ = class23_sub1s[grid_x + 1][grid_y];
						if (class23_sub1_59_ != null && class23_sub1_59_.aBoolean2036) {
							Class73.aClass89_1316.add_last(class23_sub1_59_);
						}
					}
					if (grid_y < Camera.yCameraGridPosition) {
						Ground class23_sub1_60_ = class23_sub1s[grid_x][grid_y + 1];
						if (class23_sub1_60_ != null && class23_sub1_60_.aBoolean2036) {
							Class73.aClass89_1316.add_last(class23_sub1_60_);
						}
					}
					if (grid_x > Camera.xCameraGridPosition) {
						Ground class23_sub1_61_ = class23_sub1s[grid_x - 1][grid_y];
						if (class23_sub1_61_ != null && class23_sub1_61_.aBoolean2036) {
							Class73.aClass89_1316.add_last(class23_sub1_61_);
						}
					}
					if (grid_y > Camera.yCameraGridPosition) {
						Ground class23_sub1_62_ = class23_sub1s[grid_x][grid_y - 1];
						if (class23_sub1_62_ != null && class23_sub1_62_.aBoolean2036) {
							Class73.aClass89_1316.add_last(class23_sub1_62_);
						}
					}
				}
			}
		}
	}

	public static void render_plain_tile(PlainTile var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		int var9;
		int var10 = var9 = (var6 << 7) - Camera.xCameraPosition;
		int var11;
		int var12 = var11 = (var7 << 7) - Camera.yCameraPosition;
		int var13;
		int var14 = var13 = var10 + 128;
		int var15;
		int var16 = var15 = var12 + 128;
		int var17 = Scene.current_heightmap[var1][var6][var7] - Camera.zCameraPosition;
		int var18 = Scene.current_heightmap[var1][var6 + 1][var7] - Camera.zCameraPosition;
		int var19 = Scene.current_heightmap[var1][var6 + 1][var7 + 1] - Camera.zCameraPosition;
		int var20 = Scene.current_heightmap[var1][var6][var7 + 1] - Camera.zCameraPosition;
		int var21 = var12 * var4 + var10 * var5 >> 16;
		var12 = var12 * var5 - var10 * var4 >> 16;
		var10 = var21;
		var21 = var17 * var3 - var12 * var2 >> 16;
		var12 = var17 * var2 + var12 * var3 >> 16;
		var17 = var21;
		if (var12 >= 50) {
			var21 = var11 * var4 + var14 * var5 >> 16;
			var11 = var11 * var5 - var14 * var4 >> 16;
			var14 = var21;
			var21 = var18 * var3 - var11 * var2 >> 16;
			var11 = var18 * var2 + var11 * var3 >> 16;
			var18 = var21;
			if (var11 >= 50) {
				var21 = var16 * var4 + var13 * var5 >> 16;
				var16 = var16 * var5 - var13 * var4 >> 16;
				var13 = var21;
				var21 = var19 * var3 - var16 * var2 >> 16;
				var16 = var19 * var2 + var16 * var3 >> 16;
				var19 = var21;
				if (var16 >= 50) {
					var21 = var15 * var4 + var9 * var5 >> 16;
					var15 = var15 * var5 - var9 * var4 >> 16;
					var9 = var21;
					var21 = var20 * var3 - var15 * var2 >> 16;
					var15 = var20 * var2 + var15 * var3 >> 16;
					if (var15 >= 50) {
						int var22 = rasterizer.center_x + (var10 << 9) / var12;
						int var23 = rasterizer.center_y + (var17 << 9) / var12;
						int var24 = rasterizer.center_x + (var14 << 9) / var11;
						int var25 = rasterizer.center_y + (var18 << 9) / var11;
						int var26 = rasterizer.center_x + (var13 << 9) / var16;
						int var27 = rasterizer.center_y + (var19 << 9) / var16;
						int var28 = rasterizer.center_x + (var9 << 9) / var15;
						int var29 = rasterizer.center_y + (var21 << 9) / var15;
						rasterizer.blending_alpha = 0;
						int var30;
						if ((var26 - var28) * (var25 - var29) - (var27 - var29) * (var24 - var28) > 0) {
							if (Scene.pick_mouseover_tile && isMouseWithinTriangle(StaticMethods.anInt3071 + rasterizer.center_x, GroundItem.anInt2503 + rasterizer.center_y, var27, var29, var25, var26, var28, var24)) {
								SceneController.picked_tile_x = var6;
								SceneController.picked_tile_y = var7;
							}
							if (!GLManager.opengl_mode && !var8) {
								rasterizer.clip_edges = var26 < 0 || var28 < 0 || var24 < 0 || var26 > rasterizer.clip_width || var28 > rasterizer.clip_width || var24 > rasterizer.clip_width;

								if (var0.textureid == -1) {
									if (var0.color3 != 12345678) {
										rasterizer.method1154(var27, var29, var25, var26, var28, var24, var0.color3, var0.color4, var0.color2);
									}
								} else if (ClientOptions.clientoption_textures) {
									if (var0.flat) {
										rasterizer.method1135(var27, var29, var25, var26, var28, var24, var0.color3, var0.color4, var0.color2, var10, var14, var9, var17, var18, var21, var12, var11, var15, var0.textureid);
									} else {
										rasterizer.method1135(var27, var29, var25, var26, var28, var24, var0.color3, var0.color4, var0.color2, var13, var9, var14, var19, var21, var18, var16, var15, var11, var0.textureid);
									}
								} else {
									var30 = GraphicTools.get_materials().get_material(var0.textureid).get_colour();
									rasterizer.method1154(var27, var29, var25, var26, var28, var24, repackHSL(var30, var0.color3), repackHSL(var30, var0.color4), repackHSL(var30, var0.color2));
								}
							}
						}
						if ((var22 - var24) * (var29 - var25) - (var23 - var25) * (var28 - var24) > 0) {
							if (Scene.pick_mouseover_tile && isMouseWithinTriangle(StaticMethods.anInt3071 + rasterizer.center_x, GroundItem.anInt2503 + rasterizer.center_y, var23, var25, var29, var22, var24, var28)) {
								SceneController.picked_tile_x = var6;
								SceneController.picked_tile_y = var7;
							}
							if (!GLManager.opengl_mode && !var8) {
								rasterizer.clip_edges = var22 < 0 || var24 < 0 || var28 < 0 || var22 > rasterizer.clip_width || var24 > rasterizer.clip_width || var28 > rasterizer.clip_width;
								if (var0.textureid == -1) {
									if (var0.color1 != 12345678) {
										rasterizer.method1154(var23, var25, var29, var22, var24, var28, var0.color1, var0.color2, var0.color4);
									}
								} else if (ClientOptions.clientoption_textures) {
									rasterizer.method1135(var23, var25, var29, var22, var24, var28, var0.color1, var0.color2, var0.color4, var10, var14, var9, var17, var18, var21, var12, var11, var15, var0.textureid);
								} else {
									var30 = GraphicTools.get_materials().get_material(var0.textureid).get_colour();
									rasterizer.method1154(var23, var25, var29, var22, var24, var28, repackHSL(var30, var0.color1), repackHSL(var30, var0.color2), repackHSL(var30, var0.color4));
								}
							}
						}

					}
				}
			}
		}
	}

	public static void render_shaped_tile(ShapedTile var0, int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		int var8 = var0.anIntArray627.length;
		for (int var9 = 0; var9 < var8; ++var9) {
			int var10 = var0.anIntArray627[var9] - Camera.xCameraPosition;
			int var11 = var0.anIntArray615[var9] - Camera.zCameraPosition;
			int var12 = var0.anIntArray618[var9] - Camera.yCameraPosition;
			int var13 = var12 * var3 + var10 * var4 >> 16;
			var12 = var12 * var4 - var10 * var3 >> 16;
			var10 = var13;
			var13 = var11 * var2 - var12 * var1 >> 16;
			var12 = var11 * var1 + var12 * var2 >> 16;
			if (var12 < 50) {
				return;
			}
			if (var0.anIntArray616 != null) {
				ShapedTile.anIntArray614[var9] = var10;
				ShapedTile.anIntArray630[var9] = var13;
				ShapedTile.anIntArray628[var9] = var12;
			}
			ShapedTile.anIntArray623[var9] = rasterizer.center_x + (var10 << 9) / var12;
			ShapedTile.anIntArray622[var9] = rasterizer.center_y + (var13 << 9) / var12;
		}
		rasterizer.blending_alpha = 0;
		var8 = var0.anIntArray624.length;
		for (int var9 = 0; var9 < var8; ++var9) {
			int var10 = var0.anIntArray624[var9];
			int var11 = var0.anIntArray617[var9];
			int var12 = var0.anIntArray613[var9];
			int var13 = ShapedTile.anIntArray623[var10];
			int var14 = ShapedTile.anIntArray623[var11];
			int var15 = ShapedTile.anIntArray623[var12];
			int var16 = ShapedTile.anIntArray622[var10];
			int var17 = ShapedTile.anIntArray622[var11];
			int var18 = ShapedTile.anIntArray622[var12];
			if ((var13 - var14) * (var18 - var17) - (var16 - var17) * (var15 - var14) > 0) {
				if (Scene.pick_mouseover_tile && isMouseWithinTriangle(StaticMethods.anInt3071 + rasterizer.center_x, GroundItem.anInt2503 + rasterizer.center_y, var16, var17, var18, var13, var14, var15)) {
					SceneController.picked_tile_x = var5;
					SceneController.picked_tile_y = var6;
				}

				if (!GLManager.opengl_mode && !var7) {
					rasterizer.clip_edges = var13 < 0 || var14 < 0 || var15 < 0 || var13 > rasterizer.clip_width || var14 > rasterizer.clip_width || var15 > rasterizer.clip_width;
					if (var0.anIntArray616 != null && var0.anIntArray616[var9] != -1) {
						if (ClientOptions.clientoption_textures) {
							if (var0.aBoolean629) {
								rasterizer.method1135(var16, var17, var18, var13, var14, var15, var0.anIntArray625[var9], var0.anIntArray632[var9], var0.anIntArray631[var9], ShapedTile.anIntArray614[0], ShapedTile.anIntArray614[1], ShapedTile.anIntArray614[3], ShapedTile.anIntArray630[0], ShapedTile.anIntArray630[1], ShapedTile.anIntArray630[3], ShapedTile.anIntArray628[0], ShapedTile.anIntArray628[1], ShapedTile.anIntArray628[3], var0.anIntArray616[var9]);
							} else {
								rasterizer.method1135(var16, var17, var18, var13, var14, var15, var0.anIntArray625[var9], var0.anIntArray632[var9], var0.anIntArray631[var9], ShapedTile.anIntArray614[var10], ShapedTile.anIntArray614[var11], ShapedTile.anIntArray614[var12], ShapedTile.anIntArray630[var10], ShapedTile.anIntArray630[var11], ShapedTile.anIntArray630[var12], ShapedTile.anIntArray628[var10], ShapedTile.anIntArray628[var11], ShapedTile.anIntArray628[var12], var0.anIntArray616[var9]);
							}
						} else {
							int var19 = GraphicTools.get_materials().get_material(var0.anIntArray616[var9]).get_colour();
							rasterizer.method1154(var16, var17, var18, var13, var14, var15, repackHSL(var19, var0.anIntArray625[var9]), repackHSL(var19, var0.anIntArray632[var9]), repackHSL(var19, var0.anIntArray631[var9]));
						}
					} else if (var0.anIntArray625[var9] != 12345678) {
						rasterizer.method1154(var16, var17, var18, var13, var14, var15, var0.anIntArray625[var9], var0.anIntArray632[var9], var0.anIntArray631[var9]);
					}
				}
			}
		}

	}

	public static int repackHSL(int var0, int var1) {
		var1 = var1 * (var0 & 127) >> 7;
		if (var1 < 2) {
			var1 = 2;
		} else if (var1 > 126) {
			var1 = 126;
		}

		return (var0 & '\uff80') + var1;
	}

	public static boolean isMouseWithinTriangle(int i, int i_50_, int i_51_, int i_52_, int i_53_, int i_54_, int i_55_, int i_56_) {
		if (i_50_ < i_51_ && i_50_ < i_52_ && i_50_ < i_53_) {
			return false;
		}
		if (i_50_ > i_51_ && i_50_ > i_52_ && i_50_ > i_53_) {
			return false;
		}
		if (i < i_54_ && i < i_55_ && i < i_56_) {
			return false;
		}
		if (i > i_54_ && i > i_55_ && i > i_56_) {
			return false;
		}
		int i_57_ = (i_50_ - i_51_) * (i_55_ - i_54_) - (i - i_54_) * (i_52_ - i_51_);
		int i_58_ = (i_50_ - i_53_) * (i_54_ - i_56_) - (i - i_56_) * (i_51_ - i_53_);
		int i_59_ = (i_50_ - i_52_) * (i_56_ - i_55_) - (i - i_55_) * (i_53_ - i_52_);
		if (i_57_ * i_59_ > 0 && i_59_ * i_58_ > 0) {
			return true;
		}
		return false;
	}

	public static void process_culling() {
		Scene.num_processed_cullings = 0;
		while_19_: for (int i_7_ = 0; i_7_ < num_active_cullings; i_7_++) {
			CullingCluster cullingCluster = active_cullings[i_7_];
			if (Scene.anIntArray548 != null) {
				for (int i_8_ = 0; i_8_ < Scene.anIntArray548.length; i_8_++) {
					if (Scene.anIntArray548[i_8_] != -1000000 && (cullingCluster.worldStartZ <= Scene.anIntArray548[i_8_] || cullingCluster.worldEndZ <= Scene.anIntArray548[i_8_]) && (cullingCluster.worldStartX <= Scene.anIntArray3285[i_8_] || cullingCluster.worldEndX <= Scene.anIntArray3285[i_8_]) && (cullingCluster.worldStartX >= Scene.anIntArray1433[i_8_] || cullingCluster.worldEndX >= Scene.anIntArray1433[i_8_]) && (cullingCluster.worldStartY <= Scene.anIntArray3427[i_8_] || cullingCluster.worldEndY <= Scene.anIntArray3427[i_8_]) && (cullingCluster.worldStartY >= Scene.anIntArray1455[i_8_] || cullingCluster.worldEndY >= Scene.anIntArray1455[i_8_])) {
						continue while_19_;
					}
				}
			}
			if (cullingCluster.searchMask == 1) {
				int i_9_ = cullingCluster.anInt941 - Camera.xCameraGridPosition + visible_tiles;
				if (i_9_ >= 0 && i_9_ <= visible_tiles + visible_tiles) {
					int i_10_ = cullingCluster.anInt923 - Camera.yCameraGridPosition + visible_tiles;
					if (i_10_ < 0) {
						i_10_ = 0;
					}
					int i_11_ = cullingCluster.anInt936 - Camera.yCameraGridPosition + visible_tiles;
					if (i_11_ > visible_tiles + visible_tiles) {
						i_11_ = visible_tiles + visible_tiles;
					}
					boolean bool = false;
					while (i_10_ <= i_11_) {
						if (Scene.adjacentTileOnScreen[i_9_][i_10_++]) {
							bool = true;
							break;
						}
					}
					if (bool) {
						int i_12_ = Camera.xCameraPosition - cullingCluster.worldStartX;
						if (i_12_ > 32) {
							cullingCluster.tileDistanceEnum = 1;
						} else {
							if (i_12_ >= -32) {
								continue;
							}
							cullingCluster.tileDistanceEnum = 2;
							i_12_ = -i_12_;
						}
						cullingCluster.worldDistanceFromCameraStartY = (cullingCluster.worldStartY - Camera.yCameraPosition << 8) / i_12_;
						cullingCluster.worldDistanceFromCameraEndY = (cullingCluster.worldEndY - Camera.yCameraPosition << 8) / i_12_;
						cullingCluster.worldDistanceFromCameraStartZ = (cullingCluster.worldStartZ - Camera.zCameraPosition << 8) / i_12_;
						cullingCluster.worldDistanceFromCameraEndZ = (cullingCluster.worldEndZ - Camera.zCameraPosition << 8) / i_12_;
						Scene.processed_cullings[Scene.num_processed_cullings++] = cullingCluster;
					}
				}
			} else if (cullingCluster.searchMask == 2) {
				int i_13_ = cullingCluster.anInt923 - Camera.yCameraGridPosition + visible_tiles;
				if (i_13_ >= 0 && i_13_ <= visible_tiles + visible_tiles) {
					int i_14_ = cullingCluster.anInt941 - Camera.xCameraGridPosition + visible_tiles;
					if (i_14_ < 0) {
						i_14_ = 0;
					}
					int i_15_ = cullingCluster.anInt914 - Camera.xCameraGridPosition + visible_tiles;
					if (i_15_ > visible_tiles + visible_tiles) {
						i_15_ = visible_tiles + visible_tiles;
					}
					boolean bool = false;
					while (i_14_ <= i_15_) {
						if (Scene.adjacentTileOnScreen[i_14_++][i_13_]) {
							bool = true;
							break;
						}
					}
					if (bool) {
						int i_16_ = Camera.yCameraPosition - cullingCluster.worldStartY;
						if (i_16_ > 32) {
							cullingCluster.tileDistanceEnum = 3;
						} else {
							if (i_16_ >= -32) {
								continue;
							}
							cullingCluster.tileDistanceEnum = 4;
							i_16_ = -i_16_;
						}
						cullingCluster.worldDistanceFromCameraStartX = (cullingCluster.worldStartX - Camera.xCameraPosition << 8) / i_16_;
						cullingCluster.worldDistanceFromCameraEndX = (cullingCluster.worldEndX - Camera.xCameraPosition << 8) / i_16_;
						cullingCluster.worldDistanceFromCameraStartZ = (cullingCluster.worldStartZ - Camera.zCameraPosition << 8) / i_16_;
						cullingCluster.worldDistanceFromCameraEndZ = (cullingCluster.worldEndZ - Camera.zCameraPosition << 8) / i_16_;
						Scene.processed_cullings[Scene.num_processed_cullings++] = cullingCluster;
					}
				}
			} else if (cullingCluster.searchMask == 4) {
				int i_17_ = cullingCluster.worldStartZ - Camera.zCameraPosition;
				if (i_17_ > TileConstants.SIZE_1BY1) {
					int i_18_ = cullingCluster.anInt923 - Camera.yCameraGridPosition + visible_tiles;
					if (i_18_ < 0) {
						i_18_ = 0;
					}
					int i_19_ = cullingCluster.anInt936 - Camera.yCameraGridPosition + visible_tiles;
					if (i_19_ > visible_tiles + visible_tiles) {
						i_19_ = visible_tiles + visible_tiles;
					}
					if (i_18_ <= i_19_) {
						int i_20_ = cullingCluster.anInt941 - Camera.xCameraGridPosition + visible_tiles;
						if (i_20_ < 0) {
							i_20_ = 0;
						}
						int i_21_ = cullingCluster.anInt914 - Camera.xCameraGridPosition + visible_tiles;
						if (i_21_ > visible_tiles + visible_tiles) {
							i_21_ = visible_tiles + visible_tiles;
						}
						boolean bool = false;
						while_17_: for (int i_22_ = i_20_; i_22_ <= i_21_; i_22_++) {
							for (int i_23_ = i_18_; i_23_ <= i_19_; i_23_++) {
								if (Scene.adjacentTileOnScreen[i_22_][i_23_]) {
									bool = true;
									break while_17_;
								}
							}
						}
						if (bool) {
							cullingCluster.tileDistanceEnum = 5;
							cullingCluster.worldDistanceFromCameraStartX = (cullingCluster.worldStartX - Camera.xCameraPosition << 8) / i_17_;
							cullingCluster.worldDistanceFromCameraEndX = (cullingCluster.worldEndX - Camera.xCameraPosition << 8) / i_17_;
							cullingCluster.worldDistanceFromCameraStartY = (cullingCluster.worldStartY - Camera.yCameraPosition << 8) / i_17_;
							cullingCluster.worldDistanceFromCameraEndY = (cullingCluster.worldEndY - Camera.yCameraPosition << 8) / i_17_;
							Scene.processed_cullings[Scene.num_processed_cullings++] = cullingCluster;
						}
					}
				}
			}
		}
	}

	public static void process_players(boolean bool) {
		if ((ComponentMinimap.flag_x ^ 0xffffffff) == (GameClient.currentPlayer.bound_extents_x >> 7 ^ 0xffffffff) && (GameClient.currentPlayer.bound_extents_z >> 7 ^ 0xffffffff) == (ComponentMinimap.flag_y ^ 0xffffffff)) {
			ComponentMinimap.flag_x = 0;
		}
		int i = StaticMethods.anInt3067;
		if (bool) {
			i = 1;
		}
		for (int i_4_ = 0; (i ^ 0xffffffff) < (i_4_ ^ 0xffffffff); i_4_++) {
			long l;
			Player player;
			if (bool) {
				player = GameClient.currentPlayer;
				l = 8791798054912L;
			} else {
				l = (long) GameClient.localPlayerPointers[i_4_] << 32;
				player = GameClient.localPlayers[GameClient.localPlayerPointers[i_4_]];
			}
			if (player != null && player.is_ready()) {
				int i_5_ = player.bound_extents_x >> 7;
				int i_6_ = player.bound_extents_z >> 7;
				player.aBoolean4384 = false;
				if ((ClientOptions.clientoption_idleanims_many && StaticMethods.anInt3067 > 50 || StaticMethods.anInt3067 > 200) && !bool && player.standAnimation == player.current_standing_animation) {
					player.aBoolean4384 = true;
				}
				if (i_5_ >= 0 && i_5_ < 104 && (i_6_ ^ 0xffffffff) <= -1 && i_6_ < 104) {
					if (player.aClass38_Sub1_4402 != null && GameClient.timer >= player.anInt4380 && (player.anInt4411 ^ 0xffffffff) < (GameClient.timer ^ 0xffffffff)) {
						player.aBoolean4384 = false;
						player.render_y = Scene.get_average_height(ObjType.localHeight, player.bound_extents_x, player.bound_extents_z);
						VarpDefinition.method631(ObjType.localHeight, player.bound_extents_x, player.bound_extents_z, player.render_y, player, player.anInt2680, l, player.anInt4387, player.anInt4391, player.anInt4390, player.anInt4396);
					} else {
						if ((player.bound_extents_x & 0x7f) == 64 && (player.bound_extents_z & 0x7f) == 64) {
							if (render_cycle == NPC.anIntArrayArray4367[i_5_][i_6_]) {
								continue;
							}
							NPC.anIntArrayArray4367[i_5_][i_6_] = render_cycle;
						}
						player.render_y = Scene.get_average_height(ObjType.localHeight, player.bound_extents_x, player.bound_extents_z);
						Scene.add_interactive_entity(player, ObjType.localHeight, player.bound_extents_x, player.bound_extents_z, player.render_y, 60, player.anInt2680, l, player.aBoolean2810);
					}
				}
			}
		}
	}

	public static void process_npcs(boolean inside) {
		for (int index = 0; index < EntityUpdating.localNPCCount; index++) {
			NPC npc = GameClient.activeNPCs[EntityUpdating.localNPCIndexes[index]];
			long uiod = 0x20000000L | (long) EntityUpdating.localNPCIndexes[index] << 32;
			if (npc != null && npc.is_ready() && !npc.config.visible != inside && npc.config.method820(0)) {
				int grid_x = npc.bound_extents_x >> 7;
				int grid_z = npc.bound_extents_z >> 7;
				if (grid_x >= 0 && grid_x < 104 && grid_z >= 0 && grid_z < 104) {
					if (npc.size == 1 && (npc.bound_extents_x & 0x7f) == 64 && (0x7f & npc.bound_extents_z) == 64) {
						if ((render_cycle ^ 0xffffffff) == (NPC.anIntArrayArray4367[grid_x][grid_z] ^ 0xffffffff)) {
							continue;
						}
						NPC.anIntArrayArray4367[grid_x][grid_z] = render_cycle;
					}
					if (!npc.config.clickable) {
						uiod |= ~0x7fffffffffffffffL;
					}
					npc.render_y = Scene.get_average_height(ObjType.localHeight, (-1 + npc.size) * 64 + npc.bound_extents_x, -64 - (-(npc.size * 64) - npc.bound_extents_z));
					add_interactive_entity(npc, ObjType.localHeight, npc.bound_extents_x, npc.bound_extents_z, npc.render_y, -4 + 64 * npc.size, npc.anInt2680, uiod, npc.aBoolean2810);
				}
			}
		}
	}

	public static void calculate_effective_size(int layout_x, int layout_y, int layout_width, int layout_height, boolean clear) {
		if (-2 < ~layout_width) {
			layout_width = 1;
		}
		if (1 > layout_height) {
			layout_height = 1;
		}
		if (GLManager.opengl_mode) {
			int var6 = layout_height + -334;
			if (0 <= var6) {
				if (~var6 < -101) {
					var6 = 100;
				}
			} else {
				var6 = 0;
			}

			int var7 = var6 * (fovy - fovx) / 100 + fovx;
			if (fov_minx <= var7) {
				if (fov_maxx < var7) {
					var7 = fov_maxx;
				}
			} else {
				var7 = fov_minx;
			}
			int var8 = var7 * layout_height * 512 / (layout_width * 334);
			if (var8 >= fov_miny) {
				if (~fov_maxy > ~var8) {
					int var12 = fov_maxy;
					var7 = var12 * layout_width * 334 / (layout_height * 512);
					if (~fov_minx < ~var7) {
						var7 = fov_minx;
						int var9 = var12 * layout_width * 334 / (512 * var7);
						int var10 = (-var9 + layout_height) / 2;
						if (clear) {
							GLState.reset_clipping();
							GLShapes.fill_rectangle(layout_x, layout_y, layout_width, var10, 0);
							GLShapes.fill_rectangle(layout_x, layout_y + layout_height - var10, layout_width, var10, 0);
						}

						layout_height -= var10 * 2;
						layout_y += var10;
					}
				}
			} else {
				int var12 = fov_miny;
				var7 = 334 * layout_width * var12 / (512 * layout_height);
				if (fov_maxx < var7) {
					var7 = fov_maxx;
					int var9 = 512 * layout_height * var7 / (334 * var12);
					int var10 = (layout_width - var9) / 2;
					if (clear) {
						GLState.reset_clipping();
						GLShapes.fill_rectangle(layout_x, layout_y, var10, layout_height, 0);
						GLShapes.fill_rectangle(layout_width + layout_x - var10, layout_y, var10, layout_height, 0);
					}
					layout_x += var10;
					layout_width -= 2 * var10;
				}
			}
			fov = var7 * layout_height / 334;
		}
		effective_width = (short) layout_width;
		effective_height = (short) layout_height;
		effective_y = layout_y;
		effective_x = layout_x;
	}

	public static boolean add_interactive_entity(SceneNode object, int i, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, long l, boolean bool) {
		if (object == null) {
			return true;
		}
		int i_8_ = i_3_ - i_6_;
		int i_9_ = i_4_ - i_6_;
		int i_10_ = i_3_ + i_6_;
		int i_11_ = i_4_ + i_6_;
		if (bool) {
			if (i_7_ > 640 && i_7_ < 1408) {
				i_11_ += 128;
			}
			if (i_7_ > 1152 && i_7_ < 1920) {
				i_10_ += 128;
			}
			if (i_7_ > 1664 || i_7_ < 384) {
				i_9_ -= 128;
			}
			if (i_7_ > 128 && i_7_ < 896) {
				i_8_ -= 128;
			}
		}
		i_8_ /= 128;
		i_9_ /= 128;
		i_10_ /= 128;
		i_11_ /= 128;
		return Scene.addInteractiveObject(i, i_8_, i_9_, i_10_ - i_8_ + 1, i_11_ - i_9_ + 1, i_3_, i_4_, i_5_, object, i_7_, true, l);
	}

	public static void load_floor(boolean underwater) {
		if (underwater) {
			current_heightmap = underwater_heightmap;
			current_grounds = underwater_grounds;
			Scene.current_opengl_tiles = underwater_opengl_tiles;
		} else {
			current_heightmap = surface_heightmap;
			current_grounds = surface_grounds;
			Scene.current_opengl_tiles = surface_opengl_tiles;
		}
		visible_level_end = Scene.current_grounds.length;
	}

	public static int[][][] anIntArrayArrayArray1142;
	public static boolean pick_mouseover_tile = false;
	public static long[] rendered_models_uid;
	public static boolean opengl_scene_dirty;

	public Scene() {
		// NOOP
	}

	public static boolean is_culled(int i, int i_5_, int i_6_) {
		for (int index = 0; index < num_processed_cullings; index++) {
			CullingCluster culling = processed_cullings[index];
			if (culling.tileDistanceEnum == 1) {
				int i_8_ = culling.worldStartX - i;
				if (i_8_ > 0) {
					int i_9_ = culling.worldStartY + (culling.worldDistanceFromCameraStartY * i_8_ >> 8);
					int i_10_ = culling.worldEndY + (culling.worldDistanceFromCameraEndY * i_8_ >> 8);
					int i_11_ = culling.worldStartZ + (culling.worldDistanceFromCameraStartZ * i_8_ >> 8);
					int i_12_ = culling.worldEndZ + (culling.worldDistanceFromCameraEndZ * i_8_ >> 8);
					if (i_6_ >= i_9_ && i_6_ <= i_10_ && i_5_ >= i_11_ && i_5_ <= i_12_) {
						return true;
					}
				}
			} else if (culling.tileDistanceEnum == 2) {
				int i_13_ = i - culling.worldStartX;
				if (i_13_ > 0) {
					int i_14_ = culling.worldStartY + (culling.worldDistanceFromCameraStartY * i_13_ >> 8);
					int i_15_ = culling.worldEndY + (culling.worldDistanceFromCameraEndY * i_13_ >> 8);
					int i_16_ = culling.worldStartZ + (culling.worldDistanceFromCameraStartZ * i_13_ >> 8);
					int i_17_ = culling.worldEndZ + (culling.worldDistanceFromCameraEndZ * i_13_ >> 8);
					if (i_6_ >= i_14_ && i_6_ <= i_15_ && i_5_ >= i_16_ && i_5_ <= i_17_) {
						return true;
					}
				}
			} else if (culling.tileDistanceEnum == 3) {
				int i_18_ = culling.worldStartY - i_6_;
				if (i_18_ > 0) {
					int i_19_ = culling.worldStartX + (culling.worldDistanceFromCameraStartX * i_18_ >> 8);
					int i_20_ = culling.worldEndX + (culling.worldDistanceFromCameraEndX * i_18_ >> 8);
					int i_21_ = culling.worldStartZ + (culling.worldDistanceFromCameraStartZ * i_18_ >> 8);
					int i_22_ = culling.worldEndZ + (culling.worldDistanceFromCameraEndZ * i_18_ >> 8);
					if (i >= i_19_ && i <= i_20_ && i_5_ >= i_21_ && i_5_ <= i_22_) {
						return true;
					}
				}
			} else if (culling.tileDistanceEnum == 4) {
				int i_23_ = i_6_ - culling.worldStartY;
				if (i_23_ > 0) {
					int i_24_ = culling.worldStartX + (culling.worldDistanceFromCameraStartX * i_23_ >> 8);
					int i_25_ = culling.worldEndX + (culling.worldDistanceFromCameraEndX * i_23_ >> 8);
					int i_26_ = culling.worldStartZ + (culling.worldDistanceFromCameraStartZ * i_23_ >> 8);
					int i_27_ = culling.worldEndZ + (culling.worldDistanceFromCameraEndZ * i_23_ >> 8);
					if (i >= i_24_ && i <= i_25_ && i_5_ >= i_26_ && i_5_ <= i_27_) {
						return true;
					}
				}
			} else if (culling.tileDistanceEnum == 5) {
				int i_28_ = i_5_ - culling.worldStartZ;
				if (i_28_ > 0) {
					int i_29_ = culling.worldStartX + (culling.worldDistanceFromCameraStartX * i_28_ >> 8);
					int i_30_ = culling.worldEndX + (culling.worldDistanceFromCameraEndX * i_28_ >> 8);
					int i_31_ = culling.worldStartY + (culling.worldDistanceFromCameraStartY * i_28_ >> 8);
					int i_32_ = culling.worldEndY + (culling.worldDistanceFromCameraEndY * i_28_ >> 8);
					if (i >= i_29_ && i <= i_30_ && i_6_ >= i_31_ && i_6_ <= i_32_) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static final InteractiveEntity getInteractiveEntity(int i, int i_0_, int i_1_) {
		Ground class23_sub1 = current_grounds[i][i_0_][i_1_];
		if (class23_sub1 == null) {
			return null;
		}
		for (int i_2_ = 0; i_2_ < class23_sub1.num_interactives; i_2_++) {
			InteractiveEntity interactiveObject = class23_sub1.interactives[i_2_];
			if ((interactiveObject.uid >> 29 & 0x3L) == 2L && interactiveObject.anInt601 == i_0_ && interactiveObject.anInt607 == i_1_) {
				return interactiveObject;
			}
		}
		return null;
	}

	public static final void create_culling_cluster(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_) {
		CullingCluster cullingCluster = new CullingCluster();
		cullingCluster.anInt941 = i_1_ / TileConstants.SIZE_1BY1;
		cullingCluster.anInt914 = i_2_ / TileConstants.SIZE_1BY1;
		cullingCluster.anInt923 = i_3_ / TileConstants.SIZE_1BY1;
		cullingCluster.anInt936 = i_4_ / TileConstants.SIZE_1BY1;
		cullingCluster.searchMask = i_0_;
		cullingCluster.worldStartX = i_1_;
		cullingCluster.worldEndX = i_2_;
		cullingCluster.worldStartY = i_3_;
		cullingCluster.worldEndY = i_4_;
		cullingCluster.worldStartZ = i_5_;
		cullingCluster.worldEndZ = i_6_;
		active_cullings[num_active_cullings++] = cullingCluster;
	}

	public static final boolean addInteractiveObject(int i, int offset, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, SceneNode abstractModel, int i_7_, boolean bool, long l) {
		boolean is_underworld = current_heightmap == underwater_heightmap;
		int underworld_flag = 0;
		for (int i_8_ = offset; i_8_ < offset + i_2_; i_8_++) {
			for (int i_9_ = i_1_; i_9_ < i_1_ + i_3_; i_9_++) {
				if (i_8_ < 0 || i_9_ < 0 || i_8_ >= width || i_9_ >= height) {
					return false;
				}
				Ground class23_sub1 = current_grounds[i][i_8_][i_9_];
				if (class23_sub1 != null && class23_sub1.num_interactives >= 5) {
					return false;
				}
			}
		}
		InteractiveEntity interactiveObject = new InteractiveEntity();
		interactiveObject.uid = l;
		interactiveObject.anInt598 = i;
		interactiveObject.anInt602 = i_4_;
		interactiveObject.anInt610 = i_5_;
		interactiveObject.anInt608 = i_6_;
		interactiveObject.node = abstractModel;
		interactiveObject.anInt612 = i_7_;
		interactiveObject.anInt601 = offset;
		interactiveObject.anInt607 = i_1_;
		interactiveObject.anInt613 = offset + i_2_ - 1;
		interactiveObject.anInt599 = i_1_ + i_3_ - 1;
		for (int x = offset; x < offset + i_2_; x++) {
			for (int y = i_1_; y < i_1_ + i_3_; y++) {
				int i_12_ = 0;
				if (x > offset) {
					i_12_++;
				}
				if (x < offset + i_2_ - 1) {
					i_12_ += 4;
				}
				if (y > i_1_) {
					i_12_ += 8;
				}
				if (y < i_1_ + i_3_ - 1) {
					i_12_ += 2;
				}
				for (int i_13_ = i; i_13_ >= 0; i_13_--) {
					if (current_grounds[i_13_][x][y] == null) {
						current_grounds[i_13_][x][y] = new Ground(i_13_, x, y);
					}
				}
				Ground class23_sub1 = current_grounds[i][x][y];
				class23_sub1.interactives[class23_sub1.num_interactives] = interactiveObject;
				class23_sub1.anIntArray2024[class23_sub1.num_interactives] = i_12_;
				class23_sub1.flags |= i_12_;
				class23_sub1.num_interactives++;
				if (is_underworld && anIntArrayArray3115[x][y] != 0) {
					underworld_flag = anIntArrayArray3115[x][y];
				}
			}
		}
		if (is_underworld && underworld_flag != 0) {
			for (int x = offset; x < offset + i_2_; x++) {
				for (int y = i_1_; y < i_1_ + i_3_; y++) {
					if (anIntArrayArray3115[x][y] == 0) {
						anIntArrayArray3115[x][y] = underworld_flag;
					}
				}
			}
		}
		if (bool) {
			offscreen_entities[num_offscreen_entities++] = interactiveObject;
		}
		return true;
	}

	public static final long getInteractiveUid(int i, int i_0_, int i_1_) {
		Ground class23_sub1 = current_grounds[i][i_0_][i_1_];
		if (class23_sub1 == null) {
			return 0L;
		}
		for (int i_2_ = 0; i_2_ < class23_sub1.num_interactives; i_2_++) {
			InteractiveEntity interactiveObject = class23_sub1.interactives[i_2_];
			if ((interactiveObject.uid >> 29 & 0x3L) == 2L && interactiveObject.anInt601 == i_0_ && interactiveObject.anInt607 == i_1_) {
				return interactiveObject.uid;
			}
		}
		return 0L;
	}

	public static void reset_shadows() {
		SceneLighting.num_lights = 0;
		for (int var0 = 0; var0 < SceneLighting.num_levels; ++var0) {
			for (int var1 = 0; var1 < SceneLighting.num_width; ++var1) {
				for (int var2 = 0; var2 < SceneLighting.num_height; ++var2) {
					SceneLighting.tile_lights[var0][var1][var2] = 0;
				}
			}
		}

	}

	public static final void process_projectiles() {
		for (ProjectileNode projectileNode = (ProjectileNode) Class36.aClass89_562.get_first(); projectileNode != null; projectileNode = (ProjectileNode) Class36.aClass89_562.get_next()) {
			Projectile projectile = projectileNode.projectile;
			if (ObjType.localHeight != projectile.plane || (GameClient.timer ^ 0xffffffff) < (projectile.speed ^ 0xffffffff)) {
				projectileNode.unlink();
			} else if ((projectile.delay ^ 0xffffffff) >= (GameClient.timer ^ 0xffffffff)) {
				if ((projectile.targetIndex ^ 0xffffffff) < -1) {
					NPC npc = GameClient.activeNPCs[-1 + projectile.targetIndex];
					if (npc != null && (npc.bound_extents_x ^ 0xffffffff) <= -1 && npc.bound_extents_x < 13312 && npc.bound_extents_z >= 0 && npc.bound_extents_z < 13312) {
						projectile.method1069(GameClient.timer, npc.bound_extents_z, npc.bound_extents_x, Scene.get_average_height(projectile.plane, npc.bound_extents_x, npc.bound_extents_z) - projectile.endHeight, -18499);
					}
				}
				if (projectile.targetIndex < 0) {
					int i_2_ = -projectile.targetIndex - 1;
					Player player;
					if ((i_2_ ^ 0xffffffff) == (StaticMethods.thisPlayerIndex ^ 0xffffffff)) {
						player = GameClient.currentPlayer;
					} else {
						player = GameClient.localPlayers[i_2_];
					}
					if (player != null && player.bound_extents_x >= 0 && player.bound_extents_x < 13312 && player.bound_extents_z >= 0 && player.bound_extents_z < 13312) {
						projectile.method1069(GameClient.timer, player.bound_extents_z, player.bound_extents_x, Scene.get_average_height(projectile.plane, player.bound_extents_x, player.bound_extents_z) - projectile.endHeight, -18499);
					}
				}
				projectile.method1072(InterfaceNode.anInt2459, 12891);
				add_interactive_entity(projectile, ObjType.localHeight, (int) projectile.aDouble2570, (int) projectile.aDouble2575, (int) projectile.aDouble2573, 60, projectile.anInt2924, -1L, false);
			}
		}
	}

	public static final void process_spots() {
		PositionedGraphicNode graphicNode = (PositionedGraphicNode) SpotType.aClass89_4066.get_first();
		for (/**/; graphicNode != null; graphicNode = (PositionedGraphicNode) SpotType.aClass89_4066.get_next()) {
			SpotEntity graphic = graphicNode.positionedGraphic;
			if ((ObjType.localHeight ^ 0xffffffff) == (graphic.plane ^ 0xffffffff) && !graphic.animationless) {
				if (GameClient.timer >= graphic.startTime) {
					graphic.method1079(InterfaceNode.anInt2459, -2);
					if (!graphic.animationless) {
						add_interactive_entity(graphic, graphic.plane, graphic.locationX, graphic.locationY, graphic.height, 60, 0, -1L, false);
					} else {
						graphicNode.unlink();
					}
				}
			} else {
				graphicNode.unlink();
			}
		}
	}

	public static final int get_average_height(int z, int x, int y) {
		if (null == current_heightmap) {
			return 0;
		}
		int i_32_ = y >> TileConstants.SIZE_BITS;
		int i_33_ = x >> TileConstants.SIZE_BITS;
		if (i_33_ < 0 || (i_32_ ^ 0xffffffff) > -1 || i_33_ > 103 || i_32_ > 103) {
			return 0;
		}
		int i_35_ = z;
		if (i_35_ < 3 && (MapLoader.settings[1][i_33_][i_32_] & 0x2) == 2) {
			i_35_++;
		}
		int i_36_ = x & TileConstants.SIZE_1BY1 - 1;
		int i_37_ = y & TileConstants.SIZE_1BY1 - 1;
		int i_38_ = current_heightmap[i_35_][i_33_][i_32_] * (-i_36_ + TileConstants.SIZE_1BY1) + current_heightmap[i_35_][i_33_ - -1][i_32_] * i_36_ >> TileConstants.SIZE_BITS;
		int i_39_ = i_36_ * current_heightmap[i_35_][i_33_ - -1][1 + i_32_] + (-i_36_ + TileConstants.SIZE_1BY1) * current_heightmap[i_35_][i_33_][1 + i_32_] >> TileConstants.SIZE_BITS;
		return i_38_ * (-i_37_ + TileConstants.SIZE_1BY1) + i_39_ * i_37_ >> TileConstants.SIZE_BITS;
	}

	public static final int method1052(int[][] is, int i, int i_157_) {
		int i_158_ = i >> TileConstants.SIZE_BITS;
		int i_159_ = i_157_ >> TileConstants.SIZE_BITS;
		if (i_158_ < 0 || i_159_ < 0 || i_158_ >= is.length || i_159_ >= is[0].length) {
			return 0;
		}
		int i_160_ = i & TileConstants.SIZE_1BY1 - 1;
		int i_161_ = i_157_ & TileConstants.SIZE_1BY1 - 1;
		int i_162_ = is[i_158_][i_159_] * (TileConstants.SIZE_1BY1 - i_160_) + is[i_158_ + 1][i_159_] * i_160_ >> TileConstants.SIZE_BITS;
		int i_163_ = is[i_158_][i_159_ + 1] * (TileConstants.SIZE_1BY1 - i_160_) + is[i_158_ + 1][i_159_ + 1] * i_160_ >> TileConstants.SIZE_BITS;
		return i_162_ * (TileConstants.SIZE_1BY1 - i_161_) + i_163_ * i_161_ >> TileConstants.SIZE_BITS;
	}

	public static final int get_average_height_clamp(int[][] is, int i, int i_33_) {
		int i_34_ = i >> TileConstants.SIZE_BITS;
		int i_35_ = i_33_ >> TileConstants.SIZE_BITS;
		if (i_34_ < 0 || i_35_ < 0 || i_34_ >= is.length || i_35_ >= is[0].length) {
			return 0;
		}
		int i_36_ = i & TileConstants.SIZE_1BY1 - 1;
		int i_37_ = i_33_ & TileConstants.SIZE_1BY1 - 1;
		int i_38_ = is[i_34_][i_35_] * (TileConstants.SIZE_1BY1 - i_36_) + is[i_34_ + 1][i_35_] * i_36_ >> TileConstants.SIZE_BITS;
		int i_39_ = is[i_34_][i_35_ + 1] * (TileConstants.SIZE_1BY1 - i_36_) + is[i_34_ + 1][i_35_ + 1] * i_36_ >> TileConstants.SIZE_BITS;
		return i_38_ * (TileConstants.SIZE_1BY1 - i_37_) + i_39_ * i_37_ >> TileConstants.SIZE_BITS;
	}

	public static final void method1367(int i, int i_0_, int i_1_, int i_2_) {
		Ground tile = current_grounds[i][i_0_][i_1_];
		if (tile != null) {
			current_grounds[i][i_0_][i_1_].logicHeight = i_2_;
		}
	}

	public static void reset_scene() {
		if (surface_grounds != null) {
			for (int var0 = 0; var0 < surface_grounds.length; ++var0) {
				for (int var1 = 0; var1 < width; ++var1) {
					for (int var2 = 0; var2 < height; ++var2) {
						surface_grounds[var0][var1][var2] = null;
					}
				}
			}
		}
		surface_opengl_tiles = null;
		if (underwater_grounds != null) {
			for (int var0 = 0; var0 < underwater_grounds.length; ++var0) {
				for (int var1 = 0; var1 < width; ++var1) {
					for (int var2 = 0; var2 < height; ++var2) {
						underwater_grounds[var0][var1][var2] = null;
					}
				}
			}
		}
		underwater_opengl_tiles = null;
		num_active_cullings = 0;
		if (active_cullings != null) {
			for (int var0 = 0; var0 < num_active_cullings; ++var0) {
				active_cullings[var0] = null;
			}
		}

		if (offscreen_entities != null) {
			for (int var0 = 0; var0 < num_offscreen_entities; ++var0) {
				offscreen_entities[var0] = null;
			}
			num_offscreen_entities = 0;
		}
		if (onscreen_entities != null) {
			for (int var0 = 0; var0 < onscreen_entities.length; ++var0) {
				onscreen_entities[var0] = null;
			}
		}
	}

	public static final void method818(int i, int i_16_) {
		Ground class23_sub1 = current_grounds[0][i][i_16_];
		for (int i_17_ = 0; i_17_ < 3; i_17_++) {
			Ground class23_sub1_18_ = current_grounds[i_17_][i][i_16_] = current_grounds[i_17_ + 1][i][i_16_];
			if (class23_sub1_18_ != null) {
				class23_sub1_18_.grid_level--;
				for (int i_19_ = 0; i_19_ < class23_sub1_18_.num_interactives; i_19_++) {
					InteractiveEntity interactiveObject = class23_sub1_18_.interactives[i_19_];
					if ((interactiveObject.uid >> 29 & 0x3L) == 2L && interactiveObject.anInt601 == i && interactiveObject.anInt607 == i_16_) {
						interactiveObject.anInt598--;
					}
				}
			}
		}
		if (current_grounds[0][i][i_16_] == null) {
			current_grounds[0][i][i_16_] = new Ground(0, i, i_16_);
		}
		current_grounds[0][i][i_16_].aClass23_Sub1_2022 = class23_sub1;
		current_grounds[3][i][i_16_] = null;
	}

	public static void add_tile(int var0, int var1, int var2, int shape, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18, int var19) {
		if (shape == 0) {
			PlainTile var20 = new PlainTile(var10, var11, var12, var13, -1, var18, false);
			for (int var21 = var0; var21 >= 0; --var21) {
				if (current_grounds[var21][var1][var2] == null) {
					current_grounds[var21][var1][var2] = new Ground(var21, var1, var2);
				}
			}
			current_grounds[var0][var1][var2].plain_tile = var20;
		} else if (shape == 1) {
			PlainTile var20 = new PlainTile(var14, var15, var16, var17, var5, var19, var6 == var7 && var6 == var8 && var6 == var9);
			for (int var21 = var0; var21 >= 0; --var21) {
				if (current_grounds[var21][var1][var2] == null) {
					current_grounds[var21][var1][var2] = new Ground(var21, var1, var2);
				}
			}
			current_grounds[var0][var1][var2].plain_tile = var20;
		} else {
			ShapedTile var22 = new ShapedTile(shape, var4, var5, var1, var2, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19);
			for (int var21 = var0; var21 >= 0; --var21) {
				if (current_grounds[var21][var1][var2] == null) {
					current_grounds[var21][var1][var2] = new Ground(var21, var1, var2);
				}
			}
			current_grounds[var0][var1][var2].shaped_tile = var22;
		}
	}

	public static boolean method846(int i, int i_35_, int i_36_) {
		int i_37_ = anIntArrayArrayArray1142[i][i_35_][i_36_];
		if (i_37_ == -Class73.anInt1321) {
			return false;
		}
		if (i_37_ == Class73.anInt1321) {
			return true;
		}
		int i_38_ = i_35_ << TileConstants.SIZE_BITS;
		int i_39_ = i_36_ << TileConstants.SIZE_BITS;
		if (is_culled(i_38_ + 1, current_heightmap[i][i_35_][i_36_], i_39_ + 1) && is_culled(i_38_ + TileConstants.SIZE_1BY1 - 1, current_heightmap[i][i_35_ + 1][i_36_], i_39_ + 1) && is_culled(i_38_ + TileConstants.SIZE_1BY1 - 1, current_heightmap[i][i_35_ + 1][i_36_ + 1], i_39_ + TileConstants.SIZE_1BY1 - 1) && is_culled(i_38_ + 1, current_heightmap[i][i_35_][i_36_ + 1], i_39_ + TileConstants.SIZE_1BY1 - 1)) {
			anIntArrayArrayArray1142[i][i_35_][i_36_] = Class73.anInt1321;
			return true;
		}
		anIntArrayArrayArray1142[i][i_35_][i_36_] = -Class73.anInt1321;
		return false;
	}
}
