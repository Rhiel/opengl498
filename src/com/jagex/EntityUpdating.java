package com.jagex;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.environment.SceneEnvironment;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

/**
 * Created by Chris on 5/26/2017.
 */
public class EntityUpdating {


    public static int localNPCCount = 0;
    public static int[] localNPCIndexes = new int[32768];

    public static final void renderLocalPlayer(int i) {
        StaticMethods2.packet.start_bitwise_access(i ^ i);
        int opcode = StaticMethods2.packet.getBits(1);
        if (opcode != 0) {
            int subOpcode = StaticMethods2.packet.getBits(2);
            if (subOpcode == 0) { //Update masks.
                TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = 2047;
            }
            else if (subOpcode == 1) {
                int walkDirection = StaticMethods2.packet.getBits(3);
                GameClient.currentPlayer.method1084(walkDirection, false, -30438);
                int updateMasks = StaticMethods2.packet.getBits(1);
                if (updateMasks == 1) {
                    TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = 2047;
                }
            }
            else if (subOpcode == 2) {
                int walkDirection = StaticMethods2.packet.getBits(3);
                GameClient.currentPlayer.method1084(walkDirection, true, -30438);
                int runDirection = StaticMethods2.packet.getBits(3);
                GameClient.currentPlayer.method1084(runDirection, true, -30438);
                int updateMasks = StaticMethods2.packet.getBits(1);
                if (updateMasks == 1) {
                    TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = 2047;
                }
            }
            else if (subOpcode == 3) {
                int teleporting = StaticMethods2.packet.getBits(1);
                int localX = StaticMethods2.packet.getBits(7);
                int updateMasks = StaticMethods2.packet.getBits(1);
                if (updateMasks == 1) {
                    TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = 2047;
                }
                ObjType.localHeight = StaticMethods2.packet.getBits(2);
                int localY = StaticMethods2.packet.getBits(7);
                GameClient.currentPlayer.updatePosition(localX, (byte) -126, teleporting == 1, localY);
            }
        }
    }

    public static final RSString getCombatColour(int playerLevel, byte dummy, int otherLevel) {
        int difference = playerLevel - otherLevel;
        if (dummy != -15) {
            StaticMethods.aClass16_2907 = null;
        }
        if (difference < -9) {
            return Class100.aClass16_1684;
        }
        if (difference < -6) {
            return Class91.aClass16_1552;
        }
        if (difference < -3) {
            return ClientInventory.aClass16_2371;
        }
        if (difference < 0) {
            return StaticMethods.aClass16_3388;
        }
        if (difference > 9) {
            return StaticMethods.aClass16_3458;
        }
        if (difference > 6) {
            return Class23_Sub10_Sub3.aClass16_3659;
        }
        if (difference > 3) {
            return Class42.aClass16_651;
        }
        if (difference > 0) {
            return StaticMethods2.aClass16_1739;
        }
        return Class23_Sub7.aClass16_2200;
    }

