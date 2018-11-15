package com.jagex.window;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Insets;

import com.jagex.GameClient;
import com.jagex.GameShell;
import com.jagex.GraphicsCache;
import com.jagex.InputManager;
import com.jagex.RSInterfaceLayout;
import com.jagex.RSInterfaceList;
import com.jagex.Rasterizer2D;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.game.runetek4.clientoptions.GamePreferences;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.opengl.terrain.OpenGLTile;
import com.jagex.graphics.runetek4.software.framebuffer.SoftwareGraphicsBuffer;
import com.rs2.client.scene.Scene;

/**
 * Created by Chris on 4/24/2017.
 */
public class WindowMode {

	public static Frame full_screen_frame;
	public static boolean resizable = false;
	public static int full_screen_width;
	public static int full_screen_height;

	public static void set_wm(int new_wm, boolean bool, int width, int height) {
		// ut.c(102);
		GameShell.last_resize_time = 0;
		int old_wm = get_wm();
		if (new_wm == 3 || old_wm == 3) {
			bool = true;
		}
		// if ( !Client.graphics_toolkit.j() )
		// bool = true;
		set_wm(bool, width, height, old_wm, new_wm);
	}

	public static int get_wm() {
		if (full_screen_frame != null) {
			return 3;
		}
		if (resizable) {
			return 2;
		} else {
			return 1;
		}
	}

