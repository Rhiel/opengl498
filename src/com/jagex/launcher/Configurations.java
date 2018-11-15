package com.jagex.launcher;
import java.io.File;
import java.math.BigInteger;

/**
 * A class with configurations stored.
 * @author Emperor
 * 
 */
public final class Configurations {
	
	/**
	 * The website.
	 */
	public static final String WEBSITE = "http://www.zaros.rs";
	
	/**
	 * The client build.
	 */
	public static final int CLIENT_BUILD = 498;

	/**
	 * The sub build.
	 */
	public static final int SUB_BUILD = 16;
	
	/**
	 * The server port.
	 */
	public static int SERVER_PORT = 43594;
	
	/**
	 * The login theme song.
	 */
	public static final String LOGIN_THEME_SONG = "scape main";
	
	/**
	 * The cache name.
	 */
	public static final String CACHE_NAME = File.separator + ".zaros_" + CLIENT_BUILD;
	
	/**
	 * The RSA modulus.
	 */
	public static final BigInteger MODULUS = new BigInteger("119365899446067315932975991898363325061579719991294025359328021960040125142258621067848949689980866028232491082585431814345859060363748342297790362002830405818586025541018815563000741957417375211440504983329981059065255756529758598479962175681326119784430342275130902058984323109363665114655494006708620299283");

	/**
	 * The RSA exponent.
	 */
	public static final BigInteger PUBLIC_EXPONENT = new BigInteger("65537");

	/**
	 * If the walk packet should be send when doing eg. object actions.
	 */
	public static final boolean ENABLE_WALK_AID = false;
	
	/**
	 * The operation system name.
	 */
	public static final String OS_NAME = System.getProperty("os.name").toUpperCase();
	
	/**
	 * The cache path.
	 * @return the path.
	 */
	public static String getCachePath() { 
		final String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN")) {
			return new StringBuilder(System.getProperty("user.home") + CACHE_NAME).toString();
		} else if (OS.contains("MAC")) {
			return new StringBuilder(System.getProperty("user.home") + CACHE_NAME).toString();
		} else if (OS.contains("NUX")) {
			return System.getProperty("user.home") + CACHE_NAME;
		}
		return new StringBuilder(System.getProperty("user.dir")).toString() + CACHE_NAME;
	}
}