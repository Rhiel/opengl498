package com.jagex;
/* MouseWheelHandler - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.awt.Component;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandler extends AbstractMouseWheel implements MouseWheelListener {
	public int anInt2722 = 0;

	@Override
	final synchronized int method1236(int i) {
		int i_0_ = anInt2722;
		anInt2722 = 0;
		return i_0_;
	}

	@Override
	public void detach(Component component) {
		component.removeMouseWheelListener(this);
	}

	@Override
	public void attach(Component component) {
		component.addMouseWheelListener(this);
	}

	MouseWheelHandler() {
		/* empty */
	}

	@Override
	public final synchronized void mouseWheelMoved(MouseWheelEvent mousewheelevent) {
		anInt2722 += mousewheelevent.getWheelRotation();
	}
}
