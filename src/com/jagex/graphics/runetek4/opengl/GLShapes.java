package com.jagex.graphics.runetek4.opengl;

import static com.jagex.graphics.runetek4.opengl.GLState.setup_2d_geometry_state;
import static com.jagex.graphics.runetek4.opengl.GLState.viewport_height;
import static jaggl.GLConstants.GL_LINES;
import static jaggl.GLConstants.GL_LINE_LOOP;
import static jaggl.GLConstants.GL_TRIANGLE_FAN;
import static jaggl.OpenGL.glBegin;
import static jaggl.OpenGL.glColor3ub;
import static jaggl.OpenGL.glColor4ub;
import static jaggl.OpenGL.glEnd;
import static jaggl.OpenGL.glVertex2f;

/**
 * @author Walied K. Yassen
 */
public class GLShapes {

	public static void fill_rectangle(int x, int y, int width, int height, int color) {
		setup_2d_geometry_state();
		float xx0 = x;
		float xx1 = xx0 + width;
		float yy0 = viewport_height - y;
		float yy1 = yy0 - height;
		glBegin(GL_TRIANGLE_FAN);
		{
			glColor3ub((byte) (color >> 16), (byte) (color >> 8), (byte) color);
			glVertex2f(xx0, yy0);
			glVertex2f(xx0, yy1);
			glVertex2f(xx1, yy1);
			glVertex2f(xx1, yy0);
		}
		glEnd();
	}

	public static void fill_rectangle(int x, int y, int width, int height, int color, int alpha) {
		setup_2d_geometry_state();
		float xx0 = x;
		float xx1 = xx0 + width;
		float yy0 = viewport_height - y;
		float yy1 = yy0 - height;
		glBegin(GL_TRIANGLE_FAN);
		{
			glColor4ub((byte) (color >> 16), (byte) (color >> 8), (byte) color, alpha > 255 ? -1 : (byte) alpha);
			glVertex2f(xx0, yy0);
			glVertex2f(xx0, yy1);
			glVertex2f(xx1, yy1);
			glVertex2f(xx1, yy0);
		}
		glEnd();
	}

	public static void fill_rectangle_gradient(int x, int y, int width, int height, int start_colour, int end_colour) {
		setup_2d_geometry_state();
		float xx0 = x;
		float xx1 = xx0 + width;
		float yy0 = viewport_height - y;
		float yy1 = yy0 - height;
		glBegin(GL_TRIANGLE_FAN);
		{
			glColor3ub((byte) (start_colour >> 16), (byte) (start_colour >> 8), (byte) start_colour);
			glVertex2f(xx0, yy0);
			glVertex2f(xx0, yy1);
			glColor3ub((byte) (end_colour >> 16), (byte) (end_colour >> 8), (byte) end_colour);
			glVertex2f(xx1, yy1);
			glVertex2f(xx1, yy0);
		}
		glEnd();
	}

	public static void draw_rectangle(int x, int y, int width, int height, int colour) {
		setup_2d_geometry_state();
		float var5 = x + 0.3F;
		float var6 = var5 + (width - 1);
		float var7 = viewport_height - (y + 0.3F);
		float var8 = var7 - (height - 1);
		glBegin(GL_LINE_LOOP);
		{
			glColor3ub((byte) (colour >> 16), (byte) (colour >> 8), (byte) colour);
			glVertex2f(var5, var7);
			glVertex2f(var5, var8);
			glVertex2f(var6, var8);
			glVertex2f(var6, var7);
		}
		glEnd();
	}

	public static void draw_rectangle(int x, int y, int width, int height, int color, int alpha) {
		setup_2d_geometry_state();
		float var6 = x + 0.3F;
		float var7 = var6 + (width - 1);
		float var8 = viewport_height - (y + 0.3F);
		float var9 = var8 - (height - 1);
		glBegin(GL_LINE_LOOP);
		{
			glColor4ub((byte) (color >> 16), (byte) (color >> 8), (byte) color, alpha > 255 ? -1 : (byte) alpha);
			glVertex2f(var6, var8);
			glVertex2f(var6, var9);
			glVertex2f(var7, var9);
			glVertex2f(var7, var8);
		}
		glEnd();
	}

	public static void draw_horizontal_line(int x, int y, int width, int color) {
		setup_2d_geometry_state();
		float start_x = x + 0.3F;
		float end_x = start_x + width;
		float draw_y = viewport_height - (y + 0.3F);
		glBegin(GL_LINES);
		{
			glColor3ub((byte) (color >> 16), (byte) (color >> 8), (byte) color);
			glVertex2f(start_x, draw_y);
			glVertex2f(end_x, draw_y);
		}
		glEnd();
	}

	public static void draw_vertical_line(int x, int y, int height, int colour) {
		setup_2d_geometry_state();
		float draw_x = x + 0.3F;
		float start_Y = viewport_height - (y + 0.3F);
		float end_y = start_Y - height;
		glBegin(GL_LINES);
		{
			glColor3ub((byte) (colour >> 16), (byte) (colour >> 8), (byte) colour);
			glVertex2f(draw_x, start_Y);
			glVertex2f(draw_x, end_y);
		}
		glEnd();
	}

	public static void draw_thin_line(int start_x, int start_y, int end_x, int end_y, int colour) {
		setup_2d_geometry_state();
		float var5 = start_x + 0.3F;
		float var6 = end_x + 0.3F;
		float var7 = viewport_height - (start_y + 0.3F);
		float var8 = viewport_height - (end_y + 0.3F);
		glBegin(GL_LINES);
		{
			glColor3ub((byte) (colour >> 16), (byte) (colour >> 8), (byte) colour);
			glVertex2f(var5, var7);
			glVertex2f(var6, var8);
		}
		glEnd();
	}

	public static void draw_thick_line(int start_x, int start_y, int end_x, int end_y, int color, int thickness) {
		int width = end_x - start_x;
		int height = end_y - start_y;
		int var8 = width >= 0 ? width : -width;
		int var9 = height >= 0 ? height : -height;
		int var10 = var8;
		if (var8 < var9) {
			var10 = var9;
		}
		if (var10 != 0) {
			int var11 = (width << 16) / var10;
			int var12 = (height << 16) / var10;
			if (var12 <= var11) {
				var11 = -var11;
			} else {
				var12 = -var12;
			}

			int var13 = thickness * var12 >> 17;
			int var14 = thickness * var12 + 1 >> 17;
			int var15 = thickness * var11 >> 17;
			int var16 = thickness * var11 + 1 >> 17;
			int x3 = start_x + var13;
			int x2 = start_x - var14;
			int x1 = start_x + width - var14;
			int x0 = start_x + width + var13;
			int y3 = start_y + var15;
			int y2 = start_y - var16;
			int y1 = start_y + height - var16;
			int y0 = start_y + height + var15;
			setup_2d_geometry_state();
			glColor3ub((byte) (color >> 16), (byte) (color >> 8), (byte) color);
			glBegin(GL_TRIANGLE_FAN);
			{
				if (var12 <= var11) {
					glVertex2f(x0, viewport_height - y0);
					glVertex2f(x1, viewport_height - y1);
					glVertex2f(x2, viewport_height - y2);
					glVertex2f(x3, viewport_height - y3);
				} else {
					glVertex2f(x3, viewport_height - y3);
					glVertex2f(x2, viewport_height - y2);
					glVertex2f(x1, viewport_height - y1);
					glVertex2f(x0, viewport_height - y0);
				}
			}
			glEnd();
		}
	}
}
