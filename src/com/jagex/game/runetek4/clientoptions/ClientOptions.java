package com.jagex.game.runetek4.clientoptions;

import com.jagex.SignLink;
import com.jagex.graphics.runetek4.opengl.GLManager;

/**
 * @author Walied K. Yassen
 */
public final class ClientOptions {
	public static boolean clientoption_highdetails_lighting = true;
	public static boolean clientoption_highdetails_water = true;
	public static boolean clientoption_enable_fog = true;
	public static int clientoption_brightness = 2;
	public static int clientoption_antialiasing = 1;
	public static boolean clientoption_removeroofs = false;
	public static boolean clientoptions_updated = true;
	public static boolean clientoption_stereo = true;
	public static boolean clientoption_grounddecor = true;
	public static boolean clientoption_idleanims_many = true;
	public static boolean clientoption_flickering_on = true;
	public static int clientoption_hardshadows = 2;
	public static boolean clientoption_spotshadows = true;
	public static boolean clientoption_textures = true;
	public static int clientoption_antialiasing_default = 1;
	public static boolean clientoption_removeroofs_override = false;
	public static boolean clientoption_removeroofs_selective = true;

	public static void save(SignLink signlink) {
		// NOOP
	}

	public static void apply_changes() {
		// Apply changes to the scene.
	}

	public static boolean is_removeroofs() {
		return GLManager.opengl_mode ? true : clientoption_removeroofs;
	}

	public static void set_removeroofs(boolean removeroofs) {
		clientoption_removeroofs = removeroofs;
	}

	public ClientOptions() {
		// NOOP
	}

}
