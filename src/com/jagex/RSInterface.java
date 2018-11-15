package com.jagex;

import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.media.Sprite;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLAlphaSprite;
import com.jagex.graphics.runetek4.opengl.sprite.OpenGLSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareAlphaSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwareSprite;

public class RSInterface {
	public RSString targetVerb;
	public int anInt998;
	public int layout_height;
	public int media_zangle;
	public int angle2d;
	public int type;
	public boolean media_orthographic = false;
	public int componentIndex;
	public int media_animid = -1;
	public int scroll_x;
	public int[] anIntArray1010;
	public int[] anIntArray1011;

	public int anInt258 = 0;
	public static RSString aClass16_1016 = RSString.createString("Loading )2 please wait)3");
	public boolean newer_interface;
	public int media_viewport_width;
	public RSString aClass16_1019;
	public int media_tween_tick;
	public RSString[] ops;
	public int dragDeadTime;
	public int media_origin_y;
	public int anInt1027;
	public int[] obj_ids;
	public RSString[] options;
	public static int anInt1030;
	public boolean flip_v;
	public boolean tiling;
	public int color;
	public byte h_size_mode;
	public int anInt1038;
	public int anInt1039;
	public int anInt284;
	public int anInt285;
	public int dragDeadZone;
	public int[] anIntArray1042;
	public int timestamp_last_process;
	public int media_origin_x;
	public int v_text_align;
	public int anInt1046;
	public boolean aBoolean1047;
	public int media_zoom;
	public int[] anIntArray1051;
	public boolean aBoolean1054;
	public int optionMask;
	public int parentId;
	public int blackColor;
	public int[][] childBuffers;
	public RSString optionName;
	public RSString pauseText;
	public boolean dragRenderBehaviour;
	public int line_height;
	public int line_width;
	public int[] anIntArray1064;
	public int anInt1067;
	public int font_id;
	public boolean shaded;
	public RSString default_text;
	public boolean repeat;
	public int graphicid;

	public int anInt264;
	public int media_viewport_height;
	public int anInt1078;
	public static int anInt1079;
	public static int anInt1080;
	public boolean filled;
	public static int anInt1082;
	public int scroll_y;
	public boolean has_hooks;
	public byte v_size_mode;
	public int textColor;
	public int graphic_shadow;
	public int scroll_height;
	public int enabled_graphic;
	public int anInt1090;
	public int media_id;
	public int anInt1094;
	public RSString aClass16_1095;
	public int positionY;
	public int uid;
	public int media_current_frameid;
	public int media_next_frameid = 1;
	public int[] inventoryListenerValues;
	public static RSInterface aClass64_1102 = null;
	public int anInt196;
	public int height;
	public int anInt1106;
	public int media_xangle;
	public int[] anIntArray1109;
	public int media_yangle;
	public int media_type;
	public int width;
	public int[] anIntArray1114;
	public int[] statTransmitList;
	public int anInt198;
	public int layout_x;
	public RSInterface aClass64_1121;
	public int objid;
	public boolean line_mirrored;
	public int positionX;
	public boolean mouse_over;
	public int layout_y;
	public int transparency;
	public int layout_width;
	public int scroll_width;
	public RSString opBase;
	public RSInterface[] dynamic_components;
	public int outline;
	public int halignment;
	public int shownItemAmount;
	public boolean hidden;
	static RSString aClass16_1136;
	public static int anInt1138;
	public static RSString aClass16_1139;
	public int[] varpListenerValues;
	public boolean flip_h;
	public byte v_pos_mode;
	public int modelType;
	public int anInt1144;
	static RSString aClass16_1146;
	public int[] obj_counts;
	public int content_type;
	public boolean noClickThrough;
	public byte h_pos_mode;
	public int id;
	public boolean changed;
	public int aspect_height = 1;
	public int aspect_width = 1;
	public int obj_stack_mode;
	public int[] anIntArray211;
	public int[] anIntArray212;
	public short media_depth_near;
	public short media_depth_scale = 3000;
	public boolean depthmask;
	ServerActiveProperties serverActiveProperties = new ServerActiveProperties(0, -1);
	public Object[] anObjectArray1137;
	public Object[] mouseWheelListener;
	public Object[] varpUpdateListener;
	public Object[] key_press_handler;
	public Object[] anObjectArray1053;
	public Object[] chatbox_update_handler;
	public Object[] anObjectArray1066;
	public Object[] mouse_exit_handler;
	public Object[] anObjectArray1092;
	public Object[] mouse_dragged_handler;
	public Object[] update_timer_change_handler;
	public Object[] anObjectArray1104;
	public Object[] onStartListener;
	public Object[] anObjectArray1116;
	public Object[] attachment_update_handler;
	public Object[] mouse_pressed_handler;
	public Object[] onMouseRepeatHook;
	public Object[] mainLoopListener;
	public Object[] spellUsedListener;
	public Object[] anObjectArray1014;
	public Object[] mouse_drag_pass_handler;
	public Object[] mouse_released_handler;
	public Object[] spellUsedOnItemListener;
	public Object[] skillUpdateListener;
	public Object[] inventoryUpdateListener;
	public Object[] mouse_enter_handler;
	public Object[] anObjectArray161;
	public Object[] anObjectArray162;
	// version 20
	public int media_origin_z;// TODO: this
	public Object[] onUseOnObjHook;// TODO: was added
	public boolean hasKeyEvents;
	public byte[][] opKeys;
	public byte[][] opKeyMasks;
	public int[] opKeyRates;
	public int[] opChars;
	public int[] opCursors;
	public boolean clickMask;
	public int targetOverCursor;
	public int targetLeaveCursor;
	public int mouseOverCursor;
	public int maxLines;
	public boolean overview;
	public int version;

