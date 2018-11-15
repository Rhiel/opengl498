package com.jagex;
/* Class35 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class Class35 {
	public static int cameraDirection;
	public static int anInt554;
	public static RSString aClass16_556 = RSString.createString(" )2> <col=00ffff>");
	public static int[] anIntArray557;

	public static final void method979(boolean bool) {
		boolean bool_1_ = bool;
		while (!bool_1_) {
			bool_1_ = true;
			for (int i = 0; -1 + ContextMenu.menuActionRow > i; i++) {
				if (ContextMenu.menuActionID[i] < 1000 && ContextMenu.menuActionID[i + 1] > 1000) {
					bool_1_ = false;
					RSString class16 = Class98.aClass16Array1655[i];
					Class98.aClass16Array1655[i] = Class98.aClass16Array1655[1 + i];
					Class98.aClass16Array1655[i - -1] = class16;
					RSString class16_2_ = BufferedRequest.aClass16Array4307[i];
					BufferedRequest.aClass16Array4307[i] = BufferedRequest.aClass16Array4307[1 + i];
					BufferedRequest.aClass16Array4307[1 + i] = class16_2_;
					int i_3_ = ContextMenu.menuActionCmd2[i];
					ContextMenu.menuActionCmd2[i] = ContextMenu.menuActionCmd2[i - -1];
					ContextMenu.menuActionCmd2[i + 1] = i_3_;
					i_3_ = ContextMenu.menuActionCmd3[i];
					ContextMenu.menuActionCmd3[i] = ContextMenu.menuActionCmd3[1 + i];
					ContextMenu.menuActionCmd3[i - -1] = i_3_;
					short s = ContextMenu.menuActionID[i];
					ContextMenu.menuActionID[i] = ContextMenu.menuActionID[1 + i];
					ContextMenu.menuActionID[i + 1] = s;
					long l = ContextMenu.menuActionCmd1[i];
					ContextMenu.menuActionCmd1[i] = ContextMenu.menuActionCmd1[i - -1];
					ContextMenu.menuActionCmd1[1 + i] = l;
				}
			}
		}
	}

	public static final void method980(int i, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_) {
		for (int i_9_ = i_8_; i_9_ <= i_6_ + i_8_; i_9_++) {
			for (int i_10_ = i_7_; (i_4_ + i_7_ ^ 0xffffffff) <= (i_10_ ^ 0xffffffff); i_10_++) {
				if (i_10_ >= 0 && i_10_ < 104 && i_9_ >= 0 && i_9_ < 104) {
					MapLoader.shadows_intensities[i_5_][i_10_][i_9_] = (byte) 127;
				}
			}
		}
		for (int i_11_ = i_8_; (i_11_ ^ 0xffffffff) > (i_8_ - -i_6_ ^ 0xffffffff); i_11_++) {
			for (int i_12_ = i_7_; i_12_ < i_4_ + i_7_; i_12_++) {
				if ((i_12_ ^ 0xffffffff) <= -1 && i_12_ < 104 && i_11_ >= 0 && i_11_ < 104) {
					Scene.current_heightmap[i_5_][i_12_][i_11_] = i_5_ > 0 ? Scene.current_heightmap[i_5_ + -1][i_12_][i_11_] : 0;
				}
			}
		}
		if ((i_7_ ^ 0xffffffff) < i && i_7_ < 104) {
			for (int i_13_ = 1 + i_8_; i_13_ < i_6_ + i_8_; i_13_++) {
				if ((i_13_ ^ 0xffffffff) <= -1 && i_13_ < 104) {
					Scene.current_heightmap[i_5_][i_7_][i_13_] = Scene.current_heightmap[i_5_][i_7_ - 1][i_13_];
				}
			}
		}
		if (i_8_ > 0 && i_8_ < 104) {
			for (int i_14_ = i_7_ + 1; i_4_ + i_7_ > i_14_; i_14_++) {
				if ((i_14_ ^ 0xffffffff) <= -1 && i_14_ < 104) {
					Scene.current_heightmap[i_5_][i_14_][i_8_] = Scene.current_heightmap[i_5_][i_14_][i_8_ - 1];
				}
			}
		}
		if ((i_7_ ^ 0xffffffff) <= -1 && i_8_ >= 0 && i_7_ < 104 && i_8_ < 104) {
			if (i_5_ != 0) {
				if ((i_7_ ^ 0xffffffff) >= -1 || Scene.current_heightmap[i_5_][-1 + i_7_][i_8_] == Scene.current_heightmap[i_5_ + -1][i_7_ - 1][i_8_]) {
					if (i_8_ > 0 && (Scene.current_heightmap[i_5_ - 1][i_7_][i_8_ - 1] ^ 0xffffffff) != (Scene.current_heightmap[i_5_][i_7_][i_8_ + -1] ^ 0xffffffff)) {
						Scene.current_heightmap[i_5_][i_7_][i_8_] = Scene.current_heightmap[i_5_][i_7_][i_8_ + -1];
					} else if (i_7_ > 0 && (i_8_ ^ 0xffffffff) < -1 && Scene.current_heightmap[i_5_][i_7_ + -1][-1 + i_8_] != Scene.current_heightmap[-1 + i_5_][-1 + i_7_][-1 + i_8_]) {
						Scene.current_heightmap[i_5_][i_7_][i_8_] = Scene.current_heightmap[i_5_][i_7_ + -1][i_8_ + -1];
					}
				} else {
					Scene.current_heightmap[i_5_][i_7_][i_8_] = Scene.current_heightmap[i_5_][i_7_ + -1][i_8_];
				}
			} else if ((i_7_ ^ 0xffffffff) >= -1 || (Scene.current_heightmap[i_5_][i_7_ + -1][i_8_] ^ 0xffffffff) == -1) {
				if (i_8_ > 0 && (Scene.current_heightmap[i_5_][i_7_][i_8_ + -1] ^ 0xffffffff) != -1) {
					Scene.current_heightmap[i_5_][i_7_][i_8_] = Scene.current_heightmap[i_5_][i_7_][i_8_ - 1];
				} else if (i_7_ > 0 && (i_8_ ^ 0xffffffff) < -1 && Scene.current_heightmap[i_5_][-1 + i_7_][-1 + i_8_] != 0) {
					Scene.current_heightmap[i_5_][i_7_][i_8_] = Scene.current_heightmap[i_5_][i_7_ - 1][-1 + i_8_];
				}
			} else {
				Scene.current_heightmap[i_5_][i_7_][i_8_] = Scene.current_heightmap[i_5_][i_7_ - 1][i_8_];
			}
		}
	}

	static final RSString method981(byte b, int i) {
		if ((i ^ 0xffffffff) > -1000000000) {
			return RSString.valueOf(i);
		}
		if (b != 103) {
			method983(71);
		}
		return Queue.aClass16_414;
	}

	// method982(i_16_, viewportX, fromY, i_13_, bool, fromX, sizeX, walkingFlag, false, sizeY, i, viewportY);
	static final boolean findPath(int rotation, int destinationX, int fromY, int pathType, boolean near, int fromX, int sizeX, int walkingFlag, boolean bool_21_, int sizeY, int objectType, int destinationY) {
		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				StaticMethods.pathVia[x][y] = 0;
				StaticMethods.pathCost[x][y] = 99999999;
			}
		}
		StaticMethods.pathVia[fromX][fromY] = 99;
		int currentX = fromX;
		StaticMethods.pathCost[fromX][fromY] = 0;
		int currentY = fromY;
		int writePosition = 0;
		PositionedGraphicNode.queueX[writePosition] = fromX;
		StaticMethods.queueY[writePosition++] = fromY;
		int readPosition = 0;
		int[][] flags = MapLoader.collision_maps[ObjType.localHeight].clippingFlags;
		boolean foundPath = bool_21_;
		while (readPosition != writePosition) {
			currentY = StaticMethods.queueY[readPosition];
			currentX = PositionedGraphicNode.queueX[readPosition];
			readPosition = readPosition + 1 & 0xfff;
			if (currentX == destinationX && currentY == destinationY) {
				foundPath = true;
				break;
			}
			if (objectType != 0) {
				if ((objectType < 5 || objectType == 10) && MapLoader.collision_maps[ObjType.localHeight].canDoorInteract((byte) -87, -1 + objectType, rotation, destinationX, currentY, 1, currentX, destinationY)) {
					foundPath = true;
					break;
				}
				if (objectType < 10 && MapLoader.collision_maps[ObjType.localHeight].canDecorationInteract(destinationX, (byte) 111, currentY, currentX, destinationY, rotation, objectType - 1, 1)) {
					foundPath = true;
					break;
				}
			}
			if (sizeX != 0 && sizeY != 0 && MapLoader.collision_maps[ObjType.localHeight].canInteract(destinationY, destinationX, currentX, sizeX, 1, currentY, walkingFlag, sizeY, (byte) -124)) {
				foundPath = true;
				break;
			}
			int i_32_ = 1 + StaticMethods.pathCost[currentX][currentY];
			if (currentX > 0 && StaticMethods.pathVia[currentX - 1][currentY] == 0 && (0x12c0108 & flags[currentX + -1][currentY]) == 0) {
				PositionedGraphicNode.queueX[writePosition] = currentX - 1;
				StaticMethods.queueY[writePosition] = currentY;
				writePosition = 1 + writePosition & 0xfff;
				StaticMethods.pathVia[currentX - 1][currentY] = 2;
				StaticMethods.pathCost[currentX + -1][currentY] = i_32_;
			}
			if (currentX < 103 && (StaticMethods.pathVia[1 + currentX][currentY] ^ 0xffffffff) == -1 && (flags[currentX - -1][currentY] & 0x12c0180) == 0) {
				PositionedGraphicNode.queueX[writePosition] = 1 + currentX;
				StaticMethods.queueY[writePosition] = currentY;
				writePosition = 1 + writePosition & 0xfff;
				StaticMethods.pathVia[1 + currentX][currentY] = 8;
				StaticMethods.pathCost[currentX + 1][currentY] = i_32_;
			}
			if ((currentY ^ 0xffffffff) < -1 && StaticMethods.pathVia[currentX][-1 + currentY] == 0 && (flags[currentX][-1 + currentY] & 0x12c0102) == 0) {
				PositionedGraphicNode.queueX[writePosition] = currentX;
				StaticMethods.queueY[writePosition] = -1 + currentY;
				StaticMethods.pathVia[currentX][-1 + currentY] = 1;
				writePosition = 1 + writePosition & 0xfff;
				StaticMethods.pathCost[currentX][-1 + currentY] = i_32_;
			}
			if (currentY < 103 && StaticMethods.pathVia[currentX][currentY - -1] == 0 && (0x12c0120 & flags[currentX][currentY + 1]) == 0) {
				PositionedGraphicNode.queueX[writePosition] = currentX;
				StaticMethods.queueY[writePosition] = currentY + 1;
				StaticMethods.pathVia[currentX][1 + currentY] = 4;
				StaticMethods.pathCost[currentX][1 + currentY] = i_32_;
				writePosition = 0xfff & 1 + writePosition;
			}
			if ((currentX ^ 0xffffffff) < -1 && (currentY ^ 0xffffffff) < -1 && (StaticMethods.pathVia[-1 + currentX][-1 + currentY] ^ 0xffffffff) == -1 && (flags[-1 + currentX][currentY + -1] & 0x12c010e ^ 0xffffffff) == -1 && (0x12c0108 & flags[-1 + currentX][currentY]) == 0 && (0x12c0102 & flags[currentX][currentY + -1] ^ 0xffffffff) == -1) {
				PositionedGraphicNode.queueX[writePosition] = currentX + -1;
				StaticMethods.queueY[writePosition] = -1 + currentY;
				StaticMethods.pathVia[-1 + currentX][-1 + currentY] = 3;
				StaticMethods.pathCost[currentX + -1][-1 + currentY] = i_32_;
				writePosition = 0xfff & writePosition - -1;
			}
			if (currentX < 103 && currentY > 0 && StaticMethods.pathVia[currentX - -1][-1 + currentY] == 0 && (flags[1 + currentX][-1 + currentY] & 0x12c0183) == 0 && (flags[currentX - -1][currentY] & 0x12c0180 ^ 0xffffffff) == -1 && (0x12c0102 & flags[currentX][-1 + currentY]) == 0) {
				PositionedGraphicNode.queueX[writePosition] = currentX + 1;
				StaticMethods.queueY[writePosition] = -1 + currentY;
				StaticMethods.pathVia[1 + currentX][-1 + currentY] = 9;
				writePosition = 0xfff & 1 + writePosition;
				StaticMethods.pathCost[1 + currentX][-1 + currentY] = i_32_;
			}
			if (currentX > 0 && currentY < 103 && (StaticMethods.pathVia[currentX - 1][currentY + 1] ^ 0xffffffff) == -1 && (0x12c0138 & flags[-1 + currentX][currentY - -1] ^ 0xffffffff) == -1 && (flags[-1 + currentX][currentY] & 0x12c0108) == 0 && (flags[currentX][currentY - -1] & 0x12c0120) == 0) {
				PositionedGraphicNode.queueX[writePosition] = currentX - 1;
				StaticMethods.queueY[writePosition] = 1 + currentY;
				StaticMethods.pathVia[-1 + currentX][1 + currentY] = 6;
				writePosition = 0xfff & writePosition - -1;
				StaticMethods.pathCost[-1 + currentX][1 + currentY] = i_32_;
			}
			if (currentX < 103 && currentY < 103 && (StaticMethods.pathVia[currentX + 1][1 + currentY] ^ 0xffffffff) == -1 && (flags[currentX + 1][currentY + 1] & 0x12c01e0) == 0 && (0x12c0180 & flags[1 + currentX][currentY] ^ 0xffffffff) == -1 && (0x12c0120 & flags[currentX][currentY - -1] ^ 0xffffffff) == -1) {
				PositionedGraphicNode.queueX[writePosition] = currentX + 1;
				StaticMethods.queueY[writePosition] = currentY - -1;
				StaticMethods.pathVia[currentX - -1][1 + currentY] = 12;
				writePosition = 0xfff & writePosition - -1;
				StaticMethods.pathCost[currentX - -1][currentY - -1] = i_32_;
			}
		}
		StaticMethods2.anInt1417 = 0;
		if (!foundPath) {
			if (near) {
				int i_33_ = 1000;
				int cost = 100;
				int dist = 10;
				for (int x = -dist + destinationX; x <= dist + destinationX; x++) {
					for (int y = -dist + destinationY; y <= dist + destinationY; y++) {
						if ((x ^ 0xffffffff) <= -1 && (y ^ 0xffffffff) <= -1 && x < 104 && y < 104 && StaticMethods.pathCost[x][y] < 100) {
							int i_38_ = 0;
							if (destinationX <= x) {
								if (x > -1 + destinationX - -sizeX) {
									i_38_ = x + -destinationX + -sizeX + 1;
								}
							} else {
								i_38_ = -x + destinationX;
							}
							int i_39_ = 0;
							if ((y ^ 0xffffffff) <= (destinationY ^ 0xffffffff)) {
								if ((y ^ 0xffffffff) < (-1 + destinationY - -sizeY ^ 0xffffffff)) {
									i_39_ = 1 - (destinationY - -sizeY) + y;
								}
							} else {
								i_39_ = destinationY + -y;
							}
							int i_40_ = i_38_ * i_38_ + i_39_ * i_39_;
							if (i_33_ > i_40_ || i_33_ == i_40_ && cost > StaticMethods.pathCost[x][y]) {
								currentX = x;
								cost = StaticMethods.pathCost[x][y];
								currentY = y;
								i_33_ = i_40_;
							}
						}
					}
				}
				if (i_33_ == 1000) {
					return false;
				}
				if ((fromX ^ 0xffffffff) == (currentX ^ 0xffffffff) && fromY == currentY) {
					return false;
				}
				StaticMethods2.anInt1417 = 1;
			} else {
				return false;
			}
		}
		readPosition = 0;
		PositionedGraphicNode.queueX[readPosition] = currentX;
		StaticMethods.queueY[readPosition++] = currentY;
		int i_42_;
		int i_41_ = i_42_ = StaticMethods.pathVia[currentX][currentY];
		while (currentX != fromX || currentY != fromY) {
			if ((i_41_ ^ 0xffffffff) != (i_42_ ^ 0xffffffff)) {
				i_42_ = i_41_;
				PositionedGraphicNode.queueX[readPosition] = currentX;
				StaticMethods.queueY[readPosition++] = currentY;
			}
			if ((0x1 & i_41_ ^ 0xffffffff) == -1) {
				if ((0x4 & i_41_ ^ 0xffffffff) != -1) {
					currentY--;
				}
			} else {
				currentY++;
			}
			if ((i_41_ & 0x2 ^ 0xffffffff) != -1) {
				currentX++;
			} else if ((0x8 & i_41_) != 0) {
				currentX--;
			}
			i_41_ = StaticMethods.pathVia[currentX][currentY];
		}
		if ((readPosition ^ 0xffffffff) < -1) {
			StaticMethods.sendWalkPacket(readPosition, StaticMethods.queueY, PositionedGraphicNode.queueX, pathType);
			return true;
		}
		if (pathType == 1) {
			return false;
		}
		return true;
	}

	public static void method983(int i) {
		if (i == -18540) {
			aClass16_556 = null;
			anIntArray557 = null;
			Scene.anIntArray548 = null;
		}
	}

	static final SceneNode defaultModelNode = new Mesh(null, 0);

	static final void parseMapPacket(int i) {
		// System.out.println("Handling opcode " + UnderlayFloor.currentOpcode);
		if (PacketParser.currentOpcode == 238) {
			int offset = StaticMethods2.packet.g1();
			int offsetY = Js5.regionChunkY + (offset & 0x7);
			int offsetX = StaticMethods.regionChunkX + ((offset & 0x73) >> 4);
			int soundId = StaticMethods2.packet.g2();
			if (soundId == 65535) {
				soundId = -1;
			}
			int config = StaticMethods2.packet.g1();
			int distance = (0xfa & config) >> 4;
			int volume = 0x7 & config;
			int delay = StaticMethods2.packet.g1();
			if (offsetX >= 0 && offsetY >= 0 && offsetX < 104 && offsetY < 104) {
				int radius = distance + 1;
				if ((GameClient.currentPlayer.waypointsX[0] ^ 0xffffffff) <= (offsetX - radius ^ 0xffffffff) && GameClient.currentPlayer.waypointsX[0] <= radius + offsetX && (GameClient.currentPlayer.waypointsY[0] ^ 0xffffffff) <= (-radius + offsetY ^ 0xffffffff) && offsetY + radius >= GameClient.currentPlayer.waypointsY[0] && (TimeTools.soundPreference1 ^ 0xffffffff) != -1 && volume > 0 && Class71_Sub3.soundStoreCount < 50 && soundId != -1) {
					PlayerMasks.soundStores[Class71_Sub3.soundStoreCount++] = new SoundStore((byte) 1, soundId, volume, delay, 255, (offsetY << 8) + (offsetX << 16) + (ObjType.localHeight << 24) + distance, 256, null);
				}
			}
		} else if (PacketParser.currentOpcode == 125) { // Projectile
			int offset = StaticMethods2.packet.g1();
			int startX = 2 * StaticMethods.regionChunkX + ((offset & 0xf3) >> 4);
			int startY = 2 * Js5.regionChunkY + (offset & 0xf);
			int endX = startX + StaticMethods2.packet.g1s();
			int endY = StaticMethods2.packet.g1s() + startY;
			int victimIndex = StaticMethods2.packet.g2s();
			int projectileId = StaticMethods2.packet.g2();
			int startHeight = StaticMethods2.packet.g1() * 4;
			int endHeight = StaticMethods2.packet.g1() * 4;
			int startDelay = StaticMethods2.packet.g2();
			int speed = StaticMethods2.packet.g2();
			int angle = StaticMethods2.packet.g1();
			int distance = StaticMethods2.packet.g1();
			if ((startX ^ 0xffffffff) <= -1 && (startY ^ 0xffffffff) <= -1 && startX < 208 && startY < 208 && (endX ^ 0xffffffff) <= -1 && (endY ^ 0xffffffff) <= -1 && endX < 208 && endY < 208 && projectileId != 65535) {
				endX = 64 * endX;
				startY = 64 * startY;
				endY = 64 * endY;
				startX *= 64;
				Projectile projectile = new Projectile(projectileId, ObjType.localHeight, startX, startY, -startHeight + Scene.get_average_height(ObjType.localHeight, startX, startY), GameClient.timer + startDelay, speed - -GameClient.timer, angle, distance, victimIndex, endHeight);
				projectile.method1069(startDelay + GameClient.timer, endY, endX, Scene.get_average_height(ObjType.localHeight, endX, endY) + -endHeight, -18499);
				Class36.aClass89_562.add_last(new ProjectileNode(projectile));
			}
		} else if (PacketParser.currentOpcode == 138) { // Construct ground item
			int itemId = StaticMethods2.packet.g2();
			int offset = StaticMethods2.packet.getByteA();
			int offsetY = Js5.regionChunkY + (offset & 0x7);
			int offsetX = StaticMethods.regionChunkX + ((offset & 0x7b) >> 4);
			int amount = StaticMethods2.packet.getShortA();
			if (offsetX >= 0 && offsetY >= 0 && offsetX < 104 && offsetY < 104) {
				GroundItem class38_sub2 = new GroundItem();
				class38_sub2.itemId = itemId;
				class38_sub2.amount = amount;
				if (StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY] == null) {
					StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY] = new NodeDeque();
				}
				StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY].add_last(new GroundItemNode(class38_sub2));
				Class44.method1129(offsetX, offsetY, (byte) 123);
			}
		} else if (PacketParser.currentOpcode == 198) { // Update ground item amount
			int offset = StaticMethods2.packet.g1();
			int offsetX = ((0x77 & offset) >> 4) + StaticMethods.regionChunkX;
			int offsetY = (offset & 0x7) + Js5.regionChunkY;
			int itemId = StaticMethods2.packet.g2();
			int currentAmount = StaticMethods2.packet.g2();
			int newAmount = StaticMethods2.packet.g2();
			if (offsetX >= 0 && offsetY >= 0 && offsetX < 104 && offsetY < 104) {
				NodeDeque class89 = StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY];
				if (class89 != null) {
					for (GroundItemNode class23_sub13_sub1 = (GroundItemNode) class89.get_first(); class23_sub13_sub1 != null; class23_sub13_sub1 = (GroundItemNode) class89.get_next()) {
						GroundItem item = class23_sub13_sub1.groundItem;
						if ((0x7fff & itemId) == item.itemId && item.amount == currentAmount) {
							item.amount = newAmount;
							break;
						}
					}
					Class44.method1129(offsetX, offsetY, (byte) 122);
				}
			}
		} else if (PacketParser.currentOpcode == 24) { // Construct object
			int objectId = StaticMethods2.packet.getLEShortA0(127);
			int configuration = StaticMethods2.packet.getByteC(-107);
			int rotation = 0x3 & configuration;
			int type = configuration >> 2;
			int i_115_ = Class75.anIntArray1375[type];
			int position = StaticMethods2.packet.g1();
			int x = StaticMethods.regionChunkX + ((0x7e & position) >> 4);
			int y = Js5.regionChunkY + (position & 0x7);
			if ((x ^ 0xffffffff) <= -1 && (y ^ 0xffffffff) <= -1 && x < 104 && y < 104) {
				SpawnedObject.method1373(y, objectId, 0, ObjType.localHeight, i_115_, -1, -7593, rotation, type, x);
			}
		} else if (PacketParser.currentOpcode == 214) { // Positioned graphic
			int offset = StaticMethods2.packet.g1();
			int offsetX = StaticMethods.regionChunkX + (offset >> 4 & 0x7);
			int offsetY = Js5.regionChunkY + (0x7 & offset);
			int graphicId = StaticMethods2.packet.g2();
			int height = StaticMethods2.packet.g1();
			int delay = StaticMethods2.packet.g2();
			if (offsetX >= 0 && offsetY >= 0 && offsetX < 104 && offsetY < 104) {
				offsetY = 64 + 128 * offsetY;
				offsetX = 128 * offsetX + 64;
				SpotEntity graphic = new SpotEntity(graphicId, ObjType.localHeight, offsetX, offsetY, Scene.get_average_height(ObjType.localHeight, offsetX, offsetY) - height, delay, GameClient.timer);
				SpotType.aClass89_4066.add_last(new PositionedGraphicNode(graphic));
			}
		} else if (PacketParser.currentOpcode == 134) { // Remove ground item
			int offset = StaticMethods2.packet.g1();
			int offsetY = StaticMethods.regionChunkX + ((offset & 0x7a) >> 4);
			int offsetX = Js5.regionChunkY + (offset & 0x7);
			int itemId = StaticMethods2.packet.getLEShortA0(126);
			if (offsetY >= 0 && offsetX >= 0 && offsetY < 104 && offsetX < 104) {
				NodeDeque class89 = StaticMethods2.groundItems[ObjType.localHeight][offsetY][offsetX];
				if (class89 != null) {
					for (GroundItemNode class23_sub13_sub1 = (GroundItemNode) class89.get_first(); class23_sub13_sub1 != null; class23_sub13_sub1 = (GroundItemNode) class89.get_next()) {
						if (class23_sub13_sub1.groundItem.itemId == (itemId & 0x7fff)) {
							class23_sub13_sub1.unlink();
							break;
						}
					}
					if (class89.get_first() == null) {
						StaticMethods2.groundItems[ObjType.localHeight][offsetY][offsetX] = null;
					}
					Class44.method1129(offsetY, offsetX, (byte) 120);
				}
			}
		} else if (PacketParser.currentOpcode == 188) { // Projectile
			int offset = StaticMethods2.packet.g1();
			int startY = (0x7 & offset) + Js5.regionChunkY;
			int startX = StaticMethods.regionChunkX - -(0x7 & offset >> 4);
			int endX = StaticMethods2.packet.g1s() + startX;
			int endY = StaticMethods2.packet.g1s() + startY;
			int targetIndex = StaticMethods2.packet.g2s();
			int id = StaticMethods2.packet.g2();
			int startHeight = StaticMethods2.packet.g1() * 4;
			int endHeight = StaticMethods2.packet.g1() * 4;
			int delay = StaticMethods2.packet.g2();
			int speed = StaticMethods2.packet.g2();
			int angle = StaticMethods2.packet.g1();
			int distance = StaticMethods2.packet.g1();
			if (startX >= 0 && startY >= 0 && startX < 104 && startY < 104 && endX >= 0 && endY >= 0 && endX < 104 && endY < 104 && (id ^ 0xffffffff) != -65536) {
				endX = endX * 128 - -64;
				startY = 64 + 128 * startY;
				endY = 128 * endY - -64;
				startX = 64 + startX * 128;
				Projectile class38_sub5 = new Projectile(id, ObjType.localHeight, startX, startY, Scene.get_average_height(ObjType.localHeight, startX, startY) - startHeight, GameClient.timer + delay, speed + GameClient.timer, angle, distance, targetIndex, endHeight);
				class38_sub5.method1069(delay + GameClient.timer, endY, endX, Scene.get_average_height(ObjType.localHeight, endX, endY) + -endHeight, -18499);
				Class36.aClass89_562.add_last(new ProjectileNode(class38_sub5));
			}
		} else if (PacketParser.currentOpcode == 77) { // Remove object
			int i_142_ = StaticMethods2.packet.getByteC(-120);
			int rotation = 0x3 & i_142_;
			int type = i_142_ >> 2;
			int i_145_ = Class75.anIntArray1375[type];
			int i_146_ = StaticMethods2.packet.getByteA();
			int diffX = StaticMethods.regionChunkX - -(0x7 & i_146_ >> 4);
			int diffY = Js5.regionChunkY - -(i_146_ & 0x7);
			if (diffX >= 0 && diffY >= 0 && diffX < 104 && diffY < 104) {
				SpawnedObject.method1373(diffY, -1, 0, ObjType.localHeight, i_145_, -1, -7593, rotation, type, diffX);
			}
		} else if (PacketParser.currentOpcode == 16) { // Construct ground item (with player exception)
			int amount = StaticMethods2.packet.g2();
			int itemId = StaticMethods2.packet.getLEShortA0(125);
			int ignoredPlayerIndex = StaticMethods2.packet.getShortA();
			int offset = StaticMethods2.packet.g1();
			int offsetX = (offset >> 4 & 0x7) + StaticMethods.regionChunkX;
			int offsetY = Js5.regionChunkY + (0x7 & offset);
			if (offsetX >= 0 && (offsetY ^ 0xffffffff) <= -1 && offsetX < 104 && offsetY < 104 && ignoredPlayerIndex != StaticMethods.thisPlayerIndex) {
				GroundItem item = new GroundItem();
				item.itemId = itemId;
				item.amount = amount;
				if (StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY] == null) {
					StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY] = new NodeDeque();
				}
				StaticMethods2.groundItems[ObjType.localHeight][offsetX][offsetY].add_last(new GroundItemNode(item));
				Class44.method1129(offsetX, offsetY, (byte) 113);
			}
		} else {
			if (PacketParser.currentOpcode == 74) { // Unknown - spawns an object which will rotate & move depending on the player.
				int offset = StaticMethods2.packet.getByteC(-117);
				int offsetX = (offset >> 4 & 0x7) + StaticMethods.regionChunkX;
				int offsetY = Js5.regionChunkY + (offset & 0x7);
				int duration = StaticMethods2.packet.g2();
				int objectId = StaticMethods2.packet.getShortA();
				int i_161_ = StaticMethods2.packet.getByteC0(-75);
				int i_162_ = StaticMethods2.packet.getByteS0(-18402);
				int i_163_ = StaticMethods2.packet.g1s();
				int i_164_ = StaticMethods2.packet.getByteA0((byte) 105);
				int configuration = StaticMethods2.packet.g1();
				int type = configuration >> 2;
				int typeValue = Class75.anIntArray1375[type];
				int startDelay = StaticMethods2.packet.getLEShortA0(126);
				int playerIndex = StaticMethods2.packet.getShortA();
				Player player;
				if (StaticMethods.thisPlayerIndex == playerIndex) {
					player = GameClient.currentPlayer;
				} else {
					player = GameClient.localPlayers[playerIndex];
				}
				int rotation = configuration & 0x3;
				if (player != null) {
					LocType def = LocTypeList.list(objectId);
					int sizeX;
					int sizeY;
					if (rotation == 1 || rotation == 3) {
						sizeY = def.size2d;
						sizeX = def.size3d;
					} else {
						sizeX = def.size2d;
						sizeY = def.size3d;
					}
					int i_173_ = (sizeX >> 1) + offsetX;
					int i_174_ = (sizeY >> 1) + offsetY;
					int i_175_ = offsetX - -(1 + sizeX >> 1);
					int i_176_ = offsetY + (sizeY - -1 >> 1);
					int[][] is = Scene.current_heightmap[ObjType.localHeight];
					int[][] is_177_ = null;
					if (ObjType.localHeight < 3) {
						is_177_ = Scene.current_heightmap[1 + ObjType.localHeight];
					}
					int i_178_ = is[i_173_][i_176_] + is[i_173_][i_174_] + is[i_175_][i_174_] - -is[i_175_][i_176_] >> 2;
					int i_179_ = (offsetX << 7) + (sizeX << 6);
					int i_180_ = (offsetY << 7) + (sizeY << 6);
					LocResult objectInstance = def.get_pair(is, rotation, i_179_, type, i_178_, is_177_, false, i_180_, null, true);
					if (objectInstance != null) {
						if ((i_163_ ^ 0xffffffff) < (i_162_ ^ 0xffffffff)) {
							int i_181_ = i_163_;
							i_163_ = i_162_;
							i_162_ = i_181_;
						}
						if (i_161_ > i_164_) {
							int i_182_ = i_161_;
							i_161_ = i_164_;
							i_164_ = i_182_;
						}
						SpawnedObject.method1373(offsetY, -1, startDelay + 1, ObjType.localHeight, typeValue, 1 + duration, -7593, 0, 0, offsetX);
						player.aClass38_Sub1_4402 = objectInstance.node;
						player.anInt4380 = startDelay + GameClient.timer;
						player.anInt4411 = duration + GameClient.timer;
						player.anInt4403 = i_178_;
						player.anInt4390 = i_162_ + offsetX;
						player.anInt4387 = offsetX + i_163_;
						player.anInt4391 = i_161_ + offsetY;
						player.anInt4379 = 64 * sizeY + 128 * offsetY;
						player.anInt4396 = offsetY + i_164_;
						player.anInt4395 = offsetX * 128 - -(sizeX * 64);
					}
				}
			}
			if (PacketParser.currentOpcode == 60) { // Object animation
				int offset = StaticMethods2.packet.getByteS(127);
				int x = StaticMethods.regionChunkX + ((0x74 & offset) >> 4);
				int y = Js5.regionChunkY + (offset & 0x7);
				int animationId = StaticMethods2.packet.getShortA();
				int configuration = StaticMethods2.packet.getByteS(126);
				int rotation = 0x3 & configuration;
				if (animationId == 65535) {
					animationId = -1;
				}
				int type = configuration >> 2;
				int typeThing = Class75.anIntArray1375[type];
				// System.out.println("areax=" + TextureOperation37.areaX + ", areay=" + Class105.areaY + ", x=" + x + " , y=" + y);
				ClanChatMember.animateObject(104, typeThing, x, y, animationId, type, ObjType.localHeight, rotation);
			}
		}
	}

	static {
		cameraDirection = 0;
		anInt554 = 100;
	}
}
