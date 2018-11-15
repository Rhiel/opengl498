package com.jagex;
/* SpawnedObject - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class SpawnedObject extends Linkable {
	public int ID;
	public int anInt2419;
	public int z;
	public int anInt2428;
	public int anInt2429 = 0;
	public int x;
	public int anInt2431;
	static RSString[] playerOptions = new RSString[8];
	public int y;
	public int anInt2434;
	static int anInt2435 = 0;
	public int anInt2436;
	public int anInt2437 = -1;
	public int type;

	public static void method895(int i) {
		SpriteLoader.sprites_offsetx = null;
		playerOptions = null;
		if (i < 100) {
			method895(72);
		}
	}

	static final void renderNPCUpdateMasks(byte b) {
		if (b == 41) {
			for (int i = 0; i < SoundEffects.updateMaskIndex; i++) {
				int i_259_ = TimeTools.maskUpdates[i];
				NPC npc = GameClient.activeNPCs[i_259_];
				int mask = StaticMethods2.packet.g1();
				if ((0x1 & mask) != 0) { // Ordinal 0
					// Main hit
					int i_261_ = StaticMethods2.packet.getByteA();
					int i_262_ = StaticMethods2.packet.getByteC(-116);
					npc.method1086(b + -31, i_261_, i_262_, GameClient.timer);
					npc.anInt2638 = 300 + GameClient.timer;
					npc.anInt2708 = StaticMethods2.packet.getByteC(-100);
					// System.out.println("Main hit update " + class38_sub7_sub1.anInt2708);
				}
				if ((0x10 & mask) != 0) { // Ordinal 1
					// Force chat
					npc.aClass16_2670 = StaticMethods2.packet.gstr();
					npc.anInt2639 = 100;
				}
				if ((mask & 0x20) != 0) { // Ordinal 2
					// Face coordinates
					npc.facingOffsetX = StaticMethods2.packet.getShortA();
					npc.facingOffsetY = StaticMethods2.packet.getLEShortA0(124);
				}
				if ((mask & 0x4) != 0) { // Ordinal 3
					// switch npc id
					npc.config = NPCType.getNPCDefinition(StaticMethods2.packet.getLEShortA0(b + 83));
					npc.turn180 = npc.config.turn180Animation;
					npc.index = npc.config.degreesToTurn;
					npc.walkAnimation = npc.config.walkAnimationId;
					npc.turn90ccw = npc.config.turnCounterCW;
					npc.turn90cw = npc.config.turnCW;
					npc.alsoTurn = npc.config.alsoTurn;
					npc.standAnimation = npc.config.standAnimation;
					npc.size = npc.config.size;
					npc.turnAnimation = npc.config.turnAnimation;
				}
				if ((0x40 & mask ^ 0xffffffff) != -1) { // Ordinal 4
					// Face entity
					npc.faceIndex = StaticMethods2.packet.getLEShortA0(b + 83);
					if ((npc.faceIndex ^ 0xffffffff) == -65536) {
						npc.faceIndex = -1;
					}
				}
				if ((0x80 & mask) != 0) { // Ordinal 5
					// Graphics
					npc.current_spotanimid = StaticMethods2.packet.getShortA();
					int i_263_ = StaticMethods2.packet.getLEInt();
					npc.anInt2671 = (0xffff & i_263_) + GameClient.timer;
					npc.current_spotanim_tick = 0;
					if ((npc.current_spotanimid ^ 0xffffffff) == -65536) {
						npc.current_spotanimid = -1;
					}
					npc.anInt2647 = i_263_ >> 16;
					npc.current_spotanim_frameid = 0;
					if (npc.anInt2671 > GameClient.timer) {
						npc.current_spotanim_frameid = -1;
					}
					npc.next_spotanim_frameid = 1;
				}
				if ((0x8 & mask) != 0) { // Ordinal 6
					// Animation
					int i_264_ = StaticMethods2.packet.getLEShortA0(b + 84);
					if ((i_264_ ^ 0xffffffff) == -65536) {
						i_264_ = -1;
					}
					int i_265_ = StaticMethods2.packet.g1();
					Class57.method1191(i_264_, npc, i_265_, b ^ ~0x29);
				}
				if ((mask & 0x2 ^ 0xffffffff) != -1) { // Ordinal 7
					// Supportive hit
					int i_266_ = StaticMethods2.packet.getByteS(b ^ 0x54);
					int i_267_ = StaticMethods2.packet.getByteA();
					npc.method1086(10, i_266_, i_267_, GameClient.timer);
				}
				if ((mask & 0x100) != 0) {
					// TODO: Worn items mask for NPCs
					int num_anims = StaticMethods2.packet.g1();
					int[] var12 = new int[num_anims];
					int[] var13 = new int[num_anims];
					int[] var14 = new int[num_anims];
					for (int var15 = 0; ~var15 > ~num_anims; ++var15) {
						int var10 = StaticMethods2.packet.g2();
						if (var10 == 0xffff) {
							var10 = -1;
						}
						var12[var15] = var10;
						var13[var15] = StaticMethods2.packet.g1();
						var14[var15] = StaticMethods2.packet.g2();
					}
					NPC.add_worn_obj_anim(npc, var14, var12, var13);
				}
			}
		}
	}

	static final void convertSpawnedObject(int i, SpawnedObject spawnedObject) {
		long uid = 0L;
		int i_0_ = 0;
		if (spawnedObject.type == 0) {
			uid = WallObject.getWallObjectUid(spawnedObject.z, spawnedObject.x, spawnedObject.y);
		}
		if (spawnedObject.type == 1) {
			uid = WallDecoration.getWallDecorationUid(spawnedObject.z, spawnedObject.x, spawnedObject.y);
		}
		if (spawnedObject.type == 2) {
			uid = Scene.getInteractiveUid(spawnedObject.z, spawnedObject.x, spawnedObject.y);
		}
		if (spawnedObject.type == 3) {
			uid = GroundDecoration.getGroundDecorationUid(spawnedObject.z, spawnedObject.x, spawnedObject.y);
		}
		if (i == -21619) {
			int objectId = -1;
			int i_2_ = 0;
			if (uid != 0L) {
				objectId = 0x7fffffff & (int) (uid >>> 32);
				i_0_ = 0x1f & (int) uid >> 14;
				i_2_ = 0x3 & (int) uid >> 20;
			}
			spawnedObject.anInt2431 = i_2_;
			spawnedObject.anInt2434 = i_0_;
			spawnedObject.ID = objectId;
		}
	}

	static final void process_spawned_objects() {
		for (SpawnedObject spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_first(); spawnedObject != null; spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_next()) {
			if (spawnedObject.anInt2437 == -1) {
				spawnedObject.anInt2429 = 0;
				SpawnedObject.convertSpawnedObject(-21619, spawnedObject);
			} else {
				spawnedObject.unlink();
			}
		}
	}

	static final void method1375(int i) {
		SpawnedObject spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_first();
		for (/**/; spawnedObject != null; spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_next()) {
			if ((spawnedObject.anInt2437 ^ 0xffffffff) < -1) {
				spawnedObject.anInt2437--;
			}
			if (spawnedObject.anInt2437 == 0) {
				if (spawnedObject.ID < 0 || GameShell.method26(spawnedObject.ID, spawnedObject.anInt2434, 101)) {
					StaticMethods.method892(spawnedObject.x, spawnedObject.ID, spawnedObject.y, spawnedObject.anInt2434, i ^ 0x784d, spawnedObject.type, spawnedObject.z, spawnedObject.anInt2431);
					spawnedObject.unlink();
				}
			} else {
				if ((spawnedObject.anInt2429 ^ 0xffffffff) < -1) {
					spawnedObject.anInt2429--;
				}
				if (spawnedObject.anInt2429 == 0 && spawnedObject.x >= 1 && spawnedObject.y >= 1 && spawnedObject.x <= 102 && spawnedObject.y <= 102 && ((spawnedObject.anInt2419 ^ 0xffffffff) > -1 || GameShell.method26(spawnedObject.anInt2419, spawnedObject.anInt2428, 73))) {
					StaticMethods.method892(spawnedObject.x, spawnedObject.anInt2419, spawnedObject.y, spawnedObject.anInt2428, 0, spawnedObject.type, spawnedObject.z, spawnedObject.anInt2436);
					spawnedObject.anInt2429 = -1;
					if (spawnedObject.anInt2419 == spawnedObject.ID && spawnedObject.ID == -1) {
						spawnedObject.unlink();
					} else if ((spawnedObject.anInt2419 ^ 0xffffffff) == (spawnedObject.ID ^ 0xffffffff) && spawnedObject.anInt2431 == spawnedObject.anInt2436 && (spawnedObject.anInt2428 ^ 0xffffffff) == (spawnedObject.anInt2434 ^ 0xffffffff)) {
						spawnedObject.unlink();
					}
				}
			}
		}
	}

	static final void method1373(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_) {
		if (i_5_ == -7593) {
			SpawnedObject spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_first();
			SpawnedObject spawnedObject_9_ = null;
			for (/**/; spawnedObject != null; spawnedObject = (SpawnedObject) SongUpdater.aClass89_178.get_next()) {
				if ((i_2_ ^ 0xffffffff) == (spawnedObject.z ^ 0xffffffff) && (i_8_ ^ 0xffffffff) == (spawnedObject.x ^ 0xffffffff) && (spawnedObject.y ^ 0xffffffff) == (i ^ 0xffffffff) && (i_3_ ^ 0xffffffff) == (spawnedObject.type ^ 0xffffffff)) {
					spawnedObject_9_ = spawnedObject;
					break;
				}
			}
			if (spawnedObject_9_ == null) {
				spawnedObject_9_ = new SpawnedObject();
				spawnedObject_9_.z = i_2_;
				spawnedObject_9_.y = i;
				spawnedObject_9_.type = i_3_;
				spawnedObject_9_.x = i_8_;
				SpawnedObject.convertSpawnedObject(i_5_ ^ 0x49da, spawnedObject_9_);
				SongUpdater.aClass89_178.add_last(spawnedObject_9_);
			}
			spawnedObject_9_.anInt2436 = i_6_;
			spawnedObject_9_.anInt2428 = i_7_;
			spawnedObject_9_.anInt2419 = i_0_;
			spawnedObject_9_.anInt2437 = i_4_;
			spawnedObject_9_.anInt2429 = i_1_;
		}
	}

	SpawnedObject() {
		/* empty */
	}
}
