package com.jagex.js5;

import com.jagex.FileSystem;
import com.jagex.HashTable;
import com.jagex.Js5Client;
import com.jagex.Linkable;
import com.jagex.NodeDeque;
import com.jagex.TimeTools;

/**
 * Created by Chris on 6/1/2017.
 */
public final class Js5Archive extends Archive {

	public static final int VERIFY = 1;
	public static final int PREFETCH = 2;
	public static int N;
	public static int f_i;
	public static java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
	public Js5Index index;
	public static int f_l;
	public static int get_index;
	public static int request_group_callcount;
	public int index_crc32;
	public FileSystem index_datafs;
	public static int get_group_data_callcount;
	public int indexversion;
	public int archive_id;
	public HashTable request_map;
	public AsyncCache async_cache;
	public Js5Client js5_client;
	public static int get_group_progress_callcount;
	public int s = 0;
	public static int fetch_all_groups_with_files;
	public FileSystem archive_datafs;
	public static int process_callcount;
	public FileRequest index_request;
	public static int H;
	public static int get_initialization_progress_callcount;
	public static int w;
	public byte[] group_status;
	public int current_group_id;
	public NodeDeque active_requests;
	public boolean have_cache;
	public static int p;
	public boolean prefetch_groups;
	public NodeDeque group_requests;
	public long next_cleanup;
	public boolean cache_in_mem;

	@Override
	public final byte[] get_group_data(int group_id) {
		get_group_data_callcount++;
		FileRequest var_fileRequest = fetchgroup_inner(0, group_id);
		if (var_fileRequest == null) {
			return null;
		}
		byte[] is = var_fileRequest.get_data();
		var_fileRequest.unlink();
		return is;
	}

	public final int b(byte i) {
		f_l++;
		if (index == null) {
			return 0;
		}
		if (!have_cache) {
			return index.fit_entry_count;
		}
		Linkable node = group_requests.get_first();
		if (node == null) {
			return 0;
		}
		return (int) node.uid;
	}

	public final void fetchgroup() {
		try {
			fetch_all_groups_with_files++;
			if (group_requests != null) {
				if (get_index() == null) {
					return;
				}
				if (!have_cache) {
					if (prefetch_groups) {
						boolean have_all_files = true;
						for (Linkable node = group_requests.get_first(); node != null; node = group_requests.get_next()) {
							int group_id = (int) node.uid;
							if (group_status[group_id] != 1) {
								fetchgroup_inner(PREFETCH, group_id);
							}
							if (group_status[group_id] != 1) {
								have_all_files = false;
							} else {
								node.unlink();
							}
						}
						while (current_group_id < index.group_entry_count.length) {
							if (index.group_entry_count[current_group_id] == 0) {
								current_group_id++;
							} else {
								if (js5_client.more_than_20_normal_requests()) {
									have_all_files = false;
									break;
								}
								if (group_status[current_group_id] != 1) {
									fetchgroup_inner(PREFETCH, current_group_id);
								}
								if (group_status[current_group_id] != 1) {
									Linkable node = new Linkable();
									node.uid = current_group_id;
									have_all_files = false;
									group_requests.add_last(node);
								}
								current_group_id++;
							}
						}
						if (have_all_files) {
							prefetch_groups = false;
							current_group_id = 0;
						}
					} else {
						group_requests = null;
					}
				} else {
					boolean read_all_files = true;
					for (Linkable node = group_requests.get_first(); node != null; node = group_requests.get_next()) {
						int group_id = (int) node.uid;
						if (group_status[group_id] == 0) {
							fetchgroup_inner(VERIFY, group_id);
						}
						if (group_status[group_id] == 0) {
							read_all_files = false;
						} else {
							node.unlink();
						}
					}
					while (current_group_id < index.group_entry_count.length) {
						if (index.group_entry_count[current_group_id] == 0) {
							current_group_id++;
						} else {
							if (async_cache.request_count >= 250) {
								read_all_files = false;
								break;
							}
							if (group_status[current_group_id] == 0) {
								fetchgroup_inner(VERIFY, current_group_id);
							}
							if (group_status[current_group_id] == 0) {
								Linkable node = new Linkable();
								node.uid = current_group_id;
								group_requests.add_last(node);
								read_all_files = false;
							}
							current_group_id++;
						}
					}
					if (read_all_files) {
						current_group_id = 0;
						have_cache = false;
					}
				}
			}
			if (!cache_in_mem || TimeTools.getMillis() < next_cleanup) {
				return;
			}
			for (FileRequest var_fileRequest = (FileRequest) request_map.get_first(); var_fileRequest != null; var_fileRequest = (FileRequest) request_map.get_next()) {
				if (!var_fileRequest.in_progress) {
					if (var_fileRequest.v) {
						if (!var_fileRequest.isPriority) {
							throw new RuntimeException("Unexpected non-urgent orphan!");
						}
						var_fileRequest.unlink();
					} else {
						var_fileRequest.v = true;
					}
				}
			}
			next_cleanup = TimeTools.getMillis() + 1000L;
		} catch (RuntimeException runtimeexception) {
			throw runtimeexception;
		}
	}

