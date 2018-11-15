package com.jagex;
import java.awt.Component;

class Stereo
{
	public long aLong127;
	static RSString aClass16_128 = RSString.createString(" weitere Optionen");
	public Class23_Sub10 aClass23_Sub10_130;
	public int[] samples;
	static RSString aClass16_136;
	public int anInt138 = 32;
	static RSString aClass16_141 = RSString.createString("; Max)2Age=");
	public static int[] anIntArray144;
	public int anInt149;
	public int anInt150;
	public boolean aBoolean151;
	public int anInt152;
	public Class23_Sub10[] aClass23_Sub10Array153;
	public int anInt154;
	public Class23_Sub10[] aClass23_Sub10Array155;
	public int anInt156;
	public long aLong157;
	public int anInt158;
	public int anInt159;
	public long aLong160;
	
	final synchronized void method71(boolean bool) {
		aBoolean151 = true;
		try {
			flush_line();
		} catch (Exception exception) {
			close_line();
			aLong157 = 2000L + TimeTools.getMillis();
		}
		if (bool != false) {
			method75(-69);
		}
	}
	
	final synchronized void method72(int i) {
		if (Class28.aClass33_428 != null) {
			boolean bool = true;
			for (int i_0_ = 0; i_0_ < 2; i_0_++) {
				if (this == Class28.aClass33_428.aStereoArray512[i_0_]) {
					Class28.aClass33_428.aStereoArray512[i_0_] = null;
				}
				if (Class28.aClass33_428.aStereoArray512[i_0_] != null) {
					bool = false;
				}
			}
			if (bool) {
				Class28.aClass33_428.aBoolean511 = true;
				while (Class28.aClass33_428.aBoolean517) {
					TimeTools.sleep(50L);
				}
				Class28.aClass33_428 = null;
			}
		}
		close_line();
		if (i != 0) {
			method71(false);
		}
		samples = null;
	}
	
	public final void method73(byte b, int i, Class23_Sub10 class23_sub10) {
		int i_1_ = i >> 5;
		Class23_Sub10 class23_sub10_2_ = aClass23_Sub10Array153[i_1_];
		if (class23_sub10_2_ != null) {
			class23_sub10_2_.aClass23_Sub10_2277 = class23_sub10;
		} else {
			aClass23_Sub10Array155[i_1_] = class23_sub10;
		}
		aClass23_Sub10Array153[i_1_] = class23_sub10;
		if (b == -41) {
			class23_sub10.anInt2275 = i;
		}
	}
	
	public final void method74(byte b, int i) {
		anInt156 -= i;
		if ((anInt156 ^ 0xffffffff) > -1) {
			anInt156 = 0;
		}
		if (aClass23_Sub10_130 != null) {
			aClass23_Sub10_130.method507(i);
		}
		if (b >= -126) {
			aLong127 = 37L;
		}
	}
	
	public static void method75(int i) {
		aClass16_141 = null;
		anIntArray144 = null;
		aClass16_136 = null;
		if (i == -15675) {
			aClass16_128 = null;
		}
	}
	
	static final void method76(int dummy, int interfaceId) {
		if (AbstractTimer.hasActiveInterface(-10924, interfaceId) && dummy == 15532) {
			RSInterface[] class64s = StaticMethods.cached_interfaces[interfaceId];
			for (int i_4_ = 0; (class64s.length ^ 0xffffffff) < (i_4_ ^ 0xffffffff); i_4_++) {
				RSInterface class64 = class64s[i_4_];
				if (class64 != null) {
					class64.media_tween_tick = 0;
					class64.media_current_frameid = 0;
					class64.media_next_frameid = 1;
				}
			}
		}
	}
	
	final void method77(int i) {
		aBoolean151 = true;
		if (i == 256) {
		}
	}
	
	final synchronized void method78(Class23_Sub10 class23_sub10, int i) {
		aClass23_Sub10_130 = class23_sub10;
		if (i != -15878) {
			aClass16_128 = null;
		}
	}
	
	void copy_buffers() throws Exception {
	}
	
