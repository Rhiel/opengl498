package com.jagex.graphics.runetek4.software.sprite;

import com.jagex.Rasterizer2D;
import com.jagex.graphics.runetek4.media.PaletteSprite;

public class SoftwarePaletteSprite extends PaletteSprite {

	public byte[] pixels;
	public int[] palette;

	public SoftwarePaletteSprite(int var1, int var2, int var3, int var4, int var5, int var6, byte[] var7, int[] var8) {
		trim_width = var1;
		trim_height = var2;
		offset_x = var3;
		offset_y = var4;
		width = var5;
		height = var6;
		pixels = var7;
		palette = var8;
	}

	public SoftwarePaletteSprite(int var1, int var2, int var3) {
		trim_width = width = var1;
		trim_height = height = var2;
		offset_x = offset_y = 0;
		pixels = new byte[var1 * var2];
		palette = new int[var3];
	}

	@Override
	public void draw(int x, int y) {
		x += offset_x;
		y += offset_y;
		int var3 = x + y * Rasterizer2D.width;
		int var4 = 0;
		int var5 = height;
		int var6 = width;
		int var7 = Rasterizer2D.width - var6;
		int var8 = 0;
		int var9;
		if (y < Rasterizer2D.clip_top) {
			var9 = Rasterizer2D.clip_top - y;
			var5 -= var9;
			y = Rasterizer2D.clip_top;
			var4 += var9 * var6;
			var3 += var9 * Rasterizer2D.width;
		}
		if (y + var5 > Rasterizer2D.clip_bottom) {
			var5 -= y + var5 - Rasterizer2D.clip_bottom;
		}
		if (x < Rasterizer2D.clip_left) {
			var9 = Rasterizer2D.clip_left - x;
			var6 -= var9;
			x = Rasterizer2D.clip_left;
			var4 += var9;
			var3 += var9;
			var8 += var9;
			var7 += var9;
		}
		if (x + var6 > Rasterizer2D.clip_right) {
			var9 = x + var6 - Rasterizer2D.clip_right;
			var6 -= var9;
			var8 += var9;
			var7 += var9;
		}

		if (var6 > 0 && var5 > 0) {
			plot_block_opaque(Rasterizer2D.colour_buffer, pixels, palette, 0, var4, var3, var6, var5, var7, var8);
		}
	}

	@Override
	public void draw(int x, int y, int alpha) {
		x += offset_x;
		y += offset_y;
		int var4 = x + y * Rasterizer2D.width;
		int var5 = 0;
		int var6 = height;
		int var7 = width;
		int var8 = Rasterizer2D.width - var7;
		int var9 = 0;
		int var10;
		if (y < Rasterizer2D.clip_top) {
			var10 = Rasterizer2D.clip_top - y;
			var6 -= var10;
			y = Rasterizer2D.clip_top;
			var5 += var10 * var7;
			var4 += var10 * Rasterizer2D.width;
		}

		if (y + var6 > Rasterizer2D.clip_bottom) {
			var6 -= y + var6 - Rasterizer2D.clip_bottom;
		}

		if (x < Rasterizer2D.clip_left) {
			var10 = Rasterizer2D.clip_left - x;
			var7 -= var10;
			x = Rasterizer2D.clip_left;
			var5 += var10;
			var4 += var10;
			var9 += var10;
			var8 += var10;
		}

		if (x + var7 > Rasterizer2D.clip_right) {
			var10 = x + var7 - Rasterizer2D.clip_right;
			var7 -= var10;
			var9 += var10;
			var8 += var10;
		}

		if (var7 > 0 && var6 > 0) {
			plot_translucent_block(Rasterizer2D.colour_buffer, pixels, palette, var5, var4, var7, var6, var8, var9, alpha);
		}
	}

	public void draw(int x, int y, int width, int height) {
		int var5 = this.width;
		int var6 = this.height;
		int var7 = 0;
		int var8 = 0;
		int var9 = trim_width;
		int var10 = trim_height;
		int var11 = (var9 << 16) / width;
		int var12 = (var10 << 16) / height;
		int var13;
		if (offset_x > 0) {
			var13 = ((offset_x << 16) + var11 - 1) / var11;
			x += var13;
			var7 += var13 * var11 - (offset_x << 16);
		}

		if (offset_y > 0) {
			var13 = ((offset_y << 16) + var12 - 1) / var12;
			y += var13;
			var8 += var13 * var12 - (offset_y << 16);
		}

		if (var5 < var9) {
			width = ((var5 << 16) - var7 + var11 - 1) / var11;
		}

		if (var6 < var10) {
			height = ((var6 << 16) - var8 + var12 - 1) / var12;
		}

		var13 = x + y * Rasterizer2D.width;
		int var14 = Rasterizer2D.width - width;
		if (y + height > Rasterizer2D.clip_bottom) {
			height -= y + height - Rasterizer2D.clip_bottom;
		}

		int var15;
		if (y < Rasterizer2D.clip_top) {
			var15 = Rasterizer2D.clip_top - y;
			height -= var15;
			var13 += var15 * Rasterizer2D.width;
			var8 += var12 * var15;
		}

		if (x + width > Rasterizer2D.clip_right) {
			var15 = x + width - Rasterizer2D.clip_right;
			width -= var15;
			var14 += var15;
		}

		if (x < Rasterizer2D.clip_left) {
			var15 = Rasterizer2D.clip_left - x;
			width -= var15;
			var13 += var15;
			var7 += var11 * var15;
			var14 += var15;
		}

		plot_scaled(Rasterizer2D.colour_buffer, pixels, palette, var7, var8, var13, var14, width, height, var11, var12, var5);
	}

