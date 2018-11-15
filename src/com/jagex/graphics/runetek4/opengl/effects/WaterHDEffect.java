package com.jagex.graphics.runetek4.opengl.effects;

import static jaggl.GLConstants.*;
import static jaggl.OpenGL.glActiveTexture;
import static jaggl.OpenGL.glBindTexture;
import static jaggl.OpenGL.glDisable;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glLoadIdentity;
import static jaggl.OpenGL.glPopMatrix;
import static jaggl.OpenGL.glPushMatrix;
import static jaggl.OpenGL.glTexEnvf;
import static jaggl.OpenGL.glTexEnvfv;
import static jaggl.OpenGL.glTexEnvi;
import static jaggl.OpenGL.glTexGenfv;
import static jaggl.OpenGL.glTexGeni;

import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.list.OpenGLDisplayList;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture1D;
import com.jagex.graphics.runetek4.opengl.water.WaterTextures;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

/**
 * @author Walied K. Yassen
 */
public final class WaterHDEffect implements MaterialEffect {

	private static final char ENABLE_LIST = '\000';
	private static final char DISABLE_LIST = '\001';

	private static float[] aFloatArray2178 = new float[] { 0.1F, 0.1F, 0.15F, 0.1F };
	private final float[] aFloatArray2179 = new float[4];
	private int last_animated_offset = -1;
	private OpenGLTexture1D texture;
	public OpenGLDisplayList drawList;

	public WaterHDEffect() {
		initialise_texture();
		initialise_list();
	}

	private final void initialise_texture() {
		texture = new OpenGLTexture1D(PixelFormat.ALPHA, DataType.INT8, 2, new byte[] { (byte) 0, (byte) -1 }, PixelFormat.ALPHA);
		texture.set_linear_filter(true);
		texture.set_repeat(false);
	}

	private final void initialise_list() {
		drawList = new OpenGLDisplayList(2);
		drawList.newList(ENABLE_LIST);
		{
			glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE1_RGB, GL_CONSTANT);
			glTexEnvf(GL_TEXTURE_ENV, GL_RGB_SCALE, 2.0F);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE1_ALPHA, GL_CONSTANT);
			glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
			glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
			glTexGenfv(GL_S, GL_OBJECT_PLANE, new float[] { 9.765625E-4F, 0.0F, 0.0F, 0.0F }, 0);
			glTexGenfv(GL_T, GL_OBJECT_PLANE, new float[] { 0.0F, 0.0F, 9.765625E-4F, 0.0F }, 0);
			glEnable(GL_TEXTURE_GEN_S);
			glEnable(GL_TEXTURE_GEN_T);
			if (WaterTextures.textures_3d) {
				glBindTexture(GL_TEXTURE_3D, WaterTextures.waterlakes_texture.handle);
				glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
				glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
				glTexGenfv(GL_Q, GL_OBJECT_PLANE, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
				glEnable(GL_TEXTURE_GEN_R);
				glEnable(GL_TEXTURE_GEN_Q);
				glEnable(GL_TEXTURE_3D);
			}
			glActiveTexture(GL_TEXTURE1);
			glEnable(GL_TEXTURE_1D);
			glBindTexture(GL_TEXTURE_1D, texture.handle);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_INTERPOLATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_CONSTANT);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE2_RGB, GL_TEXTURE);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_INTERPOLATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_CONSTANT);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE2_ALPHA, GL_TEXTURE);
			glEnable(GL_TEXTURE_GEN_S);
			glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
			glPushMatrix();
			glLoadIdentity();
		}
		drawList.end();
		drawList.newList(DISABLE_LIST);
		{
			glActiveTexture(GL_TEXTURE1);
			glDisable(GL_TEXTURE_1D);
			glDisable(GL_TEXTURE_GEN_S);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE2_RGB, GL_CONSTANT);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE2_ALPHA, GL_CONSTANT);
			glActiveTexture(GL_TEXTURE0);
			glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE1_RGB, GL_PREVIOUS);
			glTexEnvf(GL_TEXTURE_ENV, GL_RGB_SCALE, 1.0F);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE1_ALPHA, GL_PREVIOUS);
			glDisable(GL_TEXTURE_GEN_S);
			glDisable(GL_TEXTURE_GEN_T);
			if (WaterTextures.textures_3d) {
				glDisable(GL_TEXTURE_GEN_R);
				glDisable(GL_TEXTURE_GEN_Q);
				glDisable(GL_TEXTURE_3D);
			}
		}
		drawList.end();
	}

	@Override
	public void enable() {
		GLState.set_combine_rgb_mode(2);
		GLState.set_alpha_combine_mode(2);
		GLState.reset_texture_matrix();
		drawList.call(ENABLE_LIST);
		float var2 = 2662.4001F;
		var2 += (WaterEffect.water_xangle - 128) * 0.5F;
		if (var2 >= 3328.0F) {
			var2 = 3327.0F;
		}
		aFloatArray2179[0] = 0.0F;
		aFloatArray2179[1] = 0.0F;
		aFloatArray2179[2] = 1.0F / (var2 - 3328.0F);
		aFloatArray2179[3] = var2 / (var2 - 3328.0F);
		glTexGenfv(GL_S, GL_EYE_PLANE, aFloatArray2179, 0);
		glPopMatrix();
		glActiveTexture(GL_TEXTURE0);
		glTexEnvfv(GL_TEXTURE_ENV, GL_TEXTURE_ENV_COLOR, aFloatArray2178, 0);
	}

	@Override
	public void disable() {
		drawList.call(DISABLE_LIST);
	}

	@Override
	public void update_param(int var1) {
		glActiveTexture(GL_TEXTURE1);
		glTexEnvfv(GL_TEXTURE_ENV, GL_TEXTURE_ENV_COLOR, WaterDepthEffect.fog_colour, 0);
		glActiveTexture(GL_TEXTURE0);
		if ((var1 & 1) == 1) {
			if (WaterTextures.textures_3d) {
				if (last_animated_offset != OpenGLEffects.animation_offset) {
					aFloatArray2179[0] = 0.0F;
					aFloatArray2179[1] = 0.0F;
					aFloatArray2179[2] = 0.0F;
					aFloatArray2179[3] = OpenGLEffects.animation_offset * 0.0050F;
					glTexGenfv(GL_R, GL_OBJECT_PLANE, aFloatArray2179, 0);
					last_animated_offset = OpenGLEffects.animation_offset;
				}
			} else {
				GLState.bind_texture(WaterTextures.waterlakes_round_textures[OpenGLEffects.animation_offset * 64 / 100 % 64].handle);
			}
		} else if (WaterTextures.textures_3d) {
			aFloatArray2179[0] = 0.0F;
			aFloatArray2179[1] = 0.0F;
			aFloatArray2179[2] = 0.0F;
			aFloatArray2179[3] = 0.0F;
			glTexGenfv(GL_R, GL_OBJECT_PLANE, aFloatArray2179, 0);
		} else {
			GLState.bind_texture(WaterTextures.waterlakes_round_textures[0].handle);
		}
	}

	@Override
	public int get_render_settings() {
		return 15;
	}

}
