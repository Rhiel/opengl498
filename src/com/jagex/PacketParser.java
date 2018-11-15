package com.jagex;

import java.io.IOException;

import com.jagex.graphics.runetek4.media.Font;
import com.rs2.client.scene.Scene;

public class PacketParser {

	public static final int[] packet_sizes = new int[256];
	public static int currentOpcode;
	public static int[] anIntArray5213 = new int[32];
	public static int anInt2838 = 0;
	public static int[] c = new int[32];
	public static int b = 0;

	static {
		currentOpcode = 0;
		packet_sizes[0] = 0;
		packet_sizes[1] = 1;
		packet_sizes[2] = 0;
		packet_sizes[3] = 0;
		packet_sizes[4] = 0;
		packet_sizes[5] = 0;
		packet_sizes[6] = 7;
		packet_sizes[7] = -2;
		packet_sizes[8] = 0;
		packet_sizes[9] = 0;
		packet_sizes[10] = -1;
		packet_sizes[11] = -1;
		packet_sizes[12] = 0;
		packet_sizes[13] = 6;
		packet_sizes[14] = 0;
		packet_sizes[15] = 0;
		packet_sizes[16] = 7;
		packet_sizes[17] = 0;
		packet_sizes[18] = 0;
		packet_sizes[19] = 5;
		packet_sizes[20] = 0;
		packet_sizes[21] = 0;
		packet_sizes[22] = 0;
		packet_sizes[23] = 0;
		packet_sizes[24] = 4;
		packet_sizes[25] = 0;
		packet_sizes[26] = 0;
		packet_sizes[27] = 3;
		packet_sizes[28] = 6;
		packet_sizes[29] = -2;
		packet_sizes[30] = -2;
		packet_sizes[31] = 0;
		packet_sizes[32] = 0;
		packet_sizes[33] = 20;
		packet_sizes[34] = -2;
		packet_sizes[35] = 0;
		packet_sizes[36] = 0;
		packet_sizes[37] = 0;
		packet_sizes[38] = 0;
		packet_sizes[39] = 0;
		packet_sizes[40] = 0;
		packet_sizes[41] = 0;
		packet_sizes[42] = 0;
		packet_sizes[43] = 0;
		packet_sizes[44] = -2;
		packet_sizes[45] = 0;
		packet_sizes[46] = 0;
		packet_sizes[47] = 0;
		packet_sizes[48] = 0;
		packet_sizes[49] = 0;
		packet_sizes[50] = -2;
		packet_sizes[51] = 0;
		packet_sizes[52] = -2;
		packet_sizes[53] = 0;
		packet_sizes[54] = 0;
		packet_sizes[55] = 0;
		packet_sizes[56] = 0;
		packet_sizes[57] = 0;
		packet_sizes[58] = -2;
		packet_sizes[59] = 0;
		packet_sizes[60] = 4;
		packet_sizes[61] = 0;
		packet_sizes[62] = 0;
		packet_sizes[63] = 0;
		packet_sizes[64] = 8;
		packet_sizes[65] = 0;
		packet_sizes[66] = 0;
		packet_sizes[67] = 6;
		packet_sizes[68] = 0;
		packet_sizes[69] = 0;
		packet_sizes[70] = 0;
		packet_sizes[71] = 0;
		packet_sizes[72] = 0;
		packet_sizes[73] = 0;
		packet_sizes[74] = 14;
		packet_sizes[75] = 3;
		packet_sizes[76] = 10;
		packet_sizes[77] = 2;
		packet_sizes[78] = 0;
		packet_sizes[79] = 0;
		packet_sizes[80] = 0;
		packet_sizes[81] = -2;
		packet_sizes[82] = 2;
		packet_sizes[83] = 0;
		packet_sizes[84] = 0;
		packet_sizes[85] = 0;
		packet_sizes[86] = 0;
		packet_sizes[87] = -2;
		packet_sizes[88] = 0;
		packet_sizes[89] = -1;
		packet_sizes[90] = -1;
		packet_sizes[91] = 0;
		packet_sizes[92] = -1;
		packet_sizes[93] = 0;
		packet_sizes[94] = 0;
		packet_sizes[95] = 0;
		packet_sizes[96] = 4;
		packet_sizes[97] = 0;
		packet_sizes[98] = 0;
		packet_sizes[99] = -2;
		packet_sizes[100] = 0;
		packet_sizes[101] = 0;
		packet_sizes[102] = 0;
		packet_sizes[103] = 5;
		packet_sizes[104] = -2;
		packet_sizes[105] = 0;
		packet_sizes[106] = 0;
		packet_sizes[107] = 6;
		packet_sizes[108] = 0;
		packet_sizes[109] = 0;
		packet_sizes[110] = 3;
		packet_sizes[111] = 0;
		packet_sizes[112] = 10;
		packet_sizes[113] = 0;
		packet_sizes[114] = -1;
		packet_sizes[115] = 0;
		packet_sizes[116] = 0;
		packet_sizes[117] = 0;
		packet_sizes[118] = 2;
		packet_sizes[119] = 0;
		packet_sizes[120] = 0;
		packet_sizes[121] = 5;
		packet_sizes[122] = 4;
		packet_sizes[123] = 0;
		packet_sizes[124] = 6;
		packet_sizes[125] = 15;
		packet_sizes[126] = 4;
		packet_sizes[127] = 0;
		packet_sizes[128] = 0;
		packet_sizes[129] = 0;
		packet_sizes[130] = -1;
		packet_sizes[131] = 0;
		packet_sizes[132] = 5;
		packet_sizes[133] = 6;
		packet_sizes[134] = 3;
		packet_sizes[135] = 0;
		packet_sizes[136] = 0;
		packet_sizes[137] = 1;
		packet_sizes[138] = 5;
		packet_sizes[139] = 2;
		packet_sizes[140] = 0;
		packet_sizes[141] = 0;
		packet_sizes[142] = 0;
		packet_sizes[143] = 0;
		packet_sizes[144] = 0;
		packet_sizes[145] = 0;
		packet_sizes[146] = 8;
		packet_sizes[147] = 4;
		packet_sizes[148] = 0;
		packet_sizes[149] = 0;
		packet_sizes[150] = -2;
		packet_sizes[151] = 0;
		packet_sizes[152] = 0;
		packet_sizes[153] = 0;
		packet_sizes[154] = 0;
		packet_sizes[155] = 0;
		packet_sizes[156] = 0;
		packet_sizes[157] = 0;
		packet_sizes[158] = 0;
		packet_sizes[159] = -2;
		packet_sizes[160] = 4;
		packet_sizes[161] = 0;
		packet_sizes[162] = 0;
		packet_sizes[163] = 0;
		packet_sizes[164] = 0;
		packet_sizes[165] = 0;
		packet_sizes[166] = 0;
		packet_sizes[167] = 4;
		packet_sizes[168] = 10;
		packet_sizes[169] = 0;
		packet_sizes[170] = 0;
		packet_sizes[171] = 0;
		packet_sizes[172] = 2;
		packet_sizes[173] = 0;
		packet_sizes[174] = 2;
		packet_sizes[175] = 0;
		packet_sizes[176] = 0;
		packet_sizes[177] = -2;
		packet_sizes[178] = 12;
		packet_sizes[179] = 0;
		packet_sizes[180] = 0;
		packet_sizes[181] = 0;
		packet_sizes[182] = 9;
		packet_sizes[183] = -1;
		packet_sizes[184] = 0;
		packet_sizes[185] = 0;
		packet_sizes[186] = 0;
		packet_sizes[187] = 0;
		packet_sizes[188] = 15;
		packet_sizes[189] = 0;
		packet_sizes[190] = 0;
		packet_sizes[191] = -1;
		packet_sizes[192] = -1;
		packet_sizes[193] = 0;
		packet_sizes[194] = 0;
		packet_sizes[195] = 0;
		packet_sizes[196] = 0;
		packet_sizes[197] = 6;
		packet_sizes[198] = 7;
		packet_sizes[199] = 0;
		packet_sizes[200] = 0;
		packet_sizes[201] = 0;
		packet_sizes[202] = 8;
		packet_sizes[203] = 8;
		packet_sizes[204] = 0;
		packet_sizes[205] = 1;
		packet_sizes[206] = 0;
		packet_sizes[207] = 0;
		packet_sizes[208] = 0;
		packet_sizes[209] = 0;
		packet_sizes[210] = 0;
		packet_sizes[211] = 24;
		packet_sizes[212] = 5;
		packet_sizes[213] = -1;
		packet_sizes[214] = 6;
		packet_sizes[215] = 0;
		packet_sizes[216] = 0;
		packet_sizes[217] = 0;
		packet_sizes[218] = 0;
		packet_sizes[219] = 0;
		packet_sizes[220] = 0;
		packet_sizes[221] = 0;
		packet_sizes[222] = 3;
		packet_sizes[223] = 0;
		packet_sizes[224] = 0;
		packet_sizes[225] = 6;
		packet_sizes[226] = 0;
		packet_sizes[227] = 0;
		packet_sizes[228] = 4;
		packet_sizes[229] = 7;
		packet_sizes[230] = 6;
		packet_sizes[231] = 0;
		packet_sizes[232] = -1;
		packet_sizes[233] = 2;
		packet_sizes[234] = 0;
		packet_sizes[235] = 0;
		packet_sizes[236] = 0;
		packet_sizes[237] = 0;
		packet_sizes[238] = 5;
		packet_sizes[239] = 6;
		packet_sizes[240] = 0;
		packet_sizes[241] = 0;
		packet_sizes[242] = 0;
		packet_sizes[243] = 0;
		packet_sizes[244] = 0;
		packet_sizes[245] = 0;
		packet_sizes[246] = 3;
		packet_sizes[247] = -1;
		packet_sizes[248] = 6;
		packet_sizes[249] = 0;
		packet_sizes[250] = 6;
		packet_sizes[251] = 0;
		packet_sizes[252] = 0;
		packet_sizes[253] = 0;
		packet_sizes[254] = 0;
		packet_sizes[255] = 0;

	}

