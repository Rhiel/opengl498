package com.jagex;

import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;

public class SeqType extends Queuable {

	public int[] frames_data;
	public int[] frames_durations;
	public int[][] sounds;
	public int main_hand_obj;
	public boolean lights;
	public int off_hand_obj = -1;
	public int[] interface_frames;
	public int[] sound_adjusts;
	public int[] sound_offsets;
	public int[] sound_offsets2;
	public int priority;
	public int replay_count;
	public int move_type;
	public int replay_interval;
	public int interrupt_type;
	public int loop_type;
	public boolean[] interleave_order;
	public boolean aBoolean1859;
	public boolean vorbis;
	public int id;
	public boolean tween;
	public boolean aBoolean1872;

	public SeqType(int id) {
		this.id = id;
		lights = false;
		main_hand_obj = -1;
		move_type = -1;
		priority = 5;
		replay_interval = -1;
		replay_count = 99;
		interrupt_type = -1;
		loop_type = 2;
		aBoolean1859 = false;
		vorbis = false;
	}

	public void decode(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			decode(opcode, buffer);
		}
	}

	public void decode(int opcode, Packet buffer) {
		if (opcode == 1) {
			int length = buffer.g2();
			frames_durations = new int[length];
			for (int index = 0; index < length; index++) {
				frames_durations[index] = buffer.g2();
			}
			frames_data = new int[length];
			for (int index = 0; index < length; index++) {
				frames_data[index] = buffer.g2();
			}
			for (int index = 0; index < length; index++) {
				frames_data[index] = (buffer.g2() << 16) + frames_data[index];
			}
		} else if (opcode == 2) {
			replay_interval = buffer.g2();
		} else if (opcode == 3) {
			interleave_order = new boolean[256];
			int length = buffer.g1();
			for (int i = 0; i < length; i++) {
				interleave_order[buffer.g1()] = true;
			}
		} else if (opcode == 4) {
			aBoolean1859 = true;
		} else if (opcode == 5) {
			priority = buffer.g1();
		} else if (opcode == 6) {
			main_hand_obj = buffer.g2();
		} else if (opcode == 7) {
			off_hand_obj = buffer.g2();
		} else if (opcode == 8) {
			replay_count = buffer.g1();
		} else if (opcode == 9) {
			interrupt_type = buffer.g1();
		} else if (opcode == 10) {
			move_type = buffer.g1();
		} else if (opcode == 11) {
			loop_type = buffer.g1();
		} else if (opcode == 12) {
			int length = buffer.g1();
			interface_frames = new int[length];
			for (int index = 0; length > index; index++) {
				interface_frames[index] = buffer.g2();
			}
			for (int index = 0; length > index; index++) {
				interface_frames[index] = (buffer.g2() << 16) + interface_frames[index];
			}
		} else if (opcode == 13) {
			int length = buffer.g2();
			sounds = new int[length][];
			for (int i_12_ = 0; i_12_ < length; i_12_++) {
				int i_13_ = buffer.g1();
				if (i_13_ > 0) {
					sounds[i_12_] = new int[i_13_];
					sounds[i_12_][0] = buffer.g3();
					for (int i_14_ = 1; i_13_ > i_14_; i_14_++) {
						sounds[i_12_][i_14_] = buffer.g2();
					}
				}
			}
		} else if (opcode == 14) {
			lights = true;
		} else if (opcode == 15) {// tweened
			tween = true;
		} else if (opcode == 16) {
			aBoolean1872 = true;
		} else if (opcode == 17) {
			buffer.g1();
		} else if (opcode == 18) {// vorbis
			vorbis = true;
		} else if (opcode == 19) {// frame_sound_adjust
			if (sound_adjusts == null) {
				sound_adjusts = new int[sounds.length];
				for (int i_15_ = 0; i_15_ < sounds.length; i_15_++) {
					sound_adjusts[i_15_] = 255;
				}
			}
			sound_adjusts[buffer.g1()] = buffer.g1();
		} else if (opcode == 20) {// frame_sound_offset_seek
			if (sound_offsets == null || sound_offsets2 == null) {
				sound_offsets = new int[sounds.length];
				sound_offsets2 = new int[sounds.length];
				for (int i_16_ = 0; sounds.length > i_16_; i_16_++) {
					sound_offsets[i_16_] = 256;
					sound_offsets2[i_16_] = 256;
				}
			}
			int i_17_ = buffer.g1();
			sound_offsets[i_17_] = buffer.g2();
			sound_offsets2[i_17_] = buffer.g2();
		}
	}

	public void postDecode() {
		if (move_type == -1) {
			if (interleave_order != null) {
				move_type = 2;
			} else {
				move_type = 0;
			}
		}
		if (interrupt_type == -1) {
			if (interleave_order == null) {
				interrupt_type = 0;
			} else {
				interrupt_type = 2;
			}
		}
	}

	public Model get_animated_dialoghead(Model stationary, int current_frameid, int next_frameid, int tick) {
		int var7 = frames_data[current_frameid];
		int var6 = frames_durations[current_frameid];
		AnimFrameset var8 = SeqTypeList.load_frameset(var7 >> 16);
		var7 &= 0xffff;
		if (null == var8) {
			return stationary.copy1(true, true, true);
		} else {
			AnimFrameset var9 = null;
			if ((tween || SeqTypeList.force_tweening) && 0 != ~next_frameid && ~next_frameid > ~frames_data.length) {
				next_frameid = frames_data[next_frameid];
				var9 = SeqTypeList.load_frameset(next_frameid >> 16);
				next_frameid &= 0xffff;
			}

			AnimFrameset var10 = null;
			AnimFrameset var11 = null;
			int var13 = 0;
			int var14 = 0;
			if (null != interface_frames) {
				if (~interface_frames.length < ~current_frameid) {
					var13 = interface_frames[current_frameid];
					if (~var13 != -65536) {
						var10 = SeqTypeList.load_frameset(var13 >> 16);
						var13 &= 0xffff;
					}
				}

				if ((tween || SeqTypeList.force_tweening) && -1 != next_frameid && interface_frames.length > next_frameid) {
					var14 = interface_frames[next_frameid];
					if (~var14 != -65536) {
						var11 = SeqTypeList.load_frameset(var14 >> 16);
						var14 &= 0xffff;
					}
				}
			}

			boolean var15 = !var8.modifies_alpha(var7);
			boolean var16 = !var8.modifies_color(var7);
			if (var10 != null) {
				var15 &= !var10.modifies_alpha(var13);
				var16 &= !var10.modifies_color(var13);
			}

			if (null != var9) {
				var15 &= !var9.modifies_alpha(next_frameid);
				var16 &= !var9.modifies_color(next_frameid);
			}

			if (null != var11) {
				var15 &= !var11.modifies_alpha(var14);
				var16 &= !var11.modifies_color(var14);
			}

			Model var17 = stationary.copy1(var15, var16, !lights);
			var17.animate_model_frame(var8, var7, var9, next_frameid, tick - 1, var6, lights);
			if (null != var10) {
				var17.animate_model_frame(var10, var13, var11, var14, tick + -1, var6, lights);
			}

			return var17;
		}
	}

	public Model get_animated_model(Model model, int var2, int var3, int var5, int var6) {
		int var7 = frames_durations[var2];
		var2 = frames_data[var2];
		AnimFrameset var8 = SeqTypeList.load_frameset(var2 >> 16);
		var2 &= 0xffff;
		if (var8 == null) {
			return model.copy3(true, true, true);
		} else {
			var5 &= 3;
			AnimFrameset var9 = null;
			if ((tween || SeqTypeList.force_tweening) && ~var3 != 0 && frames_data.length > var3) {
				var3 = frames_data[var3];
				var9 = SeqTypeList.load_frameset(var3 >> 16);
				var3 &= 0xffff;
			}

			Model var10;
			if (var9 != null) {
				var10 = model.copy3(!var8.modifies_alpha(var2) & !var9.modifies_alpha(var3), !var8.modifies_color(var2) & !var9.modifies_color(var3), !lights);
			} else {
				var10 = model.copy3(!var8.modifies_alpha(var2), !var8.modifies_color(var2), !lights);
			}

			if (GLManager.opengl_mode && lights) {
				if (-2 != ~var5) {
					if (2 != var5) {
						if (~var5 == -4) {
							((OpenGLModel) var10).rotate90();
						}
					} else {
						((OpenGLModel) var10).rotate180();
					}
				} else {
					((OpenGLModel) var10).rotate270();
				}
			} else if (var5 != 1) {
				if (2 != var5) {
					if (3 == var5) {
						var10.rotate90_without_normals();
					}
				} else {
					var10.rotate180_without_normals();
				}
			} else {
				var10.rotate270_without_normals();
			}

			var10.animate_model_frame(var8, var2, var9, var3, -1 + var6, var7, lights);
			if (GLManager.opengl_mode && lights) {
				if (1 != var5) {
					if (var5 == 2) {
						((OpenGLModel) var10).rotate180();
					} else if (var5 == 3) {
						((OpenGLModel) var10).rotate270();
					}
				} else {
					((OpenGLModel) var10).rotate90();
				}
			} else if (~var5 != -2) {
				if (-3 != ~var5) {
					if (var5 == 3) {
						var10.rotate270_without_normals();
					}
				} else {
					var10.rotate180_without_normals();
				}
			} else {
				var10.rotate90_without_normals();
			}

			return var10;
		}
	}

	public Model get_animated_loc(Model stationary, int rotation, int current_frameid, int next_frameid, int num) {
		int var7 = frames_durations[current_frameid];
		current_frameid = frames_data[current_frameid];
		AnimFrameset frameset = SeqTypeList.load_frameset(current_frameid >> 16);
		current_frameid &= 0xffff;
		if (frameset == null) {
			return stationary.copy1(true, true, true);
		} else {
			rotation &= 3;
			AnimFrameset next_frameset = null;
			if ((tween || SeqTypeList.force_tweening) && ~next_frameid != 0 && ~next_frameid > ~frames_data.length) {
				next_frameid = frames_data[next_frameid];
				next_frameset = SeqTypeList.load_frameset(next_frameid >> 16);
				next_frameid &= 0xffff;
			}

			Model model;
			if (null == next_frameset) {
				model = stationary.copy1(!frameset.modifies_alpha(current_frameid), !frameset.modifies_color(current_frameid), !lights);
			} else {
				model = stationary.copy1(!frameset.modifies_alpha(current_frameid) & !next_frameset.modifies_alpha(next_frameid), !frameset.modifies_color(current_frameid) & !next_frameset.modifies_color(next_frameid), !lights);
			}

			if (lights && GLManager.opengl_mode) {
				if (1 != rotation) {
					if (-3 == ~rotation) {
						((OpenGLModel) model).rotate180();
					} else if (-4 == ~rotation) {
						((OpenGLModel) model).rotate90();
					}
				} else {
					((OpenGLModel) model).rotate270();
				}
			} else if (rotation == 1) {
				model.rotate270_without_normals();
			} else if (rotation == 2) {
				model.rotate180_without_normals();
			} else if (rotation == 3) {
				model.rotate90_without_normals();
			}

			model.animate_model_frame(frameset, current_frameid, next_frameset, next_frameid, num + -1, var7, lights);
			if (lights && GLManager.opengl_mode) {
				if (~rotation == -2) {
					((OpenGLModel) model).rotate90();
				} else if (-3 == ~rotation) {
					((OpenGLModel) model).rotate180();
				} else if (~rotation == -4) {
					((OpenGLModel) model).rotate270();
				}
			} else if (1 != rotation) {
				if (rotation == 2) {
					model.rotate180_without_normals();
				} else if (3 == rotation) {
					model.rotate270_without_normals();
				}
			} else {
				model.rotate90_without_normals();
			}

			return model;
		}
	}

	public Model get_spot_animated(Model stationary, int current_frameid, int next_frameid, int num) {
		int var6 = frames_durations[current_frameid];
		current_frameid = frames_data[current_frameid];
		AnimFrameset var7 = SeqTypeList.load_frameset(current_frameid >> 16);
		current_frameid &= 0xffff;
		if (var7 == null) {
			return stationary.copy2(true, true, true);
		} else {
			AnimFrameset var9 = null;
			if ((tween || SeqTypeList.force_tweening) && ~next_frameid != 0 && ~frames_data.length < ~next_frameid) {
				next_frameid = frames_data[next_frameid];
				var9 = SeqTypeList.load_frameset(next_frameid >> 16);
				next_frameid &= 0xffff;
			}

			Model var10;
			if (null == var9) {
				var10 = stationary.copy2(!var7.modifies_alpha(current_frameid), !var7.modifies_color(current_frameid), !lights);
			} else {
				var10 = stationary.copy2(!var7.modifies_alpha(current_frameid) & !var9.modifies_alpha(next_frameid), !var7.modifies_color(current_frameid) & !var9.modifies_color(next_frameid), !lights);
			}
			var10.animate_model_frame(var7, current_frameid, var9, next_frameid, num + -1, var6, lights);
			return var10;
		}
	}
}
