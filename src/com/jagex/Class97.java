package com.jagex;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
/* Class97 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;

public class Class97 {
	public static RSString aClass16_1631 = RSString.createString("Geben Sie Ihren Benutzernamen");
	public static RSString aClass16_1636 = RSString.createString(")1");
	public static RSString aClass16_1637 = RSString.createString("Your friend list is full)3 Max of 100 for free users)1 and 200 for members)3");
	public static RSString aClass16_1638;
	public static RSString aClass16_1639;
	public static RSString aClass16_1640 = aClass16_1637;
	public static RSString aClass16_1641;
	public static RSString passwordLabel;
	public static RSString aClass16_1644;
	public static int anInt1645;
	public static Stereo aStereo_1646;
	public static RSString aClass16_1647;

	static final void method1488(int i) {
		Class33.cached_sprites.clear();
		AbstractMouseWheel.aModelList_1166.clearModelCache((byte) 91);
		Class98.aJList_1654.clear();
	}

	static void render_colourchooser_slider(int x, int y, RSInterface widget, int hue) {
		for (int saturation = 7; saturation >= 0; saturation--) {
			for (int value = 127; value >= 0; value--) {
				int hsv = (hue & 0x3f) << 10 | (saturation & 0x7) << 7 | value & 0x7f;
				ColourUtil.initialiseColorTable(false, true);
				int rgb = ColourUtil.hslToRgbTable[hsv];
				ColourUtil.destroyColorTable(false, true);
				if (GLManager.opengl_mode) {
					GLShapes.fill_rectangle((widget.layout_width * value >> 7) + x, y + ((7 - saturation) * widget.layout_height >> 3), (widget.layout_width >> 7) + 1, (widget.layout_height >> 3) + 1, ~0xffffff | rgb);
				} else {
					Rasterizer2D.fill_rectangle((widget.layout_width * value >> 7) + x, y + ((7 - saturation) * widget.layout_height >> 3), (widget.layout_width >> 7) + 1, (widget.layout_height >> 3) + 1, ~0xffffff | rgb);
				}
			}
		}
	}

	public static void render_colourchooser_diagram(int draw_x, int draw_y, RSInterface component) {
		int value = 63;
		int saturation = 7;
		for (int hue = 63; hue >= 0; hue--) {
			int hsv = (hue & 0x3f) << 10 | (saturation & 0x7) << 7 | value & 0x7f;
			ColourUtil.initialiseColorTable(false, true);
			int rgb = ColourUtil.hslToRgbTable[hsv];
			ColourUtil.destroyColorTable(false, true);
			if (GLManager.opengl_mode) {
				GLShapes.fill_rectangle(draw_x, draw_y + ((63 - hue) * component.layout_height >> 6), component.layout_width, (component.layout_height >> 6) + 1, ~0xffffff | rgb);
			} else {
				Rasterizer2D.fill_rectangle(draw_x, draw_y + ((63 - hue) * component.layout_height >> 6), component.layout_width, (component.layout_height >> 6) + 1, ~0xffffff | rgb);
			}
		}
	}

	static void render_debug_block(int draw_x, int draw_y, RSInterface widget) {
		int x = draw_x + widget.layout_width;
		int y = draw_y + 15;
		{
			int color = -256;
			if (ReflectionAntiCheat.anInt79 < 20) {
				color = -65536;
			}
			FontCache.p12_full.draw_text_right_anchor(RSString.joinRsStrings(new RSString[] { Class97.aClass16_1641, RSString.valueOf(ReflectionAntiCheat.anInt79) }), x, y, color, 0);
			y += 15;
		}
		{
			Runtime runtime = Runtime.getRuntime();
			long l = runtime.totalMemory() / 1024L;
			long l_10_ = l - runtime.freeMemory() / 1024L;
			int i_11_ = -256;
			if (l_10_ > 262144L) {
				i_11_ = -65536;
			}
			FontCache.p12_full.draw_text_right_anchor(RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3294, RSString.valueOf((int) l_10_), Class55.slash, RSString.valueOf((int) l), Class55.aClass16_868 }), x, y, i_11_, 0);
			y += 15;
		}
		{
			int ping = (int) GameClient.pingManager.getPing();
			RSString text = StaticMethods.NA;
			if (ping != -1) {
				text = RSString.joinRsStrings(new RSString[] { StaticMethods.PING, RSString.valueOf(ping), StaticMethods.MS });
			}
			int color = -256;
			if (ping > 500) {
				color = -65536;
			}
			FontCache.p12_full.draw_text_right_anchor(text, x, y, color, 0);
			y += 15;
		}
		{
			int i_15_ = (GLManager.allocated_textures_size + GLManager.allocated_vertexbuffers_size + GLManager.allocated_sprites_size) / 1024;
			FontCache.p12_full.draw_text_right_anchor(RSString.joinRsStrings(new RSString[] { StaticMethods.OFFHEAP, RSString.valueOf(i_15_), Class55.aClass16_868 }), x, y, i_15_ > 65536 ? -65536 : -256, 0);
		}
	}

	/**
	 * Makes the specified color transparent in a buffered image.
	 * 
	 * @param im
	 * @param color
	 * @return
	 */
	public static Image makeColorTransparent(BufferedImage im, final Color color) {
		RGBImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;

			@Override
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};
		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public static void method1491(int i) {
		aClass16_1631 = null;
		passwordLabel = null;
		aStereo_1646 = null;
		if (i == 0) {
			aClass16_1644 = null;
			aClass16_1639 = null;
			aClass16_1636 = null;
			aClass16_1641 = null;
			aClass16_1647 = null;
			aClass16_1640 = null;
			aClass16_1638 = null;
			aClass16_1637 = null;
		}
	}

	static {
		aClass16_1638 = RSString.createString("Password: ");
		aClass16_1639 = RSString.createString("Select a world");
		passwordLabel = aClass16_1638;
		aClass16_1644 = aClass16_1639;
		aClass16_1641 = RSString.createString("Fps:");
		anInt1645 = -1;
		aClass16_1647 = RSString.createString(" ");
	}
}
