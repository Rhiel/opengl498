package com.jagex;
/* SongUpdater - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class SongUpdater
{
	public static RSString aClass16_174;
	public static int anInt175 = 0;
	static RSString aClass16_176;
	public static int anInt177;
	static NodeDeque aClass89_178;
	public static int anInt180;
	
	public static void method97(int i) {
		aClass16_176 = null;
		if (i > 38) {
			aClass89_178 = null;
			aClass16_174 = null;
		}
	}
	
	static final void method98(int i) {//TODO: Fix this method. Culprit for animation sound issue
		for (int i_63_ = 0; i_63_ < Class71_Sub3.soundStoreCount; i_63_++) {
			SoundStore var_soundStore = PlayerMasks.soundStores[i_63_];
			boolean bool = false;
			if (var_soundStore.b == null) {
				var_soundStore.soundDelay--;
				if ((var_soundStore.soundDelay ^ 0xffffffff)
						> ((!var_soundStore.c(372) ? -10 : -1500) ^ 0xffffffff)) {
					bool = true;
				} else {
					if (var_soundStore.k != 1 || var_soundStore.f_l != null) {
						if (var_soundStore.c(372) && (var_soundStore.r == null
								|| var_soundStore.t == null)) {
							if (var_soundStore.r == null) {
								var_soundStore.r = Class23_Sub3.a(CacheConstants.instrumentCacheIdx, var_soundStore.soundId);
							}
							if (var_soundStore.r == null) {
								continue;
							}
							if (var_soundStore.t == null) {
								var_soundStore.t = var_soundStore.r.method247(new int[]{22050});
								if (var_soundStore.t == null) {
									continue;
								}
							}
						}
					} else {
						var_soundStore.f_l = Sound.method195(CacheConstants.soundsCacheIdx, var_soundStore.soundId, 0);
						if (var_soundStore.f_l == null) {
							continue;
						}
						var_soundStore.soundDelay += var_soundStore.f_l.method194();
					}
					if (var_soundStore.soundDelay < 0) {
						int i_2_ = 8192;
						int i_65_;
						if (var_soundStore.anIntArray1497 != 0) {
							int i_4_ = (var_soundStore.anIntArray1497 & 0x36789ff) >> 24;
							if (ObjType.localHeight == i_4_) {
								int i_5_ = var_soundStore.anIntArray1497 << -1936221623 & 0x1fe00;
								int i_6_ = GameClient.currentPlayer.size << 8;
								int i_7_ = (0xff09cd & var_soundStore.anIntArray1497) >> 16;
								int i_8_ = i_6_ + (i_7_ << 9)
										+ 256 - GameClient.currentPlayer.bound_extents_x;
								int i_9_ = var_soundStore.anIntArray1497 >> 8 & 0xff;
								int i_10_ = i_6_ + -GameClient.currentPlayer.bound_extents_z
										+ 256 + (i_9_ << 9);
								int i_11_
										= Math.abs(i_8_) + Math.abs(i_10_) - 512;
								if (i_5_ < i_11_) {
									var_soundStore.soundDelay = -99999;
									continue;
								}
								if (i_11_ < 0) {
									i_11_ = 0;
								}
								i_65_ = (-i_11_ + i_5_)
										* TimeTools.soundPreference1
										* var_soundStore.soundOffset2 / i_5_ >> 2;
								if (i_8_ != 0 || i_10_ != 0) {
									int i_14_
											= 0
									+ -(int) (2607.5945876176133
									* Math.atan2
									(i_8_,
											i_10_))
									- 4096
									& 0x3fff;
									if (i_14_ > 8192) {
										i_14_ = -i_14_ + 16384;
									}
									int i_15_;
									if (i_11_ > 0) {
										if (i_11_ < 4096) {
											i_15_ = 8192
													+ (8192 + -i_11_) / 4096;
										} else {
											i_15_ = 16384;
										}
									} else {
										i_15_ = 8192;
									}
									i_2_
											= i_15_ * i_14_ / 8192 + (16384 - i_15_
											>> 1);
								}
							} else {
								i_65_ = 0;
							}
						} else {
							i_65_ = var_soundStore.soundOffset2 * (var_soundStore.k == 3
									? TimeTools.soundPreference2
									: StaticMethods.soundPreference3)
									>> 2;
						}
						if (i_65_ > 0) {
							SomeSoundClass2 var_someSoundClass2 = null;
							if (var_soundStore.k == 1) {
								var_someSoundClass2 = var_soundStore.f_l.method196().method493(WallObject.aClass45_1462);
							} else if (var_soundStore.c(372)) {
								var_someSoundClass2 = var_soundStore.t;
							}
							Class23_Sub10_Sub1 var_class23Sub10Sub1 = var_soundStore.b = Class23_Sub10_Sub1.method529(var_someSoundClass2, var_soundStore.soundOffset1, i_65_, i_2_);
							var_class23Sub10Sub1.method536(var_soundStore.soundVolume - 1);
							Class23_Sub7.aClass23_Sub10_Sub4_2201.method590(var_class23Sub10Sub1);
						}
					}
				}
			} else if (!var_soundStore.b.method227(1)) {
				bool = true;
			}
			if (bool) {
				Class71_Sub3.soundStoreCount--;
				for (int i_16_ = i_63_; Class71_Sub3.soundStoreCount > i_16_; i_16_++) {
					PlayerMasks.soundStores[i_16_] = PlayerMasks.soundStores[i_16_ + 1];
				}
				i_63_--;
			}
		}
		/*for (int i_63_ = 0; i_63_ < Class71_Sub3.soundStoreCount; i_63_++) {
			SoundStore var_soundStore = PlayerMasks.soundStores[i_63_];
			boolean bool = false;
			if (ClientInventory.soundDelays[i_63_] < -10) {
				Class71_Sub3.soundStoreCount--;
				for (int i_64_ = i_63_; Class71_Sub3.soundStoreCount > i_64_; i_64_++) {
					PlayerMasks.sounds[i_64_] = PlayerMasks.sounds[i_64_ - -1];
					Class91.aSoundArray1554[i_64_] = Class91.aSoundArray1554[i_64_ - -1];
					VarpDefinition.soundVolumes[i_64_] = VarpDefinition.soundVolumes[1 + i_64_];
					ClientInventory.soundDelays[i_64_] = ClientInventory.soundDelays[1 + i_64_];
					Class88.anIntArray1497[i_64_] = Class88.anIntArray1497[i_64_ - -1];
				}
				i_63_--;
			} else {
				Sound sound = Class91.aSoundArray1554[i_63_];
				if (sound == null) {
					sound = Sound.method195(CacheConstants.soundsCacheIdx, PlayerMasks.sounds[i_63_], 0);
					if (sound == null) {
						continue;
					}
					ClientInventory.soundDelays[i_63_] += sound.method194();
					Class91.aSoundArray1554[i_63_] = sound;
				}
				if (ClientInventory.soundDelays[i_63_] < 0) {
					int i_2_ = 8192;
					int i_65_;
					if (Class88.anIntArray1497[i_63_] == 0) {
						i_65_ = TextureOperation38.soundPreference3;
					} else {
						int i_4_ = (Class88.anIntArray1497[i_63_] & 0x36789ff) >> 24;
						if (true) {
							int i_66_ = (0xff & Class88.anIntArray1497[i_63_]) * 128;
							int i_67_ = 0xff & Class88.anIntArray1497[i_63_] >> 16;
							int i_68_ = 64 + i_67_ * 128 + -Game.currentPlayer.bound_extents_x;
							if (i_68_ < 0) {
								i_68_ = -i_68_;
							}
							int i_69_ = 0xff & Class88.anIntArray1497[i_63_] >> 8;
							int i_70_ = 128 * i_69_ + (64 - Game.currentPlayer.bound_extents_z);
							if ((i_70_ ^ 0xffffffff) > -1) {
								i_70_ = -i_70_;
							}
							int i_71_ = -128 + (i_70_ + i_68_);
							if ((i_71_ ^ 0xffffffff) < (i_66_ ^ 0xffffffff)) {
								ClientInventory.soundDelays[i_63_] = -100;
								continue;
							}
							if ((i_71_ ^ 0xffffffff) > -1) {
								i_71_ = 0;
							}
							i_65_ = ((-i_71_ + i_66_)
									* TimeTools.soundPreference1
									* PlayerMasks.soundOffsets2[i_63_] / i_66_);
						} else {
							i_65_ = 0;
						}
					}
					if ((i_65_ ^ 0xffffffff) < -1) {
						SomeSoundClass2 someSoundClass2 = sound.method196().method493(WallObject.aClass45_1462);
						Class23_Sub10_Sub1 class23_sub10_sub1 = Class23_Sub10_Sub1.method510(someSoundClass2, PlayerMasks.soundOffsets1[i_63_], i_65_);
						class23_sub10_sub1.method536(-1 + VarpDefinition.soundVolumes[i_63_]);
						Class23_Sub7.aClass23_Sub10_Sub4_2201.method590(class23_sub10_sub1);
					}
					ClientInventory.soundDelays[i_63_] = -100;
				}
			}
		}*/
		if (i >= 104) {
			if (Js5.aBoolean1806 && !StaticMethods.method343((byte) -37)) {
				if (Class21.anInt342 != 0 && WallObject.musicId != -1) {
					MusicPlayer.updateCurrentMusic(WallObject.musicId, CacheConstants.musicCacheIdx, 1, 0, Class21.anInt342, false);
				}
				Js5.aBoolean1806 = false;
			} else if ((Class21.anInt342 ^ 0xffffffff) != -1 && WallObject.musicId != -1 && !StaticMethods.method343((byte) -37)) {
				GameClient.outBuffer.putOpcode(144);
				GameClient.outBuffer.p4(WallObject.musicId);
				WallObject.musicId = -1;
			}
		}
	}
	
	static {
		aClass16_174 = RSString.createString("Prepared sound engine");
		anInt177 = 0;
		aClass16_176 = aClass16_174;
		anInt180 = 0;
		aClass89_178 = new NodeDeque();
	}
}
