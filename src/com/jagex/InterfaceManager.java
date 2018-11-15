package com.jagex;

import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.launcher.GamePlayConfiguration;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

/**
 * @author Walied K. Yassen
 */
public final class InterfaceManager {

	public static void render_layer(RSInterface[] components, int layer, int x, int y, int width, int height, int scroll_x, int scroll_y, int i_10_, int i_12_) {
		Rasterizer rasterizer = GraphicTools.get_rasterizer();
		if (GLManager.opengl_mode) {
			GLState.set_clipping(x, y, width, height);
		} else {
			Rasterizer2D.set_clipping(x, y, width, height);
			rasterizer.clip_reset();
		}
		for (RSInterface widget : components) {
			if (widget != null && ((widget.parentId ^ 0xffffffff) == (layer ^ 0xffffffff) || (layer ^ 0xffffffff) == 1412584498 && StaticMethods.fromInterface == widget)) {
				int quadid;
				if (i_10_ != -1) {
					quadid = i_10_;
				} else {
					StaticMethods.quadsx[StaticMethods.widget_quads] = scroll_x + widget.layout_x;
					StaticMethods.anIntArray2286[StaticMethods.widget_quads] = scroll_y + widget.layout_y;
					Class55.anIntArray865[StaticMethods.widget_quads] = widget.layout_height;
					WallDecoration.anIntArray372[StaticMethods.widget_quads] = widget.layout_width;
					quadid = StaticMethods.widget_quads++;
				}
				widget.anInt1144 = GameClient.timer;
				widget.anInt1078 = quadid;
				if (!widget.newer_interface || !HintIcon.isHidden(true, widget)) {
					if ((widget.content_type ^ 0xffffffff) < -1) {
						GroundObjEntity.method1141((byte) 123, widget);
					}
					int layerDrawX = scroll_x + widget.layout_x;
					int layerDrawY = scroll_y + widget.layout_y;
					int alpha = widget.transparency;
					if (Class75.qa_op_test && (Class47.getOptionMask(widget, 66).anInt2452 != 0 || widget.type == 0) && alpha > 127) {
						alpha = 127;
					}
					if (StaticMethods.fromInterface == widget) {
						if ((layer ^ 0xffffffff) != 1412584498 && !widget.dragRenderBehaviour) {
							FileSystem.anInt250 = scroll_x;
							Class31.aClass64Array484 = components;
							PlayerMasks.anInt907 = scroll_y;
							continue;
						}
						if (GameTimer.aBoolean2003 && CollisionMap.aBoolean1310) {
							int i_18_ = Mouse.mouseX;
							i_18_ -= StaticMethods.anInt3059;
							if ((Class48.anInt751 ^ 0xffffffff) < (i_18_ ^ 0xffffffff)) {
								i_18_ = Class48.anInt751;
							}
							int i_19_ = Mouse.mouseY;
							i_19_ -= QuickChatDefinition.anInt4034;
							if ((RSInterface.anInt1082 ^ 0xffffffff) < (i_19_ ^ 0xffffffff)) {
								i_19_ = RSInterface.anInt1082;
							}
							if (RSInterface.anInt1082 + RSInterface.aClass64_1102.layout_height < widget.layout_height + i_19_) {
								i_19_ = RSInterface.anInt1082 + RSInterface.aClass64_1102.layout_height - widget.layout_height;
							}
							if (RSInterface.aClass64_1102.layout_width + Class48.anInt751 < widget.layout_width + i_18_) {
								i_18_ = -widget.layout_width + Class48.anInt751 + RSInterface.aClass64_1102.layout_width;
							}
							layerDrawY = i_19_;
							layerDrawX = i_18_;
						}
						if (!widget.dragRenderBehaviour) {
							alpha = 128;
						}
					}
					int draw_y;
					int draw_x;
					int draw_height;
					int draw_width;
					if (widget.type == 2) {
						draw_x = x;
						draw_y = y;
						draw_width = width;
						draw_height = height;
					} else {
						int aspect_x = layerDrawX + widget.layout_width;
						int aspect_y = layerDrawY + widget.layout_height;
						if (widget.type == 9) {
							aspect_x++;
							aspect_y++;
						}
						draw_x = x < layerDrawX ? layerDrawX : x;
						draw_y = y < layerDrawY ? layerDrawY : y;
						draw_width = aspect_x < width ? aspect_x : width;
						draw_height = aspect_y < height ? aspect_y : height;
					}
					if (!widget.newer_interface || draw_x < draw_width && (draw_y ^ 0xffffffff) > (draw_height ^ 0xffffffff)) {
						if (widget.content_type != 0) {
							if (widget.content_type == 1337 || widget.content_type == 1403 && GLManager.opengl_mode) {
								Scene.scene_component = widget;
								Scene.scene_draw_x = layerDrawX;
								Scene.scene_draw_y = layerDrawY;
								Scene.draw_component(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, widget.content_type == 1403);
								if (GLManager.opengl_mode) {
									GLState.set_clipping(x, y, width, height);
								} else {
									Rasterizer2D.set_clipping(x, y, width, height);
								}
								continue;
							} else if (widget.content_type == 1338) {
								if (widget.method1222(126)) {
									ComponentMinimap.drawMinimapIcons(widget, layerDrawX, layerDrawY, quadid);
									if (GLManager.opengl_mode) {
										GLState.set_clipping(x, y, width, height);
									} else {
										Rasterizer2D.set_clipping(x, y, width, height);
									}
								}
								continue;
							} else if (widget.content_type == 1339) {
								if (widget.method1222(119)) {
									AbstractMouseWheel.method1241(widget, layerDrawX, layerDrawY, quadid);
									if (GLManager.opengl_mode) {
										GLState.set_clipping(x, y, width, height);
									} else {
										Rasterizer2D.set_clipping(x, y, width, height);
									}
								}
								continue;
							} else if (widget.content_type == 1400) {
								WorldMap.method799(draw_x, draw_y, widget.layout_width, widget.layout_height);
								RSInterfaceList.is_dirty[quadid] = true;
								Class36.needs_clipping[quadid] = true;
								if (GLManager.opengl_mode) {
									GLState.set_clipping(x, y, width, height);
								} else {
									Rasterizer2D.set_clipping(x, y, width, height);
								}
								continue;
							} else if (widget.content_type == 1401) {
								WorldMap.method72(draw_x, widget.layout_height, widget.layout_width, 19481, draw_y);
								RSInterfaceList.is_dirty[quadid] = true;
								Class36.needs_clipping[quadid] = true;
								if (GLManager.opengl_mode) {
									GLState.set_clipping(x, y, width, height);
								} else {
									Rasterizer2D.set_clipping(x, y, width, height);
								}
								continue;
							} else if (widget.content_type == 1405) {
								if (GamePlayConfiguration.isFPSEnabled) {
									Class97.render_debug_block(widget.layout_x, widget.layout_y, widget);
									RSInterfaceList.is_dirty[quadid] = true;
									Class36.needs_clipping[quadid] = true;
								}
								continue;
							} else if (widget.content_type == 1408) {
								Class97.render_colourchooser_diagram(layerDrawX, layerDrawY, widget);
								continue;
							} else if (widget.content_type == 1409) {
								Class97.render_colourchooser_slider(layerDrawX, layerDrawY, widget, widget.color % 64);
								continue;
							} else {
								//System.out.println("WARNING: Unhandled component content_type: " + widget.content_type);
							}
						}
						if ((widget.type ^ 0xffffffff) == -1 && widget.noClickThrough && draw_x <= Mouse.mouseX && draw_y <= Mouse.mouseY && Mouse.mouseX < draw_width && draw_height > Mouse.mouseY && !ContextMenu.menuOpen && !Class75.qa_op_test) {
							ContextMenu.menuActionRow = 1;
							BufferedRequest.aClass16Array4307[0] = StaticMethods.aClass16_3338;
							ContextMenu.menuActionID[0] = (short) 1006;
							Class98.aClass16Array1655[0] = StaticMethods.empty_string;
						}
						int i_26_ = Mouse.mouseY;
						int i_27_ = Mouse.mouseX;
						if (!ContextMenu.menuOpen && (i_27_ ^ 0xffffffff) <= (draw_x ^ 0xffffffff) && (i_26_ ^ 0xffffffff) <= (draw_y ^ 0xffffffff) && (i_27_ ^ 0xffffffff) > (draw_width ^ 0xffffffff) && draw_height > i_26_) {
							ContextMenu.buildMenu(i_26_ - layerDrawY, i_27_ + -layerDrawX, (byte) 1, widget);
						}
						if (widget.type == 0) {
							if (!widget.newer_interface && HintIcon.isHidden(true, widget) && widget != Class42.aClass64_663) {
								continue;
							}
							if (!widget.newer_interface) {
								if ((widget.scroll_height - widget.layout_height ^ 0xffffffff) > (widget.scroll_y ^ 0xffffffff)) {
									widget.scroll_y = widget.scroll_height - widget.layout_height;
								}
								if ((widget.scroll_y ^ 0xffffffff) > -1) {
									widget.scroll_y = 0;
								}
							}
							render_layer(components, widget.uid, draw_x, draw_y, draw_width, draw_height, layerDrawX - widget.scroll_x, layerDrawY - widget.scroll_y, quadid, i_12_);
							if (widget.dynamic_components != null) {
								render_layer(widget.dynamic_components, widget.uid, draw_x, draw_y, draw_width, draw_height, layerDrawX - widget.scroll_x, -widget.scroll_y + layerDrawY, quadid, i_12_);
							}
							InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(widget.uid);
							if (class23_sub25 != null) {
								if ((class23_sub25.walkable ^ 0xffffffff) == -1 && Mouse.mouseX >= draw_x && draw_y <= Mouse.mouseY && draw_width > Mouse.mouseX && Mouse.mouseY < draw_height && !ContextMenu.menuOpen && !Class75.qa_op_test) {
									BufferedRequest.aClass16Array4307[0] = StaticMethods.aClass16_3338;
									Class98.aClass16Array1655[0] = StaticMethods.empty_string;
									ContextMenu.menuActionRow = 1;
									ContextMenu.menuActionID[0] = (short) 1006;
								}
								StringNode.method909(draw_y, draw_x, draw_height, quadid, (byte) 21, layerDrawX, draw_width, class23_sub25.interfaceId, layerDrawY);
							}
							if (GLManager.opengl_mode) {
								GLState.set_clipping(x, y, width, height);
							} else {
								Rasterizer2D.set_clipping(x, y, width, height);
								rasterizer.clip_reset();
							}
						}
						if (StaticMethods.aBooleanArray3325[quadid] || Class57.anInt901 > 1) {
							if (widget.type == 0 && !widget.newer_interface && widget.layout_height < widget.scroll_height) {
								Class61.method1197(true, widget.layout_height, widget.layout_width + layerDrawX, widget.scroll_height, widget.scroll_y, layerDrawY);
							}
							if (widget.type != 1) {
								if (widget.type == 2) {
									int slot = 0;
									for (int i_29_ = 0; widget.height > i_29_; i_29_++) {
										for (int i_30_ = 0; (widget.width ^ 0xffffffff) < (i_30_ ^ 0xffffffff); i_30_++) {
											int i_31_ = layerDrawY + (widget.anInt998 + 32) * i_29_;
											int i_32_ = (widget.anInt1046 + 32) * i_30_ + layerDrawX;
											if (slot < 20) {
												i_32_ += widget.anIntArray1011[slot];
												i_31_ += widget.anIntArray1051[slot];
											}
											if (widget.obj_ids[slot] <= 0) {
												if (widget.anIntArray1042 != null && slot < 20) {
													Sprite sprite = widget.method1225(slot);
													if (sprite == null) {
														if (Class4.fetching_sprites) {
															RSInterfaceList.setDirty(widget);
														}
													} else {
														sprite.draw_clipped_left_anchor(i_32_, i_31_);
													}
												}
											} else {
												int i_33_ = -1 + widget.obj_ids[slot];
												if ((i_32_ - -32 ^ 0xffffffff) < (x ^ 0xffffffff) && (i_32_ ^ 0xffffffff) > (width ^ 0xffffffff) && (y ^ 0xffffffff) > (32 + i_31_ ^ 0xffffffff) && (i_31_ ^ 0xffffffff) > (height ^ 0xffffffff) || widget == PlayerAppearance.aClass64_790 && (Class87_Sub3.firstSlot ^ 0xffffffff) == (slot ^ 0xffffffff)) {
													Sprite sprite;
													if (NPC.anInt4374 != 1 || AbstractTimer.anInt302 != slot || Class49.anInt759 != widget.uid) {
														sprite = ObjType.get_obj_icon(1, widget.obj_stack_mode, i_33_, widget.obj_counts[slot], 3153952);
													} else {
														sprite = ObjType.get_obj_icon(2, widget.obj_stack_mode, i_33_, widget.obj_counts[slot], 0);
													}
													if (rasterizer.invalid_texture) {
														RSInterfaceList.is_dirty[quadid] = true;
													}
													if (sprite == null) {
														RSInterfaceList.setDirty(widget);
													} else if (widget != PlayerAppearance.aClass64_790 || Class87_Sub3.firstSlot != slot) {
														if (widget == GameClient.atInventoryInterface && (GameClient.atInventoryIndex ^ 0xffffffff) == (slot ^ 0xffffffff)) {
															sprite.draw(i_32_, i_31_, 128);
														} else {
															sprite.draw_clipped_left_anchor(i_32_, i_31_);
														}
													} else {
														int i_35_ = Mouse.mouseY + -StaticMethods.anInt3510;
														if (i_35_ < 5 && (i_35_ ^ 0xffffffff) < 4) {
															i_35_ = 0;
														}
														int i_36_ = Mouse.mouseX - SpawnedObject.anInt2435;
														if (i_36_ < 5 && (i_36_ ^ 0xffffffff) < 4) {
															i_36_ = 0;
														}
														if (ColourImageCache.anInt1728 < 5) {
															i_36_ = 0;
															i_35_ = 0;
														}
														sprite.draw(i_32_ + i_36_, i_31_ + i_35_, 128);
														if ((layer ^ 0xffffffff) != 0) {
															RSInterface class64_37_ = components[0xffff & layer];
															int bottom;
															int top;
															if (GLManager.opengl_mode) {
																bottom = GLState.clip_bottom;
																top = GLState.clip_top;
															} else {
																top = Rasterizer2D.clip_top;
																bottom = Rasterizer2D.clip_bottom;
															}

															if ((i_35_ + i_31_ ^ 0xffffffff) > (top ^ 0xffffffff) && class64_37_.scroll_y > 0) {
																int i_40_ = (-i_35_ + top - i_31_) * InterfaceNode.anInt2459 / 3;
																if (InterfaceNode.anInt2459 * 10 < i_40_) {
																	i_40_ = 10 * InterfaceNode.anInt2459;
																}
																if (i_40_ > class64_37_.scroll_y) {
																	i_40_ = class64_37_.scroll_y;
																}
																StaticMethods.anInt3510 += i_40_;
																class64_37_.scroll_y -= i_40_;
																RSInterfaceList.setDirty(class64_37_);
															}
															if (bottom < 32 + i_35_ + i_31_ && class64_37_.scroll_height - class64_37_.layout_height > class64_37_.scroll_y) {
																int i_41_ = (-bottom + 32 + i_31_ + i_35_) * InterfaceNode.anInt2459 / 3;
																if (InterfaceNode.anInt2459 * 10 < i_41_) {
																	i_41_ = InterfaceNode.anInt2459 * 10;
																}
																if ((-class64_37_.scroll_y + class64_37_.scroll_height - class64_37_.layout_height ^ 0xffffffff) > (i_41_ ^ 0xffffffff)) {
																	i_41_ = class64_37_.scroll_height + -class64_37_.layout_height - class64_37_.scroll_y;
																}
																class64_37_.scroll_y += i_41_;
																StaticMethods.anInt3510 -= i_41_;
																RSInterfaceList.setDirty(class64_37_);
															}
														}
													}
												}
											}
											slot++;
										}
									}
								} else if (widget.type == 3) {
									int i_42_;
									if (RSInterface.isComponentEnabled(true, widget)) {
										i_42_ = widget.textColor;
										if (widget == Class42.aClass64_663 && (widget.blackColor ^ 0xffffffff) != -1) {
											i_42_ = widget.blackColor;
										}
									} else {
										i_42_ = widget.color;
										if (Class42.aClass64_663 == widget && (widget.anInt1106 ^ 0xffffffff) != -1) {
											i_42_ = widget.anInt1106;
										}
									}
									if ((alpha ^ 0xffffffff) == -1) {
										if (widget.filled) {
											if (GLManager.opengl_mode) {
												GLShapes.fill_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_);
											} else {
												Rasterizer2D.fill_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_);
											}
										} else {
											if (GLManager.opengl_mode) {
												GLShapes.draw_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_);
											} else {
												Rasterizer2D.draw_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_);
											}
										}
									} else if (widget.filled) {
										if (GLManager.opengl_mode) {
											GLShapes.fill_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_, 256 - (0xff & alpha));
										} else {
											Rasterizer2D.fill_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_, 256 - (0xff & alpha));
										}
									} else {
										if (GLManager.opengl_mode) {
											GLShapes.draw_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_, -(0xff & alpha) + 256);
										} else {
											Rasterizer2D.draw_rectangle(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, i_42_, -(0xff & alpha) + 256);
										}
									}
								} else if (widget.type == 4) {
									Font rsFont = widget.getRSFont(StaticMedia.mod_icons, (byte) -6);
									if (rsFont == null) {
										if (Class4.fetching_sprites) {
											RSInterfaceList.setDirty(widget);
										}
									} else {
										RSString text = widget.default_text;
										int colorId;
										if (!RSInterface.isComponentEnabled(true, widget)) {
											colorId = widget.color;
											if (widget == Class42.aClass64_663 && (widget.anInt1106 ^ 0xffffffff) != -1) {
												colorId = widget.anInt1106;
											}
										} else {
											colorId = widget.textColor;
											if (Class42.aClass64_663 == widget && (widget.blackColor ^ 0xffffffff) != -1) {
												colorId = widget.blackColor;
											}
											if ((widget.aClass16_1095.length() ^ 0xffffffff) < -1) {
												text = widget.aClass16_1095;
											}
										}
										if (widget.newer_interface && widget.objid != -1) {
											ObjType def = ObjTypeList.list(widget.objid);
											text = def.name;
											if (text == null) {
												text = StaticMethods.aClass16_3378;
											}
											if ((def.stackable == 1 || widget.shownItemAmount != 1) && (widget.shownItemAmount ^ 0xffffffff) != 0) {
												text = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, text, StaticMethods.aClass16_4041, GrandExchangeOffer.method1448(widget.shownItemAmount, i_12_ + -13523) });
											}
										}
										if (widget == Varbit.aClass64_4007) {
											text = Class4.aClass16_92;
											colorId = widget.color;
										}
										if (!widget.newer_interface) {
											text = Class54.method1178(12445, widget, text);
										}
										// if (widget.uid >>> 16 == 20) {
										// System.out.println(text);
										// }
										rsFont.draw_text(text, layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, colorId, widget.shaded ? 0 : -1, widget.halignment, widget.v_text_align, widget.line_height);
									}
								} else if (widget.type == 5) {
									if (!widget.newer_interface) {
										Sprite sprite = widget.get_sprite(RSInterface.isComponentEnabled(true, widget));
										if (sprite == null) {
											if (Class4.fetching_sprites) {
												RSInterfaceList.setDirty(widget);
											}
										} else {
											sprite.draw_clipped_left_anchor(layerDrawX, layerDrawY);
										}
									} else {
										Sprite sprite = null;
										if (widget.objid != -1) {
											sprite = ObjType.get_obj_icon(widget.outline, widget.obj_stack_mode, widget.objid, widget.shownItemAmount, widget.graphic_shadow);
										} else {
											sprite = widget.get_sprite(false);
										}
										if (sprite != null) {
											int r_w = sprite.trimmed_width;
											int r_h = sprite.trimmed_height;
											if (!widget.repeat) {
												int scalar = 4096 * widget.layout_width / r_w;
												if ((widget.angle2d ^ 0xffffffff) != -1) {
													sprite.draw_rotated(layerDrawX - -(widget.layout_width / 2), widget.layout_height / 2 + layerDrawY, widget.angle2d, scalar);
												} else if (alpha != 0) {
													sprite.draw(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height, 256 - (alpha & 0xff));
												} else if ((widget.layout_width ^ 0xffffffff) == (r_w ^ 0xffffffff) && (widget.layout_height ^ 0xffffffff) == (r_h ^ 0xffffffff)) {
													sprite.draw_clipped_left_anchor(layerDrawX, layerDrawY);
												} else {
													sprite.draw(layerDrawX, layerDrawY, widget.layout_width, widget.layout_height);
												}
											} else {
												int repeat_width = (widget.layout_width + -1 + r_w) / r_w;
												int repeat_height = (widget.layout_height - (-r_h - -1)) / r_h;
												if (GLManager.opengl_mode) {
													GLState.accumlate_clipping(layerDrawX, layerDrawY, widget.layout_width + layerDrawX, widget.layout_height + layerDrawY);
													boolean fixed_width = MathTools.is_power_of_two(sprite.width);
													boolean fixed_height = MathTools.is_power_of_two(sprite.height);
													OpenGLSprite opengl_sprite = (OpenGLSprite) sprite;
													if (fixed_width && fixed_height) {
														if (alpha != 0) {
															opengl_sprite.draw_scaled(layerDrawX, layerDrawY, -(255 & alpha) + 256, repeat_width, repeat_height);
														} else {
															opengl_sprite.draw_scaled(layerDrawX, layerDrawY, repeat_width, repeat_height);
														}
													} else if (fixed_width) {
														for (int repeat_x = 0; ~repeat_height < ~repeat_x; ++repeat_x) {
															if (~alpha == -1) {
																opengl_sprite.draw_scaled(layerDrawX, repeat_x * r_h + layerDrawY, repeat_width, 1);
															} else {
																opengl_sprite.draw_scaled(layerDrawX, layerDrawY + repeat_x * r_h, 256 + -(alpha & 255), repeat_width, 1);
															}
														}
													} else if (!fixed_height) {
														for (int repeat_x = 0; ~repeat_x > ~repeat_width; ++repeat_x) {
															for (int repeat_y = 0; ~repeat_height < ~repeat_y; ++repeat_y) {
																if (alpha != 0) {
																	sprite.draw(repeat_x * r_w + layerDrawX, r_h * repeat_y + layerDrawY, -(255 & alpha) + 256);
																} else {
																	sprite.draw_clipped_left_anchor(layerDrawX - -(r_w * repeat_x), r_h * repeat_y + layerDrawY);
																}
															}
														}
													} else {
														for (int repeat_x = 0; ~repeat_width < ~repeat_x; ++repeat_x) {
															if (~alpha != -1) {
																opengl_sprite.draw_scaled(r_w * repeat_x + layerDrawX, layerDrawY, -(alpha & 255) + 256, 1, repeat_height);
															} else {
																opengl_sprite.draw_scaled(r_w * repeat_x + layerDrawX, layerDrawY, 1, repeat_height);
															}
														}
													}
													GLState.set_clipping(x, y, width, height);
												} else {
													Rasterizer2D.accumlate_clipping(layerDrawX, layerDrawY, widget.layout_width + layerDrawX, layerDrawY + widget.layout_height);
													for (int repeat_x = 0; repeat_x < repeat_width; repeat_x++) {
														for (int repeat_y = 0; repeat_y < repeat_height; repeat_y++) {
															if (widget.angle2d != 0) {
																sprite.draw_rotated(r_w * repeat_x + layerDrawX - -(r_w / 2), layerDrawY - -(r_h * repeat_y) + r_h / 2, widget.angle2d, 4096);
															} else if (alpha != 0) {
																sprite.draw(repeat_x * r_w + layerDrawX, layerDrawY - -(r_h * repeat_y), -(0xff & alpha) + 256);
															} else {
																sprite.draw_clipped_left_anchor(layerDrawX - -(repeat_x * r_w), repeat_y * r_h + layerDrawY);
															}
														}
													}
													Rasterizer2D.set_clipping(x, y, width, height);
												}
											}
										} else if (Class4.fetching_sprites) {
											RSInterfaceList.setDirty(widget);
										}
									}
								} else if (widget.type == 6) {
									boolean isEnabled = RSInterface.isComponentEnabled(true, widget);
									Model model = null;
									int var21;
									if (!isEnabled) {
										var21 = widget.media_animid;
									} else {
										var21 = widget.anInt198;
									}
									int i_52_ = 0;
									if (widget.objid == -1) {
										if (widget.media_type == 5) {
											if (widget.media_id == -1) {
												model = StaticMethods2.aPlayerAppearance_1440.getAnimatedModel(null, null, null, -1, -1, 0, 0, -1, -1, true);
											} else {
												int i_53_ = widget.media_id & 0x7ff;
												if (i_53_ == StaticMethods.thisPlayerIndex) {
													i_53_ = 2047;
												}
												Player player = GameClient.localPlayers[i_53_];
												SeqType anim = var21 == -1 ? null : SeqTypeList.list(var21);
												if (player != null && ((int) player.username.toUsernameLong() << 11 ^ 0xffffffff) == (~0x7ff & widget.media_id ^ 0xffffffff)) {
													model = player.appearance.getAnimatedModel(null, null, anim, 0, widget.media_current_frameid, 0, 0, -1, -1, true);
												}
											}
										} else if (var21 == -1) {
											model = widget.getAnimatedInterfaceModel(null, -1, -1, 0, isEnabled, GameClient.currentPlayer.appearance);// here for spec bar debug
											if (model == null && Class4.fetching_sprites) {
												RSInterfaceList.setDirty(widget);
											}
										} else {
											SeqType anim = SeqTypeList.list(var21);
											model = widget.getAnimatedInterfaceModel(anim, widget.media_current_frameid, widget.media_next_frameid, widget.media_tween_tick, isEnabled, GameClient.currentPlayer.appearance);
											if (model == null && Class4.fetching_sprites) {
												RSInterfaceList.setDirty(widget);
											}
										}
									} else {
										ObjType def = ObjTypeList.list(widget.objid);
										if (def != null) {
											def = def.getStackDefinition(123, widget.shownItemAmount);
											SeqType seq = var21 != -1 ? SeqTypeList.list(var21) : null;
											model = def.get_dialog_model(seq, widget.media_current_frameid, widget.media_next_frameid, widget.media_tween_tick, 1);
											if (model != null) {
												i_52_ = -model.get_miny() / 2;
											} else {
												RSInterfaceList.setDirty(widget);
											}
										}
									}
									if (model != null) {
										int var24;
										if (widget.media_viewport_width <= 0) {
											var24 = 256;
										} else {
											var24 = (widget.layout_width << 8) / widget.media_viewport_width;
										}
										int var25;
										if (widget.media_viewport_height > 0) {
											var25 = (widget.layout_height << 8) / widget.media_viewport_height;
										} else {
											var25 = 256;
										}
										int var26 = (var24 * widget.media_origin_x >> 8) + layerDrawX + widget.layout_width / 2;
										int var47 = layerDrawY - (-(widget.layout_height / 2) + -(var25 * widget.media_origin_y >> 8));
										if (GLManager.opengl_mode) {
											if (widget.media_orthographic) {
												GLState.setup_ortho_projection(var26, var47, widget.media_zoom, widget.media_depth_near, var24, var25);
											} else {
												GLState.setup_perspective_viewport(var26, var47, var24, var25);
												GLState.update_depth_range(widget.media_depth_scale, 1.5F * widget.media_depth_near);
											}
											GLState.load_lighting_state();
											GLState.set_depthtest_enabled(true);
											GLState.set_fog_enabled(false);
											if (widget.depthmask) {
												GLState.disable_depthmask();
											}
											if (Scene.opengl_scene_dirty) {
												GLState.reset_clipping();
												GLState.clear_depth();
												GLState.set_clipping(x, y, width, height);
												Scene.opengl_scene_dirty = false;
											}
											int var28 = Rasterizer.SINE[widget.media_xangle] * widget.media_zoom >> 16;
											int var29 = widget.media_zoom * Rasterizer.COSINE[widget.media_xangle] >> 16;
											if (widget.newer_interface) {
												model.draw(0, widget.media_yangle, widget.media_zangle, widget.media_xangle, widget.anInt258, widget.anInt264 + var28 + i_52_, widget.anInt264 + var29);
											} else {
												model.draw(0, widget.media_yangle, 0, widget.media_xangle, 0, var28, var29);
											}
											if (widget.depthmask) {
												GLState.enable_depthmask();
											}
										} else {
											rasterizer.center_adjust(var26, var47);
											int modelY = Rasterizer.SINE[widget.media_xangle] * widget.media_zoom >> 16;
											int modelSize = Rasterizer.COSINE[widget.media_xangle] * widget.media_zoom >> 16;
											if (!widget.newer_interface) {
												model.draw(0, widget.media_yangle, 0, widget.media_xangle, 0, modelY, modelSize);
											} else if (!widget.media_orthographic) {// specbar here
												model.draw(0, widget.media_yangle, widget.media_zangle, widget.media_xangle, widget.anInt258, widget.anInt264 + i_52_ + modelY, modelSize + widget.anInt264);
											} else {
												((SoftwareModel) model).method1025(0, widget.media_yangle, widget.media_zangle, widget.media_xangle, widget.anInt258, i_52_ + modelY - -widget.anInt264, modelSize + widget.anInt264, widget.media_zoom);
											}
											rasterizer.viewport_reset();
										}
									}
								} else {
									if (widget.type == 7) {
										Font class23_sub13_sub8 = widget.getRSFont(StaticMedia.mod_icons, (byte) -6);
										if (class23_sub13_sub8 == null) {
											if (Class4.fetching_sprites) {
												RSInterfaceList.setDirty(widget);
											}
											continue;
										}
										int i_60_ = 0;
										for (int i_61_ = 0; (widget.height ^ 0xffffffff) < (i_61_ ^ 0xffffffff); i_61_++) {
											for (int i_62_ = 0; (i_62_ ^ 0xffffffff) > (widget.width ^ 0xffffffff); i_62_++) {
												if ((widget.obj_ids[i_60_] ^ 0xffffffff) < -1) {
													ObjType def = ObjTypeList.list(widget.obj_ids[i_60_] - 1);
													RSString string;
													if (def.stackable != 1 && widget.obj_counts[i_60_] == 1) {
														string = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, def.name, Class23_Sub7.aClass16_2226 });
													} else {
														string = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, def.name, StaticMethods.aClass16_4041, GrandExchangeOffer.method1448(widget.obj_counts[i_60_], i_12_ + -13523) });
													}
													int i_63_ = (widget.anInt1046 + 115) * i_62_ + layerDrawX;
													int i_64_ = layerDrawY - -(i_61_ * (12 - -widget.anInt998));
													if ((widget.halignment ^ 0xffffffff) != -1) {
														if (widget.halignment != 1) {
															class23_sub13_sub8.draw_text_right_anchor(string, 115 + i_63_ - 1, i_64_, widget.color, widget.shaded ? 0 : -1);
														} else {
															class23_sub13_sub8.draw_text_centered(string, i_63_ + 57, i_64_, widget.color, widget.shaded ? 0 : -1);
														}
													} else {
														class23_sub13_sub8.draw_text(string, i_63_, i_64_, widget.color, !widget.shaded ? -1 : 0);
													}
												}
												i_60_++;
											}
										}
									}
									if (widget.type == 8 && VertexNormal.aClass64_1567 == widget && (StaticMethods.anInt3240 ^ 0xffffffff) == (Js5.anInt1811 ^ 0xffffffff)) {
										int i_65_ = 0;
										int i_66_ = 0;
										Font class23_sub13_sub8 = FontCache.p12_full;
										RSString class16 = widget.default_text;
										class16 = Class54.method1178(12445, widget, class16);
										while (class16.length() > 0) {
											int i_67_ = class16.indexOf(InstrumentDefinition.aClass16_276);
											RSString class16_68_;
											if (i_67_ != -1) {
												class16_68_ = class16.substring(i_67_, 0);
												class16 = class16.substring(4 + i_67_);
											} else {
												class16_68_ = class16;
												class16 = StaticMethods.empty_string;
											}
											int i_69_ = class23_sub13_sub8.calculate_width(class16_68_);
											i_66_ += 1 + class23_sub13_sub8.plot_height;
											if ((i_69_ ^ 0xffffffff) < (i_65_ ^ 0xffffffff)) {
												i_65_ = i_69_;
											}
										}
										i_66_ += 7;
										int i_70_ = layerDrawY + widget.layout_height - -5;
										if ((height ^ 0xffffffff) > (i_70_ - -i_66_ ^ 0xffffffff)) {
											i_70_ = -i_66_ + height;
										}
										i_65_ += 6;
										int i_71_ = -i_65_ + layerDrawX + widget.layout_width + -5;
										if ((5 + layerDrawX ^ 0xffffffff) < (i_71_ ^ 0xffffffff)) {
											i_71_ = 5 + layerDrawX;
										}
										if ((width ^ 0xffffffff) > (i_65_ + i_71_ ^ 0xffffffff)) {
											i_71_ = width + -i_65_;
										}
										if (GLManager.opengl_mode) {
											GLShapes.fill_rectangle(i_71_, i_70_, i_65_, i_66_, 16777120);
											GLShapes.draw_rectangle(i_71_, i_70_, i_65_, i_66_, 0);
										} else {
											Rasterizer2D.fill_rectangle(i_71_, i_70_, i_65_, i_66_, 16777120);
											Rasterizer2D.draw_rectangle(i_71_, i_70_, i_65_, i_66_, 0);
										}
										int i_72_ = i_70_ - (-class23_sub13_sub8.plot_height - 2);
										class16 = widget.default_text;
										class16 = Class54.method1178(12445, widget, class16);
										while ((class16.length() ^ 0xffffffff) < -1) {
											int i_73_ = class16.indexOf(InstrumentDefinition.aClass16_276);
											RSString class16_74_;
											if ((i_73_ ^ 0xffffffff) == 0) {
												class16_74_ = class16;
												class16 = StaticMethods.empty_string;
											} else {
												class16_74_ = class16.substring(i_73_, 0);
												class16 = class16.substring(i_73_ + 4);
											}
											class23_sub13_sub8.draw_text(class16_74_, i_71_ + 3, i_72_, 0, -1);
											i_72_ += 1 + class23_sub13_sub8.plot_height;
										}
									}
									if (widget.type == 9) {
										int startX;
										int endX;
										int endY;
										int startY;
										if (!widget.line_mirrored) {
											startX = layerDrawX;
											endX = widget.layout_width + layerDrawX;
											endY = layerDrawY;
											startY = layerDrawY + widget.layout_height;
										} else {
											startX = layerDrawX;
											endX = layerDrawX + widget.layout_width;
											endY = widget.layout_height + layerDrawY;
											startY = layerDrawY;
										}
										if (widget.line_width != 1) {
											if (GLManager.opengl_mode) {
												GLShapes.draw_thick_line(startX, endY, endX, startY, widget.color, widget.line_width);
											} else {
												Rasterizer2D.draw_thick_line(startX, endY, endX, startY, widget.color, widget.line_width);
											}
										} else {
											if (GLManager.opengl_mode) {
												GLShapes.draw_thin_line(startX, endY, endX, startY, widget.color);
											} else {
												Rasterizer2D.draw_thin_line(startX, endY, endX, startY, widget.color);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		// Rasterizer3D.drawingInterface = false;
	}

}
