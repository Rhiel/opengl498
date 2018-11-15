package com.jagex;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;

import com.jagex.launcher.Configurations;

public class ComponentCanvas extends Canvas {
	public static final long serialVersionUID = -8971272174415929230L;
	static RSString aClass16_47 = RSString.createString(":assist:");
	public Component aComponent48;
	static RSString aClass16_49 = RSString.createString("Cabbage");
	static RSString aClass16_52 = RSString.createString("Regeln versto-8en hat)3");
	static byte[][] aByteArrayArray54 = new byte[1000][];

	public static void method42(int i) {
		aClass16_52 = null;
		aClass16_49 = null;
		aByteArrayArray54 = null;
		if (i != 1000) {
			aByteArrayArray54 = null;
		}
		aClass16_47 = null;
	}

	@Override
	public final void update(Graphics graphics) {
		aComponent48.update(graphics);
	}

	public static boolean method4983() {
		return Configurations.OS_NAME.contains("WIN");
	}

	ComponentCanvas(Component component) {
		aComponent48 = component;
	}

	@Override
	public final void paint(Graphics graphics) {
		aComponent48.paint(graphics);
	}

}
