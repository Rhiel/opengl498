package com.jagex.graphics.runetek4.opengl;

import static jaggl.GLConstants.*;
import static jaggl.OpenGL.*;

import com.jagex.GraphicTools;
import com.jagex.Viewport;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.effects.OpenGLEffects;
import com.jagex.graphics.runetek4.opengl.environment.OpenGLEnvironment;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture2D;
import com.rs2.client.scene.light.SceneLighting;

import me.waliedyassen.materials.MaterialRaw;

/**
 * @author Walied K. Yassen
 */
public class GLState {

	/* buffers chunk */
	public static final float[] matrix_buffer = new float[16];
	/* constants */
	public static final float GRAPHIC_UNIT_RATIO = 0.09765625F;
	/* default state variables, all states must match the initialise() function */
	public static boolean lights_enabled = true;
	public static boolean depthtest_enabled = true;
	public static boolean fog_enabled = true;
	public static boolean normals_enabled = true;
	public static boolean alpha_test_enabled = true;
	public static boolean fb_writing_enabled = true;
	public static boolean blend_enabled = true;
	public static int blend_mode;
	public static int rgb_blend_func;
	public static int alpha_test_min;
	public static int alpha_blend_func;
	/* textures chunk */
	public static int combine_rgb_mode = 0;
	public static int combine_alpha_mode = 0;
	public static boolean texture_matrix_dirty;
	/* viewport chunk */
	public static int viewport_width;
	public static int viewport_height;
	public static boolean projection_updated;
	/* clipping chunk */
	public static int clip_left = 0;
	public static int clip_top = 0;
	public static int clip_right = 0;
	public static int clip_bottom = 0;
	public static OpenGLSprite clip_mask;
	/* depth range */
	public static float depth_range_start;
	public static float depth_range_end;
	public static float depth_range_offset = 0.0F;
	public static float depth_range_scale = 0.0F;
	public static int light_texture;
	// texture management
	public static int textureid;

