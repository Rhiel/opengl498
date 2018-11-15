package com.jagex;

public class ColourUtil {

	public static int[] hsvToRgbTable;
	public static int[] hslToRgbTable;

	private static int hsvToRgbDepth;
	private static int hslToRgbDepth;

	public static void initialiseColorTable(boolean hsvTable, boolean hslTable) {
		if (hsvTable) {
			hsvToRgbDepth++;
			initialiseHsvToRgbTable();
		}
		if (hslTable) {
			hslToRgbDepth++;
			initialiseHslToRgbTable();
		}
	}

	public static void initialiseHsvToRgbTable() {
		if (hslToRgbTable == null) {
			hsvToRgbTable = new int[65536];
		} else {
			return;
		}
		int idx = 0;
		for (int x = 0; x < 512; x++) {
			float hue = (0.0078125F + (x >> 3) / 64.0F) * 360.0F;
			float saturation = (x & 0x7) / 8.0F + 0.0625F;
			for (int y = 0; y < 128; y++) {
				float brightness = y / 128.0F;
				float r = 0.0F;
				float g = 0.0F;
				float b = 0.0F;
				float h = hue / 60.0F;
				int c = (int) h;
				int type = c % 6;
				float f = h - c;
				float p = brightness * (1.0F - saturation);
				float q = brightness * (1.0F - saturation * f);
				float t = brightness * (1.0F - (1.0F - f) * saturation);
				if (0 == type) {
					r = brightness;
					g = t;
					b = p;
				} else if (type == 1) {
					r = q;
					g = brightness;
					b = p;
				} else if (type == 2) {
					r = p;
					g = brightness;
					b = t;
				} else if (3 == type) {
					r = p;
					g = q;
					b = brightness;
				} else if (4 == type) {
					r = t;
					g = p;
					b = brightness;
				} else if (5 == type) {
					r = brightness;
					g = p;
					b = q;
				}
				r = (float) Math.pow(r, 0.7f);
				g = (float) Math.pow(g, 0.7f);
				b = (float) Math.pow(b, 0.7f);
				int ri = (int) (r * 256.0F);
				int gi = (int) (g * 256.0F);
				int bi = (int) (b * 256.0F);
				int hash = -16777216 + (ri << 16) + (gi << 8) + bi;
				ColourUtil.hsvToRgbTable[idx++] = hash;
			}
		}
	}

	public static void destroyColorTable(boolean rgbTable, boolean hslTable) {
		if (rgbTable) {
			hsvToRgbDepth--;
			if (hsvToRgbDepth == 0) {
				hsvToRgbTable = null;
			}
		}
		if (hslTable) {
			hslToRgbDepth--;
			if (hslToRgbDepth == 0) {
				hslToRgbTable = null;
			}
		}
	}

	public static void initialiseHslToRgbTable() {
		if (hslToRgbTable == null) {
			hslToRgbTable = new int[65536];
		} else {
			return;
		}
		for (int hsl = 0; hsl < 65536; hsl++) {
			double h = (hsl >> 10 & 0x3f) / 64.0 + 0.0078125;
			double s = (hsl >> 7 & 0x7) / 8.0 + 0.0625;
			double l = (hsl & 0x7f) / 128.0;
			double r = l;
			double g = l;
			double b = l;
			if (s != 0.0) {
				double q;
				if (l < 0.5) {
					q = (1.0 + s) * l;
				} else {
					q = l + s - s * l;
				}
				double p = 2.0 * l - q;
				double tr = 0.3333333333333333 + h;
				if (tr > 1.0) {
					tr--;
				}
				double tb = h - 0.3333333333333333;
				if (tb < 0.0) {
					tb++;
				}
				if (6.0 * tr < 1.0) {
					r = tr * (6.0 * (q - p)) + p;
				} else if (tr * 2.0 < 1.0) {
					r = q;
				} else if (3.0 * tr < 2.0) {
					r = p + 6.0 * ((0.6666666666666666 - tr) * (q - p));
				} else {
					r = p;
				}
				if (h * 6.0 < 1.0) {
					g = p + 6.0 * (q - p) * h;
				} else if (h * 2.0 < 1.0) {
					g = q;
				} else if (h * 3.0 < 2.0) {
					g = (0.6666666666666666 - h) * (q - p) * 6.0 + p;
				} else {
					g = p;
				}
				if (tb * 6.0 < 1.0) {
					b = p + (q - p) * 6.0 * tb;
				} else if (tb * 2.0 < 1.0) {
					b = q;
				} else if (tb * 3.0 < 2.0) {
					b = p + (q - p) * (0.6666666666666666 - tb) * 6.0;
				} else {
					b = p;
				}
			}
			r = Math.pow(r, 0.7f);
			g = Math.pow(g, 0.7f);
			b = Math.pow(b, 0.7f);
			int ri = (int) (r * 256.0);
			int gi = (int) (g * 256.0);
			int bi = (int) (b * 256.0);
			int rgb = (ri << 16) + (gi << 8) + bi;
			hslToRgbTable[hsl] = rgb & 0xffffff;
		}
	}

	public static final int adjust_overlay_lightness(int hsl, int lightness) {
		if (hsl == -2) {
			return 12345678;
		}
		if (hsl == -1) {
			if (lightness < 2) {
				lightness = 2;
			} else if (lightness > 126) {
				lightness = 126;
			}
			return lightness;
		}
		lightness = (hsl & 0x7f) * lightness >> 7;
		if (lightness < 2) {
			lightness = 2;
		} else if (lightness > 126) {
			lightness = 126;
		}
		return (hsl & 0xff80) + lightness;
	}

	public static short hsl_to_hsv(int hsl) {
		int hue = hsl >> 10 & 0x3f;
		int saturation = hsl >> 3 & 0x70;
		int lightness = hsl & 0x7f;
		saturation = lightness <= 64 ? saturation * lightness >> 7 : saturation * (127 - lightness) >> 7;
		int v = saturation + lightness;
		int s2;
		if (v != 0) {
			s2 = (saturation << 8) / v;
		} else {
			s2 = saturation << 1;
		}
		return (short) (hue << 10 | s2 >> 4 << 7 | v);
	}

	public static int adjust_lightness(int hsv, int lightness) {
		if (hsv == -1) {
			return 12345678;
		}
		lightness = lightness * (hsv & 0x7f) >> 7;
		if (lightness < 2) {
			lightness = 2;
		} else if (lightness > 126) {
			lightness = 126;
		}
		return (hsv & 0xff80) + lightness;
	}

}
