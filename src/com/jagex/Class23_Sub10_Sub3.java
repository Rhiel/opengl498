package com.jagex;
/* Class23_Sub10_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class23_Sub10_Sub3 extends Class23_Sub10
{
	public SomeSoundClass aSomeSoundClass_3646;
	static MemoryCache aJList_3649;
	public NodeDeque aClass89_3653 = new NodeDeque();
	static RSString aClass16_3655 = RSString.createString("oberen Rand der Webseite ausw-=hlen)3");
	public Class23_Sub10_Sub4 aClass23_Sub10_Sub4_3656 = new Class23_Sub10_Sub4();
	static RSString aClass16_3657;
	public static int[] friends_worldid;
	static RSString aClass16_3659;
	public static int anInt3660;
	
	@Override
	final Class23_Sub10 method502() {
		Class23_Sub7 class23_sub7;
		do {
			class23_sub7 = (Class23_Sub7) aClass89_3653.get_next();
			if (class23_sub7 == null) {
				return null;
			}
		} while (class23_sub7.aClass23_Sub10_Sub1_2198 == null);
		return class23_sub7.aClass23_Sub10_Sub1_2198;
	}
	
	@Override
	final Class23_Sub10 method503() {
		Class23_Sub7 class23_sub7 = (Class23_Sub7) aClass89_3653.get_first();
		if (class23_sub7 == null) {
			return null;
		}
		if (class23_sub7.aClass23_Sub10_Sub1_2198 != null) {
			return class23_sub7.aClass23_Sub10_Sub1_2198;
		}
		return method502();
	}
	
	@Override
	final int method501() {
		return 0;
	}
	
	@Override
	final void method507(int i) {
		aClass23_Sub10_Sub4_3656.method507(i);
	while_105_:
		for (Class23_Sub7 class23_sub7 = (Class23_Sub7) aClass89_3653.get_first(); class23_sub7 != null; class23_sub7 = (Class23_Sub7) aClass89_3653.get_next()) {
			if (!aSomeSoundClass_3646.method576(6295, class23_sub7)) {
				int i_0_ = i;
				while ((class23_sub7.anInt2197 ^ 0xffffffff) > (i_0_ ^ 0xffffffff)) {
					method586(class23_sub7, class23_sub7.anInt2197, (byte) -61);
					i_0_ -= class23_sub7.anInt2197;
					if (aSomeSoundClass_3646.method552(i_0_, null, 0, class23_sub7, 66)) {
						continue while_105_;
					}
				}
				method586(class23_sub7, i_0_, (byte) -61);
				class23_sub7.anInt2197 -= i_0_;
			}
		}
	}
	
	public final void method584(int i, int i_1_, int[] is, Class23_Sub7 class23_sub7, int i_2_, int i_3_) {
		if ((aSomeSoundClass_3646.anIntArray3619[class23_sub7.anInt2204] & 0x4 ^ 0xffffffff) != -1 && class23_sub7.anInt2194 < 0) {
			int i_4_ = aSomeSoundClass_3646.anIntArray3612[class23_sub7.anInt2204] / Keyboard.sampleRate;
			for (;;) {
				int i_5_ = (-class23_sub7.anInt2205 + i_4_ + 1048575) / i_4_;
				if (i_5_ > i_1_) {
					break;
				}
				class23_sub7.aClass23_Sub10_Sub1_2198.generate_samples(is, i_3_, i_5_);
				class23_sub7.anInt2205 += -1048576 + i_4_ * i_5_;
				i_1_ -= i_5_;
				Class23_Sub10_Sub1 class23_sub10_sub1 = class23_sub7.aClass23_Sub10_Sub1_2198;
				int i_6_ = Keyboard.sampleRate / 100;
				int i_7_ = 262144 / i_4_;
				i_3_ += i_5_;
				if ((i_7_ ^ 0xffffffff) > (i_6_ ^ 0xffffffff)) {
					i_6_ = i_7_;
				}
				if (aSomeSoundClass_3646.anIntArray3627[class23_sub7.anInt2204] != 0) {
					class23_sub7.aClass23_Sub10_Sub1_2198 = Class23_Sub10_Sub1.method529(class23_sub7.aSomeSoundClass2_2211, class23_sub10_sub1.method508(), 0, class23_sub10_sub1.method513());
					aSomeSoundClass_3646.method566(class23_sub7.aSoundEffects_2224.aShortArray2056[class23_sub7.anInt2215] < 0, (byte) 121, class23_sub7);
					class23_sub7.aClass23_Sub10_Sub1_2198.method515(i_6_, class23_sub10_sub1.method512());
				} else {
					class23_sub7.aClass23_Sub10_Sub1_2198 = Class23_Sub10_Sub1.method529(class23_sub7.aSomeSoundClass2_2211, class23_sub10_sub1.method508(), class23_sub10_sub1.method512(), class23_sub10_sub1.method513());
				}
				if ((class23_sub7.aSoundEffects_2224.aShortArray2056[class23_sub7.anInt2215] ^ 0xffffffff) > -1) {
					class23_sub7.aClass23_Sub10_Sub1_2198.method536(-1);
				}
				class23_sub10_sub1.method519(i_6_);
				class23_sub10_sub1.generate_samples(is, i_3_, i + -i_3_);
				if (class23_sub10_sub1.method526()) {
					aClass23_Sub10_Sub4_3656.method590(class23_sub10_sub1);
				}
			}
			class23_sub7.anInt2205 += i_4_ * i_1_;
		}
		class23_sub7.aClass23_Sub10_Sub1_2198.generate_samples(is, i_3_, i_1_);
		if (i_2_ <= 90) {
			method503();
		}
	}
	
	@Override
	final void generate_samples(int[] is, int i, int i_8_) {
		aClass23_Sub10_Sub4_3656.generate_samples(is, i, i_8_);
	while_107_:
		for (Class23_Sub7 class23_sub7 = (Class23_Sub7) aClass89_3653.get_first(); class23_sub7 != null; class23_sub7 = (Class23_Sub7) aClass89_3653.get_next()) {
			if (!aSomeSoundClass_3646.method576(6295, class23_sub7)) {
				int i_9_ = i_8_;
				int i_10_ = i;
				while (class23_sub7.anInt2197 < i_9_) {
					method584(i_10_ + i_9_, class23_sub7.anInt2197, is, class23_sub7, 124, i_10_);
					i_10_ += class23_sub7.anInt2197;
					i_9_ -= class23_sub7.anInt2197;
					if (aSomeSoundClass_3646.method552(i_9_, is, i_10_, class23_sub7, 100)) {
						continue while_107_;
					}
				}
				method584(i_10_ + i_9_, i_9_, is, class23_sub7, 93, i_10_);
				class23_sub7.anInt2197 -= i_9_;
			}
		}
	}
	
	public final void method586(Class23_Sub7 class23_sub7, int i, byte b) {
		if (b != -61) {
			method502();
		}
		if ((aSomeSoundClass_3646.anIntArray3619[class23_sub7.anInt2204] & 0x4 ^ 0xffffffff) != -1 && class23_sub7.anInt2194 < 0) {
			int i_13_ = aSomeSoundClass_3646.anIntArray3612[class23_sub7.anInt2204] / Keyboard.sampleRate;
			int i_14_ = (-class23_sub7.anInt2205 + 1048575 - -i_13_) / i_13_;
			class23_sub7.anInt2205 = class23_sub7.anInt2205 + i * i_13_ & 0xfffff;
			if ((i_14_ ^ 0xffffffff) >= (i ^ 0xffffffff)) {
				if (aSomeSoundClass_3646.anIntArray3627[class23_sub7.anInt2204] == 0) {
					class23_sub7.aClass23_Sub10_Sub1_2198 = Class23_Sub10_Sub1.method529(class23_sub7.aSomeSoundClass2_2211, class23_sub7.aClass23_Sub10_Sub1_2198.method508(), class23_sub7.aClass23_Sub10_Sub1_2198.method512(), class23_sub7.aClass23_Sub10_Sub1_2198.method513());
				} else {
					class23_sub7.aClass23_Sub10_Sub1_2198 = Class23_Sub10_Sub1.method529(class23_sub7.aSomeSoundClass2_2211, class23_sub7.aClass23_Sub10_Sub1_2198.method508(), 0, class23_sub7.aClass23_Sub10_Sub1_2198.method513());
					aSomeSoundClass_3646.method566(class23_sub7.aSoundEffects_2224.aShortArray2056[class23_sub7.anInt2215] < 0, (byte) 114, class23_sub7);
				}
				if ((class23_sub7.aSoundEffects_2224.aShortArray2056[class23_sub7.anInt2215] ^ 0xffffffff) > -1) {
					class23_sub7.aClass23_Sub10_Sub1_2198.method536(-1);
				}
				i = class23_sub7.anInt2205 / i_13_;
			}
		}
		class23_sub7.aClass23_Sub10_Sub1_2198.method507(i);
	}
	
	//walkingFlag, viewportY, fromY, fromX, rotation, findNear, 83, sizeY, viewportX, sizeX, pathType, objectType
	static final boolean findPath(int walkingFlag, int endY, int fromY, int fromX, int rotation, boolean near, int i_19_, int sizeY, int endX, int sizeX, int i_23_, int type) {
		for (int i_26_ = 0; i_26_ < 104; i_26_++) {
			for (int i_27_ = 0; i_27_ < 104; i_27_++) {
				StaticMethods.pathVia[i_26_][i_27_] = 0;
				StaticMethods.pathCost[i_26_][i_27_] = 99999999;
			}
		}
		StaticMethods.pathVia[fromX][fromY] = 99;
		StaticMethods.pathCost[fromX][fromY] = 0;
		int y = fromY;
		int x = fromX;
		int writePosition = 0;
		PositionedGraphicNode.queueX[writePosition] = fromX;
		StaticMethods.queueY[writePosition++] = fromY;
		int readPosition = 0;
		int[][] is = MapLoader.collision_maps[ObjType.localHeight].clippingFlags;
		boolean foundPath = false;
		while (readPosition != writePosition) {
			x = PositionedGraphicNode.queueX[readPosition];
			y = StaticMethods.queueY[readPosition];
			readPosition = 1 + readPosition & 0xfff;
			if (x == endX && y == endY) {
				foundPath = true;
				break;
			}
			if (type != 0) {
				if (type >= 5 && type != 10 || !MapLoader.collision_maps[ObjType.localHeight].canDoorInteract((byte) 42, type - 1, rotation, endX, y, 2, x, endY)) {
					if (type < 10 && MapLoader.collision_maps[ObjType.localHeight].canDecorationInteract(endX, (byte) 77, y, x, endY, rotation, type + -1, 2)) {
						foundPath = true;
						break;
					}
				} else {
					foundPath = true;
					break;
				}
			}
			if (sizeX != 0 && sizeY != 0 && MapLoader.collision_maps[ObjType.localHeight].canInteract(endY, endX, x, sizeX, 2, y, walkingFlag, sizeY, (byte) -124)) {
				foundPath = true;
				break;
			}
			int i_33_ = StaticMethods.pathCost[x][y] + 1;
			if (x > 0 && (StaticMethods.pathVia[-1 + x][y]) == 0 && (0x12c010e & is[x - 1][y]) == 0 && (0x12c0138 & is[x - 1][1 + y]) == 0) {
				PositionedGraphicNode.queueX[writePosition] = x - 1;
				StaticMethods.queueY[writePosition] = y;
				StaticMethods.pathVia[x + -1][y] = 2;
				writePosition = writePosition + 1 & 0xfff;
				StaticMethods.pathCost[-1 + x][y] = i_33_;
			}
			if (x < 102 && (StaticMethods.pathVia[1 + x][y] ^ 0xffffffff) == -1 && (0x12c0183 & is[2 + x][y] ^ 0xffffffff) == -1 && (0x12c01e0 & is[x - -2][y - -1] ^ 0xffffffff) == -1) {
				PositionedGraphicNode.queueX[writePosition] = 1 + x;
				StaticMethods.queueY[writePosition] = y;
				writePosition = writePosition + 1 & 0xfff;
				StaticMethods.pathVia[x + 1][y] = 8;
				StaticMethods.pathCost[x - -1][y] = i_33_;
			}
			if ((y ^ 0xffffffff) < -1 && StaticMethods.pathVia[x][-1 + y] == 0 && (0x12c010e & is[x][y - 1] ^ 0xffffffff) == -1 && (is[x - -1][-1 + y] & 0x12c0183 ^ 0xffffffff) == -1) {
				PositionedGraphicNode.queueX[writePosition] = x;
				StaticMethods.queueY[writePosition] = y - 1;
				StaticMethods.pathVia[x][-1 + y] = 1;
				StaticMethods.pathCost[x][y + -1] = i_33_;
				writePosition = 1 + writePosition & 0xfff;
			}
			if (y < 102 && StaticMethods.pathVia[x][1 + y] == 0 && (0x12c0138 & is[x][2 + y] ^ 0xffffffff) == -1 && (0x12c01e0 & is[x - -1][y + 2]) == 0) {
				PositionedGraphicNode.queueX[writePosition] = x;
				StaticMethods.queueY[writePosition] = 1 + y;
				StaticMethods.pathVia[x][1 + y] = 4;
				writePosition = 0xfff & 1 + writePosition;
				StaticMethods.pathCost[x][1 + y] = i_33_;
			}
			if (x > 0 && y > 0 && StaticMethods.pathVia[x - 1][-1 + y] == 0 && (is[-1 + x][y] & 0x12c0138 ^ 0xffffffff) == -1 && (is[-1 + x][-1 + y] & 0x12c010e) == 0 && (is[x][-1 + y] & 0x12c0183 ^ 0xffffffff) == -1) {
				PositionedGraphicNode.queueX[writePosition] = x - 1;
				StaticMethods.queueY[writePosition] = y + -1;
				writePosition = writePosition + 1 & 0xfff;
				StaticMethods.pathVia[x - 1][-1 + y] = 3;
				StaticMethods.pathCost[x + -1][y + -1] = i_33_;
			}
			if (x < 102 && y > 0 && StaticMethods.pathVia[x + 1][y + -1] == 0 && (0x12c010e & is[x + 1][y - 1] ^ 0xffffffff) == -1 && (0x12c0183 & is[x + 2][-1 + y] ^ 0xffffffff) == -1 && (0x12c01e0 & is[2 + x][y] ^ 0xffffffff) == -1) {
				PositionedGraphicNode.queueX[writePosition] = 1 + x;
				StaticMethods.queueY[writePosition] = y + -1;
				writePosition = writePosition - -1 & 0xfff;
				StaticMethods.pathVia[x - -1][-1 + y] = 9;
				StaticMethods.pathCost[1 + x][y + -1] = i_33_;
			}
			if (x > 0 && y < 102 && (StaticMethods.pathVia[-1 + x][y - -1] ^ 0xffffffff) == -1 && (is[-1 + x][y - -1] & 0x12c010e ^ 0xffffffff) == -1 && (0x12c0138 & is[-1 + x][2 + y]) == 0 && (is[x][y - -2] & 0x12c01e0) == 0) {
				PositionedGraphicNode.queueX[writePosition] = -1 + x;
				StaticMethods.queueY[writePosition] = y - -1;
				writePosition = 0xfff & 1 + writePosition;
				StaticMethods.pathVia[-1 + x][y + 1] = 6;
				StaticMethods.pathCost[x + -1][y - -1] = i_33_;
			}
			if (x < 102 && y < 102 && (StaticMethods.pathVia[x - -1][1 + y] ^ 0xffffffff) == -1 && (0x12c0138 & is[x + 1][y + 2]) == 0 && (0x12c01e0 & is[x + 2][y + 2] ^ 0xffffffff) == -1 && (0x12c0183 & is[x + 2][1 + y]) == 0) {
				PositionedGraphicNode.queueX[writePosition] = x - -1;
				StaticMethods.queueY[writePosition] = 1 + y;
				writePosition = writePosition + 1 & 0xfff;
				StaticMethods.pathVia[x - -1][y - -1] = 12;
				StaticMethods.pathCost[1 + x][y - -1] = i_33_;
			}
		}
		StaticMethods2.anInt1417 = 0;
		if (!foundPath) {
			if (!near) {
				return false;
			}
			int i_34_ = 1000;
			int i_35_ = 100;
			int i_36_ = 10;
			for (int i_37_ = endX - i_36_; i_36_ + endX >= i_37_; i_37_++) {
				for (int i_38_ = endY - i_36_; i_38_ <= endY + i_36_; i_38_++) {
					if ((i_37_ ^ 0xffffffff) <= -1 && i_38_ >= 0 && i_37_ < 104 && i_38_ < 104 && StaticMethods.pathCost[i_37_][i_38_] < 100) {
						int i_39_ = 0;
						int i_40_ = 0;
						if ((endY ^ 0xffffffff) < (i_38_ ^ 0xffffffff)) {
							i_40_ = endY + -i_38_;
						} else if ((sizeY + endY - 1 ^ 0xffffffff) > (i_38_ ^ 0xffffffff)) {
							i_40_ = i_38_ - sizeY - endY - -1;
						}
						if (endX > i_37_) {
							i_39_ = endX - i_37_;
						} else if (-1 + (endX - -sizeX) < i_37_) {
							i_39_ = i_37_ - -1 - endX + -sizeX;
						}
						int i_41_ = i_39_ * i_39_ - -(i_40_ * i_40_);
						if ((i_34_ ^ 0xffffffff) < (i_41_ ^ 0xffffffff) || (i_34_ ^ 0xffffffff) == (i_41_ ^ 0xffffffff) && StaticMethods.pathCost[i_37_][i_38_] < i_35_) {
							i_35_ = StaticMethods.pathCost[i_37_][i_38_];
							x = i_37_;
							y = i_38_;
							i_34_ = i_41_;
						}
					}
				}
			}
			if (i_34_ == 1000) {
				return false;
			}
			if ((fromX ^ 0xffffffff) == (x ^ 0xffffffff) && (fromY ^ 0xffffffff) == (y ^ 0xffffffff)) {
				return false;
			}
			StaticMethods2.anInt1417 = 1;
		}
		readPosition = 0;
		PositionedGraphicNode.queueX[readPosition] = x;
		StaticMethods.queueY[readPosition++] = y;
		int i_43_;
		int i_42_ = i_43_ = StaticMethods.pathVia[x][y];
		while ((x ^ 0xffffffff) != (fromX ^ 0xffffffff) || (fromY ^ 0xffffffff) != (y ^ 0xffffffff)) {
			if ((i_42_ ^ 0xffffffff) != (i_43_ ^ 0xffffffff)) {
				PositionedGraphicNode.queueX[readPosition] = x;
				StaticMethods.queueY[readPosition++] = y;
				i_43_ = i_42_;
			}
			if ((0x1 & i_42_ ^ 0xffffffff) != -1) {
				y++;
			} else if ((0x4 & i_42_ ^ 0xffffffff) != -1) {
				y--;
			}
			if ((i_42_ & 0x2) == 0) {
				if ((i_42_ & 0x8) != 0) {
					x--;
				}
			} else {
				x++;
			}
			i_42_ = StaticMethods.pathVia[x][y];
		}
		if ((readPosition ^ 0xffffffff) < -1) {
			StaticMethods.sendWalkPacket(readPosition, StaticMethods.queueY, PositionedGraphicNode.queueX, i_23_);
			return true;
		}
		if (i_23_ == 1) {
			return false;
		}
		return true;
	}
	
	public static void method588(int i) {
		aJList_3649 = null;
		aClass16_3659 = null;
		friends_worldid = null;
		aClass16_3655 = null;
		if (i >= -10) {
			anInt3660 = -65;
		}
		aClass16_3657 = null;
	}
	
	Class23_Sub10_Sub3(SomeSoundClass someSoundClass) {
		aSomeSoundClass_3646 = someSoundClass;
	}
	
	static {
		aJList_3649 = new MemoryCache(128);
		aClass16_3657 = RSString.createString("Angreifen");
		aClass16_3659 = RSString.createString("<col=40ff00>");
		anInt3660 = 0;
		friends_worldid = new int[200];
	}
}
