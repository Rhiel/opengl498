package com.jagex;

/**
 * Created by Chris on 4/24/2017.
 */
public class RSInterfaceLayout {

	static final void calc_layout(RSInterface widget, boolean bool) {
		int cont_w = widget.scroll_width == 0 ? widget.layout_width : widget.scroll_width;
		int cont_h = widget.scroll_height == 0 ? widget.layout_height : widget.scroll_height;
		calc_scrolling(StaticMethods.cached_interfaces[widget.uid >> 16], widget.uid, cont_w, cont_h, bool);
		if (widget.dynamic_components != null) {
			calc_scrolling(widget.dynamic_components, widget.uid, cont_w, cont_h, bool);
		}
		InterfaceNode node = (InterfaceNode) Class36.anOa565.get(widget.uid);
		if (node != null) {
			calc_layout(node.interfaceId, cont_w, cont_h);
		}
	}

	static final void calc_scrolling(RSInterface[] rsInterfaces, int scroll_id, int viewport_width, int viewport_height, boolean bool) {
		for (RSInterface rsInterface : rsInterfaces) {
			RSInterface inter = rsInterface;
			if (inter != null && (inter.parentId ^ 0xffffffff) == (scroll_id ^ 0xffffffff)) {
				calc_size(inter, viewport_width, viewport_height, 1, bool);
				calc_position(inter, viewport_width, viewport_height, 1);
				if ((inter.scroll_height - inter.layout_height ^ 0xffffffff) > (inter.scroll_y ^ 0xffffffff)) {
					inter.scroll_y = inter.scroll_height - inter.layout_height;
				}
				if ((inter.scroll_y ^ 0xffffffff) > -1) {
					inter.scroll_y = 0;
				}
				if (inter.scroll_width + -inter.layout_width < inter.scroll_x) {
					inter.scroll_x = inter.scroll_width + -inter.layout_width;
				}
				if (inter.scroll_x < 0) {
					inter.scroll_x = 0;
				}
				if (inter.type == 0) {
					calc_layout(inter, bool);
				}
			}
		}
	}

	public static void update_layout(RSInterface arg1) {
		RSInterface class94 = RSInterfaceList.get_parent(arg1);
		int cont_w;
		int cont_h;
		if (null == class94) {
			cont_w = GameShell.frame_width;
			cont_h = GameShell.frame_height;
		} else {
			cont_w = class94.layout_width;
			cont_h = class94.layout_height;
		}
		calc_size(arg1, cont_w, cont_h, 1, false);
		calc_position(arg1, cont_w, cont_h, 1);
	}

	public static void calc_size(RSInterface widget, int viewport_width, int viewport_height, int arg1, boolean arg2) {
		boolean target = widget.uid >>> 16 == 742;
		int i = widget.layout_width;
		if (widget.h_size_mode == 0) {
			widget.layout_width = widget.width;
		} else if (widget.h_size_mode == 1) {
			widget.layout_width = -widget.width + viewport_width;
		} else if (widget.h_size_mode == 2) {
			widget.layout_width = widget.width * viewport_width >> 14;
		} else if (widget.h_size_mode == 3) {
			if (widget.type == 2) {
				widget.layout_width = (widget.width - 1) * widget.anInt1046 + widget.width * 32;
			} else if (7 == widget.type) {
				widget.layout_width = widget.width * 115 + widget.anInt1046 * (-1 + widget.width);
			}
		}
		int i_0_ = widget.layout_height;
		if (widget.v_size_mode == 0) {
			widget.layout_height = widget.height;
		} else if (widget.v_size_mode == 1) {
			widget.layout_height = -widget.height + viewport_height;
		} else if (widget.v_size_mode == 2) {
			widget.layout_height = viewport_height * widget.height >> 14;
		} else if (widget.v_size_mode == 3) {
			if (widget.type == 2) {
				widget.layout_height = (-1 + widget.height) * widget.anInt998 + 32 * widget.height;
			} else if (widget.type == 7) {
				widget.layout_height = widget.anInt998 * (widget.height - 1) + widget.height * 12;
			}
		}
		if (4 == widget.h_size_mode) {
			widget.layout_width = widget.aspect_width * widget.layout_height / widget.aspect_height;
		}
		if (4 == widget.v_size_mode) {
			widget.layout_height = widget.aspect_height * widget.layout_width / widget.aspect_width;
		}
		if (Class75.qa_op_test && (Class47.getOptionMask(widget, 105).anInt2452 != 0 || widget.type == 0)) {
			if (widget.layout_height < 5 && (widget.layout_width ^ 0xffffffff) > -6) {
				widget.layout_height = 5;
				widget.layout_width = 5;
			} else {
				if ((widget.layout_width ^ 0xffffffff) >= -1) {
					widget.layout_width = 5;
				}
				if (widget.layout_height <= 0) {
					widget.layout_height = 5;
				}
			}
		}
		// if ( arg0.content_type == 1337 )
		// kk.f_i = arg0;
		if (arg2 && null != widget.anObjectArray1604 && (widget.layout_width != i || i_0_ != widget.layout_height)) {
			CS2Event class14_sub21 = new CS2Event();
			class14_sub21.component = widget;
			class14_sub21.scriptArguments = widget.anObjectArray1604;
			Class71_Sub2_Sub1.aClass89_4470.add_last(class14_sub21);
		}
	}

