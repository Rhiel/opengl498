package com.jagex.graphics.runetek4.opengl.textures;

import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.textures.DataType;
import com.jagex.graphics.runetek4.textures.PixelFormat;

public class OpenGLTextureRectangle extends OpenGLTexture2D {
	public boolean aBoolean9359;
	public int anInt9360;
	public float aFloat9361;
	public float aFloat9362;
	public int anInt9363;
	public boolean aBoolean9364;


	public OpenGLTextureRectangle(int i, PixelFormat class72, DataType class86, int i_9_, int i_10_) {
		super(i, class72, class86, i_9_, i_10_);
		anInt9363 = i_9_;
		anInt9360 = i_10_;
		if (target == 34037) {
			aFloat9361 = i_10_;
			aFloat9362 = i_9_;
			aBoolean9364 = false;
		} else {
			aFloat9361 = 1.0F;
			aFloat9362 = 1.0F;
			aBoolean9364 = true;
		}
		aBoolean9359 = false;
	}

	public OpenGLTextureRectangle(int i, int i_11_, int i_12_, boolean bool, int[] is, int i_13_, int i_14_) {
		super(i, i_11_, i_12_, bool, is, i_13_, i_14_, true);
		anInt9363 = i_11_;
		anInt9360 = i_12_;
		if (target == 34037) {
			aFloat9361 = i_12_;
			aFloat9362 = i_11_;
			aBoolean9364 = false;
		} else {
			aFloat9361 = 1.0F;
			aFloat9362 = 1.0F;
			aBoolean9364 = true;
		}
		aBoolean9359 = false;
	}

	public OpenGLTextureRectangle(int i, PixelFormat class72, DataType class86, int i_15_, int i_16_, boolean bool, byte[] is, PixelFormat class72_17_) {
		super(i, class72, class86, i_15_, i_16_, bool, is, class72_17_, true);
		anInt9363 = i_15_;
		anInt9360 = i_16_;
		if (target == 34037) {
			aFloat9361 = i_16_;
			aFloat9362 = i_15_;
			aBoolean9364 = false;
		} else {
			aFloat9361 = 1.0F;
			aFloat9362 = 1.0F;
			aBoolean9364 = true;
		}
		aBoolean9359 = false;
	}

	public OpenGLTextureRectangle(PixelFormat class72, DataType class86, int i, int i_18_, int i_19_, int i_20_) {
		super(3553, class72, class86, i_19_, i_20_);
		anInt9363 = i;
		anInt9360 = i_18_;
		aFloat9361 = (float) i_18_ / (float) i_20_;
		aFloat9362 = (float) i / (float) i_19_;
		aBoolean9364 = false;
		aBoolean9359 = true;
		set_repeat(false, false);
	}

	public OpenGLTextureRectangle(int i, int i_21_, int i_22_, int i_23_, int[] is) {
		super(3553, PixelFormat.RGBA, DataType.INT8, i_22_, i_23_);
		anInt9363 = i;
		anInt9360 = i_21_;
		set_pixels(0, i_23_ - i_21_, i, i_21_, is, 0, 0, true);
		aFloat9361 = (float) i_21_ / (float) i_23_;
		aFloat9362 = (float) i / (float) i_22_;
		aBoolean9364 = false;
		aBoolean9359 = true;
		set_repeat(false, false);
	}


	public OpenGLTextureRectangle(PixelFormat class72, DataType class86, int i, int i_24_, int i_25_, int i_26_, byte[] is, PixelFormat class72_27_) {
		super(3553, class72, class86, i_25_, i_26_);
		anInt9363 = i;
		anInt9360 = i_24_;
		set_pixels(0, i_26_ - i_24_, i, i_24_, is, class72_27_, 0, 0, true);
		aFloat9361 = (float) i_24_ / (float) i_26_;
		aFloat9362 = (float) i / (float) i_25_;
		aBoolean9364 = false;
		aBoolean9359 = true;
		set_repeat(false, false);
	}

	@Override
	public void set_linear_filter(boolean bool) {
		super.set_linear_filter(bool && !aBoolean9359);
	}

	public static OpenGLTextureRectangle method524(int i, int i_0_, boolean bool, int[] is, int i_1_, int i_2_) {
		if (GLManager.texture_non_pow2_supported || MathTools.is_power_of_two(i) && MathTools.is_power_of_two(i_0_)) {
			return new OpenGLTextureRectangle(3553, i, i_0_, bool, is, i_1_, i_2_);
		}
		if (GLManager.arb_rectangle_textures_supported) {
			return new OpenGLTextureRectangle(34037, i, i_0_, bool, is, i_1_, i_2_);
		}
		return new OpenGLTextureRectangle(i, i_0_, MathTools.get_greater_pow2(i), MathTools.get_greater_pow2(i_0_), is);
	}

	public static OpenGLTextureRectangle method525(PixelFormat class72, DataType class86, int i, int i_3_) {
		if (GLManager.texture_non_pow2_supported || MathTools.is_power_of_two(i) && MathTools.is_power_of_two(i_3_)) {
			return new OpenGLTextureRectangle(3553, class72, class86, i, i_3_);
		}
		if (GLManager.arb_rectangle_textures_supported) {
			return new OpenGLTextureRectangle(34037, class72, class86, i, i_3_);
		}
		return new OpenGLTextureRectangle(class72, class86, i, i_3_, MathTools.get_greater_pow2(i), MathTools.get_greater_pow2(i_3_));
	}

	public static OpenGLTextureRectangle method526(PixelFormat class72, DataType class86, int i, int i_4_, boolean bool, byte[] is, PixelFormat class72_5_) {
		if (GLManager.texture_non_pow2_supported || MathTools.is_power_of_two(i) && MathTools.is_power_of_two(i_4_)) {
			return new OpenGLTextureRectangle(3553, class72, class86, i, i_4_, bool, is, class72_5_);
		}
		if (GLManager.arb_rectangle_textures_supported) {
			return new OpenGLTextureRectangle(34037, class72, class86, i, i_4_, bool, is, class72_5_);
		}
		return new OpenGLTextureRectangle(class72, class86, i, i_4_, MathTools.get_greater_pow2(i), MathTools.get_greater_pow2(i_4_), is, class72_5_);
	}

}
