package com.jagex.graphics.runetek4.opengl.list;

import com.jagex.graphics.runetek4.opengl.GLManager;

import jaggl.OpenGL;

public class OpenGLDisplayList {

	public final int size;
	public int id;

	public OpenGLDisplayList(int size) {
		this.size = size;
		id = OpenGL.glGenLists(size);
	}

	public void call(char index) {
		OpenGL.glCallList(id + index);
	}

	public void newList(int index) {
		OpenGL.glNewList(id + index, OpenGL.GL_COMPILE);
	}

	public void end() {
		OpenGL.glEndList();
	}

	@Override
	protected void finalize() throws Throwable {
		for (int index = 0; index < size; index++) {
			GLManager.delete_list(id + index);
		}
	}
}
