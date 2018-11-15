package com.jagex;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import com.jagex.core.natives.NativeManager;
import com.jagex.game.runetek4.clientoptions.GamePreferences;
import com.jagex.graphics.runetek4.software.framebuffer.SoftwareGraphicsBuffer;
import com.jagex.launcher.GameLaunch;
import com.jagex.launcher.GameSetting;
import com.jagex.launcher.logger.Logger;
import com.jagex.window.WindowMode;

@SuppressWarnings("serial")
/**
 * Represents the game shell of the client.
 * 
 * @author Jagex
 */
public abstract class GameShell extends Applet implements Runnable, FocusListener, WindowListener {

	static boolean windowActive;
	public static int missed_logic_steps;
	public static AbstractTimer frame_timebase;

	public static long last_resize_time = 0L;
	public static volatile boolean recommend_canvas_replace = false;

	public static boolean have_java5_or_better = false;
	/**
	 * Represents the frame of the client.
	 */
	public static Frame frame;
	public static int load_count = 0;

	/**
	 * The path to the icon.
	 */
	public static String ICON = "favicon.png";

	/**
	 * Not refactored.
	 */

	/**
	 * The game settings.
	 */
	public GameSetting setting = GameLaunch.getSetting();

	/**
	 * Represents if the client errored already.
	 */
	public boolean erroredAlready = false;

	/**
	 * Represents the height of the client.
	 */
	public static int window_height;

	/**
	 * Represents the width of the client.
	 */
	public static int window_width;

	public static int frame_width;
	public static int frame_height;
	public static int top_margin = 0;
	public static int left_margin = 0;

	/**
	 * Represents the loading font.
	 */
	public static java.awt.Font loadingFont;

	/**
	 * Represents the java vendor.
	 */
	public static String java_vendor;

	/**
	 * Represents the java version.
	 */
	public static String java_version;

	public static String os_arch;
	public static String os_name_raw;
	public static String os_name;
	/**
	 * Represents the current canvas.
	 */
	public static Canvas canvas;

	/**
	 * Represents the time of the last canvas replace.
	 */
	public static volatile long last_canvas_replace = 0L;

	/**
	 * Represents the kill time.
	 */
	public static long killTime;

	/**
	 * Represents if the client is in shutdown mode.
	 */
	public static boolean isShutdown;

	/**
	 * Represents the game shell.
	 */
	public static GameShell active_gameshell = null;

	/***
	 * Represents if the screen needs a full redraw.
	 */
	public static volatile boolean fullRedraw = true;

	/**
	 * Represents if the client should focus in.
	 */
	public static volatile boolean focusIn = true;

	/***
	 * An unkown value.
	 */
	public static int crossY = 0;

	/**
	 * An unkown value.
	 */
	public static int[] anIntArray42 = new int[] { 2, 2, 4, 0, 1, 8, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 };

	/**
	 * An unkown value.
	 */
	public static int anInt44;

	/**
	 * If we loaded using desktop client.
	 */
	public static boolean desktop;

	/**
	 * Idle logout time.
	 */
	public static final int IDLE_LOGOUT_TIME = 300000 / 6; // 300 seconds

	public static NativeManager nativeManager;
	public static int max_memory = 64;

	/**
	 * Constructs a new {@code GameShell} {@code Object}.
	 */
	public GameShell() {
		/**
		 * empty.
		 */
	}

	static final void create_dummy_actionevent(Object object, int i, SignLink signLink) {
		if (signLink.anEventQueue189 != null) {
			for (int i_12_ = i; i_12_ < 50 && signLink.anEventQueue189.peekEvent() != null; i_12_++) {
				TimeTools.sleep(1L);
			}
			if (object != null) {
				signLink.anEventQueue189.postEvent(new ActionEvent(object, 1001, "dummy"));
			}
		}
	}

