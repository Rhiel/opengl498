package com.jagex.graphics.runetek4.opengl.clipping;

import com.jagex.graphics.runetek4.opengl.textures.OpenGLTextureRectangle;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public final class OpenGLClippingMask {
	
	public OpenGLTextureRectangle texture;

	public OpenGLClippingMask(int width, int height, byte[] mask) {
		texture = OpenGLTextureRectangle.method526(PixelFormat.ALPHA, DataType.INT8, width, height, false, mask, PixelFormat.ALPHA);
		texture.set_repeat(false, false);
	}

	public static OpenGLClippingMask create_clippingmask(int width, int height, int[] starts, int[] lengths) {
		byte[] masks = new byte[width * height];
		for (int i_3_ = 0; i_3_ < height; i_3_++) {
			int i_4_ = i_3_ * width + starts[i_3_];
			for (int i_5_ = 0; i_5_ < lengths[i_3_]; i_5_++) {
				masks[i_4_++] = (byte) -1;
			}
		}
		return new OpenGLClippingMask(width, height, masks);
	}

}
