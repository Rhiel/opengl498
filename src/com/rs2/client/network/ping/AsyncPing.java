package com.rs2.client.network.ping;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.jagex.TimeTools;

import jaclib.ping.Ping;

/**
 * @author Walied K. Yassen
 */
public final class AsyncPing implements Runnable {

	public String host;
	public InetAddress address;
	public volatile long ping;
	public volatile boolean running;

	public AsyncPing() {
		ping = -1L;
		running = true;
	}

	public void init(String host) {
		this.host = host;
		address = null;
		ping = -1L;
		if (this.host != null) {
			try {
				address = InetAddress.getByName(host);
			} catch (UnknownHostException e) {
				// NOOP
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			ping();
		}
	}

	public void ping() {
		if (address != null) {
			try {
				byte[] ip = address.getAddress();
				ping = Ping.ping(ip[0], ip[1], ip[2], ip[3], 10000L);
			} catch (Throwable e) {
				// NOOP
			}
		}
		TimeTools.sleep(1000L);
	}

	public void stop() {
		running = false;
	}

	public long getPing() {
		return ping;
	}
}
