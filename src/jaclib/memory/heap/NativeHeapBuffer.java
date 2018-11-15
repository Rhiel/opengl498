
package jaclib.memory.heap;

import jaclib.memory.Buffer;
import jaclib.memory.Source;

public final class NativeHeapBuffer implements Buffer, Source {
	public int size;
	public NativeHeap j;
	public int handle;
	public boolean p = true;

	NativeHeapBuffer(NativeHeap nativeheap, int i, int i_0_) {
		j = nativeheap;
		this.handle = i * 468492457;
		size = i_0_ * 852498195;
	}

	public final synchronized boolean w() {
		return j.isAllocated() && p;
	}

	@Override
	public final long getAddress() {
		return j.getBufferAddress(1633813401 * handle);
	}

	@Override
	public final int getSize() {
		return 1072253723 * size;
	}

	@Override
	public final synchronized void put(byte[] is, int i, int i_1_, int i_2_) {
		if (!w() | is == null | i < 0 | i_2_ + i > is.length | i_1_ < 0 | i_2_ + i_1_ > size * 1072253723)
			throw new RuntimeException();
		j.put(1633813401 * this.handle, is, i, i_1_, i_2_);
	}

	public final synchronized void deallocate() {
		if (w())
			j.deallocateBuffer(handle * 1633813401);
		p = false;
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		deallocate();
	}

	@Override
	public final synchronized void p(byte[] is, int i, int i_3_, int i_4_) {
		if (!w() | is == null | i < 0 | i_4_ + i > is.length | i_3_ < 0 | i_4_ + i_3_ > size * 1072253723)
			throw new RuntimeException();
		j.put(1633813401 * this.handle, is, i, i_3_, i_4_);
	}

	@Override
	public final synchronized void o(byte[] is, int i, int i_5_, int i_6_) {
		if (!w() | is == null | i < 0 | i_6_ + i > is.length | i_5_ < 0 | i_6_ + i_5_ > size * 1072253723)
			throw new RuntimeException();
		j.put(1633813401 * this.handle, is, i, i_5_, i_6_);
	}

	public final synchronized void gg() throws Throwable {
		super.finalize();
		deallocate();
	}

	public final synchronized void gy() throws Throwable {
		super.finalize();
		deallocate();
	}

	@Override
	public final int s() {
		return 1072253723 * size;
	}

	@Override
	public final long f() {
		return j.getBufferAddress(1633813401 * handle);
	}

	@Override
	public final long z() {
		return j.getBufferAddress(1633813401 * handle);
	}

	public final synchronized boolean l() {
		return j.isAllocated() && p;
	}
}
