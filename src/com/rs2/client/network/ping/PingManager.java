package com.rs2.client.network.ping;

/**
 * @author Walied K. Yassen
 */
public final class PingManager {

	private static int threadCounter = 0;

	private final String address;
	private final AsyncPing ping;

	public PingManager(String address) {
		this.address = address;
		ping = new AsyncPing();
		Thread pingThread = new Thread(ping);
		pingThread.setName("PingThread#-" + threadCounter++);
		pingThread.setPriority(Thread.MIN_PRIORITY);
		pingThread.start();
	}

	public void connect() {
		ping.init(address);
	}

	public void disconnect() {
		ping.init(null);
	}

	public long getPing() {
		return ping.ping;
	}
}
