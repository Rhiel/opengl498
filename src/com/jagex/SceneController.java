package com.jagex;

import com.rs2.client.scene.Scene;

/**
 * Created by Chris on 3/11/2017.
 */
public class SceneController {

	public static int picked_tile_x;
	public static int picked_tile_y;

	public static void resetClickedTile(int i, int i_11_, int i_12_) {
		Scene.pick_mouseover_tile = true;
		Class71_Sub3.anInt2742 = i;
		StaticMethods.anInt3071 = i_11_;
		GroundItem.anInt2503 = i_12_;
		picked_tile_x = -1;
		picked_tile_y = -1;
	}

	public static int mixColour(int i, int i_13_) {
		i_13_ = i_13_ * (i & 0x7f) >> 7;
		if (i_13_ < 2) {
			i_13_ = 2;
		} else if (i_13_ > 126) {
			i_13_ = 126;
		}
		return (i & 0xff80) + i_13_;
	}

	public static boolean test_triangle_intersection(int i, int i_50_, int i_51_, int i_52_, int i_53_, int i_54_, int i_55_, int i_56_) {
		if (i_50_ < i_51_ && i_50_ < i_52_ && i_50_ < i_53_) {
			return false;
		}
		if (i_50_ > i_51_ && i_50_ > i_52_ && i_50_ > i_53_) {
			return false;
		}
		if (i < i_54_ && i < i_55_ && i < i_56_) {
			return false;
		}
		if (i > i_54_ && i > i_55_ && i > i_56_) {
			return false;
		}
		int i_57_ = (i_50_ - i_51_) * (i_55_ - i_54_) - (i - i_54_) * (i_52_ - i_51_);
		int i_58_ = (i_50_ - i_53_) * (i_54_ - i_56_) - (i - i_56_) * (i_51_ - i_53_);
		int i_59_ = (i_50_ - i_52_) * (i_56_ - i_55_) - (i - i_55_) * (i_53_ - i_52_);
		if (i_57_ * i_59_ > 0 && i_59_ * i_58_ > 0) {
			return true;
		}
		return false;
	}

