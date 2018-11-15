package com.jagex;

public class FluType extends Queuable {

	public int rgb;
	public int texture_id;
	public int chroma;
	public int saturation;
	public int luminance;
	public int hue;
	public int texture_scale;
	public boolean shadowed;
	public boolean occlude;

	public FluType() {
		rgb = 0;
		texture_id = -1;
		texture_scale = TileConstants.SIZE_1BY1;
		shadowed = true;
		occlude = true;
	}

	public void decode(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			decode(buffer, opcode);
		}
	}

	public final void decode(Packet buffer, int opcode) {
		if (opcode == 1) {
			rgb = buffer.g3();
			rgb2hsl(rgb);
		} else if (opcode == 2) {
			texture_id = buffer.g2();
			if (texture_id == 65535) {
				texture_id = -1;
			}
		} else if (opcode == 3) {
			texture_scale = buffer.g2();
		} else if (opcode == 4) {
			shadowed = false;
		} else if (opcode == 5) {
			occlude = false;
		}

	}

	public void rgb2hsl(int rgb) {
		double r = (rgb >> 16 & 0xff) / 256.0;
		double g = (rgb >> 8 & 0xff) / 256.0;
		double b = (rgb & 0xff) / 256.0;
		double darkest = r;
		if (g < darkest) {
			darkest = g;
		}
		if (b < darkest) {
			darkest = b;
		}
		double lightest = r;
		if (g > lightest) {
			lightest = g;
		}
		if (b > lightest) {
			lightest = b;
		}
		double h = 0.0;
		double s = 0.0;
		double l = (lightest + darkest) / 2.0;
		if (lightest != darkest) {
			if (l < 0.5) {
				s = (lightest - darkest) / (darkest + lightest);
			}
			if (l >= 0.5) {
				s = (lightest - darkest) / (2.0 - lightest - darkest);
			}
			if (r == lightest) {
				h = (g - b) / (lightest - darkest);
			} else if (lightest == g) {
				h = 2.0 + (b - r) / (lightest - darkest);
			} else if (b == lightest) {
				h = 4.0 + (r - g) / (lightest - darkest);
			}
		}
		h /= 6.0;
		hue = (int) (256.0 * s);
		saturation = (int) (256.0 * l);
		if (hue < 0) {
			hue = 0;
		} else if (hue > 255) {
			hue = 255;
		}
		if (saturation < 0) {
			saturation = 0;
		} else if (saturation > 255) {
			saturation = 255;
		}
		if (l > 0.5) {
			luminance = (int) (s * (1.0 - l) * 512.0);
		} else {
			luminance = (int) (512.0 * (l * s));
		}
		if (luminance < 1) {
			luminance = 1;
		}
		chroma = (int) (h * luminance);

	}
}
