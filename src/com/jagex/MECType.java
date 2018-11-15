package com.jagex;

import com.jagex.graphics.runetek4.media.Sprite;

public class MECType {

	public static final int FONT_SMALL = 0;
	public static final int FONT_MEDIUM = 1;
	public static final int FONT_LARGE = 2;

	public MECTypeList loader;
	public int id;
	public int graphicId;
	public int graphicOverId;
	public RSString text;
	public int textColor;
	public int textSize;

	public MECType() {
		graphicId = -1;
		graphicOverId = -1;
		textSize = 0;
	}

	public void decode(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			decode(buffer, opcode);
		}
	}

	public void decode(Packet packet, int opcode) {
		if (opcode == 1) {
			graphicId = packet.g2();
		} else if (opcode == 2) {
			graphicOverId = packet.g2();
		} else if (opcode == 3) {
			text = packet.gstr();
		} else if (opcode == 4) {
			textColor = packet.g3();
		} else if (opcode == 5) {
			packet.g3();
		} else if (6 == opcode) {
			textSize = packet.g1();
		} else if (opcode == 7) {
			packet.g1();
		} else if (opcode == 8) {
			packet.g1();
		} else if (9 == opcode) {
			packet.g2();
			packet.g2();
			packet.g4();
			packet.g4();
		} else if (opcode >= 10 && opcode <= 14) {
			packet.gstr();
		} else if (15 == opcode) {
			int i_11_ = packet.g1();
			for (int i_12_ = 0; i_12_ < i_11_ * 2; i_12_++) {
				packet.g2s();
			}
			packet.g4();
			int i_13_ = packet.g1();
			for (int i_14_ = 0; i_14_ < i_13_; i_14_++) {
				packet.g4();
			}
			for (int i_15_ = 0; i_15_ < i_11_; i_15_++) {
				packet.g1s();
			}
		} else if (opcode == 16) {
			// removed
		} else if (17 == opcode) {
			packet.gstr();
		} else if (opcode == 18) {
			packet.g2();
		} else if (opcode == 19) {
			packet.g2();
		} else if (opcode == 20) {
			packet.g2();
			packet.g2();
			packet.g4();
			packet.g4();
		} else if (21 == opcode) {
			packet.g4();
		} else if (opcode == 22) {
			packet.g4();
		} else if (opcode == 23) {
			packet.g1();
			packet.g1();
			packet.g1();
		} else if (opcode == 24) {
			packet.g2s();
			packet.g2s();
		} else if (opcode == 249) {
			int i_16_ = packet.g1();
			for (int i_18_ = 0; i_18_ < i_16_; i_18_++) {
				boolean stringValue = packet.g1() == 1;
				packet.g3();
				if (stringValue) {
					packet.gstr();
				} else {
					packet.g4();
				}
			}
		}
	}

	public Sprite method3800(boolean bool) {
		int graphicid = bool ? graphicOverId : graphicId;
		Sprite sprite = (Sprite) MECTypeList.sprites_cache.get(graphicid);
		if (sprite != null) {
			return sprite;
		}
		if (!CacheConstants.sprites_js5.is_group_cached(graphicid)) {
			return null;
		}
		sprite = Sprite.load(CacheConstants.sprites_js5, graphicid, 0);
		if (sprite != null) {
			MECTypeList.sprites_cache.put(graphicid, sprite);
		}
		return sprite;
	}

}
