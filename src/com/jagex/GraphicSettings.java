package com.jagex;

public class GraphicSettings {

	public static boolean textures = true;
	public static boolean ground_blending = true;
	public static boolean details = true;

	public static void setTextures(boolean textures) {
		GraphicSettings.textures = textures;
	}

	public static boolean hasTextures() {
		return textures;
	}

	public static void setGroundBlending(boolean ground_blending) {
		GraphicSettings.ground_blending = ground_blending;
		MapLoader.load_region();
	}

	public static boolean hasGroundBlending() {
		return ground_blending;
	}

	public static void setDetails(boolean details) {
		GraphicSettings.details = details;
		// TODO: uncache all models.
	}

	public static boolean hasDetails() {
		return details;
	}

}
