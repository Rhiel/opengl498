package com.jagex;
/* Class33 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class33 implements Runnable
{
	public SignLink aSignLink_510;
	volatile boolean aBoolean511;
	volatile Stereo[] aStereoArray512 = new Stereo[2];
	static MemoryCache cached_sprites = new MemoryCache(200);
	volatile boolean aBoolean517;
	static RSString aClass16_518 = RSString.createString("Bitte warten Sie )2 es wird versucht)1 die Verbindung wiederherzustellen)3");
	static RSString modIcon = RSString.createString("<img=0>");
	//static BigInteger aBigInteger520;
	public static int anInt521;
	static RSString p12_full = RSString.createString("p12_full");
	
	public static void method969(byte b) {
		if (b <= 66) {
			cached_sprites = null;
		}
		modIcon = null;
		//aBigInteger520 = null;
		cached_sprites = null;
		aClass16_518 = null;
		p12_full = null;
	}
	
	@Override
	public final void run() {
		aBoolean517 = true;
		try {
			while (!aBoolean511) {
				for (int i = 0; i < 2; i++) {
					Stereo stereo = aStereoArray512[i];
					if (stereo != null) {
						stereo.method85((byte) -39);
					}
				}
				TimeTools.sleep(10L);
				GameShell.create_dummy_actionevent(null, 0, aSignLink_510);
			}
		} catch (Exception exception) {
			ForceMovement.sendError(95, exception, null);
		} finally {
			aBoolean517 = false;
		}
	}
	
	Class33() {
		aBoolean511 = false;
		aBoolean517 = false;
	}
	
//	static {
//		aBigInteger520 = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
//	}
}
