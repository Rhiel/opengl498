package com.jagex;
/* Class23_Sub13_Sub6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class VarpDefinition extends Queuable
{
	public static MemoryCache varpMap = new MemoryCache(64);
	static Js5 varpContainer;
	public static int varpSize;
	public int configID = 0;
	public static int anInt3728 = 0;
	public static int anInt3734;
	public static int anInt3735;

	static final VarpDefinition getConfigDefinition(int configId) {
		VarpDefinition def = (VarpDefinition) varpMap.get(configId);
		if (def != null) {
			return def;
		}
		byte[] bs = varpContainer.get_file(16, configId);
		def = new VarpDefinition();
		if (bs != null) {
			def.parse(new Packet(bs));
		}
		varpMap.put(configId, def);
		return def;
	}

	static final void initializeVarpWorker(Js5 varpWorker) {
		varpContainer = varpWorker;
		varpSize = varpContainer.get_file_count(16);
	}

	public static final void clearVarpList(byte b) {
		varpMap.clear();
	}

	final void parse(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			parseOpcode(opcode, buffer);
		}
	}
	
	
	public static final boolean method631(int i, int i_0_, int i_1_, int i_2_, SceneNode abstractModel, int i_3_, long l, int i_4_, int i_5_, int i_6_, int i_7_) {
		if (abstractModel == null) {
			return true;
		}
		return Scene.addInteractiveObject(i, i_4_, i_5_, i_6_ - i_4_ + 1, i_7_ - i_5_ + 1, i_0_, i_1_, i_2_, abstractModel, i_3_, true, l);
	}
	
	public static final void method632(int i, byte b, int i_8_, int[] is, int i_9_) {
		i_9_--;
		if (b == -30) {
			int i_10_ = -7 + --i;
			while (i_10_ > i_9_) {
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
				is[++i_9_] = i_8_;
			}
			while ((i ^ 0xffffffff) < (i_9_ ^ 0xffffffff)) {
				is[++i_9_] = i_8_;
			}
		}
	}
	
	public final void parseOpcode(int i, Packet buffer) {
		if (i == 5) {
			configID = buffer.g2();
		}
	}
	
	static {
		Varbit.varbitMap = new MemoryCache(64);
		anInt3734 = 0;
	}
}