	public void draw(int x, int y, int width, int height, int colour) {
		int var6 = this.width;
		int var7 = this.height;
		int var8 = 0;
		int var9 = 0;
		int var10 = trim_width;
		int var11 = trim_height;
		int var12 = (var10 << 16) / width;
		int var13 = (var11 << 16) / height;
		int var14;
		if (offset_x > 0) {
			var14 = ((offset_x << 16) + var12 - 1) / var12;
			x += var14;
			var8 += var14 * var12 - (offset_x << 16);
		}

		if (offset_y > 0) {
			var14 = ((offset_y << 16) + var13 - 1) / var13;
			y += var14;
			var9 += var14 * var13 - (offset_y << 16);
		}

		if (var6 < var10) {
			width = ((var6 << 16) - var8 + var12 - 1) / var12;
		}

		if (var7 < var11) {
			height = ((var7 << 16) - var9 + var13 - 1) / var13;
		}

		var14 = x + y * Rasterizer2D.width;
		int var15 = Rasterizer2D.width - width;
		if (y + height > Rasterizer2D.clip_bottom) {
			height -= y + height - Rasterizer2D.clip_bottom;
		}

		int var16;
		if (y < Rasterizer2D.clip_top) {
			var16 = Rasterizer2D.clip_top - y;
			height -= var16;
			var14 += var16 * Rasterizer2D.width;
			var9 += var13 * var16;
		}

		if (x + width > Rasterizer2D.clip_right) {
			var16 = x + width - Rasterizer2D.clip_right;
			width -= var16;
			var15 += var16;
		}

		if (x < Rasterizer2D.clip_left) {
			var16 = Rasterizer2D.clip_left - x;
			width -= var16;
			var14 += var16;
			var8 += var12 * var16;
			var15 += var16;
		}

		plot_scaled(Rasterizer2D.colour_buffer, pixels, palette, var8, var9, var14, var15, width, height, var12, var13, var6, colour);
	}

	public void tint(int tint_red, int tint_green, int tint_blue) {
		for (int index = 0; index < palette.length; index++) {
			int red = palette[index] >> 16 & 0xff;
			red += tint_red;
			if (red < 0) {
				red = 0;
			} else if (red > 0xff) {
				red = 0xff;
			}
			int green = palette[index] >> 8 & 0xff;
			green += tint_green;
			if (green < 0) {
				green = 0;
			} else if (green > 0xff) {
				green = 0xff;
			}
			int blue = palette[index] & 0xff;
			blue += tint_blue;
			if (blue < 0) {
				blue = 0;
			} else if (blue > 0xff) {
				blue = 0xff;
			}
			palette[index] = (red << 16) + (green << 8) + blue;
		}

	}

	public void explode_size() {
		byte[] var1 = new byte[width * height];
		int var2 = 0;
		int var3;
		for (var3 = 0; var3 < width; var3++) {
			for (int var4 = height - 1; var4 >= 0; --var4) {
				var1[var2++] = pixels[var3 + var4 * width];
			}
		}
		pixels = var1;
		var3 = offset_y;
		offset_y = offset_x;
		offset_x = trim_height - height - var3;
		var3 = height;
		height = width;
		width = var3;
		var3 = trim_height;
		trim_height = trim_width;
		trim_width = var3;
	}

	public void trim_size() {
		if (width != trim_width || height != trim_height) {
			byte[] var1 = new byte[trim_width * trim_height];
			int var2 = 0;

			for (int var3 = 0; var3 < height; var3++) {
				for (int var4 = 0; var4 < width; var4++) {
					var1[var4 + offset_x + (var3 + offset_y) * trim_width] = pixels[var2++];
				}
			}
			pixels = var1;
			width = trim_width;
			height = trim_height;
			offset_x = 0;
			offset_y = 0;
		}
	}

