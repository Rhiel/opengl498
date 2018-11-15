
package jaclib.ping;

public abstract class IcmpService implements Runnable {

	public abstract void notify(int i);

	public abstract void notify(int i, int i_0_, int i_1_);

	@Override
	public native void run();

	public native void quit();

	public static native boolean available();

	public native void l();

	public native void b();

	public native void k();

	public native void q();

	public abstract void r(int i);

	public abstract void j(int i);

	public abstract void i(int i, int i_2_, int i_3_);
}
