package com.jagex.graphics.runetek4.textures;

public enum DataType {

	INT8(1, 1),
	INT16(6, 2),
	aClass86_720(3, 4),
	aClass86_715(7, 1),
	aClass86_721(0, 2),
	INT24(2, 3),
	aClass86_723(4, 4),
	FLOATING_POINT16(8, 2),
	FLOATING_POINT32(5, 4);

	public int id;
	public int size;

	private DataType(int id, int size) {
		this.id = id;
		this.size = size;
	}
}
