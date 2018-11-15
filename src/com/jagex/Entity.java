package com.jagex;
/* Class38_Sub7 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.rs2.client.scene.environment.SceneEnvironment;

public abstract class Entity extends SceneNode {
	static RSString passLabel1 = RSString.createString("Passwort: ");
	public int index = 32;
	public int current_standing_tick;
	public int anInt2632;
	public int forceEndX;
	public int standAnimation;
	public int anInt2638;
	public int anInt2639 = 100;
	public int anInt2640;
	public int walkAnimation;
	public int seq_replays_done;
	public int anInt2644;
	public boolean aBoolean2810;
	public static RSString aClass16_2646;
	public int anInt2647;
	public int facingOffsetX;
	public static int anInt2649 = 0;
	public int forceStartX;
	static RSString aClass16_2651;
	public int[] anIntArray2652;
	public int turnAnimation;
	static RSString[] aClass16Array2654;
	public int turn180;
	public int render_y;
	public int bound_extents_z;
	public int anInt2660;
	public static RSString aClass16_2662;
	public int[] waypointsX;
	public static RSString aClass16_2665 = RSString.createString("Mar");
	public static RSString aClass16_2666;
	public static RSString aClass16_2667 = RSString.createString("Feb");
	public int size;
	public int[] anIntArray2669;
	public RSString aClass16_2670;
	public int anInt2671;
	public static RSString aClass16_2672;
	public int runAnimation;
	public int bound_extents_x;
	public static RSString aClass16_2675;
	public static RSString aClass16_2677;
	public int turn90ccw;
	static RSString aClass16_2679;
	public int anInt2680;
	public int forceEndY;
	public int faceDirection;
	public static RSString aClass16_2683;
	public int forceCommenceSpeed;
	public int anInt2820;
	public int faceIndex;
	public int[] anIntArray2687;
	public int forceStartY;
	public int alsoTurn;
	public int facingOffsetY;
	public int[] waypointsY;
	public int lastUpdate;
	public static RSString aClass16_2699;
	public int turn90cw;
	public int anInt2701;
	public static RSString aClass16_2702;
	static RSString aClass16_2703;
	static RSString aClass16_2705;
	public int anInt2828;
	public int anInt2708;
	public static RSString aClass16_2711;
	public static RSString aClass16_2712;
	public int anInt2713;
	public static RSString aClass16_2715;
	public int forcePathSpeed;
	public int forceDirection;
	public boolean[] aBooleanArray2718;
	// anim stuff
	public int current_spotanimid = -1;
	public int current_spotanim_tick = 0;
	public int current_spotanim_frameid = 0;
	public int next_spotanim_frameid = -1;
	public int current_standing_animation = 0;
	public int current_standing_frameid = 0;
	public int next_standing_frameid = -1;
	public int current_performing_seqid = -1;
	public int current_performing_tick = 0;
	public int current_performing_frameid = 0;
	public int next_performing_frameid = -1;
	public WornObjAnim[] worn_objs_animations = new WornObjAnim[12];

	final int method1081(byte b) {
		if (anInt2820 == -32768) {
			return 200;
		}
		return -anInt2820;
	}

	boolean is_ready() {
		return false;
	}

	final void method1083(int i) {
		anInt2660 = 0;
		anInt2640 = 0;
		if (i >= -61) {
			method1081((byte) -43);
		}
	}

	final void method1084(int direction, boolean runnning, int dummy) {
		int x = waypointsX[0];
		int y = waypointsY[0];
		if ((current_performing_seqid ^ 0xffffffff) != 0 && SeqTypeList.list(current_performing_seqid).move_type == 1) {
			current_performing_seqid = -1;
		}
		if (anInt2660 < 9) {
			anInt2660++;
		}
		if (direction == 0) {
			x--;
			y++;
		}
		if (direction == 1) {
			y++;
		}
		if (direction == 2) {
			x++;
			y++;
		}
		for (int index = anInt2660; index > 0; index--) {
			waypointsX[index] = waypointsX[index - 1];
			waypointsY[index] = waypointsY[index - 1];
			aBooleanArray2718[index] = aBooleanArray2718[index - 1];
		}
		if (direction == 3) {
			x--;
		}
		if (direction == 4) {
			x++;
		}
		if (direction == 5) {
			y--;
			x--;
		}
		if (direction == 6) {
			y--;
		}
		if (direction == 7) {
			x++;
			y--;
		}
		if (dummy != -30438) {
			is_ready();
		}
		waypointsX[0] = x;
		waypointsY[0] = y;
		aBooleanArray2718[0] = runnning;
	}

	final void updatePosition(int localX, byte b, boolean teleporting, int localY) {
		if ((current_performing_seqid ^ 0xffffffff) != 0 && SeqTypeList.list(current_performing_seqid).move_type == 1) {
			current_performing_seqid = -1;
		}
		if (!teleporting) {
			int offsetY = localY - waypointsY[0];
			int offsetX = localX - waypointsX[0];
			if (offsetX >= -8 && offsetX <= 8 && offsetY >= -8 && offsetY <= 8) {
				if (anInt2660 < 9) {
					anInt2660++;
				}
				for (int index = anInt2660; index > 0; index--) {
					waypointsX[index] = waypointsX[index - 1];
					waypointsY[index] = waypointsY[index - 1];
					aBooleanArray2718[index] = aBooleanArray2718[index - 1];
				}
				waypointsX[0] = localX;
				waypointsY[0] = localY;
				aBooleanArray2718[0] = false;
				return;
			}
		}
		anInt2632 = 0;
		anInt2640 = 0;
		waypointsX[0] = localX;
		anInt2660 = 0;
		waypointsY[0] = localY;
		bound_extents_x = 64 * size + 128 * waypointsX[0];
		bound_extents_z = 64 * size + 128 * waypointsY[0];
		if (GLManager.opengl_mode && GameClient.currentPlayer == this) {
			SceneEnvironment.update_brightness();
		}
	}

	final void method1086(int i, int i_9_, int i_10_, int i_11_) {
		for (int i_12_ = 0; i_12_ < 4; i_12_++) {
			if (i_11_ >= anIntArray2687[i_12_]) {
				anIntArray2669[i_12_] = i_9_;
				anIntArray2652[i_12_] = i_10_;
				anIntArray2687[i_12_] = i_11_ - -70;
				break;
			}
		}
	}

	static final boolean method1087(boolean bool) {
		if (bool != false) {
			aClass16_2675 = null;
		}
		try {
			if (GroundObjEntity.anInt708 == 2) {
				if (StaticMethods.aClass23_Sub18_2952 == null) {
					StaticMethods.aClass23_Sub18_2952 = MusicDefinition.getMusicDefinition(MusicPlayer.musicContainer, MusicPlayer.musicId, CullingCluster.anInt931);
					if (StaticMethods.aClass23_Sub18_2952 == null) {
						return false;
					}
				}
				if (StaticMethods.aInstrumentDefinition_2911 == null) {
					StaticMethods.aInstrumentDefinition_2911 = new InstrumentDefinition(Class44.soundsContainer, GameClient.instrumentsContainer);
				}
				if (ModelList.aSomeSoundClass_1437.method582(22050, StaticMethods.aInstrumentDefinition_2911, (byte) 24, StaticMethods.soundEffectsContainer, StaticMethods.aClass23_Sub18_2952)) {
					ModelList.aSomeSoundClass_1437.method564(-2);
					ModelList.aSomeSoundClass_1437.method579(2, CS2Event.anInt2257);
					ModelList.aSomeSoundClass_1437.method572(StaticMethods.aClass23_Sub18_2952, -108, StaticMethods.aBoolean3018);
					MusicPlayer.musicContainer = null;
					StaticMethods.aInstrumentDefinition_2911 = null;
					StaticMethods.aClass23_Sub18_2952 = null;
					GroundObjEntity.anInt708 = 0;
					return true;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ModelList.aSomeSoundClass_1437.method551((byte) 24);
			StaticMethods.aClass23_Sub18_2952 = null;
			MusicPlayer.musicContainer = null;
			StaticMethods.aInstrumentDefinition_2911 = null;
			GroundObjEntity.anInt708 = 0;
		}
		return false;
	}

	public static void method1089(int i) {
		aClass16_2651 = null;
		aClass16_2672 = null;
		aClass16Array2654 = null;
		aClass16_2712 = null;
		aClass16_2665 = null;
		aClass16_2703 = null;
		aClass16_2683 = null;
		aClass16_2662 = null;
		FontCache.p12_full = null;
		aClass16_2667 = null;
		aClass16_2666 = null;
		passLabel1 = null;
		aClass16_2715 = null;
		aClass16_2679 = null;
		aClass16_2702 = null;
		aClass16_2711 = null;
		aClass16_2705 = null;
		aClass16_2699 = null;
		aClass16_2675 = null;
		aClass16_2677 = null;
		aClass16_2646 = null;
	}


	Entity() {
		current_standing_tick = 0;
		turn180 = -1;
		anInt2640 = 0;
		current_standing_frameid = 0;
		anInt2638 = -1000;
		anInt2660 = 0;
		anInt2644 = 0;
		current_performing_tick = 0;
		current_spotanim_tick = 0;
		waypointsX = new int[10];
		aClass16_2670 = null;
		anInt2632 = 0;
		aBoolean2810 = false;
		anInt2820 = -32768;
		anIntArray2687 = new int[4];
		seq_replays_done = 0;
		current_performing_seqid = -1;
		turnAnimation = -1;
		facingOffsetX = 0;
		facingOffsetY = 0;
		standAnimation = -1;
		waypointsY = new int[10];
		turn90ccw = -1;
		alsoTurn = -1;
		walkAnimation = -1;
		anIntArray2669 = new int[4];
		current_spotanim_frameid = 0;
		anIntArray2652 = new int[4];
		anInt2828 = 0;
		anInt2713 = 0;
		current_spotanimid = -1;
		size = 1;
		anInt2701 = 0;
		turn90cw = -1;
		lastUpdate = 0;
		runAnimation = -1;
		current_standing_animation = -1;
		current_performing_frameid = 0;
		faceIndex = -1;
		aBooleanArray2718 = new boolean[10];
	}

	static {
		aClass16_2666 = RSString.createString("May");
		aClass16_2677 = RSString.createString("Apr");
		aClass16_2646 = RSString.createString("Jul");
		aClass16_2675 = RSString.createString("Aug");
		aClass16_2672 = RSString.createString("skill)2");
		aClass16_2683 = RSString.createString("Dec");
		aClass16_2651 = RSString.createString("Update)2Liste geladen)3");
		aClass16_2702 = RSString.createString("Nov");
		aClass16_2679 = aClass16_2672;
		aClass16_2705 = RSString.createString("cookiehost");
		aClass16_2712 = RSString.createString("Jun");
		aClass16_2699 = RSString.createString("Jan");
		aClass16_2662 = RSString.createString("Sep");
		aClass16_2715 = RSString.createString("Loading fonts )2 ");
		aClass16_2703 = aClass16_2715;
		aClass16_2711 = RSString.createString("Oct");
		aClass16Array2654 = new RSString[] { aClass16_2699, aClass16_2667, aClass16_2665, aClass16_2677, aClass16_2666, aClass16_2712, aClass16_2646, aClass16_2675, aClass16_2662, aClass16_2711, aClass16_2702, aClass16_2683 };
	}
}
