package com.jagex;

public class VertexNormal {
	public static int anInt1560;
	public int x;
	public static int anInt1563;
	static RSString aClass16_1564 = RSString.createString(" (X");
	static RSString aClass16_1543;
	public int y;
	static RSInterface aClass64_1567;
	public int magnitude;
	public int z;

	static final void method1457(int i, RSString class16) {
		RSString class16_0_ = class16.method145().method154();
		boolean bool = false;
		for (int i_1_ = 0; (StaticMethods.anInt3067 ^ 0xffffffff) < (i_1_ ^ 0xffffffff); i_1_++) {
			Player class38_sub7_sub2 = GameClient.localPlayers[GameClient.localPlayerPointers[i_1_]];
			if (class38_sub7_sub2 != null && class38_sub7_sub2.username != null && class38_sub7_sub2.username.equalsIgnoreCase(class16_0_)) {
				bool = true;
				GameClient.walkPath(0, class38_sub7_sub2.waypointsY[0], 2, class38_sub7_sub2.waypointsX[0], false, false, 0, 0, GameClient.currentPlayer.waypointsX[0], 1, GameClient.currentPlayer.waypointsY[0], 1);
				if (i != 1) {
					if (i == 4) {
						GameClient.outBuffer.putOpcode(228);
						GameClient.outBuffer.putLEShortA(GameClient.localPlayerPointers[i_1_]);
					} else if (i != 6) {
						if (i == 7) {
							GameClient.outBuffer.putOpcode(52);
							GameClient.outBuffer.putLEShort(GameClient.localPlayerPointers[i_1_]);
						}
					} else {
						GameClient.outBuffer.putOpcode(245);
						GameClient.outBuffer.putShort(GameClient.localPlayerPointers[i_1_]);
					}
				} else {
					GameClient.outBuffer.putOpcode(185);
					GameClient.outBuffer.putLEShortA(GameClient.localPlayerPointers[i_1_]);
				}
				break;
			}
		}
		if (!bool) {
			Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { IoSession.aClass16_529, class16_0_ }), StaticMethods.empty_string);
		}
	}



	public VertexNormal() {
		/* empty */
	}

	public static void method1459(byte b) {
		aClass64_1567 = null;
		aClass16_1564 = null;
		if (b != -73) {
			anInt1563 = 51;
		}
	}

	VertexNormal(VertexNormal class92_22_) {
		x = class92_22_.x;
		magnitude = class92_22_.magnitude;
		z = class92_22_.z;
		y = class92_22_.y;
	}

	static {
		aClass16_1543 = Class42.aClass16_651;
		anInt1563 = 2;
	}
}