	public static void initialise() {
		projection_updated = false;
		combine_rgb_mode = 0;
		combine_alpha_mode = 0;
		glDisable(GL_TEXTURE_2D);
		glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_COMBINE);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
		glEnable(GL_LIGHTING);
		glEnable(GL_FOG);
		glEnable(GL_DEPTH_TEST);
		lights_enabled = true;
		depthtest_enabled = true;
		fog_enabled = true;
		normals_enabled = true;
		OpenGLEffects.reset_state();
		glActiveTexture(GL_TEXTURE1);
		glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_COMBINE);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
		glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
		glActiveTexture(GL_TEXTURE0);
		set_swapinterval(0);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glShadeModel(GL_SMOOTH);
		glClearDepth(1f);
		glDepthFunc(GL_LEQUAL);
		enable_depthmask();
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		glPolygonMode(GL_FRONT, GL_FILL);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_BLEND);
		if (GLManager.blend_func_separate_supported) {
			glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 0, 0);
		} else {
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}
		glEnable(3008);
		glAlphaFunc(516, 0.0F);
		// rgb_blend_func = 1;
		// set_alpha_test_min((byte) 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		OpenGLEnvironment.reset_state();
		SceneLighting.reset_state();
	}

	public static void load_texture(int textureId) {
		OpenGLTexture2D texture = null;
		byte effect_param1 = 0;
		byte effect_type = (byte) 0;
		byte combiners = 0;
		if (textureId >= 0) {
			MaterialRaw material = GraphicTools.get_materials().get_material(textureId);
			texture = GLManager.textures.getTexture(material);
			if (texture != null) {
				if (material.speed_u != 0 || material.speed_v != 0) {
					translate_texture_matrix(OpenGLEffects.animation_offset * material.speed_u / 64.0F % 1.0F, OpenGLEffects.animation_offset * material.speed_v / 64.0F % 1.0F, 0.0F);
				} else {
					reset_texture_matrix();
				}
				effect_type = material.effect_type;
				effect_param1 = material.effect_param1;
				combiners = material.effect_combiner;
			} else {
				reset_texture_matrix();

			}
		}
		OpenGLEffects.setup_effect(effect_type, effect_param1);
		int settings = OpenGLEffects.get_current_render_settings();
		if ((settings & 0x1) == 0) {
			bind_texture(texture == null ? -1 : texture.handle);
		}
		if ((settings & 0x2) == 0) {
			if (combiners == 1) {
				set_combine_rgb_mode(GL_REPLACE);
			} else if (combiners == 0) {
				set_combine_rgb_mode(GL_MODULATE);
			} else if (combiners == 2) {
				set_combine_rgb_mode(GL_INTERPOLATE);
			} else if (combiners == 3) {
				set_combine_rgb_mode(GL_ADD);
			} else if (combiners == 4) {
				set_combine_rgb_mode(GL_SUBTRACT);
			}
		}
		if ((settings & 0x4) == 0) {
			if (combiners == 1) {
				set_alpha_combine_mode(GL_REPLACE);
			} else if (combiners == 0) {
				set_alpha_combine_mode(GL_MODULATE);
			} else if (combiners == 2) {
				set_alpha_combine_mode(GL_REPLACE);
			} else if (combiners == 3) {
				set_alpha_combine_mode(GL_MODULATE);
			} else if (combiners == 4) {
				set_alpha_combine_mode(GL_SUBTRACT);
			}
		}
	}

	public static void bind_texture(int textureid) {
		if (GLState.textureid != textureid) {
			if (textureid == -1) {
				glDisable(GL_TEXTURE_2D);
			} else {
				glEnable(GL_TEXTURE_2D);
				glBindTexture(GL_TEXTURE_2D, textureid);
			}
			GLState.textureid = textureid;
		}
	}

	public static void set_combine_rgb_mode(int mode) {
		if (combine_rgb_mode != mode) {
			if (mode == 0) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
			} else if (mode == 1) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE);
			} else if (mode == 2) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_ADD);
			} else if (mode == 3) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_SUBTRACT);
			} else if (mode == 4) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_ADD_SIGNED);
			} else if (mode == 5) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_INTERPOLATE);
			} else {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, mode);
			}
			combine_rgb_mode = mode;
		}
	}

	public static void set_alpha_combine_mode(int mode) {
		if (mode != combine_alpha_mode) {
			if (mode == 0) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE);
			} else if (mode == 1) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_REPLACE);
			} else if (mode == 2) {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_ADD);
			} else {
				glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA, mode);

			}
			combine_alpha_mode = mode;
		}
	}

	public static void set_source_rgb_combiner(int index, int source, int operand) {
		glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB + index, source);
		glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB + index, operand);
	}

	public static void set_source_alpha_combiner(int index, int source, int operand) {
		glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA + index, source);
		glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_ALPHA + index, operand);
	}

	public static void set_blending_mode(int mode) {
		if (blend_mode != mode) {
			int _rgb_blend_func;
			boolean _fb_writing;
			boolean _alpha_test;
			boolean _blend_enabled;
			if (mode == 1) {
				_rgb_blend_func = 1;
				_fb_writing = true;
				_alpha_test = true;
				_blend_enabled = true;
			} else if (mode == 2) {
				_rgb_blend_func = 2;
				_fb_writing = true;
				_alpha_test = false;
				_blend_enabled = true;
			} else if (mode == 128) {
				_rgb_blend_func = 3;
				_fb_writing = true;
				_alpha_test = true;
				_blend_enabled = true;
			} else if (mode == 3) {
				_rgb_blend_func = 0;
				_fb_writing = true;
				_alpha_test = true;
				_blend_enabled = false;
			} else {
				_rgb_blend_func = 0;
				_fb_writing = true;
				_alpha_test = false;
				_blend_enabled = false;
			}
			if (_fb_writing != fb_writing_enabled) {
				glColorMask(_fb_writing, _fb_writing, _fb_writing, true);
				fb_writing_enabled = _fb_writing;
			}
			if (_alpha_test != alpha_test_enabled) {
				alpha_test_enabled = _alpha_test;
				load_alpha_test();
			}
			if (_blend_enabled != blend_enabled) {
				blend_enabled = _blend_enabled;
				load_blend();
			}
			if (_rgb_blend_func != rgb_blend_func) {
				rgb_blend_func = _rgb_blend_func;
				load_blend_func();
			}
			blend_mode = mode;
		}
	}

	public static void load_blend() {
		if (blend_enabled) {
			glEnable(GL_BLEND);
		} else {
			glDisable(GL_BLEND);
		}
	}

	public static void load_blend_func() {
		if (GLManager.blend_func_separate_supported) {
			int alphaSrc = 0;
			int alphaDst = 0;
			if (alpha_blend_func == 0) {
				alphaSrc = 0;
				alphaDst = 0;
			} else if (alpha_blend_func == 1) {
				alphaSrc = 1;
				alphaDst = 0;
			} else if (alpha_blend_func == 2) {
				alphaSrc = 1;
				alphaDst = 1;
			} else if (alpha_blend_func == 3) {
				alphaSrc = 0;
				alphaDst = 1;
			}
			if (rgb_blend_func == 1) {
				glBlendFuncSeparate(770, 771, alphaSrc, alphaDst);
			} else if (rgb_blend_func == 2) {
				glBlendFuncSeparate(1, 1, alphaSrc, alphaDst);
			} else if (rgb_blend_func == 3) {
				glBlendFuncSeparate(774, 1, alphaSrc, alphaDst);
			} else if (rgb_blend_func == 0) {
				glBlendFuncSeparate(1, 0, alphaSrc, alphaDst);
			}
		} else if (rgb_blend_func == 1) {
			glEnable(3042);
			glBlendFunc(770, 771);
		} else if (rgb_blend_func == 2) {
			glEnable(3042);
			glBlendFunc(1, 1);
		} else if (rgb_blend_func == 3) {
			glEnable(3042);
			glBlendFunc(774, 1);
		} else {
			glDisable(3042);
		}
	}

	public static void setup_2d_opaque_sprites_state() {
		OpenGLEffects.setup_effect(0, 0);
		setup_ortho_projection();
		set_combine_rgb_mode(1);
		set_alpha_combine_mode(1);
		set_lights_enabled(false);
		set_depthtest_enabled(false);
		set_fog_enabled(false);
		reset_texture_matrix();
	}

	public static void setup_2d_translucent_sprites_state() {
		OpenGLEffects.setup_effect(0, 0);
		setup_ortho_projection();
		set_combine_rgb_mode(0);
		set_alpha_combine_mode(0);
		set_lights_enabled(false);
		set_depthtest_enabled(false);
		set_fog_enabled(false);
		reset_texture_matrix();
	}

	public static void setup_2d_geometry_state() {
		OpenGLEffects.setup_effect(0, 0);
		setup_ortho_projection();
		bind_texture(-1);
		set_lights_enabled(false);
		set_depthtest_enabled(false);
		set_fog_enabled(false);
		reset_texture_matrix();
	}

	public static void setup_2d_font_state() {
		OpenGLEffects.setup_effect(0, 0);
		setup_ortho_projection();
		set_combine_rgb_mode(0);
		set_alpha_combine_mode(0);
		set_lights_enabled(false);
		set_depthtest_enabled(false);
		set_fog_enabled(false);
		reset_texture_matrix();
	}

	public static void clear_screen() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public static void clear_screen(int colour) {
		glClearColor((colour >> 16 & 255) / 255.0F, (colour >> 8 & 255) / 255.0F, (colour & 255) / 255.0F, 0.0F);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
	}

	public static void clear_depth() {
		glClear(GL_DEPTH_BUFFER_BIT);
	}

	public static void translate_texture_matrix(float xoff, float yoff, float zoff) {
		glMatrixMode(GL_TEXTURE);
		if (texture_matrix_dirty) {
			glLoadIdentity();
		}
		glTranslatef(xoff, yoff, zoff);
		glMatrixMode(GL_MODELVIEW);
		texture_matrix_dirty = true;
	}

	public static void reset_texture_matrix() {
		if (texture_matrix_dirty) {
			glMatrixMode(GL_TEXTURE);
			glLoadIdentity();
			glMatrixMode(GL_MODELVIEW);
			texture_matrix_dirty = false;
		}
	}

	public static void update_viewport(int var0, int var1) {
		viewport_width = var0;
		viewport_height = var1;
		projection_updated = false;
	}

	public static void setup_ortho_projection() {
		if (!projection_updated) {
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0.0D, viewport_width, 0.0D, viewport_height, -1.0D, 1.0D);
			glViewport(0, 0, viewport_width, viewport_height);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			projection_updated = true;
		}
	}

	public static void setup_ortho_projection(int left, int top, int scale, int far, int right, int bottom) {
		int gl_left = -left;
		int gl_right = viewport_width - left;
		int gl_top = -top;
		int gl_bottom = viewport_height - top;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		float scale_factor = scale / 512.0F;
		float width_scale = scale_factor * (256.0F / right);
		float height_scale = scale_factor * (256.0F / bottom);
		glOrtho(gl_left * width_scale, gl_right * width_scale, -gl_bottom * height_scale, -gl_top * height_scale, 50 - far, 3584 - far);
		glViewport(0, 0, viewport_width, viewport_height);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		projection_updated = false;
	}

	public static void setup_perspective_viewport(int x, int y, int width, int height, int center_x, int center_y, float anglex, float angley, int fovx, int fovy) {
		int left = (x - center_x << 8) / fovx;
		int right = (x + width - center_x << 8) / fovx;
		int top = (y - center_y << 8) / fovy;
		int bottom = (y + height - center_y << 8) / fovy;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		setup_perspective_projection(left * GRAPHIC_UNIT_RATIO, right * GRAPHIC_UNIT_RATIO, -bottom * GRAPHIC_UNIT_RATIO, -top * GRAPHIC_UNIT_RATIO, 50.0F, 3584F);
		glViewport(x, viewport_height - y - height, width, height);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		if (anglex != 0.0F) {
			glRotatef(anglex, 1.0F, 0.0F, 0.0F);
		}
		if (angley != 0.0F) {
			glRotatef(angley, 0.0F, 1.0F, 0.0F);
		}
		projection_updated = false;
		Viewport.screen_left = left;
		Viewport.screen_right = right;
		Viewport.screen_top = top;
		Viewport.screen_bottom = bottom;
	}

	public static final void setup_perspective_viewport(int var0, int var1, int var2, int var3) {
		setup_perspective_viewport(0, 0, viewport_width, viewport_height, var0, var1, 0.0F, 0.0F, var2, var3);
	}

	public static void setup_perspective_projection(float left, float right, float bot, float top, float near, float far) {
		matrix_buffer[0] = near * 2.0f / (right - left);
		matrix_buffer[1] = 0.0F;
		matrix_buffer[2] = 0.0F;
		matrix_buffer[3] = 0.0F;
		matrix_buffer[4] = 0.0F;
		matrix_buffer[5] = near * 2.0f / (top - bot);
		matrix_buffer[6] = 0.0F;
		matrix_buffer[7] = 0.0F;
		matrix_buffer[8] = (right + left) / (right - left);
		matrix_buffer[9] = (top + bot) / (top - bot);
		matrix_buffer[10] = depth_range_start = -(far + near) / (far - near);
		matrix_buffer[11] = -1.0F;
		matrix_buffer[12] = 0.0F;
		matrix_buffer[13] = 0.0F;
		matrix_buffer[14] = depth_range_end = -(near * 2.0f * far) / (far - near);
		matrix_buffer[15] = 0.0F;
		glLoadMatrixf(matrix_buffer, 0);
		depth_range_offset = 0.0F;
		depth_range_scale = 0.0F;
	}

	public static void enable_depthmask() {
		glDepthMask(true);
	}

	public static void disable_depthmask() {
		glDepthMask(false);
	}

	public static void load_alpha_test() {
		if (alpha_test_enabled) {
			glEnable(GL_ALPHA_TEST);
		} else {
			glDisable(GL_ALPHA_TEST);
		}
		glAlphaFunc(GL_GREATER, (alpha_test_min & 0xff) / 255.0F);
		if (ClientOptions.clientoption_antialiasing > 0) {
			if (alpha_test_min == 0) {
				glDisable(GL_SAMPLE_ALPHA_TO_COVERAGE);
			} else {
				glEnable(GL_SAMPLE_ALPHA_TO_COVERAGE);
			}
		}
	}

	public static void set_swapinterval(int interval) {
		GLManager.gl.setSwapInterval(interval);
	}

	public static void load_lighting_state() {
		if (ClientOptions.clientoption_highdetails_lighting) {
			set_lights_enabled(true);
			set_normals_enabled(true);
		} else {
			set_lights_enabled(false);
			set_normals_enabled(false);
		}

	}

	public static void set_normals_enabled(boolean enabled) {
		if (normals_enabled != enabled) {
			if (enabled) {
				glEnableClientState(GL_NORMAL_ARRAY);
			} else {
				glDisableClientState(GL_NORMAL_ARRAY);
			}
			normals_enabled = enabled;
		}
	}

	public static void set_lights_enabled(boolean enabled) {
		if (lights_enabled != enabled) {
			if (enabled) {
				glEnable(GL_LIGHTING);
			} else {
				glDisable(GL_LIGHTING);
			}
			lights_enabled = enabled;
		}
	}

	public static void set_depthtest_enabled(boolean enabled) {
		if (depthtest_enabled != enabled) {
			if (enabled) {
				glEnable(GL_DEPTH_TEST);
			} else {
				glDisable(GL_DEPTH_TEST);
			}
			depthtest_enabled = enabled;
		}
	}

	public static void set_fog_enabled(boolean enabled) {
		if (fog_enabled != enabled) {
			if (enabled) {
				glEnable(GL_FOG);
			} else {
				glDisable(GL_FOG);
			}
			fog_enabled = enabled;
		}
	}

	public static void set_clipping(int left, int top, int right, int bottom) {
		if (left < 0) {
			left = 0;
		}
		if (top < 0) {
			top = 0;
		}
		if (right > viewport_width) {
			right = viewport_width;
		}
		if (bottom > viewport_height) {
			bottom = viewport_height;
		}
		clip_left = left;
		clip_top = top;
		clip_right = right;
		clip_bottom = bottom;
		glEnable(GL_SCISSOR_TEST);
		if (clip_left <= clip_right && clip_top <= clip_bottom) {
			glScissor(clip_left, viewport_height - clip_bottom, clip_right - clip_left, clip_bottom - clip_top);
		} else {
			glScissor(0, 0, 0, 0);
		}
		reset_clipmask();
	}

	public static void accumlate_clipping(int left, int top, int right, int bottom) {
		if (clip_left < left) {
			clip_left = left;
		}

		if (clip_top < top) {
			clip_top = top;
		}

		if (clip_right > right) {
			clip_right = right;
		}

		if (clip_bottom > bottom) {
			clip_bottom = bottom;
		}

		glEnable(GL_SCISSOR_TEST);
		if (clip_left <= clip_right && clip_top <= clip_bottom) {
			glScissor(clip_left, viewport_height - clip_bottom, clip_right - clip_left, clip_bottom - clip_top);
		} else {
			glScissor(0, 0, 0, 0);
		}
		reset_clipmask();
	}

	public static void reset_clipping() {
		clip_left = 0;
		clip_top = 0;
		clip_right = viewport_width;
		clip_bottom = viewport_height;
		glDisable(GL_SCISSOR_TEST);
		reset_clipmask();
	}

	public static void set_clippingmask(OpenGLSprite clipping_mask) {
		if (clipping_mask.height != clip_bottom - clip_top) {
			throw new IllegalArgumentException();
		} else {
			clip_mask = clipping_mask;
		}
	}

	public static void reset_clipmask() {
		clip_mask = null;
	}

	public static void update_depth_range(float scale) {
		update_depth_range(3000.0F, scale * 1.5F);
	}

	public static void update_depth_range(float depth_offset, float depth_scale) {
		if (!projection_updated) {
			if (depth_offset != depth_range_offset || depth_scale != depth_range_scale) {
				depth_range_offset = depth_offset;
				depth_range_scale = depth_scale;
				if (depth_scale != 0.0F) {
					float scaled_offset = depth_offset / (depth_scale + depth_offset);
					float scalar = scaled_offset * scaled_offset;
					float offset = -depth_range_end * (1.0F - scaled_offset) * (1.0F - scaled_offset) / depth_scale;
					matrix_buffer[10] = depth_range_start + offset;
					matrix_buffer[14] = depth_range_end * scalar;
				} else {
					matrix_buffer[10] = depth_range_start;
					matrix_buffer[14] = depth_range_end;
				}

				glMatrixMode(GL_PROJECTION);
				glLoadMatrixf(matrix_buffer, 0);
				glMatrixMode(GL_MODELVIEW);
			}
		}
	}

	public static float get_depth_range_offset() {
		return depth_range_offset;
	}

	public static float get_depth_range_scale() {
		return depth_range_scale;
	}
}
