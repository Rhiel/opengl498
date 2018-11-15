package com.rs2.client.scene.shadow;

import static com.jagex.graphics.runetek4.opengl.GLState.load_lighting_state;
import static com.jagex.graphics.runetek4.opengl.GLState.reset_texture_matrix;
import static com.jagex.graphics.runetek4.opengl.GLState.set_alpha_combine_mode;
import static com.jagex.graphics.runetek4.opengl.GLState.set_combine_rgb_mode;
import static com.jagex.graphics.runetek4.opengl.GLState.set_lights_enabled;
import static jaggl.OpenGL.glDepthMask;
import static jaggl.OpenGL.glPopMatrix;
import static jaggl.OpenGL.glPushMatrix;
import static jaggl.OpenGL.glTranslatef;

import com.jagex.LocResult;
import com.jagex.LocType;
import com.jagex.graphics.runetek4.opengl.effects.OpenGLEffects;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.jagex.graphics.runetek4.opengl.shadow.ShadowMap;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.rs2.client.scene.Scene;

public class SceneShadowMapper {

	private static int sceneHeightBlocks;
	private static int sceneWidthBlocks;
	public static ShadowMap[][] shadow_maps;
	public static SoftwarePaletteSprite shadows_sprite;
	public static SoftwarePaletteSprite[] floorshadows;
	public static SoftwarePaletteSprite aClass109_Sub1_2631;

	private static void method2034(SoftwarePaletteSprite var0, SoftwarePaletteSprite var1, int var2, int var3) {
		var2 += var0.offset_x;
		var3 += var0.offset_y;
		int var4 = var2 + var3 * var1.width;
		int var5 = 0;
		int var6 = var0.height;
		int var7 = var0.width;
		int var8 = var1.width - var7;
		int var9 = 0;
		int var10;
		if (var3 <= 0) {
			var10 = 1 - var3;
			var6 -= var10;
			var5 += var10 * var7;
			var4 += var10 * var1.width;
			var3 = 1;
		}

		if (var3 + var6 >= var1.height) {
			var10 = var3 + var6 + 1 - var1.height;
			var6 -= var10;
		}

		if (var2 <= 0) {
			var10 = 1 - var2;
			var7 -= var10;
			var5 += var10;
			var4 += var10;
			var9 += var10;
			var8 += var10;
			var2 = 1;
		}

		if (var2 + var7 >= var1.width) {
			var10 = var2 + var7 + 1 - var1.width;
			var7 -= var10;
			var9 += var10;
			var8 += var10;
		}

		if (var7 > 0 && var6 > 0) {
			method2044(var1.pixels, var0.pixels, var5, var4, var7, var6, var8, var9);
			method2036(var2, var3, var7, var6);
		}
	}

	private static void method2035(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		int var8 = -(var4 >> 2);
		var4 = -(var4 & 3);

		for (int var9 = -var5; var9 < 0; ++var9) {
			int var10;
			int var10001;
			for (var10 = var8; var10 < 0; ++var10) {
				var10001 = var3++;
				var0[var10001] -= var1[var2++];
				var10001 = var3++;
				var0[var10001] -= var1[var2++];
				var10001 = var3++;
				var0[var10001] -= var1[var2++];
				var10001 = var3++;
				var0[var10001] -= var1[var2++];
			}

			for (var10 = var4; var10 < 0; ++var10) {
				var10001 = var3++;
				var0[var10001] -= var1[var2++];
			}

			var3 += var6;
			var2 += var7;
		}

	}

	private static void method2036(int var0, int var1, int var2, int var3) {
		int var4 = var0 - 1 >> 7;
		int var5 = var0 - 1 + var2 - 1 >> 7;
		int var6 = var1 - 1 >> 7;
		int var7 = var1 - 1 + var3 - 1 >> 7;

		for (int var8 = var4; var8 <= var5; ++var8) {
			for (int var9 = var6; var9 <= var7; ++var9) {
				shadow_maps[var8][var9].is_dirty = true;
			}
		}

	}

