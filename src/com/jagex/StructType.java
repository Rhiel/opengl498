package com.jagex;

import com.jagex.core.tools.MathTools;

/**
 * Created by Chris on 4/20/2017.
 */
public class StructType extends Queuable {

	public HashTable values;
	static Js5 structTypeContainer;
	static MemoryCache structTypeMap = new MemoryCache(64);

	public static StructType getStructType(int id) {
		StructType structType = (StructType) structTypeMap.get(id);
		if (structType != null) {
			return structType;
		}
		byte[] bs = structTypeContainer.get_file(26, id);
		structType = new StructType();
		if (bs != null) {
			structType.readValueLoop(new Packet(bs));
		} else {
			DebugMissing.notify_struct(id);
		}
		structTypeMap.put(id, structType);
		return structType;
	}

	public void readValueLoop(Packet buffer) {
		try {
			for (;;) {
				int opcode = buffer.g1();
				if (opcode == 0) {
					break;
				}
				parseOpcode(opcode, buffer);
			}
		} catch (RuntimeException runtimeexception) {
			runtimeexception.printStackTrace();
		}
	}

	public void parseOpcode(int opcode, Packet buffer) {
		try {
			if (249 == opcode) {
				int i = buffer.g1();
				if (values == null) {
					int i_4_ = MathTools.get_greater_pow2(i);
					values = new HashTable(i_4_);
				}
				for (int i_5_ = 0; i_5_ < i; i_5_++) {
					boolean bool = 1 == buffer.g1();
					int key = buffer.g3();
					Linkable valueNode;
					if (bool) {
						valueNode = new StringNode(buffer.gstr());
					} else {
						valueNode = new IntegerNode(buffer.g4());
					}
					values.put(key, valueNode);
				}
			}
		} catch (RuntimeException runtimeexception) {
			runtimeexception.printStackTrace();
		}
	}

	public static void setConfigCache(Js5 worker) {
		structTypeContainer = worker;
	}

	public int getIntValue(int arg1, int arg2) {
		if (values == null) {
			return arg2;
		}
		IntegerNode integerNode = (IntegerNode) values.get(arg1);
		if (integerNode == null) {
			return arg2;
		}
		return integerNode.value;
	}

	public RSString getStringValue(int arg1, RSString arg2) {
		if (values == null) {
			return arg2;
		}
		StringNode stringNode = (StringNode) values.get(arg1);
		if (stringNode == null) {
			return arg2;
		}
		return stringNode.value;
	}

}
