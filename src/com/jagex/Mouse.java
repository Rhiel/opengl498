package com.jagex;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener, FocusListener {
	public static int mouseClickState = 0;
	public static int mouseClickX = 0;
	public static int mouseClickY = 0;
	public static int mouseY = 0;
	public static int mouseX = 0;
	public static boolean wheelConsumed;// added
	public static RSString hiddenOption = RSString.createString("Hidden");
	public static RSString hiddenOptionString = hiddenOption;
	public static int anInt221;
	public static RSString aClass16_223;
	public static int[] anIntArray224;
	public static RSString aClass16_227;
	public static RSString aClass16_228;
	public static int[] friends_rank = new int[200];
	public static byte[][] aByteArrayArray231;

	@Override
	public final synchronized void mousePressed(MouseEvent mouseevent) {
		if (InputManager.mouse != null) {
			InteractiveEntity.idleMouseTicks = 0;
			Class79.anInt1888 = mouseevent.getX();
			StaticMethods.anInt3289 = mouseevent.getY();
			StaticMethods.aLong3497 = TimeTools.getMillis();
			if (mouseevent.getButton() == MouseEvent.BUTTON3) {
				CullingCluster.anInt918 = 2;
				Class19.anInt325 = 2;
			} else {
				CullingCluster.anInt918 = 1;
				Class19.anInt325 = 1;
			}
		}
		if (mouseevent.isPopupTrigger()) {
			mouseevent.consume();
		}
	}

	@Override
	public final synchronized void focusLost(FocusEvent focusevent) {
		if (InputManager.mouse != null) {
			Class19.anInt325 = 0;
		}
	}

	@Override
	public final void focusGained(FocusEvent focusevent) {

	}

	@Override
	public final void mouseClicked(MouseEvent mouseevent) {
		if (mouseevent.isPopupTrigger()) {
			mouseevent.consume();
		}
	}

	@Override
	public final synchronized void mouseEntered(MouseEvent mouseevent) {
		if (InputManager.mouse != null) {
			InteractiveEntity.idleMouseTicks = 0;
			StaticMethods.anInt3449 = mouseevent.getX();
			StringNode.anInt2469 = mouseevent.getY();
		}
	}

	public static void method111(byte b) {
		anIntArray224 = null;
		if (b != 51) {
			anIntArray224 = null;
		}
		hiddenOption = null;
		hiddenOptionString = null;
		aClass16_228 = null;
		aByteArrayArray231 = null;
		aClass16_223 = null;
		aClass16_227 = null;
		friends_rank = null;
	}

	@Override
	public final synchronized void mouseMoved(MouseEvent mouseevent) {
		if (InputManager.mouse != null) {
			InteractiveEntity.idleMouseTicks = 0;
			StaticMethods.anInt3449 = mouseevent.getX();
			StringNode.anInt2469 = mouseevent.getY();
		}
	}

	@Override
	public final synchronized void mouseReleased(MouseEvent mouseevent) {
		if (InputManager.mouse != null) {
			InteractiveEntity.idleMouseTicks = 0;
			Class19.anInt325 = 0;
			mouseevent.getModifiers();
		}
		if (mouseevent.isPopupTrigger()) {
			mouseevent.consume();
		}
	}

	@Override
	public final synchronized void mouseDragged(MouseEvent mouseevent) {
		if (InputManager.mouse != null) {
			InteractiveEntity.idleMouseTicks = 0;
			StaticMethods.anInt3449 = mouseevent.getX();
			StringNode.anInt2469 = mouseevent.getY();
		}
	}

	@Override
	public final synchronized void mouseExited(MouseEvent mouseevent) {
		if (InputManager.mouse != null) {
			InteractiveEntity.idleMouseTicks = 0;
			StaticMethods.anInt3449 = -1;
			StringNode.anInt2469 = -1;
		}
	}

	static {
		aClass16_227 = RSString.createString("Ausw-=hlen");
		aClass16_228 = RSString.createString("Anmelde)2Limit -Uberschritten)3");
		aClass16_223 = RSString.createString("Wir vermuten)1 dass jemand Ihr Passwort kennt)3");
		anInt221 = 0;
		anIntArray224 = new int[] { 160, 192, 80, 96, 0, 144, 80, 48, 160 };
		aByteArrayArray231 = new byte[250][];
	}
}