	public static void add_tile_shadow(int var0, int var1, boolean var2, boolean var3, int var4, int var5, int var6, int var7, int var8, int var9) {
		if (!var2 || !var3) {
			if (!var2 || var0 != 1) {
				if (!var3 || var0 != 0) {
					int var10 = var4 << 7;
					int var11 = var6 + var7 + var8 + var9 >> 2;
					int var12 = var5 << 7;
					int var13 = var10 - (var11 * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
					int var14 = var12 - (var11 * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
					if (var0 != 0 && var0 != 1 && (var2 || var3)) {
						method2050(floorshadows[var0], shadows_sprite, var13 + 1, var14 + 1, var1, var2);
					} else {
						method2034(floorshadows[1], shadows_sprite, var13 + 1, var14 + 1);
					}
				}
			}
		}
	}

	public static void drawShadows(int var0, int var1, int var2, int var3, boolean[][] var4, int[][] var5) {
		set_combine_rgb_mode(1);
		set_alpha_combine_mode(1);
		reset_texture_matrix();
		set_lights_enabled(false);
		OpenGLEffects.setup_effect(0, 0);
		glDepthMask(false);
		for (int blockX = 0; blockX < sceneWidthBlocks; ++blockX) {
			int blockY = 0;
			while (blockY < sceneHeightBlocks) {
				int var9 = blockX * 8;
				while (true) {
					if (var9 < blockX * 8 + 8) {
						label42: {
							if (var9 - var0 >= -var2 && var9 - var0 <= var2) {
								for (int var10 = blockY * 8; var10 < blockY * 8 + 8; ++var10) {
									if (var10 - var1 >= -var2 && var10 - var1 <= var2 && var4[var9 - var0 + var2][var10 - var1 + var2]) {
										ShadowMap shadows = shadow_maps[blockX][blockY];
										if (shadows.is_dirty) {
											shadows.update(SceneShadowMapper.shadows_sprite, blockX, blockY);
											shadows.is_dirty = false;
										}
										glPushMatrix();
										glTranslatef(blockX * 1024, 0.0F, blockY * 1024);
										shadows.draw();
										glPopMatrix();
										break label42;
									}
								}
							}

							++var9;
							continue;
						}
					}
					++blockY;
					break;
				}
			}
		}
		glDepthMask(true);
		load_lighting_state();
	}

	private static boolean method2039(byte[] var0, int var1, int var2, int var3, int var4, int var5) {
		int var6 = var2 % var5;
		int var7;
		if (var6 != 0) {
			var7 = var5 - var6;
		} else {
			var7 = 0;
		}

		int var8 = -((var3 + var5 - 1) / var5);
		int var9 = -((var2 + var5 - 1) / var5);

		for (int var10 = var8; var10 < 0; ++var10) {
			for (int var11 = var9; var11 < 0; ++var11) {
				if (var0[var1] == 0) {
					return true;
				}

				var1 += var5;
			}

			var1 -= var7;
			if (var0[var1 - 1] == 0) {
				return true;
			}

			var1 += var4;
		}

		return false;
	}

	private static boolean method2040(SoftwarePaletteSprite var0, SoftwarePaletteSprite var1, int var2, int var3) {
		var2 += var0.offset_x;
		var3 += var0.offset_y;
		int var4 = var2 + var3 * var1.width;
		int var5 = var0.height;
		int var6 = var0.width;
		int var7 = var1.width - var6;
		int var8;
		if (var3 <= 0) {
			var8 = 1 - var3;
			var5 -= var8;
			var4 += var8 * var1.width;
			var3 = 1;
		}

		if (var3 + var5 >= var1.height) {
			var8 = var3 + var5 + 1 - var1.height;
			var5 -= var8;
		}

		if (var2 <= 0) {
			var8 = 1 - var2;
			var6 -= var8;
			var4 += var8;
			var7 += var8;
			var2 = 1;
		}

		if (var2 + var6 >= var1.width) {
			var8 = var2 + var6 + 1 - var1.width;
			var6 -= var8;
			var7 += var8;
		}

		if (var6 > 0 && var5 > 0) {
			byte var9 = 8;
			var7 += (var9 - 1) * var1.width;
			method2036(var2, var3, var6, var5);
			return method2039(var1.pixels, var4, var6, var5, var7, var9);
		} else {
			return false;
		}
	}

	public static void initializeShadows(int var0, int var1) {
		sceneWidthBlocks = var0 + 7 >> 3;
		sceneHeightBlocks = var1 + 7 >> 3;
		shadows_sprite = new SoftwarePaletteSprite(sceneWidthBlocks * 128 + 2, sceneHeightBlocks * 128 + 2, 0);
		shadow_maps = new ShadowMap[sceneWidthBlocks][sceneHeightBlocks];

		for (int var2 = 0; var2 < sceneWidthBlocks; ++var2) {
			for (int var3 = 0; var3 < sceneHeightBlocks; ++var3) {
				shadow_maps[var2][var3] = new ShadowMap();
			}
		}

	}

	private static void method2042(SoftwarePaletteSprite var0, SoftwarePaletteSprite var1, int var2, int var3) {
		var2 += var0.offset_x;
		var3 += var0.offset_y;
		int var4 = var2 + var3 * var1.width;
		int var5 = 0;
		int var6 = var0.height;
		int var7 = var0.width;
		int var8 = var1.width - var7;
		int var9 = 0;
		int var10;
		if (var3 <= 0) {
			var10 = 1 - var3;
			var6 -= var10;
			var5 += var10 * var7;
			var4 += var10 * var1.width;
			var3 = 1;
		}

		if (var3 + var6 >= var1.height) {
			var10 = var3 + var6 + 1 - var1.height;
			var6 -= var10;
		}

		if (var2 <= 0) {
			var10 = 1 - var2;
			var7 -= var10;
			var5 += var10;
			var4 += var10;
			var9 += var10;
			var8 += var10;
			var2 = 1;
		}

		if (var2 + var7 >= var1.width) {
			var10 = var2 + var7 + 1 - var1.width;
			var7 -= var10;
			var9 += var10;
			var8 += var10;
		}

		if (var7 > 0 && var6 > 0) {
			method2035(var1.pixels, var0.pixels, var5, var4, var7, var6, var8, var9);
			method2036(var2, var3, var7, var6);
		}
	}

	public static void method2043() {
		shadows_sprite = null;
		floorshadows = null;
		shadow_maps = null;
	}

	private static void method2044(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		int var8 = -(var4 >> 2);
		var4 = -(var4 & 3);

		for (int var9 = -var5; var9 < 0; ++var9) {
			int var10;
			int var10001;
			for (var10 = var8; var10 < 0; ++var10) {
				var10001 = var3++;
				var0[var10001] += var1[var2++];
				var10001 = var3++;
				var0[var10001] += var1[var2++];
				var10001 = var3++;
				var0[var10001] += var1[var2++];
				var10001 = var3++;
				var0[var10001] += var1[var2++];
			}

			for (var10 = var4; var10 < 0; ++var10) {
				var10001 = var3++;
				var0[var10001] += var1[var2++];
			}

			var3 += var6;
			var2 += var7;
		}

	}

	private static void method2046(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6) {
		for (int var7 = -16; var7 < 0; ++var7) {
			for (int var8 = -4; var8 < 0; ++var8) {
				int var10001 = var3++;
				var0[var10001] = (byte) (var0[var10001] + (1 - var1[var2]));
				var2 += var5;
				var10001 = var3++;
				var0[var10001] = (byte) (var0[var10001] + (1 - var1[var2]));
				var2 += var5;
				var10001 = var3++;
				var0[var10001] = (byte) (var0[var10001] + (1 - var1[var2]));
				var2 += var5;
				var10001 = var3++;
				var0[var10001] = (byte) (var0[var10001] + (1 - var1[var2]));
				var2 += var5;
			}

			var3 += var4;
			var2 += var6;
		}

	}

	public static void method2047(SoftwarePaletteSprite var0, int var1, int var2, int var3) {
		if (var0 != null) {
			int var4 = var1 - (var2 * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
			int var5 = var3 - (var2 * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
			method2042(var0, shadows_sprite, var4 + 1, var5 + 1);
		}
	}

	private static void method2048(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6) {
		for (int var7 = -16; var7 < 0; ++var7) {
			for (int var8 = -4; var8 < 0; ++var8) {
				int var10001 = var3++;
				var0[var10001] += var1[var2];
				var2 += var5;
				var10001 = var3++;
				var0[var10001] += var1[var2];
				var2 += var5;
				var10001 = var3++;
				var0[var10001] += var1[var2];
				var2 += var5;
				var10001 = var3++;
				var0[var10001] += var1[var2];
				var2 += var5;
			}

			var3 += var4;
			var2 += var6;
		}

	}

	public static boolean method2049(SoftwarePaletteSprite var0, int var1, int var2, int var3) {
		if (var0 == null) {
			return false;
		} else {
			int var4 = var1 - (var2 * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
			int var5 = var3 - (var2 * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
			return method2040(var0, shadows_sprite, var4 + 1, var5 + 1);
		}
	}

	private static void method2050(SoftwarePaletteSprite var0, SoftwarePaletteSprite var1, int var2, int var3, int var4, boolean var5) {
		if (var2 > 0 && var3 > 0 && var2 + 16 < var1.width && var3 + 16 < var1.height) {
			int var6 = var2 + var3 * var1.width;
			int var7 = var1.width - 16;
			short var8;
			byte var9;
			int var10;
			if (var4 == 0) {
				var8 = 240;
				var9 = 1;
				var10 = -var9 * 16 - 16;
			} else if (var4 == 1) {
				var8 = 255;
				var9 = -16;
				var10 = -var9 * 16 - 1;
			} else if (var4 == 2) {
				var8 = 15;
				var9 = -1;
				var10 = -var9 * 16 + 16;
			} else {
				var8 = 0;
				var9 = 16;
				var10 = -var9 * 16 + 1;
			}

			if (var5) {
				method2046(var1.pixels, var0.pixels, var8, var6, var7, var9, var10);
			} else {
				method2048(var1.pixels, var0.pixels, var8, var6, var7, var9, var10);
			}

			method2036(var2, var3, 16, 16);
		}
	}

	public static void method2051(SoftwarePaletteSprite var0, int var1, int var2, int var3) {
		if (var0 != null) {
			int var4 = var1 - (var2 * OpenGLEnvironment.shadows_scale_x >> 8) >> 3;
			int var5 = var3 - (var2 * OpenGLEnvironment.shadows_scale_z >> 8) >> 3;
			method2034(var0, shadows_sprite, var4 + 1, var5 + 1);
		}
	}

	public static void method840(LocType var0, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		int var9 = 3 & var3;
		int var10;
		int var11;
		if (-2 != ~var9 && -4 != ~var9) {
			var11 = var0.size3d;
			var10 = var0.size2d;
		} else {
			var10 = var0.size3d;
			var11 = var0.size2d;
		}
		int var14;
		int var15;
		if (-105 > ~(var7 - -var11)) {
			var15 = 1 + var7;
			var14 = var7;
		} else {
			var14 = var7 - -(var11 >> 1);
			var15 = var7 - -(1 + var11 >> 1);
		}

		int var16 = (var6 << 7) - -(var10 << 6);
		int var17 = (var7 << 7) + (var11 << 6);
		int var12;
		int var13;
		if (104 < var6 - -var10) {
			var12 = var6;
			var13 = var6 + 1;
		} else {
			var12 = var6 + (var10 >> 1);
			var13 = (var10 - -1 >> 1) + var6;
		}

		int[][] var18 = Scene.current_heightmap[var8];
		int var20 = 0;
		int var19 = var18[var12][var15] + var18[var12][var14] + var18[var13][var14] + var18[var13][var15] >> 2;
		int[][] var21;
		if (~var8 != -1) {
			var21 = Scene.current_heightmap[0];
			var20 = -(var21[var12][var15] + var21[var13][var14] + var21[var12][var14] - -var21[var13][var15] >> 2) + var19;
		}

		var21 = null;
		if (3 > var8) {
			var21 = Scene.current_heightmap[1 + var8];
		}

		LocResult var22 = var0.get_pair(var18, var3, var16, var5, var19, var21, false, var17, null, true);
		method2047(var22.shadow, -var4 + var16, var20, var17 + -var2);
	}

}
