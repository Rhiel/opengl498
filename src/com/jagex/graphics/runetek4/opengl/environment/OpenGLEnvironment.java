package com.jagex.graphics.runetek4.opengl.environment;

import static jaggl.GLConstants.GL_AMBIENT;
import static jaggl.GLConstants.GL_AMBIENT_AND_DIFFUSE;
import static jaggl.GLConstants.GL_COLOR_MATERIAL;
import static jaggl.GLConstants.GL_DIFFUSE;
import static jaggl.GLConstants.GL_FOG_COLOR;
import static jaggl.GLConstants.GL_FOG_DENSITY;
import static jaggl.GLConstants.GL_FOG_END;
import static jaggl.GLConstants.GL_FOG_HINT;
import static jaggl.GLConstants.GL_FOG_MODE;
import static jaggl.GLConstants.GL_FOG_START;
import static jaggl.GLConstants.GL_FRONT;
import static jaggl.GLConstants.GL_LIGHT0;
import static jaggl.GLConstants.GL_LIGHT1;
import static jaggl.GLConstants.GL_LIGHT_MODEL_AMBIENT;
import static jaggl.GLConstants.GL_LINEAR;
import static jaggl.GLConstants.GL_NICEST;
import static jaggl.GLConstants.GL_POSITION;
import static jaggl.OpenGL.glColorMaterial;
import static jaggl.OpenGL.glEnable;
import static jaggl.OpenGL.glFogf;
import static jaggl.OpenGL.glFogfv;
import static jaggl.OpenGL.glFogi;
import static jaggl.OpenGL.glHint;
import static jaggl.OpenGL.glLightModelfv;
import static jaggl.OpenGL.glLightfv;

import com.rs2.client.scene.SceneDefaults;

/**
 * @author Walied K. Yassen
 */
public final class OpenGLEnvironment {

	public static final float[] sun_position_buffer = new float[4];
	public static final float[] sun_normals_buffer = new float[4];
	public static final float[] fog_colour_buffer = new float[4];

	public static int fog_density;
	public static int fog_colour;
	public static int shadows_scale_x;
	public static int shadows_scale_z;
	public static int sun_colour;
	public static float sun_angle_x;
	public static float sun_angle_y;
	public static float sun_intensity;

	static {
		fog_density = -1;
		fog_colour = -1;
		sun_colour = -1;
		sun_angle_x = -1.0F;
		sun_angle_y = -1.0F;
	}

	public static void update_sun_position(float sun_x, float sun_y, float sun_z) {
		if (sun_position_buffer[0] != sun_x || sun_position_buffer[1] != sun_y || sun_position_buffer[2] != sun_z) {
			sun_position_buffer[0] = sun_x;
			sun_position_buffer[1] = sun_y;
			sun_position_buffer[2] = sun_z;
			sun_normals_buffer[0] = -sun_x;
			sun_normals_buffer[1] = -sun_y;
			sun_normals_buffer[2] = -sun_z;
			shadows_scale_x = (int) (sun_x * 256.0F / sun_y);
			shadows_scale_z = (int) (sun_z * 256.0F / sun_y);
		}
	}

	public static void upload_sun_position() {
		glLightfv(GL_LIGHT0, GL_POSITION, sun_position_buffer, 0);
		glLightfv(GL_LIGHT1, GL_POSITION, sun_normals_buffer, 0);
	}

	public static void update_suncolour(int sun_colour, float sun_intensity, float sun_angle_x, float sun_angle_y) {
		if (OpenGLEnvironment.sun_colour != sun_colour || OpenGLEnvironment.sun_intensity != sun_intensity || OpenGLEnvironment.sun_angle_x != sun_angle_x || OpenGLEnvironment.sun_angle_y != sun_angle_y) {
			OpenGLEnvironment.sun_colour = sun_colour;
			OpenGLEnvironment.sun_intensity = sun_intensity;
			OpenGLEnvironment.sun_angle_x = sun_angle_x;
			OpenGLEnvironment.sun_angle_y = sun_angle_y;
			float red = (sun_colour >> 16 & 255) / 255.0F;
			float green = (sun_colour >> 8 & 255) / 255.0F;
			float blue = (sun_colour & 255) / 255.0F;
			float[] ambient_colour = new float[] { sun_intensity * red, sun_intensity * green, sun_intensity * blue, 1.0F };
			glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambient_colour, 0);
			float[] diffuse_colour = new float[] { sun_angle_x * red, sun_angle_x * green, sun_angle_x * blue, 1.0F };
			glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse_colour, 0);
			float[] var10 = new float[] { -sun_angle_y * red, -sun_angle_y * green, -sun_angle_y * blue, 1.0F };
			glLightfv(GL_LIGHT1, GL_DIFFUSE, var10, 0);
		}
	}

	public static void update_fog(int colour, int density) {
		final int visible_distance = 3584;
		if (fog_colour != colour || fog_density != density) {
			fog_colour = colour;
			fog_density = density;
			fog_colour_buffer[0] = (colour >> 16 & 255) / 255.0F;
			fog_colour_buffer[1] = (colour >> 8 & 255) / 255.0F;
			fog_colour_buffer[2] = (colour & 255) / 255.0F;
			glFogi(GL_FOG_MODE, GL_LINEAR);
			glFogf(GL_FOG_DENSITY, 0.95F);
			glHint(GL_FOG_HINT, GL_NICEST);
			int start = visible_distance - 512 - density;
			if (start < 50) {
				start = 50;
			}
			glFogf(GL_FOG_START, start);
			glFogf(GL_FOG_END, visible_distance - 256);
			glFogfv(GL_FOG_COLOR, fog_colour_buffer, 0);
		}
	}

	public static void upload_fog_colour(float[] colour) {
		if (colour == null) {
			colour = fog_colour_buffer;
		}
		glFogfv(GL_FOG_COLOR, colour, 0);
	}

	public static void reset_state() {
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
		glEnable(GL_COLOR_MATERIAL);
		glLightfv(GL_LIGHT0, GL_AMBIENT, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
		glEnable(GL_LIGHT0);
		glLightfv(GL_LIGHT1, GL_AMBIENT, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
		glEnable(GL_LIGHT1);
		sun_colour = -1;
		fog_colour = -1;
		reset_sun();
	}

	public static final void reset_sun() {
		update_suncolour(SceneDefaults.default_sun_colour, SceneDefaults.default_sun_intensity, SceneDefaults.default_sun_angle_x, SceneDefaults.default_sun_angle_y);
		update_sun_position(SceneDefaults.default_sun_position_x, SceneDefaults.default_sun_position_y, SceneDefaults.default_sun_position_z);
		update_fog(SceneDefaults.default_fog_colour, SceneDefaults.default_fog_density);
	}

	public static int get_sun_colour() {
		return sun_colour;
	}

	public static float get_sun_angle_x() {
		return sun_angle_x;
	}

	public static float get_sun_intensity() {
		return sun_intensity;
	}

	public OpenGLEnvironment() {
		// NOOP
	}

}
