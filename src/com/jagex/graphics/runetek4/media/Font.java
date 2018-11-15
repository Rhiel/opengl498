package com.jagex.graphics.runetek4.media;

import java.util.Random;

import com.jagex.Packet;
import com.jagex.Queuable;
import com.jagex.RSString;
import com.jagex.Rasterizer2D;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;

public abstract class Font extends Queuable {

	public static RSString tag_open_underline = RSString.createString("u");
	public static RSString tag_open_times = RSString.createString("times");
	public static RSString tag_open_nbsp = RSString.createString("nbsp");
	public static RSString tag_open_br = RSString.createString("br");
	public static RSString tag_open_shad = RSString.createString("shad");
	public static RSString tag_open_copy = RSString.createString("copy");
	public static RSString tag_open_regular = RSString.createString("reg");
	public static RSString tag_open_strikethrough = RSString.createString("str");
	public static RSString tag_open_gt = RSString.createString("gt");
	public static RSString tag_open_lt = RSString.createString("lt");
	public static RSString tag_open_shy = RSString.createString("shy");
	public static RSString tag_open_euro = RSString.createString("euro");
	public static RSString tag_set_color = RSString.createString("col=");
	public static RSString tag_set_shadow = RSString.createString("shad=");
	public static RSString tag_set_trans = RSString.createString("trans=");
	public static RSString tag_set_underline = RSString.createString("u=");
	public static RSString tag_set_strikethrough = RSString.createString("str=");
	public static RSString tag_set_image = RSString.createString("img=");
	public static RSString tag_close_trans = RSString.createString(")4trans");
	public static RSString tag_close_color = RSString.createString(")4col");
	public static RSString tag_close_shadow = RSString.createString(")4shad");
	public static RSString tag_close_underline = RSString.createString(")4u");
	public static RSString tag_close_strikethrough = RSString.createString(")4str");
	public static RSString tag_full_gt = RSString.createString("<gt>");
	public static RSString tag_full_lt = RSString.createString("<lt>");
	public static RSString[] lines_buffer = new RSString[100];
	public static RSString line_buffer = RSString.create(100);
	public static int current_strikethrough = -1;
	public static int current_underline = -1;
	public static int anInt3748 = 0;
	public static int current_colour = 0;
	public static int default_trans = 256;
	public static int current_trans = 256;
	public static int current_shadow = -1;
	public static int default_shadow = -1;
	public static int default_colour = 0;
	public static int average_word_size = 0;

	public PaletteSprite[] modicons;
	public byte[] glyphs_padding;
	public int[] glyphs_xoffset;
	public int[] glyphs_yoffset;
	public int[] glyphs_width;
	public int[] glyphs_height;
	public int glyphs_miny;
	public int glyphs_maxy;
	public int plot_height;
	public int[] regular_widths;
	public int[] regular_offsets;
	public int anInt6033;
	public int anInt6035;

	public Font(byte[] var1, int[] var2, int[] var3, int[] var4, int[] var5) {
		glyphs_xoffset = var2;
		glyphs_yoffset = var3;
		glyphs_width = var4;
		glyphs_height = var5;
		deserialise(var1);
		int miny = Integer.MAX_VALUE;
		int maxy = Integer.MIN_VALUE;
		for (int var8 = 0; var8 < 256; ++var8) {
			if (glyphs_yoffset[var8] < miny && glyphs_height[var8] != 0) {
				miny = glyphs_yoffset[var8];
			}

			if (glyphs_yoffset[var8] + glyphs_height[var8] > maxy) {
				maxy = glyphs_yoffset[var8] + glyphs_height[var8];
			}
		}
		glyphs_miny = plot_height - miny;
		glyphs_maxy = maxy - plot_height;
	}

	public Font(byte[] data) {
		deserialise(data);
	}

