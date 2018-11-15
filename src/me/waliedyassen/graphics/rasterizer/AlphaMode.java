package me.waliedyassen.graphics.rasterizer;

/**
 * Represents the alpha value clipping type.
 * 
 * @author Walied K. Yassen
 */
public enum AlphaMode {

	/**
	 * The alpha is always opaque.
	 */
	ALPHA_TESTED,

	/**
	 * The alpha is tested to be opaque or not.
	 */
	ALPHA_OPAQUE,

	/**
	 * The alpha is blended and tested.
	 */
	ALPHA_BLENDED,

}
