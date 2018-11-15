package com.jagex;
/* Class23_Sub13_Sub14 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Varbit extends Queuable
{
	public static int[] anIntArray4001;
	public static int anInt4002 = 0;
	static MemoryCache varbitMap;
	static Js5 varbitContainer;
	public static RSString aClass16_4003;
	static RSString aClass16_4004 = RSString.createString("Begeben Sie sich in ein freies Gebiet)1 um");
	static RSString aClass16_4005;
	public static int anInt4006;
	static RSInterface aClass64_4007;
	static RSString aClass16_4009;
	public int mostBit;
	public int configId;
	public static int anInt4013;
	public int leastBit;

	static final Varbit getVarbitDefinition(int fileId) {
		Varbit def = (Varbit) varbitMap.get(fileId);
		if (def != null) {
			return def;
		}
		byte[] bs = varbitContainer.get_file(getVarbitArchiveId(fileId), getVarbitFileId(fileId));
		def = new Varbit();
		if (bs != null) {
			def.parseOpcodes(new Packet(bs));
		}
		varbitMap.put(fileId, def);
		return def;
	}

	static final int getVarbitArchiveId(int i) {
		return i >>> 10;
	}

	static final int getVarbitFileId(int i) {
		return 0x3ff & i;
	}

	static final void setConfigFileValue(int dummy, int fileId, int value) {
		Varbit def = Varbit.getVarbitDefinition(fileId);
		int i_32_ = def.configId;
		int last = def.mostBit;
		int first = def.leastBit;
		int calcedValue = StaticMethods.variousSettings[last - first];
		if (value < 0 || calcedValue < value) {
			value = 0;
		}
		calcedValue <<= first;
		GameClient.configs[i_32_] = MathUtils.doBitwiseOr(MathUtils.bitAnd(calcedValue ^ 0xffffffff, GameClient.configs[i_32_]), MathUtils.bitAnd(value << first, calcedValue));
	}

    static final int getConfigFileValue(int i_0_) {
        Varbit def = getVarbitDefinition(i_0_);
        int i_1_ = def.leastBit;
        int i_2_ = def.mostBit;
        int i_3_ = StaticMethods.variousSettings[i_2_ - i_1_];
        int i_4_ = def.configId;
		//System.out.println(i_4_);
		return i_3_ & GameClient.configs[i_4_] >> i_1_;
    }

    public static final void clearVarbitList(int i) {
        varbitMap.clear();
    }

    public final void parse(int opcode, Packet buffer) {
		if (opcode == 1) {
			configId = buffer.g2();
			leastBit = buffer.g1();
			mostBit = buffer.g1();
		}
	}
	
	final void parseOpcodes(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			parse(opcode, buffer);
		}
	}
	
	public static void method768() {
		varbitMap = null;
		aClass64_4007 = null;
		aClass16_4003 = null;
		aClass16_4005 = null;
		aClass16_4004 = null;
		anIntArray4001 = null;
		aClass16_4009 = null;
	}
	
	static {
		anIntArray4001 = new int[] { 1, 0, 0, 0, 1, 0, 2, 1, 1, 1, 0, 2, 0, 0, 1, 0 };
		aClass16_4005 = RSString.createString("gelb:");
		aClass16_4003 = RSString.createString("wishes to trade with you)3");
		aClass64_4007 = null;
		anInt4013 = -1;
		aClass16_4009 = aClass16_4003;
	}
}
