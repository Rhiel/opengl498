package com.jagex.launcher;

import com.jagex.FileOnDisk;
import com.jagex.launcher.logger.Logger;

public final class GameSetting {

	/**
	 * The name of the game.
	 */
	public final String name;
	
	/**
	 * The ip to the game to connect to.
	 */
	public String ip;
	
	/**
	 * Represents the world if to load.
	 */
	public final int world;
	
	/**
	 * Represents the environment of the game.
	 */
	public final String environment;
	
	/**
	 * If the client should use a lower amount of memory.
	 */
	public final boolean lowMemory;
	
	/**
	 * If we're using the swift kit client.
	 */
	public boolean swiftKit;
	
	/**
	 * Constructs a new {@code GameSetting} {@code Object}
	 * @param name the name.
	 * @param ip the ip.
	 * @param world the world.
	 * @param environment the enviornment.
	 * @param lowMemory if low memory.
	 * @param swift kit if we're using it.
	 */
	public GameSetting(String name, String ip, int world, String environment, boolean lowMemory, boolean swiftKit) {
		this.name = name;
		if (ip == null) {
			FileOnDisk.method18();
			ip = new String(Logger.method71());
		}
		this.ip = ip;
		this.world = world;
		this.environment = environment;
		this.lowMemory = lowMemory;
		this.swiftKit = swiftKit;
	}
	
	/**
	 * If we're using a swift kit client.
	 * @return true if so.
	 */
	public boolean isSwiftKit() {
		return swiftKit;
	}
	
	/** 
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the world
	 */
	public int getWorld() {
		return world;
	}
	
	/**
	 * @return the enviornment
	 */
	public String getEnvironment() {
		return environment;
	}
	
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Checks if the client has th low memory flag enabled.
	 * @return {@code True} if so.
	 */
	public boolean isLowMemory() {
		return lowMemory;
	}

	/**
	 * Sets the ip.
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Sets the swiftKit.
	 * @param swiftKit the swiftKit to set
	 */
	public void setSwiftKit(boolean swiftKit) {
		this.swiftKit = swiftKit;
	}
}
