package com.jagex;
/* Class23_Sub13_Sub17 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.media.Sprite;

public class SpotType extends Queuable {
	public short[] retex_src;
	public int spotid;
	public short[] recolor_dst;
	public boolean aBoolean4054;
	static RSString aClass16_4056;
	public int shadow;
	public int sizeXY = 128;
	public int seqid;
	public int lightness;
	public static RSString aClass16_4061 = RSString.createString("Please enter your username)3");
	public int modelid;
	public static RSString aClass16_4063;
	public static NodeDeque aClass89_4066;
	public int rotation;
	public short[] recolor_src;
	public short[] retex_dst;
	public static int runEnergy;
	public static short[] npcColors;
	public int sizeZ;
	static Js5 gfxWorker;
	static Js5 spots_js5;
	static MemoryCache gfxMap = new MemoryCache(64);

	static final void setGraphicCache(Js5 modelCache, Js5 gfxCache) {
		spots_js5 = modelCache;
		gfxWorker = gfxCache;
	}

	static final SpotType list(int i) {// index 21
		SpotType def = (SpotType) gfxMap.get(i);
		if (def != null) {
			return def;
		}
		byte[] bs = gfxWorker.get_file(getArchiveId(i), getFileId(i));
		def = new SpotType();
		def.spotid = i;
		if (bs != null) {
			def.readValueLoop(new Packet(bs));
		}
		gfxMap.put(i, def);
		return def;
	}

	static final int getArchiveId(int i) {
		return i >>> 8;
	}

	static final int getFileId(int i) {
		return i & 0xff;
	}

	final void readValueLoop(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			parseOpcode(buffer, opcode);
		}
	}

	public Model get_model(int current_frameid, int next_frameid, int num) {
		Model model = (Model) UpdateServerNode.aModelList_2325.get(spotid);
		if (model == null) {
			Mesh def = Mesh.fromJs5(spots_js5, modelid, 0);
			if (def == null) {
				return null;
			}
			if (recolor_src != null) {
				for (int i_0_ = 0; recolor_src.length > i_0_; i_0_++) {
					def.recolor(recolor_src[i_0_], recolor_dst[i_0_]);
				}
			}
			if (retex_src != null) {
				for (int i_1_ = 0; (i_1_ ^ 0xffffffff) > (retex_src.length ^ 0xffffffff); i_1_++) {
					def.retexture(retex_src[i_1_], retex_dst[i_1_]);
				}
			}
			model = def.method2008(64 - -shadow, 850 - -lightness, -30, -50, -30);
			UpdateServerNode.aModelList_2325.put(spotid, model);
		}
		Model class38_sub1_2_;
		if (seqid == -1 || current_frameid == -1) {
			class38_sub1_2_ = model.copy2(true, true, true);
		} else {
			class38_sub1_2_ = SeqTypeList.list(seqid).get_spot_animated(model, current_frameid, next_frameid, num);
		}
		if (sizeXY != 128 || sizeZ != 128) {
			class38_sub1_2_.scale(sizeXY, sizeZ, sizeXY);
		}
		if (rotation != 0) {
			if (rotation == 90) {
				class38_sub1_2_.rotate90_without_normals();
			}
			if (rotation == 180) {
				class38_sub1_2_.rotate180_without_normals();
			}
			if (rotation == 270) {
				class38_sub1_2_.rotate270_without_normals();
			}
		}
		return class38_sub1_2_;
	}

	public static void method790(int i) {
		aClass16_4061 = null;
		CacheConstants.itemCacheIdx = null;
		aClass16_4063 = null;
		if (i != 0) {
			Sprite.create_alpha();
		}
		aClass89_4066 = null;
		npcColors = null;
		aClass16_4056 = null;
	}

	public SpotType() {
		shadow = 0;
		seqid = -1;
		lightness = 0;
		aBoolean4054 = false;
		rotation = 0;
		sizeZ = 128;
	}

	public final void parseOpcode(Packet buffer, int opcode) {
		if (opcode == 1) {
			modelid = buffer.g2();
		} else if (opcode != 2) {
			if (opcode == 4) {
				sizeXY = buffer.g2();
			} else if (opcode != 5) {
				if (opcode == 6) {
					rotation = buffer.g2();
				} else if (opcode == 7) {
					shadow = buffer.g1();
				} else if (opcode != 8) {
					if (opcode != 9) {
						if (opcode == 10) {
						} else if (opcode == 11) {
						} else if (opcode == 12) {
						} else if (opcode == 13) {
						} else if (opcode == 14) {
							buffer.g1();
						} else if (opcode == 15) {
							buffer.g2();
						} else if (opcode == 16) {
							buffer.g4();
						} else if (opcode != 40) {
							if (opcode == 41) {
								int i_10_ = buffer.g1();
								retex_src = new short[i_10_];
								retex_dst = new short[i_10_];
								for (int i_11_ = 0; (i_10_ ^ 0xffffffff) < (i_11_ ^ 0xffffffff); i_11_++) {
									retex_src[i_11_] = (short) buffer.g2();
									retex_dst[i_11_] = (short) buffer.g2();
								}
							} else {
								System.out.println("Unhandled graphic opcode - op=" + opcode + " - parsing dropped!");
								buffer.index = buffer.byteBuffer.length - 1;
							}
						} else {
							int i_12_ = buffer.g1();
							recolor_src = new short[i_12_];
							recolor_dst = new short[i_12_];
							for (int i_13_ = 0; (i_13_ ^ 0xffffffff) > (i_12_ ^ 0xffffffff); i_13_++) {
								recolor_src[i_13_] = (short) buffer.g2();
								recolor_dst[i_13_] = (short) buffer.g2();
							}
						}
					} else {
						aBoolean4054 = true;
					}
				} else {
					lightness = buffer.g1();
				}
			} else {
				sizeZ = buffer.g2();
			}
		} else {
			seqid = buffer.g2();
		}
	}

	static {
		aClass16_4056 = aClass16_4061;
		aClass16_4063 = RSString.createString("Ung-Ultige Session)2ID)3");
		runEnergy = 0;
		npcColors = new short[256];
		aClass89_4066 = new NodeDeque();
		ComponentMinimap.flag_x = 0;
	}
}
