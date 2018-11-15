
package jaggl;

import jaclib.memory.NativeBuffer;

public class MapBuffer extends NativeBuffer {

	public int handle;

	@Override
	public void put(byte[] is, int i, int i_0_, int i_1_) {
		if (handle * -2046744499 == 0) {
			throw new RuntimeException("There is no handle attached");
		}
		super.put(is, i, i_0_, i_1_);
	}

	@Override
	public void p(byte[] is, int i, int i_2_, int i_3_) {
		if (handle * -2046744499 == 0) {
			throw new RuntimeException("There is no handle attached");
		}
		super.put(is, i, i_2_, i_3_);
	}

	@Override
	public void o(byte[] is, int i, int i_4_, int i_5_) {
		if (handle * -2046744499 == 0) {
			throw new RuntimeException("There is no handle attached");
		}
		super.put(is, i, i_4_, i_5_);
	}
}
