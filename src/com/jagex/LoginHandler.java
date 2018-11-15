package com.jagex;

import java.awt.Component;
import java.io.IOException;
import java.net.Socket;

import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.jagex.launcher.Configurations;
import com.jagex.launcher.Properties;

/**
 * Created by Chris on 3/11/2017.
 */
public class LoginHandler extends LoginResponses {

	public static RSString emptyString = RSString.createString("");
	public static RSString middleLoginText = RSString.createString("");
	public static RSString usernameField = RSString.createString("Chris");
	public static RSString passwordField = RSString.createString("bean");
	public static RSString logoArchiveName = RSString.createString("logo");
	static RSString loginBoxArchiveName = RSString.createString("titlebox");
	static Sprite backgroundSprite = null;
	static Sprite flippedBackgroundHalf;
	public static PaletteSprite login_button_unhovered;
	public static PaletteSprite login_button_hovered;
	public static PaletteSprite login_button;
	public static PaletteSprite world_switch_button_unhovered;
	public static PaletteSprite world_switch_button_hovered;
	public static PaletteSprite world_switch_button;

	static PaletteSprite primary_login_box;
	static PaletteSprite alternate_login_box;
	static boolean musicDisabled;
	public static int loginMusicID = -1;
	public static int flameTick = 0;
	public static int activeLoginTextField;
	public static int loginConnectionState = 0;
	static SignlinkRequest loginRequest;
	static RSString topLoginText;
	static RSString bottomLoginText;
	static PaletteSprite[] toggleMusicSprites = new PaletteSprite[3];;
	public static int logoArchiveID = -1;
	public static int loginBoxArchiveID = -1;
	public static int backgroundArchiveID = -1;
	public static int musicToggleArchiveID = -1;
	public static int loginScreenState = 2;
	public static RSString enterUsernameAndPasswordText = RSString.createString("Enter your username (V password)3");
	static boolean musicHovered = false;