	public void parseVersion10(Packet buffer) {
		newer_interface = false;
		version = 10;
		type = buffer.g1();
		anInt1038 = buffer.g1();
		content_type = buffer.g2();
		positionX = buffer.g2s();
		positionY = buffer.g2s();
		width = buffer.g2();
		height = buffer.g2();
		v_pos_mode = (byte) 0;
		h_size_mode = (byte) 0;
		h_pos_mode = (byte) 0;
		v_size_mode = (byte) 0;
		transparency = buffer.g1();
		parentId = buffer.g2();
		if ((parentId ^ 0xffffffff) != -65536) {
			parentId = (uid & ~0xffff) + parentId;
		} else {
			parentId = -1;
		}
		anInt1027 = buffer.g2();
		if ((anInt1027 ^ 0xffffffff) == -65536) {
			anInt1027 = -1;
		}
		int i_3_ = buffer.g1();
		if (i_3_ > 0) {
			anIntArray1010 = new int[i_3_];
			anIntArray1114 = new int[i_3_];
			for (int i_4_ = 0; i_3_ > i_4_; i_4_++) {
				anIntArray1114[i_4_] = buffer.g1();
				anIntArray1010[i_4_] = buffer.g2();
			}
		}
		int i_5_ = buffer.g1();
		if (i_5_ > 0) {
			childBuffers = new int[i_5_][];
			for (int i_6_ = 0; i_6_ < i_5_; i_6_++) {
				int i_7_ = buffer.g2();
				childBuffers[i_6_] = new int[i_7_];
				for (int i_8_ = 0; i_8_ < i_7_; i_8_++) {
					childBuffers[i_6_][i_8_] = buffer.g2();
					if (childBuffers[i_6_][i_8_] == 65535) {
						childBuffers[i_6_][i_8_] = -1;
					}
				}
			}
		}
		if ((type ^ 0xffffffff) == -1) {
			scroll_height = buffer.g2();
			hidden = buffer.g1() == 1;
		}
		if (type == 1) {
			buffer.g2();
			buffer.g1();
		}
		if (type == 2) {
			obj_counts = new int[height * width];
			v_size_mode = (byte) 3;
			h_size_mode = (byte) 3;
			obj_ids = new int[width * height];
			int i_9_ = buffer.g1();
			if (i_9_ == 1) {
				optionMask |= 268435456;
			}
			int i_10_ = buffer.g1();
			if (i_10_ == 1) {
				optionMask |= 1073741824;
			}
			int i_11_ = buffer.g1();
			if (i_11_ == 1) {
				optionMask |= Integer.MIN_VALUE;
			}
			int i_12_ = buffer.g1();
			if (i_12_ == 1) {
				optionMask |= 536870912;
			}
			anInt1046 = buffer.g1();
			anInt998 = buffer.g1();
			anIntArray1051 = new int[20];
			anIntArray1011 = new int[20];
			anIntArray1042 = new int[20];
			for (int i_13_ = 0; i_13_ < 20; i_13_++) {
				int i_14_ = buffer.g1();
				if (i_14_ == 1) {
					anIntArray1011[i_13_] = buffer.g2s();
					anIntArray1051[i_13_] = buffer.g2s();
					anIntArray1042[i_13_] = buffer.g4();
				} else {
					anIntArray1042[i_13_] = -1;
				}
			}
			options = new RSString[5];
			for (int i_15_ = 0; i_15_ < 5; i_15_++) {
				RSString class16 = buffer.gstr();
				if (class16.length() > 0) {
					options[i_15_] = class16;
					optionMask |= 1 << 23 - -i_15_;
				}
			}
		}
		if (type == 3) {
			filled = buffer.g1() == 1;
		}
		if (type == 4 || type == 1) {
			halignment = buffer.g1();
			v_text_align = buffer.g1();
			line_height = buffer.g1();
			font_id = buffer.g2();
			if (font_id == 65535) {
				font_id = -1;
			}
			shaded = buffer.g1() == 1;
		}
		if (type == 4) {
			default_text = buffer.gstr();
			if (default_text.toString().contains("RuneScape")) {
				RSString ok = RSString.create(default_text.toString().replace("RuneScape", "Zaros"));
				default_text = ok;
			}
			aClass16_1095 = buffer.gstr();
		}
		if (type == 1 || type == 3 || type == 4) {
			color = buffer.g4();
		}
		if (type == 3 || type == 4) {
			textColor = buffer.g4();
			anInt1106 = buffer.g4();
			blackColor = buffer.g4();
		}
		if (type == 5) {
			graphicid = buffer.g4();
			enabled_graphic = buffer.g4();
		}
		if (type == 6) {
			media_type = 1;
			media_id = buffer.g2();
			if ((media_id ^ 0xffffffff) == -65536) {
				media_id = -1;
			}
			modelType = 1;
			anInt196 = buffer.g2();
			if ((anInt196 ^ 0xffffffff) == -65536) {
				anInt196 = -1;
			}
			media_animid = buffer.g2();
			if (media_animid == 65535) {
				media_animid = -1;
			}
			anInt198 = buffer.g2();
			if (anInt198 == 65535) {
				anInt198 = -1;
			}
			media_zoom = buffer.g2();
			media_xangle = buffer.g2();
			media_yangle = buffer.g2();
		}
		if (type == 7) {
			obj_counts = new int[height * width];
			h_size_mode = (byte) 3;
			v_size_mode = (byte) 3;
			obj_ids = new int[height * width];
			halignment = buffer.g1();
			font_id = buffer.g2();
			if (font_id == 65535) {
				font_id = -1;
			}
			shaded = buffer.g1() == 1;
			color = buffer.g4();
			anInt1046 = buffer.g2s();
			anInt998 = buffer.g2s();
			int i_16_ = buffer.g1();
			options = new RSString[5];
			if (i_16_ == 1) {
				optionMask |= 1073741824;
			}
			for (int i_17_ = 0; i_17_ < 5; i_17_++) {
				RSString class16 = buffer.gstr();
				if ((class16.length() ^ 0xffffffff) < -1) {
					options[i_17_] = class16;
					optionMask |= 1 << 23 + i_17_;
				}
			}
		}
		if (type == 8) {
			default_text = buffer.gstr();
		}
		if (anInt1038 == 2 || type == 2) {
			targetVerb = buffer.gstr();
			aClass16_1019 = buffer.gstr();
			int i_18_ = buffer.g2() & 0x3f;
			optionMask |= i_18_ << 11;
		}
		if (anInt1038 == 1 || anInt1038 == 4 || anInt1038 == 5 || anInt1038 == 6) {
			optionName = buffer.gstr();
			if ((optionName.length() ^ 0xffffffff) == -1) {
				if (anInt1038 == 1) {
					optionName = StaticMethods.optionOK;
				}
				if (anInt1038 == 4) {
					optionName = StringConstants.optionSelect;
				}
				if (anInt1038 == 5) {
					optionName = StringConstants.optionSelect;
				}
				if (anInt1038 == 6) {
					optionName = Class71_Sub1_Sub1.optionContinue;
				}
			}
		}
		if (anInt1038 == 1 || anInt1038 == 4 || anInt1038 == 5) {
			optionMask |= 4194304;
		}
		if (anInt1038 == 6) {
			optionMask |= 1;
		}

		serverActiveProperties = new ServerActiveProperties(optionMask, -1);
	}

