package com.jagex;

import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

public class RSString {
	public int length;
	public boolean immutable = true;
	public byte[] bytes;
	public int hashCode;
	public static RSString aClass16_1946 = createString("(U3");
	public static Js5 sprites_js5;
	public static RSString aClass16_1948 = createString("Login");
	public static RSString aClass16_1949 = aClass16_1948;
	public static int anInt1950;
	public static RSString assistRequest = createString(":assistreq:");
	/* synthetic */ @SuppressWarnings("rawtypes")
	public static Class aClass1953;

	public static RSString joinRsStrings(RSString[] class16s) {
		if (class16s.length < 2) {
			throw new IllegalArgumentException();
		}
		return joinRsStrings(class16s, -32768, 0, class16s.length);
	}

	public static RSString valueOf(int combatLevel) {
		return createRSString(false, 10, combatLevel);
	}

	public static RSString method292(int i, boolean bool) {
		return RSString.createRSString(bool, 10, i);
	}

	public static RSString createRSString(boolean bool, int i, int combatLevel) {
		if (i < 2 || i > 36) {
			throw new IllegalArgumentException("Invalid radix:" + i);
		}
		int i_11_ = combatLevel / i;
		int i_12_ = 1;
		for (/**/; i_11_ != 0; i_11_ /= i) {
			i_12_++;
		}
		int i_13_ = i_12_;
		if (combatLevel < 0 || bool) {
			i_13_++;
		}
		byte[] bs = new byte[i_13_];
		if (combatLevel >= 0) {
			if (bool) {
				bs[0] = (byte) 43;
			}
		} else {
			bs[0] = (byte) 45;
		}
		for (int i_14_ = 0; i_14_ < i_12_; i_14_++) {
			int i_15_ = combatLevel % i;
			if (i_15_ < 0) {
				i_15_ = -i_15_;
			}
			if (i_15_ > 9) {
				i_15_ += 39;
			}
			combatLevel /= i;
			bs[-1 + i_13_ + -i_14_] = (byte) (48 + i_15_);
		}
		RSString class16 = new RSString();
		class16.length = i_13_;
		class16.bytes = bs;
		return class16;
	}

	public static RSString joinRsStrings(RSString[] class16s, int i, int i_16_, int i_17_) {
		int i_18_ = 0;
		for (int i_19_ = 0; i_19_ < i_17_; i_19_++) {
			if (class16s[i_19_ + i_16_] == null) {
				class16s[i_19_ + i_16_] = CacheFileWorker.aClass16_2869;
			}
			i_18_ += class16s[i_19_ + i_16_].length;
		}
		byte[] bs = new byte[i_18_];
		int i_20_ = 0;
		for (int i_21_ = 0; (i_17_ ^ 0xffffffff) < (i_21_ ^ 0xffffffff); i_21_++) {
			RSString class16 = class16s[i_21_ + i_16_];
			ArrayUtils.copy(class16.bytes, 0, bs, i_20_, class16.length);
			i_20_ += class16.length;
		}
		RSString class16 = new RSString();
		class16.length = i_18_;
		class16.bytes = bs;
		return class16;
	}

	public int method137(int i, int i_0_, int i_1_, byte[] bs) {
		ArrayUtils.copy(bytes, i, bs, i_0_, -i + i_1_);
		return i_1_ - i;
	}

	public RSString substring(int i) {
		return substring(length, i);
	}

