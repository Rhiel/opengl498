package com.jagex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jagex.core.tools.MathTools;

public class EnumType extends Queuable {

	public static MemoryCache recentUse = new MemoryCache(128);

	public RSString defaultString;
	public Map mappedEntries;
	public Object[] indexedEntries;
	public HashMap reverse;
	public char valueType;
	public static int anInt3990;
	public int defaultInteger;
	public char keyType;
	public int totalEntries;

	public EnumType() {
		defaultString = Class73.aClass16_1322;
	}

	private void decode(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}
			decode(buffer, opcode);
		}

	}

	private void decode(Packet buffer, int opcode) {
		if (opcode == 1) {
			keyType = ParamType.getCharFromByte(buffer.g1s());
		} else if (opcode == 2) {
			valueType = ParamType.getCharFromByte(buffer.g1s());
		} else if (opcode == 3) {
			defaultString = buffer.gstr();
		} else if (opcode == 4) {
			defaultInteger = buffer.g4();
		} else if (opcode == 5 || opcode == 6) {
			totalEntries = buffer.g2();
			mappedEntries = new HashMap(MathTools.get_greater_pow2(totalEntries));
			for (int index = 0; index < totalEntries; index++) {
				int key = buffer.g4();
				Linkable parser;
				if (opcode != 5) {
					parser = new IntegerNode(buffer.g4());
				} else {
					parser = new StringNode(buffer.gstr());
				}
				mappedEntries.put(key, parser);
			}
		} else if (opcode == 7 || opcode == 8) {
			int biggestKey = buffer.g2();
			totalEntries = buffer.g2();
			indexedEntries = new Object[biggestKey];
			for (int i = 0; i < totalEntries; i++) {
				int key = buffer.g2();
				if (opcode == 7) {
					indexedEntries[key] = new StringNode(buffer.gstr());
				} else {
					indexedEntries[key] = new IntegerNode(buffer.g4());
				}
			}
		}
	}

	private String dumpValues() {
		StringBuilder builder = new StringBuilder();
		if (mappedEntries != null) {
			builder.append('[');
			builder.append('\n');
			for (Object key : mappedEntries.keySet()) {
				Object value = getEntry((int) key);
				builder.append('\t');
				builder.append(key).append('=');
				if (value instanceof StringNode) {
					builder.append('\"').append(((StringNode) value).value).append('\"');
				} else {
					builder.append(((IntegerNode) value).value);
				}
				builder.append('\n');
			}
			builder.append(']');
		}
		if (indexedEntries != null) {
			builder.append('[');
			builder.append('\n');
			for (Object key : indexedEntries) {
				Object value = getEntry((int) key);
				builder.append('\t');
				builder.append(key).append('=');
				if (value instanceof StringNode) {
					builder.append('\"').append(((StringNode) value).value).append('\"');
				} else {
					builder.append(((IntegerNode) value).value);
				}
				builder.append('\n');
			}
			builder.append(']');
		}
		return builder.toString();
	}

	public void buildOptionsReverse() {
		HashMap hashmap = new HashMap();
		if (null != indexedEntries) {
			for (int i_9_ = 0; i_9_ < indexedEntries.length; i_9_++) {
				if (indexedEntries[i_9_] != null) {
					Object object = indexedEntries[i_9_];
					List list = (List) hashmap.get(object);
					if (null == list) {
						list = new LinkedList();
						hashmap.put(object, list);
					}
					list.add(new Integer(i_9_));
				}
			}
		} else if (null != mappedEntries) {
			Iterator iterator = mappedEntries.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Entry) iterator.next();
				Object object = entry.getValue();
				List list = (List) hashmap.get(object);
				if (list == null) {
					list = new LinkedList();
					hashmap.put(object, list);
				}
				list.add(entry.getKey());
			}
		} else {
			throw new IllegalStateException();
		}
		reverse = new HashMap();
		Iterator iterator = hashmap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			List list = (List) entry.getValue();
			int[] is = new int[list.size()];
			int i_10_ = 0;
			Iterator iterator_11_ = list.iterator();
			while (iterator_11_.hasNext()) {
				Integer integer = (Integer) iterator_11_.next();
				is[i_10_++] = integer.intValue();
			}
			if (indexedEntries == null) {
				Arrays.sort(is);
			}
			reverse.put(entry.getKey(), is);
		}
	}

	public boolean hasOutput(Object object) {
		if (totalEntries == 0) {
			return false;
		}
		if (null == reverse) {
			buildOptionsReverse();
		}
		return reverse.containsKey(object);
	}

	public int[] getReverse(Object object) {
		if (totalEntries == 0) {
			return null;
		}
		if (reverse == null) {
			buildOptionsReverse();
		}
		return (int[]) reverse.get(object);
	}

	public Object getEntry(int key) {
		if (indexedEntries != null) {
			if (key < 0 || key >= indexedEntries.length) {
				return null;
			}
			return indexedEntries[key];
		}
		if (mappedEntries != null) {
			return mappedEntries.get(new Integer(key));
		}
		return null;
	}

	public int getInteger(int key) {
		Object object = getEntry(key);
		if (object == null) {
			return defaultInteger;
		}
		return ((IntegerNode) object).value;
	}

	public RSString getString(int key) {
		Object object = getEntry(key);
		if (object == null) {
			return defaultString;
		}
		return ((StringNode) object).value;

	}

	public int getSize() {
		return totalEntries;
	}

	public static EnumType list(int enumid) {
		EnumType type = (EnumType) recentUse.get(enumid);
		if (type != null) {
			return type;
		}
		byte[] data = Class25.enums_js5.get_file(getGroupId(enumid), getFileId(enumid));
		type = new EnumType();
		if (data != null) {
			type.decode(new Packet(data));
		} else {
			DebugMissing.notify_enum(enumid);
		}
		recentUse.put(enumid, type);
		return type;
	}

	private static int getGroupId(int enumid) {
		return enumid >>> 8;
	}

	private static int getFileId(int enumid) {
		return enumid & 0xff;
	}

}
