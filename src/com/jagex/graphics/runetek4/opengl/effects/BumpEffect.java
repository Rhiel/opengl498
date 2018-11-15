package com.jagex.graphics.runetek4.opengl.effects;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.opengl.GLState;

public class BumpEffect implements MaterialEffect {

	@Override
	public void disable() {
		if (ClientOptions.clientoption_highdetails_lighting) {
			GLState.set_lights_enabled(true);
		}
	}

	@Override
	public void enable() {
		if (ClientOptions.clientoption_highdetails_lighting) {
			GLState.set_lights_enabled(false);
		}
	}

	@Override
	public void update_param(int var1) {
	}

	@Override
	public int get_render_settings() {
		return 0;
	}
}
