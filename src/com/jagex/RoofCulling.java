package com.jagex;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.rs2.client.scene.Scene;

/**
 * @author Walied K. Yassen
 */
public class RoofCulling {

	public static int[] anIntArray2696 = new int[2];
	public static int[] anIntArray2021 = new int[2];
	public static int[] anIntArray1871 = new int[2];
	public static int[] anIntArray3959 = new int[2];
	public static int[] anIntArray686 = new int[2];
	public static byte[][][] tile_settings;

	public static void update_culler_type() {
		int var1 = get_culling_type((byte) 70);
		if (0 == var1) {
			tile_settings = null;
			initialise_helpers(0);
		} else if (~var1 == -2) {
			initialise_settings((byte) 0);
			initialise_helpers(512);
			method257();
		} else {
			initialise_settings((byte) (-4 + Scene.render_cycle & 255));
			initialise_helpers(2);
		}
	}

	public static void initialise_helpers(int var0) {
		anIntArray2696 = new int[var0];
		anIntArray2021 = new int[var0];
		anIntArray1871 = new int[var0];
		anIntArray3959 = new int[var0];
		anIntArray686 = new int[var0];
	}

	public static void initialise_settings(byte settings) {
		if (null == tile_settings) {
			tile_settings = new byte[4][104][104];
		}
		for (int var2 = 0; var2 < 4; ++var2) {
			for (int var3 = 0; ~var3 > -105; ++var3) {
				for (int var4 = 0; 104 > var4; ++var4) {
					tile_settings[var2][var3][var4] = settings;
				}
			}
		}
	}

	static final void method257() {
		int var1 = 0;
		for (int var2 = 0; -105 < ~var2; ++var2) {
			for (int var3 = 0; ~var3 > -105; ++var3) {
				if (method2031(true, var2, var3, Scene.current_grounds, var1)) {
					++var1;
				}

				if (var1 >= 512) {
					return;
				}
			}
		}
	}

	public static final void method725(int var0) {
		if (~get_culling_type((byte) 70) == -3) {
			byte var2 = (byte) (255 & Scene.render_cycle + -4);
			int var3 = Scene.render_cycle % 104;
			int var4;
			int var5;
			for (var4 = 0; -5 < ~var4; ++var4) {
				for (var5 = 0; 104 > var5; ++var5) {
					tile_settings[var4][var3][var5] = var2;
				}
			}
			if (ObjType.localHeight != 3) {
				for (var4 = 0; -3 < ~var4; ++var4) {
					anIntArray686[var4] = -1000000;
					anIntArray2696[var4] = 1000000;
					anIntArray2021[var4] = 0;
					anIntArray1871[var4] = 1000000;
					anIntArray3959[var4] = 0;
				}
				if (!Camera.cameraViewChanged) {
					if ((4 & MapLoader.settings[ObjType.localHeight][GameClient.currentPlayer.bound_extents_x >> 7][GameClient.currentPlayer.bound_extents_z >> 7]) != 0) {
						method2031(false, GameClient.currentPlayer.bound_extents_x >> 7, GameClient.currentPlayer.bound_extents_z >> 7, Scene.current_grounds, 0);
					}
					if (-311 < ~Camera.yCameraCurve) {
						int var7 = GameClient.currentPlayer.bound_extents_z >> 7;
						var5 = Camera.yCameraPos >> 7;
						int var9;
						if (var5 < var7) {
							var9 = var7 - var5;
						} else {
							var9 = -var7 + var5;
						}

						var4 = Camera.xCameraPos >> 7;
						int var6 = GameClient.currentPlayer.bound_extents_x >> 7;
						int var8;
						if (~var6 < ~var4) {
							var8 = -var4 + var6;
						} else {
							var8 = -var6 + var4;
						}

						int var10;
						int var11;
						if (var8 > var9) {
							var11 = '\u8000';
							var10 = var9 * 65536 / var8;

							while (var6 != var4) {
								if (var6 <= var4) {
									if (var4 > var6) {
										--var4;
									}
								} else {
									++var4;
								}

								if (~(MapLoader.settings[ObjType.localHeight][var4][var5] & 4) != -1) {
									method2031(false, var4, var5, Scene.current_grounds, 1);
									break;
								}

								var11 += var10;
								if (var11 >= 65536) {
									if (var5 >= var7) {
										if (var7 < var5) {
											--var5;
										}
									} else {
										++var5;
									}

									var11 -= 65536;
									if (~(4 & MapLoader.settings[ObjType.localHeight][var4][var5]) != -1) {
										method2031(false, var4, var5, Scene.current_grounds, 1);
										break;
									}
								}
							}
						} else {
							var11 = '\u8000';
							var10 = 65536 * var8 / var9;

							while (~var5 != ~var7) {
								if (var5 < var7) {
									++var5;
								} else if (var5 > var7) {
									--var5;
								}

								if (~(4 & MapLoader.settings[ObjType.localHeight][var4][var5]) != -1) {
									method2031(false, var4, var5, Scene.current_grounds, 1);
									break;
								}

								var11 += var10;
								if (~var11 <= -65537) {
									if (var6 > var4) {
										++var4;
									} else if (var6 < var4) {
										--var4;
									}

									var11 -= 65536;
									if ((4 & MapLoader.settings[ObjType.localHeight][var4][var5]) != 0) {
										method2031(false, var4, var5, Scene.current_grounds, 1);
										break;
									}
								}
							}
						}
					}
				} else {
					var4 = Scene.get_average_height(ObjType.localHeight, Camera.xCameraPos, Camera.yCameraPos);
					if (800 > var4 + -Camera.zCameraPos && (4 & MapLoader.settings[ObjType.localHeight][Camera.xCameraPos >> 7][Camera.yCameraPos >> 7]) != 0) {
						method2031(false, Camera.xCameraPos >> 7, Camera.yCameraPos >> 7, Scene.current_grounds, 1);
					}
				}

			}
		}
	}

