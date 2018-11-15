package com.jagex;

public class ZoomHandler {

	public static void zoom(int notches) {
		if (notches == -1) {// Out
			if (Camera.ZOOM >= 80) { // 80 Recommended Value
				Camera.ZOOM -= 16; // This is how much it decrements.
			} else {
				// Class255.consolePrint("You can not zoom any farther outwards!", 93254474);//Replace this with the correct method.
				// This is for 718
				return;
			}
		}
		if (notches == 1) {// In
			if (Camera.ZOOM < 1000) { // 608 Recommended Value
				Camera.ZOOM += 16; // This is how much it increments.
			} else {
				// Class255.consolePrint("You can not zoom any farther inwards!", 93254474);//Replace this with the correct method. This
				// is for 718
				return;
			}
		}
	}
}
