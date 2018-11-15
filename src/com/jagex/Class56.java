package com.jagex;

import com.rs2.client.components.worldmap.WorldMap;

/* Class56 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class56 {
	static RSString aClass16_886 = RSString.createString("Verbindung abgebrochen)3");
	public static int chatMessageCount = 0;
	static RSString aClass16_892 = RSString.createString("Eingabeprozedur geladen)3");

	public static final int method1182(int i, boolean bool) {
		int i_0_ = i * (i * i >> 12) >> 12;
		if (bool != true) {
			aClass16_886 = null;
		}
		int i_1_ = -61440 + 6 * i;
		int i_2_ = 40960 - -(i_1_ * i >> 12);
		return i_2_ * i_0_ >> 12;
	}

	public static void method1183(byte b) {
		aClass16_886 = null;
		aClass16_892 = null;
	}

	static final void method1184(byte b, Entity entity) {
		if (entity.index != 0) {
			if (entity.faceIndex != -1 && entity.faceIndex < 32768) {
				NPC facedEntity = GameClient.activeNPCs[entity.faceIndex];
				if (facedEntity != null) {
					int offsetY = entity.bound_extents_x - facedEntity.bound_extents_x;
					int offsetX = entity.bound_extents_z - facedEntity.bound_extents_z;
					if (offsetY != 0 || offsetX != 0) {
						entity.faceDirection = (int) (Math.atan2(offsetY, offsetX) * 325.949) & 0x7ff;
					}
				}
			}
			if (entity.faceIndex >= 32768) {
				int index = entity.faceIndex - 32768;
				if (StaticMethods.thisPlayerIndex == index) {
					index = 2047;
				}
				if (index > 2047) {
					System.out.println("Face player index: " + index);
					index &= 2048;
				}
				Player player = GameClient.localPlayers[index];
				if (player != null) {
					int offsetY = entity.bound_extents_x - player.bound_extents_x;
					int offsetX = entity.bound_extents_z - player.bound_extents_z;
					if (offsetY != 0 || offsetX != 0) {
						entity.faceDirection = 0x7ff & (int) (325.949 * Math.atan2(offsetY, offsetX));
					}
				}
			}
			if ((entity.facingOffsetX != 0 || entity.facingOffsetY != 0) && (entity.anInt2660 == 0 || entity.anInt2632 > 0)) {
				int offsetY = 64 * entity.size - (64 - entity.bound_extents_x + 64 * (-MapLoader.region_aboslute_z + entity.facingOffsetX - MapLoader.region_aboslute_z));
				int offsetX = -((-MapLoader.region_aboslute_x + -MapLoader.region_aboslute_x + entity.facingOffsetY) * 64) + -64 + entity.size * 64 + entity.bound_extents_z;
				if (offsetY != 0 || offsetX != 0) {
					entity.faceDirection = (int) (Math.atan2(offsetY, offsetX) * 325.949) & 0x7ff;
				}
				entity.facingOffsetY = 0;
				entity.facingOffsetX = 0;
			}
			int i = 0x7ff & entity.faceDirection - entity.anInt2680;
			if (i != 0) {
				entity.anInt2644++;
				if (i <= 1024) {
					entity.anInt2680 += entity.index;
					boolean bool = true;
					if ((entity.index ^ 0xffffffff) < (i ^ 0xffffffff) || i > 2048 - entity.index) {
						bool = false;
						entity.anInt2680 = entity.faceDirection;
					}
					if (entity.standAnimation == entity.current_standing_animation && (entity.anInt2644 > 25 || bool)) {
						if (entity.alsoTurn != -1) {
							entity.current_standing_animation = entity.alsoTurn;
						} else {
							entity.current_standing_animation = entity.walkAnimation;
						}
					}
				} else {
					boolean bool = true;
					entity.anInt2680 -= entity.index;
					if ((i ^ 0xffffffff) > (entity.index ^ 0xffffffff) || (2048 + -entity.index ^ 0xffffffff) > (i ^ 0xffffffff)) {
						bool = false;
						entity.anInt2680 = entity.faceDirection;
					}
					if (entity.current_standing_animation == entity.standAnimation && (entity.anInt2644 > 25 || bool)) {
						if (entity.turnAnimation == -1) {
							entity.current_standing_animation = entity.walkAnimation;
						} else {
							entity.current_standing_animation = entity.turnAnimation;
						}
					}
				}
				entity.anInt2680 &= 0x7ff;
			} else {
				entity.anInt2644 = 0;
			}
		}
	}

	static final void method1185(int i, int i_7_, int i_8_, int i_9_, int i_10_, int i_11_) {
		int i_12_ = i_7_;
		int i_13_ = 0;
		int i_14_ = i_7_ * i_7_;
		int i_15_ = i_11_ * i_11_;
		int i_16_ = i_15_ << 1;
		int i_17_ = i_14_ << 1;
		int i_18_ = i_7_ << 1;
		int i_19_ = i_17_ + (-i_18_ + 1) * i_15_;
		int i_20_ = i_16_ * (-3 + (i_12_ << 1));
		if ((i ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff) && StaticMethods.anInt3435 >= i) {
			int i_21_ = StaticMethods.method405(i_10_ + 81, VarpDefinition.anInt3728, i_9_ - -i_11_, Class35.anInt554);
			int i_22_ = StaticMethods.method405(88, VarpDefinition.anInt3728, -i_11_ + i_9_, Class35.anInt554);
			VarpDefinition.method632(i_21_, (byte) -30, i_8_, Class4.anIntArrayArray98[i], i_22_);
		}
		int i_23_ = i_17_ * ((i_13_ << 1) + i_10_);
		int i_24_ = i_15_ << 2;
		int i_25_ = i_14_ << 2;
		int i_26_ = (-1 + i_12_) * i_24_;
		int i_27_ = i_25_ * (1 + i_13_);
		int i_28_ = i_14_ + -(i_16_ * (i_18_ + -1));
		while ((i_12_ ^ 0xffffffff) < -1) {
			i_12_--;
			if (i_19_ < 0) {
				while ((i_19_ ^ 0xffffffff) > -1) {
					i_19_ += i_23_;
					i_28_ += i_27_;
					i_13_++;
					i_23_ += i_25_;
					i_27_ += i_25_;
				}
			}
			int i_29_ = i - i_12_;
			if ((i_28_ ^ 0xffffffff) > -1) {
				i_19_ += i_23_;
				i_13_++;
				i_28_ += i_27_;
				i_27_ += i_25_;
				i_23_ += i_25_;
			}
			i_19_ += -i_26_;
			i_28_ += -i_20_;
			int i_30_ = i - -i_12_;
			i_20_ -= i_24_;
			if ((i_30_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff) && i_29_ <= StaticMethods.anInt3435) {
				int i_31_ = StaticMethods.method405(i_10_ ^ 0x39, VarpDefinition.anInt3728, i_9_ + i_13_, Class35.anInt554);
				int i_32_ = StaticMethods.method405(65, VarpDefinition.anInt3728, i_9_ - i_13_, Class35.anInt554);
				if ((i_29_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff)) {
					VarpDefinition.method632(i_31_, (byte) -30, i_8_, Class4.anIntArrayArray98[i_29_], i_32_);
				}
				if (i_30_ <= StaticMethods.anInt3435) {
					VarpDefinition.method632(i_31_, (byte) -30, i_8_, Class4.anIntArrayArray98[i_30_], i_32_);
				}
			}
			i_26_ -= i_24_;
		}
	}

	static final void method1186(int i, boolean bool, int i_33_, int i_34_, int i_35_, int i_36_, int i_37_, int i_38_) {
		int i_39_ = 0;
		int i_40_ = i_38_;
		int i_41_ = 0;
		int i_42_ = -i_34_ + i_35_;
		int i_43_ = i_38_ + -i_34_;
		int i_44_ = i_38_ * i_38_;
		int i_45_ = i_43_ * i_43_;
		int i_46_ = i_42_ * i_42_;
		int i_47_ = i_35_ * i_35_;
		int i_48_ = i_44_ << 1;
		if (bool != true) {
			aClass16_892 = null;
		}
		int i_49_ = i_47_ << 1;
		int i_50_ = i_45_ << 1;
		int i_51_ = i_38_ << 1;
		int i_52_ = i_48_ + (1 + -i_51_) * i_47_;
		int i_53_ = i_46_ << 1;
		int i_54_ = i_43_ << 1;
		int i_55_ = i_50_ + (-i_54_ + 1) * i_46_;
		int i_56_ = -((i_54_ + -1) * i_53_) + i_45_;
		int i_57_ = -((i_51_ - 1) * i_49_) + i_44_;
		int i_58_ = i_47_ << 2;
		int i_59_ = i_44_ << 2;
		int i_60_ = i_46_ << 2;
		int i_61_ = i_45_ << 2;
		int i_62_ = 3 * i_48_;
		int i_63_ = i_49_ * (-3 + i_51_);
		int i_64_ = i_50_ * 3;
		int i_65_ = (i_54_ - 3) * i_53_;
		int i_66_ = i_61_;
		int i_67_ = (i_38_ + -1) * i_58_;
		int i_68_ = i_59_;
		int i_69_ = i_60_ * (i_43_ - 1);
		int[] is = Class4.anIntArrayArray98[i_36_];
		VarpDefinition.method632(i_33_ + -i_42_, (byte) -30, i, is, -i_35_ + i_33_);
		VarpDefinition.method632(i_33_ + i_42_, (byte) -30, i_37_, is, -i_42_ + i_33_);
		VarpDefinition.method632(i_33_ + i_35_, (byte) -30, i, is, i_42_ + i_33_);
		while (i_40_ > 0) {
			if ((i_52_ ^ 0xffffffff) > -1) {
				while ((i_52_ ^ 0xffffffff) > -1) {
					i_39_++;
					i_52_ += i_62_;
					i_62_ += i_59_;
					i_57_ += i_68_;
					i_68_ += i_59_;
				}
			}
			boolean bool_70_ = i_40_ <= i_43_;
			if (bool_70_) {
				if (i_55_ < 0) {
					while (i_55_ < 0) {
						i_41_++;
						i_55_ += i_64_;
						i_56_ += i_66_;
						i_64_ += i_61_;
						i_66_ += i_61_;
					}
				}
				if (i_56_ < 0) {
					i_41_++;
					i_56_ += i_66_;
					i_55_ += i_64_;
					i_66_ += i_61_;
					i_64_ += i_61_;
				}
				i_56_ += -i_65_;
				i_65_ -= i_60_;
				i_55_ += -i_69_;
				i_69_ -= i_60_;
			}
			if ((i_57_ ^ 0xffffffff) > -1) {
				i_39_++;
				i_57_ += i_68_;
				i_52_ += i_62_;
				i_62_ += i_59_;
				i_68_ += i_59_;
			}
			int i_71_ = i_33_ - -i_39_;
			i_40_--;
			int i_72_ = i_33_ - i_39_;
			int i_73_ = -i_40_ + i_36_;
			int i_74_ = i_36_ + i_40_;
			if (bool_70_) {
				int i_75_ = -i_41_ + i_33_;
				int i_76_ = i_41_ + i_33_;
				VarpDefinition.method632(i_75_, (byte) -30, i, Class4.anIntArrayArray98[i_73_], i_72_);
				VarpDefinition.method632(i_76_, (byte) -30, i_37_, Class4.anIntArrayArray98[i_73_], i_75_);
				VarpDefinition.method632(i_71_, (byte) -30, i, Class4.anIntArrayArray98[i_73_], i_76_);
				VarpDefinition.method632(i_75_, (byte) -30, i, Class4.anIntArrayArray98[i_74_], i_72_);
				VarpDefinition.method632(i_76_, (byte) -30, i_37_, Class4.anIntArrayArray98[i_74_], i_75_);
				VarpDefinition.method632(i_71_, (byte) -30, i, Class4.anIntArrayArray98[i_74_], i_76_);
			} else {
				VarpDefinition.method632(i_71_, (byte) -30, i, Class4.anIntArrayArray98[i_73_], i_72_);
				VarpDefinition.method632(i_71_, (byte) -30, i, Class4.anIntArrayArray98[i_74_], i_72_);
			}
			i_57_ += -i_63_;
			i_63_ -= i_58_;
			i_52_ += -i_67_;
			i_67_ -= i_58_;
		}
	}

	public static final void method1187(boolean bool, int i, int i_77_, int i_78_, int i_79_) {
		if (bool == false) {
			if (i_77_ < i_78_) {
				VarpDefinition.method632(i_78_, (byte) -30, i, Class4.anIntArrayArray98[i_79_], i_77_);
			} else {
				VarpDefinition.method632(i_77_, (byte) -30, i, Class4.anIntArrayArray98[i_79_], i_78_);
			}
		}
	}

	static final void method1188(int i) {
		if (Queuable.systemUpdateTime > 1) {
			Queuable.systemUpdateTime--;
			Class88.anInt1499 = SomeSoundClass.anInt3589;
		}
		if ((StaticMethods.anInt3400 ^ 0xffffffff) < -1) {
			StaticMethods.anInt3400--;
		}
		if (StaticMethods.aBoolean3012) {
			StaticMethods.aBoolean3012 = false;
			Class44.method1128(-35);
		} else {
			for (int i_80_ = 0; i_80_ < 100; i_80_++) {
				if (!Class79.parsePackets(-51)) {
					break;
				}
			}
			if (GameClient.clientState == 30) {
				ReflectionAntiCheat.process(GameClient.outBuffer, 120, (byte) 35);
				synchronized (StaticMethods.aClass98_3513.anObject1653) {
					if (StaticMethods.aBoolean3380) {
						// Disabled this bitch until I know what it's used for.
						if (Mouse.mouseClickState != 0 || StaticMethods.aClass98_3513.anInt1659 >= 40) {
							// ISAACPacket buffer = Class23_Sub7.outBuffer;
							// Class23_Sub7.outBuffer = new ISAACPacket(5000); //TODO: Remove
							// Class23_Sub7.outBuffer.isaacCipher = buffer.isaacCipher; //^
							GameClient.outBuffer.putOpcode(43);
							GameClient.outBuffer.p1(0);
							int i_81_ = GameClient.outBuffer.index;
							int i_82_ = 0;
							for (int i_83_ = 0; (i_83_ ^ 0xffffffff) > (StaticMethods.aClass98_3513.anInt1659 ^ 0xffffffff); i_83_++) {
								if (-i_81_ + GameClient.outBuffer.index >= 240) {
									break;
								}
								i_82_++;
								int i_84_ = StaticMethods.aClass98_3513.anIntArray1661[i_83_];
								if (i_84_ < 0) {
									i_84_ = 0;
								} else if (i_84_ > GameShell.frame_height - 1) {
									i_84_ = GameShell.frame_height - 1;
								}
								int i_85_ = StaticMethods.aClass98_3513.anIntArray1658[i_83_];
								if ((i_85_ ^ 0xffffffff) > -1) {
									i_85_ = 0;
								} else if (i_85_ > GameShell.frame_width - 1) {
									i_85_ = GameShell.frame_width - 1;
								}
								int i_86_ = GameShell.frame_width * i_84_ + i_85_;
								if ((StaticMethods.aClass98_3513.anIntArray1661[i_83_] ^ 0xffffffff) == 0 && StaticMethods.aClass98_3513.anIntArray1658[i_83_] == -1) {
									i_86_ = 524287;
									i_85_ = -1;
									i_84_ = -1;
								}
								if ((HashTable.anInt1267 ^ 0xffffffff) == (i_85_ ^ 0xffffffff) && i_84_ == Class87_Sub4.anInt2843) {
									if (Varbit.anInt4002 < 2047) {
										Varbit.anInt4002++;
									}
								} else {
									int i_87_ = i_85_ + -HashTable.anInt1267;
									int i_88_ = i_84_ - Class87_Sub4.anInt2843;
									HashTable.anInt1267 = i_85_;
									Class87_Sub4.anInt2843 = i_84_;
									if (Varbit.anInt4002 < 8 && i_87_ >= -32 && i_87_ <= 31 && i_88_ >= -32 && i_88_ <= 31) {
										i_88_ += 32;
										i_87_ += 32;
										GameClient.outBuffer.putShort(i_88_ + (i_87_ << 6) + (Varbit.anInt4002 << 12));
										Varbit.anInt4002 = 0;
									} else if (Varbit.anInt4002 < 8) {
										GameClient.outBuffer.putTriByte(-483923896, i_86_ + (Varbit.anInt4002 << 19) + 8388608);
										Varbit.anInt4002 = 0;
									} else {
										GameClient.outBuffer.p4(-1073741824 - -(Varbit.anInt4002 << 19) + i_86_);
										Varbit.anInt4002 = 0;
									}
								}
							}
							GameClient.outBuffer.putIndex((byte) 21, GameClient.outBuffer.index - i_81_);
							// Class23_Sub7.outBuffer = buffer; //TODO: Remove
							if ((i_82_ ^ 0xffffffff) <= (StaticMethods.aClass98_3513.anInt1659 ^ 0xffffffff)) {
								StaticMethods.aClass98_3513.anInt1659 = 0;
							} else {
								StaticMethods.aClass98_3513.anInt1659 -= i_82_;
								for (int i_89_ = 0; (i_89_ ^ 0xffffffff) > (StaticMethods.aClass98_3513.anInt1659 ^ 0xffffffff); i_89_++) {
									StaticMethods.aClass98_3513.anIntArray1658[i_89_] = StaticMethods.aClass98_3513.anIntArray1658[i_82_ + i_89_];
									StaticMethods.aClass98_3513.anIntArray1661[i_89_] = StaticMethods.aClass98_3513.anIntArray1661[i_89_ - -i_82_];
								}
							}
						}
					} else {
						StaticMethods.aClass98_3513.anInt1659 = 0;
					}
				}
				if ((Mouse.mouseClickState ^ 0xffffffff) != -1) {
					long l = (Class23_Sub13_Sub12.aLong3975 - SoundEffects.aLong2058) / 50L;
					SoundEffects.aLong2058 = Class23_Sub13_Sub12.aLong3975;
					if ((l ^ 0xffffffffffffffffL) < -4096L) {
						l = 4095L;
					}
					int i_90_ = Mouse.mouseClickX;
					if (i_90_ < 0) {
						i_90_ = 0;
					} else if (i_90_ > GameShell.frame_width - 1) {
						i_90_ = GameShell.frame_width - 1;
					}
					int i_91_ = Mouse.mouseClickY;
					int i_92_ = 0;
					if (Mouse.mouseClickState == 2) {
						i_92_ = 1;
					}
					if (i_91_ < 0) {
						i_91_ = 0;
					} else if (i_91_ > GameShell.frame_height - 1) {
						i_91_ = GameShell.frame_height - 1;
					}
					int i_93_ = (int) l;
					GameClient.outBuffer.putOpcode(192);
					int i_94_ = i_91_ * GameShell.frame_width - -i_90_;
					// System.out.println("Mouse click data - " + i_92_ + ", " + i_93_ + ", " + i_94_ + " - [x=" + i_90_ + ", y=" + i_91_ +
					// "].");
					GameClient.outBuffer.ip4((i_92_ << 19) + (i_93_ << 20) - -i_94_);
				}
				if (StaticMethods2.keysPressed[96] || StaticMethods2.keysPressed[97] || StaticMethods2.keysPressed[98] || StaticMethods2.keysPressed[99]) {
					Class107.aBoolean1841 = true;
				}
				if (ProjectileNode.anInt3703 > 0) {
					ProjectileNode.anInt3703--;
				}
				if (Class107.aBoolean1841 && (ProjectileNode.anInt3703 ^ 0xffffffff) >= -1) {
					Class107.aBoolean1841 = false;
					ProjectileNode.anInt3703 = 20;
					GameClient.outBuffer.putOpcode(183);
					GameClient.outBuffer.putShort(Camera.cameraRotationZ);
					GameClient.outBuffer.putLEShortA(Class35.cameraDirection);
				}
				if (GameShell.windowActive == true && ProjectileNode.aBoolean3698 == false) {
					ProjectileNode.aBoolean3698 = true;
					GameClient.outBuffer.putOpcode(32);
					GameClient.outBuffer.p1(1);
				}
				if (!GameShell.windowActive == true && ProjectileNode.aBoolean3698 != false) {
					ProjectileNode.aBoolean3698 = false;
					GameClient.outBuffer.putOpcode(32);
					GameClient.outBuffer.p1(0);
				}
				StaticMethods.method269(i ^ 0x76a4b231);
				if (GameClient.clientState == 30) {
					SpawnedObject.method1375(30797);
					SongUpdater.method98(125);
					DataBuffer.anInt992++;
					if (DataBuffer.anInt992 > 750) {
						Class44.method1128(122);
					} else {
						PlayerAppearance.method1163((byte) -102);
						Class79.method1363(i + 1990505144);
						Class4.method61(0);
						if (WorldMap.worldmap_component != null) {
							WorldMap.method848(4);
						}
						InterfaceNode.anInt2459++;
						if (GameClient.cross_type != 0) {
							GameClient.cross_index += 20;
							if (GameClient.cross_index >= 400) {
								GameClient.cross_type = 0;
							}
						}
						if (GameClient.atInventoryInterface != null) {
							GameClient.atInventoryLoopCycle++;
							if (GameClient.atInventoryLoopCycle >= 15) {
								RSInterfaceList.setDirty(GameClient.atInventoryInterface);
								GameClient.atInventoryInterface = null;
							}
						}
						if (PlayerAppearance.aClass64_790 != null) {
							RSInterfaceList.setDirty(PlayerAppearance.aClass64_790);
							ColourImageCache.anInt1728++;
							if (5 + SpawnedObject.anInt2435 < Mouse.mouseX || Mouse.mouseX < SpawnedObject.anInt2435 + -5 || Mouse.mouseY > StaticMethods.anInt3510 - -5 || (StaticMethods.anInt3510 - 5 ^ 0xffffffff) < (Mouse.mouseY ^ 0xffffffff)) {
								CS2ScriptDefinition.aBoolean4262 = true;
							}
							if ((SongUpdater.anInt175 ^ 0xffffffff) == -1) {
								if (!CS2ScriptDefinition.aBoolean4262 || ColourImageCache.anInt1728 < 5) {
									if (Class95.anInt1612 != 1 && !Class87_Sub4.method1425((byte) 113, -1 + ContextMenu.menuActionRow) || ContextMenu.menuActionRow <= 2) {
										if (ContextMenu.menuActionRow > 0) {
											GameClient.doAction(ContextMenu.menuActionRow + -1);
										}
									} else {
										ContextMenu.determineMenuSize();
									}
								} else if (PlayerAppearance.aClass64_790 == CS2ScriptDefinition.aClass64_4257 && Class87_Sub3.firstSlot != Class49.secondSlot) {
									int insert = 0;
									RSInterface class64 = PlayerAppearance.aClass64_790;
									if (Class75.anInt1376 == 1 && class64.content_type == 206) {
										insert = 1;
									}
									if (class64.obj_ids[Class49.secondSlot] <= 0) {
										insert = 0;
									}
									if (Class47.getOptionMask(class64, 51).method93()) {
										int i_96_ = Class87_Sub3.firstSlot;
										int i_97_ = Class49.secondSlot;
										class64.obj_ids[i_97_] = class64.obj_ids[i_96_];
										class64.obj_counts[i_97_] = class64.obj_counts[i_96_];
										class64.obj_ids[i_96_] = -1;
										class64.obj_counts[i_96_] = 0;
									} else if (insert == 1) {
										int i_98_ = Class49.secondSlot;
										int i_99_ = Class87_Sub3.firstSlot;
										while ((i_99_ ^ 0xffffffff) != (i_98_ ^ 0xffffffff)) {
											if (i_98_ >= i_99_) {
												if ((i_99_ ^ 0xffffffff) > (i_98_ ^ 0xffffffff)) {
													class64.switchItem(108, i_99_ - -1, i_99_);
													i_99_++;
												}
											} else {
												class64.switchItem(i + 1990505087, -1 + i_99_, i_99_);
												i_99_--;
											}
										}
									} else {
										class64.switchItem(12, Class49.secondSlot, Class87_Sub3.firstSlot);
									}
									GameClient.outBuffer.putOpcode(178);
									GameClient.outBuffer.putShort(Class87_Sub3.firstSlot);
									GameClient.outBuffer.putIntA(PlayerAppearance.aClass64_790.uid);
									GameClient.outBuffer.p1(insert);
									GameClient.outBuffer.putLEShortA(Class49.secondSlot);
								}
								Mouse.mouseClickState = 0;
								PlayerAppearance.aClass64_790 = null;
								GameClient.atInventoryLoopCycle = 10;
							}
						}
						for (InterfaceUpdateQueue var_iu = InterfaceUpdateQueue.a(-87); var_iu != null; var_iu = InterfaceUpdateQueue.a(-35)) {
							int i_18_ = var_iu.f(i ^ 0x542e);
							long l = var_iu.a();
							if (i_18_ == 1) {
								CS2Runtime.int_global_vars[(int) l] = var_iu.z;
								PacketParser.anIntArray5213[MathUtils.bitAnd(PacketParser.anInt2838++, 31)] = (int) l;
							} else if (i_18_ == 2) {
								StaticMethods.str_global_vars[(int) l] = var_iu.x;
								PacketParser.c[MathUtils.bitAnd(PacketParser.b++, 31)] = (int) l;
							} else if (i_18_ == 3) {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								if (!var_iu.x.equals(var_rtInterface.default_text)) {
									var_rtInterface.default_text = var_iu.x;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ == 4) {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								int i_19_ = var_iu.z;
								int i_20_ = var_iu.t;
								int i_21_ = var_iu.anInt3596;
								if (i_19_ != var_rtInterface.media_type || (i_20_ ^ 0xffffffff) != (var_rtInterface.media_id ^ 0xffffffff)/* || var_rtInterface.V != i_21_ */) {
									var_rtInterface.media_type = i_19_;
									// var_rtInterface.V = i_21_;
									var_rtInterface.media_id = i_20_;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ == 5) {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								if (var_iu.z != var_rtInterface.media_animid) {
									if ((var_iu.z ^ 0xffffffff) != 0) {
										// if (var_rtInterface.Qc == null) {
										// var_rtInterface.Qc = new kb();
										// }
										// var_rtInterface.Qc.a((byte) 115, var_iu.z);
									} else {
										// var_rtInterface.Qc = null;
									}
									var_rtInterface.media_animid = var_iu.z;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ == 6) {
								int i_22_ = var_iu.z;
								int i_23_ = 0x1f & i_22_ >> 10;
								int i_24_ = (i_22_ & 0x3f4) >> 5;
								int i_25_ = 0x1f & i_22_;
								int i_26_ = (i_25_ << 3) + (i_23_ << 19) + (i_24_ << 11);
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								if (i_26_ != var_rtInterface.color) {
									var_rtInterface.color = i_26_;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ == 7) {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								boolean bool = var_iu.z == 1;
								if (bool != var_rtInterface.hidden) {
									var_rtInterface.hidden = bool;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ == 8) {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								if (var_rtInterface.media_xangle != var_iu.z || (var_rtInterface.media_yangle ^ 0xffffffff) != (var_iu.t ^ 0xffffffff) || var_iu.anInt3596 != var_rtInterface.media_zoom) {
									var_rtInterface.media_yangle = var_iu.t;
									var_rtInterface.media_xangle = var_iu.z;
									var_rtInterface.media_zoom = var_iu.anInt3596;
									if ((var_rtInterface.objid ^ 0xffffffff) != 0) {
										if (var_rtInterface.media_viewport_width > 0) {
											var_rtInterface.media_zoom = 32 * var_rtInterface.media_zoom / var_rtInterface.media_viewport_width;
										} else if (var_rtInterface.width > 0) {
											var_rtInterface.media_zoom = 32 * var_rtInterface.media_zoom / var_rtInterface.width;
										}
									}
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ == 9) {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								if (var_rtInterface.objid != var_iu.z || (var_iu.t ^ 0xffffffff) != (var_rtInterface.shownItemAmount ^ 0xffffffff)) {
									var_rtInterface.shownItemAmount = var_iu.t;
									var_rtInterface.objid = var_iu.z;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else if (i_18_ != 10) {
								if (i_18_ != 11) {
									if (i_18_ != 12) {
										if (i_18_ == 14) {
											RSInterface var_rtInterface = RSInterface.getInterface((int) l);
											var_rtInterface.graphicid = var_iu.z;
										} else if (i_18_ == 15) {
											// kaa.f_l = var_iu.z;
											// vl.sound_adjusts = true;
											// lka.u = var_iu.t;
										} else if (i_18_ != 16) {
											if (i_18_ == 20) {
												RSInterface var_rtInterface = RSInterface.getInterface((int) l);
												// var_rtInterface.font_monochrome = (var_iu.z == 1);
											} else if (i_18_ != 21) {
												if (i_18_ != 17) {
													if (i_18_ == 18) {
														RSInterface var_rtInterface = RSInterface.getInterface((int) l);
														int i_27_ = (int) (l >> 32);
														// var_rtInterface.set_recolour(i_27_, (short) var_iu.z, (short) var_iu.t);
													} else if (i_18_ == 19) {
														RSInterface var_rtInterface = RSInterface.getInterface((int) l);
														int i_28_ = (int) (l >> 32);
														// var_rtInterface.set_retexture(i_28_, (short) var_iu.z, (short) var_iu.t);
													}
												} else {
													RSInterface var_rtInterface = RSInterface.getInterface((int) l);
													// var_rtInterface.video_id = (var_iu.z);
												}
											} else {
												RSInterface var_rtInterface = RSInterface.getInterface((int) l);
												// var_rtInterface.f_gb = (var_iu.z == 1);
											}
										} else {
											RSInterface var_rtInterface = RSInterface.getInterface((int) l);
											var_rtInterface.font_id = var_iu.z;
										}
									} else {
										RSInterface var_rtInterface = RSInterface.getInterface((int) l);
										int i_29_ = var_iu.z;
										if (var_rtInterface != null && var_rtInterface.type == 0) {
											if (var_rtInterface.scroll_height + -var_rtInterface.layout_height < i_29_) {
												i_29_ = -var_rtInterface.layout_height + var_rtInterface.scroll_height;
											}
											if (i_29_ < 0) {
												i_29_ = 0;
											}
											if (var_rtInterface.scroll_y != i_29_) {
												var_rtInterface.scroll_y = i_29_;
												RSInterfaceList.setDirty(var_rtInterface);
											}
										}
									}
								} else {
									RSInterface var_rtInterface = RSInterface.getInterface((int) l);
									var_rtInterface.layout_y = var_rtInterface.positionY = var_iu.t;
									var_rtInterface.layout_x = var_rtInterface.positionX = var_iu.z;
									var_rtInterface.v_pos_mode = (byte) 0;
									var_rtInterface.h_pos_mode = (byte) 0;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							} else {
								RSInterface var_rtInterface = RSInterface.getInterface((int) l);
								if ((var_iu.z ^ 0xffffffff) != (var_rtInterface.anInt258 ^ 0xffffffff) || (var_rtInterface.anInt264 ^ 0xffffffff) != (var_iu.t ^ 0xffffffff) || (var_rtInterface.media_zangle ^ 0xffffffff) != (var_iu.anInt3596 ^ 0xffffffff)) {
									var_rtInterface.media_zangle = var_iu.anInt3596;
									var_rtInterface.anInt258 = var_iu.z;
									var_rtInterface.anInt264 = var_iu.t;
									RSInterfaceList.setDirty(var_rtInterface);
								}
							}
						}
						Entity.anInt2649 = 0;
						StaticMethods.aBoolean3516 = false;
						if (i != -1990505050) {
							method1185(-112, 7, -103, -128, -62, 62);
						}
						StaticMethods.withInterface = null;
						CollisionMap.aBoolean1310 = false;
						RSInterface class64 = Class42.aClass64_663;
						Class42.aClass64_663 = null;
						RSInterface class64_100_ = VertexNormal.aClass64_1567;
						VertexNormal.aClass64_1567 = null;
						for (/**/; CacheFileWorker.method1572(i ^ ~0x76a4d3ef) && Entity.anInt2649 < 128; Entity.anInt2649++) {
							CS2Event.anIntArray2265[Entity.anInt2649] = Class88.keyPressedID;
							AbstractTimer.anIntArray308[Entity.anInt2649] = Class53.anInt833;
						}
						WorldMap.worldmap_component = null;
						if ((GameClient.interface_top_id ^ 0xffffffff) != 0) {
							StaticMethods.method299(GameShell.window_width, GameClient.interface_top_id, 0, i ^ ~0x76a4b23d, 0, 0, 0, GameShell.window_height);
						}
						SomeSoundClass.anInt3589++;
						for (;;) {
							CS2Event class23_sub9 = (CS2Event) HintIcon.aClass89_206.remove_first();
							if (class23_sub9 == null) {
								break;
							}
							RSInterface class64_101_ = class23_sub9.component;
							if ((class64_101_.componentIndex ^ 0xffffffff) <= -1) {
								RSInterface class64_102_ = RSInterface.getInterface(class64_101_.parentId);
								if (class64_102_ == null || class64_102_.dynamic_components == null || class64_101_.componentIndex >= class64_102_.dynamic_components.length || class64_101_ != class64_102_.dynamic_components[class64_101_.componentIndex]) {
									continue;
								}
							}
							Class91.parseCS2Script(class23_sub9, (byte) -118);
						}
						for (;;) {
							CS2Event class23_sub9 = (CS2Event) GameTimer.aClass89_1999.remove_first();
							if (class23_sub9 == null) {
								break;
							}
							RSInterface class64_103_ = class23_sub9.component;
							if ((class64_103_.componentIndex ^ 0xffffffff) <= -1) {
								RSInterface class64_104_ = RSInterface.getInterface(class64_103_.parentId);
								if (class64_104_ == null || class64_104_.dynamic_components == null || (class64_103_.componentIndex ^ 0xffffffff) <= (class64_104_.dynamic_components.length ^ 0xffffffff) || class64_104_.dynamic_components[class64_103_.componentIndex] != class64_103_) {
									continue;
								}
							}
							Class91.parseCS2Script(class23_sub9, (byte) -126);
						}
						for (;;) {
							CS2Event class23_sub9 = (CS2Event) Class71_Sub2_Sub1.aClass89_4470.remove_first();
							if (class23_sub9 == null) {
								break;
							}
							RSInterface class64_105_ = class23_sub9.component;
							if ((class64_105_.componentIndex ^ 0xffffffff) <= -1) {
								RSInterface class64_106_ = RSInterface.getInterface(class64_105_.parentId);
								if (class64_106_ == null || class64_106_.dynamic_components == null || class64_106_.dynamic_components.length <= class64_105_.componentIndex || class64_106_.dynamic_components[class64_105_.componentIndex] != class64_105_) {
									continue;
								}
							}
							Class91.parseCS2Script(class23_sub9, (byte) -101);
						}
						if (WorldMap.worldmap_component == null) {
							WorldMap.anInt2475 = 0;
						}
						if (StaticMethods.fromInterface != null) {
							RSString.method139((byte) 29);
						}
						if ((SceneController.picked_tile_x ^ 0xffffffff) != 0) {
							int i_107_ = SceneController.picked_tile_x;
							int i_108_ = SceneController.picked_tile_y;
							boolean bool = GameClient.walkPath(0, i_108_, 0, i_107_, true, false, 0, 0, GameClient.currentPlayer.waypointsX[0], 0, GameClient.currentPlayer.waypointsY[0], 0);
							if (bool) {
								GameClient.cross_type = 1;
								GameShell.crossY = Mouse.mouseClickY;
								GameClient.cross_index = 0;
								GameClient.crossX = Mouse.mouseClickX;
							}
							SceneController.picked_tile_x = -1;
						}
						ContextMenu.processMenuClick();
						if (class64 != Class42.aClass64_663) {
							if (class64 != null) {
								RSInterfaceList.setDirty(class64);
							}
							if (Class42.aClass64_663 != null) {
								RSInterfaceList.setDirty(Class42.aClass64_663);
							}
						}
						if (VertexNormal.aClass64_1567 != class64_100_ && (StaticMethods.anInt3240 ^ 0xffffffff) == (Js5.anInt1811 ^ 0xffffffff)) {
							if (class64_100_ != null) {
								RSInterfaceList.setDirty(class64_100_);
							}
							if (VertexNormal.aClass64_1567 != null) {
								RSInterfaceList.setDirty(VertexNormal.aClass64_1567);
							}
						}
						if (VertexNormal.aClass64_1567 != null) {
							if (Js5.anInt1811 < StaticMethods.anInt3240) {
								Js5.anInt1811++;
								if (StaticMethods.anInt3240 == Js5.anInt1811) {
									RSInterfaceList.setDirty(VertexNormal.aClass64_1567);
								}
							}
						} else if (Js5.anInt1811 > 0) {
							Js5.anInt1811--;
						}
						Camera.method311(true);
						if (Camera.cameraViewChanged) {
							ObjType.method742((byte) 111);
						}
						for (int i_109_ = 0; i_109_ < 5; i_109_++) {
							StaticMethods.anIntArray3128[i_109_]++;
						}
						int i_110_ = RSString.getIdleMouseTicks();
						int i_111_ = Queue.getIdleKeyTicks(true);
						int time = GameShell.IDLE_LOGOUT_TIME;
						if (i_110_ > time && i_111_ > time) {
							StaticMethods.anInt3400 = 250;

							LocResult.method625(i + 923841154, 4000);
							GameClient.outBuffer.putOpcode(35);
						}
						Class100.anInt1686++;
						SongUpdater.anInt180++;
						GameTimer.anInt1988++;
						if (SongUpdater.anInt180 > 500) {
							SongUpdater.anInt180 = 0;
							int i_112_ = (int) (8.0 * Math.random());
							if ((0x1 & i_112_) == 1) {
								Class87_Sub4.anInt2841 += StaticMethods.anInt3291;
							}
							if ((i_112_ & 0x2) == 2) {
								Huffman.anInt1815 += Class4.anInt96;
							}
							if ((i_112_ & 0x4) == 4) {
								StaticMethods.anInt2923 += Class73.anInt1325;
							}
						}
						if (GameTimer.anInt1988 > 500) {
							GameTimer.anInt1988 = 0;
							int i_113_ = (int) (Math.random() * 8.0);
							if ((0x2 & i_113_) == 2) {
								Player.anInt4410 += Class75.anInt1382;
							}
							if ((0x1 & i_113_) == 1) {
								StaticMethods.anInt3162 += VertexNormal.anInt1563;
							}
						}
						if (Huffman.anInt1815 < -55) {
							Class4.anInt96 = 2;
						}
						if (Huffman.anInt1815 > 55) {
							Class4.anInt96 = -2;
						}
						if (Class87_Sub4.anInt2841 < -50) {
							StaticMethods.anInt3291 = 2;
						}
						if (Class87_Sub4.anInt2841 > 50) {
							StaticMethods.anInt3291 = -2;
						}
						if (StaticMethods.anInt3162 < -60) {
							VertexNormal.anInt1563 = 2;
						}
						if (StaticMethods.anInt2923 < -40) {
							Class73.anInt1325 = 1;
						}
						if (Player.anInt4410 < -20) {
							Class75.anInt1382 = 1;
						}
						if (StaticMethods.anInt3162 > 60) {
							VertexNormal.anInt1563 = -2;
						}
						if (Player.anInt4410 > 10) {
							Class75.anInt1382 = -1;
						}
						if (StaticMethods.anInt2923 > 40) {
							Class73.anInt1325 = -1;
						}
						if (Class100.anInt1686 > 50) {
							GameClient.outBuffer.putOpcode(56);
						}
						do {
							try {
								if (ColourImageCacheSlot.session == null || (GameClient.outBuffer.index ^ 0xffffffff) >= -1) {
									break;
								}
								ColourImageCacheSlot.session.write(GameClient.outBuffer.index, 0, GameClient.outBuffer.byteBuffer);
								Class100.anInt1686 = 0;
								GameClient.outBuffer.index = 0;
							} catch (java.io.IOException ioexception) {
								Class44.method1128(-11);
								break;
							}
							break;
						} while (false);
					}
				}
			}
		}
	}

}