	public static void update_loginscreen(GameShell applet) {
		if (LobbyWorld.showWorldList) {
			LobbyWorld.handleLobbyWorldFunction(applet);
		} else {
			int clickState = Mouse.mouseClickState;
			int clickX = Mouse.mouseClickX;
			int clickY = Mouse.mouseClickY;
			int hoverX = Mouse.mouseX;
			int hoverY = Mouse.mouseY;
			if (hoverX >= 703 && hoverX < 760 && hoverY >= 457 && hoverY < 498) {
				musicHovered = true;
			} else {
				musicHovered = false;
			}
			if (clickState == 1 && clickX >= 703 && clickX < 760 && clickY >= 457 && clickY < 498) {
				musicDisabled = !musicDisabled;
				Properties.get().setPlayThemeSong(!musicDisabled);
				Properties.get().save();
				if (!musicDisabled) {
					MusicPlayer.updateCurrentMusic(loginMusicID, CacheConstants.musicCacheIdx, 1, 0, 255, false);
				} else {
					MusicPlayer.stopMusic(false);
				}
			}
			if (GameClient.clientState != 5) {
				flameTick++;
				if (GameClient.clientState == 10) {
					if (Class47.anInt741 != 2) {
						int i = 5;
						int i_0_ = 109;
						int i_1_ = 457;
						int i_2_ = 41;
						if (hoverX >= i && i + i_0_ >= hoverX && i_1_ <= hoverY && hoverY <= i_1_ + i_2_) {
							world_switch_button = world_switch_button_hovered;
						} else {
							world_switch_button = world_switch_button_unhovered;
						}
						if (clickState == 1) {
							if (clickX >= i && i + i_0_ >= clickX && i_1_ <= clickY && clickY <= i_1_ + i_2_) {
								LobbyWorld.parseWorldList();
								return;
							}
						}
						if (StaticMethods2.aClass53_347 != null) {
							LobbyWorld.parseWorldList();
						}
					}
					if (loginScreenState == 2) {
						int i_5_ = 254;
						int i_6_ = 304;
						int i_7_ = 385;
						if (clickState == 1 && clickX > 225 && clickX < 529 && clickY >= i_5_ - 44 && i_5_ > clickY) {
							activeLoginTextField = 0;
						}
						i_5_ += 58;
						if (clickState == 1 && clickX > 225 && clickX < 529 && clickY >= i_5_ - 44 && i_5_ > clickY) {
							activeLoginTextField = 1;
						}
						if (-75 + i_6_ <= hoverX && i_6_ + 34 >= hoverX && hoverY >= -20 + i_7_ && hoverY <= i_7_ - -20) {
							login_button = login_button_hovered;
						} else {
							login_button = login_button_unhovered;
						}
						if (clickState == 1 && -75 + i_6_ <= clickX && i_6_ + 34 >= clickX && clickY >= -20 + i_7_ && clickY <= i_7_ - -20) {
							usernameField = usernameField.method145().method154();
							if (usernameField.length() == 0) {
								updateLoginResponse(emptyString, SpotType.aClass16_4056, emptyString);
							} else if (passwordField.length() == 0) {
								updateLoginResponse(emptyString, CullingCluster.aClass16_926, emptyString);
							} else {
								updateLoginResponse(emptyString, BZIPContext.aClass16_1353, emptyString);
								GameClient.updateClientState(20);
							}
						} else {
							i_6_ = 462;
							if (clickState == 1 && -75 + i_6_ <= clickX && i_6_ + 75 >= clickX && clickY >= i_7_ - 20 && i_7_ + 20 >= clickY) {
								passwordField = SoundEffects.aClass16_2062;
								loginScreenState = 2;
								usernameField = SoundEffects.aClass16_2062;
							}
							while (CacheFileWorker.method1572(101 ^ 0x61d3)) {
								boolean bool = false;
								for (int i_8_ = 0; InvType.aClass16_4197.length() > i_8_; i_8_++) {
									if (Class53.anInt833 == InvType.aClass16_4197.charAt(i_8_)) {
										bool = true;
										break;
									}
								}
								if (activeLoginTextField != 0) {
									if (activeLoginTextField == 1) {
										if (Class88.keyPressedID == 85 && passwordField.length() > 0) {
											passwordField = passwordField.substring(-1 + passwordField.length(), 0);
										}
										if (Class88.keyPressedID == 84 || Class88.keyPressedID == 80) {
											if (usernameField.length() == 0 || passwordField.length() == 0) {
												activeLoginTextField = 0;
											} else {
												updateLoginResponse(emptyString, BZIPContext.aClass16_1353, emptyString);
												GameClient.updateClientState(20);
											}
										}
										if (GameClient.getGameType() != 0 && Class88.keyPressedID == 84) {
											usernameField = usernameField.method145().method154();
											if (usernameField.length() == 0) {
												updateLoginResponse(emptyString, SpotType.aClass16_4056, emptyString);
											} else {
												if (passwordField.length() == 0) {
													updateLoginResponse(emptyString, CullingCluster.aClass16_926, emptyString);
												} else {
													updateLoginResponse(emptyString, BZIPContext.aClass16_1353, emptyString);
													GameClient.updateClientState(20);
													break;
												}
												break;
											}
											break;
										}
										if (bool && passwordField.length() < 20) {
											passwordField = passwordField.method165(Class53.anInt833);
										}
									}
								} else {
									if (Class88.keyPressedID == 85 && usernameField.length() > 0) {
										usernameField = usernameField.substring(usernameField.length() - 1, 0);
									}
									if (Class88.keyPressedID == 84 || Class88.keyPressedID == 80) {
										activeLoginTextField = 1;
									}
									if (bool && usernameField.length() < 12) {
										usernameField = usernameField.method165(Class53.anInt833);
									}
								}
							}
						}
					} else if (loginScreenState == 3) {
						int i_9_ = 382;
						int i_10_ = 321;
						if (clickState == 1 && clickX >= -75 + i_9_ && clickX <= 75 + i_9_ && clickY >= i_10_ + -20 && clickY <= i_10_ + 20) {
							loginScreenState = 2;
						}
					}
				}
			}
		}
	}

