package com.jagex;

public class Class23_Sub13_Sub2 extends Queuable
{
	public byte[] aByteArray3683;
	
	static final int method606(int i, int i_15_, int i_16_) {
		int i_17_ = i_15_ >>> 31;
		return -i_17_ + (i_15_ - -i_17_) / i;
	}
	
	public static void method607(boolean bool) {
		if (bool == true) {
			SpriteLoader.sprites_offsety = null;
		}
	}

	static final void method609(int i_26_) {
		Linkable integerNode = Class47.anOa722.get_first();
		for (/**/; integerNode != null; integerNode = Class47.anOa722.get_next()) {
			if ((0xffffL & integerNode.uid >> 48) == (i_26_)) {
				integerNode.unlink();
			}
		}
	}
	
	static final void playMusic(int id, byte b) {
		if (b > 101) {
			if (id == -1 && !Js5.aBoolean1806) {
				MusicPlayer.stopMusic(false);
			} else if (id != -1 
					&& (WallObject.musicId != id || !StaticMethods.method343((byte) -37))
					&& (Class21.anInt342 ^ 0xffffffff) != -1 && !Js5.aBoolean1806) {
				MusicPlayer.playMusic(id, 13910, 0, false, 2, CacheConstants.musicCacheIdx, Class21.anInt342);
			}
			WallObject.musicId = id;
		}
	}
	
	public Class23_Sub13_Sub2(byte[] bs) {
		aByteArray3683 = bs;
	}
}
