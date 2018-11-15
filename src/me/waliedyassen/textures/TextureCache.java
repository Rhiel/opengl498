package me.waliedyassen.textures;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jagex.Js5;
import com.jagex.MemoryCache;

import me.waliedyassen.graphics.rasterizer.AlphaMode;
import me.waliedyassen.materials.MaterialRaw;

public class TextureCache {

	public static final int[] brightness_table = new int[256];
	public static double brightness = -1.0;

	public final MemoryCache cache = new MemoryCache(512);
	public final Js5 textures_js5;

	public TextureCache(Js5 textures_js5) {
		this.textures_js5 = textures_js5;
	}

	public int[] get_pixels(MaterialRaw material, double brightness) {
		if (material == null) {
			return null;
		}
		TextureObject object = get_object(material, brightness);
		if (object == null) {
			return null;
		}
		return object.pixels;
	}

	public TextureObject get_object(MaterialRaw material, double brightness) {
		final int size = material.is_small ? 64 : 128;
		TextureObject object = (TextureObject) cache.get(material.id << 16 | size);
		if (object != null) {
			return object;
		}
		int[] pixels;
		if (material.alpha_mode != AlphaMode.ALPHA_TESTED) {
			pixels = get_diffuse_pixels(material, brightness);
		} else {
			pixels = get_transparent_pixels(material, brightness);
		}
		if (pixels == null) {
			return null;
		}
		object = new TextureObject(pixels);
		cache.put(material.id << 16 | size, object);
		return object;
	}

	public int[] get_transparent_pixels(MaterialRaw material, double brightness) {
		int[] pixels = get_diffuse_pixels(material, brightness);
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

	public int[] get_diffuse_pixels(MaterialRaw material, double brightness) {
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
		if (TextureCache.brightness != brightness) {
			for (int grade = 0; grade < 256; grade++) {
				int value = (int) (Math.pow(grade / 255.0, brightness) * 255.0);
				brightness_table[grade] = value > 255 ? 255 : value;
			}
			TextureCache.brightness = brightness;
		}
		for (int i = 0; i < colours.length; i++) {
			int i_47_ = brightness_table[colours[i] >> 16 & 0xff];
			int i_48_ = brightness_table[colours[i] >> 8 & 0xff];
			int i_49_ = brightness_table[colours[i] >> 0 & 0xff];
			colours[i] = colours[i] & ~0xffffff | i_47_ << 16 | i_48_ << 8 | i_49_;
		}
	}
}
