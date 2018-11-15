package com.jagex.graphics.runetek4.opengl.light;

import static jaggl.GLConstants.GL_FLOAT;
import static jaggl.GLConstants.GL_TRIANGLES;
import static jaggl.GLConstants.GL_UNSIGNED_BYTE;
import static jaggl.GLConstants.GL_UNSIGNED_SHORT;

import com.jagex.HashTable;
import com.jagex.IntegerNode;
import com.jagex.Packet;
import com.jagex.core.tools.MathTools;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.buffer.IndexBuffer;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexAttribute;
import com.jagex.graphics.runetek4.opengl.buffer.VertexBuffer;
import com.rs2.client.scene.light.FlickeringEffect;

public class PointLight {

	public final FlickeringEffect light;
	public int total_vertices;
	public int total_indices;
	public int num_vertices;
	public int num_indices;
	public byte[] colour_red;
	public byte[] colour_green;
	public byte[] colour_blue;
	public int[] position_x;
	public int[] position_z;
	public int[] position_y;
	public int[] indices;
	public HashTable vertices_cache;

	// rendering
	private VertexBuffer vertices_buffer;
	private IndexBuffer indices_buffer;
	public OpenGLVertexAttribute positions_attribute;
	public OpenGLVertexAttribute colours_attribute;

	public PointLight(FlickeringEffect light) {
		this.light = light;
		colours_attribute = new OpenGLVertexAttribute(null, GL_UNSIGNED_BYTE, 4, 0);
		positions_attribute = new OpenGLVertexAttribute(null, GL_FLOAT, 3, 4);
	}

	public void draw() {
		// toolkit.method4849((int) (pointLight.getIntensity() * 255.0F) << 24);
		GLManager.load_vertices_array(positions_attribute, null, colours_attribute, null);
		GLManager.draw_index_buffer(indices_buffer, GL_TRIANGLES, 0, num_indices);
	}

	public void initialise() {
		indices = new int[total_indices];
		position_x = new int[total_vertices];
		position_y = new int[total_vertices];
		position_z = new int[total_vertices];
		colour_red = new byte[total_vertices];
		colour_green = new byte[total_vertices];
		colour_blue = new byte[total_vertices];
		vertices_cache = new HashTable(MathTools.get_greater_pow2(total_vertices));
	}

	public int add_vertex(int point_x, int point_z, int point_y, float scale_x, float scale_z, float scale_y) {
		long var8 = 0;
		if ((point_x & 127) == 0 || (point_y & 127) == 0) {
			var8 = point_x + (point_y << 16);
			IntegerNode var10 = (IntegerNode) vertices_cache.get(var8);
			if (var10 != null) {
				return var10.value;
			}
		}
		int colour = light.colour;
		float distance_x = light.x - point_x;
		float distance_z = light.z - point_z;
		float distance_y = light.y - point_y;
		float size = (float) Math.sqrt(distance_x * distance_x + distance_z * distance_z + distance_y * distance_y);
		float normaliser = 1.0F / size;
		distance_x *= normaliser;
		distance_z *= normaliser;
		distance_y *= normaliser;
		float var16 = size / ((light.radius << 7) + 64);
		float var17 = 1.0F - var16 * var16;
		if (var17 < 0.0F) {
			var17 = 0.0F;
		}

		float var18 = distance_x * scale_x + distance_z * scale_z + distance_y * scale_y;
		if (var18 < 0.0F) {
			var18 = 0.0F;
		}

		float var19 = var18 * var17 * 2.0F;
		if (var19 > 1.0F) {
			var19 = 1.0F;
		}

		int red = (int) (var19 * (colour >> 16 & 255));
		if (red > 255) {
			red = 255;
		}

		int green = (int) (var19 * (colour >> 8 & 255));
		if (green > 255) {
			green = 255;
		}

		int blue = (int) (var19 * (colour & 255));
		if (blue > 255) {
			blue = 255;
		}

		colour_red[num_vertices] = (byte) red;
		colour_green[num_vertices] = (byte) green;
		colour_blue[num_vertices] = (byte) blue;
		position_x[num_vertices] = point_x;
		position_y[num_vertices] = point_z;
		position_z[num_vertices] = point_y;
		vertices_cache.put(var8, new IntegerNode(num_vertices));
		return num_vertices++;
	}

	public void bake() {
		Packet packet = new Packet(num_vertices * 16);
		if (GLManager.big_endianess) {
			for (int vertex = 0; vertex < num_vertices; ++vertex) {
				packet.p1(colour_red[vertex]);
				packet.p1(colour_green[vertex]);
				packet.p1(colour_blue[vertex]);
				packet.p1(255);
				packet.pf(position_x[vertex]);
				packet.pf(position_y[vertex]);
				packet.pf(position_z[vertex]);
			}
		} else {
			for (int vertex = 0; vertex < num_vertices; ++vertex) {
				packet.p1(colour_red[vertex]);
				packet.p1(colour_green[vertex]);
				packet.p1(colour_blue[vertex]);
				packet.p1(255);
				packet.ipf(position_x[vertex]);
				packet.ipf(position_y[vertex]);
				packet.ipf(position_z[vertex]);
			}
		}
		if (vertices_buffer == null) {
			vertices_buffer = GLManager.create_vertex_buffer(16, packet.byteBuffer, packet.index, false);
			positions_attribute.buffer = vertices_buffer;
			colours_attribute.buffer = vertices_buffer;
		} else {
			vertices_buffer.upload(16, packet.byteBuffer, packet.index);
		}
		packet.index = 0;
		if (GLManager.big_endianess) {
			for (int index = 0; index < num_indices; ++index) {
				packet.putShort(indices[index]);
			}
		} else {
			for (int index = 0; index < num_indices; ++index) {
				packet.putLEShort(indices[index]);
			}
		}
		if (indices_buffer == null) {
			indices_buffer = GLManager.create_index_buffer(GL_UNSIGNED_SHORT, packet.byteBuffer, packet.index, false);
		} else {
			indices_buffer.upload(GL_UNSIGNED_SHORT, packet.byteBuffer, packet.index);
		}
		position_x = null;
		position_y = null;
		position_z = null;
		colour_red = null;
		colour_green = null;
		colour_blue = null;
		indices = null;
		vertices_cache = null;
	}

	public void add_indices(int[] indices) {
		for (int index = 1; index < indices.length - 1; index++) {
			this.indices[num_indices++] = indices[0];
			this.indices[num_indices++] = indices[index];
			this.indices[num_indices++] = indices[index + 1];
		}
	}

}
