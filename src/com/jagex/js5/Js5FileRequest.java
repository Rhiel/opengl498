package com.jagex.js5;

import com.jagex.Packet;

/**
 * Created by Chris on 5/31/2017.
 */
public final class Js5FileRequest extends FileRequest {
    public        int    block_pos;
    public        byte   extra_size;
    public static int    get_data_callcount;
    public static int    get_progress_callcount;
    public Packet packet;

    @Override
	public final int get_progress() {
        get_progress_callcount++;
        if (packet == null) {
			return 0;
		}
		return packet.index * 100 / (packet.byteBuffer.length - extra_size);
    }

    @Override
	public final byte[] get_data() {
        try {
            get_data_callcount++;
			if (in_progress || packet.index < packet.byteBuffer.length - extra_size) {
				throw new RuntimeException();
			}
            return packet.byteBuffer;
        } catch (RuntimeException runtimeexception) {
            throw runtimeexception;
        }
    }
}