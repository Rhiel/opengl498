package com.rs2.client.components.worldmap;

import com.jagex.Js5;
import com.jagex.Packet;
import com.jagex.RSString;

public class WorldMapAreaLabels {

	public int num_labels;
	public RSString[] label_text;
	public short[] label_x;
	public short[] label_y;
	public int[] label_colour;
	public byte[] label_settings;

	public WorldMapAreaLabels(int var1) {
		num_labels = var1;
		label_text = new RSString[num_labels];
		label_x = new short[num_labels];
		label_y = new short[num_labels];
		label_colour = new int[num_labels];
		label_settings = new byte[num_labels];
	}

	public boolean method1789(int var1) {
		return (label_settings[var1] & 0x4) != 0;
	}

	public boolean method1787(int var1, byte var2) {
		return (label_settings[var1] & 0x8) != 0;
	}

	public boolean method1794(int var1, int var2) {
		return (label_settings[var1] & 0x10) == 0;
	}

	public int method1791(int var1) {
		return label_settings[var1] & 0x3;
	}

	public static WorldMapAreaLabels from_area_js5(Js5 data_js5, String groupname) {
		int groupid = data_js5.get_groupid(groupname.toString());
		if (groupid == -1) {
			return new WorldMapAreaLabels(0);
		} else {
			int[] fileids = data_js5.get_file_entry_file_id(groupid);
			WorldMapAreaLabels labels = new WorldMapAreaLabels(fileids.length);
			for (int var6 = 0; ~var6 > ~labels.num_labels; ++var6) {
				Packet packet = new Packet(data_js5.get_file(groupid, fileids[var6]));
				labels.label_text[var6] = packet.gstr();
				labels.label_settings[var6] = packet.g1s();
				labels.label_x[var6] = (short) packet.g2();
				labels.label_y[var6] = (short) packet.g2();
				labels.label_colour[var6] = packet.readInt();
			}
			return labels;
		}
	}

}
