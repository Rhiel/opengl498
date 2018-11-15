package com.jagex.graphics.runetek4.software.framebuffer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class ProducingGraphicsBuffer extends SoftwareGraphicsBuffer implements ImageProducer, ImageObserver {

	public ImageConsumer consumer;
	public ColorModel model;

	@Override
	public void init(Component component, int width, int height) {
		this.height = height;
		this.width = width;
		colour_buffer = new int[width * height + 1];
		model = new DirectColorModel(32, 0xff0000, 0x00ff00, 0x0000ff);
		image = component.createImage(this);
		update_pixels();
		component.prepareImage(image, this);
		update_pixels();
		component.prepareImage(image, this);
		update_pixels();
		component.prepareImage(image, this);
		bind();
	}

	@Override
	public void draw(Graphics g, int i_142_, int i_141_) {
		update_pixels();
		g.drawImage(image, i_142_, i_141_, this);
	}

	@Override
	public void draw(Graphics g, int left, int top, int right, int bottom) {
		update_pixels(left, top, right, bottom);
		Shape shape = g.getClip();
		g.clipRect(left, top, right, bottom);
		g.drawImage(image, 0, 0, this);
		g.setClip(shape);
	}

	@Override
	public synchronized void addConsumer(ImageConsumer consumer) {
		this.consumer = consumer;
		consumer.setDimensions(width, height);
		consumer.setProperties(null);
		consumer.setColorModel(model);
		consumer.setHints(14);
	}

	@Override
	public synchronized boolean isConsumer(ImageConsumer consumer) {
		if (this.consumer != consumer) {
			return false;
		}
		return true;
	}

	@Override
	public synchronized void removeConsumer(ImageConsumer consumer) {
		if (this.consumer == consumer) {
			this.consumer = null;
		}
	}

	@Override
	public void startProduction(ImageConsumer consumer) {
		addConsumer(consumer);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return true;
	}

	public synchronized void update_pixels() {
		if (consumer != null) {
			consumer.setPixels(0, 0, width, height, model, colour_buffer, 0, width);
			consumer.imageComplete(2);
		}
	}

	public synchronized void update_pixels(int left, int top, int right, int bottom) {
		if (consumer != null) {
			consumer.setPixels(left, top, right, bottom, model, colour_buffer, left + top * width, width);
			consumer.imageComplete(2);
		}
	}

	@Override
	public void requestTopDownLeftRightResend(ImageConsumer consumer) {
		// NOOP
	}
}
