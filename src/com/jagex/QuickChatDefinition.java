package com.jagex;
/* QuickChatDefinition - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.components.worldmap.WorldMap;

public class QuickChatDefinition extends Queuable {
	public static Js5 globalQuickChatContainer;
	static MemoryCache quickChatMap;
	public static Js5 localQuickChatContainer;
	public int[] anIntArray4017;
	static RSString aClass16_4021 = RSString.createString("Ihr Spielkonto wurde deaktiviert)3");
	public int[] options;
	public int[] anIntArray4026;
	public int[] anIntArray4029;
	public RSString message;
	static RSString aClass16_4032;
	public static int anInt4034;
	static RSString aClass16_4035;

	public static void method769(int i) {
		aClass16_4032 = null;
		aClass16_4021 = null;
		aClass16_4035 = null;
	}

	static final void initializeQuickChatWorkers(Js5 globalQuickChatWorker, Js5 localQuickChatWorker, int i) {
		QuickChatDefinition.localQuickChatContainer = localQuickChatWorker;
		QuickChatDefinition.globalQuickChatContainer = globalQuickChatWorker;
	}

	final int method770(int i) {
		if (anIntArray4029 == null) {
			return -1;
		}
		for (int i_0_ = 0; i_0_ < anIntArray4029.length; i_0_++) {
			if (i == anIntArray4017[i_0_]) {
				return anIntArray4029[i_0_];
			}
		}
		return -1;
	}

	static final void method771(Js5 class105, byte b) {
		FloTypeList.configsJs5 = class105;
		WorldMap.amountFloors = FloTypeList.configsJs5.get_file_count(4);
	}

	public final void parseOpcodes(int opcode, Packet buffer) {
		if (opcode == 1) {
			message = buffer.gstr();
		} else if (opcode != 2) {
			if (opcode == 3) {
				int i_2_ = buffer.g1();
				anIntArray4029 = new int[i_2_];
				anIntArray4017 = new int[i_2_];
				for (int i_3_ = 0; i_2_ > i_3_; i_3_++) {
					anIntArray4029[i_3_] = buffer.g2();
					int i_4_ = buffer.g1();
					if (i_4_ != 0) {
						anIntArray4017[i_3_] = i_4_;
					} else {
						anIntArray4017[i_3_] = -1;
					}
				}
			}
		} else {
			int i_5_ = buffer.g1();
			options = new int[i_5_];
			anIntArray4026 = new int[i_5_];
			for (int i_6_ = 0; (i_5_ ^ 0xffffffff) < (i_6_ ^ 0xffffffff); i_6_++) {
				options[i_6_] = buffer.g2();
				int i_7_ = buffer.g1();
				if (i_7_ != 0) {
					anIntArray4026[i_6_] = i_7_;
				} else {
					anIntArray4026[i_6_] = -1;
				}
			}
		}
	}

	final int method774(int i, byte b) {
		if (options == null) {
			return -1;
		}
		for (int i_11_ = 0; (i_11_ ^ 0xffffffff) > (options.length ^ 0xffffffff); i_11_++) {
			if (i == anIntArray4026[i_11_]) {
				return options[i_11_];
			}
		}
		return -1;
	}

	static final QuickChatDefinition getQuickChat(int id) {
		QuickChatDefinition quickChatDefinition = (QuickChatDefinition) quickChatMap.get(id);
		if (quickChatDefinition != null) {
			return quickChatDefinition;
		}
		byte[] bs;
		if (id < 32768) {
			bs = localQuickChatContainer.get_file(0, id);
		} else {
			bs = globalQuickChatContainer.get_file(0, 0x7fff & id);
		}
		quickChatDefinition = new QuickChatDefinition();
		if (bs != null) {
			quickChatDefinition.readValueLoop(new Packet(bs));
		}
		quickChatMap.put(id, quickChatDefinition);
		return quickChatDefinition;
	}

	final void readValueLoop(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			parseOpcodes(opcode, buffer);
		}
	}

	static {
		aClass16_4032 = RSString.createString("Startseite auf (WSpielkonto wiederherstellen(W)3");
		aClass16_4035 = RSString.createString("(Udns");
		anInt4034 = 0;
		GameClient.setLanguage(0);
	}
}
