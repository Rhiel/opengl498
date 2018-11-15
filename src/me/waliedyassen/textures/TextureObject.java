package me.waliedyassen.textures;

import com.jagex.Queuable;

public class TextureObject extends Queuable {

	public final int[] pixels;

	public TextureObject(int[] pixels) {
		this.pixels = pixels;
	}

}