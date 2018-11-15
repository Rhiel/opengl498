package com.jagex;
/* Class23_Sub13_Sub11 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.util.Date;

import com.jagex.core.collections.memory.AdvancedMemoryCache;
import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class ObjType extends Queuable {
	static short[] itemColors = new short[256];
	static AdvancedMemoryCache itemSprites = new AdvancedMemoryCache(100);
	static boolean isMember;
	static ModelList itemModelCache;
	static Js5 itemModelContainer;
	public static int itemDefinitionSize;
	public int modelRotation1 = 0;
	public int secondaryMaleDialogue;
	public int lendID = -1;
	public int stackable = 0;
	public int modelZoom;
	public int modelRotationZ;
	public int[] stackIDs;
	public int modelOffset2;
	public boolean unnoted;
	public RSString name = RSString.createString("null");
	public RSString description = RSString.createString("null");
	public short[] retex_src;
	public boolean membersItem;
	public int ambient;
	public RSString[] actions;
	static RSString aClass16_3914;
	public int lendTemplateID;
	public static int localHeight;
	public int femaleWieldY;
	public int scale_x;
	public int tertiaryMaleEquipmentModel;
	public byte[] recolorIndices;
	public int modelOffset1;
	public int tertiaryFemaleEquipmentModel;
	public short[] retex_dst;
	public int secondaryMaleModel;
	public int itemId;
	public int maleWieldY;
	public int secondaryFemaleDialogue;
	public int maleWearId;
	public int secondaryFemaleModel;
	public int interfaceModelId;
	public int[] stackAmounts;
	public short[] modifiedColors;
	public int femaleWearId;
	public int scale_y;
	public short[] originalColors;
	public int team;
	static SignlinkRequest aRequest_3944;
	public RSString[] groundActions;
	public int maleDialogue;
	public int modelRotation2;
	public int femaleDialogue;
	public static RSString aClass16_3951 = RSString.createString("Loading sprites )2 ");
	public int value;
	public int notedTemplateId;
	public int anInt3956;
	public int contrast;
	static RSString aClass16_3958;
	public int scale_z;
	public int noteInfoId;
	public static RSString membersString = RSString.createString("Members object");

	public HashTable clientScriptData;

	public int manwearxoff;
	public int manwearyoff;
	public int manwearzoff;
	public int womanwearxoff;
	public int womanwearyoff;
	public int womanwearzoff;

	static final int getFile(int i_0_) {
		return i_0_ & 0xff;
	}

	static final int getArchive(int i) {
		return i >>> 8;
	}

	static final void clearCachedItems() {
		ObjTypeList.cache.clear();
		itemModelCache.clearModelCache((byte) -125);
		itemSprites.clear();
	}

	static final void method726(int i) {
		if (i != -20911) {
			method726(-19);
		}
		if (InputManager.keyboard != null) {
			synchronized (InputManager.keyboard) {
				InputManager.keyboard = null;
			}
		}
	}

	static final void setItemCache(boolean members, Js5 modelWorker, Js5 itemWorker) {
		itemModelContainer = modelWorker;
		isMember = members;
		ObjTypeList.objs_js5 = itemWorker;
		int lastArchive = ObjTypeList.objs_js5.get_group_count() - 1;
		itemDefinitionSize = 256 * lastArchive + ObjTypeList.objs_js5.get_file_count(lastArchive);
	}

	static final Sprite get_obj_icon(int i_3_, int stack_mode, int obj_id, int obj_co, int i_1_) {
		long l = ((long) i_3_ << 38) + obj_id + ((long) obj_co << 16) + (stack_mode == 1 || stack_mode == 2 ? 137438953472L : 0L) + ((long) i_1_ << 40);
		Sprite sprite = (Sprite) itemSprites.get(l);
		if (sprite != null) {
			return sprite;
		}
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		rasterizer.invalid_texture = false;
		sprite = createItemSprite(stack_mode, i_3_, obj_id, false, i_1_, false, obj_co, false, false);
		if (sprite != null && !rasterizer.invalid_texture) {
			itemSprites.put(l, sprite);
		}
		return sprite;
	}

	static final Sprite createItemSprite(int stackMode, int i, int itemId, boolean bool_34_, int i_35_, boolean renderSpecificForSprite, int itemAmount, boolean bool_38_, boolean forceSoftware) {
		ObjType def = ObjTypeList.list(itemId);
		if (itemAmount > 1 && def.stackIDs != null) {
			int id = -1;
			for (int i_40_ = 0; i_40_ < 10; i_40_++) {
				if ((def.stackAmounts[i_40_] ^ 0xffffffff) >= (itemAmount ^ 0xffffffff) && def.stackAmounts[i_40_] != 0) {
					id = def.stackIDs[i_40_];
				}
			}
			if ((id ^ 0xffffffff) != 0) {
				def = ObjTypeList.list(id);
			}
		}
		SoftwareModel model = def.getInterfaceModel();
		if (model == null) {
			return null;
		}
		Sprite spr = null;
		if ((def.notedTemplateId ^ 0xffffffff) == 0) {
			if (def.lendTemplateID != -1) {
				spr = createItemSprite(0, i, def.lendID, false, i_35_, false, itemAmount, true, true);
				if (spr == null) {
					return null;
				}
			}
		} else {
			spr = createItemSprite(0, 1, def.noteInfoId, true, 0, false, 10, true, true);
			if (spr == null) {
				return null;
			}
		}
		int[] is = Rasterizer2D.colour_buffer;
		int i_41_ = Rasterizer2D.height;
		int i_42_ = Rasterizer2D.width;
		int[] is_43_ = new int[4];
		Rasterizer2D.method217(is_43_);
		SoftwareSprite sprite = new SoftwareSprite(36, 32);
		Rasterizer2D.initialise(sprite.pixels, 36, 32);
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		rasterizer.clip_reset();
		rasterizer.center_adjust(16, 16);
		int zoom = def.modelZoom;
		if (!bool_34_) {
			if (i == 2) {
				zoom = (int) (1.04 * zoom);
			}
		} else {
			zoom = (int) (1.5 * zoom);
		}
		rasterizer.smooth_edges = renderSpecificForSprite;
		int i_45_ = Rasterizer.SINE[def.modelRotation1] * zoom >> 16;
		int i_46_ = zoom * Rasterizer.COSINE[def.modelRotation1] >> 16;
		model.draw(0, def.modelRotation2, def.modelRotationZ, def.modelRotation1, def.modelOffset1, -(model.get_miny() / 2) + i_45_ + def.modelOffset2, i_46_ + def.modelOffset2);
		if (i >= 1) {
			sprite.add_outline_colour(1);
		}
		if (i >= 2) {
			sprite.add_outline_colour(16777215);
		}
		if (i_35_ != 0) {
			sprite.add_shadow(i_35_);
		}
		if (def.lendTemplateID != -1) {
			SoftwareSprite s = new SoftwareSprite(36, 32);
			Rasterizer2D.initialise(s.pixels, 36, 32);
			spr.draw_clipped_left_anchor(0, 0);
			sprite.draw_clipped_left_anchor(0, 0);
			sprite = s;
		}
		Rasterizer2D.initialise(sprite.pixels, 36, 32);
		if ((def.notedTemplateId ^ 0xffffffff) != 0) {
			spr.draw_clipped_left_anchor(0, 0);
		}
		if (stackMode == 1 || stackMode == 2 && (def.stackable == 1 || itemAmount != 1) && itemAmount != -1) {
			FontCache.objnum_font.draw_text(formatTextForAmount(itemAmount), 0, 9, 16776960, 1);
		}
		Rasterizer2D.initialise(is, i_42_, i_41_);
		Rasterizer2D.method218(is_43_);
		rasterizer.clip_reset();
		rasterizer.smooth_edges = true;
		if (GLManager.opengl_mode && !forceSoftware) {
			return new OpenGLSprite(sprite);
		} else {
			return sprite;
		}
	}

	static final void clearItemModelCache() {
		itemModelCache.clearModelCache((byte) -29);
	}

	static final RSString formatTextForAmount(int i) {
		if (i < 100000) {
			return RSString.joinRsStrings(new RSString[] { SomeSoundClass.aClass16_3600, RSString.valueOf(i), Player.aClass16_4406 });
		}
		if (i < 10000000) {
			return RSString.joinRsStrings(new RSString[] { ForceMovement.aClass16_402, RSString.valueOf(i / 1000), Class42.aClass16_652, Player.aClass16_4406 });
		}
		return RSString.joinRsStrings(new RSString[] { StaticMethods2.aClass16_1738, RSString.valueOf(i / 1000000), ReflectionAntiCheat.aClass16_76, Player.aClass16_4406 });
	}

	static final void method704(boolean bool, RSString string, byte b) {
		string = string.toLowerCase();
		short[] itemIds = new short[16];
		int i = 0;
		for (int itemId = 0; itemId < itemDefinitionSize; itemId++) {
			ObjType def = ObjTypeList.list(itemId);
			if ((!bool || def.unnoted) && def.notedTemplateId == -1 && def.lendTemplateID == -1 && def.anInt3956 == 0 && def.name.toLowerCase().indexOf(string) != -1) {
				if (i >= 250) {
					StaticMethods2.result_id_buffer = null;
					StaticMethods.result_buffer_size = -1;
					return;
				}
				if (itemIds.length <= i) {
					short[] resized = new short[itemIds.length * 2];
					for (int index = 0; index < i; index++) {
						resized[index] = itemIds[index];
					}
					itemIds = resized;
				}
				itemIds[i++] = (short) itemId;
			}
		}
		NameHashTable.result_buffer_ptr = 0;
		StaticMethods.result_buffer_size = i;
		RSString[] names = new RSString[StaticMethods.result_buffer_size];
		StaticMethods2.result_id_buffer = itemIds;
		for (int index = 0; index < StaticMethods.result_buffer_size; index++) {
			names[index] = ObjTypeList.list(itemIds[index]).name;
		}
		if (b <= 108) {
			StaticMethods2.method693(-115);
		}
		StaticMethods.method330(StaticMethods2.result_id_buffer, (byte) -9, names);
	}

	final void noteItem(int i, ObjType appearanceItem, ObjType attributesItem) {
		modelRotation2 = appearanceItem.modelRotation2;
		modelOffset1 = appearanceItem.modelOffset1;
		modelZoom = appearanceItem.modelZoom;
		retex_dst = appearanceItem.retex_dst;
		interfaceModelId = appearanceItem.interfaceModelId;
		value = attributesItem.value;
		originalColors = appearanceItem.originalColors;
		stackable = 1;
		modelRotationZ = appearanceItem.modelRotationZ;
		name = attributesItem.name;
		retex_src = appearanceItem.retex_src;
		membersItem = attributesItem.membersItem;
		modelRotation1 = appearanceItem.modelRotation1;
		modelOffset2 = appearanceItem.modelOffset2;
		modifiedColors = appearanceItem.modifiedColors;
		recolorIndices = appearanceItem.recolorIndices;
		manwearxoff = appearanceItem.manwearxoff;
		manwearyoff = appearanceItem.manwearyoff;
		manwearzoff = appearanceItem.manwearzoff;
		womanwearxoff = appearanceItem.womanwearxoff;
		womanwearyoff = appearanceItem.womanwearyoff;
		womanwearzoff = appearanceItem.womanwearzoff;
	}

	final Mesh getDialogueModels(int i, boolean isFemale) {
		int model_a = maleDialogue;
		int model_b = secondaryMaleDialogue;
		if (isFemale) {
			model_b = secondaryFemaleDialogue;
			model_a = femaleDialogue;
		}
		if ((model_a ^ 0xffffffff) == 0) {
			return null;
		}
		Mesh model = Mesh.fromJs5(itemModelContainer, model_a, 0);
		if ((model_b ^ 0xffffffff) != 0) {
			Mesh secondary = Mesh.fromJs5(itemModelContainer, model_b, 0);
			Mesh[] models = { model, secondary };
			model = new Mesh(models, 2);
		}
		if (originalColors != null) {
			for (int i_6_ = 0; (i_6_ ^ 0xffffffff) > (originalColors.length ^ 0xffffffff); i_6_++) {
				model.recolor(originalColors[i_6_], modifiedColors[i_6_]);
			}
		}
		if (retex_src != null) {
			for (int i_7_ = 0; (i_7_ ^ 0xffffffff) > (retex_src.length ^ 0xffffffff); i_7_++) {
				model.retexture(retex_src[i_7_], retex_dst[i_7_]);
			}
		}
		return model;
	}

	static final RSString method729(byte b, long l) {
		Class79.aCalendar1881.setTime(new Date(l));
		int i = Class79.aCalendar1881.get(7);
		if (b <= 4) {
			return null;
		}
		int i_8_ = Class79.aCalendar1881.get(5);
		int i_9_ = Class79.aCalendar1881.get(2);
		int i_10_ = Class79.aCalendar1881.get(1);
		int i_11_ = Class79.aCalendar1881.get(11);
		int i_12_ = Class79.aCalendar1881.get(12);
		int i_13_ = Class79.aCalendar1881.get(13);
		return RSString.joinRsStrings(new RSString[] { Class67.aClass16Array1185[i + -1], CullingCluster.aClass16_940, RSString.valueOf(i_8_ / 10), RSString.valueOf(i_8_ % 10), StringConstants.aClass16_1976, CS2ScriptDefinition.aClass16Array4252[i_9_], StringConstants.aClass16_1976, RSString.valueOf(i_10_), StaticMethods.aClass16_3345, RSString.valueOf(i_11_ / 10), RSString.valueOf(i_11_ % 10), StaticMethods2.requestSeperator, RSString.valueOf(i_12_ / 10), RSString.valueOf(i_12_ % 10), StaticMethods2.requestSeperator, RSString.valueOf(i_13_ / 10), RSString.valueOf(i_13_ % 10), AbstractMouseWheel.aClass16_1163 });
	}

	final boolean areEquipModelsCached(boolean bool) {
		int secondaryModel = secondaryMaleModel;
		int primaryModel = maleWearId;
		int thirdModel = tertiaryMaleEquipmentModel;
		if (bool) {
			secondaryModel = secondaryFemaleModel;
			thirdModel = tertiaryFemaleEquipmentModel;
			primaryModel = femaleWearId;
		}
		if (primaryModel == -1) {
			return true;
		}
		boolean isCached = true;
		if (!itemModelContainer.is_file_cached(primaryModel, 0)) {
			isCached = false;
		}
		if (secondaryModel != -1 && !itemModelContainer.is_file_cached(secondaryModel, 0)) {
			isCached = false;
		}
		if (thirdModel != -1 && !itemModelContainer.is_file_cached(thirdModel, 0)) {
			isCached = false;
		}
		return isCached;
	}

	static final InteractiveEntity method731(int i, int i_18_, int i_19_) {
		Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[i][i_18_][i_19_];
		if (class23_sub1 != null) {
			for (int i_20_ = 0; i_20_ < class23_sub1.num_interactives; i_20_++) {
				InteractiveEntity entity = class23_sub1.interactives[i_20_];
				if ((entity.uid >> 29 & 0x3L) == 2L && entity.anInt601 == i_18_ && entity.anInt607 == i_19_) {
					CS2ScriptDefinition.method844(entity);
					return entity;
				}
			}
		}
		return null;
	}

	final Mesh getWornModel(boolean female) {
		int primaryModel = maleWearId;
		int secondaryModel = secondaryMaleModel;
		int extraModel = tertiaryMaleEquipmentModel;
		if (female) {
			primaryModel = femaleWearId;
			secondaryModel = secondaryFemaleModel;
			extraModel = tertiaryFemaleEquipmentModel;
		}
		if (primaryModel == -1) {
			return null;
		}
		Mesh def = Mesh.fromJs5(itemModelContainer, primaryModel, 0);
		if (secondaryModel != -1) {
			Mesh secondModel = Mesh.fromJs5(itemModelContainer, secondaryModel, 0);
			if (extraModel != -1) {
				Mesh thirdModel = Mesh.fromJs5(itemModelContainer, extraModel, 0);
				Mesh[] combined = { def, secondModel, thirdModel };
				def = new Mesh(combined, 3);
			} else {
				Mesh[] combined = { def, secondModel };
				def = new Mesh(combined, 2);
			}
		}
		if (!female && (manwearxoff != 0 || manwearyoff != 0 || manwearzoff != 0)) {
			def.translate(manwearxoff, manwearyoff, manwearzoff);
		}
		if (female && (womanwearxoff != 0 || womanwearyoff != 0 || womanwearzoff != 0)) {
			def.translate(womanwearxoff, womanwearyoff, womanwearzoff);
		}
		if (originalColors != null) {
			for (int i_26_ = 0; originalColors.length > i_26_; i_26_++) {
				def.recolor(originalColors[i_26_], modifiedColors[i_26_]);
			}
		}
		if (retex_src != null) {
			for (int i_27_ = 0; i_27_ < retex_src.length; i_27_++) {
				def.retexture(retex_src[i_27_], retex_dst[i_27_]);
			}
		}
		return def;
	}

	final void parse(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			// System.out.println(opcode);
			parseOpcode((byte) 111, opcode, buffer);
		}
	}

	final ObjType getStackDefinition(int i, int amount) {
		if (stackIDs != null && amount > 1) {
			int itemId = -1;
			for (int index = 0; index < 10; index++) {
				if (stackAmounts[index] <= amount && stackAmounts[index] != 0) {
					itemId = stackIDs[index];
				}
			}
			if (itemId != -1) {
				ObjTypeList.list(itemId);
			}
		}
		return this;
	}

	public final void parseOpcode(byte b, int opcode, Packet buffer) {
		if (opcode == 1) {
			interfaceModelId = buffer.g2();
		} else if (opcode != 2) {
			if (opcode == 3) {
				description = buffer.gstr();
			} else if (opcode == 4) {
				modelZoom = buffer.g2();
			} else if (opcode != 5) {
				if (opcode != 6) {
					if (opcode != 7) {
						if (opcode != 8) {
							if (opcode == 10) {
								buffer.g2();
							}
							if (opcode == 11) {
								stackable = 1;
							} else if (opcode != 12) {
								if (opcode == 13) {
									buffer.g1();
								} else if (opcode == 14) {
									buffer.g1();
								} else if (opcode == 16) {
									membersItem = true;
								} else if (opcode == 23) {
									maleWearId = buffer.g2();
									maleWieldY = buffer.g1();
								} else if (opcode != 24) {
									if (opcode != 25) {
										if (opcode == 26) {
											secondaryFemaleModel = buffer.g2();
										} else if (opcode < 30 || opcode >= 35) {
											if (opcode >= 35 && opcode < 40) {
												actions[opcode + -35] = buffer.gstr();
											} else if (opcode != 40) {
												if (opcode != 41) {
													if (opcode != 42) {
														if (opcode != 65) {
															if (opcode != 78) {
																if (opcode == 79) {
																	tertiaryFemaleEquipmentModel = buffer.g2();
																} else if (opcode != 90) { // Short
																	if (opcode == 91) {
																		femaleDialogue = buffer.g2();
																	} else if (opcode == 92) {
																		secondaryMaleDialogue = buffer.g2();
																	} else if (opcode == 93) {
																		secondaryFemaleDialogue = buffer.g2();
																	} else if (opcode != 95) { // Short
																		if (opcode == 96) {
																			anInt3956 = buffer.g1();
																		} else if (opcode != 97) { // Short
																			if (opcode != 98) { // Short
																				if (opcode < 100 || opcode >= 110) {
																					if (opcode != 110) {
																						if (opcode == 111) {
																							scale_y = buffer.g2();
																						} else if (opcode == 112) {
																							scale_z = buffer.g2();
																						} else if (opcode == 113) {
																							ambient = buffer.g1s();
																						} else if (opcode == 114) {
																							contrast = 5 * buffer.g1s();
																						} else if (opcode == 115) {
																							team = buffer.g1();
																						} else if (opcode != 121) {
																							if (opcode == 122) {
																								lendTemplateID = buffer.g2();
																							} else if (opcode == 125) {
																								manwearxoff = buffer.g1s();
																								manwearyoff = buffer.g1s();
																								manwearzoff = buffer.g1s();
																							} else if (opcode == 126) {
																								womanwearxoff = buffer.g1s();
																								womanwearyoff = buffer.g1s();
																								womanwearzoff = buffer.g1s();
																							} else if (opcode == 249) {
																								int length = buffer.g1() & 0xFF;
																								if (clientScriptData == null) {
																									int size = MathTools.get_greater_pow2(length);
																									clientScriptData = new HashTable(size);
																								}

																								for (int index = 0; index < length; index++) {
																									boolean string = (buffer.g1() & 0xFF) == 1;
																									int key = buffer.g4();
																									Linkable value = string ? new StringNode(buffer.gstr()) : new IntegerNode(buffer.g4());
																									// def.clientScriptData.put(key, value);
																									clientScriptData.put(key, value);
																								}
																							}
																						} else {
																							lendID = buffer.g2();
																						}
																					} else {
																						scale_x = buffer.g2();
																					}
																				} else {
																					if (stackIDs == null) {
																						stackAmounts = new int[10];
																						stackIDs = new int[10];
																					}
																					stackIDs[opcode + -100] = buffer.g2();
																					stackAmounts[-100 + opcode] = buffer.g2();
																				}
																			} else {
																				notedTemplateId = buffer.g2();
																			}
																		} else {
																			noteInfoId = buffer.g2();
																		}
																	} else {
																		modelRotationZ = buffer.g2();
																	}
																} else {
																	maleDialogue = buffer.g2();
																}
															} else {
																tertiaryMaleEquipmentModel = buffer.g2();
															}
														} else {
															unnoted = true;
														}
													} else {
														int i_34_ = buffer.g1();
														recolorIndices = new byte[i_34_];
														for (int i_35_ = 0; (i_35_ ^ 0xffffffff) > (i_34_ ^ 0xffffffff); i_35_++) {
															recolorIndices[i_35_] = buffer.g1s();
														}
													}
												} else {
													int i_36_ = buffer.g1();
													retex_src = new short[i_36_];
													retex_dst = new short[i_36_];
													for (int i_37_ = 0; (i_36_ ^ 0xffffffff) < (i_37_ ^ 0xffffffff); i_37_++) {
														retex_src[i_37_] = (short) buffer.g2();
														retex_dst[i_37_] = (short) buffer.g2();
													}
												}
											} else {
												int i_38_ = buffer.g1();
												modifiedColors = new short[i_38_];
												originalColors = new short[i_38_];
												for (int i_39_ = 0; (i_39_ ^ 0xffffffff) > (i_38_ ^ 0xffffffff); i_39_++) {
													originalColors[i_39_] = (short) buffer.g2();
													modifiedColors[i_39_] = (short) buffer.g2();
												}
											}
										} else {
											groundActions[-30 + opcode] = buffer.gstr();
											if (groundActions[opcode + -30].equalsIgnoreCase(Mouse.hiddenOptionString)) {
												groundActions[opcode - 30] = null;
											}
										}
									} else {
										femaleWearId = buffer.g2();
										femaleWieldY = buffer.g1();
										femaleWieldY = 0;
									}
								} else {
									secondaryMaleModel = buffer.g2();
								}
							} else {
								value = buffer.g4();
							}
						} else {
							modelOffset2 = buffer.g2();
							if (modelOffset2 > 32767) {
								modelOffset2 -= 65536;
							}
						}
					} else {
						modelOffset1 = buffer.g2();
						if ((modelOffset1 ^ 0xffffffff) < -32768) {
							modelOffset1 -= 65536;
						}
						// anInt3922 += 100;
					}
				} else {
					modelRotation2 = buffer.g2();
				}
			} else {
				modelRotation1 = buffer.g2();
			}
		} else {
			name = buffer.gstr();
		}
		if (b != 111) {
			lendTemplateID = -35;
		}
	}

	/**
	 * Gets the name.
	 *
	 * @return the name.
	 */
	public String getName() {
		return name.toString();
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *             the name to set
	 */
	public void setName(RSString name) {
		this.name = name;
	}

	final SoftwareModel getInterfaceModel() {
		Mesh model = Mesh.fromJs5(itemModelContainer, interfaceModelId, 0);
		if (model == null) {
			return null;
		}
		if (originalColors != null) {
			for (int i = 0; (originalColors.length ^ 0xffffffff) < (i ^ 0xffffffff); i++) {
				if (recolorIndices != null && (recolorIndices.length ^ 0xffffffff) < (i ^ 0xffffffff)) {
					model.recolor(originalColors[i], itemColors[0xff & recolorIndices[i]]);
				} else {
					model.recolor(originalColors[i], modifiedColors[i]);
				}
			}
		}
		if (retex_src != null) {
			for (int i = 0; i < retex_src.length; i++) {
				model.retexture(retex_src[i], retex_dst[i]);
			}
		}
		SoftwareModel m = model.light(64 + ambient, 768 + contrast, -50, -10, -50);
		if (scale_x != 128 || scale_y != 128 || scale_z != 128) {
			m.scale(scale_x, scale_y, scale_z);
		}
		return m;
	}

	public static void method738() {
		aRequest_3944 = null;
		aClass16_3914 = null;
		aClass16_3951 = null;
		aClass16_3958 = null;
	}

	final boolean method739(boolean bool) {
		int i_40_ = maleDialogue;
		int i_42_ = secondaryMaleDialogue;
		if (bool) {
			i_40_ = femaleDialogue;
			i_42_ = secondaryFemaleDialogue;
		}
		if ((i_40_ ^ 0xffffffff) == 0) {
			return true;
		}
		boolean bool_43_ = true;
		if (!itemModelContainer.is_file_cached(i_40_, 0)) {
			bool_43_ = false;
		}
		if ((i_42_ ^ 0xffffffff) != 0 && !itemModelContainer.is_file_cached(i_42_, 0)) {
			bool_43_ = false;
		}
		return bool_43_;
	}

	final void copyLendTemplate(ObjType def, ObjType class23_sub13_sub11_45_, byte b) {
		recolorIndices = def.recolorIndices;
		secondaryFemaleDialogue = class23_sub13_sub11_45_.secondaryFemaleDialogue;
		modelZoom = def.modelZoom;
		interfaceModelId = def.interfaceModelId;
		modelRotation1 = def.modelRotation1;
		modelRotation2 = def.modelRotation2;
		secondaryMaleDialogue = class23_sub13_sub11_45_.secondaryMaleDialogue;
		secondaryFemaleModel = class23_sub13_sub11_45_.secondaryFemaleModel;
		membersItem = class23_sub13_sub11_45_.membersItem;
		modelOffset2 = def.modelOffset2;
		secondaryMaleModel = class23_sub13_sub11_45_.secondaryMaleModel;
		tertiaryFemaleEquipmentModel = class23_sub13_sub11_45_.tertiaryFemaleEquipmentModel;
		femaleWearId = class23_sub13_sub11_45_.femaleWearId;
		maleWearId = class23_sub13_sub11_45_.maleWearId;
		modifiedColors = def.modifiedColors;
		originalColors = def.originalColors;
		actions = new RSString[5];
		femaleDialogue = class23_sub13_sub11_45_.femaleDialogue;
		maleDialogue = class23_sub13_sub11_45_.maleDialogue;
		team = class23_sub13_sub11_45_.team;
		groundActions = class23_sub13_sub11_45_.groundActions;
		retex_src = def.retex_src;
		name = class23_sub13_sub11_45_.name;
		modelOffset1 = def.modelOffset1;
		tertiaryMaleEquipmentModel = class23_sub13_sub11_45_.tertiaryMaleEquipmentModel;
		value = 0;
		retex_dst = def.retex_dst;
		modelRotationZ = def.modelRotationZ;
		manwearxoff = def.manwearxoff;
		manwearyoff = def.manwearyoff;
		manwearzoff = def.manwearzoff;
		womanwearxoff = def.womanwearxoff;
		womanwearyoff = def.womanwearyoff;
		womanwearzoff = def.womanwearzoff;
		if (class23_sub13_sub11_45_.actions != null) {
			for (int i = 0; i < 4; i++) {
				actions[i] = class23_sub13_sub11_45_.actions[i];
			}
		}
		if (b != -22) {
			lendTemplateID = 104;
		}
		actions[4] = StaticMethods.aClass16_3475;
	}

	final Model get_dialog_model(SeqType seq, int current_frameid, int next_frameid, int tick, int objco) {
		if (stackIDs != null && objco > 1) {
			int i_48_ = -1;
			for (int i_49_ = 0; i_49_ < 10; i_49_++) {
				if (stackAmounts[i_49_] <= objco && stackAmounts[i_49_] != 0) {
					i_48_ = stackIDs[i_49_];
				}
			}
			if (i_48_ != -1) {
				return ObjTypeList.list(i_48_).get_dialog_model(seq, current_frameid, next_frameid, tick, 1);
			}
		}
		Model var11 = (Model) itemModelCache.get(itemId);
		if (var11 == null) {
			Mesh model = Mesh.fromJs5(itemModelContainer, interfaceModelId, 0);
			if (model == null) {
				return null;
			}
			if (originalColors != null) {
				for (int i_50_ = 0; originalColors.length > i_50_; i_50_++) {
					if (recolorIndices == null || recolorIndices.length <= i_50_) {
						model.recolor(originalColors[i_50_], modifiedColors[i_50_]);
					} else {
						model.recolor(originalColors[i_50_], itemColors[recolorIndices[i_50_] & 0xff]);
					}
				}
			}
			if (retex_src != null) {
				for (int i_51_ = 0; retex_src.length > i_51_; i_51_++) {
					model.retexture(retex_src[i_51_], retex_dst[i_51_]);
				}
			}
			var11 = model.method2008(64 + ambient, 768 + contrast, -50, -10, -50);
			if (scale_x != 128 || scale_y != 128 || scale_z != 128) {
				var11.scale(scale_x, scale_y, scale_z);
			}
			var11.renders_in_one_tile = true;
			itemModelCache.put(itemId, var11);
		}
		if (seq != null) {
			var11 = seq.get_animated_dialoghead(var11, current_frameid, next_frameid, tick);
		}
		return var11;
	}

	static final void method742(byte b) {
		int i = 128 * StaticMethods.anInt3262 + 64;
		int i_52_ = 128 * RSString.anInt1950 - -64;
		int i_53_ = Scene.get_average_height(localHeight, i_52_, i) - Class28.anInt432;
		if (Camera.yCameraPos < i) {
			Camera.yCameraPos += Class42.anInt660 * (i - Camera.yCameraPos) / 1000 + CullingCluster.anInt913;
			if ((Camera.yCameraPos ^ 0xffffffff) < (i ^ 0xffffffff)) {
				Camera.yCameraPos = i;
			}
		}
		if (Camera.xCameraPos < i_52_) {
			Camera.xCameraPos += Class42.anInt660 * (i_52_ - Camera.xCameraPos) / 1000 + CullingCluster.anInt913;
			if (i_52_ < Camera.xCameraPos) {
				Camera.xCameraPos = i_52_;
			}
		}
		if ((i_52_ ^ 0xffffffff) > (Camera.xCameraPos ^ 0xffffffff)) {
			Camera.xCameraPos -= CullingCluster.anInt913 - -(Class42.anInt660 * (-i_52_ + Camera.xCameraPos) / 1000);
			if (Camera.xCameraPos < i_52_) {
				Camera.xCameraPos = i_52_;
			}
		}
		i_52_ = 64 + StaticMethods2.anInt3868 * 128;
		if (Camera.yCameraPos > i) {
			Camera.yCameraPos -= CullingCluster.anInt913 + Class42.anInt660 * (-i + Camera.yCameraPos) / 1000;
			if ((Camera.yCameraPos ^ 0xffffffff) > (i ^ 0xffffffff)) {
				Camera.yCameraPos = i;
			}
		}
		if (i_53_ > Camera.zCameraPos) {
			Camera.zCameraPos += CullingCluster.anInt913 + Class42.anInt660 * (-Camera.zCameraPos + i_53_) / 1000;
			if (Camera.zCameraPos > i_53_) {
				Camera.zCameraPos = i_53_;
			}
		}
		if (i_53_ < Camera.zCameraPos) {
			Camera.zCameraPos -= CullingCluster.anInt913 + Class42.anInt660 * (Camera.zCameraPos - i_53_) / 1000;
			if ((i_53_ ^ 0xffffffff) < (Camera.zCameraPos ^ 0xffffffff)) {
				Camera.zCameraPos = i_53_;
			}
		}
		i = 128 * Varbit.anInt4006 + 64;
		i_53_ = Scene.get_average_height(localHeight, i_52_, i) - StaticMethods.anInt3300;
		int i_54_ = i_52_ + -Camera.xCameraPos;
		int i_55_ = i + -Camera.yCameraPos;
		int i_56_ = -Camera.zCameraPos + i_53_;
		int i_57_ = (int) Math.sqrt(i_55_ * i_55_ + i_54_ * i_54_);
		int i_58_ = 0x7ff & (int) (Math.atan2(i_56_, i_57_) * 325.949);
		int i_59_ = (int) (Math.atan2(i_54_, i_55_) * -325.949) & 0x7ff;
		if (i_58_ < 128) {
			i_58_ = 128;
		}
		int i_60_ = -Camera.xCameraCurve + i_59_;
		if (i_60_ > 1024) {
			i_60_ -= 2048;
		}
		if (i_58_ > 383) {
			i_58_ = 383;
		}
		if (b <= 87) {
			method731(-25, -37, -61);
		}
		if (Camera.yCameraCurve < i_58_) {
			Camera.yCameraCurve += SomeSoundClass.anInt3625 + (-Camera.yCameraCurve + i_58_) * StaticMethods.anInt3396 / 1000;
			if ((Camera.yCameraCurve ^ 0xffffffff) < (i_58_ ^ 0xffffffff)) {
				Camera.yCameraCurve = i_58_;
			}
		}
		if (i_60_ < -1024) {
			i_60_ += 2048;
		}
		if ((i_60_ ^ 0xffffffff) < -1) {
			Camera.xCameraCurve += SomeSoundClass.anInt3625 - -(i_60_ * StaticMethods.anInt3396 / 1000);
			Camera.xCameraCurve &= 0x7ff;
		}
		if ((i_60_ ^ 0xffffffff) > -1) {
			Camera.xCameraCurve -= SomeSoundClass.anInt3625 - -(-i_60_ * StaticMethods.anInt3396 / 1000);
			Camera.xCameraCurve &= 0x7ff;
		}
		int i_61_ = -Camera.xCameraCurve + i_59_;
		if (i_58_ < Camera.yCameraCurve) {
			Camera.yCameraCurve -= SomeSoundClass.anInt3625 + StaticMethods.anInt3396 * (-i_58_ + Camera.yCameraCurve) / 1000;
			if ((i_58_ ^ 0xffffffff) < (Camera.yCameraCurve ^ 0xffffffff)) {
				Camera.yCameraCurve = i_58_;
			}
		}
		if (i_61_ > 1024) {
			i_61_ -= 2048;
		}
		if (i_61_ < -1024) {
			i_61_ += 2048;
		}
		if (i_61_ < 0 && i_60_ > 0 || (i_61_ ^ 0xffffffff) < -1 && i_60_ < 0) {
			Camera.xCameraCurve = i_59_;
		}
	}

	public static final Class87_Sub4 method743(byte b, Packet class23_sub5) {
		if (b != -65) {
			return null;
		}
		return new Class87_Sub4(class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g2s(), class23_sub5.g3(), class23_sub5.g1());
	}

	public ObjType() {
		secondaryMaleDialogue = -1;
		lendTemplateID = -1;
		modelOffset2 = 0;
		scale_x = 128;
		unnoted = false;
		tertiaryFemaleEquipmentModel = -1;
		secondaryMaleModel = -1;
		secondaryFemaleDialogue = -1;
		team = 0;
		scale_y = 128;
		maleWearId = -1;
		membersItem = false;
		maleDialogue = -1;
		ambient = 0;
		femaleDialogue = -1;
		groundActions = new RSString[] { null, null, AbstractTimer.aClass16_307, null, null };
		modelZoom = 2000;
		tertiaryMaleEquipmentModel = -1;
		femaleWearId = -1;
		actions = new RSString[] { null, null, null, null, InvType.aClass16_4186 };
		modelOffset1 = 0;
		femaleWieldY = 0;
		modelRotation2 = 0;
		value = 1;
		modelRotationZ = 0;
		notedTemplateId = -1;
		secondaryFemaleModel = -1;
		maleWieldY = 0;
		anInt3956 = 0;
		contrast = 0;
		noteInfoId = -1;
		scale_z = 128;
		manwearxoff = 0;
		manwearyoff = 0;
		manwearzoff = 0;
		womanwearxoff = 0;
		womanwearyoff = 0;
		womanwearzoff = 0;
	}

	public boolean isNoted() {
		return !unnoted;
	}

	static {
		aClass16_3914 = aClass16_3951;
		aClass16_3958 = RSString.createString(")3)3)3");
		itemModelCache = new ModelList(50);
	}

	public int getId() {
		return itemId;
	}

	final RSString method1004(int i_24_, RSString string) {

		if (clientScriptData == null) {
			return string;
		}
		StringNode class279_sub4 = (StringNode) clientScriptData.get(i_24_);
		if (class279_sub4 == null) {
			return string;
		}
		return class279_sub4.value;
	}

	final int getParam(int id, int defaultValue) {
		if (clientScriptData == null) {
			return defaultValue;
		}
		IntegerNode class279_sub6 = (IntegerNode) clientScriptData.get(id);
		if (class279_sub6 == null) {
			return defaultValue;
		}
		return class279_sub6.value;
	}
}
