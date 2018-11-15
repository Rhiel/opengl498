package com.jagex;

import java.io.IOException;
import java.math.BigInteger;

import com.jagex.js5.CRC32;

public class Packet extends Linkable {
	public byte[] byteBuffer;
	static RSString aClass16_2169 = RSString.createString(" )2> <col=ffffff>");
	public int index;

	public static final RSString bufferToString(byte[] bs, int i, int length, int offset) {
		RSString rsString = new RSString();
		rsString.bytes = new byte[length];
		rsString.length = i;
		for (int index = offset; index < length + offset; index++) {
			if (bs[index] != 0) {
				rsString.bytes[rsString.length++] = bs[index];
			}
		}
		return rsString;
	}

	public static final int method321(Packet class23_sub5, RSString class16, int i) {
		int i_19_ = class23_sub5.index;
		class23_sub5.putSmart(class16.length);
		class23_sub5.index += GroundDecoration.aHuffman_1206.method1579(i ^ ~0x4ee1c480, i, class16.bytes, class16.length, class23_sub5.index, class23_sub5.byteBuffer);
		return class23_sub5.index + -i_19_;
	}

	public final RSString gstr() {
		int startPos = index;
		while (byteBuffer[index++] != 0) {
			/* empty */
		}
		return bufferToString(byteBuffer, 0, index - (startPos + 1), startPos);
	}

	public final RSString gjstr() {
		int start = index;
		while (byteBuffer[index++] != 0) {
			/* empty */
		}
		int length = index - start - 1;
		if (length == 0) {
			return RSString.create("");
		}
		return RSString.create(decode_jstr(byteBuffer, start, length));
	}

	public final RSString gjstr2() {
		byte ver = byteBuffer[index++];
		if (ver != 0) {
			throw new IllegalStateException("Bad version number in gjstr2");
		}
		int start = index;
		while (byteBuffer[index++] != 0) {
			/* empty */
		}
		int length = index - start;
		if (length == 0) {
			return RSString.create("");
		}
		return RSString.create(decode_jstr(byteBuffer, start, length));
	}

	public static String decode_jstr(byte[] data, int offset, int length) {
		char[] str = new char[length];
		int strlen = 0;
		for (int pos = 0; pos < length; pos++) {
			int val = data[offset + pos] & 0xff;
			if (val != 0) {
				if (val >= 128 && val < 160) {
					int chr = unicode_unescapes[val - 128];
					if (chr == 0) {
						chr = '?';
					}
					val = chr;
				}
				str[strlen++] = (char) val;
			}
		}
		return new String(str, 0, strlen);
	}