	static final boolean parseIncomingPacket(int i) throws IOException {
		if (ColourImageCacheSlot.session == null) {
			return false;
		}
		int available = ColourImageCacheSlot.session.available(-80);
		if (available == 0) {
			return false;
		}
		if (currentOpcode == -1) {
			available--;
			ColourImageCacheSlot.session.read(1, 0, StaticMethods2.packet.byteBuffer);
			StaticMethods2.packet.index = 0;
			currentOpcode = StaticMethods2.packet.getIncomingOpcode((byte) 47);
			StaticMethods.currentLength = packet_sizes[currentOpcode];
		}
		if (StaticMethods.currentLength == -1) {
			if (available <= 0) {
				return false;
			}
			ColourImageCacheSlot.session.read(1, 0, StaticMethods2.packet.byteBuffer);
			available--;
			StaticMethods.currentLength = StaticMethods2.packet.byteBuffer[0] & 0xff;
		}
		if ((StaticMethods.currentLength ^ 0xffffffff) == 1) {
			if (available <= 1) {
				return false;
			}
			available -= 2;
			ColourImageCacheSlot.session.read(2, 0, StaticMethods2.packet.byteBuffer);
			StaticMethods2.packet.index = 0;
			StaticMethods.currentLength = StaticMethods2.packet.g2();
		}
		if (available < StaticMethods.currentLength) {
			return false;
		}
		StaticMethods2.packet.index = 0;
		ColourImageCacheSlot.session.read(StaticMethods.currentLength, 0, StaticMethods2.packet.byteBuffer);
		DataBuffer.anInt992 = 0;
		Class25.anInt379 = Class25.anInt388;
		Class25.anInt388 = StaticMethods2.anInt666;
		StaticMethods2.anInt666 = currentOpcode;
		// System.out.println(currentOpcode + "," + StaticMethods.currentLength);
		if (currentOpcode == 225) { // Animate interface
			int i_1_ = StaticMethods2.packet.g4();
			int animationId = StaticMethods2.packet.getLEShortA(2);
			RSInterface inter = RSInterface.getInterface(i_1_);
			if ((animationId ^ 0xffffffff) != (inter.media_animid ^ 0xffffffff) || animationId == -1) {
				inter.media_tween_tick = 0;
				inter.media_animid = animationId;
				inter.media_current_frameid = 0;
				inter.media_next_frameid = 1;
				RSInterfaceList.setDirty(inter);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 205) { // Run energy
			GameClient.method36((byte) 102);
			SpotType.runEnergy = StaticMethods2.packet.g1();
			Class88.anInt1499 = SomeSoundClass.anInt3589;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 139) { // System update
			Queuable.systemUpdateTime = StaticMethods2.packet.getShortA() * 30;
			currentOpcode = -1;
			Class88.anInt1499 = SomeSoundClass.anInt3589;
			return true;
		}
		if (currentOpcode == 192) {
			// launch url
			byte[] bs = new byte[StaticMethods.currentLength];
			StaticMethods2.packet.xgdata(-122, StaticMethods.currentLength, 0, bs); // Reads all bytes with isaac cipher
			// System.out.println("Launching URL " + new String(bs));
			StaticMethods2.method834(Packet.bufferToString(bs, 0, StaticMethods.currentLength, 0), (byte) -110);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 112) { // Graphics packet
			int graphicId = StaticMethods2.packet.getLEShort();
			int delay = StaticMethods2.packet.g2();
			int target = StaticMethods2.packet.getIntB();
			int height = StaticMethods2.packet.getLEShort();
			if (target >> 30 == 0) {
				if (target >> 29 == 0) {
					if (target >> 28 != 0) {
						int index = target & 0xffff;
						Player player;
						if ((index ^ 0xffffffff) == (StaticMethods.thisPlayerIndex ^ 0xffffffff)) {
							player = GameClient.currentPlayer;
						} else {
							player = GameClient.localPlayers[index];
						}
						if (player != null) {
							player.current_spotanim_frameid = 0;
							player.anInt2647 = height;
							player.current_spotanimid = graphicId;
							player.current_spotanim_tick = 0;
							player.anInt2671 = GameClient.timer - -delay;
							player.next_spotanim_frameid = 1;
							if ((GameClient.timer ^ 0xffffffff) > (player.anInt2671 ^ 0xffffffff)) {
								player.current_spotanim_frameid = -1;
							}
							if ((player.current_spotanimid ^ 0xffffffff) == -65536) {
								player.current_spotanimid = -1;
							}
						}
					}
				} else {
					int index = 0xffff & target;
					NPC npc = GameClient.activeNPCs[index];
					if (npc != null) {
						npc.anInt2671 = delay + GameClient.timer;
						npc.current_spotanim_frameid = 0;
						npc.current_spotanimid = graphicId;
						if ((GameClient.timer ^ 0xffffffff) > (npc.anInt2671 ^ 0xffffffff)) {
							npc.current_spotanim_frameid = -1;
						}
						npc.current_spotanim_tick = 0;
						if ((npc.current_spotanimid ^ 0xffffffff) == -65536) {
							npc.current_spotanimid = -1;
						}
						npc.anInt2647 = height;
						npc.next_spotanim_frameid = 1;
					}
				}
			} else {
				int z = (0x398ba994 & target) >> 28;
				int y = -MapLoader.region_aboslute_x + (target & 0x3fff);
				int x = (target >> 14 & 0x3fff) + -MapLoader.region_aboslute_z;
				if (x >= 0 && (y ^ 0xffffffff) <= -1 && x < 104 && y < 104) {
					x = 128 * x - -64;
					y = 64 + y * 128;
					SpotEntity graphic = new SpotEntity(graphicId, z, x, y, Scene.get_average_height(z, x, y) - height, delay, GameClient.timer);
					SpotType.aClass89_4066.add_last(new PositionedGraphicNode(graphic));
				}
			}
			currentOpcode = -1;
			return true;
		}
		// Map packets
		if (currentOpcode == 125 || currentOpcode == 238 || currentOpcode == 198 || currentOpcode == 74 || currentOpcode == 16 || currentOpcode == 214 || currentOpcode == 188 || currentOpcode == 134 || currentOpcode == 138 || currentOpcode == 60 || currentOpcode == 77 || currentOpcode == 24) {
			Class35.parseMapPacket(-89);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 10) { // Change Walk-to option name
			if (StaticMethods.currentLength == 0) {
				StaticMethods.aClass16_3022 = TimeTools.aClass16_1599;
			} else {
				StaticMethods.aClass16_3022 = StaticMethods2.packet.gstr();
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 76) {
			int rotationY = StaticMethods2.packet.getLEShort();
			int interfaceHash = StaticMethods2.packet.getLEInt();
			int zoom = StaticMethods2.packet.getLEShort();
			int rotationX = StaticMethods2.packet.getLEShort();
			RSInterface rsInterface = RSInterface.getInterface(interfaceHash);
			if ((rotationY ^ 0xffffffff) != (rsInterface.media_xangle ^ 0xffffffff) || (rotationX ^ 0xffffffff) != (rsInterface.media_yangle ^ 0xffffffff) || (zoom ^ 0xffffffff) != (rsInterface.media_zoom ^ 0xffffffff)) {
				rsInterface.media_xangle = rotationY;
				rsInterface.media_yangle = rotationX;
				rsInterface.media_zoom = zoom;
				RSInterfaceList.setDirty(rsInterface);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 87) { // NPC rendering
			Class71_Sub1_Sub1.renderNPCs(i ^ 0x5152);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 229) { // Animate object (used for a single player).
			int animId = StaticMethods2.packet.getLEShort();
			if ((animId ^ 0xffffffff) == -65536) {
				animId = -1;
			}
			int locationHash = StaticMethods2.packet.getLEInt();
			int objectData = StaticMethods2.packet.getByteA();
			int plane = (0x36caa3aa & locationHash) >> 28;
			int x = locationHash >> 14 & 0x3fff;
			int type = objectData >> 2;
			int y = locationHash & 0x3fff;
			int rotation = 0x3 & objectData;
			y -= MapLoader.region_aboslute_x;
			x -= MapLoader.region_aboslute_z;
			int typeThing = Class75.anIntArray1375[type];
			ClanChatMember.animateObject(90, typeThing, x, y, animId, type, plane, rotation);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 213) {
			long name = StaticMethods2.packet.getLong();
			StaticMethods2.packet.g1s();
			long chatName = StaticMethods2.packet.getLong();
			long messageLength = StaticMethods2.packet.g2();
			long chatId = StaticMethods2.packet.g3();
			long friendId = chatId + (messageLength << 32);
			int modlevel_status = StaticMethods2.packet.g1();
			int ironman_status = StaticMethods2.packet.g1();
			int i_30_ = StaticMethods2.packet.g2();
			boolean ignore = false;
			while_25_: do {
				for (int index = 0; index < 100; index++) {
					if (friendId == SeekableFile.messageLog[index]) {
						ignore = true;
						break while_25_;
					}
				}
				if (modlevel_status <= 1) {
					for (int index = 0; (index ^ 0xffffffff) > (PlayerRelations.ignoreListSize ^ 0xffffffff); index++) {
						if (PlayerRelations.ignoreList[index] == name) {
							ignore = true;
							break;
						}
					}
				}
			} while (false);
			if (!ignore && StaticMethods.anInt3519 == 0) {
				SeekableFile.messageLog[Class47.friendIndex] = friendId;
				Class47.friendIndex = (Class47.friendIndex + 1) % 100;
				RSString class16 = NPC.getOtherQuickChat(i_30_, i + -20043).method752(StaticMethods2.packet, i + -20044);
				RSString nameString = RSString.joinRsStrings(new RSString[] { RankUtil.getIcon(modlevel_status, ironman_status), WallObject.getStringFromLong(-1, name).method154() });
				StaticMethods2.sendPublicChat(20, i_30_, class16, WallObject.getStringFromLong(i ^ ~0x4e4c, chatName).method154(), nameString, (byte) -103);
			}
			currentOpcode = -1;
			return true;
		}

		if (currentOpcode == 34) { // NPC related packet
			int i_33_ = StaticMethods.currentLength + StaticMethods2.packet.index;
			int windowPane = StaticMethods2.packet.g2();
			int length = StaticMethods2.packet.g2();
			if (GameClient.interface_top_id != windowPane) {
				GameClient.interface_top_id = windowPane;
				Stereo.method76(15532, GameClient.interface_top_id);
				RSInterfaceLayout.calc_layout();
				StaticMethods.method313(GameClient.interface_top_id, true);
				for (int i_36_ = 0; i_36_ < 100; i_36_++) {
					RSInterfaceList.is_dirty[i_36_] = true;
				}
			}
			while (length-- > 0) {
				int interfaceHash = StaticMethods2.packet.g4();
				int i_38_ = StaticMethods2.packet.g2();
				int i_39_ = StaticMethods2.packet.g1();
				InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(interfaceHash);
				if (class23_sub25 != null && (class23_sub25.interfaceId ^ 0xffffffff) != (i_38_ ^ 0xffffffff)) {
					GameShell.method27(true, class23_sub25, false);
					class23_sub25 = null;
				}
				if (class23_sub25 == null) {
					class23_sub25 = HashTable.get(i_39_, i_38_, interfaceHash, false);
				}
				class23_sub25.aBoolean2462 = true;
			}
			for (InterfaceNode inter = (InterfaceNode) Class36.anOa565.get_first(); inter != null; inter = (InterfaceNode) Class36.anOa565.get_next()) {
				if (inter.aBoolean2462) {
					inter.aBoolean2462 = false;
				} else {
					GameShell.method27(true, inter, false);
				}
			}
			Class47.anOa722 = new HashTable(512);
			while (StaticMethods2.packet.index < i_33_) {
				int i_40_ = StaticMethods2.packet.g4();
				int i_41_ = StaticMethods2.packet.g2();
				int i_42_ = StaticMethods2.packet.g2();
				int i_43_ = StaticMethods2.packet.g4();
				for (int i_44_ = i_41_; (i_42_ ^ 0xffffffff) <= (i_44_ ^ 0xffffffff); i_44_++) {
					long l = i_44_ + ((long) i_40_ << 32);
					Class47.anOa722.put(l, new IntegerNode(i_43_));
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 47) { // Clear minimap flag
			ComponentMinimap.flag_x = 0;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 99) { // String packet
			int interfaceHash = StaticMethods2.packet.getLEInt();
			RSString string = StaticMethods2.packet.gstr();
			RSInterface rsInterface = RSInterface.getInterface(interfaceHash);
			if (rsInterface != null && rsInterface.default_text != null) {
				if (!string.equals(rsInterface.default_text)) {
					rsInterface.default_text = string;
					RSInterfaceList.setDirty(rsInterface);
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 52) { // Parse chunk packets
			StaticMethods.regionChunkX = StaticMethods2.packet.g1();
			Js5.regionChunkY = StaticMethods2.packet.getByteC(-124);
			while (StaticMethods2.packet.index < StaticMethods.currentLength) {
				currentOpcode = StaticMethods2.packet.g1();
				Class35.parseMapPacket(66);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 13) { // Camera rotation packet.
			Camera.cameraViewChanged = true;
			StaticMethods2.anInt3868 = StaticMethods2.packet.g1();
			Varbit.anInt4006 = StaticMethods2.packet.g1();
			StaticMethods.anInt3300 = StaticMethods2.packet.g2();
			SomeSoundClass.anInt3625 = StaticMethods2.packet.g1();
			StaticMethods.anInt3396 = StaticMethods2.packet.g1();
			if (StaticMethods.anInt3396 >= 100) {
				int i_46_ = 64 + StaticMethods2.anInt3868 * 128;
				int i_47_ = Varbit.anInt4006 * 128 - -64;
				int i_48_ = Scene.get_average_height(ObjType.localHeight, i_46_, i_47_) + -StaticMethods.anInt3300;
				int i_49_ = -Camera.xCameraPos + i_46_;
				int i_50_ = i_47_ - Camera.yCameraPos;
				int i_51_ = i_48_ + -Camera.zCameraPos;
				int i_52_ = (int) Math.sqrt(i_49_ * i_49_ + i_50_ * i_50_);
				Camera.yCameraCurve = 0x7ff & (int) (325.949 * Math.atan2(i_51_, i_52_));
				Camera.xCameraCurve = (int) (Math.atan2(i_49_, i_50_) * -325.949) & 0x7ff;
				if (Camera.yCameraCurve < 128) {
					Camera.yCameraCurve = 128;
				}
				if (Camera.yCameraCurve > 383) {
					Camera.yCameraCurve = 383;
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 248) {
			int interfaceHash = StaticMethods2.packet.getLEInt();
			int i_54_ = StaticMethods2.packet.g2();
			int i_55_ = i_54_ >> 5 & 0x1f;
			int i_56_ = 0x1f & i_54_ >> 10;
			int i_57_ = 0x1f & i_54_;
			int i_58_ = (i_57_ << 3) + (i_55_ << 11) + (i_56_ << 19);
			RSInterface class64 = RSInterface.getInterface(interfaceHash);
			if ((i_58_ ^ 0xffffffff) != (class64.color ^ 0xffffffff)) {
				class64.color = i_58_;
				RSInterfaceList.setDirty(class64);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 183) { // Clan chat message
			long name = StaticMethods2.packet.getLong();
			StaticMethods2.packet.g1s();
			long clan_name = StaticMethods2.packet.getLong();
			long message_count = StaticMethods2.packet.g2();
			long message = StaticMethods2.packet.g3();
			long messageId = (message_count << 32) + message;
			int modlevel_status = StaticMethods2.packet.g1();
			int ironman_status = StaticMethods2.packet.g1();
			boolean bool = false;
			while_26_: do {
				for (int index = 0; index < 100; index++) {
					if (messageId == SeekableFile.messageLog[index]) {
						bool = true;
						break while_26_;
					}
				}
				if (modlevel_status <= 1) {
					if (Class67.anInt1176 == 1 || StaticMethods.anInt3075 == 1) {
						bool = true;
					} else {
						for (int index = 0; (PlayerRelations.ignoreListSize ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
							if ((PlayerRelations.ignoreList[index] ^ 0xffffffffffffffffL) == (name ^ 0xffffffffffffffffL)) {
								bool = true;
								break;
							}
						}
					}
				}
			} while (false);
			if (!bool && StaticMethods.anInt3519 == 0) {
				SeekableFile.messageLog[Class47.friendIndex] = messageId;
				Class47.friendIndex = (1 + Class47.friendIndex) % 100;
				RSString string = Font.escape_tags(ColourImageCacheSlot.method900(StaticMethods2.packet).method149());
				RSString nameString = RSString.joinRsStrings(new RSString[] { RankUtil.getIcon(modlevel_status, ironman_status), WallObject.getStringFromLong(-1, name).method154() });
				StaticMethods2.method1344(WallObject.getStringFromLong(-1, clan_name).method154(), string, 9, nameString, -1);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 239) { // Skill
			GameClient.method36((byte) 102);
			int experience = StaticMethods2.packet.getIntA(37);
			int skillId = StaticMethods2.packet.getByteS(i ^ 0x4e33);
			int level = StaticMethods2.packet.getByteA();
			PlayerAppearance.skillExperience[skillId] = experience;
			CacheFileWorker.skillLevels[skillId] = level;
			ReflectionRequest.anIntArray2482[skillId] = 1;
			for (int i_69_ = 0; i_69_ < (skillId == 24 ? 119 : 98); i_69_++) {
				if (experience >= Class36.anIntArray567[i_69_]) {
					ReflectionRequest.anIntArray2482[skillId] = i_69_ - -2;
				}
			}
			Class79.anIntArray1886[MathUtils.bitAnd(NodeDeque.anInt1524++, 31)] = skillId;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 246) { // Configuration (small value)
			int value = StaticMethods2.packet.getByteC0(-75);
			int configId = StaticMethods2.packet.getLEShortA0(125);
			HashTable.anIntArray1262[configId] = value;
			if ((GameClient.configs[configId] ^ 0xffffffff) != (value ^ 0xffffffff)) {
				GameClient.configs[configId] = value;
				Class71_Sub1_Sub1.method1276(configId);
			}
			CS2CallFrame.anIntArray780[MathUtils.bitAnd(31, Class71.anInt1279++)] = configId;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 228) { // Sets camera rotation (without locking)
			int i_72_ = StaticMethods2.packet.getLEShortA0(123);
			int i_73_ = StaticMethods2.packet.getLEShort();
			Camera.cameraRotationZ = i_73_;
			Class35.cameraDirection = i_72_;
			Huffman.method1576((byte) 93);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 44) { // Update scene graph
			MapLoader.decode_server_request(false);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 58) { // Run cs2 script
			RSString class16 = StaticMethods2.packet.gstr();
			Object[] objects = new Object[class16.length() - -1];
			for (int i_74_ = class16.length() + -1; (i_74_ ^ 0xffffffff) <= -1; i_74_--) {
				if (class16.charAt(i_74_) == 115) {
					objects[1 + i_74_] = StaticMethods2.packet.gstr();
				} else {
					objects[i_74_ + 1] = new Integer(StaticMethods2.packet.g4());
				}
			}
			objects[0] = new Integer(StaticMethods2.packet.g4());
			CS2Event script = new CS2Event();
			script.scriptArguments = objects;
			Class91.parseCS2Script(script, (byte) -103);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 114) {
			long playerLong = StaticMethods2.packet.getLong();
			int worldId = StaticMethods2.packet.g2();
			byte clanRight = StaticMethods2.packet.g1s();
			boolean validName = false;
			if ((~0x7fffffffffffffffL & playerLong ^ 0xffffffffffffffffL) != -1L) {
				validName = true;
			}
			if (validName) {
				if (DataBuffer.clanChatSize == 0) {
					currentOpcode = -1;
					return true;
				}
				playerLong &= 0x7fffffffffffffffL;
				int index;
				for (index = 0; DataBuffer.clanChatSize > index; index++) {
					if ((NameHashTable.currentClanChatUsers[index].uid ^ 0xffffffffffffffffL) == (playerLong ^ 0xffffffffffffffffL) && NameHashTable.currentClanChatUsers[index].userWorld == worldId) {
						break;
					}
				}
				if ((index ^ 0xffffffff) > (DataBuffer.clanChatSize ^ 0xffffffff)) {
					for (/**/; index < DataBuffer.clanChatSize + -1; index++) {
						NameHashTable.currentClanChatUsers[index] = NameHashTable.currentClanChatUsers[index - -1];
					}
					DataBuffer.clanChatSize--;
					NameHashTable.currentClanChatUsers[DataBuffer.clanChatSize] = null;
				}
			} else {
				RSString worldDisplay = StaticMethods2.packet.gstr();
				ClanChatMember member = new ClanChatMember();
				member.uid = playerLong;
				member.username = WallObject.getStringFromLong(-1, member.uid);
				member.clanRights = clanRight;
				member.worldString = worldDisplay;
				member.userWorld = worldId;
				int index;
				for (index = -1 + DataBuffer.clanChatSize; (index ^ 0xffffffff) <= -1; index--) {
					int i_79_ = NameHashTable.currentClanChatUsers[index].username.method151(member.username);
					if (i_79_ == 0) {
						NameHashTable.currentClanChatUsers[index].userWorld = worldId;
						NameHashTable.currentClanChatUsers[index].clanRights = clanRight;
						NameHashTable.currentClanChatUsers[index].worldString = worldDisplay;
						currentOpcode = -1;
						Class88.anInt1504 = SomeSoundClass.anInt3589;
						if (playerLong == Class88.currentUserLong) {
							StaticMethods.currentUserClanRights = clanRight;
						}
						return true;
					}
					if (i_79_ < 0) {
						break;
					}
				}
				if (DataBuffer.clanChatSize >= NameHashTable.currentClanChatUsers.length) {
					currentOpcode = -1;
					return true;
				}
				for (int index1 = -1 + DataBuffer.clanChatSize; (index1 ^ 0xffffffff) < (index1 ^ 0xffffffff); index1--) {
					NameHashTable.currentClanChatUsers[index1 + 1] = NameHashTable.currentClanChatUsers[index1];
				}
				if (DataBuffer.clanChatSize == 0) {
					NameHashTable.currentClanChatUsers = new ClanChatMember[100];
				}
				NameHashTable.currentClanChatUsers[1 + index] = member;
				DataBuffer.clanChatSize++;
				if (playerLong == Class88.currentUserLong) {
					StaticMethods.currentUserClanRights = clanRight;
				}
			}
			Class88.anInt1504 = SomeSoundClass.anInt3589;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 203) { // Useless?
			SomeSoundClass.aShort3603 = (short) StaticMethods2.packet.getLEShort();
			if ((SomeSoundClass.aShort3603 ^ 0xffffffff) >= -1) {
				SomeSoundClass.aShort3603 = (short) 1;
			}
			StaticMethods.aShort3456 = (short) StaticMethods2.packet.getLEShort();
			if ((StaticMethods.aShort3456 ^ 0xffffffff) < -1) {
				if ((SomeSoundClass.aShort3598 ^ 0xffffffff) < (StaticMethods.aShort3456 ^ 0xffffffff)) {
					StaticMethods.aShort3456 = SomeSoundClass.aShort3598;
				}
			} else {
				StaticMethods.aShort3456 = (short) 32767;
			}
			SomeSoundClass.aShort3598 = (short) StaticMethods2.packet.getLEShort();
			if ((SomeSoundClass.aShort3598 ^ 0xffffffff) >= -1) {
				SomeSoundClass.aShort3598 = (short) 1;
			}
			StaticMethods.aShort594 = (short) StaticMethods2.packet.getLEShortA0(123);
			currentOpcode = -1;
			if ((StaticMethods.aShort594 ^ 0xffffffff) >= -1) {
				StaticMethods.aShort594 = (short) 32767;
			} else if (SomeSoundClass.aShort3603 > StaticMethods.aShort594) {
				StaticMethods.aShort594 = SomeSoundClass.aShort3603;
			}
			return true;
		}
		if (currentOpcode == 90) {// Add "cookie"?
			CacheFileWorker.aClass16_2877 = StaticMethods2.packet.gstr();
			ReflectionAntiCheat.method51(CacheFileWorker.aClass16_2877, 0);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 197) {
			int i_81_ = StaticMethods2.packet.getLEShort();
			int interfaceHash = StaticMethods2.packet.getLEInt();
			RSInterface inter = RSInterface.getInterface(interfaceHash);
			if (inter != null && inter.type == 0) {
				if ((-inter.layout_height + inter.scroll_height ^ 0xffffffff) > (i_81_ ^ 0xffffffff)) {
					i_81_ = inter.scroll_height - inter.layout_height;
				}
				if ((i_81_ ^ 0xffffffff) > -1) {
					i_81_ = 0;
				}
				if ((i_81_ ^ 0xffffffff) != (inter.scroll_y ^ 0xffffffff)) {
					inter.scroll_y = i_81_;
					RSInterfaceList.setDirty(inter);
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 150) {// Sets Clan chat information
			Class88.anInt1504 = SomeSoundClass.anInt3589;
			long chatOwnerLong = StaticMethods2.packet.getLong();
			if ((chatOwnerLong ^ 0xffffffffffffffffL) == -1L) {
				currentOpcode = -1;
				NameHashTable.currentClanChatUsers = null;
				StaticMethods.chatOwnerName = null;
				StaticMethods.clanChatName = null;
				DataBuffer.clanChatSize = 0;
				return true;
			}
			long chatNameLong = StaticMethods2.packet.getLong();
			StaticMethods.clanChatName = WallObject.getStringFromLong(-1, chatNameLong);
			StaticMethods.chatOwnerName = WallObject.getStringFromLong(i + -20045, chatOwnerLong);
			StaticMethods2.chatKickRights = StaticMethods2.packet.g1s();
			int chatSize = StaticMethods2.packet.g1();
			if (chatSize == 255) {
				currentOpcode = -1;
				return true;
			}
			DataBuffer.clanChatSize = chatSize;
			ClanChatMember[] clanChatUsers = new ClanChatMember[100];
			for (int memberId = 0; (memberId ^ 0xffffffff) > (DataBuffer.clanChatSize ^ 0xffffffff); memberId++) {
				clanChatUsers[memberId] = new ClanChatMember();
				clanChatUsers[memberId].uid = StaticMethods2.packet.getLong();
				clanChatUsers[memberId].username = WallObject.getStringFromLong(-1, clanChatUsers[memberId].uid);
				clanChatUsers[memberId].userWorld = StaticMethods2.packet.g2();
				clanChatUsers[memberId].clanRights = StaticMethods2.packet.g1s();
				clanChatUsers[memberId].worldString = StaticMethods2.packet.gstr();
				if (Class88.currentUserLong == clanChatUsers[memberId].uid) {
					StaticMethods.currentUserClanRights = clanChatUsers[memberId].clanRights;
				}
			}
			boolean bool = false;
			int clanSize = DataBuffer.clanChatSize;
			while ((clanSize ^ 0xffffffff) < -1) {
				clanSize--;
				bool = true;
				for (int size = 0; size < clanSize; size++) {
					if ((clanChatUsers[size].username.method151(clanChatUsers[size + 1].username) ^ 0xffffffff) < -1) {
						bool = false;
						ClanChatMember nextPlayer = clanChatUsers[size];
						clanChatUsers[size] = clanChatUsers[size - -1];
						clanChatUsers[size + 1] = nextPlayer;
					}
				}
				if (bool) {
					break;
				}
			}
			currentOpcode = -1;
			NameHashTable.currentClanChatUsers = clanChatUsers;
			return true;
		}
		if (currentOpcode == 75) {
			LocResult.anInt3722 = StaticMethods2.packet.g1();
			Class87_Sub3.anInt2820 = StaticMethods2.packet.g1();
			NPC.anInt4368 = StaticMethods2.packet.g1();
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 39) { // Camera reset packet.
			Camera.cameraViewChanged = false;
			for (int i_88_ = 0; i_88_ < 5; i_88_++) {
				ISAACPacket.aBooleanArray3531[i_88_] = false;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 221) { // Reset all animations
			for (int i_89_ = 0; (GameClient.localPlayers.length ^ 0xffffffff) < (i_89_ ^ 0xffffffff); i_89_++) {
				if (GameClient.localPlayers[i_89_] != null) {
					GameClient.localPlayers[i_89_].current_performing_seqid = -1;
				}
			}
			for (int i_90_ = 0; (i_90_ ^ 0xffffffff) > (GameClient.activeNPCs.length ^ 0xffffffff); i_90_++) {
				if (GameClient.activeNPCs[i_90_] != null) {
					GameClient.activeNPCs[i_90_].current_performing_seqid = -1;
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 202) {
			int rotationY = StaticMethods2.packet.getLEShort();
			int rotationX = StaticMethods2.packet.getShortA();
			int interfaceHash = StaticMethods2.packet.getIntA(56);
			RSInterface inter = RSInterface.getInterface(interfaceHash);
			currentOpcode = -1;
			inter.anInt1090 = (rotationX << 16) + rotationY;
			return true;
		}
		if (currentOpcode == 6) { // Open interface
			int interfaceHash = StaticMethods2.packet.getIntB();
			int walkable = StaticMethods2.packet.getByteS(126);
			int interfaceId = StaticMethods2.packet.getLEShort();
			// System.out.println("Opening sub:" + interfaceId + " at: " + (interfaceHash >>> 16) + ":" + (interfaceHash & 0xffff));
			InterfaceNode interfaceNode = (InterfaceNode) Class36.anOa565.get(interfaceHash);
			try {
				if (interfaceNode != null) {
					GameShell.method27(interfaceNode.interfaceId != interfaceId, interfaceNode, false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			HashTable.get(walkable, interfaceId, interfaceHash, false);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 1) {// Change minimap status
			Huffman.anInt1819 = StaticMethods2.packet.g1();
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 102) {
			for (int index = 0; GameClient.configs.length > index; index++) {
				if ((GameClient.configs[index] ^ 0xffffffff) != (HashTable.anIntArray1262[index] ^ 0xffffffff)) {
					GameClient.configs[index] = HashTable.anIntArray1262[index];
					Class71_Sub1_Sub1.method1276(index);
					CS2CallFrame.anIntArray780[MathUtils.bitAnd(Class71.anInt1279++, 31)] = index;
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 81) { // Add ignores
			PlayerRelations.ignoreListSize = StaticMethods.currentLength / 8;
			for (int index = 0; (index ^ 0xffffffff) > (PlayerRelations.ignoreListSize ^ 0xffffffff); index++) {
				PlayerRelations.ignoreList[index] = StaticMethods2.packet.getLong();
				PlayerRelations.ignoreListNames[index] = WallObject.getStringFromLong(-1, PlayerRelations.ignoreList[index]);
			}
			currentOpcode = -1;
			Class75.anInt1372 = SomeSoundClass.anInt3589;
			return true;
		}
		if (currentOpcode == 33) { // Grand exchange offer progress
			int i_99_ = StaticMethods2.packet.g1();
			if (StaticMethods2.packet.g1() == 0) {
				GrandExchangeOffer.offers[i_99_] = new GrandExchangeOffer();
			} else {
				StaticMethods2.packet.index--;
				GrandExchangeOffer.offers[i_99_] = new GrandExchangeOffer(StaticMethods2.packet);
			}
			currentOpcode = -1;
			InteractiveEntity.anInt606 = SomeSoundClass.anInt3589;
			return true;
		}
		if (currentOpcode == 104) { // Send item overlayContainer
			int interfaceHash = StaticMethods2.packet.g4();
			int type = StaticMethods2.packet.g2();
			if (interfaceHash < -70000) {
				type += 32768;
			}
			RSInterface inter;
			if (interfaceHash < 0) {
				inter = null;
			} else {
				inter = RSInterface.getInterface(interfaceHash);
			}
			if (inter != null && inter.obj_ids != null) {
				for (int i_102_ = 0; i_102_ < inter.obj_ids.length; i_102_++) {
					inter.obj_ids[i_102_] = 0;
					inter.obj_counts[i_102_] = 0;
				}
			}
			ClientInventoryList.clear_inv(type, 1);
			int length = StaticMethods2.packet.g2();
			for (int index = 0; index < length; index++) {
				int itemId = StaticMethods2.packet.getLEShortA0(125);
				int amount = StaticMethods2.packet.g1();
				if (amount == 255) {
					amount = StaticMethods2.packet.g4();
				}
				if (inter != null && inter.obj_ids != null && inter.obj_ids.length > index) {
					inter.obj_ids[index] = itemId;
					inter.obj_counts[index] = amount;
				}
				ClientInventoryList.set_inv_slot(index, amount, itemId - 1, type, -44);
			}
			if (inter != null) {
				RSInterfaceList.setDirty(inter);
			}
			GameClient.method36((byte) 102);
			StaticMethods2.anIntArray1454[MathUtils.bitAnd(31, Class65.anInt1159++)] = MathUtils.bitAnd(32767, type);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 148) {
			if (GameClient.interface_top_id != -1) {
				StaticMethods2.method757(GameClient.interface_top_id, 0, false);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 67) { // Display model on interface
			int modelId = StaticMethods2.packet.getLEShortA0(123);
			int interfaceHash = StaticMethods2.packet.getLEInt();
			RSInterface class64 = RSInterface.getInterface(interfaceHash);
			if (modelId == 65535) {
				modelId = -1;
			}
			if (class64.media_type != 1 || (modelId ^ 0xffffffff) != (class64.media_id ^ 0xffffffff)) {
				class64.media_id = modelId;
				class64.media_type = 1;
				RSInterfaceList.setDirty(class64);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 19) { // Hide/Show interface child
			int interfaceHash = StaticMethods2.packet.getIntB();
			boolean bool = StaticMethods2.packet.g1() == 1;
			RSInterface class64 = RSInterface.getInterface(interfaceHash);
			if (!class64.hidden == bool) {
				class64.hidden = bool;
				class64.changed = true;
				RSInterfaceList.setDirty(class64);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 92) {
			long name = StaticMethods2.packet.getLong();
			int chatID = StaticMethods2.packet.g2();
			RSString class16 = NPC.getOtherQuickChat(chatID, 1).method752(StaticMethods2.packet, 0);
			StaticMethods2.sendPublicChat(19, chatID, class16, null, WallObject.getStringFromLong(-1, name).method154(), (byte) -108);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 174) { // Weight packet
			GameClient.method36((byte) 102);
			Class36.anInt569 = StaticMethods2.packet.g2s();
			Class88.anInt1499 = SomeSoundClass.anInt3589;
			String weightString = Class36.anInt569 + " kg";
			RSString string = Packet.bufferToString(weightString.getBytes(), 0, weightString.length(), 0);
			RSInterface rsinterface = RSInterface.getInterface(667 << 16 | 38);
			if (!string.equals(rsinterface.default_text)) {
				rsinterface.default_text = string;
				RSInterfaceList.setDirty(rsinterface);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 222) { // Windows pane
			int i_111_ = StaticMethods2.packet.g2();
			int i_112_ = StaticMethods2.packet.getByteS(127);
			if (i_112_ == 2) {
				StaticMethods.method834((byte) -94);
			}
			GameClient.interface_top_id = i_111_;
			Stereo.method76(15532, i_111_);
			RSInterfaceLayout.calc_layout();
			StaticMethods.method313(GameClient.interface_top_id, true);
			for (int i_114_ = 0; i_114_ < 100; i_114_++) {
				RSInterfaceList.is_dirty[i_114_] = true;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 107) { // Shake camera
			int i_115_ = StaticMethods2.packet.g1();
			int i_116_ = StaticMethods2.packet.g1();
			int i_117_ = StaticMethods2.packet.g1();
			int i_118_ = StaticMethods2.packet.g1();
			int i_119_ = StaticMethods2.packet.g2();
			ISAACPacket.aBooleanArray3531[i_115_] = true;
			StaticMethods.anIntArray2597[i_115_] = i_116_;
			Class79.anIntArray1890[i_115_] = i_117_;
			StaticMethods2.anIntArray2768[i_115_] = i_118_;
			StaticMethods.anIntArray3128[i_115_] = i_119_;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 50) {// Player rendering.
			GameClient.renderPlayers(i + -20044);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 182) { // Hint icon
			int i_120_ = StaticMethods2.packet.g1();
			int slot = i_120_ >> 6;
			HintIcon icon = new HintIcon();
			icon.targetType = 0x3f & i_120_;
			icon.arrowId = StaticMethods2.packet.g1();
			if ((icon.arrowId ^ 0xffffffff) <= -1 && icon.arrowId < StaticMethods2.hint_headicons.length) {
				if (icon.targetType == 1 || icon.targetType == 10) {
					icon.entityIndex = StaticMethods2.packet.g2();
					StaticMethods2.packet.index += 3;
				} else if (icon.targetType >= 2 && icon.targetType <= 6) {
					if (icon.targetType == 2) {
						icon.offsetX = 64;
						icon.offsetY = 64;
					}
					if (icon.targetType == 3) {
						icon.offsetX = 0;
						icon.offsetY = 64;
					}
					if (icon.targetType == 4) {
						icon.offsetY = 64;
						icon.offsetX = 128;
					}
					if (icon.targetType == 5) {
						icon.offsetX = 64;
						icon.offsetY = 0;
					}
					if (icon.targetType == 6) {
						icon.offsetX = 64;
						icon.offsetY = 128;
					}
					icon.targetType = 2;
					icon.targetX = StaticMethods2.packet.g2();
					icon.targetY = StaticMethods2.packet.g2();
					icon.height = StaticMethods2.packet.g1();
				}
				icon.modelId = StaticMethods2.packet.g2();
				if ((icon.modelId ^ 0xffffffff) == -65536) {
					icon.modelId = -1;
				}
				ReflectionRequest.currentHintIcons[slot] = icon;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 161) {
			for (int configId = 0; (configId ^ 0xffffffff) > (VarpDefinition.varpSize ^ 0xffffffff); configId++) {
				VarpDefinition def = VarpDefinition.getConfigDefinition(configId);
				if (def != null && def.configID == 0) {
					HashTable.anIntArray1262[configId] = 0;
					GameClient.configs[configId] = 0;
				}
			}
			GameClient.method36((byte) 102);
			Class71.anInt1279 += 32;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 168) { // Send item model on interface
			int itemId = StaticMethods2.packet.getLEShort();
			int amount = StaticMethods2.packet.getLEInt();
			if (itemId == 65535) {
				itemId = -1;
			}
			int interfaceHash = StaticMethods2.packet.g4();
			RSInterface class64 = RSInterface.getInterface(interfaceHash);
			if (!class64.newer_interface) {
				if ((itemId ^ 0xffffffff) == 0) {
					currentOpcode = -1;
					class64.media_type = 0;
					return true;
				}
				ObjType def = ObjTypeList.list(itemId);
				class64.media_zoom = 100 * def.modelZoom / amount;
				class64.media_yangle = def.modelRotation2;
				class64.media_type = 4;
				class64.media_id = itemId;
				class64.media_xangle = def.modelRotation1;
				RSInterfaceList.setDirty(class64);
			} else {
				class64.shownItemAmount = amount;
				class64.objid = itemId;
				ObjType def = ObjTypeList.list(itemId);
				class64.media_xangle = def.modelRotation1;
				class64.anInt258 = def.modelOffset1;
				class64.media_zangle = def.modelRotationZ;
				class64.anInt264 = def.modelOffset2;
				class64.media_zoom = def.modelZoom;
				if ((class64.media_viewport_width ^ 0xffffffff) < -1) {
					class64.media_zoom = class64.media_zoom * 32 / class64.media_viewport_width;
				} else if (class64.width > 0) {
					class64.media_zoom = class64.media_zoom * 32 / class64.width;
				}
				class64.media_yangle = def.modelRotation2;
				RSInterfaceList.setDirty(class64);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 11) {// Send public message
			long name = StaticMethods2.packet.getLong();
			RSString class16 = Font.escape_tags(ColourImageCacheSlot.method900(StaticMethods2.packet).method149());
			System.out.println("HERE");
			Class95.sendGameMessage(6, -1, class16, WallObject.getStringFromLong(-1, name).method154());
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 233) { // Set chunk offset
			Js5.regionChunkY = StaticMethods2.packet.g1();
			StaticMethods.regionChunkX = StaticMethods2.packet.getByteC(-122);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 96) { // Removes the items from an interface.
			int i_126_ = StaticMethods2.packet.g4();
			RSInterface inter = RSInterface.getInterface(i_126_);
			if (inter.obj_ids != null) {
				for (int i_127_ = 0; i_127_ < inter.obj_ids.length; i_127_++) {
					inter.obj_ids[i_127_] = -1;
					inter.obj_ids[i_127_] = 0;
				}
			}
			RSInterfaceList.setDirty(inter);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 230) {// Camera position
			Camera.cameraViewChanged = true;
			RSString.anInt1950 = StaticMethods2.packet.g1();
			StaticMethods.anInt3262 = StaticMethods2.packet.g1();
			Class28.anInt432 = StaticMethods2.packet.g2();
			CullingCluster.anInt913 = StaticMethods2.packet.g1();
			Class42.anInt660 = StaticMethods2.packet.g1();
			if (Class42.anInt660 >= 100) {
				Camera.xCameraPos = 64 + RSString.anInt1950 * 128;
				Camera.yCameraPos = 64 + 128 * StaticMethods.anInt3262;
				Camera.zCameraPos = Scene.get_average_height(ObjType.localHeight, Camera.xCameraPos, Camera.yCameraPos) - Class28.anInt432;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 124) { // Show NPC model on interface
			int npcId = StaticMethods2.packet.getLEShortA0(126);
			int interfaceHash = StaticMethods2.packet.getIntA(61);
			RSInterface class64 = RSInterface.getInterface(interfaceHash);
			if (npcId == 65535) {
				npcId = -1;
			}
			if (class64.media_type != 2 || npcId != class64.media_id) {
				class64.media_type = 2;
				class64.media_id = npcId;
				RSInterfaceList.setDirty(class64);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 207) { // Logout
			StaticMethods.method326(true);
			currentOpcode = -1;
			return false;
		}
		if (currentOpcode == 160) { // Useless?
			StaticMethods2.aShort1411 = (short) StaticMethods2.packet.g2();
			if ((StaticMethods2.aShort1411 ^ 0xffffffff) >= -1) {
				StaticMethods2.aShort1411 = (short) 320;
			}
			Class48.aShort752 = (short) StaticMethods2.packet.getLEShortA0(i + -19917);
			currentOpcode = -1;
			if (Class48.aShort752 <= 0) {
				Class48.aShort752 = (short) 256;
			}
			return true;
		}
		if (currentOpcode == 211) { // Set random.dat data
			Class71_Sub1_Sub1.method1271((byte) -126, StaticMethods2.packet);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 250) { // Configuration (large value)
			int value = StaticMethods2.packet.getLEInt();
			int configId = StaticMethods2.packet.getLEShortA0(i + -19919);
			HashTable.anIntArray1262[configId] = value;
			if (value != GameClient.configs[configId]) {
				GameClient.configs[configId] = value;
				Class71_Sub1_Sub1.method1276(configId);
			}
			CS2CallFrame.anIntArray780[MathUtils.bitAnd(Class71.anInt1279++, 31)] = configId;
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 27) {// new bottom configs small
			byte value = StaticMethods2.packet.g1s();
			int configId = StaticMethods2.packet.getLEShort();
			InterfaceUpdateQueue.insertNewConfig(value, (byte) 79, configId);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 28) {// new bottom configs large
			int value = StaticMethods2.packet.g4();
			int configId = StaticMethods2.packet.getLEShort();
			InterfaceUpdateQueue.insertNewConfig(value, (byte) 79, configId);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 29) {// new bottom configs small
			RSString value = StaticMethods2.packet.gjstr();
			int configId = StaticMethods2.packet.getLEShort();
			InterfaceUpdateQueue.insertGlobalStringPacketHash(value, configId, -89);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 30) {// new bottom configs large
			RSString value = StaticMethods2.packet.gjstr();
			int configId = StaticMethods2.packet.getLEShort();
			InterfaceUpdateQueue.insertGlobalStringPacketHash(value, configId, -89);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 137) {// Update friend server status
			StaticMethods.anInt1357 = StaticMethods2.packet.g1();
			currentOpcode = -1;
			Class75.anInt1372 = SomeSoundClass.anInt3589;
			return true;
		}
		if (currentOpcode == 212) { // Sound
			int sound = StaticMethods2.packet.g2();
			int volume = StaticMethods2.packet.g1();
			if (sound == 65535) {
				sound = -1;
			}
			int delay = StaticMethods2.packet.g2();
			Class21.addSoundStore1(-115, sound, 256, 255, volume, delay);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 122) {// ?
			int i_135_ = StaticMethods2.packet.getIntB();
			ObjType.aRequest_3944 = GameClient.gameSignlink.newSimpleThread(i + -20023, i_135_);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 82) {// Remove overlayContainer type?
			int i_136_ = StaticMethods2.packet.getLEShort();
			ClientInventoryList.delete(false, i_136_);
			StaticMethods2.anIntArray1454[MathUtils.bitAnd(31, Class65.anInt1159++)] = MathUtils.bitAnd(32767, i_136_);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 177) { // Dynamic scene graph
			MapLoader.decode_server_request(true);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 191) { // Add friend
			long nameLong = StaticMethods2.packet.getLong();
			int world = StaticMethods2.packet.g2();
			int rank = StaticMethods2.packet.g1();
			boolean bool = true;
			if (nameLong < 0L) {
				bool = false;
				nameLong &= 0x7fffffffffffffffL;
			}
			RSString world_name = StaticMethods.empty_string;
			if (world > 0) {
				world_name = StaticMethods2.packet.gstr();
			}
			RSString name = WallObject.getStringFromLong(-1, nameLong).method154();
			for (int index = 0; (Class45.friends_count ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
				if (NameHashTable.friends_uid[index] == nameLong) {
					if (world != Class23_Sub10_Sub3.friends_worldid[index]) {
						Class23_Sub10_Sub3.friends_worldid[index] = world;
						if (world > 0) {
							Class95.sendGameMessage(5, -1, RSString.joinRsStrings(new RSString[] { name, Class28.aClass16_431 }), StaticMethods.empty_string);
						}
						if (world == 0) {
							Class95.sendGameMessage(5, i + -20045, RSString.joinRsStrings(new RSString[] { name, Class36.aClass16_566 }), StaticMethods.empty_string);
						}
					}
					name = null;
					Class87_Sub3.friends_worldname[index] = world_name;
					Mouse.friends_rank[index] = rank;
					StaticMethods2.aBooleanArray1741[index] = bool;
					break;
				}
			}
			boolean bool_141_ = false;
			if (name != null && Class45.friends_count < 200) {
				NameHashTable.friends_uid[Class45.friends_count] = nameLong;
				StaticMethods.friends_name[Class45.friends_count] = name;
				Class23_Sub10_Sub3.friends_worldid[Class45.friends_count] = world;
				Class87_Sub3.friends_worldname[Class45.friends_count] = world_name;
				Mouse.friends_rank[Class45.friends_count] = rank;
				StaticMethods2.aBooleanArray1741[Class45.friends_count] = bool;
				Class45.friends_count++;
			}
			Class75.anInt1372 = SomeSoundClass.anInt3589;
			int size = Class45.friends_count;
			while ((size ^ 0xffffffff) < -1) {
				size--;
				bool_141_ = true;
				for (int index = 0; (index ^ 0xffffffff) > (size ^ 0xffffffff); index++) {
					if ((Class23_Sub10_Sub3.friends_worldid[index] ^ 0xffffffff) != (GameClient.getWorldId() ^ 0xffffffff) && GameClient.getWorldId() == Class23_Sub10_Sub3.friends_worldid[index - -1] || (Class23_Sub10_Sub3.friends_worldid[index] ^ 0xffffffff) == -1 && (Class23_Sub10_Sub3.friends_worldid[index - -1] ^ 0xffffffff) != -1) {
						bool_141_ = false;
						int i_144_ = Class23_Sub10_Sub3.friends_worldid[index];
						Class23_Sub10_Sub3.friends_worldid[index] = Class23_Sub10_Sub3.friends_worldid[index + 1];
						Class23_Sub10_Sub3.friends_worldid[index - -1] = i_144_;
						RSString class16_145_ = Class87_Sub3.friends_worldname[index];
						Class87_Sub3.friends_worldname[index] = Class87_Sub3.friends_worldname[1 + index];
						Class87_Sub3.friends_worldname[1 + index] = class16_145_;
						RSString class16_146_ = StaticMethods.friends_name[index];
						StaticMethods.friends_name[index] = StaticMethods.friends_name[1 + index];
						StaticMethods.friends_name[index - -1] = class16_146_;
						long l_147_ = NameHashTable.friends_uid[index];
						NameHashTable.friends_uid[index] = NameHashTable.friends_uid[1 + index];
						NameHashTable.friends_uid[1 + index] = l_147_;
						int i_148_ = Mouse.friends_rank[index];
						Mouse.friends_rank[index] = Mouse.friends_rank[index + 1];
						Mouse.friends_rank[index - -1] = i_148_;
						boolean bool_149_ = StaticMethods2.aBooleanArray1741[index];
						StaticMethods2.aBooleanArray1741[index] = StaticMethods2.aBooleanArray1741[1 + index];
						StaticMethods2.aBooleanArray1741[1 + index] = bool_149_;
					}
				}
				if (bool_141_) {
					break;
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 247) { // Player option.
			RSString class16 = StaticMethods2.packet.gstr();
			int i_150_ = StaticMethods2.packet.getByteS(126);
			int i_151_ = StaticMethods2.packet.getByteA();
			if (i_150_ >= 1 && i_150_ <= 8) {
				if (class16.equalsIgnoreCase(StaticMethods.aClass16_3378)) {
					class16 = null;
				}
				SpawnedObject.playerOptions[i_150_ - 1] = class16;
				Queuable.aBooleanArray2317[i_150_ + -1] = i_151_ == 0;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 89) { // Game message
			RSString message = StaticMethods2.packet.gstr();
			if (!message.method162(Class57.tradeRequest, 0)) {
				if (!message.method162(Class87_Sub1.challengeRequestMessage, 0)) {
					if (!message.method162(RSString.assistRequest, 0)) {
						if (!message.method162(CollisionMap.clanRequest, 0)) {
							if (!message.method162(Class47.aClass16_742, 0)) {
								if (!message.method162(ComponentCanvas.aClass16_47, 0)) {
									if (!message.method162(StaticMethods.aClass16_3275, 0)) {
										if (message.method162(ReflectionAntiCheat.aClass16_78, 0)) {
											boolean bool = false;
											RSString class16_152_ = message.substring(message.indexOf(StaticMethods2.requestSeperator), 0);
											long l = class16_152_.toUsernameLong();
											for (int i_153_ = 0; (i_153_ ^ 0xffffffff) > (PlayerRelations.ignoreListSize ^ 0xffffffff); i_153_++) {
												if (l == PlayerRelations.ignoreList[i_153_]) {
													bool = true;
													break;
												}
											}
											if (!bool && StaticMethods.anInt3519 == 0) {
												Class95.sendGameMessage(15, -1, StaticMethods.empty_string, class16_152_);
											}
										} else if (!message.method162(Class71_Sub1.aClass16_2728, 0)) {
											Class95.sendGameMessage(0, -1, message, StaticMethods.empty_string);
										} else {
											RSString class16_154_ = message.substring(message.indexOf(StaticMethods2.requestSeperator), 0);
											boolean bool = false;
											long l = class16_154_.toUsernameLong();
											for (int i_155_ = 0; (PlayerRelations.ignoreListSize ^ 0xffffffff) < (i_155_ ^ 0xffffffff); i_155_++) {
												if (l == PlayerRelations.ignoreList[i_155_]) {
													bool = true;
													break;
												}
											}
											if (!bool && StaticMethods.anInt3519 == 0) {
												Class95.sendGameMessage(16, i ^ ~0x4e4c, StaticMethods.empty_string, class16_154_);
											}
										}
									} else {
										RSString class16_156_ = message.substring(message.indexOf(StaticMethods2.requestSeperator), 0);
										long l = class16_156_.toUsernameLong();
										boolean bool = false;
										for (int i_157_ = 0; PlayerRelations.ignoreListSize > i_157_; i_157_++) {
											if (PlayerRelations.ignoreList[i_157_] == l) {
												bool = true;
												break;
											}
										}
										if (!bool && StaticMethods.anInt3519 == 0) {
											Class95.sendGameMessage(14, -1, StaticMethods.empty_string, class16_156_);
										}
									}
								} else {
									RSString class16_158_ = message.substring(message.indexOf(ComponentCanvas.aClass16_47), 0);
									if (StaticMethods.anInt3519 == 0) {
										Class95.sendGameMessage(13, -1, class16_158_, StaticMethods.empty_string);
									}
								}
							} else {
								RSString class16_159_ = message.substring(message.indexOf(Class47.aClass16_742), 0);
								if (StaticMethods.anInt3519 == 0) {
									Class95.sendGameMessage(12, -1, class16_159_, StaticMethods.empty_string);
								}
							}
						} else {
							RSString class16_160_ = message.substring(message.indexOf(CollisionMap.clanRequest), 0);
							Class95.sendGameMessage(11, -1, class16_160_, StaticMethods.empty_string);
						}
					} else {
						RSString username = message.substring(message.indexOf(StaticMethods2.requestSeperator), 0);
						long l = username.toUsernameLong();
						boolean bool = false;
						for (int i_162_ = 0; PlayerRelations.ignoreListSize > i_162_; i_162_++) {
							if ((l ^ 0xffffffffffffffffL) == (PlayerRelations.ignoreList[i_162_] ^ 0xffffffffffffffffL)) {
								bool = true;
								break;
							}
						}
						if (!bool && (StaticMethods.anInt3519 ^ 0xffffffff) == -1) {
							Class95.sendGameMessage(10, -1, StaticMethods.empty_string, username);
						}
					}
				} else {
					RSString username = message.substring(message.indexOf(StaticMethods2.requestSeperator), 0);
					long name = username.toUsernameLong();
					boolean ignored = false;
					for (int index = 0; index < PlayerRelations.ignoreListSize; index++) {
						if (name == PlayerRelations.ignoreList[index]) {
							ignored = true;
							break;
						}
					}
					if (!ignored && (StaticMethods.anInt3519 ^ 0xffffffff) == -1) {
						RSString str = message.substring(message.length() - 9, message.indexOf(StaticMethods2.requestSeperator) + 1);
						Class95.sendGameMessage(8, i ^ ~0x4e4c, str, username);
					}
				}
			} else {
				RSString class16_166_ = message.substring(message.indexOf(StaticMethods2.requestSeperator), 0);
				long l = class16_166_.toUsernameLong();
				boolean bool = false;
				for (int i_167_ = 0; (i_167_ ^ 0xffffffff) > (PlayerRelations.ignoreListSize ^ 0xffffffff); i_167_++) {
					if (l == PlayerRelations.ignoreList[i_167_]) {
						bool = true;
						break;
					}
				}
				if (!bool && StaticMethods.anInt3519 == 0) {
					Class95.sendGameMessage(4, -1, Varbit.aClass16_4009, class16_166_);
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 232) {// send public message (receiver).
			long name = StaticMethods2.packet.getLong();
			long some_value = StaticMethods2.packet.g2();
			long counter = StaticMethods2.packet.g3();
			int modlevel_status = StaticMethods2.packet.g1s();
			int ironman_status = StaticMethods2.packet.g1s();
			System.out.println(modlevel_status + "," + ironman_status);
			boolean isFriend = false;
			long messageId = counter + (some_value << 32);
			while_27_: do {
				for (int index = 0; index < 100; index++) {
					if (SeekableFile.messageLog[index] == messageId) {
						isFriend = true;
						break while_27_;
					}
				}
				if (modlevel_status <= 1) {
					if (Class67.anInt1176 == 1 || StaticMethods.anInt3075 == 1) {
						isFriend = true;
					} else {
						for (int index = 0; index < PlayerRelations.ignoreListSize; index++) {
							if ((PlayerRelations.ignoreList[index] ^ 0xffffffffffffffffL) == (name ^ 0xffffffffffffffffL)) {
								isFriend = true;
								break;
							}
						}
					}
				}
			} while (false);
			if (!isFriend && (StaticMethods.anInt3519 ^ 0xffffffff) == -1) {
				SeekableFile.messageLog[Class47.friendIndex] = messageId;
				Class47.friendIndex = (Class47.friendIndex + 1) % 100;
				RSString class16 = Font.escape_tags(ColourImageCacheSlot.method900(StaticMethods2.packet).method149());
				RSString nameString = RSString.joinRsStrings(new RSString[] { RankUtil.getIcon(modlevel_status, ironman_status), WallObject.getStringFromLong(-1, name).method154() });
				int value = 3;
				if (modlevel_status > 0 && modlevel_status < 3) {
					value = 7;
				}
				Class95.sendGameMessage(value, -1, class16, nameString);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 130) {
			long name = StaticMethods2.packet.getLong();
			long counter = StaticMethods2.packet.g2();
			long value = StaticMethods2.packet.g3();
			int modlevel_status = StaticMethods2.packet.g1();
			int ironman_status = StaticMethods2.packet.g1();
			int message = StaticMethods2.packet.g2();
			long messageId = (counter << 32) + value;
			boolean isFriend = false;
			while_28_: do {
				for (int index = 0; index < 100; index++) {
					if (messageId == SeekableFile.messageLog[index]) {
						isFriend = true;
						break while_28_;
					}
				}
				if (modlevel_status <= 1) {
					for (int index = 0; PlayerRelations.ignoreListSize > index; index++) {
						if (PlayerRelations.ignoreList[index] == name) {
							isFriend = true;
							break;
						}
					}
				}
			} while (false);

			if (!isFriend && StaticMethods.anInt3519 == 0) {
				SeekableFile.messageLog[Class47.friendIndex] = messageId;
				Class47.friendIndex = (Class47.friendIndex + 1) % 100;
				RSString class16 = NPC.getOtherQuickChat(message, 1).method752(StaticMethods2.packet, 0);
				RSString nameString = RSString.joinRsStrings(new RSString[] { RankUtil.getIcon(modlevel_status, ironman_status), WallObject.getStringFromLong(-1, name).method154() });
				StaticMethods2.sendPublicChat(18, message, class16, null, nameString, (byte) -106);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 167) {
			int i_181_ = StaticMethods2.packet.g4();
			InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(i_181_);
			if (class23_sub25 != null) {
				GameShell.method27(true, class23_sub25, false);
			}
			if (Varbit.aClass64_4007 != null) {
				RSInterfaceList.setDirty(Varbit.aClass64_4007);
				Varbit.aClass64_4007 = null;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 103) { // Animate NPC.
			int delay = StaticMethods2.packet.getByteA();
			int animId = StaticMethods2.packet.getShortA();
			int index = StaticMethods2.packet.getLEShort();
			NPC npc = GameClient.activeNPCs[index];
			if (npc != null) {
				Class57.method1191(animId, npc, delay, -1);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 64) { // Move child position packet
			int posY = StaticMethods2.packet.g2s();
			int interfaceData = StaticMethods2.packet.g4();
			int posX = StaticMethods2.packet.g2s();
			RSInterface class64 = RSInterface.getInterface(interfaceData);
			class64.h_pos_mode = (byte) 0;
			class64.layout_x = class64.positionX = posX;
			class64.layout_y = class64.positionY = posY;
			class64.v_pos_mode = (byte) 0;
			RSInterfaceList.setDirty(class64);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 147) { // Useless packet?
			Class87.aShort1484 = (short) StaticMethods2.packet.getLEShortA0(125);
			if (Class87.aShort1484 <= 0) {
				Class87.aShort1484 = (short) 256;
			}
			Class23_Sub13_Sub12.aShort3971 = (short) StaticMethods2.packet.getLEShort();
			if ((Class23_Sub13_Sub12.aShort3971 ^ 0xffffffff) >= -1) {
				Class23_Sub13_Sub12.aShort3971 = (short) 205;
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 126) { // Display player model on interface
			int childID = StaticMethods2.packet.getIntB();
			RSInterface child = RSInterface.getInterface(childID);
			child.media_type = 3;
			child.media_id = GameClient.currentPlayer.appearance.getMediaID();
			RSInterfaceList.setDirty(child);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 110) {// Update local player position without using player rendering packet.
			int setting = StaticMethods2.packet.getByteS(125);
			int localX = StaticMethods2.packet.g1();
			int localY = StaticMethods2.packet.getByteA();
			ObjType.localHeight = setting >> 1;
			GameClient.currentPlayer.updatePosition(localX, (byte) 9, (setting & 0x1) == 1, localY);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 159) { // Send items (using slots).
			int i_192_ = StaticMethods2.packet.g4();
			int i_193_ = StaticMethods2.packet.g2();
			if (i_192_ < -70000) {
				i_193_ += 32768;
			}
			RSInterface inter;
			if (i_192_ >= 0) {
				inter = RSInterface.getInterface(i_192_);
			} else {
				inter = null;
			}
			while (StaticMethods.currentLength > StaticMethods2.packet.index) {
				int index = StaticMethods2.packet.getSmart0();
				int itemId = StaticMethods2.packet.g2();
				int amount = 0;
				if ((itemId ^ 0xffffffff) != -1) {
					amount = StaticMethods2.packet.g1();
					if (amount == 255) {
						amount = StaticMethods2.packet.g4();
					}
				}
				if (inter != null && inter.obj_ids != null && (index ^ 0xffffffff) <= -1 && (inter.obj_ids.length ^ 0xffffffff) < (index ^ 0xffffffff)) {
					inter.obj_ids[index] = itemId;
					inter.obj_counts[index] = amount;
				}
				ClientInventoryList.set_inv_slot(index, amount, itemId - 1, i_193_, 68);
			}
			if (inter != null) {
				RSInterfaceList.setDirty(inter);
			}
			GameClient.method36((byte) 102);
			StaticMethods2.anIntArray1454[MathUtils.bitAnd(31, Class65.anInt1159++)] = MathUtils.bitAnd(32767, i_193_);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 146) {
			int interfaceHash = StaticMethods2.packet.g4();
			int secondInterfaceHash = StaticMethods2.packet.g4();
			InterfaceNode old_attachment = (InterfaceNode) Class36.anOa565.get(interfaceHash);
			InterfaceNode targ_attachment = (InterfaceNode) Class36.anOa565.get(secondInterfaceHash);
			if (targ_attachment != null) {
				GameShell.method27(old_attachment == null || old_attachment.interfaceId != targ_attachment.interfaceId, targ_attachment, false);
			}
			if (old_attachment != null) {
				old_attachment.unlink();
				Class36.anOa565.put(secondInterfaceHash, old_attachment);
			}
			RSInterface class64 = RSInterface.getInterface(interfaceHash);
			if (class64 != null) {
				RSInterfaceList.setDirty(class64);
			}
			class64 = RSInterface.getInterface(secondInterfaceHash);
			if (class64 != null) {
				RSInterfaceList.setDirty(class64);
				RSInterfaceLayout.calc_layout(class64, true);
			}
			if (GameClient.interface_top_id != -1) {
				StaticMethods2.method757(GameClient.interface_top_id, 1, false);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 118) { // Clears a region chunk.
			Js5.regionChunkY = StaticMethods2.packet.getByteA();
			StaticMethods.regionChunkX = StaticMethods2.packet.getByteA();
			int height = ObjType.localHeight;
			for (int localX = StaticMethods.regionChunkX; localX < 8 + StaticMethods.regionChunkX; localX++) {
				for (int localY = Js5.regionChunkY; localY < Js5.regionChunkY + 8; localY++) {
					if (StaticMethods2.groundItems[height][localX][localY] != null) {
						StaticMethods2.groundItems[height][localX][localY] = null;
						Class44.method1129(localX, localY, (byte) 119);
					}
				}
			}
			for (SpawnedObject spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_first(); spawnedObject != null; spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_next()) {
				if ((StaticMethods.regionChunkX ^ 0xffffffff) >= (spawnedObject.x ^ 0xffffffff) && spawnedObject.x < StaticMethods.regionChunkX - -8 && Js5.regionChunkY <= spawnedObject.y && (Js5.regionChunkY - -8 ^ 0xffffffff) < (spawnedObject.y ^ 0xffffffff) && ObjType.localHeight == spawnedObject.z) {
					spawnedObject.anInt2437 = 0;
				}
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 7) {
			ReflectionAntiCheat.decode_request(StaticMethods.currentLength, StaticMethods2.packet, 116, GameClient.gameSignlink);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 178) { // Access mask
			int interfaceHash = StaticMethods2.packet.getIntB();
			int id = StaticMethods2.packet.g4();
			int offset = StaticMethods2.packet.getLEShortA0(125);
			if ((offset ^ 0xffffffff) == -65536) {
				offset = -1;
			}
			int length = StaticMethods2.packet.getLEShort();
			if ((length ^ 0xffffffff) == -65536) {
				length = -1;
			}
			for (int idx = offset; (idx ^ 0xffffffff) >= (length ^ 0xffffffff); idx++) {
				long l = ((long) interfaceHash << 32) + idx;
				ServerActiveProperties parser = (ServerActiveProperties) Class47.anOa722.get(l);
				ServerActiveProperties node = null;
				if (parser != null) {
					node = new ServerActiveProperties(id, parser.anInt2444);
					parser.unlink();
				} else {
					if (idx != -1) {
						node = new ServerActiveProperties(id, -1);
					} else {
						node = new ServerActiveProperties(id, RSInterface.getInterface(interfaceHash).serverActiveProperties.anInt2444);
					}
				}
				Class47.anOa722.put(l, node);
			}
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 172) { // Music
			int musicId = StaticMethods2.packet.getShortA();
			if ((musicId ^ 0xffffffff) == -65536) {
				musicId = -1;
			}
			Class23_Sub13_Sub2.playMusic(musicId, (byte) 106);
			currentOpcode = -1;
			return true;
		}
		if (currentOpcode == 132) { // Music using second music cache (index 11)
			int useless = StaticMethods2.packet.putTriB(true);
			int musicId = StaticMethods2.packet.g2();
			if (musicId == 65535) {
				musicId = -1;
			}
			Class36.method990((byte) 106, useless, musicId);
			currentOpcode = -1;
			return true;
		}
		ForceMovement.sendError(95, null, "T1 - " + currentOpcode + "," + Class25.anInt388 + "," + Class25.anInt379 + " - " + StaticMethods.currentLength);
		StaticMethods.method326(true);
		return true;
	}
}
