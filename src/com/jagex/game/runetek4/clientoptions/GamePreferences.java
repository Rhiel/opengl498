package com.jagex.game.runetek4.clientoptions;

/**
 * Created by Chris on 4/24/2017.
 */
public class GamePreferences {

	public static int windowMode = 1;
	public static int marginMode = 2;

	public GamePreferences() {
	}

	public static Class getClassByName(String string) {
		try {
			// System.out.println(string);
			return Class.forName(string);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
}
