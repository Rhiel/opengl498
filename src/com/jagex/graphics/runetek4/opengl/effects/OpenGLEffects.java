package com.jagex.graphics.runetek4.opengl.effects;

import static jaggl.GLConstants.GL_TEXTURE_2D;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture2D;
import com.jagex.graphics.runetek4.opengl.water.WaterTextures;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLEffects {

	public static MaterialEffect[] effects;
	public static boolean disable_effects = false;
	public static int current_effect;
	public static int current_param;
	public static int animation_offset;

	public static void initialise() {
		OpenGLEffects.lights_texture = new OpenGLTexture2D(GL_TEXTURE_2D, PixelFormat.RGBA, DataType.INT8, 1, 1);
		initialise_effects();
	}

	public static void initialise_effects() {
		WaterTextures.initialise();
		effects = new MaterialEffect[7];
		effects[1] = new SkyboxEffect();
		effects[2] = new WaterEffect();
		effects[3] = new WaterDepthEffect();
		effects[4] = new WaterHDEffect();
		effects[5] = new MaterialShader5();
		effects[6] = new BumpEffect();
	}

	public static void setup_effect(int effect_id, int effect_param) {
		if (effects == null || effect_id >= effects.length || effects[effect_id] == null) {
			effect_id = 0;
		}
		if (effect_id < 0) {
			effect_id = 0;
		}
		if (effect_id == 4 && !ClientOptions.clientoption_highdetails_water) {
			effect_id = 2;
			effect_param = 2;
		}
		if (OpenGLEffects.current_effect == effect_id) {
			if (0 != effect_id && effect_param != OpenGLEffects.current_param) {
				OpenGLEffects.effects[effect_id].update_param(effect_param);
				OpenGLEffects.current_param = effect_param;
			}
		} else {
			if (disable_effects) {
				return;
			}
			if (OpenGLEffects.current_effect != 0) {
				OpenGLEffects.effects[OpenGLEffects.current_effect].disable();
			}
			if (-1 != ~effect_id) {
				MaterialEffect effect = OpenGLEffects.effects[effect_id];
				effect.enable();
				effect.update_param(effect_param);
			}
			OpenGLEffects.current_effect = effect_id;
			OpenGLEffects.current_param = effect_param;
		}
	}

	public static void update_effect(int effectid) {
		if (effectid == current_effect && effectid != 0) {
			MaterialEffect effect = effects[effectid];
			effect.update_param(current_param);
		}
	}

	public static void reset_state() {
		setup_effect(0, 0);
	}

	public static void destroy() {
		effects = null;
		WaterTextures.method1455();
	}

	public static int get_current_render_settings() {
		if (current_effect == 0) {
			return 0;
		}
		return effects[current_effect].get_render_settings();
	}

	public static OpenGLTexture2D lights_texture;

}
