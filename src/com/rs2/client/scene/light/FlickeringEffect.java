package com.rs2.client.scene.light;

import com.jagex.ColourUtil;
import com.jagex.MathUtils;
import com.jagex.Packet;
import com.jagex.graphics.runetek4.opengl.light.PointLight;
import com.rs2.client.scene.environment.FlickeringTextureGenerator;

public class FlickeringEffect {

	public static int[] flickering_texture;

	public final float[] color_buffer = new float[4];
	public int type;
	public int level;
	public int x;
	public int y;
	public int z;
	public int colour;
	public int radius;
	public boolean has_bridge;
	public boolean grows_upwards;
	public boolean grows_downwards;
	public PointLight point_light;
	public short[] ranges;
	public float alpha;
	public float attenuation;
	public int surrounding;
	public int effect;
	public int ticker;
	public int duration;
	public int scalar;

	public FlickeringEffect() {
		if (FlickeringEffect.flickering_texture == null) {
			FlickeringEffect.create_flickering_texture();
		}
		update_constants();
	}

	public FlickeringEffect(Packet var1) {
		if (null == FlickeringEffect.flickering_texture) {
			FlickeringEffect.create_flickering_texture();
		}

		level = var1.g1();
		grows_upwards = -1 != ~(level & 16);
		grows_downwards = -1 != ~(level & 8);
		level &= 7;
		x = var1.g2();
		y = var1.g2();
		z = var1.g2();
		radius = var1.g1();
		method1061(66);
		ranges = new short[radius * 2 + 1];
		int var2;
		for (var2 = 0; var2 < ranges.length; ++var2) {
			ranges[var2] = (short) var1.g2();
		}
		colour = ColourUtil.hslToRgbTable[var1.g2()];
		var2 = var1.g1();
		scalar = 1792 & var2 << 3;
		type = var2 & 31;
		if (31 != type) {
			update_constants();
		}
	}

	public void set_type(int var2, int var3, int var4, int var5) {
		effect = var2;
		ticker = var4;
		surrounding = var5;
		duration = var3;
	}

	public void method1061(int var1) {
		int var2 = (radius << 7) - -64;
		attenuation = 1.0F / (var2 * var2);
	}

	public void method1063(boolean var1, int var2, int var3) {
		int offset = scalar + var2 * duration / 50 & 2047;
		int var6 = effect;
		int var4;
		if (var6 == 1) {
			var4 = 1024 - -(MathUtils.SINE[offset] >> 6);
		} else if (var6 != 3) {
			if (var6 == 4) {
				var4 = offset >> 10 << 11;
			} else if (~var6 != -3) {
				if (~var6 != -6) {
					var4 = 2048;
				} else {
					var4 = (offset < 1024 ? offset : 2048 - offset) << 1;
				}
			} else {
				var4 = offset;
			}
		} else {
			var4 = FlickeringEffect.flickering_texture[offset] >> 1;
		}

		if (var1) {
			var4 = 2048;
		}

		alpha = (surrounding + (var4 * ticker >> 11)) / 2048.0F;
		float var8 = alpha / 255.0F;
		color_buffer[0] = (MathUtils.bitAnd(colour, 16771365) >> 16) * var8;
		color_buffer[2] = var8 * MathUtils.bitAnd(255, colour);
		color_buffer[1] = (MathUtils.bitAnd(colour, '\uffe7') >> 8) * var8;
	}

	public final void update_constants() {
		if (type == 2) {
			ticker = 2048;
			surrounding = 0;
			effect = 1;
			duration = 2048;
		} else if (type == 3) {
			surrounding = 0;
			duration = 4096;
			effect = 1;
			ticker = 2048;
		} else if (type == 4) {
			surrounding = 0;
			ticker = 2048;
			effect = 4;
			duration = 2048;
		} else if (type == 5) {
			effect = 4;
			ticker = 2048;
			duration = 8192;
			surrounding = 0;
		} else if (type == 6) {
			ticker = 768;
			surrounding = 1280;
			effect = 3;
			duration = 2048;
		} else if (type == 7) {
			ticker = 768;
			surrounding = 1280;
			duration = 4096;
			effect = 3;
		} else if (type == 8) {
			duration = 2048;
			effect = 3;
			ticker = 1024;
			surrounding = 1024;
		} else if (type == 9) {
			duration = 4096;
			surrounding = 1024;
			ticker = 1024;
			effect = 3;
		} else if (type == 10) {
			ticker = 512;
			effect = 3;
			surrounding = 1536;
			duration = 2048;
		} else if (type == 11) {
			effect = 3;
			duration = 4096;
			ticker = 512;
			surrounding = 1536;
		} else if (type == 12) {
			ticker = 2048;
			effect = 2;
			duration = 2048;
			surrounding = 0;
		} else if (type == 13) {
			duration = 8192;
			ticker = 2048;
			effect = 2;
			surrounding = 0;
		} else if (type == 14) {
			duration = 2048;
			surrounding = 1280;
			effect = 1;
			ticker = 768;
		} else if (type == 15) {
			ticker = 512;
			duration = 4096;
			surrounding = 1536;
			effect = 1;
		} else if (type == 16) {
			duration = 8192;
			surrounding = 1792;
			effect = 1;
			ticker = 256;
		} else {
			duration = 2048;
			surrounding = 0;
			ticker = 2048;
			effect = 0;
		}
	}

	public static final void create_flickering_texture() {
		flickering_texture = FlickeringTextureGenerator.method5620(2048, 35, 8, 8, 4, 0.4F, true);
	}

}
