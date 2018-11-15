package com.jagex.launcher;

import java.awt.Frame;

import com.jagex.GameClient;
import com.jagex.launcher.logger.Logger;

public final class GameLaunch {

	/**
	 * The default name.
	 */
	public static final String DEFAULT_NAME = "Zaros";

	/**
	 * Represents the {@link GameSetting} of this game.
	 */
	public static GameSetting SETTINGS = new GameSetting("Zaros", "185.62.190.211", 1, "live", false, false);

	/**
	 * The main method.
	 * @param args the arguments casted on runtime.
	 */
	public static void main(String[]args) {
		Logger.log("Launching game client...");
		if (args.length == 1) {
			launch(Boolean.parseBoolean(args[0]));
		} else {
			launch(false);
		}
	}
	
	public static void launch(boolean swiftkit) {
		if (swiftkit) {
			GameLaunch.SETTINGS.setSwiftKit(true);
		} else {
			GameLaunch.SETTINGS.setSwiftKit(false);
		}
		GameClient.create(GameLaunch.SETTINGS).launch(new Frame());
	}

	/**
	 * Parses the game arguments.
	 * @param args the arguments.
	 * @return the game setting.
	 */
	public static GameSetting parseArguments(String...args) {
		if (args.length < 4) {
			System.err.println("Invalid arguments specified - usage: ip_address live/office/local world_id lowmem/highmem port(optional)");
			return SETTINGS;
		}
		String address = args[0].toLowerCase();
		String ip = address;
		if (address.equals("localhost")) {
			ip = "127.0.0.1";
		}
		else if (address.equals("gameip")) {
			ip = SETTINGS.getIp();
		}
		String environment = args[1];
		int worldId = Integer.parseInt(args[2]);
		boolean lowMemory = args[3].equals("lowmem");
		if (args.length > 4) {
			Configurations.SERVER_PORT = Integer.parseInt(args[4]);
		}
		boolean swift = true;
		if (args.length > 5) {
			swift = Boolean.parseBoolean(args[5]);
		}
		return new GameSetting(DEFAULT_NAME, ip, worldId, environment, lowMemory, swift);
	}

	/**
	 * Gets the settings.
	 * @return the setting
	 */
	public static GameSetting getSetting() {
		return SETTINGS;
	}
}