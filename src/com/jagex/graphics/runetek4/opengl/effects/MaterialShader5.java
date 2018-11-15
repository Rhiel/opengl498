package com.jagex.graphics.runetek4.opengl.effects;

import static jaggl.OpenGL.glActiveTexture;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glCallList;
import static jaggl.OpenGL.glDisable;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glEndList;
import static jaggl.OpenGL.glGenLists;
import static jaggl.OpenGL.glLoadIdentity;
import static jaggl.OpenGL.glMatrixMode;
import static jaggl.OpenGL.glNewList;
import static jaggl.OpenGL.glPopMatrix;
import static jaggl.OpenGL.glPushMatrix;
import static jaggl.OpenGL.glRotatef;
import static jaggl.OpenGL.glTexGenfv;
import static jaggl.OpenGL.glTexGeni;
import static jaggl.OpenGL.glTranslatef;

import com.jagex.graphics.runetek4.opengl.water.WaterTextures;

public class MaterialShader5 implements MaterialEffect {

	private int anInt2173;
	private float[] aFloatArray2174 = new float[4];

	public MaterialShader5() {
		initialise();
	}

	private void initialise() {
		anInt2173 = glGenLists(2);
		glNewList(anInt2173, 4864);
		glActiveTexture('\u84c1');
		if (!WaterTextures.textures_3d) {
			glEnable(3553);
		} else {
			glBindTexture('\u806f', WaterTextures.waterfalls_texture.handle);
			glTexGeni(8194, 9472, 9217);
			glEnable(3170);
			glEnable('\u806f');
		}

		glTexGeni(8192, 9472, 9216);
		glTexGeni(8193, 9472, 9216);
		glEnable(3168);
		glEnable(3169);
		glActiveTexture('\u84c0');
		glEndList();
		glNewList(anInt2173 + 1, 4864);
		glActiveTexture('\u84c1');
		if (WaterTextures.textures_3d) {
			glDisable('\u806f');
			glDisable(3170);
		} else {
			glDisable(3553);
		}

		glDisable(3168);
		glDisable(3169);
		glActiveTexture('\u84c0');
		glEndList();
	}

	@Override
	public void enable() {
		glCallList(anInt2173);
	}

	@Override
	public void update_param(int var1) {
		float var4 = (1 + (var1 >> 3 & 3)) * 0.01F;
		float var3 = -0.01F * (1 + (var1 & 3));
		float var5 = 0 == (var1 & 64) ? 4.8828125E-4F : 9.765625E-4F;
		boolean var6 = -1 != ~(128 & var1);
		if (var6) {
			aFloatArray2174[0] = var5;
			aFloatArray2174[1] = 0.0F;
			aFloatArray2174[2] = 0.0F;
			aFloatArray2174[3] = 0.0F;
		} else {
			aFloatArray2174[2] = var5;
			aFloatArray2174[1] = 0.0F;
			aFloatArray2174[3] = 0.0F;
			aFloatArray2174[0] = 0.0F;
		}

		glActiveTexture('\u84c1');
		glMatrixMode(5888);
		glPushMatrix();
		glLoadIdentity();
		glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		glRotatef(WaterEffect.water_xangle * 360.0F / 2048.0F, 1.0F, 0.0F, 0.0F);
		glRotatef(360.0F * WaterEffect.water_yangle / 2048.0F, 0.0F, 1.0F, 0.0F);
		glTranslatef(-WaterEffect.water_xoffset, -WaterEffect.water_yoffset, -WaterEffect.water_zoffset);
		glTexGenfv(8192, 9474, aFloatArray2174, 0);
		aFloatArray2174[3] = var3 * OpenGLEffects.animation_offset;
		aFloatArray2174[0] = 0.0F;
		aFloatArray2174[2] = 0.0F;
		aFloatArray2174[1] = var5;
		glTexGenfv(8193, 9474, aFloatArray2174, 0);
		glPopMatrix();
		if (!WaterTextures.textures_3d) {
			int var7 = (int) (OpenGLEffects.animation_offset * var4 * 64.0F);
			glBindTexture(3553, WaterTextures.waterfall_round_textures[var7 % 64].handle);
		} else {
			aFloatArray2174[3] = OpenGLEffects.animation_offset * var4;
			aFloatArray2174[1] = 0.0F;
			aFloatArray2174[0] = 0.0F;
			aFloatArray2174[2] = 0.0F;
			glTexGenfv(8194, 9473, aFloatArray2174, 0);
		}

		glActiveTexture('\u84c0');
	}

	@Override
	public void disable() {
		glCallList(1 + anInt2173);
	}

	@Override
	public int get_render_settings() {
		return 0;
	}

}
