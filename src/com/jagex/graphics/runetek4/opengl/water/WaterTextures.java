package com.jagex.graphics.runetek4.opengl.water;

import static jaggl.GLConstants.GL_TEXTURE_2D;

import com.jagex.ArrayUtils;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture2D;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTexture3D;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public final class WaterTextures {

	public static byte[] waterfalls_data;
	public static byte[] waterlakes_data;
	public static OpenGLTexture3D waterlakes_texture;
	public static OpenGLTexture3D waterfalls_texture;
	public static OpenGLTexture2D[] waterfall_round_textures;
	public static OpenGLTexture2D[] waterlakes_round_textures;
	public static boolean textures_3d;

	public static void initialise() {
		textures_3d = GLManager.texture_3d_supported;
		generate_textures();
		initialise_waterlakes();
		initialise_waterfalls();
	}

	public static void generate_textures() {
		if (waterlakes_data == null) {
			WaterlakeDiffuseGenerator var0 = new WaterlakeDiffuseGenerator();
			waterlakes_data = var0.method2250(64, 64, 64);
		}
		if (waterfalls_data == null) {
			WaterfallDiffuseGenerator var2 = new WaterfallDiffuseGenerator();
			waterfalls_data = var2.method2243(64, 64, 64);
		}

	}

	public static void method1455() {
		if (waterlakes_texture != null) {
			waterlakes_texture.destroy();
			waterlakes_texture = null;
		}
		if (waterlakes_round_textures != null) {
			for (OpenGLTexture2D texture : waterlakes_round_textures) {
				texture.destroy();
			}
			waterlakes_round_textures = null;
		}
		if (waterfalls_texture != null) {
			waterfalls_texture.destroy();
			waterfalls_texture = null;
		}
		if (waterfall_round_textures != null) {
			for (OpenGLTexture2D texture : waterfall_round_textures) {
				texture.destroy();
			}
			waterfall_round_textures = null;
		}
	}

	public static void initialise_waterlakes() {
		if (textures_3d) {
			waterlakes_texture = new OpenGLTexture3D(PixelFormat.LUMINANCE_ALPHA, DataType.INT8, 64, 64, 64, waterlakes_data, PixelFormat.LUMINANCE_ALPHA);
			waterlakes_texture.set_linear_filter(true);
		} else {
			waterlakes_round_textures = new OpenGLTexture2D[64];
			for (int round = 0; round < 64; ++round) {
				byte[] data = new byte[64 * 64 * 2];
				ArrayUtils.copy(waterlakes_data, round * 64 * 64 * 2, data, 0, data.length);
				OpenGLTexture2D texture = waterlakes_round_textures[round] = new OpenGLTexture2D(GL_TEXTURE_2D, PixelFormat.LUMINANCE_ALPHA, DataType.INT8, 64, 64, false, data, PixelFormat.LUMINANCE_ALPHA, false);
				texture.set_linear_filter(false);
			}
		}
	}

	public static void initialise_waterfalls() {
		if (textures_3d) {
			waterfalls_texture = new OpenGLTexture3D(PixelFormat.LUMINANCE_ALPHA, DataType.INT8, 64, 64, 64, waterfalls_data, PixelFormat.LUMINANCE_ALPHA);
			waterfalls_texture.set_linear_filter(true);
		} else {
			waterfall_round_textures = new OpenGLTexture2D[64];
			for (int round = 0; round < 64; ++round) {
				byte[] data = new byte[64 * 64 * 2];
				ArrayUtils.copy(waterfalls_data, round * 64 * 64 * 2, data, 0, data.length);
				OpenGLTexture2D texture = waterfall_round_textures[round] = new OpenGLTexture2D(GL_TEXTURE_2D, PixelFormat.LUMINANCE_ALPHA, DataType.INT8, 64, 64, false, data, PixelFormat.LUMINANCE_ALPHA, false);
				texture.set_linear_filter(false);
			}
		}
	}

	public WaterTextures() {
		// NOOP
	}
}
