/*
 * Copyright (c) 2018 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.jagex.graphics.runetek4.opengl.framebuffer;

import java.awt.Canvas;
import java.awt.Dimension;

import jaggl.OpenGL;

/**
 * @author Walied K. Yassen
 */
public class OpenGLOnscreenBuffer {

	public final Canvas canvas;
	public final OpenGL gl;
	public final long handle;
	public int width;
	public int height;
	public boolean destroyed;

	public OpenGLOnscreenBuffer(Canvas canvas, OpenGL gl) {
		this(canvas, gl, gl.prepareSurface(canvas));
	}

	public OpenGLOnscreenBuffer(Canvas canvas, OpenGL gl, long handle) {
		this.canvas = canvas;
		this.gl = gl;
		this.handle = handle;
		update_size();
	}

	public void draw() {
		if (destroyed) {
			throw new IllegalStateException();
		}
		gl.swapBuffers(handle);

	}

	public void resize(int width, int height) {
		if (destroyed) {
			throw new IllegalStateException();
		}
		gl.surfaceResized(handle);
		update_size();
	}

	public void update_size() {
		Dimension dimension = canvas.getSize();
		width = dimension.width;
		height = dimension.height;
	}

	public void destroy() {
		if (destroyed) {
			throw new IllegalStateException();
		}
		gl.releaseSurface(canvas, handle);
		destroyed = true;
	}
}
