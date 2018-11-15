package com.jagex;

/**
 * Represents a floor overlay configuration.
 * 
 * @author Walied K. Yassen
 */
public class FloType extends Queuable {

	public int id;
	public int texture_id;
	public int texture_scale;
	public int tile_colour;
	public int minimap_colour;
	public int water_colour;
	public int slot;
	public boolean shadowed;
	public boolean occlude;
	public boolean blended;
	public int water_fog_depth;

	public FloType() {
		tile_colour = 0;
		occlude = true;
		texture_id = -1;
		minimap_colour = -1;
		water_colour = 0x122b3d;
		texture_scale = TileConstants.SIZE_1BY1;
		shadowed = true;
		blended = false;
		slot = 8;
		water_fog_depth = 16;
	}

	public void decode(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			decode(buffer, opcode);
		}
		slot <<= 8;
		slot |= id;
	}

	public void decode(Packet buffer, int opcode) {
		if (opcode == 1) {
			tile_colour = rgb2hsl(buffer.g3());
		} else if (opcode == 2) {
			texture_id = buffer.g1();
		} else if (opcode == 3) {
			texture_id = buffer.g2();
			if (texture_id == 65535) {
				texture_id = -1;
			}
		} else if (opcode == 5) {
			occlude = false;
		} else if (opcode == 7) {
			minimap_colour = rgb2hsl(buffer.g3());
		} else if (opcode == 8) {
			AbstractTimer.anInt305 = id;
		} else if (opcode == 9) {
			texture_scale = buffer.g2();
		} else if (opcode == 10) {
			shadowed = false;
		} else if (opcode == 11) {
			slot = buffer.g1();
		} else if (opcode == 12) {
			blended = true;
		} else if (opcode == 13) {
			water_colour = buffer.g3();
		} else if (opcode == 14) {
			water_fog_depth = buffer.g1();
		} else if (opcode == 16) {
			buffer.g1();
		}
	}

	public static int rgb2hsl(int rgb) {
		if (16711935 == rgb) {
			return -1;
		}
		return rgb2hsl_inner(rgb);
	}

	public static int rgb2hsl_inner(int rgb) {
		double red = (rgb >> 16 & 0xff) / 256.0;
		double green = (rgb >> 8 & 0xff) / 256.0;
		double blue = (rgb & 0xff) / 256.0;
		double min = red;
		if (green < min) {
			min = green;
		}
		if (blue < min) {
			min = blue;
		}
		double max = red;
		if (green > max) {
			max = green;
		}
		if (blue > max) {
			max = blue;
		}
		double hue_normalised = 0.0;
		double saturation_normalised = 0.0;
		double lightness_normalised = (max + min) / 2.0;
		if (max != min) {
			if (lightness_normalised < 0.5) {
				saturation_normalised = (max - min) / (min + max);
			}
			if (lightness_normalised >= 0.5) {
				saturation_normalised = (max - min) / (2.0 - max - min);
			}
			if (red == max) {
				hue_normalised = (green - blue) / (max - min);
			} else if (green == max) {
				hue_normalised = (blue - red) / (max - min) + 2.0;
			} else if (max == blue) {
				hue_normalised = (red - green) / (max - min) + 4.0;
			}
		}
		hue_normalised /= 6.0;
		int hue = (int) (hue_normalised * 256.0);
		int saturation = (int) (saturation_normalised * 256.0);
		int lightness = (int) (lightness_normalised * 256.0);
		if (saturation < 0) {
			saturation = 0;
		} else if (saturation > 255) {
			saturation = 255;
		}
		if (lightness < 0) {
			lightness = 0;
		} else if (lightness > 255) {
			lightness = 255;
		}
		if (lightness > 243) {
			saturation >>= 4;
		} else if (lightness > 217) {
			saturation >>= 3;
		} else if (lightness > 192) {
			saturation >>= 2;
		} else if (lightness > 179) {
			saturation >>= 1;
		}
		return (lightness >> 1) + (saturation >> 5 << 7) + ((hue & 0xff) >> 2 << 10);
	}

}
