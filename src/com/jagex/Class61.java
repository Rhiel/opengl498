package com.jagex;

public class Class61 {
	static Js5 aClass105_958;
	static RSInterface aClass64_959;
	static RSString aClass16_960;
	static RSString aClass16_961;
	static RSString aClass16_966 = RSString.createString("Bitte benutzen Sie eine andere Welt)3");
	public static RSString aClass16_968;
	public static RSString aClass16_969;
	static RSString aClass16_970 = RSString.createString("Mitglieder)2Welt");
	public static RSString aClass16_map = RSString.createString("Grabbing Maps from server -");
	public static RSString aClass16_map2 = RSString.createString("Loading Maps -");;

	static final void method1197(boolean bool, int i, int i_0_, int i_1_, int i_2_, int i_3_) {
		StaticMedia.scrollbar[0].draw(i_0_, i_3_);
		StaticMedia.scrollbar[1].draw(i_0_, -16 + i + i_3_);
		int i_4_ = i * (i - 32) / i_1_;
		if (i_4_ < 8) {
			i_4_ = 8;
		}
		int i_5_ = i_2_ * (-32 + i - i_4_) / (i_1_ - i);
		if (bool != true) {
			aClass105_958 = null;
		}
		Rasterizer2D.fill_rectangle(i_0_, i_3_ - -16, 16, -32 + i, StaticMethods2.anInt2012);
		Rasterizer2D.fill_rectangle(i_0_, i_5_ + 16 + i_3_, 16, i_4_, Huffman.anInt1827);
		Rasterizer2D.draw_vertical_line(i_0_, i_5_ + 16 + i_3_, i_4_, StaticMethods.anInt3309);
		Rasterizer2D.draw_vertical_line(1 + i_0_, i_3_ + 16 - -i_5_, i_4_, StaticMethods.anInt3309);
		Rasterizer2D.draw_horizontal_line(i_0_, 16 + i_3_ + i_5_, 16, StaticMethods.anInt3309);
		Rasterizer2D.draw_horizontal_line(i_0_, i_5_ + i_3_ - -17, 16, StaticMethods.anInt3309);
		Rasterizer2D.draw_vertical_line(15 + i_0_, i_5_ + i_3_ + 16, i_4_, StaticMethods2.anInt4487);
		Rasterizer2D.draw_vertical_line(i_0_ - -14, 17 + i_3_ + i_5_, i_4_ - 1, StaticMethods2.anInt4487);
		Rasterizer2D.draw_horizontal_line(i_0_, i_4_ + i_5_ + 15 + i_3_, 16, StaticMethods2.anInt4487);
		Rasterizer2D.draw_horizontal_line(1 + i_0_, i_4_ + i_5_ + 14 + i_3_, 15, StaticMethods2.anInt4487);
	}


	static final void method1199(int i, boolean bool, RSString class16) {
		GameClient.outBuffer.putOpcode(68);
		GameClient.outBuffer.p1(i);
		GameClient.outBuffer.putLong1(class16.toUsernameLong(), 1650435232);
		if (bool != false) {
			method1200((byte) -59);
		}
	}

	public static void method1200(byte b) {
		aClass16_969 = null;
		aClass16_968 = null;
		aClass105_958 = null;
		aClass16_970 = null;
		aClass16_961 = null;
		aClass64_959 = null;
		aClass16_966 = null;
		aClass16_960 = null;
	}

	static {
		aClass16_969 = RSString.createString("Connecting to update server");
		aClass16_960 = aClass16_969;
		aClass16_968 = RSString.createString("Loaded wordpack");
		aClass16_961 = aClass16_968;
	}
}
