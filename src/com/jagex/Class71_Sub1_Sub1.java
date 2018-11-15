package com.jagex;
/* Class71_Sub1_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;

public class Class71_Sub1_Sub1 extends Class71_Sub1
{
	public static int anInt4457;
	static RSString optionContinue;
	static RSString aClass16_4462;
	public static RSString aClass16_4463;
	public static RSString aClass16_4464;

	static final void method1271(byte b, Packet buffer) {
		if (GameClient.random_file != null) {
			try {
				GameClient.random_file.seek(0L, -11320);
				GameClient.random_file.method955(buffer.index, buffer.byteBuffer, b + 125, 24);
			} catch (Exception exception) {
				/* empty */
			}
		}
		buffer.index += 24;
	}

	static final SoftwarePaletteSprite[] method1273(int i, Js5 class105, boolean bool, int i_6_) {
		if (bool != true) {
			method1275((byte) 121);
		}
		if (!SpriteLoader.cache_sprites(class105, i, i_6_)) {
			return null;
		}
		return StaticMethods.method263(112);
	}

	static final void renderNPCs(int dummy) {
		try {
			StaticMethods.removedEntities = 0;
			SoundEffects.updateMaskIndex = 0;
			EntityUpdating.renderLocalNPCs(7028);
			//			System.out.println("Current index1: " + Overlay.currentBuffer.index + ", " + Overlay.currentBuffer.getBitPosition() + ", mask " + SoundEffects.updateMaskIndex);
			EntityUpdating.renderGlobalNPCs(false);
			//			System.out.println("Current index2: " + Overlay.currentBuffer.index + ", mask " + SoundEffects.updateMaskIndex);
			SpawnedObject.renderNPCUpdateMasks((byte) 41);
			//			System.out.println("Current index3: " + Overlay.currentBuffer.index);
			if (dummy == 7966) {
				for (int i = 0; i < StaticMethods.removedEntities; i++) {
					int index = Class54.removedEntityIndices[i];
					if (GameClient.timer != GameClient.activeNPCs[index].lastUpdate) {
						GameClient.activeNPCs[index].config = null;
						GameClient.activeNPCs[index] = null;
					}
				}
				if (StaticMethods.currentLength != StaticMethods2.packet.index) {
					System.err.println("Packet size mismatch! gnp1 pos:" + StaticMethods2.packet.index + " psize:" + StaticMethods.currentLength);
					StaticMethods2.packet.index = StaticMethods.currentLength;
					// throw new RuntimeException("gnp1 index:" + Overlay.currentBuffer.index + " psize:" +
					// TextureOperation38.currentLength);
				}
				for (int i_9_ = 0; i_9_ < EntityUpdating.localNPCCount; i_9_++) {
					if (GameClient.activeNPCs[EntityUpdating.localNPCIndexes[i_9_]] == null) {
						throw new RuntimeException("gnp2 pos:" + i_9_ + " size:" + EntityUpdating.localNPCCount);
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void method1275(byte b) {
		aClass16_4463 = null;
		aClass16_4462 = null;
		if (b < 74) {
			method1275((byte) 26);
		}
		aClass16_4464 = null;
		optionContinue = null;
	}

	static final void method1276(int configId) {
		GameClient.method36((byte) 102);
		ReflectionRequest.method914(25);
		int i_11_ = VarpDefinition.getConfigDefinition(configId).configID;
		if ((i_11_ ^ 0xffffffff) != -1) {
			int configArrayValue = GameClient.configs[configId];
			if (i_11_ == 1) {
				ClientOptions.clientoption_brightness = configArrayValue;
				if (ClientOptions.clientoption_brightness == 1) {
					GraphicTools.change_brightness(0.9F);
				}
				if (ClientOptions.clientoption_brightness == 2) {
					GraphicTools.change_brightness(0.8F);
				}
				if (ClientOptions.clientoption_brightness == 3) {
					GraphicTools.change_brightness(0.7F);
				}
				if (ClientOptions.clientoption_brightness == 4) {
					GraphicTools.change_brightness(0.6F);
				}
				StaticMethods.method890((byte) 127);
			}
			if (i_11_ == 3) {
				int i_13_ = 0;
				if ((configArrayValue ^ 0xffffffff) == -1) {
					i_13_ = 255;
				}
				if (configArrayValue == 1) {
					i_13_ = 192;
				}
				if (configArrayValue == 2) {
					i_13_ = 128;
				}
				if (configArrayValue == 3) {
					i_13_ = 64;
				}
				if (configArrayValue == 4) {
					i_13_ = 0;
				}
				if (i_13_ != Class21.anInt342) {
					if (Class21.anInt342 == 0 && WallObject.musicId != -1) {
						MusicPlayer.updateCurrentMusic(WallObject.musicId, CacheConstants.musicCacheIdx, 1, 0, i_13_, false);
						Js5.aBoolean1806 = false;
					} else if ((i_13_ ^ 0xffffffff) != -1) {
						Class65.method1229(i_13_, 110);
					} else {
						MusicPlayer.stopMusic(false);
						Js5.aBoolean1806 = false;
					}
					Class21.anInt342 = i_13_;
				}
			}
			if (i_11_ == 6) {
				InstrumentDefinition.anInt275 = configArrayValue;
			}
			if (i_11_ == 4) {
				if (configArrayValue == 0) {
					StaticMethods.soundPreference3 = 127;
				}
				if (configArrayValue == 1) {
					StaticMethods.soundPreference3 = 96;
				}
				if (configArrayValue == 2) {
					StaticMethods.soundPreference3 = 64;
				}
				if (configArrayValue == 3) {
					StaticMethods.soundPreference3 = 32;
				}
				if (configArrayValue == 4) {
					StaticMethods.soundPreference3 = 0;
				}
			}
			if (i_11_ == 5) {
				Class95.anInt1612 = configArrayValue;
			}
			if (i_11_ == 9) {
				Class75.anInt1376 = configArrayValue;
			}
			if (i_11_ == 10) {
				if (configArrayValue == 0) {
					TimeTools.soundPreference1 = 127;
				}
				if (configArrayValue == 1) {
					TimeTools.soundPreference1 = 96;
				}
				if (configArrayValue == 2) {
					TimeTools.soundPreference1 = 64;
				}
				if (configArrayValue == 3) {
					TimeTools.soundPreference1 = 32;
				}
				if (configArrayValue == 4) {
					TimeTools.soundPreference1 = 0;
				}
			}
		}
	}

	static {
		GameClient.gamePercentage = 10;
		anInt4457 = -1;
		aClass16_4463 = RSString.createString("Continue");
		aClass16_4464 = RSString.createString("Examine");
		aClass16_4462 = aClass16_4464;
		optionContinue = aClass16_4463;
	}
}
