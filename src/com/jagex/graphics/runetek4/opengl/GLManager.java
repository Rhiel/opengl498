package com.jagex.graphics.runetek4.opengl;

import static jaggl.GLConstants.*;
import static jaggl.OpenGL.*;

import java.awt.Canvas;

import com.jagex.CacheConstants;
import com.jagex.IntegerNode;
import com.jagex.Linkable;
import com.jagex.NodeDeque;
import com.jagex.TimeTools;
import com.jagex.graphics.runetek4.opengl.buffer.IndexBuffer;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLIndexBuffer;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLIndexBufferARB;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexAttribute;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexBuffer;
import com.jagex.graphics.runetek4.opengl.buffer.OpenGLVertexBufferARB;
import com.jagex.graphics.runetek4.opengl.buffer.VertexBuffer;
import com.jagex.graphics.runetek4.opengl.effects.OpenGLEffects;
import com.jagex.graphics.runetek4.opengl.framebuffer.OpenGLOnscreenBuffer;
import com.jagex.graphics.runetek4.opengl.memory.OpenGLMemoryPool;
import com.jagex.graphics.runetek4.opengl.textures.OpenGLTextureCache;
import com.rs2.client.scene.light.SceneLighting;

import jaclib.hardware_info.HardwareInfo;
import jaclib.memory.Buffer;
import jaclib.memory.Stream;
import jaclib.memory.heap.NativeHeap;
import jaggl.OpenGL;

public final class GLManager {

	/* the OpenGL status chunk */
	public static boolean opengl_mode = false;
	public static boolean opengl_loaded = false;
	public static OpenGL gl;

	/* the deletion buffer chunk */
	public static final int[] delete_buffer = new int[1000];

	/* the deletion deques chunk */
	public static final NodeDeque deleting_sprites = new NodeDeque();
	public static final NodeDeque deleting_textures = new NodeDeque();
	public static final NodeDeque deleting_lists = new NodeDeque();
	public static final NodeDeque deleting_vertexbuffers = new NodeDeque();
	/* the allocated memory sizes chunk */
	public static final NodeDeque memorypools = new NodeDeque();
	public static NativeHeap heap;
	public static int allocated_vertexbuffers_size = 0;
	public static int allocated_textures_size = 0;
	public static int allocated_sprites_size = 0;

	/* the memory timings chunk */
	public static int memorycycle = 0;
	public static long last_gc_TimeTools = 0;

	/* the GPU compatibility chunk */
	public static String gpu_vendor;
	public static String gpu_renderer;
	public static int gpu_version;
	public static int max_texture_units;
	public static int max_texture_coords;
	public static int max_image_units;
	public static boolean big_endianess;
	public static boolean vertexbuffer_supported;
	public static boolean should_use_arb_vbo;
	public static boolean multisampling_supported;
	public static boolean cubemap_supported;
	public static boolean vertex_program_supported;
	public static boolean texture_3d_supported;
	public static boolean framebuffer_objects_supported;
	public static boolean blend_func_separate_supported;
	public static boolean texture_non_pow2_supported;
	public static boolean arb_rectangle_textures_supported;
	public static int byte_type;

	/* the frame dispatching chunk */
	public static OpenGLOnscreenBuffer viewport_buffer;

	/* the bound stuff */
	public static IndexBuffer bound_index_buffer;
	public static VertexBuffer bound_vertex_buffer;

	/* cache stuff */
	public static OpenGLTextureCache textures;

