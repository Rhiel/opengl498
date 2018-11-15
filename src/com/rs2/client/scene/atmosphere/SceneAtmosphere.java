package com.rs2.client.scene.atmosphere;

import com.jagex.Packet;
import com.rs2.client.scene.SceneDefaults;

public final class SceneAtmosphere {

	public int sun_colour;
	public float sun_intensity;
	public float sun_angle_x;
	public float sun_angle_y;
	public int sun_position_x;
	public int sun_position_z;
	public int sun_position_y;
	public int fog_colour;
	public int fog_density;

	public SceneAtmosphere() {
		sun_colour = SceneDefaults.default_sun_colour;
		sun_intensity = SceneDefaults.default_sun_intensity;
		sun_angle_x = SceneDefaults.default_sun_angle_x;
		sun_angle_y = SceneDefaults.default_sun_angle_y;
		sun_position_x = SceneDefaults.default_sun_position_x;
		sun_position_y = SceneDefaults.default_sun_position_y;
		sun_position_z = SceneDefaults.default_sun_position_z;
		fog_colour = SceneDefaults.default_fog_colour;
		fog_density = SceneDefaults.default_fog_density;
	}

	public SceneAtmosphere(Packet buffer) {
		int flag = buffer.g1();
		if ((flag & 0x1) != 0) {
			sun_colour = buffer.g4();
		} else {
			sun_colour = SceneDefaults.default_sun_colour;
		}
		if ((flag & 0x2) != 0) {
			sun_intensity = buffer.g2() / 256.0F;
		} else {
			sun_intensity = SceneDefaults.default_sun_intensity;
		}
		if ((flag & 0x4) != 0) {
			sun_angle_x = buffer.g2() / 256.0F;
		} else {
			sun_angle_x = SceneDefaults.default_sun_angle_x;
		}
		if ((flag & 0x8) != 0) {
			sun_angle_y = buffer.g2() / 256.0F;
		} else {
			sun_angle_y = SceneDefaults.default_sun_angle_y;
		}
		if ((flag & 0x10) != 0) {
			sun_position_x = buffer.g2s();
			sun_position_y = buffer.g2s();
			sun_position_z = buffer.g2s();
		} else {
			sun_position_x = SceneDefaults.default_sun_position_x;
			sun_position_y = SceneDefaults.default_sun_position_y;
			sun_position_z = SceneDefaults.default_sun_position_z;
		}
		if ((flag & 0x20) != 0) {
			fog_colour = buffer.g4();
		} else {
			fog_colour = SceneDefaults.default_fog_colour;
		}
		if ((flag & 0x40) != 0) {
			fog_density = buffer.g2();
		} else {
			fog_density = SceneDefaults.default_fog_density;
		}
	}

	public void decode_hdr(Packet map_buffer) {
		float bloom = map_buffer.g1() * 8 / 255.0F;
		float brightpass = map_buffer.g1() * 8 / 255.0F;
		float whitepoint = map_buffer.g1() * 8 / 255.0F;
	}

	public void decode_skybox(Packet map_buffer) {
		int skybox_id = map_buffer.g2();
		int i_9_ = map_buffer.g2s();
		int i_10_ = map_buffer.g2s();
		int i_11_ = map_buffer.g2s();
		int i_12_ = map_buffer.g2();
	}
}
