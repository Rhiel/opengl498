package me.waliedyassen.materials;

import me.waliedyassen.graphics.rasterizer.AlphaMode;

/**
 * @author Walied K. Yassen
 */
public class MaterialRaw {

	public int id;
	public boolean is_small;
	public byte speed_u;
	public byte speed_v;
	public boolean hdr;
	public boolean repeat_s;
	public boolean repeat_t;
	public byte effect_type;
	public byte effect_param1;
	public int effect_param2;
	public byte mipmapping;
	public boolean terrains_only;
	public boolean details_only;
	public byte brightness;
	public byte intensity;
	public byte effect_combiner;
	public short colour;
	public boolean fliped;
	public AlphaMode alpha_mode;

	public int get_id() {
		return id;
	}

	public int get_size() {
		return is_small ? 64 : 128;
	}

	public int get_colour() {
		return colour & 0xffff;
	}

	public boolean get_repeat() {
		return repeat_s || repeat_t;
	}

	public AlphaMode get_alphamode() {
		return alpha_mode;
	}

	public boolean is_alpha_opaque() {
		return alpha_mode != AlphaMode.ALPHA_TESTED;
	}

}
