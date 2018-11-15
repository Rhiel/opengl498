package com.jagex;
/* Class23_Sub13_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class GroundItemNode extends Queuable {
	public static int anInt3667 = 1;
	public GroundItem groundItem;
	public static int anInt3671;
	static RSString aClass16_3674;

	public static void method604() {
		LobbyWorld.worldLists = null;
		aClass16_3674 = null;
	}

	GroundItemNode(GroundItem item) {
		groundItem = item;
	}

	static {
		GameShell.setShutdown(false);
		anInt3671 = -1;
		aClass16_3674 = RSString.createString("Fehler bei der Verbindung zum Server)3");
	}
}
