package com.jagex;

public abstract class SceneNode {

	public abstract void draw2(int i, int i_14_, int i_15_, int i_16_, int i_17_, int i_18_, int i_19_, int i_20_, long l, int bufferOffset);

	public abstract void update_shadows(int var1, int var2, int var3, int var4, int var5);

	public void sharelight(SceneNode abstractModel_0_, int i, int i_1_, int i_2_, boolean bool) {

	}

	public SceneNode method994(int i, int i_12_, int i_13_) {
		return this;
	}

	public boolean method998() {
		return false;
	}

	public abstract int get_miny();

	public void clean_memory() {

	}
}