	public void clear() {
		int offset = 0;
		int num_loops = pixels.length - 7;
		while (offset < num_loops) {
			pixels[offset++] = 0;
			pixels[offset++] = 0;
			pixels[offset++] = 0;
			pixels[offset++] = 0;
			pixels[offset++] = 0;
			pixels[offset++] = 0;
			pixels[offset++] = 0;
			pixels[offset++] = 0;
		}
		num_loops += 7;
		while (offset < num_loops) {
			pixels[offset++] = 0;
		}
	}

	public static final void plot_scaled(int[] var0, byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
		int var12 = var3;
		for (int var13 = -var8; var13 < 0; var13++) {
			int var14 = (var4 >> 16) * var11;
			for (int var15 = -var7; var15 < 0; var15++) {
				byte var16 = var1[(var3 >> 16) + var14];
				if (var16 != 0) {
					var0[var5++] = var2[var16 & 0xff];
				} else {
					var5++;
				}

				var3 += var9;
			}
			var4 += var10;
			var3 = var12;
			var5 += var6;
		}
	}

	public static final void plot_scaled(int[] dst_palette, byte[] src_pixels, int[] src_palette, int src_offset, int var4, int dst_offset, int dst_scansize, int height, int var8, int src_scansize, int var10, int width, int dst_rgb) {
		int start_offset = src_offset;
		int dst_red = dst_rgb >> 16 & 0xff;
		int dst_green = dst_rgb >> 8 & 0xff;
		int dst_blue = dst_rgb & 0xff;
		for (int x = -var8; x < 0; x++) {
			int var18 = (var4 >> 16) * width;
			for (int y = -height; y < 0; y++) {
				byte src_pixel = src_pixels[(src_offset >> 16) + var18];
				if (src_pixel != 0) {
					int src_rgb = src_palette[src_pixel & 0xff];
					int src_red = src_rgb >> 16 & 0xff;
					int src_green = src_rgb >> 8 & 0xff;
					int src_blue = src_rgb & 0xff;
					dst_palette[dst_offset++] = (src_red * dst_red >> 8 << 16) + (src_green * dst_green >> 8 << 8) + (src_blue * dst_blue >> 8);
				} else {
					dst_offset++;
				}
				src_offset += src_scansize;
			}
			var4 += var10;
			dst_offset += dst_scansize;
			src_offset = start_offset;
		}

	}

	public static final void plot_block_opaque(int[] dst_palette, byte[] src_pixels, int[] src_palette, int var3, int src_offset, int dst_offset, int width, int height, int dst_scansize, int src_scansize) {
		int num_loops = -(width >> 2);
		int num_left = -(width & 3);
		for (int x = -height; x < 0; x++) {
			for (int y = num_loops; y < 0; y++) {
				byte palette_index = src_pixels[src_offset++];
				if (palette_index != 0) {
					dst_palette[dst_offset++] = src_palette[palette_index & 0xff];
				} else {
					dst_offset++;
				}
				palette_index = src_pixels[src_offset++];
				if (palette_index != 0) {
					dst_palette[dst_offset++] = src_palette[palette_index & 0xff];
				} else {
					dst_offset++;
				}
				palette_index = src_pixels[src_offset++];
				if (palette_index != 0) {
					dst_palette[dst_offset++] = src_palette[palette_index & 0xff];
				} else {
					dst_offset++;
				}
				palette_index = src_pixels[src_offset++];
				if (palette_index != 0) {
					dst_palette[dst_offset++] = src_palette[palette_index & 0xff];
				} else {
					dst_offset++;
				}
			}
			for (int index = num_left; index < 0; index++) {
				int palette_index = src_pixels[src_offset++];
				if (palette_index != 0) {
					dst_palette[dst_offset++] = src_palette[palette_index & 0xff];
				} else {
					dst_offset++;
				}
			}
			dst_offset += dst_scansize;
			src_offset += src_scansize;
		}

	}

	public static final void plot_translucent_block(int[] dst_palette, byte[] src_pixels, int[] src_palette, int src_offset, int dst_offset, int width, int height, int src_scansize, int dst_scansize, int alpha) {
		int dst_alpha = 256 - alpha;
		for (int y = -height; y < 0; y++) {
			for (int x = -width; x < 0; x++) {
				byte palette_index = src_pixels[src_offset++];
				if (palette_index != 0) {
					int src_rgb = src_palette[palette_index & 0xff];
					int dst_rgb = dst_palette[dst_offset];
					dst_palette[dst_offset++] = ((src_rgb & 0xff00ff) * alpha + (dst_rgb & 0xff00ff) * dst_alpha & 0xff00ff00) + ((src_rgb & 0xff00) * alpha + (dst_rgb & 0xff00) * dst_alpha & 0xff0000) >> 8;
				} else {
					dst_offset++;
				}
			}
			dst_offset += src_scansize;
			src_offset += dst_scansize;
		}
	}
}
