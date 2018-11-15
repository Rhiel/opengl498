package com.jagex;
/* Class23_Sub13_Sub23 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.jagex.core.tools.MathTools;

public class CS2ScriptDefinition extends Queuable {
	static RSString aClass16_4231 = RSString.createString("0(U");
	public static RSString aClass16_4232;
	public static RSString aClass16_4233;
	public int string_var_count;
	public int long_var_count;
	public HashTable[] jump_tables;
	public int[] opcodes;
	public RSString[] str_operands;
	public long[] long_operands;
	public static RSString aClass16_4238;
	public static RSString aClass16_4239 = RSString.createString("Jan");
	public static RSString aClass16_4240;
	public int int_arg_count;
	public static RSString aClass16_4242;
	public int string_arg_count;
	public int long_arg_count;
	public int int_var_count;
	public static Interface2 anInterface2_4248;
	public static RSString aClass16_4249;
	public static RSString aClass16_4250;
	public RSString name;
	static RSString[] aClass16Array4252;
	public int[] int_operands;
	public static RSString aClass16_4254;
	public static RSString aClass16_4255;
	public static RSInterface aClass64_4257;
	public static RSString aClass16_4258;
	public static int[] anIntArray4259;
	public static RSString aClass16_4260;
	static boolean aBoolean4262;
	public int scriptid;

	public static final void method844(InteractiveEntity interactiveObject) {
		for (int i = interactiveObject.anInt601; i <= interactiveObject.anInt613; i++) {
			for (int i_0_ = interactiveObject.anInt607; i_0_ <= interactiveObject.anInt599; i_0_++) {
				Ground class23_sub1 = com.rs2.client.scene.Scene.current_grounds[interactiveObject.anInt598][i][i_0_];
				if (class23_sub1 != null) {
					for (int i_1_ = 0; i_1_ < class23_sub1.num_interactives; i_1_++) {
						if (class23_sub1.interactives[i_1_] == interactiveObject) {
							class23_sub1.num_interactives--;
							for (int i_2_ = i_1_; i_2_ < class23_sub1.num_interactives; i_2_++) {
								class23_sub1.interactives[i_2_] = class23_sub1.interactives[i_2_ + 1];
								class23_sub1.anIntArray2024[i_2_] = class23_sub1.anIntArray2024[i_2_ + 1];
							}
							class23_sub1.interactives[class23_sub1.num_interactives] = null;
							break;
						}
					}
					class23_sub1.flags = 0;
					for (int i_3_ = 0; i_3_ < class23_sub1.num_interactives; i_3_++) {
						class23_sub1.flags |= class23_sub1.anIntArray2024[i_3_];
					}
				}
			}
		}
	}

	public static void method845(int i) {
		aClass16_4240 = null;
		aClass16_4250 = null;
		aClass16_4233 = null;
		aClass16_4249 = null;
		aClass64_4257 = null;
		aClass16_4260 = null;
		aClass16_4232 = null;
		aClass16Array4252 = null;
		aClass16_4254 = null;
		SpriteLoader.sprites_pixels = null;
		aClass16_4231 = null;
		anInterface2_4248 = null;
		aClass16_4239 = null;
		QuickChatDefinition.quickChatMap = null;
		aClass16_4258 = null;
		aClass16_4238 = null;
		aClass16_4242 = null;
		aClass16_4255 = null;
		anIntArray4259 = null;
	}

	static final int toLowerCase(int i, int i_33_) {
		if (i >= 65 && i <= 90 || i >= 192 && i <= 222 && i != 215) {
			return i - -32;
		}
		if (i_33_ != 192) {
			return -10;
		}
		if (i == 159) {
			return 255;
		}
		if (i == 140) {
			return 156;
		}
		return i;
	}

	static {
		aClass16_4238 = RSString.createString("Nov");
		aClass16_4232 = RSString.createString("Feb");
		aClass16_4254 = RSString.createString("Dec");
		aClass16_4242 = RSString.createString("May");
		aClass16_4240 = RSString.createString("Jul");
		anInterface2_4248 = null;
		aClass16_4249 = RSString.createString("Apr");
		aClass16_4250 = RSString.createString("Sep");
		aClass16_4258 = RSString.createString("Jun");
		aClass16_4233 = RSString.createString("Aug");
		aClass16_4255 = RSString.createString("Oct");
		aClass16_4260 = RSString.createString("Mar");
		anIntArray4259 = new int[] { 1, 2, 4, 8 };
		aClass16Array4252 = new RSString[] { aClass16_4239, aClass16_4232, aClass16_4260, aClass16_4249, aClass16_4242, aClass16_4258, aClass16_4240, aClass16_4233, aClass16_4250, aClass16_4255, aClass16_4238, aClass16_4254 };
		QuickChatDefinition.quickChatMap = new MemoryCache(64);
		aBoolean4262 = false;
	}

	private static final int MAGIC_CODE = 'N' << 16 | 'E' << 8 | 'W';

	static final CS2ScriptDefinition getCS2ScriptDefinition(int scriptId, int dummy) {

		CS2ScriptDefinition script = (CS2ScriptDefinition) Class23_Sub10_Sub3.aJList_3649.get(scriptId);
		if (script != null) {
			return script;
		}
		byte[] bs = CacheConstants.scriptCacheIdx.get_file(scriptId, 0);
		if (bs == null) {
			if (DebugMissing.ENABLED && !CacheConstants.scriptCacheIdx.is_valid_file(scriptId, 0)) {
				DebugMissing.notify_script(scriptId);
			}
			return null;
		}
		script = new CS2ScriptDefinition();
		Packet packet = new Packet(bs);
		boolean newFormat = packet.g3() == MAGIC_CODE;
		if (!newFormat) {
			packet.index -= 3;
		}
		packet.index = packet.byteBuffer.length - 2;
		int i_19_ = packet.g2();
		int end_of_instructions = (newFormat ? -18 : -14) + packet.byteBuffer.length - i_19_;
		packet.index = end_of_instructions;
		int instr_count = packet.g4();
		script.int_var_count = packet.g2();
		script.string_var_count = packet.g2();
		if (newFormat) {
			script.long_var_count = packet.g2();
		}
		script.int_arg_count = packet.g2();
		script.string_arg_count = packet.g2();
		if (newFormat) {
			script.long_arg_count = packet.g2();
		}
		int jump_table_count = packet.g1();
		if (jump_table_count > 0) {
			script.jump_tables = new HashTable[jump_table_count];
			for (int jt_idx = 0; jt_idx < jump_table_count; jt_idx++) {
				int jt_size = packet.g2();
				HashTable jump_table = new HashTable(MathTools.get_greater_pow2(jt_size));
				script.jump_tables[jt_idx] = jump_table;
				while (jt_size-- > 0) {
					int key = packet.g4();
					int target = packet.g4();
					jump_table.put(key, new IntegerNode(target));
				}
			}
		}
		packet.index = newFormat ? 3 : 0;
		script.name = packet.method451((byte) -115);
		script.int_operands = new int[instr_count];
		script.str_operands = new RSString[instr_count];
		script.opcodes = new int[instr_count];
		int op_ptr = 0;
		while (end_of_instructions > packet.index) {
			int opcode = packet.g2();
			if (opcode == 3) {
				script.str_operands[op_ptr] = packet.gstr();
			} else if (opcode == 54) {
				if (script.long_operands == null) {
					script.long_operands = new long[instr_count];
				}
				script.long_operands[op_ptr] = packet.getLong();
			} else {
				if (opcode >= (newFormat ? 150 : 100) || opcode == 21 || opcode == 38 || opcode == 39) {
					script.int_operands[op_ptr] = packet.g1();
				} else {
					script.int_operands[op_ptr] = packet.g4();
				}
			}
			script.opcodes[op_ptr++] = opcode;
		}
		script.scriptid = scriptId;
		Class23_Sub10_Sub3.aJList_3649.put(scriptId, script);
		return script;
	}



}
