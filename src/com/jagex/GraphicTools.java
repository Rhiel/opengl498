package com.jagex;

import me.waliedyassen.graphics.rasterizer.Rasterizer;
import me.waliedyassen.materials.MaterialRawList;
import me.waliedyassen.textures.TextureCache;

/**
 * Contains all our tools for the graphics engine.
 * 
 * @author Walied K. Yassen
 */
public final class GraphicTools {

	/**
	 * The {@link Rasterizer} tool.
	 */
	public static Rasterizer rasterizer;

	/**
	 * The materials list.
	 */
	public static MaterialRawList materials;

	/**
	 * The textures cache.
	 */
	public static TextureCache textures;

	/**
	 * Initialises the rasterizer tool.
	 * 
	 * @param materials the materials list.
	 * @param textures  the textures cache.
	 */
	public static void initialise_rasterizer(MaterialRawList materials, TextureCache textures) {
		if (rasterizer != null) {
			throw new IllegalStateException("The rasterizer was already initialised!");
		}
		GraphicTools.materials = materials;
		GraphicTools.textures = textures;
		rasterizer = new Rasterizer(materials, textures);
	}

	/**
	 * Changes the global colour brightness.
	 * 
	 * @param brightness the colour brightness.
	 */
	public static void change_brightness(double brightness) {
		if (rasterizer == null) {
			throw new IllegalStateException("The rasterizer was not initialised!");
		}
		rasterizer.brightness = brightness;
		ColourUtil.initialiseColorTable(true, true);
	}

	/**
	 * Performs a cache clean-up.
	 */
	public static void clean_up() {
		textures.clear();
	}

	/**
	 * Gets the {@link Rasterizer} tool object instance.
	 * 
	 * @return the {@link Rasterizer} tool if it was present otherwise {@code null}.
	 */
	public static Rasterizer get_rasterizer() {
		return rasterizer;
	}

	/**
	 * Gets the materials list.
	 * 
	 * @return the materials list.
	 */
	public static MaterialRawList get_materials() {
		return materials;
	}

	/**
	 * Gets the textures list.
	 * 
	 * @return the textures list.
	 */
	public static TextureCache get_textures() {
		return textures;
	}

	// prevents construction
	public GraphicTools() {
		// NOOP
	}

}
