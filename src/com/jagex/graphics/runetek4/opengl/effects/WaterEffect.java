package com.jagex.graphics.runetek4.opengl.effects;

import static jaggl.GLConstants.GL_ADD;
import static jaggl.GLConstants.GL_COMBINE_RGB;
import static jaggl.GLConstants.GL_MODELVIEW;
import static jaggl.GLConstants.GL_MODULATE;
import static jaggl.GLConstants.GL_REPLACE;
import static jaggl.GLConstants.GL_TEXTURE;
import static jaggl.GLConstants.GL_TEXTURE0;
import static jaggl.GLConstants.GL_TEXTURE1;
import static jaggl.GLConstants.GL_TEXTURE_2D;
import static jaggl.GLConstants.GL_TEXTURE_3D;
import static jaggl.GLConstants.GL_TEXTURE_ENV;
import static jaggl.OpenGL.*;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.water.WaterTextures;
import com.rs2.client.scene.environment.FlickeringTextureGenerator;

public class WaterEffect implements MaterialEffect {

	//@formatter:off
	public static final String PROGRAM = "!!ARBvp1.0\n"+
			"ATTRIB  iPos         = vertex.position;\n"+
			"ATTRIB  iColour      = vertex.color;\n"+
			"OUTPUT  oPos         = result.position;\n"+
			"OUTPUT  oColour      = result.color;\n"+
			"OUTPUT  oTexCoord0   = result.texcoord[0];\n"+
			"OUTPUT  oTexCoord1   = result.texcoord[1];\n"+
			"OUTPUT  oFogCoord    = result.fogcoord;\n"+
			"PARAM   time         = program.local[65];\n"+
			"PARAM   turbulence   = program.local[64];\n"+
			"PARAM   lightAmbient = program.local[66]; \n"+
			"PARAM   pMatrix[4]   = { state.matrix.projection };\n"+
			"PARAM   mvMatrix[4]  = { state.matrix.modelview };\n"+
			"PARAM   ivMatrix[4]  = { state.matrix.texture[1] };\n"+
			"PARAM   fNoise[64]   = { program.local[0..63] };\n"+
			"TEMP    noise, clipPos, viewPos, worldPos;\n"+
			"ADDRESS noiseAddr;\n"+
			"DP4   viewPos.x, mvMatrix[0], iPos;\n"+
			"DP4   viewPos.y, mvMatrix[1], iPos;\n"+
			"DP4   viewPos.z, mvMatrix[2], iPos;\n"+
			"DP4   viewPos.w, mvMatrix[3], iPos;\n"+
			"DP4   worldPos.x, ivMatrix[0], viewPos;\n"+
			"DP4   worldPos.y, ivMatrix[1], viewPos;\n"+
			"DP4   worldPos.z, ivMatrix[2], viewPos;\n"+
			"DP4   worldPos.w, ivMatrix[3], viewPos;\n"+
			"ADD   noise.x, worldPos.x, worldPos.z;\n"+
			"SUB   noise.y, worldPos.z, worldPos.x;\n"+
			"MUL   noise, noise, 0.0001220703125;\n"+
			"FRC   noise, noise;\n"+
			"MUL   noise, noise, 64;\n"+
			"ARL   noiseAddr.x, noise.x;\n"+
			"MOV   noise.x, fNoise[noiseAddr.x].x;\n"+
			"ARL   noiseAddr.x, noise.y;\n"+
			"MOV   noise.y, fNoise[noiseAddr.x].y;\n"+
			"MUL   noise, noise, turbulence.x;\n"+
			"MAD   oTexCoord0, worldPos.xzww, 0.0078125, noise;\n"+
			"MOV   oTexCoord0.w, 1;\n"+
			"MUL   oTexCoord1.xy, worldPos.xzww, 0.0009765625;\n"+
			"MOV   oTexCoord1.zw, time.xxxw;\n"+
			"DP4   clipPos.x, pMatrix[0], viewPos;\n"+
			"DP4   clipPos.y, pMatrix[1], viewPos;\n"+
			"DP4   clipPos.z, pMatrix[2], viewPos;\n"+
			"DP4   clipPos.w, pMatrix[3], viewPos;\n"+
			"MUL   oColour.xyz, iColour, lightAmbient;\n"+
			"MOV   oColour.w, 1;\n"+
			"MOV   oFogCoord.x, clipPos.z;\n"+
			"MOV   oPos, clipPos;\n"+
			"END";
	//@formatter:on
	public static float[] aFloatArray2185 = new float[4];
	public static int water_xangle;
	public static int water_yangle;
	public static int water_zoffset;
	public static int water_yoffset;
	public static int water_xoffset;

	public int last_animation_offset = -1;
	public float[] noise;
	public int programid;
	public int drawlist;

	public WaterEffect() {
		if (GLManager.vertex_program_supported && GLManager.max_texture_units >= 2) {
			programid = glGenProgramARB();
			int[][] var3 = FlickeringTextureGenerator.method3378(64, 256, 4, 4, 3, 0.4F, false);
			int[][] var4 = FlickeringTextureGenerator.method3378(64, 256, 4, 4, 3, 0.4F, false);
			noise = new float[32768];
			int i = 0;
			for (int var6 = 0; var6 < 256; ++var6) {
				int[] var7 = var3[var6];
				int[] var8 = var4[var6];
				for (int var9 = 0; var9 < 64; ++var9) {
					noise[i++] = var7[var9] / 4096.0F;
					noise[i++] = var8[var9] / 4096.0F;
				}
			}
			method1749();
			method1750();
		}
	}

