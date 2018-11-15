package com.jagex.js5;

import com.jagex.FileSystem;
import com.jagex.Js5Client;
import com.jagex.Packet;

/**
 * Created by Chris on 6/1/2017.
 */
public final class Js5Manager {
    public Js5Archive[] archives;
    public AsyncCache async_cache;
    public Js5FileRequest masterindex_request;
    public Js5Client js5_client;
    public Packet masterindex_packet;

    public final boolean is_master_index_ready() {
        try {
            if (masterindex_packet != null) {
				return true;
			}
            if (masterindex_request == null) {
                if (js5_client.more_than_20_priority_requests()) {
					return false;
				}
                masterindex_request = js5_client.request_file(255, 255, (byte) 0, true);
            }
            if (masterindex_request.in_progress) {
				return false;
			}
            masterindex_packet = new Packet(masterindex_request.get_data());
            archives = new Js5Archive[(masterindex_packet.byteBuffer.length - 5) / 8];
            return true;
        } catch (RuntimeException runtimeexception) {
            throw runtimeexception;
        }
    }

    public final Js5Archive create_archive(int archive_id, FileSystem fit_datafs, FileSystem archive_datafs) {
        return create_archive(archive_id, fit_datafs, true, archive_datafs);
    }

    public final void process() {
        if (archives != null) {
            for (Js5Archive archive1 : archives) {
                if (archive1 != null) {
					archive1.process();
				}
            }
            for (Js5Archive archive : archives) {
                if (archive != null) {
					archive.fetchgroup();
				}
            }
        }
    }

    public Js5Manager(Js5Client js5_client, AsyncCache async_cache) {
        this.async_cache = async_cache;
        this.js5_client = js5_client;
        if (!this.js5_client.more_than_20_priority_requests()) {
			masterindex_request = this.js5_client.request_file(255, 255, (byte) 0, true);
		}
    }

    public Js5Archive create_archive(int archive_id,
                                      FileSystem fit_datafs,
                                      boolean cache_in_ram, FileSystem archive_datafs) {
        try {
            if (masterindex_packet == null) {
				throw new RuntimeException("Not ready!");
			}
            if (archive_id < 0 || ~archive_id <= ~archives.length) {
				throw new RuntimeException("Invalid archiveid requested - archiveid:");
			}
            if (archives[archive_id] != null) {
				return archives[archive_id];
			}
            Js5Archive var_js5Archive;
			masterindex_packet.index = 5 + archive_id * 8;
            int fit_crc32 = masterindex_packet.g4();
            int fit_revision = masterindex_packet.g4();
            var_js5Archive = new Js5Archive(archive_id,
                    archive_datafs,
                    fit_datafs, js5_client, async_cache, fit_crc32, fit_revision, cache_in_ram);
            archives[archive_id] = var_js5Archive;
            return var_js5Archive;
        } catch (RuntimeException runtimeexception) {
            throw runtimeexception;
        }
    }
}
