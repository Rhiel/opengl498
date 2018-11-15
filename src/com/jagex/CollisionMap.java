package com.jagex;
/* TileSetting - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.rs2.client.scene.Scene;

public class CollisionMap {
	public int maximumY;
	public int[][] clippingFlags;
	public int anInt1291;
	public int maximumX;
	static RSString clanRequest = RSString.createString(":clan:");
	static RSString[] chatMessages = new RSString[100];
	static RSString aClass16_1301;
	public static SoftwarePaletteSprite[] aIndexedSpriteArray1303;
	public int anInt1307;
	static boolean aBoolean1310;
	static RSString aClass16_1311;
	static RSString aClass16_1312 = RSString.createString("Ablegen");
	static RSString loadingPrefetchData = RSString.createString("Loading PREFETCH data");
	public static RSString aClass16_1314;

	final boolean canInteract(int destY, int destX, int x, int sizeX, int moverSize, int y, int walkFlag, int sizeY, byte b) {
		if (moverSize > 1) {
			if (method1294((byte) -41, destY, sizeX, y, moverSize, x, moverSize, sizeY, destX)) {
				return true;
			}
			return method1304(walkFlag, moverSize, sizeY, destX, y, moverSize, 115, sizeX, x, destY);
		}
		if (b != -124) {
			return true;
		}
		int i_7_ = -1 + sizeX + destX;
		int i_8_ = sizeY + destY - 1;
		if (destX <= x && i_7_ >= x && y >= destY && y <= i_8_) {
			return true;
		}
		if (destX - 1 == x && destY <= y && (y ^ 0xffffffff) >= (i_8_ ^ 0xffffffff) && (0x8 & clippingFlags[x + -anInt1291][y + -anInt1307]) == 0 && (0x8 & walkFlag ^ 0xffffffff) == -1) {
			return true;
		}
		if (x == 1 + i_7_ && destY <= y && i_8_ >= y && (clippingFlags[-anInt1291 + x][-anInt1307 + y] & 0x80) == 0 && (0x2 & walkFlag ^ 0xffffffff) == -1) {
			return true;
		}
		if (destY - 1 == y && destX <= x && i_7_ >= x && (0x2 & clippingFlags[x + -anInt1291][-anInt1307 + y]) == 0 && (0x4 & walkFlag ^ 0xffffffff) == -1) {
			return true;
		}
		if (y == i_8_ + 1 && (destX ^ 0xffffffff) >= (x ^ 0xffffffff) && (i_7_ ^ 0xffffffff) <= (x ^ 0xffffffff) && (clippingFlags[-anInt1291 + x][-anInt1307 + y] & 0x20) == 0 && (0x1 & walkFlag ^ 0xffffffff) == -1) {
			return true;
		}
		return false;
	}

	static final int[] method1291(int i, int[] is) {
		if (is == null) {
			return null;
		}
		int[] is_9_ = new int[is.length];
		ArrayUtils.arraycopy_int(is, 0, is_9_, i, is.length);
		return is_9_;
	}

	public void orClipTableSET(byte b, int y, int x) {
		x -= anInt1291;
		y -= anInt1307;
		clippingFlags[x][y] = MathUtils.doBitwiseOr(clippingFlags[x][y], 2097152);
	}

	final void flagSolidObject(boolean projectileClipped, int y, int sizeY, int sizeX, int i_14_, int x) {
		y -= anInt1307;
		int clipdata = i_14_;
		x -= anInt1291;
		if (projectileClipped) {
			clipdata += 131072;
		}
		for (int i = x; i < x + sizeX; i++) {
			if (i >= 0 && i < maximumX) {
				for (int j = y; j < y + sizeY; j++) {
					if (j >= 0 && maximumY > j) {
						addFlag(i, clipdata, false, j);
					}
				}
			}
		}
	}

	public final boolean method1294(byte b, int destY, int sizeX, int curY, int moverSizeX, int curX, int moverSizeY, int sizeY, int destX) {
		if (b != -41) {
			return false;
		}
		if (curX >= sizeX + destX || moverSizeX + curX <= destX) {
			return false;
		}
		if (destY + sizeY <= curY || curY + moverSizeY <= destY) {
			return false;
		}
		return true;
	}

	final void method1295(boolean bool, boolean bool_26_, int i, int i_27_, int i_28_, int i_29_) {
		if (bool == true) {
			i_29_ -= anInt1307;
			i -= anInt1291;
			if ((i_27_ ^ 0xffffffff) == -1) {
				if (i_28_ == 0) {
					method1302((byte) -71, i_29_, i, 128);
					method1302((byte) -124, i_29_, -1 + i, 8);
				}
				if (i_28_ == 1) {
					method1302((byte) -97, i_29_, i, 2);
					method1302((byte) -128, 1 + i_29_, i, 32);
				}
				if (i_28_ == 2) {
					method1302((byte) -22, i_29_, i, 8);
					method1302((byte) -109, i_29_, 1 + i, 128);
				}
				if (i_28_ == 3) {
					method1302((byte) -72, i_29_, i, 32);
					method1302((byte) -49, i_29_ + -1, i, 2);
				}
			}
			if (i_27_ == 1 || i_27_ == 3) {
				if (i_28_ == 0) {
					method1302((byte) -48, i_29_, i, 1);
					method1302((byte) -29, 1 + i_29_, i + -1, 16);
				}
				if (i_28_ == 1) {
					method1302((byte) -3, i_29_, i, 4);
					method1302((byte) -82, 1 + i_29_, 1 + i, 64);
				}
				if (i_28_ == 2) {
					method1302((byte) -9, i_29_, i, 16);
					method1302((byte) -47, -1 + i_29_, 1 + i, 1);
				}
				if (i_28_ == 3) {
					method1302((byte) -6, i_29_, i, 64);
					method1302((byte) -81, -1 + i_29_, i - 1, 4);
				}
			}
			if (i_27_ == 2) {
				if (i_28_ == 0) {
					method1302((byte) -29, i_29_, i, 130);
					method1302((byte) -24, i_29_, -1 + i, 8);
					method1302((byte) -2, 1 + i_29_, i, 32);
				}
				if (i_28_ == 1) {
					method1302((byte) -42, i_29_, i, 10);
					method1302((byte) -68, i_29_ - -1, i, 32);
					method1302((byte) -60, i_29_, 1 + i, 128);
				}
				if (i_28_ == 2) {
					method1302((byte) -38, i_29_, i, 40);
					method1302((byte) -71, i_29_, i + 1, 128);
					method1302((byte) -55, -1 + i_29_, i, 2);
				}
				if (i_28_ == 3) {
					method1302((byte) -113, i_29_, i, 160);
					method1302((byte) -45, -1 + i_29_, i, 2);
					method1302((byte) -118, i_29_, -1 + i, 8);
				}
			}
			if (bool_26_) {
				if ((i_27_ ^ 0xffffffff) == -1) {
					if (i_28_ == 0) {
						method1302((byte) -6, i_29_, i, 65536);
						method1302((byte) -60, i_29_, i - 1, 4096);
					}
					if (i_28_ == 1) {
						method1302((byte) -23, i_29_, i, 1024);
						method1302((byte) -68, 1 + i_29_, i, 16384);
					}
					if (i_28_ == 2) {
						method1302((byte) -68, i_29_, i, 4096);
						method1302((byte) -23, i_29_, 1 + i, 65536);
					}
					if (i_28_ == 3) {
						method1302((byte) -8, i_29_, i, 16384);
						method1302((byte) -70, -1 + i_29_, i, 1024);
					}
				}
				if (i_27_ == 1 || i_27_ == 3) {
					if (i_28_ == 0) {
						method1302((byte) -47, i_29_, i, 512);
						method1302((byte) -52, 1 + i_29_, i + -1, 8192);
					}
					if (i_28_ == 1) {
						method1302((byte) -124, i_29_, i, 2048);
						method1302((byte) -16, 1 + i_29_, 1 + i, 32768);
					}
					if (i_28_ == 2) {
						method1302((byte) -67, i_29_, i, 8192);
						method1302((byte) -35, i_29_ + -1, 1 + i, 512);
					}
					if (i_28_ == 3) {
						method1302((byte) -111, i_29_, i, 32768);
						method1302((byte) -56, i_29_ - 1, i - 1, 2048);
					}
				}
				if (i_27_ == 2) {
					if (i_28_ == 0) {
						method1302((byte) -73, i_29_, i, 66560);
						method1302((byte) -83, i_29_, -1 + i, 4096);
						method1302((byte) -28, 1 + i_29_, i, 16384);
					}
					if (i_28_ == 1) {
						method1302((byte) -39, i_29_, i, 5120);
						method1302((byte) -20, i_29_ - -1, i, 16384);
						method1302((byte) -22, i_29_, i - -1, 65536);
					}
					if (i_28_ == 2) {
						method1302((byte) -25, i_29_, i, 20480);
						method1302((byte) -61, i_29_, 1 + i, 65536);
						method1302((byte) -60, i_29_ + -1, i, 1024);
					}
					if (i_28_ == 3) {
						method1302((byte) -4, i_29_, i, 81920);
						method1302((byte) -96, -1 + i_29_, i, 1024);
						method1302((byte) -126, i_29_, -1 + i, 4096);
					}
				}
			}
		}
	}

	public final void addFlag(int i, int flag, boolean bool, int i_31_) {
		if (bool == false) {
			clippingFlags[i][i_31_] = MathUtils.doBitwiseOr(clippingFlags[i][i_31_], flag);
		}
	}

	final void addTileFlag(int i, int i_32_, int i_33_) {
		i_33_ -= anInt1307;
		i -= anInt1291;
		clippingFlags[i][i_33_] = MathUtils.doBitwiseOr(clippingFlags[i][i_33_], 262144);
	}

	public final void method1298(int i) {
		if (i <= 93) {
			method1298(103);
		}
		for (int x = 0; (x ^ 0xffffffff) > (maximumX ^ 0xffffffff); x++) {
			for (int y = 0; maximumY > y; y++) {
				if (x != 0 && y != 0 && (-5 + maximumX ^ 0xffffffff) < (x ^ 0xffffffff) && (y ^ 0xffffffff) > (-5 + maximumY ^ 0xffffffff)) {
					clippingFlags[x][y] = 16777216;
				} else {
					clippingFlags[x][y] = 16777215;
				}
			}
		}
	}

	final void method1299(int i, int i_37_, int dummy, boolean bool, int i_39_, int i_40_, int i_41_) {
		i -= anInt1291;
		i_41_ -= anInt1307;
		if (dummy != 31317) {
			maximumX = 95;
		}
		int i_42_ = 256;
		if (i_40_ == 1 || i_40_ == 3) {
			int i_43_ = i_37_;
			i_37_ = i_39_;
			i_39_ = i_43_;
		}
		if (bool) {
			i_42_ += 131072;
		}
		for (int i_44_ = i; (i + i_37_ ^ 0xffffffff) < (i_44_ ^ 0xffffffff); i_44_++) {
			if ((i_44_ ^ 0xffffffff) <= -1 && maximumX > i_44_) {
				for (int i_45_ = i_41_; i_45_ < i_39_ + i_41_; i_45_++) {
					if (i_45_ >= 0 && (i_45_ ^ 0xffffffff) > (maximumY ^ 0xffffffff)) {
						method1302((byte) -38, i_45_, i_44_, i_42_);
					}
				}
			}
		}
	}

	public static void method1300(int i) {
		if (i != 0) {
			aClass16_1314 = null;
		}
		chatMessages = null;
		aClass16_1311 = null;
		clanRequest = null;
		aClass16_1314 = null;
		aClass16_1312 = null;
		ReflectionAntiCheat.request_queue = null;
		aClass16_1301 = null;
		aIndexedSpriteArray1303 = null;
	}

	public final void method1302(byte b, int i, int i_54_, int i_55_) {
		clippingFlags[i_54_][i] = MathUtils.bitAnd(clippingFlags[i_54_][i], i_55_ ^ 0xffffffff);
		if (b >= -1) {
			anInt1307 = 54;
		}
	}

	// destinationX, (byte) 111, currentY, currentX, destinationY, rotation, objectType - 1, 1
	final boolean canDecorationInteract(int destX, byte b, int curY, int curX, int destY, int rotation, int type, int size) {
		if (size != 1) {
			if (destX >= curX && destX <= -1 + curX + size && destY >= destY && (destY ^ 0xffffffff) >= (-1 + destY + size ^ 0xffffffff)) {
				return true;
			}
		} else if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && curY == destY) {
			return true;
		}
		destX -= anInt1291;
		destY -= anInt1307;
		curX -= anInt1291;
		curY -= anInt1307;
		if (b < 52) {
			ReflectionAntiCheat.request_queue = null;
		}
		if (size == 1) {
			if (type == 6 || type == 7) {
				if (type == 7) {
					rotation = rotation - -2 & 0x3;
				}
				if (rotation == 0) {
					if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && (curY ^ 0xffffffff) == (destY ^ 0xffffffff) && (0x80 & clippingFlags[curX][curY]) == 0) {
						return true;
					}
					if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && (curY ^ 0xffffffff) == (destY - 1 ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x2 ^ 0xffffffff) == -1) {
						return true;
					}
				} else if (rotation == 1) {
					if ((curX ^ 0xffffffff) == (-1 + destX ^ 0xffffffff) && curY == destY && (0x8 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (destX ^ 0xffffffff) && curY == destY - 1 && (clippingFlags[curX][curY] & 0x2) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if ((destX + -1 ^ 0xffffffff) == (curX ^ 0xffffffff) && (destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x8) == 0) {
						return true;
					}
					if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && (destY + 1 ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x20 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
						return true;
					}
				} else if (rotation == 3) {
					if ((1 + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && (curY ^ 0xffffffff) == (destY ^ 0xffffffff) && (0x80 & clippingFlags[curX][curY]) == 0) {
						return true;
					}
					if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && curY == destY - -1 && (0x20 & clippingFlags[curX][curY]) == 0) {
						return true;
					}
				}
			}
			if (type == 8) {
				if (destX == curX && (curY ^ 0xffffffff) == (destY - -1 ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x20) == 0) {
					return true;
				}
				if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && -1 + destY == curY && (0x2 & clippingFlags[curX][curY]) == 0) {
					return true;
				}
				if (curX == destX + -1 && curY == destY && (0x8 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
					return true;
				}
				if ((curX ^ 0xffffffff) == (destX + 1 ^ 0xffffffff) && curY == destY && (clippingFlags[curX][curY] & 0x80) == 0) {
					return true;
				}
			}
		} else {
			int i_62_ = size + curX - 1;
			int i_63_ = -1 + curY - -size;
			if (type == 6 || type == 7) {
				if (type == 7) {
					rotation = 0x3 & 2 + rotation;
				}
				if ((rotation ^ 0xffffffff) == -1) {
					if (destX + 1 == curX && destY >= curY && (destY ^ 0xffffffff) >= (i_63_ ^ 0xffffffff) && (clippingFlags[curX][destY] & 0x80) == 0) {
						return true;
					}
					if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && destX <= i_62_ && (-size + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x2 & clippingFlags[destX][i_63_]) == 0) {
						return true;
					}
				} else if (rotation != 1) {
					if (rotation == 2) {
						if ((curX ^ 0xffffffff) == (destX - size ^ 0xffffffff) && curY <= destY && destY <= i_63_ && (0x8 & clippingFlags[i_62_][destY] ^ 0xffffffff) == -1) {
							return true;
						}
						if (curX <= destX && (i_62_ ^ 0xffffffff) <= (destX ^ 0xffffffff) && destY - -1 == curY && (0x20 & clippingFlags[destX][curY] ^ 0xffffffff) == -1) {
							return true;
						}
					} else if (rotation == 3) {
						if (1 + destX == curX && curY <= destY && destY <= i_63_ && (0x80 & clippingFlags[curX][destY] ^ 0xffffffff) == -1) {
							return true;
						}
						if (destX >= curX && destX <= i_62_ && (1 + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[destX][curY] & 0x20) == 0) {
							return true;
						}
					}
				} else {
					if (-size + destX == curX && destY >= curY && i_63_ >= destY && (clippingFlags[i_62_][destY] & 0x8) == 0) {
						return true;
					}
					if (curX <= destX && (i_62_ ^ 0xffffffff) <= (destX ^ 0xffffffff) && -size + destY == curY && (clippingFlags[destX][i_63_] & 0x2) == 0) {
						return true;
					}
				}
			}
			if (type == 8) {
				if (curX <= destX && (destX ^ 0xffffffff) >= (i_62_ ^ 0xffffffff) && 1 + destY == curY && (clippingFlags[destX][curY] & 0x20) == 0) {
					return true;
				}
				if ((curX ^ 0xffffffff) >= (destX ^ 0xffffffff) && (destX ^ 0xffffffff) >= (i_62_ ^ 0xffffffff) && (curY ^ 0xffffffff) == (-size + destY ^ 0xffffffff) && (0x2 & clippingFlags[destX][i_63_]) == 0) {
					return true;
				}
				if (curX == -size + destX && destY >= curY && destY <= i_63_ && (0x8 & clippingFlags[i_62_][destY]) == 0) {
					return true;
				}
				if ((1 + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && curY <= destY && i_63_ >= destY && (clippingFlags[curX][destY] & 0x80) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public final boolean method1304(int walkingFlag, int moverSizeY, int sizeY, int destX, int y, int moverSizeX, int i_69_, int sizeX, int x, int destY) {
		int fromCornerY = y + moverSizeY;
		int fromCornerX = x + moverSizeX;
		int toCornerX = sizeX + destX;
		int toCornerY = sizeY + destY;
		if (destX <= x && x < toCornerX) {
			if (destY == fromCornerY && (walkingFlag & 0x4) == 0) {
				int i_77_ = x;
				for (int i_78_ = toCornerX < fromCornerX ? toCornerX : fromCornerX; i_78_ > i_77_; i_77_++) {
					if ((clippingFlags[i_77_ - anInt1291][-1 + -anInt1307 + fromCornerY] & 0x2) == 0) {
						return true;
					}
				}
			} else if (toCornerY == y && (walkingFlag & 0x1) == 0) {
				int i_79_ = x;
				for (int i_80_ = fromCornerX <= toCornerX ? fromCornerX : toCornerX; (i_79_ ^ 0xffffffff) > (i_80_ ^ 0xffffffff); i_79_++) {
					if ((clippingFlags[-anInt1291 + i_79_][-anInt1307 + y] & 0x20 ^ 0xffffffff) == -1) {
						return true;
					}
				}
			}
		} else if (destX < fromCornerX && toCornerX >= fromCornerX) {
			if ((fromCornerY ^ 0xffffffff) == (destY ^ 0xffffffff) && (0x4 & walkingFlag ^ 0xffffffff) == -1) {
				for (int i_81_ = destX; (fromCornerX ^ 0xffffffff) < (i_81_ ^ 0xffffffff); i_81_++) {
					if ((clippingFlags[i_81_ - anInt1291][-1 + -anInt1307 + fromCornerY] & 0x2 ^ 0xffffffff) == -1) {
						return true;
					}
				}
			} else if ((toCornerY ^ 0xffffffff) == (y ^ 0xffffffff) && (0x1 & walkingFlag) == 0) {
				for (int i_82_ = destX; fromCornerX > i_82_; i_82_++) {
					if ((clippingFlags[i_82_ + -anInt1291][-anInt1307 + y] & 0x20) == 0) {
						return true;
					}
				}
			}
		} else if (y < destY || y >= toCornerY) {
			if (fromCornerY > destY && toCornerY >= fromCornerY) {
				if (fromCornerX == destX && (walkingFlag & 0x8) == 0) {
					for (int i_83_ = destY; i_83_ < fromCornerY; i_83_++) {
						if ((clippingFlags[-1 + fromCornerX + -anInt1291][-anInt1307 + i_83_] & 0x8 ^ 0xffffffff) == -1) {
							return true;
						}
					}
				} else if ((x ^ 0xffffffff) == (toCornerX ^ 0xffffffff) && (0x2 & walkingFlag) == 0) {
					for (int i_84_ = destY; fromCornerY > i_84_; i_84_++) {
						if ((clippingFlags[-anInt1291 + x][-anInt1307 + i_84_] & 0x80 ^ 0xffffffff) == -1) {
							return true;
						}
					}
				}
			}
		} else if ((destX ^ 0xffffffff) != (fromCornerX ^ 0xffffffff) || (0x8 & walkingFlag ^ 0xffffffff) != -1) {
			if ((x ^ 0xffffffff) == (toCornerX ^ 0xffffffff) && (walkingFlag & 0x2) == 0) {
				int i_85_ = y;
				for (int i_86_ = fromCornerY <= toCornerY ? fromCornerY : toCornerY; i_85_ < i_86_; i_85_++) {
					if ((0x80 & clippingFlags[x - anInt1291][i_85_ - anInt1307] ^ 0xffffffff) == -1) {
						return true;
					}
				}
			}
		} else {
			int i_87_ = y;
			for (int i_88_ = fromCornerY > toCornerY ? toCornerY : fromCornerY; i_88_ > i_87_; i_87_++) {
				if ((clippingFlags[-anInt1291 + fromCornerX - 1][-anInt1307 + i_87_] & 0x8) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	// method1305((byte) -87, -1 + objectType, rotation, destinationX, currentY, 1, currentX, destinationY)
	final boolean canDoorInteract(byte b, int objectType, int rotation, int destX, int curY, int i_92_, int curX, int destY) {
		if (i_92_ != 1) {
			if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && (destX ^ 0xffffffff) >= (i_92_ + curX - 1 ^ 0xffffffff) && (destY ^ 0xffffffff) <= (destY ^ 0xffffffff) && (destY ^ 0xffffffff) >= (destY + i_92_ - 1 ^ 0xffffffff)) {
				return true;
			}
		} else if ((curX ^ 0xffffffff) == (destX ^ 0xffffffff) && destY == curY) {
			return true;
		}
		curY -= anInt1307;
		destX -= anInt1291;
		destY -= anInt1307;
		curX -= anInt1291;
		if (i_92_ == 1) {
			if (objectType == 0) {
				if ((rotation ^ 0xffffffff) == -1) {
					if ((curX ^ 0xffffffff) == (destX - 1 ^ 0xffffffff) && (destY ^ 0xffffffff) == (curY ^ 0xffffffff)) {
						return true;
					}
					if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && (1 + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x12c0120 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
						return true;
					}
					if (curX == destX && (destY - 1 ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x12c0102) == 0) {
						return true;
					}
				} else if (rotation != 1) {
					if (rotation != 2) {
						if (rotation == 3) {
							if (curX == destX && -1 + destY == curY) {
								return true;
							}
							if ((curX ^ 0xffffffff) == (-1 + destX ^ 0xffffffff) && (destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x12c0108 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
								return true;
							}
							if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && (destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x12c0180 ^ 0xffffffff) == -1) {
								return true;
							}
						}
					} else {
						if ((1 + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && destY == curY) {
							return true;
						}
						if (destX == curX && (1 + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x12c0120 & clippingFlags[curX][curY]) == 0) {
							return true;
						}
						if (curX == destX && (curY ^ 0xffffffff) == (destY - 1 ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x12c0102 ^ 0xffffffff) == -1) {
							return true;
						}
					}
				} else {
					if (curX == destX && (destY - -1 ^ 0xffffffff) == (curY ^ 0xffffffff)) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (destX + -1 ^ 0xffffffff) && curY == destY && (0x12c0108 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && destY == curY && (0x12c0180 & clippingFlags[curX][curY]) == 0) {
						return true;
					}
				}
			}
			if (objectType == 2) {
				if ((rotation ^ 0xffffffff) != -1) {
					if (rotation != 1) {
						if (rotation == 2) {
							if ((-1 + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && destY == curY && (0x12c0108 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
								return true;
							}
							if (destX == curX && 1 + destY == curY && (0x12c0120 & clippingFlags[curX][curY]) == 0) {
								return true;
							}
							if (1 + destX == curX && (curY ^ 0xffffffff) == (destY ^ 0xffffffff)) {
								return true;
							}
							if ((curX ^ 0xffffffff) == (destX ^ 0xffffffff) && (curY ^ 0xffffffff) == (-1 + destY ^ 0xffffffff)) {
								return true;
							}
						} else if (rotation == 3) {
							if ((-1 + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && curY == destY) {
								return true;
							}
							if (destX == curX && (curY ^ 0xffffffff) == (destY + 1 ^ 0xffffffff) && (0x12c0120 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
								return true;
							}
							if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && (curY ^ 0xffffffff) == (destY ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x12c0180 ^ 0xffffffff) == -1) {
								return true;
							}
							if (destX == curX && -1 + destY == curY) {
								return true;
							}
						}
					} else {
						if ((curX ^ 0xffffffff) == (destX + -1 ^ 0xffffffff) && curY == destY && (0x12c0108 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
							return true;
						}
						if (curX == destX && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff)) {
							return true;
						}
						if (1 + destX == curX && curY == destY) {
							return true;
						}
						if (curX == destX && destY - 1 == curY && (clippingFlags[curX][curY] & 0x12c0102) == 0) {
							return true;
						}
					}
				} else {
					if ((destX - 1 ^ 0xffffffff) == (curX ^ 0xffffffff) && curY == destY) {
						return true;
					}
					if ((destX ^ 0xffffffff) == (curX ^ 0xffffffff) && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff)) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (destX - -1 ^ 0xffffffff) && (curY ^ 0xffffffff) == (destY ^ 0xffffffff) && (0x12c0180 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
						return true;
					}
					if (curX == destX && (destY - 1 ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x12c0102) == 0) {
						return true;
					}
				}
			}
			if (objectType == 9) {
				if ((curX ^ 0xffffffff) == (destX ^ 0xffffffff) && curY == 1 + destY && (clippingFlags[curX][curY] & 0x20) == 0) {
					return true;
				}
				if (curX == destX && (curY ^ 0xffffffff) == (-1 + destY ^ 0xffffffff) && (clippingFlags[curX][curY] & 0x2) == 0) {
					return true;
				}
				if ((curX ^ 0xffffffff) == (-1 + destX ^ 0xffffffff) && curY == destY && (0x8 & clippingFlags[curX][curY]) == 0) {
					return true;
				}
				if ((destX + 1 ^ 0xffffffff) == (curX ^ 0xffffffff) && (curY ^ 0xffffffff) == (destY ^ 0xffffffff) && (0x80 & clippingFlags[curX][curY] ^ 0xffffffff) == -1) {
					return true;
				}
			}
		} else {
			int i_95_ = curX - (-i_92_ + 1);
			int i_96_ = -1 + curY - -i_92_;
			if (objectType == 0) {
				if (rotation == 0) {
					if (destX - i_92_ == curX && destY >= curY && (destY ^ 0xffffffff) >= (i_96_ ^ 0xffffffff)) {
						return true;
					}
					if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && i_95_ >= destX && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff) && (clippingFlags[destX][curY] & 0x12c0120) == 0) {
						return true;
					}
					if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && (i_95_ ^ 0xffffffff) <= (destX ^ 0xffffffff) && (-i_92_ + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[destX][i_96_] & 0x12c0102) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (destX >= curX && i_95_ >= destX && destY - -1 == curY) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (-i_92_ + destX ^ 0xffffffff) && destY >= curY && (i_96_ ^ 0xffffffff) <= (destY ^ 0xffffffff) && (0x12c0108 & clippingFlags[i_95_][destY]) == 0) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && destY >= curY && i_96_ >= destY && (clippingFlags[curX][destY] & 0x12c0180) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && curY <= destY && (destY ^ 0xffffffff) >= (i_96_ ^ 0xffffffff)) {
						return true;
					}
					if (curX <= destX && i_95_ >= destX && destY - -1 == curY && (0x12c0120 & clippingFlags[destX][curY] ^ 0xffffffff) == -1) {
						return true;
					}
					if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && (destX ^ 0xffffffff) >= (i_95_ ^ 0xffffffff) && (destY + -i_92_ ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x12c0102 & clippingFlags[destX][i_96_] ^ 0xffffffff) == -1) {
						return true;
					}
				} else if (rotation == 3) {
					if ((curX ^ 0xffffffff) >= (destX ^ 0xffffffff) && (destX ^ 0xffffffff) >= (i_95_ ^ 0xffffffff) && curY == -i_92_ + destY) {
						return true;
					}
					if ((-i_92_ + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && (curY ^ 0xffffffff) >= (destY ^ 0xffffffff) && destY <= i_96_ && (clippingFlags[i_95_][destY] & 0x12c0108) == 0) {
						return true;
					}
					if ((1 + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && curY <= destY && i_96_ >= destY && (clippingFlags[curX][destY] & 0x12c0180 ^ 0xffffffff) == -1) {
						return true;
					}
				}
			}
			if (objectType == 2) {
				if ((rotation ^ 0xffffffff) == -1) {
					if ((destX - i_92_ ^ 0xffffffff) == (curX ^ 0xffffffff) && (curY ^ 0xffffffff) >= (destY ^ 0xffffffff) && destY <= i_96_) {
						return true;
					}
					if (curX <= destX && destX <= i_95_ && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff)) {
						return true;
					}
					if ((curX ^ 0xffffffff) == (1 + destX ^ 0xffffffff) && (curY ^ 0xffffffff) >= (destY ^ 0xffffffff) && destY <= i_96_ && (0x12c0180 & clippingFlags[curX][destY] ^ 0xffffffff) == -1) {
						return true;
					}
					if (curX <= destX && (i_95_ ^ 0xffffffff) <= (destX ^ 0xffffffff) && (-i_92_ + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (clippingFlags[destX][i_96_] & 0x12c0102 ^ 0xffffffff) == -1) {
						return true;
					}
				} else if (rotation == 1) {
					if ((-i_92_ + destX ^ 0xffffffff) == (curX ^ 0xffffffff) && destY >= curY && (destY ^ 0xffffffff) >= (i_96_ ^ 0xffffffff) && (clippingFlags[i_95_][destY] & 0x12c0108) == 0) {
						return true;
					}
					if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && i_95_ >= destX && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff)) {
						return true;
					}
					if (destX + 1 == curX && curY <= destY && (destY ^ 0xffffffff) >= (i_96_ ^ 0xffffffff)) {
						return true;
					}
					if (destX >= curX && (i_95_ ^ 0xffffffff) <= (destX ^ 0xffffffff) && (destY + -i_92_ ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x12c0102 & clippingFlags[destX][i_96_] ^ 0xffffffff) == -1) {
						return true;
					}
				} else if (rotation != 2) {
					if (rotation == 3) {
						if (destX + -i_92_ == curX && destY >= curY && destY <= i_96_) {
							return true;
						}
						if ((curX ^ 0xffffffff) >= (destX ^ 0xffffffff) && (i_95_ ^ 0xffffffff) <= (destX ^ 0xffffffff) && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff) && (clippingFlags[destX][curY] & 0x12c0120 ^ 0xffffffff) == -1) {
							return true;
						}
						if (1 + destX == curX && (destY ^ 0xffffffff) <= (curY ^ 0xffffffff) && i_96_ >= destY && (0x12c0180 & clippingFlags[curX][destY]) == 0) {
							return true;
						}
						if ((destX ^ 0xffffffff) <= (curX ^ 0xffffffff) && destX <= i_95_ && (curY ^ 0xffffffff) == (-i_92_ + destY ^ 0xffffffff)) {
							return true;
						}
					}
				} else {
					if (curX == destX + -i_92_ && (curY ^ 0xffffffff) >= (destY ^ 0xffffffff) && i_96_ >= destY && (clippingFlags[i_95_][destY] & 0x12c0108) == 0) {
						return true;
					}
					if (destX >= curX && destX <= i_95_ && (1 + destY ^ 0xffffffff) == (curY ^ 0xffffffff) && (0x12c0120 & clippingFlags[destX][curY]) == 0) {
						return true;
					}
					if (1 + destX == curX && (destY ^ 0xffffffff) <= (curY ^ 0xffffffff) && i_96_ >= destY) {
						return true;
					}
					if (curX <= destX && (destX ^ 0xffffffff) >= (i_95_ ^ 0xffffffff) && curY == -i_92_ + destY) {
						return true;
					}
				}
			}
			if (objectType == 9) {
				if (destX >= curX && (destX ^ 0xffffffff) >= (i_95_ ^ 0xffffffff) && (curY ^ 0xffffffff) == (1 + destY ^ 0xffffffff) && (clippingFlags[destX][curY] & 0x12c0120 ^ 0xffffffff) == -1) {
					return true;
				}
				if (destX >= curX && i_95_ >= destX && curY == -i_92_ + destY && (0x12c0102 & clippingFlags[destX][i_96_]) == 0) {
					return true;
				}
				if (-i_92_ + destX == curX && (destY ^ 0xffffffff) <= (curY ^ 0xffffffff) && i_96_ >= destY && (0x12c0108 & clippingFlags[i_95_][destY] ^ 0xffffffff) == -1) {
					return true;
				}
				if (curX == destX + 1 && (destY ^ 0xffffffff) <= (curY ^ 0xffffffff) && i_96_ >= destY && (clippingFlags[curX][destY] & 0x12c0180 ^ 0xffffffff) == -1) {
					return true;
				}
			}
		}
		return false;
	}

	static final void method1306(int i, int i_98_, int i_99_, int i_100_) {
		Ground tile = Scene.current_grounds[i][i_98_][i_99_];
		if (tile != null) {
			WallDecoration wallDecor = tile.wall_decoration;
			if (wallDecor != null) {
				wallDecor.anInt375 = wallDecor.anInt375 * i_100_ / 16;
				wallDecor.anInt371 = wallDecor.anInt371 * i_100_ / 16;
			}
		}
	}

	static final RSString method1307(long l, int i, byte b, boolean bool) {
		if (i < 2 || i > 36) {
			throw new IllegalArgumentException("Invalid radix:" + i);
		}
		int i_101_ = 1;
		long l_102_ = l / i;
		if (b != 43) {
			aClass16_1314 = null;
		}
		while ((l_102_ ^ 0xffffffffffffffffL) != -1L) {
			l_102_ /= i;
			i_101_++;
		}
		int i_103_ = i_101_;
		if (l < 0L || bool) {
			i_103_++;
		}
		byte[] bs = new byte[i_103_];
		if ((l ^ 0xffffffffffffffffL) <= -1L) {
			if (bool) {
				bs[0] = (byte) 43;
			}
		} else {
			bs[0] = (byte) 45;
		}
		for (int i_104_ = 0; (i_101_ ^ 0xffffffff) < (i_104_ ^ 0xffffffff); i_104_++) {
			int i_105_ = (int) (l % i);
			l /= i;
			if (i_105_ < 0) {
				i_105_ = -i_105_;
			}
			if (i_105_ > 9) {
				i_105_ += 39;
			}
			bs[-1 + i_103_ + -i_104_] = (byte) (48 + i_105_);
		}
		RSString class16 = new RSString();
		class16.length = i_103_;
		class16.bytes = bs;
		return class16;
	}

	CollisionMap(int i, int i_106_) {
		maximumX = i;
		anInt1307 = 0;
		maximumY = i_106_;
		clippingFlags = new int[maximumX][maximumY];
		anInt1291 = 0;
		method1298(122);
	}

	final void method1499(int i, int i_107_) {
		i_107_ -= anInt1307;
		i -= anInt1291;
		clippingFlags[i][i_107_] = MathUtils.bitAnd(clippingFlags[i][i_107_], -262145);
	}

	final void addWallFlag(boolean projectileClipped, int y, int rotation, int dummy, int x, int type) {
		x -= anInt1291;
		y -= anInt1307;
		if (type == 0) {
			if (rotation == 0) {
				addFlag(x, 128, false, y);
				addFlag(x - 1, 8, false, y);
			}
			if (rotation == 1) {
				addFlag(x, 2, false, y);
				addFlag(x, 32, false, y + 1);
			}
			if (rotation == 2) {
				addFlag(x, 8, false, y);
				addFlag(1 + x, 128, false, y);
			}
			if (rotation == 3) {
				addFlag(x, 32, false, y);
				addFlag(x, 2, false, y - 1);
			}
		}
		if (type == 1 || type == 3) {
			if (rotation == 0) {
				addFlag(x, 1, false, y);
				addFlag(x + -1, 16, false, 1 + y);
			}
			if (rotation == 1) {
				addFlag(x, 4, false, y);
				addFlag(1 + x, 64, false, y - -1);
			}
			if (rotation == 2) {
				addFlag(x, 16, false, y);
				addFlag(1 + x, 1, false, -1 + y);
			}
			if (rotation == 3) {
				addFlag(x, 64, false, y);
				addFlag(x - 1, 4, false, y - 1);
			}
		}
		if (type == 2) {
			if (rotation == 0) {
				addFlag(x, 130, false, y);
				addFlag(-1 + x, 8, false, y);
				addFlag(x, 32, false, 1 + y);
			}
			if (rotation == 1) {
				addFlag(x, 10, false, y);
				addFlag(x, 32, false, y + 1);
				addFlag(1 + x, 128, false, y);
			}
			if (rotation == 2) {
				addFlag(x, 40, false, y);
				addFlag(1 + x, 128, false, y);
				addFlag(x, 2, false, -1 + y);
			}
			if (rotation == 3) {
				addFlag(x, 160, false, y);
				addFlag(x, 2, false, -1 + y);
				addFlag(-1 + x, 8, false, y);
			}
		}
		if (projectileClipped) {
			if (type == 0) {
				if (rotation == 0) {
					addFlag(x, 65536, false, y);
					addFlag(x + -1, 4096, false, y);
				}
				if (rotation == 1) {
					addFlag(x, 1024, false, y);
					addFlag(x, 16384, false, 1 + y);
				}
				if (rotation == 2) {
					addFlag(x, 4096, false, y);
					addFlag(x + 1, 65536, false, y);
				}
				if (rotation == 3) {
					addFlag(x, 16384, false, y);
					addFlag(x, 1024, false, y - 1);
				}
			}
			if (type == 1 || type == 3) {
				if (rotation == 0) {
					addFlag(x, 512, false, y);
					addFlag(-1 + x, 8192, false, y - -1);
				}
				if (rotation == 1) {
					addFlag(x, 2048, false, y);
					addFlag(x + 1, 32768, false, y - -1);
				}
				if (rotation == 2) {
					addFlag(x, 8192, false, y);
					addFlag(x - -1, 512, false, -1 + y);
				}
				if (rotation == 3) {
					addFlag(x, 32768, false, y);
					addFlag(x - 1, 2048, false, -1 + y);
				}
			}
			if (type == 2) {
				if (rotation == 0) {
					addFlag(x, 66560, false, y);
					addFlag(-1 + x, 4096, false, y);
					addFlag(x, 16384, false, 1 + y);
				}
				if (rotation == 1) {
					addFlag(x, 5120, false, y);
					addFlag(x, 16384, false, 1 + y);
					addFlag(x - -1, 65536, false, y);
				}
				if (rotation == 2) {
					addFlag(x, 20480, false, y);
					addFlag(x + 1, 65536, false, y);
					addFlag(x, 1024, false, y - 1);
				}
				if (rotation == 3) {
					addFlag(x, 81920, false, y);
					addFlag(x, 1024, false, -1 + y);
					addFlag(-1 + x, 4096, false, y);
				}
			}
		}
	}

	static {
		aBoolean1310 = false;
		aClass16_1314 = RSString.createString("Loading config )2 ");
		aClass16_1311 = aClass16_1314;
		aClass16_1301 = RSString.createString("Wir vermuten)1 dass Ihr Konto gestohlen wurde");
	}
}
