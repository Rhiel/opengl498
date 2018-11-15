package com.jagex.js5;

import com.jagex.BZIPDecompressor;
import com.jagex.GZIPWrapper;
import com.jagex.Packet;

/**
 * Created by Chris on 6/1/2017.
 */
public class Js5Container {

	public static GZIPWrapper gzipper = new GZIPWrapper();
	public static final int MAX_ARCHIVE_LENGTH = 1024 * 1024 * 10;

	public static final byte[] get_payload(byte[] data) {
        Packet buffer = new Packet(data);
        int compression = buffer.g1();
		int packed_length = buffer.g4();
		if (packed_length < 0 || packed_length > MAX_ARCHIVE_LENGTH) {
			throw new RuntimeException("The packed length exceeds the maximum length allowed");
        }
		if (compression != 0) {
			int unpacked_length = buffer.g4();
			if (unpacked_length < 0 || unpacked_length > MAX_ARCHIVE_LENGTH) {
				throw new RuntimeException("The unpacked length exceeds the maximum length allowed");
            }
            byte[] unpacked_buffer = new byte[unpacked_length];
            if (compression == 1) { //bzip2
                BZIPDecompressor.decompress(unpacked_buffer, unpacked_length, data, packed_length, 9);
            } else { //gzip
                synchronized (gzipper) {
                    gzipper.decompress(unpacked_buffer, false, buffer);
                }
            }
            return unpacked_buffer;
        }
        byte[] packed_buffer = new byte[packed_length];
        buffer.get(packed_buffer, 0, packed_length);
        return packed_buffer;
    }

}
