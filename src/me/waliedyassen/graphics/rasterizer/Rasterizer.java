package me.waliedyassen.graphics.rasterizer;

import static com.jagex.ColourUtil.hslToRgbTable;

import com.jagex.ColourUtil;
import com.jagex.GraphicTools;
import com.jagex.Rasterizer2D;
import com.jagex.Viewport;
import com.jagex.core.tools.MathTools;

import me.waliedyassen.materials.MaterialRaw;
import me.waliedyassen.materials.MaterialRawList;
import me.waliedyassen.textures.TextureCache;

/**
 * Represents a 3D software engine rasterizer.
 * 
 * @author Walied K. Yassen
 */
public final class Rasterizer {

	/**
	 * The shadows decay table.
	 */
	public static final int[] SHADOW_DECAY = new int[512];

	/**
	 * The lights decay table.
	 */
	public static final int[] LIGHT_DECAY = new int[2048];

	/**
	 * The sine table.
	 */
	public static final int[] SINE = new int[2048];

	/**
	 * The sine table.
	 */
	public static final int[] SINE_SMOOTH = new int[16384];

	/**
	 * The cosine table.
	 */
	public static final int[] COSINE = new int[2048];

	/**
	 * The sine table.
	 */
	public static final int[] COSINE_SMOOTH = new int[16384];

	/**
	 * The materials list.
	 */
	public final MaterialRawList materials;

	/**
	 * The textures cache.
	 */
	public final TextureCache textures;

	/**
	 * The colour buffer.
	 */
	public int[] colour_buffer;

	/**
	 * Th depth buffer.
	 */
	public float[] depth_buffer;

	/**
	 * The viewport center x position.
	 */
	public int center_x;

	/**
	 * The viewport center y position.
	 */
	public int center_y;

	/**
	 * The current scanline line width.
	 */
	public int scanline_width;

	/**
	 * The current scanline offsets buffer.
	 */
	public int[] scanline_offsets;

	/**
	 * Tells whether or not to smooth the blending edges.
	 */
	public boolean smooth_edges;

	/**
	 * The edges clipping viewport height (left).
	 */
	public int clip_width;

	/**
	 * The edges clipping viewport width (right).
	 */
	public int clip_height;

	/**
	 * Tells whether to clip the edges or not.
	 */
	public boolean clip_edges;

	/**
	 * Tells whether the blending is enabled or not.
	 */
	public boolean blending;

	/**
	 * The blending alpha value.
	 */
	public int blending_alpha;

	/**
	 * The current alpha mode.
	 */
	public AlphaMode alpha_mode;

	/**
	 * Whether or not we should store the z values into a buffer.
	 */
	public boolean zbuffering;

	/**
	 * The current fog colour.
	 */
	public int fog_colour;

	/**
	 * The triangle vertex A texture id.
	 */
	public int texture_id_a;

	/**
	 * The triangle vertex B texture id.
	 */
	public int texture_id_b;

	/**
	 * The triangle vertex C texture id.
	 */
	public int texture_id_c;

	/**
	 * The triangle vertex A texture texels.
	 */
	public int[] texture_pixels_a;

	/**
	 * The triangle vertex B texture texels.
	 */
	public int[] texture_pixels_b;

	/**
	 * The triangle vertex C texture texels.
	 */
	public int[] texture_pixels_c;

	/**
	 * The triangle vertex A texture size.
	 */
	public int texture_size_a;

	/**
	 * The triangle vertex B texture size.
	 */
	public int texture_size_b;

	/**
	 * The triangle vertex C texture size.
	 */
	public int texture_size_c;

	/**
	 * The triangle vertex A texture mask.
	 */
	public int texture_mask_a;

	/**
	 * The triangle vertex B texture mask.
	 */
	public int texture_mask_b;

	/**
	 * The triangle vertex C texture mask.
	 */
	public int texture_mask_c;

	/**
	 * The triangle vertex A texture scale.
	 */
	public float texture_scale_a;

	/**
	 * The triangle vertex B texture scale.
	 */
	public float texture_scale_b;

	/**
	 * The triangle vertex C texture scale.
	 */
	public float texture_scale_c;

	/**
	 * Whether or not we should repeat the textures once we reach the UV ends.
	 */
	public boolean texture_repeat;

	/**
	 * Unknown value.
	 */
	public boolean aBoolean419;

	/**
	 * The current brightness value.
	 */
	public double brightness;

	// 530 fields, could be converted to these ones btw ^
	private boolean small_texture;
	private boolean alpha_opaque;
	public boolean invalid_texture;

	/**
	 * Constructs a new {@link Rasterizer} type object instance.
	 */
	public Rasterizer(MaterialRawList materials, TextureCache textures) {
		this.materials = materials;
		this.textures = textures;
		reset_state();
	}

	/**
	 * @param colour_buffer
	 * @param depth_buffer
	 * @param scanline_width
	 */
	public void initialise(int[] colour_buffer, float[] depth_buffer, int scanline_width) {
		this.colour_buffer = colour_buffer;
		this.depth_buffer = depth_buffer;
		this.scanline_width = scanline_width;
		// reset_state();s
	}

	/**
	 * The static initialise of {@link Rasterizer}.
	 */
	static {
		for (int n = 1; n < 512; n++) {
			SHADOW_DECAY[n] = 32768 / n;
		}
		for (int n = 1; n < 2048; n++) {
			LIGHT_DECAY[n] = 65536 / n;
		}
		for (int angle = 0; angle < 2048; angle++) {
			SINE[angle] = (int) (Math.sin(angle * 0.0030679615) * 65536.0);
			COSINE[angle] = (int) (Math.cos(angle * 0.0030679615) * 65536.0);
		}
		double d = 3.834951969714103E-4;
		for (int angle = 0; angle < 16384; angle++) {
			SINE_SMOOTH[angle] = (int) (Math.sin(d * angle) * 16384.0);
			COSINE_SMOOTH[angle] = (int) (Math.cos(d * angle) * 16384.0);
		}
	}

	/**
	 * Resets the current graphics state.
	 */
	public void reset_state() {
		// TODO: wireframe_mode = false;
		smooth_edges = true;
		blending = false;
		blending_alpha = 0;
		clip_edges = false;
		aBoolean419 = false;
		scanline_offsets = new int[4096];
		texture_id_a = -1;
		texture_pixels_a = null;
		texture_size_a = 0;
		texture_mask_a = 0;
		texture_scale_a = 0.0F;
		texture_repeat = true;
		texture_id_b = -1;
		texture_pixels_b = null;
		texture_size_b = 0;
		texture_mask_b = 0;
		texture_scale_b = 0.0F;
		texture_id_c = -1;
		texture_pixels_c = null;
		texture_size_c = 0;
		texture_mask_c = 0;
		texture_scale_c = 0.0F;
		alpha_mode = AlphaMode.ALPHA_TESTED;
	}