    public static final void setEntityActions(int i, int i_0_, int i_1_, int i_2_, byte b, int i_3_, int i_4_) {
        if ((NPC.anInt4374 ^ 0xffffffff) == -1 && !LocTypeList.aBoolean3792) {
			int i_5_ = Viewport.screen_left;
			int i_6_ = Viewport.screen_right;
			int i_7_ = Viewport.screen_top;
			int i_8_ = Viewport.screen_bottom;
            int i_9_ = (i_1_ + -i_2_) * (i_6_ + -i_5_) / i_3_ - -i_5_;
            int i_10_ = i_7_ + (i_8_ - i_7_) * (-i + i_4_) / i_0_;
            ContextMenu.setMenuActions(0L, (short) 5, i_9_, StaticMethods.empty_string, 500, StaticMethods.aClass16_3022, i_10_);
        }
        long l = -1L;
        for (int i_11_ = 0; (Scene.num_rendered ^ 0xffffffff) < (i_11_ ^ 0xffffffff); i_11_++) {
            long l_12_ = Scene.rendered_models_uid[i_11_];
            int i_13_ = 0x7f & (int) l_12_;
            int i_14_ = (0x3fd5 & (int) l_12_) >> 7;
            int i_15_ = 0x7fffffff & (int) (l_12_ >>> 32);
            int i_16_ = (int) l_12_ >> 29 & 0x3;
            if ((l_12_ ^ 0xffffffffffffffffL) != (l ^ 0xffffffffffffffffL)) {
                l = l_12_;
                if (i_16_ == 2 && SceneController.method298(ObjType.localHeight, i_13_, i_14_, l_12_)) {
                    LocType class23_sub13_sub7 = LocTypeList.list(i_15_);
                    if (class23_sub13_sub7.morphisms != null) {
                        class23_sub13_sub7 = class23_sub13_sub7.morph();
                    }
                    if (class23_sub13_sub7 == null) {
                        continue;
                    }
                    if (NPC.anInt4374 == 1) {
                        ContextMenu.setMenuActions(l_12_, (short) 14, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_4281, Class35.aClass16_556, class23_sub13_sub7.name}), 500, NPCType.aClass16_4163, i_14_);
                    } else if (LocTypeList.aBoolean3792) {
                        if ((0x4 & Class71_Sub1.anInt2725) == 4) {
                            ContextMenu.setMenuActions(l_12_, (short) 42, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_3714, Class35.aClass16_556, class23_sub13_sub7.name}), b + 463, Class49.aClass16_764, i_14_);
                        }
                    } else {
                        RSString[] class16s = class23_sub13_sub7.actions;
                        if (StaticMethods.aBoolean2977) {
                            class16s = StaticMethods.method396(b + 30076, class16s);
                        }
                        if (class16s != null) {
                            for (int i_17_ = 4; i_17_ >= 0; i_17_--) {
                                if (class16s[i_17_] != null) {
                                    short s = 0;
                                    if (i_17_ == 0) {
                                        s = (short) 29;
                                    }
                                    if (i_17_ == 1) {
                                        s = (short) 39;
                                    }
                                    if (i_17_ == 2) {
                                        s = (short) 37;
                                    }
                                    if (i_17_ == 3) {
                                        s = (short) 10;
                                    }
                                    if (i_17_ == 4) {
                                        s = (short) 1001;
                                    }
                                    ContextMenu.setMenuActions(l_12_, s, i_13_, RSString.joinRsStrings(new RSString[] { Class87_Sub2.aClass16_2805, class23_sub13_sub7.name}), 500, class16s[i_17_], i_14_);
                                }
                            }
                        }
                        ContextMenu.setMenuActions(class23_sub13_sub7.id, (short) 1005, i_13_, RSString.joinRsStrings(new RSString[] { Class87_Sub2.aClass16_2805, class23_sub13_sub7.name}), b + 463, Class71_Sub1_Sub1.aClass16_4462, i_14_);
                    }
                }
                if (i_16_ == 1) {
                    NPC class38_sub7_sub1 = GameClient.activeNPCs[i_15_];
                    if (class38_sub7_sub1.config.size == 1 && (0x7f & class38_sub7_sub1.bound_extents_x) == 64 && (class38_sub7_sub1.bound_extents_z & 0x7f) == 64) {
                        for (int i_18_ = 0; (localNPCCount ^ 0xffffffff) < (i_18_ ^ 0xffffffff); i_18_++) {
                            NPC class38_sub7_sub1_19_ = GameClient.activeNPCs[localNPCIndexes[i_18_]];
                            if (class38_sub7_sub1_19_ != null && class38_sub7_sub1_19_ != class38_sub7_sub1 && class38_sub7_sub1_19_.config.size == 1 && class38_sub7_sub1.bound_extents_x == class38_sub7_sub1_19_.bound_extents_x && class38_sub7_sub1_19_.bound_extents_z == class38_sub7_sub1.bound_extents_z) {
                                setEntityActions(i_13_, i_14_, class38_sub7_sub1_19_.config, true, localNPCIndexes[i_18_]);
                            }
                        }
						for (int i_20_ = 0; (StaticMethods.anInt3067 ^ 0xffffffff) < (i_20_ ^ 0xffffffff); i_20_++) {
                            Player class38_sub7_sub2 = GameClient.localPlayers[GameClient.localPlayerPointers[i_20_]];
                            if (class38_sub7_sub2 != null && (class38_sub7_sub2.bound_extents_x ^ 0xffffffff) == (class38_sub7_sub1.bound_extents_x ^ 0xffffffff) && (class38_sub7_sub1.bound_extents_z ^ 0xffffffff) == (class38_sub7_sub2.bound_extents_z ^ 0xffffffff)) {
                                Keyboard.method962(i_14_, i_13_, GameClient.localPlayerPointers[i_20_], (byte) 123, class38_sub7_sub2);
                            }
                        }
                    }
                    setEntityActions(i_13_, i_14_, class38_sub7_sub1.config, true, i_15_);
                }
                if (i_16_ == 0) {
                    Player class38_sub7_sub2 = GameClient.localPlayers[i_15_];
                    if ((0x7f & class38_sub7_sub2.bound_extents_x) == 64 && (class38_sub7_sub2.bound_extents_z & 0x7f) == 64) {
                        for (int i_21_ = 0; localNPCCount > i_21_; i_21_++) {
                            NPC class38_sub7_sub1 = GameClient.activeNPCs[localNPCIndexes[i_21_]];
                            if (class38_sub7_sub1 != null && class38_sub7_sub1.config.size == 1 && class38_sub7_sub2.bound_extents_x == class38_sub7_sub1.bound_extents_x && (class38_sub7_sub2.bound_extents_z ^ 0xffffffff) == (class38_sub7_sub1.bound_extents_z ^ 0xffffffff)) {
                                setEntityActions(i_13_, i_14_, class38_sub7_sub1.config, true, localNPCIndexes[i_21_]);
                            }
                        }
						for (int i_22_ = 0; (StaticMethods.anInt3067 ^ 0xffffffff) < (i_22_ ^ 0xffffffff); i_22_++) {
                            Player class38_sub7_sub2_23_ = GameClient.localPlayers[GameClient.localPlayerPointers[i_22_]];
                            if (class38_sub7_sub2_23_ != null && class38_sub7_sub2 != class38_sub7_sub2_23_ && (class38_sub7_sub2_23_.bound_extents_x ^ 0xffffffff) == (class38_sub7_sub2.bound_extents_x ^ 0xffffffff) && (class38_sub7_sub2_23_.bound_extents_z ^ 0xffffffff) == (class38_sub7_sub2.bound_extents_z ^ 0xffffffff)) {
                                Keyboard.method962(i_14_, i_13_, GameClient.localPlayerPointers[i_22_], (byte) -119, class38_sub7_sub2_23_);
                            }
                        }
                    }
                    Keyboard.method962(i_14_, i_13_, i_15_, (byte) 119, class38_sub7_sub2);
                }
                if (i_16_ == 3) {
                    NodeDeque class89 = StaticMethods2.groundItems[ObjType.localHeight][i_13_][i_14_];
                    if (class89 != null) {
                        for (GroundItemNode class23_sub13_sub1 = (GroundItemNode) class89.get_last(b ^ 0x55); class23_sub13_sub1 != null; class23_sub13_sub1 = (GroundItemNode) class89.get_prev((byte) 121)) {
                            int i_24_ = class23_sub13_sub1.groundItem.itemId;
                            ObjType class23_sub13_sub11 = ObjTypeList.list(i_24_);
                            if (NPC.anInt4374 != 1) {
                                if (!LocTypeList.aBoolean3792) {
                                    RSString[] class16s = class23_sub13_sub11.groundActions;
                                    if (StaticMethods.aBoolean2977) {
                                        class16s = StaticMethods.method396(30113, class16s);
                                    }
                                    for (int i_25_ = 4; (i_25_ ^ 0xffffffff) <= -1; i_25_--) {
                                        if (class16s != null && class16s[i_25_] != null) {
                                            short s = 0;
                                            if (i_25_ == 0) {
                                                s = (short) 17;
                                            }
                                            if (i_25_ == 1) {
                                                s = (short) 12;
                                            }
                                            if (i_25_ == 2) {
                                                s = (short) 46;
                                            }
                                            if (i_25_ == 3) {
                                                s = (short) 34;
                                            }
                                            if (i_25_ == 4) {
                                                s = (short) 33;
                                            }
                                            ContextMenu.setMenuActions(i_24_, s, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, class23_sub13_sub11.name }), 500, class16s[i_25_], i_14_);
                                        } else if (i_25_ == 2) {
                                            ContextMenu.setMenuActions(i_24_, (short) 46, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, class23_sub13_sub11.name }), b + 463, AbstractTimer.aClass16_307, i_14_);
                                        }
                                    }
                                    ContextMenu.setMenuActions(i_24_, (short) 1003, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, class23_sub13_sub11.name }), 500, Class71_Sub1_Sub1.aClass16_4462, i_14_);
                                } else if ((0x1 & Class71_Sub1.anInt2725) == 1) {
                                    ContextMenu.setMenuActions(i_24_, (short) 35, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_3714, InstrumentDefinition.aClass16_274, class23_sub13_sub11.name }), b ^ 0x1d1, Class49.aClass16_764, i_14_);
                                }
                            } else {
                                ContextMenu.setMenuActions(i_24_, (short) 43, i_13_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_4281, InstrumentDefinition.aClass16_274, class23_sub13_sub11.name }), 500, NPCType.aClass16_4163, i_14_);
                            }
                        }
                    }
                }
            }
        }
    }

    public static final void setEntityActions(int i, int i_26_, NPCType def, boolean bool, int i_27_) {
        if (ContextMenu.menuActionRow < 400) {
            if (def.morphisms != null) {
                def = def.getChildNPC();
            }
            if (def != null && def.clickable) {
                RSString class16 = def.name;
                if (def.combatLevel != 0) {
                    class16 = RSString.joinRsStrings(new RSString[] { class16, getCombatColour(GameClient.currentPlayer.combatLevel, (byte) -15, def.combatLevel), VertexNormal.aClass16_1564, ClanChatMember.aClass16_2378, RSString.valueOf(def.combatLevel), StaticMethods.aClass16_3048 });
                }
                if (NPC.anInt4374 != 1) {
                    if (!LocTypeList.aBoolean3792) {
                        RSString[] class16s = def.actions;
                        if (StaticMethods.aBoolean2977) {
                            class16s = StaticMethods.method396(30113, class16s);
                        }
                        if (class16s != null) {
                            for (int i_28_ = 4; i_28_ >= 0; i_28_--) {
                                if (class16s[i_28_] != null && (GameClient.getGameValue() != 0 || !class16s[i_28_].equalsIgnoreCase(Class31.aClass16_487))) {
                                    short s = 0;
                                    if ((i_28_ ^ 0xffffffff) == -1) {
                                        s = (short) 58;
                                    }
                                    if (i_28_ == 1) {
                                        s = (short) 45;
                                    }
                                    if (i_28_ == 2) {
                                        s = (short) 31;
                                    }
                                    if (i_28_ == 3) {
                                        s = (short) 1;
                                    }
                                    if (i_28_ == 4) {
                                        s = (short) 48;
                                    }
                                    ContextMenu.setMenuActions(i_27_, s, i, RSString.joinRsStrings(new RSString[] { Class23_Sub7.aClass16_2200, class16 }), 500, class16s[i_28_], i_26_);
                                }
                            }
                        }
                        if (GameClient.getGameValue() == 0 && class16s != null) {
                            for (int i_29_ = 4; (i_29_ ^ 0xffffffff) <= -1; i_29_--) {
                                if (class16s[i_29_] != null && class16s[i_29_].equalsIgnoreCase(Class31.aClass16_487)) {
                                    short s = 0;
                                    short s_30_ = 0;
                                    if ((i_29_ ^ 0xffffffff) == -1) {
                                        s_30_ = (short) 58;
                                    }
                                    if ((GameClient.currentPlayer.combatLevel ^ 0xffffffff) > (def.combatLevel ^ 0xffffffff)) {
                                        s = (short) 2000;
                                    }
                                    if (i_29_ == 1) {
                                        s_30_ = (short) 45;
                                    }
                                    if (i_29_ == 2) {
                                        s_30_ = (short) 31;
                                    }
                                    if (i_29_ == 3) {
                                        s_30_ = (short) 1;
                                    }
                                    if (i_29_ == 4) {
                                        s_30_ = (short) 48;
                                    }
                                    if (s_30_ != 0) {
                                        s_30_ += s;
                                    }
                                    ContextMenu.setMenuActions(i_27_, s_30_, i, RSString.joinRsStrings(new RSString[] { Class23_Sub7.aClass16_2200, class16 }), 500, class16s[i_29_], i_26_);
                                }
                            }
                        }
                        ContextMenu.setMenuActions(i_27_, (short) 1004, i, RSString.joinRsStrings(new RSString[] { Class23_Sub7.aClass16_2200, class16 }), 500, Class71_Sub1_Sub1.aClass16_4462, i_26_);
                    } else if ((Class71_Sub1.anInt2725 & 0x2) == 2) {
                        ContextMenu.setMenuActions(i_27_, (short) 21, i, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_3714, StaticMethods.aClass16_3248, class16 }), 500, Class49.aClass16_764, i_26_);
                    }
                } else {
                    ContextMenu.setMenuActions(i_27_, (short) 32, i, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_4281, StaticMethods.aClass16_3248, class16 }), 500, NPCType.aClass16_4163, i_26_);
                }
            }
        }
    }

    public static final int method826(RSString var0, int var1) {
        try {
            if(var0 != null) {
                for(int var2 = 0; Class45.friends_count > var2; ++var2) {
                    if(var0.equals(StaticMethods.friends_name[var2])) {
                        return var2;
                    }
                }

                return -1;
            } else {
                return -1;
            }
        } catch (RuntimeException var3) {
            throw var3;
        }
    }

	public static final void method342(int i, int i_17_, int i_18_, int i_19_, int i_20_, int i_21_, boolean checkBrightness) {
		if ((i_21_ ^ 0xffffffff) != (StaticMethods.anInt3279 ^ 0xffffffff) || RSInterface.anInt1138 != i_20_ || StaticMethods.player_height != i && !ClientOptions.is_removeroofs()) {
            RSInterface.anInt1138 = i_20_;
            StaticMethods.anInt3279 = i_21_;
            StaticMethods.player_height = i;
			if (ClientOptions.is_removeroofs()) {
                StaticMethods.player_height = 0;
            }
            GameClient.updateClientState(25);
            GameClient.drawLoadingText(RSInterface.aClass16_1139, true);
            int i_22_ = MapLoader.region_aboslute_z;
            int i_23_ = MapLoader.region_aboslute_x;
            MapLoader.region_aboslute_x = 8 * i_20_ - 48;
			MapLoader.region_aboslute_z = -48 + i_21_ * 8;
			WorldMap.current_player_area = WorldMap.get_selectable_area(8 * RSInterface.anInt1138, 8 * StaticMethods.anInt3279);
			WorldMap.current_player_labels = null;
			int xdiff = MapLoader.region_aboslute_x + -i_23_;
            i_23_ = MapLoader.region_aboslute_x;
			int zdiff = MapLoader.region_aboslute_z + -i_22_;
            i_22_ = MapLoader.region_aboslute_z;
            for (int i_26_ = 0; (i_26_ ^ 0xffffffff) > -32769; i_26_++) {
                NPC npc = GameClient.activeNPCs[i_26_];
                if (npc != null) {
                    for (int i_27_ = 0; i_27_ < 10; i_27_++) {
						npc.waypointsX[i_27_] -= zdiff;
						npc.waypointsY[i_27_] -= xdiff;
                    }
					npc.bound_extents_z -= 128 * xdiff;
					npc.bound_extents_x -= zdiff * 128;
                }
            }
            for (int i_28_ = 0; i_28_ < 2048; i_28_++) {
                Player player = GameClient.localPlayers[i_28_];
                if (player != null) {
                    for (int i_29_ = 0; i_29_ < 10; i_29_++) {
						player.waypointsX[i_29_] -= zdiff;
						player.waypointsY[i_29_] -= xdiff;
                    }
					player.bound_extents_x -= zdiff * 128;
					player.bound_extents_z -= 128 * xdiff;
                }
            }
            ObjType.localHeight = i;
            int i_30_ = 0;
            int i_31_ = 104;
            GameClient.currentPlayer.updatePosition(i_17_, (byte) -122, false, i_19_);
            int i_32_ = 1;
			if (zdiff < 0) {
                i_31_ = -1;
                i_30_ = 103;
                i_32_ = -1;
            }
            int i_33_ = 104;
            int i_34_ = 1;
            int i_35_ = 0;
			if ((xdiff ^ 0xffffffff) > -1) {
                i_34_ = -1;
                i_35_ = 103;
                i_33_ = -1;
            }
            for (int i_36_ = i_30_; i_36_ != i_31_; i_36_ += i_32_) {
                for (int i_37_ = i_35_; i_37_ != i_33_; i_37_ += i_34_) {
					int i_38_ = i_36_ + zdiff;
					int i_39_ = xdiff + i_37_;
                    for (int i_40_ = 0; i_40_ < 4; i_40_++) {
                        if (i_38_ >= 0 && i_39_ >= 0 && i_38_ < 104 && i_39_ < 104) {
                            StaticMethods2.groundItems[i_40_][i_36_][i_37_] = StaticMethods2.groundItems[i_40_][i_38_][i_39_];
                        } else {
                            StaticMethods2.groundItems[i_40_][i_36_][i_37_] = null;
                        }
                    }
                }
            }
            for (SpawnedObject spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_first(); spawnedObject != null; spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_next()) {
				spawnedObject.y -= xdiff;
				spawnedObject.x -= zdiff;
                if ((spawnedObject.x ^ 0xffffffff) > -1 || spawnedObject.y < 0 || spawnedObject.x >= 104 || spawnedObject.y >= 104) {
                    spawnedObject.unlink();
                }
            }
            Camera.cameraViewChanged = false;
            Class71_Sub3.soundStoreCount = 0;
            if (ComponentMinimap.flag_x != 0) {
				ComponentMinimap.flag_y -= xdiff;
				ComponentMinimap.flag_x -= zdiff;
            }
			if (GLManager.opengl_mode && checkBrightness && (Math.abs(xdiff) > 104 || 104 < Math.abs(zdiff))) {
				SceneEnvironment.update_brightness();
			}
            ComponentMinimap.last_rendered_level = -1;
            SpotType.aClass89_4066.clear();
            Class36.aClass89_562.clear();
        }
    }

    public static final void handleMinimapWalk(int i, int i_10_, int i_11_, RSInterface class64) {
        if (Huffman.anInt1819 == 0 || Huffman.anInt1819 == 3) {
            i -= class64.layout_height / 2;
            i_10_ -= class64.layout_width / 2;
            int i_12_ = Class35.cameraDirection - -StaticMethods.anInt3162 & 0x7ff;
			int i_13_ = Rasterizer.COSINE[i_12_];
            i_13_ = i_13_ * (Player.anInt4410 + 256) >> 8;
			int i_14_ = Rasterizer.SINE[i_12_];
            i_14_ = i_14_ * (Player.anInt4410 - -256) >> 8;
            int i_16_ = i * i_13_ + -(i_14_ * i_10_) >> 11;
            int i_17_ = i_14_ * i - -(i_13_ * i_10_) >> 11;
            int i_18_ = GameClient.currentPlayer.bound_extents_x - -i_17_ >> 7;
            int i_19_ = GameClient.currentPlayer.bound_extents_z + -i_16_ >> 7;
            boolean minimap = GameClient.walkPath(0, i_19_, 1, i_18_, true, false, 0, 0, GameClient.currentPlayer.waypointsX[0], 0, GameClient.currentPlayer.waypointsY[0], 0);
            if (minimap) {
                GameClient.outBuffer.p1(i_10_);
                GameClient.outBuffer.p1(i);
                GameClient.outBuffer.putShort(Class35.cameraDirection);
                GameClient.outBuffer.p1(57);
                GameClient.outBuffer.p1(StaticMethods.anInt3162);
                GameClient.outBuffer.p1(Player.anInt4410);
                GameClient.outBuffer.p1(89);
                GameClient.outBuffer.putShort(GameClient.currentPlayer.bound_extents_x);
                GameClient.outBuffer.putShort(GameClient.currentPlayer.bound_extents_z);
                GameClient.outBuffer.p1(StaticMethods2.anInt1417);
                GameClient.outBuffer.p1(63);
            }
        }
    }

    public static final void renderLocalPlayers(byte b) {
        int localCount = StaticMethods2.packet.getBits(8);
		if ((StaticMethods.anInt3067 ^ 0xffffffff) < (localCount ^ 0xffffffff)) {
			for (int i_1_ = localCount; (i_1_ ^ 0xffffffff) > (StaticMethods.anInt3067 ^ 0xffffffff); i_1_++) {
				Class54.removedEntityIndices[StaticMethods.removedEntities++] = GameClient.localPlayerPointers[i_1_];
			}
        }
		if (localCount > StaticMethods.anInt3067) {
			throw new RuntimeException("gppov1 - local:" + localCount + ", previous:" + StaticMethods.anInt3067 + ".");
        }
		StaticMethods.anInt3067 = 0;
        for (int i_2_ = 0; localCount > i_2_; i_2_++) {
            int index = GameClient.localPlayerPointers[i_2_];
            Player player = GameClient.localPlayers[index];
            int opcode = StaticMethods2.packet.getBits(1);
            if (opcode == 0) {
				GameClient.localPlayerPointers[StaticMethods.anInt3067++] = index;
                player.lastUpdate = GameClient.timer;
            } else {
                int subOpcode = StaticMethods2.packet.getBits(2);
                if (subOpcode == 0) {
                    //Update masks only.
					GameClient.localPlayerPointers[StaticMethods.anInt3067++] = index;
                    player.lastUpdate = GameClient.timer;
                    TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
                }
                else if (subOpcode == 1) {
					GameClient.localPlayerPointers[StaticMethods.anInt3067++] = index;
                    player.lastUpdate = GameClient.timer;
                    int i_6_ = StaticMethods2.packet.getBits(3);
                    player.method1084(i_6_, false, -30438);
                    int i_7_ = StaticMethods2.packet.getBits(1);
                    if (i_7_ == 1) {
                        TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
                    }
                }
                else if (subOpcode == 2) {
					GameClient.localPlayerPointers[StaticMethods.anInt3067++] = index;
                    player.lastUpdate = GameClient.timer;
                    int i_8_ = StaticMethods2.packet.getBits(3);
                    player.method1084(i_8_, true, b + -30427);
                    int i_9_ = StaticMethods2.packet.getBits(3);
                    player.method1084(i_9_, true, -30438);
                    int i_10_ = StaticMethods2.packet.getBits(1);
                    if (i_10_ == 1) {
                        TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
                    }
                }
                else if (subOpcode == 3) {
                    //Remove.
                    Class54.removedEntityIndices[StaticMethods.removedEntities++] = index;
                }
            }
        }
        if (b != -11) {
            Mouse.mouseClickY = 3;
        }
    }

    public static final void renderGlobalNPCs(boolean bool) {
        while (StaticMethods2.packet.bitdiff(1693770787, StaticMethods.currentLength) >= 27) {
            int index = StaticMethods2.packet.getBits(15);
            if (index == 32767) {
                break;
            }
            boolean bool_99_ = false;
            if (GameClient.activeNPCs[index] == null) {
                bool_99_ = true;
                GameClient.activeNPCs[index] = new NPC();
            }
            NPC npc = GameClient.activeNPCs[index];
            localNPCIndexes[localNPCCount++] = index;
            npc.lastUpdate = GameClient.timer;
            int direction = StaticMethods.anIntArray3097[StaticMethods2.packet.getBits(3)];
            if (bool_99_) {
                npc.faceDirection = npc.anInt2680 = direction;
            }
            int teleporting = StaticMethods2.packet.getBits(1);
            int flagUpdate = StaticMethods2.packet.getBits(1);
            if (flagUpdate == 1) {
                TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
            }
            int npcId = StaticMethods2.packet.getBits(14);
            npc.config = NPCType.getNPCDefinition(npcId);
            int offsetX = StaticMethods2.packet.getBits(5);
            int offsetY = StaticMethods2.packet.getBits(5);
            npc.config.headIcon = StaticMethods2.packet.getBits(5);
            npc.size = npc.config.size;
            npc.turn90cw = npc.config.turnCW;
            if (offsetX > 15) {
                offsetX -= 32;
            }
            npc.turn90ccw = npc.config.turnCounterCW;
            npc.index = npc.config.degreesToTurn;
            npc.turnAnimation = npc.config.turnAnimation;
            if (npc.index == 0) {
                npc.anInt2680 = 0;
            }
            npc.walkAnimation = npc.config.walkAnimationId;
            npc.alsoTurn = npc.config.alsoTurn;
            npc.standAnimation = npc.config.standAnimation;
            if (offsetY > 15) {
                offsetY -= 32;
            }
            npc.turn180 = npc.config.turn180Animation;
            npc.updatePosition(GameClient.currentPlayer.waypointsX[0] + offsetX, (byte) -109, teleporting == 1, offsetY + GameClient.currentPlayer.waypointsY[0]);
        }
        StaticMethods2.packet.end_bitwise_access(-65);
    }

    public static final void renderLocalNPCs(int dummy) {
        StaticMethods2.packet.start_bitwise_access(0);
        int localCount = StaticMethods2.packet.getBits(8);
        if (localCount < localNPCCount) {
            for (int i = localCount; i < localNPCCount; i++) {
                Class54.removedEntityIndices[StaticMethods.removedEntities++] = localNPCIndexes[i];
            }
        }
        if (localNPCCount < localCount) {
            throw new RuntimeException("Local count mismatch! " + localCount + ", " + localNPCCount);
        }
        localNPCCount = 0;
        int i = 0;
        if (dummy == 7028) {
            for (/**/; i < localCount; i++) {
                int index = localNPCIndexes[i];
                NPC npc = GameClient.activeNPCs[index];
                int opcode = StaticMethods2.packet.getBits(1);
                if (opcode == 0) { //Idle
                    localNPCIndexes[localNPCCount++] = index;
                    npc.lastUpdate = GameClient.timer;
                } else {
                    int subOpcode = StaticMethods2.packet.getBits(2);
                    switch (subOpcode) {
                        case 0: //Flag update only.
                            localNPCIndexes[localNPCCount++] = index;
                            npc.lastUpdate = GameClient.timer;
                            TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
                            break;
                        case 1: //Walking.
                            localNPCIndexes[localNPCCount++] = index;
                            npc.lastUpdate = GameClient.timer;
                            int direction = StaticMethods2.packet.getBits(3);
                            npc.method1084(direction, false, -30438);
                            int flagUpdate = StaticMethods2.packet.getBits(1);
                            if (flagUpdate == 1) {
                                TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
                            }
                            break;
                        case 2: //Running (? I thought 498 didn't have running npcs yet o.O..)
                            localNPCIndexes[localNPCCount++] = index;
                            npc.lastUpdate = GameClient.timer;
                            int walkDirection = StaticMethods2.packet.getBits(3);
                            npc.method1084(walkDirection, true, -30438);
                            int runDirection = StaticMethods2.packet.getBits(3);
                            npc.method1084(runDirection, true, -30438);
                            flagUpdate = StaticMethods2.packet.getBits(1);
                            if (flagUpdate == 1) {
                                TimeTools.maskUpdates[SoundEffects.updateMaskIndex++] = index;
                            }
                            break;
                        case 3: //Remove NPC.
                            Class54.removedEntityIndices[StaticMethods.removedEntities++] = index;
                            break;
                    }
                }
            }
        }
    }
}