	public static void render_loginscreen(Font boldFont, Font normalFont) {
		if (LobbyWorld.showWorldList) {// TODO: fix world list
			LobbyWorld.render(normalFont, boldFont);
		} else {
			drawBackgroundImage();
			if (GameClient.clientState == 0 || GameClient.clientState == 5) {
				int x = 20;
				int y = 253 - x;
				boldFont.draw_text_centered(StaticMethods2.aClass16_1709, 382, -x + 245, 16777215, -1);
				Rasterizer2D.draw_rectangle(230, y, 304, 34, 6625444);
				Rasterizer2D.draw_rectangle(231, 1 + y, 302, 32, 0);
				Rasterizer2D.fill_rectangle(232, 2 + y, GameClient.gamePercentage * 3, 30, 6625444);
				Rasterizer2D.fill_rectangle(GameClient.gamePercentage * 3 + 232, 2 + y, 300 - 3 * GameClient.gamePercentage, 30, 0);
				boldFont.draw_text_centered(GameClient.loadingMessage, 382, 276 + -x, 16777215, -1);
			}
			if (GameClient.clientState == 20) {
				int x = 155;
				if (alternate_login_box != null) {
					alternate_login_box.draw(196, 5);
				} else {
					primary_login_box.draw(196, 5);
				}
				boldFont.draw_text_centered(topLoginText, 382, x, 6625444, 0);
				x += 15;
				boldFont.draw_text_centered(middleLoginText, 382, x, 6625444, 0);
				x += 15;
				boldFont.draw_text_centered(bottomLoginText, 382, x, 6625444, 0);
				x += 44;
				x += 10;
				boldFont.draw_text(RSString.joinRsStrings(new RSString[] { StringNode.usernameLabel, Font.escape_tags(usernameField) }), 240, x, 6625444, 0);
				x += 55;
				boldFont.draw_text(RSString.joinRsStrings(new RSString[] { Class97.passwordLabel, passwordField.method155() }), 240, x, 6625444, 0);
				x += 15;
			}
			if (GameClient.clientState == 10) {
				primary_login_box.draw(196, 5);
				if (loginScreenState == 2) {
					int textX = 155;
					boldFont.draw_text_centered(topLoginText, 382, textX, 6625444, 0);
					textX += 15;
					boldFont.draw_text_centered(middleLoginText, 382, textX, 6625444, 0);
					textX += 15;
					boldFont.draw_text_centered(bottomLoginText, 382, textX, 6625444, 0);
					boolean cursorTick;
					if (activeLoginTextField == 0 && GameClient.timer % 40 < 20 && GameShell.windowActive) {
						cursorTick = true;
					} else {
						cursorTick = false;
					}
					textX += 44;
					textX += 10;
					boldFont.draw_text(RSString.joinRsStrings(new RSString[] { StringNode.usernameLabel, Font.escape_tags(LoginHandler.usernameField), !cursorTick ? SoundEffects.aClass16_2062 : Class95.aClass16_1621 }), 240, textX, 6625444, 0);
					if (activeLoginTextField != 1 || GameClient.timer % 40 >= 20 || !GameShell.windowActive) {
						cursorTick = false;
					} else {
						cursorTick = true;
					}
					textX += 55;
					boldFont.draw_text(RSString.joinRsStrings(new RSString[] { Class97.passwordLabel, LoginHandler.passwordField.method155(), cursorTick ? Class95.aClass16_1621 : SoundEffects.aClass16_2062 }), 240, textX, 6625444, 0);
					textX += 15;
					login_button.draw(0, 5);
				} else if (loginScreenState == 3) {
					int i_11_ = 236;
					int i_12_ = 382;
					boldFont.draw_text_centered(Class87_Sub3.aClass16_2823, 382, 211, 16776960, 0);
					int i_13_ = 321;
					boldFont.draw_text_centered(StaticMethods.aClass16_2913, 382, i_11_, 16777215, 0);
					i_11_ += 15;
					boldFont.draw_text_centered(Queuable.aClass16_2306, 382, i_11_, 16777215, 0);
					i_11_ += 15;
					boldFont.draw_text_centered(Class88.aClass16_1505, 382, i_11_, 16777215, 0);
					i_11_ += 15;
					boldFont.draw_text_centered(Class23_Sub16.aClass16_2354, 382, i_11_, 16777215, 0);
					i_11_ += 15;
					login_button.draw(-73 + i_12_, -20 + i_13_);
					boldFont.draw_text_centered(StaticMethods.aClass16_3338, i_12_, i_13_ - -5, 16777215, 0);
				}
			}
			if (GameClient.getGameValue() != 1) {
				if (flameTick > 0) {
					flameTick = 0;
				}
			}
			if (toggleMusicSprites[!musicDisabled && Properties.get().isPlayThemeSong() ? musicHovered ? 2 : 0 : 1] != null) {
				toggleMusicSprites[!musicDisabled && Properties.get().isPlayThemeSong() ? musicHovered ? 2 : 0 : 1].draw(703, 457);
			}
			if (GameClient.clientState > 5 && Class47.anInt741 != 2) {
				if (world_switch_button == null) {
					world_switch_button = PaletteSprite.load(CacheConstants.sprites_js5, 4140, 0);
				}
				if (world_switch_button != null) {
					int i_14_ = 5;
					int i_15_ = 457;
					world_switch_button.draw(i_14_, i_15_);
					int i_16_ = 109;
					int i_17_ = 41;
					boldFont.draw_text_centered(RSString.joinRsStrings(new RSString[] { GrandExchangeOffer.aClass16_1539, Class97.aClass16_1647, RSString.valueOf(GameClient.getWorldId()) }), i_16_ / 2 + i_14_, i_17_ / 2 + i_15_ - 2, 6315611, 0);
					if (StaticMethods2.aClass53_347 != null) {
						normalFont.draw_text_centered(UpdateServerNode.aClass16_2326, i_14_ + i_16_ / 2, i_15_ + i_17_ / 2 + 12, 6315611, 0);// loading text on world button
					} else {
						normalFont.draw_text_centered(StaticMethods.aClass16_3068, i_14_ + i_16_ / 2, i_17_ / 2 + i_15_ - -12, 6315611, 0);// click to switch text
					}
				}
			}
		}
	}

