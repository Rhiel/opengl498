package com.jagex;

import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.jagex.launcher.GameLaunch;
import com.rs2.client.scene.Scene;

/**
 * @author Walied K. Yassen
 */
public class StaticMethods2 {

	static RSString aClass16_1734;
	static RSString aClass16_1737 = RSString.createString("Ung-Ultiger Benutzername");
	static RSString aClass16_1738;
	static RSString aClass16_1739 = RSString.createString("<col=c0ff00>");
	static RSString aClass16_1740;
	static boolean[] aBooleanArray1741;
	static RSString aClass16_1748;

	static final boolean method1524(int i, int i_0_) {
		if (i_0_ != 0) {
			return true;
		}
		if ((i < 97 || i > 122) && (i < 65 || i > 90)) {
			return false;
		}
		return true;
	}

	public static final void method1526(int layout_x, int layout_y, int layout_width, int layout_height, int i_3_, int i_6_) {
		CS2CallFrame.anInt776 = 0;
		for (int i_7_ = -1; i_7_ < EntityUpdating.localNPCCount + StaticMethods.anInt3067; i_7_++) {
			Entity player;
			if (i_7_ == -1) {
				player = GameClient.currentPlayer;
			} else if (StaticMethods.anInt3067 <= i_7_) {
				player = GameClient.activeNPCs[EntityUpdating.localNPCIndexes[-StaticMethods.anInt3067 + i_7_]];
			} else {
				player = GameClient.localPlayers[GameClient.localPlayerPointers[i_7_]];
			}
			if (player != null && player.is_ready()) {
				if (player instanceof NPC) {
					NPCType class23_sub13_sub20 = ((NPC) player).config;
					if (class23_sub13_sub20.morphisms != null) {
						class23_sub13_sub20 = class23_sub13_sub20.getChildNPC();
					}
					if (class23_sub13_sub20 == null) {
						continue;
					}
				}
				if (i_7_ >= StaticMethods.anInt3067) {
					NPCType class23_sub13_sub20 = ((NPC) player).config;
					if (class23_sub13_sub20.morphisms != null) {
						class23_sub13_sub20 = class23_sub13_sub20.getChildNPC();
					}
					if (class23_sub13_sub20.headIcon >= 0 && (class23_sub13_sub20.headIcon ^ 0xffffffff) > (StaticMedia.headicons_prayer.length ^ 0xffffffff)) {
						Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, 15 + player.method1081((byte) -41));
						if (StaticMethods.anInt2989 > -1) {
							StaticMedia.headicons_prayer[class23_sub13_sub20.headIcon].draw_clipped_left_anchor(StaticMethods.anInt2989 + layout_x + -12, -30 + Class97.anInt1645 + layout_y);
						}
					}
					HintIcon[] class10s = ReflectionRequest.currentHintIcons;
					for (int i_8_ = 0; (i_8_ ^ 0xffffffff) > (class10s.length ^ 0xffffffff); i_8_++) {
						HintIcon class10 = class10s[i_8_];
						if (class10 != null && class10.targetType == 1 && (class10.entityIndex ^ 0xffffffff) == (EntityUpdating.localNPCIndexes[i_7_ + -StaticMethods.anInt3067] ^ 0xffffffff) && GameClient.timer % 20 < 10) {
							Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, 15 + player.method1081((byte) -41));
							if ((StaticMethods.anInt2989 ^ 0xffffffff) < 0) {
								StaticMethods2.hint_headicons[class10.arrowId].draw_clipped_left_anchor(layout_x + StaticMethods.anInt2989 + -12, -28 + layout_y + Class97.anInt1645);
							}
						}
					}
				} else {
					Player class38_sub7_sub2 = (Player) player;
					int i_9_ = 30;
					if ((class38_sub7_sub2.skullIcon ^ 0xffffffff) != 0 || (class38_sub7_sub2.headIcon ^ 0xffffffff) != 0) {
						Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, player.method1081((byte) -41) - -15);
						if ((StaticMethods.anInt2989 ^ 0xffffffff) < 0) {
							if ((class38_sub7_sub2.skullIcon ^ 0xffffffff) != 0) {
								StaticMedia.headicons_pk[class38_sub7_sub2.skullIcon].draw_clipped_left_anchor(-12 + StaticMethods.anInt2989 + layout_x, -i_9_ + Class97.anInt1645 + layout_y);
								i_9_ += 25;
							}
							if (class38_sub7_sub2.headIcon != -1) {
								StaticMedia.headicons_prayer[class38_sub7_sub2.headIcon].draw_clipped_left_anchor(-12 + StaticMethods.anInt2989 + layout_x, layout_y + Class97.anInt1645 + -i_9_);
								i_9_ += 25;
							}
						}
					}
					if (i_7_ >= 0) {
						int i_10_ = 0;
						for (HintIcon[] class10s = ReflectionRequest.currentHintIcons; (class10s.length ^ 0xffffffff) < (i_10_ ^ 0xffffffff); i_10_++) {
							HintIcon class10 = class10s[i_10_];
							if (class10 != null && class10.targetType == 10 && (class10.entityIndex ^ 0xffffffff) == (GameClient.localPlayerPointers[i_7_] ^ 0xffffffff)) {
								Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, player.method1081((byte) -41) - -15);
								if ((StaticMethods.anInt2989 ^ 0xffffffff) < 0) {
									StaticMethods2.hint_headicons[class10.arrowId].draw_clipped_left_anchor(layout_x + StaticMethods.anInt2989 + -12, Class97.anInt1645 + layout_y + -i_9_);
								}
							}
						}
					}
				}
				if (player.aClass16_2670 != null && (StaticMethods.anInt3067 <= i_7_ || (LocResult.anInt3722 ^ 0xffffffff) == -1 || LocResult.anInt3722 == 3 || LocResult.anInt3722 == 1 && Class42.method1116(((Player) player).username))) {
					Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, player.method1081((byte) -41));
					if (StaticMethods.anInt2989 > -1 && CS2CallFrame.anInt776 < Class55.anInt875) {
						Class55.anIntArray878[CS2CallFrame.anInt776] = FontCache.b12_full.calculate_width(player.aClass16_2670) / 2;
						Class55.anIntArray877[CS2CallFrame.anInt776] = FontCache.b12_full.plot_height;
						Class55.anIntArray885[CS2CallFrame.anInt776] = StaticMethods.anInt2989;
						Class55.anIntArray883[CS2CallFrame.anInt776] = Class97.anInt1645;
						Class55.anIntArray880[CS2CallFrame.anInt776] = player.anInt2713;
						Class55.anIntArray882[CS2CallFrame.anInt776] = player.anInt2701;
						Class55.anIntArray876[CS2CallFrame.anInt776] = player.anInt2639;
						Class55.aClass16Array884[CS2CallFrame.anInt776] = player.aClass16_2670;
						CS2CallFrame.anInt776++;
					}
				}
				if ((GameClient.timer ^ 0xffffffff) > (player.anInt2638 ^ 0xffffffff)) {
					Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, player.method1081((byte) -41) + 15);
					if ((StaticMethods.anInt2989 ^ 0xffffffff) < 0) {
						if (GLManager.opengl_mode) {
							GLShapes.fill_rectangle(StaticMethods.anInt2989 + layout_x + -15, -3 + layout_y - -Class97.anInt1645, player.anInt2708, 5, 65280);
							GLShapes.fill_rectangle(player.anInt2708 + -15 + layout_x + StaticMethods.anInt2989, -3 + layout_y + Class97.anInt1645, 30 - player.anInt2708, 5, 16711680);
						} else {
							Rasterizer2D.fill_rectangle(StaticMethods.anInt2989 + layout_x + -15, -3 + layout_y - -Class97.anInt1645, player.anInt2708, 5, 65280);
							Rasterizer2D.fill_rectangle(player.anInt2708 + -15 + layout_x + StaticMethods.anInt2989, -3 + layout_y + Class97.anInt1645, 30 - player.anInt2708, 5, 16711680);
						}
					}
				}
				for (int i_11_ = 0; i_11_ < 4; i_11_++) {
					if (player.anIntArray2687[i_11_] > GameClient.timer) {
						Class54.method1177(layout_height >> 1, i_3_, layout_width >> 1, i_6_, player, player.method1081((byte) -41) / 2);
						if (StaticMethods.anInt2989 > -1) {
							if (i_11_ == 1) {
								Class97.anInt1645 -= 20;
							}
							if (i_11_ == 2) {
								StaticMethods.anInt2989 -= 15;
								Class97.anInt1645 -= 10;
							}
							if (i_11_ == 3) {
								StaticMethods.anInt2989 += 15;
								Class97.anInt1645 -= 10;
							}
							StaticMedia.hitmarks[player.anIntArray2652[i_11_]].draw_clipped_left_anchor(-12 + StaticMethods.anInt2989 + layout_x, Class97.anInt1645 + layout_y - 12);
							FontCache.p11_full.draw_text_centered(RSString.valueOf(player.anIntArray2669[i_11_]), layout_x + StaticMethods.anInt2989 - 1, 3 + layout_y - -Class97.anInt1645, 16777215, 0);
						}
					}
				}
			}
		}
		int i_12_ = 0;
		for (/**/; (i_12_ ^ 0xffffffff) > (CS2CallFrame.anInt776 ^ 0xffffffff); i_12_++) {
			int i_13_ = Class55.anIntArray883[i_12_];
			int i_14_ = Class55.anIntArray885[i_12_];
			int i_15_ = Class55.anIntArray877[i_12_];
			boolean bool = true;
			int i_16_ = Class55.anIntArray878[i_12_];
			while (bool) {
				bool = false;
				for (int i_17_ = 0; (i_17_ ^ 0xffffffff) > (i_12_ ^ 0xffffffff); i_17_++) {
					if ((i_13_ + 2 ^ 0xffffffff) < (-Class55.anIntArray877[i_17_] + Class55.anIntArray883[i_17_] ^ 0xffffffff) && (i_13_ + -i_15_ ^ 0xffffffff) > (2 + Class55.anIntArray883[i_17_] ^ 0xffffffff) && (Class55.anIntArray878[i_17_] + Class55.anIntArray885[i_17_] ^ 0xffffffff) < (i_14_ + -i_16_ ^ 0xffffffff) && -Class55.anIntArray878[i_17_] + Class55.anIntArray885[i_17_] < i_16_ + i_14_ && Class55.anIntArray883[i_17_] - Class55.anIntArray877[i_17_] < i_13_) {
						i_13_ = Class55.anIntArray883[i_17_] + -Class55.anIntArray877[i_17_];
						bool = true;
					}
				}
			}
			StaticMethods.anInt2989 = Class55.anIntArray885[i_12_];
			Class97.anInt1645 = Class55.anIntArray883[i_12_] = i_13_;
			RSString class16 = Class55.aClass16Array884[i_12_];
			if ((InstrumentDefinition.anInt275 ^ 0xffffffff) != -1) {
				FontCache.b12_full.draw_text_centered(class16, StaticMethods.anInt2989 + layout_x, Class97.anInt1645 + layout_y, 16776960, 0);
			} else {
				int color = 16776960;
				if (Class55.anIntArray880[i_12_] < 6) {
					color = StaticMethods2.anIntArray4496[Class55.anIntArray880[i_12_]];
				}
				if (Class55.anIntArray880[i_12_] == 6) {
					color = Scene.render_cycle % 20 < 10 ? 16711680 : 16776960;
				}
				if (Class55.anIntArray880[i_12_] == 7) {
					color = Scene.render_cycle % 20 >= 10 ? 65535 : 255;
				}
				if (Class55.anIntArray880[i_12_] == 8) {
					color = Scene.render_cycle % 20 >= 10 ? 8454016 : 45056;
				}
				if (Class55.anIntArray880[i_12_] == 9) {
					int i_19_ = -Class55.anIntArray876[i_12_] + 150;
					if (i_19_ < 50) {
						color = 1280 * i_19_ + 16711680;
					} else if (i_19_ >= 100) {
						if (i_19_ < 150) {
							color = 65280 + -500 + i_19_ * 5;
						}
					} else {
						color = 16776960 + -((-50 + i_19_) * 327680);
					}
				}
				if (Class55.anIntArray880[i_12_] == 10) {
					int i_20_ = 150 - Class55.anIntArray876[i_12_];
					if (i_20_ < 50) {
						color = 16711680 + i_20_ * 5;
					} else if (i_20_ < 100) {
						color = 16711935 - (i_20_ * 327680 - 16384000);
					} else if (i_20_ < 150) {
						color = -((i_20_ + -100) * 5) + -32768000 + 327680 * i_20_ + 255;
					}
				}
				if (Class55.anIntArray880[i_12_] == 11) {
					int i_21_ = -Class55.anIntArray876[i_12_] + 150;
					if (i_21_ < 50) {
						color = -(327685 * i_21_) + 16777215;
					} else if (i_21_ >= 100) {
						if (i_21_ < 150) {
							color = 16777215 - (i_21_ * 327680 - 32768000);
						}
					} else {
						color = i_21_ * 327685 + -16318970;
					}
				}
				if (Class55.anIntArray882[i_12_] == 0) {
					FontCache.b12_full.draw_text_centered(class16, StaticMethods.anInt2989 + layout_x, layout_y + Class97.anInt1645, color, 0);
				}
				if (Class55.anIntArray882[i_12_] == 1) {
					FontCache.b12_full.draw_bouncingy_text_centered(class16, layout_x + StaticMethods.anInt2989, layout_y + Class97.anInt1645, color, 0, Scene.render_cycle);
				}
				if (Class55.anIntArray882[i_12_] == 2) {
					FontCache.b12_full.draw_bouncingxy_text_centered(class16, StaticMethods.anInt2989 + layout_x, Class97.anInt1645 + layout_y, color, 0, Scene.render_cycle);
				}
				if (Class55.anIntArray882[i_12_] == 3) {
					FontCache.b12_full.draw_bouncingy_text_centered(class16, layout_x + StaticMethods.anInt2989, layout_y + Class97.anInt1645, color, 0, Scene.render_cycle, 150 + -Class55.anIntArray876[i_12_]);
				}
				if (Class55.anIntArray882[i_12_] == 4) {
					int i_22_ = (150 - Class55.anIntArray876[i_12_]) * (FontCache.b12_full.calculate_width(class16) + 100) / 150;
					if (GLManager.opengl_mode) {
						GLState.accumlate_clipping(layout_x + StaticMethods.anInt2989 + -50, layout_y, 50 + StaticMethods.anInt2989 + layout_x, layout_y + layout_height);
					} else {
						Rasterizer2D.accumlate_clipping(layout_x + StaticMethods.anInt2989 + -50, layout_y, 50 + StaticMethods.anInt2989 + layout_x, layout_y + layout_height);
					}
					FontCache.b12_full.draw_text(class16, 50 + StaticMethods.anInt2989 + layout_x + -i_22_, layout_y + Class97.anInt1645, color, 0);
					if (GLManager.opengl_mode) {
						GLState.set_clipping(layout_x, layout_y, layout_width + layout_x, layout_height + layout_y);
					} else {
						GLState.set_clipping(layout_x, layout_y, layout_width + layout_x, layout_height + layout_y);
					}
				}

				if (Class55.anIntArray882[i_12_] == 5) {
					int i_23_ = 0;
					int i_24_ = -Class55.anIntArray876[i_12_] + 150;
					Rasterizer2D.accumlate_clipping(layout_x, -1 + Class97.anInt1645 + layout_y - FontCache.b12_full.plot_height, layout_x + layout_width, 5 + Class97.anInt1645 + layout_y);
					if (i_24_ >= 25) {
						if (i_24_ > 125) {
							i_23_ = i_24_ - 125;
						}
					} else {
						i_23_ = i_24_ - 25;
					}
					FontCache.b12_full.draw_text_centered(class16, layout_x - -StaticMethods.anInt2989, Class97.anInt1645 + layout_y - -i_23_, color, 0);
					if (GLManager.opengl_mode) {
						GLState.set_clipping(layout_x, layout_y, layout_x - -layout_width, layout_y + layout_height);
					} else {
						Rasterizer2D.set_clipping(layout_x, layout_y, layout_x - -layout_width, layout_y + layout_height);
					}
				}
			}
		}
	}

	public static void method1527() {
		aClass16_1738 = null;
		aClass16_1748 = null;
		aClass16_1739 = null;
		aClass16_1737 = null;
		aClass16_1734 = null;
		aClass16_1740 = null;
		aBooleanArray1741 = null;
	}

	static final void method1528(int i, int i_25_, int i_26_, int i_27_, int i_28_, boolean bool, int i_29_) {
		if (-i_29_ + i_28_ >= VarpDefinition.anInt3728 && Class35.anInt554 >= i_28_ + i_29_ && Class88.anInt1503 <= -i_29_ + i_25_ && i_25_ + i_29_ <= StaticMethods.anInt3435) {
			Huffman.method1577(i, i_25_, i_26_, i_27_, -80, i_28_, i_29_);
		} else {
			ClanChatMember.method882((byte) 83, i_26_, i_25_, i, i_28_, i_27_, i_29_);
		}
		if (bool != true) {
			aBooleanArray1741 = null;
		}
	}

	static {
		StaticMethods2.aClass16_1738 = RSString.createString("<col=00ff80>");
		StaticMethods2.aClass16_1734 = RSString.createString("Allocating memory");
		StaticMethods2.aClass16_1740 = RSString.createString("Speicher wird zugewiesen)3");
		StaticMethods2.aBooleanArray1741 = new boolean[200];
		StaticMethods2.aClass16_1748 = StaticMethods2.aClass16_1734;
	}

	public static void method1170() {
	}

	static final int method852(int i, int i_11_, int i_12_) {
		if (i_12_ > i) {
			int i_13_ = i;
			i = i_12_;
			i_12_ = i_13_;
		}
		if (i_11_ <= 65) {
			return 48;
		}
		int i_14_;
		for (/**/; i_12_ != 0; i_12_ = i_14_) {
			i_14_ = i % i_12_;
			i = i_12_;
		}
		return i;
	}

	public static int[] chatIDS;
	static RSString requestSeperator = RSString.createString(":");
	static RSString aClass16_4277;
	static ModelList aModelList_4267 = new ModelList(5);
	static byte chatKickRights;
	static RSString aClass16_4281;

	public static void method848(byte b) {
		chatIDS = null;
		aClass16_4277 = null;
		aClass16_4281 = null;
		requestSeperator = null;
		CS2Runtime.int_global_vars = null;
		aModelList_4267 = null;
	}

	static {
		aClass16_4281 = null;
		chatIDS = new int[100];
		aClass16_4277 = RSString.createString("VOLL");
	}
	static Sprite[] hint_headicons;
	static RSString aClass16_3714 = null;
	public static ISAACPacket packet;

	static final void method618(int i, int i_2_, int i_3_, int i_5_) {
		for (int i_6_ = 0; (i_6_ ^ 0xffffffff) > (StaticMethods.widget_quads ^ 0xffffffff); i_6_++) {
			if ((StaticMethods.quadsx[i_6_] + WallDecoration.anIntArray372[i_6_] ^ 0xffffffff) < (i ^ 0xffffffff) && (StaticMethods.quadsx[i_6_] ^ 0xffffffff) > (i + i_2_ ^ 0xffffffff) && Class55.anIntArray865[i_6_] + StaticMethods.anIntArray2286[i_6_] > i_5_ && StaticMethods.anIntArray2286[i_6_] < i_3_ + i_5_) {
				Class36.needs_clipping[i_6_] = true;
			}
		}
	}

	static final void method620(int i, int i_9_, int i_10_, int i_11_, int i_12_, int i_13_, int i_14_, int i_15_, int i_16_) {
		if (i_12_ == i_14_ && (i_16_ ^ 0xffffffff) == (i_13_ ^ 0xffffffff) && i_15_ == i_11_ && i == i_9_) {
			UpdateServerNode.method863(i_14_, i, i_10_, i_13_, (byte) -88, i_15_);
		} else {
			int i_17_ = i_13_;
			int i_18_ = i_14_;
			int i_19_ = 3 * i_14_;
			int i_20_ = i_13_ * 3;
			int i_21_ = 3 * i_12_;
			int i_22_ = 3 * i_11_;
			int i_23_ = i_21_ + -i_22_ + i_15_ - i_14_;
			int i_24_ = 3 * i_16_;
			int i_25_ = i_9_ * 3;
			int i_26_ = -i_21_ + -i_21_ + i_22_ + i_19_;
			int i_27_ = -i_24_ + i_25_ - (i_24_ + -i_20_);
			int i_28_ = -i_13_ + -i_25_ + i - -i_24_;
			int i_29_ = -i_19_ + i_21_;
			int i_30_ = -i_20_ + i_24_;
			for (int i_31_ = 128; i_31_ <= 4096; i_31_ += 128) {
				int i_32_ = i_31_ * i_31_ >> 12;
				int i_33_ = i_32_ * i_31_ >> 12;
				int i_34_ = i_33_ * i_23_;
				int i_35_ = i_32_ * i_26_;
				int i_36_ = i_27_ * i_32_;
				int i_37_ = i_31_ * i_29_;
				int i_38_ = i_28_ * i_33_;
				int i_39_ = i_30_ * i_31_;
				int i_40_ = i_14_ + (i_34_ - (-i_35_ + -i_37_) >> 12);
				int i_41_ = i_13_ + (i_36_ + i_38_ + i_39_ >> 12);
				UpdateServerNode.method863(i_18_, i_41_, i_10_, i_17_, (byte) -88, i_40_);
				i_17_ = i_41_;
				i_18_ = i_40_;
			}
		}
	}

	public static void method622(int i) {
		aClass16_3714 = null;
		hint_headicons = null;
		LocTypeList.objectContainer = null;
		packet = null;
	}


	static {
		StaticMethods2.packet = new ISAACPacket(5000);
	}
	public static RSString aClass16_3762;
	public static int anInt3781;
	public static RSString aClass16_3739 = RSString.createString("sch-Utteln:");
	public static RSString aClass16_3805;
	static {
		anInt3781 = 0;
		aClass16_3805 = RSString.createString(" ");
		StaticMethods2.aClass16_3762 = aClass16_3805;
	}
	public static Sprite[] hint_mapmarkers;
	public static short[] result_id_buffer;
	public static RSString aClass16_2010;
	public static int anInt2012 = 2301979;
	public static int[] anIntArray2039;
	static {
		StaticMethods2.aClass16_2010 = RSString.createString("Okay");
		StaticMethods2.anIntArray2039 = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };
	}

	public static void destruct() {
		result_id_buffer = null;
		StaticMedia.compass = null;
		anIntArray2039 = null;
		aClass16_2010 = null;
		hint_mapmarkers = null;
		com.rs2.client.scene.Scene.current_grounds = null;
	}

	static final void method233(byte b, Js5 overlayCache, Js5 modelCache) {
		Queuable.aClass105_2312 = modelCache;
		PlayerIdentityKit.identityKitContainer = overlayCache;
		PlayerIdentityKit.identityKitAmount = PlayerIdentityKit.identityKitContainer.get_file_count(3);
	}

	static final boolean method232(int i, int i_3_) {
		if (i < 48 || i > 57) {
			return false;
		}
		return true;
	}

	static final boolean method231(int i) {
		if ((0x374ce055 & i) >> 29 == 0) {
			return false;
		}
		return true;
	}

	static final void method234(SceneNode abstractModel, int i, int i_6_, int i_7_) {
		if (i_6_ < Scene.width) {
			Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[i][i_6_ + 1][i_7_];
			if (class23_sub1 != null && class23_sub1.decoration != null && class23_sub1.decoration.node.method998()) {
				abstractModel.sharelight(class23_sub1.decoration.node, 128, 0, 0, true);
			}
		}
		if (i_7_ < Scene.width) {
			Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[i][i_6_][i_7_ + 1];
			if (class23_sub1 != null && class23_sub1.decoration != null && class23_sub1.decoration.node.method998()) {
				abstractModel.sharelight(class23_sub1.decoration.node, 0, 0, 128, true);
			}
		}
		if (i_6_ < Scene.width && i_7_ < Scene.height) {
			Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[i][i_6_ + 1][i_7_ + 1];
			if (class23_sub1 != null && class23_sub1.decoration != null && class23_sub1.decoration.node.method998()) {
				abstractModel.sharelight(class23_sub1.decoration.node, 128, 0, 128, true);
			}
		}
		if (i_6_ < Scene.width && i_7_ > 0) {
			Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[i][i_6_ + 1][i_7_ - 1];
			if (class23_sub1 != null && class23_sub1.decoration != null && class23_sub1.decoration.node.method998()) {
				abstractModel.sharelight(class23_sub1.decoration.node, 128, 0, -128, true);
			}
		}
	}

	public static void method1118(boolean bool) {
		QuickChatDefinition.localQuickChatContainer = null;
		if (bool != true) {
			method1118(true);
		}
	}

	public static int anInt666 = 0;
	static RSString aClass16_1421;
	static RSString aClass16_1423;
	public static int anInt1417;
	static RSString aClass16_1412;
	static short aShort1411 = 320;
	static RSString aClass16_1408;
	static {
		aClass16_1408 = RSString.createString("flash3:");
		aClass16_1412 = RSString.createString("Musik)2Engine vorbereitet)3");
		anInt1417 = 0;
		aClass16_1421 = aClass16_1408;
		aClass16_1423 = aClass16_1408;
	}

	public static void method1349(int i) {
		StaticMethods2.aClass16_1423 = null;
		StaticMethods2.aClass16_1412 = null;
		StaticMethods2.aClass16_1408 = null;
		StaticMethods2.aClass16_1421 = null;
		GameClient.index_files = null;
	}

	static final void method1358(int i, int i_159_, int i_160_, int i_161_, int i_162_) {
		if (i_160_ < VarpDefinition.anInt3728 || Class35.anInt554 < i_161_ || (Class88.anInt1503 ^ 0xffffffff) < (i ^ 0xffffffff) || i_159_ > StaticMethods.anInt3435) {
			Class100.method1503(i, i_160_, i_162_, i_159_, true, i_161_);
		} else {
			StaticMethods.method418(i_160_, i_162_, i_161_, 0, i_159_, i);
		}
	}

	public static int[] anIntArray2768 = new int[5];

	static final void method1351(int i, int i_0_, int i_1_, int i_2_, byte b, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_) {
		if (i_0_ != i_6_ || i_2_ != i_1_ || (i_7_ ^ 0xffffffff) != (i_5_ ^ 0xffffffff) || (i ^ 0xffffffff) != (i_4_ ^ 0xffffffff)) {
			int i_8_ = i_6_;
			int i_9_ = i_2_;
			int i_10_ = i_6_ * 3;
			int i_11_ = 3 * i_1_;
			int i_12_ = i_0_ * 3;
			int i_13_ = i_2_ * 3;
			int i_14_ = 3 * i_7_;
			int i_15_ = -i_6_ + i_12_ + -i_14_ + i_5_;
			int i_16_ = 3 * i;
			int i_17_ = i_11_ + i_4_ + -i_16_ - i_2_;
			int i_18_ = i_10_ + -i_12_ + i_14_ - i_12_;
			int i_19_ = i_13_ + i_16_ - (i_11_ + i_11_);
			int i_20_ = i_12_ - i_10_;
			int i_21_ = -i_13_ + i_11_;
			for (int i_22_ = 128; i_22_ <= 4096; i_22_ += 128) {
				int i_23_ = i_22_ * i_22_ >> 12;
				int i_24_ = i_23_ * i_22_ >> 12;
				int i_25_ = i_15_ * i_24_;
				int i_26_ = i_17_ * i_24_;
				int i_27_ = i_19_ * i_23_;
				int i_28_ = i_23_ * i_18_;
				int i_29_ = i_21_ * i_22_;
				int i_30_ = i_20_ * i_22_;
				int i_31_ = (i_26_ + i_27_ - -i_29_ >> 12) + i_2_;
				int i_32_ = i_6_ + (i_30_ + i_25_ - -i_28_ >> 12);
				Class55.method1180(i_31_, i_32_, i_9_, i_8_, i_3_, (byte) 126);
				i_8_ = i_32_;
				i_9_ = i_31_;
			}
		} else {
			Class55.method1180(i_4_, i_5_, i_2_, i_6_, i_3_, (byte) 121);
		}
		if (b < 37) {
			StaticMedia.hitmarks = null;
		}
	}

	static final RSString method1354(int i, int i_143_) {
		return RSString.joinRsStrings(new RSString[] { RSString.valueOf(0xff & i_143_ >> 24), RuntimeException_Sub1.aClass16_1849, RSString.valueOf(i_143_ >> 16 & 0xff), RuntimeException_Sub1.aClass16_1849, RSString.valueOf((0xff8d & i_143_) >> 8), RuntimeException_Sub1.aClass16_1849, RSString.valueOf(i_143_ & 0xff) });
	}

	static final void method1344(RSString class16, RSString class16_22_, int i, RSString class16_23_, int i_24_) {
		StaticMethods2.sendPublicChat(i, i_24_, class16_22_, class16, class16_23_, (byte) -123);
	}

	public static void method1357() {
		StaticMedia.hitmarks = null;
		anIntArray2768 = null;
	}

	public static int[] anIntArray2401 = { 1, 1, 1, 1 };
	static RSString aClass16_2404 = RSString.createString("M");

	public static void method885(int i) {
		if (i == 1) {
			aClass16_2404 = null;
			anIntArray2401 = null;
		}
	}

	public static final void method888(int i, int i_6_, int i_7_, int i_8_, int i_9_, int i_10_, int i_11_, int i_12_) {
		int i_13_ = StaticMethods.method405(117, Class88.anInt1503, i_12_, StaticMethods.anInt3435);
		int i_14_ = StaticMethods.method405(89, Class88.anInt1503, i_9_, StaticMethods.anInt3435);
		int i_15_ = StaticMethods.method405(86, VarpDefinition.anInt3728, i_8_, Class35.anInt554);
		int i_16_ = StaticMethods.method405(110, VarpDefinition.anInt3728, i_6_, Class35.anInt554);
		int i_17_ = StaticMethods.method405(72, Class88.anInt1503, i_12_ + i_11_, StaticMethods.anInt3435);
		int i_18_ = StaticMethods.method405(93, Class88.anInt1503, i_9_ - i_11_, StaticMethods.anInt3435);
		for (int i_19_ = i_13_; (i_19_ ^ 0xffffffff) > (i_17_ ^ 0xffffffff); i_19_++) {
			VarpDefinition.method632(i_16_, (byte) -30, i, Class4.anIntArrayArray98[i_19_], i_15_);
		}
		for (int i_20_ = i_14_; i_18_ < i_20_; i_20_--) {
			VarpDefinition.method632(i_16_, (byte) -30, i, Class4.anIntArrayArray98[i_20_], i_15_);
		}
		int i_21_ = StaticMethods.method405(i_7_ + 79, VarpDefinition.anInt3728, i_8_ + i_11_, Class35.anInt554);
		int i_22_ = StaticMethods.method405(81, VarpDefinition.anInt3728, i_6_ + -i_11_, Class35.anInt554);
		for (int i_23_ = i_17_; i_23_ <= i_18_; i_23_++) {
			int[] is = Class4.anIntArrayArray98[i_23_];
			VarpDefinition.method632(i_21_, (byte) -30, i, is, i_15_);
			VarpDefinition.method632(i_22_, (byte) -30, i_10_, is, i_21_);
			VarpDefinition.method632(i_16_, (byte) -30, i, is, i_22_);
		}
	}

	static final void method230(byte b) {
		if (b > 115) {
			synchronized (InputManager.keyboard) {
				Class79.idleKeyTicks++;
				ColourImageCache.anInt1724 = StaticMethods.anInt3011;
				if (InputManager.anInt2986 >= 0) {
					while (InputManager.anInt2986 != Queuable.anInt2309) {
						int i = GameTimer.keyCodesPressed[Queuable.anInt2309];
						Queuable.anInt2309 = 1 + Queuable.anInt2309 & 0x7f;
						if (i >= 0) {
							StaticMethods2.keysPressed[i] = true;
						} else {
							StaticMethods2.keysPressed[i ^ 0xffffffff] = false;
						}
					}
				} else {
					for (int i = 0; i < 112; i++) {
						StaticMethods2.keysPressed[i] = false;
					}
					InputManager.anInt2986 = Queuable.anInt2309;
				}
				StaticMethods.anInt3011 = NameHashTable.anInt1202;
			}
		}
	}

	public static void method229(byte b) {
		StaticMedia.mapfunction_raw = null;
		StaticMethods2.aClass16_351 = null;
		StaticMethods2.aClass53_347 = null;
		if (b != -11) {
			method229((byte) -75);
		}
	}

	static RSString aClass16_351 = RSString.createString("null");
	static Class53 aClass53_347;

	static final void sendPublicChat(int chatType, int chatID, RSString chatMessage, RSString class16_1_, RSString userDisplay, byte b) {
		// if (userDisplay != null && userDisplay.toString().split("img=").length > 2) {
		// return;
		// }
		if (userDisplay == null) {
			return;
		}
		for (int i_3_ = 99; (i_3_ ^ 0xffffffff) < -1; i_3_--) {
			Queue.chatTypes[i_3_] = Queue.chatTypes[-1 + i_3_];
			UpdateServerNode.chatUsers[i_3_] = UpdateServerNode.chatUsers[-1 + i_3_];
			CollisionMap.chatMessages[i_3_] = CollisionMap.chatMessages[i_3_ + -1];
			Class23_Sub16.aClass16Array2357[i_3_] = Class23_Sub16.aClass16Array2357[i_3_ - 1];
			chatIDS[i_3_] = chatIDS[-1 + i_3_];
		}
		StringNode.anInt2473 = SomeSoundClass.anInt3589;
		Class56.chatMessageCount++;
		UpdateServerNode.chatUsers[0] = userDisplay;
		CollisionMap.chatMessages[0] = chatMessage;
		Class23_Sub16.aClass16Array2357[0] = class16_1_;
		Queue.chatTypes[0] = chatType;
		chatIDS[0] = chatID;
	}

	public static RSString aClass16_3886 = RSString.createString("::qa_op_test");
	public static RSString aClass16_3888;
	public static SoftwareSprite aClass23_Sub13_Sub10_Sub1_3880;
	public static RSString aClass16_3875;
	public static int anInt3868;

	public static final void method693(int i) {
		if (!ContextMenu.menuOpen) {
			ContextMenu.menuActionID[0] = (short) 1006;
			BufferedRequest.aClass16Array4307[0] = StaticMethods.aClass16_3338;
			Class98.aClass16Array1655[0] = StaticMethods.empty_string;
			ContextMenu.menuActionRow = 1;
		}
		if ((GameClient.interface_top_id ^ 0xffffffff) != 0) {
			Class87_Sub4.method1427(GameClient.interface_top_id, false);
		}
		if (i == 25602) {
			for (int i_3_ = 0; (i_3_ ^ 0xffffffff) > (StaticMethods.widget_quads ^ 0xffffffff); i_3_++) {
				if (RSInterfaceList.is_dirty[i_3_]) {
					Class36.needs_clipping[i_3_] = true;
				}
				StaticMethods.aBooleanArray3325[i_3_] = RSInterfaceList.is_dirty[i_3_];
				RSInterfaceList.is_dirty[i_3_] = false;
			}
			Scene.scene_draw_x = -1;
			Scene.scene_draw_y = -1;
			if (GLManager.opengl_mode) {
				Scene.opengl_scene_dirty = true;
			}
			StaticMethods.anInt3120 = GameClient.timer;
			CS2ScriptDefinition.aClass64_4257 = null;
			if (GameClient.interface_top_id != -1) {
				StaticMethods.widget_quads = 0;
				StringNode.method909(0, 0, GameShell.window_height, -1, (byte) 21, 0, GameShell.window_width, GameClient.interface_top_id, 0);
			}
			if (GLManager.opengl_mode) {
				GLState.reset_clipping();
			} else {
				Rasterizer2D.method200();
			}
			Class35.method979(false);
			if (!ContextMenu.menuOpen) {
				if ((Scene.scene_draw_x ^ 0xffffffff) != 0) {
					GameClient.drawToolTip(Scene.scene_draw_y, (byte) -64, Scene.scene_draw_x);
				}
			} else {
				ContextMenu.drawMenu();
			}
			if (Class57.anInt901 == 3) {
				for (int i_4_ = 0; (StaticMethods.widget_quads ^ 0xffffffff) < (i_4_ ^ 0xffffffff); i_4_++) {
					if (!StaticMethods.aBooleanArray3325[i_4_]) {
						if (Class36.needs_clipping[i_4_]) {
							if (GLManager.opengl_mode) {
								GLShapes.fill_rectangle(StaticMethods.quadsx[i_4_], StaticMethods.anIntArray2286[i_4_], WallDecoration.anIntArray372[i_4_], Class55.anIntArray865[i_4_], 16711680, 128);
							} else {
								Rasterizer2D.fill_rectangle(StaticMethods.quadsx[i_4_], StaticMethods.anIntArray2286[i_4_], WallDecoration.anIntArray372[i_4_], Class55.anIntArray865[i_4_], 16711680, 128);
							}
						}
					} else {
						if (GLManager.opengl_mode) {
							GLShapes.fill_rectangle(StaticMethods.quadsx[i_4_], StaticMethods.anIntArray2286[i_4_], WallDecoration.anIntArray372[i_4_], Class55.anIntArray865[i_4_], 16711935, 128);
						} else {
							Rasterizer2D.fill_rectangle(StaticMethods.quadsx[i_4_], StaticMethods.anIntArray2286[i_4_], WallDecoration.anIntArray372[i_4_], Class55.anIntArray865[i_4_], 16711935, 128);
						}

					}
				}
			}
			IoSession.method970((byte) -106, InterfaceNode.anInt2459, GameClient.currentPlayer.bound_extents_x, GameClient.currentPlayer.bound_extents_z, ObjType.localHeight);
			InterfaceNode.anInt2459 = 0;
		}
	}

	static {
		StaticMethods2.aClass16_3875 = RSString.createString(")1p");
		StaticMethods2.aClass16_3888 = RSString.createString(")2");
	}

	public static void method702(int i) {
		aClass23_Sub13_Sub10_Sub1_3880 = null;
		com.jagex.MapLoader.underlays = null;
		aClass16_3886 = null;
		aClass16_3875 = null;
		aClass16_3888 = null;
	}

	static PlayerAppearance aPlayerAppearance_1440;
	public static NodeDeque[][][] groundItems;
	public static int[] anIntArray1454;
	public static int anInt4487 = 3353893;
	static RSString aClass16_4490;
	static boolean[] keysPressed = new boolean[112];
	public static int[] anIntArray4496;

	static final void method682(int i) {
		PlayerIdentityKit.identityKitMap.clear();
	}

	static final void method683(int i, int i_43_, int i_44_, int i_45_, int i_46_) {
		if (i_45_ == -1) {
			if ((VarpDefinition.anInt3728 ^ 0xffffffff) >= (i_46_ ^ 0xffffffff) && i_46_ <= Class35.anInt554) {
				i = StaticMethods.method405(105, Class88.anInt1503, i, StaticMethods.anInt3435);
				i_44_ = StaticMethods.method405(100, Class88.anInt1503, i_44_, StaticMethods.anInt3435);
				ISAACCipher.method1264(i_43_, i_44_, i, i_46_, (byte) -31);
			}
		}
	}

	static {
		StaticMethods2.aClass16_4490 = RSString.createString("und haben es deaktiviert)3 Klicken Sie auf der");
		StaticMethods2.anIntArray4496 = new int[] { 16776960, 16711680, 65280, 65535, 16711935, 16777215 };
	}

	public static void method680(int i) {
		anIntArray4496 = null;
		keysPressed = null;
		StaticMedia.mapflag = null;
		if (i > 62) {
			aClass16_4490 = null;
		}
	}

	static RSString aClass16_4221 = RSString.createString("auf der Hautpseite)3");
	static RSString aClass16_4212;

	static final void method834(RSString class16, byte b) {
		try {
			GameShell.active_gameshell.getAppletContext().showDocument(class16.method181(GameShell.active_gameshell.getCodeBase()), "_blank");
		} catch (Exception exception) {
			/* empty */
			exception.printStackTrace();
		}
	}

	public static void method841(int i) {
		aClass16_4221 = null;
		aClass16_4212 = null;
	}

	public static void method1073(byte b) {
		if (b != -113) {
			method1073((byte) -112);
		}
		ContextMenu.menuActionID = null;
		StaticMethods.anIntArray2597 = null;
		StaticMethods.aClass16_2589 = null;
	}

	public static void aMethod498(ISAACPacket buffer) {
		buffer.putString(VertexNormal.aClass16_1543, -80);
	}

	public static void method1514(int i) {
		StaticMethods2.aClass16_1714 = null;
		StaticMethods2.aClass16_1708 = null;
		if (i >= -87) {
			method1514(84);
		}
		StaticMethods2.anIntArray1710 = null;
		StaticMethods2.aClass16_1709 = null;
		StaticMethods2.aClass16_1711 = null;
	}

	static final boolean setSoundEffectsCache(Js5 soundsWorker, SomeSoundClass someSoundClass, Js5 instrumentsWorker, int i, Js5 soundEffectsWorker) {
		if (i >= -52) {
			StaticMethods2.aClass16_1709 = null;
		}
		GameClient.instrumentsContainer = instrumentsWorker;
		ModelList.aSomeSoundClass_1437 = someSoundClass;
		StaticMethods.soundEffectsContainer = soundEffectsWorker;
		Class44.soundsContainer = soundsWorker;
		return true;
	}

	public static RSString aClass16_1708 = RSString.createString(GameLaunch.getSetting().getName() + " is loading )2 please wait)3)3)3");

	static {
		aClass16_1709 = aClass16_1708;
		aClass16_1711 = RSString.createString("Hidden)2use");
		anInt1712 = -1;
		aClass16_1714 = RSString.createString("leuchten3:");
	}
	public static int anInt1712;
	static RSString aClass16_1714;
	public static int[] anIntArray1710;
	static RSString aClass16_1711;
	static RSString aClass16_1709;

	static void method757(int windowsPane, int i_0_, boolean bool) {
		if (AbstractTimer.hasActiveInterface(-10924, windowsPane)) {
			Class71_Sub2_Sub1.method1282(0, StaticMethods.cached_interfaces[windowsPane], i_0_);
		}
	}
}