	public final void deserialise(byte[] bs) {
		Packet packet = new Packet(bs);
		int i = packet.g1();
		if (i != 0) {
			throw new RuntimeException("");
		}
		boolean bool = packet.g1() == 1;
		regular_widths = new int[256];
		packet.gdata(regular_widths, 0, 256);
		if (!bool) {
			plot_height = packet.g1();
		} else {
			int[] is = new int[256];
			int[] is_50_ = new int[256];
			for (int i_51_ = 0; i_51_ < 256; i_51_++) {
				is[i_51_] = packet.g1();
			}
			for (int i_52_ = 0; i_52_ < 256; i_52_++) {
				is_50_[i_52_] = packet.g1();
			}
			byte[][] bs_53_ = new byte[256][];
			for (int i_54_ = 0; i_54_ < 256; i_54_++) {
				bs_53_[i_54_] = new byte[is[i_54_]];
				byte b = 0;
				for (int i_55_ = 0; i_55_ < bs_53_[i_54_].length; i_55_++) {
					b += packet.g1();
					bs_53_[i_54_][i_55_] = b;
				}
			}
			byte[][] bs_56_ = new byte[256][];
			for (int i_57_ = 0; i_57_ < 256; i_57_++) {
				bs_56_[i_57_] = new byte[is[i_57_]];
				byte b = 0;
				for (int i_58_ = 0; i_58_ < bs_56_[i_57_].length; i_58_++) {
					b += packet.g1();
					bs_56_[i_57_][i_58_] = b;
				}
			}
			glyphs_padding = new byte[65536];
			for (int i_59_ = 0; i_59_ < 256; i_59_++) {
				if (i_59_ != 32 && i_59_ != 160) {
					for (int i_60_ = 0; i_60_ < 256; i_60_++) {
						if (i_60_ != 32 && i_60_ != 160) {
							glyphs_padding[(i_59_ << 8) + i_60_] = (byte) calculate_glyph_padding(bs_53_, bs_56_, is_50_, regular_widths, is, i_59_, i_60_);
						}
					}
				}
			}
			plot_height = is_50_[32] + is[32];
		}
		packet.g1();
		packet.g1();
		anInt6033 = packet.g1();
		anInt6035 = packet.g1();
	}

	public abstract void draw_glyph(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7);

	public abstract void draw_glyph(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8);

	public void draw_text_centered(RSString text, int x, int y, int colour, int shadow) {
		if (text != null) {
			set_defaults(colour, shadow);
			draw_text(text, x - calculate_width(text) / 2, y);
		}
	}

	public void draw_text(RSString text, int x, int var3) {
		var3 -= plot_height;
		int var4 = -1;
		int var5 = 0;
		int var6 = text.length();
		for (int var7 = 0; var7 < var6; ++var7) {
			int var8 = text.charAt(var7);
			if (var8 == 60) {
				var4 = var7;
			} else {
				int var10;
				if (var8 == 62 && var4 != -1) {
					RSString var9 = text.substring(var7, var4 + 1);
					var4 = -1;
					if (var9.equals(tag_open_lt)) {
						var8 = 60;
					} else if (var9.equals(tag_open_gt)) {
						var8 = 62;
					} else if (var9.equals(tag_open_nbsp)) {
						var8 = 160;
					} else if (var9.equals(tag_open_shy)) {
						var8 = 173;
					} else if (var9.equals(tag_open_times)) {
						var8 = 215;
					} else if (var9.equals(tag_open_euro)) {
						var8 = 128;
					} else if (var9.equals(tag_open_copy)) {
						var8 = 169;
					} else {
						if (!var9.equals(tag_open_regular)) {
							if (var9.startsWith(tag_set_image)) {
								try {
									var10 = var9.substring(4).toInteger();
									PaletteSprite var15 = modicons[var10];
									int var12 = regular_offsets != null ? regular_offsets[var10] : var15.trim_height;
									if (default_trans == 256) {
										var15.draw(x, var3 + plot_height - var12);
									} else {
										var15.draw(x, var3 + plot_height - var12, default_trans);
									}

									x += var15.trim_width;
									var5 = 0;
								} catch (Exception var13) {
								}
							} else {
								apply_tag(var9);
							}
							continue;
						}

						var8 = 174;
					}
				}

				if (var4 == -1) {
					if (glyphs_padding != null && var5 != 0) {
						x += glyphs_padding[(var5 << 8) + var8];
					}

					int var14 = glyphs_width[var8];
					var10 = glyphs_height[var8];
					if (var8 != 32) {
						if (default_trans == 256) {
							if (default_shadow != -1) {
								draw_glyph(var8, x + glyphs_xoffset[var8] + 1, var3 + glyphs_yoffset[var8] + 1, var14, var10, default_shadow, true);
							}

							draw_glyph(var8, x + glyphs_xoffset[var8], var3 + glyphs_yoffset[var8], var14, var10, default_colour, false);
						} else {
							if (default_shadow != -1) {
								draw_glyph(var8, x + glyphs_xoffset[var8] + 1, var3 + glyphs_yoffset[var8] + 1, var14, var10, default_shadow, default_trans, true);
							}
							draw_glyph(var8, x + glyphs_xoffset[var8], var3 + glyphs_yoffset[var8], var14, var10, default_colour, default_trans, false);
						}
					} else if (average_word_size > 0) {
						anInt3748 += average_word_size;
						x += anInt3748 >> 8;
						anInt3748 &= 0xff;
					}

					int var11 = regular_widths[var8];
					if (current_strikethrough != -1) {
						if (GLManager.opengl_mode) {
							GLShapes.draw_horizontal_line(x, var3 + (int) (plot_height * 0.7D), var11, current_strikethrough);
						} else {
							Rasterizer2D.draw_horizontal_line(x, var3 + (int) (plot_height * 0.7D), var11, current_strikethrough);
						}
					}

					if (current_underline != -1) {
						if (GLManager.opengl_mode) {
							GLShapes.draw_horizontal_line(x, var3 + plot_height + 1, var11, current_underline);
						} else {
							Rasterizer2D.draw_horizontal_line(x, var3 + plot_height + 1, var11, current_underline);
						}
					}
					x += var11;
					var5 = var8;
				}
			}
		}

	}

