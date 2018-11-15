package com.rs2.client.components.worldmap;

import com.jagex.Linkable;

public class WorldMapSquare extends Linkable {

	public int startx;
	public int starty;
	public int endx;
	public int endy;

	public WorldMapSquare(int startx, int starty, int endx, int endy) {
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
	}

	public boolean is_within(int x, int y) {
		return x >= startx && ~endx <= ~x && starty <= y && y <= endy;
	}
}
