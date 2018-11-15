package com.jagex.graphics.runetek4.opengl.textures;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jagex.Js5;
import com.jagex.MemoryCache;

import jaggl.OpenGL;
import me.waliedyassen.graphics.rasterizer.AlphaMode;
import me.waliedyassen.materials.MaterialRaw;

/**
 * @author Walied K. Yassen
 */
public class OpenGLTextureCache {

	private static final int[] brightness_table = new int[256];
	private static double brightness = -1.0;

	private final MemoryCache cache = new MemoryCache(256);
	private final Js5 textures_js5;

	public OpenGLTextureCache(Js5 textures_js5) {
		this.textures_js5 = textures_js5;
	}

	public OpenGLTexture2D getTexture(MaterialRaw material) {
		OpenGLTexture2D cached = (OpenGLTexture2D) cache.get(material.id);
		if (cached != null) {
			return cached;
		}
		if (!textures_js5.is_group_cached(material.id)) {
			return null;
		}
		int size = material.get_size();
		OpenGLTexture2D texture;
		int[] pixels;
		if (material.alpha_mode != AlphaMode.ALPHA_TESTED || !has_alpha(material.effect_type)) {
			pixels = get_pixels_opaque(material, 0.7F);
		} else {
			pixels = get_pixels_translucent(material, 0.7F);
		}
		texture = new OpenGLTexture2D(OpenGL.GL_TEXTURE_2D, size, size, material.mipmapping != 0, pixels, 0, 0, false);
		texture.set_repeat(material.repeat_s, material.repeat_t);
		cache.put(material.id, texture);
		return cached;

	}

	public int[] get_pixels_translucent(MaterialRaw material, double brightness) {
		int[] pixels = get_pixels_opaque(material, brightness);
		if (pixels == null) {
			return null;
		}
		for (int pixel = 0; pixel < pixels.length; pixel++) {
			if ((pixels[pixel] & 0xffffff) != 0) {
				// has any colour, add alpha
				pixels[pixel] = pixels[pixel] | ~0xffffff;
			} else {
				// as no colour, clip alpha
				pixels[pixel] = pixels[pixel] & 0xffffff;
			}
		}
		return pixels;
	}

	public int[] get_pixels_opaque(MaterialRaw material, double brightness) {
		byte[] data = textures_js5.get_file(material.id, 0);
		if (data == null) {
			return null;
		}
		int[] pixels;
		try {
			pixels = get_image_pixels(data, material.fliped);
		} catch (IOException e) {
			pixels = null;
		}
		if (pixels == null) {
			return null;
		}
		set_brightness(pixels, brightness);
		return pixels;
	}

	public void clear() {
		cache.clear();
	}

	public static int[] get_image_pixels(byte[] data, boolean fliped) throws IOException {
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));
		if (image == null) {
			return null;
		}
		int[] pixels = get_image_pixels(image);
		if (fliped) {
			for (int y = image.getHeight() - 1; y >= 0; y--) {
				int offset = y * image.getWidth();
				for (int x = (1 + y) * image.getWidth(); offset < x; offset++) {
					x--;
					int i_33_ = pixels[offset];
					pixels[offset] = pixels[x];
					pixels[x] = i_33_;
				}
			}
		}
		return pixels;
	}

	public static int[] get_image_pixels(BufferedImage image) {
		if (image.getType() == BufferedImage.TYPE_BYTE_GRAY || image.getType() == BufferedImage.TYPE_CUSTOM) {
			int[] temp = null;
			temp = image.getRaster().getPixels(0, 0, image.getWidth(), image.getHeight(), temp);
			int[] pixels = new int[image.getWidth() * image.getHeight()];
			if (image.getType() == BufferedImage.TYPE_BYTE_GRAY) {
				for (int index = 0; index < pixels.length; index++) {
					pixels[index] = 0xff000000 | temp[index] << 16 | temp[index] << 8 | temp[index];
				}
			} else {
				for (int index = 0; index < pixels.length; index++) {
					int new_index = index * 2;
					pixels[index] = (temp[new_index] << 8) + (temp[new_index] << 16) + temp[new_index] + (temp[new_index + 1] << 24);
				}
			}
			return pixels;
		}
		return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	}

	public static void set_brightness(int[] colours, double brightness) {
		if (OpenGLTextureCache.brightness != brightness) {
			for (int grade = 0; grade < 256; grade++) {
				int value = (int) (Math.pow(grade / 255.0, brightness) * 255.0);
				brightness_table[grade] = value > 255 ? 255 : value;
			}
			OpenGLTextureCache.brightness = brightness;
		}
		for (int i = 0; i < colours.length; i++) {
			int i_47_ = brightness_table[colours[i] >> 16 & 0xff];
			int i_48_ = brightness_table[colours[i] >> 8 & 0xff];
			int i_49_ = brightness_table[colours[i] >> 0 & 0xff];
			colours[i] = colours[i] & ~0xffffff | i_47_ << 16 | i_48_ << 8 | i_49_;
		}
	}

	public static boolean has_alpha(int effect_type) {
		return effect_type != 1 && effect_type != 7;
	}
}
