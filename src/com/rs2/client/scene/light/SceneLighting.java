package com.rs2.client.scene.light;

import static jaggl.GLConstants.GL_AMBIENT;
import static jaggl.GLConstants.GL_CONSTANT;
import static jaggl.GLConstants.GL_CONSTANT_ATTENUATION;
import static jaggl.GLConstants.GL_DIFFUSE;
import static jaggl.GLConstants.GL_DST_COLOR;
import static jaggl.GLConstants.GL_FOG_COLOR;
import static jaggl.GLConstants.GL_LIGHT4;
import static jaggl.GLConstants.GL_LINEAR_ATTENUATION;
import static jaggl.GLConstants.GL_ONE;
import static jaggl.GLConstants.GL_ONE_MINUS_SRC_ALPHA;
import static jaggl.GLConstants.GL_OPERAND0_RGB;
import static jaggl.GLConstants.GL_POSITION;
import static jaggl.GLConstants.GL_QUADRATIC_ATTENUATION;
import static jaggl.GLConstants.GL_SRC0_RGB;
import static jaggl.GLConstants.GL_SRC_ALPHA;
import static jaggl.GLConstants.GL_SRC_COLOR;
import static jaggl.GLConstants.GL_TEXTURE;
import static jaggl.GLConstants.GL_TEXTURE_ENV;
import static jaggl.OpenGL.glBlendFunc;
import static jaggl.OpenGL.glDepthMask;
import static jaggl.OpenGL.glDisable;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glFogfv;
import static jaggl.OpenGL.glLightf;
import static jaggl.OpenGL.glLightfv;
import static jaggl.OpenGL.glTexEnvfv;
import static jaggl.OpenGL.glTexEnvi;

import com.jagex.Ground;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.effects.OpenGLEffects;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.rs2.client.scene.Scene;

public class SceneLighting {

	public static final float[] light_position_buffer = new float[] { 0.0F, 0.0F, 0.0F, 1.0F };

	public static int num_levels;
	public static int num_width;
	public static int num_height;
	public static int last_level;
	public static int last_grid_x;
	public static int last_grid_y;
	public static int[][][] tile_lights;
	public static FlickeringEffect[] lights;

	public static boolean[] slots_enabled;
	public static int[] slots_owner;
	public static int[] anIntArray1023;
	public static int anInt1025;
	public static int anInt1034;
	public static int num_lights = 0;
	public static boolean[] active_lights;

	public static void initialise(int num_levels, int num_width, int num_height) {
		SceneLighting.num_levels = num_levels;
		SceneLighting.num_width = num_width;
		SceneLighting.num_height = num_height;
		SceneLighting.tile_lights = new int[num_levels][num_width][num_height];
	}

	public static void initialise() {
		lights = new FlickeringEffect[255];
		slots_owner = new int[4];
		active_lights = new boolean[4];
		anIntArray1023 = new int[4];
		slots_enabled = new boolean[4];
		tile_lights = new int[num_levels][num_width][num_height];
	}

