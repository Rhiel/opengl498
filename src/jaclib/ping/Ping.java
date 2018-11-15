
package jaclib.ping;

public class Ping {

	public Ping() throws Throwable {
		throw new Error(getClass().getSimpleName() + " cannot be constructed!");
	}

	public static int ping(byte r1, byte r2, byte c1, byte c2, long time) throws Throwable {
		int ping = ping0(r1, r2, c1, c2, time);
		if (ping < 0) {
			throw new Exception("Ngative ping received: " + ping);
		}
		return ping;
	}

	public static native boolean init();

	public static native int ping0(byte r1, byte r2, byte c1, byte c2, long time);

	public static native void quit();

}
