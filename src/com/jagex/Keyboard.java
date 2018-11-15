package com.jagex;

/* Class32 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener, FocusListener {
	static RSString aClass16_494 = RSString.createString(" )2>");
	public static int sampleRate;
	public static int[] anIntArray501 = new int[256];
	public static int anInt505;
	public static boolean ctrlShiftPressed;
	public static boolean modernHotkeyLayout;
	public static int[] keyEvents = { 0, 0, 0, 0, 0, 0, 0, 0, 85, 80, 84, 0, 91, 0, 0, 0, 81, 82, 86, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 83, 104, 105, 103, 102, 96, 98, 97, 99, 0, 0, 0, 0, 0, 0, 0, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0, 0, 0, 0, 0, 0, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54, 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37, 64, 0, 0, 0, 0, 0, 228, 231, 227, 233, 224, 219, 225, 230, 226, 232, 89, 87, 0, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 0, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static final void method962(int i, int i_0_, int i_1_, byte b, Player other) {
		if (other != GameClient.currentPlayer && ContextMenu.menuActionRow < 400) {
			RSString class16;
			RSString username = other.titleUsername == null ? other.username : other.titleUsername;
			if (other.skillTotalLevel != 0) {
				class16 = RSString.joinRsStrings(new RSString[] { username, VertexNormal.aClass16_1564, Entity.aClass16_2679, RSString.valueOf(other.skillTotalLevel), StaticMethods.aClass16_3048 });
			} else if (other.summoning != 0) {
				class16 = RSString.joinRsStrings(new RSString[] { username, EntityUpdating.getCombatColour(GameClient.currentPlayer.combatLevel, (byte) -15, other.combatLevel), VertexNormal.aClass16_1564, ClanChatMember.aClass16_2378, RSString.valueOf(other.combatLevel), RSString.create("+" + other.summoning), StaticMethods.aClass16_3048 });
			} else {
				class16 = RSString.joinRsStrings(new RSString[] { username, EntityUpdating.getCombatColour(GameClient.currentPlayer.combatLevel, (byte) -15, other.combatLevel), VertexNormal.aClass16_1564, ClanChatMember.aClass16_2378, RSString.valueOf(other.combatLevel), StaticMethods.aClass16_3048 });
			}
			if (NPC.anInt4374 == 1) {
				ContextMenu.setMenuActions(i_1_, (short) 40, i_0_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_4281, Packet.aClass16_2169, class16 }), 500, NPCType.aClass16_4163, i);
			} else if (!LocTypeList.aBoolean3792) {
				for (int i_2_ = 7; (i_2_ ^ 0xffffffff) <= -1; i_2_--) {
					if (SpawnedObject.playerOptions[i_2_] != null) {
						short s = 0;
						@SuppressWarnings("unused")
						boolean bool = false;
						if ((GameClient.getGameValue() ^ 0xffffffff) != -1 || !SpawnedObject.playerOptions[i_2_].equalsIgnoreCase(Class31.aClass16_487)) {
							if (Queuable.aBooleanArray2317[i_2_]) {
								s = (short) 2000;
							}
						} else {
							if ((GameClient.currentPlayer.combatLevel ^ 0xffffffff) > (other.combatLevel ^ 0xffffffff)) {
								s = (short) 2000;
							}
							if (GameClient.currentPlayer.currentTeamId != 0 && other.currentTeamId != 0) {
								if (other.currentTeamId == GameClient.currentPlayer.currentTeamId) {
									s = (short) 2000;
								} else {
									s = (short) 0;
								}
							}
						}
						short s_3_ = ModelNode.aShortArray4283[i_2_];
						s_3_ += s;
						ContextMenu.setMenuActions(i_1_, s_3_, i_0_, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_2896, class16 }), 500, SpawnedObject.playerOptions[i_2_], i);
					}
				}
			} else if ((0x8 & Class71_Sub1.anInt2725) == 8) {
				ContextMenu.setMenuActions(i_1_, (short) 38, i_0_, RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_3714, Packet.aClass16_2169, class16 }), 500, Class49.aClass16_764, i);
			}
			@SuppressWarnings("unused")
			int i_4_ = -41 / ((b - -8) / 62);
			for (int i_5_ = 0; (ContextMenu.menuActionRow ^ 0xffffffff) < (i_5_ ^ 0xffffffff); i_5_++) {
				if (ContextMenu.menuActionID[i_5_] == 5) {
					Class98.aClass16Array1655[i_5_] = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_2896, class16 });
					break;
				}
			}
		}
	}

	public static final void setKeyEvents(byte b) {
		if (b > 90) {
			if (GameShell.java_vendor.toLowerCase().indexOf("microsoft") == -1) {
				keyEvents[47] = 73;
				keyEvents[46] = 72;
				keyEvents[44] = 71;
				keyEvents[45] = 26;
				keyEvents[61] = 27;
				keyEvents[59] = 57;
				keyEvents[92] = 74;
				keyEvents[93] = 43;
				keyEvents[91] = 42;
				keyEvents[520] = 59;
				keyEvents[222] = 58;
				keyEvents[192] = 28;
			} else {
				keyEvents[190] = 72;
				keyEvents[192] = 58;
				keyEvents[219] = 42;
				keyEvents[186] = 57;
				keyEvents[222] = 59;
				keyEvents[191] = 73;
				keyEvents[223] = 28;
				keyEvents[220] = 74;
				keyEvents[189] = 26;
				keyEvents[188] = 71;
				keyEvents[221] = 43;
				keyEvents[187] = 27;
			}
		}
	}

	@Override
	public final void keyTyped(KeyEvent keyevent) {
		if (!DebugConsole.is_open() && keyevent.getKeyChar() != '`' && keyevent.getKeyChar() != '¬') {
			if (InputManager.keyboard != null) {
				int keyChar = InstrumentDefinition.method134(keyevent, 97);
				if ((keyChar ^ 0xffffffff) <= -1) {
					int i_6_ = 0x7f & 1 + NameHashTable.anInt1202;
					if ((ColourImageCache.anInt1724 ^ 0xffffffff) != (i_6_ ^ 0xffffffff)) {
						StaticMethods.anIntArray596[NameHashTable.anInt1202] = -1;
						LocResult.anIntArray3719[NameHashTable.anInt1202] = keyChar;
						NameHashTable.anInt1202 = i_6_;
					}
				}
			}
		}
		keyevent.consume();
	}

	@Override
	public final synchronized void keyReleased(KeyEvent keyevent) {
		if (!DebugConsole.is_open()) {
			if (keyevent.getKeyCode() == 16 || keyevent.getKeyCode() == 17) {
				ctrlShiftPressed = false;
			}
			if (InputManager.keyboard != null) {
				Class79.idleKeyTicks = 0;
				int i = keyevent.getKeyCode();
				if (i < 0 || i >= keyEvents.length) {
					i = -1;
				} else {
					i = ~0x80 & keyEvents[i];
				}
				if (InputManager.anInt2986 >= 0 && i >= 0) {
					GameTimer.keyCodesPressed[InputManager.anInt2986] = i ^ 0xffffffff;
					InputManager.anInt2986 = 0x7f & InputManager.anInt2986 - -1;
					if (Queuable.anInt2309 == InputManager.anInt2986) {
						InputManager.anInt2986 = -1;
					}
				}
			}
		}
		keyevent.consume();
	}

	@Override
	public final synchronized void focusLost(FocusEvent focusevent) {
		if (InputManager.keyboard != null) {
			InputManager.anInt2986 = -1;
		}
	}

	public static void method965(int i) {
		aClass16_494 = null;
		anIntArray501 = null;
		GameClient.atInventoryInterface = null;
		if (i != -7897) {
			method966(18, -79);
		}
	}

	@Override
	public final void focusGained(FocusEvent focusevent) {

	}

	static final boolean method966(int i, int i_25_) {
		if (i >= 97 && i <= 122) {
			return true;
		}
		if (i >= 65 && i <= 90) {
			return true;
		}
		if (i >= 48 && i <= 57) {
			return true;
		}
		if (i_25_ != 122) {
			anIntArray501 = null;
		}
		return false;
	}

	@Override
	public final synchronized void keyPressed(KeyEvent keyevent) {
		if (keyevent.getModifiers() == 3) {
			ctrlShiftPressed = true;
		}
		if (InputManager.keyboard != null) {
			Class79.idleKeyTicks = 0;
			int keyCode = keyevent.getKeyCode();

			if (keyCode == 192) {
				if (DebugConsole.is_open()) {
					DebugConsole.close();
					DebugConsole.key_press_count = 0;
				} else {
					DebugConsole.open();
				}
			} else {
				if (DebugConsole.is_open()) {
					DebugConsole.key_press_events[DebugConsole.key_press_count] = keyevent;
					if (DebugConsole.key_press_count < 128) {
						DebugConsole.key_press_count++;
					} else {
						DebugConsole.key_press_count = 0;
					}
				} else {
					if (keyCode == KeyEvent.VK_TAB) {
						if (GameClient.currentPlayer != null) {
							Class53.processClientCommands(0, RSString.createString("::reply"));
						}
					}
					if (keyCode < 0 || keyCode >= keyEvents.length) {
						keyCode = -1;
					} else {
						keyCode = keyEvents[keyCode];
						if ((0x80 & keyCode) != 0) {
							keyCode = -1;
						}
					}
					if ((InputManager.anInt2986 ^ 0xffffffff) <= -1 && (keyCode ^ 0xffffffff) <= -1) {
						GameTimer.keyCodesPressed[InputManager.anInt2986] = keyCode;
						InputManager.anInt2986 = 1 + InputManager.anInt2986 & 0x7f;
						if (Queuable.anInt2309 == InputManager.anInt2986) {
							InputManager.anInt2986 = -1;
						}
					}
					if (keyCode >= 0) {
						int i_26_ = 0x7f & 1 + NameHashTable.anInt1202;
						if ((i_26_ ^ 0xffffffff) != (ColourImageCache.anInt1724 ^ 0xffffffff)) {
							StaticMethods.anIntArray596[NameHashTable.anInt1202] = keyCode;
							LocResult.anIntArray3719[NameHashTable.anInt1202] = -1;
							NameHashTable.anInt1202 = i_26_;
						}
					}
				}
				int i_27_ = keyevent.getModifiers();
				if ((0xa & i_27_) != 0 || keyCode == 85 || keyCode == 10) {
					keyevent.consume();
				}
			}
		}
	}
}
