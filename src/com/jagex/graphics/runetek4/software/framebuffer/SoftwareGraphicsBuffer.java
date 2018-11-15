package com.jagex.graphics.runetek4.software.framebuffer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import com.jagex.Rasterizer2D;

public abstract class SoftwareGraphicsBuffer {

	public Image image;
	public int width;
	public int height;
	public int[] colour_buffer;

	public void bind() {
		Rasterizer2D.initialise(colour_buffer, width, height);
	}

	public abstract void init(Component component, int width, int height);

	public abstract void draw(Graphics g, int x, int y);

	public abstract void draw(Graphics g, int left, int top, int right, int bottom);

	public static final SoftwareGraphicsBuffer create(Component component, int width, int height) {
		try {
			SoftwareGraphicsBuffer buffer = new AdvancedGraphicsBuffer();
			buffer.init(component, width, height);
			return buffer;
		} catch (Throwable e) {
			ProducingGraphicsBuffer buffer = new ProducingGraphicsBuffer();
			buffer.init(component, width, height);
			return buffer;
		}
	}

}