	public final void process() {
		process_callcount++;
		if (group_requests != null && get_index() != null) {
			for (Linkable node = active_requests.get_first(); node != null; node = active_requests.get_next()) {
				int group_id = (int) node.uid;
				if (group_id < 0 || index.group_count <= group_id || index.group_entry_count[group_id] == 0) {
					node.unlink();
				} else {
					if (group_status[group_id] == 0) {
						fetchgroup_inner(VERIFY, group_id);// Write to cache
					}
					if (group_status[group_id] == -1) {
						fetchgroup_inner(PREFETCH, group_id); // Send normal request
					}

					if (group_status[group_id] == 1) {
						node.unlink(); // Done
					}
				}
			}
		}
	}

	@Override
	public final Js5Index get_index() {
		try {
			get_index++;
			if (index != null) {
				return index;
			}
			if (index_request == null) {
				if (js5_client.more_than_20_priority_requests()) {
					return null;
				}
				index_request = js5_client.request_file(255, archive_id, (byte) 0, true);
			}
			if (index_request.in_progress) {
				return null;
			}
			byte[] indexdata = index_request.get_data();
			if (index_request instanceof CacheFileRequest) {
				try {
					if (indexdata == null) {
						throw new RuntimeException("Index not found in disk cache");
					}
					index = new Js5Index(indexdata, index_crc32);
					if (index.indexversion != indexversion) {
						throw new RuntimeException("Index version wrong - index.indexversion:" + index.indexversion + " expected:" + indexversion);
					}
				} catch (RuntimeException runtimeexception) {
					index = null;
					if (!js5_client.more_than_20_priority_requests()) {
						index_request = js5_client.request_file(255, archive_id, (byte) 0, true);
					} else {
						index_request = null;
					}
					return null;
				}
			} else {
				try {
					if (indexdata == null) {
						throw new RuntimeException("Failed to download index from server!");
					}
					index = new Js5Index(indexdata, index_crc32);
				} catch (RuntimeException runtimeexception) {
					runtimeexception.printStackTrace();
					js5_client.enable_encryption();
					index = null;
					if (!js5_client.more_than_20_priority_requests()) {
						index_request = js5_client.request_file(255, archive_id, (byte) 0, true);
					} else {
						index_request = null;
					}
					return null;
				}
				if (index_datafs != null) {
					async_cache.put(index_datafs, archive_id, indexdata);
				}
			}
			index_request = null;
			if (archive_datafs != null) {
				group_status = new byte[index.group_count];
				s = 0;
			}
			return index;
		} catch (RuntimeException runtimeexception) {
			throw runtimeexception;
		}
	}

	public final void prefetch_groups() {
		f_i++;
		if (archive_datafs != null) {
			prefetch_groups = true;
			if (group_requests == null) {
				group_requests = new NodeDeque();
			}
		}
	}

	public final int get_group_count() {
		N++;
		if (index == null) {
			return 0;
		}
		return index.fit_entry_count;
	}

	public final int d(int i) {
		p++;
		return s;
	}

