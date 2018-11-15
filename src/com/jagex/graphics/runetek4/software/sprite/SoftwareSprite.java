package com.jagex.graphics.runetek4.software.sprite;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

import com.jagex.Rasterizer2D;
import com.jagex.graphics.runetek4.media.Sprite;

public class SoftwareSprite extends Sprite {

	public int[] pixels;

	public SoftwareSprite(byte[] bs, Component component) {
		try {
			Image image = Toolkit.getDefaultToolkit().getImage("./res/rs/zaros/background.png");
			MediaTracker mediatracker = new MediaTracker(component);
			mediatracker.addImage(image, 0);
			mediatracker.waitForAll();
			width = image.getWidth(component);
			height = image.getHeight(component);
			trimmed_width = width;
			trimmed_height = height;
			offsety = 0;
			offsetx = 0;
			pixels = new int[width * height];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
			pixelgrabber.grabPixels();
		} catch (InterruptedException interruptedexception) {
			/* empty */
		}
	}

	public SoftwareSprite(int w, int h) {
		pixels = new int[w * h];
		width = trimmed_width = w;
		height = trimmed_height = h;
		offsety = offsetx = 0;
	}

	public SoftwareSprite(int trimmed_width, int trimmed_height, int offsetx, int offsety, int width, int height, int[] pixels) {
		this.trimmed_width = trimmed_width;
		this.trimmed_height = trimmed_height;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public void bind() {
		Rasterizer2D.initialise(pixels, width, height);
	}

	@Override
	public void draw(int x, int y) {
		x += offsetx;
		y += offsety;
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
			plot_copy(Rasterizer2D.colour_buffer, pixels, var4, var3, var6, var5, var7, var8);
		}
	}