	public static int initialise(Canvas canvas, int samples) {
		if (!canvas.isDisplayable()) {
			return -1;
		}
		try {
			gl = new OpenGL();
			long handle = gl.init(canvas, 8, 8, 8, 24, 0, samples);
			if (handle == 0L) {
				return -2;
			}
			int attempts = 0;
			while (!gl.tryAttach()) {
				if (attempts++ > 5) {
					return -2;
				}
				TimeTools.sleep(1000L);
			}
			viewport_buffer = new OpenGLOnscreenBuffer(canvas, gl);
			opengl_mode = true;
			GLState.update_viewport(viewport_buffer.width, viewport_buffer.height);
			int errorCode = check_compatbility();
			if (errorCode != 0) {
				drop();
				return errorCode;
			} else {
				initialise_memorypool();
				initialise_state();
				initialise_effects();
				textures = new OpenGLTextureCache(CacheConstants.textureCacheIdx);
				attempts = 0;
				while (true) {
					glClear(GL_COLOR_BUFFER_BIT);
					try {
						viewport_buffer.draw();
						break;
					} catch (Exception e) {
						if (attempts++ > 5) {
							drop();
							return -3;
						}
						TimeTools.sleep(100L);
					}
				}
				glClear(GL_COLOR_BUFFER_BIT);
				return 0;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			drop();
			return -5;
		}
	}

	public static void initialise_effects() {
		OpenGLEffects.initialise();
	}

	public static void initialise_state() {
		SceneLighting.initialise();
		GLState.initialise();
	}

	public static void initialise_memorypool() {
		boolean highmem = HardwareInfo.getCPUInfo()[2] > 256;
		OpenGLMemoryPool memorypool;
		if (highmem) {
			memorypool = create_memorypool(146800640);
		} else {
			memorypool = create_memorypool(104857600);
		}
		set_memorypool(memorypool);
	}

	public static final int check_compatbility() {
		int error_code = 0;
		gpu_vendor = glGetString(GL_VENDOR);
		gpu_renderer = glGetString(GL_RENDERER);
		String var1 = gpu_vendor.toLowerCase();
		if (var1.indexOf("microsoft") != -1) {
			error_code |= 1;
		}
		if (var1.indexOf("brian paul") != -1 || var1.indexOf("mesa") != -1) {
			error_code |= 1;
		}
		String version_raw = glGetString(GL_VERSION);
		String[] version_parts = version_raw.replaceAll("\\.", " ").split(" ");
		if (version_parts.length >= 2) {
			try {
				int major = Integer.parseInt(version_parts[0]);
				int minor = Integer.parseInt(version_parts[1]);
				gpu_version = major * 10 + minor;
			} catch (NumberFormatException var11) {
				error_code |= 4;
			}
		} else {
			error_code |= 4;
		}
		if (gpu_version < 12) {
			error_code |= 2;
		}
		if (!gl.hasExtension("GL_ARB_multitexture")) {
			error_code |= 8;
		}
		if (!gl.hasExtension("GL_ARB_texture_env_combine")) {
			error_code |= 32;
		}
		int[] var12 = new int[1];
		glGetIntegerv(GL_MAX_TEXTURE_UNITS, var12, 0);
		max_texture_units = var12[0];
		glGetIntegerv(GL_MAX_TEXTURE_COORDS, var12, 0);
		max_texture_coords = var12[0];
		glGetIntegerv(GL_MAX_TEXTURE_IMAGE_UNITS, var12, 0);
		max_image_units = var12[0];
		if (max_texture_units < 2 || max_texture_coords < 2 || max_image_units < 2) {
			error_code |= 16;
		}
		if (error_code != 0) {
			return error_code;
		} else {
			big_endianess = Stream.z();
			vertexbuffer_supported = gl.hasExtension("GL_ARB_vertex_buffer_object");
			multisampling_supported = gl.hasExtension("GL_ARB_multisample");
			cubemap_supported = gl.hasExtension("GL_ARB_texture_cube_map");
			vertex_program_supported = gl.hasExtension("GL_ARB_vertex_program");
			texture_3d_supported = gl.hasExtension("GL_EXT_texture3D");
			framebuffer_objects_supported = gl.hasExtension("GL_EXT_framebuffer_object");
			blend_func_separate_supported = gl.hasExtension("GL_EXT_blend_func_separate");
			texture_non_pow2_supported = gl.hasExtension("GL_ARB_texture_non_power_of_two");
			arb_rectangle_textures_supported = gl.hasExtension("GL_ARB_texture_rectangle");
			byte_type = big_endianess ? GL_UNSIGNED_INT_8_8_8_8_REV : GL_UNSIGNED_BYTE;
			if (gpu_renderer.contains("radeon")) {
				int version = 0;
				String[] parts = gpu_renderer.replace('/', ' ').split(" ");
				for (String part : parts) {
					if (part.length() < 1) {
						continue;
					}
					if (part.charAt(0) == 'x' && part.length() >= 3 && true) {
						part = part.substring(1);
					}
					if (part.equals("hd")) {
						continue;
					}
					if (part.startsWith("hd")) {
						part = part.substring(2);
					}
					if (part.length() < 4) {
						continue;
					}
					try {
						version = Integer.parseInt(part.substring(0, 4));
						break;
					} catch (NumberFormatException e) {
						continue;
					}
				}
				if (version >= 7000 && version <= 7999) {
					vertexbuffer_supported = false;
				}
				if (version >= 7000 && version <= 9250) {
					texture_3d_supported = false;
				}
				arb_rectangle_textures_supported &= gl.hasExtension("GL_ARB_half_float_pixel");
				should_use_arb_vbo = vertexbuffer_supported;
			}
			if (vertexbuffer_supported) {
				try {
					int[] var14 = new int[1];
					glGenBuffersARB(1, var14, 0);
				} catch (Throwable e) {
					return -4;
				}
			}
			return 0;
		}
	}

	public static void render_frame() {
		try {
			viewport_buffer.draw();
		} catch (Exception e) {
			// NOOP
		}
	}

	public static void render_last_frame() {
		int[] var0 = new int[2];
		glGetIntegerv(GL_DRAW_BUFFER, var0, 0);
		glGetIntegerv(GL_READ_BUFFER, var0, 1);
		glDrawBuffer(GL_BACK_LEFT);
		glReadBuffer(GL_FRONT_LEFT);
		GLState.bind_texture(-1);
		glPushAttrib(GL_S);
		glDisable(GL_FOG);
		glDisable(GL_BLEND);
		GLState.set_depthtest_enabled(false);
		glDisable(GL_ALPHA_TEST);
		glRasterPos2i(0, 0);
		glCopyPixels(0, 0, GLState.viewport_width, GLState.viewport_height, GL_COLOR);
		glPopAttrib();
		glDrawBuffer(var0[0]);
		glReadBuffer(var0[1]);
	}

	public static void draw_pixels(int[] pixels, int x, int y, int width, int height) {
		GLState.setup_2d_geometry_state();
		glRasterPos2i(x, GLState.viewport_height - y);
		glPixelZoom(1.0F, -1.0F);
		glDisable(GL_BLEND);
		glDisable(GL_ALPHA_TEST);
		glDrawPixelsi(width, height, GL_BGRA, byte_type, pixels, 0);
		glEnable(GL_ALPHA_TEST);
		glEnable(GL_BLEND);
		glPixelZoom(1.0F, 1.0F);
	}

	public static OpenGLMemoryPool create_memorypool(int size) {
		OpenGLMemoryPool memorypool = new OpenGLMemoryPool(size);
		memorypools.add_last(memorypool);
		return memorypool;
	}

	public static void set_memorypool(OpenGLMemoryPool memorypool) {
		heap = memorypool.heap;
	}

	public static synchronized void cleanup() {
		int count = 0;
		while (!deleting_vertexbuffers.is_empty()) {
			IntegerNode deletingNode = (IntegerNode) deleting_vertexbuffers.remove_first();
			delete_buffer[count++] = (int) deletingNode.uid;
			allocated_vertexbuffers_size -= deletingNode.value;
			if (count == 1000) {
				glDeleteBuffersARB(count, delete_buffer, 0);
				count = 0;
			}
		}
		if (count > 0) {
			glDeleteBuffersARB(count, delete_buffer, 0);
			count = 0;
		}
		while (!deleting_textures.is_empty()) {
			IntegerNode deletingNode = (IntegerNode) deleting_textures.remove_first();
			if (deletingNode != null) {
				delete_buffer[count++] = (int) deletingNode.uid;
				allocated_textures_size -= deletingNode.value;
				if (count == 1000) {
					glDeleteTextures(count, delete_buffer, 0);
					count = 0;
				}
			}
		}
		if (count > 0) {
			glDeleteTextures(count, delete_buffer, 0);
		}
		while (!deleting_sprites.is_empty()) {
			IntegerNode deletingNode = (IntegerNode) deleting_sprites.remove_first();
			delete_buffer[count++] = (int) deletingNode.uid;
			allocated_sprites_size -= deletingNode.value;
			if (count == 1000) {
				glDeleteTextures(count, delete_buffer, 0);
				count = 0;
			}
		}
		if (count > 0) {
			glDeleteTextures(count, delete_buffer, 0);
		}
		while (!deleting_lists.is_empty()) {
			IntegerNode node = (IntegerNode) deleting_lists.remove_first();
			glDeleteLists((int) node.uid, 1);
		}
		if (allocated_vertexbuffers_size + allocated_sprites_size + allocated_textures_size > 100663296 && TimeTools.getMillis() > last_gc_TimeTools + 60000L) {
			System.gc();
			last_gc_TimeTools = TimeTools.getMillis();
		}
	}

	public static void drop() {
		for (Linkable node = memorypools.get_first(); node != null; node = memorypools.get_next()) {
			OpenGLMemoryPool memorypool = (OpenGLMemoryPool) node;
			memorypool.deallocate();
		}
		try {
			viewport_buffer.destroy();
			viewport_buffer = null;
		} catch (Throwable e) {
			// NOOP
		}
		SceneLighting.destroy_lights();
		try {
			OpenGLEffects.destroy();
		} catch (Throwable e) {
			// NOOP
		}
		gl = null;
		opengl_mode = false;
	}

	public static void load_vertices_array(OpenGLVertexAttribute positions, OpenGLVertexAttribute normals, OpenGLVertexAttribute colours, OpenGLVertexAttribute texcoords) {
		if (positions != null && positions.buffer != null) {
			bind_vertex_buffer(positions.buffer);
			glVertexPointer(positions.stride, positions.type, bound_vertex_buffer.getStride(), bound_vertex_buffer.getAddress() + positions.offset);
			glEnableClientState(GL_VERTEX_ARRAY);
		} else {
			glDisableClientState(GL_VERTEX_ARRAY);
		}
		if (normals != null && normals.buffer != null && GLState.normals_enabled) {
			bind_vertex_buffer(normals.buffer);
			glNormalPointer(normals.type, bound_vertex_buffer.getStride(), bound_vertex_buffer.getAddress() + normals.offset);
			glEnableClientState(GL_NORMAL_ARRAY);
		} else {
			glDisableClientState(GL_NORMAL_ARRAY);
		}
		if (colours != null && colours.buffer != null) {
			bind_vertex_buffer(colours.buffer);
			glColorPointer(colours.stride, colours.type, bound_vertex_buffer.getStride(), bound_vertex_buffer.getAddress() + colours.offset);
			glEnableClientState(GL_COLOR_ARRAY);
		} else {
			glDisableClientState(GL_COLOR_ARRAY);
		}
		if (texcoords != null && texcoords.buffer != null) {
			bind_vertex_buffer(texcoords.buffer);
			glTexCoordPointer(texcoords.stride, texcoords.type, bound_vertex_buffer.getStride(), bound_vertex_buffer.getAddress() + texcoords.offset);
			glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		} else {
			glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		}
	}

	public static void draw_index_buffer(IndexBuffer buffer, int mode, int start, int count) {
		bind_index_buffer(buffer);
		glDrawElements(mode, count, GL_UNSIGNED_SHORT, buffer.getAddress() + start * 2);
	}

	public static void draw_index_buffer_int(IndexBuffer buffer, int mode, int start, int count) {
		bind_index_buffer(buffer);
		glDrawElements(mode, count, GL_UNSIGNED_INT, buffer.getAddress() + start * 4);
	}

	public static void bind_vertex_buffer(VertexBuffer buffer) {
		if (bound_vertex_buffer != buffer) {
			if (vertexbuffer_supported && buffer != null) {
				glBindBufferARB(GL_ARRAY_BUFFER, buffer.getHandle());
			}
			bound_vertex_buffer = buffer;
		}
	}

	public static void bind_index_buffer(IndexBuffer buffer) {
		if (bound_index_buffer != buffer) {
			if (vertexbuffer_supported && buffer != null) {
				glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER, buffer.getHandle());
			}
			bound_index_buffer = buffer;
		}
	}

