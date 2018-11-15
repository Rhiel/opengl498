package com.jagex;

import java.awt.Component;

import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;

public abstract class AbstractMouseWheel {
	static RSString aClass16_1163 = RSString.createString(" GMT");
	static ModelList aModelList_1166;
	static RSString aClass16_1167 = RSString.createString(" Sekunde(Xn(Y -Ubertragen)3");
	static RSString aClass16_1169 = RSString.createString("oder ung-Ultiges Passwort)3");

	abstract int method1236(int i);

	static final void method1237(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_) {
		VarpDefinition.method632(i_2_, (byte) -30, i_3_, Class4.anIntArrayArray98[i_0_++], i);
		VarpDefinition.method632(i_2_, (byte) -30, i_3_, Class4.anIntArrayArray98[i_1_--], i);
		for (int i_5_ = i_0_; i_1_ >= i_5_; i_5_++) {
			int[] is = Class4.anIntArrayArray98[i_5_];
			is[i] = is[i_2_] = i_3_;
		}
		if (i_4_ != 8622) {
			method1239(-27);
		}
	}

	public abstract void detach(Component component);

	public static void method1239(int i) {
		aClass16_1169 = null;
		aModelList_1166 = null;
		LobbyWorld.selectable_world_sprites = null;
		aClass16_1167 = null;
		if (i != 3) {
			method1239(-118);
		}
		aClass16_1163 = null;
	}

	public abstract void attach(Component component);

	static final void method1241(RSInterface widget, int draw_x, int draw_y, int i_6_) {
		if (GLManager.opengl_mode) {
			GLState.set_clipping(draw_x, draw_y, widget.layout_width + draw_x, widget.layout_height + draw_y);
		}
		if (Huffman.anInt1819 >= 3) {
			if (!GLManager.opengl_mode) {
				Rasterizer2D.method216(draw_x, draw_y, 0, widget.anIntArray1064, widget.anIntArray1109);
			} else {
				Sprite sprite = widget.get_sprite(false);
				if (null != sprite) {
					sprite.draw_clipped_left_anchor(draw_x, draw_y);
				}
			}
		} else {
			if (GLManager.opengl_mode) {
				((OpenGLSprite) StaticMedia.compass).draw(draw_x, draw_y, widget.layout_width, widget.layout_height, StaticMedia.compass.width / 2, StaticMedia.compass.height / 2, Class35.cameraDirection, 256, (OpenGLSprite) widget.get_sprite(false));
			} else {
				((SoftwareSprite) StaticMedia.compass).draw_offseted_clipped(draw_x, draw_y, widget.layout_width, widget.layout_height, StaticMedia.compass.width / 2, StaticMedia.compass.height / 2, Class35.cameraDirection, 256, widget.anIntArray1064, widget.anIntArray1109);
			}
		}
		Class36.needs_clipping[i_6_] = true;
	}

	public AbstractMouseWheel() {
		/* empty */
	}

	static {
		aModelList_1166 = new ModelList(50);
	}
}