	/**
	 * Method used to construct the frame of the shell.
	 * 
	 * @param dimension
	 *                  the dimension of the frame.
	 */
	public final void construct(Dimension dimension, Frame frame) {
		try {
			dimension = new Dimension((int) dimension.getWidth(), (int) dimension.getHeight());
			setActive_gameshell(this);/** sets the game applet to this. */
			setWidth((int) dimension.getWidth());/** sets the width of the client. */
			setFrame(frame);
			setHeight((int) dimension.getHeight());/** sets the height of the client. */
			getFrame().setTitle(GameLaunch.getSetting().getName());
			Image image = getToolkit().getImage("./res/rs/zaros/" + ICON);
			getFrame().setIconImage(image);
			getFrame().setResizable(GamePreferences.windowMode == 1 ? false : true);
			getFrame().setBackground(Color.BLACK);
			getFrame().addWindowListener(this);
			if (!GameClient.getSetting().isSwiftKit()) {
				getFrame().setVisible(true);
			}
			getFrame().toFront();
			Insets insets = getFrame().getInsets();
			getFrame().setSize((int) dimension.getWidth() + insets.right + insets.left, (int) dimension.getHeight() + insets.top + insets.bottom);
			getFrame().setLocationRelativeTo(null);
			StaticMethods.aSignLink_3348 = GameClient.gameSignlink = new SignLink(true, null, 32, "runescape", GameClient.NUM_CACHE_ARCHIVES);
			GameClient.gameSignlink.newRunnable(1, this, (byte) 72);
		} catch (Exception exception) {
			ForceMovement.sendError(95, exception, null);
		}
	}

	/**
	 * Represents the starting method.
	 */
	@Override
	public final void start() {
		if (this == active_gameshell && !isShutdown()) {
			setKillTime(0L);
		}
	}

	/**
	 * Method called when the focus is lost.
	 */
	@Override
	public final void focusLost(FocusEvent focusevent) {
		focusIn = false;
	}

	/**
	 * Method called when the focus is gained.
	 */
	@Override
	public final void focusGained(FocusEvent focusevent) {
		focusIn = true;
		fullRedraw = true;
	}

	/**
	 * Method used to return the <method652>URL</method652>.
	 */
	@Override
	public final URL getDocumentBase() {
		if (getFrame() != null) {
			return null;
		}
		if (GameClient.gameSignlink != null && GameClient.gameSignlink.applet != this) {
			return GameClient.gameSignlink.applet.getDocumentBase();
		}
		return super.getDocumentBase();
	}

	/**
	 * Method used to return the applet context.
	 */
	@Override
	public final AppletContext getAppletContext() {
		if (getFrame() != null) {
			return null;
		}
		if (GameClient.gameSignlink != null && this != GameClient.gameSignlink.applet) {
			return GameClient.gameSignlink.applet.getAppletContext();
		}
		return super.getAppletContext();
	}

	/**
	 * Method used to stop the client.
	 */
	@Override
	public final void stop() {
		if (this == GameShell.active_gameshell && !GameShell.isShutdown()) {
			GameShell.setKillTime(TimeTools.getMillis() + 4000L);
		}
	}

	/**
	 * Method used to a the parameter.
	 */
	@Override
	public final String getParameter(String string) {
		if (getFrame() != null) {
			return null;
		}
		if (GameClient.gameSignlink != null && GameClient.gameSignlink.applet != this) {
			return GameClient.gameSignlink.applet.getParameter(string);
		}
		return super.getParameter(string);
	}

	/**
	 * Method used to initialize the client.
	 */
	@Override
	public abstract void init();

	/**
	 * Method not refractored.
	 */
	public final void main_redraw_wrapper() {
		long time = TimeTools.getMillis();
		long l_4_ = UpdateServerNode.aLongArray2334[StaticMethods.anInt3139];
		UpdateServerNode.aLongArray2334[StaticMethods.anInt3139] = time;
		StaticMethods.anInt3139 = 0x1f & StaticMethods.anInt3139 - -1;
		if ((l_4_ ^ 0xffffffffffffffffL) != -1L && time > l_4_) {
			int i_5_ = (int) (-l_4_ + time);
			ReflectionAntiCheat.anInt79 = ((i_5_ >> 1) + 32000) / i_5_;
		}
		if (Queue.anInt415++ > 50) {
			fullRedraw = true;
			Queue.anInt415 -= 50;
			GameShell.canvas.setSize(GameShell.window_width, GameShell.window_height);
			GameShell.canvas.setVisible(true);
			if (!setting.isSwiftKit()) {
				if (getFrame() != null && !desktop) {
					Insets insets = getFrame().getInsets();
					GameShell.canvas.setLocation(insets.left, insets.top);
				} else {
					GameShell.canvas.setLocation(0, 0);
				}
			}
		}
		main_redraw_inner(true);
	}

