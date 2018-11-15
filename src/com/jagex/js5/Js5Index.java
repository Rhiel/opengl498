package com.jagex.js5;

import com.jagex.NameHashTable;
import com.jagex.Packet;

public final class Js5Index {
    public int indexversion;
    public int group_count;
    public int crc32;
    public int[] group_revision;
    public int[] group_crc32;
    public int[] group_file_count;
    public int[][] group_file_name_raw;
    public NameHashTable group_name;
    public int[] group_name_raw;
    public int fit_entry_count;
    public int[][] file_entry_file_id;
    public int[] fit_entry_group_id;
    public int[] group_entry_count;
    public NameHashTable[] group_file_names;

    public void deserialize(byte[] is) {
        try {
            Packet packet = new Packet(Js5Container.get_payload(is));
            int version = packet.g1();
            if (version < 5 || version > 7) throw new RuntimeException("Incorrect JS5 protocol number: ");
            if (version >= 6)
                this.indexversion = packet.g4();
            else
                this.indexversion = 0;
            int flags = packet.g1();
            boolean has_names = (flags & 0x1) != 0;
            if (version < 7)
                this.fit_entry_count = packet.g2();
            else this.fit_entry_count = packet.readBigSmart();
            int file_id_buf = 0;
            this.fit_entry_group_id = new int[this.fit_entry_count];
            int max_group_id = -1;
            if (version >= 7) {
                for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++) {
                    this.fit_entry_group_id[entry_id] = file_id_buf += packet.readBigSmart();
                    if (this.fit_entry_group_id[entry_id] > max_group_id)
                        max_group_id = this.fit_entry_group_id[entry_id];
                }
            } else {
                for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++) {
                    this.fit_entry_group_id[entry_id] = file_id_buf += packet.g2();
                    if (max_group_id < this.fit_entry_group_id[entry_id])
                        max_group_id = this.fit_entry_group_id[entry_id];
                }
            }
            this.group_count = 1 + max_group_id;
            this.group_crc32 = new int[this.group_count];
            this.group_entry_count = new int[this.group_count];
            this.group_file_count = new int[this.group_count];
            this.file_entry_file_id = new int[this.group_count][];
            this.group_revision = new int[this.group_count];
            if (has_names) {
                this.group_name_raw = new int[this.group_count];
                for (int group_id = 0; group_id < this.group_count; group_id++)
                    this.group_name_raw[group_id] = -1;
                for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++)
                    this.group_name_raw[this.fit_entry_group_id[entry_id]] = packet.g4();
                this.group_name = new NameHashTable(this.group_name_raw);
            }
            for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++)
                this.group_crc32[this.fit_entry_group_id[entry_id]] = packet.g4();

            for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++)
                this.group_revision[this.fit_entry_group_id[entry_id]] = packet.g4();
            if (version >= 7) {
                for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++)
                    this.group_entry_count[this.fit_entry_group_id[entry_id]] = packet.readBigSmart();
                for (int i_18_ = 0; i_18_ < this.fit_entry_count; i_18_++) {
                    int group_id = this.fit_entry_group_id[i_18_];
                    int entry_count = this.group_entry_count[group_id];
                    file_id_buf = 0;
                    this.file_entry_file_id[group_id] = new int[entry_count];
                    int max_file_id = -1;
                    for (int file_entry_id = 0; entry_count > file_entry_id; file_entry_id++) {
                        int file_id = (this.file_entry_file_id[group_id][file_entry_id] = file_id_buf += packet.readBigSmart());
                        if (file_id > max_file_id) max_file_id = file_id;
                    }
                    this.group_file_count[group_id] = max_file_id + 1;
                    if ((~entry_count) == (~max_file_id + 1)) this.file_entry_file_id[group_id] = null;
                }
            } else {
                for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++)
                    this.group_entry_count[this.fit_entry_group_id[entry_id]] = packet.g2();
                for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++) {
                    int group_id = this.fit_entry_group_id[entry_id];
                    file_id_buf = 0;
                    int file_count = this.group_entry_count[group_id];
                    int max_file_id = -1;
                    this.file_entry_file_id[group_id] = new int[file_count];
                    for (int file_entry_id = 0; file_entry_id < file_count; file_entry_id++) {
                        int file_id = (this.file_entry_file_id[group_id][file_entry_id] = file_id_buf += packet.g2());
                        if (max_file_id < file_id) max_file_id = file_id;
                    }
                    this.group_file_count[group_id] = max_file_id + 1;
                    if ((~file_count) == (~1 + max_file_id)) this.file_entry_file_id[group_id] = null;
                }
            }
            if (!has_names)
                return;
            this.group_file_name_raw = new int[max_group_id + 1][];
            this.group_file_names = new NameHashTable[1 + max_group_id];
            for (int entry_id = 0; entry_id < this.fit_entry_count; entry_id++) {
                int group_id = this.fit_entry_group_id[entry_id];
                int entry_count = this.group_entry_count[group_id];
                this.group_file_name_raw[group_id] = new int[this.group_file_count[group_id]];
                for (int file_id = 0; file_id < this.group_file_count[group_id]; file_id++)
                    this.group_file_name_raw[group_id][file_id] = -1;
                for (int file_entry_id = 0; file_entry_id < entry_count; file_entry_id++) {
                    int file_id;
                    if (this.file_entry_file_id[group_id] == null) file_id = file_entry_id;
                    else file_id = this.file_entry_file_id[group_id][file_entry_id];
                    this.group_file_name_raw[group_id][file_id] = packet.g4();
                }
                this.group_file_names[group_id] = new NameHashTable(this.group_file_name_raw[group_id]);
            }
        } catch (RuntimeException runtimeexception) {
            runtimeexception.printStackTrace();
        }
    }

    public Js5Index(byte[] data, int crc32) {
        try {
            this.crc32 = CRC32.calculate(data, data.length);
            if (this.crc32 != crc32) throw new RuntimeException("Invalid CRC - expected:");
            deserialize(data);
        } catch (RuntimeException runtimeexception) {
            throw runtimeexception;
        }
    }

}