	public static char[] unicode_unescapes = { '\u20ac', '\0', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020', '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\0', '\u017d', '\0', '\0', '\u2018', '\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\0', '\u017e', '\u0178' };;

	public final void putLEShortA(int i) {
		byteBuffer[index++] = (byte) (i + 128);
		byteBuffer[index++] = (byte) (i >> 8);
	}

	public final void putByteS(int i) {
		byteBuffer[index++] = (byte) (128 + -i);
	}

	public final void putLEShort(int i) {
		byteBuffer[index++] = (byte) i;
		byteBuffer[index++] = (byte) (i >> 8);
	}

	public final int getSmart0() {
		int i = 0xff & byteBuffer[index];
		if (i >= 128) {
			return -32768 + g2();
		}
		return g1();
	}

	public final byte getByteC0(int i) {
		if (i != -75) {
			return (byte) 74;
		}
		return (byte) -byteBuffer[index++];
	}

	public final byte getByteA0(byte b) {
		if (b <= 2) {
			index = 117;
		}
		return (byte) (-128 + byteBuffer[index++]);
	}

	final int getByteA() {
		return -128 + byteBuffer[index++] & 0xff;
	}

	public final void putLong(long l, byte b) {
		byteBuffer[index++] = (byte) (int) (l >> 56);
		byteBuffer[index++] = (byte) (int) (l >> 48);
		byteBuffer[index++] = (byte) (int) (l >> 40);
		if (b < -107) {
			byteBuffer[index++] = (byte) (int) (l >> 32);
			byteBuffer[index++] = (byte) (int) (l >> 24);
			byteBuffer[index++] = (byte) (int) (l >> 16);
			byteBuffer[index++] = (byte) (int) (l >> 8);
			byteBuffer[index++] = (byte) (int) l;
		}
	}

	public final int method437(boolean bool) {
		int i = byteBuffer[index++];
		int i_7_ = 0;
		for (/**/; (i ^ 0xffffffff) > -1; i = byteBuffer[index++]) {
			i_7_ = (0x7f & i | i_7_) << 7;
		}
		if (bool != false) {
			GameClient.js5_stream = null;
		}
		return i_7_ | i;
	}

	public final void putBytes(int i, int i_8_, byte[] bs, int i_9_) {
		if (i_8_ >= 10) {
			for (int i_10_ = i; (i_10_ ^ 0xffffffff) > (i + i_9_ ^ 0xffffffff); i_10_++) {
				byteBuffer[index++] = bs[i_10_];
			}
		}
	}

	public final void ip4(int i) {
		byteBuffer[index++] = (byte) i;
		byteBuffer[index++] = (byte) (i >> 8);
		byteBuffer[index++] = (byte) (i >> 16);
		byteBuffer[index++] = (byte) (i >> 24);
	}

	public final byte getByteS0(int i) {
		if (i != -18402) {
			LoginHandler.loginConnectionState = -122;
		}
		return (byte) (-byteBuffer[index++] + 128);
	}

	public final int getLEInt() {
		index += 4;
		return (byteBuffer[-4 + index] & 0xff) + ((byteBuffer[-2 + index] & 0xff) << 16) + ((0xff & byteBuffer[index + -1]) << 24) - -(0xff00 & byteBuffer[index + -3] << 8);
	}

	public final int getLEShortA0(int i) {
		index += 2;
		if (i < 122) {
			aClass16_2169 = null;
		}
		return (0xff & byteBuffer[-2 + index] - 128) + (0xff00 & byteBuffer[index + -1] << 8);
	}

	public final int getLEShortA(int i) {
		index += i;
		int i_21_ = ((0xff & byteBuffer[index - 1]) << 8) + (0xff & byteBuffer[-2 + index] - 128);
		if (i_21_ > 32767) {
			i_21_ -= 65536;
		}
		return i_21_;
	}

	public final void putShort(int i) {
		byteBuffer[index++] = (byte) (i >> 8);
		byteBuffer[index++] = (byte) i;
	}

	public static final void method444() {
		GameClient.outBuffer.putOpcode(95);
		for (InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get_first(); class23_sub25 != null; class23_sub25 = (InterfaceNode) Class36.anOa565.get_next()) {
			if ((class23_sub25.walkable ^ 0xffffffff) == -1) {
				GameShell.method27(true, class23_sub25, false);
			}
		}
		if (Varbit.aClass64_4007 != null) {
			RSInterfaceList.setDirty(Varbit.aClass64_4007);
			Varbit.aClass64_4007 = null;
		}
	}

	public final void putIntA(int i) {
		byteBuffer[index++] = (byte) (i >> 8);
		byteBuffer[index++] = (byte) i;
		byteBuffer[index++] = (byte) (i >> 24);
		byteBuffer[index++] = (byte) (i >> 16);
	}

	public final void putString(RSString class16, int i) {
		if (i >= -68) {
			aClass16_2169 = null;
		}
		index += class16.method137(0, index, class16.length(), byteBuffer);
		byteBuffer[index++] = (byte) 0;
	}

	public final int getUSmart2() {
		int value = 0;
		int current = getSmart0();
		while ((current ^ 0xffffffff) == -32768) {
			current = getSmart0();
			value += 32767;
		}
		value += current;
		return value;
	}

	public final void method448(int i, int i_13_, long l) {
		if (i <= 2) {
			getTriByte(-117);
		}
		if ((--i_13_ ^ 0xffffffff) > -1 || i_13_ > 7) {
			throw new IllegalArgumentException();
		}
		for (int i_14_ = i_13_ * 8; (i_14_ ^ 0xffffffff) <= -1; i_14_ -= 8) {
			byteBuffer[index++] = (byte) (int) (l >> i_14_);
		}
	}

	public final long method449(int i, int i_15_) {
		if ((--i ^ 0xffffffff) > -1 || i > 7) {
			throw new IllegalArgumentException();
		}
		int i_16_ = i * 8;
		long l = i_15_;
		for (/**/; i_16_ >= 0; i_16_ -= 8) {
			l |= (byteBuffer[index++] & 0xffL) << i_16_;
		}
		return l;
	}

	public final void get(byte[] data, int offset, int length) {
		int idx = offset;
		for (/**/; (idx ^ 0xffffffff) > (length + offset ^ 0xffffffff); idx++) {
			data[idx] = byteBuffer[index++];
		}
	}

	public final void gdata(int[] buffer, int offset, int length) {
		for (int pos = offset; pos < offset + length; pos++) {
			buffer[pos] = byteBuffer[index++];
		}
	}

	public final void gdata(byte[] buffer, int offset, int length) {
		for (int pos = offset; pos < offset + length; pos++) {
			buffer[pos] = byteBuffer[index++];
		}
	}

	public final RSString method451(byte b) {
		if ((byteBuffer[index] ^ 0xffffffff) == -1) {
			index++;
			return null;
		}
		return gstr();
	}

	public final byte getSignedByte() {
		return byteBuffer[index++];
	}

	public final int getLEShort() {
		index += 2;
		return (byteBuffer[-2 + index] & 0xff) + (byteBuffer[index + -1] << 8 & 0xff00);
	}

	public final void putLong1(long l, int i) {
		putIntB((int) (l >> 32));
		putIntB((int) l);
		if (i != 1650435232) {
			aClass16_2169 = null;
		}
	}

	public static void method455(int i) {
		GameClient.js5_stream = null;
		aClass16_2169 = null;
	}

	public final void putTriByte(int i, int i_22_) {
		byteBuffer[index++] = (byte) (i_22_ >> 16);
		byteBuffer[index++] = (byte) (i_22_ >> 8);
		byteBuffer[index++] = (byte) i_22_;
	}

	public final int getByteS(int i) {
		return 0xff & -byteBuffer[index++] + 128;
	}

	public final int gSmart1or2s() {
		int i = byteBuffer[index] & 0xff;
		if (i < 128) {
			return g1() - 64;
		}
		return g2() - 49152;
	}

	public final void p1(int value) {
		byteBuffer[index++] = (byte) value;
	}

	public void pf(float value) {
		p4(jaclib.memory.Stream.floatToRawIntBits(value));
	}

	public void ipf(float value) {
		ip4(jaclib.memory.Stream.floatToRawIntBits(value));
	}

	public final void p4(int i) {
		byteBuffer[index++] = (byte) (i >> 24);
		byteBuffer[index++] = (byte) (i >> 16);
		byteBuffer[index++] = (byte) (i >> 8);
		byteBuffer[index++] = (byte) i;
	}

	public final void put32Bit(long l) {
		byteBuffer[index++] = (byte) (int) (l >> 32);
		byteBuffer[index++] = (byte) (int) (l >> 24);
		byteBuffer[index++] = (byte) (int) (l >> 16);
		byteBuffer[index++] = (byte) (int) (l >> 8);
		byteBuffer[index++] = (byte) (int) l;
	}

	public final int g1() {
		return byteBuffer[index++] & 0xff;
	}

	public int remaining() {
		return byteBuffer.length - index;
	}

	public final void applyRSA(int i, BigInteger biginteger, BigInteger biginteger_23_) {
		int i_24_ = index;
		index = 0;
		byte[] bs = new byte[i_24_];
		get(bs, 0, i_24_);
		BigInteger biginteger_25_ = new BigInteger(bs);
		BigInteger biginteger_26_ = biginteger_25_.modPow(biginteger, biginteger_23_);
		byte[] bs_27_ = biginteger_26_.toByteArray();
		if (i <= 116) {
			index = 14;
		}
		index = 0;
		p1(bs_27_.length);
		putBytes(0, 85, bs_27_, bs_27_.length);
	}

	public final void putShortA(int i_28_) {
		byteBuffer[index++] = (byte) (i_28_ >> 8);
		byteBuffer[index++] = (byte) (128 + i_28_);
	}

	public final void getBytesS(int i, byte[] bs, int i_31_) {
		for (int i_32_ = i; i_32_ < i_31_ + i; i_32_++) {
			bs[i_32_] = (byte) (byteBuffer[index++] - 128);
		}
	}

	public final int getIntB() {
		index += 4;
		return (0xff & byteBuffer[-2 + index]) + (0xff0000 & byteBuffer[-4 + index] << 16) + ((0xff & byteBuffer[-3 + index]) << 24) + (0xff00 & byteBuffer[-1 + index] << 8);
	}

	public final void method466(int i) {
		byteBuffer[-4 + -i + index] = (byte) (i >> 24);
		byteBuffer[index + -i + -3] = (byte) (i >> 16);
		byteBuffer[index - i + -2] = (byte) (i >> 8);
		byteBuffer[-1 + -i + index] = (byte) i;
	}

	public final void putSmart(int i) {
		if (i >= 0 && i < 128) {
			p1(i);
		} else if (i >= 0 && i < 32768) {
			putShort(i + 32768);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public final long getLong() {
		long l = 0xffffffffL & g4();
		long l_35_ = g4() & 0xffffffffL;
		return l_35_ + (l << 32);
	}

	public final void decryptXtea(int i, int i_36_, int[] is) {
		int i_37_ = index;
		int i_38_ = (i_36_ - i) / 8;
		index = i;
		for (int i_39_ = 0; (i_39_ ^ 0xffffffff) > (i_38_ ^ 0xffffffff); i_39_++) {
			int i_40_ = -957401312;
			int i_41_ = -1640531527;
			int i_42_ = g4();
			int i_43_ = g4();
			int i_44_ = 32;
			while ((i_44_-- ^ 0xffffffff) < -1) {
				i_43_ -= is[(i_40_ & 0x19cf) >>> 11] + i_40_ ^ (i_42_ << 4 ^ i_42_ >>> 5) - -i_42_;
				i_40_ -= i_41_;
				i_42_ -= is[0x3 & i_40_] + i_40_ ^ i_43_ + (i_43_ << 4 ^ i_43_ >>> 5);
			}
			index -= 8;
			p4(i_42_);
			p4(i_43_);
		}
		index = i_37_;
	}

	/**
	 * Writes the random.dat data.
	 * 
	 * @param buffer
	 *               The buffer to write the data on.
	 */
	public static final void writeRandomData(Packet buffer) {
		byte[] bs = new byte[24];
		if (GameClient.random_file != null) {
			try {
				GameClient.random_file.seek(0L, -11320);
				GameClient.random_file.method950(bs, 105);
				int i;
				for (i = 0; i < 24; i++) {
					if (bs[i] != 0) {
						break;
					}
				}
				if (i >= 24) {
					throw new IOException();
				}
			} catch (Exception exception) {
				for (int i = 0; i < 24; i++) {
					bs[i] = (byte) -1;
				}
			}
		}
		buffer.putBytes(0, 101, bs, 24);
	}

	public final int getTriByte(int i) {
		if (i < 50) {
			getByteS0(61);
		}
		index += 3;
		return (byteBuffer[index - 2] << 8 & 0xff00) + ((0xff & byteBuffer[index - 3]) << 16) + (0xff & byteBuffer[index - 1]);
	}

	public final int getTriByte() {
		index += 3;
		return (0xFF & byteBuffer[index - 3] << 16) + (0xFF & byteBuffer[index - 2] << 8) + (0xFF & byteBuffer[index - 1]);
	}

	public final int g4() {
		index += 4;
		return (~0xffffff & byteBuffer[index - 4] << 24) + (byteBuffer[index - 3] << 16 & 0xff0000) + ((byteBuffer[index - 2] & 0xff) << 8) + (0xff & byteBuffer[index - 1]);
	}

	public final int getByteC(int i) {
		if (i >= -98) {
			Camera.cameraViewChanged = false;
		}
		return 0xff & -byteBuffer[index++];
	}

	public final int g2s() {
		index += 2;
		int i = ((byteBuffer[index - 2] & 0xff) << 8) + (byteBuffer[index - 1] & 0xff);
		if ((i ^ 0xffffffff) < -32768) {
			i -= 65536;
		}
		return i;
	}

	public final int putTriB(boolean bool) {
		if (bool != true) {
			GameClient.js5_stream = null;
		}
		index += 3;
		return (byteBuffer[index - 2] & 0xff) + ((0xff & byteBuffer[index - 3]) << 16) + (0xff00 & byteBuffer[index - 1] << 8);
	}

	public final int g2() {
		index += 2;
		return (0xff & byteBuffer[index - 1]) + (byteBuffer[index - 2] << 8 & 0xff00);
	}

	public String readString() {
		StringBuilder s = new StringBuilder();
		int b;
		while ((b = g1s()) != 0) {
			s.append((char) b);
		}
		return s.toString();
	}

	public int gSmart1or2n() {
		int i_29_ = byteBuffer[index] & 0xff;
		if (i_29_ < 128) {
			return g1() - 1;
		}
		return g2() - 32769;
	}

	public int g3() {
		return (readUnsignedByte() << 16) + (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readBigSmart() {
		if (byteBuffer[index] >= 0) {
			int value = readUnsignedShort();
			if (value == 32767) {
				return -1;
			}
			return value;
		}
		return readInt() & 0x7fffffff;
	}

	public int readInt() {
		return (readUnsignedByte() << 24) + (readUnsignedByte() << 16) + (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readUnsignedShort() {
		return (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readUnsignedByte() {
		return g1s() & 0xff;
	}

	public byte g1s() {
		return byteBuffer[index++];
	}

	public int getOffset() {
		return index;
	}

	public final void putIndex(byte b, int i) {
		if (b != 21) {
			aClass16_2169 = null;
		}
		byteBuffer[-i + index - 1] = (byte) i;
	}

	public final int addcrc(int offset) {
		int crc = CRC32.calculate(byteBuffer, offset, index);
		p4(crc);
		return crc;
	}

	public final void method478(int i, int start, int i_47_, byte[] bs) {
		for (int i_48_ = i + start + i_47_; start <= i_48_; i_48_--) {
			bs[i_48_] = (byte) (-128 + byteBuffer[index++]);
		}
	}

	public final void method479(int i, int i_49_) {
		if ((i & ~0x7f ^ 0xffffffff) != -1) {
			if ((i & ~0x3fff ^ 0xffffffff) != -1) {
				if ((~0x1fffff & i ^ 0xffffffff) != -1) {
					if ((i & ~0xfffffff) != 0) {
						p1(i >>> 28 | 0x80);
					}
					p1(0x80 | i >>> 21);
				}
				p1((0x201f5b | i) >>> 14);
			}
			p1(0x80 | i >>> 7);
		}
		if (i_49_ != 128) {
			getIntA(78);
		}
		p1(i & 0x7f);
	}

	public final int getShortA() {
		index += 2;
		return (byteBuffer[index - 2] << 8 & 0xff00) + (-128 + byteBuffer[index - 1] & 0xff);
	}

	public final void putIntB(int i) {
		byteBuffer[index++] = (byte) (i >> 16);
		byteBuffer[index++] = (byte) (i >> 24);
		byteBuffer[index++] = (byte) i;
		byteBuffer[index++] = (byte) (i >> 8);
	}

	public final void putByteA(int i) {
		byteBuffer[index++] = (byte) (128 + i);
	}

	public Packet(int length) {
		byteBuffer = Class91.method1449((byte) -120, length);
		index = 0;
	}

	public Packet(byte[] bs) {
		index = 0;
		byteBuffer = bs;
	}

	public final int getIntA(int i) {
		if (i < 23) {
			byteBuffer = null;
		}
		index += 4;
		return (byteBuffer[index - 3] & 0xff) + ((byteBuffer[index - 4] & 0xff) << 8) + ((byteBuffer[index - 2] & 0xff) << 24) + (byteBuffer[index - 1] << 16 & 0xff0000);
	}

	static {
		Camera.cameraViewChanged = false;
	}

	public int gSmart2or4s() {
		if (byteBuffer[index] < 0) {
			return g4() & 0x7fffffff;
		}
		int i_32_ = g2();
		if (32767 == i_32_) {
			return -1;
		}
		return i_32_;
	}
}
