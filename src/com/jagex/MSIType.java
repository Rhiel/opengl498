package com.jagex;

import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public class MSIType extends Queuable {

	public int graphic = -1;
	public int colour;
	public boolean enlarge;

	public void decode(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			decode(buffer, opcode);
		}
	}

	public void decode(Packet buffer, int opcode) {
		if (1 == opcode) {
			graphic = buffer.g2();
		} else if (opcode == 2) {
			colour = buffer.g3();
		} else if (3 == opcode) {
			enlarge = true;
		} else if (4 == opcode) {
			graphic = -1;
		}
	}

	public SoftwarePaletteSprite get_sprite(int var1) {
		SoftwarePaletteSprite sprite = (SoftwarePaletteSprite) MSITypeList.sprites_cache.get(var1 << 16 | graphic);
		if (sprite != null) {
			return sprite;
		}
		CacheConstants.sprites_js5.is_group_cached(graphic);
		sprite = PaletteSprite.load_software(CacheConstants.sprites_js5, graphic, 0);
		if (sprite != null) {
			sprite.trim_width = sprite.width;
			sprite.trim_height = sprite.height;
			for (int var5 = 0; ~var1 < ~var5; ++var5) {
				sprite.explode_size();
			}
			MSITypeList.sprites_cache.put(var1 << 16 | graphic, sprite);
		}
		return sprite;
	}

}
