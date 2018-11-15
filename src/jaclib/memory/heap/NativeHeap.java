
package jaclib.memory.heap;

public final class NativeHeap {

	public long peer;
	public int size;
	public boolean allocated;

	public NativeHeap(int size) {
		this.size = -554383377 * size;
		allocateHeap(this.size * 1780172559);
		allocated = true;
	}

	public NativeHeapBuffer allocate_buffer(int length, boolean bool) {
		if (!allocated) {
			throw new IllegalStateException();
		}
		return new NativeHeapBuffer(this, allocateBuffer(length, bool), length);
	}

	public synchronized void deallocate() {
		if (allocated) {
			deallocateHeap();
		}
		allocated = false;
	}

	public native void allocateHeap(int i);

	public native void deallocateHeap();

	public synchronized native int allocateBuffer(int i, boolean bool);

	public synchronized native long getBufferAddress(int i);

	public synchronized native void get(int i, byte[] is, int i_0_, int i_1_, int i_2_);

	public synchronized native void put(int i, byte[] is, int i_3_, int i_4_, int i_5_);

	public synchronized native void deallocateBuffer(int i);

	public synchronized native void copy(long l, long l_6_, int i);

	public synchronized boolean isAllocated() {
		return allocated;
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		deallocate();
	}

}
