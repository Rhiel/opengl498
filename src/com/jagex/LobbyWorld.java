package com.jagex;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.launcher.Configurations;
import com.jagex.launcher.GameLaunch;
import com.rs2.client.scene.Scene;

public class LobbyWorld {
	public static boolean showWorldList = false;
	public static PaletteSprite[] members_star_sprites;
	public static PaletteSprite[] world_list_arrows_sprites;
	public static PaletteSprite[] country_flags_sprites;
	public static Sprite[] selectable_world_sprites;
	public static ArrayList<World> worlds = new ArrayList<World>();
	public static LobbyWorld[] worldLists;
	public static int worldSize;
	public static int worldSelectIndex = -1;
	public static int worldListArrowArchive;
	public static int countryFlagsArchiveID = -1;

	public int countryFlag;
	public int players;
	public int worldIndex;
	public RSString worldAddress;
	public boolean memberWorld;
	public int worldId;

	public static void render(Font normalFont, Font boldFont) {
		if (GLManager.opengl_mode) {
			GLShapes.fill_rectangle(0, 23, 765, 480, 0);
			GLShapes.fill_rectangle_gradient(0, 0, 138, 23, 12425273, 9135624);
			GLShapes.fill_rectangle_gradient(138, 0, 640, 23, 5197647, 2697513);
		} else {
			Rasterizer2D.fill_rectangle(0, 23, 765, 480, 0);
			Rasterizer2D.fill_rectangle_gradient(0, 0, 138, 23, 12425273, 9135624);
			Rasterizer2D.fill_rectangle_gradient(138, 0, 640, 23, 5197647, 2697513);
		}
		boldFont.draw_text_centered(Class97.aClass16_1644, 69, 15, 0, -1);
		if (LobbyWorld.members_star_sprites != null) {
			LobbyWorld.members_star_sprites[1].draw(140, 1);
			normalFont.draw_text(ClientInventory.aClass16_2365, 152, 10, 16777215, -1);
			LobbyWorld.members_star_sprites[0].draw(140, 12);
			normalFont.draw_text(StaticMethods.aClass16_3320, 152, 21, 16777215, -1);
		}
		if (LobbyWorld.world_list_arrows_sprites != null) {
			int arrowX3 = 610;
			int arrowX2 = 500;
			int x = 280;
			if (Class88.anIntArray1507[0] == 0 && (StaticMethods2.anIntArray2401[0] ^ 0xffffffff) == -1) {
				LobbyWorld.world_list_arrows_sprites[2].draw(x, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[0].draw(x, 4);
			}
			if ((Class88.anIntArray1507[0] ^ 0xffffffff) == -1 && StaticMethods2.anIntArray2401[0] == 1) {
				LobbyWorld.world_list_arrows_sprites[3].draw(x + 15, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[1].draw(x + 15, 4);
			}
			boldFont.draw_text(GrandExchangeOffer.aClass16_1529, x + 32, 17, 16777215, -1);
			int arrowX = 390;
			if (Class88.anIntArray1507[0] == 1 && (StaticMethods2.anIntArray2401[0] ^ 0xffffffff) == -1) {
				LobbyWorld.world_list_arrows_sprites[2].draw(arrowX, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[0].draw(arrowX, 4);
			}
			if (Class88.anIntArray1507[0] != 1 || StaticMethods2.anIntArray2401[0] != 1) {
				LobbyWorld.world_list_arrows_sprites[1].draw(15 + arrowX, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[3].draw(arrowX - -15, 4);
			}
			boldFont.draw_text(CullingCluster.aClass16_925, arrowX - -32, 17, 16777215, -1);
			if (Class88.anIntArray1507[0] == 2 && (StaticMethods2.anIntArray2401[0] ^ 0xffffffff) == -1) {
				LobbyWorld.world_list_arrows_sprites[2].draw(arrowX2, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[0].draw(arrowX2, 4);
			}
			if (Class88.anIntArray1507[0] == 2 && StaticMethods2.anIntArray2401[0] == 1) {
				LobbyWorld.world_list_arrows_sprites[3].draw(arrowX2 + 15, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[1].draw(15 + arrowX2, 4);
			}
			boldFont.draw_text(Class19.aClass16_323, arrowX2 + 32, 17, 16777215, -1);
			if (Class88.anIntArray1507[0] == 3 && StaticMethods2.anIntArray2401[0] == 0) {
				LobbyWorld.world_list_arrows_sprites[2].draw(arrowX3, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[0].draw(arrowX3, 4);
			}
			if (Class88.anIntArray1507[0] == 3 && StaticMethods2.anIntArray2401[0] == 1) {
				LobbyWorld.world_list_arrows_sprites[3].draw(15 + arrowX3, 4);
			} else {
				LobbyWorld.world_list_arrows_sprites[1].draw(15 + arrowX3, 4);
			}
			boldFont.draw_text(MemoryCache.aClass16_114, arrowX3 + 32, 17, 16777215, -1);
		}
		if (GLManager.opengl_mode) {
			GLShapes.fill_rectangle(700, 4, 58, 16, 0);
		} else {
			Rasterizer2D.fill_rectangle(700, 4, 58, 16, 0);
		}
		normalFont.draw_text_centered(StaticMethods.aClass16_3338, 729, 16, 16777215, -1);
		LobbyWorld.worldSelectIndex = -1;
		if (LobbyWorld.selectable_world_sprites != null) {
			int i = 88;
			int i_6_ = 19;
			int i_7_ = 765 / (1 + i);
			int i_8_ = 480 / (i_6_ + 1);
			int i_9_;
			int i_10_;
			do {
				i_9_ = i_8_;
				i_10_ = i_7_;
				if ((-1 + i_7_) * i_8_ >= LobbyWorld.worldSize) {
					i_7_--;
				}
				if (LobbyWorld.worldSize <= i_7_ * (-1 + i_8_)) {
					i_8_--;
				}
				if (LobbyWorld.worldSize <= i_7_ * (i_8_ + -1)) {
					i_8_--;
				}
			} while (i_9_ != i_8_ || i_7_ != i_10_);
			i_9_ = (765 - i * i_7_) / (i_7_ - -1);
			if (i_9_ > 5) {
				i_9_ = 5;
			}
			int i_11_ = (-(i_9_ * (i_7_ + -1)) + 765 - i_7_ * i) / 2;
			i_10_ = (480 + -(i_8_ * i_6_)) / (1 + i_8_);
			if (i_10_ > 5) {
				i_10_ = 5;
			}
			int i_12_ = 0;
			int minMouseX = i_11_;
			int i_14_ = (480 - (i_6_ * i_8_ - -(i_10_ * (-1 + i_8_)))) / 2;
			int minMouseY = 23 - -i_14_;
			for (int worldIndex = 0; worldIndex < LobbyWorld.worldSize; worldIndex++) {
				LobbyWorld world = LobbyWorld.worldLists[worldIndex];
				boolean worldSelectable = true;
				RSString playerString = RSString.valueOf(world.players);
				if (world.players == -1) {
					worldSelectable = false;
					playerString = GroundObjEntity.worldOffString;
				} else if (world.players > 1980) {
					playerString = ForceMovement.worldFull;
					worldSelectable = false;
				}
				if (Mouse.mouseX >= minMouseX && minMouseY <= Mouse.mouseY && Mouse.mouseX < i + minMouseX && Mouse.mouseY < minMouseY + i_6_ && worldSelectable) {
					LobbyWorld.worldSelectIndex = worldIndex;
					// LobbyWorld.selectableWorldSprites[world.memberWorld ? 1 : 0].draw_coloured(minMouseX, minMouseY, 128, 16777215);
				} /* else { */
				LobbyWorld.selectable_world_sprites[!world.memberWorld ? 0 : 1].draw(minMouseX, minMouseY);
				// }
				if (LobbyWorld.country_flags_sprites != null) {
					LobbyWorld.country_flags_sprites[world.countryFlag /* + (!world.memberWorld ? 0 : 8) */].draw(minMouseX - -29, minMouseY);
				}
				boldFont.draw_text_centered(RSString.valueOf(world.worldId), minMouseX - -15, 5 + i_6_ / 2 + minMouseY, 0, -1);
				normalFont.draw_text_centered(playerString, minMouseX - -60, minMouseY + i_6_ / 2 - -5, 268435455, -1);
				minMouseY += i_10_ - -i_6_;
				if (i_8_ <= ++i_12_) {
					minMouseY = 23 - -i_14_;
					i_12_ = 0;
					minMouseX += i_9_ - -i;
				}
			}
		}
	}

	public static void cache_sprites() {
		LobbyWorld.selectable_world_sprites = Sprite.load_software_alpha_all(CacheConstants.sprites_js5, GroundItemNode.anInt3671, 0);
		LobbyWorld.country_flags_sprites = PaletteSprite.load_all(CacheConstants.sprites_js5, LobbyWorld.countryFlagsArchiveID, 0);
		LobbyWorld.world_list_arrows_sprites = PaletteSprite.load_all(CacheConstants.sprites_js5, LobbyWorld.worldListArrowArchive, 0);
		LobbyWorld.members_star_sprites = PaletteSprite.load_all(CacheConstants.sprites_js5, Class71.starArchive, 0);
	}

	public static int method1372(RSString class16) {
		return class16.length() - -1;
	}

	public static void method1374(int i) {
		StaticMethods2.anIntArray1454 = null;
		StaticMethods2.groundItems = null;
		Scene.anIntArray1455 = null;
		StaticMethods2.aPlayerAppearance_1440 = null;
		if (i == 32) {
			ContextMenu.menuActionCmd1 = null;
		}
	}

	static {
		StaticMethods2.aPlayerAppearance_1440 = new PlayerAppearance();
		StaticMethods2.anIntArray1454 = new int[32];
		StaticMethods2.groundItems = new NodeDeque[4][104][104];
		ContextMenu.menuActionCmd1 = new long[500];
	}

	static final void parseWorldList() {
		do {
			try {
				if (StaticMethods2.aClass53_347 == null) {
					StaticMethods2.aClass53_347 = new Class53(GameClient.gameSignlink, RSString.joinRsStrings(new RSString[] { IoSession.aClass16_539, RSString.valueOf(GameClient.getLanguage()), Class79.aClass16_1887 }).method153());
				} else {
					List<String> data = WebTools.readLink("http://ariosrsps.com/worlds/worlds.php");
					if (data != null && data.size() != 0) {
						List<World> wlds = new ArrayList<>();
						String[] worldData = data.get(0).split("-");
						String ip = "";
						int worldId = 1;
						int players = 0;
						int country = 0;
						boolean member = false;
						for (String world : worldData) {
							String[] tokens = world.split("<br>");
							ip = tokens[0].trim();
							worldId = Integer.parseInt(tokens[1]);
							players = Integer.parseInt(tokens[2]);
							country = Integer.parseInt(tokens[3]);
							member = Integer.parseInt(tokens[4]) == 1;
							wlds.add(new World(worldId, member, ip, players, country));
						}
						LobbyWorld.worlds.clear();
						LobbyWorld.worlds.addAll(wlds);
						worlds.add(new World(1, true, "127.0.0.1", -1, 225));
						worlds.add(new World(2, true, "127.0.0.1", -1, 225));
					}
					ByteBuffer test = ByteBuffer.allocate(2048);
					test.putShort((short) LobbyWorld.worlds.size()); // Amount of worlds.
					for (World world : LobbyWorld.worlds) {
						test.putShort((short) (world.getId() | (world.isFlag() ? 0x8000 : 0))); // World id + member flag (0x8000).
						test.put(world.getAdress().getBytes()).put((byte) 0); // World address, "world1".matrixftw.com
						test.putShort((short) world.getPlayers()); // Player count
						test.putShort((short) world.getCountryFlag()); // Country flag
					}
					test.flip();
					byte[] bs = new byte[test.remaining()];
					test.get(bs);
					Packet buffer = new Packet(bs);
					LobbyWorld.worldSize = buffer.g2();
					LobbyWorld.worldLists = new LobbyWorld[LobbyWorld.worldSize];
					for (int worldIndex = 0; LobbyWorld.worldSize > worldIndex; worldIndex++) {
						LobbyWorld tmpWorld = LobbyWorld.worldLists[worldIndex] = new LobbyWorld();
						int worldId = buffer.g2();
						tmpWorld.memberWorld = (0x8000 & worldId) != 0;
						tmpWorld.worldId = worldId & 0x7fff;
						tmpWorld.worldAddress = buffer.gstr();
						tmpWorld.players = (short) buffer.g2();
						tmpWorld.worldIndex = worldIndex;
						tmpWorld.countryFlag = (short) buffer.g2();
					}
					SpotEntity.method1080(0, 2874 ^ 0xb3a, LobbyWorld.worldLists, LobbyWorld.worldLists.length - 1);
					StaticMethods2.aClass53_347 = null;
					showWorldList = true;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				StaticMethods2.aClass53_347 = null;
				break;
			}
			break;
		} while (false);
	}

	static {
		addWorld(1, true, "127.0.0.1", 1, 225);
		addWorld(2, false, "127.0.0.1", 1, 225);
		addWorld(3, true, "127.0.0.1", 1, 225);
		addWorld(4, true, "127.0.0.1", 1, 85);
	}

	public static void addWorld(int id, boolean members, String address, int players, int country) {
		LobbyWorld world = new LobbyWorld();
		world.worldId = id;
		world.memberWorld = members;
		world.worldAddress = RSString.create(address);
		world.players = players;
		world.worldIndex = worldSize;
		world.countryFlag = country;
	}

	public static void addWorld(LobbyWorld world) {
		worldLists = Arrays.copyOf(worldLists, ++worldSize);
		worldLists[worldSize - 1] = world;
	}

	static final void handleLobbyWorldFunction(GameShell applet_sub1) {
		do {
			if (Mouse.mouseClickState == 1) {
				int i = 280;
				if (i <= Mouse.mouseClickX && (i - -14 ^ 0xffffffff) <= (Mouse.mouseClickX ^ 0xffffffff) && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(0, -61, 0);
					break;
				}
				if (15 + i <= Mouse.mouseClickX && Mouse.mouseClickX <= i - -80 && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(1, -50, 0);
					break;
				}
				int i_22_ = 390;
				if ((i_22_ ^ 0xffffffff) >= (Mouse.mouseClickX ^ 0xffffffff) && (Mouse.mouseClickX ^ 0xffffffff) >= (i_22_ - -14 ^ 0xffffffff) && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(0, -22, 1);
					break;
				}
				if (15 + i_22_ <= Mouse.mouseClickX && (Mouse.mouseClickX ^ 0xffffffff) >= (80 + i_22_ ^ 0xffffffff) && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(1, -51, 1);
					break;
				}
				int i_23_ = 500;
				if (Mouse.mouseClickX >= i_23_ && (Mouse.mouseClickX ^ 0xffffffff) >= (14 + i_23_ ^ 0xffffffff) && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(0, -24, 2);
					break;
				}
				if ((i_23_ + 15 ^ 0xffffffff) >= (Mouse.mouseClickX ^ 0xffffffff) && i_23_ - -80 >= Mouse.mouseClickX && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(1, -97, 2);
					break;
				}
				int i_24_ = 610;
				if ((Mouse.mouseClickX ^ 0xffffffff) <= (i_24_ ^ 0xffffffff) && (Mouse.mouseClickX ^ 0xffffffff) >= (14 + i_24_ ^ 0xffffffff) && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(0, -55, 3);
					break;
				}
				if ((Mouse.mouseClickX ^ 0xffffffff) <= (i_24_ + 15 ^ 0xffffffff) && (Mouse.mouseClickX ^ 0xffffffff) >= (80 + i_24_ ^ 0xffffffff) && Mouse.mouseClickY >= 4 && Mouse.mouseClickY <= 18) {
					UpdateServerNode.method861(1, -100, 3);
					break;
				}
				if (Mouse.mouseClickX >= 700 && Mouse.mouseClickY >= 4 && Mouse.mouseClickX <= 758 && Mouse.mouseClickY <= 20) {
					showWorldList = false;
					break;
				}
				if (worldSelectIndex != -1) {
					LobbyWorld world = worldLists[worldSelectIndex];
					// if (Game.isMembers() == world.memberWorld) {
					// byte[] bs = TextureOperation32.method322(new RSString[] { class82.aClass16_1449, Class39.aClass16_600 },
					// -95).method147(true);
					byte[] bs = world.worldAddress.bytes;
					GameClient.setIp(new String(bs, 0, bs.length));
					if (GameClient.getIp() == "127.0.0.1") {
						GameClient.setIp(GameLaunch.getSetting().getIp());
					}
					// System.out.println("Current IP address: " + Game.getIp() + ", " + world.worldId);
					if (GameClient.getGameType() != 0) {
						Queuable.anInt2320 = GameClient.socket_port = Configurations.SERVER_PORT;
						GroundItem.anInt2498 = 443;
						GameClient.setGameType(0);
					}
					GameClient.setWorldId(world.worldId);
					showWorldList = false;
					// } else {
					// RSString class16 = TextureOperation32.joinRsStrings(new RSString[] { TextureOperation15.aClass16_3481,
					// world.worldAddress, Class23_Sub11.aClass16_2283, ImageSourceTextureOperation.createRSString(Game.getLanguage()),
					// ImageSourceTextureOperation.aClass16_3288, ImageSourceTextureOperation.createRSString(UpdateServerThread.lowDetail ?
					// 1 : 0), AbstractSprite.aClass16_3875, ImageSourceTextureOperation.createRSString(Class47.anInt741),
					// CacheFileWorker.aClass16_2856, ImageSourceTextureOperation.createRSString(NPC.anInt4376) }, -58);
					// try {
					// applet_sub1.getAppletContext().showDocument(class16.method153((byte) -74), "_self");
					// } catch (Exception exception) {
					// break;
					// }
					// break;
					// }
					break;
				}
			}
		} while (false);
	}
}
