package com.jagex;
/* Class88 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class Class88
{
	public static int anInt1499;
	static long currentUserLong;
	public static RSString aClass16_1502 = RSString.createString("the Play Now button on the main screen");
	public static int anInt1503 = 0;
	public static int anInt1504;
	static RSString aClass16_1505 = aClass16_1502;
	public static int keyPressedID;
	public static int[] anIntArray1507;

	public static void method1429(int i) {
		anIntArray1507 = null;
		if (i < 56) {
			currentUserLong = 72L;
		}
		aClass16_1502 = null;
		aClass16_1505 = null;
		LoginHandler.loginBoxArchiveName = null;
	}
	static final boolean findPath(int i, int rotation, int i_1_, int fromY, int fromX, int size, int type, int endY, int endX, int sizeY, int sizeX, boolean near, int walkingFlag) {
		for (int i_11_ = 0; i_11_ < 104; i_11_++) {
			for (int i_12_ = 0; i_12_ < 104; i_12_++) {
				StaticMethods.pathVia[i_11_][i_12_] = 0;
				StaticMethods.pathCost[i_11_][i_12_] = 99999999;
			}
		}
		if (i_1_ != -29517) {
			findPath(12, 31, 78, 70, -97, 24, 32, -16, 108, -4, 39, false, -31);
		}
		StaticMethods.pathVia[fromX][fromY] = 99;
		int curX = fromX;
		int curY = fromY;
		StaticMethods.pathCost[fromX][fromY] = 0;
		int readPosition = 0;
		int writePosition = 0;
		PositionedGraphicNode.queueX[writePosition] = fromX;
		StaticMethods.queueY[writePosition++] = fromY;
		int[][] is = MapLoader.collision_maps[ObjType.localHeight].clippingFlags;
		boolean foundPath = false;
		while_127_:
			while (writePosition != readPosition) {
				curY = StaticMethods.queueY[readPosition];
				curX = PositionedGraphicNode.queueX[readPosition];
				readPosition = 0xfff & readPosition + 1;
				if ((curX ^ 0xffffffff) == (endX ^ 0xffffffff) && (endY ^ 0xffffffff) == (curY ^ 0xffffffff)) {
					foundPath = true;
					break;
				}
				if (type != 0) {
					if (type >= 5 && type != 10 || !MapLoader.collision_maps[ObjType.localHeight].canDoorInteract((byte) 89, -1 + type, rotation, endX, curY, size, curX, endY)) {
						if (type < 10 && MapLoader.collision_maps[ObjType.localHeight].canDecorationInteract(endX, (byte) 69, curY, curX, endY, rotation, type + -1, size)) {
							foundPath = true;
							break;
						}
					} else {
						foundPath = true;
						break;
					}
				}
				if ((sizeX ^ 0xffffffff) != -1 && (sizeY ^ 0xffffffff) != -1 && MapLoader.collision_maps[ObjType.localHeight].canInteract(endY, endX, curX, sizeX, size, curY, walkingFlag, sizeY, (byte) -124)) {
					foundPath = true;
					break;
				}
				int i_18_ = 1 + StaticMethods.pathCost[curX][curY];
				while_120_:
					do {
						if (curX > 0 && (StaticMethods.pathVia[-1 + curX][curY] ^ 0xffffffff) == -1 && (0x12c010e & is[curX + -1][curY] ^ 0xffffffff) == -1 && (0x12c0138 & is[curX - 1][curY - -size + -1]) == 0) {
							for (int i_19_ = 1; i_19_ < size - 1; i_19_++) {
								if ((is[-1 + curX][curY + i_19_] & 0x12c013e) != 0) {
									break while_120_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = curX + -1;
							StaticMethods.queueY[writePosition] = curY;
							writePosition = 0xfff & 1 + writePosition;
							StaticMethods.pathVia[curX - 1][curY] = 2;
							StaticMethods.pathCost[curX - 1][curY] = i_18_;
						}
					} while (false);
				while_121_:
					do {
						if (curX < 102 && StaticMethods.pathVia[curX - -1][curY] == 0 && (is[curX - -size][curY] & 0x12c0183) == 0 && (is[size + curX][curY + (size - 1)] & 0x12c01e0) == 0) {
							for (int i_20_ = 1; i_20_ < -1 + size; i_20_++) {
								if ((is[size + curX][i_20_ + curY] & 0x12c01e3 ^ 0xffffffff) != -1) {
									break while_121_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = 1 + curX;
							StaticMethods.queueY[writePosition] = curY;
							writePosition = writePosition + 1 & 0xfff;
							StaticMethods.pathVia[curX - -1][curY] = 8;
							StaticMethods.pathCost[1 + curX][curY] = i_18_;
						}
					} while (false);
				while_122_:
					do {
						if (curY > 0 && StaticMethods.pathVia[curX][-1 + curY] == 0 && (is[curX][-1 + curY] & 0x12c010e ^ 0xffffffff) == -1 && (0x12c0183 & is[-1 + size + curX][-1 + curY]) == 0) {
							for (int i_21_ = 1; i_21_ < size - 1; i_21_++) {
								if ((0x12c018f & is[curX - -i_21_][curY + -1] ^ 0xffffffff) != -1) {
									break while_122_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = curX;
							StaticMethods.queueY[writePosition] = curY + -1;
							StaticMethods.pathVia[curX][curY - 1] = 1;
							writePosition = 1 + writePosition & 0xfff;
							StaticMethods.pathCost[curX][curY - 1] = i_18_;
						}
					} while (false);
				while_123_:
					do {
						if (curY < 102 && StaticMethods.pathVia[curX][1 + curY] == 0 && (0x12c0138 & is[curX][curY + size]) == 0 && (is[size + curX - 1][size + curY] & 0x12c01e0) == 0) {
							for (int i_22_ = 1; i_22_ < -1 + size; i_22_++) {
								if ((is[i_22_ + curX][size + curY] & 0x12c01f8 ^ 0xffffffff) != -1) {
									break while_123_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = curX;
							StaticMethods.queueY[writePosition] = curY + 1;
							writePosition = 1 + writePosition & 0xfff;
							StaticMethods.pathVia[curX][curY + 1] = 4;
							StaticMethods.pathCost[curX][1 + curY] = i_18_;
						}
					} while (false);
				while_124_:
					do {
						if (curX > 0 && curY > 0 && StaticMethods.pathVia[curX - 1][curY + -1] == 0 && (is[curX + -1][size + (curY - 2)] & 0x12c0138) == 0 && (is[curX - 1][-1 + curY] & 0x12c010e ^ 0xffffffff) == -1 && (0x12c0183 & is[size + (-1 + (curX - 1))][curY + -1] ^ 0xffffffff) == -1) {
							for (int i_23_ = 1; i_23_ < -1 + size; i_23_++) {
								if ((0x12c013e & is[curX - 1][i_23_ + -1 + curY]) != 0 || (0x12c018f & is[i_23_ + -1 + curX][-1 + curY] ^ 0xffffffff) != -1) {
									break while_124_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = -1 + curX;
							StaticMethods.queueY[writePosition] = curY - 1;
							StaticMethods.pathVia[-1 + curX][-1 + curY] = 3;
							StaticMethods.pathCost[-1 + curX][-1 + curY] = i_18_;
							writePosition = 1 + writePosition & 0xfff;
						}
					} while (false);
				while_125_:
					do {
						if (curX < 102 && curY > 0 && (StaticMethods.pathVia[1 + curX][curY - 1] ^ 0xffffffff) == -1 && (0x12c010e & is[curX - -1][-1 + curY]) == 0 && (is[size + curX][-1 + curY] & 0x12c0183 ^ 0xffffffff) == -1 && (is[curX - -size][size + curY + -1 - 1] & 0x12c01e0) == 0) {
							for (int i_24_ = 1; i_24_ < -1 + size; i_24_++) {
								if ((0x12c01e3 & is[size + curX][curY + (-1 - -i_24_)]) != 0 || (is[1 + curX - -i_24_][curY + -1] & 0x12c018f ^ 0xffffffff) != -1) {
									break while_125_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = 1 + curX;
							StaticMethods.queueY[writePosition] = -1 + curY;
							StaticMethods.pathVia[curX + 1][-1 + curY] = 9;
							StaticMethods.pathCost[curX + 1][-1 + curY] = i_18_;
							writePosition = 1 + writePosition & 0xfff;
						}
					} while (false);
				while_126_:
					do {
						if (curX > 0 && curY < 102 && (StaticMethods.pathVia[-1 + curX][1 + curY] ^ 0xffffffff) == -1 && (0x12c010e & is[-1 + curX][curY + 1]) == 0 && (is[-1 + curX][curY + size] & 0x12c0138 ^ 0xffffffff) == -1 && (is[curX][curY - -size] & 0x12c01e0) == 0) {
							for (int i_25_ = 1; size - 1 > i_25_; i_25_++) {
								if ((0x12c013e & is[curX + -1][i_25_ + 1 + curY]) != 0 || (is[curX + (-1 + i_25_)][size + curY] & 0x12c01f8) != 0) {
									break while_126_;
								}
							}
							PositionedGraphicNode.queueX[writePosition] = curX + -1;
							StaticMethods.queueY[writePosition] = curY - -1;
							StaticMethods.pathVia[-1 + curX][curY - -1] = 6;
							StaticMethods.pathCost[curX + -1][1 + curY] = i_18_;
							writePosition = 1 + writePosition & 0xfff;
						}
					} while (false);
				if (curX < 102 && curY < 102 && (StaticMethods.pathVia[1 + curX][1 + curY] ^ 0xffffffff) == -1 && (is[curX + 1][size + curY] & 0x12c0138) == 0 && (is[curX - -size][curY - -size] & 0x12c01e0 ^ 0xffffffff) == -1 && (0x12c0183 & is[curX + size][1 + curY]) == 0) {
					for (int i_26_ = 1; size + -1 > i_26_; i_26_++) {
						if ((0x12c01f8 & is[curX + 1 - -i_26_][size + curY]) != 0 || (is[size + curX][1 + curY - -i_26_] & 0x12c01e3 ^ 0xffffffff) != -1) {
							continue while_127_;
						}
					}
					PositionedGraphicNode.queueX[writePosition] = 1 + curX;
					StaticMethods.queueY[writePosition] = curY + 1;
					writePosition = 0xfff & 1 + writePosition;
					StaticMethods.pathVia[curX - -1][1 + curY] = 12;
					StaticMethods.pathCost[1 + curX][1 + curY] = i_18_;
				}
			}
		StaticMethods2.anInt1417 = 0;
		if (!foundPath) {
			if (near) {
				int i_27_ = 1000;
				int i_28_ = 10;
				int i_29_ = 100;
				for (int i_30_ = endX + -i_28_; (i_28_ + endX ^ 0xffffffff) <= (i_30_ ^ 0xffffffff); i_30_++) {
					for (int i_31_ = endY + -i_28_; i_28_ + endY >= i_31_; i_31_++) {
						if ((i_30_ ^ 0xffffffff) <= -1 && i_31_ >= 0 && i_30_ < 104 && i_31_ < 104 && StaticMethods.pathCost[i_30_][i_31_] < 100) {
							int i_32_ = 0;
							int i_33_ = 0;
							if ((i_30_ ^ 0xffffffff) > (endX ^ 0xffffffff)) {
								i_32_ = endX + -i_30_;
							} else if (i_30_ > -1 + (endX + sizeX)) {
								i_32_ = i_30_ - endX + (-sizeX - -1);
							}
							if ((i_31_ ^ 0xffffffff) <= (endY ^ 0xffffffff)) {
								if (sizeY + (endY - 1) < i_31_) {
									i_33_ = -sizeY + (-endY + (1 + i_31_));
								}
							} else {
								i_33_ = -i_31_ + endY;
							}
							int i_34_ = i_32_ * i_32_ - -(i_33_ * i_33_);
							if (i_34_ < i_27_ || i_27_ == i_34_ && (StaticMethods.pathCost[i_30_][i_31_] ^ 0xffffffff) > (i_29_ ^ 0xffffffff)) {
								i_27_ = i_34_;
								curY = i_31_;
								curX = i_30_;
								i_29_ = StaticMethods.pathCost[i_30_][i_31_];
							}
						}
					}
				}
				if (i_27_ == 1000) {
					return false;
				}
				if ((curX ^ 0xffffffff) == (fromX ^ 0xffffffff) && (curY ^ 0xffffffff) == (fromY ^ 0xffffffff)) {
					return false;
				}
				StaticMethods2.anInt1417 = 1;
			} else {
				return false;
			}
		}
		readPosition = 0;
		PositionedGraphicNode.queueX[readPosition] = curX;
		StaticMethods.queueY[readPosition++] = curY;
		int i_36_;
		int i_35_ = i_36_ = StaticMethods.pathVia[curX][curY];
		while (curX != fromX || curY != fromY) {
			if ((i_35_ ^ 0xffffffff) != (i_36_ ^ 0xffffffff)) {
				i_36_ = i_35_;
				PositionedGraphicNode.queueX[readPosition] = curX;
				StaticMethods.queueY[readPosition++] = curY;
			}
			if ((i_35_ & 0x1) == 0) {
				if ((i_35_ & 0x4) != 0) {
					curY--;
				}
			} else {
				curY++;
			}
			if ((i_35_ & 0x2 ^ 0xffffffff) != -1) {
				curX++;
			} else if ((0x8 & i_35_) != 0) {
				curX--;
			}
			i_35_ = StaticMethods.pathVia[curX][curY];
		}
		if (readPosition > 0) {
			StaticMethods.sendWalkPacket(readPosition, StaticMethods.queueY, PositionedGraphicNode.queueX, i);
			return true;
		}
		if (i == 1) {
			return false;
		}
		return true;
	}

	static {
		anInt1499 = 0;
		anInt1504 = 0;
		anIntArray1507 = new int[] { 0, 1, 2, 3 };
	}
}
