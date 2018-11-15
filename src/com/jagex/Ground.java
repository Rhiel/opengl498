package com.jagex;

public class Ground extends Linkable {

	public int grid_level;
	public int grid_x;
	public int grid_y;
	public int anInt2241;
	public int anInt2014;
	public int anInt2015;
	public int anInt2017;
	public boolean aBoolean2021;
	public Ground aClass23_Sub1_2022;
	public int[] anIntArray2024;
	public GroundObjEntity obj_entity;
	public int anInt2027;
	public InteractiveEntity[] interactives;
	public int num_interactives;
	public WallObject wall_object;
	public int logicHeight;
	public boolean aBoolean2033;
	public GroundDecoration decoration;
	public boolean aBoolean2036;
	public WallDecoration wall_decoration;
	public int flags = 0;
	public PlainTile plain_tile;
	public ShapedTile shaped_tile;

	public Ground(int level, int grid_x, int grid_y) {
		anInt2017 = this.grid_level = level;
		this.grid_x = grid_x;
		this.grid_y = grid_y;
		interactives = new InteractiveEntity[5];
		anIntArray2024 = new int[5];
	}
}