	public static void set_wm(boolean replace_canvas, int width, int height, int old_windowmode, int new_windowmode) {
		boolean opengl_mode = true;
		if (full_screen_frame != null && (new_windowmode != 3 || width != full_screen_width || height != full_screen_height)) {
			// FullscreenUtilities.exit_full_screen(full_screen_frame, Game.gameSignlink);
			full_screen_frame = null;
		}
		if (new_windowmode == 3 && full_screen_frame == null) {
			// full_screen_frame = FullscreenUtilities.enter_full_screen(width, height, 0, 0, Game.gameSignlink);
			if (full_screen_frame != null) {
				full_screen_height = height;
				full_screen_width = width;
				// Client.store_preferences();
			}
		}
		Container container;
		if (new_windowmode == 3 && full_screen_frame == null) {
			set_wm(true, -1, -1, old_windowmode, GamePreferences.windowMode);
		} else {
			if (full_screen_frame != null) {
				GameShell.window_width = width;
				GameShell.window_height = height;
				container = full_screen_frame;
			} else if (GameShell.frame != null) {
				Insets insets = GameShell.frame.getInsets();
				GameShell.window_width = GameShell.frame.getSize().width - insets.left - insets.right;
				GameShell.window_height = GameShell.frame.getSize().height - insets.top - insets.bottom;
				container = GameShell.frame;
			} else if (GameClient.gameSignlink.applet != null) {
				container = GameClient.gameSignlink.applet;
				GameShell.window_width = container.getSize().width;
				GameShell.window_height = container.getSize().height;
			} else {
				container = GameShell.active_gameshell;
				GameShell.window_width = container.getSize().width;
				GameShell.window_height = container.getSize().height;
			}
			GameShell.getFrame().setResizable(new_windowmode > 1);
			if (new_windowmode == 1) {
				GameShell.frame_width = 765;
				GameShell.frame_height = 503;
				GameShell.top_margin = 0;
				GameShell.left_margin = (GameShell.window_width - 765) / 2;
			} else {
				update_margins();
			}
			if (!replace_canvas) {
				if (GLManager.opengl_mode) {
					GLState.update_viewport(GameShell.frame_width, GameShell.frame_height);
				}
				GameShell.canvas.setSize(GameShell.frame_width, GameShell.frame_height);
				if (GameShell.frame != container) {
					GameShell.canvas.setLocation(GameShell.left_margin, GameShell.top_margin);
				} else {
					Insets insets = GameShell.frame.getInsets();
					GameShell.canvas.setLocation(GameShell.left_margin + insets.left, insets.top + GameShell.top_margin);
				}
			} else {
				InputManager.detach_keyboard(GameShell.canvas);
				InputManager.detach_mouse(GameShell.canvas);
				if (InputManager.mouse_wheel != null) {
					InputManager.mouse_wheel.detach(GameShell.canvas);
				}
				GameClient.active_client.addCanvas();
				InputManager.attach_keyboard(GameShell.canvas);
				InputManager.attach_mouse(GameShell.canvas);
				if (InputManager.mouse_wheel != null) {
					InputManager.mouse_wheel.attach(GameShell.canvas);
				}
			}
			if (opengl_mode && new_windowmode > 0) {
				GameShell.canvas.setIgnoreRepaint(true);
				if (!GLManager.opengl_loaded) {
					Scene.reset_scene();
					GameClient.software_frame_buffer = null;
					GameClient.software_frame_buffer = SoftwareGraphicsBuffer.create(GameShell.canvas, GameShell.frame_width, GameShell.frame_height);
					Rasterizer2D.clear();
					GameClient.loadNativeLibrary("jaggl");
					GLManager.opengl_loaded = true;
				}
				if (GLManager.opengl_loaded) {
					int compatbility = GLManager.initialise(GameShell.canvas, ClientOptions.clientoption_antialiasing * 2);
					System.out.println("OpenGL Initialised, compatibility: " + compatbility);
				}
			}
			if (!GLManager.opengl_mode && new_windowmode > 0) {

			} else {
				if (new_windowmode == 0 && width > 0) {
					// if (ClientOptions.clientoption_highdetails_lighting) {
					// if (1 == AbstractFileRequest.clientoption_brightness) {
					// DummyClass40.updateLightness(0.9F);
					// }
					//
					// if (-3 == ~AbstractFileRequest.clientoption_brightness) {
					// DummyClass40.updateLightness(0.8F);
					// }
					//
					// if (3 == AbstractFileRequest.clientoption_brightness) {
					// DummyClass40.updateLightness(0.7F);
					// }
					//
					// if (-5 == ~AbstractFileRequest.clientoption_brightness) {
					// DummyClass40.updateLightness(0.6F);
					// }
					// }

					OpenGLTile.reset_buffers();
				}
			}
			if (opengl_mode) {
				GraphicsCache.reload(false);
			}
			resizable = new_windowmode >= 2;
			if (GameClient.interface_top_id != -1) {
				RSInterfaceLayout.calc_layout();
			}
			// TODO: send window mode
			// if ( cq.u.connection != null && he.some_test(Client.client_state) )
			// GamePacketEncoder.send_window_mode();

			for (int iid = 0; iid < 100; iid++) {
				RSInterfaceList.is_dirty[iid] = true;
			}
			GameShell.fullRedraw = true;
		}
	}

	public static void update_margins() {
		int i_0_ = 0;
		if (GameClient.preferences != null) {
			i_0_ = GamePreferences.marginMode;
		}
		if (i_0_ == 2) {
			int width = GameShell.window_width > 800 ? 800 : GameShell.window_width;
			int height = GameShell.window_height > 600 ? 600 : GameShell.window_height;
			GameShell.frame_width = width;
			GameShell.left_margin = (GameShell.window_width - width) / 2;
			GameShell.frame_height = height;
			GameShell.top_margin = 0;
		} else if (i_0_ == 1) {
			int width = GameShell.window_width > 1024 ? 1024 : GameShell.window_width;
			int height = GameShell.window_height > 768 ? 768 : GameShell.window_height;
			GameShell.left_margin = (GameShell.window_width - width) / 2;
			GameShell.frame_width = width;
			GameShell.top_margin = 0;
			GameShell.frame_height = height;
		} else {
			GameShell.left_margin = 0;
			GameShell.top_margin = 0;
			GameShell.frame_width = GameShell.window_width;
			GameShell.frame_height = GameShell.window_height;
		}
	}

}
