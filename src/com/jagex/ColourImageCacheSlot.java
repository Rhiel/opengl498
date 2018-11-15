package com.jagex;
/* ColourImageCacheSlot - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ColourImageCacheSlot extends Linkable {
	public static IoSession session;
	static RSString aClass16_2443;
	public int slot_id;
	static Packet aClass23_Sub5_2445;
	public int image_id;

	public static void method899(byte b) {
		aClass23_Sub5_2445 = null;
		StaticMethods.anIntArray2441 = null;
		if (b < -44) {
			GameClient.master_index_file = null;
			aClass16_2443 = null;
		}
	}

	static final RSString method900(Packet buffer) {
		return DataBuffer.method1216(buffer, (byte) -26, 32767);
	}

	static final void method901(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_) {
		if (i_4_ != -4838) {
			aClass23_Sub5_2445 = null;
		}
		if ((i_0_ ^ 0xffffffff) == (i_1_ ^ 0xffffffff)) {
			Huffman.method1578(i_0_, true, i_2_, i, i_3_);
		} else if ((-i_0_ + i ^ 0xffffffff) <= (VarpDefinition.anInt3728 ^ 0xffffffff) && (i - -i_0_ ^ 0xffffffff) >= (Class35.anInt554 ^ 0xffffffff) && (-i_1_ + i_3_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff) && (StaticMethods.anInt3435 ^ 0xffffffff) <= (i_1_ + i_3_ ^ 0xffffffff)) {
			GroundObjEntity.method1138(i_2_, i_3_, (byte) 58, i_1_, i_0_, i);
		} else {
			Class56.method1185(i_3_, i_1_, i_2_, i, 3, i_0_);
		}
	}

	public static final void method902(int i, int i_5_, int i_6_, int i_7_) {
		Class35.anInt554 = i;
		VarpDefinition.anInt3728 = i_5_;
		Class88.anInt1503 = i_7_;
		StaticMethods.anInt3435 = i_6_;
	}

	public ColourImageCacheSlot(int i, int i_16_) {
		image_id = i;
		slot_id = i_16_;
	}

	static {
		aClass16_2443 = RSString.createString("Hier wechseln");
		aClass23_Sub5_2445 = new Packet(new byte[5000]);
	}
}
