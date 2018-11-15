package com.jagex;
/* ISAACPacket - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ISAACPacket extends Packet
{
	public static ModelList models_cache;
	public static boolean[] aBooleanArray3531 = new boolean[5];
	public ISAACCipher cypher;
	public int bitPosition;
	public static int[] anIntArray3543;
	
	public static void method484(boolean bool) {
		models_cache = null;
		aBooleanArray3531 = null;
		anIntArray3543 = null;
	}
	
	public void end_bitwise_access(int i) {
		index = (7 + bitPosition) / 8;
	}
	
	public void putOpcode(int i) {
		byteBuffer[index++] = (byte) (cypher.getNextValue(MathUtils.power(2976, 2720)) + i);
	}
	
	public void start_bitwise_access(int i) {
		bitPosition = 8 * index;
	}
	
	public int bitdiff(int i, int i_1_) {
		return -bitPosition + i_1_ * 8;
	}
	
	public int getBits(int i) {
		int i_2_ = bitPosition >> 3;
		int i_3_ = 0;
		int i_4_ = 8 + -(bitPosition & 0x7);
		bitPosition += i;
		for (/**/; i > i_4_; i_4_ = 8) {
			i_3_ += (ObjectNode.anIntArray2245[i_4_] & byteBuffer[i_2_++]) << -i_4_ + i;
			i -= i_4_;
		}
		if (i == i_4_) {
			i_3_ += ObjectNode.anIntArray2245[i_4_] & byteBuffer[i_2_];
		} else {
			i_3_ += byteBuffer[i_2_] >> -i + i_4_ & ObjectNode.anIntArray2245[i];
		}
		return i_3_;
	}
	
	final void xgdata(int i, int i_5_, int i_6_, byte[] bs) {
		if (i < -28) {
			for (int i_7_ = 0; i_5_ > i_7_; i_7_++) {
				bs[i_6_ + i_7_] = (byte) (byteBuffer[index++] - cypher.getNextValue(256));
			}
		}
	}
	
	ISAACPacket(int size) {
		super(size);
	}
	
	final int getIncomingOpcode(byte b) {
		if (b != 47) {
			bitdiff(-18, -51);
		}
		return byteBuffer[index++] - cypher.getNextValue(256) & 0xff;
	}
	
	final void initialize_cypher(byte b, int[] seed) {
		cypher = new ISAACCipher(seed);
	}
	
	public int getBitPosition() {
		return bitPosition;
	}
	
	static {
		ComponentMinimap.elements_count = 0;
		models_cache = new ModelList(50);
	}
}
