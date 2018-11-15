package com.jagex.graphics.runetek4.opengl.effects;

import static jaggl.GLConstants.*;
import static jaggl.OpenGL.*;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture1D;
import com.jagex.graphics.runetek4.opengl.water.WaterTextures;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class WaterDepthEffect implements MaterialEffect {

	public static float[] fog_colour = new float[] { 0.073F, 0.169F, 0.24F, 1.0F };
	private static float[] environment_colour = new float[4];
	private static boolean detailed = false;
	private static int depth_scale = 128;

	private final float[] uvbuffer = new float[4];
	private OpenGLTexture1D texture;
	private int drawlist = -1;

	public WaterDepthEffect() {
		if (GLManager.max_texture_units >= 2) {
			int[] var1 = new int[1];
			byte[] var2 = new byte[8];
			int var3 = 0;
			while (var3 < 8) {
				var2[var3++] = (byte) (96 + var3 * 159 / 8);
			}
			glGenTextures(1, var1, 0);
			glBindTexture(GL_TEXTURE_1D, var1[0]);
			texture = new OpenGLTexture1D(PixelFormat.ALPHA, DataType.INT8, 8, var2, PixelFormat.ALPHA);
			texture.set_linear_filter(true);
			texture.set_repeat(false);
			detailed = GLManager.max_texture_units > 2 && GLManager.texture_3d_supported;
			bake();
		}

	}

	private final void bake() {
		drawlist = glGenLists(2);
		glNewList(drawlist, GL_COMPILE);
		{
			glActiveTexture(GL_TEXTURE1);
			if (detailed) {
				glBindTexture(GL_TEXTURE_3D, WaterTextures.waterlakes_texture.handle);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_ADD);
				glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR);
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_REPLACE);
				glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_PREVIOUS);
				glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
				glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
				glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
				glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
				glTexGenfv(GL_Q, GL_OBJECT_PLANE, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
				glEnable(GL_TEXTURE_GEN_S);
				glEnable(GL_TEXTURE_GEN_T);
				glEnable(GL_TEXTURE_GEN_R);
				glEnable(GL_TEXTURE_GEN_Q);
				glEnable(GL_TEXTURE_3D);
				glActiveTexture(GL_TEXTURE2);
				glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_COMBINE);
			}
			glBindTexture(GL_TEXTURE_1D, texture.handle);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_INTERPOLATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_CONSTANT);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE2_RGB, GL_TEXTURE);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_REPLACE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_PREVIOUS);
			glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
			glEnable(GL_TEXTURE_1D);
			glEnable(GL_TEXTURE_GEN_S);
			glActiveTexture(GL_TEXTURE0);
		}
		glEndList();
		glNewList(drawlist + 1, GL_COMPILE);
		glActiveTexture(GL_TEXTURE1);
		if (detailed) {
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR);
			glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
			glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
			glDisable(GL_TEXTURE_GEN_S);
			glDisable(GL_TEXTURE_GEN_T);
			glDisable(GL_TEXTURE_GEN_R);
			glDisable(GL_TEXTURE_GEN_Q);
			glDisable(GL_TEXTURE_3D);
			glActiveTexture(GL_TEXTURE2);
			glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		}

		glTexEnvfv(GL_TEXTURE_ENV, GL_TEXTURE_ENV_COLOR, new float[] { 0.0F, 1.0F, 0.0F, 1.0F }, 0);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
		glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
		glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE2_RGB, GL_CONSTANT);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
		glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE);
		glDisable(GL_TEXTURE_1D);
		glDisable(GL_TEXTURE_GEN_S);
		glActiveTexture(GL_TEXTURE0);
		glEndList();
	}

	@Override
	public final void enable() {
		glCallList(drawlist);
	}

	@Override
	public final void disable() {
		glCallList(drawlist + 1);
	}

	@Override
	public final void update_param(int var1) {
		glActiveTexture(GL_TEXTURE1);
		if (!detailed && var1 < 0) {
			glDisable(GL_TEXTURE_GEN_S);
		} else {
			glPushMatrix();
			glLoadIdentity();
			glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(WaterEffect.water_xangle * 360.0F / 2048.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(WaterEffect.water_yangle * 360.0F / 2048.0F, 0.0F, 1.0F, 0.0F);
			glTranslatef(-WaterEffect.water_xoffset, -WaterEffect.water_yoffset, -WaterEffect.water_zoffset);
			if (detailed) {
				uvbuffer[0] = 0.0010F;
				uvbuffer[1] = 9.0E-4F;
				uvbuffer[2] = 0.0F;
				uvbuffer[3] = 0.0F;
				glTexGenfv(GL_S, 9474, uvbuffer, 0);
				uvbuffer[0] = 0.0F;
				uvbuffer[1] = 9.0E-4F;
				uvbuffer[2] = 0.0010F;
				uvbuffer[3] = 0.0F;
				glTexGenfv(GL_T, 9474, uvbuffer, 0);
				uvbuffer[0] = 0.0F;
				uvbuffer[1] = 0.0F;
				uvbuffer[2] = 0.0F;
				uvbuffer[3] = OpenGLEffects.animation_offset * 0.0050F;
				glTexGenfv(8194, 9474, uvbuffer, 0);
				glActiveTexture(GL_TEXTURE2);
			}

			glTexEnvfv(GL_TEXTURE_ENV, GL_TEXTURE_ENV_COLOR, WaterDepthEffect.calculate_water_environment_colour(), 0);
			if (var1 >= 0) {
				uvbuffer[0] = 0.0F;
				uvbuffer[1] = 1.0F / depth_scale;
				uvbuffer[2] = 0.0F;
				uvbuffer[3] = 1.0F * var1 / depth_scale;
				glTexGenfv(GL_S, GL_EYE_PLANE, uvbuffer, 0);
				glEnable(GL_TEXTURE_GEN_S);
			} else {
				glDisable(GL_TEXTURE_GEN_S);
			}

			glPopMatrix();
		}
		glActiveTexture(GL_TEXTURE0);
	}

	@Override
	public final int get_render_settings() {
		return 0;
	}

	public static float[] calculate_water_environment_colour() {
		float direction = OpenGLEnvironment.get_sun_intensity() + OpenGLEnvironment.get_sun_angle_x();
		int colour = OpenGLEnvironment.get_sun_colour();
		float var6 = 0.58823526F;
		float var3 = (colour >> 16 & 0xff) / 255.0F;
		float var4 = (colour >> 8 & 0xff) / 255.0F;
		float var5 = (colour & 0xff) / 255.0F;
		environment_colour[3] = 1.0F;
		environment_colour[2] = fog_colour[2] * var5 * var6 * direction;
		environment_colour[0] = fog_colour[0] * var3 * var6 * direction;
		environment_colour[1] = direction * var6 * var4 * fog_colour[1];
		return environment_colour;
	}

	public static float[] update_environment_colour_underwater(int var0) {
		float var2 = OpenGLEnvironment.get_sun_intensity() + OpenGLEnvironment.get_sun_angle_x();
		int var3 = OpenGLEnvironment.get_sun_colour();
		float var7 = 0.58823526F;
		environment_colour[3] = 1.0F;
		float var4 = (var3 >> 16 & 255) / 255.0F;
		float var5 = (var3 >> 8 & 0xff) / 255.0F;
		environment_colour[1] = var2 * (var0 >> 8 & 255) / 255.0F * var5 * var7;
		environment_colour[0] = var2 * var7 * var4 * (((var0 & 16754958) >> 16) / 255.0F);
		float var6 = (var3 & 255) / 255.0F;
		environment_colour[2] = (255 & var0) / 255.0F * var6 * var7 * var2;
		return environment_colour;
	}

	public static void set_water_fog_colour(int colour) {
		fog_colour[0] = (colour >> 16 & 0xff) / 255.0F;
		fog_colour[1] = (colour >> 8 & 0xff) / 255.0F;
		fog_colour[2] = (colour & 0xff) / 255.0F;
		OpenGLEffects.update_effect(3);
		OpenGLEffects.update_effect(4);
	}

	public static void set_water_depthscale(int depth_scale) {
		WaterDepthEffect.depth_scale = depth_scale;
		OpenGLEffects.update_effect(3);
		OpenGLEffects.update_effect(4);
	}

	public static void bind() {
		glClientActiveTexture(get_texture_id());
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glClientActiveTexture(GL_TEXTURE0);
	}

	public static void unbind() {
		glClientActiveTexture(get_texture_id());
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glClientActiveTexture(GL_TEXTURE0);
	}

	public static final int get_texture_id() {
		return detailed ? GL_TEXTURE2 : GL_TEXTURE1;
	}

}
