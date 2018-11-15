package com.jagex;

/**
 * Created by Chris on 4/20/2017.
 */
public class ParamType extends Queuable {

    public int defaultint;
    public char type;
    public RSString defaultstr;
    static Js5 paramTypeContainer;
    static MemoryCache paramTypeMap = new MemoryCache(64);
    boolean autodisable = true;

    public static ParamType getParamType(int id) {
        ParamType paramType = (ParamType) paramTypeMap.get(id);
        if (paramType != null) {
            return paramType;
        }
        byte[] bs = paramTypeContainer.get_file(11, id);
        paramType = new ParamType();
        if (bs != null) {
            paramType.readValueLoop(new Packet(bs));
		} else {
			DebugMissing.notify_param(id);
        }
        paramTypeMap.put(id, paramType);
        return paramType;
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
            if (opcode != 1) {
                if (opcode != 2) {
                    if(opcode != 4) {
                        if (5 == opcode) {
							defaultstr = buffer.gstr();
						}
                    } else {
						autodisable = false;
					}
                } else {
					defaultint = buffer.g4();
				}
            } else {
                type = getCharFromByte(buffer.g1s());
            }
        } catch (RuntimeException runtimeexception) {
            runtimeexception.printStackTrace();
        }
    }

    public static char getCharFromByte(byte b) {
        int i = 0xff & b;
        if (i >= 128 && i < 160) {
            int i_4_ = aCharArray6385[-128 + i];
            if (i_4_ == 0) {
				i_4_ = 63;
			}
            i = i_4_;
        }
        return (char) i;
    }

    public static char[] aCharArray6385
            = { '\u20ac', '\0', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020',
            '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\0',
            '\u017d', '\0', '\0', '\u2018', '\u2019', '\u201c', '\u201d',
            '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161',
            '\u203a', '\u0153', '\0', '\u017e', '\u0178' };

    public static void setConfigCache(Js5 worker) {
        paramTypeContainer = worker;
    }

    public boolean isString() {
        return 115 == type;
    }

}