	/**
	 * Represents the shutdown method.
	 * 
	 * @param clean
	 *              indicating if it's clean or not.
	 */
	public final void shutdown(boolean clean) {
		synchronized (this) {
			if (GameShell.isShutdown()) {
				return;
			}
			GameShell.setShutdown(true);
		}
		if (GameClient.gameSignlink.applet != null) {
			GameClient.gameSignlink.applet.destroy();
		}
		if (GameShell.canvas != null) {
			try {
				GameShell.canvas.removeFocusListener(this);
				Container container;
				if (getFrame() != null) {
					container = getFrame();
				} else {
					container = GameClient.gameSignlink.applet;
				}
				container.remove(GameShell.canvas);
			} catch (Exception exception) {
			}
		}
		try {
			method22((byte) 20);
		} catch (Exception exception) {
			/* empty */
		}
		if (GameClient.gameSignlink != null) {
			try {
				GameClient.gameSignlink.closeCacheFiles(65);
			} catch (Exception exception) {
				/* empty */
			}
		}
		destruct((byte) 56);
		if (getFrame() != null) {
			try {
				System.exit(0);
			} catch (Throwable throwable) {
				/* empty */
			}
		}
		Logger.log("Shutdown complete - clean:" + clean);
		System.exit(-1);
	}

	/**
	 * Method used to update the graphics.
	 */
	@Override
	public final void update(Graphics graphics) {
		paint(graphics);
	}

	public final void main_loop_wrapper(boolean bool) {
		if (bool != true) {
			StringConstants.optionSelect = null;
		}
		long l = TimeTools.getMillis();
		long l_6_ = Queue.aLongArray421[VarpDefinition.anInt3735];
		Queue.aLongArray421[VarpDefinition.anInt3735] = l;
		VarpDefinition.anInt3735 = VarpDefinition.anInt3735 + 1 & 0x1f;
		if (l_6_ != 0L && l_6_ < l) {
			/* empty */
		}
		synchronized (this) {
			windowActive = focusIn;
		}
		update(45);
	}

	/**
	 * Method used to destroy the client.
	 */
	@Override
	public final void destroy() {
		if (GameShell.active_gameshell == this && !GameShell.isShutdown()) {
			GameShell.setKillTime(TimeTools.getMillis());
			TimeTools.sleep(5000L);
			StaticMethods.aSignLink_3348 = null;
			shutdown(false);
		}
		System.exit(-1);
	}

	/**
	 * Method used to a the code base.
	 */
	@Override
	public final URL getCodeBase() {
		if (getFrame() != null) {
			return null;
		}
		if (GameClient.gameSignlink != null && this != GameClient.gameSignlink.applet) {
			return GameClient.gameSignlink.applet.getCodeBase();
		}
		return super.getCodeBase();
	}

	public final synchronized void addCanvas() {
		Container container;
		if (WindowMode.full_screen_frame != null) {
			container = WindowMode.full_screen_frame;
		} else if (getFrame() == null) {
			container = GameClient.gameSignlink.applet;
		} else {
			container = getFrame();
		}
		if (canvas != null) {
			canvas.removeFocusListener(this);
			container.remove(GameShell.canvas);
		}
		canvas = new ComponentCanvas(this);
		container.add(canvas);
		canvas.setSize(window_width, window_height);
		canvas.setVisible(true);
		if (getFrame() != null && !desktop) {
			Insets insets = getFrame().getInsets();
			canvas.setLocation(insets.left, insets.top);
		} else {
			canvas.setLocation(0, 0);
		}
		canvas.addFocusListener(this);
		canvas.requestFocus();
		fullRedraw = true;
		recommend_canvas_replace = false;
		last_canvas_replace = TimeTools.getMillis();
	}

