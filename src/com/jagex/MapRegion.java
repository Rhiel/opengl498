package com.jagex;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.shadow.SceneShadowMapper;

/**
 * Created by Chris on 3/28/2017.
 */
public class MapRegion {
	public static RSString MAP_M = RSString.createString("m");
	public static RSString UNDERSCORE = RSString.createString("_");
	public static RSString MAP_L = RSString.createString("l");
	public static int[][][] localRegions = new int[4][13][13];

	public static final void parseLandscape(int i, int i_2_, int i_3_, int i_4_, int i_5_, boolean bool, int i_6_, CollisionMap[] tileSettings, int i_7_, int i_8_, byte[] bs) {
		if (i_8_ != -1137131194) {
			StaticMethods2.anInt1417 = 8;
		}
		int objectId = -1;
		Packet buffer = new Packet(bs);
		for (;;) {
			int offset = buffer.getUSmart2();
			if (offset == 0) {
				break;
			}
			objectId += offset;
			int location = 0;
			for (;;) {
				int i_12_ = buffer.getSmart0();
				if (i_12_ == 0) {
					break;
				}
				location += -1 + i_12_;
				int y = 0x3f & location;
				int x = (0xff5 & location) >> 6;
				int configuration = buffer.g1();
				int rotation = 0x3 & configuration;
				int type = configuration >> 2;
				int z = location >> 12;
				if (i_6_ == z && i_4_ <= x && x < i_4_ - -8 && y >= i_3_ && 8 + i_3_ > y) {
					LocType definition = LocTypeList.list(objectId);
					int localX = getLocalX(definition.size3d, true, y & 0x7, x & 0x7, i_2_, definition.size2d, rotation) + i_5_;
					int localY = i_7_ + getLoxalY(x & 0x7, rotation, 0x7 & y, definition.size2d, definition.size3d, i_2_, (byte) -111);
					if ((localX ^ 0xffffffff) < -1 && localY > 0 && localX < 103 && localY < 103) {
						CollisionMap tileSetting = null;
						if (!bool) {
							int plane = i;
							if ((MapLoader.settings[1][localX][localY] & 0x2) == 2) {
								plane--;
							}
							if (plane >= 0) {
								tileSetting = tileSettings[plane];
							}
						}
						addGameObject(objectId, i, localX, localY, rotation + i_2_ & 0x3, tileSetting, !bool, type, i, bool);
					}
				}
			}
		}
	}

	public static final boolean cullingType(int i_25_) {
		if (i_25_ < 12 || i_25_ > 17) {
			return false;
		}
		return true;
	}