	public static boolean method226(int i, int i_17_, int i_18_, int i_19_) {
		if (!Scene.method846(i, i_17_, i_18_)) {
			return false;
		}
		int i_20_ = i_17_ << TileConstants.SIZE_BITS;
		int i_21_ = i_18_ << TileConstants.SIZE_BITS;
		int i_22_ = Scene.current_heightmap[i][i_17_][i_18_] - 1;
		int i_23_ = i_22_ - 120;
		int i_24_ = i_22_ - 230;
		int i_25_ = i_22_ - 238;
		if (i_19_ < 16) {
			if (i_19_ == 1) {
				if (i_20_ > Camera.xCameraPosition) {
					if (!Scene.is_culled(i_20_, i_22_, i_21_)) {
						return false;
					}
					if (!Scene.is_culled(i_20_, i_22_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
				}
				if (i > 0) {
					if (!Scene.is_culled(i_20_, i_23_, i_21_)) {
						return false;
					}
					if (!Scene.is_culled(i_20_, i_23_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
				}
				if (!Scene.is_culled(i_20_, i_24_, i_21_)) {
					return false;
				}
				if (!Scene.is_culled(i_20_, i_24_, i_21_ + TileConstants.SIZE_1BY1)) {
					return false;
				}
				return true;
			}
			if (i_19_ == 2) {
				if (i_21_ < Camera.yCameraPosition) {
					if (!Scene.is_culled(i_20_, i_22_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_22_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
				}
				if (i > 0) {
					if (!Scene.is_culled(i_20_, i_23_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_23_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
				}
				if (!Scene.is_culled(i_20_, i_24_, i_21_ + TileConstants.SIZE_1BY1)) {
					return false;
				}
				if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_24_, i_21_ + TileConstants.SIZE_1BY1)) {
					return false;
				}
				return true;
			}
			if (i_19_ == 4) {
				if (i_20_ < Camera.xCameraPosition) {
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_22_, i_21_)) {
						return false;
					}
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_22_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
				}
				if (i > 0) {
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_23_, i_21_)) {
						return false;
					}
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_23_, i_21_ + TileConstants.SIZE_1BY1)) {
						return false;
					}
				}
				if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_24_, i_21_)) {
					return false;
				}
				if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_24_, i_21_ + TileConstants.SIZE_1BY1)) {
					return false;
				}
				return true;
			}
			if (i_19_ == 8) {
				if (i_21_ > Camera.yCameraPosition) {
					if (!Scene.is_culled(i_20_, i_22_, i_21_)) {
						return false;
					}
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_22_, i_21_)) {
						return false;
					}
				}
				if (i > 0) {
					if (!Scene.is_culled(i_20_, i_23_, i_21_)) {
						return false;
					}
					if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_23_, i_21_)) {
						return false;
					}
				}
				if (!Scene.is_culled(i_20_, i_24_, i_21_)) {
					return false;
				}
				if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_24_, i_21_)) {
					return false;
				}
				return true;
			}
		}
		if (!Scene.is_culled(i_20_ + 64, i_25_, i_21_ + 64)) {
			return false;
		}
		if (i_19_ == 16) {
			if (!Scene.is_culled(i_20_, i_24_, i_21_ + TileConstants.SIZE_1BY1)) {
				return false;
			}
			return true;
		}
		if (i_19_ == 32) {
			if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_24_, i_21_ + TileConstants.SIZE_1BY1)) {
				return false;
			}
			return true;
		}
		if (i_19_ == 64) {
			if (!Scene.is_culled(i_20_ + TileConstants.SIZE_1BY1, i_24_, i_21_)) {
				return false;
			}
			return true;
		}
		if (i_19_ == TileConstants.SIZE_1BY1) {
			if (!Scene.is_culled(i_20_, i_24_, i_21_)) {
				return false;
			}
			return true;
		}
		return true;
	}

	public static final boolean isOnScreen(int x, int var1, int var2, int z, int far) {
		int xx1 = z * Camera.xCurveSine + x * Camera.xCurveCosine >> 16;
		int xx2 = z * Camera.xCurveCosine - x * Camera.xCurveSine >> 16;
		int yy1 = var1 * Camera.yCurveSine + xx2 * Camera.yCurveCosine >> 16;
		int yy2 = var1 * Camera.yCurveCosine - xx2 * Camera.yCurveSine >> 16;
		if (yy1 < 1) {
			yy1 = 1;
		}

		int screen_x1 = (xx1 << 9) / yy1;
		int screen_y1 = (yy2 << 9) / yy1;
		int zz0 = var2 * Camera.yCurveSine + xx2 * Camera.yCurveCosine >> 16;
		int yy0 = var2 * Camera.yCurveCosine - xx2 * Camera.yCurveSine >> 16;
		if (zz0 < 1) {
			zz0 = 1;
		}

		int screen_x2 = (xx1 << 9) / zz0;
		int screen_y2 = (yy0 << 9) / zz0;
		return !(yy1 < 50 && zz0 < 50) && !(yy1 > far && zz0 > far) && !(screen_x1 < Viewport.screen_left && screen_x2 < Viewport.screen_left) && !(screen_x1 > Viewport.screen_right && screen_x2 > Viewport.screen_right) && !(screen_y1 < Viewport.screen_top && screen_y2 < Viewport.screen_top) && (screen_y1 <= Viewport.screen_bottom || screen_y2 <= Viewport.screen_bottom);
	}

	// -50 -10 -50
	public static void method375(int i, int i_14_, int i_15_) {
		for (int z = 0; z < Scene.visible_level_end; z++) {
			for (int x = 0; x < Scene.width; x++) {
				for (int y = 0; y < Scene.height; y++) {
					Ground thisTile = Scene.current_grounds[z][x][y];
					if (thisTile != null) {
						WallObject wallObject = thisTile.wall_object;
						if (wallObject != null && wallObject.first_node.method998()) {
							method391(wallObject.first_node, z, x, y, 1, 1);
							if (wallObject.second_node != null && wallObject.second_node.method998()) {
								method391(wallObject.second_node, z, x, y, 1, 1);
								wallObject.first_node.sharelight(wallObject.second_node, 0, 0, 0, false);
								wallObject.second_node = wallObject.second_node.method994(i, i_14_, i_15_);
							}
							wallObject.first_node = wallObject.first_node.method994(i, i_14_, i_15_);
						}
						for (int i_19_ = 0; i_19_ < thisTile.num_interactives; i_19_++) {
							InteractiveEntity interactiveObject = thisTile.interactives[i_19_];
							if (interactiveObject != null && interactiveObject.node.method998()) {
								method391(interactiveObject.node, z, x, y, interactiveObject.anInt613 - interactiveObject.anInt601 + 1, interactiveObject.anInt599 - interactiveObject.anInt607 + 1);
								interactiveObject.node = interactiveObject.node.method994(i, i_14_, i_15_);
							}
						}
						GroundDecoration groundDecoration = thisTile.decoration;
						if (groundDecoration != null && groundDecoration.node.method998()) {
							StaticMethods2.method234(groundDecoration.node, z, x, y);
							groundDecoration.node = groundDecoration.node.method994(i, i_14_, i_15_);
						}
					}
				}
			}
		}
	}

	public static void method391(SceneNode abstractModel, int plane, int coordX, int coordY, int val0_1, int val1_1) {
		boolean bool = true;
		int regionX = coordX;
		int absoluteY = coordX + val0_1;
		int regionY = coordY - 1;
		int absoluteX = coordY + val1_1;
		for (int z = plane; z <= plane + 1; z++) {
			if (z != Scene.visible_level_end) {
				for (int x = regionX; x <= absoluteY; x++) {
					if (x >= 0 && x < Scene.width) {
						for (int y = regionY; y <= absoluteX; y++) {
							if (y >= 0 && y < Scene.height && (!bool || x >= absoluteY || y >= absoluteX || y < coordY && x != coordX)) {
								Ground class23_sub1 = Scene.current_grounds[z][x][y];
								if (class23_sub1 != null) {
									int i_12_ = (Scene.current_heightmap[z][x][y] + Scene.current_heightmap[z][x + 1][y] + Scene.current_heightmap[z][x][y + 1] + Scene.current_heightmap[z][x + 1][y + 1]) / 4 - (Scene.current_heightmap[plane][coordX][coordY] + Scene.current_heightmap[plane][coordX + 1][coordY] + Scene.current_heightmap[plane][coordX][coordY + 1] + Scene.current_heightmap[plane][coordX + 1][coordY + 1]) / 4;
									WallObject wallObject = class23_sub1.wall_object;
									if (wallObject != null) {
										if (wallObject.first_node.method998()) {
											abstractModel.sharelight(wallObject.first_node, (x - coordX) * TileConstants.SIZE_1BY1 + (1 - val0_1) * 64, i_12_, (y - coordY) * TileConstants.SIZE_1BY1 + (1 - val1_1) * 64, bool);
										}
										if (wallObject.second_node != null && wallObject.second_node.method998()) {
											abstractModel.sharelight(wallObject.second_node, (x - coordX) * TileConstants.SIZE_1BY1 + (1 - val0_1) * 64, i_12_, (y - coordY) * TileConstants.SIZE_1BY1 + (1 - val1_1) * 64, bool);
										}
									}
									for (int i_13_ = 0; i_13_ < class23_sub1.num_interactives; i_13_++) {
										InteractiveEntity interactiveObject = class23_sub1.interactives[i_13_];
										if (interactiveObject != null && interactiveObject.node.method998() && (x == interactiveObject.anInt601 || x == regionX) && (y == interactiveObject.anInt607 || y == regionY)) {
											int i_14_ = interactiveObject.anInt613 - interactiveObject.anInt601 + 1;
											int i_15_ = interactiveObject.anInt599 - interactiveObject.anInt607 + 1;
											abstractModel.sharelight(interactiveObject.node, (interactiveObject.anInt601 - coordX) * TileConstants.SIZE_1BY1 + (i_14_ - val0_1) * 64, i_12_, (interactiveObject.anInt607 - coordY) * TileConstants.SIZE_1BY1 + (i_15_ - val1_1) * 64, bool);
										}
									}
								}
							}
						}
					}
				}
				regionX--;
				bool = false;
			}
		}
	}

	public static boolean method298(int i, int i_1_, int i_2_, long l) {
		Ground tile = Scene.current_grounds[i][i_1_][i_2_];
		if (tile == null) {
			return false;
		}
		if (tile.wall_object != null && tile.wall_object.uid == l) {
			return true;
		}
		if (tile.wall_decoration != null && tile.wall_decoration.uid == l) {
			return true;
		}
		if (tile.decoration != null && tile.decoration.uid == l) {
			return true;
		}
		for (int i_3_ = 0; i_3_ < tile.num_interactives; i_3_++) {
			if (tile.interactives[i_3_].uid == l) {
				return true;
			}
		}
		return false;
	}
}