	public int draw_text(RSString text, int x, int y, int width, int height, int colour, int shadow, int halignment, int valignment, int max_height) {
		return draw_text(text, x, y, width, height, colour, shadow, 256, halignment, valignment, max_height);
	}

	public int draw_text(RSString text, int x, int y, int width, int height, int colour, int shadow, int trans, int halignment, int valignment, int max_height) {
		if (text == null) {
			return 0;
		} else {
			set_defaults(colour, shadow, trans);
			if (max_height == 0) {
				max_height = plot_height;
			}

			int[] line_widths = new int[] { width };
			if (height < glyphs_miny + glyphs_maxy + max_height && height < max_height + max_height) {
				line_widths = null;
			}
			int num_lines = perform_word_warp(text, line_widths, lines_buffer);
			if (valignment == 3 && num_lines == 1) {
				valignment = 1;
			}
			int draw_y;
			if (valignment == 0) {
				draw_y = y + glyphs_miny;
			} else if (valignment == 1) {
				draw_y = y + glyphs_miny + (height - glyphs_miny - glyphs_maxy - (num_lines - 1) * max_height) / 2;
			} else if (valignment == 2) {
				draw_y = y + height - glyphs_maxy - (num_lines - 1) * max_height;
			} else {
				int var15 = (height - glyphs_miny - glyphs_maxy - (num_lines - 1) * max_height) / (num_lines + 1);
				if (var15 < 0) {
					var15 = 0;
				}
				draw_y = y + glyphs_miny + var15;
				max_height += var15;
			}
			for (int line_index = 0; line_index < num_lines; ++line_index) {
				if (halignment == 0) {
					draw_text(lines_buffer[line_index], x, draw_y);
				} else if (halignment == 1) {
					draw_text(lines_buffer[line_index], x + (width - calculate_width(lines_buffer[line_index])) / 2, draw_y);
				} else if (halignment == 2) {
					draw_text(lines_buffer[line_index], x + width - calculate_width(lines_buffer[line_index]), draw_y);
				} else if (line_index == num_lines - 1) {
					draw_text(lines_buffer[line_index], x, draw_y);
				} else {
					update_average_word_size(lines_buffer[line_index], width);
					draw_text(lines_buffer[line_index], x, draw_y);
					average_word_size = 0;
				}
				draw_y += max_height;
			}

			return num_lines;
		}
	}

