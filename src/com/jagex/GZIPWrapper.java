package com.jagex;

import java.util.zip.Inflater;

public class GZIPWrapper {
	public Inflater inflater;

	public GZIPWrapper() {
		this(-1, 1000000, 1000000);
	}

	public GZIPWrapper(int i, int i_2_, int i_3_) {
		// NOOP
	}

	public void decompress(byte[] output, boolean bool, Packet stream) {
		if (stream.byteBuffer[stream.index] != 31 || stream.byteBuffer[1 + stream.index] != -117) {
			throw new RuntimeException("Invalid GZIP header!");
		}
		if (inflater == null) {
			inflater = new Inflater(true);
		}
		try {
			inflater.setInput(stream.byteBuffer, stream.index + 10, -10 + -stream.index - 8 + stream.byteBuffer.length);
			inflater.inflate(output);
			inflater.reset();
		} catch (Exception exception) {
			inflater.reset();
			throw new RuntimeException("Invalid GZIP compressed data!");
		}
	}
}
