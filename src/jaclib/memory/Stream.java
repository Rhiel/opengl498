
package jaclib.memory;

public final class Stream {
	public Buffer buffer;
	public int length;
	public int offset;
	public int local_offset;
	public byte[] local_data;
	public static boolean s = getLSB(-65536) == -1;

	public Stream() {
		this(4096);
	}

	public Stream(int i) {
		local_data = new byte[i];
	}

	public Stream(Buffer buffer) {
		this(buffer, 0, buffer.getSize());
	}

	public Stream(Buffer buffer, int offset, int size) {
		this(buffer.getSize() < 4096 ? buffer.getSize() : 4096);
		initialize(buffer, offset, size);
	}

	public void initialize(Buffer buffer, int offset, int length) {
		flush();
		this.buffer = buffer;
		this.offset = offset;
		this.length = length + offset;
		if (this.length > buffer.getSize()) {
			throw new RuntimeException();
		}
	}

	public int getOffset() {
		return local_offset + offset;
	}

	public void setOffset(int offset) {
		flush();
		this.offset = offset;
	}

	public void writeByte(int value) {
		if (local_offset >= local_data.length) {
			flush();
		}
		local_data[local_offset++] = (byte) value;
	}

	public void writeFloat(float value) {
		int i = floatToRawIntBits(value);
		writeInt(i);
	}

	public void writeInt(int value) {
		if (3 + local_offset >= local_data.length) {
			flush();
		}
		local_data[local_offset++] = (byte) (value >> 24);
		local_data[local_offset++] = (byte) (value >> 16);
		local_data[local_offset++] = (byte) (value >> 8);
		local_data[local_offset++] = (byte) value;
	}

	public void writeFloatLE(float value) {
		int i = floatToRawIntBits(value);
		writeIntLE(i);
	}

	public void writeIntLE(int i) {
		if (local_offset + 3 >= local_data.length) {
			flush();
		}
		local_data[local_offset++] = (byte) i;
		local_data[local_offset++] = (byte) (i >> 8);
		local_data[local_offset++] = (byte) (i >> 16);
		local_data[local_offset++] = (byte) (i >> 24);
	}

	public void flush() {
		if (local_offset > 0) {
			if (offset + local_offset > length) {
				throw new RuntimeException();
			}
			buffer.put(local_data, 0, offset, local_offset);
			offset += local_offset;
			local_offset = 0;
		}
	}

	public static final boolean z() {
		return s;
	}

	public static native int floatToRawIntBits(float f);

	public static final native byte getLSB(int i);

}
