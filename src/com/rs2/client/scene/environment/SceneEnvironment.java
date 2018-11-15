package com.rs2.client.scene.environment;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.rs2.client.scene.atmosphere.SceneAtmosphere;

/**
 * @author Walied K. Yassen
 */
public final class SceneEnvironment {

	public final static SceneAtmosphere[][] atmospheres = new SceneAtmosphere[13][13];
	public static boolean adjust_brightness = true;
	public static int interpolation_cycle = 0;

	// previous
	public static int previous_sun_colour;
	public static float previous_sun_intensity;
	public static float previous_sun_angle_x;
	public static float previous_sun_angle_y;
	public static int previous_fog_colour;
	public static int previous_fog_density;

	// next
	public static int next_sun_colour;
	public static float next_sun_intensity;
	public static float next_sun_angle_x;
	public static float next_sun_angle_y;
	public static int next_fog_colour;
	public static int next_fog_density;

	// current
	public static int current_sun_colour;
	public static float current_sun_intensity;
	public static float current_sun_angle_x;
	public static float current_sun_angle_y;
	public static int current_sun_position_x;
	public static int current_sun_position_y;
	public static int current_sun_position_z;
	public static int current_fog_density;
	public static int current_fog_colour;

	public static int process_atmosphere(int chunkxx, int chunky, int brightness, int cycle) {
		if (adjust_brightness) {
			brightness = 1000000;
			adjust_brightness = false;
		}
		SceneAtmosphere atmosphere = atmospheres[chunkxx][chunky];
		float _sun_intensity = atmosphere.sun_intensity * (cycle * 0.1F + 0.7F);
		float _angle_x = atmosphere.sun_angle_x;
		float _angle_y = atmosphere.sun_angle_y;
		int _sun_colour = atmosphere.sun_colour;
		int _fog_density = atmosphere.fog_density;
		int _fog_colour = atmosphere.fog_colour;
		if (!ClientOptions.clientoption_enable_fog) {
			_fog_density = 0;
		}
		if (_sun_colour != next_sun_colour || next_sun_intensity != _sun_intensity || next_sun_angle_x != _angle_x || _angle_y != next_sun_angle_y || next_fog_colour != _fog_colour || next_fog_density != _fog_density) {
			next_sun_colour = _sun_colour;
			next_sun_intensity = _sun_intensity;
			next_sun_angle_x = _angle_x;
			next_sun_angle_y = _angle_y;
			next_fog_colour = _fog_colour;
			next_fog_density = _fog_density;
			previous_sun_colour = current_sun_colour;
			previous_sun_intensity = current_sun_intensity;
			previous_sun_angle_x = current_sun_angle_x;
			previous_sun_angle_y = current_sun_angle_y;
			previous_fog_colour = current_fog_colour;
			previous_fog_density = current_fog_density;
			interpolation_cycle = 0;
		}
		if (interpolation_cycle < 65536) {
			interpolation_cycle += 250 * brightness;
			if (interpolation_cycle > 65536) {
				interpolation_cycle = 65536;
			}
			float previous_float_factor = (65536 - interpolation_cycle) / 65536.0F;
			float next_float_factor = interpolation_cycle / 65536.0F;
			int previous_int_factor = 65536 - interpolation_cycle >> 8;
			int next_int_factor = interpolation_cycle >> 8;
			current_sun_intensity = previous_float_factor * previous_sun_intensity + next_float_factor * next_sun_intensity;
			current_sun_angle_x = previous_sun_angle_x * previous_float_factor + next_float_factor * next_sun_angle_x;
			current_sun_angle_y = next_float_factor * next_sun_angle_y + previous_float_factor * previous_sun_angle_y;
			current_sun_colour = (0xff0000 & (next_sun_colour & 0xff00) * next_int_factor + previous_int_factor * (previous_sun_colour & 0xff00)) + (previous_int_factor * (previous_sun_colour & 0xff00ff) + (next_sun_colour & 0xff00ff) * next_int_factor & 0xff00ff00) >> 8;
			current_fog_colour = (0xff00ff00 & next_int_factor * (next_fog_colour & 0xff00ff) + (previous_fog_colour & 0xff00ff) * previous_int_factor) + (0xff0000 & previous_int_factor * (previous_fog_colour & 0xff00) + (next_fog_colour & 0xff00) * next_int_factor) >> 8;
			current_fog_density = previous_int_factor * previous_fog_density + next_int_factor * next_fog_density >> 8;
		}
		OpenGLEnvironment.update_suncolour(current_sun_colour, current_sun_intensity, current_sun_angle_x, current_sun_angle_y);
		OpenGLEnvironment.update_fog(current_fog_colour, current_fog_density);
		OpenGLEnvironment.update_sun_position(current_sun_position_x, current_sun_position_y, current_sun_position_z);
		OpenGLEnvironment.upload_sun_position();
		return current_fog_colour;
	}

	public static void update_sun_position(int chunkx, int chunky) {
		current_sun_position_x = atmospheres[chunkx][chunky].sun_position_x;
		current_sun_position_y = atmospheres[chunkx][chunky].sun_position_y;
		current_sun_position_z = atmospheres[chunkx][chunky].sun_position_z;
		OpenGLEnvironment.update_sun_position(current_sun_position_x, current_sun_position_y, current_sun_position_z);
	}

	public static void update_brightness() {
		adjust_brightness = true;
	}

	public static void reset_atmospheres() {
		SceneAtmosphere atmosphere = new SceneAtmosphere();
		for (int chunkx = 0; chunkx < 13; chunkx++) {
			for (int chunky = 0; chunky < 13; chunky++) {
				atmospheres[chunkx][chunky] = atmosphere;
			}
		}
	}

	public SceneEnvironment() {
		// NOOP
	}

}