	public void parseVersion15(Packet buffer) {
		version = 15;
		int i_11_ = buffer.g1();
		if (i_11_ == 255) {
			i_11_ = -1;
		}
		newer_interface = true;
		type = buffer.g1();
		if (-1 != ~(128 & type)) {
			type &= 127;
			buffer.gstr();
		}
		content_type = buffer.g2();
		positionX = buffer.g2s();
		positionY = buffer.g2s();
		width = buffer.g2();
		height = buffer.g2();
		h_size_mode = buffer.g1s();
		v_size_mode = buffer.g1s();
		h_pos_mode = buffer.g1s();
		v_pos_mode = buffer.g1s();
		parentId = buffer.g2();
		if (parentId == 65535) {
			parentId = -1;
		} else {
			parentId = parentId + (~0xffff & uid);
		}
		hidden = buffer.g1() == 1;
		if (type == 0) {
			scroll_width = buffer.g2();
			scroll_height = buffer.g2();
			if (i_11_ < 0) {
				noClickThrough = buffer.g1() == 1;
			}
		}
		if (type == 5) {
			graphicid = buffer.g4();
			angle2d = buffer.g2();
			int i = buffer.g1();
			tiling = 0 != (i & 0x2);
			repeat = 0 != (i & 0x1);
			transparency = buffer.g1();
			outline = buffer.g1();
			graphic_shadow = buffer.g4();
			flip_v = buffer.g1() == 1;
			flip_h = buffer.g1() == 1;
			color = buffer.g4();
		}
		if (type == 6) {
			media_type = 1;
			media_id = buffer.g2();
			if (media_id == 65535) {
				media_id = -1;
			}
			media_origin_x = buffer.g2s();
			media_origin_y = buffer.g2s();
			media_xangle = buffer.g2();
			media_yangle = buffer.g2();
			media_zangle = buffer.g2();
			media_zoom = buffer.g2();
			media_animid = buffer.g2();
			if ((media_animid ^ 0xffffffff) == -65536) {
				media_animid = -1;
			}
			media_orthographic = buffer.g1() == 1;
			media_depth_near = (short) buffer.g2s();
			media_depth_scale = (short) buffer.g2s();
			depthmask = buffer.g1() == 1;
			if (h_size_mode != 0) {
				media_viewport_width = buffer.g2();
			}
			if (v_size_mode != 0) {
				media_viewport_height = buffer.g2();
			}
		}
		if (type == 4) {
			font_id = buffer.g2();
			if ((font_id ^ 0xffffffff) == -65536) {
				font_id = -1;
			}
			default_text = buffer.gstr();
			line_height = buffer.g1();
			halignment = buffer.g1();
			v_text_align = buffer.g1();
			shaded = buffer.g1() == 1;
			color = buffer.g4();
			transparency = buffer.g1();
			if (i_11_ >= 0) {
				buffer.g1();
			}
		}
		if (type == 3) {
			color = buffer.g4();
			filled = buffer.g1() == 1;
			transparency = buffer.g1();
		}
		if (type == 9) {
			line_width = buffer.g1();
			color = buffer.g4();
			line_mirrored = buffer.g1() == 1;
		}
		optionMask = buffer.g3();
		int i_28_ = buffer.g1();
		if (i_28_ != 0) {
			int[] anIntArray299 = new int[11];
			byte[] aByteArray263 = new byte[11];

			for (byte[] aByteArray231 = new byte[11]; i_28_ != 0; i_28_ = buffer.g1()) {
				int var5 = (i_28_ >> 4) - 1;
				i_28_ = buffer.g1() | i_28_ << 8;
				i_28_ &= 4095;
				if (4095 == i_28_) {
					anIntArray299[var5] = -1;
				} else {
					anIntArray299[var5] = i_28_;
				}

				aByteArray263[var5] = buffer.g1s();
				aByteArray231[var5] = buffer.g1s();
			}
		}
		opBase = buffer.gstr();
		int actionAmount = buffer.g1();
		int i_31_ = actionAmount & 15;
		if (i_31_ > 0) {
			ops = new RSString[i_31_];
			for (int var8 = 0; var8 < i_31_; var8++) {
				ops[var8] = buffer.gstr();
			}
		}
		int var7 = actionAmount >> 4;
		if (var7 > 0) {
			int var8 = buffer.g1();
			int[] anIntArray249 = new int[var8 + 1];

			for (int var9 = 0; var9 < anIntArray249.length; ++var9) {
				anIntArray249[var9] = -1;
			}

			anIntArray249[var8] = buffer.g2();
		}

		if (1 < var7) {
			int var8 = buffer.g1();
			buffer.g2();
		}
		pauseText = buffer.gstr();
		if (pauseText.equals(RSString.create(""))) {
			pauseText = null;
		}
		dragDeadZone = buffer.g1();
		dragDeadTime = buffer.g1();
		dragRenderBehaviour = buffer.g1() == 1;
		targetVerb = buffer.gstr();
		int var8 = -1;
		if (0 != getTargetMask(optionMask)) {
			var8 = buffer.g2();
			int v34 = buffer.g2();
			if (-65536 == ~var8) {
				var8 = -1;
			}

			if ('\uffff' == v34) {
				v34 = -1;
			}

			int anInt238 = buffer.g2();
			if (anInt238 == '\uffff') {
				anInt238 = -1;
			}
		}
		if (i_11_ >= 0) {
			buffer.g2();
		}
		serverActiveProperties = new ServerActiveProperties(optionMask, var8);
		if (i_11_ >= 0) {
			int i_27_ = buffer.g1();
			for (int v98 = 0; v98 < i_27_; v98++) {
				int i_63_ = buffer.g3();
				int i_64_ = buffer.g4();
				params.put(i_63_, new IntegerNode(i_64_));
			}
			int v86 = buffer.g1();
			for (int i_32_ = 0; i_32_ < v86; i_32_++) {
				int i_67_ = buffer.g3();
				RSString string = buffer.gjstr2();
				params.put(i_67_, new StringNode(string));
			}
		}
		onStartListener = decodeComponentHook(buffer);
		mouse_enter_handler = decodeComponentHook(buffer);
		mouse_exit_handler = decodeComponentHook(buffer);
		spellUsedListener = decodeComponentHook(buffer);
		spellUsedOnItemListener = decodeComponentHook(buffer);
		varpUpdateListener = decodeComponentHook(buffer);
		inventoryUpdateListener = decodeComponentHook(buffer);
		skillUpdateListener = decodeComponentHook(buffer);
		mainLoopListener = decodeComponentHook(buffer);
		anObjectArray1116 = decodeComponentHook(buffer);
		if (i_11_ >= 0) {
			decodeComponentHook(buffer);
		}
		onMouseRepeatHook = decodeComponentHook(buffer);
		mouse_pressed_handler = decodeComponentHook(buffer);
		mouse_dragged_handler = decodeComponentHook(buffer);
		mouse_released_handler = decodeComponentHook(buffer);
		mouse_drag_pass_handler = decodeComponentHook(buffer);
		anObjectArray1053 = decodeComponentHook(buffer);
		anObjectArray1066 = decodeComponentHook(buffer);
		mouseWheelListener = decodeComponentHook(buffer);
		anObjectArray161 = decodeComponentHook(buffer);
		anObjectArray162 = decodeComponentHook(buffer);
		varpListenerValues = decodeTransmitList(buffer, -496);
		inventoryListenerValues = decodeTransmitList(buffer, -496);
		statTransmitList = decodeTransmitList(buffer, -496);
		anIntArray211 = decodeTransmitList(buffer, -496);
		anIntArray212 = decodeTransmitList(buffer, -496);
	}