	int get_buffer_used_size() throws Exception {
		return anInt150;
	}
	
	void initialize(Component component) throws Exception {
	}
	
	void flush_line() throws Exception {
	}
	
	void close_line() {

	}
	
	static final void method84(boolean bool) {
		synchronized (InputManager.mouse) {
			InteractiveEntity.idleMouseTicks++;
			SongUpdater.anInt175 = Class19.anInt325;
			Mouse.mouseX = StaticMethods.anInt3449;
			if (bool != true) {
				method76(46, -81);
			}
			Mouse.mouseY = StringNode.anInt2469;
			Mouse.mouseClickState = CullingCluster.anInt918;
			Mouse.mouseClickX = Class79.anInt1888;
			Mouse.mouseClickY = StaticMethods.anInt3289;
			Class23_Sub13_Sub12.aLong3975 = StaticMethods.aLong3497;
			CullingCluster.anInt918 = 0;
		}
	}
	
	final synchronized void method85(byte b) {
		if (samples != null) {
			long l = TimeTools.getMillis();
			try {
				if ((aLong157 ^ 0xffffffffffffffffL) != -1L) {
					if (aLong157 > l) {
						return;
					}
					open_line(anInt150);
					aLong157 = 0L;
					aBoolean151 = true;
				}
				int i = get_buffer_used_size();
				int i_5_ = anInt158 + anInt149;
				if (256 + i_5_ > 16384) {
					i_5_ = 16128;
				}
				if ((-i + anInt154 ^ 0xffffffff) < (anInt152 ^ 0xffffffff)) {
					anInt152 = anInt154 - i;
				}
				if ((i_5_ - -256 ^ 0xffffffff) < (anInt150 ^ 0xffffffff)) {
					anInt150 += 1024;
					i = 0;
					if (anInt150 > 16384) {
						anInt150 = 16384;
					}
					close_line();
					open_line(anInt150);
					aBoolean151 = true;
					if ((anInt150 ^ 0xffffffff) > (i_5_ + 256 ^ 0xffffffff)) {
						i_5_ = anInt150 + -256;
						anInt149 = -anInt158 + i_5_;
					}
				}
				for (/**/; (i_5_ ^ 0xffffffff) < (i ^ 0xffffffff); i += 256) {
					method87(samples, 256);
					copy_buffers();
				}
				if ((aLong160 ^ 0xffffffffffffffffL) > (l ^ 0xffffffffffffffffL)) {
					if (aBoolean151) {
						aBoolean151 = false;
					} else {
						if ((anInt152 ^ 0xffffffff) == -1 && anInt159 == 0) {
							close_line();
							aLong157 = 2000L + l;
							return;
						}
						anInt149 = Math.min(anInt159, anInt152);
						anInt159 = anInt152;
					}
					aLong160 = l - -2000L;
					anInt152 = 0;
				}
				if (b != -39) {
					anInt159 = -2;
				}
				anInt154 = i;
			} catch (Exception exception) {
				close_line();
				aLong157 = 2000L + l;
			}
			try {
				if ((l ^ 0xffffffffffffffffL) < (500000L + aLong127 ^ 0xffffffffffffffffL)) {
					l = aLong127;
				}
				for (/**/; (l ^ 0xffffffffffffffffL) < (aLong127 - -5000L ^ 0xffffffffffffffffL); aLong127 += 256000 / Keyboard.sampleRate) {
					method74((byte) -127, 256);
				}
			} catch (Exception exception) {
				aLong127 = l;
			}
		}
	}
	
	void open_line(int i) throws Exception {
	}
	
