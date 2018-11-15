package com.jagex.graphics.runetek4.opengl.effects;

interface MaterialEffect {

	void enable();

	void disable();

	void update_param(int var1);

	int get_render_settings();
}