	public void parseVersion20(Packet buffer) {
		version = 20;
		newer_interface = true;
		int format = buffer.g1();
		if (format == 255) {
			format = -1;
		}
		type = buffer.g1();
		if (-1 != ~(128 & type)) {
			type &= 127;
			buffer.gstr();
		}
		content_type = buffer.g2();
		positionX = buffer.g2s();
		positionY = buffer.g2s();
		width = buffer.g2();
		height = buffer.g2();
		h_size_mode = buffer.g1s();
		v_size_mode = buffer.g1s();
		h_pos_mode = buffer.g1s();
		v_pos_mode = buffer.g1s();
		parentId = buffer.g2();
		if (parentId == 65535) {
			parentId = -1;
		} else {
			parentId = parentId + (~0xffff & uid);
		}
		int settings = buffer.g1();
		hidden = 0 != (settings & 0x1);
		if (format >= 0) {
			noClickThrough = 0 != (settings & 0x2);
		}
		if (type == 0) {
			scroll_width = buffer.g2();
			scroll_height = buffer.g2();
			if (format < 0) {
				noClickThrough = buffer.g1() == 1;
			}
		}
		if (type == 5) {
			graphicid = buffer.g4();
			angle2d = buffer.g2();
			int i = buffer.g1();
			tiling = 0 != (i & 0x2);
			repeat = 0 != (i & 0x1);
			transparency = buffer.g1();
			outline = buffer.g1();
			graphic_shadow = buffer.g4();
			flip_v = buffer.g1() == 1;
			flip_h = buffer.g1() == 1;
			color = buffer.g4();
			if (format >= 3) {
				clickMask = buffer.g1() == 1;
			}
		}
		if (type == 6) {
			media_type = 1;
			media_id = buffer.g2();
			if (media_id == 65535) {
				media_id = -1;
			}
			int sceneFlag = buffer.g1();
			boolean view2d = (sceneFlag & 0x1) == 1;
			overview = 2 == (sceneFlag & 0x2);
			media_orthographic = 4 == (sceneFlag & 0x4);
			depthmask = (sceneFlag & 0x8) == 8;
			if (view2d) {
				media_origin_x = buffer.g2s();
				media_origin_y = buffer.g2s();
				media_xangle = buffer.g2();
				media_yangle = buffer.g2();
				media_zangle = buffer.g2();
				media_zoom = buffer.g2();
			} else if (overview) {
				media_origin_x = buffer.g2s();
				media_origin_y = buffer.g2s();
				media_origin_z = buffer.g2s();
				media_xangle = buffer.g2();
				media_yangle = buffer.g2();
				media_zangle = buffer.g2();
				media_zoom = buffer.g2s();
			}
			media_animid = buffer.g2();
			if (media_animid == 65535) {
				media_animid = -1;
			}
			if (h_size_mode != 0) {
				media_viewport_width = buffer.g2();
			}
			if (v_size_mode != 0) {
				media_viewport_height = buffer.g2();
			}
		}
		if (type == 4) {
			font_id = buffer.g2();
			if ((font_id ^ 0xffffffff) == -65536) {
				font_id = -1;
			}
			default_text = buffer.gstr();
			line_height = buffer.g1();
			halignment = buffer.g1();
			v_text_align = buffer.g1();
			shaded = buffer.g1() == 1;
			color = buffer.g4();
			transparency = buffer.g1();
			if (format >= 0) {
				maxLines = buffer.g1();
			}
		}
		if (type == 3) {
			color = buffer.g4();
			filled = buffer.g1() == 1;
			transparency = buffer.g1();
		}
		if (type == 9) {
			line_width = buffer.g1();
			color = buffer.g4();
			line_mirrored = buffer.g1() == 1;
		}
		optionMask = buffer.g3();
		int rate = buffer.g1();
		if (0 != rate) {
			opKeys = new byte[11][];
			opKeyMasks = new byte[11][];
			opKeyRates = new int[11];
			opChars = new int[11];
			for (; rate != 0; rate = buffer.g1()) {
				int index = (rate >> 4) - 1;
				rate = rate << 8 | buffer.g1();
				rate &= 0xfff;
				if (4095 == rate) {
					rate = -1;
				}
				byte key = buffer.g1s();
				if (0 != key) {
					hasKeyEvents = true;
				}
				byte mask = buffer.g1s();
				opKeyRates[index] = rate;
				opKeys[index] = new byte[] { key };
				opKeyMasks[index] = new byte[] { mask };
			}
		}
		opBase = buffer.gstr();
		int menuMask = buffer.g1();
		int menuOptionsCount = menuMask & 0xf;
		int menuCursorMask = menuMask >> 4;
		if (menuOptionsCount > 0) {
			ops = new RSString[menuOptionsCount];
			for (int option = 0; option < menuOptionsCount; option++) {
				ops[option] = buffer.gstr();
			}
		}
		if (menuCursorMask > 0) {
			int option = buffer.g1();
			opCursors = new int[1 + option];
			for (int index = 0; index < opCursors.length; index++) {
				opCursors[index] = -1;
			}
			opCursors[option] = buffer.g2();
		}
		if (menuCursorMask > 1) {
			int option = buffer.g1();
			opCursors[option] = buffer.g2();
		}
		pauseText = buffer.gstr();
		if (pauseText.equals(RSString.create(""))) {
			pauseText = null;
		}
		dragDeadZone = buffer.g1();
		dragDeadTime = buffer.g1();
		dragRenderBehaviour = buffer.g1() == 1;
		targetVerb = buffer.gstr();
		int mask = -1;
		if (0 != getTargetMask(optionMask)) {
			mask = buffer.g2();
			if (65535 == mask) {
				mask = -1;
			}
			targetOverCursor = buffer.g2();
			if (targetOverCursor == 65535) {
				targetOverCursor = -1;
			}
			targetLeaveCursor = buffer.g2();
			if (65535 == targetLeaveCursor) {
				targetLeaveCursor = -1;
			}
		}
		if (format >= 0) {
			mouseOverCursor = buffer.g2();
			if (65535 == mouseOverCursor) {
				mouseOverCursor = -1;
			}
		}
		serverActiveProperties = new ServerActiveProperties(optionMask, mask);
		if (format >= 0) {
			int i_27_ = buffer.g1();
			for (int v98 = 0; v98 < i_27_; v98++) {
				int i_63_ = buffer.g3();
				int i_64_ = buffer.g4();
				params.put(i_63_, new IntegerNode(i_64_));
			}
			int v86 = buffer.g1();
			for (int i_32_ = 0; i_32_ < v86; i_32_++) {
				int i_67_ = buffer.g3();
				RSString string = buffer.gjstr2();
				params.put(i_67_, new StringNode(string));
			}
		}
		onStartListener = decodeComponentHook(buffer);
		mouse_enter_handler = decodeComponentHook(buffer);
		mouse_exit_handler = decodeComponentHook(buffer);
		spellUsedListener = decodeComponentHook(buffer);
		spellUsedOnItemListener = decodeComponentHook(buffer);
		varpUpdateListener = decodeComponentHook(buffer);
		inventoryUpdateListener = decodeComponentHook(buffer);
		skillUpdateListener = decodeComponentHook(buffer);
		mainLoopListener = decodeComponentHook(buffer);
		anObjectArray1116 = decodeComponentHook(buffer);
		if (format >= 0) {
			onUseOnObjHook = decodeComponentHook(buffer);
		}
		onMouseRepeatHook = decodeComponentHook(buffer);
		mouse_pressed_handler = decodeComponentHook(buffer);
		mouse_dragged_handler = decodeComponentHook(buffer);
		mouse_released_handler = decodeComponentHook(buffer);
		mouse_drag_pass_handler = decodeComponentHook(buffer);
		anObjectArray1053 = decodeComponentHook(buffer);
		anObjectArray1066 = decodeComponentHook(buffer);
		mouseWheelListener = decodeComponentHook(buffer);
		anObjectArray161 = decodeComponentHook(buffer);
		anObjectArray162 = decodeComponentHook(buffer);
		varpListenerValues = decodeTransmitList(buffer, -496);
		inventoryListenerValues = decodeTransmitList(buffer, -496);
		statTransmitList = decodeTransmitList(buffer, -496);
		anIntArray211 = decodeTransmitList(buffer, -496);
		anIntArray212 = decodeTransmitList(buffer, -496);
	}

