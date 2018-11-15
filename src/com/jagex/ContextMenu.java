package com.jagex;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;

/**
 * Created by Chris on 4/12/2017.
 */
public class ContextMenu {

	public static RSString chooseOptionText = RSString.createString("Choose Option");
	public static int menuOffsetX;
	public static int menuOffsetY;
	public static int menuWidth;
	public static int menuHeight;
	public static int menuActionRow;
	public static short[] menuActionID = new short[500];
	public static int[] menuActionCmd2 = new int[500];
	public static int[] menuActionCmd3 = new int[500];
	public static boolean menuOpen = false;
	public static long[] menuActionCmd1;

	static final void determineMenuSize() {
		int baseWidth = FontCache.b12_full.calculate_width(chooseOptionText);
		for (int actionIndex = 0; actionIndex < menuActionRow; actionIndex++) {
			int actionWidth = FontCache.b12_full.calculate_width(getMenuActionName(actionIndex));
			if (baseWidth < actionWidth) {
				baseWidth = actionWidth;
			}
		}
		menuOpen = true;
		menuHeight = 15 * menuActionRow + 22;
		int menuHeight = 21 + menuActionRow * 15;
		int clickY = Mouse.mouseClickY;
		if (GameShell.window_height - 3 < clickY + menuHeight) {
			clickY = GameShell.window_height - 3 - menuHeight;
		}
		baseWidth += 8;
		int i_4_ = -(baseWidth / 2) + Mouse.mouseClickX;
		if (baseWidth + i_4_ > GameShell.window_width) {
			i_4_ = -baseWidth + GameShell.window_width;
		}
		if (i_4_ < 0) {
			i_4_ = 0;
		}
		if (clickY < 0) {
			clickY = 0;
		}
		menuOffsetY = clickY;
		menuOffsetX = i_4_;
		menuWidth = baseWidth;
	}

