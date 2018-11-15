package com.jagex;
/* InvType - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class InvType extends Queuable
{
	static RSString aClass16_4186;
	static RSString aClass16_4187;
	static MemoryCache invTypeMap = new MemoryCache(64);
	static Js5 invTypeContainer;
	public int size = 0;
	public static RSString aClass16_4190;
	public static RSString aClass16_4194 = RSString.createString("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789(Q(R+R(T(U^(V(Z(X(Y)2_=)0[*U]*W;:(W@(S*X)1<)3>)4?*6*V ");
	static RSString aClass16_4197;
	public static RSString aClass16_4199;
	static RSString aClass16_4200;
	static RSString aClass16_4201;

    static final InvType getInvType(int i) {
        InvType invType = (InvType) invTypeMap.get(i);
        if (invType != null) {
            return invType;
        }
        byte[] bs = invTypeContainer.get_file(5, i);
        invType = new InvType();
        if (bs != null) {
            invType.load(new Packet(bs));
        }
        invTypeMap.put(i, invType);
        return invType;
    }

    public final void parseOpcode(int opcode, Packet class23_sub5) {
		if (opcode == 2) {
			size = class23_sub5.g2();
		}
	}
	
	static final void method828(byte b, long l) {
		if ((l ^ 0xffffffffffffffffL) != -1L) {
			GameClient.outBuffer.putOpcode(244);
			if (b >= 51) {
				GameClient.outBuffer.putLong(l, (byte) -124);
			}
		}
	}
	
	final void load(Packet buffer) {
		for (;;) {
			int opcode = buffer.g1();
			if ((opcode ^ 0xffffffff) == -1) {
				break;
			}
			parseOpcode(opcode, buffer);
		}
	}

	public static void destruct() {
		NPCType.npcContainer = null;
		aClass16_4194 = null;
		aClass16_4199 = null;
		aClass16_4187 = null;
		aClass16_4200 = null;
		aClass16_4190 = null;
		aClass16_4186 = null;
		aClass16_4201 = null;
		aClass16_4197 = null;
		invTypeMap = null;
	}
	
	static {
		aClass16_4187 = RSString.createString("Sie k-Onnen sich selbst nicht selbst auf Ihre Ignorieren)2Liste setzen(Q");
		aClass16_4199 = RSString.createString("Drop");
		aClass16_4197 = aClass16_4194;
		aClass16_4186 = aClass16_4199;
		aClass16_4190 = RSString.createString("Loaded textures");
		aClass16_4200 = aClass16_4190;
		aClass16_4201 = RSString.createString("Bitte geben Sie Ihren Benutzernamen ein)3");
	}
}
