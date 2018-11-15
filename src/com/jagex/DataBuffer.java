package com.jagex;
/* DataBuffer - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class DataBuffer
{
	static RSString aClass16_976;
	static short[] aShortArray983 = { 6798, 8741, 25238, 4626, 4550 };
	public static int clanChatSize;
	public static int anInt992 = 0;
	static boolean no_direct_data_buffer = false;

	public static Object create_data_buffer(boolean use_array, byte[] data) {
		if ( data == null ) {
			return null;
		}
		if ( data.length > 136 && !no_direct_data_buffer ) {
			try {
				DataBuffer databuffer = new DirectDataBuffer();
				databuffer.put_data(data);
				return databuffer;
			} catch (Throwable throwable) {
				throwable.printStackTrace();
				no_direct_data_buffer = true;
			}
		}
		if ( use_array ) {
			return ArrayUtils.clone(data, (byte) 118);
		}
		return data;
	}

	public static byte[] unpack_data_buffer(boolean clone_array, Object in) {
		if ( in == null ) {
			return null;
		}
		if ( in instanceof byte[] ) {
			byte[] data = (byte[]) in;
			if ( clone_array ) {
				return ArrayUtils.clone(data, (byte) 118);
			}
			return data;
		}
		if ( in instanceof DataBuffer ) {
			DataBuffer databuffer = (DataBuffer) in;
			return databuffer.get_data(0);
		}
		throw new IllegalArgumentException();
	}

	abstract void put_data(byte[] bs);
	
	static final boolean method1208(int i, int i_0_) {
		if ((i >> 28 & 0x1) == 0) {
			return false;
		}
		return true;
	}
	
	abstract byte[] get_data(int i);
	
	public static void method1214(int i) {
		aClass16_976 = null;
		aShortArray983 = null;
		ContextMenu.chooseOptionText = null;
	}
	
	static final boolean method1215(int i, int i_5_, int i_6_) {
		if (i != 13417) {
			return true;
		}
		if ((i_5_ >> i_6_ + 1 & 0x1) == 0) {
			return false;
		}
		return true;
	}
	
	static final RSString method1216(Packet class23_sub5, byte b, int i) {
		try {
			RSString class16 = new RSString();
			if (b != -26) {
				clanChatSize = 90;
			}
			class16.length = class23_sub5.getSmart0();
			if ((class16.length ^ 0xffffffff) < (i ^ 0xffffffff)) {
				class16.length = i;
			}
			class16.bytes = new byte[class16.length];
			class23_sub5.index += GroundDecoration.aHuffman_1206.method1575(class16.length, class16.bytes, 0, class23_sub5.index, 41, class23_sub5.byteBuffer);
			return class16;
		} catch (Exception exception) {
			return ComponentCanvas.aClass16_49;
		}
	}
	
	public static int anInt985;

	static {
		anInt985 = 0;
		aClass16_976 = RSString.createString("Fehler beim Laden Ihres Spielcharakters)3");
	}
}