	static final boolean isComponentEnabled(boolean bool, RSInterface inter) {
		if (bool != true) {
			return true;
		}
		if (inter.anIntArray1114 == null) {
			return false;
		}
		for (int i = 0; (inter.anIntArray1114.length ^ 0xffffffff) < (i ^ 0xffffffff); i++) {
			int i_13_ = GameTimer.method192(-26, i, inter);
			int i_14_ = inter.anIntArray1010[i];
			if (inter.anIntArray1114[i] == 2) {
				if (i_13_ >= i_14_) {
					return false;
				}
			} else if (inter.anIntArray1114[i] != 3) {
				if (inter.anIntArray1114[i] == 4) {
					if ((i_14_ ^ 0xffffffff) == (i_13_ ^ 0xffffffff)) {
						return false;
					}
				} else if (i_14_ != i_13_) {
					return false;
				}
			} else if ((i_14_ ^ 0xffffffff) <= (i_13_ ^ 0xffffffff)) {
				return false;
			}
		}
		return true;
	}

	static final RSInterface getInterface(int interfaceHash) {
		int childId = 0xffff & interfaceHash;
		int interfaceId = interfaceHash >> 16;
		// if(interfaceId != 548)
		// System.out.println(interfaceId);
		if (StaticMethods.cached_interfaces.length <= interfaceId || interfaceId < 0) {
			return null;
		}
		try {
			if (StaticMethods.cached_interfaces[interfaceId] == null || StaticMethods.cached_interfaces[interfaceId].length <= childId || StaticMethods.cached_interfaces[interfaceId][childId] == null) {
				boolean bool = AbstractTimer.hasActiveInterface(-10924, interfaceId);
				if (!bool) {
					return null;
				}
			}
			if (StaticMethods.cached_interfaces[interfaceId].length <= childId) {
				return null;
			}
			return StaticMethods.cached_interfaces[interfaceId][childId];
		} catch (Throwable t) {
			int childLength = 0;
			if (interfaceId < StaticMethods.cached_interfaces.length && interfaceId > 0) {
				childLength = StaticMethods.cached_interfaces[interfaceId].length;
			}
			System.out.println("Interface packet error - [inter=" + interfaceId + ", child=" + childId + ", max=" + StaticMethods.cached_interfaces.length + ", max_child=" + childLength + "].");
			t.printStackTrace();
		}
		return null;
	}