	static void login() {
		try {
			if (loginConnectionState == 0) {
				if (ColourImageCacheSlot.session != null) {
					GameClient.pingManager.disconnect();
					ColourImageCacheSlot.session.shutdown();
					ColourImageCacheSlot.session = null;
				}
				StaticMethods2.anInt3781 = 0;
				loginRequest = null;
				loginConnectionState = 1;
				StaticMethods.aBoolean3012 = false;
			}
			if (loginConnectionState == 1) {
				if (loginRequest == null) {
					loginRequest = GameClient.gameSignlink.openSocket(GameClient.getIp(), GameClient.socket_port);
				}
				if (loginRequest.status == 2) {
					// throw new IOException();
					// System.err.println("Threw exception - Class42 login!");
				}
				if (loginRequest.status == 1) {
					ColourImageCacheSlot.session = new IoSession((Socket) loginRequest.result, GameClient.gameSignlink, 5000);
					loginConnectionState = 2;
					loginRequest = null;
				}
			}
			if (loginConnectionState == 2) {
				long l = Class88.currentUserLong = usernameField.toUsernameLong();
				GameClient.outBuffer.index = 0;
				GameClient.outBuffer.p1(14);
				int i = (int) (l >> 16 & 0x1fL);
				GameClient.outBuffer.p1(i);
				ColourImageCacheSlot.session.write(2, 0, GameClient.outBuffer.byteBuffer);
				StaticMethods2.packet.index = 0;
				loginConnectionState = 3;
			}
			if (loginConnectionState == 3) {
				if (SeekableFile.aStereo_471 != null) {
					SeekableFile.aStereo_471.method77(256);
				}
				if (Class97.aStereo_1646 != null) {
					Class97.aStereo_1646.method77(256);
				}
				int i = ColourImageCacheSlot.session.read((byte) 10);
				if (SeekableFile.aStereo_471 != null) {
					SeekableFile.aStereo_471.method77(256);
				}
				if (Class97.aStereo_1646 != null) {
					Class97.aStereo_1646.method77(256);
				}
				if (i != 0) {
					updateLoginState(i);
					return;
				}
				StaticMethods2.packet.index = 0;
				loginConnectionState = 4;
			}
			if (loginConnectionState == 4) {
				if (StaticMethods2.packet.index < 8) {
					int i = ColourImageCacheSlot.session.available(-1 ^ 0x41);
					if (-StaticMethods2.packet.index + 8 < i) {
						i = -StaticMethods2.packet.index + 8;
					}
					if ((i ^ 0xffffffff) < -1) {
						ColourImageCacheSlot.session.read(i, StaticMethods2.packet.index, StaticMethods2.packet.byteBuffer);
						StaticMethods2.packet.index += i;
					}
				}
				if (StaticMethods2.packet.index == 8) {
					StaticMethods2.packet.index = 0;
					Class87.aLong1489 = StaticMethods2.packet.getLong();
					loginConnectionState = 5;
				}
			}
			if (loginConnectionState == 5) {
				GameClient.outBuffer.index = 0;
				int[] is = new int[4];
				is[3] = (int) Class87.aLong1489;
				is[1] = (int) (9.9999999E7 * Math.random());
				is[2] = (int) (Class87.aLong1489 >> 32);
				is[0] = (int) (9.9999999E7 * Math.random());
				int[][] ibs = StaticMethods.method269((byte) 299);
				GameClient.outBuffer.p1(10);
				GameClient.outBuffer.p4(is[0]);
				GameClient.outBuffer.p4(is[1]);
				GameClient.outBuffer.p4(is[2]);
				GameClient.outBuffer.p4(is[3]);
				GameClient.outBuffer.putLong(usernameField.toUsernameLong(), (byte) -126);
				GameClient.outBuffer.putString(passwordField, -113);
				Class105_Sub2.method1431(GameClient.outBuffer);
				GameClient.outBuffer.p1(0);// o
				GameClient.outBuffer.applyRSA(117, Configurations.PUBLIC_EXPONENT, Configurations.MODULUS);
				ObjectNode.aClass23_Sub5_Sub1_2230.index = 0;
				if (GameClient.clientState == 40) {
					ObjectNode.aClass23_Sub5_Sub1_2230.p1(18);
				} else {
					ObjectNode.aClass23_Sub5_Sub1_2230.p1(16);
				}
				ObjectNode.aClass23_Sub5_Sub1_2230.p1(137 + GameClient.outBuffer.index + LobbyWorld.method1372(CacheFileWorker.aClass16_2877));
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(Configurations.CLIENT_BUILD);
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(Configurations.SUB_BUILD);
				// TODO: remove this from the login protocol in the server side, it was low_details in-before.
				ObjectNode.aClass23_Sub5_Sub1_2230.p1(1);
				ibs[0][0] = 1;
				Packet.writeRandomData(ObjectNode.aClass23_Sub5_Sub1_2230);
				ObjectNode.aClass23_Sub5_Sub1_2230.putString(CacheFileWorker.aClass16_2877, -99);
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(0);
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.animFramesCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.animSkinsCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.configs_js5.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.interfaceCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.soundsCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.map_js5.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.musicCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.modelCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.sprites_js5.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.textureCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.huffmanCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.extraMusicCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.scriptCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.fonts_js5.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.instrumentCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.soundEffectsCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.locCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.scriptMapIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.npcCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.itemCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.animSequenceCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.graphicCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.varbitCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.worldMapCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.localQuickChatIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.globalQuickChatIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.p4(CacheConstants.materialsCacheIdx.get_index_crc32());
				ObjectNode.aClass23_Sub5_Sub1_2230.putBytes(0, 71, GameClient.outBuffer.byteBuffer, GameClient.outBuffer.index);
				ColourImageCacheSlot.session.write(ObjectNode.aClass23_Sub5_Sub1_2230.index, 0, ObjectNode.aClass23_Sub5_Sub1_2230.byteBuffer);
				GameClient.outBuffer.initialize_cypher((byte) -127, is);
				for (int i = 0; i < 4; i++) {
					is[i] += 50;
				}
				StaticMethods2.packet.initialize_cypher((byte) -99, is);
				loginConnectionState = 6;
			}
			if (loginConnectionState == 6 && ColourImageCacheSlot.session.available(-55) > 0) {
				int i = ColourImageCacheSlot.session.read((byte) 10);
				if (i != 21 || GameClient.clientState != 20) {
					if (i == 2) {
						loginConnectionState = 9;
					} else {
						if (i == 15 && GameClient.clientState == 40) {
							ModelList.method1368((byte) 41);
							return;
						}
						if (i != 23 || CacheFileWorker.anInt2854 >= 1) {
							updateLoginState(i);
							return;
						}
						CacheFileWorker.anInt2854++;
						loginConnectionState = 0;
					}
				} else {
					loginConnectionState = 7;
				}
			}
			if (loginConnectionState == 7 && (ColourImageCacheSlot.session.available(-73) ^ 0xffffffff) < -1) {
				Class48.anInt744 = 60 * (ColourImageCacheSlot.session.read((byte) 10) + 3);
				loginConnectionState = 8;
			} else if (loginConnectionState == 7) {
				System.out.println("No bytes available!");
			}
			if (loginConnectionState == 8) {
				StaticMethods2.anInt3781 = 0;
				updateLoginResponse(RSString.joinRsStrings(new RSString[] { RSString.valueOf(Class48.anInt744 / 60), Class36.aClass16_570 }), BZIPContext.aClass16_1340, Class57.aClass16_898);
				if (--Class48.anInt744 <= 0) {
					loginConnectionState = 0;
				}
				return;
			}
			if (loginConnectionState == 9 && ColourImageCacheSlot.session.available(-76) >= 9) {
				GameClient.rights = ColourImageCacheSlot.session.read((byte) 10); // Rights?
				StaticMethods.anInt3470 = ColourImageCacheSlot.session.read((byte) 10);
				Class67.anInt1176 = ColourImageCacheSlot.session.read((byte) 10);
				StaticMethods.anInt3075 = ColourImageCacheSlot.session.read((byte) 10);
				StaticMethods.aBoolean3380 = ColourImageCacheSlot.session.read((byte) 10) == 1;
				StaticMethods.thisPlayerIndex = ColourImageCacheSlot.session.read((byte) 10);
				StaticMethods.thisPlayerIndex <<= 8;
				StaticMethods.thisPlayerIndex += ColourImageCacheSlot.session.read((byte) 10);
				Class30.anInt478 = ColourImageCacheSlot.session.read((byte) 10);
				ColourImageCacheSlot.session.read(1, 0, StaticMethods2.packet.byteBuffer);
				StaticMethods2.packet.index = 0;
				PacketParser.currentOpcode = StaticMethods2.packet.getIncomingOpcode((byte) 47);
				ColourImageCacheSlot.session.read(2, 0, StaticMethods2.packet.byteBuffer);
				StaticMethods2.packet.index = 0;
				StaticMethods.currentLength = StaticMethods2.packet.g2();
				loginConnectionState = 10;
			}
			if (loginConnectionState == 10) {
				if (ColourImageCacheSlot.session.available(-1 ^ 0xc) >= StaticMethods.currentLength) {
					StaticMethods2.packet.index = 0;
					ColourImageCacheSlot.session.read(StaticMethods.currentLength, 0, StaticMethods2.packet.byteBuffer);
					ClanChatMember.reset(0);
					StaticMethods.anInt3279 = -1;
					MapLoader.decode_server_request(false);
					PacketParser.currentOpcode = -1;
				}
				return;
			}
			StaticMethods2.anInt3781++;
			if (StaticMethods2.anInt3781 > 2000) {
				if (CacheFileWorker.anInt2854 < 1) {
					if ((Queuable.anInt2320 ^ 0xffffffff) == (GameClient.socket_port ^ 0xffffffff)) {
						GameClient.socket_port = GroundItem.anInt2498;
					} else {
						GameClient.socket_port = Queuable.anInt2320;
					}
					CacheFileWorker.anInt2854++;
					loginConnectionState = 0;
				} else {
					updateLoginState(-3);
					return;
				}
				return;
			}
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
			if (CacheFileWorker.anInt2854 < 1) {
				CacheFileWorker.anInt2854++;
				if ((Queuable.anInt2320 ^ 0xffffffff) != (GameClient.socket_port ^ 0xffffffff)) {
					GameClient.socket_port = Queuable.anInt2320;
				} else {
					GameClient.socket_port = GroundItem.anInt2498;
				}
				loginConnectionState = 0;
			} else {
				updateLoginState(-2);
				return;
			}
			return;
		}
	}

