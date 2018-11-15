package com.jagex;

/**
 * Holds all the constants that are related to our tiles system.
 * 
 * @author Walied K. Yassen
 */
public interface TileConstants {
	/**
	 * The tile size in bits.
	 */
	int SIZE_BITS = 7;
	/**
	 * The tile size in graphics unit.
	 */
	int SIZE_1BY1 = 1 << SIZE_BITS;

	/**
	 * The half tile size.
	 */
	int SIZE_1BY2 = SIZE_1BY1 / 2;

	/**
	 * The one quarter tile size.
	 */
	int SIZE_1BY4 = SIZE_1BY2 / 2;

	/**
	 * The three quarters tile size.
	 */
	int SIZE_3BY4 = SIZE_1BY4 * 3;
}