	public void draw_text(RSString text, int x, int y, int colour, int shadow) {
		if (text != null) {
			set_defaults(colour, shadow);
			draw_text(text, x, y);
		}
	}

	public void draw_bouncingy_text_centered(RSString text, int x, int y, int colour, int shadow, int var6) {
		if (text != null) {
			set_defaults(colour, shadow);
			int var7 = text.length();
			int[] bouncing_yoffset = new int[var7];
			for (int var9 = 0; var9 < var7; ++var9) {
				bouncing_yoffset[var9] = (int) (Math.sin(var9 / 2.0D + var6 / 5.0D) * 5.0D);
			}
			draw_bouncingxy_text(text, x - calculate_width(text) / 2, y, null, bouncing_yoffset);
		}
	}

	public void draw_bouncingxy_text_centered(RSString text, int x, int y, int colour, int shadow, int strength) {
		if (text != null) {
			set_defaults(colour, shadow);
			int var7 = text.length();
			int[] var8 = new int[var7];
			int[] var9 = new int[var7];
			for (int var10 = 0; var10 < var7; ++var10) {
				var8[var10] = (int) (Math.sin(var10 / 5.0D + strength / 5.0D) * 5.0D);
				var9[var10] = (int) (Math.sin(var10 / 3.0D + strength / 5.0D) * 5.0D);
			}
			draw_bouncingxy_text(text, x - calculate_width(text) / 2, y, var8, var9);
		}
	}

	public void draw_bouncingy_text_centered(RSString text, int x, int y, int colour, int shadow, int slopeness, int strength) {
		if (text != null) {
			set_defaults(colour, shadow);
			double b_strength = 7.0D - strength / 8.0D;
			if (b_strength < 0.0D) {
				b_strength = 0.0D;
			}
			int var10 = text.length();
			int[] bouncing_yoffset = new int[var10];

			for (int var12 = 0; var12 < var10; ++var12) {
				bouncing_yoffset[var12] = (int) (Math.sin(var12 / 1.5D + slopeness / 1.0D) * b_strength);
			}
			draw_bouncingxy_text(text, x - calculate_width(text) / 2, y, null, bouncing_yoffset);
		}
	}

	public int draw_bouncingx_text(RSString text, int x, int y, int colour, int shadow, Random random, int seed) {
		if (text == null) {
			return 0;
		} else {
			random.setSeed(seed);
			set_defaults(colour, shadow, 192 + (random.nextInt() & 31));
			int var8 = text.length();
			int[] bouncing_xoffset = new int[var8];
			int var10 = 0;
			for (int var11 = 0; var11 < var8; ++var11) {
				bouncing_xoffset[var11] = var10;
				if ((random.nextInt() & 3) == 0) {
					++var10;
				}
			}

			draw_bouncingxy_text(text, x, y, bouncing_xoffset, null);
			return var10;
		}
	}

	public int draw_bouncingx_text(RSString text, int x, int y, int parawidth, int paraheight, int colour, int shadow, int halignment, int valignment, Random random, int seed, int[] var12) {
		if (text == null) {
			return 0;
		} else {
			random.setSeed(seed);
			set_defaults(colour, shadow, 192 + (random.nextInt() & 31));
			int var13 = text.length();
			int[] var14 = new int[var13];
			int var15 = 0;

			int var16;
			for (var16 = 0; var16 < var13; ++var16) {
				var14[var16] = var15;
				if ((random.nextInt() & 3) == 0) {
					++var15;
				}
			}

			var16 = x;
			int var17 = y + glyphs_miny;
			int var18 = -1;
			if (valignment == 1) {
				var17 += (paraheight - glyphs_miny - glyphs_maxy) / 2;
			} else if (valignment == 2) {
				var17 = y + paraheight - glyphs_maxy;
			}

			if (halignment == 1) {
				var18 = calculate_width(text) + var15;
				var16 = x + (parawidth - var18) / 2;
			} else if (halignment == 2) {
				var18 = calculate_width(text) + var15;
				var16 = x + parawidth - var18;
			}

			draw_bouncingxy_text(text, var16, var17, var14, null);
			if (var12 != null) {
				if (var18 == -1) {
					var18 = calculate_width(text) + var15;
				}

				var12[0] = var16;
				var12[1] = var17 - glyphs_miny;
				var12[2] = var18;
				var12[3] = glyphs_miny + glyphs_maxy;
			}

			return var15;
		}
	}

