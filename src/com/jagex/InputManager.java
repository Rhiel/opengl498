package com.jagex;

import java.awt.Component;

/**
 * @author Walied K. Yassen
 */
public class InputManager {

	public static AbstractMouseWheel mouse_wheel;
	public static Mouse mouse = new Mouse();
	public static Keyboard keyboard = new Keyboard();

	public static void attach_mouse(Component component) {
		component.addMouseListener(mouse);
		component.addMouseMotionListener(mouse);
		component.addFocusListener(mouse);
	}

	public static void attach_keyboard(Component component) {
		component.setFocusTraversalKeysEnabled(false);
		component.addKeyListener(keyboard);
		component.addFocusListener(keyboard);
	}

	public static void detach_mouse(Component component) {
		component.removeMouseListener(mouse);
		component.removeMouseMotionListener(mouse);
		component.removeFocusListener(mouse);
	}

	public static void detach_keyboard(Component component) {
		component.removeKeyListener(keyboard);
		component.removeFocusListener(keyboard);
		InputManager.anInt2986 = -1;
	}

	public static int anInt2986;
}
