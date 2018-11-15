package com.jagex;

import com.jagex.graphics.runetek4.media.Font;

public class PlayerMasks {
	public static SoundStore[] soundStores = new SoundStore[50];
	public static int anInt905;
	static int anInt907;

	static final void parsePlayerUpdateMasks() {
		int index = 0;
		for (; (SoundEffects.updateMaskIndex ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
			int localPlayerIndex = TimeTools.maskUpdates[index];
			Player localPlayer = GameClient.localPlayers[localPlayerIndex];
			int mask = StaticMethods2.packet.g1();
			if ((mask & 0x8) != 0) {
				mask += StaticMethods2.packet.g1() << 8;
			}
			PlayerMasks.handlePlayerUpdateMasks(localPlayerIndex, localPlayer, mask);
		}
	}

	static final void handlePlayerUpdateMasks(int i, Player player, int mask) {
		if ((0x400 & mask) != 0) { // Ordinal 0
			// Graphics
			player.current_spotanimid = StaticMethods2.packet.getLEShort();
			int i_1_ = StaticMethods2.packet.getIntB();
			player.anInt2647 = i_1_ >> 16;
			if (player.current_spotanimid == 65535) {
				player.current_spotanimid = -1;
			}
			player.anInt2671 = (i_1_ & 0xffff) + GameClient.timer;
			player.current_spotanim_tick = 0;
			player.current_spotanim_frameid = 0;
			if ((player.anInt2671 ^ 0xffffffff) < (GameClient.timer ^ 0xffffffff)) {
				player.current_spotanim_frameid = -1;
			}
			player.next_spotanim_frameid = 1;
		}
		if ((0x40 & mask) != 0) { // Ordinal 1
			// Public chat
			int effect = StaticMethods2.packet.getLEShortA0(123);
			boolean quickChat = (effect & 0x8000) != 0;
			int modlevel_status = StaticMethods2.packet.g1();
			int ironman_status = StaticMethods2.packet.g1();
			int offset = quickChat ? 0 : StaticMethods2.packet.getByteS(125);
			int i_5_ = StaticMethods2.packet.index;
			if (player.username != null && player.appearance != null) {
				long l = player.username.toUsernameLong();
				boolean bool_7_ = false;
				if (modlevel_status != 2) {
					if (!quickChat && (Class67.anInt1176 == 1 || StaticMethods.anInt3075 == 1)) {
						bool_7_ = true;
					} else {
						for (int i_8_ = 0; PlayerRelations.ignoreListSize > i_8_; i_8_++) {
							if (PlayerRelations.ignoreList[i_8_] == l) {
								bool_7_ = true;
								break;
							}
						}
					}
				}
				if (!bool_7_ && (StaticMethods.anInt3519 ^ 0xffffffff) == -1) {
					int fileId = -1;
					if (!quickChat) {
						ColourImageCacheSlot.aClass23_Sub5_2445.index = 0;
						StaticMethods2.packet.method478(-1, 0, offset, ColourImageCacheSlot.aClass23_Sub5_2445.byteBuffer);
						ColourImageCacheSlot.aClass23_Sub5_2445.index = 0;
					}
					RSString chatMessage;
					if (quickChat) {
						effect &= 0x7fff;
						Class42 class42 = Class79.method1359(StaticMethods2.packet, (byte) 38);
						fileId = class42.chatFileID;
						chatMessage = class42.aClass23_Sub13_Sub12_646.method752(StaticMethods2.packet, 0);
					} else {
						chatMessage = Font.escape_tags(ColourImageCacheSlot.method900(ColourImageCacheSlot.aClass23_Sub5_2445).method149());
					}
					player.aClass16_2670 = chatMessage.method169();
					player.anInt2701 = 0xff & effect;
					player.anInt2713 = effect >> 8;
					player.anInt2639 = 150;
					RSString userDisplay = player.username;
					int value = 2;
					if (modlevel_status > 0) {
						if (modlevel_status < 3) {
							value = 1;
						}
						if (player.title != null) {
							userDisplay = RSString.joinRsStrings(new RSString[] { RankUtil.getIcon(modlevel_status, ironman_status), player.title, player.username });
						} else {
							userDisplay = RSString.joinRsStrings(new RSString[] { RankUtil.getIcon(modlevel_status, ironman_status), player.username });
						}
					} else {
						if (player.title != null) {
							userDisplay = RSString.joinRsStrings(new RSString[] { player.title, userDisplay });
						}
					}
					StaticMethods2.sendPublicChat(!quickChat ? value : 17, fileId, chatMessage, null, userDisplay, (byte) -117);
					// if (rights != 2) {
					// if (rights == 1) {
					// Deque.method1433(!bool_6_ ? 1 : 17, i_9_, class16, null, TextureOperation32.method322(new RSString[] {
					// Class33.modIcon, player.username }, -41), (byte) -119);
					// } else {
					// Deque.method1433(!bool_6_ ? 2 : 17, i_9_, class16, null, player.username, (byte) -117);
					// }
					// } else {
					// Deque.method1433(bool_6_ ? 17 : 1, i_9_, class16, null, TextureOperation32.method322(new RSString[] {
					// Class23_Sub7.adminIcon, player.username }, -74), (byte) -116);
					// }
				}
			}
			if (!quickChat) {
				StaticMethods2.packet.index = offset + i_5_;
			}
		}
		if ((mask & 0x80) != 0) { // Ordinal 2
			// Main hit
			int i_10_ = StaticMethods2.packet.getByteC(-112);
			int i_11_ = StaticMethods2.packet.getByteA();
			player.method1086(10, i_10_, i_11_, GameClient.timer);
			player.anInt2638 = GameClient.timer - -300;
			player.anInt2708 = StaticMethods2.packet.g1();
		}
		if ((0x10 & mask) != 0) { // Ordinal 3
			// Force chat
			player.aClass16_2670 = StaticMethods2.packet.gstr();
			if (player.aClass16_2670.charAt(0) == 126) {
				player.aClass16_2670 = player.aClass16_2670.substring(1);
				Class95.sendGameMessage(2, -1, player.aClass16_2670, player.username);
			} else if (GameClient.currentPlayer == player) {
				Class95.sendGameMessage(2, -1, player.aClass16_2670, player.username);
			}
			player.anInt2713 = 0;
			player.anInt2701 = 0;
			player.anInt2639 = 150;
		}
		if ((0x1 & mask) != 0) { // Ordinal 4
			// Animation
			int animationId = StaticMethods2.packet.g2();
			int delay = StaticMethods2.packet.getByteC(-111);
			if ((animationId ^ 0xffffffff) == -65536) {
				animationId = -1;
			}
			Player.setAnimation(0, delay, animationId, player);
		}
		if ((0x20 & mask) != 0) { // Ordinal 5
			// Face entity
			player.faceIndex = StaticMethods2.packet.getShortA();
			if ((player.faceIndex ^ 0xffffffff) == -65536) {
				player.faceIndex = -1;
			}
		}
		if ((0x4 & mask) != 0) { // Ordinal 6
			// Face location
			player.facingOffsetX = StaticMethods2.packet.getShortA();
			player.facingOffsetY = StaticMethods2.packet.g2();
		}
		if ((mask & 0x100) != 0) { // Ordinal 7
			// Force Movement
			player.forceStartX = StaticMethods2.packet.getByteA();
			player.forceStartY = StaticMethods2.packet.g1();
			player.forceEndX = StaticMethods2.packet.getByteC(-100);
			player.forceEndY = StaticMethods2.packet.getByteA();
			player.forceCommenceSpeed = StaticMethods2.packet.g2() + GameClient.timer;
			player.forcePathSpeed = StaticMethods2.packet.getShortA() + GameClient.timer;
			player.forceDirection = StaticMethods2.packet.getByteS(127);
			player.anInt2660 = 1;
			player.anInt2640 = 0;
		}
		if ((0x200 & mask) != 0) { // Ordinal 8
			// Supportive hit
			int i_14_ = StaticMethods2.packet.g1();
			int i_15_ = StaticMethods2.packet.g1();
			player.method1086(10, i_14_, i_15_, GameClient.timer);
		}
		if ((mask & 0x2) != 0) { // Ordinal 9
			// Appearance
			if (i != 0) {
				// System.out.println("Handling mask update " + i + ": " + maskData);
			}
			int length = StaticMethods2.packet.g1();
			byte[] bs = new byte[length];
			Packet buffer = new Packet(bs);
			StaticMethods2.packet.getBytesS(0, bs, length);
			NPC.aClass23_Sub5Array4377[i] = buffer;
			player.parseAppearance(12, buffer);
		}
		if ((mask & 0x800) != 0) {
			// TODO: worn items animation mask for players.
			int var4 = StaticMethods2.packet.g1();
			int[] var18 = new int[var4];
			int[] var17 = new int[var4];
			int[] var20 = new int[var4];
			for (int var22 = 0; ~var4 < ~var22; ++var22) {
				int var23 = StaticMethods2.packet.g2();
				if (var23 == 0xffff) {
					var23 = -1;
				}
				var18[var22] = var23;
				var17[var22] = StaticMethods2.packet.g1();
				var20[var22] = StaticMethods2.packet.g2();
			}
			Player.add_worn_obj_anim(player, var18, var20, var17);
		}
	}

	public static void destruct() {
		soundStores = null;
	}
}