	static final boolean method26(int i, int i_7_, int i_8_) {
		if (i_7_ == 11) {
			i_7_ = 10;
		}
		if (i_7_ >= 5 && i_7_ <= 8) {
			i_7_ = 4;
		}
		LocType class23_sub13_sub7 = LocTypeList.list(i);
		return class23_sub13_sub7.areModelTypesCached(i_7_);
	}

	static final void method27(boolean bool, InterfaceNode inter, boolean bool_0_) {
		int i_10_ = inter.interfaceId;
		int interfaceHash = (int) inter.uid;
		inter.unlink();
		if (bool) {
			RSInterfaceList.uncacheInterface((byte) 9, i_10_);
		}
		Class23_Sub13_Sub2.method609(i_10_);
		RSInterface class64 = RSInterface.getInterface(interfaceHash);
		if (class64 != null) {
			RSInterfaceList.setDirty(class64);
		}
		ContextMenu.menuOpen = false;
		ContextMenu.menuActionRow = 0;
		RuntimeException_Sub1.method1589(ContextMenu.menuHeight, ContextMenu.menuWidth, ContextMenu.menuOffsetX, ContextMenu.menuOffsetY, true);
		if (!bool_0_ && GameClient.interface_top_id != -1) {
			StaticMethods2.method757(GameClient.interface_top_id, 1, false);
		}
		HashTableIterator iter = new HashTableIterator(Class36.anOa565);
		for (InterfaceNode att_iter = (InterfaceNode) iter.start(); att_iter != null; att_iter = (InterfaceNode) iter.next()) {
			if (!att_iter.method227(1)) {
				att_iter = (InterfaceNode) iter.start();
				if (att_iter == null) {
					break;
				}
			}
			if (att_iter.walkable == 3) {
				int i_4_ = (int) att_iter.uid;
				if (i_4_ >>> 16 == i_10_) {
					method27(true, att_iter, bool_0_);
				}
			}
		}
	}

	/**
	 * Method called when the window is closed.
	 */
	@Override
	public final void windowClosing(WindowEvent windowevent) {
		destroy();
	}

	/**
	 * Method used to main_redraw_inner the loading text on the progress bar.
	 * 
	 * @param color
	 *                    the color.
	 * @param percentage
	 *                    the percentage.
	 * @param clearScreen
	 *                    if we clear screen.
	 * @param message
	 *                    the message to display.
	 */
	public static final void drawLoadingText(Color color, int percentage, boolean clearScreen, RSString message) {
		try {
			final Graphics graphics = GameShell.canvas.getGraphics();
			graphics.translate(-3, -14);
			if (GameShell.getLoadingFont() == null) {
				GameShell.setLoadingFont(new java.awt.Font("Helvetica", 1, 13));
				FileSystem.fontMetrics = GameShell.canvas.getFontMetrics(GameShell.getLoadingFont());
			}
			if (clearScreen) {
				graphics.setColor(Color.black);
				graphics.fillRect(0, 0, GameShell.window_width, GameShell.window_height);
			}
			if (color == null) {
				color = new Color(101, 24, 164);
			}
			try {
				if (ReflectionRequest.barImage == null) {
					ReflectionRequest.barImage = GameShell.canvas.createImage(304, 34);
				}
				Graphics grapphics = ReflectionRequest.barImage.getGraphics();
				grapphics.setColor(color);
				grapphics.drawRect(0, 0, 303, 33);
				grapphics.fillRect(2, 2, percentage * 3, 30);
				grapphics.setColor(Color.BLACK);
				grapphics.drawRect(1, 1, 301, 31);
				grapphics.fillRect(3 * percentage + 2, 2, -(3 * percentage) + 300, 30);
				grapphics.setFont(GameShell.getLoadingFont());
				grapphics.setColor(Color.WHITE);
				message.drawString(22, grapphics, (-message.method141(FileSystem.fontMetrics, -32) + 304) / 2);
				graphics.drawImage(ReflectionRequest.barImage, GameShell.window_width / 2 - 152, GameShell.window_height / 2 + -18, null);
			} catch (Exception exception) {
				int x = -152 + GameShell.window_width / 2;
				int y = -18 + GameShell.window_height / 2;
				graphics.setColor(color);
				graphics.drawRect(x, y, 303, 33);
				graphics.fillRect(x - -2, y - -2, percentage * 3, 30);
				graphics.setColor(Color.black);
				graphics.drawRect(x - -1, y - -1, 301, 31);
				graphics.fillRect(percentage * 3 + 2 + x, y - -2, -(3 * percentage) + 300, 30);
				graphics.setFont(GameShell.getLoadingFont());
				graphics.setColor(Color.white);
				message.drawString(y + 22, graphics, x - -((304 + -message.method141(FileSystem.fontMetrics, -32)) / 2));
			}
			if (ClanChatMember.aClass16_2393 != null) {
				graphics.setFont(GameShell.getLoadingFont());
				graphics.setColor(Color.white);
				ClanChatMember.aClass16_2393.drawString(GameShell.window_height / 2 + -26, graphics, GameShell.window_width / 2 - ClanChatMember.aClass16_2393.method141(FileSystem.fontMetrics, 5000 ^ ~0x1397) / 2);
			}
			graphics.translate(3, 14);
		} catch (Exception exception) {
			GameShell.canvas.repaint();
		}
	}

