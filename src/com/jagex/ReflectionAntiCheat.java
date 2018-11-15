package com.jagex;
/* ReflectionAntiCheat - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionAntiCheat
{
	static RSString aClass16_73;
	public static int anInt74 = 0;
	static NodeDeque request_queue = new NodeDeque();
	public static RSString aClass16_75;
	static RSString aClass16_76;
	static RSString aClass16_77;
	static RSString aClass16_78 = RSString.createString(":duelfriend:");
	public static int anInt79;
	public static RSString aClass16_80;
	static RSString aClass16_83;

	static final void initialize(byte b) {
		request_queue = new NodeDeque();
	}
	
	static final void decode_request(int dummy, Packet buffer, int i_30_, SignLink signLink) {
		ReflectionRequest reflectionRequest = new ReflectionRequest();
		reflectionRequest.op_count = buffer.g1();
		reflectionRequest.link = buffer.g4();
		reflectionRequest.method_requests = new SignlinkRequest[reflectionRequest.op_count];
		reflectionRequest.parameter_data = new byte[reflectionRequest.op_count][][];
		reflectionRequest.int_values = new int[reflectionRequest.op_count];
		reflectionRequest.field_requests = new SignlinkRequest[reflectionRequest.op_count];
		reflectionRequest.opcode = new int[reflectionRequest.op_count];
		reflectionRequest.status = new int[reflectionRequest.op_count];
		String className = null;
		for (int in_ptr = 0; reflectionRequest.op_count > in_ptr; in_ptr++) {
			try {
				int request = buffer.g1();
				if (request != 0 && request != 1 && request != 2) {
					if (request == 3 || request == 4) {
						String ret_type = new String(buffer.gstr().getBytes());
						String name = new String(buffer.gstr().getBytes());
						int par_count = buffer.g1();
						String[] par_type_names = new String[par_count];
						for (int par_ptr = 0; par_count > par_ptr; par_ptr++) {
							par_type_names[par_ptr] = new String(buffer.gstr().getBytes());
						}
						byte[][] is = new byte[par_count][];
						if (request == 3) {
							for (int par_ptr = 0; par_count > par_ptr; par_ptr++) {
								int length = buffer.g4();
								is[par_ptr] = new byte[length];
								buffer.get(is[par_ptr], 0, length);
							}
						}
						reflectionRequest.opcode[in_ptr] = request;
						@SuppressWarnings("rawtypes")
						Class[] par_types = new Class[par_count];
						for (int i_39_ = 0; (i_39_ ^ 0xffffffff) > (par_count ^ 0xffffffff); i_39_++) {
							className = par_type_names[i_39_];
							par_types[i_39_] = get_type_class(par_type_names[i_39_], (byte) -65);
						}
						reflectionRequest.method_requests[in_ptr] = signLink.get_declared_method(name, par_types, get_type_class(ret_type, (byte) -65), 2);
						reflectionRequest.parameter_data[in_ptr] = is;
					}
				} else {
					String type = new String(buffer.gstr().getBytes());
					int i_8_ = 0;
					String name = new String(buffer.gstr().getBytes());
					if (request == 1) {
						i_8_ = buffer.g4();
					}
					reflectionRequest.opcode[in_ptr] = request;
					reflectionRequest.int_values[in_ptr] = i_8_;
					reflectionRequest.field_requests[in_ptr] = signLink.get_declared_field(get_type_class(type, (byte) -65), -99, name);
				}
			} catch (ClassNotFoundException classnotfoundexception) {
				System.out.println("Class not found : " + className);
				reflectionRequest.status[in_ptr] = -1;
			} catch (SecurityException securityexception) {
				reflectionRequest.status[in_ptr] = -2;
			} catch (NullPointerException nullpointerexception) {
				reflectionRequest.status[in_ptr] = -3;
			} catch (Exception exception) {
				reflectionRequest.status[in_ptr] = -4;
			} catch (Throwable throwable) {
				reflectionRequest.status[in_ptr] = -5;
			}
		}
		request_queue.add_last(reflectionRequest);
	}

	@SuppressWarnings("rawtypes")
	static final Class get_type_class(String name, byte b) throws ClassNotFoundException {
		if (name.equals("B")) {
			return Byte.TYPE;
		}
		if (name.equals("I")) {
			return Integer.TYPE;
		}
		if (name.equals("S")) {
			return Short.TYPE;
		}
		if (name.equals("J")) {
			return Long.TYPE;
		}
		if (name.equals("Z")) {
			return Boolean.TYPE;
		}
		if (name.equals("F")) {
			return Float.TYPE;
		}
		if (name.equals("D")) {
			return Double.TYPE;
		}
		if (name.equals("C")) {
			return Character.TYPE;
		}
		return Class.forName(name);
	}

	static final void process(ISAACPacket buffer, int i, byte b) {
		if (b == 35) {
			for (;;) {
				ReflectionRequest reflectionRequest = (ReflectionRequest) request_queue.get_first();
				if (reflectionRequest == null) {
					break;
				}
				boolean bool = false;
				for (int i_18_ = 0; (reflectionRequest.op_count ^ 0xffffffff) < (i_18_ ^ 0xffffffff); i_18_++) {
					if (reflectionRequest.field_requests[i_18_] != null) {
						if (reflectionRequest.field_requests[i_18_].status == 2) {
							reflectionRequest.status[i_18_] = -5;
						}
						if (reflectionRequest.field_requests[i_18_].status == 0) {
							bool = true;
						}
					}
					if (reflectionRequest.method_requests[i_18_] != null) {
						if (reflectionRequest.method_requests[i_18_].status == 2) {
							reflectionRequest.status[i_18_] = -6;
						}
						if (reflectionRequest.method_requests[i_18_].status == 0) {
							bool = true;
						}
					}
				}
				if (bool) {
					break;
				}
				buffer.putOpcode(i);
				buffer.p1(0);
				int i_19_ = buffer.index;
				buffer.p4(reflectionRequest.link);
				for (int i_20_ = 0; (reflectionRequest.op_count ^ 0xffffffff) < (i_20_ ^ 0xffffffff); i_20_++) {
					if ((reflectionRequest.status[i_20_] ^ 0xffffffff) != -1) {
						buffer.p1(reflectionRequest.status[i_20_]);
					} else {
						try {
							int i_21_ = reflectionRequest.opcode[i_20_];
							if (i_21_ != 0) {
								if (i_21_ != 1) {
									if (i_21_ == 2) {
										Field field = (Field) reflectionRequest.field_requests[i_20_].result;
										int i_22_ = field.getModifiers();
										buffer.p1(0);
										buffer.p4(i_22_);
									}
								} else {
									Field field = (Field) reflectionRequest.field_requests[i_20_].result;
									field.setInt(null, reflectionRequest.int_values[i_20_]);
									buffer.p1(0);
								}
							} else {
								Field field = (Field) reflectionRequest.field_requests[i_20_].result;
								int i_23_ = field.getInt(null);
								buffer.p1(0);
								buffer.p4(i_23_);
							}
							if (i_21_ != 3) {
								if (i_21_ == 4) {
									Method method = (Method) reflectionRequest.method_requests[i_20_].result;
									int i_24_ = method.getModifiers();
									buffer.p1(0);
									buffer.p4(i_24_);
								}
							} else {
								Method method = (Method) reflectionRequest.method_requests[i_20_].result;
								byte[][] bs = reflectionRequest.parameter_data[i_20_];
								Object[] objects = new Object[bs.length];
								for (int i_25_ = 0; (bs.length ^ 0xffffffff) < (i_25_ ^ 0xffffffff); i_25_++) {
									ObjectInputStream objectinputstream = new ObjectInputStream(new ByteArrayInputStream(bs[i_25_]));
									objects[i_25_] = objectinputstream.readObject();
								}
								Object object = method.invoke(null, objects);
								if (object == null) {
									buffer.p1(0);
								} else if (!(object instanceof Number)) {
									if (object instanceof RSString) {
										buffer.p1(2);
										buffer.putString((RSString) object, -106);
									} else {
										buffer.p1(4);
									}
								} else {
									buffer.p1(1);
									buffer.putLong(((Number) object).longValue(), (byte) -111);
								}
							}
						} catch (ClassNotFoundException classnotfoundexception) {
							buffer.p1(-10);
						} catch (java.io.InvalidClassException invalidclassexception) {
							buffer.p1(-11);
						} catch (java.io.StreamCorruptedException streamcorruptedexception) {
							buffer.p1(-12);
						} catch (java.io.OptionalDataException optionaldataexception) {
							buffer.p1(-13);
						} catch (IllegalAccessException illegalaccessexception) {
							buffer.p1(-14);
						} catch (IllegalArgumentException illegalargumentexception) {
							buffer.p1(-15);
						} catch (java.lang.reflect.InvocationTargetException invocationtargetexception) {
							buffer.p1(-16);
						} catch (SecurityException securityexception) {
							buffer.p1(-17);
						} catch (java.io.IOException ioexception) {
							buffer.p1(-18);
						} catch (NullPointerException nullpointerexception) {
							buffer.p1(-19);
						} catch (Exception exception) {
							buffer.p1(-20);
						} catch (Throwable throwable) {
							buffer.p1(-21);
						}
					}
				}
				buffer.addcrc(i_19_);
				buffer.putIndex((byte) 21, -i_19_ + buffer.index);
				reflectionRequest.unlink();
			}
		}
	}

	public static void destruct(int i) {
		aClass16_75 = null;
		aClass16_77 = null;
		aClass16_73 = null;
		if (i != 32) {
			destruct(65);
		}
		aClass16_83 = null;
		aClass16_76 = null;
		aClass16_78 = null;
		aClass16_80 = null;
		//	aBigInteger70 = null;
	}
	
	static final void method51(RSString string, int i) {
		if (GameClient.gameSignlink.applet != null) {
			try {
				RSString class16_42_ = Class75.aClass16_1374.method172(GameClient.gameSignlink.applet);
				RSString class16_43_ = Entity.aClass16_2705.method172(GameClient.gameSignlink.applet);
				RSString class16_44_ = RSString.joinRsStrings(new RSString[] { class16_42_, AbstractTimer.aClass16_306, string, Class87_Sub3.aClass16_2825, class16_43_ });
				if (string.length() == i) {
					class16_44_ = RSString.joinRsStrings(new RSString[] { class16_44_, MonochromeImageCache.aClass16_1672 });
				} else {
					class16_44_ = RSString.joinRsStrings(new RSString[] { class16_44_, IoSession.aClass16_540, ObjType.method729((byte) 86, 94608000000L + TimeTools.getMillis()), Stereo.aClass16_141, Class79.method1361(-16309, 94608000L) });
				}
				RSString.joinRsStrings(new RSString[] { GroundDecoration.aClass16_1217, class16_44_, MemoryCache.aClass16_113 }).method143(0, GameClient.gameSignlink.applet);
			} catch (Throwable throwable) {
				/* empty */
			}
		}
	}
	
	static final void addToIgnore(int i, long name) {
		if ((name ^ 0xffffffffffffffffL) != -1L) {
			if (PlayerRelations.ignoreListSize >= 100) {
				Class95.sendGameMessage(0, i ^ 0xffffffff, StaticMethods.aClass16_2963, StaticMethods.empty_string);
			} else {
				RSString class16 = WallObject.getStringFromLong(-1, name).method154();
				for (int i_45_ = 0; (i_45_ ^ 0xffffffff) > (PlayerRelations.ignoreListSize ^ 0xffffffff); i_45_++) {
					if (name == PlayerRelations.ignoreList[i_45_]) {
						Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { class16, ModelList.aClass16_1431 }), StaticMethods.empty_string);
						return;
					}
				}
				for (int i_46_ = i; (i_46_ ^ 0xffffffff) > (Class45.friends_count ^ 0xffffffff); i_46_++) {
					if ((name ^ 0xffffffffffffffffL) == (NameHashTable.friends_uid[i_46_] ^ 0xffffffffffffffffL)) {
						Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { HashTable.aClass16_1258, class16, Class55.aClass16_864 }), StaticMethods.empty_string);
						return;
					}
				}
				if (class16.equals(GameClient.currentPlayer.username)) {
					Class95.sendGameMessage(0, -1, Class45.aClass16_693, StaticMethods.empty_string);
				} else {
					PlayerRelations.ignoreList[PlayerRelations.ignoreListSize] = name;
					PlayerRelations.ignoreListNames[PlayerRelations.ignoreListSize++] = WallObject.getStringFromLong(i + -1, name);
					Class75.anInt1372 = SomeSoundClass.anInt3589;
					GameClient.outBuffer.putOpcode(137);
					GameClient.outBuffer.putLong(name, (byte) -123);
				}
			}
		}
	}
	
	static {
		aClass16_75 = RSString.createString("M");
		anInt79 = 0;
		aClass16_77 = RSString.createString("Bitte geben Sie Ihr Passwort ein)3");
		aClass16_80 = RSString.createString("Close");
		aClass16_76 = aClass16_75;
		aClass16_83 = aClass16_75;
		aClass16_73 = aClass16_80;
	}

}