	static void updateLoginState(int response) {
		if (response == 27 || response == 28 || response == 29) {
			if (response == 27) {
				updateLoginResponse(emptyString, nonCreatedAccount, emptyString);
				WebTools.openWebpage("http://zaros.rs/register/");
			} else if (response == 28) {
				updateLoginResponse(emptyString, nonValidatedAccount, emptyString);
			} else if (response == 29) {
				updateLoginResponse(emptyString, websiteError, emptyString);
			}
			GameClient.updateClientState(10);
			return;
		}
		if (response == -3) {
			updateLoginResponse(emptyString, worldError, timeOut);
		} else if (response != -2) {
			if (response == -1) {
				updateLoginResponse(emptyString, worldError, noResponse);
			} else if (response != 3) {
				if (response != 4) {
					if (response == 5) {
						updateLoginResponse(emptyString, tryAgain, alreadyLoggedIn);
					} else if (response == 6) {
						updateLoginResponse(emptyString, reloadPage, gameUpdated);
					} else if (response == 7) {
						updateLoginResponse(emptyString, worldError2, worldFull);
					} else if (response == 8) {
						updateLoginResponse(emptyString, serverOffline, cantConnect);
					} else if (response == 9) {
						updateLoginResponse(emptyString, tooManyConnection, loginLimit);
					} else if (response == 10) {
						updateLoginResponse(emptyString, badSession, cantConnect);
					} else if (response != 11) {
						if (response == 12) {
							updateLoginResponse(emptyString, subscribe, memberWorldOnly);
						} else if (response == 13) {
							updateLoginResponse(emptyString, worldError, cantComplete);
						} else if (response != 14) {
							if (response != 16) {
								if (response == 17) {
									updateLoginResponse(emptyString, notInFreeArea, inMemberAreaString);
								} else if (response != 18) {
									if (response == 19) {
										updateLoginResponse(worldError2, inviteOnly, closedBeta);
									} else if (response != 20) {
										if (response != 22) {
											if (response == 23) {
												updateLoginResponse(emptyString, waitOneMinute, noReply);
											} else if (response == 24) {
												updateLoginResponse(emptyString, contactSupport, profileError);
											} else if (response == 25) {
												updateLoginResponse(emptyString, worldError, unexpectedLoginResponse);
											} else if (response == 26) {
												updateLoginResponse(emptyString, ruleBreak, addressBlocked);
											} else if (response == 27) {
												updateLoginResponse(emptyString, serviceUnavailable, emptyString);
											} else {
												updateLoginResponse(emptyString, worldError, unexpectedServerResponse);
											}
										} else {
											updateLoginResponse(emptyString, pleaseTryAgain, malformedPacket);
										}
									} else {
										updateLoginResponse(emptyString, worldError, invalidServerRequest);
									}
								} else {
									updateLoginResponse(emptyString, lockedAccount, stolenAccount);
								}
							} else {
								updateLoginResponse(emptyString, waitFiveMinutes, tooManyLogins);
							}
						} else {
							updateLoginResponse(emptyString, waitOneMinute, serverUpdating);
						}
					} else {
						updateLoginResponse(emptyString, changePassword, stolenPassword);
					}
				} else {
					updateLoginResponse(emptyString, checkMessages, accountDisabled);
				}
			} else {
				updateLoginResponse(emptyString, invalidUsernamePassString, emptyString);
			}
		} else {
			updateLoginResponse(emptyString, errorConnecting, emptyString);
		}
		GameClient.updateClientState(10);
	}