	public static void method139(byte b) {
		RSInterfaceList.setDirty(StaticMethods.fromInterface);
		AbstractTimer.anInt304++;
		if (!StaticMethods.aBoolean3516 || !CollisionMap.aBoolean1310) {
			if (AbstractTimer.anInt304 > 1) {
				StaticMethods.fromInterface = null;
			}
		} else {
			int i = Mouse.mouseY;
			i -= QuickChatDefinition.anInt4034;
			if (i < RSInterface.anInt1082) {
				i = RSInterface.anInt1082;
			}
			int i_3_ = Mouse.mouseX;
			if ((StaticMethods.fromInterface.layout_height + i ^ 0xffffffff) < (RSInterface.anInt1082 + RSInterface.aClass64_1102.layout_height ^ 0xffffffff)) {
				i = -StaticMethods.fromInterface.layout_height + RSInterface.anInt1082 + RSInterface.aClass64_1102.layout_height;
			}
			int i_4_ = i + -StaticMethods2.anInt1712;
			i_3_ -= StaticMethods.anInt3059;
			int i_5_ = StaticMethods.fromInterface.dragDeadZone;
			if ((i_3_ ^ 0xffffffff) > (Class48.anInt751 ^ 0xffffffff)) {
				i_3_ = Class48.anInt751;
			}
			if (i_3_ + StaticMethods.fromInterface.layout_width > Class48.anInt751 - -RSInterface.aClass64_1102.layout_width) {
				i_3_ = -StaticMethods.fromInterface.layout_width + RSInterface.aClass64_1102.layout_width + Class48.anInt751;
			}
			int i_6_ = i_3_ - StaticMethods.anInt3027;
			if (StaticMethods.fromInterface.dragDeadTime < AbstractTimer.anInt304 && (i_5_ < i_6_ || i_6_ < -i_5_ || i_5_ < i_4_ || i_4_ < -i_5_)) {
				GameTimer.aBoolean2003 = true;
			}
			int i_7_ = RSInterface.aClass64_1102.scroll_x + -Class48.anInt751 + i_3_;
			int i_8_ = -RSInterface.anInt1082 + i - -RSInterface.aClass64_1102.scroll_y;
			if (StaticMethods.fromInterface.anObjectArray1053 != null && GameTimer.aBoolean2003) {
				CS2Event class23_sub9 = new CS2Event();
				class23_sub9.component = StaticMethods.fromInterface;
				class23_sub9.scriptArguments = StaticMethods.fromInterface.anObjectArray1053;
				class23_sub9.mouseY = i_8_;
				class23_sub9.mouseX = i_7_;
				Class91.parseCS2Script(class23_sub9, (byte) -113);
			}
			if ((SongUpdater.anInt175 ^ 0xffffffff) == -1) {
				if (!GameTimer.aBoolean2003) {
					if ((Class95.anInt1612 == 1 || Class87_Sub4.method1425((byte) 126, -1 + ContextMenu.menuActionRow)) && ContextMenu.menuActionRow > 2) {
						ContextMenu.determineMenuSize();
					} else if ((ContextMenu.menuActionRow ^ 0xffffffff) < -1) {
						GameClient.doAction(ContextMenu.menuActionRow + -1);
					}
				} else {
					if (StaticMethods.fromInterface.anObjectArray1066 != null) {
						CS2Event class23_sub9 = new CS2Event();
						class23_sub9.aClass64_2255 = StaticMethods.withInterface;
						class23_sub9.scriptArguments = StaticMethods.fromInterface.anObjectArray1066;
						class23_sub9.component = StaticMethods.fromInterface;
						class23_sub9.mouseY = i_8_;
						class23_sub9.mouseX = i_7_;
						Class91.parseCS2Script(class23_sub9, (byte) -104);
					}
					RSInterface withInter = StaticMethods.withInterface;
					if (withInter == null) {
						withInter = StaticMethods.fromInterface;
					}
					if (withInter != null) {
						GameClient.outBuffer.putOpcode(234);
						GameClient.outBuffer.putIntB(StaticMethods.fromInterface.uid);
						GameClient.outBuffer.putLEShort(withInter.componentIndex);
						GameClient.outBuffer.p4(withInter.uid);
						GameClient.outBuffer.putLEShort(StaticMethods.fromInterface.componentIndex);
					}
				}
				StaticMethods.fromInterface = null;
			}
		}
	}

	public boolean equals(RSString string) {
		if (string == null) {
			return false;
		}
		if ((length ^ 0xffffffff) != (string.length ^ 0xffffffff)) {
			return false;
		}
		if (!immutable || !string.immutable) {
			if (hashCode == 0) {
				hashCode = getHashCode();
				if (hashCode == 0) {
					hashCode = 1;
				}
			}
			if (string.hashCode == 0) {
				string.hashCode = string.getHashCode();
				if (string.hashCode == 0) {
					string.hashCode = 1;
				}
			}
			if (string.hashCode != hashCode) {
				return false;
			}
		}
		for (int i = 0; i < length; i++) {
			if (string.bytes[i] != bytes[i]) {
				return false;
			}
		}
		return true;
	}

	public boolean equalsIgnoreCase(RSString class16_9_) {
		if (class16_9_ == null) {
			return false;
		}
		if (class16_9_.length != length) {
			return false;
		}
		for (int i_10_ = 0; (i_10_ ^ 0xffffffff) > (length ^ 0xffffffff); i_10_++) {
			byte b = class16_9_.bytes[i_10_];
			if (b >= 65 && b <= 90 || b >= -64 && b <= -34 && b != -41) {
				b += 32;
			}
			byte b_11_ = bytes[i_10_];
			if (b_11_ >= 65 && b_11_ <= 90 || b_11_ >= -64 && b_11_ <= -34 && b_11_ != -41) {
				b_11_ += 32;
			}
			if ((b_11_ ^ 0xffffffff) != (b ^ 0xffffffff)) {
				return false;
			}
		}
		return true;
	}

