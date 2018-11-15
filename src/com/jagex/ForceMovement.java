package com.jagex;

import java.io.DataInputStream;
import java.net.URL;

public class ForceMovement {
	public static boolean throwCacheException = false;
	public static RSString aClass16_397 = RSString.createString("FULL");
	public static int[] anIntArray398 = { 1, 1, 0, 0, 0, 8, 0, 0, 8 };
	public static RSString aClass16_399 = RSString.createString("Lade Texturen )2 ");
	public static RSString worldFull = aClass16_397;
	public static RSString aClass16_402 = RSString.createString("<col=ffffff>");

	public static void method924(int i) {
		if (i >= -78) {
			anIntArray398 = null;
		}
		anIntArray398 = null;
		aClass16_402 = null;
		worldFull = null;
		aClass16_399 = null;
		aClass16_397 = null;
		CS2Runtime.int_stack = null;
	}

	static final void sendError(int i, Throwable throwable, String string) {
		do {
			try {
				String string_0_ = "";
				if (throwable != null) {
					string_0_ = ColourImageCache.printThrowable((byte) -74, throwable);
				}
				if (string != null) {
					if (throwable != null) {
						string_0_ += " | ";
					}
					string_0_ += string;
				}
				System.out.println("Error: " + string_0_);
				string_0_ = string_0_.replace(':', '.');
				string_0_ = string_0_.replace('@', (char) i);
				string_0_ = string_0_.replace('&', '_');
				string_0_ = string_0_.replace('#', '_');
				if (StaticMethods.aSignLink_3348.applet != null) {
					SignlinkRequest request = StaticMethods.aSignLink_3348.newURLThread(new URL(StaticMethods.aSignLink_3348.applet.getCodeBase(), "clienterror.ws?c=" + GameClient.getRevision() + "&u=" + Class88.currentUserLong + "&v1=" + GameShell.java_vendor + "&v2=" + GameShell.java_version + "&e=" + string_0_), 127);
					while (request.status == 0) {
						TimeTools.sleep(1L);
					}
					if (request.status != 1) {
						break;
					}
					DataInputStream datainputstream = (DataInputStream) request.result;
					datainputstream.read();
					datainputstream.close();
				}
			} catch (Exception exception) {
				break;
			}
			break;
		} while (false);
	}

	static final void handleForceMovement(Entity entity, int i) {
		if (i <= 1) {
			sendError(-35, null, null);
		}
		if ((GameClient.timer ^ 0xffffffff) == (entity.forcePathSpeed ^ 0xffffffff) || (entity.current_performing_seqid ^ 0xffffffff) == 0 || (entity.anInt2828 ^ 0xffffffff) != -1 || entity.current_performing_tick + 1 > SeqTypeList.list(entity.current_performing_seqid).frames_durations[entity.current_performing_frameid]) {
			int i_1_ = GameClient.timer - entity.forceCommenceSpeed;
			int i_2_ = entity.forcePathSpeed - entity.forceCommenceSpeed;
			int i_3_ = entity.forceStartX * 128 + entity.size * 64;
			int i_4_ = 64 * entity.size + entity.forceEndX * 128;
			int i_5_ = entity.size * 64 + entity.forceEndY * 128;
			entity.bound_extents_x = (i_1_ * i_4_ + (-i_1_ + i_2_) * i_3_) / i_2_;
			int i_6_ = entity.forceStartY * 128 - -(entity.size * 64);
			entity.bound_extents_z = (i_1_ * i_5_ + i_6_ * (i_2_ + -i_1_)) / i_2_;
		}
		if ((entity.forceDirection ^ 0xffffffff) == -1) {
			entity.faceDirection = 1024;
		}
		if (entity.forceDirection == 1) {
			entity.faceDirection = 1536;
		}
		entity.anInt2632 = 0;
		if (entity.forceDirection == 2) {
			entity.faceDirection = 0;
		}
		if (entity.forceDirection == 3) {
			entity.faceDirection = 512;
		}
		entity.anInt2680 = entity.faceDirection;
	}
}