	final void start_applet(int i, int reivision, int i_17_, int height, int width) {
		try {
			if (GameShell.active_gameshell != null) {
				load_count++;
				if (load_count >= 3) {
					error("alreadyloaded");
				} else {
					getAppletContext().showDocument(getDocumentBase(), "_self");
				}
			} else {
				if (i != -16273) {
					start_applet(-64, 20, -124, 30, -79);
				}
				GameShell.window_height = GameShell.frame_width = height;
				top_margin = 0;
				left_margin = 0;
				GameShell.window_width = GameShell.frame_height = width;
				GameShell.active_gameshell = this;
				if (GameClient.gameSignlink == null) {
					StaticMethods.aSignLink_3348 = GameClient.gameSignlink = new SignLink(GameClient.gameSignlink.applet != null, this, i_17_, null, 0);
				}
				GameClient.gameSignlink.newRunnable(1, this, (byte) 72);
			}
		} catch (Exception exception) {
			ForceMovement.sendError(95, exception, null);
			error("crash");
		}
	}

	/**
	 * Method used to provide a sign link.
	 */
	public static final void providesignlink(SignLink signLink) {
		StaticMethods.aSignLink_3348 = GameClient.gameSignlink = signLink;
	}

	/**
	 * Method used to check if the host is valid.
	 * 
	 * @return <code>True</code> if so.
	 */
	public final boolean checkHost() {
		String string = GameClient.getSetting().getIp();
		if (string.equals("jagex.com") || string.endsWith(".jagex.com")) {
			return true;
		}
		if (string.equals("runescape.com") || string.endsWith(".runescape.com")) {
			return true;
		}
		if (string.endsWith("127.0.0.1")) {
			return true;
		}
		if (string.equals(GameLaunch.getSetting().getIp())) {
			return true;
		}
		for (/**/; string.length() > 0 && string.charAt(-1 + string.length()) >= '0' && string.charAt(-1 + string.length()) <= '9'; string = string.substring(0, string.length() - 1)) {
			/* empty */
		}
		if (string.equalsIgnoreCase(GameClient.getSetting().getIp())) {
			return true;
		}
		error("invalidhost");
		return false;
	}

	/**
	 * Method used to error the message the game.
	 * 
	 * @param error
	 */
	public final void error(String error) {
		if (!isErroredAlready()) {
			setErroredAlready(true);
			Logger.log("error_game_" + error);
			try {
				getAppletContext().showDocument(new URL(getCodeBase(), "error_game_" + error + ".ws"), "_self");
			} catch (Exception exception) {
				/* empty */
			}
		}
	}