	public int method141(FontMetrics fontmetrics, int i) {
		if (i != -32) {
			aClass16_1949 = null;
		}
		String string;
		try {
			string = new String(bytes, 0, length, "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException unsupportedencodingexception) {
			string = new String(bytes, 0, length);
		}
		return fontmetrics.stringWidth(string);
	}

	public int toInteger() {
		return parseInt(10);
	}

	public void method143(int i, Applet applet) throws Throwable {
		String string = new String(bytes, i, length);
		Class80.method1364(string, -29116, applet);
	}

	public static int method144(int y, int x, int i_14_) {
		if ((com.jagex.MapLoader.settings[i_14_][x][y] & 0x8) != 0) {
			return 0;
		}
		if (i_14_ > 0 && (0x2 & com.jagex.MapLoader.settings[1][x][y]) != 0) {
			return i_14_ + -1;
		}
		return i_14_;
	}

	public RSString method145() {
		RSString class16_15_ = WallObject.getStringFromLong(-1, toUsernameLong());
		if (class16_15_ != null) {
			return class16_15_;
		}
		return Class98.aClass16_1652;
	}

	public int getHashCode() {
		int i_16_ = 0;
		for (int i_17_ = 0; i_17_ < length; i_17_++) {
			i_16_ = (bytes[i_17_] & 0xff) + -i_16_ + (i_16_ << 5);
		}
		return i_16_;
	}

	public byte[] getBytes() {
		byte[] bs = new byte[length];
		ArrayUtils.copy(bytes, 0, bs, 0, length);
		return bs;
	}

	public RSString substring(int endIndex, int offset) {
		RSString string = new RSString();
		string.length = endIndex - offset;
		string.bytes = new byte[endIndex - offset];
		ArrayUtils.copy(bytes, offset, string.bytes, 0, string.length);
		return string;
	}

	public static RSString create(String s) {
		RSString string = new RSString();
		string.length = s.length();
		string.bytes = s.getBytes();
		return string;
	}

	public RSString method149() {
		RSString class16_20_ = new RSString();
		class16_20_.length = length;
		class16_20_.bytes = new byte[length];
		int i_21_ = 2;
		for (int i_22_ = 0; (length ^ 0xffffffff) < (i_22_ ^ 0xffffffff); i_22_++) {
			byte b = bytes[i_22_];
			if (b >= 97 && b <= 122 || b >= -32 && b <= -2 && b != -9) {
				if (i_21_ == 2) {
					b -= 32;
				}
				i_21_ = 0;
			} else if (b >= 65 && b <= 90 || b >= -64 && b <= -34 && b != -41) {
				if (i_21_ == 0) {
					b += 32;
				}
				i_21_ = 0;
			} else if (b != 46 && b != 33 && b != 63) {
				if (b == 32) {
					if (i_21_ != 2) {
						i_21_ = 1;
					}
				} else {
					i_21_ = 1;
				}
			} else {
				i_21_ = 2;
			}
			class16_20_.bytes[i_22_] = b;
		}
		return class16_20_;
	}

	public boolean method150(RSString class16_23_) {
		if (class16_23_.length > length) {
			return false;
		}
		for (int i_24_ = 0; class16_23_.length > i_24_; i_24_++) {
			byte b = class16_23_.bytes[i_24_];
			byte b_25_ = bytes[i_24_];
			if (b >= 65 && b <= 90 || b >= -64 && b <= -34 && b != -41) {
				b += 32;
			}
			if (b_25_ >= 65 && b_25_ <= 90 || b_25_ >= -64 && b_25_ <= -34 && b_25_ != -41) {
				b_25_ += 32;
			}
			if (b_25_ != b) {
				return false;
			}
		}
		return true;
	}

	public int method151(RSString playerName) {
		int size;
		if (length <= playerName.length) {
			size = length;
		} else {
			size = playerName.length;
		}
		for (int index = 0; index < size; index++) {
			if ((bytes[index] & 0xff) < (playerName.bytes[index] & 0xff)) {
				return -1;
			}
			if ((playerName.bytes[index] & 0xff) < (bytes[index] & 0xff)) {
				return 1;
			}
		}
		if (playerName.length > length) {
			return -1;
		}
		if (length > playerName.length) {
			return 1;
		}
		return 0;
	}

	public RSString toLowerCase() {
		RSString string = new RSString();
		string.length = length;
		string.bytes = new byte[length];
		for (int i_29_ = 0; i_29_ < length; i_29_++) {
			byte charValue = bytes[i_29_];
			if (charValue >= 65 && charValue <= 90 || charValue >= -64 && charValue <= -34 && charValue != -41) {
				charValue += 32;
			}
			string.bytes[i_29_] = charValue;
		}
		return string;
	}

	public URL method153() throws MalformedURLException {
		return new URL(new String(bytes, 0, length));
	}

	public RSString method154() {
		RSString class16_30_ = new RSString();
		class16_30_.length = length;
		boolean bool = true;
		class16_30_.bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			byte b_31_ = bytes[i];
			if (b_31_ == 95) {
				class16_30_.bytes[i] = (byte) 32;
				bool = true;
			} else if (b_31_ >= 97 && b_31_ <= 122 && bool) {
				class16_30_.bytes[i] = (byte) (-32 + b_31_);
				bool = false;
			} else {
				bool = false;
				class16_30_.bytes[i] = b_31_;
			}
		}
		return class16_30_;
	}

