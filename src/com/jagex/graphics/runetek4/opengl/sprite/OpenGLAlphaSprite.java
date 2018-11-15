package com.jagex.graphics.runetek4.opengl.sprite;

import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTextureRectangle;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLAlphaSprite extends OpenGLSprite {

	public OpenGLAlphaSprite(int var1, int var2, int var3, int var4, int var5, int var6, int[] var7) {
		super(var1, var2, var3, var4, var5, var6, var7);
	}

	public OpenGLAlphaSprite(SoftwareSprite var1) {
		super(var1);
	}

	@Override
	public void upload_data(int[] var1) {
		texture_width = MathTools.get_greater_pow2(width);
		texture_height = MathTools.get_greater_pow2(height);
		for (int index = 0; index < var1.length; index++) {
			var1[index] = var1[index] & 0xffffffff;
		}
		texture = OpenGLTextureRectangle.method525(PixelFormat.RGBA, DataType.INT8, texture_width, texture_height);
		texture.set_pixels(0, 0, texture_width, texture_height, var1, 0, width, false);
	}

}