	/**
	 * Method used to run the client.
	 */
	@Override
	public final void run() {
		try {
			if (GameShell.java_vendor != null) {
				String string = GameShell.java_vendor.toLowerCase();
				if (string.indexOf("sun") == -1 && string.indexOf("apple") == -1) {
					if ((string.indexOf("ibm") ^ 0xffffffff) != 0 && (GameShell.java_version == null || GameShell.java_version.equals("1.4.2"))) {
						error("wrongjava");
						return;
					}
				} else {
					String string_22_ = GameShell.java_version;
					if (string_22_.equals("1.1") || string_22_.startsWith("1.1.") || string_22_.equals("1.2") || string_22_.startsWith("1.2.")) {
						error("wrongjava");
						return;
					}
					StaticMethods.anInt3167 = 5;
				}
			}
			if (java_version != null && java_version.startsWith("1.")) {
				int index = 2;
				int minor_version = 0;
				while (java_version.length() > index) {
					int cur_char = java_version.charAt(index);
					if (cur_char < 48 || cur_char > 57) {
						break;
					}
					index++;
					minor_version = 10 * minor_version + cur_char - 48;
				}
				if (minor_version >= 5) {
					have_java5_or_better = true;
				}
			}
			if (GameClient.gameSignlink.applet != null) {
				GameClient.gameSignlink.applet.setFocusCycleRoot(true);
			}
			max_memory = (int) (Runtime.getRuntime().maxMemory() / 1048576L) + 1;
			addCanvas();
			GameClient.software_frame_buffer = SoftwareGraphicsBuffer.create(GameShell.canvas, GameShell.window_width, GameShell.window_height);
			main_init((byte) 121);
			frame_timebase = SeekableFile.create((byte) -88);
			while (killTime == 0L || TimeTools.getMillis() < killTime) {
				missed_logic_steps = frame_timebase.wait_for_next_frame(Class91.anInt1556, (byte) 66, StaticMethods.anInt3167);
				for (int i = 0; missed_logic_steps > i; i++) {
					main_loop_wrapper(true);
				}
				main_redraw_wrapper();
				create_dummy_actionevent(GameShell.canvas, 0, GameClient.gameSignlink);
			}
		} catch (Exception exception) {
			ForceMovement.sendError(95, exception, null);
			error("crash");
		}
		shutdown(true);
	}

	/**
	 * Method used to paint the screen.
	 */
	@Override
	public final synchronized void paint(Graphics graphics) {
		if (GameShell.active_gameshell == this && !GameShell.isShutdown()) {
			GameShell.fullRedraw = true;
			if (have_java5_or_better && (-GameShell.last_canvas_replace + TimeTools.getMillis() ^ 0xffffffffffffffffL) < -1001L) {
				Rectangle rectangle = graphics.getClipBounds();
				if (rectangle == null || rectangle.width >= GameShell.window_width && rectangle.height >= GameShell.window_height) {
					recommend_canvas_replace = true;
				}
			}
		}
	}

	@Override
	public final void windowIconified(WindowEvent windowevent) {
	}

	@Override
	public final void windowDeactivated(WindowEvent windowevent) {
	}

	@Override
	public final void windowOpened(WindowEvent windowevent) {
	}

	@Override
	public final void windowActivated(WindowEvent windowevent) {
	}

	@Override
	public final void windowDeiconified(WindowEvent windowevent) {
	}

	@Override
	public final void windowClosed(WindowEvent windowevent) {
	}

	abstract void main_redraw_inner(boolean bool);

	abstract void main_init(byte b);

	abstract void method22(byte b);

	/**
	 * @return the frame
	 */
	public static Frame getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *              the frame to set
	 */
	public static void setFrame(Frame frame) {
		GameShell.frame = frame;
	}

	/**
	 * @return the killTime
	 */
	public static long getKillTime() {
		return killTime;
	}

	/**
	 * @param killTime
	 *                 the killTime to set
	 */
	public static void setKillTime(long killTime) {
		GameShell.killTime = killTime;
	}