	@Override
	public final void disable() {
		if (drawlist >= 0) {
			glCallList(drawlist + 1);
		}
	}

	@Override
	public final void enable() {
		if (drawlist >= 0) {
			glCallList(drawlist);
			glActiveTexture(GL_TEXTURE1);
			glMatrixMode(GL_TEXTURE);
			glTranslatef(WaterEffect.water_xoffset, WaterEffect.water_yoffset, WaterEffect.water_zoffset);
			glRotatef(-(WaterEffect.water_yangle * 360.0F) / 2048.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(-(WaterEffect.water_xangle * 360.0F) / 2048.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
			glMatrixMode(GL_MODELVIEW);
			if (!WaterTextures.textures_3d) {
				glBindTexture(GL_TEXTURE_2D, WaterTextures.waterlakes_round_textures[(int) (OpenGLEffects.animation_offset * 64 * 0.0050F) % 64].handle);
			}
			glActiveTexture(GL_TEXTURE0);
			if (last_animation_offset != OpenGLEffects.animation_offset) {
				int var2 = (OpenGLEffects.animation_offset & 255) * 256;
				for (int index = 0; index < 64; ++index) {
					glProgramLocalParameter4fvARB(34336, index, noise, var2);
					var2 += 2;
				}
				if (WaterTextures.textures_3d) {
					glProgramLocalParameter4fARB(34336, 65, OpenGLEffects.animation_offset * 0.0050F, 0.0F, 0.0F, 1.0F);
				} else {
					glProgramLocalParameter4fARB(34336, 65, 0.0F, 0.0F, 0.0F, 1.0F);
				}
				last_animation_offset = OpenGLEffects.animation_offset;
			}

		}
	}

	@Override
	public final void update_param(int var1) {
		if (drawlist >= 0) {
			glActiveTexture(GL_TEXTURE1);
			if ((var1 & 128) == 0) {
				glEnable(WaterTextures.textures_3d ? GL_TEXTURE_3D : GL_TEXTURE_2D);
			} else {
				glDisable(WaterTextures.textures_3d ? GL_TEXTURE_3D : GL_TEXTURE_2D);
			}
			glActiveTexture(GL_TEXTURE0);
			if ((var1 & 64) == 0) {
				glGetFloatv(2899, aFloatArray2185, 0);
				glProgramLocalParameter4fvARB(34336, 66, aFloatArray2185, 0);
			} else {
				glProgramLocalParameter4fARB(34336, 66, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			int var3 = var1 & 3;
			if (var3 == 2) {
				glProgramLocalParameter4fARB(34336, 64, 0.05F, 1.0F, 1.0F, 1.0F);
			} else if (var3 == 3) {
				glProgramLocalParameter4fARB(34336, 64, 0.1F, 1.0F, 1.0F, 1.0F);
			} else {
				glProgramLocalParameter4fARB(34336, 64, 0.025F, 1.0F, 1.0F, 1.0F);
			}

		}
	}

	@Override
	public final int get_render_settings() {
		return 0;
	}

	public final void method1749() {
		drawlist = glGenLists(2);
		glNewList(drawlist, 4864);
		glActiveTexture(GL_TEXTURE1);
		if (WaterTextures.textures_3d) {
			glBindTexture(GL_TEXTURE_3D, WaterTextures.waterlakes_texture.handle);
		}
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_ADD);
		glTexEnvi(GL_TEXTURE_ENV, 0x8572, GL_REPLACE);
		glTexEnvi(GL_TEXTURE_ENV, 0x8588, 0x8578);
		glActiveTexture(GL_TEXTURE0);
		glBindProgramARB(34336, programid);
		glEnable(34336);
		glEndList();
		glNewList(drawlist + 1, 4864);
		glActiveTexture(GL_TEXTURE1);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
		glTexEnvi(GL_TEXTURE_ENV, 0x8572, GL_MODULATE);
		glTexEnvi(GL_TEXTURE_ENV, 0x8588, GL_TEXTURE);
		glDisable(WaterTextures.textures_3d ? GL_TEXTURE_3D : GL_TEXTURE_2D);
		glActiveTexture(GL_TEXTURE0);
		glBindProgramARB(34336, 0);
		glDisable(0x8620);
		glDisable(0x8804);
		glEndList();
	}

	public final void method1750() {
		if (drawlist >= 0) {
			int[] var2 = new int[1];
			glBindProgramARB(34336, programid);
			glProgramStringARB(34336, 0x8875, PROGRAM);
			glGetIntegerv(0x864b, var2, 0);
			if (var2[0] != -1) {
				return;
			}
		}

	}

	public static void updater_watereffect(int xoff, int yoff, int zoff, int xan, int yan) {
		water_xoffset = xoff;
		water_yoffset = yoff;
		water_zoffset = zoff;
		water_xangle = xan;
		water_yangle = yan;
	}

}