	public static IndexBuffer create_index_buffer(int format, byte[] buffer, int size, boolean stream) {
		if (vertexbuffer_supported && (!stream || should_use_arb_vbo)) {
			return new OpenGLIndexBufferARB(buffer, size, stream);
		}
		return new OpenGLIndexBuffer(buffer, size);
	}

	public static VertexBuffer create_vertex_buffer(int stride, byte[] buffer, int size, boolean stream) {
		if (vertexbuffer_supported && (!stream || should_use_arb_vbo)) {
			return new OpenGLVertexBufferARB(stride, buffer, size, stream);
		}
		return new OpenGLVertexBuffer(stride, buffer, size);
	}

	public static VertexBuffer create_vertex_buffer(int stride, Buffer buffer, int size, boolean stream) {
		if (vertexbuffer_supported && (!stream || should_use_arb_vbo)) {
			return new OpenGLVertexBufferARB(stride, buffer, size, stream);
		}
		return new OpenGLVertexBuffer(stride, buffer);
	}

	public static synchronized void drop_cycle() {
		memorycycle++;
		deleting_vertexbuffers.clear();
		deleting_sprites.clear();
		deleting_textures.clear();
		deleting_lists.clear();
		allocated_vertexbuffers_size = 0;
		allocated_sprites_size = 0;
		allocated_textures_size = 0;
	}

	public static synchronized void delete_vertexbuffer(int handle, int size) {
		IntegerNode node = new IntegerNode(size);
		node.uid = handle;
		deleting_vertexbuffers.add_last(node);
	}

	public static synchronized void delete_sprite(int handle, int size, int cycle) {
		if (cycle == memorycycle) {
			IntegerNode node = new IntegerNode(size);
			node.uid = handle;
			deleting_sprites.add_last(node);
		}
	}

	public static synchronized void delete_texture(int handle, int size) {
		IntegerNode node = new IntegerNode(size);
		node.uid = handle;
		deleting_textures.add_last(node);
	}

	public static synchronized void delete_list(int handle) {
		IntegerNode node = new IntegerNode();
		node.uid = handle;
		deleting_lists.add_last(node);
	}

	private GLManager() {

	}

}