	public final void method87(int[] samples, int len) {
		int sample_count = len;
		if (FileSystem.stereo) {
			sample_count <<= 1;
		}
		ArrayUtils.clear_array(samples, 0, sample_count);
		anInt156 -= len;
		if (aClass23_Sub10_130 != null && anInt156 <= 0) {
			anInt156 += Keyboard.sampleRate >> 4;
			GameClient.method40(aClass23_Sub10_130, true);
			method73((byte) -41, aClass23_Sub10_130.method505(), aClass23_Sub10_130);
			int i_7_ = 0;
			int i_8_ = 255;
			int i_9_ = 7;
		while_8_:
			while (i_8_ != 0) {
				int i_10_;
				int i_11_;
				if (i_9_ < 0) {
					i_10_ = i_9_ & 0x3;
					i_11_ = -(i_9_ >> 2);
				} else {
					i_10_ = i_9_;
					i_11_ = 0;
				}
				for (int i_12_ = i_8_ >>> i_10_ & 0x11111111; i_12_ != 0; i_12_ >>>= 4) {
					if ((i_12_ & 0x1) != 0) {
						i_8_ &= 1 << i_10_ ^ 0xffffffff;
						Class23_Sub10 class23_sub10 = null;
						Class23_Sub10 class23_sub10_13_ = aClass23_Sub10Array155[i_10_];
						while (class23_sub10_13_ != null) {
							AbstractSound class23_sub6 = class23_sub10_13_.aClass23_Sub6_2278;
							if (class23_sub6 != null && class23_sub6.anInt2193 > i_11_) {
								i_8_ |= 1 << i_10_;
								class23_sub10 = class23_sub10_13_;
								class23_sub10_13_ = class23_sub10_13_.aClass23_Sub10_2277;
							} else {
								class23_sub10_13_.aBoolean2276 = true;
								int i_14_ = class23_sub10_13_.method501();
								i_7_ += i_14_;
								if (class23_sub6 != null) {
									class23_sub6.anInt2193 += i_14_;
								}
								if (i_7_ >= anInt138) {
									break while_8_;
								}
								Class23_Sub10 class23_sub10_15_ = class23_sub10_13_.method503();
								if (class23_sub10_15_ != null) {
									int i_16_ = class23_sub10_13_.anInt2275;
									for (/**/; class23_sub10_15_ != null; class23_sub10_15_ = class23_sub10_13_.method502()) {
										method73((byte) -41, i_16_ * class23_sub10_15_.method505() >> 8, class23_sub10_15_);
									}
								}
								Class23_Sub10 class23_sub10_17_ = class23_sub10_13_.aClass23_Sub10_2277;
								class23_sub10_13_.aClass23_Sub10_2277 = null;
								if (class23_sub10 == null) {
									aClass23_Sub10Array155[i_10_] = class23_sub10_17_;
								} else {
									class23_sub10.aClass23_Sub10_2277 = class23_sub10_17_;
								}
								if (class23_sub10_17_ == null) {
									aClass23_Sub10Array153[i_10_] = class23_sub10;
								}
								class23_sub10_13_ = class23_sub10_17_;
							}
						}
					}
					i_10_ += 4;
					i_11_++;
				}
				i_9_--;
			}
			for (int i_18_ = 0; i_18_ < 8; i_18_++) {
				Class23_Sub10 class23_sub10 = aClass23_Sub10Array155[i_18_];
				aClass23_Sub10Array155[i_18_] = aClass23_Sub10Array153[i_18_] = null;
				Class23_Sub10 class23_sub10_19_;
				for (/**/; class23_sub10 != null; class23_sub10 = class23_sub10_19_) {
					class23_sub10_19_ = class23_sub10.aClass23_Sub10_2277;
					class23_sub10.aClass23_Sub10_2277 = null;
				}
			}
		}
		if (anInt156 < 0) {
			anInt156 = 0;
		}
		if (aClass23_Sub10_130 != null) {
			aClass23_Sub10_130.generate_samples(samples, 0, len);
		}
		aLong127 = TimeTools.getMillis();
	}
	
	public Stereo() {
		aLong127 = TimeTools.getMillis();
		anInt152 = 0;
		anInt156 = 0;
		aClass23_Sub10Array153 = new Class23_Sub10[8];
		anInt159 = 0;
		anInt154 = 0;
		aLong160 = 0L;
		aClass23_Sub10Array155 = new Class23_Sub10[8];
		aLong157 = 0L;
		aBoolean151 = true;
	}
	
	static {
		aClass16_136 = RSString.createString("Hierhin gehen");
	}
}