	public static final RSInterface method281(byte b, RSInterface class64) {
		int i = Class95.method1470((byte) -53, Class47.getOptionMask(class64, 103).anInt2452);
		if ((i ^ 0xffffffff) == -1) {
			return null;
		}
		for (int i_11_ = 0; i_11_ < i; i_11_++) {
			class64 = getInterface(class64.parentId);
			if (class64 == null) {
				return null;
			}
		}
		return class64;
	}

	final void switchItem(int i, int index, int otherIndex) {
		int change = obj_ids[otherIndex];
		obj_ids[otherIndex] = obj_ids[index];
		obj_ids[index] = change;
		change = obj_counts[otherIndex];
		if (i < 10) {
			mouse_exit_handler = null;
		}
		obj_counts[otherIndex] = obj_counts[index];
		obj_counts[index] = change;
	}

	public static void method1219(int i) {
		if (i != -21558) {
			method1219(113);
		}
		aClass64_1102 = null;
		aClass16_1016 = null;
		aClass16_1139 = null;
		aClass16_1136 = null;
		aClass16_1146 = null;
	}

	final void set_menu_action(int i, int i_19_, RSString class16) {
		if (ops == null || (ops.length ^ 0xffffffff) >= (i ^ 0xffffffff)) {
			RSString[] class16s = new RSString[i - -1];
			if (ops != null) {
				for (int i_20_ = 0; ops.length > i_20_; i_20_++) {
					class16s[i_20_] = ops[i_20_];
				}
			}
			ops = class16s;
		}
		ops[i] = class16;
		if (i_19_ != 0) {
			method1225(-13);
		}
	}

	public Sprite get_sprite(boolean enabled) {
		int graphic_id;
		if (version == 10) {
			if (enabled) {
				graphic_id = enabled_graphic;
			} else {
				graphic_id = graphicid;
			}
		} else {
			graphic_id = graphicid;
		}
		Class4.fetching_sprites = false;
		if (graphic_id == -1) {
			return null;
		}
		long l = ((long) graphic_shadow << 40) + ((flip_v ? 1L : 0L) << 38) + ((tiling ? 1L : 0L) << 35) + graphic_id + (((long) outline << 36) + ((!flip_h ? 0L : 1L) << 39));
		Sprite sprite = (Sprite) Class33.cached_sprites.get(l);
		if (sprite != null) {
			return sprite;
		}
		SoftwareSprite software_sprite;
		if (tiling || newer_interface) {
			software_sprite = Sprite.load_software_alpha(RSString.sprites_js5, graphic_id, 0);
		} else {
			software_sprite = Sprite.load_software(RSString.sprites_js5, graphic_id, 0);
		}
		if (software_sprite == null) {
			if (DebugMissing.ENABLED && !RSString.sprites_js5.is_valid_group(graphic_id)) {
				DebugMissing.notify_sprite(graphic_id);
			}
			Class4.fetching_sprites = true;
			return null;
		}
		if (flip_v) {
			software_sprite.flip_vertically();
		}
		if (flip_h) {
			software_sprite.flip_horizontally();
		}
		if (outline > 0) {
			software_sprite.add_outline_size(outline);
		}
		if (outline >= 1) {
			software_sprite.add_outline_colour(1);
		}
		if (outline >= 2) {
			software_sprite.add_outline_colour(16777215);
		}
		if (graphic_shadow != 0) {
			software_sprite.add_shadow(graphic_shadow);
		}
		if (GLManager.opengl_mode) {
			if (software_sprite instanceof SoftwareAlphaSprite) {
				sprite = new OpenGLAlphaSprite(software_sprite);
			} else {
				sprite = new OpenGLSprite(software_sprite);
			}
		} else {
			sprite = software_sprite;
		}
		Class33.cached_sprites.put(l, sprite);
		return sprite;
	}