	public void draw_bouncingxy_text(RSString text, int x, int y, int[] bouncing_xoffset, int[] bouncing_yoffset) {
		y -= plot_height;
		int var6 = -1;
		int var7 = 0;
		int var8 = 0;
		int var9 = text.length();
		for (int var10 = 0; var10 < var9; ++var10) {
			int var11 = text.charAt(var10);
			if (var11 == 60) {
				var6 = var10;
			} else {
				int var13;
				int var14;
				int var15;
				if (var11 == 62 && var6 != -1) {
					RSString var12 = text.substring(var10, var6 + 1);
					var6 = -1;
					if (var12.equals(tag_open_lt)) {
						var11 = 60;
					} else if (var12.equals(tag_open_gt)) {
						var11 = 62;
					} else if (var12.equals(tag_open_nbsp)) {
						var11 = 160;
					} else if (var12.equals(tag_open_shy)) {
						var11 = 173;
					} else if (var12.equals(tag_open_times)) {
						var11 = 215;
					} else if (var12.equals(tag_open_euro)) {
						var11 = 128;
					} else if (var12.equals(tag_open_copy)) {
						var11 = 169;
					} else {
						if (!var12.equals(tag_open_regular)) {
							if (var12.startsWith(tag_set_image)) {
								try {
									if (bouncing_xoffset != null) {
										var13 = bouncing_xoffset[var8];
									} else {
										var13 = 0;
									}

									if (bouncing_yoffset != null) {
										var14 = bouncing_yoffset[var8];
									} else {
										var14 = 0;
									}

									++var8;
									var15 = var12.substring(4).toInteger();
									PaletteSprite var20 = modicons[var15];
									int var17 = regular_offsets != null ? regular_offsets[var15] : var20.trim_height;
									if (default_trans == 256) {
										var20.draw(x + var13, y + plot_height - var17 + var14);
									} else {
										var20.draw(x + var13, y + plot_height - var17 + var14, default_trans);
									}

									x += var20.trim_width;
									var7 = 0;
								} catch (Exception var18) {
								}
							} else {
								apply_tag(var12);
							}
							continue;
						}

						var11 = 174;
					}
				}

				if (var6 == -1) {
					if (glyphs_padding != null && var7 != 0) {
						x += glyphs_padding[(var7 << 8) + var11];
					}

					int var19 = glyphs_width[var11];
					var13 = glyphs_height[var11];
					if (bouncing_xoffset != null) {
						var14 = bouncing_xoffset[var8];
					} else {
						var14 = 0;
					}

					if (bouncing_yoffset != null) {
						var15 = bouncing_yoffset[var8];
					} else {
						var15 = 0;
					}

					++var8;
					if (var11 != 32) {
						if (default_trans == 256) {
							if (default_shadow != -1) {
								draw_glyph(var11, x + glyphs_xoffset[var11] + 1 + var14, y + glyphs_yoffset[var11] + 1 + var15, var19, var13, default_shadow, true);
							}

							draw_glyph(var11, x + glyphs_xoffset[var11] + var14, y + glyphs_yoffset[var11] + var15, var19, var13, default_colour, false);
						} else {
							if (default_shadow != -1) {
								draw_glyph(var11, x + glyphs_xoffset[var11] + 1 + var14, y + glyphs_yoffset[var11] + 1 + var15, var19, var13, default_shadow, default_trans, true);
							}

							draw_glyph(var11, x + glyphs_xoffset[var11] + var14, y + glyphs_yoffset[var11] + var15, var19, var13, default_colour, default_trans, false);
						}
					} else if (average_word_size > 0) {
						anInt3748 += average_word_size;
						x += anInt3748 >> 8;
						anInt3748 &= 0xff;
					}

					int var16 = regular_widths[var11];
					if (current_strikethrough != -1) {
						if (GLManager.opengl_mode) {
							GLShapes.draw_horizontal_line(x, y + (int) (plot_height * 0.7D), var16, current_strikethrough);
						} else {
							Rasterizer2D.draw_horizontal_line(x, y + (int) (plot_height * 0.7D), var16, current_strikethrough);
						}
					}

					if (current_underline != -1) {
						if (GLManager.opengl_mode) {
							GLShapes.draw_horizontal_line(x, y + plot_height, var16, current_underline);
						} else {
							Rasterizer2D.draw_horizontal_line(x, y + plot_height, var16, current_underline);
						}
					}
					x += var16;
					var7 = var11;
				}
			}
		}
	}

