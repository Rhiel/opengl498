package com.jagex;
/* Class21 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class21 {
	static RSString aClass16_341 = RSString.createString("(U0a )2 via: ");
	static int anInt342 = 255;

	static final void playAnimationSound(Entity localPlayer, int playerX, int playerY, SeqType sequence, int soundIndex) {
		if (Class71_Sub3.soundStoreCount < 50 && sequence.sounds != null && sequence.sounds.length > soundIndex && sequence.sounds[soundIndex] != null) {
			int i_2_ = sequence.sounds[soundIndex][0];
			int soundID = i_2_ >> 8;
			int soundDuration = 0x7 & i_2_ >> 5;
			if (sequence.sounds[soundIndex].length > 1) {
				int randomizedSoundIndex = (int) (Math.random() * sequence.sounds[soundIndex].length);
				if (randomizedSoundIndex > 0) {
					soundID = sequence.sounds[soundIndex][randomizedSoundIndex];
				}
			}
			int i_5_ = 0x1f & i_2_;
			int i_40_ = 256;
			if (sequence.sound_offsets != null && sequence.sound_offsets2 != null) {
				i_40_ = (int) ((sequence.sound_offsets2[soundIndex] - sequence.sound_offsets[soundIndex]) * Math.random() + sequence.sound_offsets[soundIndex]);
			}
			int i_41_ = sequence.sound_adjusts == null ? 255 : sequence.sound_adjusts[soundIndex];
			if (i_5_ == 0) {
				if (localPlayer == GameClient.currentPlayer) {
					if (!sequence.vorbis) {
						addSoundStore1(-99, soundID, i_40_, i_41_, soundDuration, 0);
					} else {
						addSoundStore2(i_41_, false, i_40_, 0, soundID, 115, soundDuration);
					}
				}
			} else if ((TimeTools.soundPreference1 ^ 0xffffffff) != -1) {
				int i_42_ = localPlayer.bound_extents_x - 256 >> 9;
				int i_43_ = localPlayer.bound_extents_z - 256 >> 9;
				int i_44_ = localPlayer == GameClient.currentPlayer ? 0 : i_5_ + (i_43_ << 8) + (i_42_ << 16) + (ObjType.localHeight << 24);
				PlayerMasks.soundStores[Class71_Sub3.soundStoreCount++] = new SoundStore(sequence.vorbis ? (byte) 2 : (byte) 1, soundID, soundDuration, 0, i_41_, i_44_, i_40_, localPlayer);
			}
		}
	}

	public static final void addSoundStore1(int i, int sound, int offset1, int offset2, int volume, int delay) {
		if (StaticMethods.soundPreference3 != 0 && volume != 0 && Class71_Sub3.soundStoreCount < 50 && (sound ^ 0xffffffff) != 0) {
			PlayerMasks.soundStores[Class71_Sub3.soundStoreCount++] = new SoundStore((byte) 1, sound, volume, delay, offset2, 0, offset1, null);
		}
	}

	public static final void addSoundStore2(int i, boolean bool, int i_25_, int i_26_, int i_27_, int i_28_, int i_29_) {
		if ((!bool ? StaticMethods.soundPreference3 : TimeTools.soundPreference2) != 0 && i_29_ != 0 && Class71_Sub3.soundStoreCount < 50 && i_27_ != -1) {
			PlayerMasks.soundStores[Class71_Sub3.soundStoreCount++] = new SoundStore(bool ? (byte) 3 : (byte) 2, i_27_, i_29_, i_26_, i, 0, i_25_, null);
		}
	}


	public static void destruct(int i) {
		aClass16_341 = null;
	}
}
