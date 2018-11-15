package com.jagex;

import java.util.Calendar;
import java.util.TimeZone;

public class Class79 implements Interface2 {
	public static int anInt1876;
	public static Calendar aCalendar1881;
	public static volatile int idleKeyTicks = 0;
	public static int[] anIntArray1886;
	public static RSString aClass16_1887;
	public static volatile int anInt1888;
	public static RSString aClass16_1889;
	public static int[] anIntArray1890;

	@Override
	public final RSString method8(int i, byte b, long l, int[] is) {
		if (i == 0) {
			EnumType class23_sub13_sub13 = EnumType.list(is[0]);
			return class23_sub13_sub13.getString((int) l);
		}
		if (i == 1) {
			ObjType class23_sub13_sub11 = ObjTypeList.list((int) l);
			return class23_sub13_sub11.name;
		}
		if (b != 56) {
			method1363(126);
		}
		if (i == 6) {
			return EnumType.list(is[0]).getString((int) l);
		}
		return null;
	}

	static final Class42 method1359(Packet buffer, byte b) {
		Class42 class42 = new Class42();
		class42.chatFileID = buffer.g2();
		if (b <= 8) {
			method1359(null, (byte) -29);
		}
		class42.aClass23_Sub13_Sub12_646 = NPC.getOtherQuickChat(class42.chatFileID, 1);
		return class42;
	}

	public static void method1360(boolean bool) {
		aCalendar1881 = null;
		CS2Runtime.str_stack = null;
		CS2Runtime.int_local_vars = null;
		if (bool != true) {
			CS2Runtime.str_stack = null;
		}
		anIntArray1886 = null;
		SpriteLoader.sprites_width = null;
		aClass16_1887 = null;
		anIntArray1890 = null;
		aClass16_1889 = null;
	}

	static final RSString method1361(int i, long l) {
		if (i != -16309) {
			method1361(99, -45L);
		}
		return CollisionMap.method1307(l, 10, (byte) 43, false);
	}

	static final boolean parsePackets(int i) {
		if (i != -51) {
			method1363(-29);
		}
		try {
			return PacketParser.parseIncomingPacket(20044);
		} catch (java.io.IOException ioexception) {
			Class44.method1128(-94);
			return true;
		} catch (Exception exception) {
			String string = "T2 - " + PacketParser.currentOpcode + "," + Class25.anInt388 + "," + Class25.anInt379 + " - " + StaticMethods.currentLength + "," + (MapLoader.region_aboslute_z + GameClient.currentPlayer.waypointsX[0]) + "," + (GameClient.currentPlayer.waypointsY[0] + MapLoader.region_aboslute_x) + " - ";
			for (int i_0_ = 0; (i_0_ ^ 0xffffffff) > (StaticMethods.currentLength ^ 0xffffffff) && i_0_ < 50; i_0_++) {
				string += StaticMethods2.packet.byteBuffer[i_0_] + ",";
			}
			ForceMovement.sendError(95, exception, string);
			StaticMethods.method326(true);
			return true;
		}
	}

	static final void method1363(int i) {
		for (int i_1_ = 0; i_1_ < EntityUpdating.localNPCCount; i_1_++) {
			int i_2_ = EntityUpdating.localNPCIndexes[i_1_];
			NPC class38_sub7_sub1 = GameClient.activeNPCs[i_2_];
			if (class38_sub7_sub1 != null && class38_sub7_sub1.config != null) {
				Queue.method928((byte) 84, class38_sub7_sub1, class38_sub7_sub1.config.size);
			}
		}
		if (i < 73) {
			aClass16_1889 = null;
		}
	}

	static {
		aCalendar1881 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		aClass16_1887 = RSString.createString(")4slr2)3ws?order=LPWM");
		aClass16_1889 = RSString.createString("blinken2:");
		anIntArray1886 = new int[32];
		anIntArray1890 = new int[5];
		anInt1888 = 0;
	}
}
