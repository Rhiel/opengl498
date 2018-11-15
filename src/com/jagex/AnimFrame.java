package com.jagex;
/* AnimFrame - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class AnimFrame {
	public static short[] translateYBuffer = new short[500];
	public static short[] aShortArray288 = new short[500];
	public short[] transformation_y;
	public short[] labels;
	public static short[] translateXBuffer;
	public static short[] translateZBuffer = new short[500];
	public AnimBase base;
	public boolean modifies_alpha;
	public int num_transformations = -1;
	public short[] base_indices;
	public short[] transformation_x;
	public short[] transformation_z;
	public static short[] translateIndicesBuffer;

	public boolean modifies_color = false;
	public boolean aBoolean2646;
	public static byte[] aByteArray2638 = new byte[500];
	public byte[] tweening_properties;

	public static void method136() {
		translateIndicesBuffer = null;
		translateXBuffer = null;
		translateYBuffer = null;
		translateZBuffer = null;
		aShortArray288 = null;
		aByteArray2638 = null;
	}

	AnimFrame(byte[] bs, AnimBase anim) {
		base = null;
		modifies_alpha = false;
		num_transformations = 0;
		aBoolean2646 = false;
		base = anim;
		Packet ibuffer = new Packet(bs);
		Packet tbuffer = new Packet(bs);
		ibuffer.g1();
		ibuffer.index += 2;
		int numTranslations = ibuffer.g1();
		int transIndex = 0;
		int i_2_ = -1;
		int i_3_ = -1;
		tbuffer.index = ibuffer.index + numTranslations;
		for (int n = 0; n < numTranslations; n++) {
			int transType = base.transformation_type[n];
			if (transType == 0) {
				i_2_ = n;
			}
			int flag = ibuffer.g1();
			if (flag > 0) {
				if (transType == 0) {
					i_3_ = n;
				}
				translateIndicesBuffer[transIndex] = (short) n;
				short standardScale = 0;
				if (transType == 3 || transType == 10) {
					standardScale = (short) 128;
				}
				if ((flag & 0x1) != 0) {
					translateXBuffer[transIndex] = (short) tbuffer.gSmart1or2s();
				} else {
					translateXBuffer[transIndex] = standardScale;
				}
				if ((flag & 0x2) != 0) {
					translateYBuffer[transIndex] = (short) tbuffer.gSmart1or2s();
				} else {
					translateYBuffer[transIndex] = standardScale;
				}
				if ((flag & 0x4) != 0) {
					translateZBuffer[transIndex] = (short) tbuffer.gSmart1or2s();
				} else {
					translateZBuffer[transIndex] = standardScale;
				}

				aByteArray2638[transIndex] = (byte) (flag >>> 3 & 0x3);
				if (transType == 2 || transType == 9) {
					translateXBuffer[transIndex] = (short) (translateXBuffer[transIndex] << 2 & 0x3fff);
					translateYBuffer[transIndex] = (short) (translateYBuffer[transIndex] << 2 & 0x3fff);
					translateZBuffer[transIndex] = (short) (translateZBuffer[transIndex] << 2 & 0x3fff);
				}
				if (transType == 1) {
					translateXBuffer[transIndex] = (short) (translateXBuffer[transIndex] >> 2);
					translateYBuffer[transIndex] = (short) (translateYBuffer[transIndex] >> 2);
					translateZBuffer[transIndex] = (short) (translateZBuffer[transIndex] >> 2);
				}
				aShortArray288[transIndex] = (short) -1;
				if (transType >= 1 && transType <= 3) {
					if (i_2_ > i_3_) {
						aShortArray288[transIndex] = (short) i_2_;
						i_3_ = i_2_;
					}
				}
				if (transType == 5) {
					modifies_alpha = true;
				} else if (transType == 7) {
					modifies_color = true;
				} else if (transType == 9 || transType == 10 || transType == 8) {
					aBoolean2646 = true;
				}
				transIndex++;
			}
		}
		if (tbuffer.index != bs.length) {
			throw new RuntimeException("here89");
		}
		num_transformations = transIndex;
		base_indices = new short[transIndex];
		transformation_x = new short[transIndex];
		transformation_y = new short[transIndex];
		transformation_z = new short[transIndex];
		labels = new short[transIndex];
		tweening_properties = new byte[transIndex];
		for (int i_6_ = 0; i_6_ < transIndex; i_6_++) {
			base_indices[i_6_] = translateIndicesBuffer[i_6_];
			transformation_x[i_6_] = translateXBuffer[i_6_];
			transformation_y[i_6_] = translateYBuffer[i_6_];
			transformation_z[i_6_] = translateZBuffer[i_6_];
			labels[i_6_] = aShortArray288[i_6_];
			tweening_properties[i_6_] = aByteArray2638[i_6_];// added
		}
	}

	static {
		translateXBuffer = new short[500];
		translateIndicesBuffer = new short[500];
	}
}