	static final boolean method2031(boolean var1, int var2, int var3, Ground[][][] var4, int var5) {
		byte var6 = !var1 ? (byte) (255 & Scene.render_cycle) : 1;
		if (~var6 == ~tile_settings[ObjType.localHeight][var2][var3]) {
			return false;
		} else if (-1 == ~(MapLoader.settings[ObjType.localHeight][var2][var3] & 4)) {
			return false;
		} else {
			int var8 = 0;
			byte var7 = 0;
			PositionedGraphicNode.queueX[var7] = var2;
			int var23 = var7 + 1;
			StaticMethods.queueY[var7] = var3;
			tile_settings[ObjType.localHeight][var2][var3] = var6;

			while (~var8 != ~var23) {
				int var10 = (16740943 & PositionedGraphicNode.queueX[var8]) >> 16;
				int var11 = 255 & PositionedGraphicNode.queueX[var8] >> 24;
				int var9 = PositionedGraphicNode.queueX[var8] & '\uffff';
				int var13 = (StaticMethods.queueY[var8] & 16721603) >> 16;
				int var12 = StaticMethods.queueY[var8] & '\uffff';
				var8 = 4095 & 1 + var8;
				boolean var14 = false;
				boolean var15 = false;
				if (0 == (MapLoader.settings[ObjType.localHeight][var9][var12] & 4)) {
					var14 = true;
				}
				label257: for (int var16 = 1 + ObjType.localHeight; 3 >= var16; ++var16) {
					if (-1 == ~(MapLoader.settings[var16][var9][var12] & 8)) {
						int var18;
						int var20;
						if (var14 && var4[var16][var9][var12] != null) {
							if (null != var4[var16][var9][var12].wall_object) {
								int size = RoofCulling.get_roof_edge_size(var10);
								if (~var4[var16][var9][var12].wall_object.anInt1055 == ~size || var4[var16][var9][var12].wall_object.anInt1059 == size) {
									continue;
								}

								if (0 != var11) {
									var18 = RoofCulling.get_roof_edge_size(var11);
									if (var18 == var4[var16][var9][var12].wall_object.anInt1055 || var4[var16][var9][var12].wall_object.anInt1059 == var18) {
										continue;
									}
								}

								if (var13 != 0) {
									var18 = RoofCulling.get_roof_edge_size(var13);
									if (var18 == var4[var16][var9][var12].wall_object.anInt1055 || ~var18 == ~var4[var16][var9][var12].wall_object.anInt1059) {
										continue;
									}
								}
							}

							if (var4[var16][var9][var12].interactives != null) {
								for (int var17 = 0; ~var4[var16][var9][var12].num_interactives < ~var17; ++var17) {
									var18 = (int) (63L & var4[var16][var9][var12].interactives[var17].uid >> 14);
									if (-22 == ~var18) {
										var18 = 19;
									}

									int var19 = (int) (var4[var16][var9][var12].interactives[var17].uid >> 20 & 3L);
									var20 = var18 | var19 << 6;
									if (~var20 == ~var10 || var11 != 0 && var20 == var11 || -1 != ~var13 && var13 == var20) {
										continue label257;
									}
								}
							}
						}

						var15 = true;
						Ground var24 = var4[var16][var9][var12];
						if (var24 != null && ~var24.num_interactives < -1) {
							for (var18 = 0; ~var24.num_interactives < ~var18; ++var18) {
								InteractiveEntity var25 = var24.interactives[var18];
								if (~var25.anInt613 != ~var25.anInt601 || ~var25.anInt599 != ~var25.anInt607) {
									for (var20 = var25.anInt601; ~var25.anInt613 <= ~var20; ++var20) {
										for (int var21 = var25.anInt607; ~var21 >= ~var25.anInt599; ++var21) {
											tile_settings[var16][var20][var21] = var6;
										}
									}
								}
							}
						}

						tile_settings[var16][var9][var12] = var6;
					}
				}

				if (var15) {
					if (Scene.current_heightmap[ObjType.localHeight - -1][var9][var12] > anIntArray686[var5]) {
						anIntArray686[var5] = Scene.current_heightmap[ObjType.localHeight + 1][var9][var12];
					}
					int var16 = var9 << 7;
					if (var16 >= anIntArray2696[var5]) {
						if (~anIntArray2021[var5] > ~var16) {
							anIntArray2021[var5] = var16;
						}
					} else {
						anIntArray2696[var5] = var16;
					}
					int var17 = var12 << 7;
					if (~anIntArray1871[var5] < ~var17) {
						anIntArray1871[var5] = var17;
					} else if (anIntArray3959[var5] < var17) {
						anIntArray3959[var5] = var17;
					}
				}

				if (!var14) {
					if (-2 >= ~var9 && tile_settings[ObjType.localHeight][-1 + var9][var12] != var6) {
						PositionedGraphicNode.queueX[var23] = performOr(performOr(var9 - 1, 1179648), -754974720);
						StaticMethods.queueY[var23] = performOr(var12, 1245184);
						var23 = 1 + var23 & 4095;
						tile_settings[ObjType.localHeight][var9 - 1][var12] = var6;
					}
					++var12;
					if (104 > var12) {
						if (-1 >= ~(var9 + -1) && ~var6 != ~tile_settings[ObjType.localHeight][-1 + var9][var12] && ~(MapLoader.settings[ObjType.localHeight][var9][var12] & 4) == -1 && ~(MapLoader.settings[ObjType.localHeight][-1 + var9][var12 + -1] & 4) == -1) {
							PositionedGraphicNode.queueX[var23] = performOr(1375731712, performOr(1179648, -1 + var9));
							StaticMethods.queueY[var23] = performOr(var12, 1245184);
							tile_settings[ObjType.localHeight][-1 + var9][var12] = var6;
							var23 = 1 + var23 & 4095;
						}

						if (var6 != tile_settings[ObjType.localHeight][var9][var12]) {
							PositionedGraphicNode.queueX[var23] = performOr(318767104, performOr(var9, 5373952));
							StaticMethods.queueY[var23] = performOr(5439488, var12);
							var23 = 4095 & 1 + var23;
							tile_settings[ObjType.localHeight][var9][var12] = var6;
						}

						if (-105 < ~(1 + var9) && tile_settings[ObjType.localHeight][var9 + 1][var12] != var6 && 0 == (MapLoader.settings[ObjType.localHeight][var9][var12] & 4) && ~(MapLoader.settings[ObjType.localHeight][1 + var9][var12 - 1] & 4) == -1) {
							PositionedGraphicNode.queueX[var23] = performOr(-1845493760, performOr(5373952, var9 + 1));
							StaticMethods.queueY[var23] = performOr(5439488, var12);
							tile_settings[ObjType.localHeight][var9 - -1][var12] = var6;
							var23 = 4095 & var23 - -1;
						}
					}

					--var12;
					if (104 > 1 + var9 && var6 != tile_settings[ObjType.localHeight][var9 - -1][var12]) {
						PositionedGraphicNode.queueX[var23] = performOr(performOr(1 + var9, 9568256), 1392508928);
						StaticMethods.queueY[var23] = performOr(var12, 9633792);
						tile_settings[ObjType.localHeight][1 + var9][var12] = var6;
						var23 = var23 + 1 & 4095;
					}

					--var12;
					if (-1 >= ~var12) {
						if (0 <= var9 + -1 && tile_settings[ObjType.localHeight][-1 + var9][var12] != var6 && ~(MapLoader.settings[ObjType.localHeight][var9][var12] & 4) == -1 && ~(MapLoader.settings[ObjType.localHeight][var9 + -1][1 + var12] & 4) == -1) {
							PositionedGraphicNode.queueX[var23] = performOr(performOr(-1 + var9, 13762560), 301989888);
							StaticMethods.queueY[var23] = performOr(var12, 13828096);
							tile_settings[ObjType.localHeight][-1 + var9][var12] = var6;
							var23 = 4095 & var23 - -1;
						}

						if (var6 != tile_settings[ObjType.localHeight][var9][var12]) {
							PositionedGraphicNode.queueX[var23] = performOr(performOr(var9, 13762560), -1828716544);
							StaticMethods.queueY[var23] = performOr(13828096, var12);
							var23 = var23 - -1 & 4095;
							tile_settings[ObjType.localHeight][var9][var12] = var6;
						}

						if (-105 < ~(var9 - -1) && tile_settings[ObjType.localHeight][var9 + 1][var12] != var6 && ~(4 & MapLoader.settings[ObjType.localHeight][var9][var12]) == -1 && ~(MapLoader.settings[ObjType.localHeight][1 + var9][1 + var12] & 4) == -1) {
							PositionedGraphicNode.queueX[var23] = performOr(-771751936, performOr(var9 - -1, 9568256));
							StaticMethods.queueY[var23] = performOr(9633792, var12);
							tile_settings[ObjType.localHeight][var9 + 1][var12] = var6;
							var23 = 4095 & 1 + var23;
						}
					}
				}
			}
			if (-1000000 != anIntArray686[var5]) {
				anIntArray686[var5] += 10;
				anIntArray2696[var5] -= 50;
				anIntArray2021[var5] += 50;
				anIntArray3959[var5] += 50;
				anIntArray1871[var5] -= 50;
			}
			return true;
		}
	}

	public static int get_roof_edge_size(int settings) {
		int type = settings & 63;
		int rotation = (settings & 217) >> 6;
		if (type == 18) {
			if (rotation == 0) {
				return 1;
			}
			if (rotation == 1) {
				return 2;
			}
			if (rotation == 2) {
				return 4;
			}
			if (rotation == 3) {
				return 8;
			}
		} else if (type == 19 || type == 21) {
			if (rotation == 0) {
				return 16;
			}
			if (rotation == 1) {
				return 32;
			}
			if (rotation == 2) {
				return 64;
			}
			if (rotation == 3) {
				return 128;
			}
		}
		return 0;
	}

	// 0 = always shown
	// 1 = always removed
	// 2 = selectively
	public static int get_culling_type(byte var0) {
		return !ClientOptions.clientoption_removeroofs_override ? !ClientOptions.is_removeroofs() ? 1 : ClientOptions.clientoption_removeroofs_selective ? 2 : 1 : 0;
	}

	private static int performOr(int first, int second) {
		return first | second;
	}
}
