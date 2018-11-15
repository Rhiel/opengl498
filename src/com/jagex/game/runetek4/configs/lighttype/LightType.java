package com.jagex.game.runetek4.configs.lighttype;

import com.jagex.Packet;
import com.jagex.Queuable;

public class LightType extends Queuable {

	public int effect = 0;
	public int anInt899 = 2048;
	public int anInt907 = 0;
	public int anInt908 = 2048;

	public void decode(Packet buffer) {
		while (true) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			decode(buffer, opcode);
		}
	}

	public void decode(Packet buffer, int opcode) {
		if (opcode == 1) {
			effect = buffer.g1();
		} else if (opcode == 2) {
			anInt908 = buffer.g2();
		} else if (opcode == 3) {
			anInt899 = buffer.g2();
		} else if (4 == opcode) {
			anInt907 = buffer.g2s();
		}
	}
}