	public final FileRequest fetchgroup_inner(int action, int group_id) {
		try {
			H++;
			FileRequest request = (FileRequest) request_map.get(group_id);
			if (request != null && action == 0 && !request.isPriority && request.in_progress) {
				request.unlink();
				request = null;
			}
			if (request == null) {
				if (action == 0) {   // SignlinkRequest or get from cache
					if (archive_datafs == null || group_status[group_id] == -1) {
						if (js5_client.more_than_20_priority_requests()) {
							return null;
						}
						request = js5_client.request_file(archive_id, group_id, (byte) 2, true);
					} else {
						request = async_cache.get(archive_datafs, group_id);
					}
				} else if (action == 1) { // Write to cache
					if (archive_datafs == null) {
						throw new RuntimeException("fetchgroup_inner - VERIFY requested but no datafs available!");
					}
					request = async_cache.post_request(archive_datafs, group_id, 3);
				} else if (action == 2) {
					if (archive_datafs == null) {
						throw new RuntimeException("fetchgroup_inner - PREFETCH requested but no datafs available!");
					}
					if (group_status[group_id] != -1) {
						throw new RuntimeException("fetchgroup_inner - PREFETCH requested, but cache isn't known invalid!");
					}
					if (js5_client.more_than_20_normal_requests()) {
						return null;
					}
					request = js5_client.request_file(archive_id, group_id, (byte) 2, false);
				} else {
					throw new RuntimeException("Invalid fetchgroup mode!");
				}
				request_map.put(group_id, request);
			}
			if (request.in_progress) {
				return null;
			}
			byte[] request_data = request.get_data();
			if (request instanceof CacheFileRequest) {
				try {
					if (request_data == null || request_data.length <= 2) {
						throw new RuntimeException("Data not in cache - data: " + action);
					}
					crc32.reset();
					crc32.update(request_data, 0, request_data.length - 2);
					int crc32 = (int) Js5Archive.crc32.getValue();
					if (index.group_crc32[group_id] != crc32) {
						throw new RuntimeException("Disk fetch CRC incorrect");
					}
					int revision = ((0xff & request_data[-2 + request_data.length]) << 8) + (0xff & request_data[-1 + request_data.length]);
					if (revision != index.group_revision[group_id]) {
						throw new RuntimeException("Version incorrect - wanted:" + index.group_revision[group_id] + " got:" + revision);
					}
					if (group_status[group_id] != 1) {
						s++;
						group_status[group_id] = (byte) 1;
					}
					if (!request.isPriority) {
						request.unlink();
					}
					return request;
				} catch (Exception exception) {
					// exception.printStackTrace();
					group_status[group_id] = (byte) -1;
					request.unlink();
					if (request.isPriority && !js5_client.more_than_20_priority_requests()) {
						Js5FileRequest var_cj = js5_client.request_file(archive_id, group_id, (byte) 2, true);
						request_map.put(group_id, var_cj);
					}
					return null;
				}
			} else {
				try {
					if (request_data == null || request_data.length <= 2) {
						throw new RuntimeException("Data from server too small - data:");
					}
					crc32.reset();
					crc32.update(request_data, 0, request_data.length - 2);
					int request_crc32 = (int) crc32.getValue();
					if (request_crc32 != index.group_crc32[group_id]) {
						throw new RuntimeException("Net fetch CRC incorrect");
					}
					js5_client.reconnect_count = 0;
					js5_client.status = 0;
				} catch (RuntimeException runtimeexception) {
					js5_client.enable_encryption();
					runtimeexception.printStackTrace();
					request.unlink();
					if (request.isPriority && !js5_client.more_than_20_priority_requests()) {
						Js5FileRequest request2 = js5_client.request_file(archive_id, group_id, (byte) 2, true);
						request_map.put(group_id, request2);
					}
					return null;
				}
				request_data[request_data.length - 2] = (byte) (index.group_revision[group_id] >>> 8);
				request_data[-1 + request_data.length] = (byte) index.group_revision[group_id];
				if (archive_datafs != null) {
					async_cache.put(archive_datafs, group_id, request_data);
					if (group_status[group_id] != 1) {
						s++;
						group_status[group_id] = (byte) 1;
					}
				}
				if (!request.isPriority) {
					request.unlink();
				}
				return request;
			}
		} catch (RuntimeException runtimeexception) {
			throw runtimeexception;
		}
	}

	@Override
	public final int get_group_progress(int group_id) {
		get_group_progress_callcount++;
		FileRequest var_fileRequest = (FileRequest) request_map.get(group_id);
		if (var_fileRequest != null) {
			return var_fileRequest.get_progress();
		}
		return 0;
	}

	@Override
	public final void request_group(int group_id) {
		request_group_callcount++;
		if (archive_datafs != null) {
			for (Linkable node = active_requests.get_first(); node != null; node = active_requests.get_next()) {
				if (~(long) group_id == ~node.uid) {
					return;
				}
			}
			Linkable node = new Linkable();
			node.uid = group_id;
			active_requests.add_last(node);
		}
	}

	public final int get_initialization_progress() {
		get_initialization_progress_callcount++;
		if (get_index() == null) {
			if (index_request == null) {
				return 0;
			}
			return index_request.get_progress();
		}
		return 100;
	}

	public Js5Archive(int i, FileSystem var_jl, FileSystem index_datafs, Js5Client js5_client, AsyncCache async_cache, int index_crc32, int indexversion, boolean bool) {
		request_map = new HashTable(16);
		current_group_id = 0;
		active_requests = new NodeDeque();
		next_cleanup = 0L;
		archive_datafs = var_jl;
		archive_id = i;
		do {
			if (archive_datafs != null) {
				have_cache = true;
				group_requests = new NodeDeque();
				break;
			}
			have_cache = false;
		} while (false);
		cache_in_mem = bool;
		this.indexversion = indexversion;
		this.async_cache = async_cache;
		this.index_crc32 = index_crc32;
		this.js5_client = js5_client;
		this.index_datafs = index_datafs;
		if (this.index_datafs != null) {
			index_request = this.async_cache.get(this.index_datafs, archive_id);
		}
	}
}
