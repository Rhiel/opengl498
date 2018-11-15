package com.jagex.graphics.runetek4.opengl.effects;

import static jaggl.GLConstants.*;
import static jaggl.OpenGL.glActiveTexture;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glCallList;
import static jaggl.OpenGL.glDisable;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glEndList;
import static jaggl.OpenGL.glGenLists;
import static jaggl.OpenGL.glGenTextures;
import static jaggl.OpenGL.glLoadIdentity;
import static jaggl.OpenGL.glMatrixMode;
import static jaggl.OpenGL.glNewList;
import static jaggl.OpenGL.glRotatef;
import static jaggl.OpenGL.glTexEnvi;
import static jaggl.OpenGL.glTexGeni;
import static jaggl.OpenGL.glTexImage2Dub;
import static jaggl.OpenGL.glTexParameteri;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;

public class SkyboxEffect implements MaterialEffect {

	public int drawlist = -1;
	public boolean has_more_units;
	public int[] textureids;

	public SkyboxEffect() {
		if (GLManager.cubemap_supported && GLManager.max_texture_units >= 2) {
			initializeTextures();
			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[0]);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[1]);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[2]);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			has_more_units = GLManager.max_texture_units < 3;
		}
		compile_drawlist();
	}

	public final void compile_drawlist() {
		drawlist = glGenLists(2);
		glNewList(drawlist, GL_COMPILE);
		if (textureids != null) {
			glActiveTexture(GL_TEXTURE1);
			glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_NORMAL_MAP);
			glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_NORMAL_MAP);
			glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_NORMAL_MAP);
			glEnable(GL_TEXTURE_GEN_S);
			glEnable(GL_TEXTURE_GEN_T);
			glEnable(GL_TEXTURE_GEN_R);
			glEnable(GL_TEXTURE_CUBE_MAP);
			glMatrixMode(GL_TEXTURE);
			glLoadIdentity();
			glRotatef(22.5F, 1.0F, 0.0F, 0.0F);
			glMatrixMode(GL_MODELVIEW);
			if (!has_more_units) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PREVIOUS);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
				glActiveTexture(GL_TEXTURE2);
				glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_COMBINE);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_ADD);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PREVIOUS);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE1_RGB, GL_PREVIOUS);
				glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND1_RGB, GL_SRC_ALPHA);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_REPLACE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_PRIMARY_COLOR);
				glBindTexture(GL_TEXTURE_2D, OpenGLEffects.lights_texture.handle);
				glEnable(GL_TEXTURE_2D);
			} else {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_ADD);
				glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_ALPHA);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_REPLACE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_PRIMARY_COLOR);
			}
			glActiveTexture(GL_TEXTURE0);
		} else {
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_PRIMARY_COLOR);
		}
		glEndList();
		glNewList(drawlist + 1, GL_COMPILE);
		if (textureids != null) {
			glActiveTexture(GL_TEXTURE1);
			glDisable(GL_TEXTURE_GEN_S);
			glDisable(GL_TEXTURE_GEN_T);
			glDisable(GL_TEXTURE_GEN_R);
			glDisable(GL_TEXTURE_CUBE_MAP);
			glMatrixMode(GL_TEXTURE);
			glLoadIdentity();
			glMatrixMode(GL_MODELVIEW);
			if (!has_more_units) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
				glActiveTexture(GL_TEXTURE2);
				glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
				glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND1_RGB, GL_SRC_COLOR);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
				glDisable(GL_TEXTURE_2D);
			} else {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
				glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
			}

			glActiveTexture(GL_TEXTURE0);
		} else {
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
		}
		glEndList();
	}

	@Override
	public final void disable() {
		if (ClientOptions.clientoption_highdetails_lighting) {
			glCallList(drawlist + 1);
		} else {
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
		}

	}

	@Override
	public final void enable() {
		GLState.set_alpha_combine_mode(1);
		if (ClientOptions.clientoption_highdetails_lighting) {
			glCallList(drawlist);
		} else {
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_PRIMARY_COLOR);
		}

	}

	@Override
	public final void update_param(int tex) {
		if (ClientOptions.clientoption_highdetails_lighting && textureids != null) {
			glActiveTexture(GL_TEXTURE1);
			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[tex - 1]);
			glActiveTexture(GL_TEXTURE0);
		}

	}

	@Override
	public final int get_render_settings() {
		return 4;
	}

	public final void initializeTextures() {
		if (textureids == null) {
			textureids = new int[3];
			glGenTextures(3, textureids, 0);
		}
		short var9 = 4096;
		byte[] var10 = new byte[var9];
		byte[] var11 = new byte[var9];
		byte[] var12 = new byte[var9];
		for (int side = 0; side < 6; ++side) {
			int var14 = 0;
			for (int y = 0; y < 64; y++) {
				for (int x = 0; x < 64; x++) {
					float var5 = 2.0F * x / 64.0F - 1.0F;
					float var6 = 2.0F * y / 64.0F - 1.0F;
					float var7 = (float) (1.0D / Math.sqrt(var5 * var5 + 1.0F + var6 * var6));
					var5 *= var7;
					var6 *= var7;
					float var4;
					if (side == 0) {
						var4 = -var5;
					} else if (side == 1) {
						var4 = var5;
					} else if (side == 2) {
						var4 = var6;
					} else if (side == 3) {
						var4 = -var6;
					} else if (side == 4) {
						var4 = var7;
					} else {
						var4 = -var7;
					}
					int var1;
					int var2;
					int var3;
					if (var4 > 0.0F) {
						var1 = (int) (Math.pow(var4, 96.0D) * 255.0D);
						var2 = (int) (Math.pow(var4, 36.0D) * 255.0D);
						var3 = (int) (Math.pow(var4, 12.0D) * 255.0D);
					} else {
						var3 = 0;
						var2 = 0;
						var1 = 0;
					}
					if (GLManager.max_texture_units < 3) {
						var1 /= 5;
						var2 /= 5;
						var3 /= 5;
					} else {
						var1 /= 2;
						var2 /= 2;
						var3 /= 2;
					}
					var11[var14] = (byte) var1;
					var12[var14] = (byte) var2;
					var10[var14] = (byte) var3;
					var14++;
				}
			}

			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[0]);
			glTexImage2Dub(GL_TEXTURE_CUBE_MAP_POSITIVE_X + side, 0, GL_ALPHA, 64, 64, 0, GL_ALPHA, GL_UNSIGNED_BYTE, var11, 0);
			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[1]);
			glTexImage2Dub(GL_TEXTURE_CUBE_MAP_POSITIVE_X + side, 0, GL_ALPHA, 64, 64, 0, GL_ALPHA, GL_UNSIGNED_BYTE, var12, 0);
			glBindTexture(GL_TEXTURE_CUBE_MAP, textureids[2]);
			glTexImage2Dub(GL_TEXTURE_CUBE_MAP_POSITIVE_X + side, 0, GL_ALPHA, 64, 64, 0, GL_ALPHA, GL_UNSIGNED_BYTE, var10, 0);
			GLManager.allocated_textures_size += var9 * 3;
		}

	}
}
