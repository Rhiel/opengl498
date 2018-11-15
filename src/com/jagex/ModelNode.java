package com.jagex;
/* Class23_Sub13_Sub25 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.shadow.SceneShadowMapper;

public class ModelNode extends Queuable {
	static short[] aShortArray4283;
	public SceneNode model;
	static RSString aClass16_4288 = RSString.createString("null");
	static RSString aClass16_4296;

	ModelNode(SceneNode abstractModel) {
		model = abstractModel;
	}

	static final void method855(int i, Entity entity) {
		entity.current_standing_animation = entity.standAnimation;
		if (entity.anInt2660 == 0) {
			entity.anInt2632 = 0;
		} else {
			if (entity.current_performing_seqid != -1 && entity.anInt2828 == 0) {
				SeqType anim = SeqTypeList.list(entity.current_performing_seqid);
				if (entity.anInt2640 > 0 && (anim.interrupt_type ^ 0xffffffff) == -1) {
					entity.anInt2632++;
					return;
				}
				if (entity.anInt2640 <= 0 && (anim.move_type ^ 0xffffffff) == -1) {
					entity.anInt2632++;
					return;
				}
			}
			int i_2_ = entity.bound_extents_x;
			int i_3_ = entity.bound_extents_z;
			int i_4_ = 64 * entity.size + entity.waypointsX[entity.anInt2660 - 1] * 128;
			int i_5_ = 64 * entity.size + entity.waypointsY[entity.anInt2660 - 1] * 128;
			if (i_4_ - i_2_ > 256 || -i_2_ + i_4_ < -256 || i_5_ - i_3_ > 256 || -i_3_ + i_5_ < -256) {
				entity.bound_extents_z = i_5_;
				entity.bound_extents_x = i_4_;
			} else {
				if (i_4_ <= i_2_) {
					if ((i_2_ ^ 0xffffffff) < (i_4_ ^ 0xffffffff)) {
						if (i_5_ > i_3_) {
							entity.faceDirection = 768;
						} else if (i_5_ >= i_3_) {
							entity.faceDirection = 512;
						} else {
							entity.faceDirection = 256;
						}
					} else if (i_5_ <= i_3_) {
						if (i_5_ < i_3_) {
							entity.faceDirection = 0;
						}
					} else {
						entity.faceDirection = 1024;
					}
				} else if (i_3_ >= i_5_) {
					if ((i_3_ ^ 0xffffffff) >= (i_5_ ^ 0xffffffff)) {
						entity.faceDirection = 1536;
					} else {
						entity.faceDirection = 1792;
					}
				} else {
					entity.faceDirection = 1280;
				}
				int animationId = entity.turn180;
				int i_7_ = 4;
				int i_8_ = 0x7ff & -entity.anInt2680 + entity.faceDirection;
				if (i_8_ > 1024) {
					i_8_ -= 2048;
				}

				if (i_8_ >= -256 && i_8_ <= 256) {
					animationId = entity.walkAnimation;
				} else if (i_8_ >= 256 && i_8_ < 768) {
					animationId = entity.turn90ccw;
				} else if (i_8_ >= -768 && i_8_ <= -256) {
					animationId = entity.turn90cw;
				}
				if (animationId == -1) {
					animationId = entity.walkAnimation;
				}
				boolean bool = true;
				entity.current_standing_animation = animationId;
				if (entity instanceof NPC) {
					bool = ((NPC) entity).config.aBoolean4178;
				}
				if (!bool) {
					if (entity.anInt2660 > 1) {
						i_7_ = 6;
					}
					if (entity.anInt2660 > 2) {
						i_7_ = 8;
					}
					if (entity.anInt2632 > 0 && entity.anInt2660 > 1) {
						i_7_ = 8;
						entity.anInt2632--;
					}
				} else {
					if (entity.anInt2680 != entity.faceDirection && entity.faceIndex == -1 && entity.index != 0) {
						i_7_ = 2;
					}
					if (entity.anInt2660 > 2) {
						i_7_ = 6;
					}
					if (entity.anInt2660 > 3) {
						i_7_ = 8;
					}
					if (entity.anInt2632 > 0 && entity.anInt2660 > 1) {
						entity.anInt2632--;
						i_7_ = 8;
					}
				}
				if (entity.aBooleanArray2718[entity.anInt2660 + -1]) {
					i_7_ <<= 1;
				}
				if ((i_5_ ^ 0xffffffff) < (i_3_ ^ 0xffffffff)) {
					entity.bound_extents_z += i_7_;
					if (entity.bound_extents_z > i_5_) {
						entity.bound_extents_z = i_5_;
					}
				} else if (i_3_ > i_5_) {
					entity.bound_extents_z -= i_7_;
					if ((entity.bound_extents_z ^ 0xffffffff) > (i_5_ ^ 0xffffffff)) {
						entity.bound_extents_z = i_5_;
					}
				}
				if (i != 2) {
					aShortArray4283 = null;
				}
				if (i_7_ >= 8 && entity.walkAnimation == entity.current_standing_animation && entity.runAnimation != -1) {
					entity.current_standing_animation = entity.runAnimation;
				}
				if ((i_4_ ^ 0xffffffff) >= (i_2_ ^ 0xffffffff)) {
					if ((i_4_ ^ 0xffffffff) > (i_2_ ^ 0xffffffff)) {
						entity.bound_extents_x -= i_7_;
						if (entity.bound_extents_x < i_4_) {
							entity.bound_extents_x = i_4_;
						}
					}
				} else {
					entity.bound_extents_x += i_7_;
					if ((i_4_ ^ 0xffffffff) > (entity.bound_extents_x ^ 0xffffffff)) {
						entity.bound_extents_x = i_4_;
					}
				}
				if (i_4_ == entity.bound_extents_x && entity.bound_extents_z == i_5_) {
					entity.anInt2660--;
					if (entity.anInt2640 > 0) {
						entity.anInt2640--;
					}
				}
			}
		}
	}

	public static void method856(byte b) {
		Varbit.varbitContainer = null;
		aClass16_4288 = null;
		GameClient.software_frame_buffer = null;
		aClass16_4296 = null;
		aShortArray4283 = null;
	}

	static final void method857(int x, CollisionMap var6, int var4, int var5, int y, int z) {
		long uid = 0L;
		if (var4 == 0) {
			uid = WallObject.getWallObjectUid(z, x, y);
		}
		if (var4 == 1) {
			uid = WallDecoration.getWallDecorationUid(z, x, y);
		}
		if (var4 == 2) {
			uid = Scene.getInteractiveUid(z, x, y);
		}
		if (var4 == 3) {
			uid = GroundDecoration.getGroundDecorationUid(z, x, y);
		}
		int id = 0x7fffffff & (int) (uid >>> 32);
		int type = (0x7df06 & (int) uid) >> 14;
		int rotation = (0x39a584 & (int) uid) >> 20;
		LocType def = LocTypeList.list(id);
		if (uid != 0L) {
			SceneNode first_node = null;
			SceneNode second_node = null;
			if (var4 == 0) {
				WallObject wall_object = Class54.method1174(z, x, y);
				if (null != wall_object) {
					first_node = wall_object.first_node;
					second_node = wall_object.second_node;
				}
				if (def.clipping_type != 0) {
					var6.method1295(true, def.projectile_blocked, x, type, rotation, y);
				}
			}
			if (var4 == 1) {
				WallDecoration wall_decoration = Class44.method1127(z, x, y);
				if (wall_decoration != null) {
					first_node = wall_decoration.first_node;
					second_node = wall_decoration.second_node;
				}
			}
			if (var4 == 2) {
				InteractiveEntity entity = ObjType.method731(z, x, y);
				if (null != entity) {
					first_node = entity.node;
				}
				if (def.clipping_type != 0 && x + def.size2d < 104 && y + def.size2d < 104 && x + def.size3d < 104 && y + def.size3d < 104) {
					var6.method1299(x, def.size2d, 31317, def.projectile_blocked, def.size3d, rotation, y);
				}
			}
			if (var4 == 3) {
				GroundDecoration decoration = Class47.method1145(z, x, y);
				if (decoration != null) {
					first_node = decoration.node;
				}
				if (def.clipping_type == 1) {
					var6.method1499(x, y);
				}
			}

			if (GLManager.opengl_mode && def.shadowed) {
				if (2 != type) {
					if (5 != type) {
						if (-7 == ~type) {
							if (first_node instanceof GameObject) {
								((GameObject) first_node).reset_shadows(-1);
							} else {
								SceneShadowMapper.method840(def, 8 * StaticMethods.anIntArray2114[rotation], 4 - -rotation, 8 * StaticMethods.anIntArray3164[rotation], 4, x, y, var5);
							}
						} else if (-8 == ~type) {
							if (first_node instanceof GameObject) {
								((GameObject) first_node).reset_shadows(-1);
							} else {
								SceneShadowMapper.method840(def, 0, 4 - -(3 & 2 + rotation), 0, 4, x, y, var5);
							}
						} else if (type == 8) {
							if (!(first_node instanceof GameObject)) {
								SceneShadowMapper.method840(def, StaticMethods.anIntArray2114[rotation] * 8, rotation + 4, 8 * StaticMethods.anIntArray3164[rotation], 4, x, y, var5);
							} else {
								((GameObject) first_node).reset_shadows(-1);
							}

							if (second_node instanceof GameObject) {
								((GameObject) second_node).reset_shadows(-1);
							} else {
								SceneShadowMapper.method840(def, StaticMethods.anIntArray2114[rotation] * 8, 4 - -(3 & 2 + rotation), StaticMethods.anIntArray3164[rotation] * 8, 4, x, y, var5);
							}
						} else if (11 != type) {
							if (first_node instanceof GameObject) {
								((GameObject) first_node).reset_shadows(-1);
							} else {
								SceneShadowMapper.method840(def, 0, rotation, 0, type, x, y, var5);
							}
						} else if (first_node instanceof GameObject) {
							((GameObject) first_node).reset_shadows(-1);
						} else {
							SceneShadowMapper.method840(def, 0, 4 + rotation, 0, 10, x, y, var5);
						}
					} else if (first_node instanceof GameObject) {
						((GameObject) first_node).reset_shadows(-1);
					} else {
						SceneShadowMapper.method840(def, StaticMethods.anIntArray874[rotation] * 8, rotation, StaticMethods.anIntArray2441[rotation] * 8, 4, x, y, var5);
					}
				} else {
					if (first_node instanceof GameObject) {
						((GameObject) first_node).reset_shadows(-1);
					} else {
						SceneShadowMapper.method840(def, 0, rotation + 4, 0, type, x, y, var5);
					}
					if (second_node instanceof GameObject) {
						((GameObject) second_node).reset_shadows(-1);
					} else {
						SceneShadowMapper.method840(def, 0, 3 & rotation - -1, 0, type, x, y, var5);
					}
				}
			}
		}
	}

	static {
		aShortArray4283 = new short[] { 2, 4, 25, 44, 3, 22, 16, 41 };
		aClass16_4296 = RSString.createString("Lade Sprites )2 ");
	}
}