	@Override
	public void draw(int x, int y, int alpha) {
		x += offsetx;
		y += offsety;
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
			plot_translucent(Rasterizer2D.colour_buffer, pixels, 0, var5, var4, var7, var6, var8, var9, alpha);
		}
	}

	@Override
	public void draw(int x, int y, int width, int height) {
		if (width > 0 && height > 0) {
			int var5 = this.width;
			int var6 = this.height;
			int var7 = 0;
			int var8 = 0;
			int var9 = trimmed_width;
			int var10 = trimmed_height;
			int var11 = (var9 << 16) / width;
			int var12 = (var10 << 16) / height;
			int var13;
			if (offsetx > 0) {
				var13 = ((offsetx << 16) + var11 - 1) / var11;
				x += var13;
				var7 += var13 * var11 - (offsetx << 16);
			}

			if (offsety > 0) {
				var13 = ((offsety << 16) + var12 - 1) / var12;
				y += var13;
				var8 += var13 * var12 - (offsety << 16);
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
			plot_scaled(Rasterizer2D.colour_buffer, pixels, 0, var7, var8, var13, var14, width, height, var11, var12, var5);
		}
	}

	@Override
	public void draw(int x, int y, int width, int height, int alpha) {
		if (width > 0 && height > 0) {
			int var6 = this.width;
			int var7 = this.height;
			int var8 = 0;
			int var9 = 0;
			int var10 = trimmed_width;
			int var11 = trimmed_height;
			int var12 = (var10 << 16) / width;
			int var13 = (var11 << 16) / height;
			int var14;
			if (offsetx > 0) {
				var14 = ((offsetx << 16) + var12 - 1) / var12;
				x += var14;
				var8 += var14 * var12 - (offsetx << 16);
			}

			if (offsety > 0) {
				var14 = ((offsety << 16) + var13 - 1) / var13;
				y += var14;
				var9 += var14 * var13 - (offsety << 16);
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
			plot_scaled_translucent(Rasterizer2D.colour_buffer, pixels, 0, var8, var9, var14, var15, width, height, var12, var13, var6, alpha);
		}
	}

	@Override
	public void draw_clipped_left_anchor(int x, int y) {
		x += offsetx;
		y += offsety;
		int dst_offset = x + y * Rasterizer2D.width;
		int src_offset = 0;
		int height = this.height;
		int width = this.width;
		int dst_scansize = Rasterizer2D.width - width;
		int src_scansize = 0;
		int slope;
		if (y < Rasterizer2D.clip_top) {
			slope = Rasterizer2D.clip_top - y;
			height -= slope;
			y = Rasterizer2D.clip_top;
			src_offset += slope * width;
			dst_offset += slope * Rasterizer2D.width;
		}
		if (y + height > Rasterizer2D.clip_bottom) {
			height -= y + height - Rasterizer2D.clip_bottom;
		}
		if (x < Rasterizer2D.clip_left) {
			slope = Rasterizer2D.clip_left - x;
			width -= slope;
			x = Rasterizer2D.clip_left;
			src_offset += slope;
			dst_offset += slope;
			src_scansize += slope;
			dst_scansize += slope;
		}

		if (x + width > Rasterizer2D.clip_right) {
			slope = x + width - Rasterizer2D.clip_right;
			width -= slope;
			src_scansize += slope;
			dst_scansize += slope;
		}
		if (width > 0 && height > 0) {
			plot_clipped(Rasterizer2D.colour_buffer, pixels, 0, src_offset, dst_offset, width, height, dst_scansize, src_scansize);
		}
	}

	@Override
	public void draw_clipped_right_anchor(int var1, int var2) {
		var1 += trimmed_width - width - offsetx;
		var2 += offsety;
		int var3 = var1 + var2 * Rasterizer2D.width;
		int var4 = width - 1;
		int var5 = height;
		int var6 = width;
		int var7 = Rasterizer2D.width - var6;
		int var8 = var6 + var6;
		int var9;
		if (var2 < Rasterizer2D.clip_top) {
			var9 = Rasterizer2D.clip_top - var2;
			var5 -= var9;
			var2 = Rasterizer2D.clip_top;
			var4 += var9 * var6;
			var3 += var9 * Rasterizer2D.width;
		}

		if (var2 + var5 > Rasterizer2D.clip_bottom) {
			var5 -= var2 + var5 - Rasterizer2D.clip_bottom;
		}

		if (var1 < Rasterizer2D.clip_left) {
			var9 = Rasterizer2D.clip_left - var1;
			var6 -= var9;
			var1 = Rasterizer2D.clip_left;
			var4 -= var9;
			var3 += var9;
			var8 -= var9;
			var7 += var9;
		}

		if (var1 + var6 > Rasterizer2D.clip_right) {
			var9 = var1 + var6 - Rasterizer2D.clip_right;
			var6 -= var9;
			var8 -= var9;
			var7 += var9;
		}

		if (var6 > 0 && var5 > 0) {
			plot_clipped_reverse(Rasterizer2D.colour_buffer, pixels, 0, var4, var3, var6, var5, var7, var8);
		}
	}

	@Override
	public void draw_rotated(int x, int y, int var3, int var4, int angle, int var6) {

		if (var6 != 0) {
			x -= offsetx << 4;
			y -= offsety << 4;
			double radian = (angle & 0xffff) * 9.587379924285257E-5D;
			int var9 = (int) Math.floor(Math.sin(radian) * var6 + 0.5D);
			int var10 = (int) Math.floor(Math.cos(radian) * var6 + 0.5D);
			int var11 = -x * var10 + -y * var9;
			int var12 = -(-x) * var9 + -y * var10;
			int var13 = ((width << 4) - x) * var10 + -y * var9;
			int var14 = -((width << 4) - x) * var9 + -y * var10;
			int var15 = -x * var10 + ((height << 4) - y) * var9;
			int var16 = -(-x) * var9 + ((height << 4) - y) * var10;
			int var17 = ((width << 4) - x) * var10 + ((height << 4) - y) * var9;
			int var18 = -((width << 4) - x) * var9 + ((height << 4) - y) * var10;
			int var19;
			int var20;
			if (var11 < var13) {
				var19 = var11;
				var20 = var13;
			} else {
				var19 = var13;
				var20 = var11;
			}

			if (var15 < var19) {
				var19 = var15;
			}

			if (var17 < var19) {
				var19 = var17;
			}

			if (var15 > var20) {
				var20 = var15;
			}

			if (var17 > var20) {
				var20 = var17;
			}

			int var21;
			int var22;
			if (var12 < var14) {
				var21 = var12;
				var22 = var14;
			} else {
				var21 = var14;
				var22 = var12;
			}

			if (var16 < var21) {
				var21 = var16;
			}

			if (var18 < var21) {
				var21 = var18;
			}

			if (var16 > var22) {
				var22 = var16;
			}

			if (var18 > var22) {
				var22 = var18;
			}

			var19 >>= 12;
			var20 = var20 + 4095 >> 12;
			var21 >>= 12;
			var22 = var22 + 4095 >> 12;
			var19 += var3;
			var20 += var3;
			var21 += var4;
			var22 += var4;
			var19 >>= 4;
			var20 = var20 + 15 >> 4;
			var21 >>= 4;
			var22 = var22 + 15 >> 4;
			if (var19 < Rasterizer2D.clip_left) {
				var19 = Rasterizer2D.clip_left;
			}

			if (var20 > Rasterizer2D.clip_right) {
				var20 = Rasterizer2D.clip_right;
			}

			if (var21 < Rasterizer2D.clip_top) {
				var21 = Rasterizer2D.clip_top;
			}

			if (var22 > Rasterizer2D.clip_bottom) {
				var22 = Rasterizer2D.clip_bottom;
			}

			var20 = var19 - var20;
			if (var20 < 0) {
				var22 = var21 - var22;
				if (var22 < 0) {
					int var23 = var21 * Rasterizer2D.width + var19;
					double var24 = 1.6777216E7D / var6;
					int var26 = (int) Math.floor(Math.sin(radian) * var24 + 0.5D);
					int var27 = (int) Math.floor(Math.cos(radian) * var24 + 0.5D);
					int var28 = (var19 << 4) + 8 - var3;
					int var29 = (var21 << 4) + 8 - var4;
					int var30 = (x << 8) - (var29 * var26 >> 4);
					int var31 = (y << 8) + (var29 * var27 >> 4);
					int var34;
					int var35;
					int var32;
					int var33;
					int var38;
					int var36;
					int var37;
					if (var27 == 0) {
						if (var26 == 0) {
							for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
								var34 = var23;
								var35 = var30;
								var36 = var31;
								var37 = var20;
								if (var30 >= 0 && var31 >= 0 && var30 - (width << 12) < 0 && var31 - (height << 12) < 0) {
									for (; var37 < 0; ++var37) {
										var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
										if (var38 != 0) {
											Rasterizer2D.colour_buffer[var34++] = var38;
										} else {
											++var34;
										}
									}
								}

								++var33;
							}
						} else if (var26 < 0) {
							for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
								var34 = var23;
								var35 = var30;
								var36 = var31 + (var28 * var26 >> 4);
								var37 = var20;
								if (var30 >= 0 && var30 - (width << 12) < 0) {
									if ((var32 = var36 - (height << 12)) >= 0) {
										var32 = (var26 - var32) / var26;
										var37 = var20 + var32;
										var36 += var26 * var32;
										var34 = var23 + var32;
									}

									if ((var32 = (var36 - var26) / var26) > var37) {
										var37 = var32;
									}

									while (var37 < 0) {
										var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
										if (var38 != 0) {
											Rasterizer2D.colour_buffer[var34++] = var38;
										} else {
											++var34;
										}

										var36 += var26;
										++var37;
									}
								}

								++var33;
								var30 -= var26;
							}
						} else {
							for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
								var34 = var23;
								var35 = var30;
								var36 = var31 + (var28 * var26 >> 4);
								var37 = var20;
								if (var30 >= 0 && var30 - (width << 12) < 0) {
									if (var36 < 0) {
										var32 = (var26 - 1 - var36) / var26;
										var37 = var20 + var32;
										var36 += var26 * var32;
										var34 = var23 + var32;
									}

									if ((var32 = (1 + var36 - (height << 12) - var26) / var26) > var37) {
										var37 = var32;
									}

									while (var37 < 0) {
										var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
										if (var38 != 0) {
											Rasterizer2D.colour_buffer[var34++] = var38;
										} else {
											++var34;
										}

										var36 += var26;
										++var37;
									}
								}

								++var33;
								var30 -= var26;
							}
						}
					} else if (var27 < 0) {
						if (var26 == 0) {
							for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
								var34 = var23;
								var35 = var30 + (var28 * var27 >> 4);
								var36 = var31;
								var37 = var20;
								if (var31 >= 0 && var31 - (height << 12) < 0) {
									if ((var32 = var35 - (width << 12)) >= 0) {
										var32 = (var27 - var32) / var27;
										var37 = var20 + var32;
										var35 += var27 * var32;
										var34 = var23 + var32;
									}

									if ((var32 = (var35 - var27) / var27) > var37) {
										var37 = var32;
									}

									while (var37 < 0) {
										var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
										if (var38 != 0) {
											Rasterizer2D.colour_buffer[var34++] = var38;
										} else {
											++var34;
										}

										var35 += var27;
										++var37;
									}
								}

								++var33;
								var31 += var27;
							}
						} else if (var26 < 0) {
							for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
								var34 = var23;
								var35 = var30 + (var28 * var27 >> 4);
								var36 = var31 + (var28 * var26 >> 4);
								var37 = var20;
								if ((var32 = var35 - (width << 12)) >= 0) {
									var32 = (var27 - var32) / var27;
									var37 = var20 + var32;
									var35 += var27 * var32;
									var36 += var26 * var32;
									var34 = var23 + var32;
								}

								if ((var32 = (var35 - var27) / var27) > var37) {
									var37 = var32;
								}

								if ((var32 = var36 - (height << 12)) >= 0) {
									var32 = (var26 - var32) / var26;
									var37 += var32;
									var35 += var27 * var32;
									var36 += var26 * var32;
									var34 += var32;
								}

								if ((var32 = (var36 - var26) / var26) > var37) {
									var37 = var32;
								}

								while (var37 < 0) {
									var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
									if (var38 != 0) {
										Rasterizer2D.colour_buffer[var34++] = var38;
									} else {
										++var34;
									}

									var35 += var27;
									var36 += var26;
									++var37;
								}

								++var33;
								var30 -= var26;
								var31 += var27;
							}
						} else {
							for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
								var34 = var23;
								var35 = var30 + (var28 * var27 >> 4);
								var36 = var31 + (var28 * var26 >> 4);
								var37 = var20;
								if ((var32 = var35 - (width << 12)) >= 0) {
									var32 = (var27 - var32) / var27;
									var37 = var20 + var32;
									var35 += var27 * var32;
									var36 += var26 * var32;
									var34 = var23 + var32;
								}

								if ((var32 = (var35 - var27) / var27) > var37) {
									var37 = var32;
								}

								if (var36 < 0) {
									var32 = (var26 - 1 - var36) / var26;
									var37 += var32;
									var35 += var27 * var32;
									var36 += var26 * var32;
									var34 += var32;
								}

								if ((var32 = (1 + var36 - (height << 12) - var26) / var26) > var37) {
									var37 = var32;
								}

								while (var37 < 0) {
									var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
									if (var38 != 0) {
										Rasterizer2D.colour_buffer[var34++] = var38;
									} else {
										++var34;
									}

									var35 += var27;
									var36 += var26;
									++var37;
								}

								++var33;
								var30 -= var26;
								var31 += var27;
							}
						}
					} else if (var26 == 0) {
						for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
							var34 = var23;
							var35 = var30 + (var28 * var27 >> 4);
							var36 = var31;
							var37 = var20;
							if (var31 >= 0 && var31 - (height << 12) < 0) {
								if (var35 < 0) {
									var32 = (var27 - 1 - var35) / var27;
									var37 = var20 + var32;
									var35 += var27 * var32;
									var34 = var23 + var32;
								}

								if ((var32 = (1 + var35 - (width << 12) - var27) / var27) > var37) {
									var37 = var32;
								}

								while (var37 < 0) {
									var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
									if (var38 != 0) {
										Rasterizer2D.colour_buffer[var34++] = var38;
									} else {
										++var34;
									}

									var35 += var27;
									++var37;
								}
							}

							++var33;
							var31 += var27;
						}
					} else if (var26 < 0) {
						for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
							var34 = var23;
							var35 = var30 + (var28 * var27 >> 4);
							var36 = var31 + (var28 * var26 >> 4);
							var37 = var20;
							if (var35 < 0) {
								var32 = (var27 - 1 - var35) / var27;
								var37 = var20 + var32;
								var35 += var27 * var32;
								var36 += var26 * var32;
								var34 = var23 + var32;
							}

							if ((var32 = (1 + var35 - (width << 12) - var27) / var27) > var37) {
								var37 = var32;
							}

							if ((var32 = var36 - (height << 12)) >= 0) {
								var32 = (var26 - var32) / var26;
								var37 += var32;
								var35 += var27 * var32;
								var36 += var26 * var32;
								var34 += var32;
							}

							if ((var32 = (var36 - var26) / var26) > var37) {
								var37 = var32;
							}

							while (var37 < 0) {
								var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
								if (var38 != 0) {
									Rasterizer2D.colour_buffer[var34++] = var38;
								} else {
									++var34;
								}

								var35 += var27;
								var36 += var26;
								++var37;
							}

							++var33;
							var30 -= var26;
							var31 += var27;
						}
					} else {
						for (var33 = var22; var33 < 0; var23 += Rasterizer2D.width) {
							var34 = var23;
							var35 = var30 + (var28 * var27 >> 4);
							var36 = var31 + (var28 * var26 >> 4);
							var37 = var20;
							if (var35 < 0) {
								var32 = (var27 - 1 - var35) / var27;
								var37 = var20 + var32;
								var35 += var27 * var32;
								var36 += var26 * var32;
								var34 = var23 + var32;
							}

							if ((var32 = (1 + var35 - (width << 12) - var27) / var27) > var37) {
								var37 = var32;
							}

							if (var36 < 0) {
								var32 = (var26 - 1 - var36) / var26;
								var37 += var32;
								var35 += var27 * var32;
								var36 += var26 * var32;
								var34 += var32;
							}

							if ((var32 = (1 + var36 - (height << 12) - var26) / var26) > var37) {
								var37 = var32;
							}

							while (var37 < 0) {
								var38 = pixels[(var36 >> 12) * width + (var35 >> 12)];
								if (var38 != 0) {
									Rasterizer2D.colour_buffer[var34++] = var38;
								} else {
									++var34;
								}

								var35 += var27;
								var36 += var26;
								++var37;
							}

							++var33;
							var30 -= var26;
							var31 += var27;
						}
					}

				}
			}
		}
	}

	public void draw_rotated(int x, int y, int var3, int var4, int var5, int var6, double radian, int var9) {
		try {
			int var10 = -var3 / 2;
			int var11 = -var4 / 2;
			int var12 = (int) (Math.sin(radian) * 65536.0D);
			int var13 = (int) (Math.cos(radian) * 65536.0D);
			var12 = var12 * var9 >> 8;
			var13 = var13 * var9 >> 8;
			int var14 = (var5 << 16) + var11 * var12 + var10 * var13;
			int var15 = (var6 << 16) + var11 * var13 - var10 * var12;
			int var16 = x + y * Rasterizer2D.width;

			for (y = 0; y < var4; ++y) {
				int var17 = var16;
				int var18 = var14;
				int var19 = var15;

				for (x = -var3; x < 0; ++x) {
					int var20 = pixels[(var18 >> 16) + (var19 >> 16) * width];
					if (var20 != 0) {
						Rasterizer2D.colour_buffer[var17++] = var20;
					} else {
						++var17;
					}

					var18 += var13;
					var19 -= var12;
				}

				var14 += var12;
				var15 += var13;
				var16 += Rasterizer2D.width;
			}
		} catch (Exception var21) {
		}

	}

	public void draw_offseted(int var1, int var2, int[] var3, int[] var4) {
		if (Rasterizer2D.clip_bottom - Rasterizer2D.clip_top != var3.length) {
			throw new IllegalStateException();
		} else {
			var1 += offsetx;
			var2 += offsety;
			int var5 = 0;
			int var6 = height;
			int var7 = width;
			int var8 = Rasterizer2D.width - var7;
			int var9 = 0;
			int var10 = var1 + var2 * Rasterizer2D.width;
			int var11;
			if (var2 < Rasterizer2D.clip_top) {
				var11 = Rasterizer2D.clip_top - var2;
				var6 -= var11;
				var2 = Rasterizer2D.clip_top;
				var5 += var11 * var7;
				var10 += var11 * Rasterizer2D.width;
			}

			if (var2 + var6 > Rasterizer2D.clip_bottom) {
				var6 -= var2 + var6 - Rasterizer2D.clip_bottom;
			}

			if (var1 < Rasterizer2D.clip_left) {
				var11 = Rasterizer2D.clip_left - var1;
				var7 -= var11;
				var1 = Rasterizer2D.clip_left;
				var5 += var11;
				var10 += var11;
				var9 += var11;
				var8 += var11;
			}

			if (var1 + var7 > Rasterizer2D.clip_right) {
				var11 = var1 + var7 - Rasterizer2D.clip_right;
				var7 -= var11;
				var9 += var11;
				var8 += var11;
			}

			if (var7 > 0 && var6 > 0) {
				var11 = var1 - Rasterizer2D.clip_left;
				int var12 = var2 - Rasterizer2D.clip_top;

				for (int var13 = var12; var13 < var12 + var6; ++var13) {
					int var14 = var3[var13];
					int var15 = var4[var13];
					int var16 = var7;
					int var17;
					if (var11 > var14) {
						var17 = var11 - var14;
						if (var17 >= var15) {
							var5 += var7 + var9;
							var10 += var7 + var8;
							continue;
						}

						var15 -= var17;
					} else {
						var17 = var14 - var11;
						if (var17 >= var7) {
							var5 += var7 + var9;
							var10 += var7 + var8;
							continue;
						}

						var5 += var17;
						var16 = var7 - var17;
						var10 += var17;
					}

					var17 = 0;
					if (var16 < var15) {
						var15 = var16;
					} else {
						var17 = var16 - var15;
					}

					for (int var18 = -var15; var18 < 0; ++var18) {
						int var19 = pixels[var5++];
						if (var19 != 0) {
							Rasterizer2D.colour_buffer[var10++] = var19;
						} else {
							++var10;
						}
					}

					var5 += var17 + var9;
					var10 += var17 + var8;
				}

			}
		}
	}

	public void draw_offseted(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int[] var9, int[] var10) {
		try {
			int var11 = -var3 / 2;
			int var12 = -var4 / 2;
			int var13 = (int) (Math.sin(var7 / 326.11D) * 65536.0D);
			int var14 = (int) (Math.cos(var7 / 326.11D) * 65536.0D);
			var13 = var13 * var8 >> 8;
			var14 = var14 * var8 >> 8;
			int var15 = (var5 << 16) + var12 * var13 + var11 * var14;
			int var16 = (var6 << 16) + var12 * var14 - var11 * var13;
			int var17 = var1 + var2 * Rasterizer2D.width;

			for (var2 = 0; var2 < var4; ++var2) {
				int var18 = var9[var2];
				int var19 = var17 + var18;
				int var20 = var15 + var14 * var18;
				int var21 = var16 - var13 * var18;

				for (var1 = -var10[var2]; var1 < 0; ++var1) {
					Rasterizer2D.colour_buffer[var19++] = pixels[(var20 >> 16) + (var21 >> 16) * width];
					var20 += var14;
					var21 -= var13;
				}

				var15 += var13;
				var16 += var14;
				var17 += Rasterizer2D.width;
			}
		} catch (Exception var22) {
		}

	}

	public void draw_offseted_clipped(int x, int y, int width, int height, int var5, int var6, int angle, int scalar, int[] offsets_x, int[] offseets_y) {
		try {
			int var11 = -width / 2;
			int var12 = -height / 2;
			int var13 = (int) (Math.sin(angle / 326.11D) * 65536.0D);
			int var14 = (int) (Math.cos(angle / 326.11D) * 65536.0D);
			var13 = var13 * scalar >> 8;
			var14 = var14 * scalar >> 8;
			int var15 = (var5 << 16) + var12 * var13 + var11 * var14;
			int var16 = (var6 << 16) + var12 * var14 - var11 * var13;
			int var17 = x + y * Rasterizer2D.width;

			for (y = 0; y < height; ++y) {
				int var18 = offsets_x[y];
				int var19 = var17 + var18;
				int var20 = var15 + var14 * var18;
				int var21 = var16 - var13 * var18;
				for (x = -offseets_y[y]; x < 0; ++x) {
					int var22 = pixels[(var20 >> 16) + (var21 >> 16) * this.width];
					if (var22 != 0) {
						Rasterizer2D.colour_buffer[var19++] = var22;
					} else {
						++var19;
					}

					var20 += var14;
					var21 -= var13;
				}
				var15 += var13;
				var16 += var14;
				var17 += Rasterizer2D.width;
			}
		} catch (Exception var23) {
		}

	}

	public void add_shadow(int colour) {
		for (int y = height - 1; y > 0; --y) {
			int line = y * width;
			for (int x = width - 1; x > 0; --x) {
				if (pixels[x + line] == 0 && pixels[x + line - 1 - width] != 0) {
					pixels[x + line] = colour;
				}
			}
		}
	}

	public void add_outline_colour(int colour) {
		int[] outlined = new int[width * height];
		int var3 = 0;
		for (int var4 = 0; var4 < height; ++var4) {
			for (int var5 = 0; var5 < width; ++var5) {
				int var6 = pixels[var3];
				if (var6 == 0) {
					if (var5 > 0 && pixels[var3 - 1] != 0) {
						var6 = colour;
					} else if (var4 > 0 && pixels[var3 - width] != 0) {
						var6 = colour;
					} else if (var5 < width - 1 && pixels[var3 + 1] != 0) {
						var6 = colour;
					} else if (var4 < height - 1 && pixels[var3 + width] != 0) {
						var6 = colour;
					}
				}
				outlined[var3++] = var6;
			}
		}
		pixels = outlined;
	}

	public void add_outline_size(int size) {
		if (width != trimmed_width || height != trimmed_height) {
			int left_size = size;
			if (size > offsetx) {
				left_size = offsetx;
			}
			int right_size = size;
			if (size + offsetx + width > trimmed_width) {
				right_size = trimmed_width - offsetx - width;
			}
			int top_size = size;
			if (size > offsety) {
				top_size = offsety;
			}
			int bottom_size = size;
			if (size + offsety + height > trimmed_height) {
				bottom_size = trimmed_height - offsety - height;
			}
			int new_width = width + left_size + right_size;
			int new_height = height + top_size + bottom_size;
			int[] new_pixels = new int[new_width * new_height];
			for (int x = 0; x < height; ++x) {
				for (int y = 0; y < width; ++y) {
					new_pixels[(x + top_size) * new_width + y + left_size] = pixels[x * width + y];
				}
			}
			pixels = new_pixels;
			width = new_width;
			height = new_height;
			offsetx -= left_size;
			offsety -= top_size;
		}
	}

	public void tint(int tint_red, int tint_green, int tint_blue) {
		for (int pixel = 0; pixel < pixels.length; ++pixel) {
			int rgb = pixels[pixel];
			if (rgb != 0) {
				int red = rgb >> 16 & 0xff;
				red += tint_red;
				if (red < 1) {
					red = 1;
				} else if (red > 0xff) {
					red = 0xff;
				}
				int green = rgb >> 8 & 0xff;
				green += tint_green;
				if (green < 1) {
					green = 1;
				} else if (green > 0xff) {
					green = 0xff;
				}
				int blue = rgb & 0xff;
				blue += tint_blue;
				if (blue < 1) {
					blue = 1;
				} else if (blue > 0xff) {
					blue = 0xff;
				}
				pixels[pixel] = (red << 16) + (green << 8) + blue;
			}
		}

	}

	public void flip_vertically() {
		int[] var1 = new int[width * height];
		int var2 = 0;
		for (int var3 = height - 1; var3 >= 0; --var3) {
			for (int var4 = 0; var4 < width; ++var4) {
				var1[var2++] = pixels[var4 + var3 * width];
			}
		}
		pixels = var1;
		offsety = trimmed_height - height - offsety;
	}

	public void flip_horizontally() {
		int[] var1 = new int[width * height];
		int var2 = 0;
		for (int var3 = 0; var3 < height; ++var3) {
			for (int var4 = width - 1; var4 >= 0; --var4) {
				var1[var2++] = pixels[var4 + var3 * width];
			}
		}
		pixels = var1;
		offsetx = trimmed_width - width - offsetx;
	}

	public void apply_padding() {
		if (width != trimmed_width || height != trimmed_height) {
			int[] var1 = new int[trimmed_width * trimmed_height];

			for (int var2 = 0; var2 < height; ++var2) {
				for (int var3 = 0; var3 < width; ++var3) {
					var1[(var2 + offsety) * trimmed_width + var3 + offsetx] = pixels[var2 * width + var3];
				}
			}

			pixels = var1;
			width = trimmed_width;
			height = trimmed_height;
			offsetx = 0;
			offsety = 0;
		}
	}

	public int[] get_pixels_trimmed() {
		int[] var4 = new int[trimmed_width * trimmed_height];
		for (int var5 = 0; var5 < height; ++var5) {
			int var1 = var5 * width;
			int var2 = offsetx + (var5 + offsety) * trimmed_width;

			for (int var6 = 0; var6 < width; ++var6) {
				int var3 = pixels[var1++];
				var4[var2++] = var3 != 0 ? -16777216 | var3 : 0;
			}
		}

		return var4;
	}

	public static void plot_clipped(int[] dst_pixels, int[] src_pixels, int src_rgb, int src_offset, int dst_offset, int width, int height, int dst_scansize, int src_scansize) {
		int var9 = -(width >> 2);
		width = -(width & 3);
		for (int var10 = -height; var10 < 0; ++var10) {
			int var11;
			for (var11 = var9; var11 < 0; ++var11) {
				src_rgb = src_pixels[src_offset++];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
				src_rgb = src_pixels[src_offset++];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
				src_rgb = src_pixels[src_offset++];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
				src_rgb = src_pixels[src_offset++];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
			}
			for (var11 = width; var11 < 0; ++var11) {
				src_rgb = src_pixels[src_offset++];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
			}
			dst_offset += dst_scansize;
			src_offset += src_scansize;
		}

	}

	public static void plot_clipped_reverse(int[] dst_pixels, int[] src_pixels, int src_rgb, int src_offset, int dst_offset, int width, int height, int dst_scansize, int src_scansize) {
		int var9 = -(width >> 2);
		width = -(width & 3);
		for (int var10 = -height; var10 < 0; ++var10) {
			int var11;
			for (var11 = var9; var11 < 0; ++var11) {
				src_rgb = src_pixels[src_offset--];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
				src_rgb = src_pixels[src_offset--];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
				src_rgb = src_pixels[src_offset--];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
				src_rgb = src_pixels[src_offset--];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
			}
			for (var11 = width; var11 < 0; ++var11) {
				src_rgb = src_pixels[src_offset--];
				if (src_rgb != 0) {
					dst_pixels[dst_offset++] = src_rgb;
				} else {
					++dst_offset;
				}
			}
			dst_offset += dst_scansize;
			src_offset += src_scansize;
		}
	}

	public static void plot_copy(int[] dst_pixels, int[] src_pixels, int src_offset, int dst_offset, int width, int height, int dst_scansize, int src_scansize) {
		for (int y = -height; y < 0; ++y) {
			int end_offset = dst_offset + width - 3;
			while (dst_offset < end_offset) {
				dst_pixels[dst_offset++] = src_pixels[src_offset++];
				dst_pixels[dst_offset++] = src_pixels[src_offset++];
				dst_pixels[dst_offset++] = src_pixels[src_offset++];
				dst_pixels[dst_offset++] = src_pixels[src_offset++];
			}
			end_offset += 3;
			while (dst_offset < end_offset) {
				dst_pixels[dst_offset++] = src_pixels[src_offset++];
			}
			dst_offset += dst_scansize;
			src_offset += src_scansize;
		}
	}

	public static void plot_scaled(int[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
		int var12 = var3;

		for (int var13 = -var8; var13 < 0; ++var13) {
			int var14 = (var4 >> 16) * var11;

			for (int var15 = -var7; var15 < 0; ++var15) {
				var2 = var1[(var3 >> 16) + var14];
				if (var2 != 0) {
					var0[var5++] = var2;
				} else {
					++var5;
				}

				var3 += var9;
			}

			var4 += var10;
			var3 = var12;
			var5 += var6;
		}

	}

	public static void plot_translucent(int[] dst_pixels, int[] src_pixels, int src_rgb, int src_offset, int dst_offset, int var5, int var6, int dst_scansize, int src_scansize, int alpha) {
		int dst_alpha = 256 - alpha;
		for (int var11 = -var6; var11 < 0; ++var11) {
			for (int var12 = -var5; var12 < 0; ++var12) {
				src_rgb = src_pixels[src_offset++];
				if (src_rgb != 0) {
					int dst_rgb = dst_pixels[dst_offset];
					dst_pixels[dst_offset++] = ((src_rgb & 0xff00ff) * alpha + (dst_rgb & 0xff00ff) * dst_alpha & 0xff00ff00) + ((src_rgb & 0xff00) * alpha + (dst_rgb & 0xff00) * dst_alpha & 0xff0000) >> 8;
				} else {
					++dst_offset;
				}
			}
			dst_offset += dst_scansize;
			src_offset += src_scansize;
		}
	}

	public static void plot_scaled_translucent(int[] dst_pixels, int[] src_pixels, int src_rgb, int src_offset, int var4, int dst_offset, int dst_scansize, int var7, int var8, int src_scansize, int var10, int var11, int alpha) {
		int var13 = 256 - alpha;
		int var14 = src_offset;
		for (int var15 = -var8; var15 < 0; ++var15) {
			int var16 = (var4 >> 16) * var11;
			for (int var17 = -var7; var17 < 0; ++var17) {
				src_rgb = src_pixels[(src_offset >> 16) + var16];
				if (src_rgb != 0) {
					int var18 = dst_pixels[dst_offset];
					dst_pixels[dst_offset++] = ((src_rgb & 0xff00ff) * alpha + (var18 & 0xff00ff) * var13 & 0xff00ff00) + ((src_rgb & 0xff00) * alpha + (var18 & 0xff00) * var13 & 0xff0000) >> 8;
				} else {
					++dst_offset;
				}
				src_offset += src_scansize;
			}
			var4 += var10;
			src_offset = var14;
			dst_offset += dst_scansize;
		}

	}

}
