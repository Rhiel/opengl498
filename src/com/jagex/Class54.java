package com.jagex;

public class Class54 {
	public static CacheFileWorker[] aClass105_Sub1Array853;
	public static RSString aClass16_856;
	public static int[] removedEntityIndices = new int[1000];
	public static int[] anIntArray858;
	public static boolean[] aBooleanArray859;

	public static WallObject method1174(int i, int i_0_, int i_1_) {
		Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[i][i_0_][i_1_];
		if (class23_sub1 != null) {
			WallObject wall_object = class23_sub1.wall_object;
			class23_sub1.wall_object = null;
			return wall_object;
		}
		return null;
	}

	public static final void method1175(int i, int[][] is) {
		if (i != 4) {
			method1174(-31, 113, 86);
		}
		Class4.anIntArrayArray98 = is;
	}

	public static void method1176(byte b) {
		removedEntityIndices = null;
		anIntArray858 = null;
		aBooleanArray859 = null;
		aClass16_856 = null;
		aClass105_Sub1Array853 = null;
	}

	static final void method1177(int i, int i_3_, int i_4_, int i_5_, Entity class38_sub7, int i_6_) {
		BZIPContext.method1314(i, i_6_, i_3_, class38_sub7.bound_extents_x, i_5_, -16734, i_4_, class38_sub7.bound_extents_z);
	}

	static final RSString method1178(int i, RSInterface inter, RSString string) {
		if (i != 12445) {
			return null;
		}
		if ((string.indexOf(CacheFileWorker.aClass16_2867) ^ 0xffffffff) != 0) {
			for (;;) {
				int i_7_ = string.indexOf(Queue.aClass16_413);
				if ((i_7_ ^ 0xffffffff) == 0) {
					break;
				}
				if (GameClient.currentPlayer != null && GameClient.currentPlayer.summoning != 0 && new String(string.bytes).equals("Combat Lvl: %1")) {
					string = RSString.create("Combat Lvl: " + GameClient.currentPlayer.combatLevel + "+" + GameClient.currentPlayer.summoning);
					continue;
				}
				string = RSString.joinRsStrings(new RSString[] { string.substring(i_7_, 0), Class35.method981((byte) 103, GameTimer.method192(-26, 0, inter)), string.substring(i_7_ + 2) });
			}
			for (;;) {
				int i_8_ = string.indexOf(BZIPContext.aClass16_1360);
				if (i_8_ == -1) {
					break;
				}
				string = RSString.joinRsStrings(new RSString[] { string.substring(i_8_, 0), Class35.method981((byte) 103, GameTimer.method192(i ^ ~0x3084, 1, inter)), string.substring(i_8_ + 2) });
			}
			for (;;) {
				int i_9_ = string.indexOf(RSString.aClass16_1946);
				if ((i_9_ ^ 0xffffffff) == 0) {
					break;
				}
				string = RSString.joinRsStrings(new RSString[] { string.substring(i_9_, 0), Class35.method981((byte) 103, GameTimer.method192(i + -12471, 2, inter)), string.substring(2 + i_9_) });
			}
			for (;;) {
				int i_10_ = string.indexOf(StaticMethods.aClass16_3237);
				if (i_10_ == -1) {
					break;
				}
				string = RSString.joinRsStrings(new RSString[] { string.substring(i_10_, 0), Class35.method981((byte) 103, GameTimer.method192(-26, 3, inter)), string.substring(i_10_ + 2) });
			}
			for (;;) {
				int i_11_ = string.indexOf(Class49.aClass16_753);
				if ((i_11_ ^ 0xffffffff) == 0) {
					break;
				}
				string = RSString.joinRsStrings(new RSString[] { string.substring(i_11_, 0), Class35.method981((byte) 103, GameTimer.method192(-26, 4, inter)), string.substring(i_11_ + 2) });
			}
			for (;;) {
				int i_12_ = string.indexOf(QuickChatDefinition.aClass16_4035);
				if (i_12_ == -1) {
					break;
				}
				RSString class16_13_ = StaticMethods.empty_string;
				if (ObjType.aRequest_3944 != null) {
					class16_13_ = StaticMethods2.method1354(i ^ 0x3062, ObjType.aRequest_3944.priority);
					try {
						if (ObjType.aRequest_3944.result != null) {
							byte[] bs = ((String) ObjType.aRequest_3944.result).getBytes("ISO-8859-1");
							class16_13_ = Packet.bufferToString(bs, 0, bs.length, 0);
						}
					} catch (java.io.UnsupportedEncodingException unsupportedencodingexception) {
						/* empty */
					}
				}
				string = RSString.joinRsStrings(new RSString[] { string.substring(i_12_, 0), class16_13_, string.substring(i_12_ + 4) });
			}
		}
		return string;
	}

	static {
		aClass105_Sub1Array853 = new CacheFileWorker[256];
		anIntArray858 = new int[] { 0, 0, 2, 0, 0, 2, 1, 1, 0 };
		aClass16_856 = RSString.createString("::tele 0)1");
	}
}