	final boolean method1222(int i) {
		if (i <= 88) {
			anInt1030 = 4;
		}
		if (anIntArray1064 != null) {
			return true;
		}
		SoftwarePaletteSprite indexedSprite = Class91.method1454(RSString.sprites_js5, 0, graphicid, (byte) 92);
		if (indexedSprite == null) {
			return false;
		}
		// indexedSprite.method1123();
		anIntArray1064 = new int[indexedSprite.height];
		anIntArray1109 = new int[indexedSprite.height];
		for (int i_23_ = 0; (indexedSprite.height ^ 0xffffffff) < (i_23_ ^ 0xffffffff); i_23_++) {
			int i_24_ = indexedSprite.width;
			int i_25_ = 0;
			for (int i_26_ = 0; i_26_ < indexedSprite.width; i_26_++) {
				if ((indexedSprite.pixels[i_23_ * indexedSprite.width + i_26_] ^ 0xffffffff) != -1) {
					i_25_ = i_26_;
					break;
				}
			}
			for (int i_27_ = i_25_; indexedSprite.width > i_27_; i_27_++) {
				if ((indexedSprite.pixels[indexedSprite.width * i_23_ + i_27_] ^ 0xffffffff) == -1) {
					i_24_ = i_27_;
					break;
				}
			}
			anIntArray1064[i_23_] = i_25_;
			anIntArray1109[i_23_] = i_24_ - i_25_;
		}
		return true;
	}

	final Font getRSFont(PaletteSprite[] class43s, byte b) {
		Class4.fetching_sprites = false;
		anInt1079++;
		if (font_id == -1) {
			return null;
		}
		Font class23_sub13_sub8 = (Font) Class98.aJList_1654.get(font_id);
		if (class23_sub13_sub8 != null) {
			return class23_sub13_sub8;
		}
		class23_sub13_sub8 = FontCache.load_font(AbstractTimer.aClass105_313, RSString.sprites_js5, font_id, 0);
		if (class23_sub13_sub8 != null) {
			class23_sub13_sub8.set_glyphs(class43s, null);
			Class98.aJList_1654.put(font_id, class23_sub13_sub8);
		} else {
			DebugMissing.notify_font(font_id);
			Class4.fetching_sprites = true;
		}
		return class23_sub13_sub8;
	}

	static final int getTargetMask(int var1) {
		return 127 & var1 >> 11;
	}

	final Sprite method1225(int i) {
		Class4.fetching_sprites = false;
		if ((i ^ 0xffffffff) > -1 || (i ^ 0xffffffff) <= (anIntArray1042.length ^ 0xffffffff)) {
			return null;
		}
		int i_30_ = anIntArray1042[i];
		if (i_30_ == -1) {
			return null;
		}
		Sprite sprite = (Sprite) Class33.cached_sprites.get(i_30_);
		if (sprite != null) {
			return sprite;
		}
		sprite = Sprite.load(RSString.sprites_js5, i_30_, 0);
		if (sprite != null) {
			Class33.cached_sprites.put(i_30_, sprite);
		} else {
			Class4.fetching_sprites = true;
		}
		return sprite;
	}

	final Model getAnimatedInterfaceModel(SeqType seq, int current_frameid, int next_frameid, int tick, boolean enabled, PlayerAppearance playerAppearance) {
		anInt1080++;
		Class4.fetching_sprites = false;
		int m_type;
		int m_id;
		if (version == 10) {
			if (!enabled) {
				m_type = media_type;
				m_id = media_id;
			} else {
				m_type = modelType;
				m_id = anInt196;
			}
		} else {
			m_type = media_type;
			m_id = media_id;
		}

		if (m_type == 0) {
			return null;
		}
		if (m_type == 1 && m_id == -1) {
			return null;
		}
		if (m_type == 1) {
			Model model = (Model) AbstractMouseWheel.aModelList_1166.get((m_type << 24) + m_id);
			if (model == null) {
				Mesh class38_sub4 = Mesh.fromJs5(Class61.aClass105_958, m_id, 0);
				if (class38_sub4 == null) {
					Class4.fetching_sprites = true;
					return null;
				}
				model = class38_sub4.method2008(64, 768, -50, -10, -50);
				AbstractMouseWheel.aModelList_1166.put(m_id + (m_type << 24), model);
			}
			if (seq != null) {
				model = seq.get_animated_dialoghead(model, current_frameid, next_frameid, tick);
			}
			return model;
		}
		if (m_type == 2) {
			Model model = NPCType.getNPCDefinition(m_id).get_dialogue_model(seq, current_frameid, next_frameid, tick);
			if (model == null) {
				Class4.fetching_sprites = true;
				return null;
			}
			return model;
		}
		if (m_type == 3) {
			if (playerAppearance == null) {
				return null;
			}
			Model model = playerAppearance.method1160(seq, current_frameid, next_frameid, tick);
			if (model == null) {
				Class4.fetching_sprites = true;
				return null;
			}
			return model;
		}
		if (m_type == 4) {
			ObjType itemDef = ObjTypeList.list(m_id);
			Model model = itemDef.get_dialog_model(seq, current_frameid, next_frameid, tick, 10);
			if (model == null) {
				Class4.fetching_sprites = true;
				return null;
			}
			return model;
		}
		if (m_type == 6) {
			Model model = NPCType.getNPCDefinition(m_id).method1476(null, null, seq, 0, current_frameid, 0, next_frameid, 0, tick);
			if (model == null) {
				Class4.fetching_sprites = true;
				return null;
			}
			return model;
		}
		return null;
	}

