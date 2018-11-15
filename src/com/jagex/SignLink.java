package com.jagex;
/* SignLink - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.applet.Applet;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

import com.jagex.launcher.Configurations;

public class SignLink implements Runnable {
	public SignlinkRequest request_stack_top;
	public FileOnDisk random_file;
	public SignlinkRequest request_stack_bottom;
	public FileOnDisk cache_data_file;
	public boolean shutdown;
	public Applet applet = null;
	public EventQueue anEventQueue189;
	public static int anInt190 = 3;
	public FileOnDisk[] cache_index_files;
	public Thread signlink_thread;
	public StereoInterface anStereoInterface_193;
	public File cacheDirectory;
	public FileOnDisk cache_i255_file;
	public static String user_home;
	public boolean microsft_java;
	public Object sun_display_device;

	public final void closeCacheFiles(int i) {
		synchronized (this) {
			shutdown = true;
			notifyAll();
		}
		try {
			signlink_thread.join();
		} catch (InterruptedException interruptedexception) {
			/* empty */
		}
		if (cache_data_file != null) {
			try {
				cache_data_file.close();
			} catch (java.io.IOException ioexception) {
				/* empty */
			}
		}
		if (cache_i255_file != null) {
			try {
				cache_i255_file.close();
			} catch (java.io.IOException ioexception) {
				/* empty */
			}
		}
		if (cache_index_files != null) {
			for (FileOnDisk cache_index_file : cache_index_files) {
				if (cache_index_file != null) {
					try {
						cache_index_file.close();
					} catch (java.io.IOException ioexception) {
						/* empty */
					}
				}
			}
		}
		if (random_file != null) {
			try {
				random_file.close();
			} catch (java.io.IOException ioexception) {

			}
		}
	}

	public final SignlinkRequest list_video_modes() {
		return newThread(0, 5, (byte) 0, 0, null);
	}

	public final SignlinkRequest exit_full_screen(Frame frame) {
		return newThread(0, 7, (byte) 0, 0, frame);
	}

	public final SignlinkRequest enter_full_screen(int width, int height, int bit_depth, int refresh_rate) {
		return newThread((width << 16) + height, 6, (byte) 0, bit_depth + (refresh_rate << 16), null);
	}

	public final boolean can_change_resolution() {
		return false;// this.is_signed && (this.ms_java ? false /*microsoft_display_device != null*/ : sun_display_device != null);//NOTE:
						// ENABLE THIS ON M$ BUILDS
	}

	public final SignlinkRequest newThread(int port, int opcode, byte b, int i_2_, Object object) {
		SignlinkRequest request = new SignlinkRequest();
		request.object_parameter = object;
		request.int_parameter_2 = i_2_;
		request.priority = port;
		request.opcode = opcode;
		synchronized (this) {
			if (request_stack_bottom != null) {
				request_stack_bottom.next = request;
				request_stack_bottom = request;
			} else {
				request_stack_bottom = request_stack_top = request;
			}
			notify();
		}
		return request;
	}

	public final SignlinkRequest newSimpleThread(int i, int i_3_) {
		if (i <= 2) {
			get_declared_field(null, -90, null);
		}
		return newThread(i_3_, 3, (byte) -58, 0, null);
	}

	public final SignlinkRequest newURLThread(URL url, int i) {
		if (i < 109) {
			method108(false);
		}
		return newThread(0, 4, (byte) -38, 0, url);
	}

	public final void initializeCache(String string, int i, int i_4_) {
		for (int i_6_ = 0; i_6_ < 2; i_6_++) {
			try {
				File cacheDirectory = new File(Configurations.getCachePath());
				if (i_6_ == 1 && !cacheDirectory.exists()) {
					boolean bool = cacheDirectory.mkdir();
					if (!bool) {
						continue;
					}
				}
				if (random_file == null) {
					try {
						File file_10_ = new File(cacheDirectory, "random.dat");
						if (i_6_ == 1 || file_10_.exists()) {
							random_file = new FileOnDisk(file_10_, "rw", 25L);
						}
					} catch (Exception exception) {
						random_file = null;
					}
				}
				if (this.cacheDirectory == null) {
					try {
						cacheDirectory = new File(cacheDirectory, string);
						if (i_6_ == 1 && !cacheDirectory.exists()) {
							boolean bool = cacheDirectory.mkdir();
							if (!bool) {
								continue;
							}
						}
						File file_11_ = new File(cacheDirectory, "main_file_cache.dat2");
						if (i_6_ == 0 && !file_11_.exists()) {
							continue;
						}
						cache_data_file = new FileOnDisk(file_11_, "rw", 1048576000L);
						cache_index_files = new FileOnDisk[i_4_];
						for (int i_12_ = 0; i_12_ < i_4_; i_12_++) {
							cache_index_files[i_12_] = new FileOnDisk(new File(cacheDirectory, "main_file_cache.idx" + i_12_), "rw", 1048576L);
						}
						cache_i255_file = new FileOnDisk(new File(cacheDirectory, "main_file_cache.idx255"), "rw", 1048576L);
						this.cacheDirectory = cacheDirectory;
					} catch (Exception exception) {
						exception.printStackTrace();
						try {
							cache_data_file.close();
							for (int i_13_ = 0; i_13_ < i_4_; i_13_++) {
								cache_index_files[i_13_].close();
							}
							cache_i255_file.close();
						} catch (Exception exception_14_) {
							/* empty */
						}
						cache_data_file = cache_i255_file = null;
						cache_index_files = null;
						this.cacheDirectory = null;
					}
				}
			} catch (Exception exception) {
				/* empty */
			}
			if (random_file != null && cacheDirectory != null) {
				return;
			}
		}
		if (cacheDirectory == null) {
			throw new RuntimeException("here56");
		}
	}

	public final SignlinkRequest newRunnable(int priority, Runnable runnable, byte b) {
		return newThread(priority, 2, (byte) -22, 0, runnable);
	}

	public final SignlinkRequest openSocket(String string, int port) {
		return newThread(port, 1, (byte) -41, 0, string);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final void run() {
		for (;;) {
			SignlinkRequest current_request;
			synchronized (this) {
				for (;;) {
					if (shutdown) {
						return;
					}
					if (request_stack_top != null) {
						current_request = request_stack_top;
						request_stack_top = request_stack_top.next;
						if (request_stack_top == null) {
							request_stack_bottom = null;
						}
						break;
					}
					try {
						this.wait();
					} catch (InterruptedException interruptedexception) {
						/* empty */
					}
				}
			}
			try {
				int i = current_request.opcode;
				if (i == 1) {
					current_request.result = new Socket(InetAddress.getByName((String) current_request.object_parameter), current_request.priority);
				} else if (i != 2) {
					if (i != 4) {
						if (i == 8) {
							Object[] objects = (Object[]) current_request.object_parameter;

							current_request.result = ((Class) objects[0]).getDeclaredMethod((String) objects[1], (Class[]) objects[2]);
						} else if (i == 9) {
							Object[] objects = (Object[]) current_request.object_parameter;
							current_request.result = ((Class) objects[0]).getDeclaredField((String) objects[1]);
						} else {
							// throw new Exception();
						}
					} else {
						// TODO: Uses website for data.
						// TODO: This is used for world list.
						current_request.result = new DataInputStream(((URL) current_request.object_parameter).openStream());
					}
				} else {
					Thread thread = new Thread((Runnable) current_request.object_parameter);
					thread.setDaemon(true);
					thread.start();
					thread.setPriority(current_request.priority);
					current_request.result = thread;
				}
				current_request.status = 1;
			} catch (Throwable throwable) {
				current_request.status = 2;
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public final SignlinkRequest get_declared_method(String string, Class[] var_classes, Class classType, int i) {
		if (i != 2) {
			signlink_thread = null;
		}
		return newThread(0, 8, (byte) -102, 0, new Object[] { classType, string, var_classes });
	}

	@SuppressWarnings("rawtypes")
	public final SignlinkRequest get_declared_field(Class type, int i, String string) {
		if (i >= -12) {
			return null;
		}
		return newThread(0, 9, (byte) -125, 0, new Object[] { type, string });
	}

	public final StereoInterface method108(boolean bool) {
		if (bool != true) {
			get_declared_field(null, -66, null);
		}
		return anStereoInterface_193;
	}

	public boolean is_signed;

	public SignLink(boolean is_signed, Applet applet, int i, String string, int i_16_) {
		cache_data_file = null;
		request_stack_top = null;
		request_stack_bottom = null;
		random_file = null;
		shutdown = false;
		cache_i255_file = null;
		cacheDirectory = null;
		this.is_signed = false;
		this.is_signed = is_signed;
		GameShell.java_version = "1.1";
		GameShell.java_vendor = "Unknown";
		this.applet = applet;
		try {
			GameShell.java_vendor = System.getProperty("java.vendor");
			GameShell.java_version = System.getProperty("java.version");
		} catch (Exception exception) {
			/* empty */
		}
		if (GameShell.java_vendor.toLowerCase().contains("microsoft")) {
			microsft_java = true;
		}
		GameShell.os_arch = System.getProperty("os.arch");
		try {
			GameShell.os_name_raw = System.getProperty("os.name");
		} catch (Exception exception) {
			GameShell.os_name_raw = "Unknown";
		}
		GameShell.os_name = GameShell.os_name_raw.toLowerCase();
		try {
			user_home = System.getProperty("user.home");
			if (user_home != null) {
				user_home += "/";
			}
		} catch (Exception exception) {
			/* empty */
		}
		if (user_home == null) {
			user_home = "~/";
		}
		try {
			anEventQueue189 = Toolkit.getDefaultToolkit().getSystemEventQueue();
		} catch (Throwable throwable) {
			/* empty */
		}
		if (is_signed) {
			initializeCache(string, i, i_16_);
		}
		shutdown = false;
		signlink_thread = new Thread(this);
		signlink_thread.setPriority(10);
		signlink_thread.setDaemon(true);
		signlink_thread.start();
	}
}
