package com.jagex;
/* Class87_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Class87_Sub4 extends Class87
{
	public static int[] anIntArray2828;
	public final int anInt2831;
	static RSString aClass16_2833;
	public final int anInt2834;
	public final int anInt2835;
	static RSString aClass16_2836 = RSString.createString("Anmelde)2Zeitlimit -Uberschritten)3");
	public final int anInt2839;
	public static RSString aClass16_2840;
	public static int anInt2841;
	public final int anInt2842;
	public static int anInt2843 = 0;
	public final int anInt2845;
	public final int anInt2848;
	public final int anInt2850;
	static final void method1422(Js5 class105, byte b) {
		Varbit.varbitContainer = class105;
	}

	@Override
	public final void method1408(int i, int i_0_, int i_1_) {
		if (i_1_ > -101) {
			anInt2843 = 113;
		}
	}

	static final boolean method1425(byte b, int i) {
		if ((i ^ 0xffffffff) > -1) {
			return false;
		}
		int i_4_ = ContextMenu.menuActionID[i];
		if (i_4_ >= 2000) {
			i_4_ -= 2000;
		}
		if (i_4_ == 1002) {
			return true;
		}
		return false;
	}
	
	Class87_Sub4(int i, int i_5_, int i_6_, int i_7_, int i_8_, int i_9_, int i_10_, int i_11_, int i_12_, int i_13_) {
		super(-1, i_12_, i_13_);
		anInt2835 = i_5_;
		anInt2842 = i_10_;
		anInt2850 = i_7_;
		anInt2845 = i_6_;
		anInt2848 = i;
		anInt2839 = i_8_;
		anInt2834 = i_9_;
		anInt2831 = i_11_;
	}

	public static final byte[] ReadFile(String s)
	{
		try
		{
			File file = new File(s);
			int i = (int)file.length();
			byte abyte0[] = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(abyte0, 0, i);
			datainputstream.close();
			return abyte0;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return null;
	}

	public static final void method1427(int i, boolean bool) {
		if (bool != false) {
			method1427(-80, true);
		}
		if (AbstractTimer.hasActiveInterface(-10924, i)) {
			Class71_Sub2_Sub1.method1283(StaticMethods.cached_interfaces[i], -1, -22561);
		}
	}

	@Override
	public final void method1406(int i, int i_31_, int i_32_) {
		if (i != 1) {
			method1408(-124, -126, 109);
		}
	}
	
	public static void method1428(byte b) {
		if (b != 65) {
			method1427(-103, true);
		}
		aClass16_2840 = null;
		GameClient.client_palette = null;
		aClass16_2833 = null;
		aClass16_2836 = null;
		anIntArray2828 = null;
	}

	@Override
	public final void method1409(int i, int i_33_, int i_34_) {
		int i_35_ = i * anInt2848 >> 12;
		int i_36_ = i_34_ * anInt2835 >> 12;
		int i_37_ = i * anInt2845 >> 12;
		int i_38_ = i_34_ * anInt2850 >> 12;
		int i_39_ = anInt2839 * i >> 12;
		int i_40_ = anInt2834 * i_34_ >> 12;
		int i_41_ = anInt2831 * i_34_ >> 12;
		int i_42_ = i * anInt2842 >> 12;
		Class57.method1189(i_38_, i_40_, i_41_, -2, anInt1487, i_35_, i_42_, i_36_, i_37_, i_39_);
	}
	
	static {
		anInt2841 = 0;
		aClass16_2840 = RSString.createString("Information");
		aClass16_2833 = aClass16_2840;
		GameClient.client_palette = new short[256];
	}
}
