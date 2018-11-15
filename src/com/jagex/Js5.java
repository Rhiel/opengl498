package com.jagex;
/* Class105 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.js5.Archive;
import com.jagex.js5.Js5Container;
import com.jagex.js5.Js5Index;

public final class Js5 {
	static NodeDeque aClass89_1767 = new NodeDeque();
	static RSString aClass16_1803;
	public static RSString aClass16_1805 = RSString.createString("cyan:");
	public static boolean aBoolean1806 = false;
	public static int regionChunkY;
	static RSString aClass16_1809;
	static RSString aClass16_1810;
	public static int anInt1811;
	public Js5Index index = null;
	public int discard_unpacked;
	public Object[] group_data;
	public boolean unknownBool;
	public Archive archive;
	public static int q;
	public static int x;
	public Object[][] file_data;
	public static SignlinkRequest M;

	public static int hash_name(String string) {
		int length = string.length();
		int hash = 0;
		for (int index = 0; index < length; index++) {
			hash = to_hash_char(string.toString().charAt(index)) + (hash << 5) - hash;
		}
		return hash;
	}

	public static byte to_hash_char(char in) {
		byte result;
		if ((in <= '\0' || in >= '\u0080') && (in < '\u00a0' || in > '\u00ff')) {
			switch (in) {
				case '\u20ac':
					result = (byte) -128;
					break;
				case '\u201a':
					result = (byte) -126;
					break;
				case '\u0192':
					result = (byte) -125;
					break;
				case '\u201e':
					result = (byte) -124;
					break;
				case '\u2026':
					result = (byte) -123;
					break;
				case '\u2020':
					result = (byte) -122;
					break;
				case '\u2021':
					result = (byte) -121;
					break;
				case '\u02c6':
					result = (byte) -120;
					break;
				case '\u2030':
					result = (byte) -119;
					break;
				case '\u0160':
					result = (byte) -118;
					break;
				case '\u2039':
					result = (byte) -117;
					break;
				case '\u0152':
					result = (byte) -116;
					break;
				case '\u017d':
					result = (byte) -114;
					break;
				case '\u2018':
					result = (byte) -111;
					break;
				case '\u2019':
					result = (byte) -110;
					break;
				case '\u201c':
					result = (byte) -109;
					break;
				case '\u201d':
					result = (byte) -108;
					break;
				case '\u2022':
					result = (byte) -107;
					break;
				case '\u2013':
					result = (byte) -106;
					break;
				case '\u2014':
					result = (byte) -105;
					break;
				case '\u02dc':
					result = (byte) -104;
					break;
				case '\u2122':
					result = (byte) -103;
					break;
				case '\u0161':
					result = (byte) -102;
					break;
				case '\u203a':
					result = (byte) -101;
					break;
				case '\u0153':
					result = (byte) -100;
					break;
				case '\u017e':
					result = (byte) -98;
					break;
				case '\u0178':
					result = (byte) -97;
					break;
				default:
					result = (byte) 63;
					break;
			}
		} else {
			result = (byte) in;
		}
		return result;
	}

	public boolean is_ready() {
		if (index == null) {
			index = archive.get_index();
			if (index == null) {
				return false;
			}
			file_data = new Object[index.group_count][];
			group_data = new Object[index.group_count];
		}
		return true;
	}

	public final void discard_names(boolean discard_file_names, byte i, boolean discard_group_names) {
		if (is_ready()) {
			if (discard_group_names) {
				// index.group_name_raw = null;
				// index.group_name = null;
			}
			if (discard_file_names) {
				// index.group_file_names = null;
				// index.group_file_name_raw = null;
			}
		}
	}

	public final boolean is_group_cached(String string) {
		if (!is_ready()) {
			return false;
		}
		string = string.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(string));
		return is_group_cached(group_id);
	}

	public final boolean is_cached(String string) {
		int default_group_id = get_groupid("");
		if (default_group_id != -1) {
			return is_file_cached("", string);
		}
		return is_file_cached(string, "");
	}

	public final void discard_all_unpacked() {
		if (file_data != null) {
			for (int file_id = 0; file_id < file_data.length; file_id++) {
				file_data[file_id] = null;
			}
		}
	}

	public final byte[] get_file(int group_id, int file_id, int[] xtea_keys) {
		if (!is_valid_file(group_id, file_id)) {
			return null;
		}
		if (file_data[group_id] == null || file_data[group_id][file_id] == null) {
			boolean succeeded = unpack_group(group_id, file_id, xtea_keys);
			if (!succeeded) {
				request_group(group_id);
				succeeded = unpack_group(group_id, file_id, xtea_keys);
				if (!succeeded) {
					return null;
				}
			}
		}
		byte[] data = DataBuffer.unpack_data_buffer(false, file_data[group_id][file_id]);
		do {
			if (discard_unpacked != 1) {
				if (discard_unpacked != 2) {
					break;
				}
				file_data[group_id] = null;
				break;
			}
			file_data[group_id][file_id] = null;
			if (index.group_file_count[group_id] == 1) {
				file_data[group_id] = null;
			}
		} while (false);
		return data;
	}

	public final int get_index_crc32() {
		if (!is_ready()) {
			throw new IllegalStateException("");
		}
		return index.crc32;
	}

	public final boolean is_fully_cached() {
		if (!is_ready()) {
			return false;
		}
		boolean has_missing = true;
		for (int group_id : index.fit_entry_group_id) {
			if (group_data[group_id] == null) {
				request_group(group_id);
				if (group_data[group_id] == null) {
					has_missing = false;
				}
			}
		}
		return has_missing;
	}

	public int get_group_progress(int group_id) {
		if (!is_valid_group(group_id)) {
			return 0;
		}
		if (group_data[group_id] != null) {
			return 100;
		}
		return archive.get_group_progress(group_id);
	}

	public final boolean is_valid_group(String group_name) {
		if (!is_ready()) {
			return false;
		}
		group_name = group_name.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(group_name));
		return group_id >= 0;
	}

	public void request_group(int group_id) {
		if (unknownBool) {
			group_data[group_id] = archive.get_group_data(group_id);
		} else {
			group_data[group_id] = DataBuffer.create_data_buffer(false, archive.get_group_data(group_id));
		}
	}

	public final byte[] get_file(String group_name, String file_name) {
		if (!is_ready()) {
			return null;
		}
		group_name = group_name.toLowerCase();
		file_name = file_name.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(group_name));
		if (!is_valid_group(group_id)) {
			return null;
		}
		int file_id = index.group_file_names[group_id].get_index(hash_name(file_name));
		return get_file(group_id, file_id);
	}

	public final byte[] get_file(int group_id, int file_id) {
		return get_file(group_id, file_id, null);
	}

	public final int[] get_file_entry_file_id(int group_id) {
		if (!is_valid_group(group_id)) {
			return null;
		}
		int[] file_entry_file_id = index.file_entry_file_id[group_id];
		if (file_entry_file_id == null) {
			file_entry_file_id = new int[index.group_entry_count[group_id]];
			for (int file_id = 0; file_id < file_entry_file_id.length; file_id++) {
				file_entry_file_id[file_id] = file_id;
			}
		}
		return file_entry_file_id;
	}

	public final void prefetch_group(String group_name) {
		if (is_ready()) {
			group_name = group_name.toLowerCase();
			int group_id = index.group_name.get_index(hash_name(group_name));
			prefetch_group(group_id);
		}
	}

	public boolean is_file_cached(String groupname, String filename) {
		if (!is_ready()) {
			return false;
		}
		groupname = groupname.toLowerCase();
		filename = filename.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(groupname));
		if (!is_valid_group(group_id)) {
			return false;
		}
		int file_id = index.group_file_names[group_id].get_index(hash_name(filename));
		return is_file_cached(group_id, file_id);
	}

	public boolean is_valid_group(int group_id) {
		if (!is_ready()) {
			return false;
		}
		if (group_id < 0 || group_id >= index.group_file_count.length || index.group_file_count[group_id] == 0) {
			return false;
		}
		return true;
	}

	public void prefetch_group(int group_id) {
		archive.request_group(group_id);
	}

	public final int get_progress() {
		if (!is_ready()) {
			return 0;
		}
		int total = 0;
		int current = 0;
		for (int group_id = 0; group_id < group_data.length; group_id++) {
			if (index.group_entry_count[group_id] > 0) {
				total += 100;
				current += get_group_progress(group_id);
			}
		}
		if (total == 0) {
			return 100;
		}
		return 100 * current / total;
	}

	public final int get_groupid(int hash) {
		if (!is_ready()) {
			return -1;
		}
		int group_id = index.group_name.get_index(hash);
		if (!is_valid_group(group_id)) {
			return -1;
		}
		return group_id;
	}

	public final int get_groupid(String string) {
		if (!is_ready()) {
			return -1;
		}
		string = string.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(string));
		if (!is_valid_group(group_id)) {
			return -1;
		}
		return group_id;
	}

	public final int get_file_count(int group_id) {
		if (!is_valid_group(group_id)) {
			return 0;
		}
		return index.group_file_count[group_id];
	}

	public final void discard_all_downloaded() {
		if (group_data != null) {
			for (int group_id = 0; group_id < group_data.length; group_id++) {
				group_data[group_id] = null;
			}
		}
	}

	public final int get_group_progress(String string) {
		if (!is_ready()) {
			return 0;
		}
		string = string.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(string));
		return get_group_progress(group_id);
	}

	public final byte[] get_file(int id) {
		try {
			if (!is_ready()) {
				return null;
			}
			if (index.group_file_count.length == 1) {
				return get_file(0, id);
			}
			if (!is_valid_group(id)) {
				return null;
			}
			if (index.group_file_count[id] == 1) {
				return get_file(id, 0);
			}
			throw new RuntimeException("Unable to determine if id is groupid or fileid");
		} catch (RuntimeException runtimeexception) {
			throw runtimeexception;
		}
	}

	public final void discard_unpacked(int i) {
		if (is_valid_group(i)) {
			if (file_data != null) {
				file_data[i] = null;
			}
		}
	}

	public final boolean is_file_cached(int group_id, int file_id) {
		if (!is_valid_file(group_id, file_id)) {
			return false;
		}
		if (file_data[group_id] != null && file_data[group_id][file_id] != null) {
			return true;
		}
		if (group_data[group_id] != null) {
			return true;
		}
		request_group(group_id);
		return group_data[group_id] != null;
	}

	public final int get_group_count() {
		if (!is_ready()) {
			return -1;
		}
		return index.group_file_count.length;
	}

	public boolean unpack_group(int group_id, int requested_file_id, int[] xtea_keys) {
		if (!is_valid_group(group_id)) {
			return false;
		}
		if (group_data[group_id] == null) {
			return false;
		}
		int file_entry_count = index.group_entry_count[group_id];
		int[] file_entry_file_id = index.file_entry_file_id[group_id];
		if (file_data[group_id] == null) {
			file_data[group_id] = new Object[index.group_file_count[group_id]];
		}
		Object[] file_data = this.file_data[group_id];
		boolean have_all_files = true;
		for (int file_id = 0; file_id < file_entry_count; file_id++) {
			int _file_id;
			if (file_entry_file_id != null) {
				_file_id = file_entry_file_id[file_id];
			} else {
				_file_id = file_id;
			}
			if (file_data[_file_id] == null) {
				have_all_files = false;
				break;
			}
		}
		if (have_all_files) {
			return true;
		}
		byte[] container_data;
		if (xtea_keys == null || xtea_keys[0] == 0 && xtea_keys[1] == 0 && xtea_keys[2] == 0 && xtea_keys[3] == 0) {
			container_data = DataBuffer.unpack_data_buffer(false, group_data[group_id]);
		} else {
			container_data = DataBuffer.unpack_data_buffer(true, group_data[group_id]);
			Packet packet = new Packet(container_data);
			packet.decryptXtea(5, packet.byteBuffer.length, xtea_keys);
		}
		byte[] group_data = Js5Container.get_payload(container_data);
		if (unknownBool) {
			this.group_data[group_id] = null;
		}
		if (file_entry_count > 1) {
			if (discard_unpacked == 2) {
				int group_length = group_data.length;
				int block_count = 0xff & group_data[--group_length];
				group_length -= block_count * file_entry_count * 4;
				Packet packet = new Packet(group_data);
				int file_entry_length = 0;
				packet.index = group_length;
				int read_file_id = 0;
				for (int block_id = 0; block_id < block_count; block_id++) {
					int file_entry_length_buffer = 0;
					for (int file_entry_id = 0; file_entry_id < file_entry_count; file_entry_id++) {
						file_entry_length_buffer += packet.g4();
						int file_id;
						if (file_entry_file_id != null) {
							file_id = file_entry_file_id[file_entry_id];
						} else {
							file_id = file_entry_id;
						}
						if (requested_file_id == file_id) {
							read_file_id = file_id;
							file_entry_length += file_entry_length_buffer;
						}
					}
				}
				if (file_entry_length == 0) {
					return true;
				}
				byte[] file_entry_data = new byte[file_entry_length];
				packet.index = group_length;
				file_entry_length = 0;
				int block_start = 0;
				for (int block_id = 0; block_id < block_count; block_id++) {
					int block_length = 0;
					for (int file_entry_id = 0; file_entry_id < file_entry_count; file_entry_id++) {
						block_length += packet.g4();
						int file_id;
						if (file_entry_file_id != null) {
							file_id = file_entry_file_id[file_entry_id];
						} else {
							file_id = file_entry_id;
						}
						if (requested_file_id == file_id) {
							ArrayUtils.copy(group_data, block_start, file_entry_data, file_entry_length, block_length);
							file_entry_length += block_length;
						}
						block_start += block_length;
					}
				}
				file_data[read_file_id] = file_entry_data;
			} else {
				int group_length = group_data.length;
				int block_count = 0xff & group_data[--group_length];
				group_length -= block_count * file_entry_count * 4;
				Packet packet = new Packet(group_data);
				int[] file_entry_length = new int[file_entry_count];
				packet.index = group_length;
				for (int block_id = 0; block_id < block_count; block_id++) {
					int file_entry_length_buffer = 0;
					for (int file_entry_id = 0; file_entry_id < file_entry_count; file_entry_id++) {
						file_entry_length_buffer += packet.g4();
						file_entry_length[file_entry_id] += file_entry_length_buffer;
					}
				}
				byte[][] file_entry_data = new byte[file_entry_count][];
				for (int file_entry_id = 0; file_entry_id < file_entry_count; file_entry_id++) {
					file_entry_data[file_entry_id] = new byte[file_entry_length[file_entry_id]];
					file_entry_length[file_entry_id] = 0;
				}
				packet.index = group_length;
				int block_start = 0;
				for (int block_id = 0; block_id < block_count; block_id++) {
					int block_length = 0;
					for (int file_entry_id = 0; file_entry_id < file_entry_count; file_entry_id++) {
						block_length += packet.g4();
						ArrayUtils.copy(group_data, block_start, file_entry_data[file_entry_id], file_entry_length[file_entry_id], block_length);
						file_entry_length[file_entry_id] += block_length;
						block_start += block_length;
					}
				}
				for (int file_entry_id = 0; file_entry_id < file_entry_count; file_entry_id++) {
					int file_id;
					if (file_entry_file_id != null) {
						file_id = file_entry_file_id[file_entry_id];
					} else {
						file_id = file_entry_id;
					}
					if (discard_unpacked == 0) {
						file_data[file_id] = DataBuffer.create_data_buffer(false, file_entry_data[file_entry_id]);
					} else {
						file_data[file_id] = file_entry_data[file_entry_id];
					}
				}
			}
		} else {
			int file_id_0;
			if (file_entry_file_id == null) {
				file_id_0 = 0;
			} else {
				file_id_0 = file_entry_file_id[0];
			}
			if (discard_unpacked == 0) {
				file_data[file_id_0] = DataBuffer.create_data_buffer(false, group_data);
			} else {
				file_data[file_id_0] = group_data;
			}
		}
		return true;
	}

	public synchronized boolean is_valid_file(int id) {
		if (!is_ready()) {
			return false;
		}
		if (index.group_file_count.length == 1) {
			return is_valid_file(0, id);
		}
		if (!is_valid_group(id)) {
			return false;
		}
		if (index.group_file_count[id] == 1) {
			return is_valid_file(id, 0);
		}
		throw new RuntimeException("Unable to determine if id is groupid or fileid");
	}

	public boolean is_valid_file(int group_id, int file_id) {
		if (!is_ready()) {
			return false;
		}
		if (group_id < 0 || file_id < 0 || group_id >= index.group_file_count.length || file_id >= index.group_file_count[group_id]) {
			return false;
		}
		return true;
	}

	public final boolean is_valid_file(String group_name, String file_name) {
		if (!is_ready()) {
			return false;
		}
		group_name = group_name.toLowerCase();
		file_name = file_name.toLowerCase();
		int group_id = index.group_name.get_index(hash_name(group_name));
		return group_id >= 0 && index.group_file_names[group_id].get_index(hash_name(file_name)) >= 0;
	}

	public final boolean is_file_cached(int id) {
		try {
			if (!is_ready()) {
				return false;
			}
			if (index.group_file_count.length == 1) {
				return is_file_cached(0, id);
			}
			if (!is_valid_group(id)) {
				return false;
			}
			if (index.group_file_count[id] == 1) {
				return is_file_cached(id, 0);
			}
			throw new RuntimeException();
		} catch (RuntimeException runtimeexception) {
			throw runtimeexception;
		}
	}

	public final boolean is_group_cached(int group_id) {
		if (!is_valid_group(group_id)) {
			return false;
		}
		if (group_data[group_id] != null) {
			return true;
		}
		request_group(group_id);
		if (group_data[group_id] != null) {
			return true;
		}
		return false;
	}

	public Js5(Archive archive, boolean bool, int discard_unpacked) {
		if (discard_unpacked < 0 || discard_unpacked > 2) {
			throw new IllegalArgumentException("js5: Invalid value " + discard_unpacked + " supplied for discardunpacked");
		}
		this.discard_unpacked = discard_unpacked;
		unknownBool = bool;
		this.archive = archive;
	}

	public static final void process_entity_animation(int i, Entity entity) {
		if (i != 1) {
			aClass16_1803 = null;
		}
		entity.aBoolean2810 = false;
		if (entity.current_standing_animation != -1) {
			SeqType class23_sub13_sub22 = SeqTypeList.list(entity.current_standing_animation);
			if (class23_sub13_sub22 != null && class23_sub13_sub22.frames_data != null) {
				entity.current_standing_tick++;
				if (entity.current_standing_frameid < class23_sub13_sub22.frames_data.length && (entity.current_standing_tick ^ 0xffffffff) < (class23_sub13_sub22.frames_durations[entity.current_standing_frameid] ^ 0xffffffff)) {
					entity.current_standing_frameid++;
					++entity.next_standing_frameid;
					entity.current_standing_tick = 1;
					Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, class23_sub13_sub22, entity.current_standing_frameid);
				}
				if (entity.current_standing_frameid >= class23_sub13_sub22.frames_data.length) {
					entity.current_standing_frameid = 0;
					entity.current_standing_tick = 0;
					Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, class23_sub13_sub22, entity.current_standing_frameid);
				}
				entity.next_standing_frameid = entity.current_standing_frameid - -1;
				if (~entity.next_standing_frameid <= ~class23_sub13_sub22.frames_data.length) {
					entity.next_standing_frameid = 0;
				}
			} else {
				entity.current_standing_animation = -1;
			}
		}
		if (entity.current_spotanimid != -1 && (entity.anInt2671 ^ 0xffffffff) >= (GameClient.timer ^ 0xffffffff)) {
			if ((entity.current_spotanim_frameid ^ 0xffffffff) > -1) {
				entity.current_spotanim_frameid = 0;
			}
			int seqid = SpotType.list(entity.current_spotanimid).seqid;
			if ((seqid ^ 0xffffffff) != 0) {
				SeqType seqtype = SeqTypeList.list(seqid);
				if (seqtype == null || seqtype.frames_data == null) {
					entity.current_spotanimid = -1;
				} else {
					entity.current_spotanim_tick++;
					if ((seqtype.frames_data.length ^ 0xffffffff) < (entity.current_spotanim_frameid ^ 0xffffffff) && entity.current_spotanim_tick > seqtype.frames_durations[entity.current_spotanim_frameid]) {
						entity.current_spotanim_tick = 1;
						entity.current_spotanim_frameid++;
						Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, seqtype, entity.current_spotanim_frameid);
					}
					if (entity.current_spotanim_frameid >= seqtype.frames_data.length) {
						entity.current_spotanimid = -1;
					}
					if (seqtype.frames_data.length >= entity.current_spotanim_frameid) {
						entity.current_spotanimid = -1;
					}
					entity.next_spotanim_frameid = entity.current_spotanim_frameid + 1;
					if (entity.next_spotanim_frameid >= seqtype.frames_data.length) {
						entity.next_spotanim_frameid = -1;
					}
				}
			} else {
				entity.current_spotanimid = -1;
			}
		}
		if ((entity.current_performing_seqid ^ 0xffffffff) != 0 && entity.anInt2828 <= 1) {
			SeqType anim = SeqTypeList.list(entity.current_performing_seqid);
			if (anim.interrupt_type == 1 && entity.anInt2640 > 0 && entity.forceCommenceSpeed <= GameClient.timer && GameClient.timer > entity.forcePathSpeed) {
				entity.anInt2828 = 1;
				return;
			}
		}
		if ((entity.current_performing_seqid ^ 0xffffffff) != 0 && entity.anInt2828 == 0) {
			SeqType seq = SeqTypeList.list(entity.current_performing_seqid);
			if (seq == null || seq.frames_data == null) {
				entity.current_performing_seqid = -1;
			} else {
				entity.current_performing_tick++;
				if (seq.frames_data.length > entity.current_performing_frameid && entity.current_performing_tick > seq.frames_durations[entity.current_performing_frameid]) {
					entity.current_performing_tick = 1;
					entity.current_performing_frameid++;
					Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, seq, entity.current_performing_frameid);
				}
				if (entity.current_performing_frameid >= seq.frames_data.length) {
					entity.current_performing_frameid -= seq.replay_interval;
					entity.seq_replays_done++;
					if (seq.replay_count <= entity.seq_replays_done) {
						entity.current_performing_seqid = -1;
					} else if ((entity.current_performing_frameid ^ 0xffffffff) > -1 || entity.current_performing_frameid >= seq.frames_data.length) {
						entity.current_performing_seqid = -1;
					} else {
						Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, seq, entity.current_performing_frameid);
					}
				}
				entity.next_performing_frameid = entity.current_performing_frameid + 1;
				if (entity.next_performing_frameid >= seq.frames_data.length) {
					entity.next_performing_frameid -= seq.replay_interval;
					if (seq.replay_count > entity.seq_replays_done + 1) {
						if (0 > entity.next_performing_frameid || entity.next_performing_frameid >= seq.frames_data.length) {
							entity.next_performing_frameid = -1;
						}
					} else {
						entity.next_performing_frameid = -1;
					}
				}
				entity.aBoolean2810 = seq.aBoolean1859;
			}
		}
		if ((entity.anInt2828 ^ 0xffffffff) < -1) {
			entity.anInt2828--;
		}
		for (int var6 = 0; entity.worn_objs_animations.length > var6; ++var6) {
			WornObjAnim worn_anim = entity.worn_objs_animations[var6];
			if (null != worn_anim) {
				if (~worn_anim.duration >= -1) {
					SeqType var4 = SeqTypeList.list(worn_anim.seqid);
					if (null != var4 && var4.frames_data != null) {
						++worn_anim.timer;
						if (worn_anim.frameid < var4.frames_data.length && worn_anim.timer > var4.frames_durations[worn_anim.frameid]) {
							++worn_anim.frameid;
							worn_anim.timer = 1;
							Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, var4, worn_anim.frameid);
						}

						if (~var4.frames_data.length >= ~worn_anim.frameid) {
							++worn_anim.replaysdone;
							worn_anim.frameid -= var4.replay_interval;
							if (var4.replay_count > worn_anim.replaysdone) {
								if (-1 >= ~worn_anim.frameid && ~worn_anim.frameid > ~var4.frames_data.length) {
									Class21.playAnimationSound(entity, entity.bound_extents_z, entity.bound_extents_x, var4, worn_anim.frameid);
								} else {
									entity.worn_objs_animations[var6] = null;
								}
							} else {
								entity.worn_objs_animations[var6] = null;
							}
						}

						worn_anim.next_frameid = 1 + worn_anim.frameid;
						if (var4.frames_data.length <= worn_anim.next_frameid) {
							worn_anim.next_frameid -= var4.replay_interval;
							if (1 + worn_anim.replaysdone < var4.replay_count) {
								if (-1 < ~worn_anim.next_frameid || var4.frames_data.length <= worn_anim.next_frameid) {
									worn_anim.next_frameid = -1;
								}
							} else {
								worn_anim.next_frameid = -1;
							}
						}
					} else {
						entity.worn_objs_animations[var6] = null;
					}
				} else {
					--worn_anim.duration;
				}
			}
		}
	}

	public static void method1553(int i) {
		aClass89_1767 = null;
		if (i != -1) {
			aClass16_1809 = null;
		}
		aClass16_1803 = null;
		SpriteLoader.sprites_height = null;
		aClass16_1810 = null;
		aClass16_1805 = null;
		aClass16_1809 = null;
		GameClient.currentPlayer = null;
	}

	static {
		aClass16_1803 = aClass16_1805;
		aClass16_1810 = RSString.createString("(Y<)4col>");
		anInt1811 = 0;
		aClass16_1809 = aClass16_1805;
	}
}
