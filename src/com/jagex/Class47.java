package com.jagex;

import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.scene.Scene;

public class Class47 {
	public int anInt720;
	static RSString aClass16_721 = RSString.createString("Spieler kann nicht gefunden werden: ");
	static HashTable anOa722;
	static RSString aClass16_723;
	public int anInt724;
	public int anInt725;
	public int anInt728;
	public byte[] aByteArray729;
	public int anInt730;
	public byte[] aByteArray731;
	public int anInt732;
	public static RSString aClass16_734 = RSString.createString("Loaded input handler");
	public int anInt736;
	public static int friendIndex;
	public static int anInt741;
	static RSString aClass16_742;

	static final ServerActiveProperties getOptionMask(RSInterface inter, int i) {
		ServerActiveProperties integerNode = (ServerActiveProperties) anOa722.get(inter.componentIndex + ((long) inter.uid << 32));
		return integerNode != null ? integerNode : inter.serverActiveProperties;
	}

	public static final void handleItemSwitch(int i, int i_0_, int i_1_, int i_2_, RSInterface[] class64s, int i_3_, int i_4_, int i_5_, int i_6_) {
		if (i_5_ >= 69) {
			for (int i_7_ = 0; class64s.length > i_7_; i_7_++) {
				RSInterface inter = class64s[i_7_];
				if (inter != null && (!inter.newer_interface || inter.type == 0 || inter.has_hooks || getOptionMask(inter, 88).anInt2452 != 0 || inter == RSInterface.aClass64_1102 || inter.content_type == 1338) && (inter.parentId ^ 0xffffffff) == (i ^ 0xffffffff) && (!inter.newer_interface || !HintIcon.isHidden(true, inter))) {
					int comp_x = i_6_ + inter.layout_x;
					int comp_y = i_0_ + inter.layout_y;
					int i_10_;
					int i_11_;
					int i_12_;
					int i_13_;
					if (inter.type != 2) {
						i_11_ = i_4_ >= comp_y ? i_4_ : comp_y;
						i_10_ = (i_2_ ^ 0xffffffff) > (comp_x ^ 0xffffffff) ? comp_x : i_2_;
						int i_14_ = inter.layout_width + comp_x;
						int i_15_ = comp_y + inter.layout_height;
						if (inter.type == 9) {
							i_15_++;
							i_14_++;
						}
						i_13_ = i_3_ <= i_15_ ? i_3_ : i_15_;
						i_12_ = i_14_ < i_1_ ? i_14_ : i_1_;
					} else {
						i_10_ = i_2_;
						i_11_ = i_4_;
						i_12_ = i_1_;
						i_13_ = i_3_;
					}
					if (StaticMethods.fromInterface == inter) {
						StaticMethods.anInt3027 = comp_x;
						StaticMethods.aBoolean3516 = true;
						StaticMethods2.anInt1712 = comp_y;
					}
					if (!inter.newer_interface || i_12_ > i_10_ && (i_11_ ^ 0xffffffff) > (i_13_ ^ 0xffffffff)) {
						if (inter.type == 0) {
							if (!inter.newer_interface && HintIcon.isHidden(true, inter) && Class42.aClass64_663 != inter) {
								continue;
							}
							if (inter.noClickThrough && i_10_ <= Mouse.mouseX && (i_11_ ^ 0xffffffff) >= (Mouse.mouseY ^ 0xffffffff) && (i_12_ ^ 0xffffffff) < (Mouse.mouseX ^ 0xffffffff) && Mouse.mouseY < i_13_) {
								for (CS2Event class23_sub9 = (CS2Event) Class71_Sub2_Sub1.aClass89_4470.get_first(); class23_sub9 != null; class23_sub9 = (CS2Event) Class71_Sub2_Sub1.aClass89_4470.get_next()) {
									if (class23_sub9.hasMouseListener) {
										class23_sub9.unlink();
									}
								}
								for (CS2Event class23_sub9 = (CS2Event) GameTimer.aClass89_1999.get_first(); class23_sub9 != null; class23_sub9 = (CS2Event) GameTimer.aClass89_1999.get_next()) {
									if (class23_sub9.hasMouseListener) {
										class23_sub9.unlink();
									}
								}
								if ((AbstractTimer.anInt304 ^ 0xffffffff) == -1) {
									StaticMethods.fromInterface = null;
									RSInterface.aClass64_1102 = null;
								}
								WorldMap.anInt2475 = 0;
							}
						}
						if (inter.newer_interface) {
							boolean mouseClicked = false;
							boolean var25 = false;
							boolean mouse_over_comp;
							if ((Mouse.mouseX ^ 0xffffffff) <= (i_10_ ^ 0xffffffff) && Mouse.mouseY >= i_11_ && i_12_ > Mouse.mouseX && i_13_ > Mouse.mouseY) {
								mouse_over_comp = true;
							} else {
								mouse_over_comp = false;
							}
							if (mouse_over_comp && (inter.content_type == 1337 || inter.content_type == 1403) && Class48.anInt749 != 0) {
								ZoomHandler.zoom(Class48.anInt749);
							}
							if (Mouse.mouseClickState == 1 && (Mouse.mouseClickX ^ 0xffffffff) <= (i_10_ ^ 0xffffffff) && (i_11_ ^ 0xffffffff) >= (Mouse.mouseClickY ^ 0xffffffff) && i_12_ > Mouse.mouseClickX && Mouse.mouseClickY < i_13_) {
								mouseClicked = true;
							}
							if (SongUpdater.anInt175 == 1 && mouse_over_comp) {
								var25 = true;
							}
							if (mouseClicked) {
								StaticMethods.method273(inter, (byte) -102, -comp_y + Mouse.mouseClickY, -comp_x + Mouse.mouseClickX);
							}
							if (StaticMethods.fromInterface != null && inter != StaticMethods.fromInterface && mouse_over_comp && getOptionMask(inter, 57).method98()) {
								StaticMethods.withInterface = inter;
							}
							if (inter == RSInterface.aClass64_1102) {
								RSInterface.anInt1082 = comp_y;
								CollisionMap.aBoolean1310 = true;
								Class48.anInt751 = comp_x;
							}
							if (inter.has_hooks || (inter.content_type ^ 0xffffffff) != -1) {
								if (mouse_over_comp && Class48.anInt749 != 0 && inter.mouseWheelListener != null) {
									CS2Event script = new CS2Event();
									script.scriptArguments = inter.mouseWheelListener;
									script.mouseY = Class48.anInt749;
									script.component = inter;
									script.hasMouseListener = true;
									Class71_Sub2_Sub1.aClass89_4470.add_last(script);
								}
								if (StaticMethods.fromInterface != null || PlayerAppearance.aClass64_790 != null || ContextMenu.menuOpen || inter.content_type != 1400 && WorldMap.anInt2475 > 0) {
									var25 = false;
									mouse_over_comp = false;
									mouseClicked = false;
								}
								if (inter.content_type == 1337) {
									RSInterfaceList.setDirty(inter);
									continue;
								}
								if (inter.content_type == 1338) {
									if (mouseClicked) {
										EntityUpdating.handleMinimapWalk(Mouse.mouseClickY + -comp_y, Mouse.mouseClickX + -comp_x, -96, inter);
									}
									continue;
								}
								if (inter.content_type == 1400) {
									WorldMap.worldmap_component = inter;
									if (mouseClicked) {
										if (StaticMethods2.keysPressed[82] && GameClient.rights > 0) {
											int var19 = (int) ((Mouse.mouseClickX - comp_x - inter.layout_width / 2) * 2.0D / WorldMap.aFloat727);
											int var29 = (int) ((Mouse.mouseClickY - comp_y - inter.layout_height / 2) * 2.0D / WorldMap.aFloat727);
											int var21 = WorldMap.anInt3536 + var19;
											int var32 = WorldMap.anInt2251 + var29;
											int var23 = var21 + WorldMap.anInt3256;
											int var24 = WorldMap.anInt1460 - 1 - var32 + WorldMap.anInt65;
											handleTeleCommand(var23, var24, 0, (byte) -4);
											Packet.method444();
											continue;
										}

										WorldMap.anInt2475 = 1;
										QuickChatDefinition.anInt4034 = Mouse.mouseX;
										StaticMethods.anInt3059 = Mouse.mouseY;
										continue;
									}
									if (var25 && WorldMap.anInt2475 > 0) {
										if (WorldMap.anInt2475 == 1 && (QuickChatDefinition.anInt4034 != Mouse.mouseX || StaticMethods.anInt3059 != Mouse.mouseY)) {
											WorldMap.anInt4073 = WorldMap.anInt3536;
											WorldMap.anInt660 = WorldMap.anInt2251;
											WorldMap.anInt2475 = 2;
										}

										if (WorldMap.anInt2475 == 2) {
											WorldMap.method1175(WorldMap.anInt4073 + (int) ((QuickChatDefinition.anInt4034 - Mouse.mouseX) * 2.0D / WorldMap.aFloat3979), 112);
											WorldMap.method354(-126, WorldMap.anInt660 + (int) ((StaticMethods.anInt3059 - Mouse.mouseY) * 2.0D / WorldMap.aFloat3979));
										}
										continue;
									}
									WorldMap.anInt2475 = 0;
									continue;
								}
								if (inter.content_type == 1401) {
									if (var25) {
										WorldMap.method253(-22611, inter.layout_width, Mouse.mouseY - comp_y, Mouse.mouseX - comp_x, inter.layout_height);
									}
									continue;
								}
								if (!inter.aBoolean1047 && mouseClicked) {
									inter.aBoolean1047 = true;
									if (inter.mouse_pressed_handler != null) {
										CS2Event class23_sub9 = new CS2Event();
										class23_sub9.mouseX = Mouse.mouseClickX - comp_x;
										class23_sub9.scriptArguments = inter.mouse_pressed_handler;
										class23_sub9.hasMouseListener = true;
										class23_sub9.mouseY = Mouse.mouseClickY - comp_y;
										class23_sub9.component = inter;
										Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
									}
								}
								if (inter.aBoolean1047 && var25 && inter.mouse_dragged_handler != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.component = inter;
									class23_sub9.mouseX = Mouse.mouseX + -comp_x;
									class23_sub9.scriptArguments = inter.mouse_dragged_handler;
									class23_sub9.mouseY = Mouse.mouseY + -comp_y;
									class23_sub9.hasMouseListener = true;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if (inter.aBoolean1047 && !var25) {
									inter.aBoolean1047 = false;
									if (inter.mouse_released_handler != null) {
										CS2Event class23_sub9 = new CS2Event();
										class23_sub9.scriptArguments = inter.mouse_released_handler;
										class23_sub9.hasMouseListener = true;
										class23_sub9.mouseY = Mouse.mouseY - comp_y;
										class23_sub9.component = inter;
										class23_sub9.mouseX = -comp_x + Mouse.mouseX;
										GameTimer.aClass89_1999.add_last(class23_sub9);
									}
								}
								if (var25 && inter.mouse_drag_pass_handler != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.scriptArguments = inter.mouse_drag_pass_handler;
									class23_sub9.hasMouseListener = true;
									class23_sub9.mouseY = -comp_y + Mouse.mouseY;
									class23_sub9.component = inter;
									class23_sub9.mouseX = -comp_x + Mouse.mouseX;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if (!inter.mouse_over && mouse_over_comp) {
									inter.mouse_over = true;
									if (inter.mouse_enter_handler != null) {
										CS2Event event = new CS2Event();
										event.mouseX = -comp_x + Mouse.mouseX;
										event.mouseY = -comp_y + Mouse.mouseY;
										event.scriptArguments = inter.mouse_enter_handler;
										event.hasMouseListener = true;
										event.component = inter;
										Class71_Sub2_Sub1.aClass89_4470.add_last(event);
									}
								}
								if (inter.mouse_over && mouse_over_comp && inter.onMouseRepeatHook != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.scriptArguments = inter.onMouseRepeatHook;
									class23_sub9.mouseY = Mouse.mouseY + -comp_y;
									class23_sub9.mouseX = Mouse.mouseX - comp_x;
									class23_sub9.component = inter;
									class23_sub9.hasMouseListener = true;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if (inter.mouse_over && !mouse_over_comp) {
									inter.mouse_over = false;
									if (inter.mouse_exit_handler != null) {
										CS2Event class23_sub9 = new CS2Event();
										class23_sub9.scriptArguments = inter.mouse_exit_handler;
										class23_sub9.mouseY = -comp_y + Mouse.mouseY;
										class23_sub9.mouseX = Mouse.mouseX - comp_x;
										class23_sub9.component = inter;
										class23_sub9.hasMouseListener = true;
										GameTimer.aClass89_1999.add_last(class23_sub9);
									}
								}
								if (inter.mainLoopListener != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.component = inter;
									class23_sub9.scriptArguments = inter.mainLoopListener;
									HintIcon.aClass89_206.add_last(class23_sub9);
								}

								if (inter.anObjectArray161 != null && PacketParser.anInt2838 > inter.anInt284) {
									if (inter.anIntArray211 != null && PacketParser.anInt2838 - inter.anInt284 <= 32) {
										label531: for (int var19 = inter.anInt284; var19 < PacketParser.anInt2838; ++var19) {
											int var29 = PacketParser.anIntArray5213[var19 & 31];

											for (int var21 = 0; var21 < inter.anIntArray211.length; ++var21) {
												if (inter.anIntArray211[var21] == var29) {
													CS2Event var22 = new CS2Event();
													var22.component = inter;
													var22.scriptArguments = inter.anObjectArray161;
													Class71_Sub2_Sub1.aClass89_4470.add_last(var22);
													break label531;
												}
											}
										}
									} else {
										CS2Event var30 = new CS2Event();
										var30.component = inter;
										var30.scriptArguments = inter.anObjectArray161;
										Class71_Sub2_Sub1.aClass89_4470.add_last(var30);
									}

									inter.anInt284 = PacketParser.anInt2838;
								}

								if (inter.anObjectArray162 != null && PacketParser.b > inter.anInt285) {
									if (inter.anIntArray212 != null && PacketParser.b - inter.anInt285 <= 32) {
										label531: for (int var19 = inter.anInt285; var19 < PacketParser.b; ++var19) {
											int var29 = PacketParser.c[var19 & 31];

											for (int var21 = 0; var21 < inter.anIntArray212.length; ++var21) {
												if (inter.anIntArray212[var21] == var29) {
													CS2Event var23 = new CS2Event();
													var23.component = inter;
													var23.scriptArguments = inter.anObjectArray162;
													Class71_Sub2_Sub1.aClass89_4470.add_last(var23);
													break label531;
												}
											}
										}
									} else {
										CS2Event var30 = new CS2Event();
										var30.component = inter;
										var30.scriptArguments = inter.anObjectArray162;
										Class71_Sub2_Sub1.aClass89_4470.add_last(var30);
									}

									inter.anInt285 = PacketParser.b;
								}

								if (inter.varpUpdateListener != null && (inter.anInt1039 ^ 0xffffffff) > (Class71.anInt1279 ^ 0xffffffff)) {
									if (inter.varpListenerValues != null && Class71.anInt1279 - inter.anInt1039 <= 32) {
										while_54_: for (int i_20_ = inter.anInt1039; (i_20_ ^ 0xffffffff) > (Class71.anInt1279 ^ 0xffffffff); i_20_++) {
											int i_21_ = CS2CallFrame.anIntArray780[i_20_ & 0x1f];
											for (int i_22_ = 0; (inter.varpListenerValues.length ^ 0xffffffff) < (i_22_ ^ 0xffffffff); i_22_++) {
												if (inter.varpListenerValues[i_22_] == i_21_) {
													CS2Event class23_sub9 = new CS2Event();
													class23_sub9.component = inter;
													class23_sub9.scriptArguments = inter.varpUpdateListener;
													Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
													break while_54_;
												}
											}
										}
									} else {
										CS2Event class23_sub9 = new CS2Event();
										class23_sub9.scriptArguments = inter.varpUpdateListener;
										class23_sub9.component = inter;
										Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
									}
									inter.anInt1039 = Class71.anInt1279;
								}
								if (inter.inventoryUpdateListener != null && Class65.anInt1159 > inter.anInt1067) {
									if (inter.inventoryListenerValues == null || -inter.anInt1067 + Class65.anInt1159 > 32) {
										CS2Event class23_sub9 = new CS2Event();
										class23_sub9.scriptArguments = inter.inventoryUpdateListener;
										class23_sub9.component = inter;
										Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
									} else {
										while_55_: for (int i_23_ = inter.anInt1067; (Class65.anInt1159 ^ 0xffffffff) < (i_23_ ^ 0xffffffff); i_23_++) {
											int i_24_ = StaticMethods2.anIntArray1454[i_23_ & 0x1f];
											for (int i_25_ = 0; (inter.inventoryListenerValues.length ^ 0xffffffff) < (i_25_ ^ 0xffffffff); i_25_++) {
												if (inter.inventoryListenerValues[i_25_] == i_24_) {
													CS2Event class23_sub9 = new CS2Event();
													class23_sub9.component = inter;
													class23_sub9.scriptArguments = inter.inventoryUpdateListener;
													Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
													break while_55_;
												}
											}
										}
									}
									inter.anInt1067 = Class65.anInt1159;
								}
								if (inter.skillUpdateListener != null && inter.anInt1094 < NodeDeque.anInt1524) {
									if (inter.statTransmitList == null || -inter.anInt1094 + NodeDeque.anInt1524 > 32) {
										CS2Event script = new CS2Event();
										script.component = inter;
										script.scriptArguments = inter.skillUpdateListener;
										Class71_Sub2_Sub1.aClass89_4470.add_last(script);
									} else {
										while_56_: for (int i_26_ = inter.anInt1094; i_26_ < NodeDeque.anInt1524; i_26_++) {
											int i_27_ = Class79.anIntArray1886[0x1f & i_26_];
											for (int i_28_ = 0; i_28_ < inter.statTransmitList.length; i_28_++) {
												if (inter.statTransmitList[i_28_] == i_27_) {
													CS2Event class23_sub9 = new CS2Event();
													class23_sub9.scriptArguments = inter.skillUpdateListener;
													class23_sub9.component = inter;
													Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
													break while_56_;
												}
											}
										}
									}
									inter.anInt1094 = NodeDeque.anInt1524;
								}
								if ((inter.timestamp_last_process ^ 0xffffffff) > (StringNode.anInt2473 ^ 0xffffffff) && inter.chatbox_update_handler != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.component = inter;
									class23_sub9.scriptArguments = inter.chatbox_update_handler;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if ((inter.timestamp_last_process ^ 0xffffffff) > (Class75.anInt1372 ^ 0xffffffff) && inter.anObjectArray1104 != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.scriptArguments = inter.anObjectArray1104;
									class23_sub9.component = inter;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if (inter.timestamp_last_process < Class88.anInt1504 && inter.anObjectArray1137 != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.component = inter;
									class23_sub9.scriptArguments = inter.anObjectArray1137;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if (InteractiveEntity.anInt606 > inter.timestamp_last_process && inter.anObjectArray1092 != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.scriptArguments = inter.anObjectArray1092;
									class23_sub9.component = inter;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								if (inter.timestamp_last_process < Class88.anInt1499 && inter.update_timer_change_handler != null) {
									CS2Event class23_sub9 = new CS2Event();
									class23_sub9.scriptArguments = inter.update_timer_change_handler;
									class23_sub9.component = inter;
									Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
								}
								inter.timestamp_last_process = SomeSoundClass.anInt3589;
								if (inter.key_press_handler != null) {
									for (int i_29_ = 0; (i_29_ ^ 0xffffffff) > (Entity.anInt2649 ^ 0xffffffff); i_29_++) {
										CS2Event class23_sub9 = new CS2Event();
										class23_sub9.component = inter;
										class23_sub9.keyCode = CS2Event.anIntArray2265[i_29_];
										class23_sub9.keyChar = AbstractTimer.anIntArray308[i_29_];
										class23_sub9.scriptArguments = inter.key_press_handler;
										Class71_Sub2_Sub1.aClass89_4470.add_last(class23_sub9);
									}
								}
							}
						}
						if (!inter.newer_interface && StaticMethods.fromInterface == null && PlayerAppearance.aClass64_790 == null && !ContextMenu.menuOpen) {
							if ((inter.anInt1027 >= 0 || (inter.anInt1106 ^ 0xffffffff) != -1) && (i_10_ ^ 0xffffffff) >= (Mouse.mouseX ^ 0xffffffff) && (Mouse.mouseY ^ 0xffffffff) <= (i_11_ ^ 0xffffffff) && Mouse.mouseX < i_12_ && i_13_ > Mouse.mouseY) {
								if ((inter.anInt1027 ^ 0xffffffff) <= -1) {
									Class42.aClass64_663 = class64s[inter.anInt1027];
								} else {
									Class42.aClass64_663 = inter;
								}
							}
							if (inter.type == 8 && Mouse.mouseX >= i_10_ && (i_11_ ^ 0xffffffff) >= (Mouse.mouseY ^ 0xffffffff) && (i_12_ ^ 0xffffffff) < (Mouse.mouseX ^ 0xffffffff) && (Mouse.mouseY ^ 0xffffffff) > (i_13_ ^ 0xffffffff)) {
								VertexNormal.aClass64_1567 = inter;
							}
							if ((inter.layout_height ^ 0xffffffff) > (inter.scroll_height ^ 0xffffffff)) {
								Class87_Sub3.method1419(inter, false, comp_x + inter.layout_width, inter.layout_height, Mouse.mouseX, comp_y, Mouse.mouseY, inter.scroll_height);
							}
						}
						if ((inter.type ^ 0xffffffff) == -1) {
							handleItemSwitch(inter.uid, comp_y - inter.scroll_y, i_12_, i_10_, class64s, i_13_, i_11_, 120, -inter.scroll_x + comp_x);
							if (inter.dynamic_components != null) {
								handleItemSwitch(inter.uid, -inter.scroll_y + comp_y, i_12_, i_10_, inter.dynamic_components, i_13_, i_11_, 99, -inter.scroll_x + comp_x);
							}
							InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(inter.uid);
							if (class23_sub25 != null) {
								StaticMethods.method299(i_12_, class23_sub25.interfaceId, comp_x, 100, i_10_, i_11_, comp_y, i_13_);
							}
						}
					}
				}
			}
		}
	}

	static final void handleTeleCommand(int var0, int var1, int var2, byte var3) {
		RSString var4 = RSString.joinRsStrings(new RSString[] { PlayerAppearance.aClass94_853, RSString.valueOf(var2), WorldMap.aClass94_3268, RSString.valueOf(var0 >> 6), WorldMap.aClass94_3268, RSString.valueOf(var1 >> 6), WorldMap.aClass94_3268, RSString.valueOf(var0 & 63), WorldMap.aClass94_3268, RSString.valueOf(63 & var1) });
		var4.method158();
		Class53.processClientCommands(-91, var4);
	}

	static final GroundDecoration method1145(int i, int i_30_, int i_31_) {
		Ground class23_sub1 = Scene.current_grounds[i][i_30_][i_31_];
		if (class23_sub1 != null) {
			GroundDecoration decoration = class23_sub1.decoration;
			class23_sub1.decoration = null;
			return decoration;
		}
		return null;
	}

	public static void method1148(int i) {
		aClass16_721 = null;
		aClass16_723 = null;
		aClass16_742 = null;
		PlayerAppearance.calculatedHashes = null;
		anOa722 = null;
		aClass16_734 = null;
		if (i >= -55) {
			anInt741 = 78;
		}
	}

	static {
		aClass16_723 = aClass16_734;
		anOa722 = new HashTable(512);
		friendIndex = 0;
		anInt741 = 0;
		aClass16_742 = RSString.createString(":trade:");
	}
}