	public static final void drawMenu() {
		int xPos = menuOffsetX;
		int yPos = menuOffsetY;
		int menuW = menuWidth;
		int menuH = menuHeight + 1;
		if (GLManager.opengl_mode) {
			GLShapes.fill_rectangle(xPos, yPos + 2, menuW, menuH - 4, 0x706a5e);
			GLShapes.fill_rectangle(xPos + 1, yPos + 1, menuW - 2, menuH - 2, 0x706a5e);
			GLShapes.fill_rectangle(xPos + 2, yPos, menuW - 4, menuH, 0x706a5e);
			GLShapes.fill_rectangle(xPos + 3, yPos + 1, menuW - 6, menuH - 2, 0x2d2822);
			GLShapes.fill_rectangle(xPos + 2, yPos + 2, menuW - 4, menuH - 4, 0x2d2822);
			GLShapes.fill_rectangle(xPos + 1, yPos + 3, menuW - 2, menuH - 6, 0x2d2822);
			GLShapes.fill_rectangle(xPos + 2, yPos + 19, menuW - 4, menuH - 22, 0x524a3d);
			GLShapes.fill_rectangle(xPos + 3, yPos + 20, menuW - 6, menuH - 22, 0x524a3d);
			GLShapes.fill_rectangle(xPos + 3, yPos + 20, menuW - 6, menuH - 23, 0x2b271c);
			GLShapes.fill_rectangle(xPos + 3, yPos + 2, menuW - 6, 1, 0x2a291b);
			GLShapes.fill_rectangle(xPos + 2, yPos + 3, menuW - 4, 1, 0x2a261b);
			GLShapes.fill_rectangle(xPos + 2, yPos + 4, menuW - 4, 1, 0x252116);
			GLShapes.fill_rectangle(xPos + 2, yPos + 5, menuW - 4, 1, 0x211e15);
			GLShapes.fill_rectangle(xPos + 2, yPos + 6, menuW - 4, 1, 0x1e1b12);
			GLShapes.fill_rectangle(xPos + 2, yPos + 7, menuW - 4, 1, 0x1a170e);
			GLShapes.fill_rectangle(xPos + 2, yPos + 8, menuW - 4, 2, 0x15120b);
			GLShapes.fill_rectangle(xPos + 2, yPos + 10, menuW - 4, 1, 0x100d08);
			GLShapes.fill_rectangle(xPos + 2, yPos + 11, menuW - 4, 1, 0x090a04);
			GLShapes.fill_rectangle(xPos + 2, yPos + 12, menuW - 4, 1, 0x080703);
			GLShapes.fill_rectangle(xPos + 2, yPos + 13, menuW - 4, 1, 0x090a04);
			GLShapes.fill_rectangle(xPos + 2, yPos + 14, menuW - 4, 1, 0x070802);
			GLShapes.fill_rectangle(xPos + 2, yPos + 15, menuW - 4, 1, 0x090a04);
			GLShapes.fill_rectangle(xPos + 2, yPos + 16, menuW - 4, 1, 0x070802);
			GLShapes.fill_rectangle(xPos + 2, yPos + 17, menuW - 4, 1, 0x090a04);
			GLShapes.fill_rectangle(xPos + 2, yPos + 18, menuW - 4, 1, 0x2a291b);
			GLShapes.fill_rectangle(xPos + 3, yPos + 19, menuW - 6, 1, 0x564943);
		} else {
			Rasterizer2D.fill_rectangle(xPos, yPos + 2, menuW, menuH - 4, 0x706a5e);
			Rasterizer2D.fill_rectangle(xPos + 1, yPos + 1, menuW - 2, menuH - 2, 0x706a5e);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos, menuW - 4, menuH, 0x706a5e);
			Rasterizer2D.fill_rectangle(xPos + 3, yPos + 1, menuW - 6, menuH - 2, 0x2d2822);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 2, menuW - 4, menuH - 4, 0x2d2822);
			Rasterizer2D.fill_rectangle(xPos + 1, yPos + 3, menuW - 2, menuH - 6, 0x2d2822);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 19, menuW - 4, menuH - 22, 0x524a3d);
			Rasterizer2D.fill_rectangle(xPos + 3, yPos + 20, menuW - 6, menuH - 22, 0x524a3d);
			Rasterizer2D.fill_rectangle(xPos + 3, yPos + 20, menuW - 6, menuH - 23, 0x2b271c);
			Rasterizer2D.fill_rectangle(xPos + 3, yPos + 2, menuW - 6, 1, 0x2a291b);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 3, menuW - 4, 1, 0x2a261b);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 4, menuW - 4, 1, 0x252116);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 5, menuW - 4, 1, 0x211e15);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 6, menuW - 4, 1, 0x1e1b12);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 7, menuW - 4, 1, 0x1a170e);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 8, menuW - 4, 2, 0x15120b);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 10, menuW - 4, 1, 0x100d08);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 11, menuW - 4, 1, 0x090a04);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 12, menuW - 4, 1, 0x080703);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 13, menuW - 4, 1, 0x090a04);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 14, menuW - 4, 1, 0x070802);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 15, menuW - 4, 1, 0x090a04);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 16, menuW - 4, 1, 0x070802);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 17, menuW - 4, 1, 0x090a04);
			Rasterizer2D.fill_rectangle(xPos + 2, yPos + 18, menuW - 4, 1, 0x2a291b);
			Rasterizer2D.fill_rectangle(xPos + 3, yPos + 19, menuW - 6, 1, 0x564943);
		}
		FontCache.b12_full.draw_text(chooseOptionText, 3 + xPos, 14 + yPos, 0xc6b895, -1);
		int mouseX = Mouse.mouseX;
		int mouseY = Mouse.mouseY;
		for (int l1 = 0; l1 < menuActionRow; l1++) {
			int textY = yPos + 31 + (menuActionRow - 1 - l1) * 15;
			int disColor = 0xc6b895;
			if (mouseX > xPos && mouseX < xPos + menuW && mouseY > textY - 13 && mouseY < textY + 3) {
				if (GLManager.opengl_mode) {
					GLShapes.fill_rectangle(xPos + 3, textY - 11, menuWidth - 6, 15, 0x6f695d);
				} else {
					Rasterizer2D.fill_rectangle(xPos + 3, textY - 11, menuWidth - 6, 15, 0x6f695d);
				}
				disColor = 0xeee5c6;
			}
			FontCache.b12_full.draw_text(getMenuActionName(l1), xPos + 3, textY, disColor, 0);
		}
		StaticMethods2.method618(menuOffsetX, menuWidth, menuHeight, menuOffsetY);
	}

	static final RSString getMenuActionName(int i) {
		if (Class98.aClass16Array1655[i].length() <= 0) {
			return BufferedRequest.aClass16Array4307[i];
		}
		return RSString.joinRsStrings(new RSString[] { BufferedRequest.aClass16Array4307[i], StaticMethods2.aClass16_3762, Class98.aClass16Array1655[i] });
	}

	static final void processMenuClick() {
		if (PlayerAppearance.aClass64_790 == null) {
			if (StaticMethods.fromInterface == null) {
				int clickState = Mouse.mouseClickState;
				if (menuOpen) {
					if (clickState != 1) {
						int mouseX = Mouse.mouseX;
						int mouseY = Mouse.mouseY;
						if (mouseX < menuOffsetX - 10 || menuOffsetX + menuWidth + 10 < mouseX || menuOffsetY - 10 > mouseY || mouseY > 10 + menuHeight + menuOffsetY) {
							menuOpen = false;
							RuntimeException_Sub1.method1589(menuHeight, menuWidth, menuOffsetX, menuOffsetY, true);
						}
					}
					if (clickState == 1) {
						int i_7_ = menuOffsetY;
						int i_8_ = menuOffsetX;
						int i_9_ = menuWidth;
						int i_10_ = -1;
						int i_11_ = Mouse.mouseClickX;
						int i_12_ = Mouse.mouseClickY;
						for (int i_13_ = 0; i_13_ < menuActionRow; i_13_++) {
							int i_14_ = 15 * (-i_13_ + -1 + menuActionRow) + 31 + i_7_;
							if ((i_11_ ^ 0xffffffff) < (i_8_ ^ 0xffffffff) && i_11_ < i_9_ + i_8_ && (i_14_ + -13 ^ 0xffffffff) > (i_12_ ^ 0xffffffff) && (i_12_ ^ 0xffffffff) > (i_14_ + 3 ^ 0xffffffff)) {
								i_10_ = i_13_;
							}
						}
						if (i_10_ != -1) {
							GameClient.doAction(i_10_);
						}
						menuOpen = false;
						RuntimeException_Sub1.method1589(menuHeight, menuWidth, menuOffsetX, menuOffsetY, true);
					}
				} else {
					if (clickState == 1 && menuActionRow > 0) {
						short actionID = menuActionID[-1 + menuActionRow];
						if (actionID == 36 || actionID == 7 || actionID == 8 || actionID == 28 || actionID == 51 || actionID == 50 || actionID == 47 || actionID == 20 || actionID == 6 || actionID == 11 || actionID == 18 || actionID == 1007) {
							int i_15_ = menuActionCmd2[menuActionRow + -1];
							int i_16_ = menuActionCmd3[menuActionRow - 1];
							RSInterface inter = RSInterface.getInterface(i_16_);
							if (Class47.getOptionMask(inter, 71).method100() || Class47.getOptionMask(inter, 105).method93()) {
								CS2ScriptDefinition.aBoolean4262 = false;
								ColourImageCache.anInt1728 = 0;
								if (PlayerAppearance.aClass64_790 != null) {
									RSInterfaceList.setDirty(PlayerAppearance.aClass64_790);
								}
								PlayerAppearance.aClass64_790 = RSInterface.getInterface(i_16_);
								Class87_Sub3.firstSlot = i_15_;
								SpawnedObject.anInt2435 = Mouse.mouseClickX;
								StaticMethods.anInt3510 = Mouse.mouseClickY;
								RSInterfaceList.setDirty(PlayerAppearance.aClass64_790);
								return;
							}
						}
					}
					if (clickState == 1 && (Class95.anInt1612 == 1 && menuActionRow > 2 || Class87_Sub4.method1425((byte) -3, -1 + menuActionRow))) {
						clickState = 2;
					}
					if (clickState == 1 && menuActionRow > 0) {
						GameClient.doAction(menuActionRow + -1);
					}
					if (clickState == 2 && menuActionRow > 0) {// right click
						determineMenuSize();
					}
				}
			}
		}
	}

	static final void setMenuActions(long itemID, short s, int i, RSString formattedName, int i_140_, RSString actions, int i_142_) {
		if (!menuOpen) {
			if (i_140_ > menuActionRow) {
				BufferedRequest.aClass16Array4307[menuActionRow] = actions;
				Class98.aClass16Array1655[menuActionRow] = formattedName;
				menuActionID[menuActionRow] = s;
				menuActionCmd1[menuActionRow] = itemID;
				menuActionCmd2[menuActionRow] = i;
				menuActionCmd3[menuActionRow] = i_142_;
				menuActionRow++;
			}
		}
	}

	static final void buildMenu(int i, int i_23_, byte b, RSInterface class64) {
		if (class64.anInt1038 == 1) {
			setMenuActions(0L, (short) 30, 0, StaticMethods.empty_string, 500, class64.optionName, class64.uid);
		}
		if (b != 1) {
			InvType.invTypeMap = null;
		}
		if (class64.anInt1038 == 2 && !LocTypeList.aBoolean3792) {
			RSString class16 = MonochromeImageCache.getSelectedActionName(class64);
			if (class16 != null) {
				setMenuActions(0L, (short) 9, -1, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3458, class64.aClass16_1019 }), b + 499, class16, class64.uid);
			}
		}
		// System.out.println(class64.anInt1038);
		if (class64.anInt1038 == 3) {
			setMenuActions(0L, (short) 13, 0, StaticMethods.empty_string, 500, ReflectionAntiCheat.aClass16_73, class64.uid);
		}
		if (class64.anInt1038 == 4) {
			setMenuActions(0L, (short) 57, 0, StaticMethods.empty_string, 500, class64.optionName, class64.uid);
		}
		if (class64.anInt1038 == 5) {
			setMenuActions(0L, (short) 23, 0, StaticMethods.empty_string, 500, class64.optionName, class64.uid);
		}
		if (class64.anInt1038 == 6 && Varbit.aClass64_4007 == null) { // Action button
			setMenuActions(0L, (short) 15, -1, StaticMethods.empty_string, 500, class64.optionName, class64.uid);
		}
		if (class64.type == 2) {
			int i_24_ = 0;
			for (int i_25_ = 0; (i_25_ ^ 0xffffffff) > (class64.height ^ 0xffffffff); i_25_++) {
				for (int i_26_ = 0; (i_26_ ^ 0xffffffff) > (class64.width ^ 0xffffffff); i_26_++) {
					int i_27_ = i_26_ * (class64.anInt1046 + 32);
					int i_28_ = i_25_ * (class64.anInt998 + 32);
					if (i_24_ < 20) {
						i_28_ += class64.anIntArray1051[i_24_];
						i_27_ += class64.anIntArray1011[i_24_];
					}
					if ((i_27_ ^ 0xffffffff) >= (i_23_ ^ 0xffffffff) && i >= i_28_ && (i_23_ ^ 0xffffffff) > (32 + i_27_ ^ 0xffffffff) && i < i_28_ - -32) {
						CS2ScriptDefinition.aClass64_4257 = class64;
						Class49.secondSlot = i_24_;
						if ((class64.obj_ids[i_24_] ^ 0xffffffff) < -1) {
							ObjType itemDef = ObjTypeList.list(-1 + class64.obj_ids[i_24_]);
							if (NPC.anInt4374 == 1 && Class47.getOptionMask(class64, 71).method99()) {
								if ((Class49.anInt759 ^ 0xffffffff) != (class64.uid ^ 0xffffffff) || AbstractTimer.anInt302 != i_24_) {
									setMenuActions(itemDef.itemId, (short) 26, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_4281, InstrumentDefinition.aClass16_274, itemDef.name }), 500, NPCType.aClass16_4163, class64.uid);
								}
							} else if (LocTypeList.aBoolean3792 && Class47.getOptionMask(class64, 71).method99()) {
								if ((0x20 & Class71_Sub1.anInt2725) == 32) {
									setMenuActions(itemDef.itemId, (short) 24, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_3714, InstrumentDefinition.aClass16_274, itemDef.name }), 500, Class49.aClass16_764, class64.uid);
								}
							} else {
								RSString[] actions = itemDef.actions;
								if (StaticMethods.aBoolean2977) {
									actions = StaticMethods.method396(30113, actions);
								}
								if (Class47.getOptionMask(class64, b ^ 0x78).method99()) {
									for (int i_29_ = 4; i_29_ >= 3; i_29_--) {
										if (actions == null || actions[i_29_] == null) {
											if (i_29_ == 4) {
												setMenuActions(itemDef.itemId, (short) 11, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, itemDef.name }), 500, InvType.aClass16_4186, class64.uid);
											}
										} else {
											short s;
											if (i_29_ == 3) {
												s = (short) 6;
											} else {
												s = (short) 11;
											} // item tooltips
											setMenuActions(itemDef.itemId, s, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, itemDef.name }), 500, actions[i_29_], class64.uid);
										}
									}
								}
								if (Class47.getOptionMask(class64, 105).method96()) {
									setMenuActions(itemDef.itemId, (short) 18, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, itemDef.name }), 500, NPCType.aClass16_4163, class64.uid);
								}
								if (Class47.getOptionMask(class64, 124).method99() && actions != null) {
									for (int i_30_ = 2; i_30_ >= 0; i_30_--) {
										if (actions[i_30_] != null) {
											short s = 0;
											if (i_30_ == 0) {
												s = (short) 50;
											}
											if (i_30_ == 1) {
												s = (short) 47;
											}
											if (i_30_ == 2) {
												s = (short) 20;
											}
											// System.out.println(class16s[1]);
											setMenuActions(itemDef.itemId, s, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, itemDef.name }), 500, actions[i_30_], class64.uid);
										}
									}
								}
								actions = class64.options;
								if (StaticMethods.aBoolean2977) {
									actions = StaticMethods.method396(30113, actions);
								}
								if (actions != null) {
									for (int i_31_ = 4; (i_31_ ^ 0xffffffff) <= -1; i_31_--) {
										if (actions[i_31_] != null) {
											short s = 0;
											if ((i_31_ ^ 0xffffffff) == -1) {
												s = (short) 36;
											}
											if (i_31_ == 1) {
												s = (short) 7;
											}
											if (i_31_ == 2) {
												s = (short) 8;
											}
											if (i_31_ == 3) {
												s = (short) 28;
											}
											if (i_31_ == 4) {
												s = (short) 51;
											}
											// System.out.println(class16s[1]);
											setMenuActions(itemDef.itemId, s, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, itemDef.name }), b ^ 0x1f5, actions[i_31_], class64.uid);
										}
									}
								}
								setMenuActions(itemDef.itemId, (short) 1007, i_24_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, itemDef.name }), 500, Class71_Sub1_Sub1.aClass16_4462, class64.uid);
							}
						}
					}
					i_24_++;
				}
			}
		}
		if (class64.newer_interface) {
			if (!LocTypeList.aBoolean3792) {
				for (int i_32_ = 9; i_32_ >= 5; i_32_--) {
					RSString class16 = PositionedGraphicNode.method685(i_32_, class64, 1);
					if (class16 != null) {//
						setMenuActions(1 + i_32_, (short) 1002, class64.componentIndex, class64.opBase, 500, class16, class64.uid);
					}
				}
				RSString class16 = MonochromeImageCache.getSelectedActionName(class64);
				if (class16 != null) {
					setMenuActions(0L, (short) 9, class64.componentIndex, class64.opBase, 500, class16, class64.uid);
				}
				for (int i_33_ = 4; i_33_ >= 0; i_33_--) {
					RSString class16_34_ = PositionedGraphicNode.method685(i_33_, class64, b);
					if (class16_34_ != null) {
						setMenuActions(i_33_ + 1, (short) 19, class64.componentIndex, class64.opBase, b + 499, class16_34_, class64.uid);
					}
				}
				if (Class47.getOptionMask(class64, b + 64).method95()) {
					if (class64.pauseText != null) {
						setMenuActions(0L, (short) 15, class64.componentIndex, StaticMethods.empty_string, 500, class64.pauseText, class64.uid);
					} else {
						setMenuActions(0L, (short) 15, class64.componentIndex, StaticMethods.empty_string, 500, Class71_Sub1_Sub1.optionContinue, class64.uid);
					}
				}
			} else if (Class47.getOptionMask(class64, 72).method97() && (32 & Class71_Sub1.anInt2725) != 0) {
				setMenuActions(0L, (short) 49, class64.componentIndex, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_3714, StaticMethods.aClass16_3050, class64.opBase }), b ^ 0x1f5, Class49.aClass16_764, class64.uid);
			}
		}
	}
}