	static void updateLoginResponse(RSString bottomLine, RSString middleLine, RSString topLine) {
		middleLoginText = middleLine;
		topLoginText = topLine;
		bottomLoginText = bottomLine;
	}

	static void prepareLoginComponents(Js5 huffman, Js5 spriteCache, Component component) {
		if (!GroundItem.loginscreen_loaded) {
			if (GLManager.opengl_mode) {
				GLState.clear_screen();
			} else {
				Rasterizer2D.clear();
			}
			SoftwareSprite background_sprite = new SoftwareSprite(null, component);
			if (GLManager.opengl_mode) {
				backgroundSprite = new OpenGLSprite(background_sprite);
			} else {
				backgroundSprite = background_sprite;
			}
			topLoginText = emptyString;
			bottomLoginText = emptyString;
			middleLoginText = enterUsernameAndPasswordText;
			primary_login_box = PaletteSprite.load(spriteCache, loginBoxArchiveID);
			alternate_login_box = PaletteSprite.load(spriteCache, 4139);
			login_button_unhovered = PaletteSprite.load(CacheConstants.sprites_js5, 498);
			login_button_hovered = PaletteSprite.load(CacheConstants.sprites_js5, 500);
			login_button = login_button_unhovered;
			world_switch_button_unhovered = PaletteSprite.load(CacheConstants.sprites_js5, 4140, 0);
			world_switch_button_hovered = PaletteSprite.load(CacheConstants.sprites_js5, 4141, 0);
			world_switch_button = login_button_unhovered;
			CollisionMap.aIndexedSpriteArray1303 = StaticMethods.method370(spriteCache, false, SomeSoundClass.anInt3617);
			toggleMusicSprites[0] = PaletteSprite.load(spriteCache, 4142);
			toggleMusicSprites[1] = PaletteSprite.load(spriteCache, 4143);
			toggleMusicSprites[2] = PaletteSprite.load(spriteCache, 4144);
			ISAACPacket.anIntArray3543 = new int[256];
			for (int i_1_ = 0; i_1_ < 64; i_1_++) {
				ISAACPacket.anIntArray3543[i_1_] = i_1_ * 262144;
			}
			for (int i_2_ = 0; i_2_ < 64; i_2_++) {
				ISAACPacket.anIntArray3543[64 + i_2_] = 1024 * i_2_ + 16711680;
			}
			for (int i_3_ = 0; i_3_ < 64; i_3_++) {
				ISAACPacket.anIntArray3543[i_3_ - -128] = 16776960 + 4 * i_3_;
			}
			for (int i_4_ = 0; i_4_ < 64; i_4_++) {
				ISAACPacket.anIntArray3543[192 + i_4_] = 16777215;
			}
			InteractiveEntity.anIntArray618 = new int[256];
			for (int i_5_ = 0; i_5_ < 64; i_5_++) {
				InteractiveEntity.anIntArray618[i_5_] = i_5_ * 1024;
			}
			for (int i_6_ = 0; i_6_ < 64; i_6_++) {
				InteractiveEntity.anIntArray618[64 + i_6_] = 65280 - -(4 * i_6_);
			}
			for (int i_7_ = 0; i_7_ < 64; i_7_++) {
				InteractiveEntity.anIntArray618[128 + i_7_] = 65535 - -(262144 * i_7_);
			}
			for (int i_8_ = 0; i_8_ < 64; i_8_++) {
				InteractiveEntity.anIntArray618[i_8_ - -192] = 16777215;
			}
			Class35.anIntArray557 = new int[256];
			for (int i_9_ = 0; i_9_ < 64; i_9_++) {
				Class35.anIntArray557[i_9_] = 4 * i_9_;
			}
			for (int i_10_ = 0; i_10_ < 64; i_10_++) {
				Class35.anIntArray557[64 + i_10_] = 255 - -(i_10_ * 262144);
			}
			for (int i_11_ = 0; i_11_ < 64; i_11_++) {
				Class35.anIntArray557[128 + i_11_] = 1024 * i_11_ + 16711935;
			}
			for (int i_12_ = 0; i_12_ < 64; i_12_++) {
				Class35.anIntArray557[192 + i_12_] = 16777215;
			}
			Class87_Sub4.anIntArray2828 = new int[32768];
			Stereo.anIntArray144 = new int[256];
			Class28.anIntArray425 = new int[32768];
			StaticMethods.method424(null, (byte) 80);
			StaticMethods.anIntArray3224 = new int[32768];
			loginScreenState = 2;
			LobbyWorld.showWorldList = false;
			usernameField = SoundEffects.aClass16_2062;
			Class71_Sub2_Sub1.anIntArray4473 = new int[32768];
			passwordField = SoundEffects.aClass16_2062;
			if (Class21.anInt342 == 0) {
				musicDisabled = true;
			} else {
				musicDisabled = false;
			}
			if (!musicDisabled && Properties.get().isPlayThemeSong()) {
				MusicPlayer.playMusic(loginMusicID, 13910, 0, false, 2, CacheConstants.musicCacheIdx, 255);
			} else {
				StaticMethods.method333(-257, 2);
			}
			GameClient.js5_client.notify_login(0, false);
			Class49.aClass23_Sub13_Sub10_Sub1_754 = new SoftwareSprite(128, 254);
			StaticMethods2.aClass23_Sub13_Sub10_Sub1_3880 = new SoftwareSprite(128, 254);
			GroundItem.loginscreen_loaded = true;
		}
	}

