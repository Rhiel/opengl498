package com.jagex;

import com.jagex.graphics.runetek4.media.Model;

public class SpotEntity extends SceneNode {
	public int current_frameid = 0;
	public int next_frameid = 1;
	public SeqType aClass23_Sub13_Sub22_2610;
	public int startTime;
	public int anInt2615 = -32768;
	static RSString aClass16_2616;
	public int graphicId;
	static byte[] aByteArray2618 = { 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };
	public int plane;
	public int locationY;
	public boolean animationless;
	public int height;
	public int tick = 0;
	public int locationX;

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
	}

	@Override
	public final void draw2(int i, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_, int i_9_, int i_10_, long l, int bufferOffset) {
		Model class38_sub1 = method1078(50);
		if (class38_sub1 != null) {
			class38_sub1.draw2(i, i_4_, i_5_, i_6_, i_7_, i_8_, i_9_, i_10_, l, bufferOffset);
			anInt2615 = class38_sub1.get_miny();
		}
	}

	public static void method1077(byte b) {
		aByteArray2618 = null;
		aClass16_2616 = null;
		if (b < 73) {
			method1077((byte) 57);
		}
	}

	public final Model method1078(int i) {
		SpotType class23_sub13_sub17 = SpotType.list(graphicId);
		Model class38_sub1;
		if (animationless) {
			class38_sub1 = class23_sub13_sub17.get_model(-1, -1, 0);
		} else {
			class38_sub1 = class23_sub13_sub17.get_model(current_frameid, next_frameid, tick);
		}
		if (class38_sub1 == null) {
			return null;
		}
		return class38_sub1;
	}

	@Override
	public final int get_miny() {
		return anInt2615;
	}

	public final void method1079(int i, int i_12_) {
		if (!animationless) {
			tick += i;
			if (i_12_ != -2) {
				get_miny();
			}
			while (aClass23_Sub13_Sub22_2610.frames_durations[current_frameid] < tick) {
				tick -= aClass23_Sub13_Sub22_2610.frames_durations[current_frameid];
				current_frameid++;
				if ((current_frameid ^ 0xffffffff) <= (aClass23_Sub13_Sub22_2610.frames_data.length ^ 0xffffffff)) {
					animationless = true;
					break;
				}
				next_frameid = current_frameid + 1;
				if (aClass23_Sub13_Sub22_2610.frames_data.length <= next_frameid) {
					next_frameid -= aClass23_Sub13_Sub22_2610.replay_interval;
					if (-1 < ~next_frameid || ~next_frameid <= ~aClass23_Sub13_Sub22_2610.frames_data.length) {
						next_frameid = -1;
					}
				}
			}
		}
	}

	static final void method1080(int junk, int dummy, LobbyWorld[] worlds, int worldSize) {
		if ((worldSize ^ 0xffffffff) < (junk ^ 0xffffffff)) {
			int i_15_ = -1 + junk;
			int i_16_ = (worldSize + junk) / 2;
			int i_17_ = 1 + worldSize;
			LobbyWorld class82 = worlds[i_16_];
			worlds[i_16_] = worlds[junk];
			worlds[junk] = class82;
			while ((i_17_ ^ 0xffffffff) < (i_15_ ^ 0xffffffff)) {
				boolean bool = true;
				do {
					i_17_--;
					for (int i_18_ = 0; i_18_ < 4; i_18_++) {
						int i_19_;
						int i_20_;
						if (Class88.anIntArray1507[i_18_] != 2) {
							if (Class88.anIntArray1507[i_18_] != 1) {
								if (Class88.anIntArray1507[i_18_] == 3) {
									i_20_ = !worlds[i_17_].memberWorld ? 0 : 1;
									i_19_ = !class82.memberWorld ? 0 : 1;
								} else {
									i_20_ = worlds[i_17_].worldId;
									i_19_ = class82.worldId;
								}
							} else {
								i_20_ = worlds[i_17_].players;
								i_19_ = class82.players;
								if (i_20_ == -1 && StaticMethods2.anIntArray2401[i_18_] == 1) {
									i_20_ = 2001;
								}
								if (i_19_ == -1 && StaticMethods2.anIntArray2401[i_18_] == 1) {
									i_19_ = 2001;
								}
							}
						} else {
							i_19_ = class82.worldIndex;
							i_20_ = worlds[i_17_].worldIndex;
						}
						if (i_20_ == i_19_) {
							if (i_18_ == 3) {
								bool = false;
							}
						} else {
							if ((StaticMethods2.anIntArray2401[i_18_] != 1 || (i_19_ ^ 0xffffffff) <= (i_20_ ^ 0xffffffff)) && ((StaticMethods2.anIntArray2401[i_18_] ^ 0xffffffff) != -1 || (i_19_ ^ 0xffffffff) >= (i_20_ ^ 0xffffffff))) {
								bool = false;
							}
							break;
						}
					}
				} while (bool);
				bool = true;
				do {
					i_15_++;
					for (int i_21_ = 0; i_21_ < 4; i_21_++) {
						int i_22_;
						int i_23_;
						if (Class88.anIntArray1507[i_21_] == 2) {
							i_23_ = worlds[i_15_].worldIndex;
							i_22_ = class82.worldIndex;
						} else if (Class88.anIntArray1507[i_21_] == 1) {
							i_22_ = class82.players;
							i_23_ = worlds[i_15_].players;
							if ((i_22_ ^ 0xffffffff) == 0 && StaticMethods2.anIntArray2401[i_21_] == 1) {
								i_22_ = 2001;
							}
							if ((i_23_ ^ 0xffffffff) == 0 && StaticMethods2.anIntArray2401[i_21_] == 1) {
								i_23_ = 2001;
							}
						} else if (Class88.anIntArray1507[i_21_] != 3) {
							i_23_ = worlds[i_15_].worldId;
							i_22_ = class82.worldId;
						} else {
							i_22_ = class82.memberWorld ? 1 : 0;
							i_23_ = worlds[i_15_].memberWorld ? 1 : 0;
						}
						if (i_23_ == i_22_) {
							if (i_21_ == 3) {
								bool = false;
							}
						} else {
							if ((StaticMethods2.anIntArray2401[i_21_] != 1 || i_22_ <= i_23_) && ((StaticMethods2.anIntArray2401[i_21_] ^ 0xffffffff) != -1 || (i_22_ ^ 0xffffffff) <= (i_23_ ^ 0xffffffff))) {
								bool = false;
							}
							break;
						}
					}
				} while (bool);
				if ((i_17_ ^ 0xffffffff) < (i_15_ ^ 0xffffffff)) {
					LobbyWorld class82_24_ = worlds[i_15_];
					worlds[i_15_] = worlds[i_17_];
					worlds[i_17_] = class82_24_;
				}
			}
			method1080(junk, dummy, worlds, i_17_);
			method1080(1 + i_17_, 0, worlds, worldSize);
		}
		if (dummy != 0) {
			method1080(-91, -77, null, -92);
		}
	}

	SpotEntity(int graphicId, int plane, int offsetX, int offsetY, int height, int delay, int creationTime) {
		animationless = false;
		this.graphicId = graphicId;
		locationX = offsetX;
		this.height = height;
		this.plane = plane;
		locationY = offsetY;
		startTime = delay + creationTime; // Delay?
		int animId = SpotType.list(graphicId).seqid;
		if (animId == -1) {
			animationless = true;
		} else {
			animationless = false;
			aClass23_Sub13_Sub22_2610 = SeqTypeList.list(animId);
		}
	}

	static {
		aClass16_2616 = RSString.createString("Bitte starten Sie eine Mitgliedschaft");
	}
}
