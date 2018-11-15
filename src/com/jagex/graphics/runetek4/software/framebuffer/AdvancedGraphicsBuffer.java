package com.jagex.graphics.runetek4.software.framebuffer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class AdvancedGraphicsBuffer extends SoftwareGraphicsBuffer {

	public Component component;

	@Override
	public void init(Component component, int width, int height) {
		this.component = component;
		this.width = width;
		this.height = height;
		colour_buffer = new int[width * height + 1];
		DataBufferInt pixels = new DataBufferInt(colour_buffer, colour_buffer.length);
		DirectColorModel model = new DirectColorModel(32, 0xff0000, 0x00ff00, 0x0000ff);
		WritableRaster raster = Raster.createWritableRaster(model.createCompatibleSampleModel(width, height), pixels, null);
		image = new BufferedImage(model, raster, false, new Hashtable());
		bind();
	}

	@Override
	public void draw(Graphics g, int left, int top, int right, int bottom) {
		Shape clip = g.getClip();
		g.clipRect(left, top, right, bottom);
		g.drawImage(image, 0, 0, component);
		g.setClip(clip);
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, component);
	}
}