	static final void addGameObject(int locid, int tile_level, int tile_x, int tile_y, int rotation, CollisionMap regionPlane, boolean allowRoofs, int type, int current_level, boolean underwater) {
		if (allowRoofs && !ClientOptions.is_removeroofs() && 0 == (2 & MapLoader.settings[0][tile_x][tile_y])) {
			if (0 != (16 & MapLoader.settings[tile_level][tile_x][tile_y])) {
				return;
			}
			if (RSString.method144(tile_y, tile_x, tile_level) != StaticMethods.player_height) {
				return;
			}
		}

		if (tile_level < MapLoader.max_heights) {
			MapLoader.max_heights = tile_level;
		}

		LocType def = LocTypeList.list(locid);
		if (!GLManager.opengl_mode || !def.aBoolean1530) {
			int[][] current_heights = Scene.current_heightmap[current_level];
			int sizeY;
			int sizeX;
			if (rotation != 1 && rotation != 3) {
				sizeY = def.size3d;
				sizeX = def.size2d;
			} else {
				sizeY = def.size2d;
				sizeX = def.size3d;
			}
			int a;
			int b;
			if (sizeX + tile_x <= 104) {
				b = tile_x + (1 + sizeX >> 1);
				a = tile_x + (sizeX >> 1);
			} else {
				a = tile_x;
				b = 1 + tile_x;
			}
			int c;
			int d;
			if (sizeY + tile_y <= 104) {
				c = tile_y + (1 + sizeY >> 1);
				d = tile_y + (sizeY >> 1);
			} else {
				c = 1 + tile_y;
				d = tile_y;
			}
			int absolute_x = (tile_x << 7) + (sizeX << 6);
			int absolute_y = (tile_y << 7) + (sizeY << 6);
			int average_height = current_heights[b][c] + current_heights[a][c] + current_heights[b][d] + current_heights[a][d] >> 2;
			int shadow_height = 0;
			if (GLManager.opengl_mode && current_level != 0) {
				int[][] is_62_ = Scene.current_heightmap[0];
				shadow_height = average_height - (is_62_[b][c] + is_62_[b][d] + is_62_[a][d] + is_62_[a][c] >> 2);
			}
			int[][] is_62_ = null;
			if (underwater) {
				is_62_ = Scene.surface_heightmap[0];
			} else if (current_level < 3) {
				is_62_ = Scene.current_heightmap[1 + current_level];
			}
			long var24 = rotation << 20 | type << 14 | tile_y << 7 | tile_x | 0x40000000;
			if (def.solid == 0 || underwater) {
				var24 |= ~0x7fffffffffffffffL;
			}
			if (def.anInt2975 == 1) {
				var24 |= 0x400000L;
			}
			if (def.aBoolean1507) {
				var24 |= 2147483648L;
			}
			if (allowRoofs && def.isVisible()) {
				ClanChatMember.method881(tile_x, false, tile_y, def, tile_level, rotation);
			}
			boolean add_shadows = def.shadowed & !underwater;
			var24 |= (long) locid << 32;
			if (type == 22) {
				if (ClientOptions.clientoption_grounddecor || def.solid != 0 || def.clipping_type == 1 || def.aBoolean736) {
					SceneNode node;
					if (def.seqid == -1 && def.morphisms == null) {
						LocResult result = def.get_pair(current_heights, rotation, absolute_x, 22, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
						if (GLManager.opengl_mode && add_shadows) {
							SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
						}
						node = result.node;
					} else {
						node = new GameObject(current_level, tile_x, tile_y, locid, 22, rotation, def.seqid, def.aBoolean1492, null);
					}
					GroundDecoration.addGroundDecoration(tile_level, tile_x, tile_y, average_height, node, var24, def.aBoolean1502);
					if (def.clipping_type == 1 && regionPlane != null) {
						regionPlane.addTileFlag(tile_x, -111, tile_y);
					}
				}
			} else if (type == 10 || type == 11) {
				SceneNode abstractModel;
				if (def.seqid == -1 && def.morphisms == null && !def.opcode98) {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, 10, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				} else {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 10, rotation, def.seqid, def.aBoolean1492, null);
				}
				if (abstractModel != null) {
					boolean bool_63_ = Class91.method1453(tile_level, tile_x, tile_y, average_height, sizeX, sizeY, abstractModel, type == 11 ? 256 : 0, var24);
					if (def.soft_shadows && bool_63_ && allowRoofs) {
						int i_64_ = 15;
						if (abstractModel instanceof Model) {
							i_64_ = ((Model) abstractModel).get_size2d() / 4;
							if (i_64_ > 30) {
								i_64_ = 30;
							}
						}
						for (int i_65_ = 0; sizeX >= i_65_; i_65_++) {
							for (int i_66_ = 0; sizeY >= i_66_; i_66_++) {
								if (i_64_ > MapLoader.shadows_intensities[tile_level][tile_x - -i_65_][i_66_ + tile_y]) {
									MapLoader.shadows_intensities[tile_level][i_65_ + tile_x][i_66_ + tile_y] = (byte) i_64_;
								}
							}
						}
					}
				}
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.flagSolidObject(def.projectile_blocked, tile_y, sizeY, sizeX, 256, tile_x);
				}
			} else if (type >= 12) {
				SceneNode abstractModel;
				if (def.seqid == -1 && def.morphisms == null && !def.opcode98) {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, type, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				} else {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, type, rotation, def.seqid, def.aBoolean1492, null);
				}
				Class91.method1453(tile_level, tile_x, tile_y, average_height, 1, 1, abstractModel, 0, var24);
				if (allowRoofs && type >= 12 && type <= 17 && type != 13 && tile_level > 0) {
					MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 2340);
				}
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.flagSolidObject(def.projectile_blocked, tile_y, sizeY, sizeX, 256, tile_x);
				}
			} else if (type == 0) {
				SceneNode abstractModel;
				if (def.seqid == -1 && def.morphisms == null && !def.opcode98) {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, 0, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				} else {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 0, rotation, def.seqid, def.aBoolean1492, null);
				}
				WallObject.addWallObject(tile_level, tile_x, tile_y, average_height, abstractModel, null, CS2ScriptDefinition.anIntArray4259[rotation], 0, var24);
				if (allowRoofs) {
					if (rotation == 0) {
						if (def.soft_shadows) {
							MapLoader.shadows_intensities[tile_level][tile_x][tile_y] = (byte) 50;
							MapLoader.shadows_intensities[tile_level][tile_x][tile_y + 1] = (byte) 50;
						}
						if (def.cullable) {
							MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 585);
						}
					} else if (rotation != 1) {
						if (rotation == 2) {
							if (def.soft_shadows) {
								MapLoader.shadows_intensities[tile_level][tile_x + 1][tile_y] = (byte) 50;
								MapLoader.shadows_intensities[tile_level][tile_x + 1][1 + tile_y] = (byte) 50;
							}
							if (def.cullable) {
								MapLoader.cullings_map[tile_level][1 + tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][1 + tile_x][tile_y], 585);
							}
						} else if (rotation == 3) {
							if (def.soft_shadows) {
								MapLoader.shadows_intensities[tile_level][tile_x][tile_y] = (byte) 50;
								MapLoader.shadows_intensities[tile_level][1 + tile_x][tile_y] = (byte) 50;
							}
							if (def.cullable) {
								MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 1170);
							}
						}
					} else {
						if (def.soft_shadows) {
							MapLoader.shadows_intensities[tile_level][tile_x][1 + tile_y] = (byte) 50;
							MapLoader.shadows_intensities[tile_level][tile_x + 1][tile_y - -1] = (byte) 50;
						}
						if (def.cullable) {
							MapLoader.cullings_map[tile_level][tile_x][tile_y - -1] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y - -1], 1170);
						}
					}
				}
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.addWallFlag(def.projectile_blocked, tile_y, rotation, 60, tile_x, type);
				}
				if (def.offsetMultiplier != 16) {
					CollisionMap.method1306(tile_level, tile_x, tile_y, def.offsetMultiplier);
				}
			} else if (type == 1) {
				SceneNode abstractModel;
				if (def.seqid != -1 || def.morphisms != null || def.opcode98) {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 1, rotation, def.seqid, def.aBoolean1492, null);
				} else {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, 1, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				}
				WallObject.addWallObject(tile_level, tile_x, tile_y, average_height, abstractModel, null, StaticMethods.anIntArray3062[rotation], 0, var24);
				if (def.soft_shadows && allowRoofs) {
					if (rotation == 0) {
						MapLoader.shadows_intensities[tile_level][tile_x][1 + tile_y] = (byte) 50;
					} else if (rotation != 1) {
						if (rotation == 2) {
							MapLoader.shadows_intensities[tile_level][tile_x + 1][tile_y] = (byte) 50;
						} else if (rotation == 3) {
							MapLoader.shadows_intensities[tile_level][tile_x][tile_y] = (byte) 50;
						}
					} else {
						MapLoader.shadows_intensities[tile_level][tile_x + 1][tile_y - -1] = (byte) 50;
					}
				}
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.addWallFlag(def.projectile_blocked, tile_y, rotation, -14, tile_x, type);
				}
			} else if (type == 2) {
				int i_67_ = 0x3 & rotation + 1;
				SceneNode abstractModel;
				SceneNode abstractModel_68_;
				if (def.seqid == -1 && def.morphisms == null && !def.opcode98) {
					LocResult result = def.get_pair(current_heights, 4 + rotation, absolute_x, 2, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
					result = def.get_pair(current_heights, i_67_, absolute_x, 2, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel_68_ = result.node;
				} else {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 2, rotation + 4, def.seqid, def.aBoolean1492, null);
					abstractModel_68_ = new GameObject(current_level, tile_x, tile_y, locid, 2, i_67_, def.seqid, def.aBoolean1492, null);
				}
				WallObject.addWallObject(tile_level, tile_x, tile_y, average_height, abstractModel, abstractModel_68_, CS2ScriptDefinition.anIntArray4259[rotation], CS2ScriptDefinition.anIntArray4259[i_67_], var24);
				if (def.cullable && allowRoofs) {
					if (rotation == 0) {
						MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 585);
						MapLoader.cullings_map[tile_level][tile_x][1 + tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][1 + tile_y], 1170);
					} else if (rotation != 1) {
						if (rotation != 2) {
							if (rotation == 3) {
								MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 1170);
								MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 585);
							}
						} else {
							MapLoader.cullings_map[tile_level][tile_x + 1][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x + 1][tile_y], 585);
							MapLoader.cullings_map[tile_level][tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][tile_y], 1170);
						}
					} else {
						MapLoader.cullings_map[tile_level][tile_x][1 + tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][tile_x][1 + tile_y], 1170);
						MapLoader.cullings_map[tile_level][1 + tile_x][tile_y] = MathUtils.doBitwiseOr(MapLoader.cullings_map[tile_level][1 + tile_x][tile_y], 585);
					}
				}
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.addWallFlag(def.projectile_blocked, tile_y, rotation, 31, tile_x, type);
				}
				if (def.offsetMultiplier != 16) {
					CollisionMap.method1306(tile_level, tile_x, tile_y, def.offsetMultiplier);
				}
			} else if (type == 3) {
				SceneNode abstractModel;
				if (def.seqid != -1 || def.morphisms != null || def.opcode98) {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 3, rotation, def.seqid, def.aBoolean1492, null);
				} else {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, 3, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				}
				WallObject.addWallObject(tile_level, tile_x, tile_y, average_height, abstractModel, null, StaticMethods.anIntArray3062[rotation], 0, var24);
				if (def.soft_shadows && allowRoofs) {
					if (rotation == 0) {
						MapLoader.shadows_intensities[tile_level][tile_x][1 + tile_y] = (byte) 50;
					} else if (rotation != 1) {
						if (rotation != 2) {
							if (rotation == 3) {
								MapLoader.shadows_intensities[tile_level][tile_x][tile_y] = (byte) 50;
							}
						} else {
							MapLoader.shadows_intensities[tile_level][tile_x + 1][tile_y] = (byte) 50;
						}
					} else {
						MapLoader.shadows_intensities[tile_level][tile_x + 1][tile_y + 1] = (byte) 50;
					}
				}
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.addWallFlag(def.projectile_blocked, tile_y, rotation, 98, tile_x, type);
				}
			} else if (type == 9) {
				SceneNode abstractModel;
				if (def.seqid == -1 && def.morphisms == null && !def.opcode98) {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, type, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				} else {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, type, rotation, def.seqid, def.aBoolean1492, null);
				}
				Class91.method1453(tile_level, tile_x, tile_y, average_height, 1, 1, abstractModel, 0, var24);
				if (def.clipping_type != 0 && regionPlane != null) {
					regionPlane.flagSolidObject(def.projectile_blocked, tile_y, sizeY, sizeX, 256, tile_x);
				}
				if (def.offsetMultiplier != 16) {
					CollisionMap.method1306(tile_level, tile_x, tile_y, def.offsetMultiplier);
				}
			} else if (type == 4) {
				SceneNode abstractModel;
				if (def.seqid == -1 && def.morphisms == null && !def.opcode98) {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, 4, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				} else {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 4, rotation, def.seqid, def.aBoolean1492, null);
				}
				WallDecoration.addWallDecoration(tile_level, tile_x, tile_y, average_height, abstractModel, null, CS2ScriptDefinition.anIntArray4259[rotation], 0, 0, 0, var24);
			} else if (type == 5) {
				int i_69_ = 16;
				long l_70_ = WallObject.getWallObjectUid(tile_level, tile_x, tile_y);
				if (l_70_ != 0L) {
					i_69_ = LocTypeList.list(0x7fffffff & (int) (l_70_ >>> 32)).offsetMultiplier;
				}
				SceneNode abstractModel;
				if (def.seqid != -1 || def.morphisms != null || def.opcode98) {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 4, rotation, def.seqid, def.aBoolean1492, null);
				} else {
					LocResult result = def.get_pair(current_heights, rotation, absolute_x, 4, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x + -(StaticMethods.anIntArray2441[rotation] * 8), shadow_height, -(StaticMethods.anIntArray874[rotation] * 8) + absolute_y);
					}
					abstractModel = result.node;
				}
				WallDecoration.addWallDecoration(tile_level, tile_x, tile_y, average_height, abstractModel, null, CS2ScriptDefinition.anIntArray4259[rotation], 0, i_69_ * StaticMethods.anIntArray2441[rotation], i_69_ * StaticMethods.anIntArray874[rotation], var24);
			} else if (type == 6) {
				int i_71_ = 8;
				long uid = WallObject.getWallObjectUid(tile_level, tile_x, tile_y);
				if (uid != 0L) {
					i_71_ = LocTypeList.list((int) (uid >>> 32) & 0x7fffffff).offsetMultiplier / 2;
				}
				SceneNode abstractModel;
				if (def.seqid != -1 || def.morphisms != null || def.opcode98) {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 4, 4 + rotation, def.seqid, def.aBoolean1492, null);
				} else {
					LocResult result = def.get_pair(current_heights, rotation - -4, absolute_x, 4, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, -(8 * StaticMethods.anIntArray3164[rotation]) + absolute_x, shadow_height, -(8 * StaticMethods.anIntArray2114[rotation]) + absolute_y);
					}
					abstractModel = result.node;
				}
				WallDecoration.addWallDecoration(tile_level, tile_x, tile_y, average_height, abstractModel, null, 256, rotation, i_71_ * StaticMethods.anIntArray3164[rotation], StaticMethods.anIntArray2114[rotation] * i_71_, var24);
			} else if (type == 7) {
				int i_73_ = 0x3 & rotation + 2;
				SceneNode abstractModel;
				if (def.seqid != -1 || def.morphisms != null || def.opcode98) {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 4, 4 + i_73_, def.seqid, def.aBoolean1492, null);
				} else {
					LocResult result = def.get_pair(current_heights, 4 + i_73_, absolute_x, 4, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x, shadow_height, absolute_y);
					}
					abstractModel = result.node;
				}
				WallDecoration.addWallDecoration(tile_level, tile_x, tile_y, average_height, abstractModel, null, 256, i_73_, 0, 0, var24);
			} else if (type == 8) {
				int i_74_ = 8;
				long uid = WallObject.getWallObjectUid(tile_level, tile_x, tile_y);
				if (uid != 0L) {
					i_74_ = LocTypeList.list((int) (uid >>> 32) & 0x7fffffff).offsetMultiplier / 2;
				}
				int i_76_ = rotation + 2 & 0x3;
				SceneNode abstractModel;
				SceneNode abstractModel_77_;
				if (def.seqid != -1 || def.morphisms != null || def.opcode98) {
					abstractModel = new GameObject(current_level, tile_x, tile_y, locid, 4, rotation - -4, def.seqid, def.aBoolean1492, null);
					abstractModel_77_ = new GameObject(current_level, tile_x, tile_y, locid, 4, 4 + i_76_, def.seqid, def.aBoolean1492, null);
				} else {
					int var34 = 8 * StaticMethods.anIntArray2114[rotation];
					int var33 = StaticMethods.anIntArray3164[rotation] * 8;
					LocResult result = def.get_pair(current_heights, rotation - -4, absolute_x, 4, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x + -var33, shadow_height, -var34 + absolute_y);
					}
					abstractModel = result.node;
					result = def.get_pair(current_heights, i_76_ - -4, absolute_x, 4, average_height, is_62_, allowRoofs, absolute_y, null, add_shadows);
					if (GLManager.opengl_mode && add_shadows) {
						SceneShadowMapper.method2051(result.shadow, absolute_x - var33, shadow_height, -var34 + absolute_y);
					}
					abstractModel_77_ = result.node;
				}
				WallDecoration.addWallDecoration(tile_level, tile_x, tile_y, average_height, abstractModel, abstractModel_77_, 256, rotation, i_74_ * StaticMethods.anIntArray3164[rotation], i_74_ * StaticMethods.anIntArray2114[rotation], var24);
			}
		}

	}

	public static void decode_locs(byte[] bs, int yOff, int xOff, CollisionMap[] tileSettings, boolean underwater) {
		Packet buffer = new Packet(bs);
		int objectId = -1;
		for (;;) {
			int i_12_ = buffer.getUSmart2();
			if (i_12_ == 0) {
				break;
			}
			int location = 0;
			objectId += i_12_;
			for (;;) {
				int i_14_ = buffer.getSmart0();
				if (i_14_ == 0) {
					break;
				}
				location += -1 + i_14_;
				int plane = location >> 12;
				int yInsideRegion = location & 0x3f;
				int data = buffer.g1();
				int regionY = yInsideRegion + yOff;
				int type = data >> 2;
				int xInsideRegion = (0xfd8 & location) >> 6;
				int rotation = 0x3 & data;
				int regionX = xInsideRegion + xOff;
				if (regionX > 0 && regionY > 0 && regionX < 103 && regionY < 103) {
					CollisionMap tileSetting = null;
					if (!underwater) {
						int height = plane;
						if ((MapLoader.settings[1][regionX][regionY] & 0x2) == 2) {
							height--;
						}
						if (height > 0) {
							tileSetting = tileSettings[height];
						}
					}
					addGameObject(objectId, plane, regionX, regionY, rotation, tileSetting, !underwater, type, plane, underwater);
				}
			}
		}
	}

	static final int getLocalX(int i, boolean bool, int i_17_, int i_18_, int i_19_, int i_20_, int i_21_) {
		if ((i_21_ & 0x1) == 1) {
			int i_22_ = i_20_;
			i_20_ = i;
			i = i_22_;
		}
		if (bool != true) {
			return -91;
		}
		i_19_ &= 0x3;
		if (i_19_ == 0) {
			return i_18_;
		}
		if (i_19_ == 1) {
			return i_17_;
		}
		if (i_19_ == 2) {
			return 7 - i_18_ + 1 + -i_20_;
		}
		return 1 + -i + 7 + -i_17_;
	}

	static final int getLoxalY(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, byte b) {
		if (b >= -81) {
			getLoxalY(-51, 24, -33, -14, 61, 88, (byte) 12);
		}
		i_4_ &= 0x3;
		if ((0x1 & i_0_) == 1) {
			int i_5_ = i_2_;
			i_2_ = i_3_;
			i_3_ = i_5_;
		}
		if ((i_4_ ^ 0xffffffff) == -1) {
			return i_1_;
		}
		if (i_4_ == 1) {
			return -i + 7 + -i_2_ + 1;
		}
		if (i_4_ == 2) {
			return -i_3_ + 1 + -i_1_ + 7;
		}
		return i;
	}
}
