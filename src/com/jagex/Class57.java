package com.jagex;
/* Class57 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class57
{
	static RSString aClass16_898;
	public static RSString aClass16_899;
	static RSString tradeRequest = RSString.createString(":tradereq:");
	public static int anInt901 = 0;
	
	static final void method1189(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_) {
		if ((i_4_ ^ 0xffffffff) <= (VarpDefinition.anInt3728 ^ 0xffffffff) && (Class35.anInt554 ^ 0xffffffff) <= (i_4_ ^ 0xffffffff) && VarpDefinition.anInt3728 <= i_7_ && (i_7_ ^ 0xffffffff) >= (Class35.anInt554 ^ 0xffffffff) && i_8_ >= VarpDefinition.anInt3728 && Class35.anInt554 >= i_8_ && VarpDefinition.anInt3728 <= i_5_ && Class35.anInt554 >= i_5_ && (Class88.anInt1503 ^ 0xffffffff) >= (i_6_ ^ 0xffffffff) && (i_6_ ^ 0xffffffff) >= (StaticMethods.anInt3435 ^ 0xffffffff) && i >= Class88.anInt1503 && StaticMethods.anInt3435 >= i && i_0_ >= Class88.anInt1503 && (i_0_ ^ 0xffffffff) >= (StaticMethods.anInt3435 ^ 0xffffffff) && (Class88.anInt1503 ^ 0xffffffff) >= (i_1_ ^ 0xffffffff) && (i_1_ ^ 0xffffffff) >= (StaticMethods.anInt3435 ^ 0xffffffff)) {
			StaticMethods2.method620(i_1_, i_0_, i_3_, i_8_, i_7_, i_6_, i_4_, i_5_, i);
		} else {
			StaticMethods2.method1351(i_0_, i_7_, i, i_6_, (byte) 91, i_3_, i_1_, i_5_, i_4_, i_8_);
		}
		if (i_2_ == -2) {
		}
	}
	
	public static void method1190(byte b) {
		aClass16_898 = null;
		if (b != 107) {
			method1190((byte) -109);
		}
		aClass16_899 = null;
		tradeRequest = null;
	}
	
	static final void method1191(int animId, NPC npc, int i_9_, int i_10_) {
		if (i_10_ == -1) {
			if ((npc.current_performing_seqid ^ 0xffffffff) == (animId ^ 0xffffffff) && animId != -1) {
				SeqType anim = SeqTypeList.list(animId);
				int i_11_ = anim.loop_type;
				if (i_11_ == 1) {
					npc.next_performing_frameid = 1;
					npc.current_performing_frameid = 0;
					npc.seq_replays_done = 0;
					npc.anInt2828 = i_9_;
					npc.current_performing_tick = 0;
					Class21.playAnimationSound(npc, npc.bound_extents_z, npc.bound_extents_x, anim, npc.current_performing_frameid);
				}
				if (i_11_ == 2) {
					npc.seq_replays_done = 0;
				}
			} else if (animId == -1 || npc.current_performing_seqid == -1 || (SeqTypeList.list(animId).priority ^ 0xffffffff) <= (SeqTypeList.list(npc.current_performing_seqid).priority ^ 0xffffffff)) {
				npc.current_performing_seqid = animId;
				npc.anInt2828 = i_9_;
				npc.anInt2640 = npc.anInt2660;
				npc.next_performing_frameid = 1;
				npc.seq_replays_done = 0;
				npc.current_performing_frameid = 0;
				npc.current_performing_tick = 0;
				if ((npc.current_performing_seqid ^ 0xffffffff) != 0) {
					Class21.playAnimationSound(npc, npc.bound_extents_z, npc.bound_extents_x, SeqTypeList.list(npc.current_performing_seqid), npc.current_performing_frameid);
				}
			}
		}
	}
	
	static {
		aClass16_899 = RSString.createString("You have only just left another world)3");
		aClass16_898 = aClass16_899;
	}
}
