package com.rs2.client.components.worldmap;

import com.jagex.NodeDeque;
import com.jagex.Packet;
import com.jagex.Queuable;
import com.jagex.RSString;

public class WorldMapArea extends Queuable {

	public int minx = 12800;
	public int miny = 12800;
	public int maxx = 0;
	public int maxy = 0;
	public int size = -1;
	public boolean selectable = true;
	public RSString areaname;
	public int origin_y;
	public int origin_x;
	public NodeDeque squares;
	public RSString filename;
	public int defaultzoom = -1;

	public WorldMapArea(RSString filename, RSString areaname, int origin_x, int origin_y, int size, boolean selectable, int defaultzoom) {
		this.filename = filename;
		this.areaname = areaname;
		this.origin_x = origin_x;
		this.origin_y = origin_y;
		this.size = size;
		this.selectable = selectable;
		this.defaultzoom = defaultzoom;
		if (this.defaultzoom == 255) {
			this.defaultzoom = 0;
		}
		squares = new NodeDeque();
	}

	public boolean is_within(int x, int y) {
		if (~minx >= ~x && x <= maxx && y >= miny && y <= maxy) {
			for (WorldMapSquare var4 = (WorldMapSquare) squares.get_first(); var4 != null; var4 = (WorldMapSquare) squares.get_next()) {
				if (var4.is_within(x, y)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public void calculate_bounds() {
		minx = 12800;
		miny = 12800;
		maxx = 0;
		maxy = 0;
		for (WorldMapSquare square = (WorldMapSquare) squares.get_first(); null != square; square = (WorldMapSquare) squares.get_next()) {
			if (~square.starty > ~miny) {
				miny = square.starty;
			}
			if (~square.startx > ~minx) {
				minx = square.startx;
			}
			if (square.endx > maxx) {
				maxx = square.endx;
			}
			if (maxy < square.endy) {
				maxy = square.endy;
			}
		}
	}

	public static WorldMapArea deserialise(Packet packet) {
		WorldMapArea area = new WorldMapArea(packet.gstr(), packet.gstr(), packet.g2(), packet.g2(), packet.readInt(), packet.g1() == 1, packet.g1());
		int num_squares = packet.g1();
		for (int squareid = 0; ~squareid > ~num_squares; ++squareid) {
			area.squares.add_last(new WorldMapSquare(packet.g2(), packet.g2(), packet.g2(), packet.g2()));
		}
		area.calculate_bounds();
		return area;
	}
}