	public static void calc_position(RSInterface widget, int viewport_width, int viewport_height, int arg0) {
		try {
			if (-1 != (widget.h_pos_mode ^ 0xffffffff)) {
				if (widget.h_pos_mode != 1) {
					if (widget.h_pos_mode == 2) {
						widget.layout_x = viewport_width + -widget.layout_width + -widget.positionX;
					} else if (-4 != (widget.h_pos_mode ^ 0xffffffff)) {
						if (4 != widget.h_pos_mode) {
							widget.layout_x = -(widget.positionX * viewport_width >> 379238574) + -widget.layout_width + viewport_width;
						} else {
							widget.layout_x = (widget.positionX * viewport_width >> 251056174) + (viewport_width + -widget.layout_width) / 2;
						}
					} else {
						widget.layout_x = viewport_width * widget.positionX >> 2074882030;
					}
				} else {
					widget.layout_x = (-widget.layout_width + viewport_width) / 2 + widget.positionX;
				}
			} else {
				widget.layout_x = widget.positionX;
			}
			if (0 == widget.v_pos_mode) {
				widget.layout_y = widget.positionY;
			} else if (-2 == (widget.v_pos_mode ^ 0xffffffff)) {
				widget.layout_y = widget.positionY + (-widget.layout_height + viewport_height) / 2;
			} else if ((widget.v_pos_mode ^ 0xffffffff) == -3) {
				widget.layout_y = -widget.layout_height + viewport_height - widget.positionY;
			} else if ((widget.v_pos_mode ^ 0xffffffff) == -4) {
				widget.layout_y = widget.positionY * viewport_height >> 1694247854;
			} else if ((widget.v_pos_mode ^ 0xffffffff) == -5) {
				widget.layout_y = (viewport_height - widget.layout_height) / 2 - -(widget.positionY * viewport_height >> 1282940846);
			} else {
				widget.layout_y = -widget.layout_height + viewport_height + -(widget.positionY * viewport_height >> 547048398);
			}
			if (Class75.qa_op_test && (Class47.getOptionMask(widget, 105).anInt2452 != 0 || widget.type == 0)) {
				if (widget.layout_x < 0) {
					widget.layout_x = 0;
				} else if (viewport_width < widget.layout_x - -widget.layout_width) {
					widget.layout_x = -widget.layout_width + viewport_width;
				}
				if (-1 >= (widget.layout_y ^ 0xffffffff)) {
					if ((widget.layout_y + widget.layout_height ^ 0xffffffff) < (viewport_height ^ 0xffffffff)) {
						widget.layout_y = viewport_height - widget.layout_height;
					}
				} else {
					widget.layout_y = 0;
				}
			}
		} catch (RuntimeException runtimeexception) {
			runtimeexception.printStackTrace();
		}
	}

	public static final void calc_layout() {
		calc_layout(GameClient.interface_top_id, GameShell.window_width, GameShell.window_height);
	}

	public static final void calc_layout(int uid, int viewport_width, int viewport_height) {
		if (AbstractTimer.hasActiveInterface(-10924, uid)) {
			calc_scrolling(StaticMethods.cached_interfaces[uid], -1, viewport_width, viewport_height, true);
		}
	}
}