	public RSString method155() {
		RSString class16_32_ = new RSString();
		class16_32_.length = length;
		class16_32_.bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			class16_32_.bytes[i] = (byte) 42;
		}
		return class16_32_;
	}

	public RSString append(int i) {
		if (i <= 0 || i > 255) {
			throw new IllegalArgumentException("invalid char:" + i);
		}
		if (!immutable) {
			throw new IllegalArgumentException();
		}
		hashCode = 0;
		if (bytes.length == length) {
			int i_34_;
			for (i_34_ = 1; i_34_ <= length; i_34_ += i_34_) {
				/* empty */
			}
			byte[] bs = new byte[i_34_];
			ArrayUtils.copy(bytes, 0, bs, 0, length);
			bytes = bs;
		}
		bytes[length++] = (byte) i;
		return this;
	}

	public void method1553(int var1, boolean var2) {
		if (!immutable) {
			throw new IllegalArgumentException();
		} else if (-1 < ~var1) {
			throw new IllegalArgumentException();
		} else {
			int var3;
			if (~var1 < ~bytes.length) {
				for (var3 = 1; ~var3 > ~var1; var3 += var3) {
				}

				byte[] var4 = new byte[var3];
				ArrayUtils.copy(bytes, 0, var4, 0, length);
				bytes = var4;
			}

			for (var3 = length; ~var3 > ~var1; ++var3) {
				bytes[var3] = 32;
			}
			length = var1;
		}
	}

	public RSString append(RSString class16_35_) {
		if (!immutable) {
			throw new IllegalArgumentException();
		}
		hashCode = 0;
		if (bytes.length < length - -class16_35_.length) {
			int i;
			for (i = 1; length + class16_35_.length > i; i += i) {
				/* empty */
			}
			byte[] bs = new byte[i];
			ArrayUtils.copy(bytes, 0, bs, 0, length);
			bytes = bs;
		}
		ArrayUtils.copy(class16_35_.bytes, 0, bytes, length, class16_35_.length);
		length += class16_35_.length;
		return this;
	}

	public void method158() {
		String string;
		try {
			string = new String(bytes, 0, length, "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException unsupportedencodingexception) {
			string = new String(bytes, 0, length);
		}
		System.out.println(string);
	}

	public int parseInt(int i) {
		if (i < 1 || i > 36) {
			i = 10;
		}
		boolean bool = false;
		boolean bool_36_ = false;
		int i_37_ = 0;
		for (int i_38_ = 0; i_38_ < length; i_38_++) {
			int i_39_ = 0xff & bytes[i_38_];
			if (i_38_ == 0) {
				if (i_39_ == 45) {
					bool = true;
					continue;
				}
				if (i_39_ == 43) {
					continue;
				}
			}
			if (i_39_ < 48 || i_39_ > 57) {
				if (i_39_ >= 65 && i_39_ <= 90) {
					i_39_ -= 55;
				} else if (i_39_ >= 97 && i_39_ <= 122) {
					i_39_ -= 87;
				} else {
					throw new NumberFormatException();
				}
			} else {
				i_39_ -= 48;
			}
			if (i <= i_39_) {
				throw new NumberFormatException();
			}
			if (bool) {
				i_39_ = -i_39_;
			}
			int i_40_ = i_39_ + i_37_ * i;
			if (i_37_ != i_40_ / i) {
				throw new NumberFormatException();
			}
			i_37_ = i_40_;
			bool_36_ = true;
		}
		if (!bool_36_) {
			throw new NumberFormatException();
		}
		return i_37_;
	}

	public static RuntimeException_Sub1 method160(Throwable throwable, String string) {
		RuntimeException_Sub1 runtimeexception_sub1;
		if (throwable instanceof RuntimeException_Sub1) {
			runtimeexception_sub1 = (RuntimeException_Sub1) throwable;
			runtimeexception_sub1.aString1850 += ' ' + string;
		} else {
			runtimeexception_sub1 = new RuntimeException_Sub1(throwable, string);
		}
		return runtimeexception_sub1;
	}

	public int indexOf(int i_41_, RSString string) {
		int[] is = new int[string.length];
		int[] is_43_ = new int[256];
		int[] is_44_ = new int[string.length];
		for (int i_45_ = 0; is_43_.length > i_45_; i_45_++) {
			is_43_[i_45_] = string.length;
		}
		for (int i_46_ = 1; (string.length ^ 0xffffffff) <= (i_46_ ^ 0xffffffff); i_46_++) {
			is[i_46_ + -1] = -i_46_ + (string.length << 1);
			is_43_[MathUtils.bitAnd(255, string.bytes[-1 + i_46_])] = string.length + -i_46_;
		}
		int i_47_ = string.length + 1;
		int i_48_ = string.length;
		while ((i_48_ ^ 0xffffffff) < -1) {
			is_44_[-1 + i_48_] = i_47_;
			for (/**/; i_47_ <= string.length && string.bytes[-1 + i_47_] != string.bytes[i_48_ - 1]; i_47_ = is_44_[-1 + i_47_]) {
				if ((-i_48_ + string.length ^ 0xffffffff) >= (is[i_47_ + -1] ^ 0xffffffff)) {
					is[-1 + i_47_] = string.length - i_48_;
				}
			}
			i_48_--;
			i_47_--;
		}
		int i_49_ = 1;
		int i_50_ = i_47_;
		i_47_ = -i_50_ + string.length + 1;
		int i_51_ = 0;
		for (int i_52_ = 1; i_52_ <= i_47_; i_52_++) {
			is_44_[i_52_ + -1] = i_51_;
			for (/**/; i_51_ >= 1 && (string.bytes[-1 + i_52_] ^ 0xffffffff) != (string.bytes[i_51_ - 1] ^ 0xffffffff); i_51_ = is_44_[i_51_ + -1]) {
				/* empty */
			}
			i_51_++;
		}
		while (i_50_ < string.length) {
			for (int i_53_ = i_49_; (i_53_ ^ 0xffffffff) >= (i_50_ ^ 0xffffffff); i_53_++) {
				if ((-i_53_ + string.length - -i_50_ ^ 0xffffffff) >= (is[-1 + i_53_] ^ 0xffffffff)) {
					is[i_53_ + -1] = -i_53_ + i_50_ + string.length;
				}
			}
			i_49_ = i_50_ - -1;
			i_50_ = i_47_ + i_50_ + -is_44_[i_47_ - 1];
			i_47_ = is_44_[-1 + i_47_];
		}
		int i_54_;
		for (int i_55_ = -1 + string.length + i_41_; i_55_ < length; i_55_ += Math.max(is_43_[bytes[i_55_] & 0xff], is[i_54_])) {
			for (i_54_ = -1 + string.length; (i_54_ ^ 0xffffffff) <= -1 && string.bytes[i_54_] == bytes[i_55_]; i_54_--) {
				i_55_--;
			}
			if (i_54_ == -1) {
				return 1 + i_55_;
			}
		}
		return -1;
	}

	public boolean method162(RSString class16_56_, int i) {
		if (length < class16_56_.length) {
			return false;
		}
		int i_57_ = -class16_56_.length + length;
		for (int i_58_ = i; i_58_ < class16_56_.length; i_58_++) {
			if (class16_56_.bytes[i_58_] != bytes[i_57_ + i_58_]) {
				return false;
			}
		}
		return true;
	}

	public boolean method163(int i) {
		if (i < 1 || i > 36) {
			i = 10;
		}
		boolean bool = false;
		boolean bool_61_ = false;
		int i_62_ = 0;
		for (int i_63_ = 0; i_63_ < length; i_63_++) {
			int i_64_ = 0xff & bytes[i_63_];
			if (i_63_ == 0) {
				if (i_64_ == 45) {
					bool_61_ = true;
					continue;
				}
				if (i_64_ == 43) {
					continue;
				}
			}
			if (i_64_ >= 48 && i_64_ <= 57) {
				i_64_ -= 48;
			} else if (i_64_ < 65 || i_64_ > 90) {
				if (i_64_ >= 97 && i_64_ <= 122) {
					i_64_ -= 87;
				} else {
					return false;
				}
			} else {
				i_64_ -= 55;
			}
			if (i <= i_64_) {
				return false;
			}
			if (bool_61_) {
				i_64_ = -i_64_;
			}
			int i_65_ = i_64_ + i * i_62_;
			if (i_65_ / i != i_62_) {
				return false;
			}
			bool = true;
			i_62_ = i_65_;
		}
		return bool;
	}

	public void drawString(int i, Graphics graphics, int i_67_) {
		String string;
		try {
			string = new String(bytes, 0, length, "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException unsupportedencodingexception) {
			string = new String(bytes, 0, length);
		}
		graphics.drawString(string, i_67_, i);
	}

	public RSString method165(int i_68_) {
		if (i_68_ <= 0 || i_68_ > 255) {
			throw new IllegalArgumentException("invalid char");
		}
		RSString class16_69_ = new RSString();
		class16_69_.bytes = new byte[1 + length];
		class16_69_.length = length - -1;
		ArrayUtils.copy(bytes, 0, class16_69_.bytes, 0, length);
		class16_69_.bytes[length] = (byte) i_68_;
		return class16_69_;
	}

	public int charAt(int i) {
		return 0xff & bytes[i];
	}

	public RSString() {
		/* empty */
	}

	public int method167(RSString string) {
		int i_72_;
		if (string.length < length) {
			i_72_ = string.length;
		} else {
			i_72_ = length;
		}
		for (int i_73_ = 0; i_73_ < i_72_; i_73_++) {
			if (SomeSoundClass.anIntArray3634[bytes[i_73_] & 0xff] < SomeSoundClass.anIntArray3634[0xff & string.bytes[i_73_]]) {
				return -1;
			}
			if (SomeSoundClass.anIntArray3634[string.bytes[i_73_] & 0xff] < SomeSoundClass.anIntArray3634[bytes[i_73_] & 0xff]) {
				return 1;
			}
		}
		if (string.length > length) {
			return -1;
		}
		if (string.length < length) {
			return 1;
		}
		return 0;
	}

	public RSString[] method168(int i) {
		int i_74_ = 0;
		int i_75_ = 0;
		for (/**/; length > i_75_; i_75_++) {
			if (bytes[i_75_] == i) {
				i_74_++;
			}
		}
		RSString[] class16s = new RSString[i_74_ + 1];
		if (i_74_ == 0) {
			class16s[0] = this;
			return class16s;
		}
		int i_76_ = 0;
		int i_77_ = 0;
		for (int i_78_ = 0; i_78_ < i_74_; i_78_++) {
			int i_79_;
			for (i_79_ = 0; bytes[i_77_ + i_79_] != i; i_79_++) {
				/* empty */
			}
			class16s[i_76_++] = substring(i_77_ - -i_79_, i_77_);
			i_77_ += i_79_ + 1;
		}
		class16s[i_74_] = substring(length, i_77_);
		return class16s;
	}

	public RSString method169() {
		int i_80_ = 0;
		int i_81_ = length;
		for (/**/; length > i_80_; i_80_++) {
			if ((bytes[i_80_] < 0 || bytes[i_80_] > 32) && (0xff & bytes[i_80_]) != 160) {
				break;
			}
		}
		for (/**/; i_80_ < i_81_ && (bytes[-1 + i_81_] >= 0 && bytes[i_81_ - 1] <= 32 || (0xff & bytes[i_81_ + -1]) == 160); i_81_--) {
			/* empty */
		}
		if (i_80_ == 0 && length == i_81_) {
			return this;
		}
		RSString class16_82_ = new RSString();
		class16_82_.length = i_81_ + -i_80_;
		class16_82_.bytes = new byte[class16_82_.length];
		for (int i_83_ = 0; i_83_ < class16_82_.length; i_83_++) {
			class16_82_.bytes[i_83_] = bytes[i_80_ + i_83_];
		}
		return class16_82_;
	}

	public boolean isInteger() {
		return method163(10);
	}

	public int length() {
		return length;
	}

	public RSString method172(Applet applet) {
		String string = new String(bytes, 0, length);
		String string_84_ = applet.getParameter(string);
		if (string_84_ == null) {
			return null;
		}
		return method235(string_84_);
	}

	public static RSString method235(String string) {
		byte[] bs;
		try {
			bs = string.getBytes("ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException unsupportedencodingexception) {
			bs = string.getBytes();
		}
		RSString class16 = new RSString();
		class16.length = 0;
		class16.bytes = bs;
		for (int i_8_ = 0; i_8_ < bs.length; i_8_++) {
			if (bs[i_8_] != 0) {
				bs[class16.length++] = bs[i_8_];
			}
		}
		return class16;
	}

	public int indexOf(RSString string) {
		return indexOf(0, string);
	}

	public long method174() {
		long l = 0L;
		for (int i_86_ = 0; i_86_ < length; i_86_++) {
			l = (0xff & bytes[i_86_]) + -l + (l << 5);
		}
		return l;
	}

	public static RSString createString(int len) {
		RSString str = new RSString();
		str.length = 0;
		str.bytes = new byte[len];
		return str;
	}

	public static RSString createString(String string) {
		byte[] bs = string.getBytes();
		int i = bs.length;
		int i_1_ = 0;
		RSString rsString = new RSString();
		rsString.bytes = new byte[i];
		while (i_1_ < i) {
			int i_2_ = bs[i_1_++] & 0xff;
			if (i_2_ <= 45 && i_2_ >= 40) {
				if (i <= i_1_) {
					break;
				}
				int i_3_ = bs[i_1_++] & 0xff;
				rsString.bytes[rsString.length++] = (byte) (-1720 + i_2_ * 43 + i_3_ + -48);
			} else if (i_2_ != 0) {
				rsString.bytes[rsString.length++] = (byte) i_2_;
			}
		}
		rsString.method178();
		return rsString.method175();
	}

	public RSString method175() {
		long l = method174();
		synchronized (aClass1953 == null ? aClass1953 = RSString.class : aClass1953) {
			if (Class36.anOa572 == null) {
				Class36.anOa572 = new HashTable(4096);
			} else {
				for (StringNode stringNode = (StringNode) Class36.anOa572.get(l); stringNode != null; stringNode = (StringNode) Class36.anOa572.getLastFetchedNode()) {
					if (equals(stringNode.value)) {
						return stringNode.value;
					}
				}
			}
			StringNode stringNode = new StringNode();
			stringNode.value = this;
			immutable = false;
			Class36.anOa572.put(l, stringNode);
		}
		return this;
	}

	public boolean startsWith(RSString class16_87_) {
		if ((class16_87_.length ^ 0xffffffff) < (length ^ 0xffffffff)) {
			return false;
		}
		for (int i = 0; class16_87_.length > i; i++) {
			if ((bytes[i] ^ 0xffffffff) != (class16_87_.bytes[i] ^ 0xffffffff)) {
				return false;
			}
		}
		return true;
	}

	public RSString method177(int i, int i_88_, int i_89_) {
		byte b = (byte) i_88_;
		byte b_90_ = (byte) i;
		RSString class16_91_ = new RSString();
		class16_91_.length = length;
		class16_91_.bytes = new byte[length];
		for (int i_92_ = i_89_; i_92_ < length; i_92_++) {
			byte b_93_ = bytes[i_92_];
			if (b_93_ == b) {
				class16_91_.bytes[i_92_] = b_90_;
			} else {
				class16_91_.bytes[i_92_] = b_93_;
			}
		}
		return class16_91_;
	}

	@Override
	public int hashCode() {
		return getHashCode();
	}

	public RSString method178() {
		if (!immutable) {
			throw new IllegalArgumentException();
		}
		hashCode = 0;
		if (length != bytes.length) {
			byte[] bs = new byte[length];
			ArrayUtils.copy(bytes, 0, bs, 0, length);
			bytes = bs;
		}
		return this;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RSString) {
			return equals((RSString) object);
		}
		throw new IllegalArgumentException();
	}

	int indexOf(byte b, int i, int i_94_) {
		byte b_95_ = (byte) i_94_;
		for (int i_96_ = i; i_96_ < length; i_96_++) {
			if (bytes[i_96_] == b_95_) {
				return i_96_;
			}
		}
		return -1;
	}

	public static void method180() {
		LocTypeList.result = null;
		aClass16_1946 = null;
		aClass16_1949 = null;
		aClass16_1948 = null;
		assistRequest = null;
		sprites_js5 = null;
	}

	public URL method181(URL url) throws MalformedURLException {
		return new URL(url, new String(bytes, 0, length));
	}

	public static int getIdleMouseTicks() {
		return InteractiveEntity.idleMouseTicks;
	}

	public long toUsernameLong() {
		long l = 0L;
		for (int index = 0; index < length; index++) {
			if (index >= 12) {
				break;
			}
			l *= 37L;
			int byteAtIndex = bytes[index];
			if (byteAtIndex >= 65 && byteAtIndex <= 90) {
				l += -65 + byteAtIndex + 1;
			} else if (byteAtIndex >= 97 && byteAtIndex <= 122) {
				l += -97 + 1 + byteAtIndex;
			} else if (byteAtIndex >= 48 && byteAtIndex <= 57) {
				l += -21 - -byteAtIndex;
			}
		}
		for (/**/; l % 37L == 0L && l != 0L; l /= 37L) {
			/* empty */
		}
		return l;
	}

	public static RSString hashToString(boolean bool, int i, int i_0_, long l) {
		char c = ',';
		char c_2_ = '.';
		if (i == 0) {
			c_2_ = ',';
			c = '.';
		}
		if (i == 2) {
			c_2_ = '\u00a0';
		}
		boolean bool_3_ = false;
		if (l < 0L) {
			bool_3_ = true;
			l = -l;
		}
		StringBuilder stringbuffer = new StringBuilder(26);
		if (i_0_ > 0) {
			for (int i_4_ = 0; i_4_ < i_0_; i_4_++) {
				int i_5_ = (int) l;
				l /= 10L;
				stringbuffer.append((char) (i_5_ + 48 + -(10 * (int) l)));
			}
			stringbuffer.append(c);
		}
		int i_6_ = 0;
		for (;;) {
			int i_7_ = (int) l;
			l /= 10L;
			stringbuffer.append((char) (-(10 * (int) l) + 48 + i_7_));
			if ((l ^ 0xffffffffffffffffL) == -1L) {
				break;
			}
			if (bool && ++i_6_ % 3 == 0) {
				stringbuffer.append(c_2_);
			}
		}
		if (bool_3_) {
			stringbuffer.append('-');
		}
		return RSString.create(stringbuffer.reverse().toString());
	}

	@Override
	public String toString() {
		if (bytes == null) {
			throw new RuntimeException("here7452");
		}
		return new String(bytes);
	}

	public RSString method1542(int var1, RSString var2, int var3, int var4) {
		if (!immutable) {
			throw new IllegalArgumentException();
		} else if (0 <= var3 && var3 <= var4 && ~var4 >= ~var2.length) {
			if (length + var4 - var3 > bytes.length) {
				int var5;
				for (var5 = 1; ~(length + var2.length) < ~var5; var5 += var5) {
				}

				byte[] var6 = new byte[var5];
				ArrayUtils.copy(bytes, 0, var6, 0, length);
				bytes = var6;
			}

			ArrayUtils.copy(var2.bytes, var3, bytes, length, -var3 + var4);
			length += var4 + -var3;
			return this;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public RSString method1563(int var1) {
		return this;
	}

	public static RSString create(int size) {
		return create(size, 0);
	}

	public static RSString create(int size, int len) {
		RSString class16 = new RSString();
		class16.length = len;
		class16.bytes = new byte[size];
		return class16;
	}

	final int indexOf(RSString var1, int start, int var3) {
		int var4 = var1.length;
		if (start >= length) {
			return ~var4 == -1 ? length : -1;
		} else {
			if (~start > -1) {
				start = 0;
			}

			if (var3 == ~var4) {
				return start;
			} else {
				int var7 = length - var4;
				byte[] var5 = var1.bytes;
				byte var6 = var5[0];
				int var8 = start;

				while (~var8 >= ~var7) {
					if (~var6 != ~bytes[var8]) {
						do {
							++var8;
							if (var8 > var7) {
								return -1;
							}
						} while (~var6 != ~bytes[var8]);
					}

					boolean var9 = true;
					int var10 = 1 + var8;
					int var11 = 1;

					while (true) {
						if (var11 < var4) {
							if (~var5[var11] == ~bytes[var10]) {
								++var10;
								++var11;
								continue;
							}

							var9 = false;
						}

						if (var9) {
							return var8;
						}

						++var8;
						break;
					}
				}

				return -1;
			}
		}
	}

	public final RSString replace(RSString first, RSString second) {
		int var4 = length;
		int var5 = first.length - second.length;
		int var6 = 0;

		while (true) {
			int var7 = this.indexOf(second, var6, -1);
			if (0 > var7) {
				var6 = 0;
				RSString newString = createString(var4);

				while (true) {
					int var8 = this.indexOf(second, var6, -1);
					if (0 > var8) {
						while (~var6 > ~length) {
							newString.append(255 & bytes[var6++]);
						}
						return newString;
					}

					while (var6 < var8) {
						newString.append(bytes[var6++] & 255);
					}

					newString.append(first);
					var6 += second.length;
				}
			}

			var6 = var7 - -second.length;
			var4 += var5;
		}
	}
}