	static void unpackLoginComponents(Js5 musicWorker, Js5 huffmanWorker, int i, Js5 spriteWorker) {
		backgroundArchiveID = huffmanWorker.get_groupid("title.jpg");
		logoArchiveID = spriteWorker.get_groupid("logo");
		loginBoxArchiveID = 499;
		StaticMethods.loginButtonArchiveID = spriteWorker.get_groupid("titlebutton");
		SomeSoundClass.anInt3617 = spriteWorker.get_groupid("runes");
		musicToggleArchiveID = spriteWorker.get_groupid("title_mute");
		GroundItemNode.anInt3671 = spriteWorker.get_groupid("sl_back");
		LobbyWorld.countryFlagsArchiveID = spriteWorker.get_groupid("sl_flags");
		LobbyWorld.worldListArrowArchive = spriteWorker.get_groupid("sl_arrows");
		Class71.starArchive = spriteWorker.get_groupid("sl_stars");
		CullingCluster.anInt935 = spriteWorker.get_groupid("sl_button");
		loginMusicID = musicWorker.get_groupid(Configurations.LOGIN_THEME_SONG);
	}

	static void drawBackgroundImage() {
		backgroundSprite.draw_clipped_left_anchor(0, 0);
	}

	static int get_completed_count(Js5 spriteWorker) {
		int i_43_ = 0;
		if (spriteWorker.is_group_cached(3100)) {
			i_43_++;
		}
		if (spriteWorker.is_group_cached(logoArchiveID)) {
			i_43_++;
		}
		if (spriteWorker.is_group_cached(loginBoxArchiveID)) {
			i_43_++;
		}
		if (spriteWorker.is_group_cached(StaticMethods.loginButtonArchiveID)) {
			i_43_++;
		}
		if (spriteWorker.is_group_cached(SomeSoundClass.anInt3617)) {
			i_43_++;
		}
		if (spriteWorker.is_group_cached(musicToggleArchiveID)) {
			i_43_++;
		}
		spriteWorker.is_group_cached(GroundItemNode.anInt3671);
		spriteWorker.is_group_cached(LobbyWorld.countryFlagsArchiveID);
		spriteWorker.is_group_cached(LobbyWorld.worldListArrowArchive);
		spriteWorker.is_group_cached(Class71.starArchive);
		spriteWorker.is_group_cached(CullingCluster.anInt935);

		spriteWorker.is_group_cached(4139);
		spriteWorker.is_group_cached(4140);
		spriteWorker.is_group_cached(4141);
		spriteWorker.is_group_cached(4142);
		spriteWorker.is_group_cached(4143);
		spriteWorker.is_group_cached(4144);

		return i_43_;
	}
}
