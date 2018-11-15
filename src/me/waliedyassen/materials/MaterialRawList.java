package me.waliedyassen.materials;

import com.jagex.DebugMissing;
import com.jagex.Js5;
import com.jagex.Packet;

import me.waliedyassen.graphics.rasterizer.AlphaMode;

public class MaterialRawList {

	public final int num_materials;
	public final Js5 materials_js5;
	public final MaterialRaw[] materials;

	public MaterialRawList(Js5 materials_js5) {
		this.materials_js5 = materials_js5;
		Packet buffer = new Packet(materials_js5.get_file(0, 0));
		num_materials = buffer.g2();
		System.out.println("Loading " + num_materials + " material(s)..");
		materials = new MaterialRaw[num_materials];
		for (int id = 0; id < num_materials; id++) {
			if (buffer.g1() == 1) {
				materials[id] = new MaterialRaw();
				materials[id].id = id;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].details_only = buffer.g1() == 0;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].is_small = buffer.g1() == 1;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				buffer.g1();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].brightness = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].intensity = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].effect_type = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].effect_param1 = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].colour = (short) buffer.g2();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].speed_u = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].speed_v = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].terrains_only = buffer.g1() == 1;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].fliped = buffer.g1() == 1;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].mipmapping = buffer.g1s();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].repeat_s = buffer.g1() == 1;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].repeat_t = buffer.g1() == 1;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].hdr = buffer.g1() == 1;
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].effect_combiner = (byte) buffer.g1();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].effect_param2 = buffer.g4();
			}
		}
		for (int id = 0; id < num_materials; id++) {
			if (materials[id] != null) {
				materials[id].alpha_mode = AlphaMode.values()[buffer.g1()];
			}
		}
	}

	public MaterialRaw get_material(int id) {
		if (id >= materials.length) {
			DebugMissing.notify_material(id);
			id = 0;
		}
		return materials[id];
	}

	public int get_materials_count() {
		return num_materials;
	}

}