	/**
	 * @return the isShutdown
	 */
	public static boolean isShutdown() {
		return isShutdown;
	}

	/**
	 * @param isShutdown
	 *                   the isShutdown to set
	 */
	public static void setShutdown(boolean isShutdown) {
		GameShell.isShutdown = isShutdown;
	}

	/**
	 * @return the erroredAlready
	 */
	public boolean isErroredAlready() {
		return erroredAlready;
	}

	/**
	 * @param erroredAlready
	 *                       the erroredAlready to set
	 */
	public void setErroredAlready(boolean erroredAlready) {
		this.erroredAlready = erroredAlready;
	}

	/**
	 * A method which is not refactored.
	 */
	abstract void destruct(byte b);

	/**
	 * A method which is not refactored.
	 */
	public abstract void update(int i);

	/**
	 * @return the canvas
	 */
	public static Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas
	 *               the canvas to set
	 */
	public static void setCanvas(Canvas canvas) {
		GameShell.canvas = canvas;
	}

	/**
	 * @return the loadingFont
	 */
	static java.awt.Font getLoadingFont() {
		return loadingFont;
	}

	/**
	 * @param loadingFont
	 *                    the loadingFont to set
	 */
	public static void setLoadingFont(java.awt.Font loadingFont) {
		GameShell.loadingFont = loadingFont;
	}

	/**
	 * @return the javaVendor
	 */
	public static String getJavaVendor() {
		return java_vendor;
	}

	/**
	 * @return the javaVersion
	 */
	public static String getJavaVersion() {
		return java_version;
	}

	/**
	 * @return the lastCanvasReplace
	 */
	public static long getLastCanvasReplace() {
		return last_canvas_replace;
	}

	/**
	 * @return the gameApplet
	 */
	public static GameShell getActive_gameshell() {
		return active_gameshell;
	}

	/**
	 * @return the fullRedraw
	 */
	public static boolean isFullRedraw() {
		return fullRedraw;
	}

	/**
	 * @return the focusIn
	 */
	public static boolean isFocusIn() {
		return focusIn;
	}

	/**
	 * @param height
	 *               the height to set
	 */
	public static void setHeight(int height) {
		GameShell.window_height = height;
	}

	/**
	 * @param width
	 *              the width to set
	 */
	public static void setWidth(int width) {
		GameShell.window_width = width;
	}

	/**
	 * @param javaVendor
	 *                   the javaVendor to set
	 */
	public static void setJavaVendor(String javaVendor) {
		GameShell.java_vendor = javaVendor;
	}

	/**
	 * @param javaVersion
	 *                    the javaVersion to set
	 */
	public static void setJavaVersion(String javaVersion) {
		GameShell.java_version = javaVersion;
	}

	/**
	 * @param lastCanvasReplace
	 *                          the lastCanvasReplace to set
	 */
	public static void setLastCanvasReplace(long lastCanvasReplace) {
		GameShell.last_canvas_replace = lastCanvasReplace;
	}

	/**
	 * @param active_gameshell
	 *                         the gameApplet to set
	 */
	public static void setActive_gameshell(GameShell active_gameshell) {
		GameShell.active_gameshell = active_gameshell;
	}

	/**
	 * Sets the settings.
	 * 
	 * @param setting
	 *                the setting.
	 */
	public void setSettings(GameSetting setting) {
		this.setting = setting;
	}

	/**
	 * @param fullRedraw
	 *                   the fullRedraw to set
	 */
	public static void setFullRedraw(boolean fullRedraw) {
		GameShell.fullRedraw = fullRedraw;
	}

	/**
	 * @param focusIn
	 *                the focusIn to set
	 */
	public static void setFocusIn(boolean focusIn) {
		GameShell.focusIn = focusIn;
	}

	/**
	 * @return the desktop
	 */
	public static boolean isDesktop() {
		return desktop;
	}

	/**
	 * @param desktop
	 *                the desktop to set
	 */
	public static void setDesktop(boolean desktop) {
		GameShell.desktop = desktop;
	}
}