	public static void draw_point_lights(int var0, int var1, Ground[][][] tiles) {
		if (ClientOptions.clientoption_highdetails_lighting) {
			OpenGLEffects.setup_effect(0, 0);
			GLState.set_combine_rgb_mode(0);
			GLState.reset_texture_matrix();
			GLState.bind_texture(OpenGLEffects.lights_texture.handle);
			glDepthMask(false);
			GLState.set_lights_enabled(false);
			glBlendFunc(GL_DST_COLOR, GL_ONE);
			glFogfv(GL_FOG_COLOR, new float[] { 0.0F, 0.0F, 0.0F, 0.0F }, 0);
			glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_CONSTANT);
			glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_ALPHA);
			label69: for (int light_slot = 0; light_slot < SceneLighting.num_lights; ++light_slot) {
				FlickeringEffect effect = lights[light_slot];
				int var6 = effect.level;
				if (effect.has_bridge) {
					var6--;
				}
				if (effect.point_light != null) {
					int var7 = 0;
					int var8 = (effect.y >> 7) - effect.radius;
					int var9 = (effect.y >> 7) + effect.radius;
					if (var9 >= Scene.visible_to_ypositive) {
						var9 = Scene.visible_to_ypositive - 1;
					}
					if (var8 < Scene.visible_to_ynegative) {
						var7 += Scene.visible_to_ynegative - var8;
						var8 = Scene.visible_to_ynegative;
					}
					int var10 = var8;
					while (var10 <= var9) {
						short var11 = effect.ranges[var7++];
						int var12 = (effect.x >> 7) - effect.radius + (var11 >> 8);
						int var13 = var12 + (var11 & 255) - 1;
						if (var12 < Scene.visible_to_xnegative) {
							var12 = Scene.visible_to_xnegative;
						}
						if (var13 >= Scene.visible_to_xpositive) {
							var13 = Scene.visible_to_xpositive - 1;
						}
						int var14 = var12;
						while (true) {
							if (var14 <= var13) {
								Ground tile = null;
								if (var6 >= 0) {
									tile = tiles[var6][var14][var10];
								}
								if (var6 >= 0 && (tile == null || !tile.aBoolean2021)) {
									++var14;
									continue;
								}
								GLState.update_depth_range(201.5F - effect.level * 50.0F - 1.5F);
								glTexEnvfv(GL_TEXTURE_ENV, 8705, new float[] { 0.0F, 0.0F, 0.0F, effect.alpha }, 0);
								effect.point_light.draw();
								continue label69;
							}

							++var10;
							break;
						}
					}
				}
			}
			glTexEnvi(GL_TEXTURE_ENV, GL_SRC0_RGB, GL_TEXTURE);
			glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glDepthMask(true);
			glFogfv(GL_FOG_COLOR, OpenGLEnvironment.fog_colour_buffer, 0);
			GLState.load_lighting_state();
		}
	}

	public static void update_visible_lights(int placement, int camera_pos_x, int camera_pos_y, int camera_pos_z, int local_x, int local_y, int local_z) {
		if (ClientOptions.clientoption_highdetails_lighting) {
			if (placement == 1 && local_y > 0) {//
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y - 1, local_z);
			} else if (placement == 4 && local_y < SceneLighting.num_width - 1) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y + 1, local_z);
			} else if (placement == 8 && local_z > 0) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y, local_z - 1);
			} else if (placement == 2 && local_z < SceneLighting.num_height - 1) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y, local_z + 1);
			} else if (placement == 16 && local_y > 0 && local_z < SceneLighting.num_height - 1) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y - 1, local_z + 1);
			} else if (placement == 32 && local_y < SceneLighting.num_width - 1 && local_z < SceneLighting.num_height - 1) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y + 1, local_z + 1);
			} else if (placement == 128 && local_y > 0 && local_z > 0) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y - 1, local_z - 1);
			} else if (placement == 64 && local_y < SceneLighting.num_width - 1 && local_z > 0) {
				update_visible_lights(camera_pos_x, camera_pos_y, camera_pos_z, local_x, local_y + 1, local_z - 1);
			}
		}
	}

	public static void add_light(FlickeringEffect effect) {
		if (num_lights >= 255) {
			System.out.println("Number of lights added exceeds maximum!");
		} else {
			lights[num_lights++] = effect;
		}
	}

	public static void disable_all() {
		for (int var0 = 0; var0 < 4; ++var0) {
			slots_owner[var0] = -1;
			disable_light(var0);
		}

	}

	public static void method1266(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		if (ClientOptions.clientoption_highdetails_lighting) {
			if (last_level != var3 || last_grid_x != var4 || last_grid_y != var5 || anInt1034 != var6 || anInt1025 != var7) {
				int var8;
				for (var8 = 0; var8 < 4; ++var8) {
					slots_enabled[var8] = false;
				}

				int var9 = 0;
				label105: for (int var10 = var4; var10 <= var6; ++var10) {
					for (int var11 = var5; var11 <= var7; ++var11) {
						int var12 = tile_lights[var3][var10][var11];

						label101: while (var12 != 0) {
							int var13 = (var12 & 255) - 1;
							var12 >>>= 8;
							for (int var14 = 0; var14 < var9; ++var14) {
								if (var13 == anIntArray1023[var14]) {
									continue label101;
								}
							}
							for (int var14 = 0; var14 < 4; ++var14) {
								if (var13 == slots_owner[var14]) {
									if (!slots_enabled[var14]) {
										slots_enabled[var14] = true;
										++var8;
										if (var8 == 4) {
											break label105;
										}
									}
									continue label101;
								}
							}
							anIntArray1023[var9++] = var13;
							++var8;
							if (var8 == 4) {
								break label105;
							}
						}
					}
				}
				int var10 = 0;
				while (var10 < var9) {
					int var11 = 0;
					while (true) {
						if (var11 < 4) {
							if (slots_enabled[var11]) {
								++var11;
								continue;
							}

							slots_owner[var11] = anIntArray1023[var10];
							slots_enabled[var11] = true;
							load_light_state(var11, lights[anIntArray1023[var10]], var0, var1, var2);
						}

						++var10;
						break;
					}
				}
				for (int slot = 0; slot < 4; ++slot) {
					if (!slots_enabled[slot]) {
						slots_owner[slot] = -1;
						disable_light(slot);
					}
				}
				last_level = var3;
				last_grid_x = var4;
				last_grid_y = var5;
				anInt1034 = var6;
				anInt1025 = var7;
			}
		}
	}

	public static void update_visible_lights(int camera_pos_x, int camera_pos_y, int var2, int level, int grid_x, int grid_y) {
		if (ClientOptions.clientoption_highdetails_lighting) {
			if (last_level != level || last_grid_x != grid_x || last_grid_y != grid_y || anInt1034 != grid_x || anInt1025 != grid_y) {
				for (int var6 = 0; var6 < 4; ++var6) {
					slots_enabled[var6] = false;
				}
				int var6 = 0;
				int var7 = tile_lights[level][grid_x][grid_y];
				label71: while (var7 != 0) {
					int var8 = (var7 & 255) - 1;
					var7 >>>= 8;
					for (int slot = 0; slot < 4; ++slot) {
						if (var8 == slots_owner[slot]) {
							slots_enabled[slot] = true;
							continue label71;
						}
					}
					anIntArray1023[var6++] = var8;
				}
				int var8 = 0;
				while (var8 < var6) {
					int var9 = 0;
					while (true) {
						if (var9 < 4) {
							if (slots_enabled[var9]) {
								++var9;
								continue;
							}
							slots_owner[var9] = anIntArray1023[var8];
							slots_enabled[var9] = true;
							load_light_state(var9, lights[anIntArray1023[var8]], camera_pos_x, camera_pos_y, var2);
						}
						var8++;
						break;
					}
				}
				for (int light = 0; light < 4; ++light) {
					if (!slots_enabled[light]) {
						slots_owner[light] = -1;
						disable_light(light);
					}
				}
				last_level = level;
				last_grid_x = grid_x;
				last_grid_y = grid_y;
				anInt1034 = grid_x;
				anInt1025 = grid_y;
			}
		}
	}

	public static void load_light_state(int slot, FlickeringEffect var1, int var2, int var3, int var4) {
		int gl_slot = slot + GL_LIGHT4;
		if (!active_lights[slot]) {
			glEnable(gl_slot);
			active_lights[slot] = true;
		}
		light_position_buffer[0] = var1.x - var2;
		light_position_buffer[1] = var1.z - var3;
		light_position_buffer[2] = var1.y - var4;
		glLightf(gl_slot, GL_QUADRATIC_ATTENUATION, var1.attenuation);
		glLightfv(gl_slot, GL_DIFFUSE, var1.color_buffer, 0);
		glLightfv(gl_slot, GL_POSITION, light_position_buffer, 0);
	}

	public static void method1269(int var0, boolean var1) {
		for (int var2 = 0; var2 < SceneLighting.num_lights; ++var2) {
			lights[var2].method1063(var1, var0, -3696);
		}
		last_level = -1;
		last_grid_x = -1;
		last_grid_y = -1;
		anInt1034 = -1;
		anInt1025 = -1;
	}

	public static void bake() {
		for (int slot = 0; slot < SceneLighting.num_lights; ++slot) {
			FlickeringEffect effect = lights[slot];
			int start_level = effect.level;
			if (effect.grows_upwards) {
				start_level = 0;
			}
			int end_level = effect.level;
			if (effect.grows_downwards) {
				end_level = 3;
			}
			for (int level = start_level; level <= end_level; ++level) {
				int point = 0;
				int start_y = (effect.y >> 7) - effect.radius;
				if (start_y < 0) {
					point -= start_y;
					start_y = 0;
				}
				int end_y = (effect.y >> 7) + effect.radius;
				if (end_y > num_height - 1) {
					end_y = num_height - 1;
				}
				for (int y = start_y; y <= end_y; ++y) {
					short expanding = effect.ranges[point++];
					int start_x = (effect.x >> 7) - effect.radius + (expanding >> 8);
					int end_x = start_x + (expanding & 255) - 1;
					if (start_x < 0) {
						start_x = 0;
					}
					if (end_x > num_width - 1) {
						end_x = num_width - 1;
					}
					for (int x = start_x; x <= end_x; ++x) {
						int lighting = tile_lights[level][x][y];
						if ((lighting & 255) == 0) {
							tile_lights[level][x][y] = lighting | slot + 1;
						} else if ((lighting & 0xff00) == 0) {
							tile_lights[level][x][y] = lighting | slot + 1 << 8;
						} else if ((lighting & 16711680) == 0) {
							tile_lights[level][x][y] = lighting | slot + 1 << 16;
						} else if ((lighting & -16777216) == 0) {
							tile_lights[level][x][y] = lighting | slot + 1 << 24;
						}
					}
				}
			}
		}

	}

	public static void method1272(int var0, int var1, int var2, int var3, int var4) {
		if (ClientOptions.clientoption_highdetails_lighting) {
			label44: for (int var5 = 0; var5 < 4; ++var5) {
				if (slots_owner[var5] != -1) {
					int var6 = tile_lights[var0][var1][var2];
					int var7;
					while (var6 != 0) {
						var7 = (var6 & 255) - 1;
						var6 >>>= 8;
						if (var7 == slots_owner[var5]) {
							continue label44;
						}
					}

					var6 = tile_lights[var0][var3][var4];
					while (var6 != 0) {
						var7 = (var6 & 255) - 1;
						var6 >>>= 8;
						if (var7 == slots_owner[var5]) {
							continue label44;
						}
					}
				}
				slots_owner[var5] = -1;
				disable_light(var5);
			}

		}
	}

	public static void disable_light(int id) {
		if (active_lights[id]) {
			active_lights[id] = false;
			int var1 = id + GL_LIGHT4;
			glDisable(var1);
		}
	}

	public static void reset_state() {
		for (int slot = 0; slot < 4; slot++) {
			int gl_slot = GL_LIGHT4 + slot;
			glLightfv(gl_slot, GL_AMBIENT, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
			glLightf(gl_slot, GL_LINEAR_ATTENUATION, 0.0F);
			glLightf(gl_slot, GL_CONSTANT_ATTENUATION, 0.0F);
		}
		for (int slot = 0; slot < 4; slot++) {
			slots_owner[slot] = -1;
			disable_light(slot);
		}
	}

	public static void destroy_lights() {
		lights = null;
		slots_owner = null;
		anIntArray1023 = null;
		slots_enabled = null;
		tile_lights = null;
	}

}