	public final int[] decodeTransmitList(Packet class23_sub5, int i) {
		int i_34_ = class23_sub5.g1();
		if (i_34_ == 0) {
			return null;
		}
		int[] is = new int[i_34_];
		for (int i_35_ = 0; i_34_ > i_35_; i_35_++) {
			is[i_35_] = class23_sub5.g4();
		}
		return is;
	}

	public final Object[] decodeComponentHook(Packet buffer) {
		int num_arguments = buffer.g1();
		if (num_arguments == 0) {
			return null;
		}
		Object[] arguments = new Object[num_arguments];
		for (int index = 0; index < num_arguments; index++) {
			int type = buffer.g1();
			if (type == 1) {
				arguments[index] = buffer.gstr();
			} else {
				arguments[index] = new Integer(buffer.g4());
			}
		}
		has_hooks = true;
		return arguments;
	}

	public RSInterface() {
		scroll_x = 0;
		timestamp_last_process = -1;
		media_origin_y = 0;
		aBoolean1054 = true;
		newer_interface = false;
		optionMask = 0;
		media_tween_tick = 0;
		line_width = 1;
		angle2d = 0;
		media_origin_x = 0;
		aClass16_1019 = Huffman.aClass16_1828;
		media_zoom = 100;
		dragRenderBehaviour = false;
		anInt1039 = 0;
		anInt284 = 0;
		anInt285 = 0;
		color = 0;
		anInt998 = 0;
		scroll_y = 0;
		graphic_shadow = 0;
		anInt1067 = 0;
		filled = false;
		targetVerb = Huffman.aClass16_1828;
		media_id = -1;
		shaded = false;
		v_text_align = 0;
		positionY = 0;
		aBoolean1047 = false;
		aClass64_1121 = null;
		media_type = 1;
		height = 0;
		componentIndex = -1;
		anInt1078 = -1;
		anInt196 = -1;
		repeat = false;
		anInt1106 = 0;
		parentId = -1;
		anInt1027 = -1;
		layout_x = 0;
		font_id = -1;
		media_viewport_width = 0;
		scroll_width = 0;
		positionX = 0;
		anInt198 = -1;
		layout_y = 0;
		h_size_mode = (byte) 0;
		line_mirrored = false;
		mouse_over = false;
		blackColor = 0;
		outline = 0;
		graphicid = -1;
		uid = -1;
		shownItemAmount = 0;
		anInt1094 = 0;
		media_current_frameid = 0;
		dragDeadTime = 0;
		v_size_mode = (byte) 0;
		media_zangle = 0;
		has_hooks = false;
		media_viewport_height = 0;
		aClass16_1095 = Huffman.aClass16_1828;
		scroll_height = 0;
		dragDeadZone = 0;
		anInt1046 = 0;
		anInt1144 = -1;
		media_xangle = 0;
		media_yangle = 0;
		textColor = 0;
		line_height = 0;
		optionName = StaticMethods.optionOK;
		pauseText = null;
		enabled_graphic = -1;
		anInt1038 = 0;
		v_pos_mode = (byte) 0;
		layout_width = 0;
		default_text = Huffman.aClass16_1828;
		anInt1090 = 0;
		opBase = Huffman.aClass16_1828;
		objid = -1;
		anInt264 = 0;
		hidden = false;
		transparency = 0;
		layout_height = 0;
		halignment = 0;
		modelType = 1;
		content_type = 0;
		width = 0;
		noClickThrough = false;
		h_pos_mode = (byte) 0;
		obj_stack_mode = 2;
	}

	public Object[] anObjectArray1604;

	public HashTable params;

	public final void set_param(int param_id, int default_val) {
		if (params == null) {
			params = new HashTable(16);
			params.put(param_id, new IntegerNode(default_val));
		} else {
			IntegerNode var_integerNode = (IntegerNode) params.get(param_id);
			do {
				if (var_integerNode == null) {
					params.put(param_id, new IntegerNode(default_val));
					break;
				}
				var_integerNode.value = default_val;
			} while (false);
		}
	}

	public final void set_param(int id, RSString value) {
		if (params == null) {
			params = new HashTable(16);
			params.put(id, new StringNode(value));
		} else {
			StringNode var_stringNode = (StringNode) params.get(id);
			do {
				if (var_stringNode != null) {
					var_stringNode.value = value;
					break;
				}
				params.put(id, new StringNode(value));
			} while (false);
		}
	}

	public final void remove_param(int id) {
		if (params != null) {
			Linkable node = params.get(id);
			if (node != null) {
				node.unlink();
			}
		}
	}

	public final int get_param(int id, int defaultval) {
		if (params == null) {
			return defaultval;
		}
		IntegerNode var_integerNode = (IntegerNode) params.get(id);
		if (var_integerNode == null) {
			return defaultval;
		}
		return var_integerNode.value;
	}

	public final RSString get_param(int id, RSString defaultval) {
		if (params == null) {
			return defaultval;
		}
		StringNode var_stringNode = (StringNode) params.get(id);
		if (var_stringNode == null) {
			return defaultval;
		}
		return var_stringNode.value;
	}

	static {
		aClass16_1146 = RSString.createString("Diese Betatest)2Welt ist nur f-Ur eingeladene");
		aClass16_1139 = aClass16_1016;
		aClass16_1136 = RSString.createString("leuchten1:");
		anInt1082 = -1;
	}
}
