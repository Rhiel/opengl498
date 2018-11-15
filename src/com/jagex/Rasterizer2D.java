package com.jagex;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class Rasterizer2D {
	public static int[] colour_buffer;
	public static int width;
	public static int clip_right;
	public static int height;
	public static int clip_top;
	public static int clip_left = 0;
	public static int clip_bottom;
	public static int[] clipping_mask_x;
	public static int[] clipping_mask_y;

	public static final void method200() {
		clip_left = 0;
		clip_top = 0;
		clip_right = width;
		clip_bottom = height;
	}

	public static final void method201(int i, int i_0_, int i_1_, int i_2_, int i_3_) {
		if (i_3_ != 0) {
			if (i_3_ == 256) {
				method205(i, i_0_, i_1_, i_2_);
			} else {
				if (i_1_ < 0) {
					i_1_ = -i_1_;
				}
				int i_4_ = 256 - i_3_;
				int i_5_ = (i_2_ >> 16 & 0xff) * i_3_;
				int i_6_ = (i_2_ >> 8 & 0xff) * i_3_;
				int i_7_ = (i_2_ & 0xff) * i_3_;
				int i_8_ = i_0_ - i_1_;
				if (i_8_ < clip_top) {
					i_8_ = clip_top;
				}
				int i_9_ = i_0_ + i_1_ + 1;
				if (i_9_ > clip_bottom) {
					i_9_ = clip_bottom;
				}
				int i_10_ = i_8_;
				int i_11_ = i_1_ * i_1_;
				int i_12_ = 0;
				int i_13_ = i_0_ - i_10_;
				int i_14_ = i_13_ * i_13_;
				int i_15_ = i_14_ - i_13_;
				if (i_0_ > i_9_) {
					i_0_ = i_9_;
				}
				while (i_10_ < i_0_) {
					for (/**/; i_15_ <= i_11_ || i_14_ <= i_11_; i_15_ += i_12_++ + i_12_) {
						i_14_ += i_12_ + i_12_;
					}
					int i_16_ = i - i_12_ + 1;
					if (i_16_ < clip_left) {
						i_16_ = clip_left;
					}
					int i_17_ = i + i_12_;
					if (i_17_ > clip_right) {
						i_17_ = clip_right;
					}
					int i_18_ = i_16_ + i_10_ * width;
					for (int i_19_ = i_16_; i_19_ < i_17_; i_19_++) {
						int i_20_ = (colour_buffer[i_18_] >> 16 & 0xff) * i_4_;
						int i_21_ = (colour_buffer[i_18_] >> 8 & 0xff) * i_4_;
						int i_22_ = (colour_buffer[i_18_] & 0xff) * i_4_;
						int i_23_ = (i_5_ + i_20_ >> 8 << 16) + (i_6_ + i_21_ >> 8 << 8) + (i_7_ + i_22_ >> 8);
						colour_buffer[i_18_++] = i_23_;
					}
					i_10_++;
					i_14_ -= i_13_-- + i_13_;
					i_15_ -= i_13_ + i_13_;
				}
				i_12_ = i_1_;
				i_13_ = -i_13_;
				i_15_ = i_13_ * i_13_ + i_11_;
				i_14_ = i_15_ - i_12_;
				i_15_ -= i_13_;
				while (i_10_ < i_9_) {
					for (/**/; i_15_ > i_11_ && i_14_ > i_11_; i_14_ -= i_12_ + i_12_) {
						i_15_ -= i_12_-- + i_12_;
					}
					int i_24_ = i - i_12_;
					if (i_24_ < clip_left) {
						i_24_ = clip_left;
					}
					int i_25_ = i + i_12_;
					if (i_25_ > clip_right - 1) {
						i_25_ = clip_right - 1;
					}
					int i_26_ = i_24_ + i_10_ * width;
					for (int i_27_ = i_24_; i_27_ <= i_25_; i_27_++) {
						int i_28_ = (colour_buffer[i_26_] >> 16 & 0xff) * i_4_;
						int i_29_ = (colour_buffer[i_26_] >> 8 & 0xff) * i_4_;
						int i_30_ = (colour_buffer[i_26_] & 0xff) * i_4_;
						int i_31_ = (i_5_ + i_28_ >> 8 << 16) + (i_6_ + i_29_ >> 8 << 8) + (i_7_ + i_30_ >> 8);
						colour_buffer[i_26_++] = i_31_;
					}
					i_10_++;
					i_15_ += i_13_ + i_13_;
					i_14_ += i_13_++ + i_13_;
				}
			}
		}
	}

	public static final void initialise(int[] colour_buffer,int width, int height) {
		Rasterizer2D.colour_buffer = colour_buffer;
		Rasterizer2D.width = width;
		Rasterizer2D.height = height;
		set_clipping(0, 0, width, height);
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		if (rasterizer != null) {
			rasterizer.initialise(colour_buffer, null, width);
			rasterizer.clip_width = width;
			rasterizer.clip_height = height;
		}
	}

	public static void draw_thin_line(int i, int i_33_, int i_34_, int i_35_, int i_36_) {
		i_34_ -= i;
		i_35_ -= i_33_;
		if (i_35_ == 0) {
			if (i_34_ >= 0) {
				draw_horizontal_line(i, i_33_, i_34_ + 1, i_36_);
			} else {
				draw_horizontal_line(i + i_34_, i_33_, -i_34_ + 1, i_36_);
			}
		} else if (i_34_ == 0) {
			if (i_35_ >= 0) {
				draw_vertical_line(i, i_33_, i_35_ + 1, i_36_);
			} else {
				draw_vertical_line(i, i_33_ + i_35_, -i_35_ + 1, i_36_);
			}
		} else {
			if (i_34_ + i_35_ < 0) {
				i += i_34_;
				i_34_ = -i_34_;
				i_33_ += i_35_;
				i_35_ = -i_35_;
			}
			if (i_34_ > i_35_) {
				i_33_ <<= 16;
				i_33_ += 32768;
				i_35_ <<= 16;
				int i_37_ = (int) Math.floor((double) i_35_ / (double) i_34_ + 0.5);
				i_34_ += i;
				if (i < clip_left) {
					i_33_ += i_37_ * (clip_left - i);
					i = clip_left;
				}
				if (i_34_ >= clip_right) {
					i_34_ = clip_right - 1;
				}
				for (/**/; i <= i_34_; i++) {
					int i_38_ = i_33_ >> 16;
					if (i_38_ >= clip_top && i_38_ < clip_bottom) {
						colour_buffer[i + i_38_ * width] = i_36_;
					}
					i_33_ += i_37_;
				}
			} else {
				i <<= 16;
				i += 32768;
				i_34_ <<= 16;
				int i_39_ = (int) Math.floor((double) i_34_ / (double) i_35_ + 0.5);
				i_35_ += i_33_;
				if (i_33_ < clip_top) {
					i += i_39_ * (clip_top - i_33_);
					i_33_ = clip_top;
				}
				if (i_35_ >= clip_bottom) {
					i_35_ = clip_bottom - 1;
				}
				for (/**/; i_33_ <= i_35_; i_33_++) {
					int i_40_ = i >> 16;
					if (i_40_ >= clip_left && i_40_ < clip_right) {
						colour_buffer[i_40_ + i_33_ * width] = i_36_;
					}
					i += i_39_;
				}
			}
		}
	}

	static final void accumlate_clipping(int i, int i_41_, int i_42_, int i_43_) {
		if (clip_left < i) {
			clip_left = i;
		}
		if (clip_top < i_41_) {
			clip_top = i_41_;
		}
		if (clip_right > i_42_) {
			clip_right = i_42_;
		}
		if (clip_bottom > i_43_) {
			clip_bottom = i_43_;
		}
	}

	public static final void method205(int i, int i_44_, int i_45_, int i_46_) {
		if (i_45_ == 0) {
			method206(i, i_44_, i_46_);
		} else {
			if (i_45_ < 0) {
				i_45_ = -i_45_;
			}
			int i_47_ = i_44_ - i_45_;
			if (i_47_ < clip_top) {
				i_47_ = clip_top;
			}
			int i_48_ = i_44_ + i_45_ + 1;
			if (i_48_ > clip_bottom) {
				i_48_ = clip_bottom;
			}
			int i_49_ = i_47_;
			int i_50_ = i_45_ * i_45_;
			int i_51_ = 0;
			int i_52_ = i_44_ - i_49_;
			int i_53_ = i_52_ * i_52_;
			int i_54_ = i_53_ - i_52_;
			if (i_44_ > i_48_) {
				i_44_ = i_48_;
			}
			while (i_49_ < i_44_) {
				for (/**/; i_54_ <= i_50_ || i_53_ <= i_50_; i_54_ += i_51_++ + i_51_) {
					i_53_ += i_51_ + i_51_;
				}
				int i_55_ = i - i_51_ + 1;
				if (i_55_ < clip_left) {
					i_55_ = clip_left;
				}
				int i_56_ = i + i_51_;
				if (i_56_ > clip_right) {
					i_56_ = clip_right;
				}
				int i_57_ = i_55_ + i_49_ * width;
				for (int i_58_ = i_55_; i_58_ < i_56_; i_58_++) {
					colour_buffer[i_57_++] = i_46_;
				}
				i_49_++;
				i_53_ -= i_52_-- + i_52_;
				i_54_ -= i_52_ + i_52_;
			}
			i_51_ = i_45_;
			i_52_ = i_49_ - i_44_;
			i_54_ = i_52_ * i_52_ + i_50_;
			i_53_ = i_54_ - i_51_;
			i_54_ -= i_52_;
			while (i_49_ < i_48_) {
				for (/**/; i_54_ > i_50_ && i_53_ > i_50_; i_53_ -= i_51_ + i_51_) {
					i_54_ -= i_51_-- + i_51_;
				}
				int i_59_ = i - i_51_;
				if (i_59_ < clip_left) {
					i_59_ = clip_left;
				}
				int i_60_ = i + i_51_;
				if (i_60_ > clip_right - 1) {
					i_60_ = clip_right - 1;
				}
				int i_61_ = i_59_ + i_49_ * width;
				for (int i_62_ = i_59_; i_62_ <= i_60_; i_62_++) {
					colour_buffer[i_61_++] = i_46_;
				}
				i_49_++;
				i_54_ += i_52_ + i_52_;
				i_53_ += i_52_++ + i_52_;
			}
		}
	}

	public static final void method206(int i, int i_63_, int i_64_) {
		if (i >= clip_left && i_63_ >= clip_top && i < clip_right && i_63_ < clip_bottom) {
			colour_buffer[i + i_63_ * width] = i_64_;
		}
	}

	public static final void draw_horizontal_line(int l, int i, int k, int j) {
		if (i >= clip_top && i < clip_bottom) {
			if (l < clip_left) {
				k -= clip_left - l;
				l = clip_left;
			}
			if (l + k > clip_right) {
				k = clip_right - l;
			}
			int i1 = l + i * width;
			for (int j1 = 0; j1 < k; j1++) {
				colour_buffer[i1 + j1] = j;
			}
		}
	}

	public static final void fill_rectangle_gradient(int x, int y, int width, int height, int start_colour, int end_colour) {
		int i_75_ = 0;
		int i_76_ = 65536 / height;
		if (x < clip_left) {
			width -= clip_left - x;
			x = clip_left;
		}
		if (y < clip_top) {
			i_75_ += (clip_top - y) * i_76_;
			height -= clip_top - y;
			y = clip_top;
		}
		if (x + width > clip_right) {
			width = clip_right - x;
		}
		if (y + height > clip_bottom) {
			height = clip_bottom - y;
		}
		int i_77_ = Rasterizer2D.width - width;
		int dst_offset = x + y * Rasterizer2D.width;
		for (int i_79_ = -height; i_79_ < 0; i_79_++) {
			int i_80_ = 65536 - i_75_ >> 8;
			int i_81_ = i_75_ >> 8;
			int result = ((start_colour & 0xff00ff) * i_80_ + (end_colour & 0xff00ff) * i_81_ & ~0xff00ff) + ((start_colour & 0xff00) * i_80_ + (end_colour & 0xff00) * i_81_ & 0xff0000) >>> 8;
			for (int i_83_ = -width; i_83_ < 0; i_83_++) {
				colour_buffer[dst_offset++] = result;
			}
			dst_offset += i_77_;
			i_75_ += i_76_;
		}
	}

	public static void draw_thick_line(int start_x, int start_y, int end_x, int end_y, int colour, int thickness) {
		int width = end_x - start_x;
		int height = end_y - start_y;
		int fixedWidth = width >= 0 ? width : -width;
		int fixedHeight = height >= 0 ? height : -height;
		int dimension = fixedWidth;
		if (dimension < fixedHeight) {
			dimension = fixedHeight;
		}
		if (dimension != 0) {
			final Rasterizer rasterizer = GraphicTools.get_rasterizer();
			int lineX = (width << 16) / dimension;
			int lineY = (height << 16) / dimension;
			if (lineY <= lineX) {
				lineX = -lineX;
			} else {
				lineY = -lineY;
			}
			int lineStartY = thickness * lineY >> 17;
			int lineEndY = thickness * lineY + 1 >> 17;
			int lineStartX = thickness * lineX >> 17;
			int lineEndX = thickness * lineX + 1 >> 17;
			start_x -= rasterizer.get_current_column();
			start_y -= rasterizer.get_current_row();
			int x_a = start_x + lineStartY;
			int x_b = start_x - lineEndY;
			int x_c = start_x + width - lineEndY;
			int x_d = start_x + width + lineStartY;
			int y_a = start_y + lineStartX;
			int y_b = start_y - lineEndX;
			int y_c = start_y + height - lineEndX;
			int y_d = start_y + height + lineStartX;
			rasterizer.clip_edges(x_a, x_b, x_c);
			rasterizer.draw_flat_triangle(false, true, false, y_a, y_b, y_c, x_a, x_b, x_c, colour, 0, 0, 0);
			rasterizer.clip_edges(x_a, x_c, x_d);
			rasterizer.draw_flat_triangle(false, true, false, y_a, y_c, y_d, x_a, x_c, x_d, colour, 0, 0, 0);
		}
	}

	public static final void set_clipping(int i, int i_108_, int i_109_, int i_110_) {
		if (i < 0) {
			i = 0;
		}
		if (i_108_ < 0) {
			i_108_ = 0;
		}
		if (i_109_ > width) {
			i_109_ = width;
		}
		if (i_110_ > height) {
			i_110_ = height;
		}
		clip_left = i;
		clip_top = i_108_;
		clip_right = i_109_;
		clip_bottom = i_110_;
		reset_clippingmask();
	}

	public static final void draw_vertical_line(int i, int i_111_, int i_112_, int i_113_) {
		if (i >= clip_left && i < clip_right) {
			if (i_111_ < clip_top) {
				i_112_ -= clip_top - i_111_;
				i_111_ = clip_top;
			}
			if (i_111_ + i_112_ > clip_bottom) {
				i_112_ = clip_bottom - i_111_;
			}
			int i_114_ = i + i_111_ * width;
			for (int i_115_ = 0; i_115_ < i_112_; i_115_++) {
				colour_buffer[i_114_ + i_115_ * width] = i_113_;
			}
		}
	}

	public static final void fill_rectangle(int x, int y, int i_117_, int i_118_, int color, int alpha) {
		if (x < clip_left) {
			i_117_ -= clip_left - x;
			x = clip_left;
		}
		if (y < clip_top) {
			i_118_ -= clip_top - y;
			y = clip_top;
		}
		if (x + i_117_ > clip_right) {
			i_117_ = clip_right - x;
		}
		if (y + i_118_ > clip_bottom) {
			i_118_ = clip_bottom - y;
		}
		color = ((color & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((color & 0xff00) * alpha >> 8 & 0xff00);
		int i_121_ = 256 - alpha;
		int i_122_ = width - i_117_;
		int i_123_ = x + y * width;
		for (int i_124_ = 0; i_124_ < i_118_; i_124_++) {
			for (int i_125_ = -i_117_; i_125_ < 0; i_125_++) {
				int i_126_ = colour_buffer[i_123_];
				i_126_ = ((i_126_ & 0xff00ff) * i_121_ >> 8 & 0xff00ff) + ((i_126_ & 0xff00) * i_121_ >> 8 & 0xff00);
				colour_buffer[i_123_++] = color + i_126_;
			}
			i_123_ += i_122_;
		}
	}

	public static final void draw_vertical_line(int i, int i_132_, int i_133_, int i_134_, int i_135_) {
		if (i >= clip_left && i < clip_right) {
			if (i_132_ < clip_top) {
				i_133_ -= clip_top - i_132_;
				i_132_ = clip_top;
			}
			if (i_132_ + i_133_ > clip_bottom) {
				i_133_ = clip_bottom - i_132_;
			}
			int i_136_ = 256 - i_135_;
			int i_137_ = (i_134_ >> 16 & 0xff) * i_135_;
			int i_138_ = (i_134_ >> 8 & 0xff) * i_135_;
			int i_139_ = (i_134_ & 0xff) * i_135_;
			int i_140_ = i + i_132_ * width;
			for (int i_141_ = 0; i_141_ < i_133_; i_141_++) {
				int i_142_ = (colour_buffer[i_140_] >> 16 & 0xff) * i_136_;
				int i_143_ = (colour_buffer[i_140_] >> 8 & 0xff) * i_136_;
				int i_144_ = (colour_buffer[i_140_] & 0xff) * i_136_;
				int i_145_ = (i_137_ + i_142_ >> 8 << 16) + (i_138_ + i_143_ >> 8 << 8) + (i_139_ + i_144_ >> 8);
				colour_buffer[i_140_] = i_145_;
				i_140_ += width;
			}
		}
	}

	public static void fill_rectangle(int posX, int posY, int width, int height, int color) {
		if (posX < clip_left) {
			width -= clip_left - posX;
			posX = clip_left;
		}
		if (posY < clip_top) {
			height -= clip_top - posY;
			posY = clip_top;
		}
		if (posX + width > clip_right) {
			width = clip_right - posX;
		}
		if (posY + height > clip_bottom) {
			height = clip_bottom - posY;
		}
		int k1 = Rasterizer2D.width - width;
		int l1 = posX + posY * Rasterizer2D.width;
		for (int i2 = -height; i2 < 0; i2++) {
			for (int j2 = -width; j2 < 0; j2++) {
				colour_buffer[l1++] = color;
			}
			l1 += k1;
		}
	}

	public static final void draw_rectangle(int offsetX, int offsetY, int width, int height, int color) {
		draw_horizontal_line(offsetX, offsetY, width, color);
		draw_horizontal_line(offsetX, offsetY + height - 1, width, color);
		draw_vertical_line(offsetX, offsetY, height, color);
		draw_vertical_line(offsetX + width - 1, offsetY, height, color);
	}

	static final void draw_rectangle(int x, int y, int width, int height, int color, int alpha) {
		draw_horizontal_line(x, y, width, color, alpha);
		draw_horizontal_line(x, y + height - 1, width, color, alpha);
		draw_vertical_line(x, y + 1, height - 2, color, alpha);
		draw_vertical_line(x + width - 1, y + 1, height - 2, color, alpha);
	}

	static final void method216(int i, int i_150_, int i_151_, int[] is, int[] is_152_) {
		int i_153_ = i + i_150_ * width;
		for (i_150_ = 0; i_150_ < is.length; i_150_++) {
			int i_154_ = i_153_ + is[i_150_];
			for (i = -is_152_[i_150_]; i < 0; i++) {
				colour_buffer[i_154_++] = i_151_;
			}
			i_153_ += width;
		}
	}

	static final void method217(int[] is) {
		is[0] = clip_left;
		is[1] = clip_top;
		is[2] = clip_right;
		is[3] = clip_bottom;
	}

	static final void method218(int[] is) {
		clip_left = is[0];
		clip_top = is[1];
		clip_right = is[2];
		clip_bottom = is[3];
	}

	public static void clear() {
		int i = 0;
		int i_155_ = width * height - 7;
		while (i < i_155_) {
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
			colour_buffer[i++] = 0;
		}
		i_155_ += 7;
		while (i < i_155_) {
			colour_buffer[i++] = 0;
		}
	}

	public static final void draw_horizontal_line(int i, int i_156_, int i_157_, int i_158_, int i_159_) {
		if (i_156_ >= clip_top && i_156_ < clip_bottom) {
			if (i < clip_left) {
				i_157_ -= clip_left - i;
				i = clip_left;
			}
			if (i + i_157_ > clip_right) {
				i_157_ = clip_right - i;
			}
			int i_160_ = 256 - i_159_;
			int i_161_ = (i_158_ >> 16 & 0xff) * i_159_;
			int i_162_ = (i_158_ >> 8 & 0xff) * i_159_;
			int i_163_ = (i_158_ & 0xff) * i_159_;
			int i_164_ = i + i_156_ * width;
			for (int i_165_ = 0; i_165_ < i_157_; i_165_++) {
				int i_166_ = (colour_buffer[i_164_] >> 16 & 0xff) * i_160_;
				int i_167_ = (colour_buffer[i_164_] >> 8 & 0xff) * i_160_;
				int i_168_ = (colour_buffer[i_164_] & 0xff) * i_160_;
				int i_169_ = (i_161_ + i_166_ >> 8 << 16) + (i_162_ + i_167_ >> 8 << 8) + (i_163_ + i_168_ >> 8);
				colour_buffer[i_164_++] = i_169_;
			}
		}
	}

	public static void method221() {
		colour_buffer = null;
	}

	public static void set_clippingmask(int[] var0, int[] var1) {
		if (var0.length == clip_bottom - clip_top && var1.length == clip_bottom - clip_top) {
			clipping_mask_x = var0;
			clipping_mask_y = var1;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static void reset_clippingmask() {
		clipping_mask_x = null;
		clipping_mask_y = null;
	}

	static {
		clip_top = 0;
		clip_right = 0;
		clip_bottom = 0;
	}
}
