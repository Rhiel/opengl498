package com.jagex;
/* InstrumentDefinition - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.awt.event.KeyEvent;

public class InstrumentDefinition
{
	public Js5 soundsContainer;
	public Js5 instrumentsContainer;
	static RSString aClass16_274 = RSString.createString(" )2> <col=ff9040>");
	public static int anInt275;
	static RSString aClass16_276 = RSString.createString("<br>");
	public HashTable anOa281 = new HashTable(256);
	public HashTable anOa285 = new HashTable(256);

	final SomeSoundClass2 method125(int i_0_, int[] is) {
		if (soundsContainer.get_group_count() == 1) {
			return method133(is, i_0_, 0);
		}
		if (soundsContainer.get_file_count(i_0_) == 1) {
			return method133(is, 0, i_0_);
		}
		throw new RuntimeException("here83");
	}
	
	public static void method126(int i) {
		if (i > 0) {
			aClass16_276 = null;
			aClass16_274 = null;
		}
	}

	public final SomeSoundClass2 method127(int[] is, byte b, int i, int i_1_) {
		if (b <= 90) {
			return null;
		}
		int i_2_ = (i << 4 & 0xfffd | i >>> 12) ^ i_1_;
		i_2_ |= i << 16;
		long l = 0x100000000L ^ i_2_;
		SomeSoundClass2 someSoundClass2 = (SomeSoundClass2) anOa285.get(l);
		if (someSoundClass2 != null) {
			return someSoundClass2;
		}
		if (is != null && (is[0] ^ 0xffffffff) >= -1) {
			return null;
		}
		Class23_Sub3 class23_sub3 = (Class23_Sub3) anOa281.get(l);
		if (class23_sub3 == null) {
			class23_sub3 = Class23_Sub3.method246(instrumentsContainer, i, i_1_);
			if (class23_sub3 == null) {
				return null;
			}
			anOa281.put(l, class23_sub3);
		}
		someSoundClass2 = class23_sub3.method247(is);
		if (someSoundClass2 == null) {
			return null;
		}
		class23_sub3.unlink();
		anOa285.put(l, someSoundClass2);
		return someSoundClass2;
	}

	static final void method130(Js5 class105, Js5 class105_18_, boolean bool) {
		if (bool == false) {
			StaticMethods.modelCacheIndex = class105;
			NPCType.npcContainer = class105_18_;
		}
	}
	
	final SomeSoundClass2 method131(int[] is, boolean bool, int i) {
		if (bool != false) {
			aClass16_274 = null;
		}
		if (instrumentsContainer.get_group_count() == 1) {
			return method127(is, (byte) 100, 0, i);
		}
		int size = 0;
		if ((size = instrumentsContainer.get_file_count(i)) == 1) {
			return method127(is, (byte) 112, i, 0);
		}
		throw new RuntimeException("Music failed to readValueLoop! " + i + ", " + size);
	}

	public final SomeSoundClass2 method133(int[] is, int i, int i_41_) {
		int i_42_ = (i_41_ >>> 12 | (i_41_ & 0x70000fff) << 4) ^ i;
		i_42_ |= i_41_ << 16;
		long l = i_42_;
		SomeSoundClass2 someSoundClass2 = (SomeSoundClass2) anOa285.get(l);
		if (someSoundClass2 != null) {
			return someSoundClass2;
		}
		if (is != null && is[0] <= 0) {
			return null;
		}
		Sound sound = Sound.method195(soundsContainer, i_41_, i);
		if (sound == null) {
			return null;
		}
		someSoundClass2 = sound.method196();
		anOa285.put(l, someSoundClass2);
		if (is != null) {
			is[0] -= someSoundClass2.aByteArray3544.length;
		}
		return someSoundClass2;
	}
	
	static final int method134(KeyEvent keyevent, int i) {
		int keyCharacter = keyevent.getKeyChar();
		if (keyCharacter == 8364) {
			return 128;
		}
		if ((keyCharacter ^ 0xffffffff) >= -1 || keyCharacter >= 256) {
			keyCharacter = -1;
		}
		return keyCharacter;
	}
	
	InstrumentDefinition(Js5 soundsContainer, Js5 instrumentsCache) {
		this.soundsContainer = soundsContainer;
		instrumentsContainer = instrumentsCache;
	}
	
	static {
		anInt275 = 0;
	}
}