	public void draw_triangle(boolean colour_write, boolean depth_write, boolean transparent, float y_a, float y_b, float y_c, float x_a, float x_b, float x_c, float z_a, float z_b, float z_c, int colour_a, int colour_b, int colour_c) {
		if (!colour_write) {
			draw_flat_triangle(false, depth_write, transparent, y_a, y_b, y_c, x_a, x_b, x_c, z_a, z_b, z_c, 0);
			return;
		}
		// if (wireframe_mode) {
		// master.line((int) x_a, (int) y_a, (int) x_b, (int) y_b, 0xff000000 | colour_a);
		// master.line((int) x_b, (int) y_b, (int) x_c, (int) y_c, 0xff000000 | colour_a);
		// master.line((int) x_c, (int) y_c, (int) x_a, (int) y_a, 0xff000000 | colour_a);
		// return;
		// }
		float ba_x = x_b - x_a;
		float ba_y = y_b - y_a;
		float ca_x = x_c - x_a;
		float ca_y = y_c - y_a;
		float z_ba = z_b - z_a;
		float z_ca = z_c - z_a;
		float ba_red = (colour_b & 0xff0000) - (colour_a & 0xff0000);
		float ca_red = (colour_c & 0xff0000) - (colour_a & 0xff0000);
		float ba_green = (colour_b & 0xff00) - (colour_a & 0xff00);
		float ca_green = (colour_c & 0xff00) - (colour_a & 0xff00);
		float ba_blue = (colour_b & 0xff) - (colour_a & 0xff);
		float ca_blue = (colour_c & 0xff) - (colour_a & 0xff);
		float a_to_b;
		if (y_c != y_b) {
			a_to_b = (x_c - x_b) / (y_c - y_b);
		} else {
			a_to_b = 0.0F;
		}
		float b_to_c;
		if (y_b != y_a) {
			b_to_c = ba_x / ba_y;
		} else {
			b_to_c = 0.0F;
		}
		float c_to_a;
		if (y_c != y_a) {
			c_to_a = ca_x / ca_y;
		} else {
			c_to_a = 0.0F;
		}
		float slope = ba_x * ca_y - ca_x * ba_y;
		if (slope == 0.0F) {
			return;
		}
		float z_ba_slope = (z_ba * ca_y - z_ca * ba_y) / slope;
		float z_ca_slope = (z_ca * ba_x - z_ba * ca_x) / slope;
		float ba_red_slope = (ba_red * ca_y - ca_red * ba_y) / slope;
		float ca_red_slope = (ca_red * ba_x - ba_red * ca_x) / slope;
		float ba_green_slope = (ba_green * ca_y - ca_green * ba_y) / slope;
		float ca_green_slope = (ca_green * ba_x - ba_green * ca_x) / slope;
		float ba_blue_slope = (ba_blue * ca_y - ca_blue * ba_y) / slope;
		float ca_blue_slope = (ca_blue * ba_x - ba_blue * ca_x) / slope;
		if (y_a <= y_b && y_a <= y_c) {
			if (y_a >= clip_height) {
				return;
			}
			if (y_b > clip_height) {
				y_b = clip_height;
			}
			if (y_c > clip_height) {
				y_c = clip_height;
			}
			z_a = z_a - z_ba_slope * x_a + z_ba_slope;
			float f33 = (colour_a & 0xff0000) - ba_red_slope * x_a + ba_red_slope;
			float f36 = (colour_a & 0xff00) - ba_green_slope * x_a + ba_green_slope;
			float f39 = (colour_a & 0xff) - ba_blue_slope * x_a + ba_blue_slope;
			if (y_b < y_c) {
				x_c = x_a;
				if (y_a < 0.0F) {
					x_c -= c_to_a * y_a;
					x_a -= b_to_c * y_a;
					z_a -= z_ca_slope * y_a;
					f33 -= ca_red_slope * y_a;
					f36 -= ca_green_slope * y_a;
					f39 -= ca_blue_slope * y_a;
					y_a = 0.0F;
				}
				if (y_b < 0.0F) {
					x_b -= a_to_b * y_b;
					y_b = 0.0F;
				}
				if (y_a != y_b && c_to_a < b_to_c || y_a == y_b && c_to_a > a_to_b) {
					y_a = (int) (y_a + 0.5F);
					y_b = (int) (y_b + 0.5F);
					y_c = (int) (y_c + 0.5F) - y_b;
					y_b -= y_a;
					for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
						draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_c, (int) x_a, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
						x_c += c_to_a;
						x_a += b_to_c;
						z_a += z_ca_slope;
						f33 += ca_red_slope;
						f36 += ca_green_slope;
						f39 += ca_blue_slope;
					}

					while (--y_c >= 0.0F) {
						draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_c, (int) x_b, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
						x_c += c_to_a;
						x_b += a_to_b;
						z_a += z_ca_slope;
						f33 += ca_red_slope;
						f36 += ca_green_slope;
						f39 += ca_blue_slope;
						y_a += scanline_width;
					}
					return;
				}
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_b;
				y_b -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_a, (int) x_c, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
					x_c += c_to_a;
					x_a += b_to_c;
					z_a += z_ca_slope;
					f33 += ca_red_slope;
					f36 += ca_green_slope;
					f39 += ca_blue_slope;
				}

				while (--y_c >= 0.0F) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_b, (int) x_c, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
					x_c += c_to_a;
					x_b += a_to_b;
					z_a += z_ca_slope;
					f33 += ca_red_slope;
					f36 += ca_green_slope;
					f39 += ca_blue_slope;
					y_a += scanline_width;
				}
				return;
			}
			x_b = x_a;
			if (y_a < 0.0F) {
				x_b -= c_to_a * y_a;
				x_a -= b_to_c * y_a;
				z_a -= z_ca_slope * y_a;
				f33 -= ca_red_slope * y_a;
				f36 -= ca_green_slope * y_a;
				f39 -= ca_blue_slope * y_a;
				y_a = 0.0F;
			}
			if (y_c < 0.0F) {
				x_c -= a_to_b * y_c;
				y_c = 0.0F;
			}
			if (y_a != y_c && c_to_a < b_to_c || y_a == y_c && a_to_b > b_to_c) {
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_c;
				y_c -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_b, (int) x_a, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
					x_b += c_to_a;
					x_a += b_to_c;
					z_a += z_ca_slope;
					f33 += ca_red_slope;
					f36 += ca_green_slope;
					f39 += ca_blue_slope;
				}

				while (--y_b >= 0.0F) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_c, (int) x_a, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
					x_c += a_to_b;
					x_a += b_to_c;
					z_a += z_ca_slope;
					f33 += ca_red_slope;
					f36 += ca_green_slope;
					f39 += ca_blue_slope;
					y_a += scanline_width;
				}
				return;
			}
			y_a = (int) (y_a + 0.5F);
			y_c = (int) (y_c + 0.5F);
			y_b = (int) (y_b + 0.5F) - y_c;
			y_c -= y_a;
			for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_a, (int) x_b, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
				x_b += c_to_a;
				x_a += b_to_c;
				z_a += z_ca_slope;
				f33 += ca_red_slope;
				f36 += ca_green_slope;
				f39 += ca_blue_slope;
			}

			while (--y_b >= 0.0F) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_a, (int) x_c, z_a, z_ba_slope, f33, ba_red_slope, f36, ba_green_slope, f39, ba_blue_slope);
				x_c += a_to_b;
				x_a += b_to_c;
				z_a += z_ca_slope;
				f33 += ca_red_slope;
				f36 += ca_green_slope;
				f39 += ca_blue_slope;
				y_a += scanline_width;
			}
			return;
		}
		if (y_b <= y_c) {
			if (y_b >= clip_height) {
				return;
			}
			if (y_c > clip_height) {
				y_c = clip_height;
			}
			if (y_a > clip_height) {
				y_a = clip_height;
			}
			z_b = z_b - z_ba_slope * x_b + z_ba_slope;
			float f34 = (colour_b & 0xff0000) - ba_red_slope * x_b + ba_red_slope;
			float f37 = (colour_b & 0xff00) - ba_green_slope * x_b + ba_green_slope;
			float f40 = (colour_b & 0xff) - ba_blue_slope * x_b + ba_blue_slope;
			if (y_c < y_a) {
				x_a = x_b;
				if (y_b < 0.0F) {
					x_a -= b_to_c * y_b;
					x_b -= a_to_b * y_b;
					z_b -= z_ca_slope * y_b;
					f34 -= ca_red_slope * y_b;
					f37 -= ca_green_slope * y_b;
					f40 -= ca_blue_slope * y_b;
					y_b = 0.0F;
				}
				if (y_c < 0.0F) {
					x_c -= c_to_a * y_c;
					y_c = 0.0F;
				}
				if (y_b != y_c && b_to_c < a_to_b || y_b == y_c && b_to_c > c_to_a) {
					y_b = (int) (y_b + 0.5F);
					y_c = (int) (y_c + 0.5F);
					y_a = (int) (y_a + 0.5F) - y_c;
					y_c -= y_b;
					for (y_b = scanline_offsets[(int) y_b]; --y_c >= 0.0F; y_b += scanline_width) {
						draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_a, (int) x_b, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
						x_a += b_to_c;
						x_b += a_to_b;
						z_b += z_ca_slope;
						f34 += ca_red_slope;
						f37 += ca_green_slope;
						f40 += ca_blue_slope;
					}

					while (--y_a >= 0.0F) {
						draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_a, (int) x_c, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
						x_a += b_to_c;
						x_c += c_to_a;
						z_b += z_ca_slope;
						f34 += ca_red_slope;
						f37 += ca_green_slope;
						f40 += ca_blue_slope;
						y_b += scanline_width;
					}
					return;
				}
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_a = (int) (y_a + 0.5F) - y_c;
				y_c -= y_b;
				for (y_b = scanline_offsets[(int) y_b]; --y_c >= 0.0F; y_b += scanline_width) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_b, (int) x_a, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
					x_a += b_to_c;
					x_b += a_to_b;
					z_b += z_ca_slope;
					f34 += ca_red_slope;
					f37 += ca_green_slope;
					f40 += ca_blue_slope;
				}

				while (--y_a >= 0.0F) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_c, (int) x_a, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
					x_a += b_to_c;
					x_c += c_to_a;
					z_b += z_ca_slope;
					f34 += ca_red_slope;
					f37 += ca_green_slope;
					f40 += ca_blue_slope;
					y_b += scanline_width;
				}
				return;
			}
			x_c = x_b;
			if (y_b < 0.0F) {
				x_c -= b_to_c * y_b;
				x_b -= a_to_b * y_b;
				z_b -= z_ca_slope * y_b;
				f34 -= ca_red_slope * y_b;
				f37 -= ca_green_slope * y_b;
				f40 -= ca_blue_slope * y_b;
				y_b = 0.0F;
			}
			if (y_a < 0.0F) {
				x_a -= c_to_a * y_a;
				y_a = 0.0F;
			}
			if (b_to_c < a_to_b) {
				y_b = (int) (y_b + 0.5F);
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_a;
				y_a -= y_b;
				for (y_b = scanline_offsets[(int) y_b]; --y_a >= 0.0F; y_b += scanline_width) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_c, (int) x_b, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
					x_c += b_to_c;
					x_b += a_to_b;
					z_b += z_ca_slope;
					f34 += ca_red_slope;
					f37 += ca_green_slope;
					f40 += ca_blue_slope;
				}

				while (--y_c >= 0.0F) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_a, (int) x_b, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
					x_a += c_to_a;
					x_b += a_to_b;
					z_b += z_ca_slope;
					f34 += ca_red_slope;
					f37 += ca_green_slope;
					f40 += ca_blue_slope;
					y_b += scanline_width;
				}
				return;
			}
			y_b = (int) (y_b + 0.5F);
			y_a = (int) (y_a + 0.5F);
			y_c = (int) (y_c + 0.5F) - y_a;
			y_a -= y_b;
			for (y_b = scanline_offsets[(int) y_b]; --y_a >= 0.0F; y_b += scanline_width) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_b, (int) x_c, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
				x_c += b_to_c;
				x_b += a_to_b;
				z_b += z_ca_slope;
				f34 += ca_red_slope;
				f37 += ca_green_slope;
				f40 += ca_blue_slope;
			}

			while (--y_c >= 0.0F) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_b, (int) x_a, z_b, z_ba_slope, f34, ba_red_slope, f37, ba_green_slope, f40, ba_blue_slope);
				x_a += c_to_a;
				x_b += a_to_b;
				z_b += z_ca_slope;
				f34 += ca_red_slope;
				f37 += ca_green_slope;
				f40 += ca_blue_slope;
				y_b += scanline_width;
			}
			return;
		}
		if (y_c >= clip_height) {
			return;
		}
		if (y_a > clip_height) {
			y_a = clip_height;
		}
		if (y_b > clip_height) {
			y_b = clip_height;
		}
		z_c = z_c - z_ba_slope * x_c + z_ba_slope;
		float f35 = (colour_c & 0xff0000) - ba_red_slope * x_c + ba_red_slope;
		float f38 = (colour_c & 0xff00) - ba_green_slope * x_c + ba_green_slope;
		float f41 = (colour_c & 0xff) - ba_blue_slope * x_c + ba_blue_slope;
		if (y_a < y_b) {
			x_b = x_c;
			if (y_c < 0.0F) {
				x_b -= a_to_b * y_c;
				x_c -= c_to_a * y_c;
				z_c -= z_ca_slope * y_c;
				f35 -= ca_red_slope * y_c;
				f38 -= ca_green_slope * y_c;
				f41 -= ca_blue_slope * y_c;
				y_c = 0.0F;
			}
			if (y_a < 0.0F) {
				x_a -= b_to_c * y_a;
				y_a = 0.0F;
			}
			if (a_to_b < c_to_a) {
				y_c = (int) (y_c + 0.5F);
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_a;
				y_a -= y_c;
				for (y_c = scanline_offsets[(int) y_c]; --y_a >= 0.0F; y_c += scanline_width) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_b, (int) x_c, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
					x_b += a_to_b;
					x_c += c_to_a;
					z_c += z_ca_slope;
					f35 += ca_red_slope;
					f38 += ca_green_slope;
					f41 += ca_blue_slope;
				}

				while (--y_b >= 0.0F) {
					draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_b, (int) x_a, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
					x_b += a_to_b;
					x_a += b_to_c;
					z_c += z_ca_slope;
					f35 += ca_red_slope;
					f38 += ca_green_slope;
					f41 += ca_blue_slope;
					y_c += scanline_width;
				}
				return;
			}
			y_c = (int) (y_c + 0.5F);
			y_a = (int) (y_a + 0.5F);
			y_b = (int) (y_b + 0.5F) - y_a;
			y_a -= y_c;
			for (y_c = scanline_offsets[(int) y_c]; --y_a >= 0.0F; y_c += scanline_width) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_c, (int) x_b, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
				x_b += a_to_b;
				x_c += c_to_a;
				z_c += z_ca_slope;
				f35 += ca_red_slope;
				f38 += ca_green_slope;
				f41 += ca_blue_slope;
			}

			while (--y_b >= 0.0F) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_a, (int) x_b, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
				x_b += a_to_b;
				x_a += b_to_c;
				z_c += z_ca_slope;
				f35 += ca_red_slope;
				f38 += ca_green_slope;
				f41 += ca_blue_slope;
				y_c += scanline_width;
			}
			return;
		}
		x_a = x_c;
		if (y_c < 0.0F) {
			x_a -= a_to_b * y_c;
			x_c -= c_to_a * y_c;
			z_c -= z_ca_slope * y_c;
			f35 -= ca_red_slope * y_c;
			f38 -= ca_green_slope * y_c;
			f41 -= ca_blue_slope * y_c;
			y_c = 0.0F;
		}
		if (y_b < 0.0F) {
			x_b -= b_to_c * y_b;
			y_b = 0.0F;
		}
		if (a_to_b < c_to_a) {
			y_c = (int) (y_c + 0.5F);
			y_b = (int) (y_b + 0.5F);
			y_a = (int) (y_a + 0.5F) - y_b;
			y_b -= y_c;
			for (y_c = scanline_offsets[(int) y_c]; --y_b >= 0.0F; y_c += scanline_width) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_a, (int) x_c, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
				x_a += a_to_b;
				x_c += c_to_a;
				z_c += z_ca_slope;
				f35 += ca_red_slope;
				f38 += ca_green_slope;
				f41 += ca_blue_slope;
			}

			while (--y_a >= 0.0F) {
				draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_b, (int) x_c, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
				x_b += b_to_c;
				x_c += c_to_a;
				z_c += z_ca_slope;
				f35 += ca_red_slope;
				f38 += ca_green_slope;
				f41 += ca_blue_slope;
				y_c += scanline_width;
			}
			return;
		}
		y_c = (int) (y_c + 0.5F);
		y_b = (int) (y_b + 0.5F);
		y_a = (int) (y_a + 0.5F) - y_b;
		y_b -= y_c;
		y_c = scanline_offsets[(int) y_c];
		while (--y_b >= 0.0F) {
			draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_c, (int) x_a, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
			x_a += a_to_b;
			x_c += c_to_a;
			z_c += z_ca_slope;
			f35 += ca_red_slope;
			f38 += ca_green_slope;
			f41 += ca_blue_slope;
			y_c += scanline_width;
		}

		while (--y_a >= 0.0F) {
			draw_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_c, (int) x_b, z_c, z_ba_slope, f35, ba_red_slope, f38, ba_green_slope, f41, ba_blue_slope);
			x_b += b_to_c;
			x_c += c_to_a;
			z_c += z_ca_slope;
			f35 += ca_red_slope;
			f38 += ca_green_slope;
			f41 += ca_blue_slope;
			y_c += scanline_width;
		}
	}

	public void draw_scanline(boolean depth_write, boolean transparent, int[] colour_buffer, int buffer_offset, int background, int y, int start_x, int end_x, float start_z, float z_slope, float red, float red_slope, float green, float green_slope, float blue, float blue_slope) {
		if (clip_edges) {
			if (end_x > clip_width) {
				end_x = clip_width;
			}
			if (start_x < 0) {
				start_x = 0;
			}
		}
		if (start_x >= end_x) {
			return;
		}
		if (aBoolean419) {
			buffer_offset += start_x;
			red += red_slope * start_x;
			green += green_slope * start_x;
			blue += blue_slope * start_x;
			if (smooth_edges) {
				y = end_x - start_x >> 2;
				red_slope *= 4F;
				green_slope *= 4F;
				blue_slope *= 4F;
				if (blending_alpha == 0) {
					if (y > 0) {
						do {
							background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
							red += red_slope;
							green += green_slope;
							blue += blue_slope;
							colour_buffer[buffer_offset++] = background;
							colour_buffer[buffer_offset++] = background;
							colour_buffer[buffer_offset++] = background;
							colour_buffer[buffer_offset++] = background;
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						do {
							colour_buffer[buffer_offset++] = background;
						} while (--y > 0);
					}
				} else if (!blending) {
					int src_alpha = blending_alpha;
					int dst_alpha = 256 - blending_alpha;
					if (y > 0) {
						do {
							background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
							red += red_slope;
							green += green_slope;
							blue += blue_slope;
							background = ((background & 0xff00ff) * dst_alpha >> 8 & 0xff00ff) + ((background & 0xff00) * dst_alpha >> 8 & 0xff00);
							int i8 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset++] = (dst_alpha | i8 >> 24) << 24 | background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
								i8 = colour_buffer[buffer_offset];
								colour_buffer[buffer_offset++] = (dst_alpha | i8 >> 24) << 24 | background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
								i8 = colour_buffer[buffer_offset];
								colour_buffer[buffer_offset++] = (dst_alpha | i8 >> 24) << 24 | background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
								i8 = colour_buffer[buffer_offset];
								colour_buffer[buffer_offset++] = (dst_alpha | i8 >> 24) << 24 | background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset++] = background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
								i8 = colour_buffer[buffer_offset];
								colour_buffer[buffer_offset++] = background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
								i8 = colour_buffer[buffer_offset];
								colour_buffer[buffer_offset++] = background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
								i8 = colour_buffer[buffer_offset];
								colour_buffer[buffer_offset++] = background + ((i8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i8 & 0xff00) * src_alpha >> 8 & 0xff00);
							}
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						background = ((background & 0xff00ff) * dst_alpha >> 8 & 0xff00ff) + ((background & 0xff00) * dst_alpha >> 8 & 0xff00);
						do {
							int j8 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset++] = (dst_alpha | j8 >> 24) << 24 | background + ((j8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((j8 & 0xff00) * src_alpha >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset++] = background + ((j8 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((j8 & 0xff00) * src_alpha >> 8 & 0xff00);
							}
						} while (--y > 0);
					}
				} else {
					if (y > 0) {
						do {
							background = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
							red += red_slope;
							green += green_slope;
							blue += blue_slope;
							int i3 = buffer_offset++;
							int k8 = background;
							int k15 = colour_buffer[i3];
							int j19 = k8 + k15;
							int i23 = (k8 & 0xff00ff) + (k15 & 0xff00ff);
							k15 = (i23 & 0x1000100) + (j19 - i23 & 0x10000);
							colour_buffer[i3] = 0xff000000 | j19 - k15 | k15 - (k15 >>> 8);
							j19 = buffer_offset++;
							i23 = background;
							int l26 = colour_buffer[j19];
							int i27 = i23 + l26;
							int j27 = (i23 & 0xff00ff) + (l26 & 0xff00ff);
							l26 = (j27 & 0x1000100) + (i27 - j27 & 0x10000);
							colour_buffer[j19] = 0xff000000 | i27 - l26 | l26 - (l26 >>> 8);
							i27 = buffer_offset++;
							j27 = background;
							int k27 = colour_buffer[i27];
							int l27 = j27 + k27;
							int i28 = (j27 & 0xff00ff) + (k27 & 0xff00ff);
							k27 = (i28 & 0x1000100) + (l27 - i28 & 0x10000);
							colour_buffer[i27] = 0xff000000 | l27 - k27 | k27 - (k27 >>> 8);
							l27 = buffer_offset++;
							i28 = background;
							int j28 = colour_buffer[l27];
							int k28 = i28 + j28;
							int l28 = (i28 & 0xff00ff) + (j28 & 0xff00ff);
							j28 = (l28 & 0x1000100) + (k28 - l28 & 0x10000);
							colour_buffer[l27] = 0xff000000 | k28 - j28 | j28 - (j28 >>> 8);
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						do {
							int j3 = buffer_offset++;
							int l15 = colour_buffer[j3];
							int k19 = background + l15;
							int j23 = (background & 0xff00ff) + (l15 & 0xff00ff);
							l15 = (j23 & 0x1000100) + (k19 - j23 & 0x10000);
							colour_buffer[j3] = 0xff000000 | k19 - l15 | l15 - (l15 >>> 8);
						} while (--y > 0);
					}
				}
			} else {
				y = end_x - start_x;
				if (blending_alpha == 0) {
					do {
						colour_buffer[buffer_offset++] = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						red += red_slope;
						green += green_slope;
						blue += blue_slope;
					} while (--y > 0);
				} else if (!blending) {
					int k1 = blending_alpha;
					int k3 = 256 - blending_alpha;
					do {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						red += red_slope;
						green += green_slope;
						blue += blue_slope;
						background = ((background & 0xff00ff) * k3 >> 8 & 0xff00ff) + ((background & 0xff00) * k3 >> 8 & 0xff00);
						int i9 = colour_buffer[buffer_offset];
						if (transparent) {
							colour_buffer[buffer_offset++] = (k3 | i9 >> 24) << 24 | background + ((i9 & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((i9 & 0xff00) * k1 >> 8 & 0xff00);
						} else {
							colour_buffer[buffer_offset++] = background + ((i9 & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((i9 & 0xff00) * k1 >> 8 & 0xff00);
						}
					} while (--y > 0);
				} else {
					do {
						int l3 = buffer_offset++;
						int j9 = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						int i16 = colour_buffer[l3];
						int l19 = j9 + i16;
						int k23 = (j9 & 0xff00ff) + (i16 & 0xff00ff);
						i16 = (k23 & 0x1000100) + (l19 - k23 & 0x10000);
						colour_buffer[l3] = 0xff000000 | l19 - i16 | i16 - (i16 >>> 8);
						red += red_slope;
						green += green_slope;
						blue += blue_slope;
					} while (--y > 0);
				}
			}
			return;
		}
		buffer_offset += start_x - 1;
		start_z += z_slope * start_x;
		red += red_slope * start_x;
		green += green_slope * start_x;
		blue += blue_slope * start_x;
		if (zbuffering) {
			if (smooth_edges) {
				y = end_x - start_x >> 2;
				red_slope *= 4F;
				green_slope *= 4F;
				blue_slope *= 4F;
				if (blending_alpha == 0) {
					if (y > 0) {
						do {
							background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
							red += red_slope;
							green += green_slope;
							blue += blue_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								colour_buffer[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								colour_buffer[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								colour_buffer[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								colour_buffer[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						do {
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								colour_buffer[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
						} while (--y > 0);
					}
				} else if (!blending) {
					int l1 = blending_alpha;
					int i4 = 256 - blending_alpha;
					if (y > 0) {
						do {
							background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
							red += red_slope;
							green += green_slope;
							blue += blue_slope;
							background = ((background & 0xff00ff) * i4 >> 8 & 0xff00ff) + ((background & 0xff00) * i4 >> 8 & 0xff00);
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int k9 = colour_buffer[buffer_offset];
								if (transparent) {
									colour_buffer[buffer_offset] = (i4 | k9 >> 24) << 24 | background + ((k9 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((k9 & 0xff00) * l1 >> 8 & 0xff00);
								} else {
									colour_buffer[buffer_offset] = background + ((k9 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((k9 & 0xff00) * l1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int l9 = colour_buffer[buffer_offset];
								if (transparent) {
									colour_buffer[buffer_offset] = (i4 | l9 >> 24) << 24 | background + ((l9 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((l9 & 0xff00) * l1 >> 8 & 0xff00);
								} else {
									colour_buffer[buffer_offset] = background + ((l9 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((l9 & 0xff00) * l1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int i10 = colour_buffer[buffer_offset];
								if (transparent) {
									colour_buffer[buffer_offset] = (i4 | i10 >> 24) << 24 | background + ((i10 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((i10 & 0xff00) * l1 >> 8 & 0xff00);
								} else {
									colour_buffer[buffer_offset] = background + ((i10 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((i10 & 0xff00) * l1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int j10 = colour_buffer[buffer_offset];
								if (transparent) {
									colour_buffer[buffer_offset] = (i4 | j10 >> 24) << 24 | background + ((j10 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((j10 & 0xff00) * l1 >> 8 & 0xff00);
								} else {
									colour_buffer[buffer_offset] = background + ((j10 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((j10 & 0xff00) * l1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						background = ((background & 0xff00ff) * i4 >> 8 & 0xff00ff) + ((background & 0xff00) * i4 >> 8 & 0xff00);
						do {
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int k10 = colour_buffer[buffer_offset];
								if (transparent) {
									colour_buffer[buffer_offset] = (i4 | k10 >> 24) << 24 | background + ((k10 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((k10 & 0xff00) * l1 >> 8 & 0xff00);
								} else {
									colour_buffer[buffer_offset] = background + ((k10 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((k10 & 0xff00) * l1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
						} while (--y > 0);
					}
				} else {
					if (y > 0) {
						do {
							background = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
							red += red_slope;
							green += green_slope;
							blue += blue_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int j16 = colour_buffer[buffer_offset];
								int i20 = background + j16;
								int l23 = (background & 0xff00ff) + (j16 & 0xff00ff);
								j16 = (l23 & 0x1000100) + (i20 - l23 & 0x10000);
								colour_buffer[buffer_offset] = 0xff000000 | i20 - j16 | j16 - (j16 >>> 8);
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int k16 = colour_buffer[buffer_offset];
								int j20 = background + k16;
								int i24 = (background & 0xff00ff) + (k16 & 0xff00ff);
								k16 = (i24 & 0x1000100) + (j20 - i24 & 0x10000);
								colour_buffer[buffer_offset] = 0xff000000 | j20 - k16 | k16 - (k16 >>> 8);
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int l16 = colour_buffer[buffer_offset];
								int k20 = background + l16;
								int j24 = (background & 0xff00ff) + (l16 & 0xff00ff);
								l16 = (j24 & 0x1000100) + (k20 - j24 & 0x10000);
								colour_buffer[buffer_offset] = 0xff000000 | k20 - l16 | l16 - (l16 >>> 8);
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int i17 = colour_buffer[buffer_offset];
								int l20 = background + i17;
								int k24 = (background & 0xff00ff) + (i17 & 0xff00ff);
								i17 = (k24 & 0x1000100) + (l20 - k24 & 0x10000);
								colour_buffer[buffer_offset] = 0xff000000 | l20 - i17 | i17 - (i17 >>> 8);
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						do {
							buffer_offset++;
							if (!depth_write || start_z < depth_buffer[buffer_offset]) {
								int j17 = colour_buffer[buffer_offset];
								int i21 = background + j17;
								int l24 = (background & 0xff00ff) + (j17 & 0xff00ff);
								j17 = (l24 & 0x1000100) + (i21 - l24 & 0x10000);
								colour_buffer[buffer_offset] = 0xff000000 | i21 - j17 | j17 - (j17 >>> 8);
								if (depth_write) {
									depth_buffer[buffer_offset] = start_z;
								}
							}
							start_z += z_slope;
						} while (--y > 0);
					}
				}
				return;
			}
			y = end_x - start_x;
			if (blending_alpha == 0) {
				do {
					buffer_offset++;
					if (!depth_write || start_z < depth_buffer[buffer_offset]) {
						colour_buffer[buffer_offset] = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						if (depth_write) {
							depth_buffer[buffer_offset] = start_z;
						}
					}
					start_z += z_slope;
					red += red_slope;
					green += green_slope;
					blue += blue_slope;
				} while (--y > 0);
			} else if (!blending) {
				int i2 = blending_alpha;
				int k5 = 256 - blending_alpha;
				do {
					buffer_offset++;
					if (!depth_write || start_z < depth_buffer[buffer_offset]) {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						background = ((background & 0xff00ff) * k5 >> 8 & 0xff00ff) + ((background & 0xff00) * k5 >> 8 & 0xff00);
						int i12 = colour_buffer[buffer_offset];
						if (transparent) {
							colour_buffer[buffer_offset] = (k5 | i12 >> 24) << 24 | background + ((i12 & 0xff00ff) * i2 >> 8 & 0xff00ff) + ((i12 & 0xff00) * i2 >> 8 & 0xff00);
						} else {
							colour_buffer[buffer_offset] = background + ((i12 & 0xff00ff) * i2 >> 8 & 0xff00ff) + ((i12 & 0xff00) * i2 >> 8 & 0xff00);
						}
						if (depth_write) {
							depth_buffer[buffer_offset] = start_z;
						}
					}
					start_z += z_slope;
					red += red_slope;
					green += green_slope;
					blue += blue_slope;
				} while (--y > 0);
			} else {
				do {
					buffer_offset++;
					if (!depth_write || start_z < depth_buffer[buffer_offset]) {
						int j12 = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						int k17 = colour_buffer[buffer_offset];
						int j21 = j12 + k17;
						int i25 = (j12 & 0xff00ff) + (k17 & 0xff00ff);
						k17 = (i25 & 0x1000100) + (j21 - i25 & 0x10000);
						colour_buffer[buffer_offset] = 0xff000000 | j21 - k17 | k17 - (k17 >>> 8);
						if (depth_write) {
							depth_buffer[buffer_offset] = start_z;
						}
					}
					start_z += z_slope;
					red += red_slope;
					green += green_slope;
					blue += blue_slope;
				} while (--y > 0);
			}
			return;
		}
		if (smooth_edges) {
			y = end_x - start_x >> 2;
			red_slope *= 4F;
			green_slope *= 4F;
			blue_slope *= 4F;
			if (blending_alpha == 0) {
				if (y > 0) {
					do {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						red += red_slope;
						green += green_slope;
						blue += blue_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							colour_buffer[buffer_offset] = background;
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							colour_buffer[buffer_offset] = background;
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							colour_buffer[buffer_offset] = background;
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							colour_buffer[buffer_offset] = background;
						}
						start_z += z_slope;
					} while (--y > 0);
				}
				y = end_x - start_x & 3;
				if (y > 0) {
					background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
					do {
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							colour_buffer[buffer_offset] = background;
						}
						start_z += z_slope;
					} while (--y > 0);
				}
			} else if (!blending) {
				int j2 = blending_alpha;
				int i6 = 256 - blending_alpha;
				if (y > 0) {
					do {
						background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						red += red_slope;
						green += green_slope;
						blue += blue_slope;
						background = ((background & 0xff00ff) * i6 >> 8 & 0xff00ff) + ((background & 0xff00) * i6 >> 8 & 0xff00);
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int k12 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset] = (i6 | k12 >> 24) << 24 | background + ((k12 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((k12 & 0xff00) * j2 >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset] = background + ((k12 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((k12 & 0xff00) * j2 >> 8 & 0xff00);
							}
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int l12 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset] = (i6 | l12 >> 24) << 24 | background + ((l12 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((l12 & 0xff00) * j2 >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset] = background + ((l12 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((l12 & 0xff00) * j2 >> 8 & 0xff00);
							}
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int i13 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset] = (i6 | i13 >> 24) << 24 | background + ((i13 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((i13 & 0xff00) * j2 >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset] = background + ((i13 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((i13 & 0xff00) * j2 >> 8 & 0xff00);
							}
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int j13 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset] = (i6 | j13 >> 24) << 24 | background + ((j13 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((j13 & 0xff00) * j2 >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset] = background + ((j13 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((j13 & 0xff00) * j2 >> 8 & 0xff00);
							}
						}
						start_z += z_slope;
					} while (--y > 0);
				}
				y = end_x - start_x & 3;
				if (y > 0) {
					background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
					background = ((background & 0xff00ff) * i6 >> 8 & 0xff00ff) + ((background & 0xff00) * i6 >> 8 & 0xff00);
					do {
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int k13 = colour_buffer[buffer_offset];
							if (transparent) {
								colour_buffer[buffer_offset] = (i6 | k13 >> 24) << 24 | background + ((k13 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((k13 & 0xff00) * j2 >> 8 & 0xff00);
							} else {
								colour_buffer[buffer_offset] = background + ((k13 & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((k13 & 0xff00) * j2 >> 8 & 0xff00);
							}
						}
						start_z += z_slope;
					} while (--y > 0);
				}
			} else {
				if (y > 0) {
					do {
						background = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
						red += red_slope;
						green += green_slope;
						blue += blue_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int l17 = colour_buffer[buffer_offset];
							int k21 = background + l17;
							int j25 = (background & 0xff00ff) + (l17 & 0xff00ff);
							l17 = (j25 & 0x1000100) + (k21 - j25 & 0x10000);
							colour_buffer[buffer_offset] = 0xff000000 | k21 - l17 | l17 - (l17 >>> 8);
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int i18 = colour_buffer[buffer_offset];
							int l21 = background + i18;
							int k25 = (background & 0xff00ff) + (i18 & 0xff00ff);
							i18 = (k25 & 0x1000100) + (l21 - k25 & 0x10000);
							colour_buffer[buffer_offset] = 0xff000000 | l21 - i18 | i18 - (i18 >>> 8);
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int j18 = colour_buffer[buffer_offset];
							int i22 = background + j18;
							int l25 = (background & 0xff00ff) + (j18 & 0xff00ff);
							j18 = (l25 & 0x1000100) + (i22 - l25 & 0x10000);
							colour_buffer[buffer_offset] = 0xff000000 | i22 - j18 | j18 - (j18 >>> 8);
						}
						start_z += z_slope;
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int k18 = colour_buffer[buffer_offset];
							int j22 = background + k18;
							int i26 = (background & 0xff00ff) + (k18 & 0xff00ff);
							k18 = (i26 & 0x1000100) + (j22 - i26 & 0x10000);
							colour_buffer[buffer_offset] = 0xff000000 | j22 - k18 | k18 - (k18 >>> 8);
						}
						start_z += z_slope;
					} while (--y > 0);
				}
				y = end_x - start_x & 3;
				if (y > 0) {
					background = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
					do {
						buffer_offset++;
						if (!depth_write || start_z < depth_buffer[buffer_offset]) {
							int l18 = colour_buffer[buffer_offset];
							int k22 = background + l18;
							int j26 = (background & 0xff00ff) + (l18 & 0xff00ff);
							l18 = (j26 & 0x1000100) + (k22 - j26 & 0x10000);
							colour_buffer[buffer_offset] = 0xff000000 | k22 - l18 | l18 - (l18 >>> 8);
						}
						start_z += z_slope;
					} while (--y > 0);
				}
			}
			return;
		}
		y = end_x - start_x;
		if (blending_alpha == 0) {
			do {
				buffer_offset++;
				if (!depth_write || start_z < depth_buffer[buffer_offset]) {
					colour_buffer[buffer_offset] = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
				}
				start_z += z_slope;
				red += red_slope;
				green += green_slope;
				blue += blue_slope;
			} while (--y > 0);
		} else if (!blending) {
			int k2 = blending_alpha;
			int k7 = 256 - blending_alpha;
			do {
				buffer_offset++;
				if (!depth_write || start_z < depth_buffer[buffer_offset]) {
					background = 0xff000000 | (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
					background = ((background & 0xff00ff) * k7 >> 8 & 0xff00ff) + ((background & 0xff00) * k7 >> 8 & 0xff00);
					int i15 = colour_buffer[buffer_offset];
					if (transparent) {
						colour_buffer[buffer_offset] = (k7 | i15 >> 24) << 24 | background + ((i15 & 0xff00ff) * k2 >> 8 & 0xff00ff) + ((i15 & 0xff00) * k2 >> 8 & 0xff00);
					} else {
						colour_buffer[buffer_offset] = background + ((i15 & 0xff00ff) * k2 >> 8 & 0xff00ff) + ((i15 & 0xff00) * k2 >> 8 & 0xff00);
					}
				}
				start_z += z_slope;
				red += red_slope;
				green += green_slope;
				blue += blue_slope;
			} while (--y > 0);
		} else {
			do {
				buffer_offset++;
				if (!depth_write || start_z < depth_buffer[buffer_offset]) {
					int j15 = (int) red & 0xff0000 | (int) green & 0xff00 | (int) blue & 0xff;
					int i19 = colour_buffer[buffer_offset];
					int l22 = j15 + i19;
					int k26 = (j15 & 0xff00ff) + (i19 & 0xff00ff);
					i19 = (k26 & 0x1000100) + (l22 - k26 & 0x10000);
					colour_buffer[buffer_offset] = 0xff000000 | l22 - i19 | i19 - (i19 >>> 8);
				}
				start_z += z_slope;
				red += red_slope;
				green += green_slope;
				blue += blue_slope;
			} while (--y > 0);
		}
	}

	public void draw_flat_triangle(boolean depth_write, boolean colour_write, boolean transparent, float y_a, float y_b, float y_c, float x_a, float x_b, float x_c, float z_a, float z_b, float z_c, int colour) {
		// if (wireframe_mode) {
		// master.line((int) x_a, (int) y_a, (int) x_b, (int) y_b, colour);
		// master.line((int) x_b, (int) y_b, (int) x_c, (int) y_c, colour);
		// master.line((int) x_c, (int) y_c, (int) x_a, (int) y_a, colour);
		// return;
		// }
		float x21 = x_b - x_a;
		float y21 = y_b - y_a;
		float x31 = x_c - x_a;
		float y31 = y_c - y_a;
		float z21 = z_b - z_a;
		float z31 = z_c - z_a;
		float f15 = 0.0F;
		if (y_b != y_a) {
			f15 = (x_b - x_a) / (y_b - y_a);
		}
		float f16 = 0.0F;
		if (y_c != y_b) {
			f16 = (x_c - x_b) / (y_c - y_b);
		}
		float f17 = 0.0F;
		if (y_c != y_a) {
			f17 = (x_a - x_c) / (y_a - y_c);
		}
		float f18 = x21 * y31 - x31 * y21;
		if (f18 == 0.0F) {
			return;
		}
		float f19 = (z21 * y31 - z31 * y21) / f18;
		float f20 = (z31 * x21 - z21 * x31) / f18;
		if (y_a <= y_b && y_a <= y_c) {
			if (y_a >= clip_height) {
				return;
			}
			if (y_b > clip_height) {
				y_b = clip_height;
			}
			if (y_c > clip_height) {
				y_c = clip_height;
			}
			z_a = z_a - f19 * x_a + f19;
			if (y_b < y_c) {
				x_c = x_a;
				if (y_a < 0.0F) {
					x_c -= f17 * y_a;
					x_a -= f15 * y_a;
					z_a -= f20 * y_a;
					y_a = 0.0F;
				}
				if (y_b < 0.0F) {
					x_b -= f16 * y_b;
					y_b = 0.0F;
				}
				if (y_a != y_b && f17 < f15 || y_a == y_b && f17 > f16) {
					y_a = (int) (y_a + 0.5F);
					y_b = (int) (y_b + 0.5F);
					y_c = (int) (y_c + 0.5F) - y_b;
					y_b -= y_a;
					for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
						draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_c, (int) x_a, z_a, f19);
						x_c += f17;
						x_a += f15;
						z_a += f20;
					}

					while (--y_c >= 0.0F) {
						draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_c, (int) x_b, z_a, f19);
						x_c += f17;
						x_b += f16;
						z_a += f20;
						y_a += scanline_width;
					}
					return;
				}
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_b;
				y_b -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_a, (int) x_c, z_a, f19);
					x_c += f17;
					x_a += f15;
					z_a += f20;
				}

				while (--y_c >= 0.0F) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_b, (int) x_c, z_a, f19);
					x_c += f17;
					x_b += f16;
					z_a += f20;
					y_a += scanline_width;
				}
				return;
			}
			x_b = x_a;
			if (y_a < 0.0F) {
				x_b -= f17 * y_a;
				x_a -= f15 * y_a;
				z_a -= f20 * y_a;
				y_a = 0.0F;
			}
			if (y_c < 0.0F) {
				x_c -= f16 * y_c;
				y_c = 0.0F;
			}
			if (y_a != y_c && f17 < f15 || y_a == y_c && f16 > f15) {
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_c;
				y_c -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_b, (int) x_a, z_a, f19);
					x_b += f17;
					x_a += f15;
					z_a += f20;
				}

				while (--y_b >= 0.0F) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_c, (int) x_a, z_a, f19);
					x_c += f16;
					x_a += f15;
					z_a += f20;
					y_a += scanline_width;
				}
				return;
			}
			y_a = (int) (y_a + 0.5F);
			y_c = (int) (y_c + 0.5F);
			y_b = (int) (y_b + 0.5F) - y_c;
			y_c -= y_a;
			for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_a, (int) x_b, z_a, f19);
				x_b += f17;
				x_a += f15;
				z_a += f20;
			}

			while (--y_b >= 0.0F) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_a, colour, 0, (int) x_a, (int) x_c, z_a, f19);
				x_c += f16;
				x_a += f15;
				z_a += f20;
				y_a += scanline_width;
			}
			return;
		}
		if (y_b <= y_c) {
			if (y_b >= clip_height) {
				return;
			}
			if (y_c > clip_height) {
				y_c = clip_height;
			}
			if (y_a > clip_height) {
				y_a = clip_height;
			}
			z_b = z_b - f19 * x_b + f19;
			if (y_c < y_a) {
				x_a = x_b;
				if (y_b < 0.0F) {
					x_a -= f15 * y_b;
					x_b -= f16 * y_b;
					z_b -= f20 * y_b;
					y_b = 0.0F;
				}
				if (y_c < 0.0F) {
					x_c -= f17 * y_c;
					y_c = 0.0F;
				}
				if (y_b != y_c && f15 < f16 || y_b == y_c && f15 > f17) {
					y_b = (int) (y_b + 0.5F);
					y_c = (int) (y_c + 0.5F);
					y_a = (int) (y_a + 0.5F) - y_c;
					y_c -= y_b;
					for (y_b = scanline_offsets[(int) y_b]; --y_c >= 0.0F; y_b += scanline_width) {
						draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_a, (int) x_b, z_b, f19);
						x_a += f15;
						x_b += f16;
						z_b += f20;
					}

					while (--y_a >= 0.0F) {
						draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_a, (int) x_c, z_b, f19);
						x_a += f15;
						x_c += f17;
						z_b += f20;
						y_b += scanline_width;
					}
					return;
				}
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_a = (int) (y_a + 0.5F) - y_c;
				y_c -= y_b;
				for (y_b = scanline_offsets[(int) y_b]; --y_c >= 0.0F; y_b += scanline_width) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_b, (int) x_a, z_b, f19);
					x_a += f15;
					x_b += f16;
					z_b += f20;
				}

				while (--y_a >= 0.0F) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_c, (int) x_a, z_b, f19);
					x_a += f15;
					x_c += f17;
					z_b += f20;
					y_b += scanline_width;
				}
				return;
			}
			x_c = x_b;
			if (y_b < 0.0F) {
				x_c -= f15 * y_b;
				x_b -= f16 * y_b;
				z_b -= f20 * y_b;
				y_b = 0.0F;
			}
			if (y_a < 0.0F) {
				x_a -= f17 * y_a;
				y_a = 0.0F;
			}
			if (f15 < f16) {
				y_b = (int) (y_b + 0.5F);
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_a;
				y_a -= y_b;
				for (y_b = scanline_offsets[(int) y_b]; --y_a >= 0.0F; y_b += scanline_width) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_c, (int) x_b, z_b, f19);
					x_c += f15;
					x_b += f16;
					z_b += f20;
				}

				while (--y_c >= 0.0F) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_a, (int) x_b, z_b, f19);
					x_a += f17;
					x_b += f16;
					z_b += f20;
					y_b += scanline_width;
				}
				return;
			}
			y_b = (int) (y_b + 0.5F);
			y_a = (int) (y_a + 0.5F);
			y_c = (int) (y_c + 0.5F) - y_a;
			y_a -= y_b;
			for (y_b = scanline_offsets[(int) y_b]; --y_a >= 0.0F; y_b += scanline_width) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_b, (int) x_c, z_b, f19);
				x_c += f15;
				x_b += f16;
				z_b += f20;
			}

			while (--y_c >= 0.0F) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_b, colour, 0, (int) x_b, (int) x_a, z_b, f19);
				x_a += f17;
				x_b += f16;
				z_b += f20;
				y_b += scanline_width;
			}
			return;
		}
		if (y_c >= clip_height) {
			return;
		}
		if (y_a > clip_height) {
			y_a = clip_height;
		}
		if (y_b > clip_height) {
			y_b = clip_height;
		}
		z_c = z_c - f19 * x_c + f19;
		if (y_a < y_b) {
			x_b = x_c;
			if (y_c < 0.0F) {
				x_b -= f16 * y_c;
				x_c -= f17 * y_c;
				z_c -= f20 * y_c;
				y_c = 0.0F;
			}
			if (y_a < 0.0F) {
				x_a -= f15 * y_a;
				y_a = 0.0F;
			}
			if (f16 < f17) {
				y_c = (int) (y_c + 0.5F);
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_a;
				y_a -= y_c;
				for (y_c = scanline_offsets[(int) y_c]; --y_a >= 0.0F; y_c += scanline_width) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_b, (int) x_c, z_c, f19);
					x_b += f16;
					x_c += f17;
					z_c += f20;
				}

				while (--y_b >= 0.0F) {
					draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_b, (int) x_a, z_c, f19);
					x_b += f16;
					x_a += f15;
					z_c += f20;
					y_c += scanline_width;
				}
				return;
			}
			y_c = (int) (y_c + 0.5F);
			y_a = (int) (y_a + 0.5F);
			y_b = (int) (y_b + 0.5F) - y_a;
			y_a -= y_c;
			for (y_c = scanline_offsets[(int) y_c]; --y_a >= 0.0F; y_c += scanline_width) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_c, (int) x_b, z_c, f19);
				x_b += f16;
				x_c += f17;
				z_c += f20;
			}

			while (--y_b >= 0.0F) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_a, (int) x_b, z_c, f19);
				x_b += f16;
				x_a += f15;
				z_c += f20;
				y_c += scanline_width;
			}
			return;
		}
		x_a = x_c;
		if (y_c < 0.0F) {
			x_a -= f16 * y_c;
			x_c -= f17 * y_c;
			z_c -= f20 * y_c;
			y_c = 0.0F;
		}
		if (y_b < 0.0F) {
			x_b -= f15 * y_b;
			y_b = 0.0F;
		}
		if (f16 < f17) {
			y_c = (int) (y_c + 0.5F);
			y_b = (int) (y_b + 0.5F);
			y_a = (int) (y_a + 0.5F) - y_b;
			y_b -= y_c;
			for (y_c = scanline_offsets[(int) y_c]; --y_b >= 0.0F; y_c += scanline_width) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_a, (int) x_c, z_c, f19);
				x_a += f16;
				x_c += f17;
				z_c += f20;
			}

			while (--y_a >= 0.0F) {
				draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_b, (int) x_c, z_c, f19);
				x_b += f15;
				x_c += f17;
				z_c += f20;
				y_c += scanline_width;
			}
			return;
		}
		y_c = (int) (y_c + 0.5F);
		y_b = (int) (y_b + 0.5F);
		y_a = (int) (y_a + 0.5F) - y_b;
		y_b -= y_c;
		for (y_c = scanline_offsets[(int) y_c]; --y_b >= 0.0F; y_c += scanline_width) {
			draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_c, (int) x_a, z_c, f19);
			x_a += f16;
			x_c += f17;
			z_c += f20;
		}

		while (--y_a >= 0.0F) {
			draw_flat_scanline(depth_write, colour_write, transparent, colour_buffer, (int) y_c, colour, 0, (int) x_c, (int) x_b, z_c, f19);
			x_b += f15;
			x_c += f17;
			z_c += f20;
			y_c += scanline_width;
		}
	}

	public void draw_flat_scanline(boolean colour_write, boolean depth_write, boolean transparent, int[] pixels, int index, int colour, int num_loops, int start_x, int end_x, float depth, float depth_slope) {
		if (clip_edges) {
			if (end_x > clip_width) {
				end_x = clip_width;
			}
			if (start_x < 0) {
				start_x = 0;
			}
		}
		if (start_x >= end_x) {
			return;
		}
		index += start_x - 1;
		num_loops = end_x - start_x >> 2;
		depth += depth_slope * start_x;
		if (zbuffering) {
			if (blending_alpha == 0) {
				for (; --num_loops >= 0; depth += depth_slope) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							pixels[index] = colour;
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							pixels[index] = colour;
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							pixels[index] = colour;
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
					index++;
					if (depth_write && depth >= depth_buffer[index]) {
						continue;
					}
					if (colour_write) {
						pixels[index] = colour;
					}
					if (depth_write) {
						depth_buffer[index] = depth;
					}
				}

				for (num_loops = end_x - start_x & 3; --num_loops >= 0;) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							pixels[index] = colour;
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
				}

			} else if (blending_alpha == 254) {
				if (start_x == 0 || end_x > clip_width - 1) {
					return;
				}
				while (--num_loops >= 0) {
					index++;
					if ((!depth_write || depth < depth_buffer[index]) && colour_write) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
					index++;
					if ((!depth_write || depth < depth_buffer[index]) && colour_write) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
					index++;
					if ((!depth_write || depth < depth_buffer[index]) && colour_write) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
					index++;
					if ((!depth_write || depth < depth_buffer[index]) && colour_write) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
				}
				for (num_loops = end_x - start_x & 3; --num_loops >= 0;) {
					index++;
					if ((!depth_write || depth < depth_buffer[index]) && colour_write) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
				}

			} else {
				int src_alpha = blending_alpha;
				int dst_alpha = 256 - blending_alpha;
				colour = ((colour & 0xff00ff) * dst_alpha >> 8 & 0xff00ff) + ((colour & 0xff00) * dst_alpha >> 8 & 0xff00);
				for (; --num_loops >= 0; depth += depth_slope) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							int src_colour = pixels[index];
							if (transparent) {
								pixels[index] = (dst_alpha | src_colour >> 24) << 24 | colour + ((src_colour & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((src_colour & 0xff00) * src_alpha >> 8 & 0xff00);
							} else {
								pixels[index] = colour + ((src_colour & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((src_colour & 0xff00) * src_alpha >> 8 & 0xff00);
							}
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							int k2 = pixels[index];
							if (transparent) {
								pixels[index] = (dst_alpha | k2 >> 24) << 24 | colour + ((k2 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((k2 & 0xff00) * src_alpha >> 8 & 0xff00);
							} else {
								pixels[index] = colour + ((k2 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((k2 & 0xff00) * src_alpha >> 8 & 0xff00);
							}
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							int l2 = pixels[index];
							if (transparent) {
								pixels[index] = (dst_alpha | l2 >> 24) << 24 | colour + ((l2 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((l2 & 0xff00) * src_alpha >> 8 & 0xff00);
							} else {
								pixels[index] = colour + ((l2 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((l2 & 0xff00) * src_alpha >> 8 & 0xff00);
							}
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
					index++;
					if (depth_write && depth >= depth_buffer[index]) {
						continue;
					}
					if (colour_write) {
						int i3 = pixels[index];
						if (transparent) {
							pixels[index] = (dst_alpha | i3 >> 24) << 24 | colour + ((i3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i3 & 0xff00) * src_alpha >> 8 & 0xff00);
						} else {
							pixels[index] = colour + ((i3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i3 & 0xff00) * src_alpha >> 8 & 0xff00);
						}
					}
					if (depth_write) {
						depth_buffer[index] = depth;
					}
				}

				for (num_loops = end_x - start_x & 3; --num_loops >= 0;) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						if (colour_write) {
							int j3 = pixels[index];
							if (transparent) {
								pixels[index] = (dst_alpha | j3 >> 24) << 24 | colour + ((j3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((j3 & 0xff00) * src_alpha >> 8 & 0xff00);
							} else {
								pixels[index] = colour + ((j3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((j3 & 0xff00) * src_alpha >> 8 & 0xff00);
							}
						}
						if (depth_write) {
							depth_buffer[index] = depth;
						}
					}
					depth += depth_slope;
				}

			}
		} else if (colour_write) {
			if (blending_alpha == 0) {
				while (--num_loops >= 0) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index] = colour;
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index] = colour;
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index] = colour;
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index] = colour;
					}
					depth += depth_slope;
				}
				for (num_loops = end_x - start_x & 3; --num_loops >= 0;) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index] = colour;
					}
					depth += depth_slope;
				}

			} else if (blending_alpha == 254) {
				if (start_x == 0 || end_x > clip_width - 1) {
					return;
				}
				while (--num_loops >= 0) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
				}
				for (num_loops = end_x - start_x & 3; --num_loops >= 0;) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						pixels[index - 1] = pixels[index];
					}
					depth += depth_slope;
				}

			} else {
				int src_alpha = blending_alpha;
				int dst_alpha = 256 - blending_alpha;
				colour = ((colour & 0xff00ff) * dst_alpha >> 8 & 0xff00ff) + ((colour & 0xff00) * dst_alpha >> 8 & 0xff00);
				for (; --num_loops >= 0; depth += depth_slope) {
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						int k3 = pixels[index];
						if (transparent) {
							pixels[index] = (dst_alpha | k3 >> 24) << 24 | colour + ((k3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((k3 & 0xff00) * src_alpha >> 8 & 0xff00);
						} else {
							pixels[index] = colour + ((k3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((k3 & 0xff00) * src_alpha >> 8 & 0xff00);
						}
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						int l3 = pixels[index];
						if (transparent) {
							pixels[index] = (dst_alpha | l3 >> 24) << 24 | colour + ((l3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((l3 & 0xff00) * src_alpha >> 8 & 0xff00);
						} else {
							pixels[index] = colour + ((l3 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((l3 & 0xff00) * src_alpha >> 8 & 0xff00);
						}
					}
					depth += depth_slope;
					index++;
					if (!depth_write || depth < depth_buffer[index]) {
						int i4 = pixels[index];
						if (transparent) {
							pixels[index] = (dst_alpha | i4 >> 24) << 24 | colour + ((i4 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i4 & 0xff00) * src_alpha >> 8 & 0xff00);
						} else {
							pixels[index] = colour + ((i4 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i4 & 0xff00) * src_alpha >> 8 & 0xff00);
						}
					}
					depth += depth_slope;
					index++;
					if (depth_write && depth >= depth_buffer[index]) {
						continue;
					}
					int j4 = pixels[index];
					if (transparent) {
						pixels[index] = (dst_alpha | j4 >> 24) << 24 | colour + ((j4 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((j4 & 0xff00) * src_alpha >> 8 & 0xff00);
					} else {
						pixels[index] = colour + ((j4 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((j4 & 0xff00) * src_alpha >> 8 & 0xff00);
					}
				}

				for (num_loops = end_x - start_x & 3; --num_loops >= 0; depth += depth_slope) {
					index++;
					if (depth_write && depth >= depth_buffer[index]) {
						continue;
					}
					int k4 = pixels[index];
					if (transparent) {
						pixels[index] = (dst_alpha | k4 >> 24) << 24 | colour + ((k4 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((k4 & 0xff00) * src_alpha >> 8 & 0xff00);
					} else {
						pixels[index] = colour + ((k4 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((k4 & 0xff00) * src_alpha >> 8 & 0xff00);
					}
				}

			}
		}
	}

	public void draw_shaded_triangle(boolean colour_write, boolean depth_write, boolean transparent, float y_a, float y_b, float y_c, float x_a, float x_b, float x_c, float z_a, float z_b, float z_c, float colour_a, float colour_b, float colour_c) {
		if (!colour_write) {
			draw_flat_triangle(false, depth_write, transparent, y_a, y_b, y_c, x_a, x_b, x_c, z_a, z_b, z_c, 0);
			return;
		}
		// if (wireframe_mode) {
		// master.line((int) x_a, (int) y_a, (int) x_b, (int) y_b, ColourUtil.hsvToRgbTable[(int) colour_a & 0xffff]);
		// master.line((int) x_b, (int) y_b, (int) x_c, (int) y_c, ColourUtil.hsvToRgbTable[(int) colour_a & 0xffff]);
		// master.line((int) x_c, (int) y_c, (int) x_a, (int) y_a, ColourUtil.hsvToRgbTable[(int) colour_a & 0xffff]);
		// return;
		// }
		float x_ba = x_b - x_a;
		float y_ba_ = y_b - y_a;
		float x_ca = x_c - x_a;
		float y_ca = y_c - y_a;
		float colour_ba = colour_b - colour_a;
		float colour_ca = colour_c - colour_a;
		float z_ba = z_b - z_a;
		float z_ca = z_c - z_a;
		float cb_slope;
		if (y_c != y_b) {
			cb_slope = (x_c - x_b) / (y_c - y_b);
		} else {
			cb_slope = 0.0F;
		}
		float ba_slope;
		if (y_b != y_a) {
			ba_slope = x_ba / y_ba_;
		} else {
			ba_slope = 0.0F;
		}
		float ca_slope;
		if (y_c != y_a) {
			ca_slope = x_ca / y_ca;
		} else {
			ca_slope = 0.0F;
		}
		float slope_size = x_ba * y_ca - x_ca * y_ba_;
		if (slope_size == 0.0F) {
			return;
		}
		float colour_ba_slope = (colour_ba * y_ca - colour_ca * y_ba_) / slope_size;
		float colour_ca_slope = (colour_ca * x_ba - colour_ba * x_ca) / slope_size;
		float z_ba_slope = (z_ba * y_ca - z_ca * y_ba_) / slope_size;
		float z_ca_slope = (z_ca * x_ba - z_ba * x_ca) / slope_size;
		if (y_a <= y_b && y_a <= y_c) {
			if (y_a >= clip_height) {
				return;
			}
			if (y_b > clip_height) {
				y_b = clip_height;
			}
			if (y_c > clip_height) {
				y_c = clip_height;
			}
			colour_a = colour_a - colour_ba_slope * x_a + colour_ba_slope;
			z_a = z_a - z_ba_slope * x_a + z_ba_slope;
			if (y_b < y_c) {
				x_c = x_a;
				if (y_a < 0.0F) {
					x_c -= ca_slope * y_a;
					x_a -= ba_slope * y_a;
					colour_a -= colour_ca_slope * y_a;
					z_a -= z_ca_slope * y_a;
					y_a = 0.0F;
				}
				if (y_b < 0.0F) {
					x_b -= cb_slope * y_b;
					y_b = 0.0F;
				}
				if (y_a != y_b && ca_slope < ba_slope || y_a == y_b && ca_slope > cb_slope) {
					y_a = (int) (y_a + 0.5F);
					y_b = (int) (y_b + 0.5F);
					y_c = (int) (y_c + 0.5F) - y_b;
					y_b -= y_a;
					for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
						draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_c, (int) x_a, colour_a, colour_ba_slope, z_a, z_ba_slope);
						x_c += ca_slope;
						x_a += ba_slope;
						colour_a += colour_ca_slope;
						z_a += z_ca_slope;
					}

					while (--y_c >= 0.0F) {
						draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_c, (int) x_b, colour_a, colour_ba_slope, z_a, z_ba_slope);
						x_c += ca_slope;
						x_b += cb_slope;
						colour_a += colour_ca_slope;
						z_a += z_ca_slope;
						y_a += scanline_width;
					}
					return;
				}
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_b;
				y_b -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_a, (int) x_c, colour_a, colour_ba_slope, z_a, z_ba_slope);
					x_c += ca_slope;
					x_a += ba_slope;
					colour_a += colour_ca_slope;
					z_a += z_ca_slope;
				}

				while (--y_c >= 0.0F) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_b, (int) x_c, colour_a, colour_ba_slope, z_a, z_ba_slope);
					x_c += ca_slope;
					x_b += cb_slope;
					colour_a += colour_ca_slope;
					z_a += z_ca_slope;
					y_a += scanline_width;
				}
				return;
			}
			x_b = x_a;
			if (y_a < 0.0F) {
				x_b -= ca_slope * y_a;
				x_a -= ba_slope * y_a;
				colour_a -= colour_ca_slope * y_a;
				z_a -= z_ca_slope * y_a;
				y_a = 0.0F;
			}
			if (y_c < 0.0F) {
				x_c -= cb_slope * y_c;
				y_c = 0.0F;
			}
			if (y_a != y_c && ca_slope < ba_slope || y_a == y_c && cb_slope > ba_slope) {
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_c;
				y_c -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_b, (int) x_a, colour_a, colour_ba_slope, z_a, z_ba_slope);
					x_b += ca_slope;
					x_a += ba_slope;
					colour_a += colour_ca_slope;
					z_a += z_ca_slope;
				}

				while (--y_b >= 0.0F) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_c, (int) x_a, colour_a, colour_ba_slope, z_a, z_ba_slope);
					x_c += cb_slope;
					x_a += ba_slope;
					colour_a += colour_ca_slope;
					z_a += z_ca_slope;
					y_a += scanline_width;
				}
				return;
			}
			y_a = (int) (y_a + 0.5F);
			y_c = (int) (y_c + 0.5F);
			y_b = (int) (y_b + 0.5F) - y_c;
			y_c -= y_a;
			for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_a, (int) x_b, colour_a, colour_ba_slope, z_a, z_ba_slope);
				x_b += ca_slope;
				x_a += ba_slope;
				colour_a += colour_ca_slope;
				z_a += z_ca_slope;
			}

			while (--y_b >= 0.0F) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_a, 0, 0, (int) x_a, (int) x_c, colour_a, colour_ba_slope, z_a, z_ba_slope);
				x_c += cb_slope;
				x_a += ba_slope;
				colour_a += colour_ca_slope;
				z_a += z_ca_slope;
				y_a += scanline_width;
			}
			return;
		}
		if (y_b <= y_c) {
			if (y_b >= clip_height) {
				return;
			}
			if (y_c > clip_height) {
				y_c = clip_height;
			}
			if (y_a > clip_height) {
				y_a = clip_height;
			}
			colour_b = colour_b - colour_ba_slope * x_b + colour_ba_slope;
			z_b = z_b - z_ba_slope * x_b + z_ba_slope;
			if (y_c < y_a) {
				x_a = x_b;
				if (y_b < 0.0F) {
					x_a -= ba_slope * y_b;
					x_b -= cb_slope * y_b;
					colour_b -= colour_ca_slope * y_b;
					z_b -= z_ca_slope * y_b;
					y_b = 0.0F;
				}
				if (y_c < 0.0F) {
					x_c -= ca_slope * y_c;
					y_c = 0.0F;
				}
				if (y_b != y_c && ba_slope < cb_slope || y_b == y_c && ba_slope > ca_slope) {
					y_b = (int) (y_b + 0.5F);
					y_c = (int) (y_c + 0.5F);
					y_a = (int) (y_a + 0.5F) - y_c;
					y_c -= y_b;
					for (y_b = scanline_offsets[(int) y_b]; --y_c >= 0.0F; y_b += scanline_width) {
						draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_a, (int) x_b, colour_b, colour_ba_slope, z_b, z_ba_slope);
						x_a += ba_slope;
						x_b += cb_slope;
						colour_b += colour_ca_slope;
						z_b += z_ca_slope;
					}

					while (--y_a >= 0.0F) {
						draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_a, (int) x_c, colour_b, colour_ba_slope, z_b, z_ba_slope);
						x_a += ba_slope;
						x_c += ca_slope;
						colour_b += colour_ca_slope;
						z_b += z_ca_slope;
						y_b += scanline_width;
					}
					return;
				}
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_a = (int) (y_a + 0.5F) - y_c;
				y_c -= y_b;
				for (y_b = scanline_offsets[(int) y_b]; --y_c >= 0.0F; y_b += scanline_width) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_b, (int) x_a, colour_b, colour_ba_slope, z_b, z_ba_slope);
					x_a += ba_slope;
					x_b += cb_slope;
					colour_b += colour_ca_slope;
					z_b += z_ca_slope;
				}

				while (--y_a >= 0.0F) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_c, (int) x_a, colour_b, colour_ba_slope, z_b, z_ba_slope);
					x_a += ba_slope;
					x_c += ca_slope;
					colour_b += colour_ca_slope;
					z_b += z_ca_slope;
					y_b += scanline_width;
				}
				return;
			}
			x_c = x_b;
			if (y_b < 0.0F) {
				x_c -= ba_slope * y_b;
				x_b -= cb_slope * y_b;
				colour_b -= colour_ca_slope * y_b;
				z_b -= z_ca_slope * y_b;
				y_b = 0.0F;
			}
			if (y_a < 0.0F) {
				x_a -= ca_slope * y_a;
				y_a = 0.0F;
			}
			if (ba_slope < cb_slope) {
				y_b = (int) (y_b + 0.5F);
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_a;
				y_a -= y_b;
				for (y_b = scanline_offsets[(int) y_b]; --y_a >= 0.0F; y_b += scanline_width) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_c, (int) x_b, colour_b, colour_ba_slope, z_b, z_ba_slope);
					x_c += ba_slope;
					x_b += cb_slope;
					colour_b += colour_ca_slope;
					z_b += z_ca_slope;
				}

				while (--y_c >= 0.0F) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_a, (int) x_b, colour_b, colour_ba_slope, z_b, z_ba_slope);
					x_a += ca_slope;
					x_b += cb_slope;
					colour_b += colour_ca_slope;
					z_b += z_ca_slope;
					y_b += scanline_width;
				}
				return;
			}
			y_b = (int) (y_b + 0.5F);
			y_a = (int) (y_a + 0.5F);
			y_c = (int) (y_c + 0.5F) - y_a;
			y_a -= y_b;
			for (y_b = scanline_offsets[(int) y_b]; --y_a >= 0.0F; y_b += scanline_width) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_b, (int) x_c, colour_b, colour_ba_slope, z_b, z_ba_slope);
				x_c += ba_slope;
				x_b += cb_slope;
				colour_b += colour_ca_slope;
				z_b += z_ca_slope;
			}

			while (--y_c >= 0.0F) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_b, 0, 0, (int) x_b, (int) x_a, colour_b, colour_ba_slope, z_b, z_ba_slope);
				x_a += ca_slope;
				x_b += cb_slope;
				colour_b += colour_ca_slope;
				z_b += z_ca_slope;
				y_b += scanline_width;
			}
			return;
		}
		if (y_c >= clip_height) {
			return;
		}
		if (y_a > clip_height) {
			y_a = clip_height;
		}
		if (y_b > clip_height) {
			y_b = clip_height;
		}
		colour_c = colour_c - colour_ba_slope * x_c + colour_ba_slope;
		z_c = z_c - z_ba_slope * x_c + z_ba_slope;
		if (y_a < y_b) {
			x_b = x_c;
			if (y_c < 0.0F) {
				x_b -= cb_slope * y_c;
				x_c -= ca_slope * y_c;
				colour_c -= colour_ca_slope * y_c;
				z_c -= z_ca_slope * y_c;
				y_c = 0.0F;
			}
			if (y_a < 0.0F) {
				x_a -= ba_slope * y_a;
				y_a = 0.0F;
			}
			if (cb_slope < ca_slope) {
				y_c = (int) (y_c + 0.5F);
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_a;
				y_a -= y_c;
				for (y_c = scanline_offsets[(int) y_c]; --y_a >= 0.0F; y_c += scanline_width) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_b, (int) x_c, colour_c, colour_ba_slope, z_c, z_ba_slope);
					x_b += cb_slope;
					x_c += ca_slope;
					colour_c += colour_ca_slope;
					z_c += z_ca_slope;
				}

				while (--y_b >= 0.0F) {
					draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_b, (int) x_a, colour_c, colour_ba_slope, z_c, z_ba_slope);
					x_b += cb_slope;
					x_a += ba_slope;
					colour_c += colour_ca_slope;
					z_c += z_ca_slope;
					y_c += scanline_width;
				}
				return;
			}
			y_c = (int) (y_c + 0.5F);
			y_a = (int) (y_a + 0.5F);
			y_b = (int) (y_b + 0.5F) - y_a;
			y_a -= y_c;
			for (y_c = scanline_offsets[(int) y_c]; --y_a >= 0.0F; y_c += scanline_width) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_c, (int) x_b, colour_c, colour_ba_slope, z_c, z_ba_slope);
				x_b += cb_slope;
				x_c += ca_slope;
				colour_c += colour_ca_slope;
				z_c += z_ca_slope;
			}

			while (--y_b >= 0.0F) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_a, (int) x_b, colour_c, colour_ba_slope, z_c, z_ba_slope);
				x_b += cb_slope;
				x_a += ba_slope;
				colour_c += colour_ca_slope;
				z_c += z_ca_slope;
				y_c += scanline_width;
			}
			return;
		}
		x_a = x_c;
		if (y_c < 0.0F) {
			x_a -= cb_slope * y_c;
			x_c -= ca_slope * y_c;
			colour_c -= colour_ca_slope * y_c;
			z_c -= z_ca_slope * y_c;
			y_c = 0.0F;
		}
		if (y_b < 0.0F) {
			x_b -= ba_slope * y_b;
			y_b = 0.0F;
		}
		if (cb_slope < ca_slope) {
			y_c = (int) (y_c + 0.5F);
			y_b = (int) (y_b + 0.5F);
			y_a = (int) (y_a + 0.5F) - y_b;
			y_b -= y_c;
			for (y_c = scanline_offsets[(int) y_c]; --y_b >= 0.0F; y_c += scanline_width) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_a, (int) x_c, colour_c, colour_ba_slope, z_c, z_ba_slope);
				x_a += cb_slope;
				x_c += ca_slope;
				colour_c += colour_ca_slope;
				z_c += z_ca_slope;
			}

			while (--y_a >= 0.0F) {
				draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_b, (int) x_c, colour_c, colour_ba_slope, z_c, z_ba_slope);
				x_b += ba_slope;
				x_c += ca_slope;
				colour_c += colour_ca_slope;
				z_c += z_ca_slope;
				y_c += scanline_width;
			}
			return;
		}
		y_c = (int) (y_c + 0.5F);
		y_b = (int) (y_b + 0.5F);
		y_a = (int) (y_a + 0.5F) - y_b;
		y_b -= y_c;
		for (y_c = scanline_offsets[(int) y_c]; --y_b >= 0.0F; y_c += scanline_width) {
			draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_c, (int) x_a, colour_c, colour_ba_slope, z_c, z_ba_slope);
			x_a += cb_slope;
			x_c += ca_slope;
			colour_c += colour_ca_slope;
			z_c += z_ca_slope;
		}

		while (--y_a >= 0.0F) {
			draw_shaded_scanline(depth_write, transparent, colour_buffer, (int) y_c, 0, 0, (int) x_c, (int) x_b, colour_c, colour_ba_slope, z_c, z_ba_slope);
			x_b += ba_slope;
			x_c += ca_slope;
			colour_c += colour_ca_slope;
			z_c += z_ca_slope;
			y_c += scanline_width;
		}
	}

	public void draw_shaded_scanline(boolean depth_write, boolean transparent, int[] pixels, int buffer_offset, int background, int y, int start_x, int end_x, float colour, float colour_slope, float depth, float depth_slope) {
		if (clip_edges) {
			if (end_x > clip_width) {
				end_x = clip_width;
			}
			if (start_x < 0) {
				start_x = 0;
			}
		}
		if (start_x >= end_x) {
			return;
		}
		buffer_offset += start_x - 1;
		colour += colour_slope * start_x;
		depth += depth_slope * start_x;
		if (zbuffering) {
			if (smooth_edges) {
				y = end_x - start_x >> 2;
				colour_slope *= 4F;
				if (blending_alpha == 0) {
					if (y > 0) {
						do {
							background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
							colour += colour_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								pixels[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								pixels[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								pixels[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								pixels[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
						do {
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								pixels[buffer_offset] = background;
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
						} while (--y > 0);
					}
				} else {
					int j1 = blending_alpha;
					int j2 = 256 - blending_alpha;
					if (y > 0) {
						do {
							background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
							colour += colour_slope;
							background = ((background & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((background & 0xff00) * j2 >> 8 & 0xff00);
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								int j3 = pixels[buffer_offset];
								if (transparent) {
									pixels[buffer_offset] = (j2 | j3 >> 24) << 24 | background + ((j3 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((j3 & 0xff00) * j1 >> 8 & 0xff00);
								} else {
									pixels[buffer_offset] = background + ((j3 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((j3 & 0xff00) * j1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								int k3 = pixels[buffer_offset];
								if (transparent) {
									pixels[buffer_offset] = (j2 | k3 >> 24) << 24 | background + ((k3 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((k3 & 0xff00) * j1 >> 8 & 0xff00);
								} else {
									pixels[buffer_offset] = background + ((k3 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((k3 & 0xff00) * j1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								int l3 = pixels[buffer_offset];
								if (transparent) {
									pixels[buffer_offset] = (j2 | l3 >> 24) << 24 | background + ((l3 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((l3 & 0xff00) * j1 >> 8 & 0xff00);
								} else {
									pixels[buffer_offset] = background + ((l3 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((l3 & 0xff00) * j1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								int i4 = pixels[buffer_offset];
								if (transparent) {
									pixels[buffer_offset] = (j2 | i4 >> 24) << 24 | background + ((i4 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((i4 & 0xff00) * j1 >> 8 & 0xff00);
								} else {
									pixels[buffer_offset] = background + ((i4 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((i4 & 0xff00) * j1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
						} while (--y > 0);
					}
					y = end_x - start_x & 3;
					if (y > 0) {
						background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
						background = ((background & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((background & 0xff00) * j2 >> 8 & 0xff00);
						do {
							buffer_offset++;
							if (!depth_write || depth < depth_buffer[buffer_offset]) {
								int j4 = pixels[buffer_offset];
								if (transparent) {
									pixels[buffer_offset] = (j2 | j4 >> 24) << 24 | background + ((j4 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((j4 & 0xff00) * j1 >> 8 & 0xff00);
								} else {
									pixels[buffer_offset] = background + ((j4 & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((j4 & 0xff00) * j1 >> 8 & 0xff00);
								}
								if (depth_write) {
									depth_buffer[buffer_offset] = depth;
								}
							}
							depth += depth_slope;
						} while (--y > 0);
					}
				}
			} else {
				y = end_x - start_x;
				if (blending_alpha == 0) {
					do {
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							pixels[buffer_offset] = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
							if (depth_write) {
								depth_buffer[buffer_offset] = depth;
							}
						}
						depth += depth_slope;
						colour += colour_slope;
					} while (--y > 0);
				} else {
					int k1 = blending_alpha;
					int k2 = 256 - blending_alpha;
					do {
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
							background = ((background & 0xff00ff) * k2 >> 8 & 0xff00ff) + ((background & 0xff00) * k2 >> 8 & 0xff00);
							int k4 = pixels[buffer_offset];
							if (transparent) {
								pixels[buffer_offset] = (k2 | k4 >> 24) << 24 | background + ((k4 & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((k4 & 0xff00) * k1 >> 8 & 0xff00);
							} else {
								pixels[buffer_offset] = background + ((k4 & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((k4 & 0xff00) * k1 >> 8 & 0xff00);
							}
							if (depth_write) {
								depth_buffer[buffer_offset] = depth;
							}
						}
						colour += colour_slope;
						depth += depth_slope;
					} while (--y > 0);
				}
			}
		} else if (smooth_edges) {
			y = end_x - start_x >> 2;
			colour_slope *= 4F;
			if (blending_alpha == 0) {
				if (y > 0) {
					do {
						background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
						colour += colour_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							pixels[buffer_offset] = background;
						}
						depth += depth_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							pixels[buffer_offset] = background;
						}
						depth += depth_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							pixels[buffer_offset] = background;
						}
						depth += depth_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							pixels[buffer_offset] = background;
						}
						depth += depth_slope;
					} while (--y > 0);
				}
				y = end_x - start_x & 3;
				if (y > 0) {
					background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
					do {
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							pixels[buffer_offset] = background;
						}
						depth += depth_slope;
					} while (--y > 0);
				}
			} else {
				int l1 = blending_alpha;
				int l2 = 256 - blending_alpha;
				if (y > 0) {
					do {
						background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
						colour += colour_slope;
						background = ((background & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((background & 0xff00) * l2 >> 8 & 0xff00);
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							int l4 = pixels[buffer_offset];
							if (transparent) {
								pixels[buffer_offset] = (l2 | l4 >> 24) << 24 | background + ((l4 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((l4 & 0xff00) * l1 >> 8 & 0xff00);
							} else {
								pixels[buffer_offset] = background + ((l4 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((l4 & 0xff00) * l1 >> 8 & 0xff00);
							}
						}
						depth += depth_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							int i5 = pixels[buffer_offset];
							if (transparent) {
								pixels[buffer_offset] = (l2 | i5 >> 24) << 24 | background + ((i5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((i5 & 0xff00) * l1 >> 8 & 0xff00);
							} else {
								pixels[buffer_offset] = background + ((i5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((i5 & 0xff00) * l1 >> 8 & 0xff00);
							}
						}
						depth += depth_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							int j5 = pixels[buffer_offset];
							if (transparent) {
								pixels[buffer_offset] = (l2 | j5 >> 24) << 24 | background + ((j5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((j5 & 0xff00) * l1 >> 8 & 0xff00);
							} else {
								pixels[buffer_offset] = background + ((j5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((j5 & 0xff00) * l1 >> 8 & 0xff00);
							}
						}
						depth += depth_slope;
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							int k5 = pixels[buffer_offset];
							if (transparent) {
								pixels[buffer_offset] = (l2 | k5 >> 24) << 24 | background + ((k5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((k5 & 0xff00) * l1 >> 8 & 0xff00);
							} else {
								pixels[buffer_offset] = background + ((k5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((k5 & 0xff00) * l1 >> 8 & 0xff00);
							}
						}
						depth += depth_slope;
					} while (--y > 0);
				}
				y = end_x - start_x & 3;
				if (y > 0) {
					background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
					background = ((background & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((background & 0xff00) * l2 >> 8 & 0xff00);
					do {
						buffer_offset++;
						if (!depth_write || depth < depth_buffer[buffer_offset]) {
							int l5 = pixels[buffer_offset];
							if (transparent) {
								pixels[buffer_offset] = (l2 | l5 >> 24) << 24 | background + ((l5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((l5 & 0xff00) * l1 >> 8 & 0xff00);
							} else {
								pixels[buffer_offset] = background + ((l5 & 0xff00ff) * l1 >> 8 & 0xff00ff) + ((l5 & 0xff00) * l1 >> 8 & 0xff00);
							}
						}
						depth += depth_slope;
					} while (--y > 0);
				}
			}
		} else {
			y = end_x - start_x;
			if (blending_alpha == 0) {
				do {
					buffer_offset++;
					if (!depth_write || depth < depth_buffer[buffer_offset]) {
						pixels[buffer_offset] = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
					}
					depth += depth_slope;
					colour += colour_slope;
				} while (--y > 0);
			} else {
				int src_alpha = blending_alpha;
				int dst_alpha = 256 - blending_alpha;
				do {
					buffer_offset++;
					if (!depth_write || depth < depth_buffer[buffer_offset]) {
						background = ColourUtil.hsvToRgbTable[(int) colour & 0xffff];
						background = ((background & 0xff00ff) * dst_alpha >> 8 & 0xff00ff) + ((background & 0xff00) * dst_alpha >> 8 & 0xff00);
						int i6 = pixels[buffer_offset];
						if (transparent) {
							pixels[buffer_offset] = (dst_alpha | i6 >> 24) << 24 | background + ((i6 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i6 & 0xff00) * src_alpha >> 8 & 0xff00);
						} else {
							pixels[buffer_offset] = background + ((i6 & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((i6 & 0xff00) * src_alpha >> 8 & 0xff00);
						}
					}
					colour += colour_slope;
					depth += depth_slope;
				} while (--y > 0);
			}
		}
	}

	public void draw_materialised_triangle(boolean colour_write, boolean depth_write, boolean transparent, float x_a, float x_b, float x_c, float y_a, float y_b, float y_c, float z_a, float z_b, float z_c, float texscale_a, float texscale_b, float texscale_c, float u_a, float u_b, float u_c, float v_a, float v_b, float v_c, int colour_a, int colour_b, int colour_c, int fog_colour, float fog_density_a, float fog_density_b, float fog_density_c, int material_id) {
		if (!colour_write) {
			draw_flat_triangle(false, depth_write, transparent, x_a, x_b, x_c, y_a, y_b, y_c, z_a, z_b, z_c, 0);
			return;
		}
		if (material_id != texture_id_a) {
			MaterialRaw material = materials.get_material(material_id);
			texture_pixels_a = textures.get_pixels(material, brightness);
			if (texture_pixels_a == null) {
				blending_alpha = 255 - (colour_a >> 24 & 0xff);
				int rgb_colour = ColourUtil.hsvToRgbTable[ColourUtil.hsl_to_hsv(material.get_size()) & 0xffff];
				int center_colour = 0xff000000 | (colour_a >> 16 & 0xff) * (rgb_colour >> 16 & 0xff) << 8 & 0xff0000 | (colour_a >> 8 & 0xff) * (rgb_colour >> 8 & 0xff) & 0xff00 | (colour_a & 0xff) * (rgb_colour & 0xff) >> 8;
				draw_triangle(true, depth_write, transparent, x_a, x_b, x_c, y_a, y_b, y_c, z_a, z_b, z_c, Rasterizer.slerp(center_colour, fog_colour, fog_density_a), Rasterizer.slerp(center_colour, fog_colour, fog_density_b), Rasterizer.slerp(center_colour, fog_colour, fog_density_c));
				return;
			}
			texture_size_a = material.get_size();
			texture_mask_a = texture_size_a - 1;
			alpha_mode = material.get_alphamode();
			texture_repeat = material.get_repeat();
			texture_id_a = material_id;
		}
		this.fog_colour = fog_colour;
		if (x_a > x_b || x_a > x_c) {
			if (x_b <= x_c) {
				float temp1 = y_a;
				y_a = y_b;
				y_b = temp1;
				temp1 = x_a;
				x_a = x_b;
				x_b = temp1;
				temp1 = z_a;
				z_a = z_b;
				z_b = temp1;
				temp1 = u_a;
				u_a = u_b;
				u_b = temp1;
				temp1 = v_a;
				v_a = v_b;
				v_b = temp1;
				temp1 = texscale_a;
				texscale_a = texscale_b;
				texscale_b = temp1;
				temp1 = fog_density_a;
				fog_density_a = fog_density_b;
				fog_density_b = temp1;
				int temp2 = colour_a;
				colour_a = colour_b;
				colour_b = temp2;
			} else {
				float temp1 = y_a;
				y_a = y_c;
				y_c = temp1;
				temp1 = x_a;
				x_a = x_c;
				x_c = temp1;
				temp1 = z_a;
				z_a = z_c;
				z_c = temp1;
				temp1 = u_a;
				u_a = u_c;
				u_c = temp1;
				temp1 = v_a;
				v_a = v_c;
				v_c = temp1;
				temp1 = texscale_a;
				texscale_a = texscale_c;
				texscale_c = temp1;
				temp1 = fog_density_a;
				fog_density_a = fog_density_c;
				fog_density_c = temp1;
				int temp2 = colour_a;
				colour_a = colour_c;
				colour_c = temp2;
			}
		}
		u_a /= texscale_a;
		u_b /= texscale_b;
		u_c /= texscale_c;
		v_a /= texscale_a;
		v_b /= texscale_b;
		v_c /= texscale_c;
		z_a = 1.0F / z_a;
		z_b = 1.0F / z_b;
		z_c = 1.0F / z_c;
		texscale_a = 1.0F / texscale_a;
		texscale_b = 1.0F / texscale_b;
		texscale_c = 1.0F / texscale_c;
		float colour_a_alpha = colour_a >> 24 & 0xff;
		float colour_b_alpha = colour_b >> 24 & 0xff;
		float colour_c_alpha = colour_c >> 24 & 0xff;
		float colour_a_red = colour_a >> 16 & 0xff;
		float colour_b_red = colour_b >> 16 & 0xff;
		float colour_c_red = colour_c >> 16 & 0xff;
		float colour_a_green = colour_a >> 8 & 0xff;
		float colour_b_green = colour_b >> 8 & 0xff;
		float colour_c_green = colour_c >> 8 & 0xff;
		float colour_a_blue = colour_a & 0xff;
		float colour_b_blue = colour_b & 0xff;
		float colour_c_blue = colour_c & 0xff;
		float ba_y_slope = 0.0F;
		float ba_z_slope = 0.0F;
		float ba_texscale_slope = 0.0F;
		float u_ba_slope = 0.0F;
		float v_ba_slope = 0.0F;
		float ba_fog_density_slope = 0.0F;
		float ba_src_alpha_slope = 0.0F;
		float ba_red_slope = 0.0F;
		float ba_green_slope = 0.0F;
		float ba_blue_slope = 0.0F;
		if (x_b != x_a) {
			float ba_length = x_b - x_a;
			ba_y_slope = (y_b - y_a) / ba_length;
			ba_z_slope = (z_b - z_a) / ba_length;
			ba_texscale_slope = (texscale_b - texscale_a) / ba_length;
			u_ba_slope = (u_b - u_a) / ba_length;
			v_ba_slope = (v_b - v_a) / ba_length;
			ba_fog_density_slope = (fog_density_b - fog_density_a) / ba_length;
			ba_src_alpha_slope = (colour_b_alpha - colour_a_alpha) / ba_length;
			ba_red_slope = (colour_b_red - colour_a_red) / ba_length;
			ba_green_slope = (colour_b_green - colour_a_green) / ba_length;
			ba_blue_slope = (colour_b_blue - colour_a_blue) / ba_length;
		}
		float cb_y_slope = 0.0F;
		float cb_z_slope = 0.0F;
		float ca_texscale_slope = 0.0F;
		float cb_u_slope = 0.0F;
		float cb_v_slope = 0.0F;
		float cb_fog_density_slope = 0.0F;
		float cb_alpha_slope = 0.0F;
		float cb_red_slope = 0.0F;
		float cb_green_slope = 0.0F;
		float cb_blue_slope = 0.0F;
		if (x_c != x_b) {
			float cb_length = x_c - x_b;
			cb_y_slope = (y_c - y_b) / cb_length;
			cb_z_slope = (z_c - z_b) / cb_length;
			ca_texscale_slope = (texscale_c - texscale_b) / cb_length;
			cb_u_slope = (u_c - u_b) / cb_length;
			cb_v_slope = (v_c - v_b) / cb_length;
			cb_fog_density_slope = (fog_density_c - fog_density_b) / cb_length;
			cb_alpha_slope = (colour_c_alpha - colour_b_alpha) / cb_length;
			cb_red_slope = (colour_c_red - colour_b_red) / cb_length;
			cb_green_slope = (colour_c_green - colour_b_green) / cb_length;
			cb_blue_slope = (colour_c_blue - colour_b_blue) / cb_length;
		}
		float ac_y_slope = 0.0F;
		float ac_z_slope = 0.0F;
		float ac_texscale_slope = 0.0F;
		float ac_u_slope = 0.0F;
		float ac_v_slope = 0.0F;
		float ac_fog_densityslope = 0.0F;
		float ac_alpha_slope = 0.0F;
		float ac_red_slope = 0.0F;
		float ac_green_slope = 0.0F;
		float ac_blue_slope = 0.0F;
		if (x_a != x_c) {
			float ac_length = x_a - x_c;
			ac_y_slope = (y_a - y_c) / ac_length;
			ac_z_slope = (z_a - z_c) / ac_length;
			ac_texscale_slope = (texscale_a - texscale_c) / ac_length;
			ac_u_slope = (u_a - u_c) / ac_length;
			ac_v_slope = (v_a - v_c) / ac_length;
			ac_fog_densityslope = (fog_density_a - fog_density_c) / ac_length;
			ac_alpha_slope = (colour_a_alpha - colour_c_alpha) / ac_length;
			ac_red_slope = (colour_a_red - colour_c_red) / ac_length;
			ac_green_slope = (colour_a_green - colour_c_green) / ac_length;
			ac_blue_slope = (colour_a_blue - colour_c_blue) / ac_length;
		}
		if (x_a >= clip_height) {
			return;
		}
		if (x_b > clip_height) {
			x_b = clip_height;
		}
		if (x_c > clip_height) {
			x_c = clip_height;
		}
		if (x_b < x_c) {
			y_c = y_a;
			z_c = z_a;
			texscale_c = texscale_a;
			u_c = u_a;
			v_c = v_a;
			fog_density_c = fog_density_a;
			colour_c_alpha = colour_a_alpha;
			colour_c_red = colour_a_red;
			colour_c_green = colour_a_green;
			colour_c_blue = colour_a_blue;
			if (x_a < 0.0F) {
				y_a -= ba_y_slope * x_a;
				y_c -= ac_y_slope * x_a;
				z_a -= ba_z_slope * x_a;
				z_c -= ac_z_slope * x_a;
				texscale_a -= ba_texscale_slope * x_a;
				texscale_c -= ac_texscale_slope * x_a;
				u_a -= u_ba_slope * x_a;
				u_c -= ac_u_slope * x_a;
				v_a -= v_ba_slope * x_a;
				v_c -= ac_v_slope * x_a;
				fog_density_a -= ba_fog_density_slope * x_a;
				fog_density_c -= ac_fog_densityslope * x_a;
				colour_a_alpha -= ba_src_alpha_slope * x_a;
				colour_c_alpha -= ac_alpha_slope * x_a;
				colour_a_red -= ba_src_alpha_slope * x_a;
				colour_c_red -= ac_alpha_slope * x_a;
				colour_a_green -= ba_src_alpha_slope * x_a;
				colour_c_green -= ac_alpha_slope * x_a;
				colour_a_blue -= ba_src_alpha_slope * x_a;
				colour_c_blue -= ac_alpha_slope * x_a;
				x_a = 0.0F;
			}
			if (x_b < 0.0F) {
				y_b -= cb_y_slope * x_b;
				z_b -= cb_z_slope * x_b;
				texscale_b -= ca_texscale_slope * x_b;
				u_b -= cb_u_slope * x_b;
				v_b -= cb_v_slope * x_b;
				fog_density_b -= cb_fog_density_slope * x_b;
				colour_b_alpha -= cb_alpha_slope * x_b;
				colour_b_red -= cb_red_slope * x_b;
				colour_b_green -= cb_green_slope * x_b;
				colour_b_blue -= cb_blue_slope * x_b;
				x_b = 0.0F;
			}
			if (x_a != x_b && ac_y_slope < ba_y_slope || x_a == x_b && ac_y_slope > cb_y_slope) {
				x_a = (int) (x_a + 0.5F);
				x_b = (int) (x_b + 0.5F);
				x_c = (int) (x_c + 0.5F) - x_b;
				x_b -= x_a;
				for (x_a = scanline_offsets[(int) x_a]; --x_b >= 0.0F; x_a += scanline_width) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_c, (int) y_a, z_c, z_a, texscale_c, texscale_a, u_c, u_a, v_c, v_a, fog_density_c, fog_density_a, colour_c_alpha, colour_a_alpha, colour_c_red, colour_a_red, colour_c_green, colour_a_green, colour_c_blue, colour_a_blue);
					y_a += ba_y_slope;
					y_c += ac_y_slope;
					z_a += ba_z_slope;
					z_c += ac_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_a += u_ba_slope;
					u_c += ac_u_slope;
					v_a += v_ba_slope;
					v_c += ac_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_c += ac_fog_densityslope;
					colour_a_alpha += ba_src_alpha_slope;
					colour_c_alpha += ac_alpha_slope;
					colour_a_red += ba_red_slope;
					colour_c_red += ac_red_slope;
					colour_a_green += ba_green_slope;
					colour_c_green += ac_green_slope;
					colour_a_blue += ba_blue_slope;
					colour_c_blue += ac_blue_slope;
				}

				while (--x_c >= 0.0F) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_c, (int) y_b, z_c, z_b, texscale_c, texscale_b, u_c, u_b, v_c, v_b, fog_density_c, fog_density_b, colour_c_alpha, colour_b_alpha, colour_c_red, colour_b_red, colour_c_green, colour_b_green, colour_c_blue, colour_b_blue);
					y_b += cb_y_slope;
					y_c += ac_y_slope;
					z_b += cb_z_slope;
					z_c += ac_z_slope;
					texscale_b += ca_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_b += cb_u_slope;
					u_c += ac_u_slope;
					v_b += cb_v_slope;
					v_c += ac_v_slope;
					fog_density_b += cb_fog_density_slope;
					fog_density_c += ac_fog_densityslope;
					colour_b_alpha += cb_alpha_slope;
					colour_c_alpha += ac_alpha_slope;
					colour_b_red += cb_red_slope;
					colour_c_red += ac_red_slope;
					colour_b_green += cb_green_slope;
					colour_c_green += ac_green_slope;
					colour_b_blue += cb_blue_slope;
					colour_c_blue += ac_blue_slope;
					x_a += scanline_width;
				}
			} else {
				x_a = (int) (x_a + 0.5F);
				x_b = (int) (x_b + 0.5F);
				x_c = (int) (x_c + 0.5F) - x_b;
				x_b -= x_a;
				for (x_a = scanline_offsets[(int) x_a]; --x_b >= 0.0F; x_a += scanline_width) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_a, (int) y_c, z_a, z_c, texscale_a, texscale_c, u_a, u_c, v_a, v_c, fog_density_a, fog_density_c, colour_a_alpha, colour_c_alpha, colour_a_red, colour_c_red, colour_a_green, colour_c_green, colour_a_blue, colour_c_blue);
					y_a += ba_y_slope;
					y_c += ac_y_slope;
					z_a += ba_z_slope;
					z_c += ac_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_a += u_ba_slope;
					u_c += ac_u_slope;
					v_a += v_ba_slope;
					v_c += ac_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_c += ac_fog_densityslope;
					colour_a_alpha += ba_src_alpha_slope;
					colour_c_alpha += ac_alpha_slope;
					colour_a_red += ba_red_slope;
					colour_c_red += ac_red_slope;
					colour_a_green += ba_green_slope;
					colour_c_green += ac_green_slope;
					colour_a_blue += ba_blue_slope;
					colour_c_blue += ac_blue_slope;
				}

				while (--x_c >= 0.0F) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_b, (int) y_c, z_b, z_c, texscale_b, texscale_c, u_b, u_c, v_b, v_c, fog_density_b, fog_density_c, colour_b_alpha, colour_c_alpha, colour_b_red, colour_c_red, colour_b_green, colour_c_green, colour_b_blue, colour_c_blue);
					y_b += cb_y_slope;
					y_c += ac_y_slope;
					z_b += cb_z_slope;
					z_c += ac_z_slope;
					texscale_b += ca_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_b += cb_u_slope;
					u_c += ac_u_slope;
					v_b += cb_v_slope;
					v_c += ac_v_slope;
					fog_density_b += cb_fog_density_slope;
					fog_density_c += ac_fog_densityslope;
					colour_b_alpha += cb_alpha_slope;
					colour_c_alpha += ac_alpha_slope;
					colour_b_red += cb_red_slope;
					colour_c_red += ac_red_slope;
					colour_b_green += cb_green_slope;
					colour_c_green += ac_green_slope;
					colour_b_blue += cb_blue_slope;
					colour_c_blue += ac_blue_slope;
					x_a += scanline_width;
				}
			}
		} else {
			y_b = y_a;
			z_b = z_a;
			texscale_b = texscale_a;
			u_b = u_a;
			v_b = v_a;
			fog_density_b = fog_density_a;
			float f25 = colour_a_alpha;
			float f29 = colour_a_red;
			float f33 = colour_a_green;
			float f37 = colour_a_blue;
			if (x_a < 0.0F) {
				y_a -= ba_y_slope * x_a;
				y_b -= ac_y_slope * x_a;
				z_a -= ba_z_slope * x_a;
				z_b -= ac_z_slope * x_a;
				texscale_a -= ba_texscale_slope * x_a;
				texscale_b -= ac_texscale_slope * x_a;
				u_a -= u_ba_slope * x_a;
				u_b -= ac_u_slope * x_a;
				v_a -= v_ba_slope * x_a;
				v_b -= ac_v_slope * x_a;
				fog_density_a -= ba_fog_density_slope * x_a;
				fog_density_b -= ac_fog_densityslope * x_a;
				colour_a_alpha -= ba_src_alpha_slope * x_a;
				f25 -= ac_alpha_slope * x_a;
				colour_a_red -= ba_src_alpha_slope * x_a;
				f29 -= ac_alpha_slope * x_a;
				colour_a_green -= ba_src_alpha_slope * x_a;
				f33 -= ac_alpha_slope * x_a;
				colour_a_blue -= ba_src_alpha_slope * x_a;
				f37 -= ac_alpha_slope * x_a;
				x_a = 0.0F;
			}
			if (x_c < 0.0F) {
				y_c -= cb_y_slope * x_c;
				z_c -= cb_z_slope * x_c;
				texscale_c -= ca_texscale_slope * x_c;
				u_c -= cb_u_slope * x_c;
				v_c -= cb_v_slope * x_c;
				fog_density_c -= cb_fog_density_slope * x_c;
				colour_c_alpha -= cb_alpha_slope * x_c;
				colour_c_red -= cb_red_slope * x_c;
				colour_c_green -= cb_green_slope * x_c;
				colour_c_blue -= cb_blue_slope * x_c;
				x_c = 0.0F;
			}
			if (x_a != x_c && ac_y_slope < ba_y_slope || x_a == x_c && cb_y_slope > ba_y_slope) {
				x_a = (int) (x_a + 0.5F);
				x_c = (int) (x_c + 0.5F);
				x_b = (int) (x_b + 0.5F) - x_c;
				x_c -= x_a;
				for (x_a = scanline_offsets[(int) x_a]; --x_c >= 0.0F; x_a += scanline_width) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_b, (int) y_a, z_b, z_a, texscale_b, texscale_a, u_b, u_a, v_b, v_a, fog_density_b, fog_density_a, f25, colour_a_alpha, f29, colour_a_red, f33, colour_a_green, f37, colour_a_blue);
					y_a += ba_y_slope;
					y_b += ac_y_slope;
					z_a += ba_z_slope;
					z_b += ac_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_b += ac_texscale_slope;
					u_a += u_ba_slope;
					u_b += ac_u_slope;
					v_a += v_ba_slope;
					v_b += ac_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_b += ac_fog_densityslope;
					colour_a_alpha += ba_src_alpha_slope;
					f25 += ac_alpha_slope;
					colour_a_red += ba_red_slope;
					f29 += ac_red_slope;
					colour_a_green += ba_green_slope;
					f33 += ac_green_slope;
					colour_a_blue += ba_blue_slope;
					f37 += ac_blue_slope;
				}

				while (--x_b >= 0.0F) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_c, (int) y_a, z_c, z_a, texscale_c, texscale_a, u_c, u_a, v_c, v_a, fog_density_c, fog_density_a, colour_c_alpha, colour_a_alpha, colour_c_red, colour_a_red, colour_c_green, colour_a_green, colour_c_blue, colour_a_blue);
					y_c += cb_y_slope;
					y_a += ba_y_slope;
					z_c += cb_z_slope;
					z_a += ba_z_slope;
					texscale_c += ca_texscale_slope;
					texscale_a += ba_texscale_slope;
					u_c += cb_u_slope;
					u_a += u_ba_slope;
					v_c += cb_v_slope;
					v_a += v_ba_slope;
					fog_density_c += cb_fog_density_slope;
					fog_density_a += ba_fog_density_slope;
					colour_c_alpha += cb_alpha_slope;
					colour_a_alpha += ba_src_alpha_slope;
					colour_c_red += cb_red_slope;
					colour_a_red += ba_red_slope;
					colour_c_green += cb_green_slope;
					colour_a_green += ba_green_slope;
					colour_c_blue += cb_blue_slope;
					colour_a_blue += ba_blue_slope;
					x_a += scanline_width;
				}
			} else {
				x_a = (int) (x_a + 0.5F);
				x_c = (int) (x_c + 0.5F);
				x_b = (int) (x_b + 0.5F) - x_c;
				x_c -= x_a;
				for (x_a = scanline_offsets[(int) x_a]; --x_c >= 0.0F; x_a += scanline_width) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_a, (int) y_b, z_a, z_b, texscale_a, texscale_b, u_a, u_b, v_a, v_b, fog_density_a, fog_density_b, colour_a_alpha, f25, colour_a_red, f29, colour_a_green, f33, colour_a_blue, f37);
					y_b += ac_y_slope;
					y_a += ba_y_slope;
					z_b += ac_z_slope;
					z_a += ba_z_slope;
					texscale_b += ac_texscale_slope;
					texscale_a += ba_texscale_slope;
					u_b += ac_u_slope;
					u_a += u_ba_slope;
					v_b += ac_v_slope;
					v_a += v_ba_slope;
					fog_density_b += ac_fog_densityslope;
					fog_density_a += ba_fog_density_slope;
					f25 += ac_alpha_slope;
					colour_a_alpha += ba_src_alpha_slope;
					f29 += ac_red_slope;
					colour_a_red += ba_red_slope;
					f33 += ac_green_slope;
					colour_a_green += ba_green_slope;
					f37 += ac_blue_slope;
					colour_a_blue += ba_blue_slope;
				}

				while (--x_b >= 0.0F) {
					draw_materialised_scanline(depth_write, transparent, colour_buffer, (int) x_a, (int) y_a, (int) y_c, z_a, z_c, texscale_a, texscale_c, u_a, u_c, v_a, v_c, fog_density_a, fog_density_c, colour_a_alpha, colour_c_alpha, colour_a_red, colour_c_red, colour_a_green, colour_c_green, colour_a_blue, colour_c_blue);
					y_a += ba_y_slope;
					y_c += cb_y_slope;
					z_a += ba_z_slope;
					z_c += cb_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_c += ca_texscale_slope;
					u_a += u_ba_slope;
					u_c += cb_u_slope;
					v_a += v_ba_slope;
					v_c += cb_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_c += cb_fog_density_slope;
					colour_a_alpha += ba_src_alpha_slope;
					colour_c_alpha += cb_alpha_slope;
					colour_a_red += ba_red_slope;
					colour_c_red += cb_red_slope;
					colour_a_green += ba_green_slope;
					colour_c_green += cb_green_slope;
					colour_a_blue += ba_blue_slope;
					colour_c_blue += cb_blue_slope;
					x_a += scanline_width;
				}
			}
		}
	}

	public void draw_materialised_scanline(boolean depth_write, boolean transparent, int pixels[], int x, int start_y, int end_y, float start_z, float end_z, float scale_x, float scale_y, float a_u, float b_u, float a_v, float b_v, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
		int y_length = end_y - start_y;
		float y_scale = 1.0F / y_length;
		float z_length = (end_z - start_z) * y_scale;
		float f20 = (scale_y - scale_x) * y_scale;
		float b_u_slope = (b_u - a_u) * y_scale;
		float b_v_slope = (b_v - a_v) * y_scale;
		float f23 = (f9 - f8) * y_scale;
		float f24 = (f11 - f10) * y_scale;
		float f25 = (f13 - f12) * y_scale;
		float f26 = (f15 - f14) * y_scale;
		float f27 = (f17 - f16) * y_scale;
		if (clip_edges) {
			if (end_y > clip_width) {
				end_y = clip_width;
			}
			if (start_y < 0) {
				start_z -= z_length * start_y;
				scale_x -= f20 * start_y;
				a_u -= b_u_slope * start_y;
				a_v -= b_v_slope * start_y;
				f8 -= f23 * start_y;
				f10 -= f24 * start_y;
				f12 -= f25 * start_y;
				f14 -= f26 * start_y;
				f16 -= f27 * start_y;
				start_y = 0;
			}
		}
		if (start_y >= end_y) {
			return;
		}
		y_length = end_y - start_y;
		x += start_y;
		while (y_length-- > 0) {
			float f28 = 1.0F / start_z;
			float a_uv_scale = 1.0F / scale_x;
			if (!depth_write || f28 < depth_buffer[x]) {
				int v = (int) (a_u * a_uv_scale * texture_size_a);
				if (texture_repeat) {
					v &= texture_mask_a;
				} else if (v < 0) {
					v = 0;
				} else if (v > texture_mask_a) {
					v = texture_mask_a;
				}
				int u = (int) (a_v * a_uv_scale * texture_size_a);
				if (texture_repeat) {
					u &= texture_mask_a;
				} else if (u < 0) {
					u = 0;
				} else if (u > texture_mask_a) {
					u = texture_mask_a;
				}
				int texel = texture_pixels_a[u * texture_size_a + v];
				int alpha = 255;
				if (alpha_mode == AlphaMode.ALPHA_BLENDED) {
					alpha = (int) ((texel >> 24 & 0xff) * f10 / 255F);
				} else if (alpha_mode == AlphaMode.ALPHA_TESTED) {
					alpha = texel != 0 ? 255 : 0;
				} else {
					alpha = (int) f10;
				}
				if (alpha != 0) {
					if (alpha != 255) {
						int i2 = 0xff000000 | (int) (f12 * (texel >> 16 & 0xff)) << 8 & 0xff0000 | (int) (f14 * (texel >> 8 & 0xff)) & 0xff00 | (int) (f16 * (texel & 0xff)) >> 8;
						if (f8 != 0.0F) {
							int k2 = (int) (255F - f8);
							int j3 = ((fog_colour & 0xff00ff) * (int) f8 & 0xff00ff00 | (fog_colour & 0xff00) * (int) f8 & 0xff0000) >>> 8;
							i2 = (((i2 & 0xff00ff) * k2 & 0xff00ff00 | (i2 & 0xff00) * k2 & 0xff0000) >>> 8) + j3;
						}
						int l2 = pixels[x];
						int k3 = 255 - alpha;
						i2 = ((l2 & 0xff00ff) * k3 + (i2 & 0xff00ff) * alpha & 0xff00ff00) + ((l2 & 0xff00) * k3 + (i2 & 0xff00) * alpha & 0xff0000) >> 8;
						if (transparent) {
							pixels[x] = (alpha | pixels[x] >> 24) << 24 | i2;
						} else {
							pixels[x] = i2;
						}
						if (depth_write) {
							depth_buffer[x] = f28;
						}
					} else {
						int j2 = 0xff000000 | (int) (f12 * (texel >> 16 & 0xff)) << 8 & 0xff0000 | (int) (f14 * (texel >> 8 & 0xff)) & 0xff00 | (int) (f16 * (texel & 0xff)) >> 8;
						if (f8 != 0.0F) {
							int i3 = (int) (255F - f8);
							int l3 = ((fog_colour & 0xff00ff) * (int) f8 & 0xff00ff00 | (fog_colour & 0xff00) * (int) f8 & 0xff0000) >>> 8;
							j2 = (((j2 & 0xff00ff) * i3 & 0xff00ff00 | (j2 & 0xff00) * i3 & 0xff0000) >>> 8) + l3;
						}
						if (transparent) {
							pixels[x] = alpha << 24 | j2;
						} else {
							pixels[x] = j2;
						}
						if (depth_write) {
							depth_buffer[x] = f28;
						}
					}
				}
			}
			x++;
			start_z += z_length;
			scale_x += f20;
			a_u += b_u_slope;
			a_v += b_v_slope;
			f8 += f23;
			f10 += f24;
			f12 += f25;
			f14 += f26;
			f16 += f27;
		}
	}

	public void draw_multimaterialised_triangle(boolean colour_write, boolean depth_write, boolean transparent, float y_a, float y_b, float y_c, float x_a, float x_b, float x_c, float z_a, float z_b, float z_c, float texscale_a, float texscale_b, float texscale_c, float u_a, float u_b, float u_c, float v_a, float v_b, float v_c, int colour_a, int colour_b, int colour_c, int fog_colour, float fog_density_a, float fog_density_b, float fog_density_c, int texid_a, float texoff_a, int texid_b, float texoff_b, int texid_c, float texoff_c) {
		if (!colour_write) {
			draw_flat_triangle(false, depth_write, transparent, y_a, y_b, y_c, x_a, x_b, x_c, z_a, z_b, z_c, 0);
			return;
		}
		this.fog_colour = fog_colour;
		if (y_a > y_b || y_a > y_c) {
			if (y_b <= y_c) {
				float temp1 = x_a;
				x_a = x_b;
				x_b = temp1;
				temp1 = y_a;
				y_a = y_b;
				y_b = temp1;
				temp1 = z_a;
				z_a = z_b;
				z_b = temp1;
				temp1 = u_a;
				u_a = u_b;
				u_b = temp1;
				temp1 = v_a;
				v_a = v_b;
				v_b = temp1;
				temp1 = texscale_a;
				texscale_a = texscale_b;
				texscale_b = temp1;
				temp1 = fog_density_a;
				fog_density_a = fog_density_b;
				fog_density_b = temp1;
				temp1 = texoff_a;
				texoff_a = texoff_b;
				texoff_b = temp1;
				int temp2 = colour_a;
				colour_a = colour_b;
				colour_b = temp2;
				temp2 = texid_a;
				texid_a = texid_b;
				texid_b = temp2;
			} else {
				float temp1 = x_a;
				x_a = x_c;
				x_c = temp1;
				temp1 = y_a;
				y_a = y_c;
				y_c = temp1;
				temp1 = z_a;
				z_a = z_c;
				z_c = temp1;
				temp1 = u_a;
				u_a = u_c;
				u_c = temp1;
				temp1 = v_a;
				v_a = v_c;
				v_c = temp1;
				temp1 = texscale_a;
				texscale_a = texscale_c;
				texscale_c = temp1;
				temp1 = fog_density_a;
				fog_density_a = fog_density_c;
				fog_density_c = temp1;
				temp1 = texoff_a;
				texoff_a = texoff_c;
				texoff_c = temp1;
				int temp2 = colour_a;
				colour_a = colour_c;
				colour_c = temp2;
				temp2 = texid_a;
				texid_a = texid_c;
				texid_c = temp2;
			}
		}
		if (texid_a != texture_id_a) {
			MaterialRaw material = materials.get_material(texid_a);
			texture_pixels_a = textures.get_pixels(material, brightness);
			if (texture_pixels_a == null) {
				blending_alpha = 255 - (colour_a >> 24 & 0xff);
				int l1 = ColourUtil.hsvToRgbTable[ColourUtil.hsl_to_hsv(material.get_colour()) & 0xffff];
				int i3 = 0xff000000 | (colour_a >> 16 & 0xff) * (l1 >> 16 & 0xff) << 8 & 0xff0000 | (colour_a >> 8 & 0xff) * (l1 >> 8 & 0xff) & 0xff00 | (colour_a & 0xff) * (l1 & 0xff) >> 8;
				draw_triangle(true, depth_write, transparent, y_a, y_b, y_c, x_a, x_b, x_c, z_a, z_b, z_c, Rasterizer.slerp(i3, fog_colour, fog_density_a), Rasterizer.slerp(i3, fog_colour, fog_density_b), Rasterizer.slerp(i3, fog_colour, fog_density_c));
				return;
			}
			texture_size_a = material.get_size();
			texture_mask_a = texture_size_a - 1;
			alpha_mode = material.get_alphamode();
			texture_id_b = texid_a;
		}
		texture_scale_a = texoff_a;
		if (texid_b != texture_id_b) {
			MaterialRaw material = materials.get_material(texid_b);
			texture_pixels_b = textures.get_pixels(material, brightness);
			if (texture_pixels_b == null) {
				blending_alpha = 255 - (colour_a >> 24 & 0xff);
				int i2 = ColourUtil.hsvToRgbTable[ColourUtil.hsl_to_hsv(material.get_colour()) & 0xffff];
				int j3 = 0xff000000 | (colour_a >> 16 & 0xff) * (i2 >> 16 & 0xff) << 8 & 0xff0000 | (colour_a >> 8 & 0xff) * (i2 >> 8 & 0xff) & 0xff00 | (colour_a & 0xff) * (i2 & 0xff) >> 8;
				draw_triangle(true, depth_write, transparent, y_a, y_b, y_c, x_a, x_b, x_c, z_a, z_b, z_c, Rasterizer.slerp(j3, fog_colour, fog_density_a), Rasterizer.slerp(j3, fog_colour, fog_density_b), Rasterizer.slerp(j3, fog_colour, fog_density_c));
				return;
			}
			texture_size_b = material.get_size();
			texture_mask_b = texture_size_b - 1;
			texture_id_b = texid_b;
		}
		texture_scale_b = texoff_b;
		if (texid_c != texture_id_c) {
			MaterialRaw material = materials.get_material(texid_b);
			texture_pixels_c = textures.get_pixels(material, brightness);
			if (texture_pixels_c == null) {
				blending_alpha = 255 - (colour_a >> 24 & 0xff);
				int j2 = ColourUtil.hsvToRgbTable[ColourUtil.hsl_to_hsv(material.get_colour()) & 0xffff];
				int k3 = 0xff000000 | (colour_a >> 16 & 0xff) * (j2 >> 16 & 0xff) << 8 & 0xff0000 | (colour_a >> 8 & 0xff) * (j2 >> 8 & 0xff) & 0xff00 | (colour_a & 0xff) * (j2 & 0xff) >> 8;
				draw_triangle(true, depth_write, transparent, (int) y_a, (int) y_b, (int) y_c, (int) x_a, (int) x_b, (int) x_c, (int) z_a, (int) z_b, (int) z_c, Rasterizer.slerp(k3, fog_colour, fog_density_a), Rasterizer.slerp(k3, fog_colour, fog_density_b), Rasterizer.slerp(k3, fog_colour, fog_density_c));
				return;
			}
			texture_size_c = material.get_size();
			texture_mask_c = texture_size_c - 1;
			texture_id_c = texid_c;
		}
		texture_scale_c = texoff_c;
		u_a /= texscale_a;
		u_b /= texscale_b;
		u_c /= texscale_c;
		v_a /= texscale_a;
		v_b /= texscale_b;
		v_c /= texscale_c;
		z_a = 1.0F / z_a;
		z_b = 1.0F / z_b;
		z_c = 1.0F / z_c;
		texscale_a = 1.0F / texscale_a;
		texscale_b = 1.0F / texscale_b;
		texscale_c = 1.0F / texscale_c;
		float alpha_a = colour_a >> 24 & 0xff;
		float alpha_b = colour_b >> 24 & 0xff;
		float alpha_c = colour_c >> 24 & 0xff;
		float red_a = colour_a >> 16 & 0xff;
		float red_b = colour_b >> 16 & 0xff;
		float red_c = colour_c >> 16 & 0xff;
		float green_a = colour_a >> 8 & 0xff;
		float green_b = colour_b >> 8 & 0xff;
		float green_c = colour_c >> 8 & 0xff;
		float blue_a = colour_a & 0xff;
		float blue_b = colour_b & 0xff;
		float blue_c = colour_c & 0xff;
		float texopacity1_a = 1.0F;
		float texopacity1_b = 0.0F;
		float texopacity1_c = 0.0F;
		float texopacity3_a = 0.0F;
		float texopacity3_b = 1.0F;
		float texopacity3_c = 0.0F;
		float ba_x_slope = 0.0F;
		float ba_z_slope = 0.0F;
		float ba_texscale_slope = 0.0F;
		float ba_u_slope = 0.0F;
		float ba_v_slope = 0.0F;
		float ba_fog_density_slope = 0.0F;
		float ba_alpha_slope = 0.0F;
		float ba_red_slope = 0.0F;
		float ba_green_slope = 0.0F;
		float ba_blue_slope = 0.0F;
		float ba_texopacity1_slope = 0.0F;
		float ba_texopacity3_slope = 0.0F;
		if (y_b != y_a) {
			float ba_y = y_b - y_a;
			ba_x_slope = (x_b - x_a) / ba_y;
			ba_z_slope = (z_b - z_a) / ba_y;
			ba_texscale_slope = (texscale_b - texscale_a) / ba_y;
			ba_u_slope = (u_b - u_a) / ba_y;
			ba_v_slope = (v_b - v_a) / ba_y;
			ba_fog_density_slope = (fog_density_b - fog_density_a) / ba_y;
			ba_alpha_slope = (alpha_b - alpha_a) / ba_y;
			ba_red_slope = (red_b - red_a) / ba_y;
			ba_green_slope = (green_b - green_a) / ba_y;
			ba_blue_slope = (blue_b - blue_a) / ba_y;
			ba_texopacity1_slope = (texopacity1_b - texopacity1_a) / ba_y;
			ba_texopacity3_slope = (texopacity3_b - texopacity3_a) / ba_y;
		}
		float cb_x_slope = 0.0F;
		float cb_z_slope = 0.0F;
		float cb_texscale_slope = 0.0F;
		float cb_u_slope = 0.0F;
		float cb_v_slope = 0.0F;
		float cb_fog_density_slope = 0.0F;
		float cb_alpha_slope = 0.0F;
		float cb_red_slope = 0.0F;
		float cb_green_slope = 0.0F;
		float cb_blue_slope = 0.0F;
		float cb_texopacity1_slope = 0.0F;
		float cb_texopacity3_slope = 0.0F;
		if (y_c != y_b) {
			float cb_length = y_c - y_b;
			cb_x_slope = (x_c - x_b) / cb_length;
			cb_z_slope = (z_c - z_b) / cb_length;
			cb_texscale_slope = (texscale_c - texscale_b) / cb_length;
			cb_u_slope = (u_c - u_b) / cb_length;
			cb_v_slope = (v_c - v_b) / cb_length;
			cb_fog_density_slope = (fog_density_c - fog_density_b) / cb_length;
			cb_alpha_slope = (alpha_c - alpha_b) / cb_length;
			cb_red_slope = (red_c - red_b) / cb_length;
			cb_green_slope = (green_c - green_b) / cb_length;
			cb_blue_slope = (blue_c - blue_b) / cb_length;
			cb_texopacity1_slope = (texopacity1_c - texopacity1_b) / cb_length;
			cb_texopacity3_slope = (texopacity3_c - texopacity3_b) / cb_length;
		}
		float ac_x_slope = 0.0F;
		float ac_z_slope = 0.0F;
		float ac_texscale_slope = 0.0F;
		float ac_u_slope = 0.0F;
		float ac_v_slope = 0.0F;
		float ac_fog_density_slope = 0.0F;
		float ac_alpha_slope = 0.0F;
		float ac_red_slope = 0.0F;
		float ac_green_slope = 0.0F;
		float ac_blue_slope = 0.0F;
		float ac_texopacity1_slope = 0.0F;
		float ac_texopacity3_slope = 0.0F;
		if (y_a != y_c) {
			float ac_length = y_a - y_c;
			ac_x_slope = (x_a - x_c) / ac_length;
			ac_z_slope = (z_a - z_c) / ac_length;
			ac_texscale_slope = (texscale_a - texscale_c) / ac_length;
			ac_u_slope = (u_a - u_c) / ac_length;
			ac_v_slope = (v_a - v_c) / ac_length;
			ac_fog_density_slope = (fog_density_a - fog_density_c) / ac_length;
			ac_alpha_slope = (alpha_a - alpha_c) / ac_length;
			ac_red_slope = (red_a - red_c) / ac_length;
			ac_green_slope = (green_a - green_c) / ac_length;
			ac_blue_slope = (blue_a - blue_c) / ac_length;
			ac_texopacity1_slope = (texopacity1_a - texopacity1_c) / ac_length;
			ac_texopacity3_slope = (texopacity3_a - texopacity3_c) / ac_length;
		}
		if (y_a >= clip_height) {
			return;
		}
		if (y_b > clip_height) {
			y_b = clip_height;
		}
		if (y_c > clip_height) {
			y_c = clip_height;
		}
		if (y_b < y_c) {
			x_c = x_a;
			z_c = z_a;
			texscale_c = texscale_a;
			u_c = u_a;
			v_c = v_a;
			fog_density_c = fog_density_a;
			alpha_c = alpha_a;
			red_c = red_a;
			green_c = green_a;
			blue_c = blue_a;
			texopacity1_c = texopacity1_a;
			texopacity3_c = texopacity3_a;
			if (y_a < 0.0F) {
				x_a -= ba_x_slope * y_a;
				x_c -= ac_x_slope * y_a;
				z_a -= ba_z_slope * y_a;
				z_c -= ac_z_slope * y_a;
				texscale_a -= ba_texscale_slope * y_a;
				texscale_c -= ac_texscale_slope * y_a;
				u_a -= ba_u_slope * y_a;
				u_c -= ac_u_slope * y_a;
				v_a -= ba_v_slope * y_a;
				v_c -= ac_v_slope * y_a;
				fog_density_a -= ba_fog_density_slope * y_a;
				fog_density_c -= ac_fog_density_slope * y_a;
				alpha_a -= ba_alpha_slope * y_a;
				alpha_c -= ac_alpha_slope * y_a;
				red_a -= ba_red_slope * y_a;
				red_c -= ac_red_slope * y_a;
				green_a -= ba_green_slope * y_a;
				green_c -= ac_green_slope * y_a;
				blue_a -= ba_blue_slope * y_a;
				blue_c -= ac_blue_slope * y_a;
				texopacity1_a -= ba_texopacity1_slope * y_a;
				texopacity1_c -= ac_texopacity1_slope * y_a;
				texopacity3_a -= ba_texopacity3_slope * y_a;
				texopacity3_c -= ac_texopacity3_slope * y_a;
				y_a = 0.0F;
			}
			if (y_b < 0.0F) {
				x_b -= cb_x_slope * y_b;
				z_b -= cb_z_slope * y_b;
				texscale_b -= cb_texscale_slope * y_b;
				u_b -= cb_u_slope * y_b;
				v_b -= cb_v_slope * y_b;
				fog_density_b -= cb_fog_density_slope * y_b;
				alpha_b -= cb_alpha_slope * y_b;
				red_b -= cb_red_slope * y_b;
				green_b -= cb_green_slope * y_b;
				blue_b -= cb_blue_slope * y_b;
				texopacity1_b -= cb_texopacity1_slope * y_b;
				texopacity3_b -= cb_texopacity3_slope * y_b;
				y_b = 0.0F;
			}
			if (y_a != y_b && ac_x_slope < ba_x_slope || y_a == y_b && ac_x_slope > cb_x_slope) {
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_b;
				y_b -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_c, (int) x_a, z_c, z_a, texscale_c, texscale_a, u_c, u_a, v_c, v_a, fog_density_c, fog_density_a, alpha_c, alpha_a, red_c, red_a, green_c, green_a, blue_c, blue_a, texopacity1_c, texopacity1_a, texopacity3_c, texopacity3_a);
					x_a += ba_x_slope;
					x_c += ac_x_slope;
					z_a += ba_z_slope;
					z_c += ac_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_a += ba_u_slope;
					u_c += ac_u_slope;
					v_a += ba_v_slope;
					v_c += ac_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_c += ac_fog_density_slope;
					alpha_a += ba_alpha_slope;
					alpha_c += ac_alpha_slope;
					red_a += ba_red_slope;
					red_c += ac_red_slope;
					green_a += ba_green_slope;
					green_c += ac_green_slope;
					blue_a += ba_blue_slope;
					blue_c += ac_blue_slope;
					texopacity1_a += ba_texopacity1_slope;
					texopacity1_c += ac_texopacity1_slope;
					texopacity3_a += ba_texopacity3_slope;
					texopacity3_c += ac_texopacity3_slope;
				}

				while (--y_c >= 0.0F) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_c, (int) x_b, z_c, z_b, texscale_c, texscale_b, u_c, u_b, v_c, v_b, fog_density_c, fog_density_b, alpha_c, alpha_b, red_c, red_b, green_c, green_b, blue_c, blue_b, texopacity1_c, texopacity1_b, texopacity3_c, texopacity3_b);
					x_b += cb_x_slope;
					x_c += ac_x_slope;
					z_b += cb_z_slope;
					z_c += ac_z_slope;
					texscale_b += cb_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_b += cb_u_slope;
					u_c += ac_u_slope;
					v_b += cb_v_slope;
					v_c += ac_v_slope;
					fog_density_b += cb_fog_density_slope;
					fog_density_c += ac_fog_density_slope;
					alpha_b += cb_alpha_slope;
					alpha_c += ac_alpha_slope;
					red_b += cb_red_slope;
					red_c += ac_red_slope;
					green_b += cb_green_slope;
					green_c += ac_green_slope;
					blue_b += cb_blue_slope;
					blue_c += ac_blue_slope;
					texopacity1_b += cb_texopacity1_slope;
					texopacity1_c += ac_texopacity1_slope;
					texopacity3_b += cb_texopacity3_slope;
					texopacity3_c += ac_texopacity3_slope;
					y_a += scanline_width;
				}
			} else {
				y_a = (int) (y_a + 0.5F);
				y_b = (int) (y_b + 0.5F);
				y_c = (int) (y_c + 0.5F) - y_b;
				y_b -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_b >= 0.0F; y_a += scanline_width) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_a, (int) x_c, z_a, z_c, texscale_a, texscale_c, u_a, u_c, v_a, v_c, fog_density_a, fog_density_c, alpha_a, alpha_c, red_a, red_c, green_a, green_c, blue_a, blue_c, texopacity1_a, texopacity1_c, texopacity3_a, texopacity3_c);
					x_a += ba_x_slope;
					x_c += ac_x_slope;
					z_a += ba_z_slope;
					z_c += ac_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_a += ba_u_slope;
					u_c += ac_u_slope;
					v_a += ba_v_slope;
					v_c += ac_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_c += ac_fog_density_slope;
					alpha_a += ba_alpha_slope;
					alpha_c += ac_alpha_slope;
					red_a += ba_red_slope;
					red_c += ac_red_slope;
					green_a += ba_green_slope;
					green_c += ac_green_slope;
					blue_a += ba_blue_slope;
					blue_c += ac_blue_slope;
					texopacity1_a += ba_texopacity1_slope;
					texopacity1_c += ac_texopacity1_slope;
					texopacity3_a += ba_texopacity3_slope;
					texopacity3_c += ac_texopacity3_slope;
				}

				while (--y_c >= 0.0F) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_b, (int) x_c, z_b, z_c, texscale_b, texscale_c, u_b, u_c, v_b, v_c, fog_density_b, fog_density_c, alpha_b, alpha_c, red_b, red_c, green_b, green_c, blue_b, blue_c, texopacity1_b, texopacity1_c, texopacity3_b, texopacity3_c);
					x_b += cb_x_slope;
					x_c += ac_x_slope;
					z_b += cb_z_slope;
					z_c += ac_z_slope;
					texscale_b += cb_texscale_slope;
					texscale_c += ac_texscale_slope;
					u_b += cb_u_slope;
					u_c += ac_u_slope;
					v_b += cb_v_slope;
					v_c += ac_v_slope;
					fog_density_b += cb_fog_density_slope;
					fog_density_c += ac_fog_density_slope;
					alpha_b += cb_alpha_slope;
					alpha_c += ac_alpha_slope;
					red_b += cb_red_slope;
					red_c += ac_red_slope;
					green_b += cb_green_slope;
					green_c += ac_green_slope;
					blue_b += cb_blue_slope;
					blue_c += ac_blue_slope;
					texopacity1_b += cb_texopacity1_slope;
					texopacity1_c += ac_texopacity1_slope;
					texopacity3_b += cb_texopacity3_slope;
					texopacity3_c += ac_texopacity3_slope;
					y_a += scanline_width;
				}
			}
		} else {
			x_b = x_a;
			z_b = z_a;
			texscale_b = texscale_a;
			u_b = u_a;
			v_b = v_a;
			fog_density_b = fog_density_a;
			float f28 = alpha_a;
			float f32 = red_a;
			float f36 = green_a;
			float f40 = blue_a;
			float f44 = texopacity1_a;
			float f48 = texopacity3_a;
			if (y_a < 0.0F) {
				x_a -= ba_x_slope * y_a;
				x_b -= ac_x_slope * y_a;
				z_a -= ba_z_slope * y_a;
				z_b -= ac_z_slope * y_a;
				texscale_a -= ba_texscale_slope * y_a;
				texscale_b -= ac_texscale_slope * y_a;
				u_a -= ba_u_slope * y_a;
				u_b -= ac_u_slope * y_a;
				v_a -= ba_v_slope * y_a;
				v_b -= ac_v_slope * y_a;
				fog_density_a -= ba_fog_density_slope * y_a;
				fog_density_b -= ac_fog_density_slope * y_a;
				alpha_a -= ba_alpha_slope * y_a;
				f28 -= ac_alpha_slope * y_a;
				red_a -= ba_red_slope * y_a;
				f32 -= ac_red_slope * y_a;
				green_a -= ba_green_slope * y_a;
				f36 -= ac_green_slope * y_a;
				blue_a -= ba_blue_slope * y_a;
				f40 -= ac_blue_slope * y_a;
				texopacity1_a -= ba_texopacity1_slope * y_a;
				f44 -= ac_texopacity1_slope * y_a;
				texopacity3_a -= ba_texopacity3_slope * y_a;
				f48 -= ac_texopacity3_slope * y_a;
				y_a = 0.0F;
			}
			if (y_c < 0.0F) {
				x_c -= cb_x_slope * y_c;
				z_c -= cb_z_slope * y_c;
				texscale_c -= cb_texscale_slope * y_c;
				u_c -= cb_u_slope * y_c;
				v_c -= cb_v_slope * y_c;
				fog_density_c -= cb_fog_density_slope * y_c;
				alpha_c -= cb_alpha_slope * y_c;
				red_c -= cb_red_slope * y_c;
				green_c -= cb_green_slope * y_c;
				blue_c -= cb_blue_slope * y_c;
				texopacity1_c -= cb_texopacity1_slope * y_c;
				texopacity3_c -= cb_texopacity3_slope * y_c;
				y_c = 0.0F;
			}
			if (y_a != y_c && ac_x_slope < ba_x_slope || y_a == y_c && cb_x_slope > ba_x_slope) {
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_c;
				y_c -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_b, (int) x_a, z_b, z_a, texscale_b, texscale_a, u_b, u_a, v_b, v_a, fog_density_b, fog_density_a, f28, alpha_a, f32, red_a, f36, green_a, f40, blue_a, f44, texopacity1_a, f48, texopacity3_a);
					x_a += ba_x_slope;
					x_b += ac_x_slope;
					z_a += ba_z_slope;
					z_b += ac_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_b += ac_texscale_slope;
					u_a += ba_u_slope;
					u_b += ac_u_slope;
					v_a += ba_v_slope;
					v_b += ac_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_b += ac_fog_density_slope;
					alpha_a += ba_alpha_slope;
					f28 += ac_alpha_slope;
					red_a += ba_red_slope;
					f32 += ac_red_slope;
					green_a += ba_green_slope;
					f36 += ac_green_slope;
					blue_a += ba_blue_slope;
					f40 += ac_blue_slope;
					texopacity1_a += ba_texopacity1_slope;
					f44 += ac_texopacity1_slope;
					texopacity3_a += ba_texopacity3_slope;
					f48 += ac_texopacity3_slope;
				}

				while (--y_b >= 0.0F) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_c, (int) x_a, z_c, z_a, texscale_c, texscale_a, u_c, u_a, v_c, v_a, fog_density_c, fog_density_a, alpha_c, alpha_a, red_c, red_a, green_c, green_a, blue_c, blue_a, texopacity1_c, texopacity1_a, texopacity3_c, texopacity3_a);
					x_c += cb_x_slope;
					x_a += ba_x_slope;
					z_c += cb_z_slope;
					z_a += ba_z_slope;
					texscale_c += cb_texscale_slope;
					texscale_a += ba_texscale_slope;
					u_c += cb_u_slope;
					u_a += ba_u_slope;
					v_c += cb_v_slope;
					v_a += ba_v_slope;
					fog_density_c += cb_fog_density_slope;
					fog_density_a += ba_fog_density_slope;
					alpha_c += cb_alpha_slope;
					alpha_a += ba_alpha_slope;
					red_c += cb_red_slope;
					red_a += ba_red_slope;
					green_c += cb_green_slope;
					green_a += ba_green_slope;
					blue_c += cb_blue_slope;
					blue_a += ba_blue_slope;
					texopacity1_c += cb_texopacity1_slope;
					texopacity1_a += ba_texopacity1_slope;
					texopacity3_c += cb_texopacity3_slope;
					texopacity3_a += ba_texopacity3_slope;
					y_a += scanline_width;
				}
			} else {
				y_a = (int) (y_a + 0.5F);
				y_c = (int) (y_c + 0.5F);
				y_b = (int) (y_b + 0.5F) - y_c;
				y_c -= y_a;
				for (y_a = scanline_offsets[(int) y_a]; --y_c >= 0.0F; y_a += scanline_width) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_a, (int) x_b, z_a, z_b, texscale_a, texscale_b, u_a, u_b, v_a, v_b, fog_density_a, fog_density_b, alpha_a, f28, red_a, f32, green_a, f36, blue_a, f40, texopacity1_a, f44, texopacity3_a, f48);
					x_b += ac_x_slope;
					x_a += ba_x_slope;
					z_b += ac_z_slope;
					z_a += ba_z_slope;
					texscale_b += ac_texscale_slope;
					texscale_a += ba_texscale_slope;
					u_b += ac_u_slope;
					u_a += ba_u_slope;
					v_b += ac_v_slope;
					v_a += ba_v_slope;
					fog_density_b += ac_fog_density_slope;
					fog_density_a += ba_fog_density_slope;
					f28 += ac_alpha_slope;
					alpha_a += ba_alpha_slope;
					f32 += ac_red_slope;
					red_a += ba_red_slope;
					f36 += ac_green_slope;
					green_a += ba_green_slope;
					f40 += ac_blue_slope;
					blue_a += ba_blue_slope;
					f44 += ac_texopacity1_slope;
					texopacity1_a += ba_texopacity1_slope;
					f48 += ac_texopacity3_slope;
					texopacity3_a += ba_texopacity3_slope;
				}

				while (--y_b >= 0.0F) {
					draw_multimaterialised_scanline(depth_write, transparent, colour_buffer, (int) y_a, (int) x_a, (int) x_c, z_a, z_c, texscale_a, texscale_c, u_a, u_c, v_a, v_c, fog_density_a, fog_density_c, alpha_a, alpha_c, red_a, red_c, green_a, green_c, blue_a, blue_c, texopacity1_a, texopacity1_c, texopacity3_a, texopacity3_c);
					x_a += ba_x_slope;
					x_c += cb_x_slope;
					z_a += ba_z_slope;
					z_c += cb_z_slope;
					texscale_a += ba_texscale_slope;
					texscale_c += cb_texscale_slope;
					u_a += ba_u_slope;
					u_c += cb_u_slope;
					v_a += ba_v_slope;
					v_c += cb_v_slope;
					fog_density_a += ba_fog_density_slope;
					fog_density_c += cb_fog_density_slope;
					alpha_a += ba_alpha_slope;
					alpha_c += cb_alpha_slope;
					red_a += ba_red_slope;
					red_c += cb_red_slope;
					green_a += ba_green_slope;
					green_c += cb_green_slope;
					blue_a += ba_blue_slope;
					blue_c += cb_blue_slope;
					texopacity1_a += ba_texopacity1_slope;
					texopacity1_c += cb_texopacity1_slope;
					texopacity3_a += ba_texopacity3_slope;
					texopacity3_c += cb_texopacity3_slope;
					y_a += scanline_width;
				}
			}
		}
	}

	public void draw_multimaterialised_scanline(boolean depth_write, boolean transparent, int[] colour_buffer, int line, int x_start, int x_end, float z_start, float z_end, float texscale_start, float texscale_end, float u_start, float u_end, float v_start, float v_end, float fog_density_start, float fog_density_end, float alpha_start, float alpha_end, float red_start, float red_end, float green_start, float green_end, float blue_start, float blue_end, float texopacity_a_start, float texopacity_a_end, float texopacity_b_start, float texopacity_b_end) {
		int num_loops = x_end - x_start;
		float loop_step = 1.0F / num_loops;
		float z_slope = (z_end - z_start) * loop_step;
		float texscale_slope = (texscale_end - texscale_start) * loop_step;
		float u_slope = (u_end - u_start) * loop_step;
		float v_slope = (v_end - v_start) * loop_step;
		float fog_density_slope = (fog_density_end - fog_density_start) * loop_step;
		float alpha_slope = (alpha_end - alpha_start) * loop_step;
		float red_slope = (red_end - red_start) * loop_step;
		float green_slope = (green_end - green_start) * loop_step;
		float blue_slope = (blue_end - blue_start) * loop_step;
		float texopacity_a_slope = (texopacity_a_end - texopacity_a_start) * loop_step;
		float texopacity_b_slope = (texopacity_b_end - texopacity_b_start) * loop_step;
		if (clip_edges) {
			if (x_end > clip_width) {
				x_end = clip_width;
			}
			if (x_start < 0) {
				z_start -= z_slope * x_start;
				texscale_start -= texscale_slope * x_start;
				u_start -= u_slope * x_start;
				v_start -= v_slope * x_start;
				fog_density_start -= fog_density_slope * x_start;
				alpha_start -= alpha_slope * x_start;
				red_start -= red_slope * x_start;
				green_start -= green_slope * x_start;
				blue_start -= blue_slope * x_start;
				texopacity_a_start -= texopacity_a_slope * x_start;
				texopacity_b_start -= texopacity_b_slope * x_start;
				x_start = 0;
			}
		}
		if (x_start >= x_end) {
			return;
		}
		num_loops = x_end - x_start;
		line += x_start;
		while (num_loops-- > 0) {
			float depth = 1.0F / z_start;
			float texscale = 1.0F / texscale_start;
			if (!depth_write || depth < depth_buffer[line]) {
				float texscale_u = u_start * texscale;
				float texscale_v = v_start * texscale;
				int texel_x = (int) (texscale_u * texture_size_a * texture_scale_a) & texture_mask_a;
				int texel_y = (int) (texscale_v * texture_size_a * texture_scale_a) & texture_mask_a;
				int texel_a = texture_pixels_a[texel_x + texel_y * texture_size_a];
				texel_x = (int) (texscale_u * texture_size_b * texture_scale_b) & texture_mask_b;
				texel_y = (int) (texscale_v * texture_size_b * texture_scale_b) & texture_mask_b;
				int texel_b = texture_pixels_b[texel_x + texel_y * texture_size_b];
				texel_x = (int) (texscale_u * texture_size_c * texture_scale_c) & texture_mask_c;
				texel_y = (int) (texscale_v * texture_size_c * texture_scale_c) & texture_mask_c;
				int texel_c = texture_pixels_c[texel_x + texel_y * texture_size_c];
				float texopacity_c_start = 1.0F - (texopacity_a_start + texopacity_b_start);
				texel_a = 0xff000000 | (int) (texopacity_a_start * (texel_a >> 16 & 0xff)) << 16 | (int) (texopacity_a_start * (texel_a >> 8 & 0xff)) << 8 | (int) (texopacity_a_start * (texel_a & 0xff));
				texel_b = 0xff000000 | (int) (texopacity_b_start * (texel_b >> 16 & 0xff)) << 16 | (int) (texopacity_b_start * (texel_b >> 8 & 0xff)) << 8 | (int) (texopacity_b_start * (texel_b & 0xff));
				texel_c = 0xff000000 | (int) (texopacity_c_start * (texel_c >> 16 & 0xff)) << 16 | (int) (texopacity_c_start * (texel_c >> 8 & 0xff)) << 8 | (int) (texopacity_c_start * (texel_c & 0xff));
				int texel = texel_a + texel_b + texel_c;
				int output = 0xff000000 | (int) (red_start * (texel >> 16 & 0xff)) << 8 & 0xff0000 | (int) (green_start * (texel >> 8 & 0xff)) & 0xff00 | (int) (blue_start * (texel & 0xff)) >> 8;
				if (fog_density_start != 0.0F) {
					int dst_alpha = (int) (255F - fog_density_start);
					int blended_fog = ((fog_colour & 0xff00ff) * (int) fog_density_start & 0xff00ff00 | (fog_colour & 0xff00) * (int) fog_density_start & 0xff0000) >>> 8;
					output = (((output & 0xff00ff) * dst_alpha & 0xff00ff00 | (output & 0xff00) * dst_alpha & 0xff0000) >>> 8) + blended_fog;
				}
				if (transparent) {
					colour_buffer[line] = ((int) alpha_start | colour_buffer[line] >> 24) << 24 | output;
				} else {
					colour_buffer[line] = output;
				}
				if (depth_write) {
					depth_buffer[line] = depth;
				}
			}
			line++;
			z_start += z_slope;
			texscale_start += texscale_slope;
			u_start += u_slope;
			v_start += v_slope;
			fog_density_start += fog_density_slope;
			alpha_start += alpha_slope;
			red_start += red_slope;
			green_start += green_slope;
			blue_start += blue_slope;
			texopacity_a_start += texopacity_a_slope;
			texopacity_b_start += texopacity_b_slope;
		}
	}

	/**
	 * Gets the current scanline row number.
	 * 
	 * @return the current scanline row number.
	 */
	public int get_current_row() {
		return scanline_offsets[0] / scanline_width;
	}

	/**
	 * Gets the current scanline scanline column number.
	 * 
	 * @return the current scanline column number.
	 */
	public int get_current_column() {
		return scanline_offsets[0] % scanline_width;
	}

	// TO COMMENT
	public static int slerp(int src, int dst, float alpha) {
		return slerp(src, dst, (int) alpha);
	}

	public static int slerp(int src, int dst, int alpha) {
		int dst_alpha = 255 - alpha;
		dst = ((dst & 0xff00ff) * alpha & ~0xff00ff | (dst & 0xff00) * alpha & 0xff0000) >>> 8;
		return dst + (((src & 0xff00ff) * dst_alpha & ~0xff00ff | dst_alpha * (src & 0xff00) & 0xff0000) >>> 8);
	}

	public void clip_edges(int x_a, int x_b, int x_c) {
		clip_edges = x_a < 0 || x_a > clip_width || x_b < 0 || x_b > clip_width || x_c < 0 || x_c > clip_width;
	}

	public void center_adjust(int i, int i_0_) {
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		int i_1_ = rasterizer.scanline_offsets[0];
		int i_2_ = i_1_ / scanline_width;
		int i_3_ = i_1_ - i_2_ * scanline_width;
		center_x = i - i_3_;
		center_y = i_0_ - i_2_;
		Viewport.screen_left = -rasterizer.center_x;
		Viewport.screen_right = clip_width - rasterizer.center_x;
		Viewport.screen_top = -center_y;
		Viewport.screen_bottom = clip_height - rasterizer.center_y;
	}

	public void viewport_update(int windowOffsetX, int windowOffsetY, int clientWidth, int clientHeight) {
		clip_width = clientWidth - windowOffsetX;
		clip_height = clientHeight - windowOffsetY;
		viewport_reset();
		if (scanline_offsets.length < clip_height) {
			scanline_offsets = new int[MathTools.get_greater_pow2(clip_height)];
		}
		int totalOffset = windowOffsetY * scanline_width + windowOffsetX;
		for (int i_201_ = 0; i_201_ < clip_height; i_201_++) {
			scanline_offsets[i_201_] = totalOffset;
			totalOffset += scanline_width;
		}
	}

	public void viewport_reset() {
		center_x = clip_width / 2;
		center_y = clip_height / 2;
		Viewport.screen_left = -center_x;
		Viewport.screen_right = clip_width - center_x;
		Viewport.screen_top = -center_y;
		Viewport.screen_bottom = clip_height - center_y;
	}

	public void clip_reset() {
		viewport_update(Rasterizer2D.clip_left, Rasterizer2D.clip_top, Rasterizer2D.clip_right, Rasterizer2D.clip_bottom);
	}

	// methods from 530

	public void method1135(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18) {
		MaterialRaw material = GraphicTools.get_materials().get_material(var18);
		int[] var19 = textures.get_pixels(material, brightness);
		int var20;
		if (var19 == null) {
			var20 = material.get_colour();
			method1154(var0, var1, var2, var3, var4, var5, repack_lightness(var20, var6), repack_lightness(var20, var7), repack_lightness(var20, var8));
		} else {
			small_texture = material.is_small;
			alpha_opaque = material.is_alpha_opaque();
			var20 = var4 - var3;
			int var21 = var1 - var0;
			int var22 = var5 - var3;
			int var23 = var2 - var0;
			int var24 = var7 - var6;
			int var25 = var8 - var6;
			int var26 = 0;
			if (var1 != var0) {
				var26 = (var4 - var3 << 16) / (var1 - var0);
			}

			int var27 = 0;
			if (var2 != var1) {
				var27 = (var5 - var4 << 16) / (var2 - var1);
			}

			int var28 = 0;
			if (var2 != var0) {
				var28 = (var3 - var5 << 16) / (var0 - var2);
			}

			int var29 = var20 * var23 - var22 * var21;
			if (var29 != 0) {
				int var30 = (var24 * var23 - var25 * var21 << 9) / var29;
				int var31 = (var25 * var20 - var24 * var22 << 9) / var29;
				var10 = var9 - var10;
				var13 = var12 - var13;
				var16 = var15 - var16;
				var11 -= var9;
				var14 -= var12;
				var17 -= var15;
				int var32 = var11 * var12 - var14 * var9 << 14;
				int var33 = var14 * var15 - var17 * var12 << 5;
				int var34 = var17 * var9 - var11 * var15 << 5;
				int var35 = var10 * var12 - var13 * var9 << 14;
				int var36 = var13 * var15 - var16 * var12 << 5;
				int var37 = var16 * var9 - var10 * var15 << 5;
				int var38 = var13 * var11 - var10 * var14 << 14;
				int var39 = var16 * var14 - var13 * var17 << 5;
				int var40 = var10 * var17 - var16 * var11 << 5;
				int var41;
				if (var0 <= var1 && var0 <= var2) {
					if (var0 < clip_height) {
						if (var1 > clip_height) {
							var1 = clip_height;
						}

						if (var2 > clip_height) {
							var2 = clip_height;
						}

						var6 = (var6 << 9) - var30 * var3 + var30;
						if (var1 < var2) {
							var5 = var3 <<= 16;
							if (var0 < 0) {
								var5 -= var28 * var0;
								var3 -= var26 * var0;
								var6 -= var31 * var0;
								var0 = 0;
							}

							var4 <<= 16;
							if (var1 < 0) {
								var4 -= var27 * var1;
								var1 = 0;
							}

							var41 = var0 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if ((var0 == var1 || var28 >= var26) && (var0 != var1 || var28 <= var27)) {
								var2 -= var1;
								var1 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var1;
									if (var1 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var0, var4 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var28;
											var4 += var27;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var5 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var2 -= var1;
								var1 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var1;
									if (var1 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var0, var5 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var28;
											var4 += var27;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var5 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						} else {
							var4 = var3 <<= 16;
							if (var0 < 0) {
								var4 -= var28 * var0;
								var3 -= var26 * var0;
								var6 -= var31 * var0;
								var0 = 0;
							}

							var5 <<= 16;
							if (var2 < 0) {
								var5 -= var27 * var2;
								var2 = 0;
							}

							var41 = var0 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if ((var0 == var2 || var28 >= var26) && (var0 != var2 || var27 <= var26)) {
								var1 -= var2;
								var2 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var1;
											if (var1 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var27;
											var3 += var26;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var0, var3 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var4 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var1 -= var2;
								var2 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var1;
											if (var1 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var27;
											var3 += var26;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var0, var4 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var4 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						}
					}
				} else if (var1 <= var2) {
					if (var1 < clip_height) {
						if (var2 > clip_height) {
							var2 = clip_height;
						}

						if (var0 > clip_height) {
							var0 = clip_height;
						}

						var7 = (var7 << 9) - var30 * var4 + var30;
						if (var2 < var0) {
							var3 = var4 <<= 16;
							if (var1 < 0) {
								var3 -= var26 * var1;
								var4 -= var27 * var1;
								var7 -= var31 * var1;
								var1 = 0;
							}

							var5 <<= 16;
							if (var2 < 0) {
								var5 -= var28 * var2;
								var2 = 0;
							}

							var41 = var1 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if ((var1 == var2 || var26 >= var27) && (var1 != var2 || var26 <= var28)) {
								var0 -= var2;
								var2 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var0;
											if (var0 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var1, var5 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var26;
											var5 += var28;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var3 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var0 -= var2;
								var2 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var0;
											if (var0 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var1, var3 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var26;
											var5 += var28;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var3 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						} else {
							var5 = var4 <<= 16;
							if (var1 < 0) {
								var5 -= var26 * var1;
								var4 -= var27 * var1;
								var7 -= var31 * var1;
								var1 = 0;
							}

							var3 <<= 16;
							if (var0 < 0) {
								var3 -= var28 * var0;
								var0 = 0;
							}

							var41 = var1 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if (var26 < var27) {
								var2 -= var0;
								var0 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var0;
									if (var0 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var28;
											var4 += var27;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var1, var5 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var5 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var2 -= var0;
								var0 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var0;
									if (var0 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1143(colour_buffer, var19, 0, 0, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var28;
											var4 += var27;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1143(colour_buffer, var19, 0, 0, var1, var4 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var5 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						}
					}
				} else if (var2 < clip_height) {
					if (var0 > clip_height) {
						var0 = clip_height;
					}

					if (var1 > clip_height) {
						var1 = clip_height;
					}

					var8 = (var8 << 9) - var30 * var5 + var30;
					if (var0 < var1) {
						var4 = var5 <<= 16;
						if (var2 < 0) {
							var4 -= var27 * var2;
							var5 -= var28 * var2;
							var8 -= var31 * var2;
							var2 = 0;
						}

						var3 <<= 16;
						if (var0 < 0) {
							var3 -= var26 * var0;
							var0 = 0;
						}

						var41 = var2 - center_y;
						var32 += var34 * var41;
						var35 += var37 * var41;
						var38 += var40 * var41;
						if (var27 < var28) {
							var1 -= var0;
							var0 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var0;
								if (var0 < 0) {
									while (true) {
										--var1;
										if (var1 < 0) {
											return;
										}

										method1143(colour_buffer, var19, 0, 0, var2, var4 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var27;
										var3 += var26;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1143(colour_buffer, var19, 0, 0, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var4 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						} else {
							var1 -= var0;
							var0 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var0;
								if (var0 < 0) {
									while (true) {
										--var1;
										if (var1 < 0) {
											return;
										}

										method1143(colour_buffer, var19, 0, 0, var2, var3 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var27;
										var3 += var26;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1143(colour_buffer, var19, 0, 0, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var4 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						}
					} else {
						var3 = var5 <<= 16;
						if (var2 < 0) {
							var3 -= var27 * var2;
							var5 -= var28 * var2;
							var8 -= var31 * var2;
							var2 = 0;
						}

						var4 <<= 16;
						if (var1 < 0) {
							var4 -= var26 * var1;
							var1 = 0;
						}

						var41 = var2 - center_y;
						var32 += var34 * var41;
						var35 += var37 * var41;
						var38 += var40 * var41;
						if (var27 < var28) {
							var0 -= var1;
							var1 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var1;
								if (var1 < 0) {
									while (true) {
										--var0;
										if (var0 < 0) {
											return;
										}

										method1143(colour_buffer, var19, 0, 0, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var26;
										var5 += var28;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1143(colour_buffer, var19, 0, 0, var2, var3 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var3 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						} else {
							var0 -= var1;
							var1 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var1;
								if (var1 < 0) {
									while (true) {
										--var0;
										if (var0 < 0) {
											return;
										}

										method1143(colour_buffer, var19, 0, 0, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var26;
										var5 += var28;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1143(colour_buffer, var19, 0, 0, var2, var5 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var3 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						}
					}
				}
			}
		}
	}

	private void method1143(int[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14) {
		if (clip_edges) {
			if (var6 > clip_width) {
				var6 = clip_width;
			}

			if (var5 < 0) {
				var5 = 0;
			}
		}
		if (var5 < var6) {
			var4 += var5;
			var7 += var8 * var5;
			int var17 = var6 - var5;
			int var15;
			int var16;
			int var19;
			int var18;
			int var21;
			int var20;
			int var23;
			int var22;
			if (small_texture) {
				var23 = var5 - center_x;
				var9 += var12 * var23;
				var10 += var13 * var23;
				var11 += var14 * var23;
				var22 = var11 >> 12;
				if (var22 != 0) {
					var18 = var9 / var22;
					var19 = var10 / var22;
				} else {
					var18 = 0;
					var19 = 0;
				}

				var9 += var12 * var17;
				var10 += var13 * var17;
				var11 += var14 * var17;
				var22 = var11 >> 12;
				if (var22 != 0) {
					var20 = var9 / var22;
					var21 = var10 / var22;
				} else {
					var20 = 0;
					var21 = 0;
				}

				var2 = (var18 << 20) + var19;
				var16 = ((var20 - var18) / var17 << 20) + (var21 - var19) / var17;
				var17 >>= 3;
				var8 <<= 3;
				var15 = var7 >> 8;
				if (alpha_opaque) {
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				} else {
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				}
			} else {
				var23 = var5 - center_x;
				var9 += var12 * var23;
				var10 += var13 * var23;
				var11 += var14 * var23;
				var22 = var11 >> 14;
				if (var22 != 0) {
					var18 = var9 / var22;
					var19 = var10 / var22;
				} else {
					var18 = 0;
					var19 = 0;
				}

				var9 += var12 * var17;
				var10 += var13 * var17;
				var11 += var14 * var17;
				var22 = var11 >> 14;
				if (var22 != 0) {
					var20 = var9 / var22;
					var21 = var10 / var22;
				} else {
					var20 = 0;
					var21 = 0;
				}

				var2 = (var18 << 18) + var19;
				var16 = ((var20 - var18) / var17 << 18) + (var21 - var19) / var17;
				var17 >>= 3;
				var8 <<= 3;
				var15 = var7 >> 8;
				if (alpha_opaque) {
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				} else {
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				}
			}

		}
	}

	public void method1154(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		int var9 = var4 - var3;
		int var10 = var1 - var0;
		int var11 = var5 - var3;
		int var12 = var2 - var0;
		int var13 = var7 - var6;
		int var14 = var8 - var6;
		int var15;
		if (var2 != var1) {
			var15 = (var5 - var4 << 16) / (var2 - var1);
		} else {
			var15 = 0;
		}

		int var16;
		if (var1 != var0) {
			var16 = (var9 << 16) / var10;
		} else {
			var16 = 0;
		}

		int var17;
		if (var2 != var0) {
			var17 = (var11 << 16) / var12;
		} else {
			var17 = 0;
		}

		int var18 = var9 * var12 - var11 * var10;
		if (var18 != 0) {
			int var19 = (var13 * var12 - var14 * var10 << 8) / var18;
			int var20 = (var14 * var9 - var13 * var11 << 8) / var18;
			if (var0 <= var1 && var0 <= var2) {
				if (var0 < clip_height) {
					if (var1 > clip_height) {
						var1 = clip_height;
					}

					if (var2 > clip_height) {
						var2 = clip_height;
					}

					var6 = (var6 << 8) - var19 * var3 + var19;
					if (var1 < var2) {
						var5 = var3 <<= 16;
						if (var0 < 0) {
							var5 -= var17 * var0;
							var3 -= var16 * var0;
							var6 -= var20 * var0;
							var0 = 0;
						}

						var4 <<= 16;
						if (var1 < 0) {
							var4 -= var15 * var1;
							var1 = 0;
						}

						if ((var0 == var1 || var17 >= var16) && (var0 != var1 || var17 <= var15)) {
							var2 -= var1;
							var1 -= var0;
							var0 = scanline_offsets[var0];

							while (true) {
								--var1;
								if (var1 < 0) {
									while (true) {
										--var2;
										if (var2 < 0) {
											return;
										}

										method1146(colour_buffer, var0, 0, 0, var4 >> 16, var5 >> 16, var6, var19);
										var5 += var17;
										var4 += var15;
										var6 += var20;
										var0 += scanline_width;
									}
								}

								method1146(colour_buffer, var0, 0, 0, var3 >> 16, var5 >> 16, var6, var19);
								var5 += var17;
								var3 += var16;
								var6 += var20;
								var0 += scanline_width;
							}
						} else {
							var2 -= var1;
							var1 -= var0;
							var0 = scanline_offsets[var0];

							while (true) {
								--var1;
								if (var1 < 0) {
									while (true) {
										--var2;
										if (var2 < 0) {
											return;
										}

										method1146(colour_buffer, var0, 0, 0, var5 >> 16, var4 >> 16, var6, var19);
										var5 += var17;
										var4 += var15;
										var6 += var20;
										var0 += scanline_width;
									}
								}

								method1146(colour_buffer, var0, 0, 0, var5 >> 16, var3 >> 16, var6, var19);
								var5 += var17;
								var3 += var16;
								var6 += var20;
								var0 += scanline_width;
							}
						}
					} else {
						var4 = var3 <<= 16;
						if (var0 < 0) {
							var4 -= var17 * var0;
							var3 -= var16 * var0;
							var6 -= var20 * var0;
							var0 = 0;
						}

						var5 <<= 16;
						if (var2 < 0) {
							var5 -= var15 * var2;
							var2 = 0;
						}

						if ((var0 == var2 || var17 >= var16) && (var0 != var2 || var15 <= var16)) {
							var1 -= var2;
							var2 -= var0;
							var0 = scanline_offsets[var0];

							while (true) {
								--var2;
								if (var2 < 0) {
									while (true) {
										--var1;
										if (var1 < 0) {
											return;
										}

										method1146(colour_buffer, var0, 0, 0, var3 >> 16, var5 >> 16, var6, var19);
										var5 += var15;
										var3 += var16;
										var6 += var20;
										var0 += scanline_width;
									}
								}

								method1146(colour_buffer, var0, 0, 0, var3 >> 16, var4 >> 16, var6, var19);
								var4 += var17;
								var3 += var16;
								var6 += var20;
								var0 += scanline_width;
							}
						} else {
							var1 -= var2;
							var2 -= var0;
							var0 = scanline_offsets[var0];

							while (true) {
								--var2;
								if (var2 < 0) {
									while (true) {
										--var1;
										if (var1 < 0) {
											return;
										}

										method1146(colour_buffer, var0, 0, 0, var5 >> 16, var3 >> 16, var6, var19);
										var5 += var15;
										var3 += var16;
										var6 += var20;
										var0 += scanline_width;
									}
								}

								method1146(colour_buffer, var0, 0, 0, var4 >> 16, var3 >> 16, var6, var19);
								var4 += var17;
								var3 += var16;
								var6 += var20;
								var0 += scanline_width;
							}
						}
					}
				}
			} else if (var1 <= var2) {
				if (var1 < clip_height) {
					if (var2 > clip_height) {
						var2 = clip_height;
					}

					if (var0 > clip_height) {
						var0 = clip_height;
					}

					var7 = (var7 << 8) - var19 * var4 + var19;
					if (var2 < var0) {
						var3 = var4 <<= 16;
						if (var1 < 0) {
							var3 -= var16 * var1;
							var4 -= var15 * var1;
							var7 -= var20 * var1;
							var1 = 0;
						}

						var5 <<= 16;
						if (var2 < 0) {
							var5 -= var17 * var2;
							var2 = 0;
						}

						if ((var1 == var2 || var16 >= var15) && (var1 != var2 || var16 <= var17)) {
							var0 -= var2;
							var2 -= var1;
							var1 = scanline_offsets[var1];

							while (true) {
								--var2;
								if (var2 < 0) {
									while (true) {
										--var0;
										if (var0 < 0) {
											return;
										}

										method1146(colour_buffer, var1, 0, 0, var5 >> 16, var3 >> 16, var7, var19);
										var3 += var16;
										var5 += var17;
										var7 += var20;
										var1 += scanline_width;
									}
								}

								method1146(colour_buffer, var1, 0, 0, var4 >> 16, var3 >> 16, var7, var19);
								var3 += var16;
								var4 += var15;
								var7 += var20;
								var1 += scanline_width;
							}
						} else {
							var0 -= var2;
							var2 -= var1;
							var1 = scanline_offsets[var1];

							while (true) {
								--var2;
								if (var2 < 0) {
									while (true) {
										--var0;
										if (var0 < 0) {
											return;
										}

										method1146(colour_buffer, var1, 0, 0, var3 >> 16, var5 >> 16, var7, var19);
										var3 += var16;
										var5 += var17;
										var7 += var20;
										var1 += scanline_width;
									}
								}

								method1146(colour_buffer, var1, 0, 0, var3 >> 16, var4 >> 16, var7, var19);
								var3 += var16;
								var4 += var15;
								var7 += var20;
								var1 += scanline_width;
							}
						}
					} else {
						var5 = var4 <<= 16;
						if (var1 < 0) {
							var5 -= var16 * var1;
							var4 -= var15 * var1;
							var7 -= var20 * var1;
							var1 = 0;
						}

						var3 <<= 16;
						if (var0 < 0) {
							var3 -= var17 * var0;
							var0 = 0;
						}

						if (var16 < var15) {
							var2 -= var0;
							var0 -= var1;
							var1 = scanline_offsets[var1];

							while (true) {
								--var0;
								if (var0 < 0) {
									while (true) {
										--var2;
										if (var2 < 0) {
											return;
										}

										method1146(colour_buffer, var1, 0, 0, var3 >> 16, var4 >> 16, var7, var19);
										var3 += var17;
										var4 += var15;
										var7 += var20;
										var1 += scanline_width;
									}
								}

								method1146(colour_buffer, var1, 0, 0, var5 >> 16, var4 >> 16, var7, var19);
								var5 += var16;
								var4 += var15;
								var7 += var20;
								var1 += scanline_width;
							}
						} else {
							var2 -= var0;
							var0 -= var1;
							var1 = scanline_offsets[var1];

							while (true) {
								--var0;
								if (var0 < 0) {
									while (true) {
										--var2;
										if (var2 < 0) {
											return;
										}

										method1146(colour_buffer, var1, 0, 0, var4 >> 16, var3 >> 16, var7, var19);
										var3 += var17;
										var4 += var15;
										var7 += var20;
										var1 += scanline_width;
									}
								}

								method1146(colour_buffer, var1, 0, 0, var4 >> 16, var5 >> 16, var7, var19);
								var5 += var16;
								var4 += var15;
								var7 += var20;
								var1 += scanline_width;
							}
						}
					}
				}
			} else if (var2 < clip_height) {
				if (var0 > clip_height) {
					var0 = clip_height;
				}

				if (var1 > clip_height) {
					var1 = clip_height;
				}

				var8 = (var8 << 8) - var19 * var5 + var19;
				if (var0 < var1) {
					var4 = var5 <<= 16;
					if (var2 < 0) {
						var4 -= var15 * var2;
						var5 -= var17 * var2;
						var8 -= var20 * var2;
						var2 = 0;
					}

					var3 <<= 16;
					if (var0 < 0) {
						var3 -= var16 * var0;
						var0 = 0;
					}

					if (var15 < var17) {
						var1 -= var0;
						var0 -= var2;
						var2 = scanline_offsets[var2];

						while (true) {
							--var0;
							if (var0 < 0) {
								while (true) {
									--var1;
									if (var1 < 0) {
										return;
									}

									method1146(colour_buffer, var2, 0, 0, var4 >> 16, var3 >> 16, var8, var19);
									var4 += var15;
									var3 += var16;
									var8 += var20;
									var2 += scanline_width;
								}
							}

							method1146(colour_buffer, var2, 0, 0, var4 >> 16, var5 >> 16, var8, var19);
							var4 += var15;
							var5 += var17;
							var8 += var20;
							var2 += scanline_width;
						}
					} else {
						var1 -= var0;
						var0 -= var2;
						var2 = scanline_offsets[var2];

						while (true) {
							--var0;
							if (var0 < 0) {
								while (true) {
									--var1;
									if (var1 < 0) {
										return;
									}

									method1146(colour_buffer, var2, 0, 0, var3 >> 16, var4 >> 16, var8, var19);
									var4 += var15;
									var3 += var16;
									var8 += var20;
									var2 += scanline_width;
								}
							}

							method1146(colour_buffer, var2, 0, 0, var5 >> 16, var4 >> 16, var8, var19);
							var4 += var15;
							var5 += var17;
							var8 += var20;
							var2 += scanline_width;
						}
					}
				} else {
					var3 = var5 <<= 16;
					if (var2 < 0) {
						var3 -= var15 * var2;
						var5 -= var17 * var2;
						var8 -= var20 * var2;
						var2 = 0;
					}

					var4 <<= 16;
					if (var1 < 0) {
						var4 -= var16 * var1;
						var1 = 0;
					}

					if (var15 < var17) {
						var0 -= var1;
						var1 -= var2;
						var2 = scanline_offsets[var2];

						while (true) {
							--var1;
							if (var1 < 0) {
								while (true) {
									--var0;
									if (var0 < 0) {
										return;
									}

									method1146(colour_buffer, var2, 0, 0, var4 >> 16, var5 >> 16, var8, var19);
									var4 += var16;
									var5 += var17;
									var8 += var20;
									var2 += scanline_width;
								}
							}

							method1146(colour_buffer, var2, 0, 0, var3 >> 16, var5 >> 16, var8, var19);
							var3 += var15;
							var5 += var17;
							var8 += var20;
							var2 += scanline_width;
						}
					} else {
						var0 -= var1;
						var1 -= var2;
						var2 = scanline_offsets[var2];

						while (true) {
							--var1;
							if (var1 < 0) {
								while (true) {
									--var0;
									if (var0 < 0) {
										return;
									}

									method1146(colour_buffer, var2, 0, 0, var5 >> 16, var4 >> 16, var8, var19);
									var4 += var16;
									var5 += var17;
									var8 += var20;
									var2 += scanline_width;
								}
							}

							method1146(colour_buffer, var2, 0, 0, var5 >> 16, var3 >> 16, var8, var19);
							var3 += var15;
							var5 += var17;
							var8 += var20;
							var2 += scanline_width;
						}
					}
				}
			}
		}
	}

	private void method1146(int[] var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		if (clip_edges) {
			if (var5 > clip_width) {
				var5 = clip_width;
			}

			if (var4 < 0) {
				var4 = 0;
			}
		}

		if (var4 < var5) {
			var1 += var4;
			var6 += var7 * var4;
			int var8;
			int var9;
			int var10;
			if (small_texture) {
				var3 = var5 - var4 >> 2;
				var7 <<= 2;
				if (blending_alpha == 0) {
					if (var3 > 0) {
						do {
							var2 = hslToRgbTable[var6 >> 8];
							var6 += var7;
							var0[var1++] = var2;
							var0[var1++] = var2;
							var0[var1++] = var2;
							var0[var1++] = var2;
							--var3;
						} while (var3 > 0);
					}

					var3 = var5 - var4 & 3;
					if (var3 > 0) {
						var2 = hslToRgbTable[var6 >> 8];

						do {
							var0[var1++] = var2;
							--var3;
						} while (var3 > 0);
					}
				} else {
					var8 = blending_alpha;
					var9 = 256 - blending_alpha;
					if (var3 > 0) {
						do {
							var2 = hslToRgbTable[var6 >> 8];
							var6 += var7;
							var2 = ((var2 & 16711935) * var9 >> 8 & 16711935) + ((var2 & '\uff00') * var9 >> 8 & '\uff00');
							var10 = var0[var1];
							var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
							var10 = var0[var1];
							var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
							var10 = var0[var1];
							var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
							var10 = var0[var1];
							var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
							--var3;
						} while (var3 > 0);
					}

					var3 = var5 - var4 & 3;
					if (var3 > 0) {
						var2 = hslToRgbTable[var6 >> 8];
						var2 = ((var2 & 16711935) * var9 >> 8 & 16711935) + ((var2 & '\uff00') * var9 >> 8 & '\uff00');

						do {
							var10 = var0[var1];
							var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
							--var3;
						} while (var3 > 0);
					}
				}

			} else {
				var3 = var5 - var4;
				if (blending_alpha == 0) {
					do {
						var0[var1++] = hslToRgbTable[var6 >> 8];
						var6 += var7;
						--var3;
					} while (var3 > 0);
				} else {
					var8 = blending_alpha;
					var9 = 256 - blending_alpha;

					do {
						var2 = hslToRgbTable[var6 >> 8];
						var6 += var7;
						var2 = ((var2 & 16711935) * var9 >> 8 & 16711935) + ((var2 & '\uff00') * var9 >> 8 & '\uff00');
						var10 = var0[var1];
						var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
						--var3;
					} while (var3 > 0);
				}

			}
		}
	}

	public void method1144(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
		int var7 = 0;
		if (var1 != var0) {
			var7 = (var4 - var3 << 16) / (var1 - var0);
		}

		int var8 = 0;
		if (var2 != var1) {
			var8 = (var5 - var4 << 16) / (var2 - var1);
		}

		int var9 = 0;
		if (var2 != var0) {
			var9 = (var3 - var5 << 16) / (var0 - var2);
		}

		if (var0 <= var1 && var0 <= var2) {
			if (var0 < clip_height) {
				if (var1 > clip_height) {
					var1 = clip_height;
				}

				if (var2 > clip_height) {
					var2 = clip_height;
				}

				if (var1 < var2) {
					var5 = var3 <<= 16;
					if (var0 < 0) {
						var5 -= var9 * var0;
						var3 -= var7 * var0;
						var0 = 0;
					}

					var4 <<= 16;
					if (var1 < 0) {
						var4 -= var8 * var1;
						var1 = 0;
					}

					if ((var0 == var1 || var9 >= var7) && (var0 != var1 || var9 <= var8)) {
						var2 -= var1;
						var1 -= var0;
						var0 = scanline_offsets[var0];

						while (true) {
							--var1;
							if (var1 < 0) {
								while (true) {
									--var2;
									if (var2 < 0) {
										return;
									}

									method1149(colour_buffer, var0, var6, 0, var4 >> 16, var5 >> 16);
									var5 += var9;
									var4 += var8;
									var0 += scanline_width;
								}
							}

							method1149(colour_buffer, var0, var6, 0, var3 >> 16, var5 >> 16);
							var5 += var9;
							var3 += var7;
							var0 += scanline_width;
						}
					} else {
						var2 -= var1;
						var1 -= var0;
						var0 = scanline_offsets[var0];

						while (true) {
							--var1;
							if (var1 < 0) {
								while (true) {
									--var2;
									if (var2 < 0) {
										return;
									}

									method1149(colour_buffer, var0, var6, 0, var5 >> 16, var4 >> 16);
									var5 += var9;
									var4 += var8;
									var0 += scanline_width;
								}
							}

							method1149(colour_buffer, var0, var6, 0, var5 >> 16, var3 >> 16);
							var5 += var9;
							var3 += var7;
							var0 += scanline_width;
						}
					}
				} else {
					var4 = var3 <<= 16;
					if (var0 < 0) {
						var4 -= var9 * var0;
						var3 -= var7 * var0;
						var0 = 0;
					}

					var5 <<= 16;
					if (var2 < 0) {
						var5 -= var8 * var2;
						var2 = 0;
					}

					if ((var0 == var2 || var9 >= var7) && (var0 != var2 || var8 <= var7)) {
						var1 -= var2;
						var2 -= var0;
						var0 = scanline_offsets[var0];

						while (true) {
							--var2;
							if (var2 < 0) {
								while (true) {
									--var1;
									if (var1 < 0) {
										return;
									}

									method1149(colour_buffer, var0, var6, 0, var3 >> 16, var5 >> 16);
									var5 += var8;
									var3 += var7;
									var0 += scanline_width;
								}
							}

							method1149(colour_buffer, var0, var6, 0, var3 >> 16, var4 >> 16);
							var4 += var9;
							var3 += var7;
							var0 += scanline_width;
						}
					} else {
						var1 -= var2;
						var2 -= var0;
						var0 = scanline_offsets[var0];

						while (true) {
							--var2;
							if (var2 < 0) {
								while (true) {
									--var1;
									if (var1 < 0) {
										return;
									}

									method1149(colour_buffer, var0, var6, 0, var5 >> 16, var3 >> 16);
									var5 += var8;
									var3 += var7;
									var0 += scanline_width;
								}
							}

							method1149(colour_buffer, var0, var6, 0, var4 >> 16, var3 >> 16);
							var4 += var9;
							var3 += var7;
							var0 += scanline_width;
						}
					}
				}
			}
		} else if (var1 <= var2) {
			if (var1 < clip_height) {
				if (var2 > clip_height) {
					var2 = clip_height;
				}

				if (var0 > clip_height) {
					var0 = clip_height;
				}

				if (var2 < var0) {
					var3 = var4 <<= 16;
					if (var1 < 0) {
						var3 -= var7 * var1;
						var4 -= var8 * var1;
						var1 = 0;
					}

					var5 <<= 16;
					if (var2 < 0) {
						var5 -= var9 * var2;
						var2 = 0;
					}

					if ((var1 == var2 || var7 >= var8) && (var1 != var2 || var7 <= var9)) {
						var0 -= var2;
						var2 -= var1;
						var1 = scanline_offsets[var1];

						while (true) {
							--var2;
							if (var2 < 0) {
								while (true) {
									--var0;
									if (var0 < 0) {
										return;
									}

									method1149(colour_buffer, var1, var6, 0, var5 >> 16, var3 >> 16);
									var3 += var7;
									var5 += var9;
									var1 += scanline_width;
								}
							}

							method1149(colour_buffer, var1, var6, 0, var4 >> 16, var3 >> 16);
							var3 += var7;
							var4 += var8;
							var1 += scanline_width;
						}
					} else {
						var0 -= var2;
						var2 -= var1;
						var1 = scanline_offsets[var1];

						while (true) {
							--var2;
							if (var2 < 0) {
								while (true) {
									--var0;
									if (var0 < 0) {
										return;
									}

									method1149(colour_buffer, var1, var6, 0, var3 >> 16, var5 >> 16);
									var3 += var7;
									var5 += var9;
									var1 += scanline_width;
								}
							}

							method1149(colour_buffer, var1, var6, 0, var3 >> 16, var4 >> 16);
							var3 += var7;
							var4 += var8;
							var1 += scanline_width;
						}
					}
				} else {
					var5 = var4 <<= 16;
					if (var1 < 0) {
						var5 -= var7 * var1;
						var4 -= var8 * var1;
						var1 = 0;
					}

					var3 <<= 16;
					if (var0 < 0) {
						var3 -= var9 * var0;
						var0 = 0;
					}

					if (var7 < var8) {
						var2 -= var0;
						var0 -= var1;
						var1 = scanline_offsets[var1];

						while (true) {
							--var0;
							if (var0 < 0) {
								while (true) {
									--var2;
									if (var2 < 0) {
										return;
									}

									method1149(colour_buffer, var1, var6, 0, var3 >> 16, var4 >> 16);
									var3 += var9;
									var4 += var8;
									var1 += scanline_width;
								}
							}

							method1149(colour_buffer, var1, var6, 0, var5 >> 16, var4 >> 16);
							var5 += var7;
							var4 += var8;
							var1 += scanline_width;
						}
					} else {
						var2 -= var0;
						var0 -= var1;
						var1 = scanline_offsets[var1];

						while (true) {
							--var0;
							if (var0 < 0) {
								while (true) {
									--var2;
									if (var2 < 0) {
										return;
									}

									method1149(colour_buffer, var1, var6, 0, var4 >> 16, var3 >> 16);
									var3 += var9;
									var4 += var8;
									var1 += scanline_width;
								}
							}

							method1149(colour_buffer, var1, var6, 0, var4 >> 16, var5 >> 16);
							var5 += var7;
							var4 += var8;
							var1 += scanline_width;
						}
					}
				}
			}
		} else if (var2 < clip_height) {
			if (var0 > clip_height) {
				var0 = clip_height;
			}

			if (var1 > clip_height) {
				var1 = clip_height;
			}

			if (var0 < var1) {
				var4 = var5 <<= 16;
				if (var2 < 0) {
					var4 -= var8 * var2;
					var5 -= var9 * var2;
					var2 = 0;
				}

				var3 <<= 16;
				if (var0 < 0) {
					var3 -= var7 * var0;
					var0 = 0;
				}

				if (var8 < var9) {
					var1 -= var0;
					var0 -= var2;
					var2 = scanline_offsets[var2];

					while (true) {
						--var0;
						if (var0 < 0) {
							while (true) {
								--var1;
								if (var1 < 0) {
									return;
								}

								method1149(colour_buffer, var2, var6, 0, var4 >> 16, var3 >> 16);
								var4 += var8;
								var3 += var7;
								var2 += scanline_width;
							}
						}

						method1149(colour_buffer, var2, var6, 0, var4 >> 16, var5 >> 16);
						var4 += var8;
						var5 += var9;
						var2 += scanline_width;
					}
				} else {
					var1 -= var0;
					var0 -= var2;
					var2 = scanline_offsets[var2];

					while (true) {
						--var0;
						if (var0 < 0) {
							while (true) {
								--var1;
								if (var1 < 0) {
									return;
								}

								method1149(colour_buffer, var2, var6, 0, var3 >> 16, var4 >> 16);
								var4 += var8;
								var3 += var7;
								var2 += scanline_width;
							}
						}

						method1149(colour_buffer, var2, var6, 0, var5 >> 16, var4 >> 16);
						var4 += var8;
						var5 += var9;
						var2 += scanline_width;
					}
				}
			} else {
				var3 = var5 <<= 16;
				if (var2 < 0) {
					var3 -= var8 * var2;
					var5 -= var9 * var2;
					var2 = 0;
				}

				var4 <<= 16;
				if (var1 < 0) {
					var4 -= var7 * var1;
					var1 = 0;
				}

				if (var8 < var9) {
					var0 -= var1;
					var1 -= var2;
					var2 = scanline_offsets[var2];

					while (true) {
						--var1;
						if (var1 < 0) {
							while (true) {
								--var0;
								if (var0 < 0) {
									return;
								}

								method1149(colour_buffer, var2, var6, 0, var4 >> 16, var5 >> 16);
								var4 += var7;
								var5 += var9;
								var2 += scanline_width;
							}
						}

						method1149(colour_buffer, var2, var6, 0, var3 >> 16, var5 >> 16);
						var3 += var8;
						var5 += var9;
						var2 += scanline_width;
					}
				} else {
					var0 -= var1;
					var1 -= var2;
					var2 = scanline_offsets[var2];

					while (true) {
						--var1;
						if (var1 < 0) {
							while (true) {
								--var0;
								if (var0 < 0) {
									return;
								}

								method1149(colour_buffer, var2, var6, 0, var5 >> 16, var4 >> 16);
								var4 += var7;
								var5 += var9;
								var2 += scanline_width;
							}
						}

						method1149(colour_buffer, var2, var6, 0, var5 >> 16, var3 >> 16);
						var3 += var8;
						var5 += var9;
						var2 += scanline_width;
					}
				}
			}
		}
	}

	private void method1149(int[] var0, int var1, int var2, int var3, int var4, int var5) {
		if (clip_edges) {
			if (var5 > clip_width) {
				var5 = clip_width;
			}

			if (var4 < 0) {
				var4 = 0;
			}
		}

		if (var4 < var5) {
			var1 += var4;
			var3 = var5 - var4 >> 2;
			if (blending_alpha == 0) {
				while (true) {
					--var3;
					if (var3 < 0) {
						var3 = var5 - var4 & 3;

						while (true) {
							--var3;
							if (var3 < 0) {
								return;
							}

							var0[var1++] = var2;
						}
					}

					var0[var1++] = var2;
					var0[var1++] = var2;
					var0[var1++] = var2;
					var0[var1++] = var2;
				}
			} else if (blending_alpha == 254) {
				while (true) {
					--var3;
					if (var3 < 0) {
						var3 = var5 - var4 & 3;

						while (true) {
							--var3;
							if (var3 < 0) {
								return;
							}

							var0[var1++] = var0[var1];
						}
					}

					var0[var1++] = var0[var1];
					var0[var1++] = var0[var1];
					var0[var1++] = var0[var1];
					var0[var1++] = var0[var1];
				}
			} else {
				int var6 = blending_alpha;
				int var7 = 256 - blending_alpha;
				var2 = ((var2 & 16711935) * var7 >> 8 & 16711935) + ((var2 & '\uff00') * var7 >> 8 & '\uff00');

				while (true) {
					--var3;
					int var8;
					if (var3 < 0) {
						var3 = var5 - var4 & 3;

						while (true) {
							--var3;
							if (var3 < 0) {
								return;
							}

							var8 = var0[var1];
							var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
						}
					}

					var8 = var0[var1];
					var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
					var8 = var0[var1];
					var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
					var8 = var0[var1];
					var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
					var8 = var0[var1];
					var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
				}
			}
		}
	}

	public void method1138(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18) {
		MaterialRaw material = materials.get_material(var18 & 0xffff);
		int[] var19 = textures.get_pixels(material, brightness);
		int var20;
		if (var19 != null && blending_alpha <= 10) {
			small_texture = material.is_small;
			alpha_opaque = material.is_alpha_opaque();
			var20 = var4 - var3;
			int var21 = var1 - var0;
			int var22 = var5 - var3;
			int var23 = var2 - var0;
			int var24 = var7 - var6;
			int var25 = var8 - var6;
			int var26 = 0;
			if (var1 != var0) {
				var26 = (var4 - var3 << 16) / (var1 - var0);
			}

			int var27 = 0;
			if (var2 != var1) {
				var27 = (var5 - var4 << 16) / (var2 - var1);
			}

			int var28 = 0;
			if (var2 != var0) {
				var28 = (var3 - var5 << 16) / (var0 - var2);
			}

			int var29 = var20 * var23 - var22 * var21;
			if (var29 != 0) {
				int var30 = (var24 * var23 - var25 * var21 << 9) / var29;
				int var31 = (var25 * var20 - var24 * var22 << 9) / var29;
				var10 = var9 - var10;
				var13 = var12 - var13;
				var16 = var15 - var16;
				var11 -= var9;
				var14 -= var12;
				var17 -= var15;
				int var32 = var11 * var12 - var14 * var9 << 14;
				int var33 = var14 * var15 - var17 * var12 << 8;
				int var34 = var17 * var9 - var11 * var15 << 5;
				int var35 = var10 * var12 - var13 * var9 << 14;
				int var36 = var13 * var15 - var16 * var12 << 8;
				int var37 = var16 * var9 - var10 * var15 << 5;
				int var38 = var13 * var11 - var10 * var14 << 14;
				int var39 = var16 * var14 - var13 * var17 << 8;
				int var40 = var10 * var17 - var16 * var11 << 5;
				int var41;
				if (var0 <= var1 && var0 <= var2) {
					if (var0 < clip_height) {
						if (var1 > clip_height) {
							var1 = clip_height;
						}

						if (var2 > clip_height) {
							var2 = clip_height;
						}

						var6 = (var6 << 9) - var30 * var3 + var30;
						if (var1 < var2) {
							var5 = var3 <<= 16;
							if (var0 < 0) {
								var5 -= var28 * var0;
								var3 -= var26 * var0;
								var6 -= var31 * var0;
								var0 = 0;
							}

							var4 <<= 16;
							if (var1 < 0) {
								var4 -= var27 * var1;
								var1 = 0;
							}

							var41 = var0 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if ((var0 == var1 || var28 >= var26) && (var0 != var1 || var28 <= var27)) {
								var2 -= var1;
								var1 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var1;
									if (var1 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var0, var4 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var28;
											var4 += var27;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var5 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var2 -= var1;
								var1 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var1;
									if (var1 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var0, var5 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var28;
											var4 += var27;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var5 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						} else {
							var4 = var3 <<= 16;
							if (var0 < 0) {
								var4 -= var28 * var0;
								var3 -= var26 * var0;
								var6 -= var31 * var0;
								var0 = 0;
							}

							var5 <<= 16;
							if (var2 < 0) {
								var5 -= var27 * var2;
								var2 = 0;
							}

							var41 = var0 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if ((var0 == var2 || var28 >= var26) && (var0 != var2 || var27 <= var26)) {
								var1 -= var2;
								var2 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var1;
											if (var1 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var27;
											var3 += var26;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var0, var3 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var4 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var1 -= var2;
								var2 -= var0;
								var0 = scanline_offsets[var0];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var1;
											if (var1 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
											var5 += var27;
											var3 += var26;
											var6 += var31;
											var0 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var0, var4 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
									var4 += var28;
									var3 += var26;
									var6 += var31;
									var0 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						}
					}
				} else if (var1 <= var2) {
					if (var1 < clip_height) {
						if (var2 > clip_height) {
							var2 = clip_height;
						}

						if (var0 > clip_height) {
							var0 = clip_height;
						}

						var7 = (var7 << 9) - var30 * var4 + var30;
						if (var2 < var0) {
							var3 = var4 <<= 16;
							if (var1 < 0) {
								var3 -= var26 * var1;
								var4 -= var27 * var1;
								var7 -= var31 * var1;
								var1 = 0;
							}

							var5 <<= 16;
							if (var2 < 0) {
								var5 -= var28 * var2;
								var2 = 0;
							}

							var41 = var1 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if ((var1 == var2 || var26 >= var27) && (var1 != var2 || var26 <= var28)) {
								var0 -= var2;
								var2 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var0;
											if (var0 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var1, var5 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var26;
											var5 += var28;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var3 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var0 -= var2;
								var2 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var2;
									if (var2 < 0) {
										while (true) {
											--var0;
											if (var0 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var1, var3 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var26;
											var5 += var28;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var3 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						} else {
							var5 = var4 <<= 16;
							if (var1 < 0) {
								var5 -= var26 * var1;
								var4 -= var27 * var1;
								var7 -= var31 * var1;
								var1 = 0;
							}

							var3 <<= 16;
							if (var0 < 0) {
								var3 -= var28 * var0;
								var0 = 0;
							}

							var41 = var1 - center_y;
							var32 += var34 * var41;
							var35 += var37 * var41;
							var38 += var40 * var41;
							if (var26 < var27) {
								var2 -= var0;
								var0 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var0;
									if (var0 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var28;
											var4 += var27;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var1, var5 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var5 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							} else {
								var2 -= var0;
								var0 -= var1;
								var1 = scanline_offsets[var1];

								while (true) {
									--var0;
									if (var0 < 0) {
										while (true) {
											--var2;
											if (var2 < 0) {
												return;
											}

											method1142(colour_buffer, var19, 0, 0, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
											var3 += var28;
											var4 += var27;
											var7 += var31;
											var1 += scanline_width;
											var32 += var34;
											var35 += var37;
											var38 += var40;
										}
									}

									method1142(colour_buffer, var19, 0, 0, var1, var4 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
									var5 += var26;
									var4 += var27;
									var7 += var31;
									var1 += scanline_width;
									var32 += var34;
									var35 += var37;
									var38 += var40;
								}
							}
						}
					}
				} else if (var2 < clip_height) {
					if (var0 > clip_height) {
						var0 = clip_height;
					}

					if (var1 > clip_height) {
						var1 = clip_height;
					}

					var8 = (var8 << 9) - var30 * var5 + var30;
					if (var0 < var1) {
						var4 = var5 <<= 16;
						if (var2 < 0) {
							var4 -= var27 * var2;
							var5 -= var28 * var2;
							var8 -= var31 * var2;
							var2 = 0;
						}

						var3 <<= 16;
						if (var0 < 0) {
							var3 -= var26 * var0;
							var0 = 0;
						}

						var41 = var2 - center_y;
						var32 += var34 * var41;
						var35 += var37 * var41;
						var38 += var40 * var41;
						if (var27 < var28) {
							var1 -= var0;
							var0 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var0;
								if (var0 < 0) {
									while (true) {
										--var1;
										if (var1 < 0) {
											return;
										}

										method1142(colour_buffer, var19, 0, 0, var2, var4 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var27;
										var3 += var26;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1142(colour_buffer, var19, 0, 0, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var4 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						} else {
							var1 -= var0;
							var0 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var0;
								if (var0 < 0) {
									while (true) {
										--var1;
										if (var1 < 0) {
											return;
										}

										method1142(colour_buffer, var19, 0, 0, var2, var3 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var27;
										var3 += var26;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1142(colour_buffer, var19, 0, 0, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var4 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						}
					} else {
						var3 = var5 <<= 16;
						if (var2 < 0) {
							var3 -= var27 * var2;
							var5 -= var28 * var2;
							var8 -= var31 * var2;
							var2 = 0;
						}

						var4 <<= 16;
						if (var1 < 0) {
							var4 -= var26 * var1;
							var1 = 0;
						}

						var41 = var2 - center_y;
						var32 += var34 * var41;
						var35 += var37 * var41;
						var38 += var40 * var41;
						if (var27 < var28) {
							var0 -= var1;
							var1 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var1;
								if (var1 < 0) {
									while (true) {
										--var0;
										if (var0 < 0) {
											return;
										}

										method1142(colour_buffer, var19, 0, 0, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var26;
										var5 += var28;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1142(colour_buffer, var19, 0, 0, var2, var3 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var3 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						} else {
							var0 -= var1;
							var1 -= var2;
							var2 = scanline_offsets[var2];

							while (true) {
								--var1;
								if (var1 < 0) {
									while (true) {
										--var0;
										if (var0 < 0) {
											return;
										}

										method1142(colour_buffer, var19, 0, 0, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
										var4 += var26;
										var5 += var28;
										var8 += var31;
										var2 += scanline_width;
										var32 += var34;
										var35 += var37;
										var38 += var40;
									}
								}

								method1142(colour_buffer, var19, 0, 0, var2, var5 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
								var3 += var27;
								var5 += var28;
								var8 += var31;
								var2 += scanline_width;
								var32 += var34;
								var35 += var37;
								var38 += var40;
							}
						}
					}
				}
			}
		} else {
			var20 = material.get_colour();
			method1154(var0, var1, var2, var3, var4, var5, repack_lightness(var20, var6), repack_lightness(var20, var7), repack_lightness(var20, var8));
			invalid_texture = true;
		}
	}

	private void method1142(int[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14) {
		if (clip_edges) {
			if (var6 > clip_width) {
				var6 = clip_width;
			}

			if (var5 < 0) {
				var5 = 0;
			}
		}

		if (var5 < var6) {
			var4 += var5;
			var7 += var8 * var5;
			int var17 = var6 - var5;
			int var15;
			int var16;
			int var19;
			int var18;
			int var21;
			int var20;
			int var23;
			int var22;
			int var10000;
			if (small_texture) {
				var23 = var5 - center_x;
				var9 += (var12 >> 3) * var23;
				var10 += (var13 >> 3) * var23;
				var11 += (var14 >> 3) * var23;
				var22 = var11 >> 12;
				if (var22 != 0) {
					var18 = var9 / var22;
					var19 = var10 / var22;
				} else {
					var18 = 0;
					var19 = 0;
				}

				var9 += var12;
				var10 += var13;
				var11 += var14;
				var22 = var11 >> 12;
				if (var22 != 0) {
					var20 = var9 / var22;
					var21 = var10 / var22;
				} else {
					var20 = 0;
					var21 = 0;
				}

				var2 = (var18 << 20) + var19;
				var16 = (var20 - var18 >> 3 << 20) + (var21 - var19 >> 3);
				var17 >>= 3;
				var8 <<= 3;
				var15 = var7 >> 8;
				if (alpha_opaque) {
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var10000 = var2 + var16;
							var18 = var20;
							var19 = var21;
							var9 += var12;
							var10 += var13;
							var11 += var14;
							var22 = var11 >> 12;
							if (var22 != 0) {
								var20 = var9 / var22;
								var21 = var10 / var22;
							} else {
								var20 = 0;
								var21 = 0;
							}

							var2 = (var18 << 20) + var19;
							var16 = (var20 - var18 >> 3 << 20) + (var21 - var19 >> 3);
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 4032) + (var2 >>> 26)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				} else {
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var10000 = var2 + var16;
							var18 = var20;
							var19 = var21;
							var9 += var12;
							var10 += var13;
							var11 += var14;
							var22 = var11 >> 12;
							if (var22 != 0) {
								var20 = var9 / var22;
								var21 = var10 / var22;
							} else {
								var20 = 0;
								var21 = 0;
							}

							var2 = (var18 << 20) + var19;
							var16 = (var20 - var18 >> 3 << 20) + (var21 - var19 >> 3);
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				}
			} else {
				var23 = var5 - center_x;
				var9 += (var12 >> 3) * var23;
				var10 += (var13 >> 3) * var23;
				var11 += (var14 >> 3) * var23;
				var22 = var11 >> 14;
				if (var22 != 0) {
					var18 = var9 / var22;
					var19 = var10 / var22;
				} else {
					var18 = 0;
					var19 = 0;
				}

				var9 += var12;
				var10 += var13;
				var11 += var14;
				var22 = var11 >> 14;
				if (var22 != 0) {
					var20 = var9 / var22;
					var21 = var10 / var22;
				} else {
					var20 = 0;
					var21 = 0;
				}

				var2 = (var18 << 18) + var19;
				var16 = (var20 - var18 >> 3 << 18) + (var21 - var19 >> 3);
				var17 >>= 3;
				var8 <<= 3;
				var15 = var7 >> 8;
				if (alpha_opaque) {
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var10000 = var2 + var16;
							var18 = var20;
							var19 = var21;
							var9 += var12;
							var10 += var13;
							var11 += var14;
							var22 = var11 >> 14;
							if (var22 != 0) {
								var20 = var9 / var22;
								var21 = var10 / var22;
							} else {
								var20 = 0;
								var21 = 0;
							}

							var2 = (var18 << 18) + var19;
							var16 = (var20 - var18 >> 3 << 18) + (var21 - var19 >> 3);
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							var3 = var1[(var2 & 16256) + (var2 >>> 25)];
							var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				} else {
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var10000 = var2 + var16;
							var18 = var20;
							var19 = var21;
							var9 += var12;
							var10 += var13;
							var11 += var14;
							var22 = var11 >> 14;
							if (var22 != 0) {
								var20 = var9 / var22;
								var21 = var10 / var22;
							} else {
								var20 = 0;
								var21 = 0;
							}

							var2 = (var18 << 18) + var19;
							var16 = (var20 - var18 >> 3 << 18) + (var21 - var19 >> 3);
							var7 += var8;
							var15 = var7 >> 8;
							--var17;
						} while (var17 > 0);
					}

					var17 = var6 - var5 & 7;
					if (var17 > 0) {
						do {
							if ((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
								var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
							}

							++var4;
							var2 += var16;
							--var17;
						} while (var17 > 0);
					}
				}
			}

		}
	}

	private int repack_lightness(int var0, int var1) {
		var1 = var1 * (var0 & 127) >> 7;
		if (var1 < 2) {
			var1 = 2;
		} else if (var1 > 126) {
			var1 = 126;
		}

		return (var0 & '\uff80') + var1;
	}

}