	public void draw_text_right_anchor(RSString text, int x, int y, int colour, int shadow) {
		if (text != null) {
			set_defaults(colour, shadow);
			draw_text(text, x - calculate_width(text), y);
		}
	}

	public void update_average_word_size(RSString text, int trunucate_size) {
		boolean within_tag = false;
		int num_chs = text.length();
		int num_words = 0;
		for (int chpos = 0; chpos < num_chs; ++chpos) {
			int ch = text.charAt(chpos);
			if (ch == '<') {
				within_tag = true;
			} else if (ch == '>') {
				within_tag = false;
			} else if (!within_tag && ch == ' ') {
				num_words++;
			}
		}
		if (num_words > 0) {
			average_word_size = (trunucate_size - calculate_width(text) << 8) / num_words;
		}
	}

	public int get_paragraph_width(RSString text, int line_width) {
		int num_lines = perform_word_warp(text, new int[] { line_width }, lines_buffer);
		int max_width = 0;
		for (int line_index = 0; line_index < num_lines; ++line_index) {
			int width = calculate_width(lines_buffer[line_index]);
			if (width > max_width) {
				max_width = width;
			}
		}
		return max_width;
	}

	public int get_paragraph_height(RSString text, int line_width, int i_55_) {
		if (i_55_ == 0) {
			i_55_ = plot_height;
		}
		int i_57_ = perform_word_warp(text, new int[] { line_width }, lines_buffer);
		int i_58_ = (i_57_ - 1) * i_55_;
		return anInt6033 + i_58_ + anInt6035;
	}

	public int perform_word_warp(RSString text, int line_width) {
		return perform_word_warp(text, new int[] { line_width }, lines_buffer);
	}

	public int perform_word_warp(RSString var1, int[] var2, RSString[] var3) {
		if (var1 == null) {
			return 0;
		} else {
			line_buffer.method1553(0, false);
			int var4 = 0;
			int var5 = 0;
			int var6 = -1;
			int var7 = 0;
			byte var8 = 0;
			int var9 = -1;
			int var10 = 0;
			int var11 = 0;
			int var12 = var1.length();
			for (int var13 = 0; var13 < var12; ++var13) {
				int var14 = var1.charAt(var13);
				if (var14 == 60) {
					var9 = var13;
				} else {
					if (var14 == 62 && var9 != -1) {
						RSString ch = var1.substring(var13, var9 + 1);
						var9 = -1;
						line_buffer.append(60);
						line_buffer.append(ch);
						line_buffer.append(62);
						if (ch.equals(tag_open_br)) {
							if (var3[var11] != null) {
								var3[var11].method1553(0, false);
								var3[var11].method1542(1, line_buffer, var5, line_buffer.length());
							} else {
								var3[var11] = line_buffer.method1563(101).substring(line_buffer.length(), var5);
							}

							++var11;
							var5 = line_buffer.length();
							var4 = 0;
							var6 = -1;
							var10 = 0;
						} else if (ch.equals(tag_open_lt)) {
							var4 += get_glyph_width(60);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 60];
							}

							var10 = 60;
						} else if (ch.equals(tag_open_gt)) {
							var4 += get_glyph_width(62);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 62];
							}

							var10 = 62;
						} else if (ch.equals(tag_open_nbsp)) {
							var4 += get_glyph_width(160);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 160];
							}

							var10 = 160;
						} else if (ch.equals(tag_open_shy)) {
							var4 += get_glyph_width(173);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 173];
							}

							var10 = 173;
						} else if (ch.equals(tag_open_times)) {
							var4 += get_glyph_width(215);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 215];
							}

							var10 = 215;
						} else if (ch.equals(tag_open_euro)) {
							var4 += get_glyph_width(128);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 128];
							}

							var10 = 128;
						} else if (ch.equals(tag_open_copy)) {
							var4 += get_glyph_width(169);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 169];
							}

							var10 = 169;
						} else if (ch.equals(tag_open_regular)) {
							var4 += get_glyph_width(174);
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + 174];
							}

							var10 = 174;
						} else if (ch.startsWith(tag_set_image)) {
							try {
								int var16 = ch.substring(4).toInteger();
								var4 += modicons[var16].trim_width;
								var10 = 0;
							} catch (Exception var17) {
							}
						}

						var14 = 0;
					}

					if (var9 == -1) {
						if (var14 != 0) {
							line_buffer.append(var14);
							var4 += regular_widths[var14];
							if (glyphs_padding != null && var10 != 0) {
								var4 += glyphs_padding[(var10 << 8) + var14];
							}

							var10 = var14;
						}

						if (var14 == 32) {
							var6 = line_buffer.length();
							var7 = var4;
							var8 = 1;
						}

						if (var2 != null && var4 > var2[var11 < var2.length ? var11 : var2.length - 1] && var6 >= 0) {
							if (var3[var11] != null) {
								var3[var11].method1553(0, false);
								var3[var11] = var3[var11].method1542(1, line_buffer, var5, var6 - var8);
							} else {
								var3[var11] = line_buffer.method1563(88).substring(var6 - var8, var5);
							}

							++var11;
							var5 = var6;
							var6 = -1;
							var4 -= var7;
							var10 = 0;
						}

						if (var14 == 45) {
							var6 = line_buffer.length();
							var7 = var4;
							var8 = 0;
						}
					}
				}
			}

			if (line_buffer.length() > var5) {
				if (var3[var11] != null) {
					var3[var11].method1553(0, false);
					var3[var11] = var3[var11].method1542(1, line_buffer, var5, line_buffer.length());
				} else {
					var3[var11] = line_buffer.method1563(94).substring(line_buffer.length(), var5);
				}

				++var11;
			}

			return var11;
		}
	}

	public void set_glyphs(PaletteSprite[] glyphs, int[] regular_offsets) {
		if (regular_offsets != null && regular_offsets.length != glyphs.length) {
			throw new IllegalArgumentException();
		} else {
			modicons = glyphs;
			this.regular_offsets = regular_offsets;
		}
	}

	public void set_defaults(int colour, int shadow) {
		current_strikethrough = -1;
		current_underline = -1;
		current_shadow = shadow;
		default_shadow = shadow;
		current_colour = colour;
		default_colour = colour;
		current_trans = 256;
		default_trans = 256;
		average_word_size = 0;
		anInt3748 = 0;
	}

	public void set_defaults(int color, int shadow, int trans) {
		current_strikethrough = -1;
		current_underline = -1;
		current_shadow = shadow;
		default_shadow = shadow;
		current_colour = color;
		default_colour = color;
		current_trans = trans;
		default_trans = trans;
		average_word_size = 0;
		anInt3748 = 0;
	}

	public void apply_tag(RSString tag) {
		try {
			if (tag.startsWith(tag_set_color)) {
				default_colour = tag.substring(4).parseInt(16);
			} else if (tag.equals(tag_close_color)) {
				default_colour = current_colour;
			} else if (tag.startsWith(tag_set_trans)) {
				default_trans = tag.substring(6).toInteger();
			} else if (tag.equals(tag_close_trans)) {
				default_trans = current_trans;
			} else if (tag.startsWith(tag_set_strikethrough)) {
				current_strikethrough = tag.substring(4).parseInt(16);
			} else if (tag.equals(tag_open_strikethrough)) {
				current_strikethrough = 8388608;
			} else if (tag.equals(tag_close_strikethrough)) {
				current_strikethrough = -1;
			} else if (tag.startsWith(tag_set_underline)) {
				current_underline = tag.substring(2).parseInt(16);
			} else if (tag.equals(tag_open_underline)) {
				current_underline = 0;
			} else if (tag.equals(tag_close_underline)) {
				current_underline = -1;
			} else if (tag.startsWith(tag_set_shadow)) {
				default_shadow = tag.substring(5).parseInt(16);
			} else if (tag.equals(tag_open_shad)) {
				default_shadow = 0;
			} else if (tag.equals(tag_close_shadow)) {
				default_shadow = current_shadow;
			} else if (tag.equals(tag_open_br)) {
				set_defaults(current_colour, current_shadow, current_trans);
			}
		} catch (Exception e) {
		}

	}

	public int get_glyph_width(int var1) {
		return regular_widths[var1 & 0xff];
	}

	public int calculate_width(RSString text) {
		if (text == null) {
			return 0;
		} else {
			int var2 = -1;
			int var3 = 0;
			int var4 = 0;
			int var5 = text.length();
			for (int var6 = 0; var6 < var5; ++var6) {
				int var7 = text.charAt(var6);
				if (var7 == 60) {
					var2 = var6;
				} else {
					if (var7 == 62 && var2 != -1) {
						RSString var8 = text.substring(var6, var2 + 1);
						var2 = -1;
						if (var8.equals(tag_open_lt)) {
							var7 = 60;
						} else if (var8.equals(tag_open_gt)) {
							var7 = 62;
						} else if (var8.equals(tag_open_nbsp)) {
							var7 = 160;
						} else if (var8.equals(tag_open_shy)) {
							var7 = 173;
						} else if (var8.equals(tag_open_times)) {
							var7 = 215;
						} else if (var8.equals(tag_open_euro)) {
							var7 = 128;
						} else if (var8.equals(tag_open_copy)) {
							var7 = 169;
						} else {
							if (!var8.equals(tag_open_regular)) {
								if (var8.startsWith(tag_set_image)) {
									try {
										int var9 = var8.substring(4).toInteger();
										var4 += modicons[var9].trim_width;
										var3 = 0;
									} catch (Exception var10) {
									}
								}
								continue;
							}

							var7 = 174;
						}
					}

					if (var2 == -1) {
						var4 += regular_widths[var7];
						if (glyphs_padding != null && var3 != 0) {
							var4 += glyphs_padding[(var3 << 8) + var7];
						}

						var3 = var7;
					}
				}
			}

			return var4;
		}
	}

	public static RSString escape_tags(RSString text) {
		int num_chs = text.length();
		int extra_chs = 0;
		for (int index = 0; index < num_chs; ++index) {
			int ch = text.charAt(index);
			if (ch == '<' || ch == '>') {
				extra_chs += 3;
			}
		}
		RSString escaped = RSString.create(num_chs + extra_chs);
		for (int index = 0; index < num_chs; ++index) {
			int ch = text.charAt(index);
			if (ch == '<') {
				escaped.append(tag_full_lt);
			} else if (ch == '>') {
				escaped.append(tag_full_gt);
			} else {
				escaped.append(ch);
			}
		}
		return escaped;
	}

	public static int calculate_glyph_padding(byte[][] var0, byte[][] var1, int[] var2, int[] var3, int[] var4, int var5, int var6) {
		int var7 = var2[var5];
		int var8 = var7 + var4[var5];
		int var9 = var2[var6];
		int var10 = var9 + var4[var6];
		int var11 = var7;
		if (var9 > var7) {
			var11 = var9;
		}

		int var12 = var8;
		if (var10 < var8) {
			var12 = var10;
		}

		int var13 = var3[var5];
		if (var3[var6] < var13) {
			var13 = var3[var6];
		}

		byte[] var14 = var1[var5];
		byte[] var15 = var0[var6];
		int var16 = var11 - var7;
		int var17 = var11 - var9;

		for (int var18 = var11; var18 < var12; ++var18) {
			int var19 = var14[var16++] + var15[var17++];
			if (var19 < var13) {
				var13 = var19;
			}
		}

		return -var13;
	}
}
